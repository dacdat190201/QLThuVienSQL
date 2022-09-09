/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.TonKho;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Admin
 */
public class TonKho_DAO {
    public static boolean update_TOnKho(int sluong, String maSach){
        try {//-----------------Update số lượng tồn kho của các sách đã được bán
            String sqlUP = "update TonKho\n" +
                            "set soLuongTon = soLuongTon - ?\n" +
                            "where maSach = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlUP);
            preparedStatement.setInt(1, sluong);
            preparedStatement.setString(2, maSach);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }
    
    public static boolean update_TOnKho_Nhap(int sluong, String maSach){
        try {//-----------------Update số lượng tồn kho của các sách đã được bán
            String sqlUP = "update TonKho\n" +
                            "set soLuongTon = soLuongTon + ?\n" +
                            "where maSach = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlUP);
            preparedStatement.setInt(1, sluong);
            preparedStatement.setString(2, maSach);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }    
    
    public static boolean insert_TonKho(TonKho tonKho){
        try {
            String sqlTonKho = "insert into TonKho \n" +
            "values(?,?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement prepared = connect.prepareStatement(sqlTonKho);
            prepared.setString(1, tonKho.getMaSAch());
            prepared.setInt(2, tonKho.getSoLuongTon());

            return prepared.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
