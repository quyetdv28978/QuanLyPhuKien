/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respository;

import domainModel.ChatLieu;
import domainModel.DanhMuc;
import domainModel.SanPham;
import java.util.List;
import utility.DBConnection;
import utility.jframeCheck;

/**
 *
 * @author ADMIN
 */
public class resSanPham implements Interface<SanPham>{

//    @Override
//    public List<Object[]> getALLJoin(String dk) {
//        return DBConnection.selectQueRyJoin("from sanpham s join s.cl join s.dm");
//    }
    
    public List<Object[]> getALLJoinLoad() {
        return DBConnection.selectQueRyJoin("from sanpham s join s.cl join s.dm");
    }

    @Override
    public List<SanPham> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    public List<Object[]> getALLJoin(String dk) {
        if (DBConnection.selectQueRyJoin("from SanPham s join s.dm join s.cl") != null) {
            return DBConnection.selectQueRyJoin("from SanPham s join s.dm join s.cl");
        }
        return null;
    }
    
    public List<DanhMuc> getALLDM() {
        if (DBConnection.selectQueRy("from DanhMuc") != null) {
            return DBConnection.selectQueRy("from DanhMuc");
        }
        return null;
    }

    public List<ChatLieu> getALLCL() {
        if (DBConnection.selectQueRy("from ChatLieu") != null) {
            return DBConnection.selectQueRy("from ChatLieu");
            
        }
        return null;
    }
    
}
