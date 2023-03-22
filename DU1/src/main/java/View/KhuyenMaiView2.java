/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DomainModel.ChiTietKhuyenMai;
import DomainModel.KhuyenMai;
import DomainModel.SanPham;
import Responsitoties.ChiTietKhuyenMaiResponsitories;
import Responsitoties.KhuyenMaiResponsitories;
import Utilities.jframeCheck;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhuyenMaiView2 extends javax.swing.JFrame {

    public final ChiTietKhuyenMaiResponsitories chiTietKhuyenMaiResponsitories = new ChiTietKhuyenMaiResponsitories();
    public final KhuyenMaiResponsitories khuyenMaiResponsitories = new KhuyenMaiResponsitories();
    DefaultTableModel dtm = new DefaultTableModel();
    private final jframeCheck jcheck = new jframeCheck();
    private final List<Object> jText = new ArrayList<>();
    private DefaultComboBoxModel cbbKM = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbSanPham = new DefaultComboBoxModel();
//    private String idKM;

    /**
     * Creates new form KhachHangView
     */
    public KhuyenMaiView2() {
        initComponents();
        jText.add(txtAMoTaKM);
        jText.add(txtGiaGiam1);
//        jText.add(txtMaKhuyenMai);
        jText.add(txtTenKhuyenMai);
        jText.add(dateNgayBD);
        jText.add(dateNgayKT);
        jText.add(cbbTenKM);
        jText.add(cbbTenSP);
        jText.add(txtMoTaSP);

        loadTableKM();
        loadComBoKM();
        loadComBoSP();
        loadTableSanPham();

    }

    public void loadTableKM() {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        if (khuyenMaiResponsitories.getAllLoad() != null) {

            for (KhuyenMai object : khuyenMaiResponsitories.getAllLoad()) {
                dtm.addRow(object.toRow());
            }
        }
    }

    public void findTenKM(String ten) {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai object : khuyenMaiResponsitories.getAll(ten)) {
            dtm.addRow(object.toRow());
        }

    }

    public void findTrangThai(int trangThai) {
        dtm = (DefaultTableModel) tbHienThi.getModel();

        dtm.setRowCount(0);

        for (KhuyenMai object : khuyenMaiResponsitories.findTrangThai(trangThai)) {
            dtm.addRow(object.toRow());
        }

    }

    public void loadComBoKM() {
        List<KhuyenMai> list = khuyenMaiResponsitories.getAllLoad();
        if (list != null) {
          
        for (KhuyenMai danhMucViewModel : list) {
            cbbKM.addElement(danhMucViewModel);
        }
        cbbTenKM.setModel(cbbKM);  
        }
    }

    public void loadComBoSP() {
        List<Object> list = chiTietKhuyenMaiResponsitories.getAllSP();
        for (Object sanPhamViewModel : list) {
            cbbSanPham.addElement(sanPhamViewModel);
        }
        cbbTenSP.setModel(cbbSanPham);
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

    public void fillFormKM(int row) throws ParseException {
        KhuyenMai ctkmvm = khuyenMaiResponsitories.getAllLoad().get(row);
        txtTenKhuyenMai.setText(ctkmvm.getTenKM());
        String gia = Float.toString(ctkmvm.getGiaGiam());
        txtGiaGiam1.setText(gia);
//        txtAMoTaKM.setText(ctkmvm.getMoTa());
        Date ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayBD()));
        Date ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayKT()));
        dateNgayBD.setDate(ngayBD);
        dateNgayKT.setDate(ngayKT);
    }

    public void fillFormSP(int row) throws ParseException {
        ChiTietKhuyenMai ctkm = chiTietKhuyenMaiResponsitories.getAll(null).get(row);
        cbbKM.setSelectedItem(ctkm.getKm());
        cbbSanPham.setSelectedItem(ctkm.getSp());
    }

    private KhuyenMai getData(String dk) {
        if (dk.equalsIgnoreCase("update")) {
            System.out.println("update");
            System.out.println("id" + tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString());
            return new KhuyenMai(tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString(),
                    tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 1).toString(),
                    txtTenKhuyenMai.getText(), Float.parseFloat(txtGiaGiam1.getText()),
                    dateNgayBD.getDate(), dateNgayKT.getDate(), txtAMoTaKM.getText());
        }
        return new KhuyenMai(jcheck.createID().toString(),
                jcheck.randomMA(),
                txtTenKhuyenMai.getText().trim(), Float.parseFloat(txtGiaGiam1.getText().trim()),
                dateNgayBD.getDate(), dateNgayKT.getDate(), txtAMoTaKM.getText().trim());
    }

    public ChiTietKhuyenMai getDataCTKM(String dk) {
        if (dk.equalsIgnoreCase("update")) {
            return new ChiTietKhuyenMai(
                    tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 0).toString(),
                    tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 1).toString(),
                    (SanPham) cbbSanPham.getSelectedItem(),
                    (KhuyenMai) cbbKM.getSelectedItem()
            );
        }
        return new ChiTietKhuyenMai(
                jcheck.createID().toString(), jcheck.randomMA(),
                (SanPham) cbbSanPham.getSelectedItem(),
                (KhuyenMai) cbbKM.getSelectedItem());

    }

