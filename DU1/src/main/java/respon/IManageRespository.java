/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Respository;

import java.util.List;
import DomainModel.ChucVu;
import DomainModel.NhanVien;

/**
 *
 * @author suppe
 */
public interface IManageRespository {

    List<ChucVu> getListChucVuFromDb();

    List<Object[]> getJoin(String dieuKien);

    List<NhanVien> getListNhanVienFromDb();

    int them(NhanVien maNhanVien);

    int capNhat(NhanVien maNhanVien);

    int xoa(String maNhanVien);
}
