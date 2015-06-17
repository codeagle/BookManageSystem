package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ReaderType;
import dao.factory.DAOFactory;

public class ReaderTypeQueryAllServlet extends HttpServlet {

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
		ReaderType readertype = new ReaderType();
		try {
			ArrayList allReaderType=DAOFactory.getReaderTypeDAOInstance().findAllReaderType(readertype);
			request.getSession().setAttribute("allReaderType", allReaderType);
			request.getRequestDispatcher("readertype_queryall.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void init() throws ServletException {
		// Put your code here
	}

}
