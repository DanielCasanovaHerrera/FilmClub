package org.iesalixar.daw2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6091664994040510051L;
	
	@Id
	@Column(name= "user_id")
	private int user_id;
	
	@Column(name= "username")
	private String username;
	
	@Column(name= "password")
	private String password;
	
	@Column(name= "user_fullname")
	private String user_fullname;
	
	@Column(name= "address")
	private String address;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "role")
	private String role;
	

	public User() {
		
	}

	public User(int user_id, String username, String password, String user_fullname, String address, String email,
			String role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.user_fullname = user_fullname;
		this.address = address;
		this.email = email;
		this.role = role;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_fullname() {
		return user_fullname;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
