/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_QLBanSach;

import connect_database.MessageDialog;
import connect_database.SQLServerProvider;
import dao.BaoCao_DAO;
import dao.CT_DonNhapHang_DAO;
import dao.CT_HoaDonBan_DAO;
import dao.HoaDonBan_DAO;
import dao.HoaDonNhap_DAO;
import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import dao.NhanVien_DAO;
import dao.Sach_DAO;
import dao.TonKho_DAO;
import doi_tuong.CT_HoaDonBan;
import doi_tuong.CT_HoaDonNhap;
import doi_tuong.HoaDonBan;
import doi_tuong.HoaDonNhap;
import doi_tuong.NhanVien;
import doi_tuong.Sach;
import doi_tuong.TonKho;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import load_image.ImageHelper;


public class Home extends javax.swing.JFrame {

    CardLayout cardLayout;
    CardLayout cardLayout2;
    
    String maNV = "";
    Connection connect = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null; 
    byte[] personalImage;
    
    //----------------------------------------------------------------pnTrangChu
    DefaultTableModel dtmTrangChu1;
    DefaultTableModel dtmCTHD;
    DefaultTableModel dtmHD;
    double tongThanhTien = 0;

    //------------------------------------------------------------pnThongTinSach
    DefaultTableModel dtmThongTinSach;
    
    //-------------------------------------------------------pnThongTInKhachHAng
    DefaultTableModel dtmTTKH;
    
    //--------------------------------------------------------pnThongTInNhanVien
    DefaultTableModel dtmTTNV;
    
    //------------------------------------------------------pnThongTInNhaCungCap
    DefaultTableModel dtmNCC;    
    
    //----------------------------------------------------------pnHoaDonNhapSach
    DefaultTableModel dtmHDNS;
    DefaultTableModel dtmCT_HDNS;
    DefaultTableModel dtmNhapSach;
    DefaultTableModel dtmHDTHANHTOAN;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    double tongTT = 0;       
    
    //-----------------------------------------------------------pnHoaDonBanSach
    DefaultTableModel dtmHDBanSach;
    DefaultTableModel dtmCT_HDBanSach;    
    
    //------------------------------------------------------------------pnBaoCao 
    CardLayout cardLayout3;
    DefaultTableModel dtmBCDT;
    
    public Home(){ 
        initComponents();
        this.setTitle("CHƯƠNG TRÌNH QUẢN LÝ CỬA HÀNG BÁN SÁCH");
        this.setExtendedState(Home.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        
        cardLayout = (CardLayout)(pnRight.getLayout());

        //----------------------------------------------------------------------
        //--------------Kết nối cơ sở dữ liệu
        connectData();
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/nv1_1.jpg"));
        icon = new ImageIcon(ImageHelper.resize(icon.getImage(), 152, 147));        
        lblAnhNV.setIcon(icon);
        //----------------------------------------------------------------------
        //--------------------------Tạo chắc năng cho các card con--------------
        //------------------------------------------------------------pnTrangChu
        //-----SetLayOut CardLayout cho pnThongTinTimKiem
        cardLayout2 = (CardLayout)pnThongTinTimKiem.getLayout();
        dtmTrangChu1 = (DefaultTableModel) tblThongTinSach.getModel();
        dtmTrangChu1.setRowCount(0);
        dtmCTHD = (DefaultTableModel)tblCTHoaDon.getModel();
        dtmCTHD.setRowCount(0);
        dtmHD = (DefaultTableModel) tblHD.getModel();
        dtmHD.setRowCount(0);
        txtTimKiemTenSach.requestFocus();
        showMD_pnTrangChu();
        
        Sach_DAO.showDL_Combox_TimKiem(cboTheLoai, cboTacGia);
        
        //--Hiển thị tìm kiếm theo thể loại
        //timKiemTheoTL();
        Sach_DAO.showDL_Combox_TimKiem_TheoTL(cboTacGiaTheoTheLoai, cboTheLoai.getSelectedItem().toString().trim());        
        cboGiaBanTheoTL(); 
        
        //--Hiển thị tìm kiếm theo tác giả
        //timKiemTheoTG();
        Sach_DAO.showDL_Combox_TimKiem_TheoTG(cboTheLoaiTheoTG, cboTacGia.getSelectedItem().toString().trim());
        cboGiaBanTheoTG();
        
        //--Hiển thị tìm kiếm theo giá bán
        cboTimTheoGiaBan();
        
        //--Hiển thị thông tin mặc định cho bảng tblThongTinSach
        //hienThiDLThongTinSach();
        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
        
        //----------------------------------------------------------------------
        //--------------------------------------------------------pnThongTinSach
        dtmThongTinSach = (DefaultTableModel)tblThongTinCTSach.getModel();
        //hienThiDL();
        Sach_DAO.showDL_CT_ThongTinSach(dtmThongTinSach);
        showMD_pnThongTinSach();
        
        //----------------------------------------------------------------------
        //---------------------------------------------------pnThongTInKhachHAng
        dtmTTKH = (DefaultTableModel)tblThongTinKH.getModel();
        KhachHang_DAO.showDS_KhachHang(dtmTTKH);
        showMD_TTKH();
        
        //----------------------------------------------------------------------
        //-----------------------------------------------------pnThongTInNhaVien
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/img/nv1_1.jpg"));
        icon1 = new ImageIcon(ImageHelper.resize(icon1.getImage(), 250, 270));
        lblanhNV.setIcon(icon1);    
        
        dtmTTNV = (DefaultTableModel)tblDSNhanVien.getModel();
        NhanVien_DAO.showDS_NhanVien(dtmTTNV);
        showMD_TTNV();
        
        //----------------------------------------------------------------------
        //--------------------------------------------------pnThongTInNhaCungCap        
        dtmNCC = (DefaultTableModel)tblDSNCC.getModel();
        NhaCungCap_DAO.showDS_NCC(dtmNCC);
        showMD_pnNCC();
        
        //----------------------------------------------------------------------
        //------------------------------------------------------pnHoaDonNhapSach        
        dtmHDNS = (DefaultTableModel)tblDonNhapHang.getModel();
        HoaDonNhap_DAO.showDS_DonNhapHang(dtmHDNS);
        tblDonNhapHang.setRowSelectionInterval(0, 0);
        dtmCT_HDNS = (DefaultTableModel)tblCT_DonNhapHang.getModel();       
        String md = (String)tblDonNhapHang.getValueAt(0, 0);
        HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, md);
        
        txtNCC.setEditable(false);
        txtTenMaSach.setEditable(false);
        
        dtmNhapSach = (DefaultTableModel)tblEmptyThongTinNhapSach.getModel();
        hienThimacDinh();hienThiNhaCungCap();
        dtmNhapSach.setRowCount(0);
        
        dtmHDTHANHTOAN = (DefaultTableModel)tblHOADONNHAP.getModel();
        dtmHDTHANHTOAN.setRowCount(0);
        
        
        //----------------------------------------------------------------------
        //-------------------------------------------------------pnHoaDonBanSach
        dtmHDBanSach = (DefaultTableModel) tblThongTinDonBan.getModel();
        dtmCT_HDBanSach = (DefaultTableModel)tblCT_ThongTinDonBan.getModel();
        HoaDonBan_DAO.showDS_DonBanHang(dtmHDBanSach);
        tblThongTinDonBan.setRowSelectionInterval(0, 0);
        String maDOnBan = tblThongTinDonBan.getValueAt(0, 0).toString();
        HoaDonBan_DAO.showDS_CT_DonBanHang(dtmCT_HDBanSach, maDOnBan);
        
        txtDTKH.setEditable(false);
        
        //----------------------------------------------------------------------
        //--------------------------------------------------------------pnBaoCao
        cardLayout3 = (CardLayout)pnBC.getLayout();
        loadCBO();
        dtmBCDT = (DefaultTableModel)tblThongTinBC.getModel();
        dtmBCDT.setRowCount(0);
        txtTongDT.setEditable(false);
    }
    
