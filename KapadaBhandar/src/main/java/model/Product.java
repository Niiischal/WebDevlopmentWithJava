package model;

public class Product {
	private String productName, productDescriptioon, productPrice, productCategory, productImage;

	public Product(String productName, String productDescriptioon, String productPrice, String productCategory,
			String productImage) {
		this.productName = productName;
		this.productDescriptioon = productDescriptioon;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productImage = productImage;

	}

	public void setproductName(String productName) {
		this.productName = productName;
	}

	public String getproductName() {
		return productName;
	}

	public void setproductDescriptioon(String productDescriptioon) {
		this.productDescriptioon = productDescriptioon;
	}

	public String getproductDescriptioon() {
		return productDescriptioon;
	}

	public void setproductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getproductPrice() {
		return productPrice;
	}

	public void setproductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getproductImage() {
		return productImage;
	}

	public void setproductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getproductCategory() {
		return productCategory;
	}
}
