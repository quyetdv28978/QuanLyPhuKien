/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.jframeCheck;
import domaimodel.ChiTietKhuyenMai;
import domaimodel.Embeddedct;
import domaimodel.KhuyenMai;
import domaimodel.SanPham;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import respon. ChiTietKhuyenMaiResponsitories;
import respon.KhuyenMaiResponsitories;
import service.KhuyenMaiServices;
import viewmodel.KhuyenMaiViewModel;
/**
 *
 * @author DELL
 */
public class KhuyenMai extends javax.swing.JFrame {

    /**
     * Creates new form KhuyenMa
     */
     public final ChiTietKhuyenMaiResponsitories chiTietKhuyenMaiResponsitories = new ChiTietKhuyenMaiResponsitories();
    public final KhuyenMaiResponsitories khuyenMaiResponsitories = new KhuyenMaiResponsitories();
    public final KhuyenMaiServices khuyenMaiServices = new KhuyenMaiServices();
    DefaultTableModel dtm = new DefaultTableModel();
    private final jframeCheck jcheck = new jframeCheck();
    private final List<Object> jText = new ArrayList<>();
    private DefaultComboBoxModel cbbKM = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbSanPham = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbTT = new DefaultComboBoxModel();
    public KhuyenMai() {
        initComponents();
        chay();
         jText.add(txtAMoTaKM);
        jText.add(txtGiaGiam);
//        jText.add(txtMaKhuyenMai);
        jText.add(txtTenKhuyenMai);
        jText.add(dateNgayBD);
        jText.add(dateNgayKT);
        jText.add(cbbTenKM);
        jText.add(cbbTenSP);
        jText.add(txtMoTaSP);
        jText.add(rdConHan);
        jText.add(rdHetHan);
        
//        rdConHan.setSelected(true);

        loadTableKM();
        loadComBoKM();
        loadComBoSP();
        loadTableSanPham();
        
    }
  public void loadTableKM() {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai object : khuyenMaiResponsitories.getAllLoad()) {
            dtm.addRow(object.toRow());
        }

    }
  public void finTT(String a) {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai object : khuyenMaiResponsitories.getAllLoad()) {
            dtm.addRow(object.toRow());
        }

    }


    public void finTenKM(List<KhuyenMai> list) {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        List<KhuyenMai> khvm = khuyenMaiResponsitories.SelectbyName(txtTimKM.getText());
        for (KhuyenMai khvms : khvm) {
            dtm.addRow(khvms.toRow());
        }
    }

    public void finTrangThai(List<KhuyenMai> list) {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        List<KhuyenMai> khvm = khuyenMaiResponsitories.SelectbyTrangThai((String) cbbLocTrangThaiKM.getSelectedItem());
        for (KhuyenMai khvms : khvm) {
            dtm.addRow(khvms.toRow());
        }
    }

    public void loadComBoKM() {
        List<KhuyenMai> list = khuyenMaiResponsitories.getAllLoad();
        for (KhuyenMai danhMucViewModel : list) {
            cbbKM.addElement(danhMucViewModel);
        }
        cbbTenKM.setModel(cbbKM);
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
        for (ChiTietKhuyenMai object : chiTietKhuyenMaiResponsitories.getAll(null)) {
            dtm.addRow(object.toRow1());
        }
    }

    public void fillFormKM(int row) throws ParseException {
        KhuyenMai ctkmvm = khuyenMaiResponsitories.getAllLoad().get(row);
        txtTenKhuyenMai.setText(ctkmvm.getTenKM());
        String gia = Float.toString(ctkmvm.getGiaGiam());
        txtGiaGiam.setText(gia);
        txtAMoTaKM.setText(ctkmvm.getMoTa());
        Date ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayBD()));
        Date ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayKT()));
        dateNgayBD.setDate(ngayBD);
        dateNgayKT.setDate(ngayKT);
//         Calendar cal = Calendar.getInstance();
//        Date d = cal.getTime();   
//        if(dateNgayKT.getDate().before(d)){
//            rdHetHan.setSelected(true);
//        }else{
//            rdConHan.setSelected(true);
//        }
            String gt = ctkmvm.getTT();
             if (gt.equalsIgnoreCase("Còn Hạn")) {
            rdConHan.setSelected(true);
            } else {
            rdHetHan.setSelected(true);
            }
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
                    txtTenKhuyenMai.getText(), Float.parseFloat(txtGiaGiam.getText()),
                    dateNgayBD.getDate(), dateNgayKT.getDate(), txtAMoTaKM.getText());
        }
