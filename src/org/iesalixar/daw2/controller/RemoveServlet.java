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
 * Servlet implementation class RemoveServlet
 */
@WebServlet("/RemoveServlet")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveServlet() {
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
		int company_id = Integer.valueOf(request.getParameter("company_id"));
		
		// NewDAOImpl.remove(company_id) lets remove a new given its 'company_id'
		boolean status = ProductDaoImpl.remove(company_id);

		String err = "";
		
		if (company_id < 0 || !status) {
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
