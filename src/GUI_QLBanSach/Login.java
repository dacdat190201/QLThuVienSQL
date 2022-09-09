/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import connect_database.DataValidator;
import connect_database.MessageDialog;
import dao.NhanVien_DAO;
import doi_tuong.NhanVien;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Login extends javax.swing.JFrame {

    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;   
    
    private String tenNV = "";
    private String maNV = "";

    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("LOGIN");
        

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\Study\\Java Technology\\2_Exersice\\DoAnJava\\Image\\l.png")); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(120, 140, 30, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Study\\Java Technology\\2_Exersice\\DoAnJava\\Image\\m.png")); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(120, 70, 30, 40);

        txtTaiKhoan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txtTaiKhoan);
        txtTaiKhoan.setBounds(180, 72, 330, 30);

        btnLogin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/log-in.png"))); // NOI18N
        btnLogin.setText("   LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin);
        btnLogin.setBounds(270, 220, 160, 42);

        txtMatKhau.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txtMatKhau);
        txtMatKhau.setBounds(180, 140, 330, 30);

        jLabel1.setBackground(new java.awt.Color(201, 201, 201));
        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Study\\Java Technology\\2_Exersice\\DoAnJava\\Image\\3600fc867e1d667c83c30f9ed1ee923f.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 620, 420);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        DataValidator.validatorEmpty(txtTaiKhoan, sb, "Tên đăng nhập không được bỏ trống");
        DataValidator.validatorEmpty(txtMatKhau, sb, "Mật khẩu không được bỏ trống");
        if (sb.length() > 0 ){
            MessageDialog.showMessageDialog(this, sb.toString(), "Cảnh báo");
        }
        NhanVien_DAO dao = new NhanVien_DAO();
        try {
            NhanVien nv = dao.getTenNhanVien(txtTaiKhoan.getText(), txtMatKhau.getText());
            if (nv == null){
                MessageDialog.showMessageDialog(this, "Tài khoản hoặc mật khẩu đăng nhập sai !", "Thông báo");
            }
            else {
                maNV = nv.getMaNV();
                tenNV = nv.getTenNV();
                Home home = new Home(tenNV, maNV);
                home.setVisible(true);
                this.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }            
    }//GEN-LAST:event_btnLoginActionPerformed

 
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables

}
