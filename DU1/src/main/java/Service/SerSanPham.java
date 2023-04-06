/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaimodel.DanhMuc;
import domaimodel.SanPham;
import java.util.ArrayList;
import java.util.List;
import respon.resSanPham;
import viewmodel.DanhMucViewModel;
import viewmodel.SanPhamViewModel;

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
    public List<SanPhamViewModel> getALl(String dk) {
        List<SanPhamViewModel> sp = new ArrayList<>();
        if (this.sanPhamSV.getALLJoin(dk) != null) {
            for (Object[] i : this.sanPhamSV.getALLJoin(dk)) {
                sp.add(new SanPhamViewModel(((SanPham) i[0]).getId(), ((SanPham) i[0]).getMa(), ((SanPham) i[0]).getTenSanPham(),
                        ((SanPham) i[0]).getMauSac(), ((SanPham) i[0]).getNhaSanXuat(),
                        ((SanPham) i[0]).getMoTa(), ((SanPham) i[0]).getGiaNhap(), ((SanPham) i[0]).getGiaBan(),
                        ((SanPham) i[0]).getTrongLuong(),
                        ((SanPham) i[0]).getSoLuong(),
                        ((SanPham) i[0]).getKichThuoc(),
                        ((SanPham) i[0]).getChatLieu(),
                        new DanhMucViewModel(((DanhMuc) i[1]).getId(), ((DanhMuc) i[1]).getDongSP()),
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
    public SanPhamViewModel timOB(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(SanPhamViewModel t) {
//        System.out.println("id dm: " + t.getDm().getId());
        return new SanPham(t.getId(), t.getMa(), t.getTenSanPham(), t.getMauSac(),
                t.getNhaSanXuat(), t.getMoTa(), t.getGiaNhap(), t.getGiaBan(), t.getTrongLuong(),
                t.getSoLuong(),
                t.getKichThuoc(),
                t.getChatLieu(),
                new DanhMuc(t.getDm().getId(), t.getDm().getDongSP()),
                t.getTrangThai(), t.getQL());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<SanPhamViewModel> selectByTen(String ten) {
        List<SanPhamViewModel> sp = new ArrayList<>();
        if (this.sanPhamSV.selectByTen(ten) != null) {
            for (SanPham i : this.sanPhamSV.selectByTen(ten)) {
                sp.add(new SanPhamViewModel(i.getId(), i.getMa(), i.getTenSanPham(), i.getMauSac(),
                        i.getNhaSanXuat(), i.getMoTa(), i.getGiaNhap(), i.getGiaBan(), i.getTrongLuong(),
                        i.getSoLuong(),
                        i.getKichThuoc(),
                        i.getChatLieu(),
                        new DanhMucViewModel(i.getDm().getId(), i.getDm().getDongSP()),
                        i.getTrangThai(), i.getQL()));
            }
            return sp;
        }
        return null;
    }

    public List<SanPhamViewModel> selectByDongSP(String ten) {
        List<SanPhamViewModel> sp = new ArrayList<>();
        if (this.sanPhamSV.selectByDSP(ten) != null) {
            for (SanPham i : this.sanPhamSV.selectByDSP(ten)) {
                sp.add(new SanPhamViewModel(i.getId(), i.getMa(), i.getTenSanPham(), i.getMauSac(),
                        i.getNhaSanXuat(), i.getMoTa(), i.getGiaNhap(), i.getGiaBan(), i.getTrongLuong(),
                        i.getSoLuong(),
                        i.getKichThuoc(),
                        i.getChatLieu(),
                        new DanhMucViewModel(i.getDm().getId(), i.getDm().getDongSP()),
                        i.getTrangThai(), i.getQL()));
            }
            return sp;
        }
        return null;
    }

    public List<SanPhamViewModel> selectByTT(int tt) {
        List<SanPhamViewModel> sp = new ArrayList<>();
        if (this.sanPhamSV.selectByTT(tt) != null) {
            for (SanPham i : this.sanPhamSV.selectByTT(tt)) {
                sp.add(new SanPhamViewModel(i.getId(), i.getMa(), i.getTenSanPham(), i.getMauSac(),
                        i.getNhaSanXuat(), i.getMoTa(), i.getGiaNhap(), i.getGiaBan(), i.getTrongLuong(),
                        i.getSoLuong(),
                        i.getKichThuoc(),
                        i.getChatLieu(),
                        new DanhMucViewModel(i.getDm().getId(), i.getDm().getDongSP()),
                        i.getTrangThai(), i.getQL()));
            }
            return sp;
        }
        return null;
    }

}
