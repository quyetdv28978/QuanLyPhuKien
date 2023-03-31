/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domaiModel.SanPham;
import java.awt.Image;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.SerSanPham;
import service.serChatLieu;
import service.serDanhMuc;
import utility.jframeCheck;
import viewModel.ChatLieuViewModel;
import viewModel.DanhMucViewModel;
import viewModel.SanPhamViewModel;

/**
 *
 * @author DELL
 */
public class ViewSanPhamm extends javax.swing.JFrame {

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

    public ViewSanPhamm() {
        initComponents();
        setLocationRelativeTo(null);
        chay();
        setLocationRelativeTo(null);
        tbHienThiSP.setModel(dtmSP);
        cbbDongSP_SP.setModel(dcmDongSP_SP);
        cbbChatLieu_SP.setModel(dcmChatLieu_SP);
        String[] x = {"ID", "Mã", "Tên", "Màu sắc", "NSX", "Mô tả", "Gía nhập ", "Giá bán", "Trọng lượng", "Số lượng", "Danh mục", "Chất liệu", "Trạng thái", "QR"};
        dtmSP.setColumnIdentifiers(x);

        tbHienThiCL.setModel(dtmCL);
        String[] y = {"ID", "Tên chất Liệu"};
        dtmCL.setColumnIdentifiers(y);
        tbHienThi_DSP.setModel(dtmDM);
        String[] l = {"ID", "dòng sản phẩm"};
        dtmDM.setColumnIdentifiers(l);

        String[] z = {"ID", "Mã", "Tên", "Màu sắc", "NSX", "Mô tả", "Gía nhập ", "Giá bán", "Trọng lượng", "Số lượng", "Danh mục", "Chất liệu", "Trạng thái", "QR"};
        dtmSP.setColumnIdentifiers(z);

        showDataSP();
//        fillSP(0);
        showCbbDongSP();
        showCbbChatLieu();
        showDataCL();
        showDataDM();
    }

    public void chay() {
        Thread th = new Thread() {
            @Override
            public void run() {
                String txt = lbl_chay.getText() + " ";
                while (true) {
                    txt = txt.charAt(txt.length() - 1) + txt.substring(0, txt.length() - 1);
                    //txt=txt.substring(1, txt.length())+txt.charAt(0);
                    try {
                        sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbl_chay.setText(txt);
                }
            }

        };
        th.start();
    }

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

    private void fillDM(int row) {

    }

    private SanPhamViewModel getDataSP(String x) {
        if (x.equals("update")) {
//            System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId());
//            System.out.println((ChatLieuViewModel) dcmChatLieu_SP.getSelectedItem());
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
            return new ChatLieuViewModel(tbHienThiCL.getValueAt(tbHienThiCL.getSelectedRow(), 0).toString(), txtTenChatLieu_CL.getText().trim());
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
            JOptionPane.showMessageDialog(this, "vui lòng nhập tên sản phẩm");
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
        for (SanPhamViewModel i : this.sanPhamSV.getAll("")) {
            if (i.getMa().equalsIgnoreCase(ma)) {
                JOptionPane.showMessageDialog(this, "mã sản phẩm đã tồn tại!");
            }
            return false;
        }
        return true;
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jLabel46 = new javax.swing.JLabel();
        txtDongSP_DSP = new javax.swing.JTextField();
        btAddDSP = new javax.swing.JButton();
        btUpdateDSP = new javax.swing.JButton();
        btDeleteDelete = new javax.swing.JButton();
        btClearDSP = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHienThi_DSP = new javax.swing.JTable();
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
        jLabel42 = new javax.swing.JLabel();
        txtMa_SP = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        txtSearch_Loc = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cbbTrangThai_Loc1 = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        cbbDongSP_Loc1 = new javax.swing.JComboBox<>();
        btnTao1 = new javax.swing.JButton();
        lb_MoTa = new javax.swing.JLabel();
        lbQR_SP = new javax.swing.JLabel();
        lbl_chay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));
        jPanel3.setForeground(new java.awt.Color(255, 255, 153));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Tên Nhân Viên");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Chức Vụ :");

        jButton22.setText("jButton22");

        jButton23.setText("jButton23");

        jButton24.setText("jButton24");

        jButton25.setText("jButton25");

        jButton26.setText("jButton26");

        jButton27.setText("jButton27");

        jButton28.setText("jButton28");

        jButton29.setText("jButton29");

        jButton30.setText("jButton30");

