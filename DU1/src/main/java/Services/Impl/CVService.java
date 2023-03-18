/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.CV;
import Respositories.Impl.CVRespository;
import Services.IFService;
import ViewModel.ChucVuViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suppe
 */
public class CVService implements IFService<ChucVuViewModel> {

    private final CVRespository resCV = new CVRespository();

    @Override
    public List<ChucVuViewModel> getALl(String dk) {
        List<ChucVuViewModel> listCVV = new ArrayList<>();
        for (CV chucVuView : this.resCV.getAll(dk)) {
            listCVV.add(new ChucVuViewModel(chucVuView.getId(), chucVuView.getMa(), chucVuView.getTen()));
        }
        return listCVV;
    }

    @Override
    public int add(ChucVuViewModel q) {
        return this.resCV.add((CV) CD(q));
    }

    @Override
    public int update(ChucVuViewModel q) {
        return this.resCV.update((CV) CD(q));
    }

    @Override
    public int delete(String q) {
        return this.resCV.delete(q);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChucVuViewModel timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(ChucVuViewModel q) {
        return new CV(q.getId(), q.getMa(), q.getTen());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
