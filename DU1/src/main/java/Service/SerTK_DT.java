package service;

import domaimodel.ChiTietHoaDon;
import java.util.Date;
import java.util.List;
import respon.ResTK_DT;

public class SerTK_DT {

    private ResTK_DT rtkdt = new ResTK_DT();

    /*-------DoanhThu------------*/
 /*------theongay-----*/
    public List<ChiTietHoaDon> select_doanhthu_theongayTC(Date ngay) {
        return rtkdt.Selectby_doanhthu_theoNgayTC(ngay);
    }

    public Long select_doanhthu_theongayBH(Date ngay) {
        return rtkdt.Selectbydoanhthu_ngayBH(ngay);
    }

    /*----theothang-------*/
    public List<ChiTietHoaDon> select_doanhthu_theothangTC(int thang, int nam) {
        return rtkdt.Selectby_doanhthu_ThangTC(thang, nam);
    }

    public Long select_doanhthu_theothangBH(int thang, int nam) {
        return rtkdt.Selectbydoanhthu_thangBH(thang, nam);
    }

    /*-----hientai*/
    public List<ChiTietHoaDon> select_hientaiTC() {
        return rtkdt.SelectbyhientaiTC();
    }

    public Long select_hientaiBH() {
        return rtkdt.SelectbyhientaiBH();
    }

    /*-----theo khoang----*/
    public List<ChiTietHoaDon> select_khoangTC(Date ngaybd, Date ngaykt) {
        return rtkdt.SelectbyKhoangNgayTC(ngaybd, ngaykt);
    }

    public Long select_khoangBH(Date ngatbd, Date ngaykt) {
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
}
