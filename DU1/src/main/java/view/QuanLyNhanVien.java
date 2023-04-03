/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Image;
import java.io.File;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.ChucVuService;
import service.NhanVienService;
import utility.JframeCheck;
import viewmodel.ChucVuViewModel;
import viewmodel.NhanVienViewModel;

/**
 *
 * @author DELL
 */
public class QuanLyNhanVien extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();

    private final NhanVienService nhanVienService = new NhanVienService();
    private final ChucVuService chucVuService = new ChucVuService();

    private final JframeCheck jframeCheck = new JframeCheck();

    private final DefaultComboBoxModel dcb = new DefaultComboBoxModel();

    private String duongDanThuMucAnh;

    private final List<Object> jtext = new ArrayList<>();

    /**
     * Creates new form NewJFrame
     */
    public QuanLyNhanVien() {
        initComponents();
        chay();
        loadCombobox();
        loadTable();
        setLocationRelativeTo(null);
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
                        Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbl_chay.setText(txt);
                }
            }

        };
        th.start();
    }

    public void getImageThemAvatar(JLabel labelThemAnh, String duongDan) {
        Image image = new ImageIcon(duongDan).getImage().getScaledInstance(labelThemAnh.getWidth(), labelThemAnh.getHeight(), 0);
        labelThemAnh.setIcon(new ImageIcon(image));
    }

    public void loadCombobox() {
//        if (nhanVienService.getChucVu(null) != null) {
        for (ChucVuViewModel chucVuViewModel : chucVuService.getAllLoad()) {
            dcb.addElement(chucVuViewModel);
        }
        comboboxChucVu.setModel(dcb);
    }
