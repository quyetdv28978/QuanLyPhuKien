/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhuyenMaiViewModel {
     private String id, ma,tenKM;
    private Date ngayBD, ngayKT;
    private String moTa;
    private Float giaGiam;
    

    public KhuyenMaiViewModel() {
    }

    public KhuyenMaiViewModel(String ma, String tenKM, Date ngayBD, Date ngayKT, String moTa) {
        this.ma = ma;
        this.tenKM = tenKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.moTa = moTa;
    }

    public KhuyenMaiViewModel(String ma, String tenKM, Date ngayBD, Date ngayKT, String moTa, Float giaGiam) {
        
        this.ma = ma;
        this.tenKM = tenKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.moTa = moTa;
        this.giaGiam = giaGiam;
    }
    

    public KhuyenMaiViewModel(String id, String ma, String tenKM, Date ngayBD, Date ngayKT, String moTa) {
        this.id = id;
        this.ma = ma;
        this.tenKM = tenKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.moTa = moTa;
    }
    

    public KhuyenMaiViewModel(String id, String ma, String tenKM, Date ngayBD, Date ngayKT) {
        this.id = id;
        this.ma = ma;
        this.tenKM = tenKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    public String getId() {
        return id;
    }

    public Float getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(Float giaGiam) {
        this.giaGiam = giaGiam;
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

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    
}
