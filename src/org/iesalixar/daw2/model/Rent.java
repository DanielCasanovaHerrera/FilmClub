package org.iesalixar.daw2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Rent")
@Table(name = "rent")
public class Rent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8646802410967540331L;
	
	@Id
	@Column(name= "rent_id")
	private int rent_id;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User user_id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name= "product_id")
	private Product product_id;
	
	@Column(name= "rent_dateOut")
	private Date rent_dateOut;
	
	@Column(name= "rent_dateIn")
	private Date rent_dateIn;
	
	public Rent() {
		
	}

	public Rent(int rent_id, User user_id, Product product_id, Date rent_dateOut, Date rent_dateIn) {
		super();
		this.rent_id = rent_id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.rent_dateOut = rent_dateOut;
		this.rent_dateIn = rent_dateIn;
	}

	public int getRent_id() {
		return rent_id;
	}

	public void setRent_id(int rent_id) {
		this.rent_id = rent_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Product getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}

	public Date getRent_dateOut() {
		return rent_dateOut;
	}

	public void setRent_dateOut(Date rent_dateOut) {
		this.rent_dateOut = rent_dateOut;
	}

	public Date getRent_dateIn() {
		return rent_dateIn;
	}

	public void setRent_dateIn(Date rent_dateIn) {
		this.rent_dateIn = rent_dateIn;
	}
	
	

}
