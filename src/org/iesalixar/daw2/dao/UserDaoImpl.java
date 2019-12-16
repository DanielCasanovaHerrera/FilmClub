package org.iesalixar.daw2.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.daw2.helper.HibernateUtil;
import org.iesalixar.daw2.model.User;
/*
Class that contains all the methods of Users*/
public class UserDaoImpl implements UserDao{
	
	final static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	/*
method for a user to login*/
	public boolean login(String username, String password) {

		User user = null;
		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			user = session
					.createQuery("SELECT u FROM User As u where u.username=:username and u.password=:password",
							User.class)
					.setParameter("username", username).setParameter("password", password).uniqueResult();
			logger.info("UserDAOImpl.login :  "+user);
		} catch (Exception e) {
			logger.error("UserDAOImpl.login has raised an exception: " + e.getMessage());
		}

		return (user != null);
	}
	/*
method that returns the user's role*/
	public String getUserRole(String username) {
		// TODO Auto-generated method stub
		String role="user";
		User user = null;
		Session session = null;
		
		try {
			
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			user = session
					.createQuery("SELECT u FROM User As u where u.username=:username",
							User.class)
					.setParameter("username", username).uniqueResult();
			role=user.getRole();
			logger.info("UserDAOImpl.getUserRole :  "+role);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("UserDAOImpl.getUserRole has raised an exception: " + e.getMessage());

		}
		return role;
	}
	
	/*method that returns a user id according to the username*/
	public int getUserID(String username) {
		// TODO Auto-generated method stub
		int id=-1;
		User user = null;
		Session session = null;
		
		try {
			
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			user = session
					.createQuery("SELECT u FROM User As u where u.username=:username",
							User.class)
					.setParameter("username", username).uniqueResult();
			id=user.getUser_id();
			logger.info("UserDAOImpl.getUserID :  "+id);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("UserDAOImpl.getUserID has raised an exception: " + e.getMessage());

		}
		
		
		return id;
	}
	/*method that returns a user according to the username*/
	public static User getUserIDForUsername(String username) {
		// TODO Auto-generated method stub
		User user = null;
		Session session = null;
		
		try {
			
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			user = session
					.createQuery("SELECT u FROM User As u where u.username=:username",
							User.class)
					.setParameter("username", username).uniqueResult();
			
			logger.info("UserDAOImpl.getUserID :  "+user);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("UserDAOImpl.getUserID has raised an exception: " + e.getMessage());

		}
		
		
		return user;
	}
	
	/*method that creates a user*/
	public boolean createUser(String username, String password, String fullname, String address, String email) {

		boolean success = true;	

		User user = new User();
		user.setUser_fullname(fullname);
		user.setUsername(username);
		user.setPassword(password);
		user.setAddress(address);
		user.setEmail(email);
		user.setRole("user");

		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			logger.info("UserDAOImpl.createUser :  "+user);
		} catch (Exception e) {
			logger.error("UserDaoImpl.create has raised an exception: "); // +e.getMessage());
			e.printStackTrace();
			success = false;
		}

		return success;
	}

	/*
method that modifies a user*/
	public static boolean updateUser(User user) {
		
		boolean success = false;

		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();

			success = true;
			session.close();
		} catch (Exception e) {
			logger.error("'UserDAO.updateUser' method has raised an exception: " + e.getMessage());
		}

		return success;
	}

}
