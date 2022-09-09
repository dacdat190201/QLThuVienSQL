/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import static GUI_QLBanSach.FrameThemKH.validate;
import dao.KhachHang_DAO;
import doi_tuong.KhachHang;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrameCNTTKH extends javax.swing.JDialog {

    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;     
    DefaultTableModel dtmKH;
    
    public FrameCNTTKH(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Cập nhật thông tin khách hàng");
        this.setLocationRelativeTo(null);
        
        showMD();
        dtmKH = (DefaultTableModel)tblKH.getModel();
        hienThi();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtDCKH = new javax.swing.JTextField();
        txtEmailKH = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnSuar = new javax.swing.JButton();
        btnXN = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 24))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel2.setText("Tên khách hàng:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel3.setText("Số điện thoại:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenKHFocusLost(evt);
            }
        });

        txtDCKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtDCKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDCKHFocusLost(evt);
            }
        });

        txtEmailKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtEmailKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailKHFocusLost(evt);
            }
        });

        txtSDTKH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSDTKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTKHFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(txtSDTKH))
                        .addGap(58, 58, 58)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmailKH))
                    .addComponent(txtDCKH, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDCKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnSuar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnSuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnSuar.setText("SỬA");
        btnSuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuarActionPerformed(evt);
            }
        });

        btnXN.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnXN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        btnXN.setText("XÁC NHẬN");
        btnXN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXNActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnHuy.setText("HỦY");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSuar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Địa chỉ", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKH);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel6.setText("Nhập số điện thoai:");

        txtTK.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnTim.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tk.png"))); // NOI18N
        btnTim.setText("TÌM");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(63, 63, 63)
                                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        // TODO add your handling code here:
        int rowSL = tblKH.getSelectedRow();
        txtTenKH.setText("" + tblKH.getValueAt(rowSL, 1));
        txtSDTKH.setText("" + tblKH.getValueAt(rowSL, 2));
        txtEmailKH.setText("" + tblKH.getValueAt(rowSL, 3));
        txtDCKH.setText("" + tblKH.getValueAt(rowSL, 4));        
    }//GEN-LAST:event_tblKHMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy bỏ thao tác ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        
        hienThi();
        tblKH.setRowSelectionInterval(0, 0);
        int rowSL = tblKH.getSelectedRow();
        txtTenKH.setText("" + tblKH.getValueAt(rowSL, 1));
        txtSDTKH.setText("" + tblKH.getValueAt(rowSL, 2));
        txtEmailKH.setText("" + tblKH.getValueAt(rowSL, 3));
        txtDCKH.setText("" + tblKH.getValueAt(rowSL, 4));   
        
        btnHuy.setEnabled(false);
        btnXN.setEnabled(false);
        btnSuar.setEnabled(true);
        
        txtTenKH.setEditable(false);
        txtSDTKH.setEditable(false);
        txtEmailKH.setEditable(false);
        txtDCKH.setEditable(false);
        tblKH.setEnabled(true);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnSuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuarActionPerformed
        // TODO add your handling code here:
        txtTenKH.setEditable(true);
        txtSDTKH.setEditable(true);
        txtEmailKH.setEditable(true);
        txtDCKH.setEditable(true);
        txtTenKH.requestFocus();
        
        btnHuy.setEnabled(true);
        btnXN.setEnabled(true);
        btnSuar.setEnabled(false);
        tblKH.setEnabled(false);
    }//GEN-LAST:event_btnSuarActionPerformed

    private void btnXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXNActionPerformed
        // TODO add your handling code here:
        if (!ktRong(txtTenKH.getText(), txtEmailKH.getText(), txtSDTKH.getText(), txtDCKH.getText())){
                        JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ thông tin !", "Cảnh báo", 
                    JOptionPane.WARNING_MESSAGE);
            if (txtTenKH.getText().length() <= 0){
                txtTenKH.requestFocus(); return;
            } else if (txtEmailKH.getText().length() <= 0){
                txtEmailKH.requestFocus();return;
            } else if (txtSDTKH.getText().length() <= 0){
                txtSDTKH.requestFocus();return;
            } else {
                txtDCKH.requestFocus();return;
            }
        }
        if (!txtTenKH.getText().matches("^\\S[a-zA-z\\p{L}\\s]{1,50}$"))
        {
            JOptionPane.showMessageDialog(this, "Không được nhập kí tự đặc biệt  \n", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
            txtTenKH.selectAll();
            txtTenKH.requestFocus();return;
        }  
        if(!validate(txtEmailKH.getText())){
            JOptionPane.showConfirmDialog(this, "Vui lòng nhập có có dạng .........@... (.) ....","Thông báo",JOptionPane.WARNING_MESSAGE);
            txtEmailKH.requestFocus();
            txtEmailKH.selectAll(); return;
        }          
        if (!(txtSDTKH.getText().matches("\\d{10,11}"))){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại là số và đủ 10 - 11 số !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtSDTKH.requestFocus();
            txtSDTKH.selectAll();return;
        }  
        if (!(txtDCKH.getText().matches("^\\S[a-zA-z0-9\\p{L}\\s\\/\\,\\-\\.]{1,50}$"))){
                        JOptionPane.showMessageDialog(this, "Hãy nhập tiếng việt có dấu !", "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE);
            txtDCKH.selectAll();
            txtDCKH.requestFocus();return;
        }
        
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật thông tin cho khách hàng [" + txtTenKH.getText() + "] ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;

        int rowSL = tblKH.getSelectedRow();
        String makh = (String)tblKH.getValueAt(rowSL, 0);
        try {
            KhachHang kh = new KhachHang();
            kh.setTenKH(txtTenKH.getText());
            kh.setSDT(txtSDTKH.getText());
            kh.setDiaChi(txtDCKH.getText());
            kh.setEmail(txtEmailKH.getText());
            kh.setMaKH(makh);
            KhachHang_DAO dao = new KhachHang_DAO();
            if (dao.update_ThongTIn_KH(kh)){
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công !", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
               hienThi();
            }else{
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin không thành công !", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
               hienThi();   
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        txtTenKH.setEditable(false);
        txtSDTKH.setEditable(false);
        txtEmailKH.setEditable(false);
        txtDCKH.setEditable(false);
        txtTK.setText("");

        btnHuy.setEnabled(false);
        btnXN.setEnabled(false);
        btnSuar.setEnabled(true);            
        tblKH.setEnabled(true);

    }//GEN-LAST:event_btnXNActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát cửa sổ ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        dtmKH.setRowCount(0);
        try {
            if (KhachHang_DAO.check_KhachHang_fromSDT(txtTK.getText())){
                KhachHang_DAO.getKhachHang_fromSDT(dtmKH, txtTK.getText());
                tblKH.setRowSelectionInterval(0, 0);
                txtTenKH.setText("" + tblKH.getValueAt(0, 1));
                txtSDTKH.setText("" + tblKH.getValueAt(0, 2));
                txtEmailKH.setText("" + tblKH.getValueAt(0, 3));
                txtDCKH.setText("" + tblKH.getValueAt(0, 4));                  
            }else {
                JOptionPane.showMessageDialog(this, "Không tồn tại khách hàng cần tìm kiếm !", "Thông báo", JOptionPane.WARNING_MESSAGE);
                hienThi();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTenKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKHFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHFocusLost

    private void txtEmailKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailKHFocusLost
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtEmailKHFocusLost

    private void txtSDTKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTKHFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTKHFocusLost

    private void txtDCKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDCKHFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDCKHFocusLost


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
            java.util.logging.Logger.getLogger(FrameCNTTKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCNTTKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCNTTKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCNTTKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameCNTTKH dialog = new FrameCNTTKH(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSuar;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtDCKH;
    private javax.swing.JTextField txtEmailKH;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables

    private void hienThi() {
        try {
            KhachHang_DAO.showDS_KhachHang(dtmKH);
            md();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private void md() {
        tblKH.setRowSelectionInterval(0, 0);
        txtTenKH.setText("" + tblKH.getValueAt(0, 1));
        txtSDTKH.setText("" + tblKH.getValueAt(0, 2));
        txtEmailKH.setText("" + tblKH.getValueAt(0, 3));
        txtDCKH.setText("" + tblKH.getValueAt(0, 4));
    }
    
    private void showMD(){
        txtTenKH.setEditable(false);
        txtSDTKH.setEditable(false);
        txtEmailKH.setEditable(false);
        txtDCKH.setEditable(false);
        
        btnHuy.setEnabled(false);
        btnXN.setEnabled(false);
    }
    
    private boolean ktRong(String ten, String em, String sdt, String dc){
        if (ten.length() <= 0 || em.length() <= 0 || sdt.length() <= 0 || dc.length() <= 0){
            return false;
        }
        return true;
    }
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}+([.]\\w+)?$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
    }     
}
