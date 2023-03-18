/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author yugip
 */
@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    private String id;
    private String hinhthucthanhtoan;
    private Date ngayTao, ngayTT;
    private int tinhTrang;
    private Double tongTien;
    
    @OneToOne
    @JoinColumn(name = "idkh")
    private KhachHang kh;
    
     @OneToOne
    @JoinColumn(name = "idnv")
    private NhanVien nv;

    public HoaDon() {
    }

    public HoaDon(String id, String hinhthucthanhtoan, Date ngayTao, Date ngayTT, int tinhTrang, KhachHang kh, NhanVien nv, double tongTien) {
        this.id = id;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.ngayTao = ngayTao;
        this.ngayTT = ngayTT;
        this.tinhTrang = tinhTrang;
        this.kh = kh;
        this.nv = nv;
        this.tongTien = tongTien;
    }


    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
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

    public Date getNgayT() {
        return ngayTao;
    }

    public void setNgayT(Date ngayTao) {
        this.ngayTao = ngayTao;
    }


    public int getTrangThai() {
        return tinhTrang;
    }

    public void setTrangThai(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
