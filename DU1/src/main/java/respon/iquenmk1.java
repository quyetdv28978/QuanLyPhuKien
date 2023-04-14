/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package respon;

import domaimodel.NhanVien;
import java.util.List;

/**
 *
 * @author HP
 */
public interface iquenmk1 {
    
    public int getall(NhanVien v,String id);
    public List<NhanVien> getnv(String tk);
    public List<NhanVien> gettk();
    public List<NhanVien> getmail();
    
}
