package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ReaderInfo;
import dao.factory.DAOFactory;

public class BorrowBackOkServlet extends HttpServlet {

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
		ReaderInfo readerinfo=new ReaderInfo();
		int borrowid =Integer.parseInt(request.getParameter("borrowid"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置借阅日期格式为系统默认时间
		String backdate=df.format(new Date());
		try {
			if(DAOFactory.getBorrowBookDAOInstance().backBookById(borrowid,backdate)){
				String readerno=request.getParameter("barcode");
				request.getSession().setAttribute("readerno", readerno);
				request.getRequestDispatcher("BookBackShowServlet").forward(request, response);
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
