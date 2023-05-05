package controller.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LogoutServlet" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Clear cookies
				Cookie[] cookies=request.getCookies();
				if(cookies!=null) {
					for(Cookie cookie: cookies) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
				//clear session
				HttpSession session=request.getSession(false);
				if(session!=null) {
					session.invalidate();
				}
				response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

}