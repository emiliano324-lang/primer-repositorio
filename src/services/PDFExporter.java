package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import models.User;

public class PDFExporter {

	public void exportUsers(List<User> users, File file) throws IOException {
		
		try (
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
			Document doc = new Document(pdfDoc, PageSize.LETTER.rotate());
		){
			InputStream is = getClass().getResourceAsStream("C:\\Users\\beto_\\OneDrive\\Documentos\\UABCS IDS\\SEMESTRE 4\\PROGRAMACIÓN IV\\proyecto\\src\\img\\logo_uabcs.png");
			
			if(is != null) {
				ImageData data = ImageDataFactory.create(is.readAllBytes());
				Image img = new Image(data).scaleAbsolute(50, 50);
				
				float heightPage = PageSize.LETTER.rotate().getHeight();
				float margin = 40;
				
				img.setFixedPosition(margin, heightPage - margin - 50);
				
				doc.add(img);
			}
		}
		
	}
	
}
