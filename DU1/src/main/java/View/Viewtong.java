/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModel.ChiTietGioHang;
import DomainModel.ChiTietHoaDon;
import DomainModel.GioHang;
import DomainModel.HoaDon;
import DomainModel.KhachHang;
import DomainModel.SanPham;
import DomainModel.embeddableCTGH;
import DomainModel.embeddableCTHD;
import Responsitoties.ResGioHangCT;
import Responsitoties.ResHoaDon;
import Responsitoties.ResHoaDonCho;
import Responsitoties.ResSanPham;
import Service.SerSanPham;
import Utilities.DBConnection;
import Utilities.jframeCheck;
import ViewModel.SanPhamView;
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
import javax.swing.SwingConstants;

/**
 *
 * @author HP
 */
public class Viewtong extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    Executor executor = Executors.newSingleThreadExecutor(this);

    private SerSanPham serSP = new SerSanPham();
    private ResSanPham resSP = new ResSanPham();
    private ResHoaDonCho resHDCho = new ResHoaDonCho();
//    private SerHoaDon serHD = new SerHoaDon();
    private ResHoaDon resHD = new ResHoaDon();

    private Map<String, SanPhamView[]> listSP = new HashMap<>();
    private DefaultComboBoxModel dccKH = new DefaultComboBoxModel();

    private int count = 0;
    private String IDGH;
    private HoaDon hdCho = null;
    private List<Object> ctghCho = null;

    private Set<String> tenSP = new HashSet<>();
    private ResGioHangCT resGH = new ResGioHangCT();
    private Map<String, StringBuilder> mapSP = new HashMap<>();

    private jframeCheck jcheck = new jframeCheck();
    private DefaultTableModel dtm;

    public Viewtong() {
        initComponents();
        loadTableSP();
        initWebcam();
        loadComKH();
        loadTableHoaDon();
    }

    private void loadTableSP() {
        DefaultTableModel dtm = (DefaultTableModel) tbltimtheoma.getModel();
        dtm.setRowCount(0);
        for (SanPhamView sanPhamView : this.serSP.getALl("")) {
            dtm.addRow(new Object[]{
                sanPhamView.getMa(),
                sanPhamView.getTensanpham(),
                sanPhamView.getNhasanxuat(),
                sanPhamView.getMausac(),
                sanPhamView.getTrangthai() == 0 ? "Còn hàng" : "Hết hàng",
                sanPhamView.getSoluong(),
                sanPhamView.getGiaban(),
                sanPhamView.getTrongluong()
            });
        }
    }

    private void loadComKH() {
        for (KhachHang object : this.resHD.getALl()) {
            dccKH.addElement(object);
        }
        cbbKH.setModel(dccKH);
    }

    private void HoaDon() {
        int soluong = 0;
        float donGia = 0;

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            soluong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            donGia += ((SanPhamView) this.serSP.getALl("where s.ma = '" + tblgiohang.getValueAt(i, 0).toString() + "'").get(0)).getGiaban()
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
        }
        txtdg.setText(donGia + "");
        txtsl.setText(soluong + "");
    }

    private String dk(String ten, String giaTri) {
        return " where " + ten + " = '" + giaTri + "'";
    }

    private String getValueTable(int vt, JTable table, int i) {
        return table.getValueAt(vt, i).toString();
    }

    private void loadTableHoaDon() {
        DefaultTableModel dtm = (DefaultTableModel) tbnHD.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (HoaDon hoaDon : this.resHD.getAll("")) {
            if (hoaDon.getTrangThai() == 0) {
                tt = "Đã thanh toán";
            } else if (hoaDon.getTrangThai() == 1) {
                tt = "Đang chờ thanh toán";
            } else {
                tt = "Đã hủy";
            }
            dtm.addRow(new Object[]{
                hoaDon.getId(),
                hoaDon.getKh().getTen(),
                hoaDon.getNgayT(),
                hoaDon.getNgayTT(),
                tt,
                hoaDon.getHinhthucthanhtoan()
            });
        }
    }

    private List<ChiTietGioHang> getChiTietGioHang(String IDGH) {
        int soLuong = 0;
        List<ChiTietGioHang> listCTGH = new ArrayList<>();
//        StringBuilder tenSP1 = new StringBuilder();
//        String maKH = "";
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
//                if (maKH.equals(getValueTable(i, tblgiohang, 3)) == false) {
//                    tenSP1 = new StringBuilder();
//                }
//                tenSP1.append(getValueTable(i, tblgiohang, 1));
//                tenSP1.append(",");
//                mapSP.put(getValueTable(i, tblgiohang, 3), tenSP1);
//            maKH = getValueTable(i, tblgiohang, 3);

            int vt = i;

            GioHang gh = this.resGH.getALlGioHangs(dk("g.id", IDGH)).get(0);

            soLuong = Integer.parseInt(getValueTable(vt, tblgiohang, 2));

            SanPham spv = (SanPham) this.resSP.getAll(dk("ma", getValueTable(vt, tblgiohang, 0))).get(0);

//Update san pham
//                SanPham s = this.resSP.getAll(dk(" s.ma ", getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 0))).get(0);
//            spv.setSoluong(spv.getSoluong() - soLuong);
//            this.resSP.update(spv);
            embeddableCTGH idDouble = new embeddableCTGH(spv.getId(), IDGH);

            ChiTietGioHang ctGH = new ChiTietGioHang(idDouble, spv, gh, soLuong, 0, new Date());

            listCTGH.add(ctGH);
        }
        return listCTGH;
    }

    private void clear() {
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        ctghCho = null;
        hdCho = null;
        tblgiohang.clearSelection();
        tbnHD.clearSelection();
        tbltimtheoma.clearSelection();
//        loadTableSP();
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
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
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
        cbbKH = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbnHD = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbbtrangthai = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txttimtheoma = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();
        btnthemsp = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbbdanhmuc = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltimtheoma = new javax.swing.JTable();
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
        jPanel2 = new javax.swing.JPanel();
        result_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Cửa hàng phụ kiện trang sức N1");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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
                "Mã sản phẩm", "Sản phẩm", "Số lượng", "Mã khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
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

        cbbKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbKH, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnxoatatca)
                        .addGap(94, 94, 94)
                        .addComponent(btnxoasp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btntaohd)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxoatatca)
                    .addComponent(btnxoasp)
                    .addComponent(btntaohd))
                .addGap(13, 13, 13))
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

        cbbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán", " " }));

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        btntimkiem.setText("Tìm kiếm");

        btnthemsp.setText("Thêm sản phẩm");

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
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txttimtheoma, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                        .addGap(34, 34, 34)
                        .addComponent(btntimkiem)
                        .addGap(26, 26, 26)
                        .addComponent(btnthemsp)
                        .addGap(18, 18, 18)
                        .addComponent(cbbdanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimtheoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiem)
                    .addComponent(btnthemsp)
                    .addComponent(cbbdanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        buttonGroup1.add(rdodatt);
        rdodatt.setSelected(true);
        rdodatt.setText("Đã thanh toán");

        cbbhinhthuctt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Thẻ" }));

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(47, 47, 47)
                                .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(txtsl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel9)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(46, 46, 46)
                        .addComponent(rdodatt)
                        .addGap(18, 18, 18)
                        .addComponent(rdochuatt)
                        .addGap(29, 29, 29)
                        .addComponent(rdoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
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

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                            .addComponent(result_field, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(0, 70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lí bán hàng", jPanel4);

        javax.swing.GroupLayout jpanel45Layout = new javax.swing.GroupLayout(jpanel45);
        jpanel45.setLayout(jpanel45Layout);
        jpanel45Layout.setHorizontalGroup(
            jpanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addGap(448, 448, 448)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpanel45, javax.swing.GroupLayout.PREFERRED_SIZE, 721, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(45, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbltimtheomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltimtheomaMouseClicked
        String so = JOptionPane.showInputDialog(this, "Mời nhập số lượng");
        if (so == null) {
            return;
        }
        int soLuong = Integer.parseInt(so);
        String tenSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 1).toString();
        String maSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString();

        if (soLuong > Integer.parseInt(tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 5).toString())) {
            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
            return;
        } else if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            return;
        }

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            if (tblgiohang.getValueAt(i, 1).toString().equals(maSP_SanPham)) {
                tblgiohang.setValueAt(soLuong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
                        i, 2);
                return;
            }
        }
        DefaultTableModel dtm2 = (DefaultTableModel) tblgiohang.getModel();
        dtm2.setRowCount(count);
        count++;
        dtm2.addRow(new Object[]{
            tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString(), tenSP_SanPham, soLuong,
            ((KhachHang) dccKH.getSelectedItem()).getMa()
        });

        tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) - soLuong, tbltimtheoma.getSelectedRow(), 5);
    }//GEN-LAST:event_tbltimtheomaMouseClicked

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed
        if (tblgiohang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn vui lòng thêm sản phẩm");
            return;
        }
        HoaDon();
        List listCheck = this.resHDCho.getAll("ct.kh.ma = '" + ((KhachHang) dccKH.getSelectedItem()).getMa() + "'");
