/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChiTietHoaDon;
import domaimodel.HoaDon;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
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

    public List<ChiTietHoaDon> SelectbyNgay(Date ngay) {
        List<ChiTietHoaDon> pas;
        Date n = ngay;
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<ChiTietHoaDon> query = session.createQuery("From ChiTietHoaDon WHERE n=:key1 and TinhTrang=0");
            query.setParameter("key1", n);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
        return pas;
    }

}
