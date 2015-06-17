package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.factory.DAOFactory;
import vo.BorrowInfo;

public class BookRenewOkServlet extends HttpServlet {

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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		borrow.setBorrowdate(df.format(new Date()));
		borrow.setBorrowid(Integer.parseInt(request.getParameter("borrowid")));
	
		try {
			if(DAOFactory.getBorrowBookDAOInstance().renewBookById(borrow)){
				request.getRequestDispatcher("BookRenewServlet").forward(request, response);
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
