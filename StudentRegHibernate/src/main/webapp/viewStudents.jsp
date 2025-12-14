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