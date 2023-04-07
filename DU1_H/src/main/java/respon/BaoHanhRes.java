/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.BaoHanh;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import utility.DBConnection;

/**
 *
 * @author Admin
 */
public class BaoHanhRes implements Iresponsitories<BaoHanh>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from BaoHanh bh join bh.sp join bd.kh");
    }

    @Override
    public List<BaoHanh> getAll(String dk) {
        return DBConnection.selectQueRy("from BaoHanh");
    }

    @Override
    public int add(BaoHanh q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(BaoHanh q) {
        return DBConnection.executeQuery(q, "update");
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
    public BaoHanh timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<BaoHanh> selectByTen(String sdt) {
        List<BaoHanh> sp = new ArrayList<>();
        String sdtSL = "%" + sdt + "%";
        try(Session session=DBConnection.getsetFactory().openSession()) {
            TypedQuery<BaoHanh> query=session.createQuery("from BaoHanh where sdt like :t");
            query.setParameter("t", sdtSL);
            sp=query.getResultList();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return sp;
    }
    
}
