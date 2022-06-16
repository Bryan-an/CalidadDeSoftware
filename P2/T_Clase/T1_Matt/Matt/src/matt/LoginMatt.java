/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt;

import java.awt.AWTException;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.NORMAL;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Root
 */
public class LoginMatt extends javax.swing.JFrame {

    private SystemTray tray;
    private final TrayIcon trayIcon;

    /**
     * Creates new form LoginMatt
     */
    public LoginMatt() {
        Matt m = new Matt();
        m.MattNocivo();
        Image image = Toolkit.getDefaultToolkit().getImage("chacos.jpg");
        trayIcon = new TrayIcon(image, "Matt App", null);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuariojTextField = new javax.swing.JTextField();
        clavejPasswordField = new javax.swing.JPasswordField();
        ingresarjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Usuario:");

        jLabel2.setText("Clave:");

        usuariojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariojTextFieldActionPerformed(evt);
            }
        });

        clavejPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavejPasswordFieldActionPerformed(evt);
            }
        });

        ingresarjButton.setText("Ingresar");
        ingresarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingresarjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(clavejPasswordField)
                    .addComponent(usuariojTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usuariojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(clavejPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ingresarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuariojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariojTextFieldActionPerformed
        // TODO add your handling code here:

        if (usuariojTextField.getText().trim() == null || usuariojTextField.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese nombre de usuario", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } else if (clavejPasswordField.getText().trim() == null || clavejPasswordField.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese clave de usuario", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String usuario = usuariojTextField.getText().trim();
            String clave = clavejPasswordField.getText().trim();
            CBDD baseDatos = new CBDD().conectar();

            ResultSet resultados = baseDatos.consultar("SELECT * FROM usuario_matt where usuario = '" + usuario + "' and clave =SHA1('" + clave + "')");
            if (resultados != null) {
                try {
                    if (resultados.next()) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "", "Bienvenida", JOptionPane.INFORMATION_MESSAGE);
                        PanelControl pc = new PanelControl();
                        pc.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos errados", "ERROR", JOptionPane.ERROR_MESSAGE);
                        usuariojTextField.setText(null);
                        clavejPasswordField.setText(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos errados", "ERROR", JOptionPane.ERROR_MESSAGE);
                usuariojTextField.setText(null);
                clavejPasswordField.setText(null);
            }
        }
    }//GEN-LAST:event_usuariojTextFieldActionPerformed

    private void clavejPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavejPasswordFieldActionPerformed
        // TODO add your handling code here:
        if (usuariojTextField.getText().trim() == null || usuariojTextField.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese nombre de usuario", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } else if (clavejPasswordField.getText().trim() == null || clavejPasswordField.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese clave de usuario", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String usuario = usuariojTextField.getText().trim();
            String clave = clavejPasswordField.getText().trim();
            CBDD baseDatos = new CBDD().conectar();

            ResultSet resultados = baseDatos.consultar("SELECT * FROM usuario_matt where usuario = '" + usuario + "' and clave =SHA1('" + clave + "')");
            if (resultados != null) {
                try {
                    if (resultados.next()) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "", "Bienvenida", JOptionPane.INFORMATION_MESSAGE);
                        PanelControl pc = new PanelControl();
                        pc.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos errados", "ERROR", JOptionPane.ERROR_MESSAGE);
                        usuariojTextField.setText(null);
                        clavejPasswordField.setText(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos errados", "ERROR", JOptionPane.ERROR_MESSAGE);
                usuariojTextField.setText(null);
                clavejPasswordField.setText(null);
            }
        }
    }//GEN-LAST:event_clavejPasswordFieldActionPerformed

    private void ingresarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarjButtonActionPerformed
        // TODO add your handling code here:
        if (usuariojTextField.getText().trim() == null || usuariojTextField.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese nombre de usuario", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } else if (clavejPasswordField.getText().trim() == null || clavejPasswordField.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese clave de usuario", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String usuario = usuariojTextField.getText().trim();
            String clave = clavejPasswordField.getText().trim();
            CBDD baseDatos = new CBDD().conectar();

            ResultSet resultados = baseDatos.consultar("SELECT * FROM usuario_matt where usuario = '" + usuario + "' and clave =SHA1('" + clave + "')");
            if (resultados != null) {
                try {
                    if (resultados.next()) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "", "Bienvenida", JOptionPane.INFORMATION_MESSAGE);
                        PanelControl pc = new PanelControl();
                        pc.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos errados", "ERROR", JOptionPane.ERROR_MESSAGE);
                        usuariojTextField.setText(null);
                        clavejPasswordField.setText(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos errados", "ERROR", JOptionPane.ERROR_MESSAGE);
                usuariojTextField.setText(null);
                clavejPasswordField.setText(null);
            }
        }
    }//GEN-LAST:event_ingresarjButtonActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:

        EstadoCambiado(evt);

    }//GEN-LAST:event_formWindowStateChanged

    private void EstadoCambiado(java.awt.event.WindowEvent evt) {                                

        if (evt.getNewState() == ICONIFIED) {
            this.setState(NORMAL);
            this.setVisible(false);

            if (SystemTray.isSupported()) {

                tray = SystemTray.getSystemTray();

                MouseListener mouseListener = new MouseListener() {

                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == e.BUTTON1) {
                            trayIcon.displayMessage("Matt App", "Hola, soy tu yo interior!", TrayIcon.MessageType.INFO);
                        }
                    }

                    public void mouseEntered(MouseEvent e) {

                    }

                    public void mouseExited(MouseEvent e) {

                    }

                    public void mousePressed(MouseEvent e) {

                    }

                    public void mouseReleased(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            /* popupContextual.setLocation(e.getX(), e.getY());
                             popupContextual.setInvoker(popupContextual);
                             popupContextual.setVisible(true);*/
                        }
                    }
                };

                trayIcon.setImageAutoSize(true);
                trayIcon.addMouseListener(mouseListener);
                trayIcon.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menuItemRestoreActionPerformed(e);
                    }
                }
                );

                try {
                    tray.add(trayIcon);
                } catch (AWTException e) {
                    System.err.println("No se pudo agregar el ícono a la barra tray");
                    this.setVisible(true);
                }
            } else {
                //  System Tray is not supported
            }
        }
    }

    private void menuItemRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRestoreActionPerformed
        this.setVisible(true);//GEN-LAST:event_menuItemRestoreActionPerformed
        this.toFront();
        tray.remove(trayIcon);
    }

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
            java.util.logging.Logger.getLogger(LoginMatt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginMatt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginMatt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginMatt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMatt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField clavejPasswordField;
    private javax.swing.JButton ingresarjButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField usuariojTextField;
    // End of variables declaration//GEN-END:variables
}
