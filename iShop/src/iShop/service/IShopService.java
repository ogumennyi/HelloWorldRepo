package iShop.service;

import iShop.model.Group;
import iShop.model.Product;

import java.util.HashMap;
import java.util.List;

public interface IShopService {
	
	public List<Group> getGroups();
	public List<Product> getProducts(Integer pageNum, Integer groupId, HashMap<String, String> orderParamsMap);
	public List<String> getPagesList(Integer pageNum, Integer groupId);
	
}
