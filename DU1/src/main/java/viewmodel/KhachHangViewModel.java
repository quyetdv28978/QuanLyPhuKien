/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhachHangViewModel {
    private String id;
    private String ma;
    private String ten;
    private String sdt, diaChi;
    private Date ngaySinh;

    public KhachHangViewModel() {
    }

    public KhachHangViewModel(String id, String ma, String ten, String sdt, String diaChi, Date ngaySinh) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSđt() {
        return sdt;
    }

    public void setSđt(String sđt) {
        this.sdt = sđt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public Object[] toRow(){
        return new Object[] {id, ma, ten, sdt,ngaySinh,diaChi};
    }

}
