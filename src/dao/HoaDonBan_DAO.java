/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.HoaDonBan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class HoaDonBan_DAO {
    public static void showDS_DonBanHang(DefaultTableModel dtmDBH){
        dtmDBH.setRowCount(0);
        try {
            String sqlLayDS = "select *from DonBanHang";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlLayDS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maNV"));
                vec.add(resultSet.getString("maKH"));
                vec.add(resultSet.getString("ngayBan"));
                vec.add(resultSet.getInt("soLuongBan"));
                vec.add(resultSet.getInt("tongTien"));
                
                dtmDBH.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showDS_CT_DonBanHang(DefaultTableModel dtmDBH, String maDonBan){
        try {
            dtmDBH.setRowCount(0);
            String sqlSl = "select *from CT_DonBanHang where maDonBan = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSl);
            preparedStatement.setString(1, maDonBan);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("giaBan"));
                vec.add(resultSet.getString("thanhTien"));

                dtmDBH.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public static int getSLTon(String ms){
        int slTOn = 0;
        try {
            String sqlCheck = "select *from TonKho where maSach = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlCheck);
            preparedStatement.setString(1, ms);
            ResultSet  resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                slTOn = resultSet.getInt("soLuongTon");
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return slTOn;
    }
    
    public static boolean insert_DonBanHang(HoaDonBan donBan){
        try {
            String sqlIS = "insert into DonBanHang\n" +
                            "values (?, ?, ?, GETDATE(), ?, ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlIS);
            preparedStatement.setString(1, donBan.getMaDonBan());
            preparedStatement.setString(2, donBan.getMaNV());
            preparedStatement.setString(3, donBan.getMaKH());
            preparedStatement.setInt(4, donBan.getSoLuongBan());
            preparedStatement.setInt(5, donBan.getTongTIen());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void sort_DS_DonBanHang_SL(DefaultTableModel dtmHDBanSach){
        try {
            dtmHDBanSach.setRowCount(0);
            String sqlSL = "exec show_HD_BanSach";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maNV"));
                vec.add(resultSet.getString("maKH"));
                vec.add(resultSet.getString("ngayBan"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("tongTien"));

                dtmHDBanSach.addRow(vec);
            }               
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sort_DS_DonBanHang_NgayBan(DefaultTableModel dtmHDBanSach, int ngay, int thang, int nam){
        try {
            dtmHDBanSach.setRowCount(0);
            String sqlSL = "select *from DonBanHang where DAY(ngayBan) = ? "
                    + "and MONTH(ngayBan) = ? and YEAR(ngayBan) = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setInt(1, ngay);
            preparedStatement.setInt(2, thang);
            preparedStatement.setInt(3, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maNV"));
                vec.add(resultSet.getString("maKH"));
                vec.add(resultSet.getString("ngayBan"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("tongTien"));

                dtmHDBanSach.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }    
    
    public static boolean kt_DS_DonBanHang_NgayBan(int ngay, int thang, int nam){
        try {
            String sqlSL = "select *from DonBanHang where DAY(ngayBan) = ? "
                    + "and MONTH(ngayBan) = ? and YEAR(ngayBan) = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setInt(1, ngay);
            preparedStatement.setInt(2, thang);
            preparedStatement.setInt(3, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return false;
    } 
    
    public static void sort_DS_DonBanHang_TongTien(DefaultTableModel dtmHDBanSach){
        try {
            dtmHDBanSach.setRowCount(0);
            String sqlSL = "exec show_HD_BanSach_TT";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maNV"));
                vec.add(resultSet.getString("maKH"));
                vec.add(resultSet.getString("ngayBan"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("tongTien"));

                dtmHDBanSach.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }     
    
    public static void sort_DS_DonBanHang_Ma_SDT(DefaultTableModel dtmHDBanSach, String kh){
        try {
            dtmHDBanSach.setRowCount(0);
            String sqlSL = "exec show_HD_BanSach_Ma_SDT ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, kh);
            preparedStatement.setString(2, kh);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maNV"));
                vec.add(resultSet.getString("maKH"));
                vec.add(resultSet.getString("ngayBan"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("tongTien"));

                dtmHDBanSach.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }   
    
    public static boolean kt_DS_DonBanHang_Ma_SDT(String kh){
        try {
            String sqlSL = "exec show_HD_BanSach_Ma_SDT ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, kh);
            preparedStatement.setString(2, kh);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }     
}
