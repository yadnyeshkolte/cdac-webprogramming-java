package com.cdac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","982223");
		return con;
	}
	public boolean writeStudent(Student s) throws ClassNotFoundException, SQLException {
		Connection con = StudentDAO.getConnection();
		
		
	    String q = "insert into student(name, email, course) values (?,?,?)";
		
        PreparedStatement pmt =con.prepareStatement(q);
        
        pmt.setString(1, s.getName());
        pmt.setString(2, s.getEmail());
        pmt.setString(3, s.getCourse());
        int row =pmt.executeUpdate();

        boolean status = false;
         if(row>0)
         {
        	 status = true;
        	 
         }
       return status; 

	}
	
	public List<Student> getAllStudent() throws ClassNotFoundException, SQLException {
		List<Student> list = new ArrayList<>();
	    try (Connection con = StudentDAO.getConnection();
	            PreparedStatement pmt = con.prepareStatement("select * from student");
	            ResultSet rs = pmt.executeQuery()) {
	           
	           while(rs.next()) {
	               Student s = new Student();
	               s.setId(rs.getInt(1));
	               s.setName(rs.getString(2));
	               s.setEmail(rs.getString(3));
	               s.setCourse(rs.getString(4));
	               list.add(s);
	           }
	       }
	       return list;
	}
	
	
}
