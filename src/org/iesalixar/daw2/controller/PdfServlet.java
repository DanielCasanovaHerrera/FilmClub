package org.iesalixar.daw2.controller;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.tools.PDFBox;
import org.iesalixar.daw2.helper.Pdf;


//@WebServlet("/PdfServlet")

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
		String urlIndex="index.jsp";
		try {
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        String rent = request.getParameter("rentId");
	        String fulldescription = request.getParameter("fulldescription");
	        String userName = request.getParameter("username");
	        String img= request.getParameter("img");
	        String productname= request.getParameter("productname");
	      
	        output = Pdf.createPDF(userName,productname,img,fulldescription,rent);

	        response.addHeader("Content-Type", "application/force-download"); 
	        response.addHeader("Content-Disposition", "attachment; filename=\"" +"Equipo: "+ productname + ".pdf\"");
	        response.getOutputStream().write(output.toByteArray());
	        response.sendRedirect(urlIndex);
	    } catch (Exception ex) {            
	        ex.printStackTrace();
	        response.sendRedirect(urlIndex);
	    }
	}

}
