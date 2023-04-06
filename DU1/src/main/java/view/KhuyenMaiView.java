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
import domaimodel.KhuyenMai;
import domaimodel.NhanVien;
import domaimodel.SanPham;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
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
public class KhuyenMaiView extends javax.swing.JFrame {

    /**
     * Creates new form KhuyenMa
     */
    public final ChiTietKhuyenMaiResponsitories chiTietKhuyenMaiResponsitories = new ChiTietKhuyenMaiResponsitories();
    public final KhuyenMaiResponsitories khuyenMaiResponsitories = new KhuyenMaiResponsitories();
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
    private List<ChiTietKhuyenMai> listct;

    private void dangNhapNV(NhanVien nv) {
        Image image = new ImageIcon(nv.getAnh()).getImage().getScaledInstance(anhNV.getWidth(), anhNV.getHeight(), 0);
        anhNV.setIcon(new ImageIcon(image));
        tenNV.setText(nv.getTenNhanVien());
        CV.setText(nv.getChucVu().getTenChucVu());
    }

    public KhuyenMaiView() {
        initComponents();
        chay();
        jText.add(txtAMoTaKM);
        jText.add(txtGiaGiam);
//        jText.add(txtMaKhuyenMai);
        jText.add(txtTenKhuyenMai);
        jText.add(dateNgayBD);
        jText.add(dateNgayKT);
        jText.add(cbbTenKM);
//        jText.add(cbbTenSP);
//        jText.add(txtMoTaSP);
        jText.add(rdConHan);
        jText.add(rdHetHan);

        dangNhapNV(DangNhap.nv);
        loadTableKM();
        loadComBoKM();
        loadTableSanPham();
        loadTableSanPham2();
        sapXep(khuyenMaiResponsitories.getAllLoad());
//        chay1();

        getIconMenu(btnbanhang, "icon\\Images\\Basket.png");
        getIconMenu(btnnhanvien, "icon\\Images\\User.png");
        getIconMenu(btnsanpham, "icon\\Images\\Label.png");
        getIconMenu(btnkhachhang, "icon\\Images\\User group.png");
        getIconMenu(btnkhuyenmai, "icon\\Images\\Free.png");
        getIconMenu(btnthongke, "icon\\Images\\Diagram.png");
        getIconMenu(btnlichsu, "icon\\Images\\Clock.png");
        getIconMenu(btnDangXuat, "icon\\Images\\Open door.png");
    }

    public void getIconMenu(JButton bt, String dd) {
        Image image = new ImageIcon(dd).getImage().getScaledInstance(24, 24, 0);
        bt.setIcon(new ImageIcon(image));
    }

    public void loadTableKM() {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (domaimodel.KhuyenMai object : khuyenMaiResponsitories.getAllLoad()) {
            dtm.addRow(object.toRow());
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

    public void finTT(String a) {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (domaimodel.KhuyenMai object : khuyenMaiResponsitories.getAllLoad()) {
            dtm.addRow(object.toRow());
        }

    }

    public void finTenKM(List<domaimodel.KhuyenMai> list) {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        List<domaimodel.KhuyenMai> khvm = khuyenMaiResponsitories.SelectbyName(txtTimKM.getText());
        for (domaimodel.KhuyenMai khvms : khvm) {
            dtm.addRow(khvms.toRow());
        }
    }

    public void finTenSP(List<ChiTietKhuyenMai> list) {
        dtm = (DefaultTableModel) tbHienThiSP.getModel();
        dtm.setRowCount(0);
        List<ChiTietKhuyenMai> khvm = chiTietKhuyenMaiResponsitories.SelectbyNameSP(txtTimSP.getText());
        for (ChiTietKhuyenMai khvms : khvm) {
            dtm.addRow(khvms.toRow1());
        }
    }

    public void finTrangThai(List<KhuyenMai> list) {

        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);

        List<KhuyenMai> khvm = khuyenMaiResponsitories.SelectbyTrangThai((String) cbbLocTrangThaiKM.getSelectedItem());
        for (KhuyenMai khvms : khvm) {
            if (khvms.getTT().equalsIgnoreCase("Hết Hạn")) {
                khvms.setTrangThai("Hết Hạn");
                khuyenMaiResponsitories.update(khvms);
//            return;
            }
            dtm.addRow(khvms.toRow());
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
        if (chiTietKhuyenMaiResponsitories.getAll(null) != null) {
            for (ChiTietKhuyenMai object : chiTietKhuyenMaiResponsitories.getAll(null)) {
                dtm.addRow(object.toRow1());
            }
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

    public void fillFormKM(int row) throws ParseException {
        domaimodel.KhuyenMai ctkmvm = khuyenMaiResponsitories.getAllLoad().get(row);
        txtTenKhuyenMai.setText(ctkmvm.getTenKM());
        String gia = Float.toString(ctkmvm.getGiaGiam());
        txtGiaGiam.setText(gia);
        txtAMoTaKM.setText(ctkmvm.getMoTa());
        Date ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayBD()));
        Date ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayKT()));
        dateNgayBD.setDate(ngayBD);
        dateNgayKT.setDate(ngayKT);
        String gt = ctkmvm.getTT();
        if (gt.equalsIgnoreCase("Hết Hạn")) {
            rdHetHan.setSelected(true);
        } else {
            rdConHan.setSelected(true);
        }

    }

