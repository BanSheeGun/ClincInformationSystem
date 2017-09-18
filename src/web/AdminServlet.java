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
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.setAttribute("fpm", fpm);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/adminview.jsp");
		rd.forward(request, response);
	}

}
