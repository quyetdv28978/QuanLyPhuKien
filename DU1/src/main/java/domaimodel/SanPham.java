/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    private String id;
    private String ma, tensanpham, mausac, nhasanxuat, QL, mota;
    private float giaban, gianhap, trongluong;
    private Date ngayTao;
    private int soluong, trangthai; 
    
    @OneToOne
    @JoinColumn(name = "iddm")
    private DanhMuc dm;
    @OneToOne
    @JoinColumn(name = "idcl")
    private ChatLieu cl;

    public SanPham() {
    }

    public SanPham(String id, String ma, String tensanpham, String mausac, String nhasanxuat, String QL, String mota, float giaban, float gianhap, float trongluong, Date ngayTao, int soluong, int trangthai, DanhMuc dm, ChatLieu cl) {
        this.id = id;
        this.ma = ma;
        this.tensanpham = tensanpham;
        this.mausac = mausac;
        this.nhasanxuat = nhasanxuat;
        this.QL = QL;
        this.mota = mota;
        this.giaban = giaban;
        this.gianhap = gianhap;
        this.trongluong = trongluong;
        this.ngayTao = ngayTao;
        this.soluong = soluong;
        this.trangthai = trangthai;
        this.dm = dm;
        this.cl = cl;
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

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getNhasanxuat() {
        return nhasanxuat;
    }

    public void setNhasanxuat(String nhasanxuat) {
        this.nhasanxuat = nhasanxuat;
    }

    public String getQL() {
        return QL;
    }

    public void setQL(String QL) {
        this.QL = QL;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public float getGiaban() {
        return giaban;
    }

    public void setGiaban(float giaban) {
        this.giaban = giaban;
    }

    public float getGianhap() {
        return gianhap;
    }

    public void setGianhap(float gianhap) {
        this.gianhap = gianhap;
    }

    public float getTrongluong() {
        return trongluong;
    }

    public void setTrongluong(float trongluong) {
        this.trongluong = trongluong;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public DanhMuc getDm() {
        return dm;
    }

    public void setDm(DanhMuc dm) {
        this.dm = dm;
    }

    public ChatLieu getCl() {
        return cl;
    }

    public void setCl(ChatLieu cl) {
        this.cl = cl;
    }

    
    
    @Override
    public String toString() {
        return tensanpham;
    }

}