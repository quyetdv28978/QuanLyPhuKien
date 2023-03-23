/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaiModel;

import java.io.Serializable;
//import java.sql.Date;
import java.sql.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import viewModel.ChatLieuViewModel;
import viewModel.DanhMucViewModel;

@Entity
@Table(name = "SANPHAM")
public class SanPham implements Serializable {

    @Id
    private String id;
    private String ma, tenSanPham, mauSac, nhaSanXuat;
    private Date ngayTao;
    private Integer trangThai, soLuong;
    private Float giaNhap, giaBan, trongLuong;
    private String QL, moTa;
    @OneToOne
    @JoinColumn(name = "idcl")
    private ChatLieu cl;
    @OneToOne
    @JoinColumn(name = "iddm")
    private DanhMuc dm;

    public SanPham() {
    }

    public SanPham(String id, ChatLieu cl, DanhMuc dm) {
        this.id = id;
        this.cl = cl;
        this.dm = dm;
    }

    
    
    

    public SanPham(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat, 
            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan, 
            Float trongLuong, String QL, String moTa, ChatLieu cl, DanhMuc dm) {
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
        this.moTa = moTa;
        this.cl = cl;
        this.dm = dm;
    }

//    public SanPham(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
//            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan,
//            Float trongLuong, String QL, String moTa, ChatLieu cl, DanhMuc dm) {
//        this.id = id;
//        this.ma = ma;
//        this.tenSanPham = tenSanPham;
//        this.mauSac = mauSac;
//        this.nhaSanXuat = nhaSanXuat;
//        this.ngayTao = ngayTao;
//        this.trangThai = trangThai;
//        this.soLuong = soLuong;
//        this.giaNhap = giaNhap;
//        this.giaBan = giaBan;
//        this.trongLuong = trongLuong;
//        this.QL = QL;
//        this.moTa = moTa;
//        this.cl = cl;
//        this.dm = dm;
//    }

//    public SanPham(String id, String tenSanPham, String mauSac, String nhaSanXuat,
//            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong,
//            DanhMuc dm, ChatLieu cl, Integer trangThai, String QR) {
//        this.id = id;
//        this.tenSanPham = tenSanPham;
//        this.mauSac = mauSac;
//        this.nhaSanXuat = nhaSanXuat;
//        this.trangThai = trangThai;
//        this.soLuong = soLuong;
//        this.giaNhap = giaNhap;
//        this.giaBan = giaBan;
//        this.trongLuong = trongLuong;
//        this.QL = QL;
//        this.moTa = moTa;
//        this.cl = cl;
//        this.dm = dm;
//    }

//    public SanPham(String id,String ma, String tenSanPham, String mauSac, String nhaSanXuat,
//            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan,
//            Float trongLuong, String QL, ChatLieu cl, DanhMuc dm) {
//        this.id = id; 
//        this.ma = ma;
//        this.tenSanPham = tenSanPham;
//        this.mauSac = mauSac;
//        this.nhaSanXuat = nhaSanXuat;
//        this.ngayTao = ngayTao;
//        this.trangThai = trangThai;
//        this.soLuong = soLuong;
//        this.giaNhap = giaNhap;
//        this.giaBan = giaBan;
//        this.trongLuong = trongLuong;
//        this.QL = QL;
//        this.cl = cl;
//        this.dm = dm;
//    }

    public SanPham(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong,
            DanhMuc dm, ChatLieu cl, Integer trangThai, String QL) {
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
    
    public SanPham(String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong,
            DanhMuc dm, ChatLieu cl, Integer trangThai, String QL) {
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public ChatLieu getCl() {
        return cl;
    }

    public void setCl(ChatLieu cl) {
        this.cl = cl;
    }

    public DanhMuc getDm() {
        return dm;
    }

    public void setDm(DanhMuc dm) {
        this.dm = dm;
    }

}
