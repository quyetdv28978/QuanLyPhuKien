/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Utilities.DBConnection;
import domainmodel.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
import responsitories.KhuyenMaiResponsitories;
import viewmodel.KhachHangViewModel;
import viewmodel.KhuyenMaiViewModel;

/**
 *
 * @author Admin
 */
public class KhuyenMaiServices implements IServices<KhuyenMaiViewModel>{
public final KhuyenMaiResponsitories km = new KhuyenMaiResponsitories();
   
    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KhuyenMaiViewModel> getALl(String dk) {
        List<KhuyenMaiViewModel> listKMVM = new ArrayList<>();
        List<KhuyenMai> listKM = km.getAll(dk);
        for (KhuyenMai khuyenMai : listKM) {
            listKMVM.add(new KhuyenMaiViewModel(khuyenMai.getMa(), khuyenMai.getTenKM(), 
                    khuyenMai.getNgayTao(), khuyenMai.getNgayKT(), khuyenMai.getMoTa()));
        }
        return listKMVM;
    }
    public List<KhuyenMaiViewModel> getAllLoad() {
        List<KhuyenMaiViewModel> dm=new ArrayList<>();
        for(KhuyenMai i:km.getAllLoad()){
            dm.add(new KhuyenMaiViewModel(i.getMa(), i.getTenKM(), i.getNgayBD(), i.getNgayKT(), i.getMoTa()));
        }
        return dm;
    }

    @Override
    public int add(KhuyenMaiViewModel q) {
        return km.add((KhuyenMai)CD(q));
    }

    @Override
    public int update(KhuyenMaiViewModel q) {
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
    public KhuyenMaiViewModel timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(KhuyenMaiViewModel q) {
//        return new KhuyenMai(q.getMa(), q.getTenKM(), q.getNgayBD(), q.getNgayKT(), q.getMoTa());
    }
    
}
