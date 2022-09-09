/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doi_tuong;

import java.awt.Image;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private String taiKhoan;
    private String matKhau;
    private String maNV;
    private String tenNV;
    private String ngaySinh;
    private String gioiTInh;
    private String sdt;
    private String email;
    private String diaCHi;
    private byte[] anhNV;

    public NhanVien() {
    }

    public NhanVien(String taiKhoan, String matKhau, String maNV,
            String tenNV, String ngaySinh, String gioiTInh, String sdt, String diaCHi, String email, byte[] anhNV) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTInh = gioiTInh;
        this.sdt = sdt;
        this.diaCHi = diaCHi;
        this.email = email;
        this.anhNV = anhNV;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTInh() {
        return gioiTInh;
    }

    public void setGioiTInh(String gioiTInh) {
        this.gioiTInh = gioiTInh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaCHi() {
        return diaCHi;
    }

    public void setDiaCHi(String diaCHi) {
        this.diaCHi = diaCHi;
    }

    public byte[] getAnhNV() {
        return anhNV;
    }

    public void setAnhNV(byte[] anhNV) {
        this.anhNV = anhNV;
    }
    




    
}
