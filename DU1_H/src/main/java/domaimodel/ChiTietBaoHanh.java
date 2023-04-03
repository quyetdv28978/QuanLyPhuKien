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
import javax.persistence.Table;

@Entity
@Table(name = "chitietbaohanh")
public class ChiTietBaoHanh implements Serializable {

    @Id
    private String id;
    private String ma;
    private Integer soLanBH;
    private Date ngayBH;
    private String suaChua, thoiHan;
    private Integer soLuongBaoHanh;
    private Date ngayTra;
    private Integer tinhTrang;
    private String mota;
    @ManyToOne
    @JoinColumn(name = "idBH")
    private BaoHanh bh;

    public ChiTietBaoHanh() {
    }

    public ChiTietBaoHanh(String id, String ma, Integer soLanBH, Date ngayBH, String suaChua, String thoiHan, Integer soLuongBaoHanh, Date ngayTra, Integer tinhTrang, String mota, BaoHanh bh) {
        this.id = id;
        this.ma = ma;
        this.soLanBH = soLanBH;
        this.ngayBH = ngayBH;
        this.suaChua = suaChua;
        this.thoiHan = thoiHan;
        this.soLuongBaoHanh = soLuongBaoHanh;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
        this.mota = mota;
        this.bh = bh;
    }
    //doing
    public ChiTietBaoHanh(String id, String ma, BaoHanh bh, Integer soLanBH, Date ngayBH, Date ngayTra,
            Integer soLuongBaoHanh, String suaChua, Integer tinhTrang, String mota) {
        this.id = id;
        this.ma = ma;
        this.bh = bh;
        this.soLanBH = soLanBH;
        this.ngayBH = ngayBH;
        this.ngayTra = ngayTra;
        this.soLuongBaoHanh = soLuongBaoHanh;
        this.suaChua = suaChua;
        this.tinhTrang = tinhTrang;
        this.mota = mota;
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

    public Integer getSoLanBH() {
        return soLanBH;
    }

    public void setSoLanBH(Integer soLanBH) {
        this.soLanBH = soLanBH;
    }

    public Date getNgayBH() {
        return ngayBH;
    }

    public void setNgayBH(Date ngayBH) {
        this.ngayBH = ngayBH;
    }

    public String getSuaChua() {
        return suaChua;
    }

    public void setSuaChua(String suaChua) {
        this.suaChua = suaChua;
    }

    public String getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(String thoiHan) {
        this.thoiHan = thoiHan;
    }

    public Integer getSoLuongBaoHanh() {
        return soLuongBaoHanh;
    }

    public void setSoLuongBaoHanh(Integer soLuongBaoHanh) {
        this.soLuongBaoHanh = soLuongBaoHanh;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public BaoHanh getBh() {
        return bh;
    }

    public void setBh(BaoHanh bh) {
        this.bh = bh;
    }

    public Object[] toDataCTBH() {
        return new Object[]{id, ma, bh.getSp().getMa(), bh.getSp().getTenSanPham(),
            bh.getKh().getTen(), bh.getKh().getSdt(), soLanBH, ngayBH,
            ngayTra, soLuongBaoHanh, suaChua,
            "Đang sử lí", mota};
    }

}
