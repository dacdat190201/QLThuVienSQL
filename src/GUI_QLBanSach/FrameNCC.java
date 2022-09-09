/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import dao.NhaCungCap_DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class FrameNCC extends javax.swing.JDialog {

    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null; 
    DefaultTableModel dtmNCC;
    
    public FrameNCC(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Xóa nhà cung cấp");
        this.setSize(950, 520);
        this.setLocationRelativeTo(null);
        
        connectData();
        dtmNCC = (DefaultTableModel)tblNCC.getModel();
        hienThiDL();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        txtTenNCC = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        btnXoaNCC = new javax.swing.JButton();
        btnHuyNCC = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin nhà cung cấp", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Tên nhà cung cấp:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 82, 133, 27);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Mã nhà cung cấp:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 37, 133, 27);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Số điện thoại");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(400, 40, 108, 27);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Email:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(400, 80, 45, 27);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Địa chỉ:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, 127, 100, 27);

        txtMaNCC.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel1.add(txtMaNCC);
        txtMaNCC.setBounds(168, 39, 210, 30);

        txtTenNCC.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel1.add(txtTenNCC);
        txtTenNCC.setBounds(168, 84, 210, 30);

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel1.add(txtDiaChi);
        txtDiaChi.setBounds(168, 129, 550, 30);

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel1.add(txtSDT);
        txtSDT.setBounds(520, 40, 195, 30);

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel1.add(txtEmail);
        txtEmail.setBounds(520, 80, 195, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(29, 275, 730, 170);

        tblNCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNCC);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(29, 79, 780, 183);

        btnXoaNCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXoaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXoaNCC.setText("XÓA");
        btnXoaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNCCActionPerformed(evt);
            }
        });
        getContentPane().add(btnXoaNCC);
        btnXoaNCC.setBounds(770, 320, 100, 40);

        btnHuyNCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHuyNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnHuyNCC.setText("HỦY");
        btnHuyNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyNCCActionPerformed(evt);
            }
        });
        getContentPane().add(btnHuyNCC);
        btnHuyNCC.setBounds(770, 370, 100, 40);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN NHÀ CUNG CẤP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 10, 930, 61);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        // TODO add your handling code here:
        int rowSL = tblNCC.getSelectedRow();
        txtMaNCC.setText("" + tblNCC.getValueAt(rowSL, 0));
        txtTenNCC.setText("" + tblNCC.getValueAt(rowSL, 1));
        txtSDT.setText("" + tblNCC.getValueAt(rowSL, 2));
        txtEmail.setText("" + tblNCC.getValueAt(rowSL, 3));
        txtDiaChi.setText("" + tblNCC.getValueAt(rowSL, 4));
    }//GEN-LAST:event_tblNCCMouseClicked

    private void btnXoaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNCCActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhà cung cấp [ " + txtTenNCC.getText() +"] không ?", "Thông báo",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        try {
            if (NhaCungCap_DAO.delete_NCC(txtMaNCC.getText())){
                JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                hienThiDL();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaNCCActionPerformed

    private void btnHuyNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyNCCActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn Hủy bỏ thao tác ?", "Thông báo",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        this.dispose();
    }//GEN-LAST:event_btnHuyNCCActionPerformed


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
            java.util.logging.Logger.getLogger(FrameNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameNCC dialog = new FrameNCC(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHuyNCC;
    private javax.swing.JButton btnXoaNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNCC;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables

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

    private void hienThiDL() {
        txtMaNCC.setEditable(false);
        txtTenNCC.setEditable(false);
        txtSDT.setEditable(false);
        txtEmail.setEditable(false);
        txtDiaChi.setEditable(false);
        try {
            NhaCungCap_DAO.showDS_NCC(dtmNCC);
            hienThiMD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void hienThiMD(){
        tblNCC.setRowSelectionInterval(0, 0);
        txtMaNCC.setText("" + tblNCC.getValueAt(0, 0));
        txtTenNCC.setText("" + tblNCC.getValueAt(0, 1));
        txtSDT.setText("" + tblNCC.getValueAt(0, 2));
        txtEmail.setText("" + tblNCC.getValueAt(0, 3));
        txtDiaChi.setText("" + tblNCC.getValueAt(0, 4));
    }
}