package iShop.dao;

import iShop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCProductDAOImpl implements JDBCProductDAO {

	@Autowired
	private DataSource dataSource;
	 	
	@Override
	public List<Product> getAllProducts(ArrayList<String> orderParams) {
		String sql = "SELECT * FROM t_product";
		String orderByClause = null;
		for(String orderParam : orderParams){
			if(orderByClause==null) orderByClause = " ORDER BY "+orderParam;
			else orderByClause+=", "+orderParam;
		}
		if(orderByClause!=null) sql+=orderByClause;
		System.out.println(sql);
		Connection conn = null;
		try {
			List<Product> productsList = new ArrayList<Product>();
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getInt("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getInt("GROUP_ID"), rs.getFloat("PRODUCT_PRICE"));
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
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public List<Product> getProductsByGroup(int group_id, ArrayList<String> orderParams) {
		String sql = "SELECT * FROM t_product WHERE group_id=?";
		String orderByClause = null;
		for(String orderParam : orderParams){
			if(orderByClause==null) orderByClause = " ORDER BY "+orderParam;
			else orderByClause+=", "+orderParam;
		}
		if(orderByClause!=null) sql+=orderByClause;
		System.out.println(sql);
		Connection conn = null;
		try {
			List<Product> productsList = new ArrayList<Product>();
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, group_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getInt("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getInt("GROUP_ID"), rs.getFloat("PRODUCT_PRICE"));
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
				} catch (SQLException e) {}
			}
		}
	}

}
