package service;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package service;
//
//import utility.DBConnection;
//import domaimodel.ChiTietKhuyenMai;
//import domaimodel.DanhMuc;
//import domaimodel.KhuyenMai;
//import domaimodel.SanPham;
//import java.util.ArrayList;
//import java.util.List;
//import respon.ChiTietKhuyenMaiResponsitories;
//import viewmodel.ChiTietKhuyenMaiViewModel;
//import viewmodel.DanhMucViewModel;
//import viewmodel.KhuyenMaiViewModel;
//import viewmodel.SanPhamViewModel;
//
///**
// *
// * @author Admin
// */
//public class ChiTietKhuyenMaiServices implements IServices<ChiTietKhuyenMaiViewModel> {
//
//    public final ChiTietKhuyenMaiResponsitories sn;
//
//    public ChiTietKhuyenMaiServices() {
//        this.sn = new ChiTietKhuyenMaiResponsitories();
//    }
//
//    public List<Object[]> getALLJoin(String dk) {
//        if (DBConnection.selectQueRyJoin("from ChiTietKhuyenMai ctkm join ctkm.sp join ctkm.km") != null) {
//            return DBConnection.selectQueRyJoin("from ChiTietKhuyenMai ctkm join ctkm.sp join ctkm.km");
//        }
//        return null;
//    }
//
//    @Override
//    public List<ChiTietKhuyenMaiViewModel> getALl(String dk) {
//        List<ChiTietKhuyenMaiViewModel> listNVV = new ArrayList<>();
//        if (this.sn.getALLJoin(dk) != null) {
//            for (Object[] objects : sn.getALLJoin(dk)) {
//                listNVV.add(new ChiTietKhuyenMaiViewModel(((ChiTietKhuyenMai) objects[0]).getId(),
//                        ((ChiTietKhuyenMai) objects[0]).getMa(),
//                        ((ChiTietKhuyenMai) objects[0]).getGiaGiam(),
//                        new SanPhamViewModel(((SanPham) objects[1]).getMa(),
//                                ((SanPham) objects[1]).getTenSanPham(),
//                                new DanhMucViewModel(((SanPham) objects[1]).
//                                        getDm().getDongSP())),
//                        new KhuyenMaiViewModel(((KhuyenMai) objects[2]).getMa(),
//                                ((KhuyenMai) objects[2]).getTenKM(),
//                                ((KhuyenMai) objects[2]).getNgayBD(),
//                                ((KhuyenMai) objects[2]).getNgayKT(),
//                                ((KhuyenMai) objects[2]).getMoTa())));
//                return listNVV;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public int add(ChiTietKhuyenMaiViewModel q
//    ) {
//        return DBConnection.executeQuery(q, null);
//    }
//
//    @Override
//    public int update(ChiTietKhuyenMaiViewModel q
//    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int delete(String q
//    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public String timID(String ma
//    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ChiTietKhuyenMaiViewModel timOB(String id
//    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Object CD(ChiTietKhuyenMaiViewModel q
//    ) {
//
//        return new ChiTietKhuyenMaiViewModel(q.getId(), q.getMa(), q.getGiaGiam(),
//                new SanPhamViewModel(q.getSpvm().getMa(), q.getSpvm().getTenSanPham(),
//                        q.getSpvm().getDm()), new KhuyenMaiViewModel(q.getKmvm().getMa(),
//                        q.getKmvm().getTenKM(), q.getKmvm().getNgayBD(),
//                        q.getKmvm().getNgayKT(), q.getKmvm().getMoTa()));
//    }
//
//    @Override
//    public boolean checkTrung(String ma
//    ) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//     public List<KhuyenMaiViewModel> getAllKM(){
//        if (sn.getALlKM()!= null) {
//            List<KhuyenMaiViewModel> listCVV = new ArrayList<>();
//            for (KhuyenMai chucVu : sn.getALlKM()) {
//                listCVV.add(new KhuyenMaiViewModel(chucVu.getId(), chucVu.getMa(), chucVu.getTenKM(), chucVu.getNgayBD(), chucVu.getNgayKT(), chucVu.getMoTa()));
//            }
//            return listCVV;
//        }
//        return null;
//    }
//
//}
