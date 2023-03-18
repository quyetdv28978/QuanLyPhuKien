/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Respositories.Impl;

import DomainModels.CV;
import Respositories.IfResponsitoties;
import Utilities.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class CVRespository implements IfResponsitoties<CV>{

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CV> getAll(String dk) {
        return DBConnection.selectQueRy("from ChucVu");
    }

    @Override
    public int add(CV q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(CV q) {
        return DBConnection.executeQuery(q, "update");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, CV.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CV timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
