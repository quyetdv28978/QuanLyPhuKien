/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domainModel.ChatLieu;
import domainModel.DanhMuc;
import domainModel.SanPham;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import respository.resSanPham;
import service.SerSanPham;
import service.serChatLieu;
import service.serDanhMuc;
import utility.jframeCheck;
import viewModel.ChatLieuViewModel;
import viewModel.DanhMucViewModel;
import viewModel.SanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public class viewSanPham extends javax.swing.JFrame {

    private DefaultTableModel dtmSP = new DefaultTableModel();
    private DefaultTableModel dtmDM = new DefaultTableModel();
    private DefaultTableModel dtmCL = new DefaultTableModel();

    private DefaultComboBoxModel dcmDongSP_SP = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcmChatLieu_SP = new DefaultComboBoxModel();

    private final SerSanPham sanPhamSV = new SerSanPham();
    private final serChatLieu chatLieuSV = new serChatLieu();
    private final serDanhMuc danhMucSV = new serDanhMuc();
    private final jframeCheck jcheck = new jframeCheck();
    private final List<Object> jText = new ArrayList<>();
//    private final List<Object> jTextCV = new ArrayList<>();
//    private final DefaultComboBoxModel dccCV = new DefaultComboBoxModel();

    public viewSanPham() {
        initComponents();
        setLocationRelativeTo(null);
        tbHienThiSP.setModel(dtmSP);
        cbbDongSP_SP.setModel(dcmDongSP_SP);
        cbbChatLieu_SP.setModel(dcmChatLieu_SP);
        String[] x = {"ID", "Mã", "Tên", "Màu sắc", "NSX", "Mô tả", "Gía nhập ", "Giá bán", "Trọng lượng", "Số lượng", "Danh mục", "Chất liệu", "Trạng thái", "QR"};
        dtmSP.setColumnIdentifiers(x);

        tbHienThiCL.setModel(dtmCL);
        String[] y = {"ID", "Mã", "Tên chất Liệu"};
        dtmCL.setColumnIdentifiers(y);
        tbHienThi_DSP.setModel(dtmDM);
        String[] l = {"ID", "dòng sản phẩm"};
        dtmDM.setColumnIdentifiers(l);

        tbHienThiSP_Loc.setModel(dtmSP);
        String[] z = {"ID", "Mã", "Tên", "Màu sắc", "NSX", "Mô tả", "Gía nhập ", "Giá bán", "Trọng lượng", "Số lượng", "Danh mục", "Chất liệu", "Trạng thái", "QR"};
        cbbDongSP_Loc.setModel(dcmDongSP_SP);
        dtmSP.setColumnIdentifiers(z);

        showDataSP();
//        fillSP(0);
        showCbbDongSP();
        showCbbChatLieu();
        showDataCL();
        showDataDM();

    }

    //Default SanPham
    private void showDataSP() {
        dtmSP.setRowCount(0);
        List<SanPhamViewModel> sp = this.sanPhamSV.getAll("");
        for (SanPhamViewModel i : sp) {
            dtmSP.addRow(i.toDataRow());
        }
    }

    private void showCbbDongSP() {
        for (DanhMucViewModel i : this.danhMucSV.getAllLoad()) {
            dcmDongSP_SP.addElement(i);
        }
    }

    private void showCbbChatLieu() {
        for (ChatLieuViewModel i : this.chatLieuSV.getAllLoad()) {
            dcmChatLieu_SP.addElement(i);
        }
    }

    //Default ChatLieu
    private void showDataCL() {
        dtmCL.setRowCount(0);
        List<ChatLieuViewModel> cl = this.chatLieuSV.getAllLoad();
        for (ChatLieuViewModel i : cl) {
            dtmCL.addRow(i.toDataRow());
        }
    }

    //Default DanhMuc
    private void showDataDM() {
        dtmDM.setRowCount(0);
        List<DanhMucViewModel> dm = this.danhMucSV.getAllLoad();
        for (DanhMucViewModel i : dm) {
            dtmDM.addRow(i.toDataRow());
        }
    }

    private void fillSP(int row) {
        SanPhamViewModel sp = this.sanPhamSV.getAll("").get(row);
        txtMa_SP.setText(sp.getMa());
        txtTen_SP.setText(sp.getTenSanPham());
        txtMauSac_SP.setText(sp.getMauSac());
        txtMota_SP.setText(sp.getMoTa());
        txtNSX_SP.setText(sp.getNhaSanXuat());
        txtGiaNhap_SP.setText(Float.toString(sp.getGiaNhap()));
        txtGiaBan_SP.setText(Float.toString(sp.getGiaBan()));
        txtTrongLuong_SP.setText(Float.toString(sp.getTrongLuong()));
        txtSoLuong_SP.setText(Integer.toString(sp.getSoLuong()));
//        dcmDongSP_SP.setSelectedItem(sp.getDongsp()); 
        dcmDongSP_SP.setSelectedItem(sp.getDm());
        dcmChatLieu_SP.setSelectedItem(sp.getCl().getTenChatLieu());
        Integer tt = sp.getTrangThai();
        if (tt != 0) {
            rdConHang.setSelected(true);
        } else {
            rdHetHang.setSelected(true);
        }
        Image i = new ImageIcon(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 13)
                .toString()).getImage()
                .getScaledInstance(lbQR_SP.getWidth(), lbQR_SP.getHeight(), 0);
        lbQR_SP.setIcon(new ImageIcon(i));
        tbHienThiSP.setRowSelectionInterval(row, row);

        System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId() + "jflkajsdflks");
    }
    
    private void fill(int row) {
        DanhMucViewModel dm=this.danhMucSV.getAllLoad().get(row);
        lbID_DSP.setText(dm.getId());
        txtDongSP_DSP.setText(dm.getDongSP());
        tbHienThi_DSP.setRowSelectionInterval(row, row);
    }

    private void fillCL(int row) {
        ChatLieuViewModel cl = chatLieuSV.getAllLoad().get(row);
        lbID.setText(cl.getId());
        lbMa.setText(cl.getMa());
        txtTenChatLieu_CL.setText(cl.getTenChatLieu());
        tbHienThiCL.setRowSelectionInterval(row, row);
    }

    private void fillDM(int row) {

    }

    private SanPhamViewModel getDataSP(String x) {
        if (x.equals("update")) {
            System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId());
            System.out.println((ChatLieuViewModel) dcmChatLieu_SP.getSelectedItem());
            return new SanPhamViewModel(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 0).toString(),
                    txtMa_SP.getText().trim(), txtTen_SP.getText().trim(), txtMauSac_SP.getText().trim(),
                    txtNSX_SP.getText().trim(), txtMota_SP.getText().trim(),
                    Float.valueOf(txtGiaNhap_SP.getText().trim()), Float.valueOf(txtGiaBan_SP.getText().trim()),
                    Float.valueOf(txtTrongLuong_SP.getText().trim()), Integer.valueOf(txtSoLuong_SP.getText().trim()),
                    (DanhMucViewModel) dcmDongSP_SP.getSelectedItem(),
                    (ChatLieuViewModel) dcmChatLieu_SP.getSelectedItem(),
                    rdHetHang.isSelected() == true ? 0 : 1,
                    dd);
        }
