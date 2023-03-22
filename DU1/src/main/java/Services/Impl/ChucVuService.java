/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.ChucVu;
import Respositories.IManageChucVuRespository;
import Respositories.Impl.ChucVuRespository;
import Services.IManageChucVuService;
import ViewModels.ChucVuViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suppe
 */
public class ChucVuService implements IManageChucVuService {

    public final ChucVuRespository chucVuRespository;

    public ChucVuService() {
        chucVuRespository = new ChucVuRespository();
    }

    @Override
    public List<ChucVuViewModel> getListFromDb() {
        List<ChucVuViewModel> list = new ArrayList<>();
        for (ChucVu chucVu : chucVuRespository.getListFromDb()) {
            list.add(new ChucVuViewModel(
                    chucVu.getIdChucVu(),
                    chucVu.getMaChucVu(),
                    chucVu.getTenChucVu()));
        };
        return list;
    }

    public List<ChucVuViewModel> getAllLoad() {
        List<ChucVuViewModel> chucVuViewModel = new ArrayList<>();
        for (ChucVu chucVu : chucVuRespository.getAllLoad()) {
            chucVuViewModel.add(new ChucVuViewModel(
                    chucVu.getIdChucVu(),
                    chucVu.getMaChucVu(),
                    chucVu.getTenChucVu()));
        }
        return chucVuViewModel;
    }
}
