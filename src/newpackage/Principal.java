package newpackage;

import Administrador.OpcionesAdministrador;
import Asesoresdeventa.RegistoEquipos;
import Asesoresdeventa.AcesoresDeVenta;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Principal extends javax.swing.JFrame {

    OpcionesAdministrador opcion;
    AcesoresDeVenta asesores;
    RegistoEquipos registro;
    public ConectarMySQL conectar;
    public static Principal p;
    boolean encontrarU = false;
    PreparedStatement consultarU = null, consultarTipoU = null;
    public String tipoUsuario, obtenerContraseña;
    int admin;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    public void setObtenerContraseña(String obtenerContraseña) {
        this.obtenerContraseña = obtenerContraseña;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public Principal() {

        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 777, 600);
        setResizable(false);
        conectarBD();
//        ImageIcon imgicon = new ImageIcon("src/imagenes/enova.jpg");
//        Icon icon = new ImageIcon(imgicon.getImage().getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_DEFAULT));
//        jLabel4.setIcon(icon);
        digitarNumeros(txtUsuario);
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

    public void conectarBD() {
        try {
            conectar = new ConectarMySQL("localhost", "enova", "root", ""); 
        } catch (Exception ex) {
            System.out.println("exepcion al inicio:  " + ex.getMessage());

        }

    }

    private void buscarUsuario() {
        encontrarU = false;
        try {
            consultarU = null;
            if (consultarU == null) {
                consultarU = conectar.getConexion().prepareStatement("select idUsuario,contraseña,idTipousuario from usuarios where idUsuario=?");
            }
            consultarU.setString(1, txtUsuario.getText());
            AcesoresDeVenta.setNumUsuario(txtUsuario.getText()); 
            ResultSet consulta = consultarU.executeQuery();
            while (consulta.next()) {
                encontrarU = true;
                setObtenerContraseña(consulta.getString(2));
                setAdmin(Integer.parseInt(consulta.getString(3)));
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    private void buscarTipoUsuario() {
        try {
            if (consultarTipoU == null) {
                consultarTipoU = conectar.getConexion().prepareStatement("select nombre from tipousuario where idTipoUsuario=?");
            }
            consultarTipoU.setInt(1, admin);
            ResultSet consulta = consultarTipoU.executeQuery();
            while (consulta.next()) {
                setTipoUsuario(consulta.getString(1));
            }
        } catch (Exception e) {
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        txtcontraseña = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel1.setText("Ingresados a Laboratorio");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 340, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/enova.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 380, 240));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel5.setText("Sistema de Registro de Equipos ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 310, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 570));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsuario.setBackground(new java.awt.Color(0, 153, 153));
        txtUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setBorder(null);
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 280, 40));

        txtcontraseña.setBackground(new java.awt.Color(0, 153, 153));
        txtcontraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcontraseña.setForeground(new java.awt.Color(255, 255, 255));
        txtcontraseña.setBorder(null);
        txtcontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraseñaActionPerformed(evt);
            }
        });
        jPanel2.add(txtcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 280, 40));

        btnIngresar.setBackground(new java.awt.Color(0, 153, 153));
        btnIngresar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 280, 40));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 280, 20));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 150, 30));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 90, 30));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 280, 20));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 280, 20));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 240, 20));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 180, 20));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 120, 20));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 90, 20));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 50, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 390, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        if (txtUsuario.getText().equals("") || txtcontraseña.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe digitar Usuario y Contraseña");
        } else {
            buscarUsuario();
            if (encontrarU == true && txtcontraseña.getText().equals(obtenerContraseña)) {
                buscarTipoUsuario();
                if (tipoUsuario.equalsIgnoreCase("admin")) {
                    opcion = new OpcionesAdministrador(conectar);
                    opcion.setVisible(true);
                    dispose();
                    txtUsuario.setText("");
                    txtcontraseña.setText("");
                } else {
                    if (tipoUsuario.equalsIgnoreCase("tecnico")) {
                        asesores = new AcesoresDeVenta(conectar);
                        asesores.setVisible(true);
                        dispose();
                        txtUsuario.setText("");
                        txtcontraseña.setText("");
                    } else {
                        if (tipoUsuario.equalsIgnoreCase("acesorventa")) {
                            asesores = new AcesoresDeVenta(conectar);
                            asesores.setVisible(true);
                            dispose();
                            txtUsuario.setText("");
                            txtcontraseña.setText("");
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuario O Contraseña Icorrectos");
            }

        }

    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained

    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost

    }//GEN-LAST:event_txtUsuarioFocusLost

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontraseñaActionPerformed

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
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//com.jtattoo.plaf.mint.MintLookAndFeel
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        JFrame.setDefaultLookAndFeelDecorated(true);
//      SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeCoffeeSkin"); 
        p = new Principal();
        p.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public static javax.swing.JTextField txtUsuario;
    private javax.swing.JPasswordField txtcontraseña;
    // End of variables declaration//GEN-END:variables
}
