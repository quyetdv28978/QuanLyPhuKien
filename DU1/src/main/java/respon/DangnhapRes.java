/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.NhanVien;
import java.util.List;
import utility.DBConnection;

/**
 *
 * @author yugip
 */
public class DangnhapRes {

    public List<NhanVien> gettk() {
        return DBConnection.selectQueRy("from NhanVien where taiKhoan is not null and matKhau is not null");
    }
}
