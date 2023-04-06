/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 *
 * @author yugip
 */
@Entity
@Table(name = "baohanh")
public class BaoHanh {

    @Id
    private String id;
    private String ma;
    private String ngayTao;
//    private String suaChua;
    private Integer tinhTrang;
//    private Date ngayTra;
//    private Integer soLuongBaoHanh;
    
    private Date ngayBH;
    @OneToOne
    @JoinColumn(name = "idsp")
    private SanPham sp;
    @ManyToOne
    @JoinColumn(name = "idkh")
    private KhachHang kh;

    public BaoHanh() {
    }

    public BaoHanh(String id, String ma, int tinhTrang, SanPham sp, KhachHang kh) {
        this.id = id;
        this.ma = ma;
        this.tinhTrang = tinhTrang;
        this.sp = sp;
        this.kh = kh;
    }

    public BaoHanh(String ma, Date ngayBH, SanPham sanPham, KhachHang khachHang) {
        this.ma = ma;
        this.ngayBH = ngayBH;
        this.sp = sanPham;
        this.kh = khachHang;
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

    public int getTrangthai() {
        return tinhTrang;
    }

    public void setTrangthai(int trangthai) {
        this.tinhTrang = trangthai;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

//    public String getThoiHan() {
//        return thoiHan;
//    }
//
//    public void setThoiHan(String thoiHan) {
//        this.thoiHan = thoiHan;
//    }
//
//    public String getSuaChua() {
//        return suaChua;
//    }
//
//    public void setSuaChua(String suaChua) {
//        this.suaChua = suaChua;
////    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

//    public Date getNgayTra() {
//        return ngayTra;
//    }
//
//    public void setNgayTra(Date ngayTra) {
//        this.ngayTra = ngayTra;
//    }
//
    public Date getNgayBH() {
        return ngayBH;
    }

    public void setNgayBH(Date ngayBH) {
        this.ngayBH = ngayBH;
    }

//    public Integer getSoLuongBaoHanh() {
//        return soLuongBaoHanh;
//    }
//
//    public void setSoLuongBaoHanh(Integer soLuongBaoHanh) {
//        this.soLuongBaoHanh = soLuongBaoHanh;
//    }

    public BaoHanh(String id, String ma, Integer tinhTrang, Date ngayTra, Date ngayBH, Integer soLuongBaoHanh, SanPham sp, KhachHang kh) {
        this.id = id;
        this.ma = ma;
        this.tinhTrang = tinhTrang;
//        this.ngayTra = ngayTra;
//        this.ngayBH = ngayBH;
//        this.soLuongBaoHanh = soLuongBaoHanh;
        this.sp = sp;
        this.kh = kh;
    }
//    

    public BaoHanh(String id,String ma, Date ngayBH,SanPham sp, KhachHang kh) {
        this.id = id;
        this.ma = ma;
        this.ngayBH = ngayBH;
        this.sp = sp;
        this.kh = kh;
    }

   
    
    public Object[] toRowBH(){
    return new Object[] {id,ma,sp.getMa(),sp.getTenSanPham(), kh.getTen(), kh.getSdt(),ngayBH,tinhTrang == 1 ?"Còn Hạn" : "Còn Hạn" };
}

}
