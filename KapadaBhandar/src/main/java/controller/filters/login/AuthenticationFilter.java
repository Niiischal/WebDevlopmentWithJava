package controller.filters.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
private ServletContext context;

@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	this.context = filterConfig.getServletContext();
	this.context.log("AuthenticationFilter initialized");
}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//check if the request is a login or logout request
		String uri = req.getRequestURI ();
		System.out.println(uri);
		
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("user") != null;

		String loginURI = req.getContextPath() + "/login.jsp";
		String registerURI = req.getContextPath() + "/pages/registration.jsp";
		String adminURI = req.getContextPath() + "/pages/admin.jsp";
		String indexURI = req.getContextPath() + "/index.jsp";

		boolean isLoginURI = uri.equals(loginURI);
		boolean isRegisterURI = uri.equals(registerURI);
		boolean isAdminURI = uri.equals(adminURI);
		boolean isindexURI = uri.equals(indexURI);
		boolean isCSS = uri.endsWith(".css");
		boolean isLoginServlet = uri.endsWith("LoginServlet");
		boolean isLogoutServlet = uri.endsWith("LogoutServlet");
		boolean isRegisterServlet = uri.endsWith("Register");
		boolean isImg = uri.endsWith(".jpg");

		if (isLoginURI || isRegisterURI || isindexURI || isCSS || isLoginServlet || isLogoutServlet
				|| isRegisterServlet || isImg) {
			this.context.log("Requested Resource::" + uri);
			chain.doFilter(request, response);
		} else if (isAdminURI) {
			if(session!=null) {
			this.context.log("Requested Resource::" + uri);
			String username = (String) session.getAttribute("user");
			String password = (String) session.getAttribute("password");
			if (username != null && username.equals("admin") && password != null && password.equals("admin@123")) {
				chain.doFilter(request, response);
			} else if (username != null) {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
			}

			else {
				res.sendRedirect(req.getContextPath() + "/login.jsp");
			}
		  }
			else {
				res.sendRedirect(req.getContentType()+ "/login.jsp");
			}
		} else if (!loggedIn) {
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}
@Override
	public void destroy() {
	// TODO Auto-generated method stub

}

}
