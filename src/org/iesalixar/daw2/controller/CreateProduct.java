package org.iesalixar.daw2.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.ArraySuffix;
import org.iesalixar.daw2.dao.ProductDaoImpl;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/CreateProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProduct() {
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
		String 	urlIndex = "index.jsp";
		HttpSession sesion = request.getSession();
		
		String shortname=request.getParameter("shortname");
		int type_id=Integer.valueOf(request.getParameter("type_id"));
		String fulldescription=request.getParameter("fulldescription");
		String company=request.getParameter("company");
		Date year=Date.valueOf(request.getParameter("year"));
		double reposition_value=Double.valueOf(request.getParameter("reposition_value"));
		//Byte img= Byte.valueOf(request.getParameter("img"));
		
		
		 
		
		if (!shortname.equals(" ") && type_id!= 0 && !fulldescription.equals(" ") && !company.equals(" ") && reposition_value!=0 ) {
			ProductDaoImpl.createProduct(shortname,type_id ,fulldescription, company, year, reposition_value);
		}
		
		response.sendRedirect(urlIndex);
	}

}
