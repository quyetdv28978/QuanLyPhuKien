/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.ChucVu;
import DomainModels.NhanVien;
import Respositories.Impl.NhanVienRespository;
import Services.IManageNhanVienService;
import ViewModels.ChucVuViewModel;
import ViewModels.NhanVienViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suppe
 */
public class NhanVienService implements IManageNhanVienService {

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
                        ((NhanVien) nhanVien[0]).getTaiKhoan(),
                        ((NhanVien) nhanVien[0]).getTenNhanVien(),
                        ((NhanVien) nhanVien[0]).getCmnd(),
                        ((NhanVien) nhanVien[0]).getGioiTinh(),
                        ((NhanVien) nhanVien[0]).getNgaySinh(),
                        ((NhanVien) nhanVien[0]).getDiaChi(),
                        ((NhanVien) nhanVien[0]).getSdt(),
                        ((NhanVien) nhanVien[0]).getEmail(),
                        ((NhanVien) nhanVien[0]).getAnh(),
                        ((NhanVien) nhanVien[0]).getNgayTao(),
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
                nhanVienViewModel.getTaiKhoan(),
                nhanVienViewModel.getTenNhanVien(),
                nhanVienViewModel.getCmnd(),
                nhanVienViewModel.getGioiTinh(),
                nhanVienViewModel.getNgaySinh(),
                nhanVienViewModel.getDiaChi(),
                nhanVienViewModel.getSdt(),
                nhanVienViewModel.getEmail(),
                nhanVienViewModel.getAnh(),
                nhanVienViewModel.getNgayTao(),
                new ChucVu(
                        nhanVienViewModel.getChucVuViewModel().getIdChucVu(),
                        nhanVienViewModel.getChucVuViewModel().getMaChucVu(),
                        nhanVienViewModel.getChucVuViewModel().getTenChucVu()
                ),
                nhanVienViewModel.getTrangThai()
        );
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

}
