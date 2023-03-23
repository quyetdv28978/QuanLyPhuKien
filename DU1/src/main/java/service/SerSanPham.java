/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaiModel.ChatLieu;
import domaiModel.DanhMuc;
import domaiModel.SanPham;
import java.util.ArrayList;
import java.util.List;
import respository.resSanPham;
import utility.DBConnection;
import viewModel.ChatLieuViewModel;
import viewModel.DanhMucViewModel;
import viewModel.SanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public class SerSanPham implements Interface<SanPhamViewModel> {

    public final resSanPham sanPhamSV;

    public SerSanPham() {
        this.sanPhamSV = new resSanPham();
    }

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamViewModel> getAll(String dk) {
        List<SanPhamViewModel> sp = new ArrayList<>();
        if (this.sanPhamSV.getALLJoin(dk) != null) {
            for (Object[] i : this.sanPhamSV.getALLJoin(dk)) {
                sp.add(new SanPhamViewModel(((SanPham) i[0]).getId(), ((SanPham) i[0]).getMa(), ((SanPham) i[0]).getTenSanPham(),
                        ((SanPham) i[0]).getMauSac(), ((SanPham) i[0]).getNhaSanXuat(),
                        ((SanPham) i[0]).getMoTa(), ((SanPham) i[0]).getGiaNhap(), ((SanPham) i[0]).getGiaBan(),
                        ((SanPham) i[0]).getTrongLuong(),
                        ((SanPham) i[0]).getSoLuong(),
                        new DanhMucViewModel(((DanhMuc) i[1]).getId(),((DanhMuc) i[1]).getDongSP()),
                        new ChatLieuViewModel(((ChatLieu) i[2]).getId(),((ChatLieu) i[2]).getMa(),((ChatLieu) i[2]).getTenChatLieu()),
                        ((SanPham) i[0]).getTrangThai(),
                        ((SanPham) i[0]).getQL()));
            }
            return sp;
        }
        return null;
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
    public SanPhamViewModel timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(SanPhamViewModel t) {
        System.out.println("id dm: " + t.getDm().getId());
        System.out.println(t.getCl().getId());
        return new SanPham(t.getId(),t.getMa(), t.getTenSanPham(), t.getMauSac(),
                t.getNhaSanXuat(), t.getMoTa(), t.getGiaNhap(), t.getGiaBan(), t.getTrongLuong(),
                t.getSoLuong(),
                new DanhMuc(t.getDm().getId(),t.getDm().getDongSP()),
                new ChatLieu(t.getCl().getId(),t.getCl().getMa(),t.getCl().getTenChatLieu()),
                t.getTrangThai(), t.getQL());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
