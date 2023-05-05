package controller.servlet.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dbconnection.DbConnection;
import resource.MyConstants;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String User = request.getParameter("uName");
		String pwd = request.getParameter("pwd");
		if(User!=null && pwd!=null) {
			if (User.equals("admin") && pwd.equals("admin@123")) {
				HttpSession session = request.getSession();
				session.setAttribute("user", User);
				session.setAttribute("password", pwd);
				
				// setting session to expire in 30 minutes
				session.setMaxInactiveInterval(30*60);
				response.sendRedirect(request.getContextPath()+"/pages/admin.jsp");
			}
			else {
				DbConnection connection =  new DbConnection();
				Boolean isUserRegistered = connection.UserRegistered(MyConstants.VALIDATELOGIN, User, pwd);			
				System.out.println(isUserRegistered);
				if(isUserRegistered != null && isUserRegistered){
					HttpSession session = request.getSession();
					session.setAttribute("user", User);
					//session expires in 30 minutes
					session.setMaxInactiveInterval(30*60);
					
					Cookie userName = new Cookie("user", User);
					userName.setMaxAge(30*60);
					response.addCookie(userName);
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}else{
					// set error message
				    request.setAttribute("errorMessage", "Invalid username or password");
				    // forward request to login page
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				    dispatcher.include(request, response);	
				}
			}
		}else{
			// set error message
		    request.setAttribute("errorMessage", "Empty Username/Password.");
		    // forward request to login page
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		    dispatcher.include(request, response);	
		}
    }

}
