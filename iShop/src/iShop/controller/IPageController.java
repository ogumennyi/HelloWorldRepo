package iShop.controller;

import java.util.ArrayList;

import iShop.dao.JDBCGroupDAO;
import iShop.dao.JDBCProductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String allProducts(@RequestParam("p_name_order") String p_name_order, @RequestParam("p_price_order") String p_price_order, ModelMap modelMap) {
		ArrayList<String> orderParams = new ArrayList<String>();
		processOrderParamsByColumnName(orderParams, "product_name", "p_name_order", p_name_order, modelMap);
		processOrderParamsByColumnName(orderParams, "product_price", "p_price_order", p_price_order, modelMap);
		modelMap.put("groupsList", groupDAO.getGroups());
		modelMap.put("productsList", productDAO.getAllProducts(orderParams));
        return "ipage";
    }
	
	@RequestMapping(value = "/ipage/{group_id}", method = RequestMethod.GET)
    public String productsByGroup(@PathVariable int group_id, @RequestParam("p_name_order") String p_name_order, @RequestParam("p_price_order") String p_price_order, ModelMap modelMap) {
		ArrayList<String> orderParams = new ArrayList<String>();
		processOrderParamsByColumnName(orderParams, "product_name", "p_name_order", p_name_order, modelMap);
		processOrderParamsByColumnName(orderParams, "product_price", "p_price_order", p_price_order, modelMap);
		modelMap.put("groupsList", groupDAO.getGroups());
		modelMap.put("productsList", productDAO.getProductsByGroup(group_id, orderParams));
		modelMap.put("current_group_id", group_id);
        return "ipage";
    }
	
	private void processOrderParamsByColumnName(ArrayList<String> orderParams, String fieldName, String paramName, String paramOrder, ModelMap model){
		if(!"none".equals(paramOrder.toLowerCase())) orderParams.add(fieldName+" "+paramOrder);
		putOrderParamsToModelMap(paramName, paramOrder, model);
	}
	
	private void putOrderParamsToModelMap(String paramName, String paramOrder, ModelMap model){
		switch(paramOrder.toLowerCase()){
			case "none": model.put(paramName, "none"); model.put(paramName+"_n", "asc"); break;
			case "asc": model.put(paramName, "asc"); model.put(paramName+"_n", "desc"); break;
			case "desc": model.put(paramName, "desc"); model.put(paramName+"_n", "none"); break;
		}
	}
	
}
