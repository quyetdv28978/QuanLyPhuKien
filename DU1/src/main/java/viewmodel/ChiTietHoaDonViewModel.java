/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.HoaDon;
import domaimodel.SanPham;
import java.util.Date;


/**
 *
 * @author DELL
 */
public class ChiTietHoaDonViewModel {

    private String id;
    private int soLuong;
    private float donGia;
    private Date ngayTao;
    private int trangThai;
    private HoaDon hd;
    private SanPham sp;
 

    public ChiTietHoaDonViewModel() {
    }

    public ChiTietHoaDonViewModel(String id, int soLuong, float donGia, Date ngayTao, int trangThai, HoaDon hd, SanPham sp) {
        this.id = id;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.hd = hd;
        this.sp = sp;
    }

    public ChiTietHoaDonViewModel(int soLuong, float donGia, Date ngayTao, int trangThai, HoaDon hd, SanPham sp) {
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.hd = hd;
        this.sp = sp;
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

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public Object[] toDataRow() {
        return new Object[]{id,(soLuong*donGia),ngayTao,trangThai,hd,sp};
    }

   

 

}
