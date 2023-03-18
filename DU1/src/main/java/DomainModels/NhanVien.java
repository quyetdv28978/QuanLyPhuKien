/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author suppe
 */
@Entity
@Table(name = "NHANVIEN")
public class NhanVien implements Serializable {

    @Id
    private String idNhanVien;
    private String maNhanVien;
    private String tenNhanVien;
    private String cmnd;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String sdt;
    private String anh;
    private String taiKhoan;
    private String matKhau;
    private Date ngayTao;
    private String idChucVu;
    private int trangThai;
    @ManyToOne
    @JoinColumn(name = "idChucVu")
    private ChucVu chucVu;

    public NhanVien() {
    }

    public NhanVien(String idNhanVien, String maNhanVien, String tenNhanVien, String cmnd, String gioiTinh, Date ngaySinh, String diaChi, String sdt, String anh, String taiKhoan, String matKhau, Date ngayTao, String idChucVu, int trangThai, ChucVu chucVu) {
        this.idNhanVien = idNhanVien;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.cmnd = cmnd;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.anh = anh;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ngayTao = ngayTao;
        this.idChucVu = idChucVu;
        this.trangThai = trangThai;
        this.chucVu = chucVu;
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String cmnd, String gioiTinh, Date ngaySinh, String sdt, int trangThai, ChucVu chucVu) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.cmnd = cmnd;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.trangThai = trangThai;
        this.chucVu = chucVu;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(String idChucVu) {
        this.idChucVu = idChucVu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

}
