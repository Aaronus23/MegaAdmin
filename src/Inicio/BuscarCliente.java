/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import Conector.Conector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jesús Ernesto
 */
public class BuscarCliente extends javax.swing.JFrame {
    Vector <String> cols;
    private static BuscarCliente instancia=null;
    public static BuscarCliente getInstance(){
        if(instancia==null){
            instancia=new BuscarCliente();
        }
        return instancia;
    }
    /**
     * Creates new form BuscarCliente
     */
    public BuscarCliente() {
        cols=new Vector<>();
        cols.add("ID");
        cols.add("Nombre");
        cols.add("Telefono");
        cols.add("Telefono2");
        cols.add("Direccion");
        Conector.getInstance();
        initComponents();
    }
    private TableRowSorter trsfiltro;
    public void filtro() {
        trsfiltro.setRowFilter(RowFilter.regexFilter(FiltroGeneral.getText()));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDatos = new javax.swing.JTable();
        EliminarClienteB = new javax.swing.JButton();
        InsertarClienteB = new javax.swing.JButton();
        FiltroGeneral = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CLIENTES");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Filtro General:");

        try{
            TablaDatos.setAutoCreateRowSorter(true);
            TablaDatos.setModel(Conector.getInstance().buildTableModel("SELECT * FROM cliente",cols));
        } catch(SQLException ex){
        }
        jScrollPane1.setViewportView(TablaDatos);

        EliminarClienteB.setText("Eliminar Cliente");
        EliminarClienteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarClienteBActionPerformed(evt);
            }
        });

        InsertarClienteB.setText("Insertar Cliente");
        InsertarClienteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarClienteBActionPerformed(evt);
            }
        });

        FiltroGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroGeneralActionPerformed(evt);
            }
        });
        FiltroGeneral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FiltroGeneralKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(FiltroGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(InsertarClienteB, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EliminarClienteB, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EliminarClienteB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(InsertarClienteB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(FiltroGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
            dispose();
            BuscarCliente.instancia=null;
    }//GEN-LAST:event_formWindowClosed

    private void InsertarClienteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarClienteBActionPerformed
        IngresarCliente.getInstance().setVisible(true);
    }//GEN-LAST:event_InsertarClienteBActionPerformed

    private void EliminarClienteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarClienteBActionPerformed
        int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente eliminar a este cliente?","Eliminar",JOptionPane.WARNING_MESSAGE);
            if(opc==JOptionPane.YES_OPTION){
                try {
                    Conector.getInstance().Insertar("DELETE FROM cliente WHERE id="+BuscarCliente.getInstance().TablaDatos.getValueAt(BuscarCliente.getInstance().TablaDatos.getSelectedRow(),0));
                    JOptionPane.showMessageDialog(null, "¡Datos del cliente eliminados exitosamente!");
                    dispose();
                    instancia=null;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar datos",null, JOptionPane.WARNING_MESSAGE);
                    Logger.getLogger(BuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_EliminarClienteBActionPerformed

    private void FiltroGeneralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroGeneralKeyTyped
        FiltroGeneral.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (FiltroGeneral.getText());
                FiltroGeneral.setText(cadena);
                repaint();
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(TablaDatos.getModel());
        TablaDatos.setRowSorter(trsfiltro);
    }//GEN-LAST:event_FiltroGeneralKeyTyped

    private void FiltroGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiltroGeneralActionPerformed
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
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarClienteB;
    private javax.swing.JTextField FiltroGeneral;
    private javax.swing.JButton InsertarClienteB;
    public javax.swing.JTable TablaDatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
