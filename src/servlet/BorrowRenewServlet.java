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

public class BorrowRenewServlet extends HttpServlet {

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
		ReaderInfo readerinfo= new ReaderInfo();
		BorrowInfo borrow = new BorrowInfo();
		if(request.getParameter("barcode")==null){
			try {
				int readerid = 0;
				ArrayList allReaderinfo=DAOFactory.getReaderInfoDAOInstance().findReaderInfoById(readerid);
				request.getSession().setAttribute("allReaderinfo", allReaderinfo);
				request.getRequestDispatcher("bookRenew.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			int readerid = Integer.parseInt(request.getParameter("barcode"));
			System.out.println();
			ArrayList allBorrowBook = new ArrayList();
			try {
				ArrayList allReaderinfo = DAOFactory.getReaderInfoDAOInstance()
						.findReaderInfoById(readerid);
				request.getSession().setAttribute("allReaderinfo", allReaderinfo);
				readerinfo=(ReaderInfo)allReaderinfo.get(0);
				if(readerinfo.getRtypename().equals("学生")){
					 allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
							.findBookRenewWithStudent(borrow, readerid);
					}
					if(readerinfo.getRtypename().equals("教师")){
						 allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
								.findBookRenewWithTeacher(borrow, readerid);
					}
					request.getSession().setAttribute("allBorrowBook", allBorrowBook);
				request.getRequestDispatcher("bookRenew.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