    public Home(String tenNV, String maNV) {
        //----------------------------------------------------------------------
        initComponents();
        this.setTitle("CHƯƠNG TRÌNH QUẢN LÝ CỬA HÀNG BÁN SÁCH");
        this.setExtendedState(Home.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        
        //-----SetLayout tạo các frame chức năng
        cardLayout = (CardLayout)(pnRight.getLayout());
        
        //----------------------------------------------------------------------
        //--------------Kết nối cơ sở dữ liệu
        connectData();
        //-----Lấy tên cho lblNhanVien
        lblTenNV.setText(tenNV);
        this.maNV = maNV;
        ImageIcon icon = show_anh_NV(maNV);
        icon = new ImageIcon(ImageHelper.resize(icon.getImage(), 152, 147));        
        lblAnhNV.setIcon(icon);
        //----------------------------------------------------------------------
        //--------------------------Tạo chắc năng cho các card con--------------
        //------------------------------------------------------------pnTrangChu
        //-----SetLayOut CardLayout cho pnThongTinTimKiem
        cardLayout2 = (CardLayout)pnThongTinTimKiem.getLayout();
        dtmTrangChu1 = (DefaultTableModel) tblThongTinSach.getModel();
        dtmTrangChu1.setRowCount(0);
        dtmCTHD = (DefaultTableModel)tblCTHoaDon.getModel();
        dtmCTHD.setRowCount(0);
        dtmHD = (DefaultTableModel) tblHD.getModel();
        dtmHD.setRowCount(0);
        txtTimKiemTenSach.requestFocus();
        showMD_pnTrangChu();
        
        Sach_DAO.showDL_Combox_TimKiem(cboTheLoai, cboTacGia);
        
        //--Hiển thị tìm kiếm theo thể loại
        Sach_DAO.showDL_Combox_TimKiem_TheoTL(cboTacGiaTheoTheLoai, cboTheLoai.getSelectedItem().toString().trim());        
        cboGiaBanTheoTL(); 
        
        //--Hiển thị tìm kiếm theo tác giả
        Sach_DAO.showDL_Combox_TimKiem_TheoTG(cboTheLoaiTheoTG, cboTacGia.getSelectedItem().toString().trim());
        cboGiaBanTheoTG();
        
        //--Hiển thị tìm kiếm theo giá bán
        cboTimTheoGiaBan();
        
        //--Hiển thị thông tin mặc định cho bảng tblThongTinSach
        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
        
        //----------------------------------------------------------------------
        //--------------------------------------------------------pnThongTinSach
        dtmThongTinSach = (DefaultTableModel)tblThongTinCTSach.getModel();
        Sach_DAO.showDL_CT_ThongTinSach(dtmThongTinSach);
        showMD_pnThongTinSach();
        
        //----------------------------------------------------------------------
        //---------------------------------------------------pnThongTInKhachHAng
        dtmTTKH = (DefaultTableModel)tblThongTinKH.getModel();
        KhachHang_DAO.showDS_KhachHang(dtmTTKH);
        showMD_TTKH();
        
        //----------------------------------------------------------------------
        //-----------------------------------------------------pnThongTInNhaVien
        dtmTTNV = (DefaultTableModel)tblDSNhanVien.getModel();
        NhanVien_DAO.showDS_NhanVien(dtmTTNV);      
        showMD_TTNV();
        tblDSNhanVien.setRowSelectionInterval(0, 0);
        String m = tblDSNhanVien.getValueAt(0, 0).toString().trim();
        ImageIcon icon1 = show_anh_NV(m);
        icon1 = new ImageIcon(ImageHelper.resize(icon1.getImage(), 250, 270));        
        lblanhNV.setIcon(icon1);
        
        //----------------------------------------------------------------------
        //--------------------------------------------------pnThongTInNhaCungCap        
        dtmNCC = (DefaultTableModel)tblDSNCC.getModel();
        NhaCungCap_DAO.showDS_NCC(dtmNCC);
        showMD_pnNCC();
        
        //----------------------------------------------------------------------
        //------------------------------------------------------pnHoaDonNhapSach  
        macdinh();
        dtmHDNS = (DefaultTableModel)tblDonNhapHang.getModel();
        HoaDonNhap_DAO.showDS_DonNhapHang(dtmHDNS);
        tblDonNhapHang.setRowSelectionInterval(0, 0);
        dtmCT_HDNS = (DefaultTableModel)tblCT_DonNhapHang.getModel();       
        String md = (String)tblDonNhapHang.getValueAt(0, 0);
        HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, md);
        
        txtNCC.setEditable(false);
        txtTenMaSach.setEditable(false);
        
        
        dtmNhapSach = (DefaultTableModel)tblEmptyThongTinNhapSach.getModel();
        hienThimacDinh();hienThiNhaCungCap();
        dtmNhapSach.setRowCount(0);
        
        dtmHDTHANHTOAN = (DefaultTableModel)tblHOADONNHAP.getModel();
        dtmHDTHANHTOAN.setRowCount(0);        
        //----------------------------------------------------------------------
        //-------------------------------------------------------pnHoaDonBanSach
        dtmHDBanSach = (DefaultTableModel) tblThongTinDonBan.getModel();
        dtmCT_HDBanSach = (DefaultTableModel)tblCT_ThongTinDonBan.getModel();
        HoaDonBan_DAO.showDS_DonBanHang(dtmHDBanSach);
        String maDOnBan = tblThongTinDonBan.getValueAt(0, 0).toString();
        HoaDonBan_DAO.showDS_CT_DonBanHang(dtmCT_HDBanSach, maDOnBan);
        
        txtDTKH.setEditable(false);
        
        //----------------------------------------------------------------------
        //--------------------------------------------------------------pnBaoCao
        cardLayout3 = (CardLayout)pnBC.getLayout();
        loadCBO();
        dtmBCDT = (DefaultTableModel)tblThongTinBC.getModel();
        dtmBCDT.setRowCount(0);
        txtTongDT.setEditable(false);       
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpTimKiemSach = new javax.swing.ButtonGroup();
        grpLocKH = new javax.swing.ButtonGroup();
        grpLocHDNhapHang = new javax.swing.ButtonGroup();
        grpSXCTDonNhapHang = new javax.swing.ButtonGroup();
        grpTimKiemDonBanHang = new javax.swing.ButtonGroup();
        grpSapXepHDBan = new javax.swing.ButtonGroup();
        grpQuyBC = new javax.swing.ButtonGroup();
        grpDKBC = new javax.swing.ButtonGroup();
        pnLeft = new javax.swing.JPanel();
        pnCauLenh = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnThongTinSach = new javax.swing.JButton();
        btnThongTinKH = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnHDNhapSach = new javax.swing.JButton();
        btnHoaDonBanSach = new javax.swing.JButton();
        btnBCDoanhThu = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblAnhNV = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        btnLogouts = new javax.swing.JButton();
        pnRight = new javax.swing.JPanel();
        pnTrangChu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnTraCuuSach = new javax.swing.JPanel();
        pnThongTinTimKiem = new javax.swing.JPanel();
        pnTimTheoTenSach = new javax.swing.JPanel();
        txtTimKiemTenSach = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        pnTimTheoTheLoai = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboTacGiaTheoTheLoai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboGiaBanTheoTheLoai = new javax.swing.JComboBox<>();
        cboTheLoai = new javax.swing.JComboBox<>();
        pnTimKiemTheoTacGia = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        cboGiaBanTheoTG = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        cboTheLoaiTheoTG = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        cboTacGia = new javax.swing.JComboBox<>();
        pnTimKiemTheoGia = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        cboTimTheoGiaBan = new javax.swing.JComboBox<>();
        radTenSach = new javax.swing.JRadioButton();
        radTacGia = new javax.swing.JRadioButton();
        radGiaBan = new javax.swing.JRadioButton();
        radTheLoai = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblThongTinSach = new javax.swing.JTable();
        btnThemVaoHD = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnRaPhieu = new javax.swing.JButton();
        btnXoaKhoiHoaDon1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTT = new javax.swing.JTextField();
        txtTTT = new javax.swing.JTextField();
        btnThemMaKH = new javax.swing.JButton();
        txtKH = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnHuyHD = new javax.swing.JButton();
        pnThongTinSach = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaS = new javax.swing.JTextField();
        txtTenS = new javax.swing.JTextField();
        txtTacGia = new javax.swing.JTextField();
        txtNXB = new javax.swing.JTextField();
        txtNamXB = new javax.swing.JTextField();
        txtTheLoai = new javax.swing.JTextField();
        txtTonKho = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        btnCapNhat = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThongTinCTSach = new javax.swing.JTable();
        pnThongTinKH = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtTinhTrang = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblThongTinKH = new javax.swing.JTable();
        btnLoc = new javax.swing.JButton();
        radTVV = new javax.swing.JRadioButton();
        radTVT = new javax.swing.JRadioButton();
        btnSuaThongTin = new javax.swing.JButton();
        btnThemKH = new javax.swing.JButton();
        pnThongTinNV = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblanhNV = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDSNhanVien = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblTENNV = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnCapNhatThongTinNV = new javax.swing.JButton();
        btnTimKiemNV = new javax.swing.JButton();
        pnNhaCungCap = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDSNCC = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        txtLienHeNCC = new javax.swing.JTextField();
        txtDiaChiNCC = new javax.swing.JTextField();
        txtTenNCC = new javax.swing.JTextField();
        txtEmailNCC = new javax.swing.JTextField();
        btnThemNCC1 = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();
        pnHDNhapSach = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDonNhapHang = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        radNgayLapDon = new javax.swing.JRadioButton();
        radSoLuong = new javax.swing.JRadioButton();
        radTongTien = new javax.swing.JRadioButton();
        txtNCC = new javax.swing.JTextField();
        radNCC = new javax.swing.JRadioButton();
        btnMD = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblCT_DonNhapHang = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        radGiaNhap = new javax.swing.JRadioButton();
        radSLNhap = new javax.swing.JRadioButton();
        txtTenMaSach = new javax.swing.JTextField();
        radTenMaSach = new javax.swing.JRadioButton();
        btnMD1 = new javax.swing.JButton();
        btnSearch1 = new javax.swing.JButton();
        btnThemHDMoi = new javax.swing.JButton();
        pnHDBanSach = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblThongTinDonBan = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        radSL = new javax.swing.JRadioButton();
        radTT = new javax.swing.JRadioButton();
        radNgayBan = new javax.swing.JRadioButton();
        radSDT = new javax.swing.JRadioButton();
        txtDTKH = new javax.swing.JTextField();
        btnLM = new javax.swing.JButton();
        btnSort = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblCT_ThongTinDonBan = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        radSLSX = new javax.swing.JRadioButton();
        radGBSX = new javax.swing.JRadioButton();
        btnTK = new javax.swing.JButton();
        pnBCDoanhThu = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        btnBaoCao = new javax.swing.JButton();
        pnBC = new javax.swing.JPanel();
        pnBCNgay = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        cboBCTNgay_Thang = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        cboBCNgay = new javax.swing.JComboBox<>();
        cboBCTNgay_Thang_Nam = new javax.swing.JComboBox<>();
        pnBCThang = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        cboBCTThang_Nam = new javax.swing.JComboBox<>();
        cboBCTThang = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        pnBCQuy = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        cboBCTNam = new javax.swing.JComboBox<>();
        radqui1 = new javax.swing.JRadioButton();
        radqui2 = new javax.swing.JRadioButton();
        radBCNgay = new javax.swing.JRadioButton();
        radBCThang = new javax.swing.JRadioButton();
        radBCQuy = new javax.swing.JRadioButton();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblThongTinBC = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        txtTongDT = new javax.swing.JTextField();
        btnRaPhieuBC = new javax.swing.JButton();
        pnHoaDonNhap = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        txtNgayNhapSach = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
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
        jPanel27 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        btnThemSach = new javax.swing.JButton();
        btnRAPHIEUNHAP = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmptyThongTinNhapSach = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblHOADONNHAP = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        txtTTTTTTTTTT = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        txtTongThanhTien = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mnuDangXuat = new javax.swing.JMenuItem();
        mnuThoat = new javax.swing.JMenuItem();
        mnuThongTinNV = new javax.swing.JMenu();
        mnuXoaNV = new javax.swing.JMenuItem();
        mnuTKNhanVien = new javax.swing.JMenuItem();
        mnuThemNV = new javax.swing.JMenuItem();
        mnuCTTTNhanVien = new javax.swing.JMenuItem();
        mnuNCC = new javax.swing.JMenu();
        mnuXoaNCC = new javax.swing.JMenuItem();
        mnuTTCTNCC = new javax.swing.JMenuItem();
        mnuKH = new javax.swing.JMenu();
        mnuXoaKH = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnLeft.setBackground(new java.awt.Color(204, 51, 0));
        pnLeft.setPreferredSize(new java.awt.Dimension(300, 665));

        btnHome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        btnHome.setText("TRANG CHỦ");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnThongTinSach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThongTinSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/book.png"))); // NOI18N
        btnThongTinSach.setText("THÔNG TIN SÁCH");
        btnThongTinSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinSachActionPerformed(evt);
            }
        });

        btnThongTinKH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThongTinKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/customer.png"))); // NOI18N
        btnThongTinKH.setText("THÔNG TIN KHÁCH HÀNG");
        btnThongTinKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinKHActionPerformed(evt);
            }
        });

        btnHDNhapSach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHDNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bill.png"))); // NOI18N
        btnHDNhapSach.setText("HÓA ĐƠN NHẬP SÁCH");
        btnHDNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDNhapSachActionPerformed(evt);
            }
        });

        btnHoaDonBanSach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHoaDonBanSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bill.png"))); // NOI18N
        btnHoaDonBanSach.setText("HÓA ĐƠN BÁN SÁCH");
        btnHoaDonBanSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonBanSachActionPerformed(evt);
            }
        });

        btnBCDoanhThu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBCDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        btnBCDoanhThu.setText("BÁO CÁO DOANH THU");
        btnBCDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBCDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnCauLenhLayout = new javax.swing.GroupLayout(pnCauLenh);
        pnCauLenh.setLayout(pnCauLenhLayout);
        pnCauLenhLayout.setHorizontalGroup(
            pnCauLenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongTinKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongTinSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(pnCauLenhLayout.createSequentialGroup()
                .addGroup(pnCauLenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHDNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoaDonBanSach, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBCDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnCauLenhLayout.setVerticalGroup(
            pnCauLenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCauLenhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongTinSach, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongTinKH, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHDNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDonBanSach, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBCDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblTenNV.setBackground(new java.awt.Color(255, 58, 95));
        lblTenNV.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(255, 255, 0));
        lblTenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNV.setText("TÊN NHÂN VIÊN");

        btnLogouts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/log-out).png"))); // NOI18N
        btnLogouts.setText("Đăng Xuất");
        btnLogouts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnLeftLayout = new javax.swing.GroupLayout(pnLeft);
        pnLeft.setLayout(pnLeftLayout);
        pnLeftLayout.setHorizontalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnCauLenh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnLeftLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLeftLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnLogouts, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnLeftLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnLeftLayout.setVerticalGroup(
            pnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogouts, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnCauLenh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnLeft, java.awt.BorderLayout.LINE_START);

        pnRight.setBackground(new java.awt.Color(153, 255, 0));
        pnRight.setLayout(new java.awt.CardLayout());

        pnTrangChu.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 153, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN SÁCH");
        pnTrangChu.add(jLabel2);
        jLabel2.setBounds(0, 0, 1010, 70);

        pnTraCuuSach.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "TÌm kiếm sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        pnThongTinTimKiem.setLayout(new java.awt.CardLayout());

        txtTimKiemTenSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Nhập tên sách:");

        btnTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tk.png"))); // NOI18N
        btnTimKiem.setText("TÌM KIẾM");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTimTheoTenSachLayout = new javax.swing.GroupLayout(pnTimTheoTenSach);
        pnTimTheoTenSach.setLayout(pnTimTheoTenSachLayout);
        pnTimTheoTenSachLayout.setHorizontalGroup(
            pnTimTheoTenSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimTheoTenSachLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTimTheoTenSachLayout.setVerticalGroup(
            pnTimTheoTenSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimTheoTenSachLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnTimTheoTenSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pnThongTinTimKiem.add(pnTimTheoTenSach, "pnTimTheoTen");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Thể loại:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Tác giả:");

        cboTacGiaTheoTheLoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Giá bán:");

        cboGiaBanTheoTheLoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        cboTheLoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboTheLoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboTheLoaiFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pnTimTheoTheLoaiLayout = new javax.swing.GroupLayout(pnTimTheoTheLoai);
        pnTimTheoTheLoai.setLayout(pnTimTheoTheLoaiLayout);
        pnTimTheoTheLoaiLayout.setHorizontalGroup(
            pnTimTheoTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimTheoTheLoaiLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboTacGiaTheoTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboGiaBanTheoTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(pnTimTheoTheLoaiLayout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTimTheoTheLoaiLayout.setVerticalGroup(
            pnTimTheoTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimTheoTheLoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTimTheoTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(pnTimTheoTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTacGiaTheoTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGiaBanTheoTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pnThongTinTimKiem.add(pnTimTheoTheLoai, "pnTimTheoTheLoai");

        jLabel56.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel56.setText("Giá bán:");

        cboGiaBanTheoTG.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel57.setText("Thể loại:");

        cboTheLoaiTheoTG.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel58.setText("Tác giả:");

        cboTacGia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboTacGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboTacGiaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pnTimKiemTheoTacGiaLayout = new javax.swing.GroupLayout(pnTimKiemTheoTacGia);
        pnTimKiemTheoTacGia.setLayout(pnTimKiemTheoTacGiaLayout);
        pnTimKiemTheoTacGiaLayout.setHorizontalGroup(
            pnTimKiemTheoTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemTheoTacGiaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboTheLoaiTheoTG, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboGiaBanTheoTG, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(pnTimKiemTheoTacGiaLayout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTimKiemTheoTacGiaLayout.setVerticalGroup(
            pnTimKiemTheoTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemTheoTacGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTimKiemTheoTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(pnTimKiemTheoTacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTheLoaiTheoTG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGiaBanTheoTG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pnThongTinTimKiem.add(pnTimKiemTheoTacGia, "pnTImTheoTG");

        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel52.setText("Giá bán:");

        cboTimTheoGiaBan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnTimKiemTheoGiaLayout = new javax.swing.GroupLayout(pnTimKiemTheoGia);
        pnTimKiemTheoGia.setLayout(pnTimKiemTheoGiaLayout);
        pnTimKiemTheoGiaLayout.setHorizontalGroup(
            pnTimKiemTheoGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemTheoGiaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTimTheoGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        pnTimKiemTheoGiaLayout.setVerticalGroup(
            pnTimKiemTheoGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemTheoGiaLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnTimKiemTheoGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimTheoGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pnThongTinTimKiem.add(pnTimKiemTheoGia, "pnTimTheoGiaBan");

        grpTimKiemSach.add(radTenSach);
        radTenSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTenSach.setSelected(true);
        radTenSach.setText("Tên sách");
        radTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTenSachActionPerformed(evt);
            }
        });

        grpTimKiemSach.add(radTacGia);
        radTacGia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTacGia.setText("Tác giả");
        radTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTacGiaActionPerformed(evt);
            }
        });

        grpTimKiemSach.add(radGiaBan);
        radGiaBan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radGiaBan.setText("Giá bán");
        radGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGiaBanActionPerformed(evt);
            }
        });

        grpTimKiemSach.add(radTheLoai);
        radTheLoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTheLoai.setText("Thể loại");
        radTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTheLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTraCuuSachLayout = new javax.swing.GroupLayout(pnTraCuuSach);
        pnTraCuuSach.setLayout(pnTraCuuSachLayout);
        pnTraCuuSachLayout.setHorizontalGroup(
            pnTraCuuSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTraCuuSachLayout.createSequentialGroup()
                .addComponent(pnThongTinTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(233, 233, 233))
            .addGroup(pnTraCuuSachLayout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(radTenSach)
                .addGap(18, 18, 18)
                .addComponent(radTheLoai)
                .addGap(12, 12, 12)
                .addComponent(radTacGia)
                .addGap(18, 18, 18)
                .addComponent(radGiaBan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTraCuuSachLayout.setVerticalGroup(
            pnTraCuuSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTraCuuSachLayout.createSequentialGroup()
                .addGroup(pnTraCuuSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radTenSach)
                    .addComponent(radTacGia)
                    .addComponent(radGiaBan)
                    .addComponent(radTheLoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnThongTinTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnTrangChu.add(pnTraCuuSach);
        pnTraCuuSach.setBounds(20, 77, 780, 170);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tblThongTinSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblThongTinSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Thể loại", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinSachMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblThongTinSach);

        btnThemVaoHD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnThemVaoHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnThemVaoHD.setText("THÊM VÀO HÓA ĐƠN");
        btnThemVaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoHDActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        btnLamMoi.setText("LÀM MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemVaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 23, Short.MAX_VALUE)
                        .addComponent(btnThemVaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        pnTrangChu.add(jPanel3);
        jPanel3.setBounds(20, 260, 780, 220);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Chi tiết hóa đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        btnRaPhieu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnRaPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/raphieu.png"))); // NOI18N
        btnRaPhieu.setText("RA PHIẾU");
        btnRaPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaPhieuActionPerformed(evt);
            }
        });

        btnXoaKhoiHoaDon1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXoaKhoiHoaDon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXoaKhoiHoaDon1.setText("XÓA KHỎI HÓA ĐƠN");
        btnXoaKhoiHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoiHoaDon1ActionPerformed(evt);
            }
        });

        tblCTHoaDon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Số  lượng ", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaKhoiHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRaPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnXoaKhoiHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(btnRaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );

        pnTrangChu.add(jPanel4);
        jPanel4.setBounds(20, 490, 790, 230);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Phiếu thanh toán", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("HÓA ĐƠN");

        tblHD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sách", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHD);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("TỔNG TIỀN:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("MÃ KHÁCH HÀNG");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("TỔNG THÀNH TIỀN:");

        txtTT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTTT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnThemMaKH.setText("jButton1");
        btnThemMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMaKHActionPerformed(evt);
            }
        });

        txtKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKHMouseClicked(evt);
            }
        });
        txtKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKHActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuyHD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHuyHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnHuyHD.setText("HỦY HÓA ĐƠN");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThemMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pnTrangChu.add(jPanel5);
        jPanel5.setBounds(810, 80, 280, 640);

        pnRight.add(pnTrangChu, "pnTrangChu");

        pnThongTinSach.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("THÔNG TIN SÁCH");
        pnThongTinSach.add(jLabel10);
        jLabel10.setBounds(0, 0, 1560, 140);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 30))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Mã sách:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Tên sách:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Tác giả");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("Nhà xuất bản:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText("Năm xuất bản:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setText("Giá bán:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setText("Thể loại:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setText("Tồn kho:");

        txtMaS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTenS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTacGia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtNXB.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtNamXB.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTheLoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTonKho.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtGiaBan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenS, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaS, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(122, 122, 122))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pnThongTinSach.add(jPanel6);
        jPanel6.setBounds(70, 140, 1430, 290);

        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lm.png"))); // NOI18N
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnThongTinSach.add(btnCapNhat);
        btnCapNhat.setBounds(800, 450, 240, 50);

        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/settings.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnThongTinSach.add(btnSua);
        btnSua.setBounds(490, 450, 240, 50);

        tblThongTinCTSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblThongTinCTSach.setModel(new javax.swing.table.DefaultTableModel(
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
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinCTSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinCTSachMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThongTinCTSach);

        pnThongTinSach.add(jScrollPane4);
        jScrollPane4.setBounds(70, 530, 1430, 340);

        pnRight.add(pnThongTinSach, "pnThongTInSach");

        pnThongTinKH.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("THÔNG TIN KHÁCH HÀNG");
        pnThongTinKH.add(jLabel19);
        jLabel19.setBounds(0, 20, 1560, 140);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin chi tiết", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Mã khách hàng:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setText("Tên khách hàng:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setText("Số điện thoại:");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Email:");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel24.setText("Địa chỉ:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel25.setText("Tình trạng:");

        txtMaKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtTinhTrang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTinhTrang, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addComponent(txtEmail)))
                    .addComponent(txtDiaChi))
                .addGap(27, 27, 27))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pnThongTinKH.add(jPanel7);
        jPanel7.setBounds(50, 170, 1190, 280);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin chi tiết", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        tblThongTinKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblThongTinKH.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinKHMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblThongTinKH);

        btnLoc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter.png"))); // NOI18N
        btnLoc.setText("LỌC");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        grpLocKH.add(radTVV);
        radTVV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTVV.setSelected(true);
        radTVV.setText("Thành viên VIP");

        grpLocKH.add(radTVT);
        radTVT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTVT.setText("Thành viên Thường");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radTVT)
                            .addComponent(radTVV))
                        .addGap(0, 103, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(radTVV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radTVT)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnThongTinKH.add(jPanel8);
        jPanel8.setBounds(50, 500, 1530, 340);

        btnSuaThongTin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSuaThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lm.png"))); // NOI18N
        btnSuaThongTin.setText("CẬP NHẬT THÔNG TIN");
        btnSuaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinActionPerformed(evt);
            }
        });
        pnThongTinKH.add(btnSuaThongTin);
        btnSuaThongTin.setBounds(1250, 340, 310, 66);

        btnThemKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnThemKH.setText("THÊM THÀNH VIÊN");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });
        pnThongTinKH.add(btnThemKH);
        btnThemKH.setBounds(1250, 210, 310, 66);

        pnRight.add(pnThongTinKH, "pnThongTinKH");

        pnThongTinNV.setLayout(null);

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("THÔNG TIN NHÂN VIÊN");
        pnThongTinNV.add(jLabel26);
        jLabel26.setBounds(0, 0, 1560, 140);

        jPanel9.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblanhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblanhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );

        pnThongTinNV.add(jPanel9);
        jPanel9.setBounds(90, 150, 250, 270);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Danh sách nhân viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        tblDSNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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
        jScrollPane6.setViewportView(tblDSNhanVien);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1410, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnThongTinNV.add(jPanel10);
        jPanel10.setBounds(60, 580, 1440, 370);

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel27.setText("Tên nhân viên:");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel28.setText("Địa chỉ:");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel29.setText("Mã nhân viên:");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel30.setText("Ngày sinh:");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel31.setText("Giới tính:");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel32.setText("Số điện thoại:");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel33.setText("Email:");

        lblMaNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblTENNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblNgaySinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblSDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblGioiTinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblEmail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTENNV, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTENNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnThongTinNV.add(jPanel11);
        jPanel11.setBounds(370, 170, 840, 220);

        btnCapNhatThongTinNV.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnCapNhatThongTinNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lm.png"))); // NOI18N
        btnCapNhatThongTinNV.setText("CẬP NHẬT THÔNG TIN");
        btnCapNhatThongTinNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatThongTinNVActionPerformed(evt);
            }
        });

        btnTimKiemNV.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        btnTimKiemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tk.png"))); // NOI18N
        btnTimKiemNV.setText("TRA CỨU THÔNG TIN");
        btnTimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(btnCapNhatThongTinNV, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhatThongTinNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnThongTinNV.add(jPanel12);
        jPanel12.setBounds(210, 470, 1070, 80);

        pnRight.add(pnThongTinNV, "pnThongTinNV");

        pnNhaCungCap.setLayout(null);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("NHÀ CUNG CẤP");
        pnNhaCungCap.add(jLabel34);
        jLabel34.setBounds(0, 0, 1560, 140);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin chi tiết", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N

        tblDSNCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblDSNCC.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDSNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNCCMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblDSNCC);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1440, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );

        pnNhaCungCap.add(jPanel14);
        jPanel14.setBounds(50, 520, 1460, 280);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin chi tiết", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 20))); // NOI18N

        jLabel40.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel40.setText("Mã nhà cung cấp:");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel41.setText("Tên nhà cung cấp:");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel42.setText("Liên hệ:");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel43.setText("Email:");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel44.setText("Địa chỉ:");

        txtMaNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtLienHeNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtDiaChiNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTenNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtEmailNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnThemNCC1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnThemNCC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnThemNCC1.setText("THÊM");
        btnThemNCC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCC1ActionPerformed(evt);
            }
        });

        btnSuaNCC.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSuaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lm.png"))); // NOI18N
        btnSuaNCC.setText("CẬP NHẬT SỬA ĐỔI");
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLienHeNCC)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDiaChiNCC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(btnThemNCC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(txtDiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnThemNCC1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pnNhaCungCap.add(jPanel15);
        jPanel15.setBounds(50, 190, 1460, 270);

        pnRight.add(pnNhaCungCap, "pnNhaCungCap");

        pnHDNhapSach.setLayout(null);

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("NHẬP SÁCH");
        pnHDNhapSach.add(jLabel35);
        jLabel35.setBounds(0, 0, 1560, 140);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin đơn hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        tblDonNhapHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblDonNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã đơn", "Tên nhà cung cấp", "Người lập đơn", "Ngày nhập", "Số lượng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonNhapHangMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblDonNhapHang);

        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter.png"))); // NOI18N
        btnSearch.setText("LỌC");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Tìm kiếm & sắp xếp", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        grpLocHDNhapHang.add(radNgayLapDon);
        radNgayLapDon.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radNgayLapDon.setText("Ngày lập đơn");
        radNgayLapDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNgayLapDonActionPerformed(evt);
            }
        });

        grpLocHDNhapHang.add(radSoLuong);
        radSoLuong.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radSoLuong.setText("Số lượng");
        radSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSoLuongActionPerformed(evt);
            }
        });

        grpLocHDNhapHang.add(radTongTien);
        radTongTien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTongTien.setText("Tổng tiền");
        radTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTongTienActionPerformed(evt);
            }
        });

        txtNCC.setText("Nhập tên nhà cung cấp");

        grpLocHDNhapHang.add(radNCC);
        radNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radNCC.setText("Nhà cung cấp");
        radNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radSoLuong)
                            .addComponent(radNCC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radTongTien)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radNgayLapDon)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radNgayLapDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(radNCC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnMD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnMD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lm.png"))); // NOI18N
        btnMD.setText("LÀM MỚI");
        btnMD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMD, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnHDNhapSach.add(jPanel13);
        jPanel13.setBounds(50, 160, 1500, 320);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Chi tiết đơn hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        tblCT_DonNhapHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblCT_DonNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã đơn", "Mã sách", "Số lượng", "Giá nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tblCT_DonNhapHang);

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Tìm kiếm & sắp xếp", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        grpSXCTDonNhapHang.add(radGiaNhap);
        radGiaNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radGiaNhap.setSelected(true);
        radGiaNhap.setText("Giá nhập");
        radGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGiaNhapActionPerformed(evt);
            }
        });

        grpSXCTDonNhapHang.add(radSLNhap);
        radSLNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radSLNhap.setText("Số lượng");
        radSLNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSLNhapActionPerformed(evt);
            }
        });

        txtTenMaSach.setText("Nhập tên - mã sách");

        grpSXCTDonNhapHang.add(radTenMaSach);
        radTenMaSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTenMaSach.setText("Tên - Mã sách nhập");
        radTenMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTenMaSachActionPerformed(evt);
            }
        });

        btnMD1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnMD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lm.png"))); // NOI18N
        btnMD1.setText("LÀM MỚI");
        btnMD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMD1ActionPerformed(evt);
            }
        });

        btnSearch1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter.png"))); // NOI18N
        btnSearch1.setText("LỌC");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMD1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(txtTenMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(radTenMaSach)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(radGiaNhap)
                        .addGap(35, 35, 35)
                        .addComponent(radSLNhap)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radGiaNhap)
                    .addComponent(radSLNhap))
                .addGap(18, 18, 18)
                .addComponent(radTenMaSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMD1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnHDNhapSach.add(jPanel16);
        jPanel16.setBounds(50, 550, 1500, 370);

        btnThemHDMoi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnThemHDMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnThemHDMoi.setText("THÊM HD MỚI");
        btnThemHDMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDMoiActionPerformed(evt);
            }
        });
        pnHDNhapSach.add(btnThemHDMoi);
        btnThemHDMoi.setBounds(620, 500, 236, 47);

        pnRight.add(pnHDNhapSach, "pnHDNhapSach");

        pnHDBanSach.setLayout(null);

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("THÔNG TIN HÓA ĐƠN BÁN SÁCH");
        pnHDBanSach.add(jLabel38);
        jLabel38.setBounds(0, 0, 1560, 140);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin đơn bán", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N
        jPanel18.setLayout(null);

        tblThongTinDonBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblThongTinDonBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã đơn ", "Nhân viên", "Khách hàng", "Ngày bán", "Số lượng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinDonBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinDonBanMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblThongTinDonBan);

        jPanel18.add(jScrollPane10);
        jScrollPane10.setBounds(30, 37, 1087, 300);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Tìm kiếm & Sắp xếp", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        grpTimKiemDonBanHang.add(radSL);
        radSL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radSL.setSelected(true);
        radSL.setText("Số lượng");
        radSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSLActionPerformed(evt);
            }
        });

        grpTimKiemDonBanHang.add(radTT);
        radTT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radTT.setText("Tổng tiền");
        radTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTTActionPerformed(evt);
            }
        });

        grpTimKiemDonBanHang.add(radNgayBan);
        radNgayBan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radNgayBan.setText("Ngày bán");
        radNgayBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNgayBanActionPerformed(evt);
            }
        });

        grpTimKiemDonBanHang.add(radSDT);
        radSDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radSDT.setText("Mã khách hàng");
        radSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSDTActionPerformed(evt);
            }
        });

        txtDTKH.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDTKH.setText("Nhập số điện thoại");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radNgayBan)
                            .addComponent(radSDT))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(radSL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(radTT)
                        .addGap(35, 35, 35))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radSL)
                    .addComponent(radTT))
                .addGap(18, 18, 18)
                .addComponent(radNgayBan)
                .addGap(18, 18, 18)
                .addComponent(radSDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel18.add(jPanel20);
        jPanel20.setBounds(1130, 30, 300, 240);

        btnLM.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new.png"))); // NOI18N
        btnLM.setText("LÀM MỚI");
        btnLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLMActionPerformed(evt);
            }
        });
        jPanel18.add(btnLM);
        btnLM.setBounds(1140, 290, 130, 45);

        btnSort.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter.png"))); // NOI18N
        btnSort.setText("LỌC");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });
        jPanel18.add(btnSort);
        btnSort.setBounds(1300, 290, 130, 45);

        pnHDBanSach.add(jPanel18);
        jPanel18.setBounds(60, 160, 1450, 360);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Chi tiết đơn bán", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        tblCT_ThongTinDonBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblCT_ThongTinDonBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã đơn", "Mã sách", "Số lượng", "Giá bán", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblCT_ThongTinDonBan);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Sắp xếp", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        grpSapXepHDBan.add(radSLSX);
        radSLSX.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radSLSX.setSelected(true);
        radSLSX.setText("Số lượng");

        grpSapXepHDBan.add(radGBSX);
        radGBSX.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radGBSX.setText("Giá bán");

        btnTK.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sort.png"))); // NOI18N
        btnTK.setText("SẮP XẾP");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radSLSX)
                    .addComponent(radGBSX))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(radSLSX)
                .addGap(18, 18, 18)
                .addComponent(radGBSX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnHDBanSach.add(jPanel19);
        jPanel19.setBounds(60, 560, 1450, 310);

        pnRight.add(pnHDBanSach, "pnHDBanSach");

        pnBCDoanhThu.setLayout(null);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("BÁO CÁO");
        pnBCDoanhThu.add(jLabel48);
        jLabel48.setBounds(0, 0, 1560, 140);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Báo cáo theo:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 22))); // NOI18N

        btnBaoCao.setText("TÌM KIẾM");
        btnBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoCaoActionPerformed(evt);
            }
        });

        pnBC.setLayout(new java.awt.CardLayout());

        jLabel53.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel53.setText("Năm:");

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel37.setText("Tháng:");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel36.setText("Ngày:");

        javax.swing.GroupLayout pnBCNgayLayout = new javax.swing.GroupLayout(pnBCNgay);
        pnBCNgay.setLayout(pnBCNgayLayout);
        pnBCNgayLayout.setHorizontalGroup(
            pnBCNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBCNgayLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addComponent(cboBCNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(cboBCTNgay_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel53)
                .addGap(18, 18, 18)
                .addComponent(cboBCTNgay_Thang_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        pnBCNgayLayout.setVerticalGroup(
            pnBCNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBCNgayLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnBCNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(cboBCTNgay_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36)
                    .addComponent(cboBCNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBCTNgay_Thang_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pnBC.add(pnBCNgay, "pnBCNgay");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel45.setText("Năm:");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel46.setText("Tháng:");

        javax.swing.GroupLayout pnBCThangLayout = new javax.swing.GroupLayout(pnBCThang);
        pnBCThang.setLayout(pnBCThangLayout);
        pnBCThangLayout.setHorizontalGroup(
            pnBCThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(pnBCThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnBCThangLayout.createSequentialGroup()
                    .addGap(143, 143, 143)
                    .addComponent(jLabel46)
                    .addGap(18, 18, 18)
                    .addComponent(cboBCTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(61, 61, 61)
                    .addComponent(jLabel45)
                    .addGap(18, 18, 18)
                    .addComponent(cboBCTThang_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(147, Short.MAX_VALUE)))
        );
        pnBCThangLayout.setVerticalGroup(
            pnBCThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
            .addGroup(pnBCThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnBCThangLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(pnBCThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboBCTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboBCTThang_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46)
                        .addComponent(jLabel45))
                    .addContainerGap(20, Short.MAX_VALUE)))
        );

        pnBC.add(pnBCThang, "pnBCThang");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel47.setText("Năm:");

        grpQuyBC.add(radqui1);
        radqui1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radqui1.setSelected(true);
        radqui1.setText("Quý 1");

        grpQuyBC.add(radqui2);
        radqui2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radqui2.setText("Quý 2");

        javax.swing.GroupLayout pnBCQuyLayout = new javax.swing.GroupLayout(pnBCQuy);
        pnBCQuy.setLayout(pnBCQuyLayout);
        pnBCQuyLayout.setHorizontalGroup(
            pnBCQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBCQuyLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(radqui1)
                .addGap(49, 49, 49)
                .addComponent(radqui2)
                .addGap(101, 101, 101)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(cboBCTNam, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        pnBCQuyLayout.setVerticalGroup(
            pnBCQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBCQuyLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnBCQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBCQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboBCTNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47))
                    .addGroup(pnBCQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radqui1)
                        .addComponent(radqui2)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnBC.add(pnBCQuy, "pnBCQuy");

        grpDKBC.add(radBCNgay);
        radBCNgay.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radBCNgay.setSelected(true);
        radBCNgay.setText("Theo ngày");
        radBCNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBCNgayActionPerformed(evt);
            }
        });

        grpDKBC.add(radBCThang);
        radBCThang.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radBCThang.setText("Theo tháng");
        radBCThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBCThangActionPerformed(evt);
            }
        });

        grpDKBC.add(radBCQuy);
        radBCQuy.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        radBCQuy.setText("Theo quý");
        radBCQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBCQuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnBC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(radBCNgay)
                        .addGap(49, 49, 49)
                        .addComponent(radBCThang)
                        .addGap(44, 44, 44)
                        .addComponent(radBCQuy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radBCNgay)
                    .addComponent(radBCThang)
                    .addComponent(radBCQuy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnBC, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnBCDoanhThu.add(jPanel22);
        jPanel22.setBounds(250, 200, 1020, 140);

        tblThongTinBC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã đơn", "Mã sản phẩm", "Số lượng", "Giá nhập", "Giá bán", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tblThongTinBC);

        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel49.setText("Tổng doanh thu:");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongDT, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pnBCDoanhThu.add(jPanel23);
        jPanel23.setBounds(250, 370, 1020, 440);

        btnRaPhieuBC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/raphieu.png"))); // NOI18N
        btnRaPhieuBC.setText("RA PHIẾU");
        btnRaPhieuBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaPhieuBCActionPerformed(evt);
            }
        });
        pnBCDoanhThu.add(btnRaPhieuBC);
        btnRaPhieuBC.setBounds(660, 810, 237, 51);

        pnRight.add(pnBCDoanhThu, "pnBCDoanhThu");

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nhập thông tin sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 22))); // NOI18N

        jLabel50.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel50.setText("Chọn nhà cung cấp:");

        cboNhaCungCap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhaCungCapActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel51.setText("Ngày nhập:");

        txtNgayNhapSach.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addGap(23, 23, 23)
                .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addGap(37, 37, 37)
                .addComponent(txtNgayNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgayNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Nhập thông tin sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 22))); // NOI18N

        jLabel54.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel54.setText("Mã sách:");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel55.setText("Tên sách:");

        jLabel59.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel59.setText("Tác giả:");

        jLabel60.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel60.setText("Nhà xuất bản:");

        jLabel61.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel61.setText("Năm xuất bản:");

        jLabel62.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel62.setText("Thể loại:");

        jLabel63.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel63.setText("Số lượng:");

        jLabel64.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel64.setText("Đơn giá nhập:");

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

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60))
                .addGap(51, 51, 51)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaSachNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(txtTenSachNhap)
                    .addComponent(txtTacGiaNhap)
                    .addComponent(txtNXBNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTG, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel61)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64))
                .addGap(48, 48, 48)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoluongNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(txtTheLoaiNhap, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNamXBNhap, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDonGiaNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDGN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel61)
                    .addComponent(txtMaSachNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamXBNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel62)
                    .addComponent(txtTenSachNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTheLoaiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel63)
                    .addComponent(txtTacGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoluongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTG, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel64)
                    .addComponent(txtNXBNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDGN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.setPreferredSize(new java.awt.Dimension(89, 25));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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

        btnRAPHIEUNHAP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/raphieu.png"))); // NOI18N
        btnRAPHIEUNHAP.setText("RA PHIẾU");
        btnRAPHIEUNHAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRAPHIEUNHAPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRAPHIEUNHAP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRAPHIEUNHAP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin đơn nhập", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 22))); // NOI18N
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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
        jScrollPane2.setViewportView(tblEmptyThongTinNhapSach);

        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("HÓA ĐƠN NHẬP SÁCH");

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Hóa đơn nhập sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 22))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("HÓA ĐƠN NHẬP");

        tblHOADONNHAP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sách", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(tblHOADONNHAP);

        jLabel67.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel67.setText("TỔNG TIỀN:");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTTTTTTTTTT)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTTTTTTTTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnLuu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay.png"))); // NOI18N
        btnLuu.setText("THANH TOÁN");
        btnLuu.setPreferredSize(new java.awt.Dimension(89, 25));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
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

        txtTongThanhTien.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout pnHoaDonNhapLayout = new javax.swing.GroupLayout(pnHoaDonNhap);
        pnHoaDonNhap.setLayout(pnHoaDonNhapLayout);
        pnHoaDonNhapLayout.setHorizontalGroup(
            pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                .addGroup(pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 1560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                        .addGroup(pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoaDonNhapLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtTongThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHoaDonNhapLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnHoaDonNhapLayout.setVerticalGroup(
            pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnHoaDonNhapLayout.createSequentialGroup()
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(pnHoaDonNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pnRight.add(pnHoaDonNhap, "pnHoaDonNhap");

        getContentPane().add(pnRight, java.awt.BorderLayout.CENTER);

        mnuHeThong.setText("Hệ thống");

        mnuDangXuat.setText("Đăng xuất");
        mnuDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mnuDangXuat);

        mnuThoat.setText("Thoát");
        mnuThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThoatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mnuThoat);

        jMenuBar1.add(mnuHeThong);

        mnuThongTinNV.setText("Nhân viên");

        mnuXoaNV.setText("Xóa nhân viên");
        mnuXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuXoaNVActionPerformed(evt);
            }
        });
        mnuThongTinNV.add(mnuXoaNV);

        mnuTKNhanVien.setText("Quản lí tài khoản");
        mnuTKNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTKNhanVienActionPerformed(evt);
            }
        });
        mnuThongTinNV.add(mnuTKNhanVien);

        mnuThemNV.setText("Thêm nhân viên");
        mnuThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThemNVActionPerformed(evt);
            }
        });
        mnuThongTinNV.add(mnuThemNV);

        mnuCTTTNhanVien.setText("Thông tin chi tiết");
        mnuCTTTNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCTTTNhanVienActionPerformed(evt);
            }
        });
        mnuThongTinNV.add(mnuCTTTNhanVien);

        jMenuBar1.add(mnuThongTinNV);

        mnuNCC.setText("Nhà cung cấp");

        mnuXoaNCC.setText("Xóa nhà cung cấp");
        mnuXoaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuXoaNCCActionPerformed(evt);
            }
        });
        mnuNCC.add(mnuXoaNCC);

        mnuTTCTNCC.setText("Thông tin chi tiết");
        mnuTTCTNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTTCTNCCActionPerformed(evt);
            }
        });
        mnuNCC.add(mnuTTCTNCC);

        jMenuBar1.add(mnuNCC);

        mnuKH.setText("Khách hàng");

        mnuXoaKH.setText("Xóa thông tin khách hàng");
        mnuXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuXoaKHActionPerformed(evt);
            }
        });
        mnuKH.add(mnuXoaKH);

        jMenuBar1.add(mnuKH);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnTrangChu");
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnThongTinSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinSachActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnThongTInSach");
    }//GEN-LAST:event_btnThongTinSachActionPerformed

    private void btnThongTinKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinKHActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnThongTinKH");
        KhachHang_DAO.showDS_KhachHang(dtmTTKH);
    }//GEN-LAST:event_btnThongTinKHActionPerformed

    private void btnHDNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDNhapSachActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnHDNhapSach");
    }//GEN-LAST:event_btnHDNhapSachActionPerformed

    private void btnHoaDonBanSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonBanSachActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnHDBanSach");
    }//GEN-LAST:event_btnHoaDonBanSachActionPerformed

    private void btnBCDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBCDoanhThuActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnBCDoanhThu");
    }//GEN-LAST:event_btnBCDoanhThuActionPerformed

    private void btnLogoutsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutsActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không ?", "Thông báo",
                 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        this.dispose();
        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_btnLogoutsActionPerformed

    private void mnuDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDangXuatActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không ?", "Thông báo",
                 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        this.dispose();
        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_mnuDangXuatActionPerformed

    private void mnuThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThoatActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không ?", "Thông báo",
                 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        System.exit(0);
    }//GEN-LAST:event_mnuThoatActionPerformed

    private void mnuXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuXoaNVActionPerformed
        // TODO add your handling code here:
        FrameNV frmNV = new FrameNV(this, true);
        frmNV.setVisible(true);
        NhanVien_DAO.showDS_NhanVien(dtmTTNV);
    }//GEN-LAST:event_mnuXoaNVActionPerformed

    private void mnuXoaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuXoaNCCActionPerformed
        // TODO add your handling code here:
        FrameNCC frmNCC = new FrameNCC(this, true);
        frmNCC.setVisible(true);
        NhaCungCap_DAO.showDS_NCC(dtmNCC);
    }//GEN-LAST:event_mnuXoaNCCActionPerformed

    private void mnuXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuXoaKHActionPerformed
        // TODO add your handling code here:
        FrameKH frmKH = new FrameKH(this, true);
        frmKH.setVisible(true);
        KhachHang_DAO.showDS_KhachHang(dtmTTKH);
    }//GEN-LAST:event_mnuXoaKHActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
        tblThongTinSach.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void radTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTenSachActionPerformed
        // TODO add your handling code here:
        cardLayout2.show(pnThongTinTimKiem, "pnTimTheoTen");
        txtTimKiemTenSach.setEditable(true);
        txtTimKiemTenSach.requestFocus();
    }//GEN-LAST:event_radTenSachActionPerformed

    private void radTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTheLoaiActionPerformed
        // TODO add your handling code here:
        cardLayout2.show(pnThongTinTimKiem, "pnTimTheoTheLoai");
    }//GEN-LAST:event_radTheLoaiActionPerformed

    private void radTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTacGiaActionPerformed
        // TODO add your handling code here:.
        cardLayout2.show(pnThongTinTimKiem, "pnTImTheoTG");
    }//GEN-LAST:event_radTacGiaActionPerformed

    private void radGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGiaBanActionPerformed
        // TODO add your handling code here:
        cardLayout2.show(pnThongTinTimKiem, "pnTimTheoGiaBan");
    }//GEN-LAST:event_radGiaBanActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        dtmTrangChu1.setRowCount(0);
        //--------Nếu chọn tìm theo tên sách
        if (radTenSach.isSelected()){
            if (txtTimKiemTenSach.getText().length() <= 0){
                JOptionPane.showMessageDialog(this, "Hãy nhập tên sách !", "Thông báo", 
                        JOptionPane.WARNING_MESSAGE);
                txtTimKiemTenSach.requestFocus();
                Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
                tblThongTinSach.setRowSelectionInterval(0, 0);
            }
            else {
                try {      
                    boolean kq = Sach_DAO.co_timKiem_TheoTenSach(txtTimKiemTenSach.getText().toString());
                    if (!kq){
                        MessageDialog.showMessageDialog(this, "Không có kết quả tương ứng", "Thông báo");
                        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
                        tblThongTinSach.setRowSelectionInterval(0, 0);
                    }else{
                        Sach_DAO.timKiem_TheoTenSach(dtmTrangChu1, txtTimKiemTenSach.getText().toString(), kq);
                        tblThongTinSach.setRowSelectionInterval(0, 0);
                    }
                        
                } catch (Exception e) {
                    e.printStackTrace();
                }               
            }
        }
        //Tìm kiếm theo thể loại
        else if (radTheLoai.isSelected()){
            try {
                if (Sach_DAO.check_TheoTheLoai(cboTheLoai.getSelectedItem().toString(),
                        cboTacGiaTheoTheLoai.getSelectedItem().toString(),
                        cboGiaBanTheoTheLoai.getSelectedItem().toString())){
                    Sach_DAO.timKiem_TheoTheLoai(dtmTrangChu1, cboTheLoai.getSelectedItem().toString(),
                            cboTacGiaTheoTheLoai.getSelectedItem().toString(),
                            cboGiaBanTheoTheLoai.getSelectedItem().toString());
                    tblThongTinSach.setRowSelectionInterval(0, 0);   
                }else {
                        MessageDialog.showMessageDialog(this, "Không có kết quả tương ứng", "Thông báo");
                        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
                        tblThongTinSach.setRowSelectionInterval(0, 0);                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }   
        }
        //Tìm kiếm theo tác giả
        else if (radTacGia.isSelected()){
            try {
                if (Sach_DAO.check_TheoTheLoai(cboTheLoaiTheoTG.getSelectedItem().toString(),
                        cboTacGia.getSelectedItem().toString(),
                        cboGiaBanTheoTG.getSelectedItem().toString())){
                    Sach_DAO.timKiem_TheoTheLoai(dtmTrangChu1, cboTheLoaiTheoTG.getSelectedItem().toString(),
                            cboTacGia.getSelectedItem().toString(),
                            cboGiaBanTheoTG.getSelectedItem().toString());   
                    tblThongTinSach.setRowSelectionInterval(0, 0);                    
                }else {
                        MessageDialog.showMessageDialog(this, "Không có kết quả tương ứng", "Thông báo");
                        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
                        tblThongTinSach.setRowSelectionInterval(0, 0);                           
                }
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
        else if (radGiaBan.isSelected()){
            try {
                if (Sach_DAO.check_TheoGiaBan(cboTimTheoGiaBan.getSelectedItem().toString())){
                    Sach_DAO.timKiem_TheoGiaBan(dtmTrangChu1, cboTimTheoGiaBan.getSelectedItem().toString());   
                    tblThongTinSach.setRowSelectionInterval(0, 0);
                }else {
                        MessageDialog.showMessageDialog(this, "Không có kết quả tương ứng", "Thông báo");
                        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
                        tblThongTinSach.setRowSelectionInterval(0, 0);                     
                }
            } catch (Exception e) {
                e.printStackTrace();
            }             
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cboTheLoaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboTheLoaiFocusLost
        // TODO add your handling code here:
        cboTacGiaTheoTheLoai.removeAllItems();
        Sach_DAO.showDL_Combox_TimKiem_TheoTL(cboTacGiaTheoTheLoai, cboTheLoai.getSelectedItem().toString().trim());
    }//GEN-LAST:event_cboTheLoaiFocusLost

    private void cboTacGiaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboTacGiaFocusLost
        // TODO add your handling code here:
        cboTheLoaiTheoTG.removeAllItems();
        Sach_DAO.showDL_Combox_TimKiem_TheoTG(cboTheLoaiTheoTG, cboTacGia.getSelectedItem().toString().trim());
    }//GEN-LAST:event_cboTacGiaFocusLost

    private void btnThemVaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoHDActionPerformed
        // TODO add your handling code here:
        int rowSL = tblThongTinSach.getSelectedRow();
        String ms = (String) tblThongTinSach.getValueAt(rowSL, 0);
        int soLuongTon = 0;
        
        //Gọi frame nhập số lượng và lấy số lượng đã nhập
        FrameSoLuongSachMua frm = new FrameSoLuongSachMua(this, true);
        frm.setVisible(true);
        int sluong = frm.getSoLuong();
        if (sluong == 0)return;
        
        //-------------------------------------------------Kiểm tra số lượng nhập vào có thỏa hay không - nếu không bắt nhập lại          
        soLuongTon = HoaDonBan_DAO.getSLTon(ms);            
        if (soLuongTon == 0){
            JOptionPane.showMessageDialog(this, "Sách đã hết hàng vui lòng chọn sản phẩm khác !", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE); 
            return;
        }
        
        while (soLuongTon < sluong) {            
            JOptionPane.showMessageDialog(this, "Số lượng sách chỉ còn " + soLuongTon + " hãy nhập lại số lượng");
            frm.setVisible(true);
            sluong = frm.getSoLuong();
        }            
        //----------------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------Kiểm tra sách đã được thêm chưa - nếu đã thêm thì k cho thêm nữa
        int soDOng = tblCTHoaDon.getRowCount();
        String [] mas = new String[soDOng];
        for (int i = 0; i < soDOng; i++){
            mas[i] = (String) tblCTHoaDon.getValueAt(i, 0);
        }

        for (int i = 0; i < soDOng; i++){
            if (mas[i].equals(ms)){
                JOptionPane.showMessageDialog(this, "Sách đã được mua vui lòng chọn sách khác", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        //-----Hiện thông tin lên bảng 
        double donGia = Double.parseDouble("" + tblThongTinSach.getValueAt(rowSL, 6));
        double thanhTien = sluong * donGia;
        tongThanhTien = tongThanhTien + thanhTien;

        Vector<Object> vec = new Vector<Object>();
        vec.add(ms);
        vec.add(tblThongTinSach.getValueAt(rowSL, 1));
        vec.add(sluong);
        vec.add(donGia);
        vec.add(thanhTien);

        dtmCTHD.addRow(vec);        
        
        Sach_DAO.showDL_ThongTinSach(dtmTrangChu1);
        
        btnThemVaoHD.setEnabled(false);
        btnRaPhieu.setEnabled(true);
        //-------------------------------------------------------------------------------------------------------------------                
    }//GEN-LAST:event_btnThemVaoHDActionPerformed

    private void btnXoaKhoiHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoiHoaDon1ActionPerformed
        // TODO add your handling code here:
        int rowSL = tblCTHoaDon.getSelectedRow();
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sách [" + tblCTHoaDon.getValueAt(rowSL, 1) + "] không ?",
                "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        dtmCTHD.removeRow(rowSL);
        
        btnXoaKhoiHoaDon1.setEnabled(false);
        
        int kt = tblCTHoaDon.getRowCount();
        if (kt <= 0){
            btnRaPhieu.setEnabled(false);
        }
    }//GEN-LAST:event_btnXoaKhoiHoaDon1ActionPerformed

    private void tblThongTinSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinSachMouseClicked
        // TODO add your handling code here:
        btnThemVaoHD.setEnabled(true);
    }//GEN-LAST:event_tblThongTinSachMouseClicked

    private void tblCTHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHoaDonMouseClicked
        // TODO add your handling code here:
        btnXoaKhoiHoaDon1.setEnabled(true);
    }//GEN-LAST:event_tblCTHoaDonMouseClicked

    private void btnRaPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaPhieuActionPerformed
        // TODO add your handling code here:
        btnThanhToan.setEnabled(true);
        btnHuyHD.setEnabled(true);
        btnThemMaKH.setEnabled(true);
        btnThemVaoHD.setEnabled(false);
        btnXoaKhoiHoaDon1.setEnabled(false);
        btnLamMoi.setEnabled(false);
        tblThongTinSach.setVisible(false);
        tblCTHoaDon.setVisible(false);
        
        radTenSach.setEnabled(false);
        radTacGia.setEnabled(false);
        radTheLoai.setEnabled(false);
        radGiaBan.setEnabled(false);
        btnTimKiem.setEnabled(false);
        
        //------Load thông tin sản phẩm mua vào hóa đơn
        double tong = 0;
        
        int soSP = tblCTHoaDon.getRowCount();
        String [] tenS = new String[soSP];
        String [] slSP = new String[soSP];
        String [] thanhTien = new String[soSP];
        
        for (int i = 0; i < soSP; i++) {
            tenS[i] = "" + tblCTHoaDon.getValueAt(i, 1);
            slSP[i] = "" + tblCTHoaDon.getValueAt(i, 2);
            thanhTien[i] = "" + tblCTHoaDon.getValueAt(i, 4);
        }
        
        for (int i = 0; i < soSP; i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(tenS[i]);
            vec.add(slSP[i]);
            vec.add(thanhTien[i]);
            
            dtmHD.addRow(vec);
        }
        
        //------Tính tổng tiền cho hóa đơn
        for (int i = 0; i < soSP; i++) {
            tong += Double.parseDouble(thanhTien[i]);
        }
        txtTT.setText("" + (int) tong);
        txtTTT.setText("" + (int) tong);
        
    }//GEN-LAST:event_btnRaPhieuActionPerformed

    private void btnThemMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMaKHActionPerformed
        // TODO add your handling code here:
        FrameThemKH kh = new FrameThemKH(this, true);
        kh.setVisible(true);
        txtKH.setText(kh.getDt());
    }//GEN-LAST:event_btnThemMaKHActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (txtKH.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "Hãy nhập mã khách hàng trước khi thanh toán", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }
        else {
            //Xác nhận có muốn thanh toán k
            int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn thanh toán hóa đơn ?", "Thông báo",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (tl == JOptionPane.NO_OPTION) return;

            //--------------------------------Nếu xác nhận thì lưu CTHD vào database
            String maKH = "";
            //----Tạo mã đơn tự động
            double md = Math.random();
            int mso= (int)  (100 + md * 1000  + 1); 

            //----Lấy thông tin chi tiết của đơn hàng
            int slSP = tblCTHoaDon.getRowCount();
            String [] maSachBan = new String[slSP];
            int [] slban = new int[slSP];
            String [] giaBan = new String[slSP];
            String [] thanhTien = new String[slSP];

            for (int i = 0; i < slSP; i++) {
                maSachBan[i] = "" + tblCTHoaDon.getValueAt(i, 0);
                slban[i] = (int) tblCTHoaDon.getValueAt(i, 2);
                giaBan[i] = "" + tblCTHoaDon.getValueAt(i, 3);
                thanhTien[i] = "" + tblCTHoaDon.getValueAt(i, 4);
            }
            //----------------------------------------------Lấy mã khách hàng từ SDT
            maKH = KhachHang_DAO.getMa_fromSDT(txtKH.getText());
            //----------------------Kiểm tra khách hàng có mua từ 5 đơn trở lên chưa
            //-------------------------------Nếu đủ thì sẽ update lên Thành viên Vip
            try {
                int count = KhachHang_DAO.check_TinhTrang_KhachHang(maKH);
                if (count == 5){
                    KhachHang_DAO.update_TinhTrang_KH(maKH);
//                  show_DSHDBanSach();                     
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //-------------------------------------------Lưu đơn hàng vào DonBanHang
            int tsl = 0;
            for (int i = 0; i < slSP; i++) {
                tsl += slban[i];
            }
            try {               
                int tt = Integer.parseInt(txtTTT.getText());
                HoaDonBan donBan = new HoaDonBan();
                donBan.setMaDonBan("MD" + mso);
                donBan.setMaNV(maNV);
                donBan.setMaKH(maKH);
                donBan.setSoLuongBan(tsl);
                donBan.setTongTIen(tt);
                
                boolean kq = HoaDonBan_DAO.insert_DonBanHang(donBan);

            } catch (Exception e) {
                System.out.println("1" + e.getMessage());
            } finally { //-------------------Lưu chi tiết đơn hàng vào CT_DonBanHang
                for (int i = 0; i < slSP; i++) {
                    try {
                        CT_HoaDonBan ct = new CT_HoaDonBan();
                        ct.setMaDOnBan("MD" + mso);
                        ct.setMaSach(maSachBan[i]);
                        ct.setSoLuongBan(slban[i]);
                        ct.setGiaBan(Float.parseFloat(giaBan[i]));
                        ct.setThanhTIen(Float.parseFloat(thanhTien[i]));
                        
                        boolean kq = CT_HoaDonBan_DAO.insert_CT_HoaDonBan(ct);
                    } catch (Exception e) {
                        System.out.println("2." + e.getMessage());
                    }
                }
            }
            //---------------Update số lượng tồn kho vào bản TonKho cho các sách bán
            for (int i = 0; i < slSP; i++) {
                try {//-----------------Update số lượng tồn kho của các sách đã được bán
                    boolean kq = TonKho_DAO.update_TOnKho(slban[i], maSachBan[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            FrameTinhTien tt = new FrameTinhTien(this, true, txtTTT.getText());
            tt.setVisible(true);

            dtmHD.setRowCount(0);
            dtmCTHD.setRowCount(0);
            txtTT.setText("");
            txtTTT.setText("");
            txtKH.setText("");
            tblThongTinSach.setVisible(true);
            tblCTHoaDon.setVisible(true);
            btnHuyHD.setEnabled(false);
            btnThanhToan.setEnabled(false);
            btnRaPhieu.setEnabled(false);
            btnLamMoi.setEnabled(true);

            radTenSach.setEnabled(true);
            radTacGia.setEnabled(true);
            radTheLoai.setEnabled(true);
            radGiaBan.setEnabled(true);
            btnTimKiem.setEnabled(true);                   
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy Hóa đơn không ?", "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        
        dtmHD.setRowCount(0);
        dtmCTHD.setRowCount(0);
        txtTT.setText("");
        txtTTT.setText("");
        txtKH.setText("");
        tblThongTinSach.setVisible(true);
        tblCTHoaDon.setVisible(true);
        btnHuyHD.setEnabled(false);
        btnThanhToan.setEnabled(false);
        btnRaPhieu.setEnabled(false);
        btnLamMoi.setEnabled(true);
        
        radTenSach.setEnabled(true);
        radTacGia.setEnabled(true);
        radTheLoai.setEnabled(true);
        radGiaBan.setEnabled(true);
        btnTimKiem.setEnabled(true);
    }//GEN-LAST:event_btnHuyHDActionPerformed

    private void tblThongTinCTSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinCTSachMouseClicked
        // TODO add your handling code here:
        int rowSL = tblThongTinCTSach.getSelectedRow();
        txtMaS.setText("" + tblThongTinCTSach.getValueAt(rowSL, 0));
        txtTenS.setText("" + tblThongTinCTSach.getValueAt(rowSL, 1));
        txtTacGia.setText("" + tblThongTinCTSach.getValueAt(rowSL, 2));
        txtNXB.setText("" + tblThongTinCTSach.getValueAt(rowSL, 3));
        txtNamXB.setText("" + tblThongTinCTSach.getValueAt(rowSL, 4));
        txtTheLoai.setText("" + tblThongTinCTSach.getValueAt(rowSL, 5));
        txtTonKho.setText("" + tblThongTinCTSach.getValueAt(rowSL, 6));
        txtGiaBan.setText("" + tblThongTinCTSach.getValueAt(rowSL, 7));        
    }//GEN-LAST:event_tblThongTinCTSachMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        DialogSuaThongTinSach sach = new DialogSuaThongTinSach(this, true);
        sach.setVisible(true);
        Sach_DAO.showDL_CT_ThongTinSach(dtmThongTinSach);
        tblThongTinCTSach.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        DialogCapNhatGiaBan gb = new DialogCapNhatGiaBan(this, true);
        gb.setVisible(true);
        Sach_DAO.showDL_CT_ThongTinSach(dtmThongTinSach);
        tblThongTinCTSach.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void tblThongTinKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinKHMouseClicked
        // TODO add your handling code here:
        int rowSL = tblThongTinKH.getSelectedRow();
        txtMaKH.setText("" + tblThongTinKH.getValueAt(rowSL, 0));
        txtTenKH.setText("" + tblThongTinKH.getValueAt(rowSL, 1));
        txtSDT.setText("" + tblThongTinKH.getValueAt(rowSL, 2));
        txtEmail.setText("" + tblThongTinKH.getValueAt(rowSL, 3));
        txtDiaChi.setText("" + tblThongTinKH.getValueAt(rowSL, 4));
        txtTinhTrang.setText("" + tblThongTinKH.getValueAt(rowSL, 5));        
        
    }//GEN-LAST:event_tblThongTinKHMouseClicked

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        FrameThemKH kh = new FrameThemKH(this, true);
        kh.setVisible(true);
        KhachHang_DAO.showDS_KhachHang(dtmTTKH);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnSuaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinActionPerformed
        // TODO add your handling code here:
        FrameCNTTKH ttkh = new FrameCNTTKH(this, true);
        ttkh.setVisible(true);
        KhachHang_DAO.showDS_KhachHang(dtmTTKH);
    }//GEN-LAST:event_btnSuaThongTinActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if (radTVV.isSelected()){
            dtmTTKH.setRowCount(0);
            KhachHang_DAO.sort_KH_TheoTinhTrang_VIP(dtmTTKH);
        } else {
            dtmTTKH.setRowCount(0);
            KhachHang_DAO.sort_KH_TheoTinhTrang_THUONG(dtmTTKH);
        }
        tblThongTinKH.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnLocActionPerformed

    private void tblDSNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNhanVienMouseClicked
        // TODO add your handling code here:
        int rowSl = tblDSNhanVien.getSelectedRow();
        if (rowSl >= 0){
            String manv = (String) tblDSNhanVien.getValueAt(rowSl, 0);
            NhanVien_DAO dao = new NhanVien_DAO();
            NhanVien nv = dao.show_TT_NhanVien(manv);
            
            if (nv != null){
                lblTENNV.setText(nv.getTenNV());
                //---Ngay sinh
                lblNgaySinh.setText(nv.getNgaySinh());                
                //---Gioi tinh
                lblGioiTinh.setText(nv.getGioiTInh());
                //---
                lblSDT.setText(nv.getSdt());
                lblEmail.setText(nv.getEmail());
                lblDiaChi.setText(nv.getDiaCHi());
                
                //---Anh nhan vien
                if (nv.getAnhNV() != null){
                    try {
                        Image img = ImageHelper.createImageFromByteArray(nv.getAnhNV(), "jpg");
                        ImageIcon icon = new ImageIcon(img);
                        icon = new ImageIcon(ImageHelper.resize(icon.getImage(), 250, 270));        
                        lblanhNV.setIcon(icon);
                        personalImage = nv.getAnhNV();
                    } catch (IOException ex) {
                        Logger.getLogger(DialogCNTTNV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    personalImage = nv.getAnhNV();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/img/nv1_1.jpg"));
                    icon = new ImageIcon(ImageHelper.resize(icon.getImage(), 250, 270)); 
                    lblanhNV.setIcon(icon); 
                }
            }
        }        
    }//GEN-LAST:event_tblDSNhanVienMouseClicked

    private void mnuTKNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTKNhanVienActionPerformed
        // TODO add your handling code here:
        DiaLogQuyenQLTK tk = new DiaLogQuyenQLTK(this, true);
        tk.setVisible(true);
    }//GEN-LAST:event_mnuTKNhanVienActionPerformed

    private void btnCapNhatThongTinNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatThongTinNVActionPerformed
        // TODO add your handling code here:
        DialogCNTTNV cnttnv = new DialogCNTTNV(this, true);
        cnttnv.setVisible(true);
        NhanVien_DAO.showDS_NhanVien(dtmTTNV);
    }//GEN-LAST:event_btnCapNhatThongTinNVActionPerformed

    private void btnTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNVActionPerformed
        // TODO add your handling code here:
        DialogTraCuuTTNV tcttnv = new DialogTraCuuTTNV(this, true);
        tcttnv.setVisible(true);
    }//GEN-LAST:event_btnTimKiemNVActionPerformed

    private void mnuThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThemNVActionPerformed
        // TODO add your handling code here:
        DiaLogThemNV addnv = new DiaLogThemNV(this, true);
        addnv.setVisible(true);
        NhanVien_DAO.showDS_NhanVien(dtmTTNV);
    }//GEN-LAST:event_mnuThemNVActionPerformed

    private void mnuCTTTNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCTTTNhanVienActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnThongTinNV");          
    }//GEN-LAST:event_mnuCTTTNhanVienActionPerformed

    private void tblDSNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNCCMouseClicked
        // TODO add your handling code here:
        int rsl = tblDSNCC.getSelectedRow();
        txtMaNCC.setText(tblDSNCC.getValueAt(rsl, 0).toString().trim());
        txtTenNCC.setText(tblDSNCC.getValueAt(rsl, 1).toString().trim());
        txtLienHeNCC.setText(tblDSNCC.getValueAt(rsl, 2).toString().trim());
        txtEmailNCC.setText(tblDSNCC.getValueAt(rsl, 3).toString().trim());
        txtDiaChiNCC.setText(tblDSNCC.getValueAt(rsl, 4).toString().trim());        
    }//GEN-LAST:event_tblDSNCCMouseClicked

    private void btnThemNCC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCC1ActionPerformed
        // TODO add your handling code here:
        DiaLogThemNCC themNCC = new DiaLogThemNCC(this, true);
        themNCC.setVisible(true);
        NhaCungCap_DAO.showDS_NCC(dtmNCC);
    }//GEN-LAST:event_btnThemNCC1ActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        // TODO add your handling code here:
        DiaLogSuaTTNCC ttncc = new DiaLogSuaTTNCC(this, true);
        ttncc.setVisible(true);
        NhaCungCap_DAO.showDS_NCC(dtmNCC);
    }//GEN-LAST:event_btnSuaNCCActionPerformed

    private void mnuTTCTNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTTCTNCCActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnRight, "pnNhaCungCap");
    }//GEN-LAST:event_mnuTTCTNCCActionPerformed

    private void tblDonNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonNhapHangMouseClicked
        // TODO add your handling code here:
        int rsl = tblDonNhapHang.getSelectedRow();
        String md = (String)tblDonNhapHang.getValueAt(rsl, 0);
        
        try {
            dtmCT_HDNS.setRowCount(0);
            HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, md);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblDonNhapHangMouseClicked

    private void radSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSoLuongActionPerformed
        // TODO add your handling code here:
        txtNCC.setVisible(false);
    }//GEN-LAST:event_radSoLuongActionPerformed

    private void radTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTongTienActionPerformed
        // TODO add your handling code here:
        txtNCC.setVisible(false);
    }//GEN-LAST:event_radTongTienActionPerformed

    private void radNgayLapDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNgayLapDonActionPerformed
        // TODO add your handling code here:
        txtNCC.setVisible(false);      
    }//GEN-LAST:event_radNgayLapDonActionPerformed

    private void radNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNCCActionPerformed
        // TODO add your handling code here:
        txtNCC.setVisible(true);
        txtNCC.setEditable(true);
        txtNCC.setText("Nhập tên nhà cung cấp");
        txtNCC.requestFocus();
        txtNCC.selectAll();
    }//GEN-LAST:event_radNCCActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (kt(radTongTien.isSelected(), radNgayLapDon.isSelected(), radSoLuong.isSelected(), radNCC.isSelected())){
            JOptionPane.showMessageDialog(this, "Hãy chọn điều kiện Lọc ! ", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            if (radSoLuong.isSelected()){
                try {
                    HoaDonNhap_DAO.sort_SL(dtmHDNS);
                    HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, tblDonNhapHang.getValueAt(0, 0).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (radNgayLapDon.isSelected()){
                DiaLogLich lich = new DiaLogLich(this, true);
                lich.setVisible(true);
                int ngay = lich.getNgay();
                int thang = lich.getThang();
                int nam = lich.getNam();
                if (!lich.isKq()) return;
                String n = ngay + "/" + thang + "/" + nam;
                
                try {
                    if (HoaDonNhap_DAO.kt_ngayNhap(ngay, thang, nam)){
                        HoaDonNhap_DAO.timKiem_TheoNgayNhap(dtmHDNS, n);
                        HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, tblDonNhapHang.getValueAt(0, 0).toString());
                    } else{
                        JOptionPane.showMessageDialog(this, "Không có Hóa đơn nhập hàng nào ngày [ " + n + " ] !","Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        HoaDonNhap_DAO.showDS_DonNhapHang(dtmHDNS);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }         
            } else if (radTongTien.isSelected()){
                try {
                    HoaDonNhap_DAO.sort_TT(dtmHDNS);
                    HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, tblDonNhapHang.getValueAt(0, 0).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }      
            } else {
                try {
                    if (HoaDonNhap_DAO.check_NCC(txtNCC.getText())){
                        HoaDonNhap_DAO.tim_kiem_Ten(dtmHDNS, txtNCC.getText());
                        HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, tblDonNhapHang.getValueAt(0, 0).toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "Không có nhà cung cấp [ " + txtNCC.getText() + " ] !", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        HoaDonNhap_DAO.showDS_DonNhapHang(dtmHDNS);
                        txtNCC.requestFocus();
                        txtNCC.selectAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }          
            }
        }
        tblDonNhapHang.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnMDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMDActionPerformed
        // TODO add your handling code here:
        HoaDonNhap_DAO.showDS_DonNhapHang(dtmHDNS);
        HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, tblDonNhapHang.getValueAt(0, 0).toString().trim());
        tblDonNhapHang.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnMDActionPerformed

    private void radTenMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTenMaSachActionPerformed
        // TODO add your handling code here:
        txtTenMaSach.setVisible(true);
        txtTenMaSach.setEditable(true);
        txtTenMaSach.setText("Nhập Tên - Mã sách");
        txtTenMaSach.requestFocus();
        txtTenMaSach.selectAll();        
    }//GEN-LAST:event_radTenMaSachActionPerformed

    private void btnMD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMD1ActionPerformed
        // TODO add your handling code here:
        tblDonNhapHang.setRowSelectionInterval(0, 0);
        int rsl = tblDonNhapHang.getSelectedRow();
        String md = (String)tblDonNhapHang.getValueAt(rsl, 0);
        dtmCT_HDNS.setRowCount(0);
        try {
            HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, md);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }//GEN-LAST:event_btnMD1ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
        dtmCT_HDNS.setRowCount(0);
        int rsl = tblDonNhapHang.getSelectedRow();
        String ma = tblDonNhapHang.getValueAt(rsl, 0).toString();
        if (radGiaNhap.isSelected()){
            try {
                CT_DonNhapHang_DAO.sort_GiaNhap(dtmCT_HDNS, ma);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (radSLNhap.isSelected()){
            try {
                CT_DonNhapHang_DAO.sort_SLNhap(dtmCT_HDNS, ma);
            } catch (Exception e) {
                e.printStackTrace();
            }  
        }else {
            try {
                if (!CT_DonNhapHang_DAO.check_Sach(dtmCT_HDNS, txtTenMaSach.getText(), ma)){
                    JOptionPane.showMessageDialog(this, "Không có sách [ " + txtTenMaSach.getText() + " ] Trong đơn nhập !",
                            "Thông báo", JOptionPane.WARNING_MESSAGE);
                    HoaDonNhap_DAO.showDS_CT_DonNhapHang(dtmCT_HDNS, tblDonNhapHang.getValueAt(0, 0).toString().trim());
                }else{
                    CT_DonNhapHang_DAO.tim_Theo_Ten(dtmCT_HDNS, txtTenMaSach.getText(), ma);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }  
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void radGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGiaNhapActionPerformed
        // TODO add your handling code here:
        txtTenMaSach.setVisible(false);
    }//GEN-LAST:event_radGiaNhapActionPerformed

    private void radSLNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSLNhapActionPerformed
        // TODO add your handling code here:
        txtTenMaSach.setVisible(false);
    }//GEN-LAST:event_radSLNhapActionPerformed

    private void btnThemHDMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDMoiActionPerformed
        // TODO add your handling code here:
//        DioLogNhapSach ns = new DioLogNhapSach(this, true);
//        ns.setVisible(true);
//        HoaDonNhap_DAO.showDS_DonNhapHang(dtmHDNS);
        
        cardLayout.show(pnRight, "pnHoaDonNhap");
        
    }//GEN-LAST:event_btnThemHDMoiActionPerformed

    private void tblThongTinDonBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinDonBanMouseClicked
        // TODO add your handling code here:
        int rsl = tblThongTinDonBan.getSelectedRow();
        String maDonBan = (String)tblThongTinDonBan.getValueAt(rsl, 0);
        try {
            CT_HoaDonBan_DAO.show_CT_HoaDonBan(dtmCT_HDBanSach, maDonBan);
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }//GEN-LAST:event_tblThongTinDonBanMouseClicked

    private void radSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSLActionPerformed
        // TODO add your handling code here:
        txtDTKH.setVisible(false);
    }//GEN-LAST:event_radSLActionPerformed

    private void radTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTTActionPerformed
        // TODO add your handling code here:
        txtDTKH.setVisible(false);
    }//GEN-LAST:event_radTTActionPerformed

    private void radNgayBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNgayBanActionPerformed
        // TODO add your handling code here:
        txtDTKH.setVisible(false);
    }//GEN-LAST:event_radNgayBanActionPerformed

    private void radSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSDTActionPerformed
        // TODO add your handling code here:
        txtDTKH.setVisible(true);
        txtDTKH.setEditable(true);
        txtDTKH.selectAll();
        txtDTKH.requestFocus();
    }//GEN-LAST:event_radSDTActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        // TODO add your handling code here:
        if (kt1(radTT.isSelected(), radSL.isSelected(), radNgayBan.isSelected(), radSDT.isSelected())){
            JOptionPane.showMessageDialog(this, "Hãy chọn điều kiện Lọc ! ", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            if (radSL.isSelected()){
                try {
                    HoaDonBan_DAO.sort_DS_DonBanHang_SL(dtmHDBanSach);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (radNgayBan.isSelected()){
                DiaLogLich lich = new DiaLogLich(this, true);
                lich.setVisible(true);
                int ngay = lich.getNgay();
                int thang = lich.getThang();
                int nam = lich.getNam();
                
                if (!lich.isKq()) return;
                try {
                    if (HoaDonBan_DAO.kt_DS_DonBanHang_NgayBan(ngay, thang, nam)){
                        HoaDonBan_DAO.sort_DS_DonBanHang_NgayBan(dtmHDBanSach, ngay, thang, nam);
                    }else {
                        JOptionPane.showMessageDialog(this, "Không có Hóa đơn nào được lập trong ngày " + ngay + " / "
                                + thang +  " / " + nam, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }         
            } else if (radTT.isSelected()){
                try {
                    HoaDonBan_DAO.sort_DS_DonBanHang_TongTien(dtmHDBanSach);
                } catch (Exception e) {
                    e.printStackTrace();
                }      
            } else {
                try {
                    if (HoaDonBan_DAO.kt_DS_DonBanHang_Ma_SDT(txtDTKH.getText())){
                        HoaDonBan_DAO.sort_DS_DonBanHang_Ma_SDT(dtmHDBanSach, txtDTKH.getText());
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Không có khách hàng [ " + txtDTKH.getText() + " ] !", "Thông báo",
                                JOptionPane.WARNING_MESSAGE);
                        HoaDonBan_DAO.showDS_DonBanHang(dtmHDBanSach);
                        txtDTKH.selectAll();
                        txtDTKH.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }            
            }
        }
        tblThongTinDonBan.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnSortActionPerformed

    private void btnLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLMActionPerformed
        // TODO add your handling code here:
        HoaDonBan_DAO.showDS_DonBanHang(dtmHDBanSach);
        tblThongTinDonBan.setRowSelectionInterval(0, 0);
        String ma  = tblThongTinDonBan.getValueAt(0, 0).toString().trim();       
        HoaDonBan_DAO.showDS_CT_DonBanHang(dtmCTHD, ma);
    }//GEN-LAST:event_btnLMActionPerformed

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        // TODO add your handling code here:
        if (!radSLSX.isSelected() && !radGBSX.isSelected()){
            JOptionPane.showMessageDialog(this, "Hãy chọn điều kiện sắp xếp !", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            int rowSL = tblThongTinDonBan.getSelectedRow();
            String ma = tblThongTinDonBan.getValueAt(rowSL, 0).toString().trim();
            if (radSLSX.isSelected()){
                try {
                    CT_HoaDonBan_DAO.sort_CT_HoaDonBan_SLBan(dtmCT_HDBanSach, ma);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    CT_HoaDonBan_DAO.sort_CT_HoaDonBan_GiaBan(dtmCT_HDBanSach, ma);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnTKActionPerformed

    private void radBCNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBCNgayActionPerformed
        // TODO add your handling code here:
        cardLayout3.show(pnBC, "pnBCNgay");
    }//GEN-LAST:event_radBCNgayActionPerformed

    private void radBCThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBCThangActionPerformed
        // TODO add your handling code here:
        cardLayout3.show(pnBC, "pnBCThang");
    }//GEN-LAST:event_radBCThangActionPerformed

    private void radBCQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBCQuyActionPerformed
        // TODO add your handling code here:
        cardLayout3.show(pnBC, "pnBCQuy"); 
    }//GEN-LAST:event_radBCQuyActionPerformed

    private void btnBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoCaoActionPerformed
        // TODO add your handling code here:
        dtmBCDT.setRowCount(0);
        if (radBCNgay.isSelected()){
            int ngay = Integer.parseInt(cboBCNgay.getSelectedItem().toString().trim());
            int thang = Integer.parseInt(cboBCTNgay_Thang.getSelectedItem().toString().trim());
            int nam = Integer.parseInt(cboBCTNgay_Thang_Nam.getSelectedItem().toString().trim());
            if (ktNgay(ngay, thang, nam)){
                try {
                    if (!BaoCao_DAO.kt_baoCao(ngay, thang, nam)){
                        JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được lập trong ngày: " + ngay + " / " + thang + " / " + nam, "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        BaoCao_DAO.baoCao(dtmBCDT, ngay, thang, nam);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                switch(thang){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (cboBCNgay.getSelectedItem().equals("31")){
                                JOptionPane.showMessageDialog(this, "Tháng " + thang + " chỉ có 30 ngày. Vui lòng chọn lại !", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);                      
                        }
                        break;
                    case 2:
                        if (nam % 400 == 0 || nam %4 == 0){
                            if (cboBCNgay.getSelectedItem().equals("30") || cboBCNgay.getSelectedItem().equals("31")){
                                    JOptionPane.showMessageDialog(this, "Năm " + nam + " Tháng " + thang + " chỉ có 29 ngày. Vui lòng chọn lại !", "Thông báo",
                                            JOptionPane.INFORMATION_MESSAGE);                             
                            }
                        }
                        else{
                            if (cboBCNgay.getSelectedItem().equals("29") || cboBCNgay.getSelectedItem().equals("30") || cboBCNgay.getSelectedItem().equals("31")){
                                    JOptionPane.showMessageDialog(this, "Năm " + nam + " Tháng " + thang + " chỉ có 28 ngày. Vui lòng chọn lại !", "Thông báo",
                                            JOptionPane.INFORMATION_MESSAGE);                       
                            }
                        }
                    break;
                }  
            }
        } else if (radBCThang.isSelected()){
            int thang = Integer.parseInt(cboBCTThang.getSelectedItem().toString().trim());
            int nam = Integer.parseInt(cboBCTThang_Nam.getSelectedItem().toString().trim());
            try {
                if (!BaoCao_DAO.kt_baoCao_Thang(thang, nam)){
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được lập trong tháng: " + thang + " / " + nam + " !", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                }else {
                    BaoCao_DAO.baoCao_Thang(dtmBCDT, thang, nam);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }             
        } else {
           if (radqui1.isSelected()){
            int nam = Integer.parseInt(cboBCTNam.getSelectedItem().toString().trim());
                try {
                    if (!BaoCao_DAO.kt_baoCao_Quy1(nam)){
                        JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được lập trong quý 1 năm " + nam, "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        BaoCao_DAO.baoCao_Quy1(dtmBCDT, nam);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } 
           } else {
                int nam = Integer.parseInt(cboBCTNam.getSelectedItem().toString().trim());
                try {
                    if (!BaoCao_DAO.kt_baoCao_Quy2(nam)){
                        JOptionPane.showMessageDialog(this, "Không có hóa đơn nào được lập trong quý 2 năm " + nam, "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        BaoCao_DAO.baoCao_Quy2(dtmBCDT, nam);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }          
           }
        }
        int tongDT = 0;
        int coutRow = tblThongTinBC.getRowCount();
        int [] tdt = new int[coutRow];
        for (int i = 0; i < coutRow; i++) {
            tdt[i] = Integer.parseInt(tblThongTinBC.getValueAt(i, 5).toString().trim());
        }
        for (int i = 0; i < coutRow; i++) {
            tongDT += tdt[i];
        }
        txtTongDT.setText("" + tongDT);
    }//GEN-LAST:event_btnBaoCaoActionPerformed

    private void btnRaPhieuBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaPhieuBCActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Thành công !" , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnRaPhieuBCActionPerformed

    private void txtKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKHMouseClicked
        // TODO add your handling code here:
        DialogMaKH sdt = new DialogMaKH(this, true);
        sdt.setVisible(true);
        
        double tien = sdt.getGiamGia();
        int tienGoc = Integer.parseInt(txtTT.getText());
        if (tien == 0.03){
            txtKH.setText(sdt.getSdt());
            double tt = tienGoc - (tienGoc * tien);
            txtTTT.setText("" + (int) tt);            
        } else {
            txtKH.setText(sdt.getSdt());
            txtTTT.setText(txtTT.getText());
        }    
    }//GEN-LAST:event_txtKHMouseClicked

    private void txtKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKHActionPerformed

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
    }//GEN-LAST:event_txtTenSachNhapFocusLost

    private void txtTacGiaNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTacGiaNhapFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTacGiaNhapFocusLost

    private void txtNXBNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNXBNhapFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNXBNhapFocusLost

    private void txtNamXBNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNamXBNhapFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamXBNhapFocusLost

    private void txtTheLoaiNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTheLoaiNhapFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTheLoaiNhapFocusLost

    private void txtSoluongNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoluongNhapFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoluongNhapFocusLost

    private void txtDonGiaNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDonGiaNhapFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaNhapFocusLost

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here
        //--Lấy mã nhân viên
        DialogMaNVNhapHang ma = new DialogMaNVNhapHang(null, true);
        ma.setVisible(true);
        if (!ma.isKq()) return;
        String maNV = ma.getMaNV();

        //--Lấy mã nhà cung cấp
        String mncc = NhaCungCap_DAO.getMaNCC(cboNhaCungCap.getSelectedItem().toString());

        double rd = Math.random();
        int md = (int) (100 + rd * 1000  + 1);
        int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu và thanh toán hóa đơn",
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
                dtmHDTHANHTOAN.setRowCount(0);
                hienThimacDinh();
                cardLayout.show(pnRight, "pnHDNhapSach");
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
            btnRAPHIEUNHAP.setEnabled(false);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int tl = JOptionPane.showConfirmDialog(this, "      Bạn chưa hoàn thành thao tác\n      Bạn có chắc chắn muốn Thoát ?", "Cảnh báo",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (tl == JOptionPane.NO_OPTION) return;
        else{
            txtMaSachNhap.setText("");
            txtTenSachNhap.setText("");
            txtTacGiaNhap.setText("");
            txtNXBNhap.setText("");
            txtNamXBNhap.setText("");
            txtTheLoaiNhap.setText("");
            txtSoluongNhap.setText("");
            txtDonGiaNhap.setText("");
            txtTongThanhTien.setText("");
            dtmNhapSach.setRowCount(0);
            dtmHDTHANHTOAN.setRowCount(0);
            hienThimacDinh();
            cardLayout.show(pnRight, "pnHDNhapSach");
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

        btnRAPHIEUNHAP.setEnabled(true);
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

    private void btnRAPHIEUNHAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRAPHIEUNHAPActionPerformed
        // TODO add your handling code here:
        int soSP = tblEmptyThongTinNhapSach.getRowCount();
        String [] tenS = new String[soSP];
        String [] slSP = new String[soSP];
        String [] thanhTien = new String[soSP];
        
        for (int i = 0; i < soSP; i++) {
            tenS[i] = "" + tblEmptyThongTinNhapSach.getValueAt(i, 1);
            slSP[i] = "" + tblEmptyThongTinNhapSach.getValueAt(i, 6);
            thanhTien[i] = "" + tblEmptyThongTinNhapSach.getValueAt(i, 8);
        }        
        
        for (int i = 0; i < soSP; i++) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(tenS[i]);
            vec.add(slSP[i]);
            vec.add(thanhTien[i]);
            
            dtmHDTHANHTOAN.addRow(vec);
        }
        dtmNhapSach.setRowCount(0);
        txtTTTTTTTTTT.setText(txtTongThanhTien.getText());
        btnXoa.setEnabled(false);
        btnThemSach.setEnabled(false);
        btnRAPHIEUNHAP.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnRAPHIEUNHAPActionPerformed


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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBCDoanhThu;
    private javax.swing.JButton btnBaoCao;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhatThongTinNV;
    private javax.swing.JButton btnHDNhapSach;
    private javax.swing.JButton btnHoaDonBanSach;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnLM;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnLogouts;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnMD;
    private javax.swing.JButton btnMD1;
    private javax.swing.JButton btnRAPHIEUNHAP;
    private javax.swing.JButton btnRaPhieu;
    private javax.swing.JButton btnRaPhieuBC;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnSuaThongTin;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemHDMoi;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThemMaKH;
    private javax.swing.JButton btnThemNCC1;
    private javax.swing.JButton btnThemSach;
    private javax.swing.JButton btnThemVaoHD;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongTinKH;
    private javax.swing.JButton btnThongTinSach;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemNV;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaKhoiHoaDon1;
    private javax.swing.JComboBox<String> cboBCNgay;
    private javax.swing.JComboBox<String> cboBCTNam;
    private javax.swing.JComboBox<String> cboBCTNgay_Thang;
    private javax.swing.JComboBox<String> cboBCTNgay_Thang_Nam;
    private javax.swing.JComboBox<String> cboBCTThang;
    private javax.swing.JComboBox<String> cboBCTThang_Nam;
    private javax.swing.JComboBox<String> cboGiaBanTheoTG;
    private javax.swing.JComboBox<String> cboGiaBanTheoTheLoai;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JComboBox<String> cboTacGia;
    private javax.swing.JComboBox<String> cboTacGiaTheoTheLoai;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JComboBox<String> cboTheLoaiTheoTG;
    private javax.swing.JComboBox<String> cboTimTheoGiaBan;
    private javax.swing.ButtonGroup grpDKBC;
    private javax.swing.ButtonGroup grpLocHDNhapHang;
    private javax.swing.ButtonGroup grpLocKH;
    private javax.swing.ButtonGroup grpQuyBC;
    private javax.swing.ButtonGroup grpSXCTDonNhapHang;
    private javax.swing.ButtonGroup grpSapXepHDBan;
    private javax.swing.ButtonGroup grpTimKiemDonBanHang;
    private javax.swing.ButtonGroup grpTimKiemSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JLabel lblDGN;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMS;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNXB;
    private javax.swing.JLabel lblNamXB;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblTENNV;
    private javax.swing.JLabel lblTG;
    private javax.swing.JLabel lblTS;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JLabel lblanhNV;
    private javax.swing.JMenuItem mnuCTTTNhanVien;
    private javax.swing.JMenuItem mnuDangXuat;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenu mnuKH;
    private javax.swing.JMenu mnuNCC;
    private javax.swing.JMenuItem mnuTKNhanVien;
    private javax.swing.JMenuItem mnuTTCTNCC;
    private javax.swing.JMenuItem mnuThemNV;
    private javax.swing.JMenuItem mnuThoat;
    private javax.swing.JMenu mnuThongTinNV;
    private javax.swing.JMenuItem mnuXoaKH;
    private javax.swing.JMenuItem mnuXoaNCC;
    private javax.swing.JMenuItem mnuXoaNV;
    private javax.swing.JPanel pnBC;
    private javax.swing.JPanel pnBCDoanhThu;
    private javax.swing.JPanel pnBCNgay;
    private javax.swing.JPanel pnBCQuy;
    private javax.swing.JPanel pnBCThang;
    private javax.swing.JPanel pnCauLenh;
    private javax.swing.JPanel pnHDBanSach;
    private javax.swing.JPanel pnHDNhapSach;
    private javax.swing.JPanel pnHoaDonNhap;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnNhaCungCap;
    private javax.swing.JPanel pnRight;
    private javax.swing.JPanel pnThongTinKH;
    private javax.swing.JPanel pnThongTinNV;
    private javax.swing.JPanel pnThongTinSach;
    private javax.swing.JPanel pnThongTinTimKiem;
    private javax.swing.JPanel pnTimKiemTheoGia;
    private javax.swing.JPanel pnTimKiemTheoTacGia;
    private javax.swing.JPanel pnTimTheoTenSach;
    private javax.swing.JPanel pnTimTheoTheLoai;
    private javax.swing.JPanel pnTraCuuSach;
    private javax.swing.JPanel pnTrangChu;
    private javax.swing.JRadioButton radBCNgay;
    private javax.swing.JRadioButton radBCQuy;
    private javax.swing.JRadioButton radBCThang;
    private javax.swing.JRadioButton radGBSX;
    private javax.swing.JRadioButton radGiaBan;
    private javax.swing.JRadioButton radGiaNhap;
    private javax.swing.JRadioButton radNCC;
    private javax.swing.JRadioButton radNgayBan;
    private javax.swing.JRadioButton radNgayLapDon;
    private javax.swing.JRadioButton radSDT;
    private javax.swing.JRadioButton radSL;
    private javax.swing.JRadioButton radSLNhap;
    private javax.swing.JRadioButton radSLSX;
    private javax.swing.JRadioButton radSoLuong;
    private javax.swing.JRadioButton radTT;
    private javax.swing.JRadioButton radTVT;
    private javax.swing.JRadioButton radTVV;
    private javax.swing.JRadioButton radTacGia;
    private javax.swing.JRadioButton radTenMaSach;
    private javax.swing.JRadioButton radTenSach;
    private javax.swing.JRadioButton radTheLoai;
    private javax.swing.JRadioButton radTongTien;
    private javax.swing.JRadioButton radqui1;
    private javax.swing.JRadioButton radqui2;
    private javax.swing.JTable tblCTHoaDon;
    private javax.swing.JTable tblCT_DonNhapHang;
    private javax.swing.JTable tblCT_ThongTinDonBan;
    private javax.swing.JTable tblDSNCC;
    private javax.swing.JTable tblDSNhanVien;
    private javax.swing.JTable tblDonNhapHang;
    private javax.swing.JTable tblEmptyThongTinNhapSach;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblHOADONNHAP;
    private javax.swing.JTable tblThongTinBC;
    private javax.swing.JTable tblThongTinCTSach;
    private javax.swing.JTable tblThongTinDonBan;
    private javax.swing.JTable tblThongTinKH;
    private javax.swing.JTable tblThongTinSach;
    private javax.swing.JTextField txtDTKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiNCC;
    private javax.swing.JTextField txtDonGiaNhap;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailNCC;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtKH;
    private javax.swing.JTextField txtLienHeNCC;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaS;
    private javax.swing.JTextField txtMaSachNhap;
    private javax.swing.JTextField txtNCC;
    private javax.swing.JTextField txtNXB;
    private javax.swing.JTextField txtNXBNhap;
    private javax.swing.JTextField txtNamXB;
    private javax.swing.JTextField txtNamXBNhap;
    private javax.swing.JTextField txtNgayNhapSach;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoluongNhap;
    private javax.swing.JTextField txtTT;
    private javax.swing.JTextField txtTTT;
    private javax.swing.JTextField txtTTTTTTTTTT;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTacGiaNhap;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenMaSach;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTenS;
    private javax.swing.JTextField txtTenSachNhap;
    private javax.swing.JTextField txtTheLoai;
    private javax.swing.JTextField txtTheLoaiNhap;
    private javax.swing.JTextField txtTimKiemTenSach;
    private javax.swing.JTextField txtTinhTrang;
    private javax.swing.JTextField txtTonKho;
    private javax.swing.JTextField txtTongDT;
    private javax.swing.JTextField txtTongThanhTien;
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
    
    private ImageIcon show_anh_NV(String ma){
        NhanVien_DAO dao = new NhanVien_DAO();
        NhanVien nv = dao.show_TT_NhanVien(ma);
        ImageIcon icon = null;
        if (nv != null){
            //---Anh nhan vien
            if (nv.getAnhNV() != null){
                try {
                    Image img = ImageHelper.createImageFromByteArray(nv.getAnhNV(), "jpg");
                    icon = new ImageIcon(img);
                    personalImage = nv.getAnhNV();
                    
                } catch (IOException ex) {
                    Logger.getLogger(DialogCNTTNV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                personalImage = nv.getAnhNV();
                icon = new ImageIcon(getClass().getResource("/img/nv1_1.jpg")); 
            }
        }
        return icon;
    }

    //----------------------------------------------------------------pnTrangChu
    private void cboGiaBanTheoTL() {
        cboGiaBanTheoTheLoai.addItem(" < 10,000 ");
        cboGiaBanTheoTheLoai.addItem(" > 10,000 ");
        cboGiaBanTheoTheLoai.addItem(" > 50,000 ");
        cboGiaBanTheoTheLoai.addItem(" > 100,000 ");
        cboGiaBanTheoTheLoai.addItem(" > 200,000 ");
    }
    
    private void cboGiaBanTheoTG(){
        cboGiaBanTheoTG.addItem(" < 10,000 ");
        cboGiaBanTheoTG.addItem(" > 10,000 ");
        cboGiaBanTheoTG.addItem(" > 50,000 ");
        cboGiaBanTheoTG.addItem(" > 100,000 ");
        cboGiaBanTheoTG.addItem(" > 200,000 ");        
    }
    
    private void cboTimTheoGiaBan(){
        cboTimTheoGiaBan.addItem(" < 10,000 ");
        cboTimTheoGiaBan.addItem(" > 10,000 ");
        cboTimTheoGiaBan.addItem(" > 50,000 ");
        cboTimTheoGiaBan.addItem(" > 100,000 ");
        cboTimTheoGiaBan.addItem(" > 200,000 ");   
    }

    private void showMD_pnTrangChu() {
        btnThemVaoHD.setEnabled(false);
        btnXoaKhoiHoaDon1.setEnabled(false);
        btnRaPhieu.setEnabled(false);
        btnThanhToan.setEnabled(false);
        btnHuyHD.setEnabled(false);
        btnThemMaKH.setEnabled(false);
        txtTT.setEditable(false);
        txtKH.setEditable(false);
        txtTTT.setEditable(false);
    }
    
    //------------------------------------------------------------pnThongTinSach
    private void macdinh() {
        txtMaS.setEditable(false);
        txtTenS.setEditable(false);
        txtTacGia.setEditable(false);
        txtNXB.setEditable(false);
        txtNamXB.setEditable(false);
        txtTheLoai.setEditable(false);
        txtTonKho.setEditable(false);
        txtGiaBan.setEditable(false);
    }
    
    private void showMD_pnThongTinSach() {
        tblThongTinCTSach.setRowSelectionInterval(0, 0);
        txtMaS.setText("" + tblThongTinCTSach.getValueAt(0, 0));
        txtTenS.setText("" + tblThongTinCTSach.getValueAt(0, 1));
        txtTacGia.setText("" + tblThongTinCTSach.getValueAt(0, 2));
        txtNXB.setText("" + tblThongTinCTSach.getValueAt(0, 3));
        txtNamXB.setText("" + tblThongTinCTSach.getValueAt(0, 4));
        txtTheLoai.setText("" + tblThongTinCTSach.getValueAt(0, 5));
        txtTonKho.setText("" + tblThongTinCTSach.getValueAt(0, 6));
        txtGiaBan.setText("" + tblThongTinCTSach.getValueAt(0, 7));
    }

    //-------------------------------------------------------pnThongTinKhachHang
    private void showMD_TTKH() {
        tblThongTinKH.setRowSelectionInterval(0, 0);
        txtMaKH.setText("" + tblThongTinKH.getValueAt(0, 0));
        txtTenKH.setText("" + tblThongTinKH.getValueAt(0, 1));
        txtSDT.setText("" + tblThongTinKH.getValueAt(0, 2));
        txtEmail.setText("" + tblThongTinKH.getValueAt(0, 3));
        txtDiaChi.setText("" + tblThongTinKH.getValueAt(0, 4));
        txtTinhTrang.setText("" + tblThongTinKH.getValueAt(0, 5));
        
        txtMaKH.setEditable(false);
        txtTenKH.setEditable(false);
        txtSDT.setEditable(false);
        txtEmail.setEditable(false);
        txtDiaChi.setEditable(false);
        txtTinhTrang.setEditable(false);
    }
    
    //--------------------------------------------------------pnThongTinNhanVien  
    private void showMD_TTNV(){
        tblDSNhanVien.setRowSelectionInterval(0, 0);
        lblMaNV.setText(tblDSNhanVien.getValueAt(0, 0).toString());
        lblTENNV.setText(tblDSNhanVien.getValueAt(0, 1).toString());
        lblNgaySinh.setText(tblDSNhanVien.getValueAt(0, 2).toString());
        lblGioiTinh.setText(tblDSNhanVien.getValueAt(0, 3).toString());
        lblSDT.setText(tblDSNhanVien.getValueAt(0, 4).toString());
        lblEmail.setText(tblDSNhanVien.getValueAt(0, 5).toString());
        lblDiaChi.setText(tblDSNhanVien.getValueAt(0, 6).toString());
    }

    //------------------------------------------------------pnThongTInNhaCungCap      
    private void showMD_pnNCC(){
        tblDSNCC.setRowSelectionInterval(0, 0);
        txtMaNCC.setText(tblDSNCC.getValueAt(0, 0).toString().trim());
        txtTenNCC.setText(tblDSNCC.getValueAt(0, 1).toString().trim());
        txtLienHeNCC.setText(tblDSNCC.getValueAt(0, 2).toString().trim());
        txtEmailNCC.setText(tblDSNCC.getValueAt(0, 3).toString().trim());
        txtDiaChiNCC.setText(tblDSNCC.getValueAt(0, 4).toString().trim());
    }

    //----------------------------------------------------------pnHoaDonNhapSach
    private boolean kt(boolean c1, boolean c2, boolean c3, boolean c4){
        if (!c1 && !c2 && !c3 && !c4){
            return true;
        }
        return false;
    }
    
    //-----------------------------------------------------------pnHoaDonBanSach        
    private boolean kt1(boolean sl, boolean nn, boolean tt, boolean ncc){
        if (!sl && !nn && !tt && !ncc){
            return true;
        }
        return false;
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
        txtTongThanhTien.setEditable(false);
        txtTongThanhTien.setEnabled(false);
        txtTongThanhTien.setVisible(false);
        //---Lấy ngày hiện tại làm ngày nhập sách
        LocalDate tg = LocalDate.now();
        txtNgayNhapSach.setText(tg.format(DateTimeFormatter.ISO_LOCAL_DATE).toString());   
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        btnRAPHIEUNHAP.setEnabled(false);
        txtTTTTTTTTTT.setText("");
        btnThemSach.setEnabled(true);
    }    

    private void hienThiNhaCungCap() {
        NhaCungCap_DAO.getTenNCC(cboNhaCungCap);
    }
    
    //------------------------------------------------------------------pnBaoCao 
    private void loadCBO(){
        for (int i = 1; i < 32; i++) {
            cboBCNgay.addItem("" + i);
        }
        for (int i = 1; i < 13; i++) {
            cboBCTNgay_Thang.addItem("" + i);
        }
        for (int i = 2010; i < 2022; i++) {
            cboBCTNgay_Thang_Nam.addItem("" + i);
        }
        
        for (int i = 1; i < 13; i++) {
            cboBCTThang.addItem("" + i);
        }
        for (int i = 2010; i < 2022; i++) {
            cboBCTThang_Nam.addItem("" + i);
        }

        for (int i = 2010; i < 2022; i++) {
            cboBCTNam.addItem("" + i);
        }        
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

}

