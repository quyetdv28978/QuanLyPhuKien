package service;

import domaimodel.KhachHang;
import Responshitory.ResChatlieu;
import Responshitory.ResKhachHang;
import viewmodel.KhachHangViewModel;
import java.util.ArrayList;
import java.util.List;

public class SerKhachHang implements Interface<KhachHangViewModel> {

    public final ResKhachHang kh;

    public SerKhachHang() {
        this.kh = new ResKhachHang();
    }

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<KhachHangViewModel> getAll(String dk) {
        List<KhachHangViewModel> cl = new ArrayList<>();
        for (KhachHang i : this.kh.getAll(dk)) {
            cl.add(new KhachHangViewModel(i.getId(), i.getMa(), i.getTen(), i.getNgaySinh(), i.getSdt(), i.getSdt()));
        }
        return cl;
    }

    public List<KhachHangViewModel> getAllLoad() {
        List<KhachHangViewModel> cl = new ArrayList<>();
        for (KhachHang i : this.kh.getAllLoad()) {
            cl.add(new KhachHangViewModel(i.getId(), i.getMa(), i.getTen(), i.getNgaySinh(), i.getSdt(), i.getSdt()));
        }
        return cl;
    }

    @Override
    public int add(KhachHangViewModel t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(KhachHangViewModel t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhachHangViewModel timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(KhachHangViewModel t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
