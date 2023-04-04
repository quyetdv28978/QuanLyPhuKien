/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domaimodel.KhachHang;
import domaimodel.NhanVien;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class HoaDonViewModel {

    private String id;
    private Date ngayTao, ngayTT;
    private int tinhTrang;
    private String hinhthucthanhtoan;
    private KhachHang kh;
    private NhanVien nv;

    /**
     *
     * @param nv1
     */
    public HoaDonViewModel(NhanVien nv1) {
                this.nv = nv;

    }

//    public HoaDonViewModel(NhanVien nv) {
//    }

    

    public HoaDonViewModel(String id, Date ngayTao, Date ngayTT, int tinhTrang, String hinhthucthanhtoan, KhachHang kh, NhanVien nv) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.ngayTT = ngayTT;
        this.tinhTrang = tinhTrang;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.kh = kh;
        this.nv = nv;
    }

    public HoaDonViewModel(Date ngayTT) {
        this.ngayTT = ngayTT;
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

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }
    
    
    
    
}