//    private boolean checkMaKM(String ma) {
//        if (khuyenMaiResponsitories.getAllLoad()!=null) {
//        for (int i = 0; i < khuyenMaiResponsitories.getAllLoad().size(); i++) {
//            if (khuyenMaiResponsitories.getAllLoad().get(i).getTenKM().equalsIgnoreCase(ma)) {
//                if (tbHienThi.getSelectedRow() == i) {
//                    continue;
//                }
//                return false;
//            }
//        }
//        }
//        return true;
//    }

//    private boolean checkCalendar() {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = cal.getTime();
//        Date a = dateNgayBD.getDate();
//        Date b = dateNgayKT.getDate();
//        Date endDate = new Date(date.getTime() - (30 * 24 * 60 * 60 * 1000));
//        String ngay = d.format(date);
//        System.out.println("na" + ngay);
//        if (dateNgayBD.getDate().before(endDate) == false) {
//            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được quá 30 ngày so với hiện tại");
//            dateNgayBD.requestFocus();
//            return false;
//        }
//        if (dateNgayBD.getDate().after(date) == false) {
//            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không thể là ngày quá khứ");
//            dateNgayBD.requestFocus();
//            return false;
//        }
//
//        if (dateNgayKT.getDate().after(date) == false) {
//            JOptionPane.showMessageDialog(this, "Ngày kết thúc không thể là ngày quá khứ");
//            dateNgayKT.requestFocus();
//            return false;
//        }
//        if (dateNgayKT.getDate().after(a) == false) {
//            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được sau ngày bắt đầu");
//            dateNgayKT.requestFocus();
//            return false;
//        }
//
//        return true;
//    }

    private void clear() {
        jcheck.clearView(jText, tbHienThi);
        dateNgayBD.setDate(null);
        dateNgayKT.setDate(null);

    }
