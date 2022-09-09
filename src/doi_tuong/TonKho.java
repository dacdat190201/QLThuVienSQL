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
public class TonKho {
    private String maSAch;
    private int soLuongTon;

    public TonKho() {
    }

    public TonKho(String maSAch, int soLuongTon) {
        this.maSAch = maSAch;
        this.soLuongTon = soLuongTon;
    }

    public String getMaSAch() {
        return maSAch;
    }

    public void setMaSAch(String maSAch) {
        this.maSAch = maSAch;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    
    
}
