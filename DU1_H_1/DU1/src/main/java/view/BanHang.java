/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domaimodel.ChiTietGioHang;
import domaimodel.ChiTietHoaDon;
import domaimodel.GioHang;
import domaimodel.HoaDon;
import domaimodel.KhachHang;
import domaimodel.SanPham;
import domaimodel.embeddableCTGH;
import domaimodel.embeddableCTHD;
import respon.ResGioHangCT;
import respon.ResHoaDon;
import respon.ResHoaDonCho;
import respon.resSanPham;
//import Service.SerSanPham;
import utility.JframeCheck;
//import viewmodel.SanPhamView;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import domaimodel.ChiTietKhuyenMai;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import net.bytebuddy.asm.Advice;
import org.hibernate.dialect.InterbaseDialect;
import respon.ChiTietKhuyenMaiResponsitories;
import respon.KhachHangResponsitories;
import utility.DBConnection;
import utility.JframeCheck;

/**
 *
 * @author HP
 */
public class BanHang extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    Executor executor = Executors.newSingleThreadExecutor(this);

    private ChiTietKhuyenMaiResponsitories ctkm = new ChiTietKhuyenMaiResponsitories();
    private resSanPham resSP = new resSanPham();
    private ResHoaDonCho resHDCho = new ResHoaDonCho();
    private KhachHangResponsitories resKH = new KhachHangResponsitories();
    private ResHoaDon resHD = new ResHoaDon();

//    private Map<String, SanPhamViewModel[]> listSP = new HashMap<>();
    private DefaultComboBoxModel dccKH = new DefaultComboBoxModel();

    private int count = 0;
    private String IDGH, IDHD;
    private HoaDon hdCho = null;
    private List<Object> ctghCho = null;

    private Set<String> tenSP = new HashSet<>();
    private ResGioHangCT resGH = new ResGioHangCT();
    private Map<String, StringBuilder> mapSP = new HashMap<>();

    private JframeCheck jcheck = new JframeCheck();
    private DefaultTableModel dtm;

    public static BanHang banHang = new BanHang();

    public BanHang(String ma) {
        initComponents();
        loadTableSP();
//        initWebcam();
        loadTableHoaDon(this.resHD.getAll(""));
        System.out.println(ma);
//        txttenkh.repaint();
        txttenkh.setText(ma);
    }

    public BanHang() {
        initComponents();
        loadTableSP();
//        initWebcam();
        loadTableHoaDon(this.resHD.getAll(""));
    }

    private KhachHang khachHangMiNi;

    public void getData(KhachHang data) {
        txttenkh.setText(data.getTen());
        khachHangMiNi = data;
        setLocationRelativeTo(null);
    }

    private void loadTableSP() {
        if ( this.resSP.getAll("") != null) {
        
        DefaultTableModel dtm = (DefaultTableModel) tbltimtheoma.getModel();
        dtm.setRowCount(0);
        for (SanPham sanPhamView : this.resSP.getAll("")) {
            dtm.addRow(new Object[]{
                sanPhamView.getMa(),
                sanPhamView.getTenSanPham(),
                sanPhamView.getNhaSanXuat(),
                sanPhamView.getMauSac(),
                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                sanPhamView.getSoLuong(),
                sanPhamView.getGiaBan(),
                sanPhamView.getTrongLuong()
            });
        }    
        }
    }

//    private void loadComKH() {
//        for (KhachHang object : this.resHD.getALl()) {
//            dccKH.addElement(object);
//        }
//        cbbKH.setModel(dccKH);
//    }
    private String dk(String ten, String giaTri) {
        return " where " + ten + " = '" + giaTri + "'";
    }

    private String getValueTable(int vt, JTable table, int i) {
        return table.getValueAt(vt, i).toString();
    }


    private void loadTableHoaDon(List<HoaDon> l) {
        if (l!=null) {
        DefaultTableModel dtm = (DefaultTableModel) tbnHD.getModel();
        dtm.setRowCount(0);
        String tt = "";
        String tenKH;
        for (HoaDon hoaDon : l) {
            if (hoaDon.getTinhTrang() == 0) {
                tt = "Đã thanh toán";
            } else if (hoaDon.getTinhTrang() == 1) {
                tt = "Đang chờ thanh toán";
            } else if (hoaDon.getTinhTrang() == 2) {
                tt = "Đã hủy";
            } else if (hoaDon.getTinhTrang() == 3) {
                tt = "Đang giao hàng";
            } else {
                tt = "Đang xử lí";
            }
            if (hoaDon.getKh() == null) {
                tenKH = "";
            } else {
                tenKH = hoaDon.getKh().getTen();
            }
            dtm.addRow(new Object[]{
                hoaDon.getId(),
                tenKH,
                hoaDon.getNgayT(),
                hoaDon.getNgayTT(),
                tt,
                hoaDon.getHinhthucthanhtoan()
            });
        }
        }
    }
    

