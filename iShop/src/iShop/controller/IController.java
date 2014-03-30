package iShop.controller;

import iShop.dao.JDBCGroupDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IController {
	
	@Autowired
	JDBCGroupDAO groupDAO;

	@RequestMapping("/ipage")
    public String ipage(ModelMap model) {
		model.put("groupsList", groupDAO.getGroups());
        System.out.println(groupDAO.getGroups());
        return "ipage";
    }
	
}
