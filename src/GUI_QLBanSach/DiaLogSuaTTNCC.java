/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import dao.NhaCungCap_DAO;
import doi_tuong.NhaCungCap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class DiaLogSuaTTNCC extends javax.swing.JDialog {

    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null; 
    
    String ten, sdt, email, diac;
    boolean kq = false;
    
    public DiaLogSuaTTNCC(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Cập nhật sửa đổi");
        this.setLocationRelativeTo(null);
        
        txtTenNCC.setEditable(false);
        txtLienHeNCC.setEditable(false);
        txtEmailNCC.setEditable(false);
        txtDiaChiNCC.setEditable(false);
        btnLuu.setEnabled(false);
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtLienHeNCC = new javax.swing.JTextField();
        txtDiaChiNCC = new javax.swing.JTextField();
        txtTenNCC = new javax.swing.JTextField();
        txtEmailNCC = new javax.swing.JTextField();
        btnThoat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        btnTimNCC = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CẬP NHẬT THÔNG TIN");

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 24))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel41.setText("Tên nhà cung cấp:");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel42.setText("Liên hệ:");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel43.setText("Email:");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel44.setText("Địa chỉ:");

        txtLienHeNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtDiaChiNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTenNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtEmailNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenNCC))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(txtLienHeNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDiaChiNCC))))
                .addGap(27, 27, 27))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLienHeNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnThoat.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        btnLuu.setText("LƯU");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel45.setText("Mã nhà cung cấp:");

        txtMaNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnTimNCC.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnTimNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tk.png"))); // NOI18N
        btnTimNCC.setText("TÌM KIẾM");
        btnTimNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        if (!(ktRong(txtMaNCC.getText(), txtTenNCC.getText(), txtLienHeNCC.getText(), txtEmailNCC.getText(), txtDiaChiNCC.getText()))){
            int tl = JOptionPane.showConfirmDialog(this, "     Bạn chưa hoàn thành thao tác       \n"
                + "     Bạn có chắc muốn thoát ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (tl == JOptionPane.NO_OPTION) return;
            this.dispose();
        } else {
            int tl = JOptionPane.showConfirmDialog(this, "     Xác nhận Thoát cửa sổ ?     ", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (tl == JOptionPane.NO_OPTION) return;
            this.dispose();
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (!txtTenNCC.getText().matches("^\\S[a-zA-z\\p{L}\\s]{1,50}$") || txtTenNCC.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "   Không được bỏ trống\n   Không được nhập ký tự đặc biệt\n", "Thông báo",
                        JOptionPane.WARNING_MESSAGE); 
            txtTenNCC.selectAll();
            txtTenNCC.requestFocus();return;
        }else if (!txtLienHeNCC.getText().matches("\\d{10,11}") || txtLienHeNCC.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "   Không được bỏ trống\n   Hãy nhập dãy số đủ 10 - 11 số", "Thông báo",
                        JOptionPane.WARNING_MESSAGE); 
            txtLienHeNCC.selectAll();
            txtLienHeNCC.requestFocus();return;
        }else if (!validate(txtEmailNCC.getText()) || txtEmailNCC.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "   Không được bỏ trống\n   Vui lòng nhập có có dạng .........@... (.) ....", "Thông báo",
                        JOptionPane.WARNING_MESSAGE); 
            txtEmailNCC.selectAll();
            txtEmailNCC.requestFocus();return;               
        }else if (!txtDiaChiNCC.getText().matches("^\\S[a-zA-z0-9\\p{L}\\s\\.\\/\\-\\,]{1,50}$")
                            || txtDiaChiNCC.getText().length() <= 0) {
            JOptionPane.showMessageDialog(this, "   Không được bỏ trống\n   Không được nhập kí tự đặc biệt", "Thông báo",
                        JOptionPane.WARNING_MESSAGE); 
            txtDiaChiNCC.selectAll();
            txtDiaChiNCC.requestFocus();return;
        }         
        
        int tl = JOptionPane.showConfirmDialog(this, "Bạn đồng ý cập nhật thông tin cho nhà cung cấp ?", "Thông báo",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        try {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNCC(txtMaNCC.getText());
            ncc.setTenNCC(txtTenNCC.getText());
            ncc.setSdtNCC(txtLienHeNCC.getText());
            ncc.setEmailNCC(txtEmailNCC.getText());
            ncc.setDiaCHi(txtDiaChiNCC.getText());
            NhaCungCap_DAO dao = new NhaCungCap_DAO();
            if (dao.update_NCC(ncc)){
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhà cung cấp thành công !", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                txtMaNCC.setText("");
                txtTenNCC.setText("");
                txtLienHeNCC.setText("");
                txtEmailNCC.setText("");
                txtDiaChiNCC.setText("");
                txtMaNCC.requestFocus();
            }else {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhà cung cấp thất bại thành công !", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnTimNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNCCActionPerformed
        // TODO add your handling code here:
        if (txtMaNCC.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "Hãy nhập thông tin tìm kiếm !", "Thông báo", 
                    JOptionPane.WARNING_MESSAGE);
            txtMaNCC.requestFocus();
        } else {
            txtTenNCC.setEditable(true);
            txtLienHeNCC.setEditable(true);
            txtEmailNCC.setEditable(true);
            txtDiaChiNCC.setEditable(true);
            try {
                NhaCungCap ncc = NhaCungCap_DAO.tim_NCC(txtMaNCC.getText());
                if (ncc != null){
                    txtTenNCC.setText(ncc.getTenNCC());
                    txtLienHeNCC.setText(ncc.getSdtNCC());
                    txtEmailNCC.setText(ncc.getEmailNCC());
                    txtDiaChiNCC.setText(ncc.getDiaCHi());
                    txtTenNCC.requestFocus();
                    btnLuu.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "   Mã nhà cung cấp không chính xác\n   Không có nhà cung cấp nào có mã [ " + txtMaNCC.getText() + " ] !", 
                            "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtMaNCC.selectAll();
                    txtMaNCC.requestFocus();
                }   
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnTimNCCActionPerformed

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
            java.util.logging.Logger.getLogger(DiaLogSuaTTNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiaLogSuaTTNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiaLogSuaTTNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiaLogSuaTTNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiaLogSuaTTNCC dialog = new DiaLogSuaTTNCC(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JTextField txtDiaChiNCC;
    private javax.swing.JTextField txtEmailNCC;
    private javax.swing.JTextField txtLienHeNCC;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
   
    private boolean ktRong(String ma, String ten, String sdt, String email, String diachi ){
        if (ma.length() > 0 && ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0){
            return true;
        }
        return false;
    }    

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}+([.]\\w+)?$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
    } 
}
