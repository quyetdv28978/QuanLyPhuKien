/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Service.SerChatLieu;
import Service.SerDanhMuc;
//import Service.SerKhuyenMai;
import Service.SerSanPham;
import Utilities.jframeCheck;
import ViewModel.ChatLieuView;
import ViewModel.DanhMucView;
//import ViewModel.KhuyenMaiView;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//Check anh trung voi trung, check chu, check so luon
/**
 *
 * @author yugip
 */
public class SanPhamView extends javax.swing.JFrame {

    private final SerDanhMuc serDM = new SerDanhMuc();
    private final SerChatLieu serCL = new SerChatLieu();
    private final SerSanPham serSP = new SerSanPham();
//    private final SerKhuyenMai serKM = new SerKhuyenMai();
    private final jframeCheck jcheck = new jframeCheck();
    private List<Object> jtext = new ArrayList<>();
    private List<Object> jtextcl = new ArrayList<>();
    private List<Object> jtextSP = new ArrayList<>();
    private DefaultComboBoxModel dccDM = new DefaultComboBoxModel();
    private DefaultComboBoxModel dccCl = new DefaultComboBoxModel();
    private DefaultComboBoxModel dccKM = new DefaultComboBoxModel();
    private String dd;

    /**
     * Creates new form SanPhamView
     */
    public SanPhamView() {
        initComponents();
        jtext.add(txtTDM);
        jtextcl.add(txtTCL);
        jtextSP.add(txtT);
        jtextSP.add(txtMS);
        jtextSP.add(txtNSX);
        jtextSP.add(txtMT);
        jtextSP.add(txtGN);
        jtextSP.add(txtGB);
        jtextSP.add(txtTL);
        jtextSP.add(txtSL);
        loadTableDM();
        loadTableCL();
        loadTableSP();
        loadCom();
//        File f = new File("QRCODE\\5.png");
//        File f2 = new File("QRCODE\\quyet.png");
//        f.renameTo(f2);
    }

    private void loadTableDM() {
        DefaultTableModel dtm = (DefaultTableModel) tbnDM.getModel();
        dtm.setRowCount(0);
        for (DanhMucView danhMucView : this.serDM.getALl("")) {
            dtm.addRow(new Object[]{
                danhMucView.getId(),
                danhMucView.getTen()
            });
        }
    }

    private void loadTableCL() {
        DefaultTableModel dtm = (DefaultTableModel) tbnCL.getModel();
        dtm.setRowCount(0);
        for (ChatLieuView danhMucView : this.serCL.getALl("")) {
            dtm.addRow(new Object[]{
                danhMucView.getId(),
                danhMucView.getTen(),
                danhMucView.getMa()

            });
        }
    }

    private void loadTableSP() {
        DefaultTableModel dtm = (DefaultTableModel) tbnSP.getModel();
        dtm.setRowCount(0);
        for (ViewModel.SanPhamView sanPhamView : this.serSP.getALl("")) {
            dtm.addRow(new Object[]{
                sanPhamView.getId(),
                sanPhamView.getMa(),
                sanPhamView.getTensanpham(),
                sanPhamView.getMausac(),
                sanPhamView.getNhasanxuat(),
                sanPhamView.getNgayTao(),
                sanPhamView.getSoluong(),
                sanPhamView.getGianhap(),
                sanPhamView.getGiaban(),
                sanPhamView.getTrongluong(),
                sanPhamView.getDm(),
                sanPhamView.getCl(),
                sanPhamView.getMota(),
                sanPhamView.getQL()
            });

        }
    }

    private void loadCom() {
        for (ChatLieuView chatLieuView : this.serCL.getALl("")) {
            dccCl.addElement(chatLieuView);
        }
        cbbCL.setModel(dccCl);
        for (DanhMucView chatLieuView : this.serDM.getALl("")) {
            dccDM.addElement(chatLieuView);
        }
        cbbDM.setModel(dccDM);
//        for (KhuyenMaiView khuyenMaiView : this.serKM.getALl("")) {
//            dccKM.addElement(khuyenMaiView);
//        }
//        cbbKM.setModel(dccKM);
    }

    private DanhMucView dateDM(String dk) {
        if (dk != null) {
            return new DanhMucView(tbnDM.getValueAt(tbnDM.getSelectedRow(), 0).toString(), txtTDM.getText().trim());
        }
        return new DanhMucView(jcheck.createID().toString(), txtTDM.getText().trim());
    }

