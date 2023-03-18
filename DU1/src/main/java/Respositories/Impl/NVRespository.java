/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Respositories.Impl;

import DomainModels.CV;
import DomainModels.NV;
import Respositories.IfResponsitoties;
import Utilities.DBConnection;
import Utilities.jframeCheck;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author yugip
 */
public class NVRespository implements IfResponsitoties<NV> {

    private int check;
    private jframeCheck jcheck = new jframeCheck();

    @Override
    public List<NV> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int add(NV q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(NV q) {
        Session ss = DBConnection.getseFactory().openSession();
        NV nv = DBConnection.getseFactory().openSession().get(NV.class, q.getId());
        nv.setTen(q.getTen());
        nv.setDiachi(q.getDiachi());
        nv.setGioitinh(q.getGioitinh());
        nv.setNgaySinh(q.getNgaySinh());
        nv.setSdt(q.getSdt());
        nv.setCv(q.getCv());
        return DBConnection.executeQuery(nv, "update");

    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, NV.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NV timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getALLJoin(String dk) {
        if (DBConnection.selectQueRyJoin("from NhanVien n join n.cv") != null) {
            return DBConnection.selectQueRyJoin("from NhanVien n join n.cv");
        }
        return null;
    }

    public List<CV> getALlCV() {
        if (DBConnection.selectQueRy("from ChucVu") != null) {
            return DBConnection.selectQueRy("from ChucVu");
        }
        return null;
    }
}
