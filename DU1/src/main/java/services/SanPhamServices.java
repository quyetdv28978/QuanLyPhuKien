/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.ChatLieu;
import domainmodel.DanhMuc;
import domainmodel.SanPham;
import java.util.ArrayList;
import java.util.List;
import responsitories.SanPhamResponsitories;
import Utilities.DBConnection;
import viewmodel.ChatLieuViewModel;
import viewmodel.DanhMucViewModel;
import viewmodel.SanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public class SanPhamServices implements IServices<SanPhamViewModel>{

    public final SanPhamResponsitories sanPham;
    
    public SanPhamServices() {
        this.sanPham=new SanPhamResponsitories();
    }
   
    @Override
    public int add(SanPhamViewModel t) {
        return this.sanPham.add((SanPham)CD(t));
    }

    @Override
    public int update(SanPhamViewModel t) {
        return this.sanPham.update((SanPham)CD(t));
    }

    @Override
    public int delete(String t) {
        return this.sanPham.delete(t);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPhamViewModel timOB(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(SanPhamViewModel t) {
        return new SanPhamViewModel(t.getMa(), t.getTenSanPham(), t.getMauSac(),
                    t.getNhaSanXuat(), t.getNgayTao(), t.getTrangThai(), t.getSoLuong(), 
                    t.getGiaNhap(), t.getGiaBan(), t.getTrongLuong(), t.getQL(),
                    new ChatLieuViewModel(t.getCl().getId(),t.getCl().getMa(),t.getCl().getTenChatLieu()),
                    new DanhMucViewModel(t.getDm().getId(), t.getDm().getDongSP()));
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamViewModel> getALl(String dk) {
           List<SanPhamViewModel> sp=new ArrayList<>();
            if (this.sanPham.getALLJoin(dk) != null) {
            for (Object[] i : this.sanPham.getALLJoin(dk)) {
                sp.add(new SanPhamViewModel(((SanPham) i[0]).getMa(), ((SanPham) i[0]).getTenSanPham(),
                        new DanhMucViewModel(((DanhMuc) i[2]).getId(),((DanhMuc) i[2]).getDongSP()))); 
                       
            }
            return sp;
        }
        return null;
    }

    
    
}
