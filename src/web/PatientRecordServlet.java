package web;

import java.io.IOException;
import service.*;
import entity.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PatientRecordServlet
 */
@WebServlet("/PatientRecordServlet")
public class PatientRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	private PatientRecordService prs = new 	PatientRecordService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
		String oper = request.getParameter("oper");
		request.setAttribute("oper", oper);
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
