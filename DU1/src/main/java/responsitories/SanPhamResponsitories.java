/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import domainmodel.SanPham;
import java.util.List;
import Utilities.DBConnection;

/**
 *
 * @author ADMIN
 */
public class SanPhamResponsitories implements Iresponsitories<SanPham>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from SanPham s join s.cl join s.dm");
    }
    
    public List<Object[]> getALLJoinLoad() {
        return DBConnection.selectQueRyJoin("from SanPham s join s.cl join s.dm");
    }

    @Override
    public List<SanPham> getAll(String dk) {
    return DBConnection.selectQueRy("from SanPham");
    }

    @Override
    public int add(SanPham t) {
        return DBConnection.executeQuery(t, null);
    }

    @Override
    public int update(SanPham t) {
        return DBConnection.executeQuery(t, "update");
    }

    @Override
    public int delete(String t) {
        return DBConnection.delete(t, SanPham.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
