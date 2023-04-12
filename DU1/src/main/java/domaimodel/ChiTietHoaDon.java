/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import domaimodel.SanPham;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author yugip
 */
@Entity
@Table(name = "chitiethoadon")
public class ChiTietHoaDon implements Serializable {

    @EmbeddedId
    private embeddableCTHD SPHD;
    @ManyToOne
    @JoinColumn(name = "idhd", insertable = false, updatable = false)
    private HoaDon hd;
    @ManyToOne
    @JoinColumn(name = "idsp", insertable = false, updatable = false)
    private SanPham sp;

    private int soluong;

    private float donGia, giagiam;

    private Date ngayTao;

    private int trangThai;

    @Transient
    private String maSP, tenSP;
    @Transient
    private int Sluong, SOHD;
    @Transient
    private double TONGTIEN, SKH;

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSluong() {
        return Sluong;
    }

    public void setSluong(int Sluong) {
        this.Sluong = Sluong;
    }

    public int getSOHD() {
        return SOHD;
    }

    public void setSOHD(int SOHD) {
        this.SOHD = SOHD;
    }

    public double getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(double TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public double getSKH() {
        return SKH;
    }

    public void setSKH(double SKH) {
        this.SKH = SKH;
    }

    public ChiTietHoaDon(SanPham sp, HoaDon hd, int soluong, Date ngayTao) {
        this.sp = sp;
        this.soluong = soluong;
        this.hd = hd;
        this.ngayTao = ngayTao;
    }

    public ChiTietHoaDon(embeddableCTHD SPHD, HoaDon hd, SanPham sp, int soluong, float donGia, float giagiam, Date ngayTao, int trangThai) {
        this.SPHD = SPHD;
        this.hd = hd;
        this.sp = sp;
        this.soluong = soluong;
        this.donGia = donGia;
        this.giagiam = giagiam;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(embeddableCTHD SPHD, HoaDon hd, SanPham sp,
            int soluong, float donGia, Date ngayTao,
            int trangThai, float giagiam) {
        this.SPHD = SPHD;
        this.hd = hd;
        this.sp = sp;
        this.soluong = soluong;
        this.donGia = donGia;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.giagiam = giagiam;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public embeddableCTHD getSPHD() {
        return SPHD;
    }

    public void setSPHD(embeddableCTHD SPHD) {
        this.SPHD = SPHD;
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

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getGiagiam() {
        return giagiam;
    }

    public void setGiagiam(float giagiam) {
        this.giagiam = giagiam;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "SPHD=" + SPHD + ", hd=" + hd + ", sp=" + sp + ", soluong=" + soluong + ", donGia=" + donGia + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + '}';
    }

//    public static void main(String[] args) {
//        for (Object object : DBConnection.selectQueRy("from ChiTietHoaDon")) {
//            System.out.println(object);
//        }
//    }
    public Object[] torow() {
        return new Object[]{
            SPHD, (donGia * soluong) - giagiam
        };
    }
}
