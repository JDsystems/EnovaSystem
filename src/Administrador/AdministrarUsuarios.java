package Administrador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import newpackage.ConectarMySQL;

/**
 *
 * @author david
 */
public class AdministrarUsuarios extends javax.swing.JFrame {

    OpcionesAdministrador opcion;
    ActualizarUsuario actualizar;
    boolean encontrado;
    PreparedStatement consultarUser, guardarUsuario, borrarUsuario;
    ConectarMySQL conexion;

    public AdministrarUsuarios(ConectarMySQL conexion) {
        this.conexion = conexion;
        initComponents();
        ocultarTxt();
        ocultarLabel();
        setVisible(true);
        setBounds(180, 150, 600, 480);
        this.getContentPane().setBackground(Color.white);
        digitarNumeros(txtIdentificacion);
        digitarNumeros1(txtTelefono);
        comboTipoUsuario.setEditable(true);
 
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

    public void ocultarTxt() {
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        txtIdentificacion.setVisible(false);
        txtNombre.setVisible(false);
        txtTelefono.setVisible(false);
        txtDireccion.setVisible(false);
        txtContraseña.setVisible(false);
        comboTipoUsuario.setVisible(false);
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        btnBuscar.setVisible(false);
        btnEliminar.setVisible(false);
        btnActualizar.setVisible(false);

    }

    private void ocultarLabel() {
        lblIdentificacion.setVisible(false);
        lblNombre.setVisible(false);
        lblTelefono.setVisible(false);
        lblDireccion.setVisible(false);
        lblContraseña.setVisible(false);
    }

    private void borrar() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtContraseña.setText("");

    }

