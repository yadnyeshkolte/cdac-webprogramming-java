Problem Statement 2: 
Book Library Management System (Insert + View Only)**

### *(Servlet + JSP + JDBC) — With View Servlet Calling Explanation*

Develop a *Book Library Management Web Application* using *Servlets, JSP, and JDBC*.
The system must support *only two operations: Insert Book and View Books*.

---

# *✔ 1. Add New Book Details*

Create a form page named **addBook.html**, where the user can enter:

* *Book Title*
* *Author Name*
* *Genre*
* *Year Published*

When the form is submitted:

1. A servlet named **AddBookServlet** should read the form parameters.
2. The servlet establishes a JDBC connection to MySQL.
3. Inserts the data into a table called **books**.
4. After successful insertion, the servlet forwards the request to success.jsp which shows:
   *“Book Added Successfully!”*

---

# *✔ 2. View All Books (Calling the ViewBooksServlet)*

To call the **ViewBooksServlet**, create a link or button on the success.jsp page or on a separate menu page.

### *Example: Add a button or link to call the ViewBooksServlet*

In success.jsp or addBook.html, add:

html
<a href="ViewBooksServlet">View All Books</a>


OR

html
<form action="ViewBooksServlet" method="get">
    <button type="submit">View All Books</button>
</form>


This triggers the servlet.

---

# *✔ Function of ViewBooksServlet*

The servlet should:

1. Connect to MySQL using JDBC.
2. Fetch all books from the books table.
3. Set the list in request attributes using:

   java
   request.setAttribute("bookList", list);
   
4. Forward the request to **viewBooks.jsp**, where the data is displayed in a table.

---

# *✔ 3. Database Structure*

sql
CREATE TABLE books (
   id INT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(150),
   author VARCHAR(100),
   genre VARCHAR(50),
   year_published INT
);


---

# *✔ 4. Application Flow*


addBook.html  
      ↓ (POST)
AddBookServlet  
      ↓ insert into DB  
success.jsp  
      ↓ (Click View All Books link/button)
ViewBooksServlet  
      ↓ fetch from DB  
viewBooks.jsp


---

# *✔ 5. Technologies*

| Layer     | Technology    |
| --------- | ------------- |
| Frontend  | HTML, JSP     |
| Backend   | Servlet       |
| Database  | MySQL         |
| DB Access | JDBC          |
| Server    | Apache Tomcat |