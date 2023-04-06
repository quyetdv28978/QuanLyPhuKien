/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaimodel.ChucVu;
import domaimodel.NhanVien;
import respon.NhanVienRespository;
import viewmodel.ChucVuViewModel;
import viewmodel.NhanVienViewModel;
import java.util.ArrayList;
import java.util.List;
import service.IManageService;

/**
 *
 * @author suppe
 */
public class NhanVienService implements IManageService {

    public final NhanVienRespository nhanVienRespository;

    public NhanVienService() {
        nhanVienRespository = new NhanVienRespository();
    }

    @Override
    public List<NhanVienViewModel> getListNhanVienFromDb(String dieuKien) {

        List<NhanVienViewModel> list = new ArrayList<>();

        if (nhanVienRespository.getAllJoin(dieuKien) != null) {

            for (Object[] nhanVien : nhanVienRespository.getAllJoin(dieuKien)) {

                list.add(new NhanVienViewModel(
                        ((NhanVien) nhanVien[0]).getIdNhanVien(),
                        ((NhanVien) nhanVien[0]).getMaNhanVien(),
                        ((NhanVien) nhanVien[0]).getTenNhanVien(),
                        ((NhanVien) nhanVien[0]).getCmnd(),
                        ((NhanVien) nhanVien[0]).getGioiTinh(),
                        ((NhanVien) nhanVien[0]).getNgaySinh(),
                        ((NhanVien) nhanVien[0]).getDiaChi(),
                        ((NhanVien) nhanVien[0]).getSdt(),
                        ((NhanVien) nhanVien[0]).getEmail(),
                        ((NhanVien) nhanVien[0]).getAnh(),
                        ((NhanVien) nhanVien[0]).getTenTaiKhoan(),
                        ((NhanVien) nhanVien[0]).getMatKhau(),
                        new ChucVuViewModel(((ChucVu) nhanVien[1]).getIdChucVu(),
                                ((ChucVu) nhanVien[1]).getMaChucVu(),
                                ((ChucVu) nhanVien[1]).getTenChucVu()),
                        ((NhanVien) nhanVien[0]).getTrangThai()
                ));
            }
            return list;
        }
        return null;
    }

    @Override
    public Object CD(NhanVienViewModel nhanVienViewModel) {

        return new NhanVien(
                nhanVienViewModel.getIdNhanVien(),
                nhanVienViewModel.getMaNhanVien(),
                nhanVienViewModel.getTenNhanVien(),
                nhanVienViewModel.getCmnd(),
                nhanVienViewModel.getGioiTinh(),
                nhanVienViewModel.getNgaySinh(),
                nhanVienViewModel.getDiaChi(),
                nhanVienViewModel.getSdt(),
                nhanVienViewModel.getEmail(),
                nhanVienViewModel.getAnh(),
                nhanVienViewModel.getTenTaiKhoan(),
                nhanVienViewModel.getMatKhau(),
                new ChucVu(
                        nhanVienViewModel.getChucVuViewModel().getIdChucVu(),
                        nhanVienViewModel.getChucVuViewModel().getMaChucVu(),
                        nhanVienViewModel.getChucVuViewModel().getTenChucVu()
                ),
                nhanVienViewModel.getTrangThai()
        );
    }

    public List<NhanVien> timKiemTheoMa(String ma) {
        return nhanVienRespository.timKiemTheoMa(ma);
    }

    @Override
    public int them(NhanVienViewModel nhanVienViewModel) {

        return nhanVienRespository.them((NhanVien) CD(nhanVienViewModel));
    }

    @Override
    public int capNhat(NhanVienViewModel nhanVienViewModel) {
        return nhanVienRespository.capNhat((NhanVien) CD(nhanVienViewModel));
    }

    @Override
    public int xoa(String maNhanVien) {
        return nhanVienRespository.xoa(maNhanVien);
    }

    @Override
    public List<ChucVuViewModel> getListChucVuFromDb() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
