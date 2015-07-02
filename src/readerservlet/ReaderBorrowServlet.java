package readerservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BorrowInfo;
import vo.ReaderInfo;
import dao.factory.DAOFactory;

public class ReaderBorrowServlet extends HttpServlet {

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
		ReaderInfo readerInfo = new ReaderInfo();
		BorrowInfo borrow = new BorrowInfo();
		
			int readerid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
			try {
				ArrayList allReader = DAOFactory.getReaderInfoDAOInstance()
						.findReaderInfoById(readerid);
				request.getSession().setAttribute("allReader", allReader);
				readerInfo=(ReaderInfo)allReader.get(0);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(request.getParameter("inputkey")!=null){
				borrow.setBookid(Integer.parseInt(request.getParameter("inputkey")));
				borrow.setReaderid(readerid);
				SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
				borrow.setBorrowdate(format.format(new Date()));
				try {
					if(DAOFactory.getBorrowBookDAOInstance().insertBorrowBook(borrow)){
						try {
							ArrayList allReader = DAOFactory.getReaderInfoDAOInstance()
									.findReaderInfoById(readerid);
							request.getSession().setAttribute("allReader", allReader);
							readerInfo=(ReaderInfo)allReader.get(0);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				ArrayList allBorrowBook = new ArrayList();
				if(readerInfo.getRtypename().equals("学生")){
				 allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
						.findAllBorrowBookWithStudent(borrow, readerid);
				}
				if(readerInfo.getRtypename().equals("教师")){
					 allBorrowBook = DAOFactory.getBorrowBookDAOInstance()
							.findAllBorrowBookWithTeacher(borrow, readerid);
				}
				request.getSession().setAttribute("allBorrowBook", allBorrowBook);
				request.getRequestDispatcher("reader_borrow.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
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
