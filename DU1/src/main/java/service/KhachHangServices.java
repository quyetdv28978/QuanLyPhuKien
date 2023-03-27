/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaimodel.KhachHang;
import java.util.ArrayList;
import java.util.List;
import respon.KhachHangResponsitories;
import viewmodel.KhachHangViewModel1;

/**
 *
 * @author Admin
 */
public class KhachHangServices implements IServices<KhachHangViewModel1> {

    public final KhachHangResponsitories hangResponsitories = new KhachHangResponsitories();

   

    @Override
    public int add(KhachHangViewModel1 q) {
        return hangResponsitories.add((KhachHang) CD(q));
    }

    @Override
    public int update(KhachHangViewModel1 q) {
        return hangResponsitories.update((KhachHang) CD(q));
    }

    @Override
    public int delete(String q) {
        return hangResponsitories.delete(q);
    }

    @Override
    public String timID(String ma) {
        return hangResponsitories.timID(ma);
    }

    @Override
    public KhachHangViewModel1 timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(KhachHangViewModel1 q) {
        return new KhachHang(q.getId(), q.getMa(), q.getTen(), q.getSdt(), q.getDiaChi(), q.getGioiTinh(), q.getNgaySinh());

    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<KhachHangViewModel1> getAllKhachHang() {
        if (this.hangResponsitories.getAllKH() != null) {
            List<KhachHangViewModel1> listKH = new ArrayList<>();
            List<KhachHang> a = hangResponsitories.getAllKH();
            for (KhachHang chucVu : a) {
                listKH.add(new KhachHangViewModel1(chucVu.getId(),chucVu.getMa(), chucVu.getTen(), chucVu.getGioiTinh(), chucVu.getSdt(), chucVu.getNgaySinh(), chucVu.getDiaChi()));
            }
            return listKH;
        }
        return null;
    }
     @Override
    public List<KhachHangViewModel1> getALl(String dk) {
        List<KhachHangViewModel1> a = new ArrayList<>();
        if(hangResponsitories.getAll(dk) != null){
        for (KhachHang khachHang : hangResponsitories.getAll(dk)) {
            a.add(new KhachHangViewModel1(khachHang.getId(), khachHang.getMa(),
                    khachHang.getTen(), khachHang.getGioiTinh(), khachHang.getSdt(), 
                    khachHang.getNgaySinh(), khachHang.getDiaChi()));
        }
        return a;
        }
        return null;

    }
      public List<KhachHang> SelectbyName(String ten) {
    return hangResponsitories.SelectbyName(ten);
    }
}
