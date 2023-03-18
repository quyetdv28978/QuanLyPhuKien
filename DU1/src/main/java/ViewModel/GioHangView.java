/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.sql.Date;

/**
 *
 * @author yugip
 */
public class GioHangView {
    private String id;
    private String ma, tenkh;
    private Date ngayTao;
    private int tinhtrang;
    private NhanVienView nv;

    public GioHangView() {
    }

    public GioHangView(String id, String ma, String tenkh, Date ngayTao, int tinhtrang, NhanVienView nv) {
        this.id = id;
        this.ma = ma;
        this.tenkh = tenkh;
        this.ngayTao = ngayTao;
        this.tinhtrang = tinhtrang;
        this.nv = nv;
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

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public NhanVienView getNv() {
        return nv;
    }

    public void setNv(NhanVienView nv) {
        this.nv = nv;
    }
}
