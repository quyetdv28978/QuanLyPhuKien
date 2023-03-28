/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domaimodel.ChatLieu;
import domaimodel.DanhMuc;
import domaimodel.SanPham;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bridj.cpp.std.list;
import respon.resSanPham;
import service.SerSanPham;
import service.serChatLieu;
import service.serDanhMuc;
import utility.JframeCheck;
import viewmodel.ChatLieuViewModel;
import viewmodel.DanhMucViewModel;
import viewmodel.SanPhamViewModel;

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

    private DefaultTableModel dtm_TT = new DefaultTableModel();
    private DefaultComboBoxModel dcmDongSP_Loc = new DefaultComboBoxModel();

    private final SerSanPham sanPhamSV = new SerSanPham();
    private final serChatLieu chatLieuSV = new serChatLieu();
    private final serDanhMuc danhMucSV = new serDanhMuc();
    private final JframeCheck jcheck = new JframeCheck();
    private final List<Object> jText = new ArrayList<>();
//    private final List<Object> jTextCV = new ArrayList<>();
//    private final DefaultComboBoxModel dccCV = new DefaultComboBoxModel();

    public viewSanPham() {
        initComponents();
        setLocationRelativeTo(null);
        tbHienThiSP.setModel(dtmSP);
        cbbDongSP_SP.setModel(dcmDongSP_SP);
        cbbChatLieu_SP.setModel(dcmChatLieu_SP);
        cbbDongSP_Loc1.setModel(dcmDongSP_Loc);
        String[] x = {"ID", "Mã", "Tên", "Màu sắc", "NSX", "Mô tả", "Gía nhập ", "Giá bán", "Trọng lượng", "Số lượng", "Danh mục", "Chất liệu", "Trạng thái", "QR"};
        dtmSP.setColumnIdentifiers(x);

        tbHienThiCL.setModel(dtmCL);
        String[] y = {"ID", "Mã", "Tên chất Liệu"};
        dtmCL.setColumnIdentifiers(y);
        tbHienThi_DSP.setModel(dtmDM);
        String[] l = {"ID", "dòng sản phẩm"};
        dtmDM.setColumnIdentifiers(l);

        String[] z = {"ID", "Mã", "Tên", "Màu sắc", "NSX", "Mô tả", "Gía nhập ", "Giá bán", "Trọng lượng", "Số lượng", "Danh mục", "Chất liệu", "Trạng thái", "QR"};
        dtmSP.setColumnIdentifiers(z);

        dcmDongSP_Loc.addElement(new DanhMucViewModel("Show"));

        showDataSP();
//        fillSP(0);
        showCbbDongSP();
        showCbbChatLieu();
        showCbbDongSP_Loc();
        showDataCL();
        showDataDM();

    }

    //Default SanPham
    private void showDataSP() {
        if (this.sanPhamSV.getALl("") != null) {

            dtmSP.setRowCount(0);
            List<SanPhamViewModel> sp = this.sanPhamSV.getALl("");
            for (SanPhamViewModel i : sp) {
                dtmSP.addRow(i.toDataRow());
            }
        }
    }

    private void showDataSP(List<viewmodel.SanPhamViewModel> l) {
        if (this.sanPhamSV.getALl("") != null) {

            dtmSP.setRowCount(0);
            List<viewmodel.SanPhamViewModel> sp = l;
            for (SanPhamViewModel i : sp) {
                dtmSP.addRow(i.toDataRow());
            }
        }
    }

    private void showCbbDongSP_Loc() {
        for (DanhMucViewModel i : this.danhMucSV.getAllLoad()) {
            dcmDongSP_Loc.addElement(i);
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
        SanPhamViewModel sp = this.sanPhamSV.getALl("").get(row);
        txtMa_SP.setText(sp.getMa());
        txtTen_SP.setText(sp.getTenSanPham());
        txtMauSac_SP.setText(sp.getMauSac());
        Image iMT = new ImageIcon(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 5).
                toString()).getImage().
                getScaledInstance(lb_MoTa.getWidth(), lb_MoTa.getHeight(), 0);
        ;
        lb_MoTa.setIcon(new ImageIcon(iMT));
        mt = tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 5).toString();
        txtNSX_SP.setText(sp.getNhaSanXuat());
        txtGiaNhap_SP.setText(Float.toString(sp.getGiaNhap()));
        txtGiaBan_SP.setText(Float.toString(sp.getGiaBan()));
        txtTrongLuong_SP.setText(Float.toString(sp.getTrongLuong()));
        txtSoLuong_SP.setText(Integer.toString(sp.getSoLuong()));