//        System.out.println(((DanhMucViewModel) cbbDongSP_SP.getSelectedItem()).getId());
//        System.out.println(((ChatLieuViewModel) cbbChatLieu_SP.getSelectedItem()).getId());
        return new SanPhamViewModel(jcheck.createID().toString(), txtMa_SP.getText().trim(), txtTen_SP.getText().trim(),
                txtMauSac_SP.getText().trim(), txtNSX_SP.getText().trim(), txtMota_SP.getText().trim(),
                Float.valueOf(txtGiaNhap_SP.getText().trim()), Float.valueOf(txtGiaBan_SP.getText().trim()),
                Float.valueOf(txtTrongLuong_SP.getText().trim()), Integer.valueOf(txtSoLuong_SP.getText().trim()),
                (DanhMucViewModel) dcmDongSP_SP.getSelectedItem(),
                (ChatLieuViewModel) dcmChatLieu_SP.getSelectedItem(),
                rdHetHang.isSelected() == true ? 0 : 1,
                dd);

    }

    private ChatLieuViewModel getDataCL(String x) {
        if (x.equals("update")) {
            return new ChatLieuViewModel(tbHienThiCL.getValueAt(tbHienThiCL.getSelectedRow(), 0).toString(),
                    tbHienThiCL.getValueAt(tbHienThiCL.getSelectedRow(), 1).toString(), txtTenChatLieu_CL.getText().trim());
        }
        return new ChatLieuViewModel(jcheck.createID().toString(), jcheck.randomMA(), txtTenChatLieu_CL.getText().trim());
    }

    private DanhMucViewModel getDataDM(String x) {
        if (x.equals("update")) {
            
            return new DanhMucViewModel(tbHienThi_DSP.getValueAt(tbHienThi_DSP.getSelectedRow(), 0).toString(),
                    txtDongSP_DSP.getText().trim());
        }
        return new DanhMucViewModel(jcheck.createID().toString(), txtDongSP_DSP.getText().trim());
    }

    private void clearSP() {
        txtMa_SP.setText("");
        txtTen_SP.setText("");
        txtMauSac_SP.setText("");
        txtNSX_SP.setText("");
        txtMota_SP.setText("");
        txtGiaNhap_SP.setText("");
        txtGiaBan_SP.setText("");
        txtTrongLuong_SP.setText("");
        txtSoLuong_SP.setText("");
        cbbDongSP_SP.setSelectedIndex(0);
        cbbChatLieu_SP.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        lbQR_SP.setText("");
    }

    private void clearCL() {
        lbID.setText("");
        lbMa.setText("");
        txtTenChatLieu_CL.setText("");
    }

    private boolean checkSP() {
        if (txtMa_SP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!");
            return false;
        }
        if (txtTen_SP.getText().isEmpty() || !txtTen_SP.getText().matches("[A-Za-z0-9]+")) {
            JOptionPane.showMessageDialog(this, "vui lòng nhập tên sản phẩm và chỉ nhập số và chữ");
            return false;
        }
        if (txtMauSac_SP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập màu sắc sản phẩm!");
            return false;
        }
        if (txtNSX_SP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nhà sản xuất sản phẩm!");
            return false;
        }
        if (txtMota_SP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả sản phẩm!");
            return false;
        }
        if (txtGiaNhap_SP.getText().isEmpty() || !txtGiaNhap_SP.getText().matches("[0-9]+(.[0-9+]){0,1}")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá NHẬP sản phẩm và chỉ nhập số!");
            return false;
        }
        if (txtGiaBan_SP.getText().isEmpty() || !txtGiaBan_SP.getText().matches("[0-9]+(.[0-9+]){0,1}")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá BÁN sản phẩm và chỉ nhập số!");
            return false;
        }
        if (txtTrongLuong_SP.getText().isEmpty() || !txtTrongLuong_SP.getText().matches("[0-9]+(.[0-9+]){0,}")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng sản phẩm và chỉ nhập số!");
            return false;
        }
        if (txtSoLuong_SP.getText().isEmpty() || !txtSoLuong_SP.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng sản phẩm và chỉ nhập số nguyên!");
            return false;
        }
        return true;
    }

    private boolean checkDM() {
        if (txtDongSP_DSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!");
            return false;
        }
        return true;
    }

    private boolean checkCL() {
        if (txtTenChatLieu_CL.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        txtTen_SP = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtMauSac_SP = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtNSX_SP = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtSoLuong_SP = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        rdConHang = new javax.swing.JRadioButton();
        txtTrongLuong_SP = new javax.swing.JTextField();
        rdHetHang = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        txtGiaBan_SP = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtGiaNhap_SP = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMota_SP = new javax.swing.JTextArea();
        cbbDongSP_SP = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cbbChatLieu_SP = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btAddSP = new javax.swing.JButton();
        btUpdateSP = new javax.swing.JButton();
        btDeleteSP = new javax.swing.JButton();
        btClearSP = new javax.swing.JButton();
        btnTao = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHienThiSP = new javax.swing.JTable();
        lbQR_SP = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtMa_SP = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbHienThiCL = new javax.swing.JTable();
        btAddChatLieu = new javax.swing.JButton();
        btUpdateChatLieu = new javax.swing.JButton();
        btDeleteChatLieu = new javax.swing.JButton();
        btClearChatLieu = new javax.swing.JButton();
        lbMa = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        txtTenChatLieu_CL = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHienThi_DSP = new javax.swing.JTable();
        lable = new javax.swing.JLabel();
        lbID_DSP = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtDongSP_DSP = new javax.swing.JTextField();
        btAddDSP = new javax.swing.JButton();
        btUpdateDSP = new javax.swing.JButton();
        btDeleteDelete = new javax.swing.JButton();
        btClearDSP = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtSearch_Loc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cbbDongSP_Loc = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cbbTrangThai_Loc = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbHienThiSP_Loc = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Tên nhân viên:");

        jLabel3.setText("Trần Tùng");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Ảnh đại diện");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );

        jLabel4.setText("Chức vụ:");

        jPanel9.setBackground(new java.awt.Color(255, 255, 0));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Sản phẩm");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Bán hàng");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nhân viên");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Khách hàng");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Khuyến mãi");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Thống kê");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Đổi mật khẩu");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Lịch sử");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Đăng xuất");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel15))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel12, jLabel13, jLabel14, jLabel16, jLabel8, jLabel9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel8, jLabel9});

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Của Hàng Phụ Kiện Trang Sức N1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(311, 311, 311))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Chào mừng mọi người đến với của hàng phụ kiện trang sức. Chúc mọi người một ngày tốt lành!!!");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(244, 244, 244))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Quản lý sản phẩm");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        txtTen_SP.setName("Tên sản phẩm"); // NOI18N

        jLabel28.setText("Màu sắc: ");

        txtMauSac_SP.setName("Màu sắc"); // NOI18N

        jLabel29.setText("Nhà sản xuất:");

        txtNSX_SP.setName("Nhà sản xuất"); // NOI18N

        jLabel30.setText("Số lượng");

        txtSoLuong_SP.setName("Số lượng"); // NOI18N
        txtSoLuong_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuong_SPActionPerformed(evt);
            }
        });

        jLabel31.setText("Trang thai: ");

        jLabel32.setText("Trọng lượng:");

        buttonGroup1.add(rdConHang);
        rdConHang.setSelected(true);
        rdConHang.setText("Còn hàng");

        txtTrongLuong_SP.setName("Trọng lượng"); // NOI18N

        buttonGroup1.add(rdHetHang);
        rdHetHang.setText("Hết hàng");

        jLabel33.setText("Giá bán:");

        txtGiaBan_SP.setName("Giá bán"); // NOI18N

        jLabel34.setText("Giá nhập:");

        txtGiaNhap_SP.setName("Giá nhập"); // NOI18N

        jLabel35.setText("Mô tả:");

        txtMota_SP.setColumns(20);
        txtMota_SP.setRows(5);
        txtMota_SP.setName("Mô tả"); // NOI18N
        jScrollPane2.setViewportView(txtMota_SP);

        cbbDongSP_SP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDongSP_SP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDongSP_SPItemStateChanged(evt);
            }
        });

        jLabel36.setText("Dòng SP:");

        jLabel37.setText("Chất liệu:");

        cbbChatLieu_SP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel38.setText("Tên sản phẩm: ");

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btAddSP.setText("Add");
        btAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddSPActionPerformed(evt);
            }
        });

        btUpdateSP.setText("Update");
        btUpdateSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateSPActionPerformed(evt);
            }
        });

        btDeleteSP.setText("Delete");
        btDeleteSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteSPActionPerformed(evt);
            }
        });

        btClearSP.setText("Clear");
        btClearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btAddSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDeleteSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btUpdateSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btClearSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddSP)
                    .addComponent(btUpdateSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDeleteSP)
                    .addComponent(btClearSP))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnTao.setText("Tạo mã");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        jLabel39.setText("QR:");

        tbHienThiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ma", "Tên sản phẩm", "Màu sắc", "Nhà sản xuất", "Ngày nhập", "Số lượng", "Giá nhập", "Giá bán", "Trọng lượng", "Danh mục", "Chất liệu", "Mô tả", "QR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true
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

        lbQR_SP.setText(" ");

        jLabel40.setText("Mã SP:");

        txtMa_SP.setName("Tên sản phẩm"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel28))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMauSac_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtTen_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtMa_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                .addGap(50, 50, 50))
                            .addComponent(jLabel30)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaBan_SP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrongLuong_SP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdHetHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbbDongSP_SP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbbChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rdConHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(41, 41, 41))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lbQR_SP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtMauSac_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbQR_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTao)))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel34)
                                    .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40)
                                    .addComponent(txtMa_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(txtGiaBan_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbDongSP_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel37)
                                    .addComponent(cbbChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel32)
                                .addComponent(txtTrongLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(rdConHang))
                                .addGap(3, 3, 3)
                                .addComponent(rdHetHang)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel39))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Chất Liệu"));

        tbHienThiCL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbHienThiCL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiCLMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbHienThiCL);

        btAddChatLieu.setText("Add");
        btAddChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddChatLieuActionPerformed(evt);
            }
        });

        btUpdateChatLieu.setText("Update");
        btUpdateChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateChatLieuActionPerformed(evt);
            }
        });

        btDeleteChatLieu.setText("Delete");
        btDeleteChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteChatLieuActionPerformed(evt);
            }
        });

        btClearChatLieu.setText("Clear");
        btClearChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearChatLieuActionPerformed(evt);
            }
        });

        lbMa.setText("  ");

        jLabel43.setText("ID:");

        jLabel44.setText("Mã:");

        jLabel45.setText("Tên chất liệu:");

        lbID.setText("  ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lbMa, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel45)
                            .addGap(18, 18, 18)
                            .addComponent(txtTenChatLieu_CL, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btAddChatLieu)
                        .addGap(18, 18, 18)
                        .addComponent(btUpdateChatLieu)
                        .addGap(18, 18, 18)
                        .addComponent(btDeleteChatLieu)
                        .addGap(18, 18, 18)
                        .addComponent(btClearChatLieu)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddChatLieu, btClearChatLieu, btDeleteChatLieu, btUpdateChatLieu});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(lbID))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(lbMa))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(txtTenChatLieu_CL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAddChatLieu)
                            .addComponent(btUpdateChatLieu)
                            .addComponent(btDeleteChatLieu)
                            .addComponent(btClearChatLieu)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Dòng sản  phẩm"));

        tbHienThi_DSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbHienThi_DSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThi_DSPMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbHienThi_DSP);

        lable.setText("ID");

        lbID_DSP.setText(" ");

        jLabel41.setText("Dòng Sản phẩm:");

        btAddDSP.setText("Add");
        btAddDSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddDSPActionPerformed(evt);
            }
        });

        btUpdateDSP.setText("Update");
        btUpdateDSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateDSPActionPerformed(evt);
            }
        });

        btDeleteDelete.setText("Delete");
        btDeleteDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteDeleteActionPerformed(evt);
            }
        });

        btClearDSP.setText("Clear");
        btClearDSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearDSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(lable, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDongSP_DSP)
                            .addComponent(lbID_DSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btAddDSP)
                        .addGap(18, 18, 18)
                        .addComponent(btUpdateDSP)
                        .addGap(18, 18, 18)
                        .addComponent(btDeleteDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btClearDSP)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddDSP, btClearDSP, btDeleteDelete, btUpdateDSP});

        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lable)
                            .addComponent(lbID_DSP))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtDongSP_DSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAddDSP)
                            .addComponent(btUpdateDSP)
                            .addComponent(btDeleteDelete)
                            .addComponent(btClearDSP))
                        .addGap(0, 59, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính chi tiết", jPanel8);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm:"));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("nhập mã sp để tìm kiếm:"));

        txtSearch_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch_LocActionPerformed(evt);
            }
        });

        jButton1.setText("Search");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel24.setText("Dòng SP:");

        cbbDongSP_Loc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhẫn", "Dây chuyền", "Lắc tay", "Bông tai" }));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(cbbDongSP_Loc, 0, 154, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbbDongSP_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel26.setText("Trạng thái:");

        cbbTrangThai_Loc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn", "Hết", "Chưa rõ" }));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTrangThai_Loc, 0, 151, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(cbbTrangThai_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(75, 75, 75)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbHienThiSP_Loc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ma", "Tên sản phẩm", "Màu sắc", "Nhà sản xuất", "Ngày nhập", "Số lượng", "Giá nhập", "Giá bán", "Trọng lượng", "Danh mục", "Chất liệu", "Mô tả", "QR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHienThiSP_Loc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiSP_LocMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbHienThiSP_Loc);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(164, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Tiện ích", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(312, 312, 312)
                                .addComponent(jLabel17)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
