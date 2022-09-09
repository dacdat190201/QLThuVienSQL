/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect_database.SQLServerProvider;
import doi_tuong.NhanVien;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVien_DAO {   
    public static void showDS_NhanVien(DefaultTableModel dtmDSNV){
        dtmDSNV.setRowCount(0);
        try {
            String sqlSelect = "select *from NhanVien";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSelect);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {                    
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(rs.getString("maNV"));
                    vec.add(rs.getString("tenNV"));
                    vec.add(rs.getString("ngaySinh"));
                    vec.add(rs.getString("gioiTinh"));
                    vec.add(rs.getString("sdtNV"));
                    vec.add(rs.getString("emailNV"));
                    vec.add(rs.getString("diaChi"));
                    
                    dtmDSNV.addRow(vec);
                } 
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
    
    public static boolean insert_NhanVien(NhanVien nv){
        try {
            String sqlInSert = "set dateformat DMY \n"
                                  + "insert into NhanVien\n" +
                                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connec = SQLServerProvider.openConnection();
            PreparedStatement prepared = connec.prepareStatement(sqlInSert);
            prepared.setString(1, nv.getTaiKhoan());
            prepared.setString(2, nv.getMatKhau());
            prepared.setString(3, nv.getMaNV());
            prepared.setString(4, nv.getTenNV());
            prepared.setString(5, nv.getNgaySinh());
            prepared.setString(6, nv.getGioiTInh());
            prepared.setString(7, nv.getSdt());
            prepared.setString(8, nv.getEmail());
            prepared.setString(9, nv.getDiaCHi());
            if (nv.getAnhNV() != null){
                Blob hinh = new SerialBlob(nv.getAnhNV());
                prepared.setBlob(10, hinh);
            }else {
                Blob hinh = null;
                prepared.setBlob(10, hinh);
            }
            
            return prepared.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public NhanVien getTenNhanVien(String tenDN, String mk){
        try {
            String sqlSL = "select *from NhanVien where taiKhoan = ? and matKhau = ?";
            Connection con = SQLServerProvider.openConnection();
            PreparedStatement prepare = con.prepareStatement(sqlSL);
            prepare.setString(1, tenDN);
            prepare.setString(2, mk);
            try (ResultSet rs = prepare.executeQuery();){
                if (rs.next()){
                    NhanVien nv = new NhanVien();
                    nv.setTaiKhoan(rs.getString("taiKhoan"));
                    nv.setMaNV(rs.getString("maNV"));
                    nv.setTenNV(rs.getString("tenNV"));
                    return nv;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean update_NhanVien(NhanVien nv){
        try {
            String sqlInSert = "set dateformat DMY\n" +
                              " update NhanVien\n" +
                              " set tenNV = ?, ngaySinh = ?, gioiTinh = ?, sdtNV = ?, emailNV = ?, diaChi = ?, anhNV = ?\n" +
                              " where maNV = ?";
            Connection connec = SQLServerProvider.openConnection();
            PreparedStatement prepared = connec.prepareStatement(sqlInSert);

            prepared.setString(1, nv.getTenNV());
            prepared.setString(2, nv.getNgaySinh());
            prepared.setString(3, nv.getGioiTInh());
            prepared.setString(4, nv.getSdt());
            prepared.setString(5, nv.getEmail());
            prepared.setString(6, nv.getDiaCHi());
            
            if (nv.getAnhNV() != null){
                Blob hinh = new SerialBlob(nv.getAnhNV());
                prepared.setBlob(7, hinh);
            }else {
                Blob hinh = null;
                prepared.setBlob(7, hinh);
            }
            prepared.setString(8, nv.getMaNV());
            
            
            return prepared.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean delete_NhanVien(String manv){
        try {
            String sqlInSert = "delete from NhanVien where maNV = ?";
            Connection connec = SQLServerProvider.openConnection();
            PreparedStatement prepared = connec.prepareStatement(sqlInSert);

            prepared.setString(1, manv);
            
            return prepared.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static NhanVien show_TT_NhanVien(String mavn){
        try {
            String sqlSelect = "select *from NhanVien where maNV = ?";
            Connection con  = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sqlSelect);
            preparedStatement.setString(1, mavn);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while (rs.next()) {
                    NhanVien nv = createNhanVien(rs);                   
                    return nv;
                } 
            }        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static NhanVien createNhanVien(final ResultSet rs) throws SQLException{
        NhanVien nv = new NhanVien();
        nv.setTenNV(rs.getString("tenNV"));
        nv.setNgaySinh(rs.getString("ngaySinh"));
        nv.setGioiTInh(rs.getString("gioiTinh"));
        nv.setSdt(rs.getString("sdtNV"));
        nv.setEmail(rs.getString("emailNV"));
        nv.setDiaCHi(rs.getString("diaChi"));

        Blob blob = rs.getBlob("anhNV");
        if (blob != null){
            nv.setAnhNV(blob.getBytes(1, (int) blob.length()));
        }

        return nv;      
    }

    public static boolean check_MaNV(String maNV){
        try {
            String sqlCheck = "select *from NhanVien where maNV = ?";
            Connection connect = SQLServerProvider.openConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sqlCheck);
            preparedStatement.setString(1, maNV);
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
