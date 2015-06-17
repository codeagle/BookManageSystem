package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ReaderInfo;
import vo.ReaderType;
import dao.factory.DAOFactory;

public class ReaderInfoQueryAllServlet extends HttpServlet {

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
		ReaderType readertype =new ReaderType();
		try {
			ArrayList allReaderInfo=DAOFactory.getReaderInfoDAOInstance().findAllReaderInfo(readerinfo);
			request.getSession().setAttribute("allReaderInfo", allReaderInfo);
			ArrayList allReaderType =DAOFactory.getReaderTypeDAOInstance().findAllReaderType(readertype);
			request.getSession().setAttribute("allReaderType", allReaderType);
			request.getRequestDispatcher("readerinfo_queryall.jsp").forward(request, response);
			
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
