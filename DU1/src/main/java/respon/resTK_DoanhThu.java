package respon;

import domaimodel.ChiTietHoaDon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utility.DBConnection;

/**
 *
 * @author DELL
 */
public class resTK_DoanhThu {

//----------------------TK Doanh Thu-------------------//
//------------theo ngay---------------------*//
    public List<ChiTietHoaDon> Selectby_doanhthu_theoNgayTC(Date ngay) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date)=:key1 AND c.hd.tinhTrang=0 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngay);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD(((Number) ob[0]).intValue());
                c.setTONGTIEN(ob[1] != null ? ((Number) ob[1]).doubleValue() : 0.0);
                c.setSKH(((Number) ob[2]).doubleValue());
                pas.add(c);
            }
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public Long Selectbydoanhthu_ngayBH(Date ngay) {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(DISTINCT h.id) FROM domaimodel.HoaDon h WHERE CAST(h.ngayTao AS date) = :key1 AND h.tinhTrang = 2", Long.class);
            query.setParameter("key1", ngay,TemporalType.DATE);
            return query.getSingleResult(); // Trả về kiểu Long
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }


//------------------theo tháng------------------*//
    public List<ChiTietHoaDon> Selectby_doanhthu_ThangTC(Integer thang, Integer nam) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE MONTH(c.hd.ngayTT)=:key1 AND YEAR(c.hd.ngayTT)=:key2 AND c.hd.tinhTrang=0 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", thang);
            query.setParameter("key2", nam);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD(((Number) ob[0]).intValue());
                c.setTONGTIEN(ob[1] != null ? ((Number) ob[1]).doubleValue() : 0.0);
                c.setSKH(ob[2] != null ? ((Number) ob[2]).doubleValue() : 0);
                pas.add(c);
            }
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;

    }

    public Long Selectbydoanhthu_thangBH(int thang, int nam) {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(DISTINCT h.id) FROM HoaDon h WHERE MONTH(h.ngayTao)=:fromDate AND YEAR(h.ngayTao)=:toDate AND h.tinhTrang = 2", Long.class);
            query.setParameter("fromDate", thang);
            query.setParameter("toDate", nam);
            return query.getSingleResult(); // Trả về kiểu Long
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    //-----------khoangngay---------------*//
    public List<ChiTietHoaDon> SelectbyKhoangNgayTC(Date ngaybd, Date ngayKt) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date) BETWEEN :key1 AND :key2 AND c.hd.tinhTrang=0 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngaybd);
            query.setParameter("key2", ngayKt);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD(((Number) ob[0]).intValue());
                c.setTONGTIEN(ob[1] != null ? ((Number) ob[1]).doubleValue() : 0.0);
                c.setSKH(ob[2] != null ? ((Number) ob[2]).doubleValue() : 0);
                pas.add(c);
            }
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public Long SelectbyKhoangNgayBH(Date fromDate, Date toDate) {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(DISTINCT h.id) FROM HoaDon h WHERE h.ngayTao BETWEEN :fromDate AND :toDate AND h.tinhTrang = 2", Long.class);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            return query.getSingleResult(); // Trả về kiểu Long
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    //--------------hientai---------------*/    
    public List<ChiTietHoaDon> SelectbyhientaiTC() {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date)=CURRENT_DATE() AND c.hd.tinhTrang=0";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD(((Number) ob[0]).intValue());
                c.setTONGTIEN(ob[1] != null ? ((Number) ob[1]).doubleValue() : 0.0);
                c.setSKH(ob[2] != null ? ((Number) ob[2]).doubleValue() : 0);
                pas.add(c);
            }
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public Long SelectbyhientaiBH() {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(DISTINCT h.id) FROM HoaDon h WHERE CAST(h.ngayTT as date)=CURRENT_DATE() AND h.tinhTrang = 2", Long.class);
            return query.getSingleResult(); // Trả về kiểu Long
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }


    /*-------------------------------------Phần Sản Phẩm --------------------------------*/
 /*-------tổng hợp theo năm-------*/
    public List<ChiTietHoaDon> selectTonghop(int nam) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT  c.sp.ma, c.sp.tenSanPham, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam)\n"
                    + "FROM domaimodel.ChiTietHoaDon c Where YEAR(c.hd.ngayTT)=:key1 and c.hd.tinhTrang=0\n"
                    + "GROUP BY c.sp.ma, c.sp.tenSanPham\n"
                    + "ORDER BY COUNT(c.sp.id) DESC";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", nam);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon r = new ChiTietHoaDon();
                r.setMaSP(((String) ob[0]).toString());
                r.setTenSP(((String) ob[1]).toString());
                r.setSOHD(((Number) ob[2]).intValue());
                r.setSluong(((Number) ob[3]).intValue());
                r.setTONGTIEN(ob[4] != null ? ((Number) ob[4]).doubleValue() : 0.0);
                pas.add(r);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    /*tìm Theo Ngày------*/
    
    public List<ChiTietHoaDon> selectSPNgay(Date ngay) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT c.sp.ma, c.sp.tenSanPham, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date)=:key1 AND c.hd.tinhTrang = 0 GROUP BY c.sp.ma, c.sp.tenSanPham ORDER BY COUNT(c.sp.id) DESC";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngay);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon r = new ChiTietHoaDon();
                r.setMaSP(((String) ob[0]).toString());
                r.setTenSP(((String) ob[1]).toString());
                r.setSOHD(((Number) ob[2]).intValue());
                r.setSluong(((Number) ob[3]).intValue());
                r.setTONGTIEN(ob[4] != null ? ((Number) ob[4]).doubleValue() : 0.0);
                pas.add(r);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    /*Tìm Theo Tháng-----*/
    public List<ChiTietHoaDon> selectSPThang(int thang, int nam) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT c.sp.ma, c.sp.tenSanPham, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam)\n"
                    + "FROM domaimodel.ChiTietHoaDon c Where MONTH(c.hd.ngayTT)=:key1 AND YEAR(c.hd.ngayTT)=:key2 and c.hd.tinhTrang=0\n"
                    + "GROUP BY c.sp.ma, c.sp.tenSanPham\n"
                    + "ORDER BY COUNT(c.sp.id) DESC";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", thang);
            query.setParameter("key2", nam);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon r = new ChiTietHoaDon();
                r.setMaSP(((String) ob[0]).toString());
                r.setTenSP(((String) ob[1]).toString());
                r.setSOHD(((Number) ob[2]).intValue());
                r.setSluong(((Number) ob[3]).intValue());
                r.setTONGTIEN(ob[4] != null ? ((Number) ob[4]).doubleValue() : 0.0);
                pas.add(r);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

//        public List<ChiTietHoaDon> SelectbyNgay(Date ngay) {
//        List<ChiTietHoaDon> pas;
//        Date n = ngay;
//        try (Session session = DBConnection.getsetFactory().openSession()) {
//            TypedQuery<ChiTietHoaDon> query = session.createQuery("From ChiTietHoaDon WHERE n=:key1 and TinhTrang=0");
//            query.setParameter("key1", n);
//            System.out.println(query);
//            pas = query.getResultList();
//            session.close();
//
//        }
//        return pas;
//    }
}
