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
 * Servlet implementation class FamilyServlet
 */
@WebServlet("/FamilyServlet")
public class FamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Family f = null;
	FamilyService fs = new FamilyService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if (op.equals("delete")) {
			try {
				fs.deleteFamily(Integer.parseInt(request.getParameter("fid"))); 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/adminServlet");
				rd.forward(request, response);
			}
			return;
		}
		if (op.equals("update")) {
			try {
				Family f = new Family();
				f.setFamilyId(Integer.parseInt(request.getParameter("fid")));
				f.setAddress(request.getParameter("add"));
				fs.updateFamily(f);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				request.removeAttribute("add");
				request.setAttribute("op", "view");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/familyServlet?op=view");
				rd.forward(request, response);
			}
			return;
		}
		int fid = 0;
		try {
			fid = (int)request.getAttribute("fid");
		} catch (Exception e) {
			fid = Integer.parseInt(request.getParameter("fid"));
		}
		f = fs.queryFamily(fid);
		PatientPageModel ppm = new PatientPageModel();
		ppm.setLp(fs.queryFamilyMembers(fid));
		
		request.setAttribute("ppm", ppm);
		request.setAttribute("op", op);
		request.setAttribute("f", f);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/familyIndex.jsp");
		rd.forward(request, response);	
	}


}
