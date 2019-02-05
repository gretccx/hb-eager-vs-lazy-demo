package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor detail obj
			int id = 3;
			InstructorDetail tempInstructorDetail =
					session.get(InstructorDetail.class, id);
			
			// print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// print the associated instructor
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
			
			// delete the instructor detail
			System.out.println("Deleting instructor detail£º " + tempInstructorDetail);
			// remove the associated obj reference
			// break bi-directional link
			tempInstructorDetail.getInstructor().setInstructroDetail(null);
			session.delete(tempInstructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
	}

}
