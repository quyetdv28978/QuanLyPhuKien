/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import Utilities.DBConnection;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    private float donGia;

    private Date ngayTao;

    private int trangThai;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(embeddableCTHD SPHD, HoaDon hd, SanPham sp, int soluong, float donGia, Date ngayTao, int trangThai) {
        this.SPHD = SPHD;
        this.hd = hd;
        this.sp = sp;
        this.soluong = soluong;
        this.donGia = donGia;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
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

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "SPHD=" + SPHD + ", hd=" + hd + ", sp=" + sp + ", soluong=" + soluong + ", donGia=" + donGia + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + '}';
    }
    
    
    
    public static void main(String[] args) {
        for (Object object : DBConnection.selectQueRy("from ChiTietHoaDon")) {
            System.out.println(object);
        }
    }

}
