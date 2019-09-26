/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asesoresdeventa;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;
import newpackage.ConectarMySQL;

/**
 *
 * @author david
 */
public class RcibirEquiposRemitidos extends javax.swing.JFrame {

    DefaultListModel modelo = new DefaultListModel();
    PreparedStatement consultar, consultarCliente, consultarServicio, consultarEquipo, fecha, guardarTodo;
    Remisiones r;
    ConectarMySQL conexion;
    boolean positivo;
    String fechaActual, numCliente;

    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * Creates new form RcibirEquiposRemitidos
     */
    public RcibirEquiposRemitidos(ConectarMySQL conexionRemision) {
        conexion = conexionRemision;
        initComponents();
        setVisible(true);
        setBounds(200, 50, 400, 500);
        consultarServicio();
        ocultar();
        this.getContentPane().setBackground(Color.white);
    }

    @SuppressWarnings("unchecked")
    private void ocultar() {
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel1.setVisible(false);
        jLabel7.setVisible(false);
        txtProcedimientos.setVisible(false);
        jScrollPane2.setVisible(false);
        jButton2.setVisible(false);
    }

    private void habilitar() {
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jLabel1.setVisible(true);
        jLabel7.setVisible(true);
        txtProcedimientos.setVisible(true);
        jScrollPane2.setVisible(true);
        jButton2.setVisible(true);
    }

    private void consultarServicio() {
        try {
            modelo.removeAllElements();
            consultarServicio = null;
            if (consultarServicio == null) {
                consultarServicio = conexion.getConexion().prepareStatement("SELECT\n"
                        + "     remisionequipos.`idServicio`\n"
                        + "FROM\n"
                        + "     `servicios` servicios INNER JOIN `clientes_servicios` clientes_servicios ON servicios.`idServicio` = clientes_servicios.`idServicio`\n"
                        + "     INNER JOIN `remisionequipos` remisionequipos ON servicios.`idServicio` = remisionequipos.`idServicio`");
            }
            ResultSet c = consultarServicio.executeQuery();
            while (c.next()) {
//                setNum_servicio(Integer.parseInt(c.getString(1)));
                modelo.addElement(c.getString(1));
                jList1.setModel(modelo);

            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    private void buscarFecha() {
        try {
            if (fecha == null) {
                fecha = conexion.getConexion().prepareStatement("select now()");
            }
            ResultSet consulta = fecha.executeQuery();
            while (consulta.next()) {
                setFechaActual(consulta.getString(1));
            }
        } catch (Exception e) {
        }

    }

    private void guardar() {
        buscarFecha();
        try {
            guardarTodo = null;
            if (guardarTodo == null) {
                guardarTodo = conexion.getConexion().prepareStatement("update remisionequipos set fechaEntrega=?,procedimientos=?,estado=? where idServicio=? ");
            }
            guardarTodo.setInt(4, Integer.parseInt(jList1.getSelectedValue()));
            guardarTodo.setString(1, fechaActual);
            guardarTodo.setString(2, txtProcedimientos.getText());
            guardarTodo.setString(3, "completado");
            guardarTodo.execute();
            JOptionPane.showMessageDialog(this, "Datos Guardados");
        } catch (Exception e) {
        }

    }

    private void crearReporte() {
        JasperReport reporte;
        String ruta3 = "/reportes/recibirRemision.jasper";
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("numServicio", Integer.parseInt(jList1.getSelectedValue()));
            System.out.println("parametro numServicio: " + parametros.put("numServicio", Integer.parseInt(jList1.getSelectedValue())));
//            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta3);
//            reporte = JasperCompileManager.compileReport(ruta3);
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta3));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion.getConexion());
            Exporter exportar = new JRPdfExporter();
            exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
            exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("RecepcionRemision.pdf"));
            exportar.exportReport();
            JasperViewer visualizar = new JasperViewer(jasperPrint, false);
            visualizar.setTitle("Formato Recepcion Remision De Equipos");
            visualizar.setVisible(true);
            visualizar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            System.out.println("Excepcion AL entregar: " + e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtProcedimientos = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 120, 110));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 160, 20));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("Procedimientos Realizados");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 180, 20));

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 320, 30));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setText("Recibir Equipos Remitidos");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 20));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 160, 20));

        txtProcedimientos.setColumns(20);
        txtProcedimientos.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtProcedimientos.setRows(5);
        txtProcedimientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProcedimientosKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtProcedimientos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 320, -1));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 160, 20));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 100, -1));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Cliente:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 160, 20));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Equipo: ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (txtProcedimientos.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe Completar Los Campos");
        } else {
            guardar();
            crearReporte();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        try {
            jButton2.setEnabled(true);
            if (consultarEquipo == null) {
                consultarEquipo = conexion.getConexion().prepareStatement("select marca,modelo from equipos where idEquipo=?");
            }
            consultarEquipo.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet cons = consultarEquipo.executeQuery();
            while (cons.next()) {
                jLabel1.setText("Marca: " + cons.getString(1));
                jLabel4.setText("Modelo: " + cons.getString(2));
            }

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        habilitar();

        try {
            if (consultar == null) {
                consultar = conexion.getConexion().prepareStatement("select idCliente from clientes_servicios where idEquipo=?");
            }
            consultar.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet con = consultar.executeQuery();
            while (con.next()) {
                setNumCliente(con.getString(1));
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

        try {
            if (consultarCliente == null) {
                consultarCliente = conexion.getConexion().prepareStatement("select nombre from clientes where idClientes=?");

            }
            consultarCliente.setString(1, numCliente);
            System.out.println("nombre de: " + numCliente);
            ResultSet consulta = consultarCliente.executeQuery();
            while (consulta.next()) {
                jLabel5.setText(consulta.getString(1));
                System.out.println("nombre: " + consulta.getString(1));
            }

        } catch (Exception e) {
        }

        try {
            PreparedStatement consulta = conexion.getConexion().prepareStatement("select estado from remisionequipos where idServicio=?");
            consulta.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet con = consulta.executeQuery();
            while (con.next()) {
                String estado = con.getString(1);
                if (estado.equals("completado")) {
                    modelo.removeElementAt(jList1.getSelectedIndex());
                    ocultar();
                    JOptionPane.showMessageDialog(this, "Este Equipo ya Fue Recibido y Esta Listo para Entregar al Cliente");

                }

            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jList1MouseClicked

    private void txtProcedimientosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimientosKeyTyped
        txtProcedimientos.setLineWrap(true);
        txtProcedimientos.setWrapStyleWord(true);
    }//GEN-LAST:event_txtProcedimientosKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        r = new Remisiones(conexion);
        r.setVisible(true);
        super.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RcibirEquiposRemitidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RcibirEquiposRemitidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RcibirEquiposRemitidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RcibirEquiposRemitidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtProcedimientos;
    // End of variables declaration//GEN-END:variables
}
