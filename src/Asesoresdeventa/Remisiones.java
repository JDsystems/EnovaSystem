package Asesoresdeventa;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

public class Remisiones extends javax.swing.JFrame {

    AcesoresDeVenta a;
    DefaultListModel model = new DefaultListModel();
    RcibirEquiposRemitidos r;
    ConectarMySQL conexionRemision;
    PreparedStatement consultarFecha, guardar, guardarestado, consultar, consultar1, consultar2;
    String fechaInicial, estado1;
    int idServicio;
    boolean existe, estado = false;

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Remisiones(ConectarMySQL conexionBD) {
        conexionRemision = conexionBD;
        initComponents();
        jLabel7.setVisible(false);
        jList1.setVisible(false);
        jScrollPane2.setVisible(false);
        lblMarca.setVisible(false);
        lblModelo.setVisible(false);
        btnBuscar.setVisible(false);
        txtIdCliente.setVisible(false);
        ocultar();
        setVisible(true);
        setBounds(180, 50, 500, 640);
        this.getContentPane().setBackground(Color.white);
        autocompletar();
        digitarNumeros(txtIdCliente);
        digitarNumeros1(txtId_Nit);
        digitarNumeros2(txtTelefono);
    }

    @SuppressWarnings("unchecked")
    private void digitarNumeros(JTextField b) {
        b.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                    getToolkit().beep();
                }

            }
        });

    }

    private void digitarNumeros1(JTextField b) {
        b.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                    getToolkit().beep();
                }

            }
        });

    }

    private void digitarNumeros2(JTextField b) {
        b.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                    getToolkit().beep();
                }

            }
        });

    }

    private void ocultar() {
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        txtDireccion.setVisible(false);
        txtTecnico_empresa.setVisible(false);
        txtId_Nit.setVisible(false);
        txtTelefono.setVisible(false);
        txtObservacion.setVisible(false);
        jScrollPane1.setVisible(false);
        btnGuardarRemision.setVisible(false);

    }

    private void habilitar() {
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        txtTecnico_empresa.setVisible(true);
        txtId_Nit.setVisible(true);
        txtTelefono.setVisible(true);
        txtObservacion.setVisible(true);
        jScrollPane1.setVisible(true);
        btnGuardarRemision.setVisible(true);
        jLabel6.setVisible(true);
        txtDireccion.setVisible(true);

    }

    public void consultaFecha() {
        try {
            consultarFecha = null;
            if (consultarFecha == null) {
                consultarFecha = conexionRemision.getConexion().prepareStatement("select now()");
            }
            ResultSet consulta = consultarFecha.executeQuery();
            while (consulta.next()) {
                setFechaInicial(consulta.getString(1));
                System.out.println("fecha inicial: " + fechaInicial);

            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private void consultarEquipos() {
        try {
            existe = false;
            model.removeAllElements();
            consultar = null;
            if (consultar == null) {

                consultar = conexionRemision.getConexion().prepareStatement("select  idEquipo from clientes_servicios where idCliente=?");
            }
            consultar.setString(1, txtIdCliente.getText());
            ResultSet consulta = consultar.executeQuery();
            while (consulta.next()) {
                model.addElement(consulta.getString(1));
                jList1.setModel(model);
            }
            existe = true;
        } catch (Exception e) {
        }

    }

    private void guardar() {
        try {
            guardar = null;
            if (guardar == null) {
                guardar = conexionRemision.getConexion().prepareStatement("insert into remisionequipos(fechaRemision,nombreTecnico,identificacion,telefon,direccion,observacion,idServicio,estado) values(?,?,?,?,?,?,?,?)");
                guardar.setString(1, fechaInicial);
                guardar.setString(2, txtTecnico_empresa.getText());
                guardar.setString(3, txtId_Nit.getText());
                guardar.setString(4, txtTelefono.getText());
                guardar.setString(5, txtDireccion.getText());
                guardar.setString(6, txtObservacion.getText());
                guardar.setInt(7, idServicio);
                guardar.setString(8, "true");
                guardar.execute();
                txtTecnico_empresa.setText("");
                txtId_Nit.setText("");
                txtTelefono.setText("");
                txtDireccion.setText("");
                txtObservacion.setText("");
                JOptionPane.showMessageDialog(this, "Datos Guardadoos");

            }

        } catch (Exception e) {
            System.out.println("exepcion: " + e.getMessage());
        }
    }

    private void crearReporte() {
        JasperReport reporte;
        String ruta3 = "/reportes/formatoRemision.jasper";
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("numServicio", idServicio);
            System.out.println("parametro numServicio: " + parametros.put("numServicio", idServicio));
//            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta3);
//            reporte = JasperCompileManager.compileReport(ruta3);
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta3));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexionRemision.getConexion());
            Exporter exportar = new JRPdfExporter();
            exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
            exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("formatoRemision.pdf"));
            exportar.exportReport();
            JasperViewer visualizar = new JasperViewer(jasperPrint, false);
            visualizar.setTitle("Formato Remision De Equipos");
            visualizar.setVisible(true);
            visualizar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            System.out.println("Excepcion AL entregar: " + e.getMessage());
        }

    }

    private void autocompletar() {
        TextAutoCompleter textAut = new TextAutoCompleter(txtIdCliente);
        PreparedStatement completar = null;
        try {
            if (completar == null) {
                completar = conexionRemision.getConexion().prepareStatement("select idClientes from clientes");
            }
            ResultSet consulta = completar.executeQuery();
            while (consulta.next()) {
                textAut.addItem(consulta.getString("idClientes"));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        txtTecnico_empresa = new javax.swing.JTextField();
        txtId_Nit = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        btnGuardarRemision = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        btnRemitir = new javax.swing.JButton();
        btnRecibir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar Cliente");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 130, 30));

        txtIdCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 150, 30));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 30));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Equipos");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 130, 30));

        jScrollPane2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        jList1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 130, 140));

        txtTecnico_empresa.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtTecnico_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 280, 30));

        txtId_Nit.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtId_Nit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtId_NitActionPerformed(evt);
            }
        });
        getContentPane().add(txtId_Nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 280, 30));

        txtTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 280, 30));

        txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 280, 30));

        txtObservacion.setColumns(20);
        txtObservacion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtObservacion.setRows(5);
        txtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservacion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 280, -1));

        btnGuardarRemision.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnGuardarRemision.setText("Guardar");
        btnGuardarRemision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRemisionActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarRemision, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 440, 30));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Observaciones");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Direccion");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 120, 30));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Telefono");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 120, 30));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("Identificacion-Nit");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("Tecnico-Empresa");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 30));

        lblModelo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(lblModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 300, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pc1.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 90, 90));

        lblMarca.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(lblMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 300, 30));

        btnRemitir.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRemitir.setText("Remitir");
        btnRemitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemitirActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemitir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 150, 30));

        btnRecibir.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRecibir.setText("Recibir Equipos");
        btnRecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecibir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 150, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtId_NitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId_NitActionPerformed

    }//GEN-LAST:event_txtId_NitActionPerformed

    private void txtObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionKeyTyped
        txtObservacion.setLineWrap(true);
        txtObservacion.setWrapStyleWord(true);
    }//GEN-LAST:event_txtObservacionKeyTyped

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        consultarEquipos();
        if (existe == false) {
            JOptionPane.showMessageDialog(this, "Cliente No tiene Equipos gurdados");
        } else {
            jLabel7.setVisible(true);
            jList1.setVisible(true);
            jScrollPane2.setVisible(true);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        try {
            btnGuardarRemision.setEnabled(true);
            if (consultar1 == null) {
                consultar1 = conexionRemision.getConexion().prepareStatement("select marca,modelo from equipos where idEquipo=?");
            }
            consultar1.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet consulta = consultar1.executeQuery();
            while (consulta.next()) {
                habilitar();
                lblMarca.setVisible(true);
                lblModelo.setVisible(true);
                lblMarca.setText("Marca: " + consulta.getString(1));
                lblModelo.setText("Modelo: " + consulta.getString(2));

            }
        } catch (Exception e) {
        }

        try {

            if (consultar2 == null) {
                consultar2 = conexionRemision.getConexion().prepareStatement("select idServicio from clientes_servicios where  idEquipo=?");
            }
            consultar2.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet con = consultar2.executeQuery();
            while (con.next()) {
                setIdServicio(Integer.parseInt(con.getString(1)));
            }
            estado1 = "";
            PreparedStatement consulta = conexionRemision.getConexion().prepareStatement("select estado from remisionequipos where idServicio=?");
            consulta.setInt(1, idServicio);
            ResultSet c = consulta.executeQuery();
            while (c.next()) {
                estado1 = c.getString(1);
            }
            if (estado1.equals("true") || estado1.equals("completado")) {
                JOptionPane.showMessageDialog(this, "equipo ya fue remitido");
                btnGuardarRemision.setEnabled(false);
            } else {
                btnGuardarRemision.setEnabled(true);
            }

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

        try {
            PreparedStatement consultar = conexionRemision.getConexion().prepareStatement("select fechaEntrega from clientes_servicios where idServicio=?");
            consultar.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet consulta = consultar.executeQuery();
            while (consulta.next()) {
                String fecha = consulta.getString(1);                                                     
                if (!fecha.equals("null")) {
                    JOptionPane.showMessageDialog(this, "Equipo Fue Entregado al Cliente");
                    btnGuardarRemision.setEnabled(false); 
                }
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jList1MouseClicked

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void btnGuardarRemisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRemisionActionPerformed
        if (txtTecnico_empresa.getText().equals("") || txtId_Nit.getText().equals("") || txtTelefono.getText().equals("") || txtDireccion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe Completar los Campos");
        } else {
            consultaFecha();
            guardar();
            crearReporte();
        }

    }//GEN-LAST:event_btnGuardarRemisionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        a = new AcesoresDeVenta(conexionRemision);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRemitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemitirActionPerformed
        btnBuscar.setVisible(true);
        txtIdCliente.setVisible(true);
    }//GEN-LAST:event_btnRemitirActionPerformed

    private void btnRecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirActionPerformed
        r = new RcibirEquiposRemitidos(conexionRemision);
        r.setVisible(true);
        super.dispose();
    }//GEN-LAST:event_btnRecibirActionPerformed

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
            java.util.logging.Logger.getLogger(Remisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Remisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Remisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Remisiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    private void cons_servicio() {

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardarRemision;
    private javax.swing.JButton btnRecibir;
    private javax.swing.JButton btnRemitir;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtId_Nit;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtTecnico_empresa;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
