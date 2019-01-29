package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			Student temp = new Student("Paul", "Wall", "paul@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the sudent obj
			System.out.println("Saving the student...");
			session.save(temp);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}

}
