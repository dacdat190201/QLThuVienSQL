/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BaoCao_DAO {
    public static void baoCao(DefaultTableModel dtmBC, int ngay, int thang, int nam){
        try {
            String sqlBCNgay = "exec BCngay ?, ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
            preparedStatement.setInt(1, ngay);
            preparedStatement.setInt(2, thang);
            preparedStatement.setInt(3, nam);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                        
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(resultSet.getString("maDonBan"));
                    vec.add(resultSet.getString("maSach"));
                    vec.add(resultSet.getString("soLuongBan"));
                    vec.add(resultSet.getString("giaNhap"));
                    vec.add(resultSet.getString("giaBan"));

                    int slb = resultSet.getInt("soLuongBan");
                    int giaNhap = resultSet.getInt("giaNhap");
                    int giaBan = resultSet.getInt("giaBan");
                    int dt = (giaBan - giaNhap) * slb;

                    vec.add(dt);

                    dtmBC.addRow(vec);
                }              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean kt_baoCao(int ngay, int thang, int nam){
        try {
            String sqlBCNgay = "exec BCngay ?, ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
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
    
    public static void baoCao_Thang(DefaultTableModel dtmBC, int thang, int nam){
        try {
            String sqlBCNgay = "exec BCTHANG ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
            preparedStatement.setInt(1, thang);
            preparedStatement.setInt(2, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                        
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("giaNhap"));
                vec.add(resultSet.getString("giaBan"));

                int slb = resultSet.getInt("soLuongBan");
                int giaNhap = resultSet.getInt("giaNhap");
                int giaBan = resultSet.getInt("giaBan");
                int dt = (giaBan - giaNhap) * slb;

                vec.add(dt);

                dtmBC.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public static boolean kt_baoCao_Thang(int thang, int nam){
        try {
            String sqlBCNgay = "exec BCTHANG ?, ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
            preparedStatement.setInt(1, thang);
            preparedStatement.setInt(2, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                        
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }      

    public static void baoCao_Quy1(DefaultTableModel dtmBC, int nam){
        try {
            String sqlBCNgay = "exec BCQuy1 ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
            preparedStatement.setInt(1, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                        
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("giaNhap"));
                vec.add(resultSet.getString("giaBan"));

                int slb = resultSet.getInt("soLuongBan");
                int giaNhap = resultSet.getInt("giaNhap");
                int giaBan = resultSet.getInt("giaBan");
                int dt = (giaBan - giaNhap) * slb;

                vec.add(dt);

                dtmBC.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }     
    
    public static boolean kt_baoCao_Quy1(int nam){
        try {
            String sqlBCNgay = "exec BCQuy1 ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
            preparedStatement.setInt(1, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    } 
    
    public static void baoCao_Quy2(DefaultTableModel dtmBC, int nam){
        try {
            String sqlBCNgay = "exec BCQuy2 ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
            preparedStatement.setInt(1, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                        
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("giaNhap"));
                vec.add(resultSet.getString("giaBan"));

                int slb = resultSet.getInt("soLuongBan");
                int giaNhap = resultSet.getInt("giaNhap");
                int giaBan = resultSet.getInt("giaBan");
                int dt = (giaBan - giaNhap) * slb;

                vec.add(dt);

                dtmBC.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }     
    
    public static boolean kt_baoCao_Quy2(int nam){
        try {
            String sqlBCNgay = "exec BCQuy2 ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlBCNgay);
            preparedStatement.setInt(1, nam);
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
