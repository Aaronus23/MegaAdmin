/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import Conector.Conector;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import notas.NotaProduccion;
import notas.NotaVenta;

/**
 *
 * @author Jesús Ernesto
 */
public class NuevoPedido extends javax.swing.JFrame {
    private static NuevoPedido instancia=null;
    String telefono;
    public static NuevoPedido getInstance(){
        if(instancia==null){
            instancia=new NuevoPedido();
        }
        return instancia;
    }
    /**
     * Creates new form NuevoPedido
     */
    public void setear(String nombre, String folio,String telefono) {
        Nombre.setText(nombre);
        IdCliente.setText(folio);
        this.telefono=telefono;
    }
    
    public NuevoPedido() {
        Conector.getInstance();
        initComponents();
        Fecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        try {
            Conector.getInstance().Buscar("SELECT * FROM pedido ORDER BY id DESC LIMIT 1");
            if(Conector.getInstance().cdr.next()){
                Id.setText((Conector.getInstance().cdr.getInt("id")+1)+"");
            }
            else
                Id.setText("1");
        }
         catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al conectar la base de datos",null,JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(NuevoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Concepto = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        Fecha = new javax.swing.JFormattedTextField();
        GenerarBtn = new javax.swing.JButton();
        Id = new javax.swing.JFormattedTextField();
        Total = new javax.swing.JFormattedTextField();
        IdCliente = new javax.swing.JFormattedTextField();
        AbonoTotal = new javax.swing.JFormattedTextField();
        BuscarClienteN = new javax.swing.JButton();
        VerificarNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVO PEDIDO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Folio del Pedido:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Clave del Cliente:");

        jLabel4.setText("Concepto: ");

        jLabel5.setText("TOTAL ($):");

        jLabel6.setText("Abono Total ($):");

        jScrollPane1.setViewportView(Concepto);

        jLabel7.setText("Fecha:");

        Fecha.setEditable(false);
        Fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaActionPerformed(evt);
            }
        });

        GenerarBtn.setText("Generar Nuevo Pedido");
        GenerarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarBtnActionPerformed(evt);
            }
        });

        Id.setEditable(false);
        Id.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        Total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        IdCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        AbonoTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        BuscarClienteN.setText("Buscar Cliente");
        BuscarClienteN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarClienteNActionPerformed(evt);
            }
        });

        VerificarNuevo.setText("Verificar Pedido");
        VerificarNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerificarNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BuscarClienteN, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(VerificarNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GenerarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Nombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1))
                            .addComponent(IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Fecha)
                            .addComponent(Total)
                            .addComponent(AbonoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel5))))
                        .addGap(72, 72, 72))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, 0)
                        .addComponent(IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addComponent(AbonoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(GenerarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BuscarClienteN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(VerificarNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaActionPerformed

    private void GenerarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarBtnActionPerformed
        if("".equals(Id.getText())||"".equals(Fecha.getText())||"".equals(Nombre.getText())||"".equals(Total.getText())||"".equals(IdCliente.getText())||"".equals(AbonoTotal.getText())||"".equals(Concepto.getText())){
            JOptionPane.showMessageDialog(null, "Existen casillas vacias",null,JOptionPane.WARNING_MESSAGE);
        }
        else{
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente agregar nuevo pedido?","Pedido",JOptionPane.WARNING_MESSAGE);
            if(opc==JOptionPane.YES_OPTION){
                BigDecimal abo,tot;
                abo=new BigDecimal(AbonoTotal.getText());
                tot=new BigDecimal(Total.getText());
                if(abo.compareTo(tot)==1) JOptionPane.showMessageDialog(null, "¡Abono Mayor que total!",null,JOptionPane.WARNING_MESSAGE);
                else{
                    try {
                        Conector.getInstance().Insertar("INSERT INTO pedido VALUES("+Id.getText()+",'"+fecha+"',"+IdCliente.getText()+",'"+Concepto.getText()+"',"+Total.getText()+","+AbonoTotal.getText()+")");
                        Conector.getInstance().Insertar("INSERT INTO abono VALUES(NULL,'"+fecha+"',"+Id.getText()+","+AbonoTotal.getText()+")");
                        JOptionPane.showMessageDialog(null, "¡Pedido realizado con éxito!");
                        Object[] possibleValues = { " ","Nota de Venta", "Orden de producción" };
                        Object selectedValue = JOptionPane.showInputDialog(null,"¿Desea generar alguna nota? ", "Generar Nota",  JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
                        
                        if(selectedValue=="Nota de Venta") {
                            NotaVenta.getInstance().setear(Id.getText(),Nombre.getText(),telefono,Concepto.getText(),AbonoTotal.getText(),Total.getText());
                            try {
                                NotaVenta.getInstance().createPdf("NotaVenta.pdf");
                            } catch (DocumentException ex) {
                                JOptionPane.showMessageDialog(null,"Error al generar nota de venta",null,JOptionPane.ERROR_MESSAGE);
                                Logger.getLogger(NuevoPedido.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null,"Error al generar nota de venta",null,JOptionPane.ERROR_MESSAGE);
                                Logger.getLogger(NuevoPedido.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if(selectedValue=="Orden de producción") {
                            NotaProduccion.getInstance().setear(Id.getText(),Nombre.getText(),telefono,Concepto.getText());
                            try {
                                NotaProduccion.getInstance().createPdf("NotaProduccion.pdf");
                            } catch (DocumentException ex) {
                                JOptionPane.showMessageDialog(null,"Error al generar orden de producción",null,JOptionPane.ERROR_MESSAGE);
                                Logger.getLogger(NuevoPedido.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null,"Error al generar orden de producción",null,JOptionPane.ERROR_MESSAGE);
                                Logger.getLogger(NuevoPedido.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
            
                        dispose();
                        NuevoPedido.instancia=null;
                    } catch (SQLException ex) {
                        if(ex.getSQLState().startsWith("23"))
                            JOptionPane.showMessageDialog(null,"Cliente Inexistente",null,JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(NuevoPedido.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }
        }
    }//GEN-LAST:event_GenerarBtnActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
           dispose();
           NuevoPedido.instancia=null;
    }//GEN-LAST:event_formWindowClosed

    private void BuscarClienteNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarClienteNActionPerformed
        FiltroClientes.getInstance().setVisible(true);
        FiltroClientes.clase_procedencia="NuevoPedido";
    }//GEN-LAST:event_BuscarClienteNActionPerformed

    private void VerificarNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerificarNuevoActionPerformed
        try {
            Conector.getInstance().Buscar("SELECT cliente.nombre,cliente.telefono FROM cliente JOIN pedido WHERE pedido.idCliente=cliente.id AND cliente.id="+IdCliente.getText());
            if(Conector.getInstance().cdr.next()){
                Nombre.setText(Conector.getInstance().cdr.getString("nombre"));
                telefono=Conector.getInstance().cdr.getString("telefono");
            }
            else{
               JOptionPane.showMessageDialog(null,"Cliente Inexistente",null,JOptionPane.ERROR_MESSAGE);
                Nombre.setText("");
                telefono="";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error al conectar la base de datos!",null,JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(NuevoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_VerificarNuevoActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JFormattedTextField AbonoTotal;
    private javax.swing.JButton BuscarClienteN;
    public javax.swing.JTextPane Concepto;
    public javax.swing.JFormattedTextField Fecha;
    public javax.swing.JButton GenerarBtn;
    public javax.swing.JFormattedTextField Id;
    public javax.swing.JFormattedTextField IdCliente;
    public javax.swing.JTextField Nombre;
    public javax.swing.JFormattedTextField Total;
    private javax.swing.JButton VerificarNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