//   THEM SAN PHAM VAO GIO HANG CHI TIET
    private List<ChiTietGioHang> getChiTietGioHang(String IDGH) {
        int soLuong = 0;
        List<ChiTietGioHang> listCTGH = new ArrayList<>();

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {

            int vt = i;

            GioHang gh = this.resGH.getALlGioHangs(dk("g.id", IDGH)).get(0);

            soLuong = Integer.parseInt(getValueTable(vt, tblgiohang, 2));

            SanPham spv = (SanPham) this.resSP.getAll(dk("ma", getValueTable(vt, tblgiohang, 0))).get(0);

//Update san pham
            embeddableCTGH idDouble = new embeddableCTGH(spv.getId(), IDGH);

            ChiTietGioHang ctGH = new ChiTietGioHang(idDouble, spv, gh, soLuong, 0, new Date());

            listCTGH.add(ctGH);
        }
        return listCTGH;
    }

//  CLEAR DU LIEU "DAT HANG"  
    private void clearDatHang() {
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        ctghCho = null;
        hdCho = null;
        tblgiohang.clearSelection();
        tbnHD.clearSelection();
        tbltimtheoma.clearSelection();
        count = 0;
        txtDShip.setText("");
        txtSDTShip.setText("");
        txtTenShip.setText("");
//        txtPVC.setText("");
        txtdg1.setText("");
        txtsl.setText("");
        txtsl1.setText("");
        txttongtien1.setText("");
        txtgiamgia1.setText("");
    }

