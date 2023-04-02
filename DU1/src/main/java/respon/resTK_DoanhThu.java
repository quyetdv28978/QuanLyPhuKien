package respon;

import domaimodel.ChiTietHoaDon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utility.DBConnection;

/**
 *
 * @author DELL
 */
public class resTK_DoanhThu implements Iresponsitories<ChiTietHoaDon> {

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietHoaDon> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<ChiTietHoaDon> getAllloadform() {
        if (DBConnection.selectQueRy("From ChiTietHoaDon ") != null) {
            return DBConnection.selectQueRy("From ChiTietHoaDon");
        }
        return null;
    }

    @Override
    public int add(ChiTietHoaDon q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(ChiTietHoaDon q) {
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
    public ChiTietHoaDon timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//----------------------TK Doanh Thu-------------------//
//------------theo ngay---------------------*//
    public List<ChiTietHoaDon> Selectby_doanhthu_theoNgayTC(Date ngayTT) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date) = :key1 AND c.hd.tinhTrang=0 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngayTT);
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

    public List<ChiTietHoaDon> Selectby_doanhthu_theongayBH(Date ngayTT) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTao as date) =:key1 AND c.hd.tinhTrang=2 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngayTT);
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

    public List<ChiTietHoaDon> Selectby_doanhthu_ThangBH(Integer thang, Integer nam) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE MONTH(c.hd.ngayTao)=:key1 AND YEAR(c.hd.ngayTao)=:key2 AND c.hd.tinhTrang=2 ";
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

    //-----------khoangngay---------------*//
    public List<ChiTietHoaDon> SelectbyKhoangNgayTC(Date ngaybd, Date ngayKt) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date) BETWEEN :key1 AND :key2 AND c.hd.tinhTrang=0 ";
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
  
    public List<ChiTietHoaDon> SelectbyKhoangNgayBH(Date ngaybd, Date ngayKt) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTao as date) BETWEEN :key1 AND :key2 AND c.hd.tinhTrang=2";
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
    
    //--------------hientai---------------*/    
    public List<ChiTietHoaDon> SelectbyhientaiTC() {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT count(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date)=CAST(GETDATE() as date) AND c.hd.tinhTrang=0";
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

    public List<ChiTietHoaDon> SelectbyhientaiBH() {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTao as date)=CAST(GETDATE() as date) AND c.hd.tinhTrang=2";
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
    
    /*-------------------------------------Phần Sản Phẩm --------------------------------*/
 /*-------tổng hợp theo năm-------*/
    public List<ChiTietHoaDon> selectTonghop(int nam) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT  c.sp.ma, c.sp.tenSanPham, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam)\n"
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
            String sql = "SELECT  c.sp.ma, c.sp.tenSanPham, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam)\n"
                    + "FROM domaimodel.ChiTietHoaDon c Where CAST(c.hd.ngayTT AS java.sql.Date)=:key1 and c.hd.tinhTrang=0\n"
                    + "GROUP BY c.sp.ma, c.sp.tenSanPham\n"
                    + "ORDER BY COUNT(c.sp.id) DESC";
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
            String sql = "SELECT  c.sp.ma, c.sp.tenSanPham, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam)\n"
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

}
