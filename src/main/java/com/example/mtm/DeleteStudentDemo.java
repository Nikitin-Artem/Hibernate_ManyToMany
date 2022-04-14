package com.example.mtm;

import com.example.mtm.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Чтобы удалить студента, список курсов должен быть пустым для этого студента или поменят CascadeType, что удалит курсы и для других студентво
            int studentId = 1;
            Student tempStudent = session.get(Student.class, studentId);

            System.out.println("Deleting student: " + tempStudent);
            session.delete(tempStudent);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
