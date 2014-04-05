package iShop.controller;

import java.util.HashMap;
import java.util.List;

import iShop.dao.JDBCGroupDAO;
import iShop.dao.JDBCProductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IPageController {

	@Autowired
	JDBCGroupDAO groupDAO;

	@Autowired
	JDBCProductDAO productDAO;

	@RequestMapping(value = "/ipage", method = RequestMethod.GET)
	public String productsByGroup(ModelMap modelMap, 
			@RequestParam(value = "group_id", required = false) Integer groupId,
			@RequestParam(value = "p_name_order", defaultValue = "none") String pNameOrder,
			@RequestParam(value = "p_price_order", defaultValue = "none") String pPriceOrder,
			@RequestParam(value = "page", required = false) Integer pageNum) {
		if(pageNum==null) pageNum = new Integer(1);
		HashMap<String, String> orderParams = new HashMap<String, String>();
		processOrderParamsByColumnName(orderParams, "p_name_order", pNameOrder, modelMap);
		processOrderParamsByColumnName(orderParams, "p_price_order", pPriceOrder, modelMap);
		modelMap.put("groupsList", groupDAO.getGroups());
		modelMap.put("productsList", productDAO.getProducts(pageNum, groupId, orderParams));
		modelMap.put("group_id", groupId);
		modelMap.put("page", pageNum);
		List<String> pagesList = productDAO.getPagesList(pageNum, groupId);
		modelMap.put("pagesList", pagesList);
		modelMap.put("maxPageNum", pagesList.get(pagesList.size() - 1));
		return "ipage";
	}

	private void processOrderParamsByColumnName(HashMap<String, String> orderParams, String paramName,
			String paramOrder, ModelMap model) {
		if (!"none".equals(paramOrder.toLowerCase()))
			orderParams.put(paramName, paramOrder);
		switch (paramOrder.toLowerCase()) {
		case "none":
			model.put(paramName, "none");
			model.put(paramName + "_n", "asc");
			break;
		case "asc":
			model.put(paramName, "asc");
			model.put(paramName + "_n", "desc");
			break;
		case "desc":
			model.put(paramName, "desc");
			model.put(paramName + "_n", "none");
			break;
		}
	}

}