//        new viewChatLieu().setVisible(true);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void txtSearch_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch_LocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch_LocActionPerformed

    private void txtSoLuong_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuong_SPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuong_SPActionPerformed

    String dd;

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        JFileChooser j = new JFileChooser("QRCODE");
        j.showOpenDialog(this);
        File f = j.getSelectedFile();

        dd = "QRCODE\\" + f.getName();
        Image a = new ImageIcon(dd).getImage().getScaledInstance(lbQR_SP.getWidth(), lbQR_SP.getHeight(), 0);
        lbQR_SP.setIcon(new ImageIcon(a));
    }//GEN-LAST:event_btnTaoActionPerformed

    private void tbHienThiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiSPMouseClicked
//        jcheck.clickTable(jtextSP, tbHienThiSP);
        fillSP(tbHienThiSP.getSelectedRow());
    }//GEN-LAST:event_tbHienThiSPMouseClicked

    private void tbHienThiCLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiCLMouseClicked
        fillCL(tbHienThiCL.getSelectedRow());
    }//GEN-LAST:event_tbHienThiCLMouseClicked

    private void btAddChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddChatLieuActionPerformed
        // TODO add your handling code here:
//        if (jcheck.checkData(jText, this) == false) {
//            return;
//        }
//        if (jcheck.checkDinhDang(jText, new String[]{"0[0-9]{9}"}, new int[]{20, 40}, this) == 0) {
//            return;
//        }
        int x = JOptionPane.showConfirmDialog(this, "Xác nhận thêm chất liệu?", "Thông báo:", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.chatLieuSV.add(getDataCL("")) == 1) {
                return;
            }
        }

        showDataCL();
