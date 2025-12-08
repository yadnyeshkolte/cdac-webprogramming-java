package com.cdac;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "982223"; // ⚠️ CHANGE THIS
    
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }
    
    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (title, author, genre, year_published) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre());
            pstmt.setInt(4, book.getYearPublished());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY id DESC";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setYearPublished(rs.getInt("year_published"));
                bookList.add(book);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookList;
    }
}