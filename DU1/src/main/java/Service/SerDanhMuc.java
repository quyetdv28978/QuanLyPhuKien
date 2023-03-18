/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModel.DanhMuc;
import Responsitoties.IfResponsitoties;
import Responsitoties.ResDanhMuc;
import ViewModel.DanhMucView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yugip
 */
public class SerDanhMuc implements IFService<DanhMucView> {

    private ResDanhMuc resDM = new ResDanhMuc();

    @Override
    public List<DanhMucView> getALl(String dk) {
        List<DanhMucView> listDMV = new ArrayList<>();
        if (this.resDM.getAll(dk) != null) {
            for (DanhMuc danhMuc : this.resDM.getAll(dk)) {
                listDMV.add(new DanhMucView(danhMuc.getId(), danhMuc.getTen()));
            }
        }
        return listDMV;
    }

    @Override
    public int add(DanhMucView q) {
        return this.resDM.add((DanhMuc)CD(q));
    }

    @Override
    public int update(DanhMucView q) {
        return this.resDM.update((DanhMuc)CD(q));
    }

    @Override
    public int delete(String q) {
        return this.resDM.delete(q);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DanhMucView timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(DanhMucView q) {
        return new DanhMuc(q.getId(), q.getTen());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
