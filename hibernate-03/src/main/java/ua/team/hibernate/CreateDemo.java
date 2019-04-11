package ua.team.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ua.team.hibernate.entity.Instructor;
import ua.team.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session factory
		Session session = factory.getCurrentSession();
		try {

			Instructor tempInstructor = new Instructor("Racine", "Chiog", "info@scoan.org");
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/user/scoanvideos",
					"meditate without breake");

			// associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor - this will also save the details object because
			// CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