// CLEAR DU LIEU VE TU DAU "BAN HANG"
    private void clear() {
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        ctghCho = null;
        hdCho = null;
        tblgiohang.clearSelection();
        tbnHD.clearSelection();
        tbltimtheoma.clearSelection();
        count = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void clickTableGioHang() {
        int soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Mời nhập số lượng"));
        String tenSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 1).toString();

        if (soLuong > Integer.parseInt(tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 5).toString())) {
            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
            return;
        }

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            if (tblgiohang.getValueAt(i, 1).toString().equals(tenSP_SanPham)) {
                tblgiohang.setValueAt(soLuong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
                        i, 2);
                return;
            }
        }
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(count);
        count++;
        dtm.addRow(new Object[]{
            tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString(), tenSP_SanPham, soLuong,
            ((KhachHang) dccKH.getSelectedItem()).getMa()
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        jpanel45 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        btnxoatatca = new javax.swing.JButton();
        btnxoasp = new javax.swing.JButton();
        btntaohd = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbnHD = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbbtrangthai = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txttimtheoma = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbbdanhmuc = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltimtheoma = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        result_field = new javax.swing.JTextField();
        tabbHD = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txttenkh = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtsl = new javax.swing.JTextField();
        txtdg = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtgiamgia = new javax.swing.JTextField();
        rdodatt = new javax.swing.JRadioButton();
        cbbhinhthuctt = new javax.swing.JComboBox<>();
        txttongtien = new javax.swing.JTextField();
        txttienkhachdua = new javax.swing.JTextField();
        txttienthua = new javax.swing.JTextField();
        btntt = new javax.swing.JButton();
        rdochuatt = new javax.swing.JRadioButton();
        rdoHuy = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTenShip = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSDTShip = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDShip = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtPVC = new javax.swing.JTextField();
        txtsl1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtdg1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtgiamgia1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txttongtien1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnChuyenHang = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Cửa hàng phụ kiện trang sức N1");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Bán sản phẩm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Giỏ hàng");

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Sản phẩm", "Số lượng", "Giảm giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblgiohangMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblgiohang);

        btnxoatatca.setText("Xóa tất cả");
        btnxoatatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoatatcaActionPerformed(evt);
            }
        });

        btnxoasp.setText("Xóa sản phẩm");
        btnxoasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaspActionPerformed(evt);
            }
        });

        btntaohd.setText("Tạo hóa đơn");
        btntaohd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntaohd)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnxoatatca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnxoasp)
                .addGap(24, 24, 24))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btntaohd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxoasp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoatatca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Danh sách hóa đơn");

        tbnHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên khách hàng", "Ngày tạo", "Ngày thanh toán", "Tình trạng", "Hình thức thanh toán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbnHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnHDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbnHDMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tbnHD);

        jLabel6.setText("Trạng thái");

        cbbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Hóa đơn chờ", "Đã hủy", "Đang giao hàng", "Đang xử lí", "Tất cả" }));
        cbbtrangthai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbtrangthaiItemStateChanged(evt);
            }
        });
        cbbtrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtrangthaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)
                        .addGap(35, 35, 35)
                        .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Tìm kiếm theo mã");

        txttimtheoma.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txttimtheomaHierarchyChanged(evt);
            }
        });
        txttimtheoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimtheomaActionPerformed(evt);
            }
        });
        txttimtheoma.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txttimtheomaPropertyChange(evt);
            }
        });
        txttimtheoma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimtheomaKeyReleased(evt);
            }
        });

        jLabel8.setText("Danh mục");

        cbbdanhmuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhẫn, dây chuyền", "Vòng đeo tay", " " }));

        tbltimtheoma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã ", "Tên sản phẩm", "Nhà sản xuất", "Màu sắc", "Trạng thái", "Số lượng", "Giá", "Trọng lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltimtheoma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltimtheomaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltimtheoma);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txttimtheoma, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                .addGap(277, 277, 277))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbdanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimtheoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbdanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Tạo hóa đơn");

        jLabel11.setText("Tên khách hàng");

        txttenkh.setName("Tên khách hàng"); // NOI18N

        jLabel14.setText("Số lượng ");

        jLabel15.setText("Đơn giá");

        jLabel16.setText("Giảm giá");

        jLabel18.setText("Trạng thái");

        jLabel19.setText("Hình thức thanh toán");

        jLabel20.setText("Tổng tiền");

        jLabel21.setText("Tiền khách đưa");

        jLabel22.setText("Tiền thừa");

        txtgiamgia.setText("0");

        buttonGroup1.add(rdodatt);
        rdodatt.setSelected(true);
        rdodatt.setText("Đã thanh toán");

        cbbhinhthuctt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Thẻ" }));

        txttongtien.setText("0");

        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyReleased(evt);
            }
        });

        btntt.setText("Thanh toán");
        btntt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnttActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdochuatt);
        rdochuatt.setText("Chờ");
        rdochuatt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdochuattActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHuy);
        rdoHuy.setText("Hủy");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(46, 46, 46)
                        .addComponent(rdodatt)
                        .addGap(18, 18, 18)
                        .addComponent(rdochuatt)
                        .addGap(29, 29, 29)
                        .addComponent(rdoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addGap(28, 28, 28)
                                    .addComponent(cbbhinhthuctt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel22))
                                    .addGap(49, 49, 49)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(btntt, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(78, 78, 78)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtdg)
                                        .addComponent(txtsl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(47, 47, 47)
                                .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtdg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbbhinhthuctt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdodatt)
                    .addComponent(jLabel18)
                    .addComponent(rdochuatt)
                    .addComponent(rdoHuy))
                .addGap(42, 42, 42)
                .addComponent(btntt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbHD.addTab("Hóa đơn", jPanel8);

        jLabel10.setText("Tên khách hàng: ");

        jLabel12.setText("Số điện thoại:");

        jLabel13.setText("Địa chỉ:");

        jLabel24.setText("Phí vận chuyển:");

        txtPVC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPVC.setText("25");

        jLabel25.setText("Số lượng ");

        jLabel26.setText("Đơn giá");

        jLabel27.setText("Giảm giá");

        txtgiamgia1.setText("0");

        jLabel28.setText("Tổng tiền");

        txttongtien1.setText("0");
        txttongtien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtien1ActionPerformed(evt);
            }
        });

        btnChuyenHang.setText("Giao đơn hàng");
        btnChuyenHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenHangActionPerformed(evt);
            }
        });

        jButton3.setText("Hủy đơn hàng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Hoàn thành");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel29.setText("VND");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenShip, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel26)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtgiamgia1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addComponent(txttongtien1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDShip, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSDTShip, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(txtPVC)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(78, 78, 78)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtdg1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                    .addComponent(txtsl1))))
                        .addContainerGap(78, Short.MAX_VALUE))
                    .addComponent(jSeparator2)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnChuyenHang)
                        .addGap(58, 58, 58)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSDTShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(txtPVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(txtsl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtdg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtgiamgia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txttongtien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnChuyenHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        tabbHD.addTab("Đặt hàng", jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(result_field, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(tabbHD, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabbHD)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(result_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 64, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lí bán hàng", jPanel4);

        jPanel10.setBackground(new java.awt.Color(255, 255, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên Nhân Viên :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("NVA");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Chức Vụ :");

        btntrangchu.setBackground(new java.awt.Color(255, 255, 153));
        btntrangchu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrangchu.setText("Trang Chủ");

        btnbanhang.setBackground(new java.awt.Color(255, 255, 153));
        btnbanhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbanhang.setText("Bán Hàng");

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
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

        javax.swing.GroupLayout jpanel45Layout = new javax.swing.GroupLayout(jpanel45);
        jpanel45.setLayout(jpanel45Layout);
        jpanel45Layout.setHorizontalGroup(
            jpanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel45Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jpanel45Layout.createSequentialGroup()
                        .addGap(431, 431, 431)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jpanel45Layout.setVerticalGroup(
            jpanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(455, 455, 455))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanel45, javax.swing.GroupLayout.PREFERRED_SIZE, 721, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    CHON SAN PHAM VAO GIO HANG
    private String chonSanPham() {
        String so = JOptionPane.showInputDialog(this, "Mời nhập số lượng");
        if (so == null) {
            return null;
        }
        int soLuong = Integer.parseInt(so);
        String tenSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 1).toString();
        String maSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString();
        float ctkm2;
        if (this.ctkm.getALLJoin1(maSP_SanPham).isEmpty()) {
            ctkm2 = 0;
        } else {
            ctkm2 = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(maSP_SanPham).get(0)).getKm().getGiaGiam();
        }

        if (soLuong > Integer.parseInt(tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 5).toString())) {
            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
            return null;
        } else if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            return null;
        }
        tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) - soLuong, tbltimtheoma.getSelectedRow(), 5);

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            if (tblgiohang.getValueAt(i, 0).toString().equals(maSP_SanPham)) {
                tblgiohang.setValueAt(soLuong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
                        i, 2);
                return "asdf";
            }
        }
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(count);
        count++;
        dtm.addRow(new Object[]{
            tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString(), tenSP_SanPham, soLuong,
            ctkm2
        });

        return "la sao";
    }

//    THEM SAN PHAM VAO GIO, SE HIEN THI O HOA DON
    private void sanPhamHienThiOHoaDon() {
        int soluong = 0;
        float donGia = 0;
        float giaGiam = 0;
        float tongTien = 0;

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            SanPham s = this.resSP.getAll(dk(" s.ma ", tblgiohang.getValueAt(i, 0).toString())).get(0);
            soluong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            donGia += ((SanPham) this.resSP.getAll("where s.ma = '"
                    + tblgiohang.getValueAt(i, 0).toString() + "'").
                    get(0)).getGiaBan()
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            giaGiam += (s.getGiaBan() * Float.parseFloat(tblgiohang.getValueAt(i, 3).toString()) / 100)
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString())
                    ;
            tongTien = donGia - giaGiam;
        }

        if (tongTien < 0) {
            tongTien = -tongTien;
        }

        txttongtien.setText(tongTien + "");
        txtdg.setText(donGia + "");
        txtsl.setText(soluong + "");
        txtgiamgia.setText(giaGiam + "");
    }

    //    THEM SAN PHAM VAO GIO, SE HIEN THI O DAT HANG
    private void sanPhamHienThiODATHANG() {
        int soluong = 0;
        float donGia = 0;
        float giaGiam = 0;
        float tongTien = 0;

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            SanPham s = this.resSP.getAll(dk(" s.ma ", tblgiohang.getValueAt(i, 0).toString())).get(0);
            soluong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            donGia += ((SanPham) this.resSP.getAll("where s.ma = '"
                    + tblgiohang.getValueAt(i, 0).toString() + "'").
                    get(0)).getGiaBan()
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            giaGiam += (s.getGiaBan() * Float.parseFloat(tblgiohang.getValueAt(i, 3).toString()) / 100)
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            tongTien = donGia - giaGiam + Integer.parseInt(txtPVC.getText().trim());
        }
        if (tongTien < 0) {
            tongTien = -(tongTien);
        }
        txttongtien1.setText(tongTien + "");
        txtdg1.setText(donGia + "");
        txtsl1.setText(soluong + "");
        txtgiamgia1.setText(giaGiam + "");
    }

