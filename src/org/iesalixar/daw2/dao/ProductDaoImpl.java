package org.iesalixar.daw2.dao;

import java.awt.Image;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.daw2.helper.HibernateUtil;
import org.iesalixar.daw2.model.Product;
import org.iesalixar.daw2.model.Type;
import org.iesalixar.daw2.model.User;
/*
Class that contains all the methods of Products*/
public class ProductDaoImpl {

	final static Logger logger = Logger.getLogger(ProductDaoImpl.class);

	/*method that returns a list of Products*/
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
	
	/*method that returns a list of products according to the type*/
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
	
	/*method that returns a product according to the id*/
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
	
	/*method that remove a Product*/
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
	
	/*method that return a img in bytes array*/
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
	
	/*method that changes the status of a product for active or innactive*/
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
	
	/*
method that checks the status of a product and changes it*/
	public static boolean setApproved(int product_id) {
		return changeToApprove(product_id, true);
	}
	/*
	method that checks the status of a product and changes it*/
	public static boolean setUnapproved(int product_id) {
		return changeToApprove(product_id, false);
	}
	
	/*method that changes the status of a product for active or innactive*/
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
	/*
	method that checks the state of a product and changes it*/
	public static boolean setState(int product_id) {
		return changeToState(product_id, true);
	}
	/*
	method that checks the state of a product and changes it*/
	public static boolean setNotState(int product_id) {
		return changeToState(product_id, false);
	}
	
	/*method that modifies a product*/
	public static boolean updateProduct(Product product){
		boolean success = false;

		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			session.beginTransaction();
			session.saveOrUpdate(product);
			session.getTransaction().commit();

			success = true;
			session.close();
		} catch (Exception e) {
			logger.error("'ProductDaoImpl.updateProduct' method has raised an exception: " + e.getMessage());
		}

		return success;
	}
	
/*method that create a product*/
	 public static boolean createProduct(String shortname,int type_id, String fulldescription, String company, Date year, double reposition_value) {

		boolean success = true;	
		
		Type type= TypeDaoImpl.getTypeId(type_id);
		

		Product product= new Product();
		product.setShortname(shortname);
		product.setType_id(type);
		product.setFulldescription(fulldescription);
		//product.setImg(img);
		product.setCompany(company);
		product.setYear(year);
		product.setReposition_value(reposition_value);
		product.setActive(true);
		product.setState(true);

		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(product);
			session.getTransaction().commit();
			logger.info("UserDAOImpl.createUser :  "+product);
		} catch (Exception e) {
			logger.error("UserDaoImpl.create has raised an exception: "); // +e.getMessage());
			e.printStackTrace();
			success = false;
		}

		return success;
	}
	
	 

	
}
