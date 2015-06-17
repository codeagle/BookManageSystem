package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BookInfo;
import dao.factory.DAOFactory;

public class BookInfoFindByIdServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		int bookid =Integer.parseInt(request.getParameter("bookid"));
		request.getSession().setAttribute("bookid", bookid);
		String btypename=request.getParameter("btypename");
		String casename=request.getParameter("casename");
		String pub=request.getParameter("pub");
		request.getSession().setAttribute("btypename", btypename);
		request.getSession().setAttribute("casename", casename);
		request.getSession().setAttribute("pub", pub);
		try {
			BookInfo bookinfo=DAOFactory.getBookInfoDAOInstance().findBookInfoById(bookid);
			request.getSession().setAttribute("bookinfo", bookinfo);
			request.getRequestDispatcher("bookinfo_update.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
