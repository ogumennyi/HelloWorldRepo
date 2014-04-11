package iShop.controller;

import org.springframework.stereotype.Component;

@Component
public class Params {

	private Integer groupId;
	private Integer page;
	private String nameOrder;
	private String priceOrder;
	
	public Integer getGroupId() {
		return groupId;
	}
	
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String getNameOrder() {
		return nameOrder;
	}
	
	public void setNameOrder(String nameOrder) {
		this.nameOrder = nameOrder;
	}
	
	public String getPriceOrder() {
		return priceOrder;
	}
	
	public void setPriceOrder(String priceOrder) {
		this.priceOrder = priceOrder;
	}

}
