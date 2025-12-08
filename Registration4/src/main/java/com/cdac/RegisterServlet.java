package com.cdac;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{

	public void doPost(HttpServletRequest request , HttpServletResponse response  ) throws IOException, ServletException
	{
					boolean status = false;
		
		          response.setContentType("text/html");
		          PrintWriter out=response.getWriter();
		          
		          String name =request.getParameter("name");
		          String email =request.getParameter("email");
		          String course  =request.getParameter("course");
		          
		          
		          

		          Student s = new Student(name,email,course);
		          
		          StudentDAO  sd = new StudentDAO();
		          
		          
		          try {
					status = sd.writeStudent(s);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          
		         if(status) {
		        	 response.sendRedirect("success.jsp");
		         }
		         else {
		        	 response.sendRedirect("error.html");
		         }
		          /*
		          
		          StudentDao sdao = new StudentDao();
		          boolean status=false;
		          try {
		        	  
		        	   status=sdao.saveStudent(s);
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          
		          
		          if(status)
		          {
		          out.println("<h2> Registered successfully </h2>");
		          }else
		          {
		        	  out.println("<h2> error in Registration </h2>"); 
		          }
//		          request.setAttribute("stu",s );
//		          
//		          
//		          
//		          RequestDispatcher rd =request.getRequestDispatcher("view.jsp");
//		          rd.forward(request, response);
		          
		          
		          
		          */
//    	        	 
		        
		          }
		          
		          
		
		
	} 
	


