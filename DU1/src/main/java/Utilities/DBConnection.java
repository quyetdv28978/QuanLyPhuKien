/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;


import java.util.Properties;
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
        pro.put(Environment.DRIVER,"com.microsoft.sqlserver.jdbc.SQLServerDriver");
        pro.put(Environment.URL, "jdbc:sqlserver://localhost;database=DU1_NHOM1;trustServerCertificate=true");
        pro.put(Environment.USER, "sa");
        pro.put(Environment.PASS, "123456");
        pro.put(Environment.SHOW_SQL, true);
        confi.setProperties(pro);
//        confi.addAnnotatedClass(SanPham.class);
        
        ServiceRegistry ser = new StandardServiceRegistryBuilder().applySettings(confi.getProperties()).build();
        FACTORY = confi.buildSessionFactory(ser);
        
    }
    
    public static SessionFactory getseFactory(){
        return FACTORY;
    }
}
