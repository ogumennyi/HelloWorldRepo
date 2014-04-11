package iShop.service;

import iShop.model.Group;
import iShop.model.Product;

import java.util.List;
import java.util.Map;

public interface IShopService {
	
	public List<Group> getGroups();
	public List<Product> getProducts(Integer pageNum, Integer groupId, Map<String, String> orderParamsMap);
	public List<String> getPagesList(Integer pageNum, Integer groupId);
	
}
