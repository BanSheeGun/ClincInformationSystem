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
	PatientService ps = new PatientService();
	DentistService ds = new DentistService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
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
				try {
					int pid = Integer.parseInt(request.getParameter("ID"));
					Patient p = ps.getPatientById(pid);
					if (p == null)
						throw new Exception();
					request.setAttribute("pid", pid);
					request.setAttribute("op", "view");
					RequestDispatcher rd2 = this.getServletContext().getRequestDispatcher("/patientServlet?op=view");
					rd2.forward(request, response);		
				} catch (Exception e) {
					RequestDispatcher rd2 = this.getServletContext().getRequestDispatcher("/index.jsp");
					rd2.forward(request, response);					
				}
				break;
			case "doctor" :
				try {
					int did = Integer.parseInt(request.getParameter("ID"));
					Dentist d = ds.getDentistById(did);
					if (d == null)
						throw new Exception();
					request.setAttribute("did", did);
					request.setAttribute("op", "view");
					RequestDispatcher rd3 = this.getServletContext().getRequestDispatcher("/dentistServlet?op=view");
					rd3.forward(request, response);		
				} catch (Exception e) {
					RequestDispatcher rd3 = this.getServletContext().getRequestDispatcher("/index.jsp");
					rd3.forward(request, response);					
				}
				break;
			case "family" :
				try {
					int fid = Integer.parseInt(request.getParameter("ID"));
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
