package com.cineplex.model.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ModelManager {
	
	private static ModelManager instance = null;
	private Configuration config;
	private static SessionFactory sessionFactory = null;
	
	private ModelManager(){
		super();
		config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	
	public static ModelManager sharedInstance(){
		if(instance == null){
			instance = new ModelManager();
		}
		return instance;
	}
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
}
