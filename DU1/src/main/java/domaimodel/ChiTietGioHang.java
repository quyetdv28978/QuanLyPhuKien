package domaimodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHITIETHOADON")
public class ChiTietGioHang implements Serializable {

    @Id
    private String id;
    private int soLuong, trangthai;
    private float  donGia,giaGiam;
    private Date ngayTao;
    @ManyToOne
    @JoinColumn(name = "idsp", insertable = false, updatable = false)
    private SanPham sp;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idgh", insertable = false, updatable = false)
    private GioHang gh;

    public ChiTietGioHang(String id, int soLuong, int trangthai, float donGia, float giaGiam, Date ngayTao, SanPham sp, GioHang gh) {
        this.id = id;
        this.soLuong = soLuong;
        this.trangthai = trangthai;
        this.donGia = donGia;
        this.giaGiam = giaGiam;
        this.ngayTao = ngayTao;
        this.sp = sp;
        this.gh = gh;
    }

    public ChiTietGioHang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(float giaGiam) {
        this.giaGiam = giaGiam;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public GioHang getGh() {
        return gh;
    }

    public void setGh(GioHang gh) {
        this.gh = gh;
    }

   

}
