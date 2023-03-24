/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.KhachHang;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class GioHangViewModel {
     private String id, ma;
    private Date ngayTao;
    private String tenKH;
    private int tinhTrang;
    private KhachHang kh;

    public GioHangViewModel() {
    }

    public GioHangViewModel(String id, String ma, Date ngayTao, String tenKH, int tinhTrang, KhachHang kh) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.tenKH = tenKH;
        this.tinhTrang = tinhTrang;
        this.kh = kh;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }
    
}
