package web;

import java.io.IOException;
import service.*;
import entity.*;

import javax.servlet.RequestDispatcher;
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
	private AppointmentService as = new AppointmentService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
		String oper = null;
		Payment pay = null;
		Invoice inv = null;
		String op = null;
		try {
			op = request.getParameter("op");
		} catch (Exception e) {
			op = (String)request.getAttribute("op");
		}
		try {
			if (op.equals("new")) {
				int did, aid;
				try {
					did = Integer.parseInt(request.getParameter("did"));
				} catch (Exception e) {
					did = (int)request.getAttribute("did");
				}
				try {
					aid = Integer.parseInt(request.getParameter("aid"));
				} catch (Exception e) {
					aid = (int)request.getAttribute("aid");
				}
				Appointment ap = as.queryAppointment(aid);
				prs.createPatientRecord(ap);
				as.deleteAppointment(aid);
				request.setAttribute("op", "view");
				request.setAttribute("did", did);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/dentistServlet?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("view")) {
				try {
					oper = request.getParameter("oper");
				} catch (Exception e) {
					oper = (String)request.getAttribute("oper");
				}
				int pid;
				try {
					pid = Integer.parseInt(request.getParameter("prid"));
				} catch (Exception e) {
					pid = (int)request.getAttribute("prid");
				}
				PatientRecord pr = prs.queryPatientRecord(pid);
				if (pr.getPaymentId() != 0) {
					pay = prs.queryPayment(pr.getPaymentId());
					if (pay.getInvoiceId() != 0)
						inv = prs.queryInvoice(pay.getInvoiceId());
				}
				request.setAttribute("pr", pr);
				request.setAttribute("pay", pay);
				request.setAttribute("inv", inv);
				request.setAttribute("op", "view");
				request.setAttribute("oper", oper);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/patientRecordIndex.jsp?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("update")) {
				try {
					oper = request.getParameter("oper");
				} catch (Exception e) {
					oper = (String)request.getAttribute("oper");
				}
				int pid;
				try {
					pid = Integer.parseInt(request.getParameter("pid"));
				} catch (Exception e) {
					pid = (int)request.getAttribute("pid");
				}
				String content = null;
				try {
					content = request.getParameter("con");
				} catch (Exception e) {
					content = (String)request.getAttribute("con");
				}
				prs.updatePatientRecord(pid, content);
				request.setAttribute("op", "view");
				request.setAttribute("oper", oper);
				request.setAttribute("prid", pid);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/patientRecordServlet?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("newpay")) {
				try {
					oper = request.getParameter("oper");
				} catch (Exception e) {
					oper = (String)request.getAttribute("oper");
				}
				int prid;
				try {
					prid = Integer.parseInt(request.getParameter("prid"));
				} catch (Exception e) {
					prid = (int)request.getAttribute("prid");
				}
				double number;
				try {
					number = Double.parseDouble(request.getParameter("num"));
				} catch (Exception e) {
					number = (double)request.getAttribute("num");
				}
				PatientRecord pr = prs.queryPatientRecord(prid);
				pay = new Payment();
				pay.setNumber(number);
				pay.setPatientId(pr.getPatientId());
				pay = prs.createPayment(pay);
				prs.updatePatientRecord(prid, pay.getPaymentId());
				request.setAttribute("op", "view");
				request.setAttribute("oper", oper);
				request.setAttribute("prid", prid);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/patientRecordServlet?op=view");
				rd.forward(request, response);
				return;
			}
			if (op.equals("newinv")) {
				try {
					oper = request.getParameter("oper");
				} catch (Exception e) {
					oper = (String)request.getAttribute("oper");
				}
				int prid;
				try {
					prid = Integer.parseInt(request.getParameter("prid"));
				} catch (Exception e) {
					prid = (int)request.getAttribute("prid");
				}
				int payid;
				try {
					payid = Integer.parseInt(request.getParameter("payid"));
				} catch (Exception e) {
					payid = (int)request.getAttribute("payid");
				}
				pay = prs.queryPayment(payid);
				inv = prs.createInvoice(pay);
				prs.updatePayment(payid, inv.getInvoiceId());
				request.setAttribute("op", "view");
				request.setAttribute("oper", oper);
				request.setAttribute("prid", prid);
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/patientRecordServlet?op=view");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
