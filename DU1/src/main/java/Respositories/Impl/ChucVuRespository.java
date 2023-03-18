/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Respositories.Impl;

import DomainModels.ChucVu;
import Respositories.IManageChucVuRespository;
import Utilities.DBConnection;
import java.util.List;

/**
 *
 * @author suppe
 */
public class ChucVuRespository implements IManageChucVuRespository {

    @Override
    public List<ChucVu> getListFromDb() {
        return DBConnection.selectQueRy("FROM CHUCVU");
    }

}
