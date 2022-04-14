package com.example.mtm;

import com.example.mtm.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
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

            int courseId = 1;
            Course course = session.get(Course.class, courseId);

            System.out.println("Deleting course: " + course);
            session.delete(course);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
