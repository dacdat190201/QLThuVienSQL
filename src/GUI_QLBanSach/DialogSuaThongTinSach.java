/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import dao.Sach_DAO;
import doi_tuong.Sach;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class DialogSuaThongTinSach extends javax.swing.JDialog {

    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null; 
    DefaultTableModel dtmSAch;
    
    public DialogSuaThongTinSach(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Cập nhật thông tin sách");
        this.setLocationRelativeTo(null);
        
        macDinh();
        
        dtmSAch = (DefaultTableModel)tblDiaLogSach.getModel();
        hienThiDL();
        
        
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmA = new javax.swing.JTextField();
        txtTG = new javax.swing.JTextField();
        txtNHaXB = new javax.swing.JTextField();
        txtNXB = new javax.swing.JTextField();
        txtTL = new javax.swing.JTextField();
        txtTonkho = new javax.swing.JTextField();
        txtGB = new javax.swing.JTextField();
        txtTEn = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnLuu1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiaLogSach = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN SÁCH");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Chi tiết:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 24))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Mã sách:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Tên sách:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Tác giả:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Nhà xuất bản:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Năm xuất bản:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Thể loại:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Tồn kho:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Giá bán:");

        txtmA.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtTG.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTG.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTGFocusLost(evt);
            }
        });

        txtNHaXB.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNHaXB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNHaXBFocusLost(evt);
            }
        });

        txtNXB.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNXB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNXBFocusLost(evt);
            }
        });

        txtTL.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTL.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTLFocusLost(evt);
            }
        });

        txtTonkho.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtGB.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtGB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGBFocusLost(evt);
            }
        });

        txtTEn.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTEn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTEnFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNHaXB, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTEn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTG, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtmA, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGB, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTonkho, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmA, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTEn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTonkho, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTG, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNHaXB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(null);

        btnHuy.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnHuy.setText("HỦY");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel2.add(btnHuy);
        btnHuy.setBounds(70, 0, 165, 40);

        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/settings.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSua);
        btnSua.setBounds(280, 0, 165, 40);

        btnThoat.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel2.add(btnThoat);
        btnThoat.setBounds(700, 0, 165, 40);

        btnLuu1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnLuu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        btnLuu1.setText("XÁC NHẬN");
        btnLuu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnLuu1);
        btnLuu1.setBounds(490, 0, 165, 40);

        tblDiaLogSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblDiaLogSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Thể loại", "Tồn kho", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDiaLogSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiaLogSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDiaLogSach);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDiaLogSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiaLogSachMouseClicked
        // TODO add your handling code here:
        int rowSL = tblDiaLogSach.getSelectedRow();
        txtmA.setText("" + tblDiaLogSach.getValueAt(rowSL, 0));
        txtTEn.setText("" + tblDiaLogSach.getValueAt(rowSL, 1));
        txtTG.setText("" + tblDiaLogSach.getValueAt(rowSL, 2));
        txtNHaXB.setText("" + tblDiaLogSach.getValueAt(rowSL, 3));
        txtNXB.setText("" + tblDiaLogSach.getValueAt(rowSL, 4));
        txtTL.setText("" + tblDiaLogSach.getValueAt(rowSL, 5));
        txtTonkho.setText("" + tblDiaLogSach.getValueAt(rowSL, 6));
        txtGB.setText("" + tblDiaLogSach.getValueAt(rowSL, 7));
    }//GEN-LAST:event_tblDiaLogSachMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        txtTEn.setEditable(true);
        txtTEn.selectAll();
        txtTEn.requestFocus();
        txtTG.setEditable(true);
        txtNHaXB.setEditable(true);
        txtNXB.setEditable(true);
        txtTL.setEditable(true);
        txtGB.setEditable(true);
        
        btnHuy.setEnabled(true);
        btnLuu1.setEnabled(true);        

        btnSua.setEnabled(false);  
        tblDiaLogSach.setEnabled(false);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy sửa đổi ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;   
        
        txtmA.setEditable(false);
        txtTEn.setEditable(false);
        txtTG.setEditable(false);
        txtNHaXB.setEditable(false);
        txtNXB.setEditable(false);
        txtTL.setEditable(false);
        txtTonkho.setEditable(false);
        txtGB.setEditable(false);
        
        btnHuy.setEnabled(false);
        btnLuu1.setEnabled(false);
        tblDiaLogSach.setEnabled(true);

        btnSua.setEnabled(true);
        int rowSL = tblDiaLogSach.getSelectedRow();
        txtmA.setText("" + tblDiaLogSach.getValueAt(rowSL, 0));
        txtTEn.setText("" + tblDiaLogSach.getValueAt(rowSL, 1));
        txtTG.setText("" + tblDiaLogSach.getValueAt(rowSL, 2));
        txtNHaXB.setText("" + tblDiaLogSach.getValueAt(rowSL, 3));
        txtNXB.setText("" + tblDiaLogSach.getValueAt(rowSL, 4));
        txtTL.setText("" + tblDiaLogSach.getValueAt(rowSL, 5));
        txtTonkho.setText("" + tblDiaLogSach.getValueAt(rowSL, 6));
        txtGB.setText("" + tblDiaLogSach.getValueAt(rowSL, 7));        
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu1ActionPerformed
        // TODO add your handling code here:
        
        if (ktRong(txtTEn.getText() ,txtTG.getText(), txtNHaXB.getText(), txtNXB.getText(), txtTL.getText(),
                txtTonkho.getText(), txtGB.getText())){
            if (ktgb(txtGB.getText())){
                int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu sửa đổi thông tin sách ?", "Thông báo",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (tl == JOptionPane.NO_OPTION) return;
                try {                   
                    Sach sach = new Sach();
                    sach.setMaSach(txtmA.getText());
                    sach.setTenSach(txtTEn.getText());
                    sach.setTacGia(txtTG.getText());
                    sach.setNhaXB(txtNHaXB.getText());
                    sach.setNxb(Integer.parseInt(txtNXB.getText()));
                    sach.setTheLoai(txtTL.getText());
                    sach.setGiaBan(Integer.parseInt(txtGB.getText()));

                    Sach_DAO dao = new Sach_DAO();
                    if (dao.update_ThongTinSach(sach)){
                        JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin thành công !!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        hienThiDL();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                txtmA.setEditable(false);
                txtTEn.setEditable(false);
                txtTG.setEditable(false);
                txtNHaXB.setEditable(false);
                txtNXB.setEditable(false);
                txtTL.setEditable(false);
                txtTonkho.setEditable(false);
                txtGB.setEditable(false);

                btnHuy.setEnabled(false);
                btnLuu1.setEnabled(false);

                btnSua.setEnabled(true); 
                tblDiaLogSach.setEnabled(true);
            }else {
                JOptionPane.showMessageDialog(this, "Không được cập nhật giá bán thấp hơn giá nhập", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
                txtGB.selectAll();
                txtGB.requestFocus();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ thông tin !", "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
            if (txtTEn.getText().length() <= 0){
                txtTEn.requestFocus();
            } else if (txtTG.getText().length() <= 0){
                txtTG.requestFocus();
            } else if (txtNHaXB.getText().length() <= 0){
                txtNHaXB.requestFocus();
            } else if (txtNXB.getText().length() <= 0){
                txtNXB.requestFocus();
            } else if (txtTL.getText().length() <= 0){
                txtTL.requestFocus();
            } else if (txtTonkho.getText().length() <= 0){
                txtTonkho.requestFocus();
            } else{
                txtGB.requestFocus();
            }        
        }
    }//GEN-LAST:event_btnLuu1ActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát thao tác ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void txtTEnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTEnFocusLost
        // TODO add your handling code here:
        if (!txtTEn.getText().matches("^\\S[a-zA-z0-9\\p{L}\\s]{1,50}$"))
        {
            JOptionPane.showMessageDialog(this, "Không được nhập kí tự đặc biệt  \n", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
            txtTEn.selectAll();
            txtTEn.requestFocus();
        }       
    }//GEN-LAST:event_txtTEnFocusLost

    private void txtTGFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTGFocusLost
        // TODO add your handling code here:
            if (!txtTG.getText().matches("^\\S[a-zA-z\\p{L}\\s]{1,50}$"))
            {
                JOptionPane.showMessageDialog(this, "Không được nhập số, kí tự đặc biệt  \n", "Thông báo",
                                JOptionPane.WARNING_MESSAGE);
                txtTG.selectAll();
                txtTG.requestFocus();
            }  
    }//GEN-LAST:event_txtTGFocusLost

    private void txtNHaXBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNHaXBFocusLost
        // TODO add your handling code here:.
        if (!txtNHaXB.getText().matches("^\\S[a-zA-z0-9\\p{L}\\s]{1,50}$"))
        {
            JOptionPane.showMessageDialog(this, "Không được nhập kí tự đặc biệt  \n", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
            txtNHaXB.selectAll();
            txtNHaXB.requestFocus();
        } 
    }//GEN-LAST:event_txtNHaXBFocusLost

    private void txtNXBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNXBFocusLost
        // TODO add your handling code here:
        if (!txtNXB.getText().matches("\\d{4}"))
        {
            JOptionPane.showMessageDialog(this, "Hãy nhập số  \n", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
            txtNXB.selectAll();
            txtNXB.requestFocus();
        }
    }//GEN-LAST:event_txtNXBFocusLost

    private void txtTLFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTLFocusLost
        // TODO add your handling code here:
        if (!txtTL.getText().matches("^\\S[a-zA-z\\p{L}\\s]{1,50}$"))
        {
            JOptionPane.showMessageDialog(this, "Không được nhập kí tự đặc biệt  \n", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
            txtTL.selectAll();
            txtTL.requestFocus();
        }  
    }//GEN-LAST:event_txtTLFocusLost

    private void txtGBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGBFocusLost
        // TODO add your handling code here:
        if (!txtGB.getText().matches("[0-9\\.]*$"))
        {
            JOptionPane.showMessageDialog(this, "Không được nhập kí tự đặc biệt  \n", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
            txtGB.selectAll();
            txtGB.requestFocus();
        }
    }//GEN-LAST:event_txtGBFocusLost


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
            java.util.logging.Logger.getLogger(DialogSuaThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogSuaThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogSuaThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogSuaThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogSuaThongTinSach dialog = new DialogSuaThongTinSach(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLuu1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDiaLogSach;
    private javax.swing.JTextField txtGB;
    private javax.swing.JTextField txtNHaXB;
    private javax.swing.JTextField txtNXB;
    private javax.swing.JTextField txtTEn;
    private javax.swing.JTextField txtTG;
    private javax.swing.JTextField txtTL;
    private javax.swing.JTextField txtTonkho;
    private javax.swing.JTextField txtmA;
    // End of variables declaration//GEN-END:variables


    private void hienThiDL() {
        try {
            Sach_DAO.showDL_CT_pnThongTinSach(dtmSAch);
            hienThiMD();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hienThiMD() {
        tblDiaLogSach.setRowSelectionInterval(0, 0);
        txtmA.setText("" + tblDiaLogSach.getValueAt(0, 0));
        txtTEn.setText("" + tblDiaLogSach.getValueAt(0, 1));
        txtTG.setText("" + tblDiaLogSach.getValueAt(0, 2));
        txtNHaXB.setText("" + tblDiaLogSach.getValueAt(0, 3));
        txtNXB.setText("" + tblDiaLogSach.getValueAt(0, 4));
        txtTL.setText("" + tblDiaLogSach.getValueAt(0, 5));
        txtTonkho.setText("" + tblDiaLogSach.getValueAt(0, 6));
        txtGB.setText("" + tblDiaLogSach.getValueAt(0, 7));   
    }

    private void macDinh() {
        txtmA.setEditable(false);
        txtTEn.setEditable(false);
        txtTG.setEditable(false);
        txtNHaXB.setEditable(false);
        txtNXB.setEditable(false);
        txtTL.setEditable(false);
        txtTonkho.setEditable(false);
        txtGB.setEditable(false);
        
        btnHuy.setEnabled(false);
        btnLuu1.setEnabled(false);
    }
    
    private boolean ktRong(String ts, String tg, String nhaxb, String nam, String tl, String tk, String gb){
        if (ts.length() <= 0 || tg.length() <= 0 || nhaxb.length() <= 0 || nam.length() <= 0 || tl.length() <= 0
                || tk.length() <= 0 || gb.length() <= 0){
            return false;
        }
        return true;
    }
    
    private boolean ktgb(String gb){
        Sach_DAO dao = new Sach_DAO();
        if (!dao.Check_GiaBan_Sach(txtmA.getText(), Integer.parseInt(gb))){
            return false;
        }
        return true;
    }
}
