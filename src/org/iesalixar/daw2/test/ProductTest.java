package org.iesalixar.daw2.test;

import static org.junit.Assert.*;

import org.iesalixar.daw2.dao.ProductDaoImpl;
import org.iesalixar.daw2.model.Product;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	
	Product product;
	ProductDaoImpl productImpl;
	@Before
	public void before() {
		product= new Product();
		product.setShortname("Producto1");
	}
	
	
	@Test
	public void test() {	
	 assertEquals("Producto1", product.getShortname());
	}
	
	@Test
	public void test2() {	
		
		 assertEquals(2, productImpl.getProducts(-1, true).size());
	}

}