//    private boolean checkKM(){
//        if(khuyenMaiResponsitories.)
//        return true;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        txtTenKhuyenMai = new javax.swing.JTextField();
        btnTaoKM = new javax.swing.JButton();
        btnSuaKM = new javax.swing.JButton();
        btnLamMoiKM = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        txtGiaGiam1 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtAMoTaKM = new javax.swing.JTextArea();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        btnXoaKm = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        cbbLoc = new javax.swing.JComboBox<>();
        dateNgayBD = new com.toedter.calendar.JDateChooser();
        dateNgayKT = new com.toedter.calendar.JDateChooser();
        jPanel29 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        txtTimKM = new javax.swing.JTextField();
        btnTimKiem6 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnTaoSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTaSP = new javax.swing.JTextArea();
        jPanel32 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        txtTimSP = new javax.swing.JTextField();
        btnTimKiemSP = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHienThiSP = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        cbbTenKM = new javax.swing.JComboBox<>();
        cbbTenSP = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(238, 235, 155));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(238, 235, 155));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 850));

        jLabel2.setText("Chào mừng đến với Cửa hàng bán trang sức nữ. Chúc một ngày tốt lành  ");

        jPanel2.setBackground(new java.awt.Color(242, 242, 136));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(242, 242, 136));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 36));

        jLabel3.setText("Bán Hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel3)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(242, 242, 136));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 36));

        jLabel15.setText("Nhân Viên");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel15)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(242, 242, 136));
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 36));

        jLabel14.setText("           Sản Phẩm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 51));

        jLabel17.setText("Khách Hàng");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(14, 14, 14))
        );

        jPanel8.setBackground(new java.awt.Color(242, 242, 136));

        jLabel18.setText("Khuyến Mãi");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel18)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(242, 242, 136));

        jLabel20.setText("Thống Kê");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel20)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(242, 242, 136));

        jLabel22.setText("Lịch Sử");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(242, 242, 136));

        jLabel23.setText("Đổi Mật Khẩu");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel23)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );

        jLabel4.setText("Tên Nhân Viên:");

        jLabel5.setText("Chức Vụ:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(242, 242, 136));

        jLabel19.setText("Đăng Xuất");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách"));

        jLabel59.setText("Tên Chương Trình :");

        txtTenKhuyenMai.setName("SĐT"); // NOI18N
        txtTenKhuyenMai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenKhuyenMaiFocusLost(evt);
            }
        });

        btnTaoKM.setText("Tạo");
        btnTaoKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoKMActionPerformed(evt);
            }
        });

        btnSuaKM.setText("Sửa");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        btnLamMoiKM.setText("Làm mới");
        btnLamMoiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKMActionPerformed(evt);
            }
        });

        jLabel60.setText("Giảm Giá :");

        jLabel61.setText("Ngày Bắt Đầu :");

        jLabel62.setText("Ngày Kết Thúc : ");

        jLabel63.setText("Mô Tả :");

        txtAMoTaKM.setColumns(20);
        txtAMoTaKM.setRows(5);
        jScrollPane9.setViewportView(txtAMoTaKM);

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã KH", "Tên Chương Trình", "Giảm Giá", "Bắt Đầu", "Kết Thúc", "Trạng thái", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbHienThi);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXoaKm.setText("Xoá");
        btnXoaKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKmActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        cbbLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(btnTaoKM, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnSuaKM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoaKm))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel60))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaGiam1)
                                    .addComponent(txtTenKhuyenMai))))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                                .addComponent(btnLamMoiKM)
                                                .addGap(66, 66, 66)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addComponent(jLabel62)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addComponent(jLabel61)
                                        .addGap(18, 18, 18)
                                        .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel61)
                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel59))
                    .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel62)
                        .addComponent(txtGiaGiam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60))
                    .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaoKM)
                            .addComponent(btnSuaKM)
                            .addComponent(btnLamMoiKM)
                            .addComponent(btnXoaKm))))
                .addGap(43, 43, 43)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phân Loại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel64.setText("Tìm Kiếm :");

        txtTimKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKMKeyReleased(evt);
            }
        });

        btnTimKiem6.setText("Tìm Kiếm");
        btnTimKiem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem6)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem6))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(394, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(303, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Khuyến Mãi", jPanel11);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách"));

        jLabel13.setText("Tên SP :");

        btnTaoSP.setText("Tạo");
        btnTaoSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnLamMoiSP.setText("Làm mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        jLabel67.setText("Mô Tả :");

        txtMoTaSP.setColumns(20);
        txtMoTaSP.setRows(5);
        jScrollPane2.setViewportView(txtMoTaSP);

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phân Loại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel68.setText("Tìm Kiếm :");

        btnTimKiemSP.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel68)
                .addGap(18, 18, 18)
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnTimKiemSP)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemSP))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        tbHienThiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên KM", "Tên Sản Phẩm", "Kết Thúc", "Giá Giảm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHienThiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbHienThiSP);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnXoaSP.setText("Xoá");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        jLabel69.setText("Tên KM : ");

        cbbTenKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel69)))
                .addGap(37, 37, 37)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbTenSP, 0, 147, Short.MAX_VALUE)
                    .addComponent(cbbTenKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnTaoSP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaSP)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(btnSuaSP)
                                .addGap(35, 35, 35)
                                .addComponent(btnLamMoiSP))))
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel69)
                                    .addComponent(cbbTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel67))
                        .addGap(69, 69, 69))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoSP)
                    .addComponent(btnSuaSP)
                    .addComponent(btnLamMoiSP))
                .addGap(18, 18, 18)
                .addComponent(btnXoaSP)
                .addGap(20, 20, 20)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 866, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 612, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Sản Phẩm Áp Dụng", jPanel15);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 360, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(280, 280, 280))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
//        
        cbbKM.removeAllElements();
        cbbSanPham.removeAllElements();

