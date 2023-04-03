package view;

import domaimodel.Bieudo1;
import domaimodel.ChiTietGioHang;
import domaimodel.ChiTietHoaDon;
import domaimodel.HoaDon;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import service.SerTKDoanhThu;

public class ThongKe extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    private final SerTKDoanhThu scthd = new SerTKDoanhThu();
    private final ChiTietGioHang s = new ChiTietGioHang();

    public ThongKe() {
        initComponents();
        setLocationRelativeTo(null);
        chay();
        Bieudo1 bd = new Bieudo1();
        bd.setDataToChart1(jPanel10);
        getIconMenu(btnbanhang, "icon\\Images\\Basket.png");
        getIconMenu(btnnhanvien, "icon\\Images\\User.png");
        getIconMenu(btnsanpham, "icon\\Images\\Label.png");
        getIconMenu(btnkhachhang, "icon\\Images\\User group.png");
        getIconMenu(btnkhuyenmai, "icon\\Images\\Free.png");
        getIconMenu(btnthongke, "icon\\Images\\Diagram.png");
        getIconMenu(btnlichsu, "icon\\Images\\Clock.png");
        getIconMenu(btnquenmatkhau, "icon\\Images\\Refresh.png");
        getIconMenu(btndangxuat, "icon\\Images\\Open door.png");
        setLocationRelativeTo(null);
        String logoPath = "icon\\Images\\Basket.png";

        // Tạo ImageIcon bằng đường dẫn của logo mới
        ImageIcon logo = new ImageIcon(logoPath);

        // Tạo JFrame cho ứng dụng của bạn
        JFrame frame = new JFrame("MyApp");

        // Thiết lập logo mới cho ứng dụng của bạn
//        frame.setIconImage(logo.getImage());
//        DefaultTableModel dtm = (DefaultTableModel) tblxuat.getModel();
//        dtm.setRowCount(0);
//        dtm.addRow(new Object[]{12, 10000000});
//        dtm.addRow(new Object[]{15, 20000000});
//        dtm.addRow(new Object[]{20, 30000000});
    }

    /*----Doanh thu theo ngay --------*/
    public void findNgay(List<ChiTietHoaDon> list) {
        dtm = (DefaultTableModel) Tbltheongay.getModel();
        dtm.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.select_doanhthu_theongayTC( dateTK_doanhthu.getDate());
        for (ChiTietHoaDon c : kh) {
            dtm.addRow(new Object[]{
                c.getSOHD(), c.getTONGTIEN(), c.getSKH()
            });
        }
    }
    
  
    /*---doanh thu theo tháng*/
 /*------doanh thu theo thang---------*/
    public void findThang(List<ChiTietHoaDon> list) {
        dtm = (DefaultTableModel) Tbltheongay.getModel();
        dtm.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.select_doanhthu_theothangTC(Integer.parseInt((String) cbbthang.getSelectedItem()), Integer.parseInt(txtnam.getText()));
        for (ChiTietHoaDon s : kh) {
            dtm.addRow(new Object[]{
                s.getSOHD(), s.getTONGTIEN(), s.getSKH()
            });
        }

    }

    /*-----SAN PHAM-------*/
 /*------Tong Hop-------*/
    public void findTOP(List<ChiTietHoaDon> list) {
        dtm = (DefaultTableModel) tblTONGHOP.getModel();
        dtm.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.SelectTOP(Integer.parseInt(txtn.getText()));
        for (ChiTietHoaDon t : kh) {
            dtm.addRow(new Object[]{
                t.getMaSP(), t.getTenSP(), t.getSOHD(), t.getSluong(), t.getTONGTIEN()
            });
        }
    }

    /*----SP theo thang-----*/
    public void findSPThang(List<ChiTietHoaDon> list) {
        dtm = (DefaultTableModel) tblSP_H.getModel();
        dtm.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.SelectbySPThang(Integer.parseInt((String) cbbSPthang.getSelectedItem()), Integer.parseInt(txtSPNam.getText()));
        for (ChiTietHoaDon t : kh) {
            dtm.addRow(new Object[]{
                t.getMaSP(), t.getTenSP(), t.getSOHD(), t.getSluong(), t.getTONGTIEN()
            });
        }
    }

    /*---SP theo ngay-----*/
    public void findSPNgay(List<ChiTietHoaDon> list) {
        dtm = (DefaultTableModel) tblSP_H.getModel();
        dtm.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.SelectbySPNgay(date_SPNgay.getDate());
        for (ChiTietHoaDon t : kh) {
            dtm.addRow(new Object[]{
                t.getMaSP(), t.getTenSP(), t.getSOHD(), t.getSluong(), t.getTONGTIEN()
            });
        }
    }
    
    public void getIconMenu(JButton bt, String dd) {
        Image image = new ImageIcon(dd).getImage().getScaledInstance(24, 24, 0);
        bt.setIcon(new ImageIcon(image));
    }

    public void getImageThemAvatar(JLabel labelThemAnh, String duongDan) {
        Image image = new ImageIcon(duongDan).getImage().getScaledInstance(labelThemAnh.getWidth(), labelThemAnh.getHeight(), 0);
        labelThemAnh.setIcon(new ImageIcon(image));
    }

    public void chay() {
        Thread th = new Thread() {
            @Override
            public void run() {
                String txt = lbl_ChuChay.getText() + " ";
                while (true) {
                    txt = txt.charAt(txt.length() - 1) + txt.substring(0, txt.length() - 1);
                    //txt=txt.substring(1, txt.length())+txt.charAt(0);
                    try {
                        sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbl_ChuChay.setText(txt);
                }
            }

        };
        th.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_ChuChay = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnbanhang = new javax.swing.JButton();
        btnnhanvien = new javax.swing.JButton();
        btnsanpham = new javax.swing.JButton();
        btnkhachhang = new javax.swing.JButton();
        btnkhuyenmai = new javax.swing.JButton();
        btnthongke = new javax.swing.JButton();
        btnlichsu = new javax.swing.JButton();
        btndangxuat = new javax.swing.JButton();
        btnquenmatkhau = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        paneltk = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblSoDH2 = new javax.swing.JLabel();
        lblthanhcong2 = new javax.swing.JLabel();
        lblbihuy = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        lbldtngay = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lbldtthang = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        lblKH = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbbltg = new javax.swing.JComboBox<>();
        paneTT = new javax.swing.JPanel();
        panekhoangngay = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        datetngayBD = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        datengayKT = new com.toedter.calendar.JDateChooser();
        btnkhoangngay = new javax.swing.JButton();
        paneHientai = new javax.swing.JPanel();
        btnhiendthientai = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbltheongay = new javax.swing.JTable();
        cbbthoigian = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        panetngay = new javax.swing.JPanel();
        dateTK_doanhthu = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        panemthang = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbbthang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtnam = new javax.swing.JTextField();
        btntim = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP_H = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        panelSPtheongay = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        date_SPNgay = new com.toedter.calendar.JDateChooser();
        panelSPThang = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbbSPthang = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtSPNam = new javax.swing.JTextField();
        btnthang = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTONGHOP = new javax.swing.JTable();
        btnhien = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtn = new javax.swing.JTextField();
        cbbccngay = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("Cửa Hàng Phụ Kiện Trang Sức N1");

        lbl_ChuChay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_ChuChay.setText("Xin Chào Mọi Người Đến Đến Với Cửa Hàng Phụ Kiện Trang Sức N1.Chúc mọi người có một ngày tốt lành.                                                                                                                                                                                                                                                                                                   ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setForeground(new java.awt.Color(255, 255, 153));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên Nhân Viên :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Chức Vụ :");

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
        btnthongke.setText("Thống Kê");
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

        btndangxuat.setBackground(new java.awt.Color(255, 255, 153));
        btndangxuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndangxuat.setText("Đăng Xuất");
        btndangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndangxuatActionPerformed(evt);
            }
        });

        btnquenmatkhau.setBackground(new java.awt.Color(255, 255, 153));
        btnquenmatkhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnquenmatkhau.setText("Quên Mật Khẩu ");
        btnquenmatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquenmatkhauActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Bảo Hành");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(btnnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlichsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnquenmatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(btndangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(btnbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnlichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnquenmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Thống Kê ");

        paneltk.setBackground(new java.awt.Color(255, 255, 255));
        paneltk.setLayout(new java.awt.CardLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 0, 0, new java.awt.Color(0, 0, 0)));

        jPanel7.setBackground(new java.awt.Color(255, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jPanel20.setBackground(new java.awt.Color(255, 255, 153));
        jPanel20.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 51, 102));
        jLabel29.setText("Số ĐH :");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 51, 102));
        jLabel30.setText("Thành Công :");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 51, 102));
        jLabel31.setText("Bị Hủy :");

        lblSoDH2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoDH2.setForeground(new java.awt.Color(255, 51, 102));
        lblSoDH2.setText("0");

        lblthanhcong2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblthanhcong2.setForeground(new java.awt.Color(255, 51, 102));
        lblthanhcong2.setText("0");

        lblbihuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblbihuy.setForeground(new java.awt.Color(255, 51, 102));
        lblbihuy.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSoDH2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblthanhcong2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblbihuy, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblSoDH2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lblthanhcong2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(lblbihuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(153, 255, 153));
        jPanel21.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 102));
        jLabel32.setText("Doanh Thu Ngày :");

        lbldtngay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbldtngay.setForeground(new java.awt.Color(255, 51, 102));
        lbldtngay.setText("0");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 51, 102));
        jLabel34.setText("VND");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel32)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lbldtngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel34)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldtngay)
                    .addComponent(jLabel34))
                .addGap(23, 23, 23))
        );

        jPanel22.setBackground(new java.awt.Color(153, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 102));
        jLabel35.setText("Doanh Thu Tháng :");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 51, 102));
        jLabel36.setText("VND");

        lbldtthang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbldtthang.setForeground(new java.awt.Color(255, 51, 102));
        lbldtthang.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(lbldtthang, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36))
                    .addComponent(jLabel35))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(30, 30, 30)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldtthang)
                    .addComponent(jLabel36))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 204, 204));
        jPanel23.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 51, 102));
        jLabel38.setText("Tổng Khách Hàng");

        lblKH.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblKH.setForeground(new java.awt.Color(255, 51, 102));
        lblKH.setText("0");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblKH, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel38)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblKH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Loại Thời Gian :");

        cbbltg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo Ngày", "Hôm Nay", " ", " ", " ", " " }));
        cbbltg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbltgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbltg, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(cbbltg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        paneTT.setBackground(new java.awt.Color(255, 255, 204));
        paneTT.setBorder(new javax.swing.border.MatteBorder(null));
        paneTT.setLayout(new java.awt.CardLayout());

        panekhoangngay.setBackground(new java.awt.Color(255, 255, 204));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Từ Ngày :");

        datetngayBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datetngayBDMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Đến Ngày :");

        datengayKT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datengayKTMouseClicked(evt);
            }
        });

        btnkhoangngay.setBackground(new java.awt.Color(102, 204, 255));
        btnkhoangngay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnkhoangngay.setForeground(new java.awt.Color(0, 0, 0));
        btnkhoangngay.setText("Tìm");
        btnkhoangngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhoangngayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panekhoangngayLayout = new javax.swing.GroupLayout(panekhoangngay);
        panekhoangngay.setLayout(panekhoangngayLayout);
        panekhoangngayLayout.setHorizontalGroup(
            panekhoangngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panekhoangngayLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(datetngayBD, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(datengayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnkhoangngay, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panekhoangngayLayout.setVerticalGroup(
            panekhoangngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panekhoangngayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panekhoangngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panekhoangngayLayout.createSequentialGroup()
                        .addGroup(panekhoangngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panekhoangngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnkhoangngay)
                                .addComponent(datetngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(datengayKT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );

        paneTT.add(panekhoangngay, "card3");

        paneHientai.setBackground(new java.awt.Color(255, 255, 204));

        btnhiendthientai.setBackground(new java.awt.Color(102, 204, 255));
        btnhiendthientai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhiendthientai.setForeground(new java.awt.Color(0, 0, 0));
        btnhiendthientai.setText("Hiện");
        btnhiendthientai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhiendthientaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneHientaiLayout = new javax.swing.GroupLayout(paneHientai);
        paneHientai.setLayout(paneHientaiLayout);
        paneHientaiLayout.setHorizontalGroup(
            paneHientaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneHientaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnhiendthientai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneHientaiLayout.setVerticalGroup(
            paneHientaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneHientaiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnhiendthientai)
                .addContainerGap())
        );

        paneTT.add(paneHientai, "card2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(paneTT, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneTT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane4MouseClicked(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1302, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Biểu đồ", jPanel10);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        Tbltheongay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số ĐH", "Doanh thu", "Số Khách Hàng"
            }
        ));
        Tbltheongay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbltheongayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tbltheongay);

        cbbthoigian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo Ngày", " Theo Tháng", " " }));
        cbbthoigian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbthoigianActionPerformed(evt);
            }
        });

        jPanel11.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel11.setLayout(new java.awt.CardLayout());

        panetngay.setBackground(new java.awt.Color(255, 255, 255));
        panetngay.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        dateTK_doanhthu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateTK_doanhthuMouseClicked(evt);
            }
        });
        dateTK_doanhthu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTK_doanhthuPropertyChange(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("chọn ngày :");

        javax.swing.GroupLayout panetngayLayout = new javax.swing.GroupLayout(panetngay);
        panetngay.setLayout(panetngayLayout);
        panetngayLayout.setHorizontalGroup(
            panetngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panetngayLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addComponent(dateTK_doanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        panetngayLayout.setVerticalGroup(
            panetngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panetngayLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panetngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateTK_doanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel11.add(panetngay, "card2");

        panemthang.setBackground(new java.awt.Color(255, 255, 255));
        panemthang.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tháng :");

        cbbthang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Năm :");

        btntim.setBackground(new java.awt.Color(0, 204, 255));
        btntim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btntim.setForeground(new java.awt.Color(0, 0, 0));
        btntim.setText("Tìm");
        btntim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntimMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panemthangLayout = new javax.swing.GroupLayout(panemthang);
        panemthang.setLayout(panemthangLayout);
        panemthangLayout.setHorizontalGroup(
            panemthangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panemthangLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbthang, 0, 107, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnam, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btntim)
                .addGap(16, 16, 16))
        );
        panemthangLayout.setVerticalGroup(
            panemthangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panemthangLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panemthangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntim))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel11.add(panemthang, "card3");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(cbbthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(cbbthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane4.addTab("Chi Tiết ", jPanel16);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTabbedPane3.addTab("Doanh Thu", jPanel6);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        tblSP_H.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Số ĐH", "Tổng Tiền"
            }
        ));
        jScrollPane2.setViewportView(tblSP_H);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.CardLayout());

        panelSPtheongay.setBackground(new java.awt.Color(255, 255, 255));
        panelSPtheongay.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        panelSPtheongay.setPreferredSize(new java.awt.Dimension(480, 54));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tìm Ngày :");

        date_SPNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                date_SPNgayMouseClicked(evt);
            }
        });
        date_SPNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_SPNgayPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout panelSPtheongayLayout = new javax.swing.GroupLayout(panelSPtheongay);
        panelSPtheongay.setLayout(panelSPtheongayLayout);
        panelSPtheongayLayout.setHorizontalGroup(
            panelSPtheongayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPtheongayLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(372, Short.MAX_VALUE))
            .addGroup(panelSPtheongayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSPtheongayLayout.createSequentialGroup()
                    .addGap(87, 87, 87)
                    .addComponent(date_SPNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelSPtheongayLayout.setVerticalGroup(
            panelSPtheongayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPtheongayLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(panelSPtheongayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSPtheongayLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(date_SPNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );

        jPanel12.add(panelSPtheongay, "card2");

        panelSPThang.setBackground(new java.awt.Color(255, 255, 255));
        panelSPThang.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tháng : ");

        cbbSPthang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Năm :");

        txtSPNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSPNamActionPerformed(evt);
            }
        });

        btnthang.setBackground(new java.awt.Color(51, 204, 255));
        btnthang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnthang.setForeground(new java.awt.Color(0, 0, 0));
        btnthang.setText("Tìm");
        btnthang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnthangMouseClicked(evt);
            }
        });
        btnthang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSPThangLayout = new javax.swing.GroupLayout(panelSPThang);
        panelSPThang.setLayout(panelSPThangLayout);
        panelSPThangLayout.setHorizontalGroup(
            panelSPThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPThangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSPthang, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSPNam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthang)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelSPThangLayout.setVerticalGroup(
            panelSPThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPThangLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelSPThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbbSPthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtSPNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthang))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel12.add(panelSPThang, "card3");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng Hợp Sản Phẩm đã Bán Trong Các Năm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 51, 102))); // NOI18N
        jPanel15.setForeground(new java.awt.Color(255, 0, 102));

        tblTONGHOP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên Sp", "Số ĐH", "Số Lượng", "Tổng Tiền"
            }
        ));
        jScrollPane3.setViewportView(tblTONGHOP);

        btnhien.setBackground(new java.awt.Color(51, 204, 255));
        btnhien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhien.setForeground(new java.awt.Color(0, 0, 0));
        btnhien.setText("Hien");
        btnhien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhienActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Năm :");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(btnhien)
                        .addGap(252, 252, 252))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhien)
                .addGap(13, 13, 13))
        );

        cbbccngay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TNgày", "TTháng" }));
        cbbccngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbccngayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbccngay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(cbbccngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jTabbedPane5.addTab("Chi Tiết", jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1302, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Biều Đồ", jPanel14);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );

        jTabbedPane3.addTab("Sản Phẩm", jPanel9);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel5);

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Báo Cáo Doanh Thu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(274, 274, 274))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paneltk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(paneltk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1))))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_ChuChay, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_ChuChay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
//        this.setVisible(false);
//        new BanHang().setVisible(true);
        this.dispose();
        new viewSanPham().setVisible(true);
    }//GEN-LAST:event_btnbanhangActionPerformed

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
//        this.setVisible(false);
//        new NhanVie().setVisible(true);
        this.dispose();
        new QuanLyNhanVien().setVisible(true);
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamActionPerformed
//        this.setVisible(false);
//        new SanPha().setVisible(true);
        this.dispose();
        new viewSanPham().setVisible(true);
    }//GEN-LAST:event_btnsanphamActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
