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
public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String sdtNCC;
    private String emailNCC;
    private String diaCHi;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNCC, String tenNCC, String sdtNCC, String emailNCC, String diaCHi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdtNCC = sdtNCC;
        this.emailNCC = emailNCC;
        this.diaCHi = diaCHi;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSdtNCC() {
        return sdtNCC;
    }

    public void setSdtNCC(String sdtNCC) {
        this.sdtNCC = sdtNCC;
    }

    public String getEmailNCC() {
        return emailNCC;
    }

    public void setEmailNCC(String emailNCC) {
        this.emailNCC = emailNCC;
    }

    public String getDiaCHi() {
        return diaCHi;
    }

    public void setDiaCHi(String diaCHi) {
        this.diaCHi = diaCHi;
    }
    
}
