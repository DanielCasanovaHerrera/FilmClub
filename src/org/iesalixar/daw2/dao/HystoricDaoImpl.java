package org.iesalixar.daw2.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.daw2.helper.HibernateUtil;
import org.iesalixar.daw2.model.Hystoric;
import org.iesalixar.daw2.model.Product;
import javax.swing.JOptionPane;
import org.iesalixar.daw2.model.User;

public class HystoricDaoImpl {
	final static Logger logger = Logger.getLogger(HystoricDaoImpl.class);
	
	public static boolean createHystoric(Product product_id,User user_id) {
		
		boolean result = false;
		Hystoric hystoric=new Hystoric();
		hystoric.setProduct_id(product_id);
		hystoric.setUser_id(user_id);
		
		Session session = null;
		HibernateUtil.buildSessionFactory();
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(hystoric);
			session.getTransaction().commit();
			result = true;
			logger.info("HystoricDaoImpl.createHystoric' " + hystoric);
		} catch (Exception e) {
			logger.error("'HystoricDaoImpl.createHystoric' method has raised an exception: " + e.getMessage());
			result = false;
		}
		
		return result;
		
	}
	
	public static boolean removeHystoric(int user_id,int product_id) {
		boolean success = true;

		Session session = null;

		try {
			Hystoric hystoric = getHystoricId(user_id,product_id);
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(hystoric);
			session.getTransaction().commit();
			logger.info(hystoric);

		} catch (Exception e) {
			logger.error("HystoricDaoImpl.remove has raised an exception: " + e.getMessage());
			success = false;
		}

		return success;
	}
	
	public static Hystoric getHystoricId(int user_id,int product_id) {
		
		String sql="";
		Hystoric hystoric = null;
		Session session = null;

		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			hystoric = session.createQuery("SELECT h FROM Hystoric As h where h.user_id=:user_id AND h.product_id:=product_id",Hystoric.class).setParameter("user_id", user_id).setParameter("product_id",  product_id).uniqueResult();
			logger.info(hystoric);
		} catch (Exception e) {
			logger.error("HystoricDaoImpl.getProductId has raised an exception: " + e.getMessage());
		}

		return hystoric;
	}

}
