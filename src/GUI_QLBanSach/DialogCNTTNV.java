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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import load_image.ImageHelper;


public class DialogCNTTNV extends javax.swing.JDialog {
   
    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DefaultTableModel dtmTTNV;
    
    byte[] personalImage;
    
    public DialogCNTTNV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Cập nhật thông tin nhân viên");
        this.setLocationRelativeTo(null);
        
        md();

        dtmTTNV = (DefaultTableModel)tblDSNhanVien.getModel();
        NhanVien_DAO.showDS_NhanVien(dtmTTNV);
        showMD_TTNV();
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/nv1_1.jpg"));
        lblAnhNV.setIcon(icon);        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngt = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSuar = new javax.swing.JButton();
        btnXN = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDC = new javax.swing.JTextField();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        cboNgay = new javax.swing.JComboBox<>();
        txtemail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        cboNam = new javax.swing.JComboBox<>();
        lblAnhNV = new javax.swing.JLabel();
        btnTHemAnh = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        txtTK = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CẬP NHẬT THÔNG TIN NHÂN VIÊN");

        btnSuar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnSuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/settings.png"))); // NOI18N
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
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnSuar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnXN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSuar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblDSNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSNhanVien);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điền thông tin nhân viên:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Tên nhân viên:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Ngày sinh:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Giới tính:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Số điện thoại:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Địa chỉ:");

        txtTenNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtDC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btngt.add(radNam);
        radNam.setSelected(true);
        radNam.setText("Nam");

        btngt.add(radNu);
        radNu.setText("Nữ");

        txtemail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Email:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Tháng:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Năm:");

        btnTHemAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/picture.png"))); // NOI18N
        btnTHemAnh.setText("THÊM HÌNH");
        btnTHemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTHemAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(cboThang, 0, 80, Short.MAX_VALUE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(103, 103, 103)
                        .addComponent(radNam)
                        .addGap(76, 76, 76)
                        .addComponent(radNu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDC)
                            .addComponent(txtemail))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lblAnhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTHemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radNam)
                            .addComponent(radNu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTHemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnTim.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tk.png"))); // NOI18N
        btnTim.setText("TÌM");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        txtTK.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel10.setText("Nhập mã nhân viên:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel10)
                        .addGap(40, 40, 40)
                        .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuarActionPerformed
        // TODO add your handling code here:
        tblDSNhanVien.setEnabled(false);
        txtTenNV.setEditable(true);
        txtSDT.setEditable(true);
        txtemail.setEditable(true);
        txtDC.setEditable(true);
        txtTenNV.requestFocus();
        
        cboNgay.setEnabled(true);
        cboThang.setEnabled(true);
        cboNam.setEnabled(true);
        
        radNam.setEnabled(true);
        radNu.setEnabled(true);        

        btnHuy.setEnabled(true);
        btnXN.setEnabled(true);
        btnTHemAnh.setEnabled(true);
        btnSuar.setEnabled(false);
    }//GEN-LAST:event_btnSuarActionPerformed

    private void btnXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXNActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        DataValidator.validatorEmpty(txtTenNV, sb, "Tên nhân viên không được để trống");
        DataValidator.validatorEmpty(txtSDT, sb, "Số điện thoại không được để trống");
        DataValidator.validatorEmpty(txtemail, sb, "Email không được để trống");
        DataValidator.validatorEmpty(txtDC, sb, "Địa chỉ không được để trống");
        
        if (sb.length() > 0){
            MessageDialog.showMessageDialog(this, sb.toString(), "Cảnh báo");
            return;
        }        
        
        if (!txtTenNV.getText().matches("^\\S[a-zA-z\\p{L}\\s]{1,50}$")){
            JOptionPane.showMessageDialog(this, "   Không được nhập ký tự số !\n"
                    + " Ký tự đặc biệt !", "Thông báo", 
                    JOptionPane.WARNING_MESSAGE);
            txtTenNV.selectAll();
            txtTenNV.requestFocus();return;
        }
        if (!txtSDT.getText().matches("\\d{10,11}")){
            JOptionPane.showMessageDialog(this, "   Nhập đủ 10 - 11 số !", "Thông báo", 
                    JOptionPane.WARNING_MESSAGE);
            txtSDT.selectAll();
            txtSDT.requestFocus();return;
        }  
        if (!validate(txtemail.getText())){
            JOptionPane.showMessageDialog(this, "   Vui lòng nhập có có dạng .........@... (.) .... !", "Thông báo", 
                    JOptionPane.WARNING_MESSAGE);
            txtemail.selectAll();
            txtemail.requestFocus();return;
        }         
        if (!txtDC.getText().matches("^\\S[a-zA-z0-9\\p{L}\\s\\.\\/\\-\\,]{1,50}$")){
            JOptionPane.showMessageDialog(this, "   Không được nhập kí tự đặc biệt !", "Thông báo", 
                    JOptionPane.WARNING_MESSAGE);
            txtDC.selectAll();
            txtDC.requestFocus();return;
        } 
        
        //----Lấy ngày tháng năm sinh
        int ngay = Integer.parseInt(cboNgay.getSelectedItem().toString());
        int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        String ngaySinh = "" + ngay + "/" + thang + "/" + nam;
        
        //----Lấy giới tính
        String gt = "";
        if (radNam.isSelected()){
            gt = "Nam";
        } 
        if (radNu.isSelected()){
            gt = "Nữ";
        }
        
        //----Lấy mã nhân viên
        int rsl = tblDSNhanVien.getSelectedRow();
        String mnv = tblDSNhanVien.getValueAt(rsl, 0).toString();
        
        //----Kiểm tra nhập đủ thông tin chưa --> Rồi thì lưu thông tin nhân viên

        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật thông tin cho nhân viên [" + txtTenNV.getText() + "] ?", "Thông báo",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;            
        if (ktNgay(ngay, thang, nam)){
            try {
                NhanVien nv = new NhanVien();
                nv.setTenNV(txtTenNV.getText());
                nv.setSdt(txtSDT.getText());
                nv.setNgaySinh(ngaySinh);
                nv.setGioiTInh(gt);
                nv.setEmail(txtemail.getText());
                nv.setDiaCHi(txtDC.getText());
                nv.setMaNV(mnv);
                nv.setAnhNV(personalImage);
                
                NhanVien_DAO dao = new NhanVien_DAO();
                if (dao.update_NhanVien(nv)){
                    MessageDialog.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công", "Thông báo");

                    txtTenNV.setEditable(false);
                    txtSDT.setEditable(false);
                    txtemail.setEditable(false);
                    txtDC.setEditable(false);
                    cboNgay.setEnabled(false);
                    cboThang.setEnabled(false);
                    cboNam.setEnabled(false);

                    radNam.setEnabled(false);
                    radNu.setEnabled(false);

                    btnHuy.setEnabled(false);
                    btnXN.setEnabled(false);
                    btnTHemAnh.setEnabled(false);
                    btnSuar.setEnabled(true);
                    tblDSNhanVien.setEnabled(true);
                    this.dispose();                    
                }else{
                    MessageDialog.showMessageDialog(this, "Cập nhật thông tin nhân viên không thành công", "Cảnh báo");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            switch(thang){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (cboNgay.getSelectedItem().equals("31")){
                            JOptionPane.showMessageDialog(this, "Tháng " + thang + " chỉ có 30 ngày. Vui lòng chọn lại !", "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);                      
                    }
                    break;
                case 2:
                    if (nam % 400 == 0 || nam %4 == 0){
                        if (cboNgay.getSelectedItem().equals("30") || cboNgay.getSelectedItem().equals("31")){
                                JOptionPane.showMessageDialog(this, "Năm " + nam + " Tháng " + thang + " chỉ có 29 ngày. Vui lòng chọn lại !", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);                             
                        }
                    }
                    else{
                        if (cboNgay.getSelectedItem().equals("29") || cboNgay.getSelectedItem().equals("30") || cboNgay.getSelectedItem().equals("31")){
                                JOptionPane.showMessageDialog(this, "Năm " + nam + " Tháng " + thang + " chỉ có 28 ngày. Vui lòng chọn lại !", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);                       
                        }
                    }
                break;
            } 
        }
    }//GEN-LAST:event_btnXNActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy bỏ thao tác ?", "Thông báo",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        
        showMD_TTNV();
        
        btnHuy.setEnabled(false);
        btnXN.setEnabled(false);
        btnTHemAnh.setEnabled(false);
        btnSuar.setEnabled(true);

        txtTenNV.setEditable(false);
        txtSDT.setEditable(false);
        txtemail.setEditable(false);
        txtDC.setEditable(false);
        cboNgay.setEnabled(false);
        cboThang.setEnabled(false);
        cboNam.setEnabled(false);
        
        radNam.setEnabled(false);
        radNu.setEnabled(false);
        tblDSNhanVien.setEnabled(true);
 
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát cửa sổ ?", "Thông báo",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void tblDSNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNhanVienMouseClicked
        // TODO add your handling code here:
        int rowSl = tblDSNhanVien.getSelectedRow();
        if (rowSl >= 0){
            String manv = (String) tblDSNhanVien.getValueAt(rowSl, 0);
            NhanVien_DAO dao = new NhanVien_DAO();
            NhanVien nv = dao.show_TT_NhanVien(manv);
            
            String ngaySinh = "";
            String gt = "";
            if (nv != null){
                txtTenNV.setText(nv.getTenNV());
                //---Ngay sinh
                ngaySinh = nv.getNgaySinh();
                String [] ns = ngaySinh.split("-");
                int ngay = Integer.parseInt(ns[2]);
                int thang = Integer.parseInt(ns[1]);
                int nam = Integer.parseInt(ns[0]);

                cboNgay.setSelectedItem("" + ngay);
                cboThang.setSelectedItem("" + thang);
                cboNam.setSelectedItem("" + nam);
                
                //---Gioi tinh
                gt = nv.getGioiTInh();
                if (gt.equals("Nam")){
                    radNam.setSelected(true);
                }else {
                    radNu.setSelected(true);
                }
                
                //---
                txtSDT.setText(nv.getSdt());
                txtemail.setText(nv.getEmail());
                txtDC.setText(nv.getDiaCHi());
                
                //---Anh nhan vien
                if (nv.getAnhNV() != null){
                    try {
                        Image img = ImageHelper.createImageFromByteArray(nv.getAnhNV(), "jpg");
                        lblAnhNV.setIcon(new ImageIcon(img));
                        personalImage = nv.getAnhNV();
                    } catch (IOException ex) {
                        Logger.getLogger(DialogCNTTNV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    personalImage = nv.getAnhNV();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/img/nv1_1.jpg"));
                    lblAnhNV.setIcon(icon); 
                }
            }
        }        
    }//GEN-LAST:event_tblDSNhanVienMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if (txtTK.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "Hãy nhập mã nhân viên để tìm kiếm !", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtTK.requestFocus();
        }else {
            dtmTTNV.setRowCount(0);
            try {
                NhanVien nv =NhanVien_DAO.show_TT_NhanVien(txtTK.getText());
                if (nv != null) {
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(nv.getMaNV());
                    vec.add(nv.getTenNV());
                    vec.add(nv.getNgaySinh());
                    vec.add(nv.getGioiTInh());
                    vec.add(nv.getSdt());
                    vec.add(nv.getEmail());
                    vec.add(nv.getDiaCHi());

                    dtmTTNV.addRow(vec);
                }else{
                    JOptionPane.showMessageDialog(this, "Không có nhân viên [ " + txtTK.getText() + " ] !", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    txtTK.selectAll();
                    txtTK.requestFocus();
                    NhanVien_DAO.showDS_NhanVien(dtmTTNV);
                    tblDSNhanVien.setRowSelectionInterval(0, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnTHemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTHemAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()){
                    return true;
                }else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }
            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION){
            return;
        }
        File file = chooser.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = ImageHelper.resize(icon.getImage(), 199, 193);
            ImageIcon resizeIcon = new ImageIcon(img);
            lblAnhNV.setIcon(resizeIcon);
            personalImage = ImageHelper.toByteArray(img, "jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTHemAnhActionPerformed


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
            java.util.logging.Logger.getLogger(DialogCNTTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCNTTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCNTTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCNTTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCNTTNV dialog = new DialogCNTTNV(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnTHemAnh;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXN;
    private javax.swing.ButtonGroup btngt;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboNgay;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JTable tblDSNhanVien;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtemail;
    // End of variables declaration//GEN-END:variables

    private void md() {
        btnHuy.setEnabled(false);
        btnXN.setEnabled(false);
        btnTHemAnh.setEnabled(false);
        btnSuar.setEnabled(true);

        txtTenNV.setEditable(false);
        txtSDT.setEditable(false);
        txtemail.setEditable(false);
        txtDC.setEditable(false);
        for (int i = 1; i < 32; i++) {
            cboNgay.addItem("" + i);
        }
        for (int i = 1; i < 13; i++) {
            cboThang.addItem("" + i);
        }
        for (int i = 1990; i < 2001; i++) {
            cboNam.addItem("" + i);
        }
        cboNgay.setEnabled(false);
        cboThang.setEnabled(false);
        cboNam.setEnabled(false);
        
        radNam.setEnabled(false);
        radNu.setEnabled(false);
    }

    private void showMD_TTNV() {
        tblDSNhanVien.setRowSelectionInterval(0, 0);
        txtTenNV.setText((String)tblDSNhanVien.getValueAt(0, 1));
        //---Hiển thị ngày - tháng - năm sinh 
        String ngaySinh = tblDSNhanVien.getValueAt(0, 2).toString();
        String [] ns = ngaySinh.split("-");
        int ngay = Integer.parseInt(ns[2]);
        int thang = Integer.parseInt(ns[1]);
        int nam = Integer.parseInt(ns[0]);
        
        cboNgay.setSelectedItem("" + ngay);
        cboThang.setSelectedItem("" + thang);
        cboNam.setSelectedItem("" + nam);
        
        //----Lấy giới tính
        String gt = tblDSNhanVien.getValueAt(0, 3).toString();
        if (gt.equals("Nam")){
            radNam.setSelected(true);
        }else {
            radNu.setSelected(true);
        }
        
        txtSDT.setText(tblDSNhanVien.getValueAt(0, 4).toString());
        txtemail.setText(tblDSNhanVien.getValueAt(0, 5).toString());
        txtDC.setText(tblDSNhanVien.getValueAt(0, 6).toString());
    }
    
    private  boolean ktNgay(int ngay, int thang, int nam){
        switch(thang){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (ngay < 0 || ngay > 30){
                    return false;
                }
                break;
            case 2:
                if (nam % 400 == 0 || nam %4 == 0){
                    if (ngay < 0 || ngay > 29){
                        return false;
                    }
                }
                else{
                    if (ngay < 0 || ngay > 28){
                        return false;
                    }
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (ngay < 0 || ngay > 31){
                    return false;
                }
                break;
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