        jButton31.setText("jButton31");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41)
                    .addComponent(jLabel40)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 51));
        jLabel2.setText("Quản Lý Sản Phẩm");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

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
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(lbID))
                        .addGap(30, 30, 30)
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

        lable.setText("ID");

        lbID_DSP.setText(" ");

        jLabel46.setText("Dòng Sản phẩm:");

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
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(lable, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDongSP_DSP, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(lbID_DSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable)
                    .addComponent(lbID_DSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
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
                .addContainerGap(240, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính chi tiết", jPanel8);

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
        rdHetHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdHetHangActionPerformed(evt);
            }
        });

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

        jLabel42.setText("Mã SP:");

        txtMa_SP.setName("Tên sản phẩm"); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiện ích"));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("nhập mã sp để tìm kiếm:"));

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

        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setText("Trạng thái:");

        cbbTrangThai_Loc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn", "Hết", "Chưa rõ" }));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTrangThai_Loc1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cbbTrangThai_Loc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel25.setText("Dòng SP:");

        cbbDongSP_Loc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhẫn", "Dây chuyền", "Lắc tay", "Bông tai" }));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(cbbDongSP_Loc1, 0, 145, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(138, 138, 138))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(lb_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel42))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMa_SP)
                                        .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaBan_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel30))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrongLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel38)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(36, 36, 36)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMauSac_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel36))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbDongSP_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdHetHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdConHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel39)
                                .addGap(18, 18, 18)
                                .addComponent(btnTao))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(lbQR_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel35)
                                .addComponent(cbbDongSP_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel37)
                                    .addComponent(cbbChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(rdConHang)
                                        .addGap(3, 3, 3)
                                        .addComponent(rdHetHang)))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel39)
                                    .addComponent(btnTao))
                                .addGap(18, 18, 18)
                                .addComponent(lbQR_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lb_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTao1))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtMauSac_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
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
                            .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(481, 481, 481)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(227, 227, 227)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jTabbedPane1)
                    .addGap(39, 39, 39)))
        );

        lbl_chay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_chay.setForeground(new java.awt.Color(0, 0, 0));
        lbl_chay.setText("Chào Mừng Tất Cả Mọi Người Đến Với Cửa Hàng Phụ Kiện Nữ N2.Chúc mọi người có một ngày tốt lành .                                                                                                                                                                                ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_chay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_chay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoLuong_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuong_SPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuong_SPActionPerformed

    private void txtGiaNhap_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaNhap_SPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaNhap_SPActionPerformed

    private void cbbDongSP_SPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDongSP_SPItemStateChanged
        //        System.out.println(((DanhMucViewModel)dcmDongSP_SP.getSelectedItem()).getId());        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDongSP_SPItemStateChanged

    private void btAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddSPActionPerformed
        // TODO add your handling code here:
        if (checkTrungSP(txtMa_SP.getText()) && checkSP()) {
//        if (checkSP()) {
            int x = JOptionPane.showConfirmDialog(this, "Xác nhận thêm sản phẩm?", "Thông báo:", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (this.sanPhamSV.add(getDataSP("")) == 1) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    clearSP();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
            showDataSP();
        }
    }//GEN-LAST:event_btAddSPActionPerformed

    private void btUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateSPActionPerformed
        // TODO add your handling code here:
        if (checkSP()&&checkTrungSP(txtMa_SP.getText())) {
            //            System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId());
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
        }

    }//GEN-LAST:event_btUpdateSPActionPerformed

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

    private void btClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearSPActionPerformed
        // TODO add your handling code here:
        clearSP();
    }//GEN-LAST:event_btClearSPActionPerformed

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

    private void txtSearch_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch_LocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch_LocActionPerformed

    private void selectByName(List<SanPhamViewModel> sp) {
        dtmSP.setRowCount(0);
        for (SanPhamViewModel i : this.sanPhamSV.selectByMa(txtSearch_Loc.getText())) {
            dtmSP.addRow(i.toDataRow());
        }
    }
    
    private void txtSearch_LocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch_LocKeyReleased
        // TODO add your handling code here:
        String ma = txtSearch_Loc.getText();
        List<SanPhamViewModel> sp = this.sanPhamSV.selectByMa(ma);
        selectByName(sp);
    }//GEN-LAST:event_txtSearch_LocKeyReleased

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
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                clearCL();
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

    private void clearDM() {
        lbID_DSP.setText("");
        txtDongSP_DSP.setText("");
    }
    private void btDeleteDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteDeleteActionPerformed
        // TODO add your handling code here:
        int row = tbHienThiCL.getSelectedRow();
        if (row < 0)
            JOptionPane.showMessageDialog(this, "Mời bạn chọn đối tượng muốn xóa!");
        else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo:", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                chatLieuSV.delete(tbHienThi_DSP.getValueAt(tbHienThi_DSP.getSelectedRow(), 0).toString());
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
            clearDM();
            showDataDM();
        }
    }//GEN-LAST:event_btDeleteDeleteActionPerformed

    private void btClearDSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearDSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btClearDSPActionPerformed

    private void tbHienThi_DSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThi_DSPMouseClicked
        // TODO add your handling code here:
        fill(tbHienThi_DSP.getSelectedRow());
    }//GEN-LAST:event_tbHienThi_DSPMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:

        dcmChatLieu_SP.removeAllElements();
        dcmDongSP_SP.removeAllElements();
        showCbbChatLieu();
        showCbbDongSP();
        showDataSP();
        //        fillSP(0);

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void rdHetHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdHetHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdHetHangActionPerformed

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
            java.util.logging.Logger.getLogger(ViewSanPhamm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSanPhamm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSanPhamm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSanPhamm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ViewSanPhamm().setVisible(true);
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
    private javax.swing.JButton btnTao1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu_SP;
    private javax.swing.JComboBox<String> cbbDongSP_Loc1;
    private javax.swing.JComboBox<String> cbbDongSP_SP;
    private javax.swing.JComboBox<String> cbbTrangThai_Loc1;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lable;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbID_DSP;
    private javax.swing.JLabel lbQR_SP;
    private javax.swing.JLabel lb_MoTa;
    private javax.swing.JLabel lbl_chay;
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
