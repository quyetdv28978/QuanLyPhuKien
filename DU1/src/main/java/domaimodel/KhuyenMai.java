/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai implements Serializable{
    @Id
    private String id;
    private String  ma,tenKM;
    private Float giaGiam;
    private Date ngayBD, ngayKT,ngayTao;
    private int trangThai;
//    private String moTa;

    public KhuyenMai() {
        
    }

    public KhuyenMai(String id, String tenKM, Date ngayKT) {
        this.id = id;
        this.tenKM = tenKM;
        this.ngayKT = ngayKT;
    }

    public KhuyenMai(String tenKM) {
        this.tenKM = tenKM;
    }

    

    public KhuyenMai(String id, String ma, String tenKM, Float giaGiam, Date ngayBD, Date ngayKT, String moTa) {
        this.id = id;
        this.ma = ma;
        this.tenKM = tenKM;
        this.giaGiam = giaGiam;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
//        this.moTa = moTa;
    }
    


    public KhuyenMai(String id, String ma, String tenKM, Float giaGiam, Date ngayBD, Date ngayKT, Date ngayTao, String moTa) {
        this.id = id;
        this.ma = ma;
        this.tenKM = tenKM;
        this.giaGiam = giaGiam;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.ngayTao = ngayTao;
//        this.moTa = moTa;
    }

//    public String getMoTa() {
//        return moTa;
//    }
//
//    public void setMoTa(String moTa) {
//        this.moTa = moTa;
//    }



    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public Float getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(Float giaGiam) {
        this.giaGiam = giaGiam;
    }
    

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
   
    public int getTT(){
         Calendar cal = Calendar.getInstance();
    Date d = cal.getTime();
        if(ngayKT.before(d)){
            return 1;
        }else{
            return 0;
        }
    }
    public Object[] toRow(){
        return new Object[]{id,ma, tenKM,giaGiam,ngayBD,ngayKT};
    }

    public KhuyenMai(String tenKM, Date ngayKT) {
        this.tenKM = tenKM;
        this.ngayKT = ngayKT;
    }

    @Override
    public String toString() {
        return tenKM ;
    }
//    public String to(){
//        return getTT() == 0 ?"còn hạn" :"hết hạn";
//    }
    
}