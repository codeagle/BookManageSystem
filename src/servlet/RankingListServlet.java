package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BookInfo;
import vo.BorrowInfo;
import vo.ReaderInfo;
import dao.factory.DAOFactory;

public class RankingListServlet extends HttpServlet {

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
		BookInfo bookinfo =  new  BookInfo();
		BorrowInfo borrow = new BorrowInfo();
		ReaderInfo readerinfo= new ReaderInfo();
		try {
			ArrayList allRankingList=DAOFactory.getBookInfoDAOInstance().borrowRankingList(bookinfo);
			request.getSession().setAttribute("allRankingList", allRankingList);
			//查询是否有超期图书
			if(((int)request.getSession().getAttribute("limit"))==2){
				
			try {
				ArrayList allOverBook = new ArrayList();
				int readerid=(int)request.getSession().getAttribute("userid");
				ArrayList allReaderinfo = DAOFactory.getReaderInfoDAOInstance()
						.findReaderInfoById(readerid);
				request.getSession().setAttribute("allReaderinfo", allReaderinfo);
				readerinfo=(ReaderInfo)allReaderinfo.get(0);
				if(readerinfo.getRtypename().equals("学生")){
					allOverBook = DAOFactory.getBorrowBookDAOInstance()
							.findAllBorrowBookWithStudent(borrow, readerid);
					}
				if(readerinfo.getRtypename().equals("教师")){
					allOverBook = DAOFactory.getBorrowBookDAOInstance()
								.findAllBorrowBookWithTeacher(borrow, readerid);
					}
					request.getSession().setAttribute("allOverBook", allOverBook);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}else{
				ArrayList allOverBook = new ArrayList();
				request.getSession().setAttribute("allOverBook", allOverBook);
			}
			request.getRequestDispatcher("main.jsp").forward(request, response);
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
