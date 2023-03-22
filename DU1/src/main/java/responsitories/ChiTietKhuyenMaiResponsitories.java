/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.DBConnection;
import domainmodel.ChiTietKhuyenMai;
import domainmodel.KhuyenMai;
import domainmodel.SanPham;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ChiTietKhuyenMaiResponsitories implements Iresponsitories<ChiTietKhuyenMai>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from ChiTietKhuyenMai");
    }
    public List<Object> getALLJoin1(String dk) {
        return DBConnection.selectQueRy("from ChiTietKhuyenMai");
    }
     public List<Object[]> getALLChiTietKM() {
        return DBConnection.selectQueRyJoin("from ChiTietKhuyenMai ctkm join ctkm.sp join ctkm.km");
    }

    @Override
    public List<ChiTietKhuyenMai> getAll(String dk) {
        return DBConnection.selectQueRy("from ChiTietKhuyenMai");
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
            return DBConnection.selectQueRy("from KhuyenMai");
        }
        return null;
    }
    public List<Object> getAllSP(){
        return DBConnection.selectQueRy("from SanPham");
    }
    
    
}
