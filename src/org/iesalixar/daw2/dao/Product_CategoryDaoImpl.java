package org.iesalixar.daw2.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.daw2.helper.HibernateUtil;
import org.iesalixar.daw2.model.Product_Category;

public class Product_CategoryDaoImpl {
	
	final static Logger logger = Logger.getLogger(Product_CategoryDaoImpl.class);
	
	public static List<Product_Category> getCategory(int product_id) {
		
		String sql="SELECT pc From Product_Category As pc WHERE pc.product_id='"+product_id+"'";
		
		
		List<Product_Category> categories= new ArrayList<Product_Category>();
		
		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			categories = session.createQuery(sql, Product_Category.class).list();

			logger.info("Product_CategoryDaoImpl.getCategory"+categories);
		} catch (Exception e) {
			logger.error("Product_CategoryDaoImpl.getCategory has raised an exception: " + e.getMessage());
		}
		
		
		return categories;
		
	}

}
