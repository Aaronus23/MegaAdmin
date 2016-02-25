/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import Conector.Conector;
import com.itextpdf.text.DocumentException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.*;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.TableRowSorter;
import notas.NotaCaja;
import static notas.NotaCaja.RESULT;
import java.util.Iterator;
import javax.swing.table.TableModel;
import notas.dat;
/**
 *
 * @author Jesús Ernesto
**/

public class Caja extends javax.swing.JFrame {
   ArrayList filtros,datos;
   Vector cols;
   ArrayList f1,f2,finalf;
   TableRowSorter trsfiltro,filtroAnd,filtroOr1,filtroOr2;
   dat dato;
   BigDecimal tot=BigDecimal.ZERO;
   private static Caja instancia=null;
    public static Caja getInstance(){
        if(instancia==null){
            instancia=new Caja();
        }
        return instancia;
    }
    /**
     * Creates new form NotaCaja
     */
    public Caja() {
        Conector.getInstance();
        cols=new Vector<>();
        cols.add("Fecha");
        cols.add("Concepto");
        cols.add("Monto");
        initComponents();
        getTot();
    }
    public void filtroSolo() {
        trsfiltro.setRowFilter(RowFilter.regexFilter(Filtro.getText(),1));
        getTot();
    }
    public void filtroMixto(){
        String x1=FechaInicio.getText();
        String x2=FechaFinal.getText();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha1 = new Date();
        Date fecha2 = new Date();
        try {
            fecha1 = format.parse(x1);
            fecha2 = format.parse(x2);
            
        } catch (ParseException ex) {
            ex.printStackTrace();
            }
        f1=new ArrayList();f2=new ArrayList();finalf=new ArrayList();
        f1.add(RowFilter.dateFilter(ComparisonType.EQUAL, fecha1,0));
        f1.add(RowFilter.dateFilter(ComparisonType.AFTER, fecha1,0));
        f2.add(RowFilter.dateFilter(ComparisonType.EQUAL, fecha2,0));
        f2.add(RowFilter.dateFilter(ComparisonType.BEFORE, fecha2,0));
        filtroOr1.setRowFilter(RowFilter.orFilter(f1));
        filtroOr2.setRowFilter(RowFilter.orFilter(f2));
        finalf.add(filtroOr1);
        finalf.add(filtroOr2);
        //finalf.add(RowFilter.regexFilter(Filtro.getText(),1));
        filtroAnd.setRowFilter(RowFilter.andFilter(finalf));
        getTot();
    }

