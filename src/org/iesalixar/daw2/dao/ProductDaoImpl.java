package org.iesalixar.daw2.dao;

import java.awt.Image;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.daw2.helper.HibernateUtil;
import org.iesalixar.daw2.model.Product;

public class ProductDaoImpl {

	final static Logger logger = Logger.getLogger(ProductDaoImpl.class);

	public static List<Product> getProducts(int user_id, boolean actived) {

		String sql = "";
		if (user_id == -1)
			sql = "SELECT p from Product As p WHERE p.active=" + (actived ? "1" : "0") + " AND p.state=1";
		else
			sql = "SELECT p from Product As p WHERE p.active='" + (actived ? "1" : "0") + "' AND p.user_id="+ user_id + " AND p.state=1'";

		List<Product> res = new ArrayList<Product>();
		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			res = session.createQuery(sql, Product.class).list();

			logger.info("ProductDAOImpl.getProducts"+res);
		} catch (Exception e) {
			logger.error("ProductDAOImpl.getProducts has raised an exception: " + e.getMessage());
		}
		return res;
	}
	
	public static List<Product> getForType(int user_id, boolean actived,int type_id) {

		String sql = "";
		if (user_id == -1)
			sql = "SELECT p from Product As p WHERE p.active=" + (actived ? "1" : "0") + " AND p.state=1 AND p.type_id='"+type_id+"'";
		else
			sql = "SELECT p from Product As p WHERE p.active='" + (actived ? "1" : "0") + "' AND p.user_id="+ user_id + " AND p.state=1 AND p.type_id='"+type_id+"'";

		List<Product> res = new ArrayList<Product>();

		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			res = session.createQuery(sql, Product.class).list();

			logger.info(res);
		} catch (Exception e) {
			logger.error("ProductDAOImpl.getProducts has raised an exception: " + e.getMessage());
		}
		return res;
	}
	
	
	public static Product getProductId(int id) {
		Product product = null;
		Session session = null;

		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			product = session.get(Product.class, id);
			logger.info(product);
		} catch (Exception e) {
			logger.error("ProductDAOImpl.getProductId has raised an exception: " + e.getMessage());
		}

		return product;
	}
	
	public static boolean remove(int product_id) {
		boolean success = true;

		Session session = null;

		try {
			Product product = getProductId(product_id);
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(product);
			session.getTransaction().commit();
			logger.info(product);

		} catch (Exception e) {
			logger.error("ProductDAOImpl.remove has raised an exception: " + e.getMessage());
			success = false;
		}

		return success;
	}
	
	public static byte[] loadImage(int product_id) {
		String sql = "";
		
		sql = "SELECT p from Product As p WHERE p.product_id='"+product_id+"'";
		

		Product product = null;

		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			product = session.createQuery(sql, Product.class).uniqueResult();

			logger.info(product);
		} catch (Exception e) {
			logger.error("ProductDAOImpl.getProducts has raised an exception: " + e.getMessage());
		}
		
		return product.getImg();
		
	}
	
	
	private static boolean changeToApprove(int product_id, boolean active) {
		boolean success = true;

		Session session = null;

		try {
			Product product = getProductId(product_id);
			product.setActive(active);
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(product);
			session.getTransaction().commit();
			logger.info(product);

		} catch (Exception e) {
			logger.error("ProductDAOImpl.changeToApprove has raised an exception: " + e.getMessage());
			success = false;
		}

		return success;
	}
	
	public static boolean setApproved(int product_id) {
		return changeToApprove(product_id, true);
	}

	public static boolean setUnapproved(int product_id) {
		return changeToApprove(product_id, false);
	}
	
	private static boolean changeToState(int product_id, boolean state) {
		boolean success = true;

		Session session = null;

		try {
			Product product = getProductId(product_id);
			product.setState(state);
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(product);
			session.getTransaction().commit();
			logger.info(product);

		} catch (Exception e) {
			logger.error("ProductDAOImpl.changeToState has raised an exception: " + e.getMessage());
			success = false;
		}

		return success;
	}
	
	public static boolean setState(int product_id) {
		return changeToState(product_id, true);
	}

	public static boolean setNotState(int product_id) {
		return changeToState(product_id, false);
	}
	
}
