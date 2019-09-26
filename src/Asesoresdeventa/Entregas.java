package Asesoresdeventa;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
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

public class Entregas extends javax.swing.JFrame {

    DefaultListModel modelo = new DefaultListModel();
    AcesoresDeVenta acesores;
    ConectarMySQL conexionEntrega;
    PreparedStatement consultar, consultar1, consultarRefEquipo, cliente, entregar, fecha, consultarServ, consultarTecnico;
    boolean positivo;
    int numEquipo, numServicio;
    String fechaActual, estado;
    private String idTecnico;

    private void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public void setNumServicio(int numServicio) {
        this.numServicio = numServicio;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public void setNumEquipo(int numEquipo) {
        this.numEquipo = numEquipo;
    }

    public Entregas(ConectarMySQL conexionBD) {
        conexionEntrega = conexionBD;
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        ocultar();
        jList1.setVisible(false);
        jScrollPane2.setVisible(false);
        autocompletar();
        digitarNumeros(txtIdCliente);
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

    private void ocultar() {
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        comboGarantias.setVisible(false);
        txtSuministros.setVisible(false);
        txtFallas.setVisible(false);
        txtSolucion.setVisible(false);
        jScrollPane1.setVisible(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        txtPanelObservacion.setVisible(false);

    }

    private void habilitar() {
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel12.setVisible(true);
        jLabel13.setVisible(true);
        jLabel14.setVisible(true);
        jLabel15.setVisible(true);
        jLabel16.setVisible(true);
        jLabel17.setVisible(true);
        jLabel18.setVisible(true);
        jLabel19.setVisible(true);
        jLabel20.setVisible(true);
        comboGarantias.setVisible(true);
        txtSuministros.setVisible(true);
        txtFallas.setVisible(true);
        txtSolucion.setVisible(true);
        jScrollPane1.setVisible(true);
        jScrollPane3.setVisible(true);
        jScrollPane4.setVisible(true);
        jScrollPane5.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        txtPanelObservacion.setVisible(true);
    }

    private void consultarCliente() {
        positivo = false;
        modelo.removeAllElements();
        try {
            if (consultar == null) {
                consultar = conexionEntrega.getConexion().prepareStatement("select idEquipo from clientes_servicios where idCliente=?");
            }
            consultar.setString(1, txtIdCliente.getText());
            ResultSet con = consultar.executeQuery();
            while (con.next()) {
                modelo.addElement(con.getString(1));
                jList1.setModel(modelo);
                positivo = true;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    private void cargarCliente() {
        try {
            if (cliente == null) {
                cliente = conexionEntrega.getConexion().prepareStatement("select nombre,telefono,direccion from clientes where idClientes=?");
            }
            cliente.setString(1, txtIdCliente.getText());
            ResultSet consulta = cliente.executeQuery();
            while (consulta.next()) {
                jLabel14.setText(consulta.getString(1));
                jLabel15.setText(consulta.getString(2));
                jLabel16.setText(consulta.getString(3));

            }
        } catch (Exception e) {
        }

    }

    private void buscarFecha() {
        try {
            if (fecha == null) {
                fecha = conexionEntrega.getConexion().prepareStatement("select now()");
            }
            ResultSet consulta = fecha.executeQuery();
            while (consulta.next()) {
                setFechaActual(consulta.getString(1));
            }
        } catch (Exception e) {
        }

    }

    // en la tabla clientes_servicios se deben guardar los datos  no actualizar en el boton de confirmar del frame entregas
    private void guardarEntrega() {
        entregar = null;
        try {
            if (entregar == null) {
                entregar = conexionEntrega.getConexion().prepareStatement("update clientes_servicios set fechaEntrega=?,fallaEncontrada=?, solucion=?,suministros=?,garantia=? where idEquipo=?");
                entregar.setInt(6, Integer.parseInt(jList1.getSelectedValue()));
                entregar.setString(1, fechaActual);
                entregar.setString(2, txtFallas.getText());
                entregar.setString(3, txtSolucion.getText());
                entregar.setString(4, txtSuministros.getText());
                entregar.setString(5, comboGarantias.getSelectedItem().toString());
                entregar.execute();
            }
        } catch (Exception e) {
        }

    }

    private void crearReporte() {
        JasperReport reporte;
        String ruta3 = "/reportes/entregaEquipos.jasper";
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("numCliente", txtIdCliente.getText());
            parametros.put("numServicio", numServicio);
            System.out.println("parametro numServicio: " + parametros.put("numServicio", numServicio));
//            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta3);
//            reporte = JasperCompileManager.compileReport(ruta3);
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta3));
            System.out.println("generando archivo desde: " + ruta3);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexionEntrega.getConexion());
            Exporter exportar = new JRPdfExporter();
            exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
            exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("formatoEntrega2.pdf"));
            exportar.exportReport();
            JasperViewer visualizar = new JasperViewer(jasperPrint, false);
            visualizar.setTitle("Formato Entrega De Equipos");
            visualizar.setVisible(true);
            visualizar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            System.out.println("Excepcion AL entregar: " + e.getMessage());
        }

    }

    private void crearReporte2() {
        JasperReport reporte;
        String ruta3 = "/reportes/SoporteDeEntrega.jasper";
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("numCliente", txtIdCliente.getText());
            parametros.put("numServicio", numServicio);
            System.out.println("parametro numServicio: " + parametros.put("numServicio", numServicio));
//            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta3);
//            reporte = JasperCompileManager.compileReport(ruta3);
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta3));
            System.out.println("generando archivo desde: " + ruta3);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexionEntrega.getConexion());
            Exporter exportar = new JRPdfExporter();
            exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
            exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("SoporteEntrega.pdf"));
            exportar.exportReport();
            JasperViewer visualizar = new JasperViewer(jasperPrint, false);
            visualizar.setTitle("Formato Entrega De Equipos");
            visualizar.setVisible(true);
            visualizar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            System.out.println("Excepcion AL entregar: " + e.getMessage());
        }

    }

    private void limpiarCampos() {

        txtIdCliente.setText("");
        txtPanelObservacion.setText("");
        txtFallas.setText("");
        txtSolucion.setText("");
        JFrame.setDefaultLookAndFeelDecorated(true);
//        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin"); 
    }

    private void autocompletar() {
        TextAutoCompleter textAut = new TextAutoCompleter(txtIdCliente);
        PreparedStatement completar = null;
        try {
            if (completar == null) {
                completar = conexionEntrega.getConexion().prepareStatement("select idClientes from clientes");
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

        jPanel1 = new javax.swing.JPanel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPanelObservacion = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtFallas = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSolucion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtSuministros = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        comboGarantias = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtIdCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 160, 30));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("N° Identificacion cliente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 170, 30));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 160, 30));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 30));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 200, 30));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 200, 30));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 200, 30));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 200, 30));

        txtPanelObservacion.setEditable(false);
        txtPanelObservacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPanelObservacion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(txtPanelObservacion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 290, 100));

        jList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jList1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 120, 190));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Referencia:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, 30));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Marca Y Modelo:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 140, 30));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Accesorios:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 140, 30));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Procesador:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 140, 30));

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Garantias: ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 90, 30));

        txtFallas.setColumns(20);
        txtFallas.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtFallas.setRows(5);
        txtFallas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtFallas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFallasKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtFallas);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 290, 100));

        txtSolucion.setColumns(20);
        txtSolucion.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtSolucion.setRows(5);
        txtSolucion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSolucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSolucionKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(txtSolucion);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, 290, 100));

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Problema Encontrado");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 170, 30));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel13.setText("Observacion Inicial");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 180, 30));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 200, 30));

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 200, 30));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 200, 30));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 290, 40));

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ok.png"))); // NOI18N
        jButton3.setText("Confirmar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 290, 40));

        txtSuministros.setColumns(20);
        txtSuministros.setRows(5);
        txtSuministros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSuministros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSuministrosKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(txtSuministros);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 290, 100));

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel17.setText("Solucion");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 180, 30));

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel18.setText("Suministros");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 180, 30));

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("RAM:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 140, 30));

        comboGarantias.setBackground(new java.awt.Color(204, 204, 255));
        comboGarantias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8 Dias", "30 Dias", "60 Dias" }));
        jPanel1.add(comboGarantias, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 160, 30));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pc1.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 90, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        consultarCliente();
        if (positivo == true) {
            ocultar();
            jList1.setVisible(true);
            jScrollPane2.setVisible(true);
            cargarCliente();
        } else {
            ocultar();
            jList1.setVisible(false);
            jScrollPane2.setVisible(false);
            JOptionPane.showMessageDialog(this, "Cliente No Encontrado\nverifique Su N° De Identificacion");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        try {
            jButton3.setEnabled(true);
            if (consultar1 == null) {
                consultar1 = conexionEntrega.getConexion().prepareStatement("select marca,modelo,accesorios,procesador,ram,observaciones from equipos where idEquipo=?");
            }
            consultar1.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            int numEquipo = Integer.parseInt(jList1.getSelectedValue());
            ResultSet consulta = consultar1.executeQuery();
            while (consulta.next()) {
                habilitar();
                jLabel2.setText(consulta.getString(1));
                jLabel3.setText(consulta.getString(2));
                jLabel4.setText(consulta.getString(3));
                jLabel5.setText(consulta.getString(4));
                jLabel6.setText(consulta.getString(5));
                txtPanelObservacion.setText(consulta.getString(6));
            }
        } catch (Exception e) {
        }
        try {
            PreparedStatement con = conexionEntrega.getConexion().prepareStatement("select fechaEntrega from clientes_servicios where fechaEntrega is not null and idEquipo=?");
            con.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet consult = con.executeQuery();
            while (consult.next()) {
                String nulo = consult.getString(1);
                System.out.println("nulo: " + nulo);
                if (!nulo.equals("null")) {
                    JOptionPane.showMessageDialog(this, "equipo ya fue Entregado al cliente");
                    jButton3.setEnabled(false);
                } else {
                    jButton3.setEnabled(true);
                }

            }
        } catch (Exception e) {
        }

        try {
            if (consultarServ == null) {
                consultarServ = conexionEntrega.getConexion().prepareStatement("select idServicio from clientes_servicios where idEquipo=?");
            }
            consultarServ.setInt(1, Integer.parseInt(jList1.getSelectedValue()));
            ResultSet consulta = consultarServ.executeQuery();
            while (consulta.next()) {
                setNumServicio(Integer.parseInt(consulta.getString(1)));
            }

            PreparedStatement con = conexionEntrega.getConexion().prepareStatement("select estado from remisionequipos where idServicio=?");
            con.setInt(1, numServicio);
            ResultSet c = con.executeQuery();
            while (c.next()) {
                estado = c.getString(1);
                if (estado.equals("true")) {
                    jButton3.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Equipo se encuentra remitido");
                } else {
                    jButton3.setEnabled(true);
                }
            }

        } catch (Exception e) {
            System.out.println("excepcion: " + e.getMessage());
        }


    }//GEN-LAST:event_jList1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (txtFallas.getText().equals("") || txtSolucion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por Favor complete la Informacion faltante");

        } else {
            buscarFecha();
            guardarEntrega();
            int imprimir = JOptionPane.showConfirmDialog(this, "Desea Imprimir el Formato?", "", JOptionPane.YES_NO_OPTION);
            if (imprimir == 0) {
                crearReporte();
                crearReporte2();
                acesores = new AcesoresDeVenta(conexionEntrega);
                acesores.setVisible(true);
                limpiarCampos();
                super.dispose();
            } else {
                acesores = new AcesoresDeVenta(conexionEntrega);
                acesores.setVisible(true);
                super.dispose();
                limpiarCampos();
            }

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        acesores = new AcesoresDeVenta(conexionEntrega);
        acesores.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtFallasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFallasKeyTyped
        txtFallas.setLineWrap(true);
        txtFallas.setWrapStyleWord(true);
    }//GEN-LAST:event_txtFallasKeyTyped

    private void txtSolucionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSolucionKeyTyped
        txtSolucion.setLineWrap(true);
        txtSolucion.setWrapStyleWord(true);

    }//GEN-LAST:event_txtSolucionKeyTyped

    private void txtSuministrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSuministrosKeyTyped
        txtSuministros.setLineWrap(true);
        txtSuministros.setWrapStyleWord(true);
    }//GEN-LAST:event_txtSuministrosKeyTyped

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
            java.util.logging.Logger.getLogger(Entregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Entregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Entregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboGarantias;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea txtFallas;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextPane txtPanelObservacion;
    private javax.swing.JTextArea txtSolucion;
    private javax.swing.JTextArea txtSuministros;
    // End of variables declaration//GEN-END:variables

}
