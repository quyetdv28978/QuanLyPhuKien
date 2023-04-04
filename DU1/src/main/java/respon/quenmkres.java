/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.NhanVien;
import java.util.ArrayList;
import java.util.List;
import utility.DBConnection11;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
public class quenmkres implements iquenmk {

    @Override
    public int suamk(String mk) {
        String sql = "update nhanvien set matkhau=? ";
        return DBConnection11.ExcuteDungna(sql, mk);
    }

    @Override
    public List<NhanVien> getall() {

        List<NhanVien> ar = new ArrayList<>();
        try {
            String sql = "select * from nhanvien";
            ResultSet rs = DBConnection11.openDbConnection().prepareStatement(sql).executeQuery();
        while(rs.next()){
            ar.add(new NhanVien(rs.getString("taikhoan"), rs.getString("matkhau")));
        }
       
    }
        catch(Exception e){
            e.printStackTrace();
        }
        return ar;
        }

}
