/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaiModel.ChatLieu;
import domaiModel.DanhMuc;
import domaiModel.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import utility.DBConnection;
import utility.jframeCheck;
import viewModel.SanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public class resSanPham implements Interface<SanPham> {

//    @Override
//    public List<Object[]> getALLJoin(String dk) {
//        return DBConnection.selectQueRyJoin("from sanpham s join s.cl join s.dm");
//    }
    public List<Object[]> getALLJoinLoad() {
        return DBConnection.selectQueRyJoin("from sanpham s join s.cl join s.dm");
    }

    public List<SanPham> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int add(SanPham t) {
        return DBConnection.executeQuery(t, null);
    }

    public int update(SanPham t) {
        return DBConnection.executeQuery(t, "update");
    }

    public int delete(String t) {
        return DBConnection.delete(t, SanPham.class);
    }

    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public SanPham timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        //doing
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

    public List<SanPham> selectByMa(String ma) {
        List<SanPham> sp = new ArrayList<>();
        String maSelect = "%" + ma + "%";
        try(Session session=DBConnection.getseFactory().openSession()) {
            TypedQuery<SanPham> query=session.createQuery("from SanPham where ma like :t");
            query.setParameter("t", maSelect);
            sp=query.getResultList();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return sp;
    }
    
    

}
