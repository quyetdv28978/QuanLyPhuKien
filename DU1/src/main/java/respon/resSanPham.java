/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChiTietHoaDon;
import domaimodel.DanhMuc;
import domaimodel.HoaDon;
import domaimodel.NhanVien;
import domaimodel.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utility.DBConnection;

/**
 *
 * @author ADMIN
 */
public class resSanPham implements Iresponsitories<SanPham> {

//    @Override
//    public List<Object[]> getALLJoin(String dk) {
//        return DBConnection.selectQueRyJoin("from sanpham s join s.cl join s.dm");
//    }
    public List<Object[]> getALLJoinLoad() {
        return DBConnection.selectQueRyJoin("from sanpham s join s.dm");
    }

    //Huong
    public List<SanPham> getAll123(String dk) {
        if (DBConnection.selectQueRy("from SanPham") != null) {
            return DBConnection.selectQueRy("from SanPham");
        }
        return null;
    }

    @Override
    public List<SanPham> getAll(String dk) {
        if (DBConnection.selectQueRy("from SanPham s " + dk) != null) {
            return DBConnection.selectQueRy("from SanPham s " + dk);
        } else
            return null;
    }

    public List<SanPham> getAllHH(String dk) {
        return DBConnection.selectQueRy("from SanPham where trangThai=1 ");
    }

    public List<SanPham> getAll2(String dk) {
        return DBConnection.selectQueRy("from SanPham s " + dk);
    }

    public DanhMuc timDM(String ten) {
        Query q = DBConnection.getsetFactory().openSession().createQuery("from DanhMuc d where d.dongSP = :ten");
        q.setParameter("ten", ten);

        if (q.list().isEmpty()) {

            return null;
        }
        return (DanhMuc) q.getSingleResult();
//        return (DanhMuc)DBConnection.getsetFactory().openSession().createQuery("from DanhMuc d where d.dongSP ='"+ten+"'").getSingleResult();
    }

    public SanPham timSP(String ten) {
        Query q = DBConnection.getsetFactory().openSession().createQuery("from SanPham sp where sp.tenSanPham = :ten");
        q.setParameter("ten", ten);

        if (q.list().isEmpty()) {

            return null;
        }
        return (SanPham) q.getSingleResult();
//        return (DanhMuc)DBConnection.getsetFactory().openSession().createQuery("from DanhMuc d where d.dongSP ='"+ten+"'").getSingleResult();
    }

    @Override
    public int add(SanPham t) {
        return DBConnection.executeQuery(t, null);
    }

    @Override
    public int update(SanPham t) {
        return DBConnection.executeQuery(t, "update");
    }

    @Override
    public int delete(String t) {
        return DBConnection.delete(t, SanPham.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<SanPham> selectByGiaT(Float gia1, Float gia2) {
        List<SanPham> sp;
        Float gia1Select = gia1;
        Float gia2Select = gia2;
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("from SanPham where giaBan between :t1 and :t2");
            query.setParameter("t1", gia1Select);
            query.setParameter("t2", gia2Select);
            sp = query.getResultList();
            session.close();
        }
        return sp;
    }

    public List<Object[]> getALLJoin(String dk) {
        if (DBConnection.selectQueRyJoin("from SanPham s join s.dm where s.trangThai = 0") != null) {
            return DBConnection.selectQueRyJoin("from SanPham s join s.dm where s.trangThai = 0");
        }
        return null;
    }

    public List<DanhMuc> getALLDM() {
        if (DBConnection.selectQueRy("from DanhMuc") != null) {
            return DBConnection.selectQueRy("from DanhMuc");
        }
        return null;
    }

    public List<SanPham> selectByTen(String ten) {
        List<SanPham> sp = new ArrayList<>();
        String tenSelect = "%" + ten + "%";
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("from SanPham where tenSanPham like :t and trangThai = 0");
            query.setParameter("t", tenSelect);
            sp = query.getResultList();
        } catch (Exception e) {
        }
        return sp;
    }

    public List<SanPham> selectByDSP(String ten) {
        List<SanPham> sp = new ArrayList<>();
        String tenSelect = "%" + ten + "%";
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("from SanPham sp where sp.dm.dongSP like :t and sp.trangThai = 0");
            query.setParameter("t", tenSelect);
            sp = query.getResultList();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return sp;
    }

    public List<SanPham> selectByTT(int tt) {
        List<SanPham> sp = new ArrayList<>();
        int ttSelect = tt;
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("from SanPham where trangThai = :t");
            query.setParameter("t", ttSelect);
            sp = query.getResultList();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return sp;
    }

    //HUONG
    public List<SanPham> SelectbyGia(Float gia1, Float gia2) {
        List<SanPham> pas;
        Float gia = gia1;
        Float b = gia2;
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("From SanPham  WHERE giaBan between :key and :key2");
            query.setParameter("key", gia);
            query.setParameter("key2", b);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }

        return pas;
    }

    public List<SanPham> SelectbyTen(String tenSanPham) {
        List<SanPham> pas;

        String a = "%" + tenSanPham + "%";
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("From SanPham  WHERE tenSanPham like :key");
            query.setParameter("key", a);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }

        return pas;
    }

    public List<ChiTietHoaDon> getALLCTHD() {
        if (DBConnection.selectQueRy("from ChiTietHoaDon") != null) {
            return DBConnection.selectQueRy("from ChiTietHoaDon");

        }
        return null;
    }

    public List<HoaDon> getALLHD() {
        if (DBConnection.selectQueRy("from HoaDon") != null) {
            return DBConnection.selectQueRy("from HoaDon");

        }
        return null;
    }

    public List<NhanVien> getALLNV() {
        if (DBConnection.selectQueRy("from NhanVien") != null) {
            return DBConnection.selectQueRy("from NhanVien");

        }
        return null;
    }

    public List<Object[]> getAlljointon(String dk) {
        if (DBConnection.selectQueRyJoin("from SanPham s join s.ChiTietHoaDon c join  c.Join c.HoaDon  h Join h.NhanVien") != null) {
            return DBConnection.selectQueRyJoin("from SanPham s join s.ChiTietHoaDon c join  c.Join c.HoaDon  h Join h.NhanVien");
        }
        return null;
    }

    //giá bé hơn bằng
    public List<SanPham> getAllSPHDCungGiaNoEm(String dk, int tt) {
        return DBConnection.selectQueRy("from SanPham\n"
                + "where soLuong > 0 and giaBan >= '" + dk + "'");
    }

}
