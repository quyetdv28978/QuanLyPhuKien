package service;

import domaimodel.ChiTietHoaDon;
import domaimodel.HoaDon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import respon.Iresponsitories;
import respon.resTK_DoanhThu;
import viewmodel.ChiTietHoaDonViewModel;
import viewmodel.HoaDonViewModel;

public class SerTKDoanhThu implements Iresponsitories<ChiTietHoaDonViewModel> {

    public final resTK_DoanhThu rtkdt = new resTK_DoanhThu();

    /*-------DoanhThu------------*/
 /*------theongay-----*/
    public List<ChiTietHoaDon> select_doanhthu_theongayTC(Date ngay) {
        return rtkdt.Selectby_doanhthu_theoNgayTC(ngay);
    }

    public List<ChiTietHoaDon> select_doanhthu_theongayBH(Date ngay) {
        return rtkdt.Selectby_doanhthu_theongayBH(ngay);
    }

    /*----theothang-------*/
    public List<ChiTietHoaDon> select_doanhthu_theothangTC(int thang, int nam) {
        return rtkdt.Selectby_doanhthu_ThangTC(thang, nam);
    }

    public List<ChiTietHoaDon> select_doanhthu_theothangBH(int thang, int nam) {
        return rtkdt.Selectby_doanhthu_ThangBH(thang, nam);
    }

    /*-----hientai*/
    public List<ChiTietHoaDon> select_hientaiTC() {
        return rtkdt.SelectbyhientaiTC();
    }

    public List<ChiTietHoaDon> select_hientaiBH() {
        return rtkdt.SelectbyhientaiBH();
    }

    /*-----theo khoang----*/
    public List<ChiTietHoaDon> select_khoangTC(Date ngaybd,Date ngaykt) {
        return rtkdt.SelectbyKhoangNgayTC(ngaybd, ngaykt);
    }

    public List<ChiTietHoaDon> select_khoangBH(Date ngatbd,Date ngaykt) {
        return rtkdt.SelectbyKhoangNgayBH(ngatbd, ngaykt);
    }

    /*-------SANPHAM-------------*/
 /*----tim theo thang----*/
    public List<ChiTietHoaDon> SelectbySPThang(Integer thang, Integer nam) {
        return rtkdt.selectSPThang(thang, nam);
    }

    /*----tim theo ngay*/
    public List<ChiTietHoaDon> SelectbySPNgay(Date ngay) {
        return rtkdt.selectSPNgay(ngay);
    }

    /*--------tim TONGHOP---------*/
    public List<ChiTietHoaDon> SelectTOP(int nam) {
        return rtkdt.selectTonghop(nam);
    }

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietHoaDonViewModel> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int add(ChiTietHoaDonViewModel q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(ChiTietHoaDonViewModel q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietHoaDonViewModel timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    public List<ChiTietHoaDon> Selectbyhientai(){
////    return rtkdt.Selectbyhientai();
//    }
    public List<ChiTietHoaDonViewModel> getAllcthd() {
        if (this.rtkdt.getAllloadform() != null) {
            List<ChiTietHoaDonViewModel> listct = new ArrayList<>();
            List<ChiTietHoaDon> a = rtkdt.getAllloadform();
            for (ChiTietHoaDon c : a) {
                listct.add(new ChiTietHoaDonViewModel(c.getSPHD(), c.getHd(), c.getSp(), c.getSoluong(), c.getDonGia(), c.getGiagiam(), c.getNgayTao(), c.getTrangThai()));
            }
            return listct;
        }
        return null;
    }
}
