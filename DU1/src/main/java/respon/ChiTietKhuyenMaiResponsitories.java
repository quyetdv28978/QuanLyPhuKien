/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import utility.DBConnection;

//import domaimodel.ChiTietKhuyenMai;
//import domaimodel.KhuyenMai;
//import domaimodel.SanPham;
import domaimodel.ChiTietKhuyenMai;
import domaimodel.KhuyenMai;
//import domaimodel.SanPham;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
//import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ChiTietKhuyenMaiResponsitories implements Iresponsitories<ChiTietKhuyenMai>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return  DBConnection.selectQueRyJoin("from ChiTietKhuyenMai");
    }
    public List<Object> getALLJoin1(String dk) {
        return  DBConnection.selectQueRy("from ChiTietKhuyenMai");
    }
     public List<Object[]> getALLChiTietKM() {
        return DBConnection.selectQueRyJoin("from ChiTietKhuyenMai ctkm join ctkm.sp join ctkm.km");
    }

    @Override
    public List<ChiTietKhuyenMai> getAll(String dk) {
        if(DBConnection.selectQueRy("from ChiTietKhuyenMai") !=null){
        return  DBConnection.selectQueRy("from ChiTietKhuyenMai");
        }
        return null;
    }
    

    @Override
    public int add(ChiTietKhuyenMai q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(ChiTietKhuyenMai q) {
//       Session ss = DBConnection.getseFactory().openSession();
//       ChiTietKhuyenMai nv = DBConnection.getseFactory().openSession().get(ChiTietKhuyenMai.class, q.getId());
//       nv.setKm(q.getKm());
//       nv.setSp(q.getSp());
//       nv.setNgayKTCTKM(q.getNgayKTCTKM());
//      nv.setGiaGiam(q.getGiaGiam());
      
        return DBConnection.executeQuery(q, "update");
        
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, ChiTietKhuyenMai.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietKhuyenMai timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<KhuyenMai> getALlKM() {
        if (DBConnection.selectQueRy("from KhuyenMai") != null) {
            return  DBConnection.selectQueRy("from KhuyenMai");
        }
        return null;
    }
    public List<Object> getAllSP(){
        return  DBConnection.selectQueRy("from SanPham");
    }
     public List<ChiTietKhuyenMai> SelectbyTrangThaiCT(String trangThai) {
      List<ChiTietKhuyenMai> pas;
          System.out.println("a" + trangThai);
        String nameSelect = "%" + trangThai + "%";
        try ( Session session = DBConnection.getseFactory().openSession()) {
            TypedQuery<ChiTietKhuyenMai> query = session.createQuery("From KhuyenMai  WHERE trangThai like :key");
            query.setParameter("key", nameSelect);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
        return pas;
  }
    
}
