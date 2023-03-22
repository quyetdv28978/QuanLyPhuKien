/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import ViewModels.NhanVienViewModel;
import java.util.List;

/**
 *
 * @author suppe
 */
public interface IManageNhanVienService {

    List<NhanVienViewModel> getListNhanVienFromDb(String dieuKien);
    
    Object CD (NhanVienViewModel nhanVienViewModel);

    int them(NhanVienViewModel nhanNhanVienViewModel);

    int capNhat(NhanVienViewModel nhanVienViewModel);

    int xoa(String maNhanVien);
}
