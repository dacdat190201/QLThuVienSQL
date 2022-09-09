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
public class CT_HoaDonBan {
    private String maDOnBan;
    private String maSach;
    private int soLuongBan;
    private float giaBan;
    private float thanhTIen;

    public CT_HoaDonBan() {
    }

    public CT_HoaDonBan(String maDOnBan, String maSach, int soLuongBan, float giaBan, float thanhTIen) {
        this.maDOnBan = maDOnBan;
        this.maSach = maSach;
        this.soLuongBan = soLuongBan;
        this.giaBan = giaBan;
        this.thanhTIen = thanhTIen;
    }

    public String getMaDOnBan() {
        return maDOnBan;
    }

    public void setMaDOnBan(String maDOnBan) {
        this.maDOnBan = maDOnBan;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public float getThanhTIen() {
        return thanhTIen;
    }

    public void setThanhTIen(float thanhTIen) {
        this.thanhTIen = thanhTIen;
    }
    
}
