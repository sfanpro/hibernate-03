package ua.team.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ua.team.hibernate.entity.Instructor;
import ua.team.hibernate.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session factory
		Session session = factory.getCurrentSession();
		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor by the primary key id
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			System.out.println("find the instructor: " + tempInstructor);

			// delete the instructor
			if (tempInstructor != null) {
				System.out.println("Deleting: " + tempInstructor);
				// also delete detail objects
				session.delete(tempInstructor);
			}

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
