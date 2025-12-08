package com.cdac;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    
    @Override
    public void init() {
        bookDAO = new BookDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String yearStr = request.getParameter("year");
        
        try {
            int yearPublished = Integer.parseInt(yearStr);
            
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setYearPublished(yearPublished);
            
            boolean success = bookDAO.addBook(book);
            
            if (success) {
                request.setAttribute("bookTitle", title);
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.html");
            }
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}