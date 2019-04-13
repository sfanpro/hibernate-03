package ua.team.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ua.team.hibernate.entity.Course;
import ua.team.hibernate.entity.Instructor;
import ua.team.hibernate.entity.InstructorDetail;
import ua.team.hibernate.entity.Review;
import ua.team.hibernate.entity.Student;

public class CreateCoursesAndStudetsDemo {

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

			// create a course
			Course tempCourse = new Course("meditation");

			// create the students
			Student tempStudent1 = new Student("Poll", "Wosher", "poll@tean.ua");
			Student tempStudent2 = new Student("Mary", "Happy", "mary@tean.ua");
			Student tempStudent3 = new Student("Nikol", "Apllebeiry", "nikol@tean.ua");
			Student tempStudent4 = new Student("Stiv", "Job", "stiv@tean.ua");

			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			tempCourse.addStudent(tempStudent3);
			tempCourse.addStudent(tempStudent4);

			// save the students
			System.out.println("\nSaving studet...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			session.save(tempStudent4);
			System.out.println("Saved students: " + tempCourse.getStudents());

			// save the course
			System.out.println("\nSaving the course...");
			session.save(tempCourse);
			System.out.println("saved the course: " + tempCourse);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
