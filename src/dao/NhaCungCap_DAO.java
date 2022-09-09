/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhaCungCap_DAO {
    public static void showDS_NCC(DefaultTableModel dtmNCC){
        dtmNCC.setRowCount(0);
        try {
            String sqlSL = "select *from NhaCungCap";
            Connection conn = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maNCC"));
                vec.add(resultSet.getString("tenNCC"));
                vec.add(resultSet.getString("sdt"));
                vec.add(resultSet.getString("email"));
                vec.add(resultSet.getString("diaChi"));
                
                dtmNCC.addRow(vec);
            } 
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean insert_NCC(NhaCungCap ncc){
        try {
            String sqlINS = "insert into NhaCungCap\n" +
                    "values (?, ?, ?, ?, ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlINS);
            preparedStatement.setString(1, ncc.getMaNCC());
            preparedStatement.setString(2, ncc.getTenNCC());
            preparedStatement.setString(3, ncc.getSdtNCC());
            preparedStatement.setString(4, ncc.getEmailNCC());
            preparedStatement.setString(5, ncc.getDiaCHi());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static NhaCungCap tim_NCC(String mncc){
        try {
            NhaCungCap ncc = new NhaCungCap();
            String sqlSL = "select *from NhaCungCap where maNCC = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSL);
            preparedStatement.setString(1, mncc);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {                
                    ncc.setTenNCC(resultSet.getString("tenNCC"));
                    ncc.setSdtNCC(resultSet.getString("sdt"));
                    ncc.setEmailNCC(resultSet.getString("email"));
                    ncc.setDiaCHi(resultSet.getString("diaChi"));
                    
                    return ncc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }    
    
    public static boolean update_NCC(NhaCungCap ncc){
        try {
            String sqlINS = "update NhaCungCap\n" +
                            "set tenNCC = ?, sdt = ?, email = ?, diaChi = ?\n" +
                            "where maNCC = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlINS);
            preparedStatement.setString(1, ncc.getTenNCC());
            preparedStatement.setString(2, ncc.getSdtNCC());
            preparedStatement.setString(3, ncc.getEmailNCC());
            preparedStatement.setString(4, ncc.getDiaCHi());
            preparedStatement.setString(5, ncc.getMaNCC());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }
    
    public static void getTenNCC(JComboBox cboNCC){
        try {
            String sqlSelectNCC = "select *from NhaCungCap";
            Connection connect = SQLServerProvider.openConnection();
            
            PreparedStatement prepared = connect.prepareStatement(sqlSelectNCC);
            
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {                
                String tenNCC = resultSet.getString("tenNCC");
                
                cboNCC.addItem(tenNCC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cboNCC.setSelectedIndex(0);
        cboNCC.addItem("Custom..........");   
    }
    
    public static String getMaNCC(String tenNCC){
        try {
            String sqlsl = "select *from NhaCungCap where tenNCC = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement prepared = connect.prepareStatement(sqlsl);
            prepared.setString(1, tenNCC);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {                
                return resultSet.getString("maNCC");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static boolean delete_NCC(String maNCC){
        try {
            String sqlDelete = "delete from NhaCungCap where maNCC = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maNCC);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
