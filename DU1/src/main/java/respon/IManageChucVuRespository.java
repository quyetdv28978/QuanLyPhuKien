/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package respon;
import domaimodel.ChucVu;
import java.util.List;

/**
 *
 * @author suppe
 */
public interface IManageChucVuRespository {

    List<ChucVu> getListFromDb();
    
}
