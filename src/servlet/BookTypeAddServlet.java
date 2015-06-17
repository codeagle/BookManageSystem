package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.factory.DAOFactory;
import vo.BookType;

public class BookTypeAddServlet extends HttpServlet {

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
		//获取表单类型信息
		BookType bookType = new BookType();
		bookType.setBtypename(request.getParameter("typename"));
		bookType.setBtypeid(Integer.parseInt(request.getParameter("typeid")));
		
		//执行db
		try {
			
			if(DAOFactory.getBookTypeDAOInstance().doCreate(bookType)){
				
				request.getSession().setAttribute("typename", bookType.getBtypename());
				request.getRequestDispatcher("BookTypeQueryAllServlet").forward(request, response);
			}else{
				System.out.println("添加失败");
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
