/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import viewmodel.ChiTietHoaDonViewModel;
import viewmodel.HoaDonViewModel;
import domaimodel.ChiTietHoaDon;
import domaimodel.HoaDon;
import domaimodel.SanPham;
import domaimodel.embeddableCTHD;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import respon.ChiTietHoaDonres;
import service.Interface;
import viewmodel.SanPhamViewModel;

/**
 *
 * @author DELL
 */
public class TK_SanPhamTon implements Interface<ChiTietHoaDonViewModel>{
    public final ChiTietHoaDonres rct;

    public TK_SanPhamTon() {
        this.rct = new ChiTietHoaDonres();
    }
    
    

    @Override
    public List<ChiTietHoaDonViewModel> getALl(String dk) {
         List<ChiTietHoaDonViewModel> list=new  ArrayList<>();
         if (rct.getALLJoin(dk)!= null) {
             for (Object[] ct :this.rct.getALLJoin(dk)) {
                list.add(new ChiTietHoaDonViewModel(new HoaDon(((HoaDon) ct[0]).getNv()), new SanPham(((SanPham) ct[1]).getMa(), ((SanPham) ct[1]).getTenSanPham(), ((SanPham) ct[1]).getSoLuong()), ((SanPham) ct[1]).getSoLuong()-((ChiTietHoaDon) ct[2]).getSoluong(),((ChiTietHoaDon) ct[2]).getNgayTao()));
             }
             return list;
        }
         return  null;
    }
    
    
    @Override
    public int add(ChiTietHoaDonViewModel q) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public ChiTietHoaDonViewModel timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(ChiTietHoaDonViewModel q) {
     return  new ChiTietHoaDon(new SanPham(q.getSp().getMa(),q.getSp().getTenSanPham(), q.getSp().getSoLuong()), new HoaDon(q.getHd().getNv()), q.getSp().getSoLuong()-q.getSoluong(), q.getNgayTao());
        
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private ChiTietHoaDonViewModel ChiTietHoaDonViewModel(embeddableCTHD sphd, HoaDonViewModel hoaDonViewModel, SanPhamViewModel sanPhamViewModel, int soluong, float donGia, float giagiam, Date ngayTao, int trangThai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
