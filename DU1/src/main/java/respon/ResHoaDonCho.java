/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.HoaDon;
import utility.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResHoaDonCho {
    public List<HoaDon> getAll(String dk, int tt){
        List list = DBConnection.selectQueRy("from HoaDon ct where ct.tinhTrang = "+ tt +" and " + dk);
        
        if (list != null) {
            return list;
        }
        return null;
    }
    
    public List<Object> getAllHDCHo(String dk, int tt){
       return DBConnection.selectQueRy("from ChiTietGioHang c where c.gh.tinhtrang =  "+ tt +" and  c.gh.nv.id = '" + dk + "'");
    }
}
