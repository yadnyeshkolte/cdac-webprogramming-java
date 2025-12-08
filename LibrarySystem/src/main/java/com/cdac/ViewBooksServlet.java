package com.cdac;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewBooksServlet")
public class ViewBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    
    @Override
    public void init() {
        bookDAO = new BookDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Book> bookList = bookDAO.getAllBooks();
        request.setAttribute("bookList", bookList);
        request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}