    public void consultarUsuario() {

        encontrado = false;
        consultarUser = null;
        try {
            if (consultarUser == null) {
                consultarUser = conexion.getConexion().prepareStatement("select idUsuario,nombre,telefono,direccion,contraseña,idTipoUsuario from usuarios where idUsuario=?");
            }
            consultarUser.setString(1, txtIdentificacion.getText());
            ResultSet consulta = consultarUser.executeQuery();
            while (consulta.next()) {
                txtIdentificacion.setText(consulta.getString(1));
                lblIdentificacion.setText(txtIdentificacion.getText());
                lblNombre.setText(consulta.getString(2));
                lblTelefono.setText(consulta.getString(3));
                lblDireccion.setText(consulta.getString(4));
                lblContraseña.setText(consulta.getString(5));
                comboTipoUsuario.setSelectedIndex(Integer.parseInt(consulta.getString(6)) - 1);
                encontrado = true;
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private void Eliminar() {
        consultarUsuario();
        if (encontrado == false) {
            JOptionPane.showMessageDialog(this, "El Usuario Que Desea Eliminar No Existe");

        } else {
            try {
                borrarUsuario = null;
                if (borrarUsuario == null) {
                    borrarUsuario = conexion.getConexion().prepareStatement("delete from usuarios where idUsuario=?");
                }
                borrarUsuario.setString(1, lblIdentificacion.getText());
                System.out.println(lblIdentificacion.getText());
                borrarUsuario.execute();
                JOptionPane.showMessageDialog(this, "Usuario Eliminado");
                borrar();

            } catch (Exception e) {
            }

        }

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        comboTipoUsuario = new javax.swing.JComboBox<>();
        txtContraseña = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        lblIdentificacion = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("Identificacion");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 140, -1));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 110, -1));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Telefono");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 120, -1));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Direccion");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 120, 40));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel5.setText("Contraseña");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 130, 40));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Perfil De Usuarios");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 160, -1));

        btnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 170, 30));

        btnAñadir.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        getContentPane().add(btnAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 170, -1));

        btnConsultar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 160, -1));

        btnEliminar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 160, 30));

        btnGuardar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 170, 30));

        btnVolver.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 170, -1));

        comboTipoUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        comboTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Tecnico", "Acesor De Venta" }));
        getContentPane().add(comboTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 270, 30));

        txtContraseña.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 270, 30));

        txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 270, 30));

        txtTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 270, 30));

        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 270, 30));

        txtIdentificacion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 270, 30));

        lblIdentificacion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblIdentificacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 300, 30));

        lblNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 300, 30));

        lblTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblTelefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 300, 30));

        lblDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblDireccion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 300, 30));

        lblContraseña.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblContraseña.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 300, 30));

        btnBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 270, 30));

        btnActualizar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 170, 30));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel8.setText("Administracion de Usuarios");
        jPanel1.add(jLabel8);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        txtIdentificacion.setVisible(true);
        txtNombre.setVisible(true);
        txtTelefono.setVisible(true);
        txtDireccion.setVisible(true);
        txtContraseña.setVisible(true);
        comboTipoUsuario.setVisible(true);
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        btnAñadir.setVisible(false);
        btnConsultar.setVisible(false);
        btnVolver.setVisible(false);
        comboTipoUsuario.setEditable(true); 
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        btnAñadir.setVisible(true);
        btnConsultar.setVisible(true);
        btnVolver.setVisible(true);
        comboTipoUsuario.setEnabled(true); 
        ocultarTxt();
        ocultarLabel();
        borrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        jLabel1.setVisible(true);
        txtIdentificacion.setVisible(true);
        btnBuscar.setVisible(true);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        consultarUsuario();
        if (encontrado == false) {
            txtIdentificacion.setText("");
            JOptionPane.showMessageDialog(this, "Usuario No Encontrado");
            txtIdentificacion.grabFocus();

        } else {
            btnBuscar.setVisible(false);
            btnGuardar.setVisible(false);
            jLabel2.setVisible(true);
            txtIdentificacion.setVisible(false);
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
            lblIdentificacion.setVisible(true);
            lblNombre.setVisible(true);
            lblTelefono.setVisible(true);
            lblDireccion.setVisible(true);
            lblContraseña.setVisible(true);
            comboTipoUsuario.setVisible(true);
            btnGuardar.setVisible(false);
            btnCancelar.setVisible(true);
            btnAñadir.setVisible(false);
            btnConsultar.setVisible(false);
            btnEliminar.setVisible(true);
            btnActualizar.setVisible(true);
            btnVolver.setVisible(false);
            comboTipoUsuario.setEnabled(false);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtIdentificacion.getText().equals("") || txtNombre.getText().equals("") || txtTelefono.getText().equals("") || txtDireccion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar Todos Los Datos Solicitados");

        } else {

            consultarUsuario();
            try {
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(this, "Usuario Ya Esta Registrado");
                    borrar();
                } else {
                    if (guardarUsuario == null) {
                        guardarUsuario = conexion.getConexion().prepareStatement("insert into usuarios values(?,?,?,?,?,?)");
                    }
                    guardarUsuario.setString(1, txtIdentificacion.getText());
                    guardarUsuario.setString(2, txtNombre.getText());
                    guardarUsuario.setString(3, txtTelefono.getText());
                    guardarUsuario.setString(4, txtDireccion.getText());
                    guardarUsuario.setString(5, txtContraseña.getText());
                    guardarUsuario.setInt(6, comboTipoUsuario.getSelectedIndex() + 1);
                    guardarUsuario.execute();
                    JOptionPane.showMessageDialog(this, "Registro Exitoso");
                    borrar();

                }

            } catch (Exception e) {
                System.out.println(""+e.getMessage()); 
            }

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar = new ActualizarUsuario(this.conexion);
        actualizar.setVisible(true);
        ActualizarUsuario.txtIdentificacion.setText(lblIdentificacion.getText());
        ActualizarUsuario.txtNombre.setText(lblNombre.getText());
        ActualizarUsuario.txtTelefono.setText(lblTelefono.getText());
        ActualizarUsuario.txtDireccion.setText(lblDireccion.getText());
        ActualizarUsuario.txtContraseña.setText(lblContraseña.getText());
        ActualizarUsuario.comboTipoUsuario.setSelectedIndex(comboTipoUsuario.getSelectedIndex());
        ActualizarUsuario.txtNuevaContraseña.grabFocus();
        dispose();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int confirmar = JOptionPane.showConfirmDialog(this, "Seguro Que desea Eliminar Este Usuario?", "", JOptionPane.YES_NO_OPTION);
        if (confirmar == 0) {
            Eliminar();
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        opcion = new OpcionesAdministrador(conexion);
        opcion.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