//        this.jcheck.clearView(jText, tbHienThiCL);


    }//GEN-LAST:event_btAddChatLieuActionPerformed

    private void btUpdateChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateChatLieuActionPerformed
        // TODO add your handling code here:
//        if (jcheck.checkData(jText, this) == false) {
//            return;
//        }
//        if (jcheck.checkDinhDang(jText, new String[]{"0[0-9]{9}"}, new int[]{20, 40}, this) == 0) {
//            return;
//        }
        int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không?", "Thông báo:", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.chatLieuSV.update(getDataCL("update")) == 1) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
                return;
            }
            clearCL();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại!");
        }

        showDataCL();
        this.jcheck.clearView(jText, tbHienThiCL);
    }//GEN-LAST:event_btUpdateChatLieuActionPerformed

    private void btDeleteChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteChatLieuActionPerformed
        // TODO add your handling code here:
        int row = tbHienThiCL.getSelectedRow();
        if (row < 0)
            JOptionPane.showMessageDialog(this, "Mời bạn chọn đối tượng muốn xóa!");
        else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo:", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                chatLieuSV.delete(tbHienThiCL.getValueAt(tbHienThiCL.getSelectedRow(), 0).toString());
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
            clearCL();
            showDataCL();
        }
    }//GEN-LAST:event_btDeleteChatLieuActionPerformed

    private void btClearChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearChatLieuActionPerformed
        clearCL();
    }//GEN-LAST:event_btClearChatLieuActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:

        dcmChatLieu_SP.removeAllElements();
        dcmDongSP_SP.removeAllElements();
        showCbbChatLieu();
        showCbbDongSP();
        showDataSP();
