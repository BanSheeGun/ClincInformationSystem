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
		int did;
		try {
			if (op.equals("view")) {
				try {
					did = (int)request.getAttribute("did");
				} catch (Exception e) {
					did = Integer.parseInt(request.getParameter("did"));
				}
				Dentist d = ds.getDentistById(did);
				request.setAttribute("d", d);
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " DentistId = " + did + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " DentistId = " + did + " ");
				request.setAttribute("appm", appm);
				request.setAttribute("op", "view");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/dentistIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("new")) {
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/new/newdentist.jsp");
				rd.forward(request, response);
				return;
			}
			if (op.equals("edit")) {
				request.setAttribute("op", "edit");
				try {
					did = (int)request.getAttribute("did");
				} catch (Exception e) {
					did = Integer.parseInt(request.getParameter("did"));
				}
				Dentist d = ds.getDentistById(did);
				request.setAttribute("d", d);
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " DentistId = " + did + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " DentistId = " + did + " ");
				request.setAttribute("appm", appm);
				request.setAttribute("op", "edit");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/dentistIndex.jsp?op=edit");
				rd.forward(request, response);
				return;
			}
			if (op.equals("create")) {
				Dentist p = new Dentist();
				p.setAddress(request.getParameter("address"));
				p.setAge(Integer.parseInt(request.getParameter("age")));
				p.setEmail(request.getParameter("email"));
				p.setClinicId(Integer.parseInt(request.getParameter("clinicId")));
				p.setName(request.getParameter("name"));
				p.setSex(request.getParameter("sex"));
				p.setTel(request.getParameter("tel"));
				p = ds.createDentistInfo(p);
				request.setAttribute("d", p);
				did = p.getDentistId();
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " DentistId = " + did + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " DentistId = " + did + " ");
				request.setAttribute("appm", appm);
				request.setAttribute("op", "view");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/dentistIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("update")) {
				Dentist p = new Dentist();
				p.setAddress(request.getParameter("address"));
				p.setAge(Integer.parseInt(request.getParameter("age")));
				p.setEmail(request.getParameter("email"));
				p.setClinicId(Integer.parseInt(request.getParameter("clinicId")));
				p.setName(request.getParameter("name"));
				p.setDentistId(Integer.parseInt(request.getParameter("dentistId")));
				p.setSex(request.getParameter("sex"));
				p.setTel(request.getParameter("tel"));
				p = ds.createDentistInfo(p);
				request.setAttribute("d", p);
				did = p.getDentistId();
				PatientRecordPageModel prpm = prs.queryPatientRecord(prn, " DentistId = " + did + " ");
				request.setAttribute("prpm", prpm);
				AppointmentPageModel appm = aps.queryAppointment(apn, " DentistId = " + did + " ");
				request.setAttribute("appm", appm);
				request.setAttribute("op", "view");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/dentistIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("delete")) {
				try {
					did = (int)request.getAttribute("did");
				} catch (Exception e) {
					did = Integer.parseInt(request.getParameter("did"));
				}
				ds.deleteDentistById(did);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/adminServlet");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
