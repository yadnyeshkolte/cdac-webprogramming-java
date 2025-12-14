package com.cdac;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        
        StudentDAO sd = new StudentDAO();
        
        try {
            sd.deleteStudent(id);
            res.sendRedirect("view");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("error.html");
        }
    }
}