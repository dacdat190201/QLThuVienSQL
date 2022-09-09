/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import dao.CT_DonNhapHang_DAO;
import dao.HoaDonNhap_DAO;
import dao.NhaCungCap_DAO;
import dao.Sach_DAO;
import dao.TonKho_DAO;
import doi_tuong.CT_HoaDonNhap;
import doi_tuong.HoaDonNhap;
import doi_tuong.Sach;
import doi_tuong.TonKho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class DioLogNhapSach extends javax.swing.JDialog {

    private Connection connect = null;
    private PreparedStatement prepared = null;
    private ResultSet resultSet = null;
    
    private DefaultTableModel dtmNhapSach;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    double tongTT = 0;    
    
    public DioLogNhapSach(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Hóa đơn nhập sách");
        this.setLocationRelativeTo(null);
        
        hienThimacDinh();
        
        ketNoiDL();
        dtmNhapSach = (DefaultTableModel) tblEmptyThongTinNhapSach.getModel();
        hienThiNhaCungCap();       
        dtmNhapSach.setRowCount(0);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNgayNhapSach = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaSachNhap = new javax.swing.JTextField();
        txtTenSachNhap = new javax.swing.JTextField();
        txtTacGiaNhap = new javax.swing.JTextField();
        txtNXBNhap = new javax.swing.JTextField();
        txtNamXBNhap = new javax.swing.JTextField();
        txtTheLoaiNhap = new javax.swing.JTextField();
        txtSoluongNhap = new javax.swing.JTextField();
        txtDonGiaNhap = new javax.swing.JTextField();
        lblMS = new javax.swing.JLabel();
        lblTS = new javax.swing.JLabel();
        lblTG = new javax.swing.JLabel();
        lblNXB = new javax.swing.JLabel();
        lblNamXB = new javax.swing.JLabel();
        lblTheLoai = new javax.swing.JLabel();
        lblSL = new javax.swing.JLabel();
        lblDGN = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnThemSach = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmptyThongTinNhapSach = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTongThanhTien = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHIẾU NHẬP SÁCH");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nhập thông tin sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 22))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Chọn nhà cung cấp:");

        cboNhaCungCap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhaCungCapActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Ngày nhập:");

        txtNgayNhapSach.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtNgayNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtNgayNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nhập thông tin sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 22))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Mã sách:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Tên sách:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Tác giả:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Nhà xuất bản:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Năm xuất bản:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Thể loại:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Số lượng:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Đơn giá nhập:");

        txtMaSachNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaSachNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaSachNhapFocusLost(evt);
            }
        });

        txtTenSachNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenSachNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenSachNhapFocusLost(evt);
            }
        });

        txtTacGiaNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTacGiaNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTacGiaNhapFocusLost(evt);
            }
        });

        txtNXBNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNXBNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNXBNhapFocusLost(evt);
            }
        });

        txtNamXBNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNamXBNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNamXBNhapFocusLost(evt);
            }
        });

        txtTheLoaiNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTheLoaiNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTheLoaiNhapFocusLost(evt);
            }
        });

        txtSoluongNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSoluongNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoluongNhapFocusLost(evt);
            }
        });

        txtDonGiaNhap.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtDonGiaNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDonGiaNhapFocusLost(evt);
            }
        });

        lblMS.setForeground(java.awt.Color.red);

        lblTS.setForeground(java.awt.Color.red);

        lblTG.setForeground(java.awt.Color.red);

        lblNXB.setForeground(java.awt.Color.red);

        lblNamXB.setForeground(java.awt.Color.red);

        lblTheLoai.setForeground(java.awt.Color.red);

        lblSL.setForeground(java.awt.Color.red);

        lblDGN.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaSachNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtTenSachNhap)
                            .addComponent(txtTacGiaNhap)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtNXBNhap)))
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTG, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNamXBNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(txtTheLoaiNhap)
                    .addComponent(txtSoluongNhap)
                    .addComponent(txtDonGiaNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDGN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel8)
                        .addComponent(txtMaSachNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNamXBNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel9)
                        .addComponent(txtTenSachNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTheLoaiNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel10)
                        .addComponent(txtTacGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTG, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSoluongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel11)
                        .addComponent(txtNXBNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDGN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDonGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnLuu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        btnLuu.setText("LƯU");
        btnLuu.setPreferredSize(new java.awt.Dimension(89, 25));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.setPreferredSize(new java.awt.Dimension(89, 25));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnThoat.setText("THOÁT");
        btnThoat.setPreferredSize(new java.awt.Dimension(89, 25));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnThemSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnThemSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnThemSach.setText("THÊM");
        btnThemSach.setPreferredSize(new java.awt.Dimension(89, 25));
        btnThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin đơn nhập", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 22))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblEmptyThongTinNhapSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblEmptyThongTinNhapSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Thể loại", "Số lượng", "Đơn giá nhập", "Thành tiền"
            }
        ));
        tblEmptyThongTinNhapSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmptyThongTinNhapSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmptyThongTinNhapSach);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Tổng tiền:");

        txtTongThanhTien.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(30, 30, 30)
                                .addComponent(txtTongThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTongThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhaCungCapActionPerformed
        // TODO add your handling code here:
        if (cboNhaCungCap.getSelectedItem().equals("Custom..........")){
            DiaLogThemNCC themNCC = new DiaLogThemNCC(null, true);
            themNCC.setVisible(true);
            cboNhaCungCap.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cboNhaCungCapActionPerformed

    private void txtMaSachNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaSachNhapFocusLost
        // TODO add your handling code here:
        //----Kiểm tra nếu mã sách đã có trong dữ liệu
        if (ktMaSach(txtMaSachNhap.getText())){
            //---Hiển thị thông tin của mã sách đó
            try {
                Sach sach = Sach_DAO.get_TT_Sach(txtMaSachNhap.getText());

                txtTenSachNhap.setText(sach.getTenSach());
                txtTacGiaNhap.setText(sach.getTacGia());
                txtNXBNhap.setText(sach.getNhaXB());
                txtNamXBNhap.setText("" + sach.getNxb());
                txtTheLoaiNhap.setText(sach.getTheLoai());

                txtTenSachNhap.setEditable(false);
                txtTacGiaNhap.setEditable(false);
                txtNXBNhap.setEditable(false);
                txtNamXBNhap.setEditable(false);
                txtTheLoaiNhap.setEditable(false);
                txtSoluongNhap.requestFocus();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            txtTenSachNhap.setEditable(true);
            txtTacGiaNhap.setEditable(true);
            txtNXBNhap.setEditable(true);
            txtNamXBNhap.setEditable(true);
            txtTheLoaiNhap.setEditable(true);
            txtNamXBNhap.selectAll();
        }
    }//GEN-LAST:event_txtMaSachNhapFocusLost

    private void txtTenSachNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenSachNhapFocusLost
        // TODO add your handling code here:
        txtTheLoaiNhap.selectAll();
        txtTheLoaiNhap.requestFocus();
    }//GEN-LAST:event_txtTenSachNhapFocusLost

    private void txtTacGiaNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTacGiaNhapFocusLost
        // TODO add your handling code here:
        txtSoluongNhap.selectAll();   
        txtSoluongNhap.requestFocus();
    }//GEN-LAST:event_txtTacGiaNhapFocusLost

    private void txtNXBNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNXBNhapFocusLost
        // TODO add your handling code here:
        txtDonGiaNhap.selectAll();
        txtDonGiaNhap.requestFocus();        
    }//GEN-LAST:event_txtNXBNhapFocusLost

    private void txtNamXBNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNamXBNhapFocusLost
        // TODO add your handling code here:
        txtTenSachNhap.selectAll();
        txtTenSachNhap.requestFocus();
    }//GEN-LAST:event_txtNamXBNhapFocusLost

    private void txtTheLoaiNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTheLoaiNhapFocusLost
        // TODO add your handling code here:
        txtTacGiaNhap.selectAll();
        txtTacGiaNhap.requestFocus();
    }//GEN-LAST:event_txtTheLoaiNhapFocusLost

    private void txtSoluongNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoluongNhapFocusLost
        // TODO add your handling code here:
        txtNXBNhap.selectAll();
        txtNXBNhap.requestFocus();
    }//GEN-LAST:event_txtSoluongNhapFocusLost

    private void txtDonGiaNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDonGiaNhapFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaNhapFocusLost

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here
        //--Lấy mã nhân viên
        DialogMaNVNhapHang ma = new DialogMaNVNhapHang(null, true);
        ma.setVisible(true);
        String maNV = ma.getMaNV();
        
        //--Lấy mã nhà cung cấp
        String mncc = NhaCungCap_DAO.getMaNCC(cboNhaCungCap.getSelectedItem().toString());

        
        double rd = Math.random();
        int md = (int) (100 + rd * 1000  + 1);
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu hóa đơn",
            "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;

        int soTT = tblEmptyThongTinNhapSach.getRowCount();
        String [] maSach = new String[soTT];
        String [] tenSach = new String[soTT];
        String [] tg = new String[soTT];
        String [] nxb = new String[soTT];
        int [] namxb = new int[soTT];
        String [] theLoai = new String[soTT];
        int [] soLuongTon = new int[soTT];
        int [] donGiaNhap = new int[soTT];
        String [] thanhTien = new String[soTT];

        for (int i = 0; i < soTT; i++){
            maSach[i] = (String) tblEmptyThongTinNhapSach.getValueAt(i, 0);
            tenSach[i] = (String) tblEmptyThongTinNhapSach.getValueAt(i, 1);
            tg[i] = (String) tblEmptyThongTinNhapSach.getValueAt(i, 2);
            nxb[i] = (String) tblEmptyThongTinNhapSach.getValueAt(i, 3);
            namxb[i] = Integer.parseInt("" + tblEmptyThongTinNhapSach.getValueAt(i, 4));
            theLoai[i] = (String) tblEmptyThongTinNhapSach.getValueAt(i, 5);
            soLuongTon[i] = Integer.parseInt("" + tblEmptyThongTinNhapSach.getValueAt(i, 6));
            donGiaNhap[i] = Integer.parseInt("" + tblEmptyThongTinNhapSach.getValueAt(i, 7));
            thanhTien[i] = tblEmptyThongTinNhapSach.getValueAt(i, 8).toString().trim();
        }
        boolean kq = false;
        int tongSL = 0;
        int ttt = 0;
        for (int i = 0; i < soTT; i++) {
            tongSL += soLuongTon[i];
            ttt += Integer.parseInt(thanhTien[i]);
        }
        try {
            //------------------------------Insert vào bảng MuaSach
            HoaDonNhap hoaDonNhap = new HoaDonNhap();
            hoaDonNhap.setMaDonNhap("MD" + md);
            hoaDonNhap.setMaNV(maNV);
            hoaDonNhap.setMaNCC(mncc);
            hoaDonNhap.setNgayNhap("" + txtNgayNhapSach.getText());
            hoaDonNhap.setSoLuong(tongSL);
            hoaDonNhap.setTongTienHD(ttt);
            
            kq = HoaDonNhap_DAO.insert_DonNhapHang(hoaDonNhap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            for (int i = 0; i < soTT; i++) {
                //-------------------- Nếu sách đã có thì cập nhật tồn kho
                if (ktMaSach(maSach[i])){
                    try {
                        kq = TonKho_DAO.update_TOnKho_Nhap(soLuongTon[i], maSach[i]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally{
                        try {
                            //-----thêm vào CT_MuaSach                            
                            CT_HoaDonNhap cthdn = new CT_HoaDonNhap();
                            cthdn.setMaDonNhap("MD" + md);
                            cthdn.setMaSach(maSach[i]);
                            cthdn.setSlNhap(soLuongTon[i]);
                            cthdn.setGiaBan(donGiaNhap[i]);
                            
                            kq = CT_DonNhapHang_DAO.insert_CT_DonNhapHang(cthdn);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    //----------Nếu là sách mới thì thêm thông tin vào bảng thông tin sách
                    //----------Thêm số lượng vào SachTonKho
                    //----------Lưu thông tin phiếu mua hàng vào MuaSach
                    try {
                        //---------------Insert thông tin sách vào bảng ThongTinSach                       
                        Sach sach = new Sach();
                        sach.setMaSach(maSach[i]);
                        sach.setTenSach(tenSach[i]);
                        sach.setTacGia(tg[i]);
                        sach.setNhaXB(nxb[i]);
                        sach.setNxb(namxb[i]);
                        sach.setTheLoai(theLoai[i]);
                        
                        kq = Sach_DAO.insert_Sach(sach);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        //-----------------------------Thêm thông tin vào bảng CTMuaSach
                        try {
                            //-----thêm vào CT_MuaSach                            
                            CT_HoaDonNhap cthdn = new CT_HoaDonNhap();
                            cthdn.setMaDonNhap("MD" + md);
                            cthdn.setMaSach(maSach[i]);
                            cthdn.setSlNhap(soLuongTon[i]);
                            cthdn.setGiaBan(donGiaNhap[i]);
                            
                            kq = CT_DonNhapHang_DAO.insert_CT_DonNhapHang(cthdn);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finally {
                            //------------------------------Thêm số lượng nhập vào bảng tồn kho
                            try {                               
                                TonKho tonKho = new TonKho();
                                tonKho.setMaSAch(maSach[i]);
                                tonKho.setSoLuongTon(soLuongTon[i]);
                                kq = TonKho_DAO.insert_TonKho(tonKho);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            if (kq){
                JOptionPane.showMessageDialog(this, "Lưu thành công !","Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                dtmNhapSach.setRowCount(0);
            }            
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa Mã: " + txtMaSachNhap.getText() + " Khỏi phiếu nhập",
            "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        else{
            int rowSL = tblEmptyThongTinNhapSach.getSelectedRow();
            dtmNhapSach.removeRow(rowSL);
            txtMaSachNhap.setText("");
            txtTenSachNhap.setText("");
            txtTacGiaNhap.setText("");
            txtNXBNhap.setText("");
            txtNamXBNhap.setText("");
            txtTheLoaiNhap.setText("");
            txtSoluongNhap.setText("");
            txtDonGiaNhap.setText("");
            txtTongThanhTien.setText("");
            btnXoa.setEnabled(false);
        }
        int dl = tblEmptyThongTinNhapSach.getRowCount();
        if (dl == 0){
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(false);
            
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        if (txtMaSachNhap.getText().length() <= 0 && txtTenSachNhap.getText().length() <= 0
                && txtTacGiaNhap.getText().length() <= 0 && txtNXBNhap.getText().length() <= 0
                && txtNamXBNhap.getText().length() <= 0 && txtTheLoaiNhap.getText().length() <= 0
                && txtDonGiaNhap.getText().length() <= 0 && txtSoluongNhap.getText().length() <= 0){
            int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (tl == JOptionPane.NO_OPTION) return;
            else{
                this.dispose();
            }            
        }else {
            int tl = JOptionPane.showConfirmDialog(this, "      Bạn chưa hoàn thành thao tác\n      Bạn có muốn Thoát ?", "Cảnh báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (tl == JOptionPane.NO_OPTION) return;
            else{
                this.dispose();
            }            
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSachActionPerformed
        // TODO add your handling code here:
        while (txtMaSachNhap.getText().equals("") || !txtMaSachNhap.getText().matches("^\\S[a-zA-z0-9]{1,50}$")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập mã sách, không có ký tự đặc biệt !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtMaSachNhap.requestFocus();txtMaSachNhap.selectAll();
            return;
        }
        while (txtNamXBNhap.getText().equals("") || !txtNamXBNhap.getText().matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập năm xuất bản, không ký tự đặc biệt !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtNamXBNhap.requestFocus();txtNamXBNhap.selectAll();
            return;
        }

        while (txtTenSachNhap.getText().equals("") || !txtTenSachNhap.getText().matches("^\\S[a-zA-z0-9\\p{L}\\s\\-]{1,50}$")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập tên sách, không có ký tự đặc biệt !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtTenSachNhap.requestFocus();txtTenSachNhap.selectAll();
            return;
        }
        while (txtTheLoaiNhap.getText().equals("") || !txtTheLoaiNhap.getText().matches("^\\S[a-zA-Z\\p{L}\\s\\-]{1,50}$")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập thể loại sách, không bao gồm số và ký tự đặc biệt !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtTheLoaiNhap.requestFocus();txtTheLoaiNhap.selectAll();
            return;
        }
        while (txtTacGiaNhap.getText().equals("") || !txtTacGiaNhap.getText().matches("^\\S[a-zA-z\\p{L}\\s\\-]{1,50}$")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập tác giả, không bao gồm số và ký tự đặc biệt !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtTacGiaNhap.requestFocus();txtTacGiaNhap.selectAll();
            return;
        }
        while (txtSoluongNhap.getText().equals("") || !txtSoluongNhap.getText().matches("[0-9]*$") 
                || txtSoluongNhap.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số lượng, không bao gồm chữ và ký tự đặc biệt !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtSoluongNhap.requestFocus();txtSoluongNhap.selectAll();
            return;
        }
        while (txtNXBNhap.getText().equals("") || !txtNXBNhap.getText().matches("^[a-zA-z0-9\\p{L}\\s\\-]{1,50}$")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập nhà xuất bản, không bao gồm ký tự đặc biệt !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtNXBNhap.requestFocus();txtNXBNhap.selectAll();
            return;
        }
        while (txtDonGiaNhap.getText().equals("") || !txtDonGiaNhap.getText().matches("[0-9]*$")
                || txtDonGiaNhap.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập giá nhập lớn hơn 0, không bao gồm chữ và ký tự đặc biệt  !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtDonGiaNhap.requestFocus();txtDonGiaNhap.selectAll();
            return;
        }
        
        int rowCount = tblEmptyThongTinNhapSach.getRowCount();
        if (rowCount > 0){
            String[] mas = new String[rowCount];
            for (int i = 0; i < rowCount; i++) {
                mas[i] = (String)tblEmptyThongTinNhapSach.getValueAt(i, 0);
            }
            for (int i = 0; i < rowCount; i++) {
                if (mas[i].equals(txtMaSachNhap.getText())){
                    JOptionPane.showMessageDialog(this, "Sách đã có trong đơn nhập ?", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    txtMaSachNhap.requestFocus();
                    txtMaSachNhap.selectAll();
                    return;
                }
            }  
        }
        
        int sl = Integer.parseInt(txtSoluongNhap.getText());
        int dg = Integer.parseInt(txtDonGiaNhap.getText());
        int tt = sl * dg;

        tongTT = tongTT + tt;

        Vector<Object> vec = new Vector<Object>();
        vec.add(txtMaSachNhap.getText());
        vec.add(txtTenSachNhap.getText());
        vec.add(txtTacGiaNhap.getText());
        vec.add(txtNXBNhap.getText());
        vec.add(txtNamXBNhap.getText());
        vec.add(txtTheLoaiNhap.getText());
        vec.add(txtSoluongNhap.getText());
        vec.add(txtDonGiaNhap.getText());
        vec.add(tt);

        dtmNhapSach.addRow(vec);

        txtTongThanhTien.setText(String.valueOf(tongTT));

        txtMaSachNhap.setText("");
        txtTenSachNhap.setText("");
        txtTacGiaNhap.setText("");
        txtNXBNhap.setText("");
        txtNamXBNhap.setText("");
        txtTheLoaiNhap.setText("");
        txtSoluongNhap.setText("");
        txtDonGiaNhap.setText("");
        txtMaSachNhap.requestFocus();

        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnThemSachActionPerformed

    private void tblEmptyThongTinNhapSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmptyThongTinNhapSachMouseClicked
        // TODO add your handling code here:
        int rowSL = tblEmptyThongTinNhapSach.getSelectedRow();
        txtMaSachNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 0));
        txtTenSachNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 1));
        txtTacGiaNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 2));
        txtNXBNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 3));
        txtNamXBNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 4));
        txtTheLoaiNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 5));
        txtSoluongNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 6));
        txtDonGiaNhap.setText((String)tblEmptyThongTinNhapSach.getValueAt(rowSL, 7));
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tblEmptyThongTinNhapSachMouseClicked


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
            java.util.logging.Logger.getLogger(DioLogNhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DioLogNhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DioLogNhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DioLogNhapSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DioLogNhapSach dialog = new DioLogNhapSach(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnThemSach;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDGN;
    private javax.swing.JLabel lblMS;
    private javax.swing.JLabel lblNXB;
    private javax.swing.JLabel lblNamXB;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblTG;
    private javax.swing.JLabel lblTS;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JTable tblEmptyThongTinNhapSach;
    private javax.swing.JTextField txtDonGiaNhap;
    private javax.swing.JTextField txtMaSachNhap;
    private javax.swing.JTextField txtNXBNhap;
    private javax.swing.JTextField txtNamXBNhap;
    private javax.swing.JTextField txtNgayNhapSach;
    private javax.swing.JTextField txtSoluongNhap;
    private javax.swing.JTextField txtTacGiaNhap;
    private javax.swing.JTextField txtTenSachNhap;
    private javax.swing.JTextField txtTheLoaiNhap;
    private javax.swing.JTextField txtTongThanhTien;
    // End of variables declaration//GEN-END:variables

    private void ketNoiDL() {
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
    
    private boolean ktMaSach(String mas){
        try {
            if (Sach_DAO.check_Ma_Sach(mas)){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void hienThimacDinh(){
        txtNgayNhapSach.setEditable(false);        
        //---Lấy ngày hiện tại làm ngày nhập sách
        LocalDate tg = LocalDate.now();
        txtNgayNhapSach.setText(tg.format(DateTimeFormatter.ISO_LOCAL_DATE).toString());   
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
    }    

    private void hienThiNhaCungCap() {
        NhaCungCap_DAO.getTenNCC(cboNhaCungCap);
    }
}
