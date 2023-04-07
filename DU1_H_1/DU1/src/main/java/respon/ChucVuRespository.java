/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChucVu;
import domaimodel.NhanVien;
import utility.DBConnection;
import java.util.List;

/**
 *
 * @author suppe
 */
public class ChucVuRespository implements IManageRespository {

    @Override
    public List<ChucVu> getListChucVuFromDb() {
        if (DBConnection.selectQueRy("FROM ChucVu") != null) {
            return DBConnection.selectQueRy("FROM ChucVu");
        }
        return null;
    }

    public List<ChucVu> getAllLoad() {
        return DBConnection.selectQueRy("from ChucVu");
    }

    @Override
    public List<Object[]> getJoin(String dieuKien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> getListNhanVienFromDb() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int them(NhanVien maNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int capNhat(NhanVien maNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String maNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
