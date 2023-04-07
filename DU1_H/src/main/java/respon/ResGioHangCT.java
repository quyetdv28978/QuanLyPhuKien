/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.ChiTietGioHang;
import domaimodel.ChiTietHoaDon;
import domaimodel.GioHang;
import domaimodel.HoaDon;
import utility.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResGioHangCT implements IfResponsitoties<ChiTietGioHang> {

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietGioHang> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int add(ChiTietGioHang q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(ChiTietGioHang q) {
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
    public ChiTietGioHang timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//    Hoa don
    public int addHD (HoaDon hd){
        return DBConnection.executeQuery(hd, null);
    }
//    Hoa don chi tiet
    public int addHDCT(ChiTietHoaDon cthd){
        return DBConnection.executeQuery(cthd, null);
    }

    
//  Them gio hang  
    public int addGH(GioHang q) {
        return DBConnection.executeQuery(q, null);
    }
    
//    Update gio hang
    public int updateGH (GioHang g){
        return DBConnection.executeQuery(g, "update");
    }
    
//    Update hoa don
    public int updateHD(HoaDon hd){
        return DBConnection.executeQuery(hd, "update");
    }
    
//    GetAll gio hang
    
    public List<GioHang> getALlGioHangs(String dk){
        return DBConnection.selectQueRy("From GioHang g " + dk);
    }
    
 
}
