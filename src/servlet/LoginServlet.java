package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.User;
import dao.factory.DAOFactory;


public class LoginServlet extends HttpServlet {
	/*public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.doPost(request, response);
	}
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		
		User user = new User();
		user.setName(request.getParameter("userid"));
		user.setPwd(request.getParameter("pwd"));
		try {
			if(DAOFactory.getUserDAOInstance().findLogin(user)){
			request.getSession().setAttribute("name", user.getName());
				
				request.getRequestDispatcher("main.jsp").forward(request, response);
			} 
			else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
	     } catch (Exception e) {
			e.printStackTrace();
		}
	}
}
