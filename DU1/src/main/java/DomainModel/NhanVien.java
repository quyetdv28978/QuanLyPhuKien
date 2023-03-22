/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

    @Id
    private String id;
    private String ma, ten, gioitinh, diachi, sdt, tentk, mk;
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @OneToOne
    @JoinColumn(name = "idcv")
    private ChucVu cv;

    public NhanVien() {
    }

    public NhanVien(String id, String ma, String ten, String gioitinh, String diachi, String sdt, Date ngaySinh, ChucVu cv, String tentk, String mk) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tentk = tentk;
        this.mk = mk;
        this.ngaySinh = ngaySinh;
        this.cv = cv;
    }

    public NhanVien(String ma, String ten, String gioitinh, String diachi, String sdt, Date ngaySinh, ChucVu cv, String tentk, String mk) {
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tentk = tentk;
        this.mk = mk;
        this.ngaySinh = ngaySinh;
        this.cv = cv;
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

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public ChucVu getCv() {
        return cv;
    }

    public void setCv(ChucVu cv) {
        this.cv = cv;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    
    
}