    public void fillFormSP(int row) throws ParseException {
        ChiTietKhuyenMai ctkm = chiTietKhuyenMaiResponsitories.getAll(null).get(row);
        cbbKM.setSelectedItem(ctkm.getKm());

    }

    private KhuyenMai getData(String dk) {
        if (dk.equalsIgnoreCase("update")) {
            System.out.println("update");
            System.out.println("id" + tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString());
            return new domaimodel.KhuyenMai(tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString(),
                    tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 1).toString(),
                    txtTenKhuyenMai.getText().trim(), Float.parseFloat(txtGiaGiam.getText().trim()),
                    dateNgayBD.getDate(), dateNgayKT.getDate(), rdHetHan.isSelected() == true ? "Hết Hạn" : "Còn Hạn", txtAMoTaKM.getText().trim());
        }

        return new KhuyenMai(jcheck.createID().toString(),
                jcheck.randomMA(),
                txtTenKhuyenMai.getText().trim(), Float.parseFloat(txtGiaGiam.getText().trim()),
                dateNgayBD.getDate(), dateNgayKT.getDate(), rdHetHan.isSelected() == true ? "Hết Hạn" : "Còn Hạn",
                txtAMoTaKM.getText().trim());
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
            Date dateBD = dateNgayBD.getDate();
            String bd = formatter.format(dateBD);

            //ngyaf kết thúc
            Date dateKT = dateNgayKT.getDate();
            String kt = formatter.format(dateKT);

//            //ngày bđ lớn hơn 30 ngày
            Date endDate = new Date(date.getTime() - (30 * 24 * 60 * 60 * 1000));

