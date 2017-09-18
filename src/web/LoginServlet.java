package web;

import entity.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	FamilyService fs = new FamilyService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		switch (type) {
			default :
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				break;
			case "admin" :
				RequestDispatcher rd1 = this.getServletContext().getRequestDispatcher("/adminServlet");
				rd1.forward(request, response);
				break;
			case "patient" :
				break;
			case "doctor" :
				break;
			case "family" :
				try {
					int fid = Integer.parseInt(request.getParameter("ID"));
					System.out.println(fid);
					Family f = fs.queryFamily(fid);
					if (f == null)
						throw new Exception();
					request.setAttribute("fid", fid);
					RequestDispatcher rd4 = this.getServletContext().getRequestDispatcher("/familyServlet?op=view");
					rd4.forward(request, response);		
				} catch (Exception e) {
					RequestDispatcher rd4 = this.getServletContext().getRequestDispatcher("/index.jsp");
					rd4.forward(request, response);					
				}
				break;
		}
	}

}
