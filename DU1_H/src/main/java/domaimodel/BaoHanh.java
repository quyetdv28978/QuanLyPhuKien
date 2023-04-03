/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author yugip
 */
@Entity
@Table(name = "baohanh")
public class BaoHanh implements Serializable{

    @Id
    private String id;
    private String ma;
    private Date ngayTao;
    private String moTa;
    private Integer tinhTrang;
    @OneToOne
    @JoinColumn(name = "idsp")
    private SanPham sp;
    @ManyToOne
    @JoinColumn(name = "idkh")
    private KhachHang kh;

    public BaoHanh() {
    }

    public BaoHanh(String id, String ma, Integer tinhTrang, SanPham sp, KhachHang kh) {
        this.id = id;
        this.ma = ma;
        this.tinhTrang = tinhTrang;
        this.sp = sp;
        this.kh = kh;
    }

    public BaoHanh(String id, String ma, SanPham sp, KhachHang kh, Integer tinhTrang) {
        this.id = id;
        this.ma = ma;
        this.sp = sp;
        this.kh = kh;

        this.tinhTrang = tinhTrang;
    }

    public BaoHanh(SanPham sp, KhachHang kh) {
        this.sp=sp;
        this.kh=kh;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
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

    public Integer getTrangthai() {
        return tinhTrang;
    }

    public void setTrangthai(Integer trangthai) {
        this.tinhTrang = trangthai;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }
    
    public String getTT() {
        if(tinhTrang==0)
            return "Đã hoàn thành";
        else return "Đang sử lí";
    }

    public Object[] toRowBH() {
        return new Object[]{id, ma, sp.getMa(), sp.getTenSanPham(), kh.getTen(),
            kh.getSdt(), "Còn hạn"};
    }

}
