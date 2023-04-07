/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.toedter.calendar.JCalendar;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.JframeCheck;
import domaimodel.ChiTietKhuyenMai;
import domaimodel.Embeddedct;
import domaimodel.KhachHang;
import domaimodel.KhuyenMai;
import domaimodel.SanPham;
import java.awt.event.ItemEvent;
//import java.sql.Date;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
//import java.sql.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import respon.BaoHanhRes;
import respon.ChiTietKhuyenMaiResponsitories;
import respon.KhuyenMaiResponsitories;
import respon.resSanPham;
import service.KhuyenMaiServices;
import service.SerSanPham;
import utility.DBConnection;
import viewmodel.KhuyenMaiViewModel;
import viewmodel.SanPhamViewModel;

/**
 *
 * @author DELL
 */
public class BaoHanh extends javax.swing.JFrame {

    /**
     * Creates new form KhuyenMa
     */
    public final ChiTietKhuyenMaiResponsitories chiTietKhuyenMaiResponsitories = new ChiTietKhuyenMaiResponsitories();
    public final BaoHanhRes baoHanhRes = new BaoHanhRes();
    public final KhuyenMaiServices khuyenMaiServices = new KhuyenMaiServices();
    public final resSanPham sanPham = new resSanPham();
    public final SerSanPham serSanPham = new SerSanPham();
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm1 = new DefaultTableModel();
    private final JframeCheck jcheck = new JframeCheck();
    private final List<Object> jText = new ArrayList<>();
    private DefaultComboBoxModel cbbKM = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbSanPham = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbTT = new DefaultComboBoxModel();
    private List<KhuyenMai> list;

    public BaoHanh() {
        initComponents();
        chay();
        
        tbHienThi.setModel(dtm);
        String[] x = {"id", "ma", "maSp", "Ten SP", "tenKH", "sdt", "Trạng thái"};
        dtm.setColumnIdentifiers(x);
//        showDataTB();
//        finTenKM();
        
    }

    public void loadTableBH() {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (domaimodel.BaoHanh object : baoHanhRes.getALlBH()) {
            dtm.addRow(object.toRowBH());
        }

    }

    public void sapXep(List<KhuyenMai> list1) {
        list = list1;
        Collections.sort(list, (KhuyenMai o1, KhuyenMai o2)
                -> o1.getNgayBD().getTime() > o2.getNgayBD().getTime() ? -1 : 1);

        loadTableKM1(list);

    }

    public void loadTableKM1(List<KhuyenMai> lit) {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (domaimodel.KhuyenMai object : lit) {
            dtm.addRow(object.toRow());
        }

    }

    public void finTenKM(List<domaimodel.BaoHanh> list) {

        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        List<domaimodel.BaoHanh> khvm = baoHanhRes.SelectbyMaBH(txtTim.getText());
        for (domaimodel.BaoHanh khvms : khvm) {
//            khvms.set;
            baoHanhRes.update(khvms);
            dtm.addRow(khvms.toRowBH());
        }
    }
    
    public void finTenKM() {

        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        List<domaimodel.BaoHanh> khvm = baoHanhRes.SelectbyMaBH(txtTim.getText());
        for (domaimodel.BaoHanh khvms : khvm) {
//            khvms.set;
            baoHanhRes.update(khvms);
            dtm.addRow(khvms.toRowBH());
        }
    }

    

    public void finGia(List<SanPham> list) {
        dtm = (DefaultTableModel) tbHienThiTTSP.getModel();
        dtm.setRowCount(0);
        List<SanPham> khvm = sanPham.SelectbyGia(Float.parseFloat(txtTu.getText()), Float.parseFloat(txtDen.getText()));
        for (SanPham khvms : khvm) {
            dtm.addRow(khvms.toRowi());
        }
    }

    public void loadComBoKM() {
        List<domaimodel.KhuyenMai> list = khuyenMaiResponsitories.getAllLoad();
        for (domaimodel.KhuyenMai danhMucViewModel : list) {
            cbbKM.addElement(danhMucViewModel);
        }
        cbbTenKM.setModel(cbbKM);
    }

    public void loadTableSanPham() {
        dtm = (DefaultTableModel) tbHienThiSP.getModel();
        dtm.setRowCount(0);
        for (ChiTietKhuyenMai object : chiTietKhuyenMaiResponsitories.getAll(null)) {
            dtm.addRow(object.toRow1());
        }
    }

    public void loadTableSanPham2() {
        dtm = (DefaultTableModel) tbHienThiTTSP.getModel();
        dtm.setRowCount(0);
        for (SanPham object1 : sanPham.getAll123(null)) {
            dtm.addRow(object1.toRowi());
        }
    }

