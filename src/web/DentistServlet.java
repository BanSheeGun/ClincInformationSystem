package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.*;
import service.*;
/**
 * Servlet implementation class DentistServlet
 */
@WebServlet("/DentistServlet")
public class DentistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DentistService ds = new DentistService();
	PatientRecordService prs = new PatientRecordService();
	AppointmentService aps = new AppointmentService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
		String op = request.getParameter("op");
		int prn, apn;
		try {
			prn = Integer.parseInt(request.getParameter("prn"));
		} catch (Exception e) {
			prn = 1;
		}
		try {
			apn = Integer.parseInt(request.getParameter("rn"));
		} catch (Exception e) {
			apn = 1;
		}
		try {
			int did = (int)request.getAttribute("did");
			if (op.equals("view")) {
				Dentist d = ds.getDentistById(did);
				request.setAttribute("d", d);
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " DentistId = " + did + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " DentistId = " + did + " ");
				request.setAttribute("appm", appm);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/dentistIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
