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
import model.Product;
import resource.MyConstants;

/**
 * Servlet implementation class Register
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProducts" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50 )

public class AddProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProducts() {
        super();
        // TODO Auto-generated constructor stub
    }
   


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productName =request.getParameter("productName");
		String productDescriptioon = request.getParameter("productDescriptioon");
		String productPrice = request.getParameter("productPrice");
		String productCategory = request.getParameter("productCategory");
		Part Image = request.getPart("productImage");
		PhotoUploader upload = new PhotoUploader();
		upload.uploadPhoto(Image);
		
		String productimagePath = "images/" + upload.getFileName(Image);
		Product productModel = new Product(productName,productDescriptioon, productPrice, productCategory, productimagePath);
			DbConnection con = new DbConnection();
			int result = con.addProducts(MyConstants.ADDPRODUCT, productModel);
			
			if(result>0){
				request.setAttribute("productMessage", "Product Successfully Added!");
				response.sendRedirect("pages/admin.jsp");
			}else if(result == -1){
				request.setAttribute("productMessage", "Product Already Exists!");
				response.sendRedirect(request.getContextPath() + "pages/admin.jsp");
			}else{
				request.setAttribute("productMessage", "Failed to Add Product!");
				response.sendRedirect(request.getContextPath() + "pages/admin.jsp");
			}
			

			
		} 
	 

}
