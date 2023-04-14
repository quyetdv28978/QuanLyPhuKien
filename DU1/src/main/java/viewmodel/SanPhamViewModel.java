/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.DanhMuc;
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
    private Float kichThuoc;
    private String chatLieu;
    private String QL,moTa;
    private DanhMucViewModel dm;
    

    public SanPhamViewModel(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan, Float trongLuong, Float kichThuoc, String chatLieu
            , String QL, DanhMucViewModel dm) {
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
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.QL = QL;
        this.dm = dm;
    }
    
    public SanPhamViewModel(String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            Date ngayTao, Integer trangThai, Integer soLuong, Float giaNhap, Float giaBan, Float kichThuoc, String chatLieu,
            Float trongLuong, String QL, DanhMucViewModel dm) {
        
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
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.dm = dm;
    }
    
    //Đang dùng
    public SanPhamViewModel(String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong, Float kichThuoc, String chatLieu,
            DanhMucViewModel dm, Integer trangThai, String QL) {
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
    
    public SanPhamViewModel(String id, String ma, String tenSanPham, String mauSac, String nhaSanXuat,
            String moTa, Float giaNhap, Float giaBan, Float trongLuong, Integer soLuong, Float kichThuoc, String chatLieu,
            DanhMucViewModel dm, Integer trangThai, String QL) {
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
        this.kichThuoc = kichThuoc;
        this.chatLieu = chatLieu;
        this.dm = dm;
    }

    public SanPhamViewModel(String ma, String tenSanPham, Integer soLuong) {
        this.ma = ma;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
    }

    public SanPhamViewModel(String id, String ma, DanhMucViewModel dm) {
        this.id = id;
        this.ma = ma;
        this.dm = dm;
    }

    //doing
    public SanPhamViewModel(String id, String ma, String tenSanPham, String mauSac, 
            String nhaSanXuat, String moTa, Float giaNhap, Float giaBan, Float trongLuong, 
            Integer soLuong, Float kichThuoc, String chatLieu, DanhMucViewModel dm, int trangThai, String QL) {
        
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

    public Float getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(Float kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
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
            trongLuong,soLuong,kichThuoc,chatLieu,dm,trangThai==0?"Còn hàng":"Hết hàng",QL};
    }
}
