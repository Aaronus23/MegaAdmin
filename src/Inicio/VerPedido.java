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
import javax.swing.table.TableRowSorter;
import notas.NotaProduccion;
import notas.NotaVenta;

/**
 *
 * @author Jesús Ernesto
 */

public class VerPedido extends javax.swing.JFrame {
    private static VerPedido instancia=null;
    Vector<String> cols;
    BigDecimal tot;
    ArrayList f1,f2,finalf;
    TableRowSorter filtroAnd;
    public static VerPedido getInstance(){
        if(instancia==null){
            instancia=new VerPedido();
        }
        return instancia;
    }
    public VerPedido() {   
        cols=new Vector<>();
        cols.add("Folio");
        cols.add("Fecha");
        cols.add("Nombre");
        cols.add("Concepto");
        cols.add("Abonado");
        cols.add("Total");
        cols.add("Telefono");
        initComponents();
        Conector.getInstance();
        filtroAnd=new TableRowSorter();
        Filtro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                checarFiltros();
            }
        
        });
        FechaInicial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                checarFiltros();
            }
        
        });
        FechaFinal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                checarFiltros();
            }
        
        });
        getTot();
    }
    void checarFiltros(){
        String cadena = Filtro.getText();
        Filtro.setText(cadena);
        repaint();
        FechaInicial.setEditable(ChekFecha.isSelected());
        FechaFinal.setEditable(ChekFecha.isSelected());
        if(ChekFecha.isSelected())
            filtroMixto();
        else
            filtroSolo();
        TablaPedidos.setRowSorter(filtroAnd); 
        getTot();
    }
    public void filtroSolo() {
        filtroAnd = new TableRowSorter(TablaPedidos.getModel());
        filtroAnd.setRowFilter(RowFilter.regexFilter("(?i)"+Filtro.getText()));
        getTot();
    }
    public void filtroMixto(){
        /*
        String valor = null;
        valor=valor.replace("a", "(a|á|A|Á)");
        valor=valor.replace("e", "(e|é|E|É)");
        valor=valor.replace("i", "(i|í|I|Í)");
        valor=valor.replace("o", "(o|ó|O|Ó)");
        valor=valor.replace("u", "(u|ú|U|Ú)");
        valor=valor.replace("A", "(a|á|A|Á)");
        valor=valor.replace("E", "(e|é|E|É)");
        valor=valor.replace("I", "(i|í|I|Í)");
        valor=valor.replace("O", "(o|ó|O|Ó)");
        valor=valor.replace("U", "(u|ú|U|Ú)");
        */
        filtroAnd = new TableRowSorter(TablaPedidos.getModel());
        String x1=FechaInicial.getText();
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
        f1=new ArrayList();
        f2=new ArrayList();
        finalf=new ArrayList();
        f1.add(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL, fecha1,1));
        f1.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, fecha1,1));
        f2.add(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL, fecha2,1));
        f2.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, fecha2,1));
        finalf.add(RowFilter.orFilter(f1));
        finalf.add(RowFilter.orFilter(f2));
        finalf.add(RowFilter.regexFilter("(?i)"+Filtro.getText()));
        //finalf.add(RowFilter.regexFilter(Filtro.getText(),1));
        filtroAnd.setRowFilter(RowFilter.andFilter(finalf));
        getTot();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPedidos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        FechaInicial = new javax.swing.JFormattedTextField(new Date());
        jLabel2 = new javax.swing.JLabel();
        FechaFinal = new javax.swing.JFormattedTextField(new Date());
        jLabel3 = new javax.swing.JLabel();
        Filtro = new javax.swing.JTextField();
        ChekFecha = new javax.swing.JCheckBox();
        InsertarPedidoV = new javax.swing.JButton();
        EliminarPedidoV = new javax.swing.JButton();
        HacerNotaVenta = new javax.swing.JButton();
        TotalV = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        OrdenProduccion = new javax.swing.JButton();
        AbonarPedidoV = new javax.swing.JButton();
        TotalV2 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PEDIDOS");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        try{
            TablaPedidos.setModel(Conector.getInstance().buildTableModel("SELECT pedido.id, pedido.fecha,cliente.nombre,pedido.concepto,pedido.abonoTotal,pedido.total,cliente.telefono FROM pedido JOIN cliente ON pedido.idCliente=cliente.id",cols));
        } catch(SQLException ex){
        }
        TablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPedidosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaPedidos);

        jLabel1.setText("Fecha Inicial:");

        FechaInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel2.setText("Fecha Final:");

        FechaFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel3.setText("Filtrar Datos:");

        Filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroActionPerformed(evt);
            }
        });
        Filtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FiltroKeyPressed(evt);
            }
        });

        ChekFecha.setText("Filtrar por Fecha");
        ChekFecha.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ChekFechaStateChanged(evt);
            }
        });

        InsertarPedidoV.setText("Insertar Pedido");
        InsertarPedidoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarPedidoVActionPerformed(evt);
            }
        });

        EliminarPedidoV.setText("Eliminar Pedido");
        EliminarPedidoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarPedidoVActionPerformed(evt);
            }
        });

        HacerNotaVenta.setText("Hacer Nota de Venta");
        HacerNotaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HacerNotaVentaActionPerformed(evt);
            }
        });

        TotalV.setEditable(false);
        TotalV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        TotalV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalVActionPerformed(evt);
            }
        });

        jLabel4.setText("TOTAL ($):");

        OrdenProduccion.setText("Hacer Orden de Producción");
        OrdenProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrdenProduccionActionPerformed(evt);
            }
        });

        AbonarPedidoV.setText("Abonar a este Pedido");
        AbonarPedidoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbonarPedidoVActionPerformed(evt);
            }
        });

        TotalV2.setEditable(false);
        TotalV2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        TotalV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalV2ActionPerformed(evt);
            }
        });

        jLabel5.setText("TOTAL VENDIDO($):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ChekFecha)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(75, 75, 75)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(InsertarPedidoV, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(EliminarPedidoV, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(OrdenProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(HacerNotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(AbonarPedidoV, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(TotalV, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TotalV2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(FechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChekFecha)
                    .addComponent(FechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(TotalV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(OrdenProduccion)
                                    .addComponent(InsertarPedidoV))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EliminarPedidoV)
                                    .addComponent(HacerNotaVenta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AbonarPedidoV)
                                .addGap(27, 27, 27))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiltroActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        dispose();
        VerPedido.instancia=null;
    }//GEN-LAST:event_formWindowClosed
 void getTot(){
        BigDecimal cant;
        tot=BigDecimal.ZERO;
        BigDecimal cant2; //Esta cantidad servirá para mostrar el total de dinero de los pedidos pero sin descontarle los abonos
        for(int i=0; i<TablaPedidos.getRowCount(); i++){
            cant = new BigDecimal(TablaPedidos.getValueAt(i,5)+"").subtract(new BigDecimal(TablaPedidos.getValueAt(i, 4)+""));
            tot=tot.add(cant);
        }
        TotalV.setText(tot.toString());
        tot=BigDecimal.ZERO;
        for(int j=0; j<TablaPedidos.getRowCount(); j++){
            cant2 = new BigDecimal(TablaPedidos.getValueAt(j,5)+"");
            tot=tot.add(cant2);
        }
        TotalV2.setText(tot.toString());
    }
    private void EliminarPedidoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarPedidoVActionPerformed
        int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente eliminar a este pedido?","Eliminar",JOptionPane.WARNING_MESSAGE);
            if(opc==JOptionPane.YES_OPTION){
                int id=TablaPedidos.getSelectedRow();
                if(id==-1)
                    JOptionPane.showMessageDialog(null, "¡Ningun pedido seleccionado!",null,JOptionPane.WARNING_MESSAGE);
                else{
                    try {
                        Conector.getInstance().Insertar("DELETE FROM pedido WHERE id="+TablaPedidos.getValueAt(id,0));
                        JOptionPane.showMessageDialog(null, "¡Datos del pedido eliminados exitosamente!");
                        VerPedido.getInstance().TablaPedidos.setModel(Conector.getInstance().buildTableModel("SELECT pedido.id, pedido.fecha,cliente.nombre,pedido.concepto,pedido.abonoTotal,pedido.total,cliente.telefono FROM pedido JOIN cliente ON pedido.idCliente=cliente.id",VerPedido.getInstance().cols));
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "¡Ningun pedido seleccionado!",null,JOptionPane.WARNING_MESSAGE);
                        Logger.getLogger(VerPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }//GEN-LAST:event_EliminarPedidoVActionPerformed

    private void InsertarPedidoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarPedidoVActionPerformed
        NuevoPedido.getInstance().setVisible(true);
    }//GEN-LAST:event_InsertarPedidoVActionPerformed

    private void HacerNotaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HacerNotaVentaActionPerformed
        int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente generar una nota de venta?","Generar Nota de Venta",JOptionPane.INFORMATION_MESSAGE);
        if(opc==JOptionPane.YES_OPTION){
            int fil=TablaPedidos.getSelectedRow();
            if(fil==-1){
                JOptionPane.showMessageDialog(null, "¡Ninguna fila seleccionada!",null,JOptionPane.WARNING_MESSAGE);
                return;
            } 
            NotaVenta.getInstance();
            NotaVenta.getInstance().setear(TablaPedidos.getValueAt(fil,0)+"",TablaPedidos.getValueAt(fil,2)+"",TablaPedidos.getValueAt(fil,6)+"",TablaPedidos.getValueAt(fil,3)+"",TablaPedidos.getValueAt(fil,4)+"",TablaPedidos.getValueAt(fil,5)+"");
            try {
                NotaVenta.getInstance().createPdf("NotaVenta.pdf");
            JOptionPane.showMessageDialog(null, "¡Nota de venta generado con éxito!");
            } catch (DocumentException | IOException ex) {
                JOptionPane.showMessageDialog(null, "¡Error al generar Nota de Venta!",null,JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(VerPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_HacerNotaVentaActionPerformed

    private void OrdenProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrdenProduccionActionPerformed
        int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente generar Orden de Producción?","Generar Nota",JOptionPane.INFORMATION_MESSAGE);
        if(opc==JOptionPane.YES_OPTION){
            int fil=TablaPedidos.getSelectedRow();
            if(fil==-1){
                JOptionPane.showMessageDialog(null, "¡Ninguna fila seleccionada!",null,JOptionPane.WARNING_MESSAGE);
                return;
            } 
            NotaProduccion.getInstance();
            NotaProduccion.getInstance().setear(TablaPedidos.getValueAt(fil,0)+"",TablaPedidos.getValueAt(fil,2)+"",TablaPedidos.getValueAt(fil,6)+"",TablaPedidos.getValueAt(fil,3)+"");
            try {
                NotaProduccion.getInstance().createPdf("NotaProduccion.pdf");
               JOptionPane.showMessageDialog(null, "¡Orden de producción generada con éxito!");
            } catch (DocumentException | IOException ex) {
                JOptionPane.showMessageDialog(null, "¡Error al generar orden de producción!",null,JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(VerPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_OrdenProduccionActionPerformed

    private void AbonarPedidoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbonarPedidoVActionPerformed
        BigDecimal cantS=null;
        try{
            cantS=new BigDecimal(JOptionPane.showInputDialog(null,"Cantidad a abonar",""));
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "¡Cantidad en formato invalido",null,JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opc=JOptionPane.showConfirmDialog(null,"¿Desea realmente abonar a este pedido?","",JOptionPane.WARNING_MESSAGE);
        if(opc==JOptionPane.YES_OPTION){
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            int id= TablaPedidos.getSelectedRow();
            if(id==-1){
                JOptionPane.showMessageDialog(null, "¡Ningun pedido seleccionado!",null,JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(cantS.compareTo(BigDecimal.ZERO)<1){
                JOptionPane.showMessageDialog(null, "¡Estas abonando una cantidad menor o igual a cero",null,JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Conector.getInstance().Insertar("INSERT INTO abono VALUES(NULL,'"+fecha+"',"+TablaPedidos.getValueAt(id, 0)+","+cantS+")");
                Conector.getInstance().Insertar("UPDATE pedido SET abonoTotal=abonoTotal+"+cantS+" WHERE id="+TablaPedidos.getValueAt(id, 0));
                JOptionPane.showMessageDialog(null, "¡Abono realizado con éxito!");
                Caja.getInstance().TablaCaja.setModel(Conector.getInstance().buildTableModel("SELECT fecha ,CONCAT('Abono al pedido: ',idPedido) as concepto,monto FROM abono UNION SELECT fecha,concepto,monto FROM caja ORDER BY fecha DESC",Caja.getInstance().cols));
                TablaPedidos.setModel(Conector.getInstance().buildTableModel("SELECT pedido.id, pedido.fecha,cliente.nombre,pedido.concepto,pedido.abonoTotal,pedido.total,cliente.telefono FROM pedido JOIN cliente ON pedido.idCliente=cliente.id",cols));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "¡Error al conectar la base de datos!",null,JOptionPane.ERROR_MESSAGE);
                 Logger.getLogger(VerPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_AbonarPedidoVActionPerformed

    private void ChekFechaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ChekFechaStateChanged
        checarFiltros();
    }//GEN-LAST:event_ChekFechaStateChanged

    private void FiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltroKeyPressed
        checarFiltros();
    }//GEN-LAST:event_FiltroKeyPressed

    private void TablaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPedidosMouseClicked
        String folio, fecha, nombre, concepto, abono, total, numero;
        int row = TablaPedidos.rowAtPoint(evt.getPoint());
        if(evt.getClickCount()==2) {
            MostrarDatosPedidos.getInstance().setVisible(true);
            folio=TablaPedidos.getValueAt(row,0).toString();
            fecha=new SimpleDateFormat("dd/MM/yyyy").format(TablaPedidos.getValueAt(row,1));
            nombre=TablaPedidos.getValueAt(row,2).toString();
            concepto=TablaPedidos.getValueAt(row,3).toString();
            abono=TablaPedidos.getValueAt(row,4).toString();
            total=TablaPedidos.getValueAt(row,5).toString();
            numero=TablaPedidos.getValueAt(row,6).toString();     
            MostrarDatosPedidos.getInstance().setear(folio,fecha,nombre,concepto,abono,total,numero);
        }
    }//GEN-LAST:event_TablaPedidosMouseClicked

    private void TotalVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalVActionPerformed

    private void TotalV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalV2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalV2ActionPerformed


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
            java.util.logging.Logger.getLogger(VerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AbonarPedidoV;
    private javax.swing.JCheckBox ChekFecha;
    private javax.swing.JButton EliminarPedidoV;
    public javax.swing.JFormattedTextField FechaFinal;
    public javax.swing.JFormattedTextField FechaInicial;
    public javax.swing.JTextField Filtro;
    private javax.swing.JButton HacerNotaVenta;
    private javax.swing.JButton InsertarPedidoV;
    private javax.swing.JButton OrdenProduccion;
    public javax.swing.JTable TablaPedidos;
    private javax.swing.JFormattedTextField TotalV;
    private javax.swing.JFormattedTextField TotalV2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
