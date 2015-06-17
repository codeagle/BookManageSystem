package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.factory.DAOFactory;
import vo.Publishing;

public class PublishingAddServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb18030");
		request.setCharacterEncoding("gb18030");
		Publishing publishing = new Publishing();
		publishing.setPubid(Integer.parseInt(request.getParameter("pubid")));
		publishing.setPubname(request.getParameter("pubname"));
		try {
			if(DAOFactory.getPublishingDAOInstance().doCreate(publishing)){
				request.getSession().setAttribute("publishing", publishing);
				request.getRequestDispatcher("PublishingQueryAllServlet").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
