package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session obj to save the Java obj
			// create a student obj
			System.out.println("Creating new student object...");
			Student temp = new Student("Daffy", "Duck", "daffy@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the sudent obj
			System.out.println("Saving the student...");
			System.out.println(temp);
			session.save(temp);
			
			// commit transaction
			session.getTransaction().commit();
			
			// new code for retrieving
			// find out the student's id: PK which is set by Hibernate
			System.out.println("Saved student. Generated id: " + temp.getId());
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: PK
			System.out.println("\nGetting student with id: " + temp.getId());
			Student myStudent = session.get(Student.class, temp.getId());
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}

}
