/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.HoaDon;
import domaimodel.KhachHang;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class DoiTraViewModel {
     private String id;
    private Date ngayTao;
    private String lyDo;
    private int trangThai;
    private KhachHang kh;
    private HoaDon hd;

    public DoiTraViewModel() {
    }

    public DoiTraViewModel(String id, Date ngayTao, String lyDo, int trangThai, KhachHang kh, HoaDon hd) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.lyDo = lyDo;
        this.trangThai = trangThai;
        this.kh = kh;
        this.hd = hd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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
    
}
