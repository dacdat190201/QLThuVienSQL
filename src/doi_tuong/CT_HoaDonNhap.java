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
public class CT_HoaDonNhap {
    private String maDonNhap;
    private String maSach;
    private int slNhap;
    private int giaBan;

    public CT_HoaDonNhap() {
    }

    public CT_HoaDonNhap(String maDonNhap, String maSach, int slNhap, int giaBan) {
        this.maDonNhap = maDonNhap;
        this.maSach = maSach;
        this.slNhap = slNhap;
        this.giaBan = giaBan;
    }

    public String getMaDonNhap() {
        return maDonNhap;
    }

    public void setMaDonNhap(String maDonNhap) {
        this.maDonNhap = maDonNhap;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSlNhap() {
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        this.slNhap = slNhap;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }
    
}
