package org.iesalixar.daw2.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Product")
@Table(name = "product")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3792145471324259831L;
	
	@Id
	@Column(name= "product_id")
	private int product_id;
	
	@OneToOne
	@JoinColumn(name= "type_id")
	private Type type_id;
	
	@Column(name= "shortname")
	private String shortname;
	
	@Column(name= "fulldescription")
	private String fulldescription;
	
	@Column(name= "img")
	private byte[] img;
	
	@Column(name= "company")
	private String company;
	
	@Column(name= "year")
	private Date year;
	
	@Column(name= "reposition_value")
	private double reposition_value;
	
	@Column(name= "active")
	private boolean active;
	
	@Column(name= "state")
	private boolean state;
	
	public Product() {
		
	}

	public Product(int product_id, Type type_id, String shortname, String fulldescription, byte[] img, String company,
			Date year, double reposition_value, boolean active, boolean state) {
		super();
		this.product_id = product_id;
		this.type_id = type_id;
		this.shortname = shortname;
		this.fulldescription = fulldescription;
		this.img = img;
		this.company = company;
		this.year = year;
		this.reposition_value = reposition_value;
		this.active = active;
		this.state = state;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Type getType_id() {
		return type_id;
	}

	public void setType_id(Type type_id) {
		this.type_id = type_id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getFulldescription() {
		return fulldescription;
	}

	public void setFulldescription(String fulldescription) {
		this.fulldescription = fulldescription;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public double getReposition_value() {
		return reposition_value;
	}

	public void setReposition_value(double reposition_value) {
		this.reposition_value = reposition_value;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	

}