//    }

    public void loadTable() {
        dtm = (DefaultTableModel) tableHienThi.getModel();
        dtm.setRowCount(0);
        if (nhanVienService.getListNhanVienFromDb(null) != null) {
            List<NhanVienViewModel> listNhanVien = nhanVienService.getListNhanVienFromDb("");
            for (NhanVienViewModel nhanVienViewModel : listNhanVien) {
                dtm.addRow(
                        new Object[]{
                            nhanVienViewModel.getIdNhanVien(),
                            nhanVienViewModel.getMaNhanVien(),
                            nhanVienViewModel.getTenTaiKhoan(),
                            nhanVienViewModel.getTenNhanVien(),
                            nhanVienViewModel.getCmnd(),
                            nhanVienViewModel.getGioiTinh(),
                            nhanVienViewModel.getNgaySinh(),
                            nhanVienViewModel.getDiaChi(),
                            nhanVienViewModel.getSdt(),
                            nhanVienViewModel.getEmail(),
                            nhanVienViewModel.getAnh(),
                            nhanVienViewModel.getNgayTao(),
                            nhanVienViewModel.getChucVuViewModel(),
                            nhanVienViewModel.trangThai()
                        });
            }
        }
    }

    public void clear() {
        txtTenNhanVien.setText("");
        txtMaNhanVien.setText("");
        txtTenTaiKhoan.setText("");
        txtSdt.setText("");
        txtDiaChi.setText("");
        txtCmnd.setText("");
        labelThemAnh.setIcon(null);
        txtEmail.setText("");
        dateNgaySinh.setDate(null);
        dateNgayTao.setDate(null);

    }

    private NhanVienViewModel getDataNhanVien(String nhanVien) {

        int index = tableHienThi.getSelectedRow();
        String gioiTinh = null;
        int trangThai = 0;
        if (radioNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        if (comboboxTrangThai.getSelectedItem().equals("Còn làm")) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }
        if (nhanVien.equals("capNhat")) {

            return new NhanVienViewModel(
                    tableHienThi.getValueAt(index, 0).toString(),
                    txtMaNhanVien.getText(),
                    txtTenTaiKhoan.getText(),
                    txtTenNhanVien.getText(),
                    txtCmnd.getText(),
                    gioiTinh,
                    dateNgaySinh.getDate(),
                    txtDiaChi.getText(),
                    txtSdt.getText(),
                    txtEmail.getText(),
                    duongDanThuMucAnh,
                    dateNgayTao.getDate(),
                    (ChucVuViewModel) dcb.getSelectedItem(),
                    trangThai
            );
        }
        return new NhanVienViewModel(
                jframeCheck.createID().toString(),
                txtMaNhanVien.getText().trim(),
                txtTenTaiKhoan.getText().trim(),
                txtTenNhanVien.getText().trim(),
                txtCmnd.getText().trim(),
                gioiTinh,
                dateNgaySinh.getDate(),
                txtDiaChi.getText().trim(),
                txtSdt.getText().trim(),
                txtEmail.getText().trim(),
                duongDanThuMucAnh,
                dateNgayTao.getDate(),
                (ChucVuViewModel) dcb.getSelectedItem(),
                trangThai
        );
    }

    private boolean checkMaNhanVien(String maNhanVien) {
        for (int i = 0; i < nhanVienService.getListNhanVienFromDb(null).size(); i++) {
            if (nhanVienService.getListNhanVienFromDb(null).get(i).getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
                if (tableHienThi.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checkTenTaiKhoan(String tenTaiKhoan) {
        for (int i = 0; i < nhanVienService.getListNhanVienFromDb(null).size(); i++) {
            if (nhanVienService.getListNhanVienFromDb(null).get(i).getMaNhanVien().equalsIgnoreCase(tenTaiKhoan)) {
                if (tableHienThi.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checkCMND(String CMND) {
        for (int i = 0; i < nhanVienService.getListNhanVienFromDb(null).size(); i++) {
            if (nhanVienService.getListNhanVienFromDb(null).get(i).getMaNhanVien().equalsIgnoreCase(CMND)) {
                if (tableHienThi.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checkEmail(String email) {
        for (int i = 0; i < nhanVienService.getListNhanVienFromDb(null).size(); i++) {
            if (nhanVienService.getListNhanVienFromDb(null).get(i).getMaNhanVien().equalsIgnoreCase(email)) {
                if (tableHienThi.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checkSdt(String sdt) {
        for (int i = 0; i < nhanVienService.getListNhanVienFromDb(null).size(); i++) {
            if (nhanVienService.getListNhanVienFromDb(null).get(i).getMaNhanVien().equalsIgnoreCase(sdt)) {
                if (tableHienThi.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public boolean validateData() {
        LocalDate ngayHienTai = null;
        LocalDate ngaySinh = null;
        if (dateNgaySinh.getDate() != null) {
            ngayHienTai = LocalDate.now();
            ngaySinh = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dateNgaySinh.getDate()));
        }
        if (txtMaNhanVien.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên trống!");
            return false;
        } else if (!txtMaNhanVien.getText().matches("[a-zA-Z0-9]*")) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được chứa ký tự đặc biệt");
            return false;
        } else if (!checkMaNhanVien(txtMaNhanVien.getText())) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!");
            return false;
        } else if (!txtTenTaiKhoan.getText().matches("[a-zA-Z0-9]*")) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản không được chứa ký tự đặc biệt");
            return false;
        } else if (txtTenTaiKhoan.getText().trim().length() < 5) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản có độ dài tối thiểu là 5 ký tự!");
            return false;
        } else if (txtTenTaiKhoan.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản có độ dài tối đa là 50 ký tự!");
            return false;
        } else if (txtTenNhanVien.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên trống!");
            return false;
        } else if (!txtTenNhanVien.getText().matches("\\D*")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa ký tự đặc biệt");
            return false;
        } else if (txtCmnd.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Mã căn cước công dân trống!");
            return false;
        } else if (!txtCmnd.getText().matches("[0-9]{12}")) {
            JOptionPane.showMessageDialog(this, "Vui lòng xem lại mã Căn cước công dân (gồm 12 chữ số)");
            return false;
        } else if (txtEmail.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Email trống!");
            return false;
        } else if (!txtEmail.getText().matches("\\w+@\\w+\\.\\w+")) {
            JOptionPane.showMessageDialog(this, "Email sai định dạng!");
            return false;
        } else if (txtDiaChi.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Địa chi trống!");
            return false;
        } else if (!radioNam.isSelected() && !radioNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Giới tính trống!");
            return false;
        } else if (dateNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh trống!");
            return false;
        } else if ((ngayHienTai.getYear() - ngaySinh.getYear()) < 18) {
            JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi mới được nhận!");
            return false;
        } else if (dateNgayTao.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày tạo!");
            return false;
        } else if (txtSdt.getText().trim().length() < 1) {
            JOptionPane.showMessageDialog(this, "Số điện thoại trống!");
            return false;
        } else if (!txtSdt.getText().matches("0[0-9]{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng!");
            return false;
        } else {
            return true;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
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
        btnquenmk = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        txtCmnd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        radioNam = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        radioNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTenTaiKhoan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        comboboxChucVu = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        comboboxTrangThai = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        panelThemAnh = new javax.swing.JPanel();
        labelThemAnh = new javax.swing.JLabel();
        buttonThem = new javax.swing.JButton();
        buttonCapNhat = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHienThi = new javax.swing.JTable();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        dateNgayTao = new com.toedter.calendar.JDateChooser();
        lbl_chay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1382, 770));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 153));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel20.setText("Tên Nhân Viên");

        jLabel21.setText("Chức Vụ ");

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

        btnquenmk.setBackground(new java.awt.Color(255, 255, 153));
        btnquenmk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnquenmk.setText("Quên Mật Khẩu");
        btnquenmk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquenmkActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btntrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnlichsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnquenmk, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnquenmk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Quản lý nhân viên");

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Mã nhân viên:");

        jLabel8.setText("Tên nhân viên:");

        jLabel9.setText("CMND:");

        radioNam.setText("Nam");

        jLabel10.setText("Giới tính:");

        radioNu.setText("Nữ");

        jLabel11.setText("Ngày sinh:");

        jLabel12.setText("Số điện thoại:");

        jLabel13.setText("Tên tài khoản:");

        jLabel14.setText("Ngày tạo:");

        jLabel15.setText("Email:");

        jLabel16.setText("Chức vụ:");

        jLabel17.setText("Trạng thái:");

        comboboxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn làm", "Đã nghỉ" }));

        jLabel18.setText("Địa chỉ:");

        labelThemAnh.setFont(new java.awt.Font("Wide Latin", 1, 72)); // NOI18N
        labelThemAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelThemAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelThemAnhLayout = new javax.swing.GroupLayout(panelThemAnh);
        panelThemAnh.setLayout(panelThemAnhLayout);
        panelThemAnhLayout.setHorizontalGroup(
            panelThemAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelThemAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        panelThemAnhLayout.setVerticalGroup(
            panelThemAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelThemAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        buttonThem.setText("Lưu");
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        buttonCapNhat.setText("Cập nhật");
        buttonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatActionPerformed(evt);
            }
        });

        buttonXoa.setText("Xóa");
        buttonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaActionPerformed(evt);
            }
        });

        tableHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tài khoản", "Tên", "CMND", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "Ảnh", "Ngày vào làm", "Chức vụ", "Trạng thái"
            }
        ));
        tableHienThi.setGridColor(new java.awt.Color(255, 255, 255));
        tableHienThi.setSelectionBackground(new java.awt.Color(51, 102, 255));
        tableHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHienThi);
        if (tableHienThi.getColumnModel().getColumnCount() > 0) {
            tableHienThi.getColumnModel().getColumn(0).setMinWidth(0);
            tableHienThi.getColumnModel().getColumn(0).setMaxWidth(0);
            tableHienThi.getColumnModel().getColumn(10).setMinWidth(0);
            tableHienThi.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(buttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(buttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(buttonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(radioNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioNu))
                                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(100, 100, 100)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel13))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTenTaiKhoan)
                                            .addComponent(txtCmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel17))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(comboboxTrangThai, 0, 200, Short.MAX_VALUE)
                                            .addComponent(comboboxChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dateNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(123, 123, 123)
                                .addComponent(panelThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 60, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(panelThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(radioNam)
                            .addComponent(radioNu)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(dateNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboboxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboboxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Thông tin nhân viên", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lbl_chay.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbl_chay.setText("Chào Mừng Tất Mọi Người Đến Với Cửa Hàng Phụ Kiện Trang Sức N1.Chúc Mọi Người Một Ngày Tốt Lành .                                                                                                                                                                                 ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_chay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_chay, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1384, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntrangchuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntrangchuActionPerformed
//        this.setVisible(false);
//        new Trangchu().setVisible(true);
    }//GEN-LAST:event_btntrangchuActionPerformed

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
        this.setVisible(false);
        new QuanLyNhanVien().setVisible(true);
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

    private void btnquenmkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquenmkActionPerformed
        this.setVisible(false);
        new QuenMatKhau().setVisible(true);
    }//GEN-LAST:event_btnquenmkActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        this.setVisible(false);
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void labelThemAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemAnhMouseClicked
        JFileChooser chonAnh = new JFileChooser("icon\\Icon");
        chonAnh.showOpenDialog(this);
        File fileAnh = chonAnh.getSelectedFile();
        Image anh = new ImageIcon("icon\\Icon\\" + fileAnh.getName()).getImage().getScaledInstance(labelThemAnh.getWidth(), panelThemAnh.getHeight(), 0);
        labelThemAnh.setIcon(new ImageIcon(anh));
        duongDanThuMucAnh = "icon\\Icon\\" + fileAnh.getName();
    }//GEN-LAST:event_labelThemAnhMouseClicked

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        tableHienThi.clearSelection();
        if (validateData()) {
            if (nhanVienService.them(getDataNhanVien("")) == 1) {
                return;
            }
        } else {
            return;
        }
        clear();
        loadTable();

    }//GEN-LAST:event_buttonThemActionPerformed

    private void buttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatActionPerformed
        if (nhanVienService.capNhat(getDataNhanVien("capNhat")) == 1) {
            return;
        }
        loadTable();
        clear();
    }//GEN-LAST:event_buttonCapNhatActionPerformed

    private void buttonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaActionPerformed
        int index = tableHienThi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng cần xóa tại bảng!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo:", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                nhanVienService.xoa(tableHienThi.getValueAt(index, 0).toString());
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
            clear();
            loadTable();
        }
    }//GEN-LAST:event_buttonXoaActionPerformed

    private void tableHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHienThiMouseClicked
        int index = tableHienThi.getSelectedRow();
        Image image = new ImageIcon(tableHienThi.getValueAt(index, 10).toString()).getImage().getScaledInstance(labelThemAnh.getWidth(), labelThemAnh.getHeight(), 0);

        txtMaNhanVien.setText((String) tableHienThi.getValueAt(index, 1));
        txtTenTaiKhoan.setText((String) tableHienThi.getValueAt(index, 2));
        txtTenNhanVien.setText((String) tableHienThi.getValueAt(index, 3));
        txtCmnd.setText((String) tableHienThi.getValueAt(index, 4));
        if (tableHienThi.getValueAt(index, 5).equals("Nam")) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        dateNgaySinh.setDate((Date) tableHienThi.getValueAt(index, 6));
        txtDiaChi.setText((String) tableHienThi.getValueAt(index, 7));
        txtSdt.setText((String) tableHienThi.getValueAt(index, 8));
        txtEmail.setText((String) tableHienThi.getValueAt(index, 9));
        labelThemAnh.setIcon(new ImageIcon(image));
        dateNgayTao.setDate((Date) tableHienThi.getValueAt(index, 11));
        comboboxChucVu.setSelectedItem(tableHienThi.getValueAt(index, 12));
        comboboxTrangThai.setSelectedItem((String) tableHienThi.getValueAt(index, 13));
        duongDanThuMucAnh = tableHienThi.getValueAt(index, 10).toString();
    }//GEN-LAST:event_tableHienThiMouseClicked

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
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnkhuyenmai;
    private javax.swing.JButton btnlichsu;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnquenmk;
    private javax.swing.JButton btnsanpham;
    private javax.swing.JButton btnthongke;
    private javax.swing.JButton btntrangchu;
    private javax.swing.JButton buttonCapNhat;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JComboBox<String> comboboxChucVu;
    private javax.swing.JComboBox<String> comboboxTrangThai;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private com.toedter.calendar.JDateChooser dateNgayTao;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel labelThemAnh;
    private javax.swing.JLabel lbl_chay;
    private javax.swing.JPanel panelThemAnh;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTable tableHienThi;
    private javax.swing.JTextField txtCmnd;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
