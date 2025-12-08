package com.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertBooksServlet extends HttpServlet{
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		int id = Integer.parseInt(req.getParameter("idname"));
		String title = req.getParameter("titlename");
		String author = req.getParameter("authorname");
		String genre = req.getParameter("genrename");
		int year = Integer.parseInt(req.getParameter("yearname"));
		
		Books b = new Books(id, title, author, genre, year);
		BooksDao ddao = new BooksDao();
		
		boolean status = false;
		
		try {
			status = ddao.saveBook(b);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(status) {
			out.println("<h1> Registered Successfully</h1>");
		}
		else {
			out.println("<h1> Error in Registration</h1>");
		}
		
		//req.setAttribute(, genre, ddao)
		
		
	}
}
