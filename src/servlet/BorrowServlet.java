package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BookInfo;
import vo.BorrowInfo;
import vo.ReaderInfo;
import dao.factory.DAOFactory;

public class BorrowServlet extends HttpServlet {

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
		ReaderInfo readerInfo=new ReaderInfo();
		if(request.getParameter("readerNo")==null||request.getParameter("readerNo").isEmpty()){	
			try {		
				//readerInfo.setReaderid(Integer.parseInt(request.getParameter("readerNo")));
				ArrayList allBorrowReader = (ArrayList)DAOFactory.getBorrowReaderDAOInstance().findAllBorrowReader(readerInfo);
				request.getSession().setAttribute("allBorrowReader", allBorrowReader);
				request.getRequestDispatcher("book_borrow.jsp").forward(request, response);
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			System.out.println("null中的方法");
		}else{	
			int readerid =Integer.parseInt(request.getParameter("readerNo"));
			readerInfo.setReaderid(readerid);
			System.out.println(readerid);
		try {
			BorrowInfo borrow= new BorrowInfo();
			int  countnum=DAOFactory.getBorrowReaderDAOInstance().countBorrowNumber(readerid).getBorrownumber();
			request.getSession().setAttribute("countnum", countnum);
		ArrayList allBorrowReader=(ArrayList)DAOFactory.getBorrowReaderDAOInstance().findAllBorrowReader(readerInfo);
		ArrayList allBorrowBook=new ArrayList();
		if(request.getParameter("rtypename").equals("教师")){
		 allBorrowBook =(ArrayList)DAOFactory.getBorrowBookDAOInstance().findAllBorrowBookWithTeacher(borrow, readerid);
		}if(request.getParameter("rtypename").equals("学生")){
		 allBorrowBook =(ArrayList)DAOFactory.getBorrowBookDAOInstance().findAllBorrowBookWithStudent(borrow, readerid);	
		}
		request.getSession().setAttribute("allBorrowReader", allBorrowReader);
		request.getSession().setAttribute("allBorrowBook", allBorrowBook);
		if(!request.getParameter("inputkey").trim().isEmpty()){
			BookInfo bookinfo=DAOFactory.getBorrowBookDAOInstance().findBookByISBN(request.getParameter("inputkey"));
			borrow.setReaderid(readerid);
			borrow.setBookname(bookinfo.getBookname());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置借阅日期格式为系统默认时间
			borrow.setBorrowdate(df.format(new Date()));
			DAOFactory.getBorrowBookDAOInstance().insertBorrowBook(borrow);
			countnum=DAOFactory.getBorrowReaderDAOInstance().countBorrowNumber(readerid).getBorrownumber();
			request.getSession().setAttribute("countnum", countnum);
			allBorrowBook =(ArrayList)DAOFactory.getBorrowBookDAOInstance().findAllBorrowBookWithTeacher(borrow, readerid);
			request.getSession().setAttribute("allBorrowBook", allBorrowBook);
			request.getRequestDispatcher("book_borrow.jsp").forward(request, response);
			return;
			
		}
		request.getRequestDispatcher("book_borrow.jsp").forward(request, response);
		return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
