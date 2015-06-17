package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BookType;
import dao.factory.DAOFactory;

public class BookTypeFindByIdServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		int typeid=Integer.parseInt(request.getParameter("typeid"));
		request.getSession().setAttribute("typeid", typeid);
		try {
			BookType booktype =DAOFactory.getBookTypeDAOInstance().findBookTypeById(typeid);
			request.getSession().setAttribute("booktype", booktype);
			request.getRequestDispatcher("booktype_update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