//       String a = "";
//       if(){
//           a = "Còn Hạn";
//       }else{
//           a = "Hết hạn";
//           }
        return new KhuyenMai(jcheck.createID().toString(),
                jcheck.randomMA(),
                txtTenKhuyenMai.getText().trim(), Float.parseFloat(txtGiaGiam.getText().trim()),
                dateNgayBD.getDate(), dateNgayKT.getDate(),rdConHan.isSelected() == true ? "Còn Hạn" : "Hết hạn"
                        ,txtAMoTaKM.getText().trim());
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

    private boolean checkCalendar() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = cal.getTime();
        Date a = dateNgayBD.getDate();
        System.out.println("Ngày Bắt đầu" + a);
        Date b = dateNgayKT.getDate();
        Date endDate = new Date(date.getTime() - (30 * 24 * 60 * 60 * 1000));
        Date endDate1 = new Date(date.getTime() - (1 *24 * 60 *60 * 1000));
        String ngay = d.format(date);
        System.out.println("na" + ngay);
        System.out.println("na" + endDate1);
        if (dateNgayBD.getDate().before(endDate) == false) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được quá 30 ngày so với hiện tại");
            dateNgayBD.requestFocus();
            return false;
        }
        if (dateNgayBD.getDate().after(endDate1) == false) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không thể là ngày quá khứ");
            dateNgayBD.requestFocus();
            return false;
        }

        if (dateNgayKT.getDate().after(endDate1) == false) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không thể là ngày quá khứ");
            dateNgayKT.requestFocus();
            return false;
        }
        if (dateNgayKT.getDate().after(a) == false) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được sau ngày bắt đầu");
            dateNgayKT.requestFocus();
            return false;
        }

        return true;
    }

    private void clear() {
        jcheck.clearView(jText, tbHienThi);
        dateNgayBD.setDate(null);
        dateNgayKT.setDate(null);

    }
    
       public void chay() {
        Thread th=new Thread(){
            @Override
            public void run() {
                String txt=lbl_Chay.getText()+ " ";
                while (true) {   
                      txt = txt.charAt(txt.length() - 1) + txt.substring(0, txt.length() - 1);
                    //txt=txt.substring(1, txt.length())+txt.charAt(0);
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
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThiSP = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTimSP = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbbLocKM = new javax.swing.JComboBox<>();
        jPanel39 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTaSP = new javax.swing.JTextArea();
        jLabel69 = new javax.swing.JLabel();
        cbbTenKM = new javax.swing.JComboBox<>();
        cbbTenSP = new javax.swing.JComboBox<>();
        btnTaoSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_Chay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_Chay.setText("Chào Mừng Tất Cả Mọi Người Đến Với Cửa Hàng Trang Sức Phụ Kiện Nữ N1.Chúc Mọi Người Có Một Ngày Vui Vẻ.                                                                                                                                                      ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

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
        jLabel2.setText("Chương Trình Khuyến Mãi");

        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 51, 51))); // NOI18N

        tbHienThiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên KM", "Tên SP", "Ngày kết thúc", "Giá Giảm", "Trạng Thái"
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

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 102, 51))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Trạng Thái :");

        cbbLocKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel16)
                .addGap(23, 23, 23)
                .addComponent(cbbLocKM, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbbLocKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnTim)))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách"));

        jLabel13.setText("Tên SP :");

        jLabel79.setText("Mô Tả :");

        txtMoTaSP.setColumns(20);
        txtMoTaSP.setRows(5);
        jScrollPane2.setViewportView(txtMoTaSP);

        jLabel69.setText("Tên KM : ");

        cbbTenKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel13))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel69)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbTenSP, 0, 147, Short.MAX_VALUE)
                            .addComponent(cbbTenKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(85, 85, 85)
                        .addComponent(jLabel79)
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnTaoSP)
                        .addGap(44, 44, 44)
                        .addComponent(btnSuaSP)
                        .addGap(67, 67, 67)
                        .addComponent(jButton6)))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69)
                            .addComponent(cbbTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoSP)
                    .addComponent(btnSuaSP)
                    .addComponent(jButton6))
                .addGap(439, 439, 439))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Sản Phẩm Áp Dụng", jPanel15);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 51, 51))); // NOI18N

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
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 102, 51))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Trạng Thái :");

        cbbLocTrangThaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hạn", "Hết Hạn", " ", " " }));
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
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách"));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jLabel73)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel76)
                                .addComponent(jLabel74))
                            .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel75)
                                .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
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
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Khuyến Mãi", jPanel13);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(446, 446, 446)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1766, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(12, 12, 12))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        cbbSanPham.removeAllElements();

        //        showDataSP();
        loadComBoKM();
        loadComBoSP();
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
            if(rdHetHan.isSelected() == true){
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
        String ten = txtTimKM.getText();
        System.out.println(ten);
        List<KhuyenMai> list = khuyenMaiResponsitories.SelectbyName(ten);
        finTenKM(list);
    }//GEN-LAST:event_txtTimSPKeyReleased

    private void btnTaoKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoKMActionPerformed

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

            if(khuyenMaiResponsitories.add(getData(" "))==1){
                JOptionPane.showMessageDialog(this, "thành công");
            }
        

        loadTableKM();
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
        System.out.println("1 :" +a);
        List<KhuyenMai> list = khuyenMaiResponsitories.SelectbyTrangThai(a);
        
//        if(a.equals("Còn Hạn")){
//            finTT(a);
//        }else{
//            finTT(a);
//        }
finTrangThai(list);

        
        
    }//GEN-LAST:event_cbbLocTrangThaiKMActionPerformed

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
                new KhuyenMai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiKM;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnTaoKM;
    private javax.swing.JButton btnTaoSP;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JComboBox<String> cbbLocKM;
    private javax.swing.JComboBox<String> cbbLocTrangThaiKM;
    private javax.swing.JComboBox<String> cbbTenKM;
    private javax.swing.JComboBox<String> cbbTenSP;
    private com.toedter.calendar.JDateChooser dateNgayBD;
    private com.toedter.calendar.JDateChooser dateNgayKT;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbl_Chay;
    private javax.swing.JRadioButton rdConHan;
    private javax.swing.JRadioButton rdHetHan;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbHienThiSP;
    private javax.swing.JTextArea txtAMoTaKM;
    private javax.swing.JTextField txtGiaGiam;
    private javax.swing.JTextArea txtMoTaSP;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTimKM;
    private javax.swing.JTextField txtTimSP;
    // End of variables declaration//GEN-END:variables
}
