/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;


import domaimodel.BaoHanh;
import domaimodel.ChiTietHoaDon;
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
//
//    public List<Object[]> getALLChiTietBH() {
//        return DBConnection.selectQueRyJoin("from BaoHanh ctbh join ctbh.bh");
//    }

    @Override
    public List<BaoHanh> getAll(String dk) {
        if (DBConnection.selectQueRy("from BaoHanh") != null) {
            return DBConnection.selectQueRy("from BaoHanh");
        }
        return null;
    }
    
    @Override
    public int add(BaoHanh q) {
//        System.out.println("so" + q.getSoLanBH());
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(BaoHanh q) {
        System.out.println("hhh" + q);
        System.out.println("id vtbh" + q.getId());
        
        if (DBConnection.executeQuery(q, "update") != 0) {
            return DBConnection.executeQuery(q, "");
        }
        return 0;

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

    public List<BaoHanh> getALlBH() {
        if (DBConnection.selectQueRy("from BaoHanh") != null) {
            return DBConnection.selectQueRy("from BaoHanh");
        }
        return null;
    }

    public int themCTBH(String dk) {
        return DBConnection.selectQueRy("from BaoHanh k where k.bh.id = '" + dk + "'").size() + 1;
    }

}