//        dcmDongSP_SP.setSelectedItem(sp.getDongsp()); 
        dcmDongSP_SP.setSelectedItem(sp.getDm());
        dcmChatLieu_SP.setSelectedItem(sp.getCl());
        Integer tt = sp.getTrangThai();
        if (tt != 0) {
            rdConHang.setSelected(true);
        } else {
            rdHetHang.setSelected(true);
        }
        Image iQR = new ImageIcon(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 13)
                .toString()).getImage()
                .getScaledInstance(lbQR_SP.getWidth(), lbQR_SP.getHeight(), 0);
        lbQR_SP.setIcon(new ImageIcon(iQR));
        dd = tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 13).toString();
        tbHienThiSP.setRowSelectionInterval(row, row);

        System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId() + "jflkajsdflks");
    }

    private void fill(int row) {
        DanhMucViewModel dm = this.danhMucSV.getAllLoad().get(row);
        lbID_DSP.setText(dm.getId());
        txtDongSP_DSP.setText(dm.getDongSP());
        tbHienThi_DSP.setRowSelectionInterval(row, row);
    }

    private void fillCL(int row) {
        ChatLieuViewModel cl = chatLieuSV.getAllLoad().get(row);
        lbID.setText(cl.getId());
        txtTenChatLieu_CL.setText(cl.getTenChatLieu());
        tbHienThiCL.setRowSelectionInterval(row, row);
    }

    private SanPhamViewModel getDataSP(String x) {
        if (x.equals("update")) {
            System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId());
            System.out.println((ChatLieuViewModel) dcmChatLieu_SP.getSelectedItem());
            return new SanPhamViewModel(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 0).toString(),
                    txtMa_SP.getText().trim(), txtTen_SP.getText().trim(), txtMauSac_SP.getText().trim(),
                    txtNSX_SP.getText().trim(), mt,
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
                txtMauSac_SP.getText().trim(), txtNSX_SP.getText().trim(), mt,
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
                    txtTenChatLieu_CL.getText().trim());
        }
        return new ChatLieuViewModel(jcheck.createID().toString(), txtTenChatLieu_CL.getText().trim());
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
        lb_MoTa.setText("");
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
        txtTenChatLieu_CL.setText("");
    }

    private boolean checkSP() {
        if (txtMa_SP.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm!");
            return false;
        }
        if (txtTen_SP.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "vui lòng nhập tên sản phẩm và chỉ nhập số và chữ");
            return false;
        }
        if (txtMauSac_SP.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập màu sắc sản phẩm!");
            return false;
        }
        if (txtNSX_SP.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nhà sản xuất sản phẩm!");
            return false;
        }
