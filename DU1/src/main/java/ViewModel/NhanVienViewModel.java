/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;

/**
 *
 * @author suppe
 */
public class NhanVienViewModel {

    private String id;
    private String ma;
    private String ten;
    private String cmnd;
    private String gioitinh;
    private String diachi;
    private String sdt;
    private String tentk;
    private String mk;
    private Date ngaySinh;
    private Date ngayTao;
    private int trangThai;
    private ChucVuViewModel chucVuViewModel;

    public NhanVienViewModel() {
    }

    public NhanVienViewModel(String id, String ma, String ten, String cmnd, String gioitinh, String diachi, String sdt, String tentk, String mk, Date ngaySinh, Date ngayTao, int trangThai, ChucVuViewModel chucVuViewModel) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.cmnd = cmnd;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tentk = tentk;
        this.mk = mk;
        this.ngaySinh = ngaySinh;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.chucVuViewModel = chucVuViewModel;
    }

    public NhanVienViewModel(String ma, String ten, String cmnd, String gioitinh, String diachi, String sdt, Date ngaySinh, Date ngayTao, int trangThai, ChucVuViewModel chucVuViewModel) {
        this.ma = ma;
        this.ten = ten;
        this.cmnd = cmnd;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.chucVuViewModel = chucVuViewModel;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public ChucVuViewModel getChucVuViewModel() {
        return chucVuViewModel;
    }

    public void setChucVuViewModel(ChucVuViewModel chucVuViewModel) {
        this.chucVuViewModel = chucVuViewModel;
    }

}
