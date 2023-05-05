package controller.register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import resource.MyConstants;
import model.Customer;

/**
 * Servlet implementation class Register
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50 )

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName =request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String address = request.getParameter("address");
		String email = request.getParameter("e_mail");
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String rePassword= request.getParameter("repwd");
		Part Image = request.getPart("ProfilePhoto");
		PhotoUploader upload = new PhotoUploader();
		upload.uploadPhoto(Image);
		
		String imagePath = "images/" + upload.getFileName(Image);
		Customer registerModel = new Customer(firstName,lastName,address, email, username, password, rePassword, imagePath);
			DbConnection con = new DbConnection();
			int result = con.registerUser(MyConstants.REGISTERUSER, registerModel);
			
			if(result>0){
				request.setAttribute("registrationMessage", "User Successfully Registered!");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}else if(result == -1){
				request.setAttribute("registrationMessage", "User Already Exists!");
				response.sendRedirect(request.getContextPath() + "/pages/registration.jsp");
			}else{
				request.setAttribute("registrationMessage", "Failed to Register!");
				response.sendRedirect(request.getContextPath() + "/pages/registration.jsp");
			}
			
		} 

}
