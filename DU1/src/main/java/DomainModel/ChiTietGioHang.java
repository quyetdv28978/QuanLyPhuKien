/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import Utilities.DBConnection;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
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
@Table(name = "chitietgiohang")
public class ChiTietGioHang implements Serializable {

    @EmbeddedId
    private embeddableCTGH idDouble;
    @ManyToOne
    @JoinColumn(name = "idsp", insertable = false, updatable = false)
    private SanPham sp;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idgh", insertable = false, updatable = false)
    private GioHang gh;

    private int soLuong, trangthai;
    private Date ngayTao;

    public ChiTietGioHang() {
    }

    public ChiTietGioHang(embeddableCTGH idDouble, SanPham sp, GioHang gh, int soLuong, int trangthai, Date ngayTao) {
        this.idDouble = idDouble;
        this.sp = sp;
        this.gh = gh;
        this.soLuong = soLuong;
        this.trangthai = trangthai;
        this.ngayTao = ngayTao;
    }

    public embeddableCTGH getIdDouble() {
        return idDouble;
    }

    public void setIdDouble(embeddableCTGH idDouble) {
        this.idDouble = idDouble;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public GioHang getGh() {
        return gh;
    }

    public void setGh(GioHang gh) {
        this.gh = gh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "ChiTietGioHang{" + "idDouble=" + idDouble + ", sp=" + sp + ", gh=" + gh + ", soLuong=" + soLuong + ", trangthai=" + trangthai + ", ngayTao=" + ngayTao + '}';
    }
    
    
}
