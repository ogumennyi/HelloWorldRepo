package iShop.dao;

import iShop.model.Product;

import java.util.List;

public interface JDBCProductDAO {

	public List<Product> getAllProducts(String p_name_order);
	public List<Product> getProductsByGroup(int group_id, String p_name_order);
	
}
