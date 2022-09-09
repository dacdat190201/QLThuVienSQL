/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.MessageDialog;
import connect_database.SQLServerProvider;
import doi_tuong.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Sach_DAO {
    public static void showDL_ThongTinSach(DefaultTableModel dtmThongTInSach){
        dtmThongTInSach.setRowCount(0);
        try {
            String sqlSelect = "select *from ThongTinSach where  giaBan > 0";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSelect);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                    
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(rs.getString("maSach"));
                    vec.add(rs.getString("tenSach"));
                    vec.add(rs.getString("tacGia"));
                    vec.add(rs.getString("nhaXB"));
                    vec.add(rs.getString("namXB"));
                    vec.add(rs.getString("theLoai"));
                    vec.add(rs.getInt("giaBan"));

                    dtmThongTInSach.addRow(vec);
                } 
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showDL_CT_ThongTinSach(DefaultTableModel dtmThongTInSach){
        dtmThongTInSach.setRowCount(0);
        try {
            String sqlSelect = "exec SL_ThongTinCTSach";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSelect);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                    
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(rs.getString("maSach"));
                    vec.add(rs.getString("tenSach"));
                    vec.add(rs.getString("tacGia"));
                    vec.add(rs.getString("nhaXB"));
                    vec.add(rs.getString("namXB"));
                    vec.add(rs.getString("theLoai"));
                    vec.add(rs.getInt("soLuongTon"));
                    vec.add(rs.getInt("giaBan"));

                    dtmThongTInSach.addRow(vec);
                } 
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public static void showDL_Combox_TimKiem(JComboBox cboTL, JComboBox cboTG){
        try {
            String sqlSL = "select *from ThongTinSach";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSL);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                
                    //--------------Tải dữ liệu cho cboTheLoai
                    String tl = rs.getString("theLoai");
                    cboTL.addItem(tl);
                    //--------------Tải dữ liệu cho cboTacGia
                    String tg = rs.getString("tacGia");
                    cboTG.addItem(tg);           
                }
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showDL_Combox_TimKiem_TheoTL(JComboBox tgtl, String tl){
        try {
            String sqlSLec = "select *from ThongTinSach where theLoai = ?";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSLec);
            preparedStatement.setString(1, tl);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                
                    String tg_tl = rs.getString("tacGia");
                    tgtl.addItem(tg_tl);         
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showDL_Combox_TimKiem_TheoTG(JComboBox tltg, String tg){
        try {
            String sqlSLec = "select *from ThongTinSach where tacGia = ?";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSLec);
            preparedStatement.setString(1, tg);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                
                    String tl_tg = rs.getString("theLoai");
                    tltg.addItem(tl_tg);         
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void timKiem_TheoTenSach(DefaultTableModel dtmTTTK, String ten, boolean kq){
        try {
            String sqlSelect = "select *from ThongTinSach where tenSach = ? and giaBan > 0";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            preparedStatement.setString(1, ten);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                  
                Vector<Object> vec = new Vector<>();
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("tenSach"));
                vec.add(resultSet.getString("tacGia"));
                vec.add(resultSet.getString("nhaXB"));
                vec.add(resultSet.getString("namXB"));
                vec.add(resultSet.getString("theLoai"));
                vec.add(resultSet.getInt("giaBan"));
                
                dtmTTTK.addRow(vec);
                kq = true;
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean co_timKiem_TheoTenSach(String ten){
        try {
            String sqlSelect = "select *from ThongTinSach where tenSach = ? and giaBan > 0";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            preparedStatement.setString(1, ten);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                  
                return true;
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void timKiem_TheoTheLoai(DefaultTableModel dtmTKTL, String tl, String tg, String gb){
        try {
            String sqlSelect = "select *from ThongTinSach where theLoai = ? and tacGia = ? and giaBan between ? and ? ";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            preparedStatement.setString(1, tl);
            preparedStatement.setString(2, tg);
            if (gb.equals(" < 10,000 ")){
                preparedStatement.setString(3, "0");
                preparedStatement.setString(4, "10000");
            } else if (gb.equals(" > 10,000 ")){
                preparedStatement.setString(3, "10000");
                preparedStatement.setString(4, "50000");
            } else if (gb.equals(" > 50,000 ")){
                preparedStatement.setString(3, "50000");
                preparedStatement.setString(4, "100000");
            } else if (gb.equals(" > 100,000 ")){
                preparedStatement.setString(3, "100000");
                preparedStatement.setString(4, "200000");
            } else if (gb.equals(" > 200,000 ")){
                preparedStatement.setString(3, "200000");
                preparedStatement.setString(4, "500000");
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                    
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("tenSach"));
                vec.add(resultSet.getString("tacGia"));
                vec.add(resultSet.getString("nhaXB"));
                vec.add(resultSet.getString("namXB"));
                vec.add(resultSet.getString("theLoai"));
                vec.add(resultSet.getInt("giaBan"));

                dtmTKTL.addRow(vec);
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
    public static void timKiem_TheoGiaBan(DefaultTableModel dtmTKTG, String gb){
        try {
            String sqlSelect = "select *from ThongTinSach where giaBan between ? and ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            if (gb.equals(" < 10,000 ")){
                preparedStatement.setString(1, "0");
                preparedStatement.setString(2, "10000");
            } else if (gb.equals(" > 10,000 ")){
                preparedStatement.setString(1, "10000");
                preparedStatement.setString(2, "50000");
            } else if (gb.equals(" > 50,000 ")){
                preparedStatement.setString(1, "50000");
                preparedStatement.setString(2, "100000");
            } else if (gb.equals(" > 100,000 ")){
                preparedStatement.setString(1, "100000");
                preparedStatement.setString(2, "200000");
            } else if (gb.equals(" > 200,000 ")){
                preparedStatement.setString(1, "200000");
                preparedStatement.setString(2, "500000");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                    
                Vector<Object> vec = new Vector<Object>();
                vec.add(resultSet.getString("maSach"));
                vec.add(resultSet.getString("tenSach"));
                vec.add(resultSet.getString("tacGia"));
                vec.add(resultSet.getString("nhaXB"));
                vec.add(resultSet.getString("namXB"));
                vec.add(resultSet.getString("theLoai"));
                vec.add(resultSet.getInt("giaBan"));

                dtmTKTG.addRow(vec);
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
    public static boolean update_ThongTinSach(Sach sach){
        try {
            String sqlUP = "update ThongTinSach\n" +
                            "set tenSach = ?, tacGia = ?, nhaXB = ?, namXB = ?, theLoai = ?, giaBan = ?\n" +
                            "where  maSach = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlUP);
            preparedStatement.setString(1, sach.getTenSach());
            preparedStatement.setString(2, sach.getTacGia());
            preparedStatement.setString(3, sach.getNhaXB());
            preparedStatement.setInt(4, sach.getNxb());
            preparedStatement.setString(5, sach.getTheLoai());
            preparedStatement.setInt(6, sach.getGiaBan());
            preparedStatement.setString(7, sach.getMaSach());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void showDL_CT_pnThongTinSach(DefaultTableModel dtmThongTInSach){
        dtmThongTInSach.setRowCount(0);
        try {
            String sqlSelect = "exec SL_ThongTinCTSach";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSelect);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                    
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(rs.getString("maSach"));
                    vec.add(rs.getString("tenSach"));
                    vec.add(rs.getString("tacGia"));
                    vec.add(rs.getString("nhaXB"));
                    vec.add(rs.getString("namXB"));
                    vec.add(rs.getString("theLoai"));
                    vec.add(rs.getString("soLuongTon"));
                    vec.add(rs.getInt("giaBan"));

                    dtmThongTInSach.addRow(vec);
                } 
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean Check_GiaBan_Sach(String ms, int gb){
        int gia = 0;
        try {
            String sqlcheck = "exec GiaNhap_GanNhat ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlcheck);
            preparedStatement.setString(1, ms);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                    
                gia = resultSet.getInt("giaNhap");
            }
            if (gia > gb){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return true;
    }
    
    public static boolean update_GiaBanSach(Sach sach){
        try {
            String sqlUP = "update ThongTinSach\n" +
                            "set giaBan = ? \n" +
                            "where maSach = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlUP);
            preparedStatement.setInt(1, sach.getGiaBan());
            preparedStatement.setString(2, sach.getMaSach());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }        
        return false;
    }
    
    public static boolean check_Ma_Sach(String mas){
        try {
            String sqlCheck = "select *from ThongTinSach where maSach = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement prepared = connect.prepareStatement(sqlCheck);
            prepared.setString(1, mas);
            
            ResultSet resultSet = prepared.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static Sach get_TT_Sach(String maS){
        try {
            String sqlSL = "select *from ThongTinSach where maSach = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement prepared = connect.prepareStatement(sqlSL);
            prepared.setString(1, maS);

            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Sach sach = new Sach();
                sach.setTenSach(resultSet.getString("tenSach"));
                sach.setTacGia(resultSet.getString("tacGia"));
                sach.setNhaXB(resultSet.getString("nhaXB"));
                sach.setNxb(resultSet.getInt("namXB"));
                sach.setTheLoai(resultSet.getString("theLoai"));
                
                return sach;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    public static boolean insert_Sach(Sach sach){
        try {
            //---------------Insert thông tin sách vào bảng ThongTinSach
            String sqlInsert = "insert into ThongTinSach(maSach, tenSach, tacGia, nhaXB, namXB, theLoai)\n" +
                                "values(?, ?, ?, ?, ?, ?)";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement prepared = connect.prepareStatement(sqlInsert);
            prepared.setString(1, sach.getMaSach());
            prepared.setString(2, sach.getTenSach());
            prepared.setString(3, sach.getTacGia());
            prepared.setString(4, sach.getNhaXB());
            prepared.setInt(5, sach.getNxb());
            prepared.setString(6, sach.getTheLoai());

            return prepared.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean check_TheoTheLoai(String tl, String tg, String gb){
        try {
            String sqlSelect = "select *from ThongTinSach where theLoai = ? and tacGia = ? and giaBan between ? and ? ";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            preparedStatement.setString(1, tl);
            preparedStatement.setString(2, tg);
            if (gb.equals(" < 10,000 ")){
                preparedStatement.setString(3, "0");
                preparedStatement.setString(4, "10000");
            } else if (gb.equals(" > 10,000 ")){
                preparedStatement.setString(3, "10000");
                preparedStatement.setString(4, "50000");
            } else if (gb.equals(" > 50,000 ")){
                preparedStatement.setString(3, "50000");
                preparedStatement.setString(4, "100000");
            } else if (gb.equals(" > 100,000 ")){
                preparedStatement.setString(3, "100000");
                preparedStatement.setString(4, "200000");
            } else if (gb.equals(" > 200,000 ")){
                preparedStatement.setString(3, "200000");
                preparedStatement.setString(4, "500000");
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {                    
                return true;
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return false;
    }
    
    public static boolean check_TheoGiaBan(String gb){
        try {
            String sqlSelect = "select *from ThongTinSach where giaBan between ? and ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            if (gb.equals(" < 10,000 ")){
                preparedStatement.setString(1, "0");
                preparedStatement.setString(2, "10000");
            } else if (gb.equals(" > 10,000 ")){
                preparedStatement.setString(1, "10000");
                preparedStatement.setString(2, "50000");
            } else if (gb.equals(" > 50,000 ")){
                preparedStatement.setString(1, "50000");
                preparedStatement.setString(2, "100000");
            } else if (gb.equals(" > 100,000 ")){
                preparedStatement.setString(1, "100000");
                preparedStatement.setString(2, "200000");
            } else if (gb.equals(" > 200,000 ")){
                preparedStatement.setString(1, "200000");
                preparedStatement.setString(2, "500000");
            }
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
