package org.iesalixar.daw2.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.tools.PDFBox;



public class PdfServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6507663067115034626L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response); 		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		try {
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        String username = request.getParameter("username");
	        String productname = request.getParameter("productname");
	        String fulldescription = request.getParameter("fulldescription");
	       //output = PDFBox.createPDF(username, productname, fulldescription);

	        response.addHeader("Content-Type", "application/force-download"); 
	        response.addHeader("Content-Disposition", "attachment; filename=\"" + productname + ".pdf\"");
	        response.getOutputStream().write(output.toByteArray());

	    } catch (Exception ex) {            
	        ex.printStackTrace();
	    }
	}

}