            //check
            if (dateNgayBD.getDate().before(endDate) == false) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được quá 30 ngày so với hiện tại");
                dateNgayBD.requestFocus();
                return false;
            }
            if (bd.equalsIgnoreCase(dateString)) {
                System.out.println("ok");
            } else {
                if (dateNgayBD.getDate().getTime() < new Date().getTime()) {
                    JOptionPane.showMessageDialog(this, "Ngày bắt đầu không thể là ngày quá khứ");
                    return false;
                }
            }
            if (bd.equalsIgnoreCase(kt)) {
                System.out.println("hi");
            } else if (a1.compareTo(dateNgayBD.getDate()) < 0) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc không được sau ngày bắt đầu");
                dateNgayKT.requestFocus();
                return false;
            }

        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiView.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;
    }

    private void clear() {
        jcheck.clearView(jText, tbHienThi);
        dateNgayBD.setDate(null);
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

                    new view.KhuyenMaiView().setVisible(true);
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
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtTimKM = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbbLocTrangThaiKM = new javax.swing.JComboBox<>();
        jPanel36 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        txtTenKhuyenMai = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txtGiaGiam = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        dateNgayBD = new com.toedter.calendar.JDateChooser();
        jLabel76 = new javax.swing.JLabel();
        dateNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtAMoTaKM = new javax.swing.JTextArea();
        btnTaoKM = new javax.swing.JButton();
        btnLamMoiKM = new javax.swing.JButton();
        btnSuaKM = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rdHetHan = new javax.swing.JRadioButton();
        rdConHan = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThiSP = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTimSP = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        btnTaoSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHienThiTTSP = new javax.swing.JTable();
        cbbTenKM = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDen = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btntrangchu = new javax.swing.JButton();
        btnbanhang = new javax.swing.JButton();
        btnnhanvien = new javax.swing.JButton();
        btnsanpham = new javax.swing.JButton();
        btnkhachhang = new javax.swing.JButton();
        btnkhuyenmai = new javax.swing.JButton();
        btnthongke = new javax.swing.JButton();
        btnlichsu = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        anhNV = new javax.swing.JLabel();
        tenNV = new javax.swing.JLabel();
        CV = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_Chay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_Chay.setText("Chào Mừng Tất Cả Mọi Người Đến Với Cửa Hàng Trang Sức Phụ Kiện Nữ N1.Chúc Mọi Người Có Một Ngày Vui Vẻ.                                                                                                                                                      ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 51));
        jLabel2.setText("Chương Trình Khuyến Mãi");

        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(153, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã KM", "Tên Chương Trình", "Giảm Giá", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái", "Mô Tả"
            }
        ));
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbHienThi);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tìm Kiếm :");

        txtTimKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKMKeyReleased(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Trạng Thái :");

        cbbLocTrangThaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hạn", "Hết Hạn" }));
        cbbLocTrangThaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTrangThaiKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel15)
                .addGap(23, 23, 23)
                .addComponent(cbbLocTrangThaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbbLocTrangThaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        jLabel73.setText("Tên Chương Trình :");

        txtTenKhuyenMai.setName("SĐT"); // NOI18N
        txtTenKhuyenMai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenKhuyenMaiFocusLost(evt);
            }
        });

        jLabel74.setText("Giảm Giá :");

        jLabel75.setText("Ngày Bắt Đầu :");

        dateNgayBD.setDateFormatString("dd/MM/yyyy");
        dateNgayBD.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayBDPropertyChange(evt);
            }
        });

        jLabel76.setText("Ngày Kết Thúc : ");

        dateNgayKT.setDateFormatString("dd/MM/yyyy");

        jLabel77.setText("Mô Tả :");

        txtAMoTaKM.setColumns(20);
        txtAMoTaKM.setRows(5);
        jScrollPane13.setViewportView(txtAMoTaKM);

        btnTaoKM.setBackground(new java.awt.Color(51, 204, 255));
        btnTaoKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoKM.setText("Thêm");
        btnTaoKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoKMActionPerformed(evt);
            }
        });

        btnLamMoiKM.setBackground(new java.awt.Color(51, 204, 255));
        btnLamMoiKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoiKM.setText("Mới");
        btnLamMoiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKMActionPerformed(evt);
            }
        });

        btnSuaKM.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaKM.setText("Sửa");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        jLabel5.setText("Trạng Thái :");

        buttonGroup7.add(rdHetHan);
        rdHetHan.setText("Hết Hạn");

        buttonGroup7.add(rdConHan);
        rdConHan.setText("Còn Hạn");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(btnTaoKM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaKM))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74)
                            .addComponent(jLabel73)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(rdHetHan)
                                .addGap(62, 62, 62)
                                .addComponent(rdConHan))
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addComponent(txtGiaGiam))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                        .addComponent(btnLamMoiKM)
                        .addGap(132, 132, 132)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addComponent(jLabel76))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(26, 26, 26))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel75)
                                .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel76)
                                .addComponent(jLabel74))
                            .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel77)
                                .addComponent(jLabel5)
                                .addComponent(rdHetHan)
                                .addComponent(rdConHan))))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaoKM)
                            .addComponent(btnLamMoiKM)
                            .addComponent(btnSuaKM))))
                .addGap(412, 412, 412))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Khuyến Mãi", jPanel13);

        jPanel15.setBackground(new java.awt.Color(153, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tbHienThiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên KM", "Tên SP", "Ngày bắt đầu", "Ngày kết thúc", "Giá Giảm", "Trạng Thái"
            }
        ));
        tbHienThiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHienThiSP);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tìm Kiếm :");

        txtTimSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSPKeyReleased(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(51, 204, 255));
        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTim.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnTim))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));

        btnTaoSP.setBackground(new java.awt.Color(51, 204, 255));
        btnTaoSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoSP.setText("Thêm");
        btnTaoSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoSPActionPerformed(evt);
            }
        });

        btnSuaSP.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 204, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Làm Mới");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tbHienThiTTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "TênSP", "Giá Bán"
            }
        ));
        tbHienThiTTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiTTSPMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbHienThiTTSP);

        cbbTenKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Tên Khuyến Mãi :");

        jLabel8.setText("Tìm kiếm :");

        txtTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPKeyReleased(evt);
            }
        });

        jLabel7.setText("Giá Từ : ");

        txtTu.setText("0");
        txtTu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuKeyReleased(evt);
            }
        });

        jLabel9.setText("Đến :");

        txtDen.setText("0");
        txtDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDenKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTaoSP)
                .addGap(357, 357, 357)
                .addComponent(btnSuaSP)
                .addGap(251, 251, 251)
                .addComponent(jButton6)
                .addGap(262, 262, 262))
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbTenKM, 0, 251, Short.MAX_VALUE)
                            .addComponent(txtTimKiemSP))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(btnSuaSP)
                    .addComponent(btnTaoSP))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        jTabbedPane3.addTab("Chi Tiết Khuyến Mãi", jPanel15);

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 153));

        jLabel20.setText("Tên Nhân Viên:");

        jLabel21.setText("Chức Vụ:");

        btntrangchu.setBackground(new java.awt.Color(255, 255, 153));
        btntrangchu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrangchu.setText("Trang Chủ");
        btntrangchu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntrangchuActionPerformed(evt);
            }
        });

        btnbanhang.setBackground(new java.awt.Color(255, 255, 153));
        btnbanhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbanhang.setText("Bán Hàng");
        btnbanhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbanhangActionPerformed(evt);
            }
        });

        btnnhanvien.setBackground(new java.awt.Color(255, 255, 153));
        btnnhanvien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnnhanvien.setText("Nhân Viên");
        btnnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhanvienActionPerformed(evt);
            }
        });

        btnsanpham.setBackground(new java.awt.Color(255, 255, 153));
        btnsanpham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsanpham.setText("Sản Phẩm");
        btnsanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsanphamActionPerformed(evt);
            }
        });

        btnkhachhang.setBackground(new java.awt.Color(255, 255, 153));
        btnkhachhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnkhachhang.setText("Khách Hàng");
        btnkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhachhangActionPerformed(evt);
            }
        });

        btnkhuyenmai.setBackground(new java.awt.Color(255, 255, 153));
        btnkhuyenmai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnkhuyenmai.setText("Khuyến Mãi");
        btnkhuyenmai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhuyenmaiActionPerformed(evt);
            }
        });

        btnthongke.setBackground(new java.awt.Color(255, 255, 153));
        btnthongke.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthongke.setText("Thống kê");
        btnthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkeActionPerformed(evt);
            }
        });

        btnlichsu.setBackground(new java.awt.Color(255, 255, 153));
        btnlichsu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlichsu.setText("Lịch Sử");
        btnlichsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlichsuActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(255, 255, 153));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        tenNV.setText("jLabel1");

        CV.setText("jLabel10");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(anhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CV, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btntrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(btnkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnlichsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(anhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(tenNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(CV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btntrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnlichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(691, 691, 691)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1766, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane3))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Chay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txtTenKhuyenMaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhuyenMaiFocusLost

    }//GEN-LAST:event_txtTenKhuyenMaiFocusLost

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        //
        cbbKM.removeAllElements();
//        cbbSanPham.removeAllElements();

        //        showDataSP();
        loadComBoKM();
//        loadComBoSP();
    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed
        if (jcheck.checkClcick(tbHienThi, this) == false) {
            return;
        } else {
            if (txtTenKhuyenMai.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Tên KM đang trống");
                txtTenKhuyenMai.requestFocus();
                return;
            }
            if (txtGiaGiam.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Giảm Giá đang trống");
                txtGiaGiam.requestFocus();
                return;
            }
            if (dateNgayBD.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Ngày BĐ KM đang trống");
                dateNgayBD.requestFocus();
                return;
            }
            if (dateNgayKT.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Ngày KT KM đang trống");
                dateNgayKT.requestFocus();
                return;
            }
            if (txtGiaGiam.getText().matches("^(100|[1-9][0-9]?)$") == false) {
                JOptionPane.showMessageDialog(this, "Giảm giá sai định dạng");
                txtGiaGiam.requestFocus();
                return;
            }
            if (checkMaKM(txtTenKhuyenMai.getText().trim()) == false) {
                JOptionPane.showMessageDialog(this, "Tên đã tồn tại");
                txtTenKhuyenMai.requestFocus();
                return;
            }
            if (rdHetHan.isSelected() == true) {
                JOptionPane.showConfirmDialog(this, "Bạn đang chọn sai trạng thái");
                return;
            }
            if (checkCalendar() == false) {
                return;
            }

            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?", "Update", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                khuyenMaiResponsitories.update(getData("update"));
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");

            }
        }

        loadTableKM();
        clear();
    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void btnLamMoiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKMActionPerformed
        jcheck.clearView(jText, tbHienThi);
        dateNgayBD.setDate(null);
        dateNgayKT.setDate(null);
    }//GEN-LAST:event_btnLamMoiKMActionPerformed

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        try {

            fillFormKM(tbHienThi.getSelectedRow());
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void btnTaoSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoSPActionPerformed
        tbHienThiSP.clearSelection();
        if (checKM(cbbKM.getSelectedItem().toString(), tbHienThiTTSP.getValueAt(tbHienThiTTSP.getSelectedRow(), 2).toString()) == false) {
            JOptionPane.showMessageDialog(this, "Một sản phẩm chỉ được áp dụng một khuyến mãi");
            return;
        }
        if (checThemKM(((KhuyenMai) cbbKM.getSelectedItem()).getNgayBD(),
                tbHienThiTTSP.getValueAt(tbHienThiTTSP.getSelectedRow(), 2).toString()) == false) {
            JOptionPane.showMessageDialog(this, "Không được áp dụng khuyến mãi cho 1 sp cùng một thời gian");
            return;
        }
        if (chiTietKhuyenMaiResponsitories.add(getDataCTKM("")) == 1) {
            JOptionPane.showMessageDialog(this, "Thành công");
        }

        loadTableSanPham();
        clear();
    }//GEN-LAST:event_btnTaoSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        if (jcheck.checkClcick(tbHienThiSP, this) == false) {
            return;
        } else {
            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?", "Update", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                chiTietKhuyenMaiResponsitories.update(getDataCTKM("update"));
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");

            }
        }
        loadTableSanPham();
        clear();
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        clear();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tbHienThiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiSPMouseClicked
        int row = tbHienThiSP.getSelectedRow();
        try {
            fillFormSP(row);
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHienThiSPMouseClicked

    private void txtTimSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSPKeyReleased
        String ten = txtTimSP.getText();
        System.out.println(ten);
        List<ChiTietKhuyenMai> list = chiTietKhuyenMaiResponsitories.SelectbyNameSP(ten);
        finTenSP(list);
    }//GEN-LAST:event_txtTimSPKeyReleased

    private void btnTaoKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoKMActionPerformed
        tbHienThi.clearSelection();
        if (txtTenKhuyenMai.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Tên KM đang trống");
            txtTenKhuyenMai.requestFocus();
            return;
        }
        if (txtGiaGiam.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Giảm Giá đang trống");
            txtGiaGiam.requestFocus();
            return;
        }
        if (dateNgayBD.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày BĐ KM đang trống");
            dateNgayBD.requestFocus();
            return;
        }
        if (dateNgayKT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày KT KM đang trống");
            dateNgayKT.requestFocus();
            return;
        }
        if (txtGiaGiam.getText().matches("^(100|[1-9][0-9]?)$") == false) {
            JOptionPane.showMessageDialog(this, "Giảm giá sai định dạng");
            txtGiaGiam.requestFocus();
            return;
        }
        if (checkMaKM(txtTenKhuyenMai.getText().trim()) == false) {
            JOptionPane.showMessageDialog(this, "Tên đã tồn tại");
            txtTenKhuyenMai.requestFocus();
            return;
        }
        if (checkCalendar() == false) {
            return;
        }

        if (khuyenMaiResponsitories.add(getData(" ")) == 1) {
            JOptionPane.showMessageDialog(this, "thành công");
        }
        sapXep(khuyenMaiResponsitories.getAllLoad());
