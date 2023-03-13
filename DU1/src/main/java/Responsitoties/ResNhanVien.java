/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsitoties;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import Utilities.DBConnection;
import Utilities.jframeCheck;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author yugip
 */
public class ResNhanVien implements IfResponsitoties<NhanVien> {

    private int check;
    private jframeCheck jcheck = new jframeCheck();

    @Override
    public List<NhanVien> getAll(String dk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int add(NhanVien q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(NhanVien q) {
        Session ss = DBConnection.getseFactory().openSession();
        NhanVien nv = DBConnection.getseFactory().openSession().get(NhanVien.class, q.getId());
        nv.setTen(q.getTen());
        nv.setDiachi(q.getDiachi());
        nv.setGioitinh(q.getGioitinh());
        nv.setNgaySinh(q.getNgaySinh());
        nv.setSdt(q.getSdt());
        nv.setCv(q.getCv());
        nv.setUser(q.getUser());
        return DBConnection.executeQuery(nv, "update");

    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, NhanVien.class);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVien timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getALLJoin(String dk) {
        if (DBConnection.selectQueRyJoin("from NhanVien n join n.cv join n.user") != null) {
            return DBConnection.selectQueRyJoin("from NhanVien n join n.cv join n.user");
        }
        return null;
    }

    public List<ChucVu> getALlCV() {
        if (DBConnection.selectQueRy("from ChucVu") != null) {
            return DBConnection.selectQueRy("from ChucVu");
        }
        return null;
    }
}
