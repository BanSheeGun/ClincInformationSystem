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
 * Servlet implementation class PatientServlet
 */
@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PatientService ps = new PatientService();
	PatientRecordService prs = new PatientRecordService();
	AppointmentService aps = new AppointmentService();
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
		int pid;
		try {
			if (op.equals("view")) {
				try {
					pid = (int)request.getAttribute("pid");
				} catch (Exception e) {
					pid = Integer.parseInt(request.getParameter("pid"));
				}
				Patient p = ps.getPatientById(pid);
				request.setAttribute("p", p);
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " PatientId = " + pid + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " PatientId = " + pid + " ");
				request.setAttribute("appm", appm);
				request.setAttribute("op", "view");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("new")) {
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/new/newpatient.jsp");
				rd.forward(request, response);
				return;
			}
			if (op.equals("edit")) {
				request.setAttribute("op", "edit");
				try {
					pid = (int)request.getAttribute("pid");
				} catch (Exception e) {
					pid = Integer.parseInt(request.getParameter("pid"));
				}
				Patient p = ps.getPatientById(pid);
				request.setAttribute("p", p);
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " PatientId = " + pid + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " PatientId = " + pid + " ");
				request.setAttribute("appm", appm);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientIndex.jsp?op=edit");
				rd.forward(request, response);
				return;
			}
			if (op.equals("create")) {
				Patient p = new Patient();
				p.setAddress(request.getParameter("address"));
				p.setAge(Integer.parseInt(request.getParameter("age")));
				p.setEmail(request.getParameter("email"));
				p.setFamilyId(Integer.parseInt(request.getParameter("familyId")));
				p.setName(request.getParameter("name"));
				p.setSex(request.getParameter("sex"));
				p.setTel(request.getParameter("tel"));
				p = ps.createPatientInfo(p);
				request.setAttribute("p", p);
				pid = p.getPatientId();
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " PatientId = " + pid + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " PatientId = " + pid + " ");
				request.setAttribute("appm", appm);
				request.setAttribute("op", "view");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("update")) {
				Patient p = new Patient();
				p.setAddress(request.getParameter("address"));
				p.setAge(Integer.parseInt(request.getParameter("age")));
				p.setEmail(request.getParameter("email"));
				p.setFamilyId(Integer.parseInt(request.getParameter("familyId")));
				p.setName(request.getParameter("name"));
				p.setPatientId(Integer.parseInt(request.getParameter("patientId")));
				p.setSex(request.getParameter("sex"));
				p.setTel(request.getParameter("tel"));
				p = ps.updatePatientInfo(p);
				request.setAttribute("p", p);
				pid = p.getPatientId();
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " PatientId = " + pid + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " PatientId = " + pid + " ");
				request.setAttribute("appm", appm);
				request.setAttribute("op", "view");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("delete")) {
				try {
					pid = (int)request.getAttribute("pid");
				} catch (Exception e) {
					pid = Integer.parseInt(request.getParameter("pid"));
				}
				ps.deletePatientById(pid);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/adminServlet");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
