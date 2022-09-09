/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doi_tuong;

/**
 *
 * @author Admin
 */
public class HoaDonBan {
    private String maDonBan;
    private String maNV;
    private String maKH;
    private String ngayBan;
    private int soLuongBan;
    private int tongTIen;

    public HoaDonBan() {
    }

    public HoaDonBan(String maDonBan, String maNV, String maKH, String ngayBan, int soLuongBan, int tongTIen) {
        this.maDonBan = maDonBan;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayBan = ngayBan;
        this.soLuongBan = soLuongBan;
        this.tongTIen = tongTIen;
    }

    public String getMaDonBan() {
        return maDonBan;
    }

    public void setMaDonBan(String maDonBan) {
        this.maDonBan = maDonBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public int getTongTIen() {
        return tongTIen;
    }

    public void setTongTIen(int tongTIen) {
        this.tongTIen = tongTIen;
    }
    
}
