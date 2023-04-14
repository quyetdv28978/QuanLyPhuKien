/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table (name= "doiHang")
public class DoiHang implements Serializable{
    @Id
    private String id;
    private String
             ma;
    private Date ngayDoi;
    @ManyToOne
    @JoinColumn(name = "idKH")
    private KhachHang kh;
    @ManyToOne
    @JoinColumn(name = "idHD")
    private HoaDon hd;

    public DoiHang() {
    }

    public DoiHang(String id,String ma, Date ngayDoi, KhachHang kh, HoaDon hd) {
        this.id = id;
        this.ma = ma;
        this.ngayDoi = ngayDoi;
        this.kh = kh;
        this.hd = hd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getngayDoi() {
        return ngayDoi;
    }

    public void setngayDoi(Date ngayDoi) {
        this.ngayDoi = ngayDoi;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }
    
//    public Object[] toRow(){
//        return new Object[]{id, ma,}
//    }
}
