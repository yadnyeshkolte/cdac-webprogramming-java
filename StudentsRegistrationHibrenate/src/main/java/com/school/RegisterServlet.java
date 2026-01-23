package com.school;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		Student s = new Student();

		s.setName(req.getParameter("name"));
		s.setCourse(req.getParameter("course"));
		s.setEmail(req.getParameter("email"));
		
		
	}
}