//Them gio hang neu khach hang khong co hoa don cho
        if (listCheck.isEmpty()) {

            IDGH = jcheck.createID().toString();
            this.resGH.addGH(new GioHang(IDGH, jcheck.randomMA(),
                    new Date(new java.util.Date().getTime()), 0, (KhachHang) dccKH.getSelectedItem()));

        }
        if (!listCheck.isEmpty()) {
            HoaDon k = (HoaDon) listCheck.get(0);
            if (tbnHD.getSelectedRow() != -1) {
                if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 0).equals(k.getId())) {
                    GioHang gh = ((ChiTietGioHang) ctghCho.get(0)).getGh();
                    for (ChiTietGioHang chiTietGioHang : getChiTietGioHang(gh.getId())) {
                        resGH.add(chiTietGioHang);
                    }
                }
            }
        } else {
            IDGH = jcheck.createID().toString();
            this.resGH.addGH(new GioHang(IDGH, jcheck.randomMA(),
                    new Date(), 0, (KhachHang) dccKH.getSelectedItem()));

            for (ChiTietGioHang chiTietGioHang : getChiTietGioHang(IDGH)) {
                resGH.add(chiTietGioHang);

            }
        }

        tblgiohang.clearSelection();
        tbnHD.clearSelection();
        tbltimtheoma.clearSelection();
        count = 0;
    }//GEN-LAST:event_btntaohdActionPerformed

    private void btnttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnttActionPerformed
        if (tblgiohang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn");
            return;
        }
        if (IDGH == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn");
            return;
        }

        String idHD = jcheck.createID().toString();
        String id = null;
        int trangThai = 0;
        Date ngayTT = null;

