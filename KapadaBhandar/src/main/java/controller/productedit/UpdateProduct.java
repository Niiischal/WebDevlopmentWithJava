package controller.productedit;


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
import controller.register.PhotoUploader;

/**
 * Servlet implementation class Register
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProduct" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
maxFileSize = 1024 * 1024 * 10, //10MB
maxRequestSize = 1024 * 1024 * 50 )

public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productID =  Integer.parseInt(request.getParameter("productID"));
		String productName =request.getParameter("productName");
		String productDescriptioon = request.getParameter("productDescriptioon");
		String productPrice = request.getParameter("productPrice");
		String productCategory = request.getParameter("productCategory");
		Part Image = request.getPart("productImage");
		String oldImage = request.getParameter("oldImage");
		String productimagePath = oldImage;
		if(Image!=null) {
		    PhotoUploader upload = new PhotoUploader();
			upload.removePhoto(oldImage);
			upload.uploadPhoto(Image);
			productimagePath = "images/" + upload.getFileName(Image);
		}

			Product productModel = new Product(productName,productDescriptioon, productPrice, productCategory, productimagePath);
			DbConnection con = new DbConnection();
			int result = con.updateProduct(MyConstants.UPDATEPRODUCT, productModel, productID);
			if(result>0){
				response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
			}else if(result == -1){
				response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
			}else{
				response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
			}
			

			
		} 
	 

}
