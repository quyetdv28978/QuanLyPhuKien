/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import utility.DBConnection;
import domaimodel.KhachHang;
import domaimodel.KhuyenMai;
import domaimodel.SanPham;
import domaimodel.KhachHang;
import domaimodel.KhuyenMai;
import domaimodel.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import viewmodel.KhuyenMaiViewModel;

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
    
     public List<KhuyenMai> SelectbyName(String tenKM) {
      List<KhuyenMai> pas;
        String nameSelect = "%" + tenKM + "%";
        try ( Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<KhuyenMai> query = session.createQuery("From KhuyenMai  WHERE tenKM like :key");
            query.setParameter("key", nameSelect);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
        return pas;
  }
     
      public List<KhuyenMai> SelectbyTrangThai(String trangThai) {
      List<KhuyenMai> pas;
        String nameSelect = "%" + trangThai + "%";
        try ( Session session = DBConnection.getsetFactory().openSession()) {
            TypedQuery<KhuyenMai> query = session.createQuery("From KhuyenMai  WHERE trangThai like :key ");
            query.setParameter("key", nameSelect);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
        return pas;
  }
}
