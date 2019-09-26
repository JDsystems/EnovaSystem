package Asesoresdeventa;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import newpackage.ConectarMySQL;
//3813222-1517-1518-1112

public class AdministrarClientes extends javax.swing.JFrame {

    AcesoresDeVenta acces;
    ConectarMySQL conexionAdminCliente;
    PreparedStatement consultar, actualizar;
    boolean existe;

    public AdministrarClientes(ConectarMySQL conexionBD) {
        conexionAdminCliente = conexionBD;
        initComponents();
        buttonGroup1.add(rdActualizar);
        setBounds(180, 100, 455, 590);
        this.getContentPane().setBackground(Color.white);
        ocultar1();
        ocultar2();
        rdActualizar.setVisible(false);
        autocompletar();
    }

    @SuppressWarnings("unchecked")
    private void consultar() {
        existe = false;
        try {
            if (consultar == null) {
                consultar = conexionAdminCliente.getConexion().prepareStatement("select nombre, telefono,direccion,correo from clientes where idClientes=?");
            }
            consultar.setString(1, txtNumCliente.getText());
            ResultSet consulta = consultar.executeQuery();
            while (consulta.next()) {
                existe = true;
                lblNombre.setText(consulta.getString(1));
                lblTelefono.setText(consulta.getString(2));
                lblDireccion.setText(consulta.getString(3));
                lblCorreo.setText(consulta.getString(4));
            }
        } catch (Exception e) {
        }
    }

    private void actualizarCliente() {
        actualizar = null;
        try {
            if (actualizar == null) {
                actualizar = conexionAdminCliente.getConexion().prepareStatement("update clientes set  nombre=?, telefono=?, direccion=?, correo=? where idClientes=?");

                actualizar.setString(5, txtNumCliente.getText());
                actualizar.setString(1, txtNombre.getText());
                actualizar.setString(2, txtTelefono.getText());
                actualizar.setString(3, txtDireccion.getText());
                actualizar.setString(4, txtCorreo.getText());
                actualizar.execute();
                JOptionPane.showMessageDialog(this, "Cliente Actualizado");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void ocultar1() {
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        lblNombre.setVisible(false);
        lblTelefono.setVisible(false);
        lblDireccion.setVisible(false);
        lblCorreo.setVisible(false);

    }

    private void ocultar2() {
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        txtNombre.setVisible(false);
        txtTelefono.setVisible(false);
        txtDireccion.setVisible(false);
        txtCorreo.setVisible(false);
        btnConfirmar.setVisible(false);

    }

    private void habilitar1() {
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        lblNombre.setVisible(true);
        lblTelefono.setVisible(true);
        lblDireccion.setVisible(true);
        lblCorreo.setVisible(true); 

    }

    private void habilitar2() {
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel12.setVisible(true);
        jLabel13.setVisible(true);
        txtNombre.setVisible(true);
        txtTelefono.setVisible(true);
        txtDireccion.setVisible(true);
        txtCorreo.setVisible(true); 
        btnConfirmar.setVisible(true);

    }

    private void autocompletar() {
        TextAutoCompleter textAut = new TextAutoCompleter(txtNumCliente);
        PreparedStatement completar = null;
        try {
            if (completar == null) {
                completar = conexionAdminCliente.getConexion().prepareStatement("select idClientes from clientes");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtNumCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        rdActualizar = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNumCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtNumCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtNumCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 167, 30));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("N° Cliente:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 30));

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 126, -1));

        rdActualizar.setBackground(new java.awt.Color(255, 255, 255));
        rdActualizar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        rdActualizar.setText("Actualizar");
        rdActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdActualizarMouseClicked(evt);
            }
        });
        getContentPane().add(rdActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 120, -1));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Telefono");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, -1));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel5.setText("Correo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 120, 30));

        lblNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 260, 30));

        lblTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 260, 30));

        lblDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 260, 30));

        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 270, 30));

        txtTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 270, 30));

        txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 270, 30));

        btnConfirmar.setBackground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 400, 30));

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Correo");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 130, 30));

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel11.setText("Telefono");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 130, 30));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel10.setText("Nombre");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 130, 30));

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 30));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel13.setText("Direccion");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 130, 30));
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 270, 30));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Direccion");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 120, -1));

        lblCorreo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        getContentPane().add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 260, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed

    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtNumClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumClienteActionPerformed

    }//GEN-LAST:event_txtNumClienteActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed

    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed

    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtNumCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite El Numero Del Cliente");
        } else {
            consultar();
            if (existe == false) {
                ocultar1();
                ocultar2();
                rdActualizar.setVisible(false);
                JOptionPane.showMessageDialog(this, "Cliente No Existe");

            } else {
                habilitar1();
                rdActualizar.setVisible(true);
                ocultar2();
                buttonGroup1.clearSelection();
                jLabel3.setEnabled(true);
                jLabel4.setEnabled(true);
                jLabel5.setEnabled(true);
                jLabel6.setEnabled(true); 
                lblNombre.setEnabled(true);
                lblTelefono.setEnabled(true);
                lblDireccion.setEnabled(true);
                lblCorreo.setEnabled(true);  
                btnConfirmar.setEnabled(true);
            }

        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void rdActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdActualizarMouseClicked
        habilitar2();
        jLabel3.setEnabled(false);
        jLabel4.setEnabled(false);
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        lblNombre.setEnabled(false);
        lblTelefono.setEnabled(false);
        lblDireccion.setEnabled(false);
        lblCorreo.setEnabled(false); 
        txtNombre.setText(lblNombre.getText());
        txtTelefono.setText(lblTelefono.getText());
        txtDireccion.setText(lblDireccion.getText());
        txtCorreo.setText(lblCorreo.getText());
        txtNombre.grabFocus();
    }//GEN-LAST:event_rdActualizarMouseClicked

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        actualizarCliente();
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText(""); 
        btnConfirmar.setEnabled(false);
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        acces = new AcesoresDeVenta(conexionAdminCliente);
        acces.setVisible(true);
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
            java.util.logging.Logger.getLogger(AdministrarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JRadioButton rdActualizar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumCliente;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
