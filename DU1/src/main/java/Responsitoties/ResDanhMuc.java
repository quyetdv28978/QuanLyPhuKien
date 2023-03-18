/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsitoties;

import DomainModel.DanhMuc;
import Utilities.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResDanhMuc implements IfResponsitoties<DanhMuc>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DanhMuc> getAll(String dk) {
        List<DanhMuc> listDM = DBConnection.selectQueRy("from DanhMuc");
        if (listDM!=null) {
            return listDM;
        }
        return null;
    }

    @Override
    public int add(DanhMuc q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(DanhMuc q) {
        return DBConnection.executeQuery(q, "sdf");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, DanhMuc.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DanhMuc timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
