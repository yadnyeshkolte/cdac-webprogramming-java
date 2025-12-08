<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success - Book Added</title>
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
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            background: white;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            padding: 50px;
            text-align: center;
            max-width: 500px;
            animation: slideIn 0.5s ease;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .success-icon {
            width: 80px;
            height: 80px;
            background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 30px;
            animation: scaleIn 0.6s ease;
        }

        @keyframes scaleIn {
            from {
                transform: scale(0);
            }
            to {
                transform: scale(1);
            }
        }

        .success-icon::before {
            content: "✓";
            color: white;
            font-size: 50px;
            font-weight: bold;
        }

        h1 {
            color: #11998e;
            margin-bottom: 15px;
            font-size: 28px;
        }

        .message {
            color: #666;
            margin-bottom: 30px;
            font-size: 16px;
            line-height: 1.6;
        }

        .book-title {
            background: #f0f0f0;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 30px;
            color: #333;
            font-weight: 600;
        }

        .btn-container {
            display: flex;
            gap: 15px;
            justify-content: center;
        }

        .btn {
            padding: 14px 30px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
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

        .btn-secondary {
            background: #f0f0f0;
            color: #333;
        }

        .btn-secondary:hover {
            background: #e0e0e0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="success-icon"></div>
        <h1>Book Added Successfully!</h1>
        <p class="message">
            The book has been successfully added to the library database.
        </p>
        
        <% String bookTitle = (String) request.getAttribute("bookTitle"); %>
        <% if (bookTitle != null) { %>
            <div class="book-title">
                📖 "<%= bookTitle %>"
            </div>
        <% } %>
        
        <div class="btn-container">
            <a href="ViewBooksServlet" class="btn btn-primary">
                View All Books
            </a>
            <a href="addBook.html" class="btn btn-secondary">
                Add Another Book
            </a>
        </div>
    </div>
</body>
</html>