    private ChatLieuView dateCL(String dk) {
        if (dk != null) {
            return new ChatLieuView(tbnDM.getValueAt(tbnDM.getSelectedRow(), 0).toString(), txtTCL.getText().trim(),
                    tbnDM.getValueAt(tbnDM.getSelectedRow(), 1).toString());
        }
        return new ChatLieuView(jcheck.createID().toString(), txtTCL.getText().trim(), jcheck.randomMA());
    }

    private ViewModel.SanPhamView dateSP(String dk) {
        if (anh.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "anh khong duoc de trong");
            return null;
        }
        if (dk != null) {
            return new ViewModel.SanPhamView(tbnSP.getValueAt(tbnSP.getSelectedRow(), 0).toString(), txtM.getText().trim(
            ),
                    txtT.getText().trim(), txtMS.getText().trim(), txtNSX.getText().trim(),
                    dd, txtMT.getText().trim(),
                    Float.parseFloat(txtGB.getText().trim()), Float.parseFloat(txtGN.getText().trim()),
                    Float.parseFloat(txtTL.getText().trim()), new Date(new java.util.Date().getTime()),
                    Integer.parseInt(txtSL.getText().trim()), rdo1.isSelected() == true ? 0 : 1,
                    (DanhMucView) dccDM.getSelectedItem(), (ChatLieuView) dccCl.getSelectedItem());
        }

        return new ViewModel.SanPhamView(jcheck.createID().toString(), txtM.getText().trim(),
                txtT.getText().trim(), txtMS.getText().trim(), txtNSX.getText().trim(),
                dd, txtMT.getText().trim(),
                Float.parseFloat(txtGB.getText().trim()), Float.parseFloat(txtGN.getText().trim()),
                Float.parseFloat(txtTL.getText().trim()), new Date(new java.util.Date().getTime()),
                Integer.parseInt(txtSL.getText().trim()), rdo1.isSelected() == true ? 0 : 1,
                (DanhMucView) dccDM.getSelectedItem(), (ChatLieuView) dccCl.getSelectedItem());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNSX = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTL = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGB = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGN = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMT = new javax.swing.JTextArea();
        cbbDM = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbbCL = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbnSP = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        tbnThem3 = new javax.swing.JButton();
        tbnS3 = new javax.swing.JButton();
        tbnX3 = new javax.swing.JButton();
        tbnN3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rdo1 = new javax.swing.JRadioButton();
        rdo2 = new javax.swing.JRadioButton();
        btnTao = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        anh = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtM = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTCL = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbnCL = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        tbnThem2 = new javax.swing.JButton();
        tbnS2 = new javax.swing.JButton();
        tbnX2 = new javax.swing.JButton();
        tbnN2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTDM = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbnDM = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        tbnThem1 = new javax.swing.JButton();
        tbnS1 = new javax.swing.JButton();
        tbnX1 = new javax.swing.JButton();
        tbnN1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setText("Tên sản phẩm: ");

        txtT.setName("Tên sản phẩm"); // NOI18N

        jLabel2.setText("Màu sắc: ");

        txtMS.setName("Màu sắc"); // NOI18N

        jLabel3.setText("Nhà sản xuất:");

        txtNSX.setName("Nhà sản xuất"); // NOI18N

        jLabel4.setText("Số lượng");

