/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import ViewModel.NhanVienViewModel;
import java.util.List;

/**
 *
 * @author suppe
 */
public interface IManageNhanVienService {

    List<NhanVienViewModel> getListFromDb();

    int them(String maNhanVien);

    int capNhat(String maNhanVien);

    int xoa(String maNhanVien);
}
