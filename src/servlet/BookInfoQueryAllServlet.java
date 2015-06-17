package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.CaseInfo;
import vo.BookInfo;
import vo.BookType;
import vo.Publishing;
import dao.factory.DAOFactory;

public class BookInfoQueryAllServlet extends HttpServlet {

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
		BookInfo bookinfo = new BookInfo();
		BookType booktype = new BookType();
		Publishing publishing = new Publishing();
		CaseInfo bookcase = new CaseInfo();

		try {
			ArrayList allBookInfo = DAOFactory.getBookInfoDAOInstance()
					.findAllBookInfo(bookinfo);
			request.getSession().setAttribute("allBookInfo", allBookInfo);
			ArrayList allBookType = DAOFactory.getBookTypeDAOInstance()
					.findAllBookType(booktype);
			request.getSession().setAttribute("allBookType", allBookType);
			ArrayList allPublishing = DAOFactory.getPublishingDAOInstance()
					.findAllPublishing(publishing);
			request.getSession().setAttribute("allPublishing", allPublishing);
			ArrayList allBookCase = DAOFactory.getCaseInfoDAOInstance()
					.findAllCaseInfo(bookcase);
			request.getSession().setAttribute("allBookCase", allBookCase);
			request.getRequestDispatcher("bookinfo_queryall.jsp").forward(
					request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