//        fillSP(0);


    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddSPActionPerformed
        // TODO add your handling code here:
        if (checkSP()) {
            int x = JOptionPane.showConfirmDialog(this, "Xác nhận thêm sản phẩm?", "Thông báo:", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (this.sanPhamSV.add(getDataSP("")) == 1) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
            showDataSP();
        }
    }//GEN-LAST:event_btAddSPActionPerformed

    private void btDeleteSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteSPActionPerformed
        // TODO add your handling code here:
        int row = tbHienThiSP.getSelectedRow();
        if (row < 0)
            JOptionPane.showMessageDialog(this, "Mời bạn chọn đối tượng muốn xóa!");
        else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo:", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                sanPhamSV.delete(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 0).toString());
                clearSP();
                showDataSP();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }

        }
    }//GEN-LAST:event_btDeleteSPActionPerformed

    private void btUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateSPActionPerformed
        // TODO add your handling code here:
        System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId());
        int x = JOptionPane.showConfirmDialog(this, "Xác nhận Sửa?", "Thông báo:", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.sanPhamSV.update(getDataSP("update")) == 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
                clearSP();
                showDataSP();
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại!");
        }


    }//GEN-LAST:event_btUpdateSPActionPerformed

    private void btClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearSPActionPerformed
        // TODO add your handling code here:
        clearSP();
    }//GEN-LAST:event_btClearSPActionPerformed

    private void cbbDongSP_SPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDongSP_SPItemStateChanged
