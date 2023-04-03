/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
//import java.sql.Date;
import java.sql.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import viewmodel.DanhMucViewModel;

@Entity
@Table(name = "SANPHAM")
public class SanPham implements Serializable {

    @Id
    private String id;
    private String ma, tenSanPham, mauSac, nhaSanXuat;
    private Date ngayTao;
    private Integer soLuong;
    private int trangThai;
    private Float giaNhap, giaBan, trongLuong;
    private String QL, moTa;
    private String chatLieu;
    private Float kichThuoc;
    @OneToOne
    @JoinColumn(name = "iddm")
    private DanhMuc dm;

    public SanPham() {
    }
    
    public SanPham(String ma, String tenSanPham) {
        this.ma = ma;
        this.tenSanPham = tenSanPham;
    }

    public SanPham(String id, DanhMuc dm) {
        this.id = id;
        this.dm = dm;
    }

    public SanPham(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            Date ngayTao, int trangThai, Integer soLuong, Float giaNhap, Float giaBan,
            Float trongLuong, Float kichThuoc, String chatLieu, String QL, String moTa, DanhMuc dm) {
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
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.dm = dm;
    }

    public SanPham(String id, String ma, String tenSanPham, Float giaBan, Float kichThuoc, String chatLieu, DanhMuc dm) {
        this.id = id;
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.dm = dm;
    }
    //doing Load
    public SanPham(String id, String ma, String tenSanPham, String mauSac, 
            String nhaSanXuat, String moTa, Float giaNhap, Float giaBan, Float trongLuong, 
            Integer soLuong, Float kichThuoc, String chatLieu, DanhMuc dm, int trangThai, String QL) {
        
        this.id=id;
        this.ma=ma;
        this.tenSanPham=tenSanPham;
        this.mauSac=mauSac;
        this.nhaSanXuat=nhaSanXuat;
        this.moTa=moTa;
        this.giaNhap=giaNhap;
        this.giaBan=giaBan;
        this.trongLuong=trongLuong;
        this.soLuong=soLuong;
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.dm=dm;
        this.trangThai=trangThai;
        this.QL=QL;
    }
    
//    public SanPham(String id, String tenSanPham) {
//        this.id = id;
//        this.tenSanPham = tenSanPham;
//    }

    public SanPham(String ma, String tenSanPham, Integer soLuong) {
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
    }

    public SanPham(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham(String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong, Float kichThuoc, String chatLieu,
            DanhMuc dm, int trangThai, String QL) {
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
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.dm = dm;
    }

    public SanPham(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan,
            Float trongLuong, String QL, String moTa, Float kichThuoc, String chatLieu) {
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
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
    }

    public SanPham(String id, String ma, String tenSanPham, int trangThai, Float giaBan) {
        this.id = id;
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
        this.giaBan = giaBan;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
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

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public Float getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(Float kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public DanhMuc getDm() {
        return dm;
    }

    public void setDm(DanhMuc dm) {
        this.dm = dm;
    }

    public boolean getTT() {
        if (trangThai == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Object[] toRowi() {
        return new Object[]{id, ma, tenSanPham, giaBan, getTT()};
    }

    @Override
    public String toString() {
        return tenSanPham;
    }
}