//Kiem tra hoa don cho cua khach 
//.getId().equals(getValueTable(tbnHD.getSelectedRow(), tbnHD, 0))
        List listCheck = this.resHDCho.getAll("ct.kh.ma = '" + ((KhachHang) dccKH.getSelectedItem()).getMa() + "'");
        if (!listCheck.isEmpty() && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Bạn đang có một hóa đơn chờ vui lòng thanh toán trước");
        } else if (hdCho != null) {
            ChiTietGioHang cthd = ((ChiTietGioHang) ctghCho.get(0));
            if (cthd != null && hdCho != null) {

// Xu li hoa don cho
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

                GioHang g;
                idHD = hdCho.getId();
                if (trangThai == 0) {
                    HoaDon hd = ((HoaDon) this.resHD.getAll(dk(" d.id ", idHD)).get(0));
                    if (hdCho != null) {
                        g = ((ChiTietGioHang) ctghCho.get(0)).getGh();
                        g.setTinhtrang(0);
                        this.resGH.updateGH(g);
                        hdCho.setTrangThai(0);
                        hdCho.setNgayTT(new Date(new java.util.Date().getTime()));
                        this.resGH.updateHD(hdCho);
                        hd = hdCho;
                    }

                    for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                        id = getValueTable(i, tblgiohang, 0);
                        SanPham sp = ((SanPham) this.resSP.getAll(dk(" s.ma ", id)).get(0));

                        embeddableCTHD idDouble = new embeddableCTHD(idHD, sp.getId());
                        ChiTietHoaDon ctHD = new ChiTietHoaDon(idDouble, hd, sp, Integer.parseInt(getValueTable(i, tblgiohang, 2)),
                                Float.parseFloat(getValueTable(i, tblgiohang, 2)) * sp.getGiaban(),
                                new Date(), trangThai
                        );
                        sp.setSoluong(sp.getSoluong() - Integer.parseInt(getValueTable(i, tblgiohang, 2)));
                        this.resSP.update(sp);
                        this.resGH.addHDCT(ctHD);
                        
                    }
//  reset lai tu dau     
                    clear();
                    loadTableSP();
                    count = 0;
                } else if (trangThai == 1) {
                    JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn chờ vui lòng thanh toán hoặc hủy");
                } else if (trangThai == 2) {
                    if (hdCho != null) {
                        hdCho.setTinhTrang(2);
                        this.resGH.updateHD(hdCho);
                        g = ((ChiTietGioHang) ctghCho.get(0)).getGh();
                        g.setTinhtrang(2);
                        this.resGH.updateGH(g);
// reset lai tu dau                       
                        loadTableHoaDon();
                        clear();
                        loadTableSP();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "Bạn đang có một hóa đơn chờ vui lòng thanh toán trước");
            }
        } //            HoaDon k = (HoaDon) listCheck.get(0);
        else {
//            HoaDon hdTable = this.resHD.getAll("").get(tbnHD.getSelectedRow());
//            if (hdTable.getKh().getMa().equals(((KhachHang) dccKH.getSelectedItem()).getMa()) && hdTable.getTinhTrang() == 1) {
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

//        Thêm hóa đơn
            if (hdCho == null) {
                idHD = jcheck.createID().toString();
                this.resGH.addHD(new HoaDon(idHD, cbbhinhthuctt.getSelectedItem().toString(),
                        new Date(new java.util.Date().getTime()), ngayTT, trangThai,
                        (KhachHang) dccKH.getSelectedItem(), null,
                        Double.parseDouble(txtdg.getText().trim())));
            }

            if (trangThai == 0) {
                HoaDon hd = ((HoaDon) this.resHD.getAll(dk(" d.id ", idHD)).get(0));

                for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                    id = getValueTable(i, tblgiohang, 0);
                    SanPham sp = ((SanPham) this.resSP.getAll(dk(" s.ma ", id)).get(0));

                    embeddableCTHD idDouble = new embeddableCTHD(idHD, sp.getId());
                    ChiTietHoaDon ctHD = new ChiTietHoaDon(idDouble, hd, sp, Integer.parseInt(getValueTable(i, tblgiohang, 2)),
                            Float.parseFloat(getValueTable(i, tblgiohang, 2)) * sp.getGiaban(),
                            new Date(new java.util.Date().getTime()), trangThai
                    );
                    sp.setSoluong(sp.getSoluong() - Integer.parseInt(getValueTable(i, tblgiohang, 2)));
                    this.resSP.update(sp);
                    this.resGH.addHDCT(ctHD);
                }
            } else if (trangThai == 1) {

                GioHang g = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0));
                g.setTinhtrang(1);
                this.resGH.updateGH(g);
