/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModel.ChucVu;
import Responsitoties.ResChucVu;
import ViewModel.ChucVuView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yugip
 */
public class SerChucVU implements IFService<ChucVuView>{
private final ResChucVu resCV = new ResChucVu();
    @Override
    public List<ChucVuView> getALl(String dk) {
        List<ChucVuView> listCVV = new ArrayList<>();
        for (ChucVu chucVuView : this.resCV.getAll(dk)) {
            listCVV.add(new ChucVuView(chucVuView.getId(), chucVuView.getMa(), chucVuView.getTen()));
        }
        return listCVV;
    }

    @Override
    public int add(ChucVuView q) {
        return this.resCV.add((ChucVu)CD(q));
    }

    @Override
    public int update(ChucVuView q) {
        return this.resCV.update((ChucVu)CD(q));
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
    public ChucVuView timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(ChucVuView q) {
        return new ChucVu(q.getId(), q.getMa(), q.getTen());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
