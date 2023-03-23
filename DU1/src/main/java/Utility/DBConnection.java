package Utilities;

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
        Configuration conf = new Configuration();
        Properties pro = new Properties();
        pro.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        pro.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        pro.put(Environment.URL, "jdbc:sqlserver://localhost;database=DU1_NHOM1;trustServerCertificate=true");
        pro.put(Environment.USER, "sa"); 
        pro.put(Environment.PASS, "123456");

        pro.put(Environment.PASS, "123");

        pro.put(Environment.SHOW_SQL, true);
        conf.setProperties(pro);

        ServiceRegistry ser = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(ser);
    }

    public static SessionFactory getsetFactory() {
        return FACTORY;
    }

    ;



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
