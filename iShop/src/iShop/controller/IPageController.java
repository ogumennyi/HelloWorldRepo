package iShop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import iShop.service.IShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IPageController {

	@Autowired
	private IShopService iShopService;
	
	@Autowired
	private ParamsValidator paramsValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(paramsValidator);
	}
	
	@RequestMapping(value = "/ipage", method = RequestMethod.GET)
	public String productsByGroup(ModelMap modelMap, @Valid Params params, BindingResult result) {
		if(result.hasErrors()){
			return "error";
		} else {			
			Map<String, String> orderParams = new HashMap<String, String>();
			processOrderParamsByColumnName(orderParams, "nameOrder", params.getNameOrder(), modelMap);
			processOrderParamsByColumnName(orderParams, "priceOrder", params.getPriceOrder(), modelMap);
			modelMap.put("groupsList", iShopService.getGroups());
			modelMap.put("productsList", iShopService.getProducts(params.getPage(), params.getGroupId(), orderParams));
			modelMap.put("groupId", params.getGroupId());
			modelMap.put("page", params.getPage());
			List<String> pagesList = iShopService.getPagesList(params.getPage(), params.getGroupId());
			modelMap.put("pagesList", pagesList);
			modelMap.put("maxPageNum", pagesList.get(pagesList.size() - 1));
			return "ipage";
		}
	}

	private void processOrderParamsByColumnName(Map<String, String> orderParams, String paramName,
			String paramOrder, ModelMap model) {
		if (!"none".equals(paramOrder.toLowerCase()))
			orderParams.put(paramName, paramOrder);
		switch (paramOrder.toLowerCase()) {
		case "none":
			model.put(paramName, "none");
			model.put(paramName + "Next", "asc");
			break;
		case "asc":
			model.put(paramName, "asc");
			model.put(paramName + "Next", "desc");
			break;
		case "desc":
			model.put(paramName, "desc");
			model.put(paramName + "Next", "none");
			break;
		}
	}

}
