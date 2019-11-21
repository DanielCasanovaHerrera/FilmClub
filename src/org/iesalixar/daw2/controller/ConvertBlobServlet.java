package org.iesalixar.daw2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.daw2.dao.ProductDaoImpl;

/**
 * Servlet implementation class ConvertBlobServlet
 */
@WebServlet("/ConvertBlobServlet")
public class ConvertBlobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDaoImpl productDaoImpl;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvertBlobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int product_id=Integer.parseInt(request.getParameter("product_id"));
		byte [] image= ProductDaoImpl.loadImage(product_id);
		response.setContentType("image/jpeg");
		ServletOutputStream ouputStream=response.getOutputStream();
		ouputStream.write(image);
		ouputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int product_id=Integer.parseInt(request.getParameter("product_id"));
		byte [] image= productDaoImpl.loadImage(product_id);
		response.setContentType("image/jpeg");
		ServletOutputStream ouputStream=response.getOutputStream();
		ouputStream.write(image);
		ouputStream.close();
	}

}
