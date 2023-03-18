/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModel.KhachHang;
import java.sql.Date;

/**
 *
 * @author yugip
 */
public class HoaDonView {

    private String id;
    private String hinhthucthanhtoan;
    private KhachHangView kh;
    private NhanVienView nv;
    private Date ngayT, ngayTT;
    private int trangThai;

    public HoaDonView() {
    }

    public HoaDonView(String id, String hinhthucthanhtoan, KhachHangView kh, NhanVienView nv, Date ngayT, Date ngayTT, int trangThai) {
        this.id = id;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.kh = kh;
        this.nv = nv;
        this.ngayT = ngayT;
        this.ngayTT = ngayTT;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }

    public KhachHangView getKh() {
        return kh;
    }

    public void setKh(KhachHangView kh) {
        this.kh = kh;
    }

    public NhanVienView getNv() {
        return nv;
    }

    public void setNv(NhanVienView nv) {
        this.nv = nv;
    }

    public Date getNgayT() {
        return ngayT;
    }

    public void setNgayT(Date ngayT) {
        this.ngayT = ngayT;
    }

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
