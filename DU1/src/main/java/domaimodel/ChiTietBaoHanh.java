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
@Table(name = "CHITIETBAOHANH")
public class ChiTietBaoHanh implements Serializable{
    @Id
    private String id;
    private String ma;
    private Date ngayBH;
    private String moTa;
    private int tinhTrang;
    private String suaChua;
    private int soLuongBaoHanh, soLanBH;
    private Date ngayTra;
    
    @ManyToOne
    @JoinColumn(name = "idbh")
    private BaoHanh bh;

    public ChiTietBaoHanh() {
    }

//    public ChiTietBaoHanh(String id, Date ngayBH, String moTa, int tinhTrang, String suaChua, int soLuongBaoHanh, Date ngayTra, BaoHanh bh) {
//        this.id = id;
////        this.ma = ma;
//        this.ngayBH = ngayBH;
//        this.moTa = moTa;
//        this.tinhTrang = tinhTrang;
//        this.suaChua = suaChua;
//        this.soLuongBaoHanh = soLuongBaoHanh;
////        this.soLanBH = soLanBH;
//        this.ngayTra = ngayTra;
//        this.bh = bh;
//    }

    public ChiTietBaoHanh(String id, String ma,String moTa, int tinhTrang, String suaChua, int soLuongBaoHanh, Date ngayTra, BaoHanh bh) {
        this.id = id;
        this.ma = ma;
//        this.ngayBH = ngayBH;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.suaChua = suaChua;
        this.soLuongBaoHanh = soLuongBaoHanh;
        this.ngayTra = ngayTra;
        this.bh = bh;
    }

    public ChiTietBaoHanh(String id,String ma,int soLanBH, int tinhTrang,BaoHanh bh) {
        this.id = id;
        this.ma = ma;
        this.tinhTrang = tinhTrang;
        this.soLanBH = soLanBH;
        this.bh = bh;
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

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getSuaChua() {
        return suaChua;
    }

    public void setSuaChua(String suaChua) {
        this.suaChua = suaChua;
    }

    public int getSoLuongBaoHanh() {
        return soLuongBaoHanh;
    }

    public void setSoLuongBaoHanh(int soLuongBaoHanh) {
        this.soLuongBaoHanh = soLuongBaoHanh;
    }

    public int getSoLanBH() {
        return soLanBH;
    }

    public void setSoLanBH(int soLanBH) {
        this.soLanBH = soLanBH;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public BaoHanh getBh() {
        return bh;
    }

    public void setBh(BaoHanh bh) {
        this.bh = bh;
    }

    @Override
    public String toString() {
        return "ChiTietBaoHanh{" + "id=" + id + ", ma=" + ma + ", ngayBH=" + ngayBH + ", moTa=" + moTa + ", tinhTrang=" + tinhTrang + ", suaChua=" + suaChua + ", soLuongBaoHanh=" + soLuongBaoHanh + ", soLanBH=" + soLanBH + ", ngayTra=" + ngayTra + ", bh=" + bh + '}';
    }
    
    public Object[] toRowCTBH(){
        return new Object[]{id,ma,bh.getMa(),bh.getKh().getTen(), bh.getKh().getSdt(), bh.getSp().getMa(),
            bh.getSp().getTenSanPham(),suaChua,bh.getNgayBH(),ngayTra,soLuongBaoHanh, soLanBH, tinhTrang == 0 ?"Đang Xử Lí": "Đã Hoàn Thành",moTa };
        }
    }

