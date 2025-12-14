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