package iShop.dao;

import iShop.model.Product;

import java.util.List;
import java.util.Map;

public interface JDBCProductDAO {

	public List<Product> getProducts(Integer pageNum, int pageSize,  Integer groupId, Map<String, String> orderParamsMap);
	public int getProductsCount(Integer groupId);
	
}