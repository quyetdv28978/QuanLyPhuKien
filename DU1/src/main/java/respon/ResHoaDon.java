/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChiTietHoaDon;
import respon.IfResponsitoties;
import domaimodel.HoaDon;
import domaimodel.KhachHang;
import utility.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResHoaDon implements IfResponsitoties<HoaDon>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from ChiTietHoaDon d left join d.hd " + dk);
    }

    @Override
    public List<HoaDon> getAll(String dk) {
        return DBConnection.selectQueRy("from HoaDon d " + dk);
    }
    
     public List<ChiTietHoaDon> getAllChiTietHoaDon(String dk) {
        return DBConnection.selectQueRy("from ChiTietHoaDon d " + dk);
    }

    @Override
    public int add(HoaDon q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(HoaDon q) {
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
    public HoaDon timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    select kh
    public List<KhachHang> getALl(){
        return DBConnection.selectQueRy("from KhachHang");
    }
}