//        showDataSP();
        loadComBoKM();
        loadComBoSP();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnTaoKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoKMActionPerformed

        if (txtTenKhuyenMai.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Tên KM đang trống");
            txtTenKhuyenMai.requestFocus();
            return;
        }
        if (txtGiaGiam1.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Giảm Giá đang trống");
            txtGiaGiam1.requestFocus();
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

        if (txtGiaGiam1.getText().matches("^(100|[1-9][0-9]?)$") == false) {
            JOptionPane.showMessageDialog(this, "Giảm giá sai định dạng");
            txtGiaGiam1.requestFocus();
            return;
        }
//        if (checkMaKM(txtTenKhuyenMai.getText().trim()) == false) {
//            JOptionPane.showMessageDialog(this, "Tên đã tồn tại");
//            txtTenKhuyenMai.requestFocus();
//            return;
//        }
//        if (checkCalendar() == false) {
//            return;
//        }

        if (khuyenMaiResponsitories.add(getData("")) == 1) {
            JOptionPane.showMessageDialog(this, "Tạo thành công");
            return;
        }

        loadTableKM();
        clear();
    }//GEN-LAST:event_btnTaoKMActionPerformed

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed
        if (jcheck.checkClcick(tbHienThi, this) == false) {
            return;
        } else {
            if (txtTenKhuyenMai.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Tên KM đang trống");
                txtTenKhuyenMai.requestFocus();
                return;
            }
            if (txtGiaGiam1.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Giảm Giá đang trống");
                txtGiaGiam1.requestFocus();
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
            if (txtGiaGiam1.getText().matches("^(100|[1-9][0-9]?)$") == false) {
                JOptionPane.showMessageDialog(this, "Giảm giá sai định dạng");
                txtGiaGiam1.requestFocus();
                return;
            }
//            if (checkMaKM(txtTenKhuyenMai.getText().trim()) == false) {
//                JOptionPane.showMessageDialog(this, "Tên đã tồn tại");
//                txtTenKhuyenMai.requestFocus();
//                return;
//            }
//            if (checkCalendar() == false) {
//                return;
//            }

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
            Logger.getLogger(KhuyenMaiView2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void btnTaoSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoSPActionPerformed

        if (chiTietKhuyenMaiResponsitories.add(getDataCTKM("")) == 1) {
            return;
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

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void tbHienThiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiSPMouseClicked
        int row = tbHienThiSP.getSelectedRow();
        try {
            fillFormSP(row);
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiView2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHienThiSPMouseClicked

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        int row = tbHienThiSP.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn thông tin để xoá?");
        } else {
            int tb = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (tb == JOptionPane.YES_OPTION) {
                chiTietKhuyenMaiResponsitories.delete(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 0).toString());
                JOptionPane.showMessageDialog(this, "Xoá thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xoá thất bại");
            }
            loadTableSanPham();
            clear();

        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnXoaKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKmActionPerformed
        int row = tbHienThi.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khuyến mãi để xoá?");
        } else {
            int tb = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (tb == JOptionPane.YES_OPTION) {
                khuyenMaiResponsitories.delete(tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString());
                JOptionPane.showMessageDialog(this, "Xoá thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xoá thất bại");
            }
            loadTableKM();
            clear();
        }


    }//GEN-LAST:event_btnXoaKmActionPerformed

    private void btnTimKiem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem6ActionPerformed

    }//GEN-LAST:event_btnTimKiem6ActionPerformed

    private void txtTenKhuyenMaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhuyenMaiFocusLost

    }//GEN-LAST:event_txtTenKhuyenMaiFocusLost

    private void cbbLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocActionPerformed

    }//GEN-LAST:event_cbbLocActionPerformed

    private void txtTimKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKMKeyReleased
//        findTenKM(txtTimKM.getText());
    }//GEN-LAST:event_txtTimKMKeyReleased

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
            java.util.logging.Logger.getLogger(KhuyenMaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiView2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new KhuyenMaiView2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiKM;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnTaoKM;
    private javax.swing.JButton btnTaoSP;
    private javax.swing.JButton btnTimKiem6;
    private javax.swing.JButton btnTimKiemSP;
    private javax.swing.JButton btnXoaKm;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbLoc;
    private javax.swing.JComboBox<String> cbbTenKM;
    private javax.swing.JComboBox<String> cbbTenSP;
    private com.toedter.calendar.JDateChooser dateNgayBD;
    private com.toedter.calendar.JDateChooser dateNgayKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbHienThiSP;
    private javax.swing.JTextArea txtAMoTaKM;
    private javax.swing.JTextField txtGiaGiam1;
    private javax.swing.JTextArea txtMoTaSP;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTimKM;
    private javax.swing.JTextField txtTimSP;
    // End of variables declaration//GEN-END:variables
}
