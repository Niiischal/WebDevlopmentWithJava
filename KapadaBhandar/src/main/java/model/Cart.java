package model;

public class Cart {
	private String productID, username, productName, productPrice;
	
	public Cart(String productID, String username, String productName, String productPrice) {
		this.productID = productID;
		this.username = username;
		this.productName = productName;
		this.productPrice = productPrice;
		
	}

	public void setproductID(String productID) {
		this.productID = productID;
	}

	public String getproductID() {
		return productID;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getusername() {
		return username;
	}
	
	public void setproductName(String productName) {
		this.productName = productName;
	}

	public String getproductName() {
		return productName;
	}

	public void setproductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getproductPrice() {
		return productPrice;
	}



}
