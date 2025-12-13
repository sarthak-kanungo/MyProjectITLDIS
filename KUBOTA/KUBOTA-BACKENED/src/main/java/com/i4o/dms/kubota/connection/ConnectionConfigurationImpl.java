package com.i4o.dms.kubota.connection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;


public class ConnectionConfigurationImpl implements ConnectionConfiguration{

	@PersistenceContext
	EntityManager entityManager;
	
//	public static EntityManager getEntityManager() {
//		Map<String, String> properties = new HashMap<>();
//		properties.put("javax.persistence.jdbc.user", "admin");
//		properties.put("javax.persistence.jdbc.password", "admin");
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://localhost:6136/myDbFile.odb",
//				properties);
//		return emf.createEntityManager();
//	}
	
	public Connection getConnection(){
		Session session = (Session)entityManager.getDelegate();
		SessionImpl sessionImpl = (SessionImpl) session;
		return sessionImpl.connection();
	}
}
