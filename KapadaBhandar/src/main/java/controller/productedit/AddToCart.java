package controller.productedit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dbconnection.DbConnection;
import model.Cart;
import resource.MyConstants;
/**
 * Servlet implementation class AddCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productID = request.getParameter("productID");
		String productName = request.getParameter("productName");
		String username = request.getParameter("username");
		String productPrice = request.getParameter("productPrice");
		Cart cartModel = new Cart(productID, username, productName, productPrice);
		DbConnection con = new DbConnection();
		int result = con.cart(MyConstants.ADDTOCART, cartModel);
		if(result > 0) {
			response.sendRedirect("pages/cart.jsp");
		}
		else {
			response.sendRedirect("index.jsp");
		}
	}

}