//    THEM SAN PHAM VAO GIO HANG CHI
    private void themSanPhamVaoGioHangChiTiet() {
        int soLuong = 0;
        System.out.println(tblgiohang.getRowCount());
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {

            int vt = i;

            GioHang gh = this.resGH.getALlGioHangs(dk("g.id", IDGH)).get(0);

            soLuong = Integer.parseInt(getValueTable(vt, tblgiohang, 2));

            SanPham spv = (SanPham) this.resSP.getAll(dk("ma", getValueTable(vt, tblgiohang, 0))).get(0);

            embeddableCTGH idDouble = new embeddableCTGH(spv.getId(), IDGH);

            ChiTietGioHang ctGH = new ChiTietGioHang(idDouble, spv, gh, soLuong, 0, new Date());

            this.resGH.add(ctGH);
        }
    }

//  HIEN THI SAN PHAM O DAT HANG 
    private void themSPVaoDatHang() {
        int soluong = 0;
        float donGia = 0;
        float giaGiam = 0;
        float tongTien = 0;

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            SanPham s = this.resSP.getAll(dk(" s.ma ", tblgiohang.getValueAt(i, 0).toString())).get(0);
            soluong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            donGia += ((SanPham) this.resSP.getAll("where s.ma = '"
                    + tblgiohang.getValueAt(i, 0).toString() + "'").
                    get(0)).getGiaBan()
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            giaGiam += (s.getGiaBan() * Float.parseFloat(tblgiohang.getValueAt(i, 3).toString()) / 100)
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());

        }
        tongTien = donGia - giaGiam + Integer.parseInt(txtPVC.getText().trim());
        if (tongTien < 0) {
            tongTien = -(tongTien);
        }
        txttongtien1.setText(tongTien + "");
        txtdg1.setText(donGia + "");
        txtsl1.setText(soluong + "");
        txtgiamgia1.setText(giaGiam + "");
    }

    private void tbltimtheomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltimtheomaMouseClicked
//  MUA HANG     
        if (tabbHD.getSelectedIndex() == 0) {
//        CHON SAN PHAM VAO GIO HANG
            if (chonSanPham() == null) {
                return;
            }

//        THEM SAN PHAM VAO GIO HANG CHI TIET
//            themSanPhamVaoGioHangChiTiet();
//         HIEN THI SAN PHAM THANH TOAN
            sanPhamHienThiOHoaDon();

//  DAT HANG          
        } else {
//        CHON SAN PHAM VAO GIO HANG
            if (chonSanPham() == null) {
                return;
            }
//        THEM SAN PHAM VAO GIO HANG CHI TIET
//            themSanPhamVaoGioHangChiTiet();

//         HIEN THI SAN PHAM THANH TOAN TRONG DAT HANG
            themSPVaoDatHang();
        }
    }//GEN-LAST:event_tbltimtheomaMouseClicked

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed
//  ID HOA DON , ID GIO HANG SE DUOC TAO TRUOC
        IDHD = jcheck.createID().toString();
        IDGH = IDHD;

// TAO HOA DON KHI BAM NUT
        this.resGH.addHD(new HoaDon(IDHD, new Date(new java.util.Date().getTime()), 4,
                null, null
        ));

