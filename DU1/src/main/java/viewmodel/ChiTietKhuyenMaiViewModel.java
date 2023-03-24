/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.KhuyenMai;
import domaimodel.SanPham;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class ChiTietKhuyenMaiViewModel {
     private String id, ma;
    private float giaGiam;
    private Date ngayTao;
    private int trangThai;
    private SanPham sp;
    private KhuyenMai km;

    public ChiTietKhuyenMaiViewModel() {
    }

    public ChiTietKhuyenMaiViewModel(String id, String ma, float giaGiam, Date ngayTao, int trangThai, SanPham sp, KhuyenMai km) {
        this.id = id;
        this.ma = ma;
        this.giaGiam = giaGiam;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.sp = sp;
        this.km = km;
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

    public float getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(float giaGiam) {
        this.giaGiam = giaGiam;
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

    public KhuyenMai getKm() {
        return km;
    }

    public void setKm(KhuyenMai km) {
        this.km = km;
    }
    
}
