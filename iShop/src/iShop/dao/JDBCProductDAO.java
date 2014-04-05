package iShop.dao;

import iShop.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface JDBCProductDAO {

	public List<Product> getAllProducts(ArrayList<String> p_name_order);
	public List<Product> getProductsByGroup(int group_id, ArrayList<String> p_name_order);
	
}