        txtSL.setName("Số lượng"); // NOI18N
        txtSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLActionPerformed(evt);
            }
        });

        jLabel5.setText("Trọng lượng:");

        txtTL.setName("Trọng lượng"); // NOI18N

        jLabel6.setText("Giá bán:");

        txtGB.setName("Giá bán"); // NOI18N

        jLabel7.setText("Giá nhập:");

        txtGN.setName("Giá nhập"); // NOI18N

        jLabel8.setText("Mô tả:");

        txtMT.setColumns(20);
        txtMT.setRows(5);
        txtMT.setName("Mô tả"); // NOI18N
        jScrollPane1.setViewportView(txtMT);

        cbbDM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Danh muc:");

        jLabel10.setText("Chất liệu:");

        cbbCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbnSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tbnSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbnSP);

        tbnThem3.setText("Thêm");
        tbnThem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnThem3ActionPerformed(evt);
            }
        });

        tbnS3.setText("Sửa");
        tbnS3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnS3ActionPerformed(evt);
            }
        });

        tbnX3.setText("Xóa");
        tbnX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnX3ActionPerformed(evt);
            }
        });

        tbnN3.setText("Mới");
        tbnN3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnN3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbnThem3)
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbnN3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tbnS3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(tbnX3)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tbnN3, tbnS3, tbnThem3, tbnX3});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbnThem3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnS3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnX3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(tbnN3)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tbnN3, tbnS3, tbnThem3, tbnX3});

        jLabel13.setText("QR:");

        jLabel14.setText("Trang thai: ");

        buttonGroup1.add(rdo1);
        rdo1.setSelected(true);
        rdo1.setText("Còn hàng");

        buttonGroup1.add(rdo2);
        rdo2.setText("Hết hàng");

        btnTao.setText("Tạo mã");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(anh, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(anh, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jLabel17.setText("Ma:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMS, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGN, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGB, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnTao)))))
                .addGap(116, 116, 116)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbDM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbCL, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtM, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtGN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel17)
                    .addComponent(txtM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtGB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbbCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(rdo1)
                    .addComponent(rdo2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTao)
                            .addComponent(jLabel13)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel1);

        jLabel12.setText("Tên:");

        tbnCL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã", "Tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbnCL);

        tbnThem2.setText("Thêm");
        tbnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnThem2ActionPerformed(evt);
            }
        });

        tbnS2.setText("Sửa");
        tbnS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnS2ActionPerformed(evt);
            }
        });

        tbnX2.setText("Xóa");
        tbnX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnX2ActionPerformed(evt);
            }
        });

        tbnN2.setText("Mới");
        tbnN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnN2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbnThem2)
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbnN2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tbnS2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(tbnX2)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tbnN2, tbnS2, tbnThem2, tbnX2});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnS2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnX2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(tbnN2)
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tbnN2, tbnS2, tbnThem2, tbnX2});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTCL, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtTCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(158, 158, 158)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(261, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chất liệu", jPanel3);

        jLabel11.setText("Tên:");

        txtTDM.setName("Tên"); // NOI18N

        tbnDM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbnDM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnDMMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbnDM);

        tbnThem1.setText("Thêm");
        tbnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnThem1ActionPerformed(evt);
            }
        });

        tbnS1.setText("Sửa");
        tbnS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnS1ActionPerformed(evt);
            }
        });

        tbnX1.setText("Xóa");
        tbnX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnX1ActionPerformed(evt);
            }
        });

        tbnN1.setText("Mới");
        tbnN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbnThem1)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbnN1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tbnS1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(tbnX1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tbnN1, tbnS1, tbnThem1, tbnX1});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnS1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnX1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(tbnN1)
                .addGap(33, 33, 33))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tbnN1, tbnS1, tbnThem1, tbnX1});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtTDM, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(169, 169, 169)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 267, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh mục", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLActionPerformed

    private void tbnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnThem1ActionPerformed
        if (jcheck.checkData(jtext, this) == false) {
            return;
        }

        DanhMucView nvv = dateDM(null);
        if (nvv != null) {
            if (this.serDM.add(nvv) == 0) {
                JOptionPane.showMessageDialog(this, "Them thanh cong");
                loadTableDM();
                jcheck.clearView(jtext, tbnCL);
            }
        }
    }//GEN-LAST:event_tbnThem1ActionPerformed

    private void tbnS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnS1ActionPerformed
        if (jcheck.checkData(jtext, this) == false) {
            return;
        }
        if (jcheck.checkClcick(tbnDM, this) == false) {
            return;
        }

        DanhMucView nvv = dateDM("a");
        if (nvv != null) {
            if (this.serDM.update(nvv) == 0) {
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
                loadTableDM();
                jcheck.clearView(jtext, tbnDM);
            }
        }
    }//GEN-LAST:event_tbnS1ActionPerformed

    private void tbnX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnX1ActionPerformed
        if (jcheck.checkClcick(tbnDM, this) == false) {
            return;
        }
        this.serDM.delete(tbnDM.getValueAt(tbnDM.getSelectedRow(), 0).toString());
        loadTableDM();
        this.jcheck.clearView(jtext, tbnDM);
    }//GEN-LAST:event_tbnX1ActionPerformed

    private void tbnN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnN1ActionPerformed

    private void tbnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnThem2ActionPerformed
        if (jcheck.checkData(jtextcl, this) == false) {
            return;
        }

        ChatLieuView nvv = dateCL(null);
        if (nvv != null) {
            if (this.serCL.add(nvv) == 0) {
                JOptionPane.showMessageDialog(this, "Them thanh cong");
                loadTableCL();
                jcheck.clearView(jtextcl, tbnCL);
            }
        }
    }//GEN-LAST:event_tbnThem2ActionPerformed

    private void tbnS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnS2ActionPerformed
        if (jcheck.checkData(jtextcl, this) == false) {
            return;
        }

        ChatLieuView nvv = dateCL(null);
        if (nvv != null) {
            if (this.serCL.update(nvv) == 0) {
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
                loadTableCL();
                jcheck.clearView(jtextcl, tbnCL);
            }
        }
    }//GEN-LAST:event_tbnS2ActionPerformed

    private void tbnX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnX2ActionPerformed
        if (jcheck.checkClcick(tbnCL, this) == false) {
            return;
        }
        this.serCL.delete(tbnCL.getValueAt(tbnCL.getSelectedRow(), 0).toString());
        loadTableCL();
        this.jcheck.clearView(jtextcl, tbnCL);
    }//GEN-LAST:event_tbnX2ActionPerformed

    private void tbnN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnN2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnN2ActionPerformed

    private void tbnThem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnThem3ActionPerformed
        if (jcheck.checkData(jtextSP, this) == false) {
            return;
        }

        ViewModel.SanPhamView nvv = dateSP(null);
        if (nvv != null) {
            if (this.serSP.add(nvv) == 0) {
                JOptionPane.showMessageDialog(this, "Them thanh cong");
                loadTableSP();
                jcheck.clearView(jtextSP, tbnSP);
                anh.setIcon(null);
            }
        }
    }//GEN-LAST:event_tbnThem3ActionPerformed

    private void tbnS3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnS3ActionPerformed
        if (jcheck.checkData(jtextSP, this) == false) {
            return;
        }

        ViewModel.SanPhamView nvv = dateSP("sfasfd");
        if (nvv != null) {
            if (this.serSP.update(nvv) == 0) {
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
                loadTableSP();
                jcheck.clearView(jtextSP, tbnSP);
                anh.setIcon(null);
            }
        }
    }//GEN-LAST:event_tbnS3ActionPerformed

    private void tbnX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnX3ActionPerformed
        if (jcheck.checkClcick(tbnSP, this) == false) {
            return;
        }
        System.out.println(tbnSP.getValueAt(tbnSP.getSelectedRow(), 0).toString());
        this.serSP.delete(tbnSP.getValueAt(tbnSP.getSelectedRow(), 0).toString());
        loadTableSP();
        this.jcheck.clearView(jtextSP, tbnSP);
        anh.setIcon(null);
    }//GEN-LAST:event_tbnX3ActionPerformed

    private void tbnN3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnN3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnN3ActionPerformed

    private void tbnDMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnDMMouseClicked
        jcheck.clickTable(jtext, tbnDM);        // TODO add your handling code here:
    }//GEN-LAST:event_tbnDMMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        loadCom();        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void tbnSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnSPMouseClicked
        jcheck.clickTable(jtextSP, tbnSP);
    }//GEN-LAST:event_tbnSPMouseClicked

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        JFileChooser j = new JFileChooser("D:\\DU1_nhanh_quyet\\DU1\\DU1\\QRCODE");
        j.showOpenDialog(this);
        File f = j.getSelectedFile();
        dd = "D:\\DU1_nhanh_quyet\\DU1\\DU1\\QRCODE\\" + f.getName();
        Image a = new ImageIcon(dd).getImage().getScaledInstance(128, 100, 0);
        anh.setIcon(new ImageIcon(a));
    }//GEN-LAST:event_btnTaoActionPerformed

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
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anh;
    private javax.swing.JButton btnTao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbCL;
    private javax.swing.JComboBox<String> cbbDM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JRadioButton rdo2;
    private javax.swing.JTable tbnCL;
    private javax.swing.JTable tbnDM;
    private javax.swing.JButton tbnN1;
    private javax.swing.JButton tbnN2;
    private javax.swing.JButton tbnN3;
    private javax.swing.JButton tbnS1;
    private javax.swing.JButton tbnS2;
    private javax.swing.JButton tbnS3;
    private javax.swing.JTable tbnSP;
    private javax.swing.JButton tbnThem1;
    private javax.swing.JButton tbnThem2;
    private javax.swing.JButton tbnThem3;
    private javax.swing.JButton tbnX1;
    private javax.swing.JButton tbnX2;
    private javax.swing.JButton tbnX3;
    private javax.swing.JTextField txtGB;
    private javax.swing.JTextField txtGN;
    private javax.swing.JTextField txtM;
    private javax.swing.JTextField txtMS;
    private javax.swing.JTextArea txtMT;
    private javax.swing.JTextField txtNSX;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtT;
    private javax.swing.JTextField txtTCL;
    private javax.swing.JTextField txtTDM;
    private javax.swing.JTextField txtTL;
    // End of variables declaration//GEN-END:variables
}
