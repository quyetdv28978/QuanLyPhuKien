/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domainmodel.Embeddedct;
import domainmodel.SanPham;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ChiTietKhuyenMaiViewModel {
    private Embeddedct ei;
    private String  id;
    private  String ma;
    private float giaGiam;
    private SanPhamViewModel spvm;
    private KhuyenMaiViewModel kmvm;

    public ChiTietKhuyenMaiViewModel() {
    }

    public ChiTietKhuyenMaiViewModel(String id, String ma, float giaGiam, SanPhamViewModel spvm, KhuyenMaiViewModel kmvm) {
        this.id = id;
        this.ma = ma;
        this.giaGiam = giaGiam;
        this.spvm = spvm;
        this.kmvm = kmvm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Embeddedct getEi() {
        return ei;
    }

    public void setEi(Embeddedct ei) {
        this.ei = ei;
    }

    
    

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public float getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(float giaGiam) {
        this.giaGiam = giaGiam;
    }

    public SanPhamViewModel getSpvm() {
        return spvm;
    }

    public void setSpvm(SanPhamViewModel spvm) {
        this.spvm = spvm;
    }

    public KhuyenMaiViewModel getKmvm() {
        return kmvm;
    }

    public void setKmvm(KhuyenMaiViewModel kmvm) {
        this.kmvm = kmvm;
    }
    public Object[] toRow(){
        return new Object[]{id,ma,kmvm.getTenKM(), spvm.getDm().getDongSP(), spvm.getTenSanPham(),giaGiam, kmvm.getNgayBD(),kmvm.getNgayKT(),kmvm.getMoTa()};
    }
    
}
