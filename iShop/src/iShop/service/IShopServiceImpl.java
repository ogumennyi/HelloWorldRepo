package iShop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iShop.dao.JDBCGroupDAO;
import iShop.dao.JDBCProductDAO;
import iShop.model.Group;
import iShop.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IShopServiceImpl implements IShopService {
	
	@Autowired
	private JDBCGroupDAO groupDAO;
	@Autowired
	private JDBCProductDAO productDAO;
	private static final int PAGE_SIZE = 10;
	
	@Override
	@Transactional
	public List<Group> getGroups() {
		return groupDAO.getGroups();
	}

	@Override
	@Transactional
	public List<Product> getProducts(Integer pageNum, Integer groupId, HashMap<String, String> orderParamsMap) {
		return productDAO.getProducts(pageNum, PAGE_SIZE, groupId, orderParamsMap);
	}

	@Override
	@Transactional
	public List<String> getPagesList(Integer pageNum, Integer groupId) {
		ArrayList<String> pagesList = new ArrayList<String>();
		int rowsCount = productDAO.getProductsCount(groupId);
		if(rowsCount==0){
			pagesList.add("0");
			return pagesList;
		}
		int pagesCount = rowsCount/PAGE_SIZE+1;
		final int EDGE_PAGES_CNT = 1;
		final int NEIGHBOUR_PAGES_CNT = 2;
		if(pageNum <= NEIGHBOUR_PAGES_CNT + EDGE_PAGES_CNT + 1){
			for(int i=1; i<=pageNum; i++){
				pagesList.add(String.valueOf(i));
			}
		} else {
			for(int i=1; i<=EDGE_PAGES_CNT; i++){
				pagesList.add(String.valueOf(i));
			}
			pagesList.add("...");
			for(int i=pageNum-NEIGHBOUR_PAGES_CNT; i<=pageNum; i++){
				pagesList.add(String.valueOf(i));
			}
		}
		if(pageNum >= pagesCount - NEIGHBOUR_PAGES_CNT - EDGE_PAGES_CNT){
			for(int i=pageNum+1; i<=pagesCount; i++){
				pagesList.add(String.valueOf(i));
			}
		} else {
			for(int i=pageNum+1; i<=pageNum+NEIGHBOUR_PAGES_CNT; i++){
				pagesList.add(String.valueOf(i));
			}
			pagesList.add("...");
			for(int i=pagesCount-EDGE_PAGES_CNT; i<=pagesCount; i++){
				pagesList.add(String.valueOf(i));
			}
		}
		System.out.println(pageNum);
		System.out.println(pagesList);
		return pagesList;
	}

}
