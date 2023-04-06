/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.BaoHanh;
import domaimodel.ChiTietBaoHanh;
import java.util.List;
import utility.DBConnection;

/**
 *
 * @author Admin
 */
public class ChiTietBaoHanhRes implements Iresponsitories<ChiTietBaoHanh> {

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from ChiTietBaoHanh");
    }

    public List<Object[]> getALLChiTietBH() {
        return DBConnection.selectQueRyJoin("from ChiTietBaoHanh ctbh join ctbh.bh");
    }

    @Override
    public List<ChiTietBaoHanh> getAll(String dk) {
        if (DBConnection.selectQueRy("from ChiTietBaoHanh") != null) {
            return DBConnection.selectQueRy("from ChiTietBaoHanh");
        }
        return null;
    }

    @Override
    public int add(ChiTietBaoHanh q) {
        System.out.println("so" + q.getSoLanBH());
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(ChiTietBaoHanh q) {
        System.out.println("hhh" + q);
        System.out.println("id vtbh" + q.getBh().getId());
        if (DBConnection.executeQuery(q, "update") != 0) {
            return DBConnection.executeQuery(q, "update");
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
    public ChiTietBaoHanh timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<BaoHanh> getALlBH() {
        if (DBConnection.selectQueRy("from BaoHanh") != null) {
            return DBConnection.selectQueRy("from BaoHanh");
        }
        return null;
    }

    public int themCTBH(String dk) {
        return DBConnection.selectQueRy("from ChiTietBaoHanh k where k.bh.id = '" + dk + "'").size() + 1;
    }

}
