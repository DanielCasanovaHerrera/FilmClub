package org.iesalixar.daw2.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.daw2.helper.HibernateUtil;
import org.iesalixar.daw2.model.*;
/*
Class that contains all the methods of Rent*/
public class RentDaoImpl {

	final static Logger logger = Logger.getLogger(RentDaoImpl.class);
	/*method that activates or deactivates the rental of a product*/
	public static boolean createOrChangeRent(Product product_id, User user_id, Date rentOut, Date rentIn) {

		boolean result = false;
		Rent rent = new Rent();
		rent.setProduct_id(product_id);
		rent.setUser_id(user_id);
		rent.setRent_dateOut(rentOut);
		rent.setRent_dateIn(rentIn);

		Session session = null;
		HibernateUtil.buildSessionFactory();
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(rent);
			session.getTransaction().commit();
			result = true;
			logger.info("RentDaoImpl.createOrChangeRent' " + rent);
		} catch (Exception e) {
			logger.error("'RentDaoImpl.createOrChangeRent' method has raised an exception: " + e.getMessage());
			result = false;
		}

		return result;

	}
	/*method that returns a list of rent according to the username*/
	public static List<Rent> rentUser(String username) {
		UserDaoImpl userdaoimpl= new UserDaoImpl();
		int user_id=userdaoimpl.getUserID(username);
		String sql= "SELECT r FROM Rent As r WHERE r.user_id='"+user_id+"'";
		List<Rent> rent =  new ArrayList<Rent>();
		Session session = null;
		
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			rent=session.createQuery(sql,Rent.class).list();
			
			logger.info("RentDaoImpl.rentUser' " + rent);
		} catch (Exception e) {
			logger.error("'RentDaoImpl.rentUser' method has raised an exception: " + e.getMessage());
			
		}

		return rent;

	}
	/*method that remove a Rent*/
	public static boolean removeRent(int rent_id) {
		boolean success = true;

		Session session = null;

		try {
			Rent rent = getRentId(rent_id);
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(rent);
			session.getTransaction().commit();
			logger.info(rent);

		} catch (Exception e) {
			logger.error("ProductDAOImpl.remove has raised an exception: " + e.getMessage());
			success = false;
		}

		return success;
	}
	/*method that returns a rent according to the id*/
	public static Rent getRentId(int id) {
		Rent rent = null;
		Session session = null;

		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			rent = session.get(Rent.class, id);
			logger.info(rent);
		} catch (Exception e) {
			logger.error("ProductDAOImpl.getProductId has raised an exception: " + e.getMessage());
		}

		return rent;
	}

}
