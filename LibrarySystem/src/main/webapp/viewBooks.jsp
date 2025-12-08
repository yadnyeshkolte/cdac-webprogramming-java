<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.cdac.Book" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Books - Library Management</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 40px 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            padding: 40px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            flex-wrap: wrap;
            gap: 20px;
        }

        h1 {
            color: #667eea;
            font-size: 32px;
        }

        .btn {
            padding: 12px 25px;
            border: none;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            transition: all 0.3s ease;
            display: inline-block;
        }

        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 20px rgba(102, 126, 234, 0.3);
        }

        .stats {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
            text-align: center;
        }

        .stats h2 {
            font-size: 36px;
            margin-bottom: 5px;
        }

        .stats p {
            opacity: 0.9;
        }

        .table-container {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
            font-size: 14px;
            text-transform: uppercase;
        }

        tbody tr {
            border-bottom: 1px solid #e0e0e0;
            transition: all 0.2s ease;
        }

        tbody tr:hover {
            background: #f5f5f5;
        }

        td {
            padding: 15px;
            color: #333;
        }

        .genre-badge {
            background: #667eea;
            color: white;
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 12px;
            display: inline-block;
        }

        .no-data {
            text-align: center;
            padding: 60px 20px;
            color: #999;
        }

        .no-data-icon {
            font-size: 64px;
            margin-bottom: 20px;
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            h1 {
                font-size: 24px;
            }

            table {
                font-size: 14px;
            }

            th, td {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📚 Library Book Collection</h1>
            <a href="addBook.html" class="btn btn-primary">+ Add New Book</a>
        </div>

        <%
            List<Book> bookList = (List<Book>) request.getAttribute("bookList");
            int totalBooks = (bookList != null) ? bookList.size() : 0;
        %>

        <div class="stats">
            <h2><%= totalBooks %></h2>
            <p>Total Books in Library</p>
        </div>

        <div class="table-container">
            <% if (bookList != null && !bookList.isEmpty()) { %>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Genre</th>
                            <th>Year Published</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Book book : bookList) { %>
                            <tr>
                                <td><strong>#<%= book.getId() %></strong></td>
                                <td><%= book.getTitle() %></td>
                                <td><%= book.getAuthor() %></td>
                                <td>
                                    <span class="genre-badge"><%= book.getGenre() %></span>
                                </td>
                                <td><%= book.getYearPublished() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <div class="no-data">
                    <div class="no-data-icon">📚</div>
                    <h2>No Books Found</h2>
                    <p>The library is empty. Start by adding some books!</p>
                    <br>
                    <a href="addBook.html" class="btn btn-primary">Add Your First Book</a>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>