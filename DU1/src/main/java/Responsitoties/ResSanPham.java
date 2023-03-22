/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsitoties;

import DomainModel.SanPham;
import Utilities.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResSanPham implements IfResponsitoties<SanPham> {

    @Override
    public List<Object[]> getALLJoin(String dk) {
        return DBConnection.selectQueRyJoin("from SanPham s join s.dm join s.cl " + dk);
    }

    @Override
    public List<SanPham> getAll(String dk) {
        return DBConnection.selectQueRy("from SanPham s " + dk);
    }

    @Override
    public int add(SanPham q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(SanPham q) {
        return DBConnection.executeQuery(q, "gi");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, SanPham.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham timObject(String dk) {
        return getAll(dk).get(0);
    }

}