//        if (txtMota_SP.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả sản phẩm!");
//            return false;
//        }
        if (txtGiaNhap_SP.getText().trim().length() < 1 || !txtGiaNhap_SP.getText().matches("[0-9]+(.[0-9+]){0,1}")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá NHẬP sản phẩm và chỉ nhập số!");
            return false;
        }
        if (txtGiaBan_SP.getText().trim().length() < 1 || !txtGiaBan_SP.getText().matches("[0-9]+(.[0-9+]){0,1}")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá BÁN sản phẩm và chỉ nhập số!");
            return false;
        }
        if (txtTrongLuong_SP.getText().trim().length() < 1 || !txtTrongLuong_SP.getText().matches("[0-9]+(.[0-9+]){0,}")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trọng lượng sản phẩm và chỉ nhập số!");
            return false;
        }
        if (txtSoLuong_SP.getText().trim().length() < 1 || !txtSoLuong_SP.getText().matches("[0-9]+")) {
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

    private boolean checkTrungSP(String ma) {
//            for(SanPhamViewModel i: this.sanPhamSV.getALl("")){
//                if(i.getMa().equalsIgnoreCase(ma)){
//                    JOptionPane.showMessageDialog(this, "Mã đã tồn tại!");
//                }
//                return false;
//            }
//            return true;
        for (int i = 0; i < this.sanPhamSV.getALl("").size(); i++) {
            if (this.sanPhamSV.getALl("").get(i).getMa().equalsIgnoreCase(ma)) {
//                System.out.println("MAaaaaaaaaaaa; " + this.sanPhamSV.getALl("").get(i).getMa());
                if (tbHienThiSP.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
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
        jLabel40 = new javax.swing.JLabel();
        txtMa_SP = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        txtSearch_Loc = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cbbTrangThai_Loc1 = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        cbbDongSP_Loc1 = new javax.swing.JComboBox<>();
        btnTao1 = new javax.swing.JButton();
        lb_MoTa = new javax.swing.JLabel();
        lbQR_SP = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbHienThiCL = new javax.swing.JTable();
        btAddChatLieu = new javax.swing.JButton();
        btUpdateChatLieu = new javax.swing.JButton();
        btDeleteChatLieu = new javax.swing.JButton();
        btClearChatLieu = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        txtTenChatLieu_CL = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        lable = new javax.swing.JLabel();
        lbID_DSP = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtDongSP_DSP = new javax.swing.JTextField();
        btAddDSP = new javax.swing.JButton();
        btUpdateDSP = new javax.swing.JButton();
        btDeleteDelete = new javax.swing.JButton();
        btClearDSP = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHienThi_DSP = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btntrangchu = new javax.swing.JButton();
        btnbanhang = new javax.swing.JButton();
        btnnhanvien = new javax.swing.JButton();
        btnsanpham = new javax.swing.JButton();
        btnkhachhang = new javax.swing.JButton();
        btnkhuyenmai = new javax.swing.JButton();
        btmthongke = new javax.swing.JButton();
        btnlichsu = new javax.swing.JButton();
        btnqmk = new javax.swing.JButton();
        btndx = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Của Hàng Phụ Kiện Trang Sức N1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(406, 406, 406))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Chào mừng mọi người đến với của hàng phụ kiện trang sức. Chúc mọi người một ngày tốt lành!!!");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(322, 322, 322))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        txtGiaNhap_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhap_SPActionPerformed(evt);
            }
        });

        jLabel35.setText("Mô tả:");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btUpdateSP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btClearSP, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(26, 26, 26))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddSP, btClearSP, btDeleteSP, btUpdateSP});

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

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAddSP, btClearSP, btDeleteSP, btUpdateSP});

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

        jLabel40.setText("Mã SP:");

        txtMa_SP.setName("Tên sản phẩm"); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiện ích"));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("nhập TÊN sp để tìm kiếm:"));

        txtSearch_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch_LocActionPerformed(evt);
            }
        });
        txtSearch_Loc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch_LocKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch_Loc)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(txtSearch_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc - Trang thái sản phẩm"));

        jLabel27.setText("Trạng thái:");

        cbbTrangThai_Loc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Show", "Còn hàng", "Hết hàng" }));
        cbbTrangThai_Loc1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTrangThai_Loc1ItemStateChanged(evt);
            }
        });
        cbbTrangThai_Loc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThai_Loc1ActionPerformed(evt);
            }
        });
        cbbTrangThai_Loc1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbbTrangThai_Loc1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTrangThai_Loc1, 0, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cbbTrangThai_Loc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc - Dòng Sản phẩm"));

        jLabel25.setText("Dòng SP:");

        cbbDongSP_Loc1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDongSP_Loc1ItemStateChanged(evt);
            }
        });
        cbbDongSP_Loc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDongSP_Loc1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbDongSP_Loc1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbbDongSP_Loc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTao1.setText("Thêm ảnh");
        btnTao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTao1ActionPerformed(evt);
            }
        });

        lb_MoTa.setText(" ");
        lb_MoTa.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbQR_SP.setText(" ");
        lbQR_SP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Show");
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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel35))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnTao1)))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lb_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(txtMauSac_SP)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdHetHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdConHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrongLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lbQR_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addGap(460, 460, 460))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMa_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel38)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel34)
                                                    .addComponent(jLabel33)
                                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(26, 26, 26)
                                                        .addComponent(jLabel39)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnTao))
                                                    .addComponent(txtGiaBan_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addGap(188, 188, 188)
                                                                .addComponent(jLabel36))
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addComponent(txtTen_SP)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(cbbChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(cbbDongSP_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel30))
                                        .addGap(46, 46, 46))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(42, 42, 42)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGiaBan_SP, txtGiaNhap_SP, txtMa_SP, txtMauSac_SP, txtNSX_SP, txtSoLuong_SP, txtTen_SP, txtTrongLuong_SP});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel28, jLabel29, jLabel30, jLabel32, jLabel33, jLabel34, jLabel38, jLabel40});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtMa_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(cbbDongSP_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38)
                                    .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37)
                                    .addComponent(cbbChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel28)
                                            .addComponent(txtMauSac_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel29)
                                            .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(rdConHang)
                                        .addGap(3, 3, 3)
                                        .addComponent(rdHetHang)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel34)
                                    .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39)
                                    .addComponent(btnTao))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel33)
                                            .addComponent(txtGiaBan_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel32)
                                            .addComponent(txtTrongLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel30)
                                            .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lbQR_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lb_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTao1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtGiaBan_SP, txtGiaNhap_SP, txtMa_SP, txtMauSac_SP, txtNSX_SP, txtSoLuong_SP, txtTen_SP, txtTrongLuong_SP});

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

        jLabel43.setText("ID:");

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
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
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
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(863, 863, 863))
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
                            .addComponent(jLabel45)
                            .addComponent(txtTenChatLieu_CL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAddChatLieu)
                            .addComponent(btUpdateChatLieu)
                            .addComponent(btDeleteChatLieu)
                            .addComponent(btClearChatLieu)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Dòng sản  phẩm"));

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

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(18, 18, 18)
                                .addComponent(txtDongSP_DSP, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(lable, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbID_DSP, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btAddDSP)
                        .addGap(18, 18, 18)
                        .addComponent(btUpdateDSP)
                        .addGap(18, 18, 18)
                        .addComponent(btDeleteDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btClearDSP)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel18Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddDSP, btClearDSP, btDeleteDelete, btUpdateDSP});

        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(27, 27, 27)
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
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính chi tiết", jPanel8);

        jPanel11.setBackground(new java.awt.Color(255, 255, 153));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên Nhân Viên :");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("NVA");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Chức Vụ :");

        btntrangchu.setBackground(new java.awt.Color(255, 255, 153));
        btntrangchu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrangchu.setText("Trang Chủ");

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

        btmthongke.setBackground(new java.awt.Color(255, 255, 153));
        btmthongke.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btmthongke.setText("Thống Kê");
        btmthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmthongkeActionPerformed(evt);
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

        btnqmk.setBackground(new java.awt.Color(255, 255, 153));
        btnqmk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnqmk.setText("Quên Mật Khẩu");

        btndx.setBackground(new java.awt.Color(255, 255, 153));
        btndx.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndx.setText("Đăng Xuất");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btmthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlichsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnqmk, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(btndx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btntrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btmthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnlichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnqmk, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndx, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(492, 492, 492)
                        .addComponent(jLabel17))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1)
                        .addGap(28, 28, 28))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:

