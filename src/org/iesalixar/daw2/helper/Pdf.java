package org.iesalixar.daw2.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;

public class Pdf {
	
	private static final PDFont FONT = PDType1Font.TIMES_ITALIC;
    private static final float FONT_SIZE = 12;
    private static final float LEADING = -1.5f * FONT_SIZE;
	
	public static ByteArrayOutputStream createPDF( String fulldescription, String userName, String rent, String img, String productname) throws IOException {

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try (final PDDocument doc = new PDDocument()){

            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            PDRectangle mediaBox = page.getMediaBox();
            float marginY = 80;
            float marginX = 60;
            float width = mediaBox.getWidth() - 2 * marginX;
            float startX = mediaBox.getLowerLeftX() + marginX;
            float startY = mediaBox.getUpperRightY() - marginY;
            
            contentStream.beginText();
            addParagraph(contentStream, width, startX, startY, userName, true);
            addParagraph(contentStream, width, 0, -FONT_SIZE, productname);
            addParagraph(contentStream, width, 0, -FONT_SIZE, fulldescription, false);
            addParagraph(contentStream, width, 0, -FONT_SIZE, img, false);
            addParagraph(contentStream, width, 0, -FONT_SIZE, rent, false);
            contentStream.endText();

            contentStream.close();

            doc.save(output);      
        } catch (IOException e){
            System.err.println("Exception while trying to create pdf document - " + e);
        }
		return output;
	}
	
	private static void addParagraph(PDPageContentStream contentStream, float width, float sx,
			float sy, String text) throws IOException {
		text = text.replace("\n", "").replace("\r", "");
		text= remove(text);
		addParagraph(contentStream, width, sx, sy, text, false);
	}

	private static void addParagraph(PDPageContentStream contentStream, float width, float sx,
			float sy, String text, boolean justify) throws IOException {
		text = text.replace("\n", "").replace("\r", "");
		text= remove(text);
		List<String> lines = parseLines(text, width);
		contentStream.setFont(FONT, FONT_SIZE);
		contentStream.newLineAtOffset(sx, sy);
		for (String line: lines) {
			float charSpacing = 0;
			if (justify){
				if (line.length() > 1) {
					float size = FONT_SIZE * FONT.getStringWidth(line) / 1000;
					float free = width - size;
					if (free > 0 && !lines.get(lines.size() - 1).equals(line)) {
						charSpacing = free / (line.length() - 1);
					}
				}
			}
			contentStream.setCharacterSpacing(charSpacing);
			
			contentStream.showText(line);
			contentStream.newLineAtOffset(0, LEADING);
		}
	}

	private static List<String> parseLines(String text, float width) throws IOException {
		List<String> lines = new ArrayList<String>();
		text = text.replace("\n", "").replace("\r", "");
		text= remove(text);
		int lastSpace = -1;
		while (text.length() > 0) {
			int spaceIndex = text.indexOf(' ', lastSpace + 1);
			if (spaceIndex < 0)
				spaceIndex = text.length();
			text = text.replace("\n", "").replace("\r", "");
			text= remove(text);
			String subString = text.substring(0, spaceIndex);
			float size = FONT_SIZE * FONT.getStringWidth(subString) / 1000;
			if (size > width) {
				if (lastSpace < 0){
					lastSpace = spaceIndex;
				}
				subString = text.substring(0, lastSpace);
				lines.add(subString);
				text = text.substring(lastSpace).trim();
				text = text.replace("\n", "").replace("\r", "");
				text= remove(text);
				lastSpace = -1;
			} else if (spaceIndex == text.length()) {
				lines.add(text);
				text = "";
				text= remove(text);
			} else {
				lastSpace = spaceIndex;
			}
		}
		return lines;
	}
	
	public static String remove(String test) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            if (WinAnsiEncoding.INSTANCE.contains(test.charAt(i))) {
                b.append(test.charAt(i));
            }
        }
        return b.toString();
    }

}
