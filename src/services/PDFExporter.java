package services;

import java.awt.Image;
import java.io.InputStream;

import javax.swing.text.Document;

public class PDFExporter {

	public void exportUser() {
		
		try (
			pdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
			Document doc = new Document(pdfDoc, PageSize.LETTER.rotate());
		){
			InputStream is = getClass().getResourceAsStream("C:\\Users\\beto_\\OneDrive\\Documentos\\UABCS IDS\\SEMESTRE 4\\PROGRAMACIÓN IV\\proyecto\\src\\img\\logo_uabcs.png");
			
			if(is != null) {
				ImageData data = ImageDataFactory.create(is.readAllBytes);
				Image img = new Image(data).scaleAbsolute();
				
				float heightPage = PageSize.LETTER.rotate().getHeight();
				float margin = 40;
				
				img.setFixedPosition(margin, heightPage - margin - 50);
			}
		}
	}
	
}
