package Asesoresdeventa;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
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

public class RegistoEquipos extends javax.swing.JFrame {

    AcesoresDeVenta acesores;
    PreparedStatement consultarC, guardarC, guardarE, client_servic, buscarT, obtenerIdEquipo,
            obtenerservicio, equi_client, servicio, fechaInicial, completar;
    String idCliente, fecha, nom, tel, dir, id, correo;
    boolean encontrarCliente, encontrarTecnico;
    ConectarMySQL conecxionAMysql;
    int tipoUsuario, idEquipo, idServicio;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public RegistoEquipos(ConectarMySQL conexionBD) {
        conecxionAMysql = conexionBD;
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        bloquear();
        autocompletar();
        digitarNumeros(txtIdCliente);
        digitarNumeros1(txtTelefonoCliente);
        digitarNumeros2(txtIdTecnico);
        digitarNumeros3(txtIdTecnico);

//        btnDatosServicio.setEnabled(false);
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

    private void digitarNumeros3(JTextField b) {
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

    private void bloquear() {
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);

    }

    public void consultarCliente() {
        encontrarCliente = false;
        try {
            if (consultarC == null) {
                consultarC = conecxionAMysql.getConexion().prepareStatement("select idClientes,nombre,telefono,direccion,correo from clientes where idClientes=?");
            }
            consultarC.setString(1, txtIdCliente.getText());
            ResultSet consulta = consultarC.executeQuery();
            while (consulta.next()) {
                setId(consulta.getString(1));
                setNom(consulta.getString(2));
                setTel(consulta.getString(3));
                setDir(consulta.getString(4));
                setCorreo(consulta.getString(5));
                encontrarCliente = true;

            }

        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void GuardarCliente() {
        if (guardarC == null) {

            try {
                guardarC = conecxionAMysql.getConexion().prepareStatement("insert into clientes values(?,?,?,?,?)");
                guardarC.setString(1, txtIdCliente.getText());
                idCliente = txtIdCliente.getText();
                guardarC.setString(2, txtNombreCliente.getText());
                guardarC.setString(3, txtTelefonoCliente.getText());
                guardarC.setString(4, txtDireccionCliente.getText());
                guardarC.setString(5, txtCorreo.getText());
                guardarC.execute();
            } catch (SQLException ex) {
                ex.getMessage();
            }

        }
    }

    private void guardarEquipos() {
        try {
            if (guardarE == null) {
                guardarE = conecxionAMysql.getConexion().prepareStatement("insert into equipos(marca,modelo,accesorios,procesador,ram,observaciones) values(?,?,?,?,?,?)");
                guardarE.setString(1, txtReferencia.getText());
                guardarE.setString(2, txtMarcaYmod.getText());
                guardarE.setString(3, txtAccesorios.getText());
                guardarE.setString(4, txtProcesador.getText());
                guardarE.setString(5, txtRam.getText());
                guardarE.setString(6, txtObservaciones.getText());
                guardarE.execute();
                obtenerIdEquipo = conecxionAMysql.getConexion().prepareStatement("select last_insert_id()");
                ResultSet consulta = obtenerIdEquipo.executeQuery();
                while (consulta.next()) {
                    setIdEquipo(Integer.parseInt(consulta.getString(1)));
                }

            }
        } catch (Exception e) {
        }

    }

    //puedo ingresar en los equipos solo el id o referencia y luego en la entrega puedo actualizar los datos.
    private void guardarEquiposClientes() {
        try {
            if (equi_client == null) {
                equi_client = conecxionAMysql.getConexion().prepareStatement("insert into equipos_has_clientes values(?,?)");
                equi_client.setInt(1, idEquipo);
                equi_client.setString(2, idCliente);
                equi_client.execute();
            }
        } catch (Exception e) {
        }

    }

    private void buscarTecnico() {
        autocompletar();
        encontrarTecnico = false;
        try {
            if (buscarT == null) {
                buscarT = conecxionAMysql.getConexion().prepareStatement("select idTipousuario,nombre,telefono  from Usuarios where idUsuario=?");
            }
            buscarT.setString(1, txtIdTecnico.getText());
            ResultSet consulta = buscarT.executeQuery();
            while (consulta.next()) {
                setTipoUsuario(Integer.parseInt(consulta.getString(1)));
                txtNombreTecnico.setText(consulta.getString(2));
                txtTelefonoTecnico.setText(consulta.getString(3));
                encontrarTecnico = true;
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private void guardarServicio() {
        try {
            if (servicio == null) {
                servicio = conecxionAMysql.getConexion().prepareStatement("insert into servicios(tipoServicio,idTecnico) values(?,?)");
                servicio.setString(1, String.valueOf(comboServicios.getSelectedItem()));
                servicio.setString(2, txtIdTecnico.getText());
                servicio.execute();
                obtenerservicio = conecxionAMysql.getConexion().prepareStatement("select last_insert_id()");
                ResultSet consulta = obtenerservicio.executeQuery();
                while (consulta.next()) {
                    setIdServicio(Integer.parseInt(consulta.getString(1)));
                }
            }
        } catch (Exception e) {
        }

    }

    private void obtenerFecha() {
        try {
            if (fechaInicial == null) {
                fechaInicial = conecxionAMysql.getConexion().prepareStatement("select now()");
            }
            ResultSet consulta = fechaInicial.executeQuery();
            while (consulta.next()) {
                fecha = consulta.getString(1);
            }
        } catch (Exception e) {
        }
    }

    public void Clientes_servicios() {
        try {
            if (client_servic == null) {
                client_servic = conecxionAMysql.getConexion().prepareStatement("insert into clientes_servicios(idServicio,fechaRecibido,idEquipo,idCliente) values(?,?,?,?)");
                client_servic.setInt(1, idServicio);
                client_servic.setString(2, fecha);
                client_servic.setInt(3, idEquipo);
                client_servic.setString(4, idCliente);
                client_servic.execute();
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

//GTextField gTextField = new GTextField(300,80,true);
    private void crearReporte() {
        JasperReport informe;
        String url = "/reportes/informeDeRecibido.jasper";
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("numCliente", idCliente);
            param.put("numServicio", idServicio);
            System.out.println("numero del equipo: " + param.put("numCliente", idCliente));
            System.out.println("numero del Servicio: " + param.put("numServicio", idServicio));
            informe = (JasperReport) JRLoader.loadObject(getClass().getResource(url));
//            informe = (JasperReport) JRLoader.loadObjectFromFile(url); 
            JasperPrint jasperPrint = JasperFillManager.fillReport(informe, param, conecxionAMysql.getConexion());
            Exporter exportar = new JRPdfExporter();
            exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
            exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("formatoDe Recibido.pdf"));
            exportar.exportReport();
            JasperViewer visualizar = new JasperViewer(jasperPrint, false);
            visualizar.setTitle("Formato de Soporte Para el Cliente");
            visualizar.setVisible(true);
            visualizar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (JRException ex) {
            System.out.println("" + ex.getMessage());
        }

    }

    private void autocompletar() {
        TextAutoCompleter textAut = new TextAutoCompleter(txtIdTecnico);
        try {
            if (completar == null) {
                completar = conecxionAMysql.getConexion().prepareStatement("select idUsuario,nombre from usuarios where idTipousuario=2");
            }
            ResultSet consulta = completar.executeQuery();
            while (consulta.next()) {
                textAut.addItem(consulta.getString("idUsuario"));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        btnDatosCliente = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMarcaYmod = new javax.swing.JTextField();
        txtProcesador = new javax.swing.JTextField();
        txtReferencia = new javax.swing.JTextField();
        txtAccesorios = new javax.swing.JTextField();
        txtRam = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnDatosEquipo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNombreTecnico = new javax.swing.JTextField();
        txtIdTecnico = new javax.swing.JTextField();
        txtTelefonoTecnico = new javax.swing.JTextField();
        btnDatosServicio = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        comboServicios = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, 30));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("N° Identificacion:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 30));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Telefono:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 160, 30));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Direccion:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 160, 30));

        txtNombreCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 240, 30));

        txtIdCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jPanel2.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 240, 30));

        txtTelefonoCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtTelefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 240, 30));

        txtDireccionCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtDireccionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 240, 30));

        btnDatosCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnDatosCliente.setText("Siguiente");
        btnDatosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 220, 40));

        btnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnCancelar.setText("Volver");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 180, 40));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel5.setText("Correo Electronico:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 30));

        txtCorreo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jPanel2.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 240, 30));

        jTabbedPane1.addTab("Datos Del Cliente", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Modelo");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 110, -1));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Procesador");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 110, -1));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Marca");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 110, -1));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Accesorios");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 110, -1));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel10.setText("Ram");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 110, -1));

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel11.setText("Observaciones");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 110, -1));
        jPanel1.add(txtMarcaYmod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 240, 30));

        txtProcesador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProcesadorActionPerformed(evt);
            }
        });
        jPanel1.add(txtProcesador, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 240, 30));

        txtReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReferenciaActionPerformed(evt);
            }
        });
        jPanel1.add(txtReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 240, 30));
        jPanel1.add(txtAccesorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 240, 30));
        jPanel1.add(txtRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 240, 30));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 240, 100));

        btnDatosEquipo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnDatosEquipo.setText("Siguiente");
        btnDatosEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosEquipoActionPerformed(evt);
            }
        });
        jPanel1.add(btnDatosEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 400, 40));

        jTabbedPane1.addTab("Datos Del Equipo", jPanel1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Nombre");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 150, 30));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel13.setText("Tecnico Asignado");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 30));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel14.setText("Tipo De Servicio");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 150, 30));

        txtNombreTecnico.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jPanel5.add(txtNombreTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 210, 30));

        txtIdTecnico.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jPanel5.add(txtIdTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 210, 30));

        txtTelefonoTecnico.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jPanel5.add(txtTelefonoTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 210, 30));

        btnDatosServicio.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnDatosServicio.setText("Finalizar");
        btnDatosServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosServicioActionPerformed(evt);
            }
        });
        jPanel5.add(btnDatosServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 470, 40));

        btnBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 70, 30));

        comboServicios.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        comboServicios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Garantia De Equipos", "Servicio Tecnico De computadores", "Servicio Tecnico De Portatiles", "Servicio Tecnico De Impresoras", "Servicio Tecnico De Camaras De Seguridad" }));
        jPanel5.add(comboServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 300, -1));

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel15.setText("Telefono");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 150, 30));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel16.setText("Tipo de Servicio ");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 440, 30));

        jTabbedPane1.addTab("Servicios", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDireccionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionClienteActionPerformed

    private void txtTelefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoClienteActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void txtReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaActionPerformed

    private void txtProcesadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProcesadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProcesadorActionPerformed

    private void btnDatosServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosServicioActionPerformed
        if (txtNombreTecnico.getText().equals("") || txtIdTecnico.getText().equals("") || txtTelefonoTecnico.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe Completar Los Campos");

        } else {
            guardarServicio();
            obtenerFecha();
            Clientes_servicios();
            crearReporte();
            acesores = new AcesoresDeVenta(conecxionAMysql);
            acesores.setVisible(true);
            super.dispose();

        }
    }//GEN-LAST:event_btnDatosServicioActionPerformed

    private void btnDatosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosClienteActionPerformed
        if (txtNombreCliente.getText().equals("") || txtIdCliente.getText().equals("") || txtTelefonoCliente.getText().equals("") || txtDireccionCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe Completar Los Campos");
        } else {
            consultarCliente();
            if (encontrarCliente == true) {
                txtIdCliente.setText(id);
                txtNombreCliente.setText(nom);
                txtTelefonoCliente.setText(tel);
                txtDireccionCliente.setText(dir);
                txtCorreo.setText(correo);
                bloquear();
                int confirmar = JOptionPane.showConfirmDialog(this, "El Cliente Ya se Encuentra Almacenado\nDesea Añadir equipos A Su Lista.?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == 0) {
                    idCliente = txtIdCliente.getText();
                    jTabbedPane1.setSelectedIndex(1);
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(0, false);
                }
            } else {
                GuardarCliente();
                jTabbedPane1.setSelectedIndex(1);
                jTabbedPane1.setEnabledAt(0, false);
                jTabbedPane1.setEnabledAt(1, true);

            }

        }

    }//GEN-LAST:event_btnDatosClienteActionPerformed

    private void btnDatosEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosEquipoActionPerformed
        guardarEquipos();
        jTabbedPane1.setSelectedIndex(2);
        jTabbedPane1.setEnabledAt(2, true);
        jTabbedPane1.setEnabledAt(1, false);
        guardarEquiposClientes();


    }//GEN-LAST:event_btnDatosEquipoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        acesores = new AcesoresDeVenta(conecxionAMysql);
        acesores.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarTecnico();
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(RegistoEquipos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistoEquipos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistoEquipos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistoEquipos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDatosCliente;
    private javax.swing.JButton btnDatosEquipo;
    private javax.swing.JButton btnDatosServicio;
    private javax.swing.JComboBox<String> comboServicios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtAccesorios;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdTecnico;
    private javax.swing.JTextField txtMarcaYmod;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreTecnico;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtProcesador;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoTecnico;
    // End of variables declaration//GEN-END:variables
}
