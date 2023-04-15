/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChiTietHoaDon;
import domaimodel.HoaDon;
import domaimodel.SanPham;
import utility.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResHoaDonCho {

    public List<HoaDon> getAll(String dk, int tt) {
        List list = DBConnection.selectQueRy("from HoaDon ct where ct.tinhTrang = " + tt + " and " + dk);

        if (list != null) {
            return list;
        }
        return null;
    }

    public List<Object> getAllSPHD(String dk, int tt) {
        return DBConnection.selectQueRy("from ChiTietHoaDon c where c.hd.id = '" + dk + "'");
    }

    public List<Object> getAllSPHDNoEm(String dk, int tt) {
        return DBConnection.selectQueRy("from chitiethoadonNoEmbe c where c.hd.id = '" + dk + "'");
    }

    public List<SanPham> getAllSPHDCungGiaNoEm(String dk, int tt) {
        return DBConnection.selectQueRy("from SanPham\n"
                + "where giaBan <= '" + dk + "'");
    }

    public List<Object> getAllHDCHo(String dk, int tt, String dk2) {
        return DBConnection.selectQueRy("from ChiTietGioHang c where c.gh.tinhtrang =  " + tt + " and  c.gh.nv.id = '" + dk + "'"
                + " and c.gh.id ='" + dk2 + "'");
    }

    public boolean checkSPApDungKm(String idSP, String idhd) {
        if (!DBConnection.selectQueRy("from ChiTietHoaDon cthd where giagiam > 0 and cthd.hd.ma = '" + idhd + "' and cthd.sp.ma = '" + idSP + "'").isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Object> getALLCTHDandHD(String dk) {
        if (DBConnection.selectQueRy("from ChiTietHoaDon c where c.hd.tinhTrang = 0 and c.hd.ma = '" + dk + "'") != null) {
            return DBConnection.selectQueRy("from ChiTietHoaDon c where c.hd.tinhTrang = 0 and c.hd.ma = '" + dk + "'");
        }
        return null;
    }

}
