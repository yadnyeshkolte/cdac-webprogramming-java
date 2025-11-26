package com.cdac;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class JdbcCallable 
{

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/dbda","root","cdacacts");
		
		System.out.println("get connection with data base");
		
//		        CallableStatement cmt=con.prepareCall("{ call getEmpName(? ,?) }");
//		         System.out.println("enter the dept no");
//		        Scanner s = new Scanner(System.in);
//				int dno = s.nextInt();
//				cmt.setInt(1, dno);
//				
//				cmt.executeQuery();
//				cmt.registerOutParameter(2,Types.VARCHAR);
//		        String  ename = cmt.getString(2);
//				System.out.println(ename +  " "+" is getting max salary " );
		        
		        
		        
        CallableStatement cmt=con.prepareCall("{ call getEmpdetails(?) }");
        System.out.println("enter the emp no");
       Scanner s = new Scanner(System.in);
		int eno = s.nextInt();
		cmt.setInt(1, eno);
		
		ResultSet rs = cmt.executeQuery();
		
		 while(rs.next())
         {
       	  System.out.println(rs.getInt("EMPNO")+ " "+ rs.getString(2)+ " "+rs.getFloat(6) );
         }     
		        
	}
	
	
}
