package com.cdac;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        
        StudentDAO sd = new StudentDAO();
        Student student = sd.getStudentById(id);
        
        req.setAttribute("student", student);
        req.getRequestDispatcher("updateStudent.jsp").forward(req, res);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setEmail(email);
        s.setMobile(mobile);
        
        StudentDAO sd = new StudentDAO();
        
        try {
            sd.updateStudent(s);
            res.sendRedirect("view");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("error.html");
        }
    }
}