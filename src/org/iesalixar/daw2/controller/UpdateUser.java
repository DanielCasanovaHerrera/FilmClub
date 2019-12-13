package org.iesalixar.daw2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iesalixar.daw2.dao.UserDaoImpl;
import org.iesalixar.daw2.model.User;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String urlLogin = "/FilmClub/login.jsp", urlsetings = "/FilmClub/settingsUser.jsp", urlIndex="/FilmClub/index.jsp";
		HttpSession sesion = request.getSession();
		
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		User user = UserDaoImpl.getUserIDForUsername(username);
		
		if (user.getUsername() != username) {
			user.setUsername(username);
		}else {
			user.setUsername(user.getUsername());
		
		}
		if (user.getPassword() != password) {
			user.setPassword(password);
		}else {
			user.setPassword(user.getPassword());
		}
		if (user.getUser_fullname() != fullname) {
			user.setUser_fullname(fullname);
		}else {
			user.setUser_fullname(user.getUser_fullname());
		}
		if (user.getAddress() != address) {
			user.setAddress(address);
		}else {
			user.setAddress(user.getAddress());
		}
		if (user.getEmail() != email) {
			user.setEmail(email);
		}else {
			user.setEmail(user.getEmail());
		}
		
		UserDaoImpl.updateUser(user);
		
		
		response.sendRedirect(urlIndex);
		
		
	}

}
