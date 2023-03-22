/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsitoties;

import DomainModel.KhuyenMai;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class KhuyenMaiResponsitories implements Iresponsitories<KhuyenMai>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KhuyenMai> getAll(String dk) {
           if(DBConnection.selectQueRy("from KhuyenMai") != null){
       return DBConnection.selectQueRy("from KhuyenMai where tenKM = " + "'" + dk+ "'" );
       }
         return null;
    }
     public List<KhuyenMai> findTrangThai(int dk) {
           if(DBConnection.selectQueRy("from KhuyenMai") != null){
       return DBConnection.selectQueRy("from KhuyenMai where trangThai = " + "'" + dk+ "'" );
       }
         return null;
    }
    
     public List<KhuyenMai> getAllLoad() {
         if(DBConnection.selectQueRy("from KhuyenMai") != null){
       return DBConnection.selectQueRy("from KhuyenMai");
       }
         return null;
    }

    @Override
    public int add(KhuyenMai q) {
        return DBConnection.executeQuery(q,null);
    }

    @Override
    public int update(KhuyenMai q) {
        return DBConnection.executeQuery(q, "skhfks");
    }

    @Override
    public int delete(String q) {
        return  DBConnection.delete(q, KhuyenMai.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhuyenMai timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