//  TAO GIO HANG DONG THOI VOI HOA DON
        this.resGH.addGH(new GioHang(IDGH, jcheck.randomMA(),
                new Date(new java.util.Date().getTime()), 0));

        loadTableHoaDon(this.resHD.getAll(""));

    }//GEN-LAST:event_btntaohdActionPerformed

    private void btnttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnttActionPerformed
        String id = null;
        int trangThai = 0;
        Date ngayTT = null;
        HoaDon hd;
        if (IDHD == null) {
            hd = hdCho;
        } else {
            hd = ((HoaDon) this.resHD.getAll(dk(" d.id ", IDHD)).get(0));
        }

        if (rdodatt.isSelected() == true) {
            trangThai = 0;
            ngayTT = new Date(new java.util.Date().getTime());
        } else if (rdochuatt.isSelected()) {
            trangThai = 1;
            ngayTT = null;
        } else if (rdoHuy.isSelected()) {
            trangThai = 2;
            ngayTT = null;
        }
//  Kiểm tra xem thong tin khach hang cua hoa don        
        if (hdCho != null && khachHangMiNi == null) {
            khachHangMiNi = hdCho.getKh();
        }
        if (trangThai == 0) {

            for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                id = getValueTable(i, tblgiohang, 0);
                SanPham sp = ((SanPham) this.resSP.getAll(dk(" s.ma ", id)).get(0));
                embeddableCTHD idDouble = new embeddableCTHD(IDHD, sp.getId());
                ChiTietHoaDon ctHD = new ChiTietHoaDon(idDouble, hd, sp,
                        Integer.parseInt(getValueTable(i, tblgiohang, 2)),
                        Float.parseFloat(getValueTable(i, tblgiohang, 2)) * sp.getGiaBan(),
                        new Date(new java.util.Date().getTime()), trangThai,
                        Float.parseFloat(txtgiamgia.getText().trim())
                );
                sp.setSoLuong(sp.getSoLuong() - Integer.parseInt(getValueTable(i, tblgiohang, 2)));

                hd.setTinhTrang(0);
                hd.setNgayTT(new Date());
                hd.setHinhthucthanhtoan(cbbhinhthuctt.getSelectedItem().toString());
                hd.setKh(khachHangMiNi);
                System.out.println(khachHangMiNi.getTen());

                this.resSP.update(sp);

                this.resGH.addHDCT(ctHD);
            }

//  TRUONG HOP HOA DON CHO, GIO HANG TRANG THAI 1, HOA DON TRANG THAI 1
        } else if (trangThai == 1) {
//  TRUONG HOP KHI XU LI HOA DON CHO, NEU CHON RADIO 'CHO'          
            if (hdCho != null) {
                JOptionPane.showMessageDialog(this, "Không thể tạo hóa chờ vui lòng thanh toán hóa đơn chờ hiện tại");
                return;
            } else {
                GioHang g = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0));
                g.setTinhtrang(1);

                hd.setTinhTrang(1);
                hd.setKh(khachHangMiNi);
                g.setNv(hd.getKh());
                this.resGH.updateGH(g);
                this.resGH.updateGH(g);
            }

//  TRUONG HOP HOA DON HUY, GIO HANG TRANG THAI 2, HOA DON TRANG THAI 2              
        } else if (trangThai == 2) {
//  TRUONG HOP XU LI HOA DON KHI CHON RADIO HUY          
            if (hdCho != null) {
                hdCho.setTinhTrang(2);
                GioHang gioHang = ((ChiTietGioHang) ctghCho.get(0)).getGh();
                gioHang.setTinhtrang(2);
                this.resGH.updateGH(gioHang);
            } else {
                GioHang g = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0));
                g.setTinhtrang(2);
                hd.setTinhTrang(2);
                hd.setKh(khachHangMiNi);
                g.setNv(hd.getKh());
                this.resGH.updateGH(g);
            }
        }
        if (hdCho == null) {
            themSanPhamVaoGioHangChiTiet();
        }

        this.resGH.updateHD(hd);
        loadTableHoaDon(this.resHD.getAll(""));
        clear();
        loadTableSP();
        IDHD = null;
        IDGH = null;
        khachHangMiNi = null;
        txtsl.setText("");
        txtdg.setText("");
        txttenkh.setText("");
        txtgiamgia.setText("");
        txttienkhachdua.setText("");
        txttienthua.setText("");
        txttongtien.setText("");
    }//GEN-LAST:event_btnttActionPerformed

    private void rdochuattActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdochuattActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdochuattActionPerformed

