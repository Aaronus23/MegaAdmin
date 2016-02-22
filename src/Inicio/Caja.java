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
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.TableRowSorter;
import notas.NotaCaja;
import static notas.NotaCaja.RESULT;
import notas.dat;
/**
 *
 * @author Jesús Ernesto
 */
public class Caja extends javax.swing.JFrame {
   ArrayList filtros,datos;
   Vector cols;
   TableRowSorter trsfiltro;
   DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
   dat dato;
   NotaCaja nota;
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
        trsfiltro = new TableRowSorter(TablaCaja.getModel());
    }
    private void getTot(){
        BigDecimal tot=BigDecimal.ZERO;
        BigDecimal cant;
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
        Filtros = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCaja = new javax.swing.JTable();
        Filtro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Total = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CAJA");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setText("De:");

        FechaInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(""))));
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

        FechaFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(""))));

        jLabel3.setText("Ver Total:");

        PDF.setText("Generar Nuevo PDF");
        PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PDFActionPerformed(evt);
            }
        });

        Filtros.setText("Filtrar");
        Filtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrosActionPerformed(evt);
            }
        });

        try{
            TablaCaja.setModel(Conector.getInstance().buildTableModel("SELECT fecha,concepto,monto FROM caja",cols)
            );
        } catch(SQLException ex){
        }
        TablaCaja.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaCaja);

        Filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroActionPerformed(evt);
            }
        });
        Filtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FiltroKeyTyped(evt);
            }
        });

        jLabel4.setText("Filtrar Datos:");

        jLabel5.setText("$ M.N.");

        Total.setEditable(false);
        Total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Filtros, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5))
                                .addComponent(Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(FechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Filtros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Filtro, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
                    .addComponent(PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void FiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroKeyTyped
        Filtro.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                repaint();
                filtros.add(RowFilter.regexFilter(Filtro.getText(),1));
                trsfiltro.setRowFilter(RowFilter.andFilter(filtros));
            }
        });
        TablaCaja.setRowSorter(trsfiltro);
        getTot();
    }//GEN-LAST:event_FiltroKeyTyped

    private void FechaInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FechaInicioKeyTyped
        
    }//GEN-LAST:event_FechaInicioKeyTyped

    private void FechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaInicioActionPerformed

    private void FiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrosActionPerformed
        filtros = new ArrayList<RowFilter<Object,Object>>();
        ArrayList<RowFilter<Object,Object>> fechStart = new ArrayList<RowFilter<Object,Object>>();
        ArrayList<RowFilter<Object,Object>> fechEnd = new ArrayList<RowFilter<Object,Object>>();
        Date ini = null,fin=null;
        trsfiltro = new TableRowSorter(TablaCaja.getModel());
      try {
           ini=format.parse(FechaInicio.getText());
           fin=format.parse(FechaFinal.getText());
       } catch (ParseException ex) {
           JOptionPane.showMessageDialog(null,ex.toString());
           Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        RowFilter fechAF= RowFilter.dateFilter(ComparisonType.AFTER,ini);
        RowFilter fechEQ= RowFilter.dateFilter(ComparisonType.EQUAL,ini);
        fechStart.add(fechAF);
        fechStart.add(fechEQ);
        fechAF= RowFilter.dateFilter(ComparisonType.AFTER,fin);
        fechEQ= RowFilter.dateFilter(ComparisonType.EQUAL,fin);
        fechEnd.add(fechEQ);
        fechEnd.add(fechAF);
        filtros.add(RowFilter.orFilter(fechStart));
        filtros.add(RowFilter.orFilter(fechEnd));
       trsfiltro.setRowFilter(RowFilter.andFilter(filtros));
       TablaCaja.setRowSorter(trsfiltro);
       getTot();
    }//GEN-LAST:event_FiltrosActionPerformed

    private void PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PDFActionPerformed
        int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente generar un PDF?","Generar PDF",JOptionPane.WARNING_MESSAGE);
        if(opc==JOptionPane.YES_OPTION){
            datos=new ArrayList<dat>();
            for(int i=0; i<TablaCaja.getRowCount(); i++){
                    dato=new dat();
                    dato.fecha= (Date)TablaCaja.getValueAt(i,0);
                    dato.concepto=(String) TablaCaja.getValueAt(i,1);
                    dato.monto=TablaCaja.getValueAt(i,2).toString();
                    datos.add(dato);
            }
            try {
                new NotaCaja().createPdf(RESULT,datos);
                JOptionPane.showMessageDialog(null, "¡PDF generado con éxito!");
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
    }//GEN-LAST:event_PDFActionPerformed

    private void FiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiltroActionPerformed

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
    public javax.swing.JFormattedTextField FechaFinal;
    public javax.swing.JFormattedTextField FechaInicio;
    public javax.swing.JTextField Filtro;
    public javax.swing.JButton Filtros;
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