//        this.setVisible(false);
//        new KhachHan().setVisible(true);

        this.dispose();
        new KhachHangView().setVisible(true);
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnkhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhuyenmaiActionPerformed
//        this.setVisible(false);
//        new KhuyenMa().setVisible(true);
        this.dispose();
        new KhuyenMaiView().setVisible(true);
    }//GEN-LAST:event_btnkhuyenmaiActionPerformed

    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        this.dispose();
        new ThongKe().setVisible(true);
    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btnlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlichsuActionPerformed
//        this.setVisible(false);
//        new LichS().setVisible(true);

        this.dispose();
        new LichSu().setVisible(true);

    }//GEN-LAST:event_btnlichsuActionPerformed

    private void btnquenmatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquenmatkhauActionPerformed
//        this.setVisible(false);
//        new QuenMatKhau().setVisible(true);
    }//GEN-LAST:event_btnquenmatkhauActionPerformed

    private void btndangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndangxuatActionPerformed
//        this.setVisible(false);
//        new DangNhap().setVisible(true);
    }//GEN-LAST:event_btndangxuatActionPerformed

    private void cbbltgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbltgActionPerformed
        String SelectValue = (String) cbbltg.getSelectedItem();
        if (SelectValue.equals("Theo Ngày")) {
            paneHientai.setVisible(false);
            panekhoangngay.setVisible(true);
        } else {
            paneHientai.setVisible(true);
            panekhoangngay.setVisible(false);
        }
    }//GEN-LAST:event_cbbltgActionPerformed

    private void TbltheongayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbltheongayMouseClicked

        int row = Tbltheongay.getSelectedRow();
        if (cbbthoigian.getSelectedIndex() == 0) {
            lblbihuy.setText(((scthd.select_doanhthu_theongayBH(dateTK_doanhthu.getDate())))+ "");
            lblthanhcong2.setText(Tbltheongay.getValueAt(row, 0).toString());
            lbldtngay.setText(Tbltheongay.getValueAt(row, 1).toString());
            lblKH.setText(Tbltheongay.getValueAt(row, 2).toString());
            lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
        } else if (cbbthoigian.getSelectedIndex() == 1) {
            lblbihuy.setText(((scthd.select_doanhthu_theothangBH(Integer.parseInt((String) cbbthang.getSelectedItem()), Integer.parseInt(txtnam.getText())))) + "");
            lblthanhcong2.setText(Tbltheongay.getValueAt(row, 0).toString());
            lbldtthang.setText(Tbltheongay.getValueAt(row, 1).toString());
            lblKH.setText(Tbltheongay.getValueAt(row, 2).toString());
            lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
        }
    }//GEN-LAST:event_TbltheongayMouseClicked

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked

    }//GEN-LAST:event_jPanel10MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            HSSFWorkbook hSSFWorkbook = new HSSFWorkbook();
            HSSFSheet sheet = hSSFWorkbook.createSheet("Thống kê");
            HSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Số Hóa đơn");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Doanh thu");

