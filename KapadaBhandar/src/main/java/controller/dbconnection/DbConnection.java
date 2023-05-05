package controller.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import resource.MyConstants;
import model.Product;
import model.Customer;
import model.Encryption;


public class DbConnection {
	public Connection getConnection() {
		try{
			Class.forName(MyConstants.DRIVERNAME);
			Connection connection = DriverManager.getConnection(MyConstants.DATABASEURL,MyConstants.DATABASEUSERNAME,MyConstants.DATABASEUSERPASSWORD);
			return connection;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	
	}


	public Boolean UserRegistered(String query, String username, String password) {
		Connection dbconnection = getConnection();
		if(dbconnection != null) {
			try {
				//Get the user name provided by the user
				PreparedStatement userStatement = dbconnection.prepareStatement(MyConstants.GETUSERNAME);
				userStatement.setString(1, username);
				ResultSet userResult = userStatement.executeQuery();
				if(userResult.next()) {
					//if the user exists...
					PreparedStatement passwordStatement = dbconnection.prepareStatement(MyConstants.GETPASSWORD);
					passwordStatement.setString(1,username);
					ResultSet passwordResult = passwordStatement.executeQuery();
					if(passwordResult.next()) {
						String encryptedPassword = passwordResult.getString("password");
						String decryptedPassword = Encryption.decrypt(encryptedPassword);
						System.out.println(encryptedPassword);
						System.out.println(decryptedPassword);
						System.out.println(password);
						boolean out = checkPassword(decryptedPassword, password);
						System.out.println(out);
						return out;
					}else return false;
				}
				else {
					return false;
				}
				
			} catch(SQLException e){ 
				return null;
			}
		}
		else return null; 
	}
	
	public ResultSet selectAllQuery(String query) {
		Connection dbconnection = getConnection();
		if(dbconnection != null) {
			try {
				PreparedStatement statement = dbconnection.prepareStatement(query);
				ResultSet result = statement.executeQuery();
				return result;
			} catch (SQLException e) {
				return null;
			}
		}else {
			return null;
		}
	}

	private boolean checkPassword(String decryptedPassword, String password) {
		// TODO Auto-generated method stub
		if(decryptedPassword.equals(password)) return true;
		else return false;
	}

	public int registerUser(String query, Customer registerModel) {
		Connection dbconnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st= dbconnection.prepareStatement(query);
			st.setString(1, registerModel.getfirstName());
			st.setString(2, registerModel.getlastName());
			st.setString(3, registerModel.getaddress());
			st.setString(4, registerModel.getemail());
			st.setString(5, registerModel.getusername());
			st.setString(6, Encryption.encrypt(registerModel.getpassword()) );
			st.setString(7, Encryption.encrypt(registerModel.getrePassword()) );		
			st.setString(8, registerModel.getimagePath());
			result = st.executeUpdate();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
	}

	public int addProducts(String query, Product productModel) {
		Connection dbconnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st= dbconnection.prepareStatement(query);
			st.setString(1, productModel.getproductName());
			st.setString(2, productModel.getproductDescriptioon());
			st.setString(3, productModel.getproductPrice());
			st.setString(4, productModel.getproductCategory());
			st.setString(5, productModel.getproductImage());
			result = st.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
	}
	
	public int updateProduct(String query, Product productModel, int productID) {
		Connection dbconnection = getConnection();
		int result = 0;
		try {
			PreparedStatement st= dbconnection.prepareStatement(query);
			st.setString(1, productModel.getproductName());
			st.setString(2, productModel.getproductDescriptioon());
			st.setString(3, productModel.getproductPrice());
			st.setString(4, productModel.getproductCategory());
			st.setString(5, productModel.getproductImage());
			st.setInt(6, productID);
			result = st.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
	}
		
		public int removeProduct(String query, String productID) {
			Connection dbconnection = getConnection();
			int result = 0;
			try {
				PreparedStatement st = dbconnection.prepareStatement(query);
				st.setString(1, productID);
				result = st.executeUpdate();
				return result;
			}
			catch(SQLException e) {
				e.printStackTrace();
				return result;
			}
	}
	
		public int updateUser(String query, Customer registerModel, int userID) {
			Connection dbConnection = getConnection();
			int result = 0;
			try {
				PreparedStatement st= dbConnection.prepareStatement(query);
				st.setString(1, registerModel.getfirstName());
				st.setString(2, registerModel.getlastName());
				st.setString(3, registerModel.getaddress());
				st.setString(4, registerModel.getemail());
				st.setString(5, registerModel.getusername());
				st.setString(6, Encryption.encrypt(registerModel.getpassword()) );
				st.setString(7, registerModel.getimagePath());
				st.setInt(8,userID);
				result = st.executeUpdate();
				
				return result;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return result;
			}
		}

	
}
