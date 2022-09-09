/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.CT_HoaDonNhap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CT_DonNhapHang_DAO {
    public static void sort_GiaNhap(DefaultTableModel dtmCT_HDNS, String ma){
        try {
            String sqlSL = "exec show_HD_GiaNhap ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, ma);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonNhap"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongNhap"));
                vec.add(resultSet.getString("giaNhap"));

                dtmCT_HDNS.addRow(vec);
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sort_SLNhap(DefaultTableModel dtmCT_HDNS, String ma){
        try {
            String sqlSL = "exec show_HD_SLNhap ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, ma);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonNhap"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongNhap"));
                vec.add(resultSet.getString("giaNhap"));

                dtmCT_HDNS.addRow(vec);
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    public static boolean check_Sach(DefaultTableModel dtmCT_HDNS, String text, String ma){
        try {
            String sqlSL = "select *from CT_DonNhapHang, ThongTinSach where CT_DonNhapHang.maSach = ThongTinSach.maSach\n" +
                            "and maDonNhap = ? and (tenSach = ? or ThongTinSach.maSach = ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, ma);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return false;
    }
    
    public static void tim_Theo_Ten(DefaultTableModel dtmCT_HDNS, String text, String ma){
        try {
            String sqlSL = "select *from CT_DonNhapHang, ThongTinSach where CT_DonNhapHang.maSach = ThongTinSach.maSach\n" +
                            "and maDonNhap = ? and (tenSach = ? or ThongTinSach.maSach = ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, ma);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, text);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonNhap"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongNhap"));
                vec.add(resultSet.getString("giaNhap"));

                dtmCT_HDNS.addRow(vec);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean insert_CT_DonNhapHang(CT_HoaDonNhap ct_donNhaphang){
        try {
            //-----thêm vào CT_MuaSach
            String sqlIS = "insert into CT_DonNhapHang\n" +
            "values (?, ?, ?, ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement prepared = connect.prepareStatement(sqlIS);
            prepared.setString(1, ct_donNhaphang.getMaDonNhap());
            prepared.setString(2, ct_donNhaphang.getMaSach());
            prepared.setInt(3, ct_donNhaphang.getSlNhap());
            prepared.setInt(4, ct_donNhaphang.getGiaBan());

            return prepared.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }        
        return false;
    }
}
