/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asesoresdeventa;

import Administrador.OpcionesAdministrador;
import java.awt.Color;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import newpackage.ConectarMySQL;
import newpackage.Principal;

/**
 *
 * @author david
 */
public class AcesoresDeVenta extends javax.swing.JFrame {

    AdministrarClientes admin;
    OpcionesAdministrador opciones;
    Principal p;
    Entregas entregas;
    Remisiones remitir;
    RegistoEquipos registro;
    ConectarMySQL conexionBD;
    PreparedStatement consulta = null;
    public static String numUsuario;
    int idUsuario;

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public static void setNumUsuario(String numUsuario) {
        AcesoresDeVenta.numUsuario = numUsuario;
    }

    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    public AcesoresDeVenta(ConectarMySQL conexion) {
        conexionBD = conexion;
        initComponents();
        setVisible(true);
        setBounds(250, 150, 680, 490); 
        this.getContentPane().setBackground(Color.white);
    }
    private void consultarInicioSesion() {
        String sql = "select idTipousuario from usuarios where idUsuario=?";
        try {
            if (consulta == null) {
                consulta = conexionBD.getConexion().prepareStatement(sql);
            }
            consulta.setString(1, numUsuario);
            ResultSet con = consulta.executeQuery();
            while (con.next()) {
                setIdUsuario(Integer.parseInt(con.getString(1)));
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegistroEquipos = new javax.swing.JButton();
        btnRemisiones = new javax.swing.JButton();
        btnEntregas = new javax.swing.JButton();
        btnAdminClientes = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistroEquipos.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRegistroEquipos.setText("Registro De Equipos");
        btnRegistroEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroEquiposActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistroEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 40));

        btnRemisiones.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRemisiones.setText("Remisiones");
        btnRemisiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemisionesActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemisiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 250, 40));

        btnEntregas.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnEntregas.setText("Entregas");
        btnEntregas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregasActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntregas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 250, 40));

        btnAdminClientes.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnAdminClientes.setText("Administrar Clientes");
        btnAdminClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdminClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 40));

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jButton1.setText("Lista de Clientes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 250, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/enova.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 380, -1));

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.png"))); // NOI18N
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 250, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pc.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 260, 230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistroEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroEquiposActionPerformed
        registro = new RegistoEquipos(conexionBD);
        registro.setVisible(true);
        registro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.dispose();
    }//GEN-LAST:event_btnRegistroEquiposActionPerformed

    private void btnRemisionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemisionesActionPerformed
        remitir = new Remisiones(conexionBD);
        remitir.setVisible(true);
        super.dispose();

    }//GEN-LAST:event_btnRemisionesActionPerformed

    private void btnEntregasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregasActionPerformed
        entregas = new Entregas(conexionBD);
        entregas.setVisible(true);
        entregas.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.dispose();

    }//GEN-LAST:event_btnEntregasActionPerformed

    private void btnAdminClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminClientesActionPerformed
        admin = new AdministrarClientes(conexionBD);
        admin.setVisible(true);
        super.dispose();
    }//GEN-LAST:event_btnAdminClientesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TablaClientes l = new TablaClientes(conexionBD);
        l.setVisible(true);
        super.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        consultarInicioSesion();
        if (idUsuario == 2 || idUsuario == 3) {
            p = new Principal();
            p.setVisible(true);
            super.dispose();
        } else {
            if (idUsuario == 1) {
                opciones = new OpcionesAdministrador(conexionBD);
                opciones.setVisible(true);
                super.dispose();
            }
        }
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(AcesoresDeVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcesoresDeVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcesoresDeVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcesoresDeVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAdminClientes;
    private javax.swing.JButton btnEntregas;
    private javax.swing.JButton btnRegistroEquipos;
    private javax.swing.JButton btnRemisiones;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
