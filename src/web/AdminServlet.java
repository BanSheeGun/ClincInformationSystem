package web;

import java.io.IOException;
import java.util.*;
import entity.*;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	private FamilyService fs = new FamilyService();
	private int fn = 0;
	private PatientService ps = new PatientService();
	private int pn = 0;
	private DentistService ds = new DentistService();
	private int dn = 0;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
		try {
			fn = Integer.parseInt(request.getParameter("fn"));
		} catch (Exception e) {
			fn = 1;
		}
		int ftn = fs.queryFamilyNumbers();
		ftn = (ftn + 9) / 10;
		if (fn > ftn)
			fn = ftn; 
		if (fn < 1)
			fn = 1;
		List<Family> lf = fs.queryFamilyList((fn-1)*10);
		FamilyPageModel fpm = new FamilyPageModel();
		fpm.setFn(fn);
		fpm.setFtn(ftn);
		fpm.setLf(lf);
		

		try {
			dn = Integer.parseInt(request.getParameter("dn"));
		} catch (Exception e) {
			dn = 1;
		}
		int dtn = ds.getDentistNumbers();
		dtn = (dtn + 9) / 10;
		if (dn > dtn)
			dn = dtn; 
		if (dn < 1)
			dn = 1;
		List<Dentist> ld = ds.getDentist((dn-1)*10);
		DentistPageModel dpm = new DentistPageModel();
		dpm.setDn(dn);
		dpm.setDtn(dtn);
		dpm.setLd(ld);
		
		try {
			pn = Integer.parseInt(request.getParameter("pn"));
		} catch (Exception e) {
			pn = 1;
		}
		int ptn = ps.getPatientNumbers();
		ptn = (ptn + 9) / 10;
		if (pn > ptn)
			pn = ptn; 
		if (pn < 1)
			pn = 1;
		List<Patient> lp = ps.getPatient((pn-1)*10);
		PatientPageModel ppm = new PatientPageModel();
		ppm.setPn(pn);
		ppm.setPtn(ptn);
		ppm.setLp(lp);
		request.setAttribute("ppm", ppm);
		request.setAttribute("fpm", fpm);
		request.setAttribute("dpm", dpm);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/adminview.jsp");
		rd.forward(request, response);
	}

}
