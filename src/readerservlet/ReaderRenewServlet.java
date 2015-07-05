package readerservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BorrowInfo;
import vo.ReaderInfo;
import dao.factory.DAOFactory;

public class ReaderRenewServlet extends HttpServlet {

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

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		ReaderInfo readerinfo = new ReaderInfo();
		BorrowInfo borrow = new BorrowInfo();
		int readerid = (Integer)request.getSession().getAttribute("userid");
		ArrayList allBorrowBook = new ArrayList();
		try {
			ArrayList allReaderinfo = DAOFactory.getReaderInfoDAOInstance()
					.findReaderInfoById(readerid);
			request.getSession().setAttribute("allReaderinfo", allReaderinfo);
			readerinfo = (ReaderInfo) allReaderinfo.get(0);
			if (readerinfo.getRtypename().equals("学生")) {
				allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
						.findBookRenewWithStudent(borrow, readerid);
			}
			if (readerinfo.getRtypename().equals("教师")) {
				allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
						.findBookRenewWithTeacher(borrow, readerid);
			}
			request.getSession().setAttribute("allBorrowBook", allBorrowBook);
			request.getRequestDispatcher("reader_bookRenew.jsp").forward(request,
					response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
