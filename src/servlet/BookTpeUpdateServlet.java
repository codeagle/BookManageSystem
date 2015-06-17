package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.factory.DAOFactory;
import vo.BookType;

public class BookTpeUpdateServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		BookType booktype = new  BookType();
		booktype.setBtypename(request.getParameter("typename"));
		booktype.setBtypeid(Integer.parseInt(request.getParameter("typeid")));
		try {
			DAOFactory.getBookTypeDAOInstance().doUpdate(booktype);
			request.getRequestDispatcher("BookTypeQueryAllServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
