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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jesús Ernesto
 */
public class FiltroClientes extends javax.swing.JFrame {
    public String id,nombre,telefono,telefono2,direccion,folio;
    int row;
    Vector <String> cols;
    
    public static String clase_procedencia;
    
    private static FiltroClientes instancia=null;
    public static FiltroClientes getInstance(){
        if(instancia==null){
            instancia=new FiltroClientes();
        }
        return instancia;
    }
    /**
     * Creates new form FiltroClientes
     */
    public FiltroClientes() {
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
        trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)"+Filtro.getText()));
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
        TablaClientesEliminar = new javax.swing.JTable();
        AceptarEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Filtro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CLIENTES");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Gulim", 1, 14)); // NOI18N
        jLabel1.setText("CLIENTES");

        try{
            TablaClientesEliminar.setModel(Conector.getInstance().buildTableModel("SELECT * FROM cliente",cols));
        } catch (SQLException ex){
        }
        TablaClientesEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaClientesEliminarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaClientesEliminar);

        AceptarEliminar.setText("Aceptar");
        AceptarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarEliminarActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrar:");

        Filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroActionPerformed(evt);
            }
        });
        Filtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FiltroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(jLabel1)))
                        .addGap(0, 389, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(AceptarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AceptarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarEliminarActionPerformed
        dispose();
        FiltroClientes.instancia=null;
        id=TablaClientesEliminar.getValueAt(row,0).toString();
        if(TablaClientesEliminar.getValueAt(row,1)==null) {
            nombre=" ";
        }
        else {
            nombre=TablaClientesEliminar.getValueAt(row,1).toString();
        }        
        if(TablaClientesEliminar.getValueAt(row,2)==null) {
            telefono=" ";
        }
        else {
            telefono=TablaClientesEliminar.getValueAt(row,2).toString();
        }
        if(TablaClientesEliminar.getValueAt(row,3)==null) {
            telefono2=" ";
        }
        else {
            telefono2=TablaClientesEliminar.getValueAt(row,3).toString();
        }
        if(TablaClientesEliminar.getValueAt(row,4)==null) {
            direccion=" ";
        }
        else {
        direccion=TablaClientesEliminar.getValueAt(row,4).toString();
        }
        if(clase_procedencia=="EliminarCliente")
            EliminarCliente.getInstance().setear(id,nombre,telefono,telefono2,direccion);
        if(clase_procedencia=="NuevoPedido")
            NuevoPedido.getInstance().setear(nombre, id,telefono);
    }//GEN-LAST:event_AceptarEliminarActionPerformed

    private void TablaClientesEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaClientesEliminarMouseClicked
        row = TablaClientesEliminar.rowAtPoint(evt.getPoint());
        if(evt.getClickCount()==2) {
           id=TablaClientesEliminar.getValueAt(row,0).toString();
        if(TablaClientesEliminar.getValueAt(row,1)==null) {
            nombre=" ";
        }
        else {
            nombre=TablaClientesEliminar.getValueAt(row,1).toString();
        }        
        if(TablaClientesEliminar.getValueAt(row,2)==null) {
            telefono=" ";
        }
        else {
            telefono=TablaClientesEliminar.getValueAt(row,2).toString();
        }
        if(TablaClientesEliminar.getValueAt(row,3)==null) {
            telefono2=" ";
        }
        else {
            telefono2=TablaClientesEliminar.getValueAt(row,3).toString();
        }
        if(TablaClientesEliminar.getValueAt(row,4)==null) {
            direccion=" ";
        }
        else {
        direccion=TablaClientesEliminar.getValueAt(row,4).toString();
        }
        if(clase_procedencia=="EliminarCliente")
            EliminarCliente.getInstance().setear(id,nombre,telefono,telefono2,direccion);
        if(clase_procedencia=="NuevoPedido")
            NuevoPedido.getInstance().setear(nombre, id,telefono);
        dispose();
        FiltroClientes.instancia=null;
        }
    }//GEN-LAST:event_TablaClientesEliminarMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
                    dispose();
                    FiltroClientes.instancia=null;
    }//GEN-LAST:event_formWindowClosed

    private void FiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiltroActionPerformed

    private void FiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroKeyTyped
        Filtro.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (Filtro.getText());
                Filtro.setText(cadena);
                repaint();
                filtro();
            }
        });
        trsfiltro = new TableRowSorter(TablaClientesEliminar.getModel());
        TablaClientesEliminar.setRowSorter(trsfiltro);
    }//GEN-LAST:event_FiltroKeyTyped

    private void FiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroKeyPressed
        
    }//GEN-LAST:event_FiltroKeyPressed

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
            java.util.logging.Logger.getLogger(FiltroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FiltroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FiltroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FiltroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FiltroClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton AceptarEliminar;
    public javax.swing.JTextField Filtro;
    public javax.swing.JTable TablaClientesEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
