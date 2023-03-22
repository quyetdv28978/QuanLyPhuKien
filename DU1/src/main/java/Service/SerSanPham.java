/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModel.ChatLieu;
import DomainModel.DanhMuc;
import DomainModel.SanPham;
import Responsitoties.ResSanPham;
import ViewModel.ChatLieuView;
import ViewModel.DanhMucView;
import ViewModel.SanPhamView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yugip
 */
public class SerSanPham implements IFService<SanPhamView> {
    
    private final ResSanPham resSP = new ResSanPham();
    
    @Override
    public List<ViewModel.SanPhamView> getALl(String dk) {
        List l = resSP.getALLJoin(dk);
        List<SanPhamView> listSPV = new ArrayList<>();
        if (l != null) {
            for (Object[] objects : this.resSP.getALLJoin(dk)) {
                listSPV.add(new SanPhamView(((SanPham) objects[0]).getId(), ((SanPham) objects[0]).getMa(), ((SanPham) objects[0]).getTensanpham(),
                        ((SanPham) objects[0]).getMausac(), ((SanPham) objects[0]).getNhasanxuat(),
                        ((SanPham) objects[0]).getQL(), ((SanPham) objects[0]).getMota(),
                        ((SanPham) objects[0]).getGiaban(), ((SanPham) objects[0]).getGianhap(),
                        ((SanPham) objects[0]).getTrongluong(), ((SanPham) objects[0]).getNgayTao(),
                        ((SanPham) objects[0]).getSoluong(), ((SanPham) objects[0]).getTrangthai(),
                        new DanhMucView(((DanhMuc) objects[1]).getTen()), new ChatLieuView(((ChatLieu) objects[2]).getId(),((ChatLieu) objects[2]).getTen(), ((ChatLieu) objects[2]).getMa())));
            }
        }
        return listSPV;
    }
    
    @Override
    public int add(ViewModel.SanPhamView q) {
        return this.resSP.add((SanPham)CD(q));
    }
    
    @Override
    public int update(ViewModel.SanPhamView q) {
        return this.resSP.update((SanPham)CD(q));
    }
    
    @Override
    public int delete(String q) {
        return this.resSP.delete(q);
    }
    
    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    @Override
    public Object CD(ViewModel.SanPhamView q) {
        return new SanPham(q.getId(), q.getMa(), q.getTensanpham(), q.getMausac(), q.getNhasanxuat(),
                q.getQL(), q.getMota(),
                q.getGiaban(), q.getGianhap(),
                q.getTrongluong(), q.getNgayTao(), q.getSoluong(),
                q.getTrangthai(), new DanhMuc(q.getDm().getId(),q.getDm().getTen()), new ChatLieu(q.getCl().getId(),q.getCl().getTen(), q.getCl().getMa()));
    }
    
    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPhamView timOB(String id) {
        SanPham s = this.resSP.timObject(id);
        return new SanPhamView
        (s.getId(), s.getMa(), s.getTensanpham());
    }
}
