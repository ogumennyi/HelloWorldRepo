package iShop.dao;

import iShop.model.Product;

import java.util.HashMap;
import java.util.List;

public interface JDBCProductDAO {

	public List<Product> getProducts(Integer pageNum, int pageSize,  Integer groupId, HashMap<String, String> orderParamsMap);
	public int getProductsCount(Integer groupId);
	
}