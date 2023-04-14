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
public class resquenmk implements iquenmk{
    @Override
    public int getall(NhanVien v, String id) {
        return DBConnection.executeQuery(v, id);
    }

    @Override
    public List<NhanVien> getnv(String tk) {
        return DBConnection.selectQueRy("from NhanVien where taiKhoan="+"'"+tk+"'");
    }

    @Override
    public List<NhanVien> gettk() {
        return DBConnection.selectQueRy("from NhanVien ");
    }
}
