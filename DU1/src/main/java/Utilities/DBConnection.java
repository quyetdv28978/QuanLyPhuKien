/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import DomainModel.ChatLieu;
import DomainModel.ChiTietGioHang;
import DomainModel.ChiTietHoaDon;
import DomainModel.ChiTietKhuyenMai;
import DomainModel.ChucVu;
import DomainModel.DanhMuc;
import DomainModel.GioHang;
import DomainModel.HoaDon;
import DomainModel.KhachHang;
import DomainModel.KhuyenMai;
import DomainModel.NhanVien;
import DomainModel.SanPham;
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author yugip
 */
public class DBConnection {

    private static final SessionFactory FACTORY;

    static {
        Configuration confi = new Configuration();
        Properties pro = new Properties();
        pro.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        pro.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        pro.put(Environment.URL, "jdbc:sqlserver://localhost;database=DU1_NHOM1;trustServerCertificate=true");
        pro.put(Environment.USER, "sa");
        pro.put(Environment.PASS, "1");
        pro.put(Environment.SHOW_SQL, true);
        confi.setProperties(pro);
        confi.addAnnotatedClass(ChucVu.class);
        confi.addAnnotatedClass(NhanVien.class);
        confi.addAnnotatedClass(DanhMuc.class);
        confi.addAnnotatedClass(ChatLieu.class);
        confi.addAnnotatedClass(SanPham.class);
        confi.addAnnotatedClass(KhachHang.class);
        confi.addAnnotatedClass(HoaDon.class);
        confi.addAnnotatedClass(ChiTietHoaDon.class);
        confi.addAnnotatedClass(GioHang.class);
        confi.addAnnotatedClass(ChiTietGioHang.class);
        confi.addAnnotatedClass(KhuyenMai.class);
        confi.addAnnotatedClass(ChiTietKhuyenMai.class);

        ServiceRegistry ser = new StandardServiceRegistryBuilder().applySettings(confi.getProperties()).build();
        FACTORY = confi.buildSessionFactory(ser);

    }

    public static SessionFactory getseFactory() {
        return FACTORY;
    }

    public static List selectQueRy(String sql) {
        Session ss = null;
        List listOB = null;
        try {
            ss = getseFactory().openSession();
            Query q = ss.createQuery(sql);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }

        return listOB;
//System.out.println(args[0]);
//        System.out.println("2: " + args[1]);
//        for (Object arg : args) {
//            System.out.println(arg);
//        }
//        return null;
    }

    public static List<Object[]> selectQueRyJoin(String sql) {
        Session ss = null;
        List listOB = null;
        try {
            ss = getseFactory().openSession();
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
            ss = getseFactory().openSession();
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
            ss = getseFactory().openSession();
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