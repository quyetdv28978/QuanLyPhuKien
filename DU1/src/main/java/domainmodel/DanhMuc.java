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

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "DanhMuc")
public class DanhMuc implements Serializable{
    @Id
    private String id;
    private String ma;
    private String dongSP;
    private Date ngayTao;
    private String trangThai;

    public DanhMuc() {
    }

    public DanhMuc(String id, String ma, String dongSP, Date ngayTao, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.dongSP = dongSP;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
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

    public String getDongSP() {
        return dongSP;
    }

    public void setDongSP(String dongSP) {
        this.dongSP = dongSP;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public DanhMuc(String id, String ma, String dongSP) {
        this.id = id;
        this.ma = ma;
        this.dongSP = dongSP;
    }
    
            
    
}
