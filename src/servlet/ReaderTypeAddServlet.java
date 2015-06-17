package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.factory.DAOFactory;
import vo.ReaderType;

public class ReaderTypeAddServlet extends HttpServlet {

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

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		ReaderType readertype= new ReaderType();
		readertype.setRtypeid(Integer.parseInt(request.getParameter("rtypeid")));
		readertype.setRtypename(request.getParameter("rtypename"));
		readertype.setNumber(Integer.parseInt(request.getParameter("number")));
		try {
			if(DAOFactory.getReaderTypeDAOInstance().doCreate(readertype)){
				request.getSession().setAttribute("readertype", readertype);
				request.getRequestDispatcher("ReaderTypeQueryAllServlet").forward(request, response);
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
