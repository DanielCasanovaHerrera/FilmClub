package org.iesalixar.daw2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.daw2.dao.ProductDaoImpl;

/**
 * Servlet implementation class ChangeStatusServlet
 */
@WebServlet("/ChangeStatusServlet")
public class ChangeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStatusServlet() {
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
		int product_id = Integer.valueOf(request.getParameter("product_id2"));
		
		boolean status;
		if(request.getParameter("product_id").split("&")[1].equals("approve"))
			status = ProductDaoImpl.setApproved(product_id);
		else
			status = ProductDaoImpl.setUnapproved(product_id);

		String err = "";
		
		if (product_id < 0 || !status) {
			err += "Error!";
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		RequestDispatcher rd = getServletContext()
				.getRequestDispatcher("/administrator.jsp");
		rd.forward(request, response);
	}

}