//            for (int i = 0; i < tblxuat.getRowCount(); i++) {
//                row = sheet.createRow(3 + i + 1);
//                for (int j = 0; j < tblxuat.getColumnCount(); j++) {
//                    cell = row.createCell(j, CellType.STRING);
//                    cell.setCellValue(tblxuat.getValueAt(i, j).toString());
//                }
//
//            }
            File f = new File("Excel\\test.xls");

            System.out.println(f.createNewFile());
            try {
                FileOutputStream fis = new FileOutputStream(f);
                hSSFWorkbook.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "thanh cong");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbthoigianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbthoigianActionPerformed
        String SelectValue = (String) cbbthoigian.getSelectedItem();
        if (SelectValue.equals("Theo Ngày")) {
            panetngay.setVisible(true);
            panemthang.setVisible(false);
        } else if (SelectValue.equals(" Theo Tháng")) {
            panetngay.setVisible(false);
            panemthang.setVisible(true);
        }
    }//GEN-LAST:event_cbbthoigianActionPerformed

    private void dateTK_doanhthuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateTK_doanhthuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTK_doanhthuMouseClicked

    private void dateTK_doanhthuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTK_doanhthuPropertyChange
        if (dateTK_doanhthu.getDate() != null) {
            List<ChiTietHoaDon> list = this.scthd.select_doanhthu_theongayTC(dateTK_doanhthu.getDate());
            findNgay(list);
            System.out.println(list.size());
        }

    }//GEN-LAST:event_dateTK_doanhthuPropertyChange

    private void btntimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntimMouseClicked
        if (cbbthang.getSelectedItem() != null && txtnam.getText() != null) {
            List<ChiTietHoaDon> list = this.scthd.select_doanhthu_theothangTC(Integer.parseInt((String) cbbthang.getSelectedItem()), Integer.parseInt(txtnam.getText()));
            findThang(list);
            System.out.println(list);

            System.out.println(list.size());
        }
    }//GEN-LAST:event_btntimMouseClicked

    private void date_SPNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_SPNgayMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_date_SPNgayMouseClicked

    private void date_SPNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_SPNgayPropertyChange
        if (date_SPNgay.getDate() != null) {
            List<ChiTietHoaDon> kh = scthd.SelectbySPNgay(date_SPNgay.getDate());
            findSPNgay(kh);
        }

    }//GEN-LAST:event_date_SPNgayPropertyChange

    private void cbbccngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbccngayActionPerformed
        String SelectValue = (String) cbbccngay.getSelectedItem();
        if (SelectValue.equals("TNgày")) {
            panelSPtheongay.setVisible(true);
            panelSPThang.setVisible(false);
        } else if (SelectValue.equals("TTháng")) {
            panelSPtheongay.setVisible(false);
            panelSPThang.setVisible(true);
        }
    }//GEN-LAST:event_cbbccngayActionPerformed

    private void btnhienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhienActionPerformed
        List<ChiTietHoaDon> list = scthd.SelectTOP(Integer.parseInt(txtn.getText()));
        findTOP(list);
        System.out.println(list);
    }//GEN-LAST:event_btnhienActionPerformed

    private void txtSPNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSPNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSPNamActionPerformed

    private void btnthangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthangMouseClicked
        List<ChiTietHoaDon> kh = scthd.SelectbySPThang(Integer.parseInt((String) cbbSPthang.getSelectedItem()), Integer.parseInt(txtSPNam.getText()));
        findSPThang(kh);
        System.out.println(kh.size());

    }//GEN-LAST:event_btnthangMouseClicked

    private void datetngayBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datetngayBDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_datetngayBDMouseClicked

    private void datengayKTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datengayKTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_datengayKTMouseClicked

    private void btnkhoangngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhoangngayActionPerformed
        lblbihuy.setText(((scthd.select_khoangBH(datetngayBD.getDate(), datengayKT.getDate()))) + "");
        lblthanhcong2.setText(((ChiTietHoaDon) (scthd.select_khoangTC(datetngayBD.getDate(), datengayKT.getDate()).get(0))).getSOHD() + "");
        lbldtngay.setText(((ChiTietHoaDon) (scthd.select_khoangTC(datetngayBD.getDate(), datengayKT.getDate()).get(0))).getTONGTIEN() + "");
        lblKH.setText(((ChiTietHoaDon) (scthd.select_khoangTC(datetngayBD.getDate(), datengayKT.getDate()).get(0))).getSKH() + "");
        lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
    }//GEN-LAST:event_btnkhoangngayActionPerformed

    private void btnhiendthientaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhiendthientaiActionPerformed
        lblbihuy.setText(scthd.select_hientaiBH() + "");
        lblthanhcong2.setText(((ChiTietHoaDon) (scthd.select_hientaiTC().get(0))).getSOHD() + "");
        lbldtngay.setText(((ChiTietHoaDon) (scthd.select_hientaiTC().get(0))).getTONGTIEN() + "");
        lblKH.setText(((ChiTietHoaDon) (scthd.select_hientaiTC().get(0))).getSKH() + "");
        lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
    }//GEN-LAST:event_btnhiendthientaiActionPerformed

    private void btnthangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthangActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tbltheongay;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btndangxuat;
    private javax.swing.JButton btnhien;
    private javax.swing.JButton btnhiendthientai;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnkhoangngay;
    private javax.swing.JButton btnkhuyenmai;
    private javax.swing.JButton btnlichsu;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnquenmatkhau;
    private javax.swing.JButton btnsanpham;
    private javax.swing.JButton btnthang;
    private javax.swing.JButton btnthongke;
    private javax.swing.JButton btntim;
    private javax.swing.JComboBox<String> cbbSPthang;
    private javax.swing.JComboBox<String> cbbccngay;
    private javax.swing.JComboBox<String> cbbltg;
    private javax.swing.JComboBox<String> cbbthang;
    private javax.swing.JComboBox<String> cbbthoigian;
    private com.toedter.calendar.JDateChooser dateTK_doanhthu;
    private com.toedter.calendar.JDateChooser date_SPNgay;
    private com.toedter.calendar.JDateChooser datengayKT;
    private com.toedter.calendar.JDateChooser datetngayBD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
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
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JLabel lblKH;
    private javax.swing.JLabel lblSoDH2;
    private javax.swing.JLabel lbl_ChuChay;
    private javax.swing.JLabel lblbihuy;
    private javax.swing.JLabel lbldtngay;
    private javax.swing.JLabel lbldtthang;
    private javax.swing.JLabel lblthanhcong2;
    private javax.swing.JPanel paneHientai;
    private javax.swing.JPanel paneTT;
    private javax.swing.JPanel panekhoangngay;
    private javax.swing.JPanel panelSPThang;
    private javax.swing.JPanel panelSPtheongay;
    private javax.swing.JPanel paneltk;
    private javax.swing.JPanel panemthang;
    private javax.swing.JPanel panetngay;
    private javax.swing.JTable tblSP_H;
    private javax.swing.JTable tblTONGHOP;
    private javax.swing.JTextField txtSPNam;
    private javax.swing.JTextField txtn;
    private javax.swing.JTextField txtnam;
    // End of variables declaration//GEN-END:variables

}
