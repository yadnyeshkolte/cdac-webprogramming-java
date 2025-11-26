package com.student;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationServe extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String course = req.getParameter("course");
		
		out.println("<h1>"+id+"</h1>");
		out.println("<h1>"+name+"</h1>");
		out.println("<h1>"+email+"</h1>");
		out.println("<h1>"+gender+"</h1>");
		out.println("<h1>"+course+"</h1>");

		//res.sendRedirect("display.html");
	}
}
