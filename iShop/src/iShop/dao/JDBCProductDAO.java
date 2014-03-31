package iShop.dao;

import iShop.model.Product;

import java.util.List;

public interface JDBCProductDAO {

	public List<Product> getAllProducts();
	public List<Product> getProductsByGroup(int group_id);
	
}