//                
            } else if (trangThai == 2) {
                if (hdCho == null) {
                    GioHang g = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0));
                    g.setTinhtrang(2);
                    this.resGH.updateGH(g);
                }
            }
            loadTableHoaDon();
            clear();
            loadTableSP();
        }
        IDGH = null;
        txtsl.setText("");
        txtdg.setText("");
    }//GEN-LAST:event_btnttActionPerformed

    private void rdochuattActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdochuattActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdochuattActionPerformed

    private void tbnHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnHDMouseClicked
        DefaultTableModel dtm5 = (DefaultTableModel) tblgiohang.getModel();
        dtm5.setRowCount(0);
        count = 0;
        if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang chờ thanh toán")) {
            hdCho = (HoaDon) this.resHDCho.getAll(" ct.id= " + "'" + getValueTable(tbnHD.getSelectedRow(),
                    tbnHD, 0) + "'").get(0);
            ctghCho = this.resHDCho.getAllHDCHo(hdCho.getKh().getId());
            System.out.println(ctghCho.size());
            DefaultTableModel dtm3 = (DefaultTableModel) tblgiohang.getModel();
            dtm3.setRowCount(count);

            for (Object object : ctghCho) {
                System.out.println(((ChiTietGioHang) object).getSp().getMa());
                dtm3.addRow(new Object[]{
                    ((ChiTietGioHang) object).getSp().getMa(),
                    ((ChiTietGioHang) object).getSp().getTensanpham(),
                    ((ChiTietGioHang) object).getSoLuong(),
                    ((ChiTietGioHang) object).getGh().getNv().getMa()
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
        }
    }//GEN-LAST:event_tbnHDMouseClicked

    private void tbnHDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnHDMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnHDMouseEntered

    private void btnxoatatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoatatcaActionPerformed
        DefaultTableModel dtm6 = (DefaultTableModel) tblgiohang.getModel();
        dtm6.setRowCount(0);
        loadTableSP();
        count = 0;
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
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimtheomaKeyReleased

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
//Kiem tra ten san pham co trong gio hang chuaw                        
//                        if (!tenSP.isEmpty()) {
//                            for (String string : tenSP) {
//                                if (string.equals(sanpham.getTensanpham())) {
//                                    kt = 1;
//                                    break;
//                                }
//                            }
//                        }
//Kiem tra san pham co trong gio hang chua                        
//                        if (kt == 1) {
//                            break;
//                        }
                        String sl = JOptionPane.showInputDialog(this, "Mời nhập số lượng ");
                        if (sl == null) {
                            break;
                        }
                        soluong = Integer.parseInt(sl);

//Kiem tra ten san pham trong gio                        
                        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                            if (getValueTable(i, tblgiohang, 1).equals(sanpham.getTensanpham())) {
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
                                sanpham.getTensanpham(),
                                soluong,
                                ((KhachHang) dccKH.getSelectedItem()).getMa()
                            });
                            tenSP.add(sanpham.getTensanpham());
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
            java.util.logging.Logger.getLogger(Viewtong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Viewtong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Viewtong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Viewtong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Viewtong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btnthemsp;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btntt;
    private javax.swing.JButton btnxoasp;
    private javax.swing.JButton btnxoatatca;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbKH;
    private javax.swing.JComboBox<String> cbbdanhmuc;
    private javax.swing.JComboBox<String> cbbhinhthuctt;
    private javax.swing.JComboBox<String> cbbtrangthai;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanel45;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JRadioButton rdochuatt;
    private javax.swing.JRadioButton rdodatt;
    private javax.swing.JTextField result_field;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tbltimtheoma;
    private javax.swing.JTable tbnHD;
    private javax.swing.JTextField txtdg;
    private javax.swing.JTextField txtgiamgia;
    private javax.swing.JTextField txtsl;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JTextField txttienthua;
    private javax.swing.JTextField txttimtheoma;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
