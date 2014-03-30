package iShop.controller;

import iShop.dao.JDBCGroupDAO;
import iShop.dao.JDBCProductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IController {
	
	@Autowired
	JDBCGroupDAO groupDAO;
	
	@Autowired
	JDBCProductDAO productDAO;

	@RequestMapping("/ipage")
    public String ipage(ModelMap model) {
		model.put("groupsList", groupDAO.getGroups());
		model.put("productsList", productDAO.getProductsByGroup(1));
        return "ipage";
    }
	
}
