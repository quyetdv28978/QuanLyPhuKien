/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChucVu;
import domaimodel.NhanVien;
import java.util.ArrayList;
import utility.DBConnection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
//import respon.IManageNhanVienRespository;

/**
 *
 * @author suppe
 */
public class NhanVienRespository implements IManageRespository {

    public List<Object[]> getAllJoin(String dieuKien) {
        if (DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu") != null) {
            return DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu");
        }
        return null;
    }

    public List<ChucVu> getAllChucVu() {
        if (DBConnection.selectQueRy("from ChucVu") != null) {
            return DBConnection.selectQueRy("from ChucVu");
        }
        return null;
    }

    public List<NhanVien> timKiemTheoMa(String ma) {
        List<NhanVien> listTimKiemTheoMa = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT tenNhanvien FROM NHANVIEN WHERE maNhanVien =: key1 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ma);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] object : ressulistArr) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNhanVien((object[0]).toString());
                listTimKiemTheoMa.add(nhanVien);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTimKiemTheoMa;
    }
    
        public List<NhanVien> timKiemTheoTen(String ma) {
        List<NhanVien> listTimKiemTheoMa = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT tenNhanvien FROM NHANVIEN WHERE tenNhanVien =: key1";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ma);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] object : ressulistArr) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNhanVien((object[0]).toString());
                listTimKiemTheoMa.add(nhanVien);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTimKiemTheoMa;
    }

    @Override
    public List<Object[]> getJoin(String dieuKien) {
        if (DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu") != null) {
            return DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu");
        } else {
            return null;
        }
    }

    @Override
    public List<NhanVien> getListNhanVienFromDb() {
        return DBConnection.selectQueRy("FROM NhanVien");
    }

    @Override
    public int them(NhanVien maNhanVien) {
        return DBConnection.executeQuery(maNhanVien, null);
    }

    @Override
    public int capNhat(NhanVien maNhanVien) {
        return DBConnection.executeQuery(maNhanVien, "capNhat");
    }

    @Override
    public int xoa(String maNhanVien) {
        return DBConnection.delete(maNhanVien, NhanVien.class);
    }

    public List<Object[]> getAllJoinLoad() {
        return DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu");
    }

    @Override
    public List<ChucVu> getListChucVuFromDb() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
