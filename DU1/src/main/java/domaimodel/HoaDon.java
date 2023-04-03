/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
public class HoaDon implements Serializable {
    @Id
    private String id;
    private Date ngayTao, ngayTT;
    private int tinhTrang;
    
        private String hinhthucthanhtoan;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idkh")
    private KhachHang kh;
    
     @OneToOne
    @JoinColumn(name = "idnv")
    private NhanVien nv;

    public HoaDon(NhanVien nv1) {
        this.nv=nv;
    }

    public HoaDon() {
    }

 

    
    public HoaDon(String id, Date ngayTao, Date ngayTT, int tinhTrang, String hinhthucthanhtoan) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.ngayTT = ngayTT;
        this.tinhTrang = tinhTrang;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }

    public HoaDon(String id, Date ngayTao, Date ngayTT, int tinhTrang, String hinhthucthanhtoan, KhachHang kh, NhanVien nv) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.ngayTT = ngayTT;
        this.tinhTrang = tinhTrang;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.kh = kh;
        this.nv = nv;
    }

   
    
    

    public HoaDon(String id, Date ngayTao, int tinhTrang,
            KhachHang kh, NhanVien nv) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.tinhTrang = tinhTrang;
        this.kh = kh;
        this.nv = nv;
    }


    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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


    public Date getNgayT() {
        return ngayTao;
    }

    public void setNgayT(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

   

    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }


    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", ngayTao=" + ngayTao + ", ngayTT=" + ngayTT + ", tinhTrang=" + tinhTrang + ", hinhthucthanhtoan=" + hinhthucthanhtoan + ", kh=" + kh + ", nv=" + nv + '}';
    }
    
    
}
