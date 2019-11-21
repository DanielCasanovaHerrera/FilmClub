package org.iesalixar.daw2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Type")
@Table(name = "type")
public class Type implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8954319248680947297L;
	
	@Id
	@Column(name= "type_id")
	private int type_id;
	
	@Column(name= "typename")
	private String typename;
	
	public Type() {
		
	}

	public Type(int type_id, String typename) {
		super();
		this.type_id = type_id;
		this.typename = typename;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	
	
	
	

}
