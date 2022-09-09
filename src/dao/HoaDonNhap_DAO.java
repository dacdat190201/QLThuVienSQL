/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.HoaDonNhap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class HoaDonNhap_DAO {
    public static void showDS_DonNhapHang(DefaultTableModel dtmDNH){
        dtmDNH.setRowCount(0);
        try {           
            String sqlSL = "exec show_HDNS";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonNhap"));
                vec.add(resultSet.getString("tenNCC"));
                vec.add(resultSet.getString("maNV"));
                vec.add(resultSet.getString("ngayNhap"));
                vec.add(resultSet.getString("soLuong"));
                vec.add(resultSet.getString("tongTienHD"));
                
                dtmDNH.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    public static void showDS_CT_DonNhapHang(DefaultTableModel dtmCTDNH, String maDOnNhap){
        dtmCTDNH.setRowCount(0);        
        try {
            dtmCTDNH.setRowCount(0);
            String sqlSL = "select *from CT_DonNhapHang where maDonNhap = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, maDOnNhap);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonNhap"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongNhap"));
                vec.add(resultSet.getString("giaNhap"));
                
                dtmCTDNH.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }   
    
    public static void sort_SL(DefaultTableModel dtmHDNS){
        try {
            dtmHDNS.setRowCount(0);
            String sqlSL = "exec show_HD_Soluong_Giam";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(resultSet.getString("maDonNhap"));
                    vec.add(resultSet.getString("tenNCC"));
                    vec.add(resultSet.getString("maNV"));
                    vec.add(resultSet.getString("ngayNhap"));
                    vec.add(resultSet.getString("soLuong"));
                    vec.add(resultSet.getString("tongTienHD"));

                    dtmHDNS.addRow(vec);
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public static boolean kt_ngayNhap(int ngay, int thang, int nam){
        try {
            String sqlSL = "select *from DonNhapHang where DAY(ngayNhap) = ? and MONTH(ngayNhap) = ? and YEAR(ngayNhap) = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setInt(1, ngay);
            preparedStatement.setInt(2, thang);
            preparedStatement.setInt(3, nam);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                
                    return true;
                }              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return false;
    }
    
    public static void timKiem_TheoNgayNhap(DefaultTableModel dtmHDNS, String ngay){
        try {
            dtmHDNS.setRowCount(0);
            String sqlSL = "set dateformat DMY\n" +
                            "select maDonNhap, tenNCC, maNV, ngayNhap, soLuong, tongTienHD\n" +
                            "from DonNhapHang, NhaCungCap\n" +
                            "where DonNhapHang.maNCC = NhaCungCap.maNCC\n" +
                            "and ngayNhap = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, ngay);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(resultSet.getString("maDonNhap"));
                    vec.add(resultSet.getString("tenNCC"));
                    vec.add(resultSet.getString("maNV"));
                    vec.add(resultSet.getString("ngayNhap"));
                    vec.add(resultSet.getString("soLuong"));
                    vec.add(resultSet.getString("tongTienHD"));

                    dtmHDNS.addRow(vec);
                }              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    public static void sort_TT(DefaultTableModel dtmHDNS){
        try {
            dtmHDNS.setRowCount(0);
            String sqlSL = "exec show_HD_TongTien_Giam";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(resultSet.getString("maDonNhap"));
                    vec.add(resultSet.getString("tenNCC"));
                    vec.add(resultSet.getString("maNV"));
                    vec.add(resultSet.getString("ngayNhap"));
                    vec.add(resultSet.getString("soLuong"));
                    vec.add(resultSet.getString("tongTienHD"));

                    dtmHDNS.addRow(vec);
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }    
    
    public static boolean check_NCC(String text){
        try {
            String sqlSL = "exec show_HD_TenNCC ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, text);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                
                    return true;
                }              
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }
    
    public static void tim_kiem_Ten(DefaultTableModel dtmHDNS, String text){
        try {
            dtmHDNS.setRowCount(0);
            String sqlSL = "exec show_HD_TenNCC ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, text);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(resultSet.getString("maDonNhap"));
                    vec.add(resultSet.getString("tenNCC"));
                    vec.add(resultSet.getString("maNV"));
                    vec.add(resultSet.getString("ngayNhap"));
                    vec.add(resultSet.getString("soLuong"));
                    vec.add(resultSet.getString("tongTienHD"));

                    dtmHDNS.addRow(vec);
                }              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public static boolean insert_DonNhapHang(HoaDonNhap donNhapHang){
        try {
            //------------------------------Insert vào bảng MuaSach
            String sqlMuaSach = "insert into DonNhapHang values(?, ?, ?, ?, ?, ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement prepared = connect.prepareStatement(sqlMuaSach);
            prepared.setString(1, donNhapHang.getMaDonNhap());
            prepared.setString(2, donNhapHang.getMaNV());
            prepared.setString(3, donNhapHang.getMaNCC());
            prepared.setString(4, donNhapHang.getNgayNhap());
            prepared.setInt(5, donNhapHang.getSoLuong());
            prepared.setInt(6, donNhapHang.getTongTienHD());

            return prepared.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return false;
    }
}
