/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.DanhMuc;
import java.util.ArrayList;
import java.util.List;
import responsitories.DanhMucResponsitories;
import viewmodel.DanhMucViewModel;

/**
 *
 * @author ADMIN
 */
public class DanhMucServices implements IServices<DanhMucViewModel>{

    public final DanhMucResponsitories danhMuc;
    
    public DanhMucServices() {
        this.danhMuc=new DanhMucResponsitories();
    }
    public List<DanhMucViewModel> getAllLoad() {
        List<DanhMucViewModel> dm=new ArrayList<>();
        for(DanhMuc i:danhMuc.getAllLoad()){
            dm.add(new DanhMucViewModel(i.getId(), i.getDongSP()));
        }
        return dm;
    }

    @Override
    public int add(DanhMucViewModel t) {
        return danhMuc.add((DanhMuc)CD(t));
    }

    @Override
    public int update(DanhMucViewModel t) {
        return danhMuc.update((DanhMuc)CD(t));
    }

    @Override
    public int delete(String t) {
        return danhMuc.delete(t);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DanhMucViewModel timOB(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(DanhMucViewModel t) {
        return new DanhMuc(t.getDongSP());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DanhMucViewModel> getALl(String dk) {
         List<DanhMucViewModel> dm=new ArrayList<>();
        for(DanhMuc i:danhMuc.getAll(dk)){
            dm.add(new DanhMucViewModel(i.getId(), i.getDongSP()));
        }
        return dm;
    }

    
}
