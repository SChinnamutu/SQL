package com.perf.blog.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionUtil {
	
	
	@Autowired
	public EntityManagerFactory entityManagerFactory;
	
	public EntityManager getManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	
	
}
