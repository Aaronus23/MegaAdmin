package notas;

import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfContentByte;
import java.util.logging.Level;
import com.itextpdf.text.Font.FontFamily;
import java.util.logging.Logger;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.GrayColor;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotaProduccion {
    
        String nombre="";
        String Telefono="";
        String Concepto="";
        String folio="";
        
    void setear(String folio,String nombre, String telefono, String Concepto) {
        this.nombre=nombre;
        this.Telefono=telefono;
        this.Concepto=Concepto;
        this.folio=folio;
    }
        
    class ImageBackgroundEvent implements PdfPCellEvent {
 
        protected Image image;
 
        public ImageBackgroundEvent(Image image) {
            this.image = image;
        }
 
        public void cellLayout(PdfPCell cell, Rectangle position,
                PdfContentByte[] canvases) {
            try {
                PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
                image.scaleAbsolute(position);
                image.setAbsolutePosition(position.getLeft(), position.getBottom());
                cb.addImage(image);
            } catch (DocumentException e) {
                throw new ExceptionConverter(e);
            }
        }
 
    }
    
    public static final String RESULT
        = "NotaProduccion.pdf";

    public static void main(String[] args)
    	throws DocumentException, IOException {
    	new NotaProduccion().createPdf(RESULT);
    }

    public void createPdf(String filename)
	throws DocumentException, IOException {
        
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(curDate);
        
        Font font = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        Font fuente_titulo=new Font(FontFamily.HELVETICA,16,Font.BOLD,GrayColor.RED);
        Font fuente_negritas=new Font(FontFamily.HELVETICA,12,Font.BOLD);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        
        PdfPTable table = new PdfPTable(5);
        String RESOURCE = "logo.jpg";
        PdfPCell cell;
        Image img = null;
        try {
            img = Image.getInstance(RESOURCE);
        } catch (BadElementException ex) {
            Logger.getLogger(NotaProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotaProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        cell=new PdfPCell();
        cell.setCellEvent(new ImageBackgroundEvent(img));
        cell.setColspan(2);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("      Av. Independencia No. 1864-A \n      Fracc. Jardines de la Concepción\n      Producción: \n      Municipio de Pabellón de Arteaga No. 122\n      Parque Industrial del Valle de Aguascalientes\n      San Francisco de los Romo, Ags ",font));
        cell.setColspan(3);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("                      ORDEN DE PRODUCCIÓN ",fuente_titulo));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
                
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("     Fecha   "+DateToStr));
        cell.setColspan(2);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("        FOLIO "));
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "+folio));
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
       
        cell=new PdfPCell(new Phrase("Nombre: ",fuente_negritas));
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "+nombre));
        cell.setColspan(4);
        cell.setBorder(2);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("Teléfono: ",fuente_negritas));
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "+Telefono));
        cell.setColspan(4);
        cell.setBorder(2);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" CONCEPTO",fuente_negritas));
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(4);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "+Concepto));
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
        cell.setColspan(5);
        table.addCell(cell);
        
        document.add(table);
        
        document.close();
    }    
    
    void abrirArchivo(){
        try {
            File objetofile = new File ("NotaProduccion.pdf");
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
             System.out.println(ex);
        }
    }
}
