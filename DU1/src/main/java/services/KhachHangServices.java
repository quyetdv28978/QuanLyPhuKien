/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.KhachHang;
import java.util.ArrayList;
import java.util.List;
import responsitories.KhachHangResponsitories;
import viewmodel.KhachHangViewModel;

/**
 *
 * @author Admin
 */
public class KhachHangServices implements IServices<KhachHangViewModel>{
public final KhachHangResponsitories hangResponsitories = new KhachHangResponsitories();

   

    @Override
    public List<KhachHangViewModel> getALl(String dk) {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int add(KhachHangViewModel q) {
        return hangResponsitories.add((KhachHang) CD(q));
    }

    @Override
    public int update(KhachHangViewModel q) {
        return hangResponsitories.update((KhachHang) CD(q));
    }

    @Override
    public int delete(String q) {
        return hangResponsitories.delete(q);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhachHangViewModel timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(KhachHangViewModel q) {
        
        return new KhachHang(q.getId(), q.getMa(), q.getTen(), q.getSđt(), q.getDiaChi(), q.getNgaySinh());
                
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public List<KhachHangViewModel> getAllKhachHang(){
        if (this.hangResponsitories.getAllKH()!= null) {
            List<KhachHangViewModel> listKH = new ArrayList<>();
            List<KhachHang> a = hangResponsitories.getAllKH();
            for (KhachHang chucVu : a ){
                listKH.add(new KhachHangViewModel(chucVu.getId(), chucVu.getMa(), chucVu.getTen(), chucVu.getSđt(), chucVu.getDiaChi(), chucVu.getNgaySinh()));
            }
            return listKH;
        }
        return null;
    }
}
