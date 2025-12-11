Based on the images provided, here is the complete Markdown file containing all the code, configurations, and notes.

-----

# Student Registration System with Hibernate (XML Configuration)

### 1\. Project Structure

**StudentRegHibernate/**

  * **src/**
      * `com.cdac.controller/`
          * `RegisterServlet.java`
          * `ViewStudentsServlet.java`
      * `com.cdac.dao/`
          * `StudentDAO.java`
      * `com.cdac.model/`
          * `Student.java`
      * `hibernate.cfg.xml`
  * **WebContent/**
      * `register.jsp`
      * `viewStudents.jsp`
      * `success.jsp`
      * **WEB-INF/**
          * `web.xml`

-----

### 2\. Student Entity

**File:** `Student.java`

```java
package com.cdac.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String course;

    public Student() {}

    public Student(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    // Getters & Setters
}
```

-----

### 3\. Hibernate Configuration File

**File:** `hibernate.cfg.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>

        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/studentdb</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">root</property>

        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>

        <mapping class="com.cdac.model.Student"/>

    </session-factory>
</hibernate-configuration>
```

-----

### 4\. Hibernate Utility Class (NO CHAINING)

*Note: You said do NOT use builder chaining, so I will create the SessionFactory the classic way.*

**File:** `HibernateUtil.java`

```java
package com.cdac.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            factory = cfg.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
```

  * **Checklist:**
      * [x] No method chaining
      * [x] Classic XML-based configuration

-----

### 5\. Corrected StudentDAO.java (No Chaining in Session Creation)

**File:** `StudentDAO.java`

```java
package com.cdac.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cdac.model.Student;

public class StudentDAO {

    // INSERT Student
    public static int save(Student s) {

        int id = 0;
        Transaction tx = null;

        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();

            // X No Chaining
            // factory.openSession().beginTransaction();   X Not allowed
            Session session = factory.openSession();    // OK

            tx = session.beginTransaction();

            id = (int) session.save(s);

            tx.commit();
            session.close();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }

        return id;
    }

    // SELECT All Students
    public static List<Student> getAllStudents() {

        List<Student> list = null;

        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();

            // X No Chaining
            // factory.openSession().createQuery(...).list(); X Not allowed
            Session session = factory.openSession();       // OK

            String hql = "FROM Student";

            Query<Student> query = session.createQuery(hql, Student.class);
            list = query.list();

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
```

**Additional HQL Example (Parameterized Query):**
*(This snippet demonstrates how to query by ID using parameters)*

```java
String hql = "FROM Student WHERE id = :id";

Query<Student> query = session.createQuery(hql, Student.class);
query.setParameter("id", id);
```

-----

### 6\. RegisterServlet – Insert Student

**File:** `RegisterServlet.java`

```java
package com.cdac.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.cdac.model.Student;
import com.cdac.dao.StudentDAO;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");

        Student s = new Student(name, email, course);
        int id = StudentDAO.save(s);

        if (id > 0)
            resp.sendRedirect("success.jsp");
        else
            resp.getWriter().println("Error in saving student");
    }
}
```

-----

### 7\. ViewStudentsServlet – Select All Students

**File:** `ViewStudentsServlet.java`

```java
package com.cdac.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import java.util.List;
import com.cdac.dao.StudentDAO;
import com.cdac.model.Student;

public class ViewStudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Student> students = StudentDAO.getAllStudents();

        req.setAttribute("studentList", students);
        RequestDispatcher rd = req.getRequestDispatcher("viewStudents.jsp");
        rd.forward(req, resp);
    }
}
```

-----

### 8\. JSP Files

  * **`register.jsp`**: Same as previous JDBC version (no change).
  * **`viewStudents.jsp`**: Same as previous JDBC version.

-----

### 9\. web.xml

**File:** `web.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://jakarta.ee/xml/ns/jakartaee"
         version="5.0">

    <servlet>
        <servlet-name>RegisterServlet</servlet-name>
        <servlet-class>com.cdac.controller.RegisterServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>RegisterServlet</servlet-name>
        <url-pattern>/register</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>ViewStudentsServlet</servlet-name>
        <servlet-class>com.cdac.controller.ViewStudentsServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>ViewStudentsServlet</servlet-name>
        <url-pattern>/view</url-pattern>
    </servlet-mapping>

</web-app>
```

-----

### FINAL RESULT

You now have a complete working Student Registration System with:

  * [x] HTML Form
  * [x] JSP UI
  * [x] Servlet Controller
  * [x] Hibernate DAO
  * [x] Insert + Select
  * [x] SessionFactory without chaining
  * [x] XML-based configuration
  * [x] web.xml instead of annotations