package ua.team.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ua.team.hibernate.entity.Course;
import ua.team.hibernate.entity.Instructor;
import ua.team.hibernate.entity.InstructorDetail;
import ua.team.hibernate.entity.Review;
import ua.team.hibernate.entity.Student;

public class GetCoursesForMaryDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session factory
		Session session = factory.getCurrentSession();
		try {

			// start a transaction
			session.beginTransaction();

			// get student Mary from DB
			int studentId = 10;
			Student tempStudent = session.get(Student.class, studentId);

			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("\nCourses: " + tempStudent.getCourses());

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
