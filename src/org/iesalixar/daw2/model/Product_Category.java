package org.iesalixar.daw2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Product_Category")
@Table(name = "product_category")
public class Product_Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1060714584553799465L;
	
	@Id
	@Column(name= "product_category_id")
	private int product_category_id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name= "category_id")
	private Category category_id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name= "product_id")
	private Product product_id;

	public Product_Category() {
		
	}

	public Product_Category(int product_category_id, Category category_id, Product product_id) {
		super();
		this.product_category_id = product_category_id;
		this.category_id = category_id;
		this.product_id = product_id;
	}

	public int getProduct_category_id() {
		return product_category_id;
	}

	public void setProduct_category_id(int product_category_id) {
		this.product_category_id = product_category_id;
	}

	public Category getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}

	public Product getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}
	
	
	
	
	
	

}
