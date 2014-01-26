package com.ing.mobile.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//comment
public class HibernateUtil {
	
	private static Logger log = Logger.getLogger("hibernateUtil");
	private static SessionFactory sessionFactory;
	
	static{
		try{
			log.debug("About to create the session factory");
			sessionFactory = new Configuration().configure().buildSessionFactory();
			log.debug("done");
		}catch (Throwable e) {
			log.error("Exception",e);
		}	
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown(){
		getSessionFactory().close();
	}
}