//  HAM XU LI HOA DON CHO VA GIAO HANG
    private void xuLiHoaDonChoGiaoHang(int tt) {
        float giaGiam = 0;
        hdCho = (HoaDon) this.resHDCho.getAll(" ct.id= " + "'" + getValueTable(tbnHD.getSelectedRow(),
                tbnHD, 0) + "'", tt).get(0);
        ctghCho = this.resHDCho.getAllHDCHo(hdCho.getKh().getId(), tt);
        System.out.println(ctghCho.size());
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(count);

        for (Object object : ctghCho) {
            if (!this.ctkm.getALLJoin1(((ChiTietGioHang) object)
                    .getSp().getMa()).isEmpty()) {
                giaGiam = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(((ChiTietGioHang) object)
                        .getSp().getMa()).get(0)).getKm().getGiaGiam();
            } else {
                giaGiam = 0;
            }
            dtm.addRow(new Object[]{
                ((ChiTietGioHang) object).getSp().getMa(),
                ((ChiTietGioHang) object).getSp().getTenSanPham(),
                ((ChiTietGioHang) object).getSoLuong(),
                giaGiam
            });
        }
        count++;
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            for (int j = 0; j < tbltimtheoma.getRowCount(); j++) {
                if (tblgiohang.getValueAt(i, 0).toString().equals(getValueTable(j, tbltimtheoma, 0))) {
                    tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(j, tbltimtheoma, 5)) - Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
                            j, 5);
                    break;
                }
            }

        }
        if (tabbHD.getSelectedIndex() == 0) {
            sanPhamHienThiOHoaDon();
            txttenkh.setText(hdCho.getKh().getTen());
        } else if (tabbHD.getSelectedIndex() == 1) {
            sanPhamHienThiODATHANG();
            System.out.println(hdCho.getKh().getTen() + " hkfajhsfdjasfjd");
            txtTenShip.setText(hdCho.getKh().getTen());
            txtDShip.setText(hdCho.getKh().getDiaChi());
            txtSDTShip.setText(hdCho.getKh().getSdt());
        }

    }

    private void tbnHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnHDMouseClicked
        count = 0;
        if (tabbHD.getSelectedIndex() == 0) {
//            dtm = (DefaultTableModel) tblgiohang.getModel();
//            dtm.setRowCount(0);
//            count = 0;
            if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang chờ thanh toán")) {
//  XU LI HOA DON CHO O BAN HANG
                xuLiHoaDonChoGiaoHang(1);
            } else if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang xử lí")) {
                IDHD = ((HoaDon) this.resHD.getAll(dk(" d.id ", getValueTable(tbnHD.getSelectedRow(),
                        tbnHD, 0))).get(0)).getId();
                IDGH = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", getValueTable(tbnHD.getSelectedRow(),
                        tbnHD, 0))).get(0)).getId();
            }
        } else if (tabbHD.getSelectedIndex() == 1) {
//            dtm = (DefaultTableModel) tblgiohang.getModel();
//            dtm.setRowCount(0);
//            count = 0;
            if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang giao hàng")
                    && tabbHD.getSelectedIndex() == 1) {
//  XU LI HOA DON O DAT HANG   
                xuLiHoaDonChoGiaoHang(3);
            }
        }
    }//GEN-LAST:event_tbnHDMouseClicked

    private void tbnHDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnHDMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnHDMouseEntered

    private void btnxoatatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoatatcaActionPerformed
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        loadTableSP();
        count = 0;
        if (tabbHD.getSelectedIndex() == 0) {
            sanPhamHienThiOHoaDon();
        } else
            sanPhamHienThiODATHANG();
        clear();
    }//GEN-LAST:event_btnxoatatcaActionPerformed

    private void btnxoaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaspActionPerformed
        if (tblgiohang.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
            return;
        }
        String soluongI = JOptionPane.showInputDialog(this, "Chọn số lượng cần xóa");
        if (soluongI.trim().length() == 0) {
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa không?") != JOptionPane.YES_OPTION) {
            return;
        }
        int soLuong = Integer.parseInt(soluongI);
        if (soLuong <= 0 || soLuong > Integer.parseInt(getValueTable(tblgiohang.getSelectedRow(), tblgiohang, 2))) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số lượng");
            return;
        }

        SanPham s = this.resSP.getAll(dk(" s.ma ", getValueTable(tblgiohang.getSelectedRow(), tblgiohang, 0))).get(0);
        tblgiohang.setValueAt(Integer.parseInt(getValueTable(tblgiohang.getSelectedRow(), tblgiohang, 2)) - soLuong,
                tblgiohang.getSelectedRow(), 2);
        if (Integer.parseInt(getValueTable(tblgiohang.getSelectedRow(), tblgiohang, 2)) <= 0) {
            dtm = (DefaultTableModel) tblgiohang.getModel();
            dtm.removeRow(tblgiohang.getSelectedRow());
            tblgiohang.clearSelection();
            return;
        }
        for (int i = 0; i < tbltimtheoma.getRowCount(); i++) {
            if (getValueTable(i, tbltimtheoma, 0).equals(getValueTable(tblgiohang.getSelectedRow(), tblgiohang, 0))) {
                tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(i, tbltimtheoma, 5)) + soLuong, i, 5);
                break;
            }
        }
        if (tabbHD.getSelectedIndex() == 0) {
            sanPhamHienThiOHoaDon();
        } else
            sanPhamHienThiODATHANG();

        tbnHD.clearSelection();
