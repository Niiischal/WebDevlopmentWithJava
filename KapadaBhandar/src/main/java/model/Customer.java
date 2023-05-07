package model;

public class Customer {

	private String firstName, lastName, address, email, username, password, imagePath;

	public Customer(String firstName, String lastName, String address, String email, String username, String password,
			String imagePath) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.username = username;
		this.password = password;
		this.imagePath = imagePath;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public String getaddress() {
		return address;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getemail() {
		return email;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getusername() {
		return username;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getpassword() {
		return password;
	}

	public void setimagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getimagePath() {
		return imagePath;
	}

}
