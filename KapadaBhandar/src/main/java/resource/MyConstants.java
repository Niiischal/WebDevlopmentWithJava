package resource;

public class MyConstants{
    // Database configuration constants
    public static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASEURL = "jdbc:mysql://localhost:3306/coursework";
    public static final String DATABASEUSERNAME = "root";
    public static final String DATABASEUSERPASSWORD= "";

    // User details constants
    public static final String GETUSERNAME = "SELECT username from registration WHERE username = ?";
    public static final String GETPASSWORD = "SELECT password from registration WHERE username = ?";   
    public static final String VALIDATELOGIN = "SELECT username, password FROM registration WHERE username = ? AND password = ?";
	public static final String GETALLINFOBYID = "SELECT * FROM registration WHERE userID";
    public static final String GETALLINFO = "SELECT * FROM registration";
    public static final String ADDPRODUCT = "INSERT INTO product(productName, productDescriptioon, productPrice, productCategory, productImage) VALUES (?,?,?,?,?)";
    public static final String REGISTERUSER = "INSERT INTO registration (firstName,lastName,address,email,username,password,rePassword,image) VALUES (?,?,?,?,?,?,?,?)";
    public static final String UPDATEPRODUCT = "UPDATE product SET productName = ?, productDescriptioon = ?, productPrice = ?, productCategory = ?, productImage = ? WHERE productID= ?";
    public static final String DELETEPRODUCT = "DELETE FROM product WHERE productID = ?";
}