//        this.resSP.update(s);
//        loadTableSP();
    }//GEN-LAST:event_btnxoaspActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked

    }//GEN-LAST:event_tblgiohangMouseClicked

    private void txttimtheomaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txttimtheomaPropertyChange
        System.out.println(txttimtheoma.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_txttimtheomaPropertyChange

    private void txttimtheomaHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txttimtheomaHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimtheomaHierarchyChanged

    private void txttimtheomaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimtheomaKeyReleased
        
    }//GEN-LAST:event_txttimtheomaKeyReleased

    private void txttienkhachduaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienkhachduaKeyReleased
        try {
            if (Float.parseFloat(txttienkhachdua.getText().trim()) < 0) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        txttienthua.setText(Float.parseFloat(txttienkhachdua.getText().trim()) - (Float.parseFloat(txtdg.getText().trim())
                - Float.parseFloat(txtgiamgia.getText().trim())) + "");
    }//GEN-LAST:event_txttienkhachduaKeyReleased

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
        this.dispose();
        new QuanLyNhanVien().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamActionPerformed
        this.dispose();
        new viewSanPham().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnsanphamActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
        this.dispose();
        new KhachHangView().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnkhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhuyenmaiActionPerformed
        this.dispose();
        new KhuyenMaiView().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnkhuyenmaiActionPerformed

    private void btmthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmthongkeActionPerformed
        this.dispose();
        new ThongKe().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btmthongkeActionPerformed

    private void btnlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlichsuActionPerformed
        this.dispose();
        new LichSu().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnlichsuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new khachHangMini().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txttongtien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtien1ActionPerformed

    private void chuyenHangChoKhach(int trangThai, Date ngayTT) {
        String id = null;
        HoaDon hd;

        hd = ((HoaDon) this.resHD.getAll(dk(" d.id ", IDHD)).get(0));
// KIEM TRA XEM NEU HUY DON HANG THI KHONG TAO
        if (trangThai == 0) {
            for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                id = getValueTable(i, tblgiohang, 0);
                SanPham sp = ((SanPham) this.resSP.getAll(dk(" s.ma ", id)).get(0));
                embeddableCTHD idDouble = new embeddableCTHD(IDHD, sp.getId());
                ChiTietHoaDon ctHD = new ChiTietHoaDon(idDouble, hd, sp,
                        Integer.parseInt(getValueTable(i, tblgiohang, 2)),
                        Float.parseFloat(getValueTable(i, tblgiohang, 2)) * sp.getGiaBan(),
                        new Date(new java.util.Date().getTime()), 0,
                        Float.parseFloat(txtgiamgia1.getText().trim())
                );
                sp.setSoLuong(sp.getSoLuong() - Integer.parseInt(getValueTable(i, tblgiohang, 2)));
                hd.setHinhthucthanhtoan("Đã thanh toán");
                hd.setNgayTT(new Date());
                this.resSP.update(sp);

                this.resGH.addHDCT(ctHD);
            }
        }
        KhachHang a = new KhachHang(jcheck.createID().toString(), jcheck.randomMA(), txtTenShip.getText().trim(), txtSDTShip.getText().trim(),
                txtDShip.getText().trim());
        GioHang g = this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0);
        g.setTinhtrang(trangThai);
        hd.setTinhTrang(trangThai);
        hd.setKh(a);
        g.setNv(a);
        this.resHD.update(hd);
        this.resGH.updateGH(g);
    }

    private void btnChuyenHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenHangActionPerformed
//  CHUYEN HANG CHO KHACH HANG
        if (hdCho != null) {
            JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn chờ lúc này");
            return;
        }
        themSanPhamVaoGioHangChiTiet();
        chuyenHangChoKhach(3, null);
        loadTableHoaDon(this.resHD.getAll(""));
        clearDatHang();
    }//GEN-LAST:event_btnChuyenHangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// HUY DON HANG VAN CHUYEN

        if (IDHD == null) {
            IDHD = hdCho.getId();
        } else if (hdCho != null) {
            GioHang g = ((ChiTietGioHang) ctghCho.get(0)).getGh();
            g.setTinhtrang(2);
            this.resGH.updateGH(g);
        }
        themSanPhamVaoGioHangChiTiet();
        HoaDon hd = this.resHD.getAll(dk(" d.id ", IDHD)).get(0);

        hd.setTinhTrang(2);
        this.resHD.update(hd);

        loadTableHoaDon(this.resHD.getAll(""));
        clearDatHang();
        loadTableSP();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// KHACH HANG NHAN DUOC HANG CHUYEN SANG HOAN THANH
        themSanPhamVaoGioHangChiTiet();
        chuyenHangChoKhach(0, new Date());
        loadTableHoaDon(this.resHD.getAll(""));
        clearDatHang();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbbtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaiActionPerformed

    private void cbbtrangthaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbtrangthaiItemStateChanged
//Đã thanh toán, Chưa thanh toán, Đã hũy, Đang giao hàng
        int tt = 0;
        if (cbbtrangthai.getSelectedIndex() == 0) {
            tt = 0;
        } else if (cbbtrangthai.getSelectedIndex() == 1) {
            tt = 1;
        } else if (cbbtrangthai.getSelectedIndex() == 2) {
            tt = 2;
        } else if (cbbtrangthai.getSelectedIndex() == 3) {
            tt = 3;
        } else if (cbbtrangthai.getSelectedIndex() == 4) {
            tt = 4;
        }
        else if (cbbtrangthai.getSelectedIndex()== 5) {
            loadTableHoaDon(this.resHD.getAll(""));
            return;
        }
        loadTableHoaDon(this.resHDCho.getAll(" ct.tinhTrang = " + tt, tt));         // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaiItemStateChanged

    private void tblgiohangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblgiohangMouseEntered

    private void txttimtheomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimtheomaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimtheomaActionPerformed

    /**
     * @param args the command line arguments
     */
    private void initWebcam() {
//        if (panel == null) {
        Dimension size = WebcamResolution.QVGA.getSize();

        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);

        executor.execute(this);
        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, jPanel2.getWidth(), jPanel2.getHeight()));
