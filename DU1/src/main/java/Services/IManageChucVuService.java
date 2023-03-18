/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.ChucVu;
import java.util.List;

/**
 *
 * @author suppe
 */
public interface IManageChucVuService {

    List<ChucVu> getListFromDb();

}
