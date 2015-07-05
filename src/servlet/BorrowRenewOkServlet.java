package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BorrowInfo;
import dao.factory.DAOFactory;

public class BorrowRenewOkServlet extends HttpServlet {

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
		BorrowInfo borrow= new BorrowInfo();
		borrow.setId(Integer.parseInt(request.getParameter("borrowid")));
		borrow.setBookid(Integer.parseInt(request.getParameter("bookid")));
		int readerid =Integer.parseInt(request.getParameter("barcode"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置借阅日期格式为系统默认时间
		String borrowdate=df.format(new Date());
		borrow.setReturndate(borrowdate);
		try {
			if(DAOFactory.getBorrowBookDAOInstance().renewBookById(borrow, readerid)){		
				request.getRequestDispatcher("BorrowRenewServlet").forward(request, response);	
				return ;
			}
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
