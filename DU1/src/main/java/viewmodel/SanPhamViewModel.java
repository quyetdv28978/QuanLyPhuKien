/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domainmodel.DanhMuc;
import java.sql.Date;

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
    private String QL;
    private ChatLieuViewModel cl;
    private DanhMucViewModel dm;
    public SanPhamViewModel() {
    }

    public SanPhamViewModel(String ma, String tenSanPham, DanhMucViewModel dm) {
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.dm = dm;
    }

    public SanPhamViewModel(String tenSanPham, DanhMucViewModel dm) {
        this.tenSanPham = tenSanPham;
        this.dm = dm;
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

    @Override
    public String toString() {
        return  tenSanPham ;
    }
     public String toString1() {
        return  dm.getDongSP() ;
    }
    
}
