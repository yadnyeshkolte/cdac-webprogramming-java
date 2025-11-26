package com.cdac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatement
{

public static void main(String[] args) throws ClassNotFoundException, SQLException 
{

	//load the driver
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/dbda","root","cdacacts");
	//create statement
	
	      Statement smt =con.createStatement();
	      String q1 = "select * from emp";
	      
          ResultSet rs=smt.executeQuery(q1);
          
          while(rs.next())
          {
        	  System.out.println(rs.getInt("EMPNO")+ " "+ rs.getString(2)+ " "+rs.getFloat(6) );
          }
          
          rs.close();
          
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	
	
	
}	
	
}
