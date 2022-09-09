/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DialogMaKH extends javax.swing.JDialog {

    private String sdt;
    private double giamGia;
    
    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null; 
    
    public DialogMaKH(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Nhập mã khách hàng");
        this.setLocationRelativeTo(null);
        
        connectData();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtMaKHTV = new javax.swing.JTextField();
        btnXacNhan = new javax.swing.JButton();
        btnXacNhan1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel1.setText("Nhập số điện thoại");

        txtMaKHTV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnXacNhan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xn.png"))); // NOI18N
        btnXacNhan.setText("XÁC NHẬN");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnXacNhan1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXacNhan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXacNhan1.setText("HỦY");
        btnXacNhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaKHTV, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnXacNhan1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKHTV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacNhan1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        boolean kt = false;
        if (txtMaKHTV.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "Hãy nhập thông tin trước khi xác nhận !", "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
            txtMaKHTV.requestFocus();
        } else {
            sdt = txtMaKHTV.getText();
            String tinhTrang = "";
            try {
                String sqlSL = "select *from KhachHang where  sdt = ?";
                preparedStatement = connect.prepareStatement(sqlSL);
                preparedStatement.setString(1, sdt);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {                
                    tinhTrang = resultSet.getString("tinhTrang");
                    kt = true;
                }
                if (kt){
                    if (tinhTrang.equals("Thành viên Vip")){
                        giamGia = 0.03;
                    } else {
                        giamGia = 1.0;
                    }
                    dispose();                    
                } else {
                    JOptionPane.showMessageDialog(this, "Không có khách hàng nào có số [ " + txtMaKHTV.getText() + " ] trong cửa hàng",
                            "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    txtMaKHTV.selectAll();
                    txtMaKHTV.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnXacNhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhan1ActionPerformed
        // TODO add your handling code here:
        txtMaKHTV.setText("");
        this.dispose();
    }//GEN-LAST:event_btnXacNhan1ActionPerformed

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
            java.util.logging.Logger.getLogger(DialogMaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogMaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogMaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogMaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogMaKH dialog = new DialogMaKH(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXacNhan1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtMaKHTV;
    // End of variables declaration//GEN-END:variables


    public String getSdt() {
        return sdt;
    }
    
    public double getGiamGia() {
        return giamGia;
    }    

    private void connectData() {
        //Khai báo các biến thao tác vơi csdl
        String strServerName = "DACTHANH";
        String strDBName = "QL_BANSACH";
        String strUserName = "sa";
        String strPassword = "123";
        
        //---Kết nối
        try {
            //Đăng kí csdl
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Đường dẫn
            String connURL = "jdbc:sqlserver://" + strServerName +
                    ":1433; databaseName = " + strDBName + ";user = " + strUserName + ";password = " + strPassword  ;
            //--
            connect = DriverManager.getConnection(connURL);
            
            //-----------------------------------------
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
