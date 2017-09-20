package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;
import entity.*;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	AppointmentService as = new AppointmentService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
		String op = null;
		int aid = 0;
		int did = 0;
		int pid = 0;
		int st = 0;
		try {
			op = request.getParameter("op");
		} catch (Exception e) {
			op = (String)request.getAttribute("op");
		}
		try {
			if (op.equals("new")) {
				try {
					pid = Integer.parseInt(request.getParameter("pid"));
				} catch (Exception e) {
					pid = (int)request.getAttribute("pid");
				}
				request.setAttribute("pid", pid);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/new/newappointment.jsp");
				rd.forward(request, response);
				return;
			}
			if (op.equals("create")) {
				Appointment ap = new Appointment();
				ap.setClinicId(Integer.parseInt(request.getParameter("clinicId")));
				ap.setDate("");
				ap.setDentistId(Integer.parseInt(request.getParameter("dentistId")));
				ap.setPatientId(Integer.parseInt(request.getParameter("patientId")));
				ap.setStatus(0);
				ap = as.createPatientRecord(ap);
				pid = ap.getPatientId();
				request.setAttribute("op", "view");
				request.setAttribute("pid", pid);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/patientServlet?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("update")) {
				try {
					aid = Integer.parseInt(request.getParameter("aid"));
				} catch (Exception e) {
					aid = (int)request.getAttribute("aid");
				}
				try {
					did = Integer.parseInt(request.getParameter("did"));
				} catch (Exception e) {
					did = (int)request.getAttribute("did");
				}
				try {
					st = Integer.parseInt(request.getParameter("status"));
				} catch (Exception e) {
					st = (int)request.getAttribute("status");
				}
				as.updateAppointment(aid, st);
				request.setAttribute("op", "view");
				request.setAttribute("did", did);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/dentistServlet?op=view");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
