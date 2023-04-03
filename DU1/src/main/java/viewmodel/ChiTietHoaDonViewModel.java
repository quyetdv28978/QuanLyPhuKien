package viewmodel;

import domaimodel.HoaDon;
import domaimodel.SanPham;
import domaimodel.embeddableCTHD;
import java.util.Date;
import java.util.Vector;

public class ChiTietHoaDonViewModel {

    private embeddableCTHD SPHD;
    private HoaDon hd;
    private SanPham sp;
    private int soluong;
    private float donGia, giagiam;
    private Date ngayTao;
    private int trangThai;

    public ChiTietHoaDonViewModel() {
    }

    public ChiTietHoaDonViewModel(HoaDon hd, SanPham sp, int soluong, Date ngayTao) {
        this.hd = hd;
        this.sp = sp;
        this.soluong = soluong;
        this.ngayTao = ngayTao;
    }

    public ChiTietHoaDonViewModel(embeddableCTHD SPHD, HoaDon hd, SanPham sp, int soluong, float donGia, float giagiam, Date ngayTao, int trangThai) {
        this.SPHD = SPHD;
        this.hd = hd;
        this.sp = sp;
        this.soluong = soluong;
        this.donGia = donGia;
        this.giagiam = giagiam;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }
    
    public ChiTietHoaDonViewModel(int soluong) {
        this.soluong = soluong;
    }

    public embeddableCTHD getSPHD() {
        return SPHD;
    }

    public void setSPHD(embeddableCTHD SPHD) {
        this.SPHD = SPHD;
    }

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getGiagiam() {
        return giagiam;
    }

    public void setGiagiam(float giagiam) {
        this.giagiam = giagiam;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] torow() {
        return new Object[]{hd.getId(), (donGia * soluong) - giagiam};
    }

}
