/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.DBConnection;
import domainmodel.DanhMuc;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DanhMucResponsitories implements Iresponsitories<DanhMuc>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DanhMuc> getAll(String dk) {
        return DBConnection.selectQueRy("from DanhMuc");
    }

    @Override
    public int add(DanhMuc q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(DanhMuc q) {
        return DBConnection.executeQuery(q, "update");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, DanhMuc.class);
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
