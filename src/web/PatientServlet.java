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
		try {
			int pid = (int)request.getAttribute("pid");
			if (op.equals("view")) {
				Patient p = ps.getPatientById(pid);
				request.setAttribute("p", p);
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " PatientId = " + pid + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " PatientId = " + pid + " ");
				request.setAttribute("appm", appm);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("new")) {
				return;
			}
			if (op.equals("create")) {
				Patient p = new Patient();
				p.setAddress((String)request.getAttribute("address"));
				p.setAge((int)request.getAttribute("age"));
				p.setEmail((String)request.getAttribute("email"));
				p.setFamilyId((int)request.getAttribute("familyId"));
				p.setName((String)request.getAttribute("name"));
				p.setPatientId((int)request.getAttribute("patientId"));
				p.setSex((String)request.getAttribute("sex"));
				p.setTel((String)request.getAttribute("tel"));
				p = ps.createPatientInfo(p);
				request.setAttribute("p", p);
				pid = p.getPatientId();
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " PatientId = " + pid + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " PatientId = " + pid + " ");
				request.setAttribute("appm", appm);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("update")) {
				Patient p = new Patient();
				p.setAddress((String)request.getAttribute("address"));
				p.setAge((int)request.getAttribute("age"));
				p.setEmail((String)request.getAttribute("email"));
				p.setFamilyId((int)request.getAttribute("familyId"));
				p.setName((String)request.getAttribute("name"));
				p.setPatientId((int)request.getAttribute("patientId"));
				p.setSex((String)request.getAttribute("sex"));
				p.setTel((String)request.getAttribute("tel"));
				p = ps.updatePatientInfo(p);
				request.setAttribute("p", p);
				pid = p.getPatientId();
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " PatientId = " + pid + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " PatientId = " + pid + " ");
				request.setAttribute("appm", appm);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