//        System.out.println(((DanhMucViewModel)dcmDongSP_SP.getSelectedItem()).getId());        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDongSP_SPItemStateChanged

    private void btAddDSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddDSPActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this, "Xác nhận thêm " + txtDongSP_DSP.getText().trim() + " ?", "Thông báo:", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.danhMucSV.add(getDataDM("")) != 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }

        showDataDM();
    }//GEN-LAST:event_btAddDSPActionPerformed

    private void btUpdateDSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateDSPActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this, "Xác nhận sửa " + txtDongSP_DSP.getText().trim() + " ?", "Thông báo:", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.danhMucSV.update(getDataDM("update")) != 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại!");
        }

        showDataDM();
    }//GEN-LAST:event_btUpdateDSPActionPerformed

    private void btDeleteDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDeleteDeleteActionPerformed

    private void btClearDSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearDSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btClearDSPActionPerformed

    private void tbHienThiSP_LocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiSP_LocMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbHienThiSP_LocMouseClicked

    private void tbHienThi_DSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThi_DSPMouseClicked
        // TODO add your handling code here:
        fill(tbHienThi_DSP.getSelectedRow());
    }//GEN-LAST:event_tbHienThi_DSPMouseClicked

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
            java.util.logging.Logger.getLogger(viewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddChatLieu;
    private javax.swing.JButton btAddDSP;
    private javax.swing.JButton btAddSP;
    private javax.swing.JButton btClearChatLieu;
    private javax.swing.JButton btClearDSP;
    private javax.swing.JButton btClearSP;
    private javax.swing.JButton btDeleteChatLieu;
    private javax.swing.JButton btDeleteDelete;
    private javax.swing.JButton btDeleteSP;
    private javax.swing.JButton btUpdateChatLieu;
    private javax.swing.JButton btUpdateDSP;
    private javax.swing.JButton btUpdateSP;
    private javax.swing.JButton btnTao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu_SP;
    private javax.swing.JComboBox<String> cbbDongSP_Loc;
    private javax.swing.JComboBox<String> cbbDongSP_SP;
    private javax.swing.JComboBox<String> cbbTrangThai_Loc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lable;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbID_DSP;
    private javax.swing.JLabel lbMa;
    private javax.swing.JLabel lbQR_SP;
    private javax.swing.JRadioButton rdConHang;
    private javax.swing.JRadioButton rdHetHang;
    private javax.swing.JTable tbHienThiCL;
    private javax.swing.JTable tbHienThiSP;
    private javax.swing.JTable tbHienThiSP_Loc;
    private javax.swing.JTable tbHienThi_DSP;
    private javax.swing.JTextField txtDongSP_DSP;
    private javax.swing.JTextField txtGiaBan_SP;
    private javax.swing.JTextField txtGiaNhap_SP;
    private javax.swing.JTextField txtMa_SP;
    private javax.swing.JTextField txtMauSac_SP;
    private javax.swing.JTextArea txtMota_SP;
    private javax.swing.JTextField txtNSX_SP;
    private javax.swing.JTextField txtSearch_Loc;
    private javax.swing.JTextField txtSoLuong_SP;
    private javax.swing.JTextField txtTenChatLieu_CL;
    private javax.swing.JTextField txtTen_SP;
    private javax.swing.JTextField txtTrongLuong_SP;
    // End of variables declaration//GEN-END:variables
}
