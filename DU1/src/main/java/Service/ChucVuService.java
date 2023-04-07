/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaimodel.ChucVu;
import respon.ChucVuRespository;
import viewmodel.ChucVuViewModel;
import viewmodel.NhanVienViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suppe
 */
public class ChucVuService implements IManageService {

    public final ChucVuRespository chucVuRespository;

    public ChucVuService() {
        chucVuRespository = new ChucVuRespository();
    }

    @Override
    public List<ChucVuViewModel> getListChucVuFromDb() {
        List<ChucVuViewModel> list = new ArrayList<>();
        for (ChucVu chucVu : chucVuRespository.getListChucVuFromDb()) {
            list.add(new ChucVuViewModel(
                    chucVu.getIdChucVu(),
                    chucVu.getMaChucVu(),
                    chucVu.getTenChucVu()));
        };
        return list;
    }

    public List<ChucVuViewModel> getAllLoad() {
        List<ChucVuViewModel> chucVuViewModel = new ArrayList<>();
        if (chucVuRespository.getAllLoad() != null) {
        for (ChucVu chucVu : chucVuRespository.getAllLoad()) {
            chucVuViewModel.add(new ChucVuViewModel(
                    chucVu.getIdChucVu(),
                    chucVu.getMaChucVu(),
                    chucVu.getTenChucVu())
            );
        }    
        }
        return chucVuViewModel;
    }


    @Override
    public List<NhanVienViewModel> getListNhanVienFromDb(String dieuKien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(NhanVienViewModel nhanVienViewModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int them(NhanVienViewModel nhanNhanVienViewModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int capNhat(NhanVienViewModel nhanVienViewModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String maNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
