package com.ticket.dbconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class hibernateConnection {
	
	static SessionFactory sessionFactory = null;
	static Session session = null;
	Transaction transaction = null;
	
	public static void setSession() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	 	  //session = sessionFactory.openSession();
	 	  
	}
	
		
	public static Session getSession() {
		
			session = sessionFactory.openSession();
		
		  
		  return session;
	 }
	
	public Transaction getTransaction(Session session) {
		transaction = session.beginTransaction();
		return transaction;
	}
		
	public void closeSessionwithCommit(Session session) {
             session.getTransaction().commit(); 
             session.close();
             
	}
	
	
}