package servlet;

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

public class BookRenewServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		ReaderInfo readerinfo = new ReaderInfo();
		BorrowInfo borrow= new BorrowInfo();
			String readerno=request.getParameter("barcode");
			readerinfo.setReaderno(request.getParameter("barcode"));
			try {		
				ArrayList allReaderinfo=DAOFactory.getBorrowReaderDAOInstance().findAllBorrowReader(readerinfo);
				request.getSession().setAttribute("allReaderinfo", allReaderinfo);
				request.getSession().setAttribute("readerno", readerno);
				ArrayList allBorrowBook =(ArrayList)DAOFactory.getBorrowBookDAOInstance().findBorrowBookNoYes(borrow, readerno);
				request.getSession().setAttribute("allBorrowBook", allBorrowBook);
				request.getRequestDispatcher("bookRenew.jsp").forward(request, response);
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
