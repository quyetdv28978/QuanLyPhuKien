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
import org.apache.poi.hssf.record.chart.DatRecord;
import utility.DBConnection;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "BAOHANH")
public class BaoHanh implements Serializable {

    @Id
    private String id;
    private String ma;
    private Date ngayBH;
    private String moTa;
    private int tinhTrang;
    private String suaChua;
    private int soLuongBaoHanh;
    private Date ngayTra;

    @ManyToOne
    @JoinColumn(name = "idCTHD", referencedColumnName = "id")
    private chitiethoadonNoEmbe cthd;

    public BaoHanh(String id, String ma, Date ngayBH, String moTa, int tinhTrang, String suaChua, int soLuongBaoHanh, Date ngayTra, chitiethoadonNoEmbe cthd) {
        this.id = id;
        this.ma = ma;
        this.ngayBH = ngayBH;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.suaChua = suaChua;
        this.soLuongBaoHanh = soLuongBaoHanh;
        this.ngayTra = ngayTra;
        this.cthd = cthd;
    }

    public BaoHanh(String id, String ma, Date ngayBH, String moTa, int tinhTrang, String suaChua, int soLuongBaoHanh, Date ngayTra) {
        this.id = id;
        this.ma = ma;
        this.ngayBH = ngayBH;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.suaChua = suaChua;
        this.soLuongBaoHanh = soLuongBaoHanh;
        this.ngayTra = ngayTra;
    }

  
    public BaoHanh() {
    }

    public chitiethoadonNoEmbe getCthd() {
        return cthd;
    }

    public void setCthd(chitiethoadonNoEmbe cthd) {
        this.cthd = cthd;
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

    public Date getNgayBH() {
        return ngayBH;
    }

    public void setNgayBH(Date ngayBH) {
        this.ngayBH = ngayBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getSuaChua() {
        return suaChua;
    }

    public void setSuaChua(String suaChua) {
        this.suaChua = suaChua;
    }

    public int getSoLuongBaoHanh() {
        return soLuongBaoHanh;
    }

    public void setSoLuongBaoHanh(int soLuongBaoHanh) {
        this.soLuongBaoHanh = soLuongBaoHanh;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

//    @Override
//    public String toString() {
//        return "ChiTietBaoHanh{" + "id=" + id + ", ma=" + ma + ", ngayBH=" + ngayBH + ", moTa=" + moTa + ", tinhTrang=" + tinhTrang + ", suaChua=" + suaChua + ", soLuongBaoHanh=" + soLuongBaoHanh + ", soLanBH=" + soLanBH + ", ngayTra=" + ngayTra + ", bh=" + bh + '}';
//    }
    public Object[] toRowCTBH() {
        return new Object[]{id, ma, cthd.getHd().getKh().getTen(), cthd.getHd().getKh().getSdt(), cthd.getSp().getMa(), cthd.getSp().getTenSanPham(),
            suaChua, new Date(), ngayTra, soLuongBaoHanh, tinhTrang == 0 ? "Đang Xử Lí" : "Đã Hoàn Thành", moTa};
    }

    public static void main(String[] args) {
        System.out.println(DBConnection.selectQueRy("from chitiethoadonNoEmbe").size());
    }
}
