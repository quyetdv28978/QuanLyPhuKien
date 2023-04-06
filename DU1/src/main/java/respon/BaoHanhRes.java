/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.BaoHanh;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import utility.DBConnection;

/**
 *
 * @author Admin
 */
public class BaoHanhRes implements Iresponsitories<BaoHanh> {

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from BaoHanh");
    }

    public List<Object[]> getALLChiTietBH() {
        return DBConnection.selectQueRyJoin("from BaoHanh bh join bh.sp join bh.kh");
    }

    @Override
    public List<BaoHanh> getAll(String dk) {
        return DBConnection.selectQueRy("from BaoHanh");
    }

    @Override
    public int add(BaoHanh q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(BaoHanh q) {
        
        return DBConnection.executeQuery(q, "update");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, BaoHanh.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BaoHanh timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<BaoHanh> getALlBH() {
        if (DBConnection.selectQueRy("from BaoHanh") != null) {
            return DBConnection.selectQueRy("from BaoHanh");
        }
        return null;
    }

//    public List<Object> getAllBH() {
//        return DBConnection.selectQueRy("from BaoHanh");
//    }
    public List<BaoHanh> SelectbyMaBH(String tenKM) {
        List<BaoHanh> pas;
        String nameSelect = "%" + tenKM + "%";
        try (Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<BaoHanh> query = session.createQuery("FROM BaoHanh bh \n"
//                    + "JOIN bh.sp sp \n"
//                    + "JOIN bh.kh kh \n"
                    + "WHERE bh.kh.sdt LIKE :key OR bh.ma LIKE :key");
            query.setParameter("key", nameSelect);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
        return pas;
    }

}
