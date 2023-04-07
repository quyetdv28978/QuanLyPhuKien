/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai implements Serializable{
    @Id
    private String id;
    private String  ma,tenKM;
    private Float giaGiam;
    private Date ngayBD, ngayKT,ngayTao;
    private String trangThai;
    private String moTa;


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
        this.moTa = moTa;
    }

    public KhuyenMai(String id, String ma, String tenKM, Float giaGiam, Date ngayBD, Date ngayKT, String trangThai, String moTa) {
        this.id = id;
        this.ma = ma;
        this.tenKM = tenKM;
        this.giaGiam = giaGiam;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }
    
    


    public KhuyenMai(String id, String ma, String tenKM, Float giaGiam, Date ngayBD, Date ngayKT, Date ngayTao, String moTa) {
        this.id = id;
        this.ma = ma;
        this.tenKM = tenKM;
        this.giaGiam = giaGiam;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.ngayTao = ngayTao;
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }



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

    public String getTrangThai() {
        return trangThai;
    }

    public Float getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(Float giaGiam) {
        this.giaGiam = giaGiam;
    }
    

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
   
    public String getTT() {
         Calendar cal = Calendar.getInstance();
         Date date = cal.getTime();
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date now = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").parse( new SimpleDateFormat("dd/MM/yyyy").format(new Date(date.getTime()))+" 00:00:00 AM");
//            Date bd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").parse( new SimpleDateFormat("dd/MM/yyyy").format(+" 00:00:00 AM");



int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1; // vì tháng trong Calendar tính từ 0 đến 11 nên phải cộng thêm 1
            int year = cal.get(Calendar.YEAR);
            String dateString = formatter.format(cal.getTime());

            //ngày bắt đầu 
            Date dateBD = ngayBD;
            String bd = formatter.format(dateBD);

            //ngyaf kết thúc
            Date dateKT = ngayKT;
            String kt = formatter.format(dateKT);
            System.out.println("kt chuqa" + kt);
            System.out.println("bd chuqa" + bd);


        if(ngayKT.compareTo(now) < 0){
            System.out.println("ngaykt");
            return "Hết Hạn";
        }else if(dateString.equalsIgnoreCase(bd)){
//        }else if (ngayBD.compareTo(now) > 0 && ngayKT.compareTo(now)<0){
//            System.out.println("ngay chua");
//            System.out.println("nh3" + ngayBD.toString());
//            System.out.println("nh2" + ngayKT.toString());
            return "Còn Hạn";
        }else{
            System.out.println("hhh" + ngayBD.getTime());
            System.out.println("nh3" + ngayBD.toString());
            System.out.println("nh2" + ngayKT.toString());
            System.out.println("ngaybđ");
            

            return "Chưa Hoạt Động";
        }
        }catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public Object[] toRow(){
        return new Object[]{id,ma, tenKM,giaGiam,ngayBD,ngayKT,getTT(),moTa};
    }
    

    public KhuyenMai(String tenKM, Date ngayKT) {
        this.tenKM = tenKM;
        this.ngayKT = ngayKT;
    }

    @Override
    public String toString() {
        return tenKM ;
    }
//    public static void main(String[] args) {
//        Date now;
//        try {
//            now = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").parse( new SimpleDateFormat("dd/MM/yyyy").format(new Date())+" 00:00:00 AM");
//            System.out.println("now " + now);
//        } catch (ParseException ex) {
//            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//    }
//    public static void main(String[] args) {
////        System.out.println(new Date().before(new SimpleDateFormat("MM/dd/yyyy").parse("4/30/2023")));
////new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a").parse("2023/03/27 00:00:00 AM")
//        
//    }
//    public String to(){
//        return getTT() == 0 ?"còn hạn" :"hết hạn";
//    }
 
//    public static void main(String[] args) {
//        System.out.println("ngay: " + );
    
}
