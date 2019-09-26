package Administrador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import newpackage.ConectarMySQL;

public class ActualizarUsuario extends javax.swing.JFrame {

    AdministrarUsuarios a;
    PreparedStatement actualizarUsuario, buscar;
    private ConectarMySQL conexion;
    String obtenerPassword;

    public void setObtenerPassword(String obtenerPassword) {
        this.obtenerPassword = obtenerPassword;
    }

    public ActualizarUsuario(ConectarMySQL conexion) {
        this.conexion = conexion;
        initComponents();
        txtIdentificacion.setToolTipText("este campo no puede ser modificado");
         this.getContentPane().setBackground(Color.white);
        setVisible(true);
        setBounds(280, 100, 575, 530);
        digitarNumeros(txtTelefono); 

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

    private void buscarPassword() {
        try {
            if (buscar == null) {
                buscar = conexion.getConexion().prepareStatement("select contraseña from usuarios where idUsuario=?");
            }
            buscar.setString(1, txtIdentificacion.getText());
            ResultSet consulta = buscar.executeQuery();
            while (consulta.next()) {

                setObtenerPassword(consulta.getString(1));
            }
        } catch (Exception e) {
        }

    }

    private void actualizar() {
        buscarPassword();
        if (!obtenerPassword.equals(txtContraseña.getText())) {
            JOptionPane.showMessageDialog(this, "Su Contraseña Actual Es Incorrecta");

        } else {
            try {
                if (actualizarUsuario == null) {
                    actualizarUsuario = conexion.getConexion().prepareStatement("update usuarios set idUsuario=?, nombre=?,telefono=?,direccion=?,contraseña=?,idTipoUsuario=? where idUsuario=?");
                    actualizarUsuario.setString(7, txtIdentificacion.getText());
                    actualizarUsuario.setString(1, txtIdentificacion.getText());
                    actualizarUsuario.setString(2, txtNombre.getText());
                    actualizarUsuario.setString(3, txtTelefono.getText());
                    actualizarUsuario.setString(4, txtDireccion.getText());
                    actualizarUsuario.setString(5, txtNuevaContraseña.getText());
                    actualizarUsuario.setInt(6, comboTipoUsuario.getSelectedIndex() + 1);
                    actualizarUsuario.execute();
                    JOptionPane.showMessageDialog(this, "Usuario Actualizado con Exito");
                }

            } catch (Exception e) {
                e.getMessage();
            }

        }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnGuardarCambios = new javax.swing.JButton();
        comboTipoUsuario = new javax.swing.JComboBox<>();
        txtNuevaContraseña = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("Identificacion-Usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 240, 30));

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 160, 30));

        jLabel3.setBackground(new java.awt.Color(0, 153, 153));
        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Telefono");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 170, 30));

        jLabel4.setBackground(new java.awt.Color(0, 153, 153));
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Direccion");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 170, 30));

        jLabel5.setBackground(new java.awt.Color(0, 153, 153));
        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel5.setText("Contraseña Actual");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 220, 30));

        jLabel8.setBackground(new java.awt.Color(0, 153, 153));
        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Nueva Contraseña");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 160, 30));

        jLabel6.setBackground(new java.awt.Color(0, 153, 153));
        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Perfil De Usuario");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 210, -1));

        btnAtras.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        btnAtras.setText("Cancelar");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 230, 40));

        btnGuardarCambios.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnGuardarCambios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ok.png"))); // NOI18N
        btnGuardarCambios.setText("Guardar Cambios");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarCambios, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 230, 40));

        comboTipoUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        comboTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Tecnico", "Acesor De Venta" }));
        getContentPane().add(comboTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 230, 30));

        txtNuevaContraseña.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtNuevaContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 230, 30));

        txtContraseña.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 230, 30));

        txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 230, 30));

        txtTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 230, 30));

        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 230, 30));

        txtIdentificacion.setEditable(false);
        txtIdentificacion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtIdentificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdentificacionMouseClicked(evt);
            }
        });
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 230, 30));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel7.setText("Actualizacion de Datos de Usuarios");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 580, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed

    }//GEN-LAST:event_txtDireccionActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        if (txtNuevaContraseña.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar su Nueva Contraseña");

        } else {
            actualizar();
            a = new AdministrarUsuarios(conexion);
            a.setVisible(true);
            super.dispose();
        }

    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        a = new AdministrarUsuarios(conexion);
        a.setVisible(true);
        super.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtIdentificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdentificacionMouseClicked

    }//GEN-LAST:event_txtIdentificacionMouseClicked

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardarCambios;
    public static javax.swing.JComboBox<String> comboTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txtContraseña;
    public static javax.swing.JTextField txtDireccion;
    public static javax.swing.JTextField txtIdentificacion;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtNuevaContraseña;
    public static javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