//        dcmChatLieu_SP.removeAllElements();
//        dcmDongSP_SP.removeAllElements();
//        showCbbChatLieu();
//        showCbbDongSP();
//        showDataSP();
//        fillSP(0);
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddSPActionPerformed
        tbHienThiSP.clearSelection();

        if (checkTrungSP(txtMa_SP.getText()) == false) {
            JOptionPane.showMessageDialog(this, "mã đã tồn tại!");
            return;
        }
        if (checkSP() == false) {
            return;
        }
        int x = JOptionPane.showConfirmDialog(this, "Xác nhận thêm sản phẩm?", "Thông báo:", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.sanPhamSV.add(getDataSP("")) == 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                showDataSP();
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
        showDataSP();

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
        if (checkSP() == false) {
            return;
        }
        if (checkTrungSP(txtMa_SP.getText()) == false) {
            JOptionPane.showMessageDialog(this, "mã đã tồn tại!");

            return;
        }
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
        dcmChatLieu_SP.removeAllElements();
        dcmDongSP_SP.removeAllElements();
        showCbbChatLieu();
        showCbbDongSP();
        showDataSP();

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
        int row = tbHienThi_DSP.getSelectedRow();
        if (row < 0)
            JOptionPane.showMessageDialog(this, "mời bạn chọn đối tượng muốn xóa!", "Thông báo:", JOptionPane.ERROR);
        else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Thông báo:", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo:", JOptionPane.INFORMATION_MESSAGE);
                danhMucSV.delete(tbHienThi_DSP.getValueAt(tbHienThi_DSP.getSelectedRow(), 0).toString());
                showDataDM();
                dcmChatLieu_SP.removeAllElements();
                dcmDongSP_SP.removeAllElements();
                showCbbChatLieu();
                showCbbDongSP();
                showDataSP();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Thông báo:", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btDeleteDeleteActionPerformed

    private void btClearDSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearDSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btClearDSPActionPerformed

    private void tbHienThi_DSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThi_DSPMouseClicked
        // TODO add your handling code here:
        fill(tbHienThi_DSP.getSelectedRow());
    }//GEN-LAST:event_tbHienThi_DSPMouseClicked

    private void txtGiaNhap_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaNhap_SPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaNhap_SPActionPerformed

    private void txtSearch_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch_LocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch_LocActionPerformed

    String mt;
    private void btnTao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTao1ActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser("icon\\Image");
        j.showOpenDialog(this);
        File f = j.getSelectedFile();

        mt = "icon\\Image\\" + f.getName();
        Image a = new ImageIcon(mt).getImage().getScaledInstance(lb_MoTa.getWidth(), lb_MoTa.getHeight(), 0);
        lb_MoTa.setIcon(new ImageIcon(a));
    }//GEN-LAST:event_btnTao1ActionPerformed

    private void btClearChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearChatLieuActionPerformed
        clearCL();
    }//GEN-LAST:event_btClearChatLieuActionPerformed

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
            dcmChatLieu_SP.removeAllElements();
            dcmDongSP_SP.removeAllElements();
            showCbbChatLieu();
            showCbbDongSP();
            showDataSP();
        }
    }//GEN-LAST:event_btDeleteChatLieuActionPerformed

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
        dcmChatLieu_SP.removeAllElements();
        dcmDongSP_SP.removeAllElements();
        showCbbChatLieu();
        showCbbDongSP();
        this.jcheck.clearView(jText, tbHienThiCL);
    }//GEN-LAST:event_btUpdateChatLieuActionPerformed

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
        dcmChatLieu_SP.removeAllElements();
        dcmDongSP_SP.removeAllElements();
        showCbbChatLieu();
        showCbbDongSP();
        showDataSP();
        //        this.jcheck.clearView(jText, tbHienThiCL);

    }//GEN-LAST:event_btAddChatLieuActionPerformed

    private void tbHienThiCLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiCLMouseClicked
        fillCL(tbHienThiCL.getSelectedRow());
    }//GEN-LAST:event_tbHienThiCLMouseClicked

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
this.dispose(); new BanHang().setVisible(true);        // TODO add your handling code here:
        this.dispose();
        new viewSanPham().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnbanhangActionPerformed

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
        this.dispose();
        new QuanLyNhanVien().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamActionPerformed
        this.dispose();
        new viewSanPham().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnsanphamActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
        this.dispose();
        new KhachHangView().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnkhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhuyenmaiActionPerformed
        this.dispose();
        new KhuyenMaiView().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnkhuyenmaiActionPerformed

    private void btmthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmthongkeActionPerformed
        this.dispose();
        new ThongKe().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btmthongkeActionPerformed

    private void btnlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlichsuActionPerformed
        this.dispose();
        new LichSu().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnlichsuActionPerformed

    private void selectByName(List<SanPhamViewModel> sp) {
        dtmSP.setRowCount(0);
        for (SanPhamViewModel i : this.sanPhamSV.selectByTen(txtSearch_Loc.getText())) {
            dtmSP.addRow(i.toDataRow());
        }
    }

    private void txtSearch_LocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch_LocKeyReleased
        // TODO add your handling code here:
        String ten = txtSearch_Loc.getText();
        List<SanPhamViewModel> sp = this.sanPhamSV.selectByTen(ten);
        selectByName(sp);
    }//GEN-LAST:event_txtSearch_LocKeyReleased

