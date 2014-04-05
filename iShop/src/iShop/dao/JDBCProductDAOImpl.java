package iShop.dao;

import iShop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCProductDAOImpl implements JDBCProductDAO {

	@Autowired
	private DataSource dataSource;
	private static final int PAGE_SIZE = 10;

	@Override
	public List<Product> getProducts(Integer pageNum, Integer groupId, HashMap<String, String> orderParamsMap) {
		String sql = "SELECT * FROM t_product";
		if(groupId != null)
			sql+=" WHERE group_id="+groupId.toString();
		String orderByClause = null;
		for (String orderParam : orderParamsMap.keySet()) {
			if (orderByClause == null)
				orderByClause = " ORDER BY " + getColumnNameByParam(orderParam) + " " + orderParamsMap.get(orderParam);
			else
				orderByClause += ", " + getColumnNameByParam(orderParam) + " " + orderParamsMap.get(orderParam);
		}
		if (orderByClause != null)
			sql += orderByClause;
		sql += " LIMIT "+(pageNum-1)*PAGE_SIZE+", "+PAGE_SIZE;
		System.out.println(sql);
		Connection conn = null;
		try {
			List<Product> productsList = new ArrayList<Product>();
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getInt("PRODUCT_ID"), rs.getString("PRODUCT_NAME"),
						rs.getInt("GROUP_ID"), rs.getFloat("PRODUCT_PRICE"));
				productsList.add(product);
			}
			rs.close();
			ps.close();
			return productsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<String> getPagesList(Integer pageNum, Integer groupId) {
		ArrayList<String> pagesList = new ArrayList<String>();
		int rowsCount = getRowsCount(groupId);
		if(rowsCount==0){
			pagesList.add("0");
			return pagesList;
		}
		int pagesCount = getRowsCount(groupId)/PAGE_SIZE+1;
		final int EDGE_PAGES_CNT = 1;
		final int NEIGHBOUR_PAGES_CNT = 2;
		if(pageNum <= NEIGHBOUR_PAGES_CNT + EDGE_PAGES_CNT + 1){
			for(int i=1; i<=pageNum; i++){
				pagesList.add(String.valueOf(i));
			}
		} else {
			for(int i=1; i<=EDGE_PAGES_CNT; i++){
				pagesList.add(String.valueOf(i));
			}
			pagesList.add("...");
			for(int i=pageNum-NEIGHBOUR_PAGES_CNT; i<=pageNum; i++){
				pagesList.add(String.valueOf(i));
			}
		}
		if(pageNum >= pagesCount - NEIGHBOUR_PAGES_CNT - EDGE_PAGES_CNT){
			for(int i=pageNum+1; i<=pagesCount; i++){
				pagesList.add(String.valueOf(i));
			}
		} else {
			for(int i=pageNum+1; i<=pageNum+NEIGHBOUR_PAGES_CNT; i++){
				pagesList.add(String.valueOf(i));
			}
			pagesList.add("...");
			for(int i=pagesCount-EDGE_PAGES_CNT; i<=pagesCount; i++){
				pagesList.add(String.valueOf(i));
			}
		}
		System.out.println(pageNum);
		System.out.println(pagesList);
		return pagesList;
	}	

	private String getColumnNameByParam(String param) {
		switch (param) {
		case "p_name_order":
			return "product_name";
		case "p_price_order":
			return "product_price";
		default:
			return null;
		}
	}
	
	private int getRowsCount(Integer groupId){
		String sql = "SELECT COUNT(*) FROM t_product";
		if(groupId != null) 
			sql+=" WHERE group_id="+groupId.toString();
		Connection conn = null;
		int rowsCount = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rowsCount = rs.getInt(1);
			}
			rs.close();
			ps.close();
			return rowsCount;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
}
