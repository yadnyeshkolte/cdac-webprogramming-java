package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BooksDao {
	
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cwpj","root","982223");
		return con;
	}
	
	public boolean saveBook(Books b) throws ClassNotFoundException, SQLException {
		String q = "insert into books values(?,?,?,?,?)";
		PreparedStatement pmt = createConnection().prepareStatement(q);
		pmt.setInt(1, b.getId());
		pmt.setString(2, b.getTitle());
		pmt.setString(3, b.getAuthor());
		pmt.setString(4, b.getGenre());
		pmt.setInt(5, b.getYear());
		
		int row = pmt.executeUpdate();
		
		boolean status = false;
		if(row>0) {
			status = true;
		}
		
		return status;
		
	}
}
