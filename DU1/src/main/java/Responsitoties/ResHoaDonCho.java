/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsitoties;

import DomainModel.ChiTietGioHang;
import DomainModel.HoaDon;
import DomainModel.KhachHang;
import Utilities.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResHoaDonCho {
    public List<Object> getAll(String dk){
        return DBConnection.selectQueRy("from HoaDon ct where ct.tinhTrang = 1 and " + dk);
    }
    
    public List<Object> getAllHDCHo(String dk){
//       HoaDon kh = ((HoaDon)getAll("ct.kh.id = '" + "47B77505-09B9-4C67-90E5-34634B658AAF" + "'").get(0));
       return DBConnection.selectQueRy("from ChiTietGioHang c where c.gh.tinhtrang = 1 and  c.gh.nv.id = '" + dk + "'");
    }
    
    public static void main(String[] args) {
        ResHoaDonCho res = new ResHoaDonCho();
        ChiTietGioHang c= ((ChiTietGioHang) res.getAllHDCHo("").get(0));
        System.out.println(c);
    }
}
