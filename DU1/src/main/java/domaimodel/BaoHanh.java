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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "BAOHANH")
public class BaoHanh implements Serializable{
    @Id
    private String id;
    private String ma;
    private Date ngayTao, ngayBH;
    private String moTa;
    private Integer tinhTrang;
    private String suaChua;
    
    @ManyToOne
    @JoinColumn(name = "idkh")
    private KhachHang kh;
    @OneToOne
    @JoinColumn(name = "idSP")
    private SanPham sp;

    public BaoHanh() {
    }

    public BaoHanh(String id, String ma, Date ngayTao, Date ngayBH, String moTa, Integer tinhTrang, String suaChua, KhachHang kh, SanPham sp) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.ngayBH = ngayBH;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.suaChua = suaChua;
        this.kh = kh;
        this.sp = sp;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayBH() {
        return ngayBH;
    }

    public void setNgayBH(Date ngayBH) {
        this.ngayBH = ngayBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getSuaChua() {
        return suaChua;
    }

    public void setSuaChua(String suaChua) {
        this.suaChua = suaChua;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }
    
    public Object[] toDatatRow() {
        return new Object[]{id,ma,ngayTao,ngayBH,moTa,tinhTrang,suaChua,kh,sp};
    }

}
