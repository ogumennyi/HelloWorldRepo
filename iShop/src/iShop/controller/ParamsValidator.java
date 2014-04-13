package iShop.controller;

import iShop.dao.JDBCGroupDAO;
import iShop.dao.JDBCProductDAO;
import iShop.exception.CustomException;
import iShop.service.IShopServiceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ParamsValidator implements Validator {
	
	@Autowired
	private JDBCGroupDAO groupDAO;	
	@Autowired
	private JDBCProductDAO productDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Params.class);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Params p = (Params) obj;
		if(p.getGroupId() != null && groupDAO.getGroup(p.getGroupId()) == null) throw new CustomException("There are no groups for the Group Id parameter");
		
		if(p.getPage() == null) p.setPage(1);
		else {
			int pagesCount = (productDAO.getProductsCount(p.getGroupId())-1)/IShopServiceImpl.PAGE_SIZE+1;
			if(p.getPage() > pagesCount) throw new CustomException("The page index could not be greater than pages count");
			if(p.getPage() <= 0) throw new CustomException("The page index could not be less than 0");
		}
		
		String[] sortTypes = new String[]{null, "", "asc", "desc"};
		if(!Arrays.asList(sortTypes).contains(p.getNameOrder())) throw new CustomException("Incorrect value for the Product Name parameter");
		if(!Arrays.asList(sortTypes).contains(p.getPriceOrder())) throw new CustomException("Incorrect value for the Product Price parameter");
	}

}
