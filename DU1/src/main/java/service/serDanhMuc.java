/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaimodel.DanhMuc;
import java.util.ArrayList;
import java.util.List;
import respon.resDanhMuc;
import viewmodel.DanhMucViewModel;

/**
 *
 * @author ADMIN
 */
public class serDanhMuc implements Interface<DanhMucViewModel>{

    public final resDanhMuc danhMuc;
    
    public serDanhMuc() {
        this.danhMuc=new resDanhMuc();
    }

    @Override
    public List<DanhMucViewModel> getALl(String dk) {
        List<DanhMucViewModel> dm=new ArrayList<>();
        for(DanhMuc i:this.danhMuc.getAll(dk)){
            dm.add(new DanhMucViewModel(i.getId(), i.getDongSP()));
        }
        return dm;
    }
    
    public List<DanhMucViewModel> getAllLoad() {
        List<DanhMucViewModel> dm=new ArrayList<>();
        for(DanhMuc i:this.danhMuc.getAllLoad()){
            dm.add(new DanhMucViewModel(i.getId(), i.getDongSP()));
        }
        return dm;
    }

    @Override
    public int add(DanhMucViewModel t) {
        return this.danhMuc.add((DanhMuc)CD(t));
    }

    @Override
    public int update(DanhMucViewModel t) {
        return this.danhMuc.update((DanhMuc)CD(t));
    }

    @Override
    public int delete(String t) {
        return this.delete(t);
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
        return new DanhMuc(t.getId(),t.getDongSP());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