    private void selectbyTen(List<SanPham> sp) {
        dtm = (DefaultTableModel) tbHienThiTTSP.getModel();
        dtm.setRowCount(0);
        List<SanPham> khvm = sanPham.SelectbyTen(txtTimKiemSP.getText());
        for (SanPham khvms : khvm) {
            dtm.addRow(khvms.toRowi());
        }
    }

    public void fillFormBH(int row) throws ParseException {
        domaimodel.BaoHanh ctkmvm = baoHanhRes.getALlBH().get(row);
        txtSDT.setText(ctkmvm.getKh().getSdt());
        lbMaBH.setText(ctkmvm.getMa());
        lbTenSP.setText(ctkmvm.getSp().getTenSanPham());
        txtTenKH.setText(ctkmvm.getKh().getTen());
//        Date ngayBH = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayBH()));
//        Date ngayTra = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayTra()));
//        dateNgayTra.setDate(ngayTra);
//        dateNgayBH.setDate(ngayBH);
        int tt = ctkmvm.getTinhTrang();
        if (cbbTrangThai.getSelectedIndex() == 0) {
            tt = 0;
        } else {
            tt = 1;
        }

    }

    public void fillFormSP(int row) throws ParseException {
        ChiTietKhuyenMai ctkm = chiTietKhuyenMaiResponsitories.getAll(null).get(row);
        cbbKM.setSelectedItem(ctkm.getKm());

    }

    private domaimodel.BaoHanh getData(String dk) {
        if (dk.equalsIgnoreCase("update")) {
            System.out.println("update");
            System.out.println("id" + tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString());
            return new domaimodel.BaoHanh(tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString(),
                    tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 1).toString(),,
                    dateNgayTra.getDate(), dateNgayBH.getDate(), Integer.parseInt(txtSL.getText()),
                    new SanPham(tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 2).toString(), tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 3).toString()),
                    new KhachHang(tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 4).toString(), tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 5).toString()));

