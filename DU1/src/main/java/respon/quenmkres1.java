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
 * @author HP
 */
public class quenmkres1 implements iquenmk1 {

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
        return DBConnection.selectQueRy("from NhanVien");
    }

    @Override
    public List<NhanVien> getmail() {
        return DBConnection.selectQueRy("from NhanVien where email="+"'"+"thanhhandsome2507@gmail.com"+"'");
    }

   

 

  

  
   

}
