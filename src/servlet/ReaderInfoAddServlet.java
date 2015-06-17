package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.factory.DAOFactory;
import vo.ReaderInfo;

public class ReaderInfoAddServlet extends HttpServlet {

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
		ReaderInfo readerinfo = new ReaderInfo();
		readerinfo.setReaderid(Integer.parseInt(request.getParameter("readerNo")));
		readerinfo.setRtypename(request.getParameter("rtypename"));
		readerinfo.setReadername(request.getParameter("readername"));
		readerinfo.setIdcard(request.getParameter("idcard"));
		try {
			if(DAOFactory.getReaderInfoDAOInstance().doCreate(readerinfo)){
				request.getSession().setAttribute("readerinfo", readerinfo);
				request.getRequestDispatcher("ReaderInfoQueryAllServlet").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
