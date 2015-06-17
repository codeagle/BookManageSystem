package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.factory.DAOFactory;
import vo.CaseInfo;

public class BookCaseAddServlet extends HttpServlet {

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
		CaseInfo bookcase = new CaseInfo();
		bookcase.setCaseid(Integer.parseInt(request.getParameter("caseid")));
		bookcase.setCname(request.getParameter("casename"));
		try {
			if(DAOFactory.getCaseInfoDAOInstance().doCreate(bookcase)){
				
				request.getSession().setAttribute("casename", bookcase.getCname());
				request.getRequestDispatcher("BookCaseQueryAllServlet").forward(request, response);
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
