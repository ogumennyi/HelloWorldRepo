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

	@Override
	public List<Product> getProducts(Integer pageNum, int pageSize, Integer groupId, HashMap<String, String> orderParamsMap) {
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
		sql += " LIMIT "+(pageNum-1)*pageSize+", "+pageSize;
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
	public int getProductsCount(Integer groupId){
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
	
}
