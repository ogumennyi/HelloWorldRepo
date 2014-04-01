package iShop.controller;

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
public class IController {
	
	@Autowired
	JDBCGroupDAO groupDAO;
	
	@Autowired
	JDBCProductDAO productDAO;

	@RequestMapping(value = "/ipage", method = RequestMethod.GET)
    public String AllProducts(@RequestParam("p_name_order") String p_name_order, ModelMap model) {
		switch(p_name_order.toLowerCase()){
			case "none": model.put("p_name_order", "none"); model.put("p_name_order_n", "asc"); break;
			case "asc": model.put("p_name_order", "asc"); model.put("p_name_order_n", "desc"); break;
			case "desc": model.put("p_name_order", "desc"); model.put("p_name_order_n", "none"); break;
		}
		model.put("groupsList", groupDAO.getGroups());
		model.put("productsList", productDAO.getAllProducts(p_name_order));
        return "ipage";
    }
	
	@RequestMapping(value = "/ipage/{group_id}", method = RequestMethod.GET)
    public String SelectedProducts(@PathVariable int group_id, @RequestParam("p_name_order") String p_name_order, ModelMap model) {
		switch(p_name_order.toLowerCase()){
			case "none": model.put("p_name_order", "none"); model.put("p_name_order_n", "asc"); break;
			case "asc": model.put("p_name_order", "asc"); model.put("p_name_order_n", "desc"); break;
			case "desc": model.put("p_name_order", "desc"); model.put("p_name_order_n", "none"); break;
		}
		model.put("groupsList", groupDAO.getGroups());
		model.put("productsList", productDAO.getProductsByGroup(group_id, p_name_order));
		model.put("current_group_id", group_id);
        return "ipage";
    }
	
}
