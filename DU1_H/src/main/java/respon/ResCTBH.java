/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChiTietBaoHanh;
import java.util.ArrayList;
import java.util.List;
import utility.DBConnection;

/**
 *
 * @author ADMIN
 */
public class ResCTBH implements Iresponsitories<ChiTietBaoHanh>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from ChiTietBaoHanh ct join ct.bh");
    }

    @Override
    public List<ChiTietBaoHanh> getAll(String dk) {
        return DBConnection.selectQueRy("from ChiTietBaoHanh");
    }

    @Override
    public int add(ChiTietBaoHanh q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(ChiTietBaoHanh q) {
        return DBConnection.executeQuery(q, "update");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, ChiTietBaoHanh.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietBaoHanh timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    public List<ChiTietBaoHanh> searchBySDT_CTBH(String sdt) {
//        List<ChiTietBaoHanh> ct=new ArrayList<>();
//        String sdt="%"+sdt+"%";
//        try{
//            
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return ct;
//    }
    
}
