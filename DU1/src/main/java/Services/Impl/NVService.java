/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.CV;
import DomainModels.NV;
import Respositories.Impl.NVRespository;
import Services.IFService;
import ViewModel.ChucVuViewModel;
import ViewModel.NhanVienViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yugip
 */
public class NVService implements IFService<NhanVienViewModel> {

    private final NVRespository resNV = new NVRespository();

    @Override
    public List<NhanVienViewModel> getALl(String dk) {
        List<NhanVienViewModel> listNVV = new ArrayList<>();
        if (this.resNV.getALLJoin(dk) != null) {
            for (Object[] objects : this.resNV.getALLJoin(dk)) {
                listNVV.add(new NhanVienViewModel(((NV) objects[0]).getId(), ((NV) objects[0]).getMa(), ((NV) objects[0]).getTen(),
                        ((NV) objects[0]).getGioitinh(), ((NV) objects[0]).getDiachi(),
                        ((NV) objects[0]).getSdt(), ((NV) objects[0]).getNgaySinh(),
                        new ChucVuViewModel(((CV) objects[1]).getId(), ((CV) objects[1]).getMa(), ((CV) objects[1]).getTen()),
                        ((NV) objects[0]).getTentk(),
                        ((NV) objects[0]).getMk()
                ));
            }
            return listNVV;
        }
        return null;
    }

    @Override
    public int add(NhanVienViewModel q) {

        return this.resNV.add((NV) CD(q));
    }

    @Override
    public int update(NhanVienViewModel q) {
        return this.resNV.update((NV) CD(q));
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
    public NhanVienViewModel timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(NhanVienViewModel q) {
        return new NV(q.getId(), q.getMa(), q.getTen(), q.getGioitinh(), q.getDiachi(),
                q.getSdt(), q.getNgaySinh(),
                new CV(q.getCv().getId(), q.getCv().getMa(), q.getCv().getTen()),
                q.getTentk(),
                q.getMk()
        );
    }

    public List<ChucVuViewModel> getAllCVV() {
        if (this.resNV.getALlCV() != null) {
            List<ChucVuViewModel> listCVV = new ArrayList<>();

            for (CV chucVu : this.resNV.getALlCV()) {
                listCVV.add(new ChucVuViewModel(chucVu.getId(), chucVu.getMa(), chucVu.getTen()));
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
