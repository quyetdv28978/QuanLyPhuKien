
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

//import domaimodel.ChatLieu;
import domaimodel.ChiTietKhuyenMai;
//import domaimodel.DanhMuc;
import domaimodel.KhachHang;
import domaimodel.KhuyenMai;
import domaimodel.SanPham;

import domaimodel.ChatLieu;
import domaimodel.ChiTietKhuyenMai;
import domaimodel.DanhMuc;
import domaimodel.KhachHang;
import domaimodel.KhuyenMai;
import domaimodel.SanPham;

import java.util.List;



import domaimodel.ChucVu;
import domaimodel.NhanVien;
import domaimodel.ChatLieu;
import domaimodel.ChiTietGioHang;
import domaimodel.ChiTietHoaDon;
//import domaimodel.ChiTietGioHang;
//import domaimodel.ChiTietHoaDon;
import domaimodel.DanhMuc;
import domaimodel.GioHang;
import domaimodel.HoaDon;
//import domaimodel.GioHang;
//import domaimodel.HoaDon;
import domaimodel.SanPham;

import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class DBConnection {

    private static final SessionFactory FACTORY;

    static {
        Configuration confi = new Configuration();
        Properties pro = new Properties();
        pro.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        pro.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        pro.put(Environment.URL, "jdbc:sqlserver://localhost;database=DU1_NHOM1_1;trustServerCertificate=true");
        pro.put(Environment.USER, "sa");
        pro.put(Environment.PASS, "123456");
        pro.put(Environment.SHOW_SQL, true);
        confi.setProperties(pro);

        confi.addAnnotatedClass(ChatLieu.class);
        confi.addAnnotatedClass(ChucVu.class);
        confi.addAnnotatedClass(DanhMuc.class);
        confi.addAnnotatedClass(NhanVien.class);
        confi.addAnnotatedClass(SanPham.class);
        confi.addAnnotatedClass(ChiTietHoaDon.class);
        confi.addAnnotatedClass(ChiTietGioHang.class);
        confi.addAnnotatedClass(GioHang.class);
        confi.addAnnotatedClass(HoaDon.class);
        confi.addAnnotatedClass(KhuyenMai.class);
        confi.addAnnotatedClass(KhachHang.class);
        confi.addAnnotatedClass(ChiTietKhuyenMai.class);


        
        confi.addAnnotatedClass(KhachHang.class);
        confi.addAnnotatedClass(KhuyenMai.class);

        ServiceRegistry ser = new StandardServiceRegistryBuilder().applySettings(confi.getProperties()).build();
        FACTORY = confi.buildSessionFactory(ser);

    }

    public static SessionFactory getsetFactory() {
        return FACTORY;
    }

    public static List selectQueRy(String sql) {
        Session ss = null;
        List listOB = null;
        try {

            ss = getsetFactory().openSession();
            listOB = ss.createQuery(sql).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }

        return listOB;
    }

    public static List<Object[]> selectQueRyJoin(String sql) {
        Session ss = null;
        List listOB = null;
        try {

            ss = getsetFactory().openSession();

            listOB = ss.createQuery(sql).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }

        return listOB;
    }

    public static int executeQuery(Object o, String id) {
        Session ss = null;
        try {

            ss = getsetFactory().openSession();

            if (id != null) {
                ss.getTransaction().begin();
                ss.update(o);
                ss.getTransaction().commit();
                return 0;
            }
            ss.getTransaction().begin();
            ss.save(o);
            ss.getTransaction().commit();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return 1;
    }

    public static int delete(String id, Class a) {
        Session ss = null;
        try {
            ss = getsetFactory().openSession();

            Object o = ss.get(a, id);
            ss.getTransaction().begin();
            ss.delete(o);
            ss.getTransaction().commit();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }

        return 1;
    }
}
