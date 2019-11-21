package org.iesalixar.daw2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Category")
@Table(name = "category")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -366028731459363379L;
	
	@Id
	@Column(name= "category_id")
	private int category_id;
	
	@Column(name= "categoryname")
	private String categoryname;
	
	public Category() {
		
	}

	public Category(int category_id, String categoryname) {
		super();
		this.category_id = category_id;
		this.categoryname = categoryname;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	
	
	

}
