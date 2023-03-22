/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import Responsitoties.ResNhanVien;
import ViewModel.ChucVuView;
import ViewModel.NhanVienView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yugip
 */
public class SerNhanVien implements IFService<NhanVienView> {

    private final ResNhanVien resNV = new ResNhanVien();

    @Override
    public List<NhanVienView> getALl(String dk) {
        List<NhanVienView> listNVV = new ArrayList<>();
        if (this.resNV.getALLJoin(dk) != null) {
            for (Object[] objects : this.resNV.getALLJoin(dk)) {
                listNVV.add(new NhanVienView(((NhanVien) objects[0]).getId(), ((NhanVien) objects[0]).getMa(), ((NhanVien) objects[0]).getTen(),
                        ((NhanVien) objects[0]).getGioitinh(), ((NhanVien) objects[0]).getDiachi(),
                        ((NhanVien) objects[0]).getSdt(), ((NhanVien) objects[0]).getNgaySinh(),
                           new ChucVuView(((ChucVu) objects[1]).getId(), ((ChucVu) objects[1]).getMa(), ((ChucVu) objects[1]).getTen()),
                        null, null));
                        }
            return listNVV;
        }
        return null;
    }

    @Override
    public int add(NhanVienView q) {

        return this.resNV.add((NhanVien) CD(q));
    }

    @Override
    public int update(NhanVienView q) {
        return this.resNV.update((NhanVien) CD(q));
    }

    @Override
    public int delete(String q) {
        return this.resNV.delete(q);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVienView timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(NhanVienView q) {
        return new NhanVien(q.getId(), q.getMa(), q.getTen(), q.getGioitinh(), q.getDiachi(),
                q.getSdt(), q.getNgaySinh(),
                new ChucVu(q.getCv().getId(), q.getCv().getMa(), q.getCv().getTen()),
                null, null
        );
    }

    public List<ChucVuView> getAllCVV() {
        if (this.resNV.getALlCV() != null) {
            List<ChucVuView> listCVV = new ArrayList<>();

            for (ChucVu chucVu : this.resNV.getALlCV()) {
                listCVV.add(new ChucVuView(chucVu.getId(), chucVu.getMa(), chucVu.getTen()));
            }
            return listCVV;
        }
        return null;
    }

//    @Override
//    public boolean checkTrung(String ma) {
//        
//    }
    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