//            jPanel2.repaint();
//            this.pack();
//        }
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            if (image != null) {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
//                    e.printStackTrace();
                    //No result...
                }
//                System.out.println("result trong qr: " + result.getText());
                if (result != null) {
                    int soluong;
                    result_field.setText(result.getText());
                    SanPham sanpham = null;
                    try {
                        sanpham = ((SanPham) this.resSP.getAll(dk(" s.ma ", result_field.getText().trim())).get(0));
                    } catch (Exception e) {
                    }

//Them san pham vao gio hang bang quet QR                  
                    if (sanpham != null) {
//                        int kt = 0;
                        int kt2 = 0;
//Kiem tra ten san pham co 
                        String sl = JOptionPane.showInputDialog(this, "Mời nhập số lượng ");
                        if (sl == null) {
                            break;
                        }
                        soluong = Integer.parseInt(sl);

//Kiem tra ten san pham trong gio                        
                        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                            if (getValueTable(i, tblgiohang, 1).equals(sanpham.getTenSanPham())) {
                                soluong += Integer.parseInt(getValueTable(i, tblgiohang, 2));
                                tblgiohang.setValueAt(soluong, i, 2);
                                kt2 = 1;
                                break;
                            }
                        }
//Kiem tra san pham co trong gio chua (tang so luong)                        
                        if (kt2 != 1) {
                            dtm = (DefaultTableModel) tblgiohang.getModel();
                            dtm.setRowCount(count);
                            dtm.addRow(new Object[]{
                                sanpham.getMa(),
                                sanpham.getTenSanPham(),
                                soluong,
                                ((KhachHang) dccKH.getSelectedItem()).getMa()
                            });
                            tenSP.add(sanpham.getTenSanPham());
                            count++;
                        }
                    }

// Kiểm tra Set tensanpham co rong khong và txt != null
//                    if (!tenSP.isEmpty() && result_field.getText().trim().length() != 0) {
//// kiểm tra mã san phẩm bằng với QR không
//                        for (String string : tenSP) {
//                            System.out.println("String : " + string);
//                            if (string.equals(result_field.getText())) {
//                                a = 1;
//                                System.out.println("trong fore:");
//                                break;
//                            }
//                        }
//                    }
////  Nếu bằng thì dừng while                  
//                    if (a == 1) {
//                        break;
//                    }
////Thêm mã sp vào Set
//                    tenSP.add(result_field.getText());
////Kiểm tra QR đưa vào giỏ              
//
//    
//                    for (int i = 0; i < tblgiohang.getRowCount(); i++) {
//                        if (result_field.getText().equals(tblgiohang.getValueAt(i, 0).toString())) {
//                            if (txtsl.getText().trim().length() == 0 && txtdg.getText().trim().length() == 0) {
//                                System.out.println("trong if:");
//                                txtsl.setText(tblgiohang.getValueAt(i, 2).toString());
//                                txtdg.setText(((SanPhamView) this.serSP.getALl("where s.ma = '" + tblgiohang.getValueAt(i, 0).toString() + "'").get(0)).getGiaban()
//                                        * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()) + "");
//                                result_field.setText("");
//                            } else {
//                                System.out.println("trong else:");
//                                txtsl.setText(Integer.parseInt(txtsl.getText().trim()) + Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()) + "");
//                                txtdg.setText(((SanPhamView) this.serSP.getALl("where s.ma = '" + tblgiohang.getValueAt(i, 0).toString() + "'").get(0)).getGiaban()
//                                        * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()) + Float.parseFloat(txtdg.getText().trim()) + "");
//                                result_field.setText("");
//                            }
//                            break;
//                        }
//                    }
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

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
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                banHang.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmthongke;
    private javax.swing.JButton btnChuyenHang;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btndx;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnkhuyenmai;
    private javax.swing.JButton btnlichsu;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnqmk;
    private javax.swing.JButton btnsanpham;
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btntrangchu;
    private javax.swing.JButton btntt;
    private javax.swing.JButton btnxoasp;
    private javax.swing.JButton btnxoatatca;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbdanhmuc;
    private javax.swing.JComboBox<String> cbbhinhthuctt;
    private javax.swing.JComboBox<String> cbbtrangthai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanel45;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JRadioButton rdochuatt;
    private javax.swing.JRadioButton rdodatt;
    private javax.swing.JTextField result_field;
    private javax.swing.JTabbedPane tabbHD;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tbltimtheoma;
    private javax.swing.JTable tbnHD;
    private javax.swing.JTextField txtDShip;
    private javax.swing.JTextField txtPVC;
    private javax.swing.JTextField txtSDTShip;
    private javax.swing.JTextField txtTenShip;
    private javax.swing.JTextField txtdg;
    private javax.swing.JTextField txtdg1;
    private javax.swing.JTextField txtgiamgia;
    private javax.swing.JTextField txtgiamgia1;
    private javax.swing.JTextField txtsl;
    private javax.swing.JTextField txtsl1;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JTextField txttienthua;
    private javax.swing.JTextField txttimtheoma;
    private javax.swing.JTextField txttongtien;
    private javax.swing.JTextField txttongtien1;
    // End of variables declaration//GEN-END:variables
}
