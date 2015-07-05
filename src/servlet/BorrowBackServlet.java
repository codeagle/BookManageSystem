package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BorrowInfo;
import vo.ReaderInfo;
import dao.factory.DAOFactory;

public class BorrowBackServlet extends HttpServlet {

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
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
				request.getRequestDispatcher("bookBack.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			int readerid = Integer.parseInt(request.getParameter("barcode"));
			ArrayList allBorrowBook = new ArrayList();
			try {
				ArrayList allReaderinfo = DAOFactory.getReaderInfoDAOInstance()
						.findReaderInfoById(readerid);
				request.getSession().setAttribute("allReaderinfo", allReaderinfo);
				readerinfo=(ReaderInfo)allReaderinfo.get(0);
				if(readerinfo.getRtypename().equals("学生")){
					 allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
							.findAllBorrowBookWithStudent(borrow, readerid);
					}
				if(readerinfo.getRtypename().equals("教师")){
						 allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
								.findAllBorrowBookWithTeacher(borrow, readerid);
					}
					request.getSession().setAttribute("allBorrowBook", allBorrowBook);
				request.getRequestDispatcher("bookBack.jsp").forward(request, response);
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