//        loadTableKM();
        clear();
    }//GEN-LAST:event_btnTaoKMActionPerformed

    private void txtTimKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKMKeyReleased
        String ten = txtTimKM.getText();
        System.out.println(ten);
        List<KhuyenMai> list = khuyenMaiResponsitories.SelectbyName(ten);
        finTenKM(list);
    }//GEN-LAST:event_txtTimKMKeyReleased

    private void cbbLocTrangThaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrangThaiKMActionPerformed
        String a = (String) cbbLocTrangThaiKM.getSelectedItem();
//        System.out.println("1 :" + a);
        List<KhuyenMai> list = khuyenMaiResponsitories.SelectbyTrangThai(a);
        finTrangThai(list);


    }//GEN-LAST:event_cbbLocTrangThaiKMActionPerformed

    private void tbHienThiTTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiTTSPMouseClicked

    }//GEN-LAST:event_tbHienThiTTSPMouseClicked

    private void txtTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuKeyReleased
        Float ten = Float.parseFloat(txtTu.getText());
        Float ten1 = Float.parseFloat(txtDen.getText());

        System.out.println(ten);
        System.out.println(ten1);
        List<SanPham> list = sanPham.SelectbyGia(ten, ten1);
        finGia(list);
    }//GEN-LAST:event_txtTuKeyReleased

    private void txtDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenKeyReleased
        Float ten = Float.parseFloat(txtTu.getText());
        Float ten1 = Float.parseFloat(txtDen.getText());

        System.out.println(ten);
        System.out.println(ten1);
        List<SanPham> list = sanPham.SelectbyGia(ten, ten1);
        finGia(list);
    }//GEN-LAST:event_txtDenKeyReleased

    private void txtTimKiemSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPKeyReleased
        String ten = txtTimKiemSP.getText();
        System.out.println(ten);
        List<SanPham> list = sanPham.SelectbyTen(ten);
        selectbyTen(list);
    }//GEN-LAST:event_txtTimKiemSPKeyReleased

    private void dateNgayBDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayBDPropertyChange
