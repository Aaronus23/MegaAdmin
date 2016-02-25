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
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCellEvent;
import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;



public class NotaCaja{
    
    
        PdfPTable table = new PdfPTable(5);
        PdfPCell cell=new PdfPCell();      
        
        Date fecha = new Date();
        static ArrayList <dat> ggg;
       
    public NotaCaja() {
        
    }
    
        private static NotaCaja instancia=null;
    
    public static NotaCaja getInstance(){
        if(instancia==null){
            instancia=new NotaCaja();
        }
        return instancia;
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
        = "Caja.pdf";

    /*public static void main(String[] args) throws DocumentException, IOException {
    	new NotaCaja().createPdf(RESULT,ggg);
    }*/

    public void createPdf(String filename, ArrayList datos)
	throws DocumentException, IOException {
        
        dat contenedor=new dat();
        BigDecimal total=BigDecimal.ZERO;
        
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(curDate);
        
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        Font fuente_titulo=new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,GrayColor.RED);
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();

        String RESOURCE = "logo.jpg";
   
        Image img = null;
        try {
            img = Image.getInstance(RESOURCE);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(NotaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }        

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
        
        cell=new PdfPCell(new Phrase(""));
        cell.setColspan(3);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("          Fecha"));
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("      "+DateToStr));
        cell.setBorder(2);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("                                        CAJA ",fuente_titulo));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);        
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
                
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(5);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" Fecha"));
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("                      Concepto"));
        cell.setColspan(3);
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("    Monto"));
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
        table.addCell(cell);
        
        for(int i=0;i<datos.size();i++) {
            contenedor=(dat)datos.get(i);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = formato.format(contenedor.fecha);
            cell=new PdfPCell(new Phrase(" "+fecha));
            cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
            table.addCell(cell);
        
            cell=new PdfPCell(new Phrase(" "+contenedor.concepto));
            cell.setColspan(3);
            cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
            table.addCell(cell);
            total=total.add(new BigDecimal(contenedor.monto));
            cell=new PdfPCell(new Phrase(" "+contenedor.monto));
            cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
            table.addCell(cell);
        }
        
        cell=new PdfPCell(new Phrase(" "));
        cell.setColspan(3);
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase("     Total "));
        cell.setBorder(0);
        table.addCell(cell);
        
        cell=new PdfPCell(new Phrase(" "+total));
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT |Rectangle.RIGHT);
        table.addCell(cell);
        
        document.add(table);
        
        document.close();
        abrirArchivo();
    }
    
    void abrirArchivo(){
        try {
            File objetofile = new File ("Caja.pdf");
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
             System.out.println(ex);
        }
    }
}

