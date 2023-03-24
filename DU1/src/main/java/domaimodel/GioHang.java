package domaimodel;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "giohang")
public class GioHang {
    @Id
    private String id;
    private String ma;
    private Date ngayTao;
    private int tinhtrang;
    @OneToOne
    @JoinColumn(name = "idkh")
    private KhachHang nv;

    public GioHang() {
    }

    public GioHang(String id, String ma, Date ngayTao, int tinhtrang, KhachHang nv) {
        this.id = id;
        this.ma = ma;
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

    public KhachHang getNv() {
        return nv;
    }

    public void setNv(KhachHang nv) {
        this.nv = nv;
    }
    
     
    
}