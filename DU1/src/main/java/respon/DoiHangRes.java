/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.DoiHang;
import java.util.List;
import utility.DBConnection;

/**
 *
 * @author Admin
 */
public class DoiHangRes implements Iresponsitories<DoiHang>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
         if (DBConnection.selectQueRyJoin("from DoiHang d join d.hd join d.sp") != null) {
            return DBConnection.selectQueRyJoin("from DoiHang d join d.hd join d.sp");
        }
        return null;
    }
    public List<Object> getALLJoin1(String dk) {
         if (DBConnection.selectQueRy("from DoiHang d join d.hd join d.sp") != null) {
            return DBConnection.selectQueRy("from DoiHang d join d.hd join d.sp");
        }
        return null;
    }

    @Override
    public List<DoiHang> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int add(DoiHang q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(DoiHang q) {
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
    public DoiHang timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
