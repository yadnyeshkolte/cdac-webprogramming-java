package com.cdac;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request , HttpServletResponse response  ) throws IOException, ServletException
	{
		StudentDAO sd = new StudentDAO();

		//List<Student> students = sd.getAllStudent();
		
		List<Student> list = null;
		try {
			list = sd.getAllStudent();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("studentList", list);
		RequestDispatcher rd = request.getRequestDispatcher("viewStudents.jsp");
		rd.forward(request, response);
		
		        
	}
		          
}
