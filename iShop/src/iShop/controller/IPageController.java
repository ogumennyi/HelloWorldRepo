package iShop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import iShop.service.IShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping(value = "/ipage")
	public String productsByGroup(ModelMap modelMap, @Valid Params params) throws Exception {
		Map<String, String> orderParams = new HashMap<String, String>();
		if (params.getNameOrder() != null && params.getNameOrder().length() > 0)
			orderParams.put("nameOrder", params.getNameOrder());
		if (params.getPriceOrder() != null && params.getPriceOrder().length() > 0)
			orderParams.put("priceOrder", params.getPriceOrder());
		modelMap.put("groupsList", iShopService.getGroups());
		modelMap.put("productsList", iShopService.getProducts(params.getPage(), params.getGroupId(), orderParams));
		modelMap.put("params", params);
		List<String> pagesList = iShopService.getPagesList(params.getPage(), params.getGroupId());
		modelMap.put("pagesList", pagesList);
		modelMap.put("maxPageNum", pagesList.get(pagesList.size() - 1));
		return "ipage";
	}

}
