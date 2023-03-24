/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.GioHang;
import domaimodel.SanPham;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class ChiTietGioHangViewModel {

    private String id;
    private int soLuong;
    private float donGia;
    private Date ngayTao;
    private int trangThai;
    private SanPham sp;
    private GioHang gh;

    public ChiTietGioHangViewModel() {
    }

    public ChiTietGioHangViewModel(String id, int soLuong, float donGia, Date ngayTao, int trangThai, SanPham sp, GioHang gh) {
        this.id = id;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.sp = sp;
        this.gh = gh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public GioHang getGh() {
        return gh;
    }

    public void setGh(GioHang gh) {
        this.gh = gh;
    }

}
