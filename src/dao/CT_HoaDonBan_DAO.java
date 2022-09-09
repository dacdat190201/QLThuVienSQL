/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.CT_HoaDonBan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CT_HoaDonBan_DAO {
    public static boolean insert_CT_HoaDonBan(CT_HoaDonBan ct){
        try {
            String sqlINS = "insert into CT_DonBanHang\n"
                    + "values(?, ?, ?, ?, ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlINS);
            preparedStatement.setString(1, ct.getMaDOnBan());
            preparedStatement.setString(2, ct.getMaSach());
            preparedStatement.setInt(3, ct.getSoLuongBan());
            preparedStatement.setFloat(4, ct.getGiaBan());
            preparedStatement.setFloat(5, ct.getThanhTIen());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void show_CT_HoaDonBan(DefaultTableModel dtmCT_HDBanSach, String maDonBan){
        try {
            dtmCT_HDBanSach.setRowCount(0);
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
                
                dtmCT_HDBanSach.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    public static void sort_CT_HoaDonBan_SLBan(DefaultTableModel dtmCT_HDBanSach, String ma){
        try {
            dtmCT_HDBanSach.setRowCount(0);
            String sqlSX = "select *from CT_DonBanHang where maDonBan = ? order by soLuongBan DESC";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSX);
            preparedStatement.setString(1, ma);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                        
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("giaBan"));
                vec.add(resultSet.getString("thanhTien"));

                dtmCT_HDBanSach.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void sort_CT_HoaDonBan_GiaBan(DefaultTableModel dtmCT_HDBanSach, String ma){
        try {
            dtmCT_HDBanSach.setRowCount(0);
            String sqlSX = "select *from CT_DonBanHang where maDonBan = ? order by giaBan DESC";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSX);
            preparedStatement.setString(1, ma);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                        
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maDonBan"));
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("soLuongBan"));
                vec.add(resultSet.getString("giaBan"));
                vec.add(resultSet.getString("thanhTien"));

                dtmCT_HDBanSach.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
