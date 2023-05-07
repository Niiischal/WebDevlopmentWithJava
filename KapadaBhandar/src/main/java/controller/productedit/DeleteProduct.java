package controller.productedit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dbconnection.DbConnection;
import resource.MyConstants;

/**
 * Servlet implementation class RemoveProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteProduct" })
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productID = request.getParameter("productID");
		System.out.println(productID);
		DbConnection con = new DbConnection();
		int result = con.removeProduct(MyConstants.DELETEPRODUCT, productID);

		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
		}
	}

}
