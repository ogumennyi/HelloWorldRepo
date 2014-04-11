package iShop.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ParamsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Params.class);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Params p = (Params) obj;
		if(p.getGroupId() != null && p.getGroupId() <= 0) err.reject("group_id");
		
		if(p.getPage() == null) p.setPage(1);
		else if(p.getPage() != null && p.getPage() <= 0) err.reject("page");
		
		if(p.getNameOrder() == null) p.setNameOrder("none");
		else if(p.getNameOrder() != null && p.getNameOrder().isEmpty()) err.reject("p_name_order");
		
		if(p.getPriceOrder() == null) p.setPriceOrder("none");
		else if(p.getPriceOrder() != null && p.getPriceOrder().isEmpty()) err.reject("p_price_order");
	}

}
