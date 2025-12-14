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