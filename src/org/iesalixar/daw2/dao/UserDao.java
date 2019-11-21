package org.iesalixar.daw2.dao;

public interface UserDao {
	public boolean login(String username, String password);
	public int getUserID(String username);
	public String getUserRole(String username);
	public boolean createUser(String username, String password, String fullname, String address, String email);
}
