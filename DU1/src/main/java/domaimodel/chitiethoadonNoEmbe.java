/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import com.itextpdf.text.pdf.PdfName;
import domaimodel.SanPham;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author yugip
 */
@Entity
@Table(name = "chitiethoadon")
public class chitiethoadonNoEmbe implements Serializable {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "idhd")
    private HoaDon hd;
    @ManyToOne
    @JoinColumn(name = "idsp")
    private SanPham sp;
    
    @OneToMany(mappedBy = "cthd",orphanRemoval = true)
    Set<BaoHanh> a;

    private int soluong;

    private float donGia, giagiam;

    private Date ngayTao;

    private int trangThai;

    @Transient
    private String maSP, tenSP, MS, NSX;
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

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    public String getNSX() {
        return NSX;
    }

    public void setNSX(String NSX) {
        this.NSX = NSX;
    }
    
    

    public void setSKH(double SKH) {
        this.SKH = SKH;
    }

    public chitiethoadonNoEmbe(SanPham sp, HoaDon hd, int soluong, Date ngayTao) {
        this.sp = sp;
        this.soluong = soluong;
        this.hd = hd;
        this.ngayTao = ngayTao;
    }

    public chitiethoadonNoEmbe(HoaDon hd, SanPham sp, int soluong, float donGia, float giagiam, Date ngayTao, int trangThai) {
        this.hd = hd;
        this.sp = sp;
        this.soluong = soluong;
        this.donGia = donGia;
        this.giagiam = giagiam;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public chitiethoadonNoEmbe(String id,String maSP, String tenSP) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

   

    public chitiethoadonNoEmbe() {
    }

    public chitiethoadonNoEmbe( HoaDon hd, SanPham sp,
            int soluong, float donGia, Date ngayTao,
            int trangThai, float giagiam) {
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

    public Object[] torow() {
        return new Object[]{
           (donGia * soluong) - giagiam
        };
    }

    public Set<BaoHanh> getA() {
        return a;
    }

    public void setA(Set<BaoHanh> a) {
        this.a = a;
    }

    public Object[] toRowhd() {
        return new Object[]{hd.getId(), hd.getMa(), hd.getKh().getTen(), hd.getKh().getSdt(), hd.getNgayTT(), 0};
    }

    public Object[] toRowhd1() {
        return new Object[]{ sp.getMa(), sp.getTenSanPham(), soluong};
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
