package iShop.controller;

import iShop.dao.JDBCGroupDAO;
import iShop.dao.JDBCProductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IController {
	
	@Autowired
	JDBCGroupDAO groupDAO;
	
	@Autowired
	JDBCProductDAO productDAO;

	@RequestMapping(value = "/ipage", method = RequestMethod.GET)
    public String AllProducts(ModelMap model) {
		model.put("groupsList", groupDAO.getGroups());
		model.put("productsList", productDAO.getAllProducts());
        return "ipage";
    }
	
	@RequestMapping(value = "/ipage/{group_id}", method = RequestMethod.GET)
    public String SelectedProducts(@PathVariable int group_id, ModelMap model) {
		model.put("groupsList", groupDAO.getGroups());
		model.put("productsList", productDAO.getProductsByGroup(group_id));
		model.put("current_group_id", group_id);
        return "ipage";
    }
	
}
