package iShop.model;

public class Product {
	
	private int productId;
	private String productName;
	private int groupId;
	private float productPrice;

	public Product(int productId, String productName, int groupId, float productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.groupId = groupId;
		this.productPrice = productPrice;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public float getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
}
