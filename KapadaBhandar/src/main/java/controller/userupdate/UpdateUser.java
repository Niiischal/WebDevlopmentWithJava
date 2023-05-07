package controller.userupdate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import resource.MyConstants;
import model.Customer;
import controller.register.PhotoUploader;

/**
 * Servlet implementation class Register
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateUser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)

public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("userID"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Part Image = request.getPart("image");
		String oldImage = request.getParameter("oldImage");
		String imagePath = oldImage;
		if (Image != null) {
			PhotoUploader upload = new PhotoUploader();
			upload.removePhoto(oldImage);
			upload.uploadPhoto(Image);
			imagePath = "images/" + upload.getFileName(Image);
		}
		Customer userModel = new Customer(firstName, lastName, address, email, username, password, imagePath);
		DbConnection con = new DbConnection();
		int result = con.updateUser(MyConstants.UPDATEUSER, userModel, userID);
		HttpSession session = request.getSession(false);
		session.setAttribute("user", username);
		session.setAttribute("password", password);
		if (result > 0) {
			request.setAttribute("registrationMessage", "User Successfully Updated!");
			response.sendRedirect(request.getContextPath() + "/pages/user.jsp");
		} else if (result == -1) {
			request.setAttribute("registrationMessage", "User Already Exists!");
			response.sendRedirect(request.getContextPath() + "/pages/user.jsp");
		} else {
			request.setAttribute("registrationMessage", "Failed to Update!");
			response.sendRedirect(request.getContextPath() + "/pages/user.jsp");
		}

	}

}
