/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhachHang_DAO {
    public static void showDS_KhachHang(DefaultTableModel dtmDSKH){
        dtmDSKH.setRowCount(0);
        try {
            String sqlSelect = "select *from KhachHang";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSelect);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                    
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(rs.getString("maKH"));
                    vec.add(rs.getString("tenKH"));
                    vec.add(rs.getString("sdt"));
                    vec.add(rs.getString("email"));
                    vec.add(rs.getString("diaChi"));
                    vec.add(rs.getString("tinhTrang"));

                    dtmDSKH.addRow(vec);
                } 
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
    
    public static boolean delete_KhachHang(String makh){
        try {
            String sqlDelete = "delete from KhachHang where maKH = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlDelete);
            preparedStatement.setString(1, makh);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean insert_KhachHang(KhachHang kh){
        try {
            String sqlIS = "insert into KhachHang (maKH, tenKH, sdt, email, diaChi)\n" +
                            "values(?, ?, ?, ?, ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlIS);
            preparedStatement.setString(1, kh.getMaKH());
            preparedStatement.setString(2, kh.getTenKH());
            preparedStatement.setString(3, kh.getSDT());
            preparedStatement.setString(4, kh.getEmail());
            preparedStatement.setString(5, kh.getDiaChi());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static String getMa_fromSDT(String sdt){
        try {
            String sqlSL = "select *from KhachHang where  sdt = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, sdt);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                return resultSet.getString("maKH");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return "";
    }
    
    public static int check_TinhTrang_KhachHang(String makh) throws Exception{
        int count = 0;
        String sqlktUP = "select *from DonBanHang where  maKH = ?";
        Connection connect = SQLServerProvider.openConnection();
        PreparedStatement preparedStatement = connect.prepareStatement(sqlktUP);
        preparedStatement.setString(1, makh);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {                
            count++;
        }
        return count;
    }
    
    public static boolean update_TinhTrang_KH(String makh) {
        try {
            String sqlup = "update KhachHang\n" +
                            "set tinhTrang = N'Thành viên Vip'\n" +
                            "where maKH = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlup);
            preparedStatement.setString(1, makh);

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void getKhachHang_fromSDT(DefaultTableModel dtmDSKH, String sdt){
        try {
            String sqlSL = "select *from KhachHang where  sdt = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, sdt);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                    
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(rs.getString("maKH"));
                    vec.add(rs.getString("tenKH"));
                    vec.add(rs.getString("sdt"));
                    vec.add(rs.getString("email"));
                    vec.add(rs.getString("diaChi"));
                    vec.add(rs.getString("tinhTrang"));

                    dtmDSKH.addRow(vec);
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean check_KhachHang_fromSDT(String sdt){
        try {
            String sqlSL = "select *from KhachHang where  sdt = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, sdt);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                    
                    return true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }    
    
    public static boolean update_ThongTIn_KH(KhachHang kh) {
        try {
            String sqlup = "update KhachHang\n" +
                                "set tenKH = ?, sdt = ?, diaChi = ?, email = ?\n" +
                                "where maKH = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlup);
            preparedStatement.setString(1, kh.getTenKH());
            preparedStatement.setString(2, kh.getSDT());
            preparedStatement.setString(3, kh.getDiaChi());
            preparedStatement.setString(4, kh.getEmail());
            preparedStatement.setString(5, kh.getMaKH());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }    
    
    public static void sort_KH_TheoTinhTrang_VIP(DefaultTableModel dtmTTKH){
        try {
            String sqlSX = "select *from KhachHang order by tinhTrang DESC";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSX);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(resultSet.getString("maKH"));
                    vec.add(resultSet.getString("tenKH"));
                    vec.add(resultSet.getString("sdt"));
                    vec.add(resultSet.getString("email"));
                    vec.add(resultSet.getString("diaChi"));
                    vec.add(resultSet.getString("tinhTrang"));

                    dtmTTKH.addRow(vec);
                }                    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public static void sort_KH_TheoTinhTrang_THUONG(DefaultTableModel dtmTTKH){
        try {
            String sqlSX = "select *from KhachHang order by tinhTrang ASC";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSX);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(resultSet.getString("maKH"));
                    vec.add(resultSet.getString("tenKH"));
                    vec.add(resultSet.getString("sdt"));
                    vec.add(resultSet.getString("email"));
                    vec.add(resultSet.getString("diaChi"));
                    vec.add(resultSet.getString("tinhTrang"));

                    dtmTTKH.addRow(vec);
                }                    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