//        return new KhuyenMai(jcheck.createID().toString(),
//                jcheck.randomMA(),
//                txtTenKhuyenMai.getText().trim(), Float.parseFloat(txtGiaGiam.getText().trim()),
//                dateNgayTra.getDate(), dateNgayKT.getDate(), rdHetHan.isSelected() == true ? "Hết Hạn" : "Còn Hạn",
//                txtAMoTaKM.getText().trim());
        }
    }

    public ChiTietKhuyenMai getDataCTKM(String dk) {

        ChiTietKhuyenMai chiTietKhuyenMai = null;
        SanPham pham = new SanPham(tbHienThiTTSP.getValueAt(tbHienThiTTSP.getSelectedRow(), 0).
                toString(), tbHienThiTTSP.getValueAt(tbHienThiTTSP.getSelectedRow(), 2).toString());
        if (dk.equalsIgnoreCase("update")) {
            return chiTietKhuyenMai = new ChiTietKhuyenMai(
                    tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 0).toString(),
                    tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 1).toString(),
                    pham,
                    (KhuyenMai) cbbKM.getSelectedItem()
            );
        }

        Session s = DBConnection.getsetFactory().openSession();
        s.saveOrUpdate(pham);
        System.out.println("h" + pham);
        chiTietKhuyenMai = new ChiTietKhuyenMai(
                jcheck.createID().toString(), jcheck.randomMA(),
                pham,
                (KhuyenMai) cbbKM.getSelectedItem());
        s.saveOrUpdate(chiTietKhuyenMai);
        return chiTietKhuyenMai;
    }

    private boolean checkMaKM(String ma) {
        for (int i = 0; i < khuyenMaiResponsitories.getAllLoad().size(); i++) {
            if (khuyenMaiResponsitories.getAllLoad().get(i).getTenKM().equalsIgnoreCase(ma)) {
                if (tbHienThi.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checKM(String tenKM, String tenSP) {
        for (int i = 0; i < chiTietKhuyenMaiResponsitories.getAll(null).size(); i++) {
            if (chiTietKhuyenMaiResponsitories.getAll(null).get(i).getKm().getTenKM().equalsIgnoreCase(tenKM)
                    && chiTietKhuyenMaiResponsitories.getAll(null).get(i).getSp().getTenSanPham().equalsIgnoreCase(tenSP)) {
                if (tbHienThiSP.getSelectedRow() == i) {
                    continue;

                }
                return false;
            }
        }
        return true;
    }

    private boolean checThemKM(Date ngayBD, String ten) {
        for (int i = 0; i < chiTietKhuyenMaiResponsitories.getAll(null).size(); i++) {
            if (chiTietKhuyenMaiResponsitories.getAll(null).get(i).getKm().getNgayBD().compareTo(ngayBD) >= 0
                    && chiTietKhuyenMaiResponsitories.getAll(null).get(i).getKm().getNgayKT().compareTo(ngayBD) <= 0
                    && chiTietKhuyenMaiResponsitories.getAll(null).get(i).getSp().getTenSanPham().equalsIgnoreCase(ten)) {
                if (tbHienThiSP.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checkCalendar() {

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date a1, a2, a3;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            //ép về dd/mm/yyyy 00:00
            a1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").
                    parse(new SimpleDateFormat("dd/MM/yyyy").format(dateNgayKT.getDate()) + " 00:00:00 AM");
            dateNgayKT.setDate(a1);

            // lấy ngày tháng hiện tại
            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1; // vì tháng trong Calendar tính từ 0 đến 11 nên phải cộng thêm 1
            int year = cal.get(Calendar.YEAR);
            String dateString = formatter.format(cal.getTime());

            //ngày bắt đầu 
            Date dateBD = dateNgayTra.getDate();
            String bd = formatter.format(dateBD);

            //ngyaf kết thúc
            Date dateKT = dateNgayKT.getDate();
            String kt = formatter.format(dateKT);

//            //ngày bđ lớn hơn 30 ngày
            Date endDate = new Date(date.getTime() - (30 * 24 * 60 * 60 * 1000));

            //check
            if (dateNgayTra.getDate().before(endDate) == false) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được quá 30 ngày so với hiện tại");
                dateNgayTra.requestFocus();
                return false;
            }
            if (bd.equalsIgnoreCase(dateString)) {
                System.out.println("ok");
            } else {
                if (dateNgayTra.getDate().getTime() < new Date().getTime()) {
                    JOptionPane.showMessageDialog(this, "Ngày bắt đầu không thể là ngày quá khứ");
                    return false;
                }
            }
            if (bd.equalsIgnoreCase(kt)) {
                System.out.println("hi");
            } else if (a1.compareTo(dateNgayTra.getDate()) < 0) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc không được sau ngày bắt đầu");
                dateNgayKT.requestFocus();
                return false;
            }

        } catch (ParseException ex) {
            Logger.getLogger(BaoHanh.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;
    }

    private void clear() {
        jcheck.clearView(jText, tbHienThi);
        dateNgayTra.setDate(null);
        dateNgayKT.setDate(null);

    }

    public void chay() {
        Thread th = new Thread() {
            @Override
            public void run() {
                String txt = lbl_Chay.getText() + " ";
                while (true) {
                    txt = txt.charAt(txt.length() - 1) + txt.substring(0, txt.length() - 1);

                    try {
                        sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbl_Chay.setText(txt);
                }
            }

        };
        th.start();
    }
    private final int a = 12 * 60 * 60 * 1000;

    public void chay1() {

        Thread th = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    new view.BaoHanh().setVisible(true);
                }
            }

        };
        th.start();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup7 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbl_Chay = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbMaBH = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbTenSP = new javax.swing.JLabel();
        cbbSuaChua = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAMoTa = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateNgayTra = new com.toedter.calendar.JDateChooser();
        dateNgayBH = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbhHienThiCTBH = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_Chay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_Chay.setText("Chào Mừng Tất Cả Mọi Người Đến Với Cửa Hàng Trang Sức Phụ Kiện Nữ N1.Chúc Mọi Người Có Một Ngày Vui Vẻ.                                                                                                                                                      ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên Nhân Viên :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Chức Vụ :");

        jButton7.setBackground(new java.awt.Color(255, 255, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Trang chủ");

        jButton8.setBackground(new java.awt.Color(255, 255, 153));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("Bán Hàng");

        jButton9.setBackground(new java.awt.Color(255, 255, 153));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setText("Nhân Viên");

        jButton10.setBackground(new java.awt.Color(255, 255, 153));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setText("Sản Phẩm");

        jButton11.setBackground(new java.awt.Color(255, 255, 153));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setText("Khách Hàng");

        jButton12.setBackground(new java.awt.Color(255, 255, 153));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setText("Khuyến Mãi");

        jButton13.setBackground(new java.awt.Color(255, 255, 153));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setText("Thống Kê");

        jButton14.setBackground(new java.awt.Color(255, 255, 153));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton14.setText("Lịch Sử");

        jButton15.setBackground(new java.awt.Color(255, 255, 153));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setText("Quên Mật Khẩu");

        jButton16.setBackground(new java.awt.Color(255, 255, 153));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton16.setText("Đăng Xuất");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel1)))
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 51));
        jLabel2.setText("Bảo Hành");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin bảo hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)))); // NOI18N

        jLabel5.setText("Tên KH :");

        jLabel6.setText("Mã bảo hành :");

        lbMaBH.setText("jLabel7");

        jLabel8.setText("Tên SP :");

        jLabel9.setText("Sửa chữa : ");

        lbTenSP.setText("jLabel10");

        cbbSuaChua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đội Sửa Chữa 1", "Đội Sửa Chữa 2", "Đội Sửa Chữa 3" }));

        jLabel11.setText("Trạng thái :");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Xử Lí", "Đã Hoàn Thành" }));

        jLabel12.setText("Mô tả :");

        txtAMoTa.setColumns(20);
        txtAMoTa.setRows(5);
        jScrollPane1.setViewportView(txtAMoTa);

        jLabel14.setText("SDT :");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm Mới");

        jLabel3.setText("Ngày BH :");

        jLabel4.setText("Ngày Trả :");

        dateNgayTra.setDateFormatString("dd/MM/yyyy");
        dateNgayTra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayTraPropertyChange(evt);
            }
        });

        dateNgayBH.setDateFormatString("dd/MM/yyyy");
        dateNgayBH.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayBHPropertyChange(evt);
            }
        });

        jLabel7.setText("Số Lượng :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbMaBH)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(lbTenSP)
                            .addComponent(txtSL)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel9)
                                .addComponent(jLabel3)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateNgayBH, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbbSuaChua, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 24, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSua)
                        .addGap(31, 31, 31)
                        .addComponent(btnLamMoi)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbMaBH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbTenSP))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dateNgayBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbSuaChua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(47, 47, 47)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnLamMoi))
                .addGap(44, 44, 44))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã BH", "Mã SP", "Tên SP", "Tên KH", "SDT", "Ngày Nhận", "Trạng Thái"
            }
        ));
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHienThi);

        jLabel15.setText("Tìm Kiếm :");

        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jButton1.setText("SDT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel15)
                .addGap(34, 34, 34)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTim)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbhHienThiCTBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã BH", "Tên KH", "Sdt", "Mã SP", "Tên SP", "Ngày Nhận", "Ngày Trả", "Số lượng", "Trạng Thái"
            }
        ));
        jScrollPane3.setViewportView(tbhHienThiCTBH);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(720, 720, 720)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1684, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Chay)
                .addContainerGap(1679, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Chay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String a = txtTim.getText();
        List<domaimodel.BaoHanh> list1 = baoHanhRes.SelectbyMaBH(a);
        finTenKM(list1);
        System.out.println("list" + list1.get(0).getMa());

    }//GEN-LAST:event_btnTimActionPerformed

    private void dateNgayTraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayTraPropertyChange
        //        System.out.println("aaaaaaaa" + dateNgayBD.getDate());
    }//GEN-LAST:event_dateNgayTraPropertyChange

    private void dateNgayBHPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayBHPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNgayBHPropertyChange

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        int row = tbHienThi.getSelectedRow();
        try {
            fillFormBH(row);
        } catch (ParseException ex) {
            Logger.getLogger(BaoHanh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (jcheck.checkClcick(tbHienThi, this) == false) {
            return;
        } else {

            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?", "Update", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                baoHanhRes.update(getData("update"));
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");

            }
        }
        loadTableBH();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void showDataTB(List<domaimodel.BaoHanh> sdt) {
        dtm.setRowCount(0);
        for (domaimodel.BaoHanh i : this.baoHanhRes.getAll("")) {
            dtm.addRow(i.toRowBH());
        }
    }

    private void showDataTB() {
        
        dtm.setRowCount(0);
        for (domaimodel.BaoHanh i : this.baoHanhRes.getAll("")) {
            dtm.addRow(i.toRowBH());
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        String sdt = txtTim.getText();
//        List<domaimodel.BaoHanh> sp = this.baoHanhRes.selectByTen(sdt);
//        showDataTB(sp);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaoHanh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTim;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JComboBox<String> cbbSuaChua;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private com.toedter.calendar.JDateChooser dateNgayBH;
    private com.toedter.calendar.JDateChooser dateNgayTra;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbMaBH;
    private javax.swing.JLabel lbTenSP;
    private javax.swing.JLabel lbl_Chay;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbhHienThiCTBH;
    private javax.swing.JTextArea txtAMoTa;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
