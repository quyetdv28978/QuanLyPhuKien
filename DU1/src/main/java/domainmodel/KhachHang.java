/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "KHACHHANG")
public class KhachHang implements Serializable {

    @Id
    private String id;
    private String ma;
    private String ten;
    private String sdt, diaChi;
    private String gioiTinh;
    private Date ngayTao;
    private int trangThai;
    
    
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;

    public KhachHang() {
    }

    public KhachHang( String ma, String ten, String sdt, String diaChi, String gioiTinh, Date ngaySinh) {
        
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public KhachHang(String id, String ma, String ten, String sdt, String diaChi, String gioiTinh, Date ngaySinh) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }
    

    public KhachHang(String id, String ma, String ten, String sdt, String diaChi, String gioiTinh, Date ngayTao, int trangThai, Date ngaySinh) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.ngaySinh = ngaySinh;
    }
    

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSđt() {
        return sdt;
    }

    public void setSđt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

}
