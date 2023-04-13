/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.HoaDon;
import java.util.List;
import utility.DBConnection;

/**
 *
 * @author yugip
 */
public class restksp implements  ihoadon{

    @Override
    public List<HoaDon> gethd() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getList() {
        return DBConnection.selectQueRyJoin("SELECT Sum(CHITIEThoadon.soLuong),sanpham.tenSanPham\n" +
"FROM     SANPHAM INNER JOIN\n" +
"                  CHITIETHOADON ON SANPHAM.id = CHITIETHOADON.idSP INNER JOIN\n" +
"                  HOADON ON CHITIETHOADON.idHD = HOADON.id where hoadon.tinhtrang=0 group by sanpham.tenSanPham");
    }
 
   
}
