package com.cdac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class jdbcPreparedStatement
{

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
	
		//load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/dbda","root","cdacacts");
		String q1 = "select * from emp where empno = ?";
		PreparedStatement pmt =con.prepareStatement(q1);
		System.out.println("enter the empno to search");
		Scanner s = new Scanner(System.in);
		int no = s.nextInt();
		pmt.setInt(1, no);
		ResultSet rs =pmt.executeQuery();
		 while(rs.next())
         {
       	  System.out.println(rs.getInt("EMPNO")+ " "+ rs.getString(2)+ " "+rs.getFloat(6) );
         }
         
		 rs.close();
		
		
		
		
		
	} 
	
	
	
}
