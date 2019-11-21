package org.iesalixar.daw2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.daw2.dao.HystoricDaoImpl;
import org.iesalixar.daw2.dao.ProductDaoImpl;
import org.iesalixar.daw2.dao.RentDaoImpl;
import org.iesalixar.daw2.dao.UserDaoImpl;
import org.iesalixar.daw2.model.User;

/**
 * Servlet implementation class ChangeRentServlet
 */
@WebServlet("/ChangeRentServlet")
public class ChangeRentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeRentServlet() {
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
		String urlIndex = "/FilmClub/index.jsp";
		
		if (RentDaoImpl.removeRent(Integer.valueOf(request.getParameter("rent_id")))) {
			ProductDaoImpl.setState(Integer.valueOf(request.getParameter("product_id")));
			//HystoricDaoImpl.removeHystoric(Integer.valueOf(request.getParameter("user_id")),Integer.valueOf(request.getParameter("product_id")));
		} 
		response.sendRedirect(urlIndex);
	}

}
