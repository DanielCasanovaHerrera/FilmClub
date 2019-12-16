package org.iesalixar.daw2.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.daw2.dao.HystoricDaoImpl;
import org.iesalixar.daw2.dao.ProductDaoImpl;
import org.iesalixar.daw2.dao.RentDaoImpl;
import org.iesalixar.daw2.dao.UserDaoImpl;
import org.iesalixar.daw2.model.Product;
import org.iesalixar.daw2.model.User;

/**
 * Servlet implementation class NewRentServlet
 */
@WebServlet("/NewRentServlet")
public class NewRentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String urlIndex = "/FilmClub/index.jsp";
		Calendar c = Calendar.getInstance();
		java.util.Date date=new java.util.Date();
		
		Date rentOut = new Date(date.getTime());
		
		c.setTime(date);
		c.add(Calendar.DATE, 25);

		
		Date rentIn = new Date(c.getTimeInMillis());
		//collect the data passed by parameter
		Product product_id= ProductDaoImpl.getProductId(Integer.valueOf(request.getParameter("product_id")));
		User username= UserDaoImpl.getUserIDForUsername(request.getParameter("username"));
		
		
		
		//the data is checked
		// if it's true change te estate for the product and create a hystoric
		if (RentDaoImpl.createOrChangeRent(product_id,username , rentOut, rentIn)) {
			ProductDaoImpl.setNotState(Integer.valueOf(request.getParameter("product_id")));
			HystoricDaoImpl.createHystoric(product_id, username);
		}
		
		response.sendRedirect(urlIndex);
	}

}
