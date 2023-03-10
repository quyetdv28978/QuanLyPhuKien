/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;

public class NhanVienView {

    private String id;
    private String ma, ten, gioitinh, diachi, sdt;
    private Date ngaySinh;
    private int tuoi;
    private ChucVuView cv;

    public NhanVienView() {
    }

    public NhanVienView(String ma, String ten, String gioitinh, String diachi, String sdt, Date ngaySinh, ChucVuView cv, int tuoi) {
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.cv = cv;
        this.tuoi = tuoi;
    }

    public NhanVienView(String id, String ma, String ten, String gioitinh, String diachi, String sdt, Date ngaySinh, ChucVuView cv, int tuoi) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.cv = cv;
        this.tuoi = tuoi;
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

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public ChucVuView getCv() {
        return cv;
    }

    public void setCv(ChucVuView cv) {
        this.cv = cv;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

}
