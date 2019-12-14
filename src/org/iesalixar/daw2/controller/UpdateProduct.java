package org.iesalixar.daw2.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iesalixar.daw2.dao.ProductDaoImpl;
import org.iesalixar.daw2.dao.UserDaoImpl;
import org.iesalixar.daw2.model.Product;
import org.iesalixar.daw2.model.User;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
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
		
		String urlsetings = "/FilmClub/updateProduct.jsp", urlIndex="/FilmClub/index.jsp";
		HttpSession sesion = request.getSession();
		
		int id = Integer.valueOf(request.getParameter("id"));
	
		String fulldescription = request.getParameter("fulldescription");
		String company = request.getParameter("company");
		Double reposition = Double.valueOf(request.getParameter("reposition"));
		
		Product product = ProductDaoImpl.getProductId(id);
	
		
		if (product.getCompany().equals(company)) {
			
			product.setCompany(product.getCompany());
		}else {
			product.setCompany(company);
		}
		if (product.getReposition_value() != reposition) {
			product.setReposition_value(reposition);
		}else {
			product.setReposition_value(product.getReposition_value());
		}
		
		
		ProductDaoImpl.updateProduct(product);
		
		
		response.sendRedirect(urlIndex);
	}

}
