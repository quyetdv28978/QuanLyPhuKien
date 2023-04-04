/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import utility.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResHoaDonCho {
    public List<Object> getAll(String dk){
        List list = DBConnection.selectQueRy("from HoaDon ct where ct.tinhTrang = 1 and " + dk);
        
        if (list != null) {
            return list;
        }
        return null;
    }
    
    public List<Object> getAllHDCHo(String dk){
       return DBConnection.selectQueRy("from ChiTietGioHang c where c.gh.tinhtrang = 1 and  c.gh.nv.id = '" + dk + "'");
    }
//    
//    public static void main(String[] args) {
//        ResHoaDonCho res = new ResHoaDonCho();
//        ChiTietGioHang c= ((ChiTietGioHang) res.getAllHDCHo("").get(0));
//        System.out.println(c);
//    }
}
