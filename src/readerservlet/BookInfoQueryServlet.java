package readerservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BookInfo;
import vo.BookType;
import vo.CaseInfo;
import vo.Publishing;
import dao.factory.DAOFactory;

public class BookInfoQueryServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
		// "
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		BookInfo bookinfo = new BookInfo();
		// request.getParameter("bookname");
		if (request.getParameter("bookname") == null||request.getParameter("bookname").isEmpty()
				) {
			try {
				ArrayList allBookInfo = DAOFactory.getBookInfoDAOInstance()
						.findAllBookInfo(bookinfo);
				request.getSession().setAttribute("allBookInfo", allBookInfo);
				request.getRequestDispatcher("reader_bqueryall.jsp").forward(
						request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				String bookname = request.getParameter("bookname");
				ArrayList allBookInfo = DAOFactory.getBookInfoDAOInstance()
						.findBookInfoByIndistinct(bookinfo, bookname);
				request.getSession().setAttribute("allBookInfo", allBookInfo);
				request.getRequestDispatcher("reader_bqueryall.jsp").forward(
						request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