    private void getTot(){
        BigDecimal cant;
        tot=BigDecimal.ZERO;
        for(int i=0; i<TablaCaja.getRowCount(); i++){
            cant = new BigDecimal(TablaCaja.getValueAt(i, 2)+"");
            tot=tot.add(cant);
         }
        Total.setText(tot.toString());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        FechaInicio = new javax.swing.JFormattedTextField(new Date());
        jLabel2 = new javax.swing.JLabel();
        FechaFinal = new javax.swing.JFormattedTextField(new Date());
        jLabel3 = new javax.swing.JLabel();
        PDF = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCaja = new javax.swing.JTable();
        Filtro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Total = new javax.swing.JFormattedTextField();
        ChekFechaCaja = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CAJA");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("De:");

        FechaInicio.setEditable(false);
        FechaInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        FechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaInicioActionPerformed(evt);
            }
        });
        FechaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FechaInicioKeyTyped(evt);
            }
        });

        jLabel2.setText("Hasta:");

        FechaFinal.setEditable(false);
        FechaFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel3.setText("Ver Total:");

        PDF.setText("Generar Nuevo PDF");
        PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PDFActionPerformed(evt);
            }
        });

        try{
            TablaCaja.setModel(Conector.getInstance().buildTableModel("SELECT fecha ,CONCAT('Abono al pedido: ',idPedido) as concepto,monto FROM abono UNION SELECT fecha,concepto,monto FROM caja ORDER BY fecha",cols));
        } catch(SQLException ex){
        }
        TablaCaja.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaCaja);

        Filtro.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                FiltroComponentAdded(evt);
            }
        });
        Filtro.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                FiltroInputMethodTextChanged(evt);
            }
        });
        Filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroActionPerformed(evt);
            }
        });
        Filtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FiltroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FiltroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FiltroKeyTyped(evt);
            }
        });

        jLabel4.setText("Filtrar Datos:");

        jLabel5.setText("$ M.N.");

        Total.setEditable(false);
        Total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        ChekFechaCaja.setText("Filtrar por Fecha");
        ChekFechaCaja.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ChekFechaCajaStateChanged(evt);
            }
        });
        ChekFechaCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChekFechaCajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ChekFechaCaja)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(FechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChekFechaCaja))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(PDF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void FiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroKeyTyped
        if(ChekFechaCaja.isSelected()==true)
        {
            /*Filtro.addKeyListener(new KeyAdapter() {
                public void keyReleased(final KeyEvent e) {
                    String cadena = (Filtro.getText());
                    Filtro.setText(cadena);
                    repaint();
                    filtroMixto();
                }
            }); 
            filtroAnd = new TableRowSorter(TablaCaja.getModel());
            TablaCaja.setRowSorter(filtroAnd); 
            getTot();*/
        }
        else{
            Filtro.addKeyListener(new KeyAdapter() {
                public void keyReleased(final KeyEvent e) {
                    String cadena = (Filtro.getText());
                    Filtro.setText(cadena);
                    repaint();
                    filtroSolo();
                }
            });
            trsfiltro = new TableRowSorter(TablaCaja.getModel());
            TablaCaja.setRowSorter(trsfiltro);
            getTot();
        }
    }//GEN-LAST:event_FiltroKeyTyped

    private void FechaInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FechaInicioKeyTyped
        
    }//GEN-LAST:event_FechaInicioKeyTyped

    private void FechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaInicioActionPerformed

    private void PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PDFActionPerformed
        int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente generar un PDF?","Generar PDF",JOptionPane.INFORMATION_MESSAGE);
        if(opc==JOptionPane.YES_OPTION){
            datos=new ArrayList<>();
            for(int i=0; i<TablaCaja.getRowCount(); i++){
                    dato=new dat();
                    dato.fecha= (Date)TablaCaja.getValueAt(i,0);
                    dato.concepto=(String) TablaCaja.getValueAt(i,1);
                    dato.monto=TablaCaja.getValueAt(i,2).toString();
                    datos.add(dato);
            }
            try {
                NotaCaja.getInstance().createPdf(RESULT,datos);
                JOptionPane.showMessageDialog(null, "¡PDF generado con éxito!");
            } catch (DocumentException | IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al generar PDF",null, JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
            Caja.instancia=null;
        }
    }//GEN-LAST:event_PDFActionPerformed

    private void FiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiltroActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        dispose();
        Caja.instancia=null;
    }//GEN-LAST:event_formWindowClosed

    private void ChekFechaCajaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ChekFechaCajaStateChanged
        if(ChekFechaCaja.isSelected()==true)
        {
            FechaInicio.setEditable(true);
            FechaFinal.setEditable(true);
            /*Filtro.addKeyListener(new KeyAdapter() {
                public void keyReleased(final KeyEvent e) {
                    String cadena = (Filtro.getText());
                    Filtro.setText(cadena);
                    repaint();
                    filtroMixto();
                }
            });  
            filtroAnd = new TableRowSorter(TablaCaja.getModel());
            TablaCaja.setRowSorter(filtroAnd); 
            getTot();*/
        }
        else{
            FechaInicio.setEditable(false);
            FechaFinal.setEditable(false);
            /*Filtro.addKeyListener(new KeyAdapter() {
                public void keyReleased(final KeyEvent e) {
                    String cadena = (Filtro.getText());
                    Filtro.setText(cadena);
                    repaint();
                    filtroSolo();
                }
            });
            trsfiltro = new TableRowSorter(TablaCaja.getModel());
            TablaCaja.setRowSorter(trsfiltro);
            getTot();*/
        }
    }//GEN-LAST:event_ChekFechaCajaStateChanged

    private void ChekFechaCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChekFechaCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChekFechaCajaActionPerformed

    private void FiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroKeyPressed
        
    }//GEN-LAST:event_FiltroKeyPressed

    private void FiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroKeyReleased
        
    }//GEN-LAST:event_FiltroKeyReleased

    private void FiltroComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_FiltroComponentAdded
        
    }//GEN-LAST:event_FiltroComponentAdded

    private void FiltroInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_FiltroInputMethodTextChanged
        
    }//GEN-LAST:event_FiltroInputMethodTextChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       //</editor-fold>
       
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ChekFechaCaja;
    public javax.swing.JFormattedTextField FechaFinal;
    public javax.swing.JFormattedTextField FechaInicio;
    public javax.swing.JTextField Filtro;
    public javax.swing.JButton PDF;
    public javax.swing.JTable TablaCaja;
    public javax.swing.JFormattedTextField Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
