/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaimodel.ChatLieu;
import domaimodel.DanhMuc;
import domaimodel.SanPham;
import java.util.ArrayList;
import java.util.List;
import respon.resSanPham;
import utility.DBConnection;
import viewmodel.ChatLieuViewModel;
import viewmodel.DanhMucViewModel;
import viewmodel.SanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public class SerSanPham implements IServices<SanPhamViewModel> {

    public final resSanPham sanPhamSV;

    public SerSanPham() {
        this.sanPhamSV = new resSanPham();
    }
    public List<SanPhamViewModel> getAllSP123(String dk) {
        List<SanPham> sanPhams = sanPhamSV.getAll123(dk);
        List<SanPhamViewModel> list = new ArrayList<>();
        for (SanPham sanPhamViewModel : sanPhams) {
            list.add(new SanPhamViewModel(sanPhamViewModel.getId(), sanPhamViewModel.getMa()
                    , sanPhamViewModel.getTenSanPham(), sanPhamViewModel.getGiaBan(), sanPhamViewModel.getTrangThai()));
            
        }
        return list;
    }

    @Override
    public int add(SanPhamViewModel t) {
        return this.sanPhamSV.add((SanPham) CD(t));
    }

    @Override
    public int update(SanPhamViewModel t) {
        return this.sanPhamSV.update((SanPham) CD(t));
    }

    @Override
    public int delete(String t) {
        return this.sanPhamSV.delete(t);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public Object CD(SanPhamViewModel t) {
        System.out.println("id dm: " + t.getDm().getId());
        System.out.println(t.getCl().getId());
        System.out.println("ma: " +t.getMa());
        return new SanPham(t.getId(),t.getMa(), t.getTenSanPham(), t.getMauSac(),
                t.getNhaSanXuat(), t.getMoTa(), t.getGiaNhap(), t.getGiaBan(), t.getTrongLuong(),
                t.getSoLuong(),
                new DanhMuc(t.getDm().getId(),t.getDm().getDongSP()),
                new ChatLieu(t.getCl().getId(),t.getCl().getTenChatLieu()),
                t.getTrangThai(), t.getQL());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamViewModel> getALl(String dk) {
         List<SanPhamViewModel> sp = new ArrayList<>();
        if (this.sanPhamSV.getALLJoin(dk) != null) {
            for (Object[] i : this.sanPhamSV.getALLJoin(dk)) {
                sp.add(new SanPhamViewModel(((SanPham) i[0]).getId(), ((SanPham) i[0]).getMa(), ((SanPham) i[0]).getTenSanPham(),
                        ((SanPham) i[0]).getMauSac(), ((SanPham) i[0]).getNhaSanXuat(),
                        ((SanPham) i[0]).getMoTa(), ((SanPham) i[0]).getGiaNhap(), ((SanPham) i[0]).getGiaBan(),
                        ((SanPham) i[0]).getTrongLuong(),
                        ((SanPham) i[0]).getSoLuong(),
                        new DanhMucViewModel(((DanhMuc) i[1]).getId(),((DanhMuc) i[1]).getDongSP()),
                        new ChatLieuViewModel(((ChatLieu) i[2]).getId(),((ChatLieu) i[2]).getTenChatLieu()),
                        ((SanPham) i[0]).getTrangThai(),
                        ((SanPham) i[0]).getQL()));
            }
            return sp;
        }
        return null;
    }

    @Override
    public SanPhamViewModel timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
