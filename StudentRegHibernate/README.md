#### Student.java
```java
package com.cdac;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String mobile;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
```

### DAO Layer

#### StudentDAO.java
```java
package com.cdac;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StudentDAO {
    
    private SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        return cfg.buildSessionFactory();
    }
    
    public void writeStudent(Student s) throws Exception {
        SessionFactory factory = getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        session.persist(s);
        
        tx.commit();
        session.close();
    }
    
    public List<Student> getAllStudents() {
        SessionFactory factory = getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        List<Student> list = session.createQuery("from Student", Student.class).list();
        
        tx.commit();
        session.close();
        return list;
    }
    
    public Student getStudentById(int id) {
        SessionFactory factory = getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        Student student = session.get(Student.class, id);
        
        tx.commit();
        session.close();
        return student;
    }
    
    public void updateStudent(Student s) throws Exception {
        SessionFactory factory = getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        session.merge(s);
        
        tx.commit();
        session.close();
    }
    
    public void deleteStudent(int id) throws Exception {
        SessionFactory factory = getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
        }
        
        tx.commit();
        session.close();
    }
}
```

### Servlet Classes

#### RegisterServlet.java
```java
package com.cdac;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet 
{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
    	
        String name  = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        
        Student s = new Student();
       s.setName(name);
       s.setEmail(email);
       s.setMobile(mobile);
        
        
        StudentDAO sd = new StudentDAO();
        
               
					 try {
						sd.writeStudent(s);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					 res.sendRedirect("success.jsp");
    }
}
```

#### ViewStudentsServlet.java
```java
package com.cdac;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewStudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	 StudentDAO sd = new StudentDAO();
    	
    	 List<Student> lst=null;
	
			lst = sd.getAllStudents();
		

        req.setAttribute("studentList", lst);
        
        RequestDispatcher rd = req.getRequestDispatcher("viewStudents.jsp");
        rd.forward(req, resp);
    }
}
```

#### UpdateServlet.java
```java
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
```

#### DeleteServlet.java
```java
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
```

### Configuration Files

#### web.xml
```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xmlns="https://jakarta.ee/xml/ns/jakartaee" 
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd" 
         id="WebApp_ID" version="5.0">
    
    <display-name>Student Registration Hibernate Application</display-name>
    
    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
        <welcome-file>index.jsp</welcome-file>
        <welcome-file>register.html</welcome-file>
    </welcome-file-list>
    
    <!-- Register Servlet -->
    <servlet>
        <servlet-name>RegisterServlet</servlet-name>
        <servlet-class>com.cdac.RegisterServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>RegisterServlet</servlet-name>
        <url-pattern>/register</url-pattern>
    </servlet-mapping>
    
    <!-- View Students Servlet -->
    <servlet>
        <servlet-name>ViewStudentsServlet</servlet-name>
        <servlet-class>com.cdac.ViewStudentsServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewStudentsServlet</servlet-name>
        <url-pattern>/view</url-pattern>
    </servlet-mapping>
    
    <!-- Update Servlet -->
    <servlet>
        <servlet-name>UpdateServlet</servlet-name>
        <servlet-class>com.cdac.UpdateServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>UpdateServlet</servlet-name>
        <url-pattern>/update</url-pattern>
    </servlet-mapping>
    
    <!-- Delete Servlet -->
    <servlet>
        <servlet-name>DeleteServlet</servlet-name>
        <servlet-class>com.cdac.DeleteServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>DeleteServlet</servlet-name>
        <url-pattern>/delete</url-pattern>
    </servlet-mapping>
    
</web-app>
```

#### hibernate.cfg.xml
```xml
<hibernate-configuration>
<session-factory>
<!--  Database Connectivity  -->
<property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/dac</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">982223</property>
<!--  Dialect  -->
<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
<!--  DDL Auto  -->
<property name="hibernate.hbm2ddl.auto">update</property>
<!--  Show SQL  -->
<property name="hibernate.show_sql">true</property>
<!--  Mapped Class  -->
<mapping class="com.cdac.Student"/>
</session-factory>
</hibernate-configuration>
```

### HTML Files

#### register.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
</head>
<body>
<h2>Student Registration Form</h2>

<form action="register" method="post">
    Name: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Mobile: <input type="text" name="mobile" required><br><br>

    <input type="submit" value="Register">
</form>

<br>
<a href="view">View All Students</a>

</body>
</html>
```

#### error.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Error is Registration!</h2>
<a href="register.html">Go Back</a> |

</body>
</html>
```

#### success.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Student Registered Successfully!</h2>
<a href="register.html">Go Back</a> |
<a href="view">View Students</a>
</body>
</html>
```

#### updateStudent.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cdac.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
</head>
<body>
<h2>Update Student Information</h2>

<%
    Student student = (Student) request.getAttribute("student");
%>

<form action="update" method="post">
    <input type="hidden" name="id" value="<%= student.getId() %>">
    
    Name: <input type="text" name="name" value="<%= student.getName() %>" required><br><br>
    Email: <input type="email" name="email" value="<%= student.getEmail() %>" required><br><br>
    Mobile: <input type="text" name="mobile" value="<%= student.getMobile() %>" required><br><br>

    <input type="submit" value="Update">
</form>

<br>
<a href="view">Back to Student List</a>

</body>
</html>
```

#### viewStudents.jsp
```jsp
<%@ page import="java.util.*, com.cdac.Student" %>

<h2>All Registered Students</h2>

<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Actions</th>
    </tr>

<%
    List<Student> list = (List<Student>) request.getAttribute("studentList");
    if (list != null && !list.isEmpty()) {
        for (Student s : list) {
%>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getName() %></td>
        <td><%= s.getEmail() %></td>
        <td><%= s.getMobile() %></td>
        <td>
            <a href="update?id=<%= s.getId() %>">Edit</a> |
            <a href="delete?id=<%= s.getId() %>" 
               onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
        </td>
    </tr>
<%
        }
    } else {
%>
    <tr>
        <td colspan="5" style="text-align: center;">No students registered yet.</td>
    </tr>
<%
    }
%>
</table>

<br>
<a href="register.html">Register Another Student</a>
```

### pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>cdac</groupId>
  <artifactId>StudentRegHibernate</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>StudentRegHibernate Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    
    
    <dependency>
<groupId>org.hibernate.orm</groupId>
<artifactId>hibernate-core</artifactId>
<version>7.2.0.CR4</version>
</dependency>
<!--  https://mvnrepository.com/artifact/mysql/mysql-connector-java  -->
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>8.0.33</version>
</dependency>
<!--  Servlet API  -->
<dependency>
<groupId>jakarta.servlet</groupId>
<artifactId>jakarta.servlet-api</artifactId>
<version>6.0.0</version>
<scope>provided</scope>
</dependency>
<!--  JSP API  -->
<dependency>
<groupId>jakarta.servlet.jsp</groupId>
<artifactId>jakarta.servlet.jsp-api</artifactId>
<version>3.1.1</version>
<scope>provided</scope>
</dependency>

  </dependencies>
  <build>
    <finalName>StudentRegHibernate</finalName>
  </build>
</project>

```
