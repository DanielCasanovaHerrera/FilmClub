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

@Entity(name = "Hystoric")
@Table(name = "hystoric")
public class Hystoric implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6614855616310457015L;

	@Id
	@Column(name = "hystoric_id")
	private int hystoric_id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user_id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product_id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "rent_id")
	private Rent rent_id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "reposition_value")
	private Product reposition_value;

	@ManyToOne(optional = false)
	@JoinColumn(name = "rent_dateIn")
	private Rent rent_dateIn;

	public Hystoric() {

	}

	public Hystoric(int hystoric_id, User user_id, Product product_id, Rent rent_id, Product reposition_value,
			Rent rent_dateIn) {
		super();
		this.hystoric_id = hystoric_id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.rent_id = rent_id;
		this.reposition_value = reposition_value;
		this.rent_dateIn = rent_dateIn;
	}

	public int getHystoric_id() {
		return hystoric_id;
	}

	public void setHystoric_id(int hystoric_id) {
		this.hystoric_id = hystoric_id;
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

	public Rent getRent_id() {
		return rent_id;
	}

	public void setRent_id(Rent rent_id) {
		this.rent_id = rent_id;
	}

	public Product getReposition_value() {
		return reposition_value;
	}

	public void setReposition_value(Product reposition_value) {
		this.reposition_value = reposition_value;
	}

	public Rent getRent_dateIn() {
		return rent_dateIn;
	}

	public void setRent_dateIn(Rent rent_dateIn) {
		this.rent_dateIn = rent_dateIn;
	}

}