//    private void selectByNameDSP(List<SanPhamViewModel> sp) {
//        for (SanPhamViewModel i : this.sanPhamSV.selectByDongSP(cbbDongSP_Loc1.getSelectedItem().toString())) {
//            dcmChatLieu_SP.getElementAt((i.getCl().getTenChatLieu()));
//        }
//    }

    private void cbbTrangThai_Loc1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbTrangThai_Loc1KeyReleased
        // TODO add your handling code here:
//        String ten = cbbDongSP_Loc1.getSelectedItem().toString();
//        List<SanPhamViewModel> sp = this.sanPhamSV.selectByDongSP(ten);
//        selectByNameDSP(sp);
    }//GEN-LAST:event_cbbTrangThai_Loc1KeyReleased

    private void searchByTT(List<SanPhamViewModel> sp) {
//        dtm_TT.setRowCount(0);
//        for (SanPhamViewModel i : this.sanPhamSV.selectByTT(Integer.valueOf(cbbTrangThai_Loc1.getSelectedItem().toString()))) {
//            dtm_TT.addRow(i.toDataRow());
//        }

    }

    private void cbbTrangThai_Loc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThai_Loc1ActionPerformed
        // TODO add your handling code here:
//        String tt = cbbTrangThai_Loc1.getSelectedItem().toString();
//        List<SanPhamViewModel> sp = this.sanPhamSV.selectByTT(Integer.valueOf(tt));
//        searchByTT(sp);
    }//GEN-LAST:event_cbbTrangThai_Loc1ActionPerformed

    private void cbbDongSP_Loc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDongSP_Loc1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbDongSP_Loc1ActionPerformed

    private void cbbDongSP_Loc1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDongSP_Loc1ItemStateChanged
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            if (cbbDongSP_Loc1.getSelectedIndex() == 0) {
                showDataSP();
            } else {
                showDataSP(new SerSanPham().selectByDongSP(((DanhMucViewModel) cbbDongSP_Loc1.getSelectedItem()).getDongSP()));
            }
        }
    }//GEN-LAST:event_cbbDongSP_Loc1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showDataSP();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbTrangThai_Loc1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTrangThai_Loc1ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            int tt = 0;
            if (cbbTrangThai_Loc1.getSelectedItem().toString().equals("Còn hàng")) {
                tt = 1;
                showDataSP(new SerSanPham().selectByTT((tt)));
            } else if (cbbTrangThai_Loc1.getSelectedItem().toString().equals("Hết hàng")) {
                tt = 0;
                showDataSP(new SerSanPham().selectByTT((tt)));
            } else {
                showDataSP();
            }

        }
    }//GEN-LAST:event_cbbTrangThai_Loc1ItemStateChanged

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
            java.util.logging.Logger.getLogger(viewSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btmthongke;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnTao1;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btndx;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnkhuyenmai;
    private javax.swing.JButton btnlichsu;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnqmk;
    private javax.swing.JButton btnsanpham;
    private javax.swing.JButton btntrangchu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu_SP;
    private javax.swing.JComboBox<String> cbbDongSP_Loc1;
    private javax.swing.JComboBox<String> cbbDongSP_SP;
    private javax.swing.JComboBox<String> cbbTrangThai_Loc1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lable;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbID_DSP;
    private javax.swing.JLabel lbQR_SP;
    private javax.swing.JLabel lb_MoTa;
    private javax.swing.JRadioButton rdConHang;
    private javax.swing.JRadioButton rdHetHang;
    private javax.swing.JTable tbHienThiCL;
    private javax.swing.JTable tbHienThiSP;
    private javax.swing.JTable tbHienThi_DSP;
    private javax.swing.JTextField txtDongSP_DSP;
    private javax.swing.JTextField txtGiaBan_SP;
    private javax.swing.JTextField txtGiaNhap_SP;
    private javax.swing.JTextField txtMa_SP;
    private javax.swing.JTextField txtMauSac_SP;
    private javax.swing.JTextField txtNSX_SP;
    private javax.swing.JTextField txtSearch_Loc;
    private javax.swing.JTextField txtSoLuong_SP;
    private javax.swing.JTextField txtTenChatLieu_CL;
    private javax.swing.JTextField txtTen_SP;
    private javax.swing.JTextField txtTrongLuong_SP;
    // End of variables declaration//GEN-END:variables
}
