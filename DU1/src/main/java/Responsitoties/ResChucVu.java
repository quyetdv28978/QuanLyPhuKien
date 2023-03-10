/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsitoties;

import DomainModel.ChucVu;
import Utilities.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResChucVu implements IfResponsitoties<ChucVu>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChucVu> getAll(String dk) {
        return DBConnection.selectQueRy("from ChucVu");
    }

    @Override
    public int add(ChucVu q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(ChucVu q) {
        return DBConnection.executeQuery(q, "update");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, ChucVu.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChucVu timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
