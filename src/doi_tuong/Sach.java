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
public class Sach {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String nhaXB;
    private String theLoai;
    private int nxb;
    private int giaBan;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String tacGia, String nhaXB, String theLoai, int nxb, int giaBan) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.nhaXB = nhaXB;
        this.theLoai = theLoai;
        this.nxb = nxb;
        this.giaBan = giaBan;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getNxb() {
        return nxb;
    }

    public void setNxb(int nxb) {
        this.nxb = nxb;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }
    
    
}
