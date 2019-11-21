package org.iesalixar.daw2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iesalixar.daw2.dao.UserDaoImpl;

/**
 * Servlet implementation class ResgistrationServlet
 */
@WebServlet("/ResgistrationServlet")
public class ResgistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao = new UserDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResgistrationServlet() {
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
		doGet(request, response);

		String 	urlRegister = "/register.jsp"; 
		String 	urlIndex = "index.jsp";

		String username=request.getParameter("username");

		String password=request.getParameter("password");

		String user_fullname=request.getParameter("user_fullname");

		String address=request.getParameter("address");

		String email=request.getParameter("email");
		
		String err = "";
		
		if (username.equals("") || password.equals("") || user_fullname.equals("") || address.equals("") || email.equals("")) {
			err += "Must enter full information!";
		} else {
			try {
				if (!userDao.createUser(username,password,user_fullname,address,email)) 
					err += "Registration process went wrong!";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}
		
		try {
			if (err.length() == 0) {
				
	            response.sendRedirect(urlIndex);
			} else {
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher(urlRegister);
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(urlRegister);
		}
	
		
	}

}