//        System.out.println("aaaaaaaa" + dateNgayBD.getDate());
    }//GEN-LAST:event_dateNgayBDPropertyChange

    private void btntrangchuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntrangchuActionPerformed
        //        this.setVisible(false);
        //        new Trangchu().setVisible(true);
    }//GEN-LAST:event_btntrangchuActionPerformed

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
        this.setVisible(false);
        new BanHang().setVisible(true);
    }//GEN-LAST:event_btnbanhangActionPerformed

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
        this.setVisible(false);
        new QuanLyNhanVien().setVisible(true);
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamActionPerformed
        this.setVisible(false);
        new viewSanPham().setVisible(true);
    }//GEN-LAST:event_btnsanphamActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
        this.setVisible(false);
        new KhachHangView().setVisible(true);
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnkhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhuyenmaiActionPerformed
        this.setVisible(false);
        new KhuyenMaiView().setVisible(true);
    }//GEN-LAST:event_btnkhuyenmaiActionPerformed

    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        this.setVisible(false);
        new ThongKe().setVisible(true);
    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btnlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlichsuActionPerformed
        this.setVisible(false);
        new LichSu().setVisible(true);
    }//GEN-LAST:event_btnlichsuActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        this.setVisible(false);
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

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
                new KhuyenMaiView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CV;
    private javax.swing.JLabel anhNV;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnLamMoiKM;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnTaoKM;
    private javax.swing.JButton btnTaoSP;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnkhuyenmai;
    private javax.swing.JButton btnlichsu;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnsanpham;
    private javax.swing.JButton btnthongke;
    private javax.swing.JButton btntrangchu;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JComboBox<String> cbbLocTrangThaiKM;
    private javax.swing.JComboBox<String> cbbTenKM;
    private com.toedter.calendar.JDateChooser dateNgayBD;
    private com.toedter.calendar.JDateChooser dateNgayKT;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbl_Chay;
    private javax.swing.JRadioButton rdConHan;
    private javax.swing.JRadioButton rdHetHan;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbHienThiSP;
    private javax.swing.JTable tbHienThiTTSP;
    private javax.swing.JLabel tenNV;
    private javax.swing.JTextArea txtAMoTaKM;
    private javax.swing.JTextField txtDen;
    private javax.swing.JTextField txtGiaGiam;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTimKM;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JTextField txtTu;
    // End of variables declaration//GEN-END:variables
}
