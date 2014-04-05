package iShop.dao;

import iShop.model.Product;

import java.util.HashMap;
import java.util.List;

public interface JDBCProductDAO {

	public List<Product> getProducts(Integer pageNum, Integer groupId, HashMap<String, String> orderParamsMap);
	public List<String> getPagesList(Integer currentPage, Integer groupId);
	
}