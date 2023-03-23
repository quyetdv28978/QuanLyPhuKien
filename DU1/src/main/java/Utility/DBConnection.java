/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// <<<<<<< HEAD
package utility;

//import DomainModel.ChucVu;
//import DomainModel.NhanVien;
//import ViewModel.NhanVienView;
// import domaiModel.ChatLieu;
// import domaiModel.DanhMuc;
// import domaiModel.SanPham;
// =======
// package Utility;

// import DomainModel.ChucVu;
// import DomainModel.NhanVien;
// >>>>>>> Kanna
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
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
// <<<<<<< HEAD
        pro.put(Environment.USER, "sa");
        pro.put(Environment.PASS, "2132411");
        pro.put(Environment.SHOW_SQL, true);
        confi.setProperties(pro);
        confi.addAnnotatedClass(ChatLieu.class);
        confi.addAnnotatedClass(DanhMuc.class);
        confi.addAnnotatedClass(SanPham.class);
// =======
//         pro.put(Environment.USER, "Kanna");
//         pro.put(Environment.PASS, "S2gaungok");
//         pro.put(Environment.SHOW_SQL, true);
//         confi.setProperties(pro);
//         confi.addAnnotatedClass(ChucVu.class);
//         confi.addAnnotatedClass(NhanVien.class);
// >>>>>>> Kanna

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
