/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "CHITIETKHUYENMAI")
public class ChiTietKhuyenMai implements Serializable{
    @Id
//    private Embeddedct ei;
    private String  id;
    private String ma;
    private String moTa;
    private String trangThai;
   
    @ManyToOne
    @JoinColumn(name = "idSP")
    private SanPham sp;
    
    @ManyToOne
    @JoinColumn(name = "idKM")
    private KhuyenMai km;

   
    public ChiTietKhuyenMai() {
    }

    public ChiTietKhuyenMai(String id, String ma, SanPham sp, KhuyenMai km) {
        this.id = id;
        this.ma = ma;
//        this.giaGiam = giaGiam;
//        this.ngayTao = ngayTao;
        this.sp = sp;
        this.km = km;
    }
    public ChiTietKhuyenMai( String id, String ma) {
        
        this.id = id;
        this.ma = ma;
    }

    public ChiTietKhuyenMai( String ma, Date ngayTao, String trangThai, SanPham sp, KhuyenMai km) {
        this.ma = ma;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
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

    @Override
    public String toString() {
        return sp.getTenSanPham();
    }
    
     public Object[] toRow1(){
        return new Object[]{id,km.getTenKM(), sp.getTenSanPham(),km.getNgayKT(),km.getGiaGiam(),moTa};
    }

    
}
