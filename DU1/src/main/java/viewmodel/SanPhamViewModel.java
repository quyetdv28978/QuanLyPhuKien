/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.ChatLieu;
import domaimodel.DanhMuc;
import java.sql.Date;
import viewmodel.ChatLieuViewModel;

/**
 *
 * @author ADMIN
 */
public class SanPhamViewModel {
    
    private String id;
    private String ma,tenSanPham,mauSac,nhaSanXuat;
    private Date ngayTao;
    private Integer trangThai,soLuong;
    private Float giaNhap,giaBan,trongLuong;
    private String QL,moTa;
    private ChatLieuViewModel cl;
    private DanhMucViewModel dm;
    public SanPhamViewModel(String id1, String ma1, String tenSanPham1, String mauSac1, String nhaSanXuat1, String moTa1, Float giaNhap1, Float giaBan1, Float trongLuong1, Integer soLuong1, DanhMuc dm1, ChatLieu cl1, Integer trangThai1, String ql) {
    }

    public SanPhamViewModel(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan, Float trongLuong
            , String QL, ChatLieuViewModel cl, DanhMucViewModel dm) {
        this.id = id;
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.mauSac = mauSac;
        this.nhaSanXuat = nhaSanXuat;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trongLuong = trongLuong;
        this.QL = QL;
        this.cl = cl;
        this.dm = dm;
    }
    
    public SanPhamViewModel(String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan,
            Float trongLuong, String QL, ChatLieuViewModel cl, DanhMucViewModel dm) {
        
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.mauSac = mauSac;
        this.nhaSanXuat = nhaSanXuat;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trongLuong = trongLuong;
        this.QL = QL;
        this.cl = cl;
        this.dm = dm;
    }
    
    //Đang dùng
    public SanPhamViewModel(String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong,
            DanhMucViewModel dm, ChatLieuViewModel cl, Integer trangThai, String QL) {
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.mauSac = mauSac;
        this.nhaSanXuat = nhaSanXuat;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trongLuong = trongLuong;
        this.QL = QL;
        this.moTa = moTa;
        this.cl = cl;
        this.dm = dm;
    }
    
    public SanPhamViewModel(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong,
            DanhMucViewModel dm, ChatLieuViewModel cl, Integer trangThai, String QL) {
        this.id = id;
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.mauSac = mauSac;
        this.nhaSanXuat = nhaSanXuat;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trongLuong = trongLuong;
        this.QL = QL;
        this.moTa = moTa;
        this.cl = cl;
        this.dm = dm;
    }

    public SanPhamViewModel(String ma, String tenSanPham, Integer soLuong) {
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public Float getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(Float trongLuong) {
        this.trongLuong = trongLuong;
    }

    public String getQL() {
        return QL;
    }

    public void setQL(String QL) {
        this.QL = QL;
    }

    public ChatLieuViewModel getCl() {
        return cl;
    }

    public void setCl(ChatLieuViewModel cl) {
        this.cl = cl;
    }

    public DanhMucViewModel getDm() {
        return dm;
    }

    public void setDm(DanhMucViewModel dm) {
        this.dm = dm;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
//    public DanhMucViewModel getDongsp() {
//        return dm;
//    }
//    
//    public ChatLieuViewModel getTencl() {
//        return cl;
//    }
    
    public String nSX() {
        return nhaSanXuat;
    }
    
    public Object[] toDataRow() {
        return new Object[]{id,ma,tenSanPham,mauSac,nhaSanXuat,moTa,giaNhap,giaBan,
            trongLuong,soLuong,dm,cl,trangThai==0?"Hết hàng":"Còn hàng",QL};
    }
}
