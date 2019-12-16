package org.iesalixar.daw2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iesalixar.daw2.dao.UserDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDaoImpl userDao = new UserDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		//collect the data passed by parameter
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String urlLogin = "/FilmClub/login.jsp", urlIndex = "/FilmClub/index.jsp";
		String err = "";
		//it is checked if the parameters
		if (username.equals("") || password.equals("") ) {
			err += "Must enter full information!";
		} else {
			try {
				if (userDao.login(username, password) == false) 
					err += "username or password incorrect!";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
		if (err.length() > 0) {
			request.setAttribute("err", err);
		}
		
		try {
			if (err.length() == 0) {
				//and create the cookie with the dates for the user
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("user_id", userDao.getUserID(username));
				session.setAttribute("role", userDao.getUserRole(username));

				Cookie loginCookie = new Cookie("username", username);
				loginCookie.setMaxAge(30 * 5); // setting cookie to expiry in 30 mins

				response.addCookie(loginCookie);
				response.sendRedirect(urlIndex);
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlIndex);
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(urlIndex);
		}
		
		

	}

}
