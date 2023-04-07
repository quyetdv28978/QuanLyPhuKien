package respon;

import domaimodel.ChiTietHoaDon;
import viewmodel.ChiTietHoaDonViewModel;
import domaimodel.HoaDon;
import domaimodel.SanPham;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import utility.DBConnection;

public class ChiTietHoaDonres implements Iresponsitories<ChiTietHoaDonViewModel> {

    @Override
    public List<Object[]> getALLJoin(String dk) {
        if (DBConnection.selectQueRyJoin("from ChiTietHoaDon c join s.hd join s.sp") != null) {
            return DBConnection.selectQueRyJoin("from ChiTietHoaDon c join s.hd join s.sp");
        }
        return null;
    }

    @Override
    public List<ChiTietHoaDonViewModel> getAll(String dk) {
        return DBConnection.selectQueRy("from ChiTietHoaDon c" + dk);
    }
     

    @Override
    public int add(ChiTietHoaDonViewModel q) {
        return  DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(ChiTietHoaDonViewModel q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietHoaDonViewModel timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDon> getALLHD() {
        if (DBConnection.selectQueRy("from HoaDon") != null) {
            return DBConnection.selectQueRy("from HoaDon");
        }
        return null;
    }

    public List<SanPham> getALLSP() {
        if (DBConnection.selectQueRy("from SanPham") != null) {
            return DBConnection.selectQueRy("from SanPham");
        }
        return null;
    }
    
       public List<Object[]> getALLJoinLoad() {
        return DBConnection.selectQueRyJoin("from ChiTietHoaDon c join s.hd join s.sp");
    }
    
         public List<Object[]> getALLJ() {
        if (DBConnection.selectQueRyJoin("from ChiTietHoaDon c join s.hd join s.sp") != null) {
            return DBConnection.selectQueRyJoin("from ChiTietHoaDon c join s.hd join s.sp");
        }
        return null;
    }
       


}
