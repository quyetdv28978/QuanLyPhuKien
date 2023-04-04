/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChucVu;
import domaimodel.NhanVien;
import utility.DBConnection;
import java.util.List;
//import respon.IManageNhanVienRespository;

/**
 *
 * @author suppe
 */
public class NhanVienRespository implements IManageNhanVienRespository {

    @Override
    public List<Object[]> getJoin(String dieuKien) {
        if (DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu") != null) {
            return DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu");
        } else {
            return null;
        }
    }

    @Override
    public List<NhanVien> getListNhanVienFromDb() {
        return DBConnection.selectQueRy("FROM NhanVien");
    }

    @Override
    public int them(NhanVien maNhanVien) {
        return DBConnection.executeQuery(maNhanVien, null);
    }

    @Override
    public int capNhat(NhanVien maNhanVien) {
        return DBConnection.executeQuery(maNhanVien, "capNhat");
    }

    @Override
    public int xoa(String maNhanVien) {
        return DBConnection.delete(maNhanVien, NhanVien.class);
    }

    public List<Object[]> getAllJoinLoad() {
        return DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu");
    }

    public List<Object[]> getAllJoin(String dieuKien) {
        if (DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu") != null) {
            return DBConnection.selectQueRyJoin("from NhanVien nv join nv.chucVu");
        }
        return null;
    }

    public List<ChucVu> getAllChucVu() {
        if (DBConnection.selectQueRy("from ChucVu") != null) {
            return DBConnection.selectQueRy("from ChucVu");
        }
        return null;
    }

}
