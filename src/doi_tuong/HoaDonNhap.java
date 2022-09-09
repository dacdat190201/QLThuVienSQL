/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doi_tuong;

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
public class HoaDonNhap {
    private String maDonNhap;
    private String maNV;
    private String maNCC;
    private String ngayNhap;
    private int soLuong;
    private int tongTienHD;

    public HoaDonNhap(String maDonNhap, String maNV, String maNCC, String ngayNhap, int soLuong, int tongTienHD) {
        this.maDonNhap = maDonNhap;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.ngayNhap = ngayNhap;
        this.soLuong = soLuong;
        this.tongTienHD = tongTienHD;
    }

    public HoaDonNhap() {
    }

    public String getMaDonNhap() {
        return maDonNhap;
    }

    public void setMaDonNhap(String maDonNhap) {
        this.maDonNhap = maDonNhap;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTienHD() {
        return tongTienHD;
    }

    public void setTongTienHD(int tongTienHD) {
        this.tongTienHD = tongTienHD;
    }
}
