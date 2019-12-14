package org.iesalixar.daw2.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.daw2.helper.HibernateUtil;
import org.iesalixar.daw2.model.Type;

public class TypeDaoImpl {
	final static Logger logger = Logger.getLogger(TypeDaoImpl.class);
	
		public static Type getTypeId(int id) {
		Type type = null;
		Session session = null;

		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			type = session.get(Type.class, id);
			logger.info(type);
		} catch (Exception e) {
			logger.error("TypeDAOImpl.getTypeId has raised an exception: " + e.getMessage());
		}

		return type;
	}
	 
	 

}
