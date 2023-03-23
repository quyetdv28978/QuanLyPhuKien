/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respository;

import domaiModel.DanhMuc;
import java.util.List;
import utility.DBConnection;

/**
 *
 * @author ADMIN
 */
public class resDanhMuc implements Interface<DanhMuc>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DanhMuc> getAll(String dk) {
        return DBConnection.selectQueRy("from DanhMuc");
    }
    
    public List<DanhMuc> getAllLoad() {
        return DBConnection.selectQueRy("from DanhMuc");
    }

    @Override
    public int add(DanhMuc t) {
        return DBConnection.executeQuery(t, null);
    }

    @Override
    public int update(DanhMuc t) {
        return DBConnection.executeQuery(t, "update");
    }

    @Override
    public int delete(String t) {
        return DBConnection.delete(t, DanhMuc.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DanhMuc timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
