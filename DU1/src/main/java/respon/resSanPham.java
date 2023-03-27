/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

//import domaimodel.ChatLieu;
import domaimodel.DanhMuc;
import domaimodel.KhuyenMai;
import domaimodel.SanPham;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import utility.DBConnection;
//import utility.jframeCheck;

/**
 *
 * @author ADMIN
 */
public class resSanPham implements Iresponsitories<SanPham>{

//    @Override
//    public List<Object[]> getALLJoin(String dk) {
//        return DBConnection.selectQueRyJoin("from sanpham s join s.cl join s.dm");
//    }
    
    public List<Object[]> getALLJoinLoad() {
        return DBConnection.selectQueRyJoin("from sanpham s join s.cl join s.dm");
    }

    @Override
    public List<SanPham> getAll(String dk) {
//        if (DBConnection.selectQueRy("from SanPham") != null) {
        return  DBConnection.selectQueRy("from SanPham");
//        }
//        return null;
    }
    public List<SanPham> getAll123(String dk) {
        if (DBConnection.selectQueRy("from SanPham") != null) {
        return  DBConnection.selectQueRy("from SanPham");
        }
        return null;
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
      public List<SanPham> SelectbyGia(Float gia1, Float gia2) {
      List<SanPham> pas;
        Float gia = gia1 ;
                Float b =  gia2;
        try ( Session session = DBConnection.getseFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("From SanPham  WHERE giaBan between :key and :key2");
            query.setParameter("key", gia);
            query.setParameter("key2", b);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
        
        return pas;
        }
      public List<SanPham> SelectbyTen(String tenSanPham) {
      List<SanPham> pas;
        
        String a = "%" + tenSanPham + "%";
        try ( Session session = DBConnection.getseFactory().openSession()) {
            TypedQuery<SanPham> query = session.createQuery("From SanPham  WHERE tenSanPham like :key");
            query.setParameter("key", a);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
        
        return pas;
        }
      
        public static void main(String[] args) {
       SanPham s= DBConnection.getseFactory().openSession().get(SanPham.class, "C2E5951A-A4F4-42A9-AA80-0C87781AD57D");
            System.out.println(s.getId());
        }
    }

   
    
    

