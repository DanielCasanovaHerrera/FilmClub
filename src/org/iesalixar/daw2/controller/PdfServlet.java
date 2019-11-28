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
		PDDocument doc =new PDDocument();
		try {
	        //ByteArrayOutputStream output = new ByteArrayOutputStream();
	        String username = request.getParameter("username");
	        String productname = request.getParameter("productname");
	        String fulldescription = request.getParameter("fulldescription");
	        //String img= request.getParameter("img");
	        
	       //output = PDFBox.createPDF(username, productname, fulldescription);
	        String pdfName="Pdf"+productname+".pdf";
	        
	        PDPage pag1= new PDPage();
	        doc.addPage(pag1);
	        
	        PDPageContentStream contentStream = new PDPageContentStream(doc, pag1);
	        
	        contentStream.beginText();
	        contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
	        
	        //Setting the leading
	        contentStream.setLeading(14.5f);

	        //Setting the position for the line
	        contentStream.newLineAtOffset(25, 725);
	        
	        
	        contentStream.showText(username);
	        contentStream.newLine();
	        contentStream.showText(productname);
	        contentStream.newLine();
	        contentStream.showText(fulldescription);
	        contentStream.newLine();
	        //contentStream.showText(img);
	        contentStream.endText();
	        contentStream.close();
	        doc.save(pdfName);

	       // response.addHeader("Content-Type", "application/force-download"); 
	       // response.addHeader("Content-Disposition", "attachment; filename=\"" + productname + ".pdf\"");
	        //response.getOutputStream().write(output.toByteArray());
	        
	        doc.close();
	        
	        String urlIndex = "/FilmClub/index.jsp";
	        response.sendRedirect(urlIndex);
	    } catch (Exception ex) {            
	        ex.printStackTrace();
	    }finally{
	    	doc.close();
	    }
	}

}
