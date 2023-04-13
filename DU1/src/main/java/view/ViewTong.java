/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domaimodel.BaoHanh;
import respon.chitietembe;

//import java.sql.Date;
import domaimodel.Bieudo1;

import service.SerTK_DT;

import domaimodel.KhuyenMai;
import service.KhachHangServices;
import viewmodel.KhachHangViewModel;

//import java.sql.Date;
import org.hibernate.Session;
import respon.KhuyenMaiResponsitories;
import service.KhuyenMaiServices;
import utility.DBConnection;

import java.awt.CardLayout;

import static java.lang.Thread.sleep;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import service.ChucVuService;
import service.NhanVienService;
import viewmodel.ChucVuViewModel;
import viewmodel.NhanVienViewModel;

import java.awt.event.ItemEvent;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.SerSanPham;
import service.serDanhMuc;
import viewmodel.DanhMucViewModel;
import viewmodel.SanPhamViewModel;

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
import com.itextpdf.text.BaseColor;
import domaimodel.ChiTietKhuyenMai;
import domaimodel.NhanVien;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import respon.BaoHanhRes;
import respon.ChiTietKhuyenMaiResponsitories;
import respon.KhachHangResponsitories;
import utility.JframeCheck;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import domaimodel.DanhMuc;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.Box;
import respon.resbdsp;

/**
 *
 * @author yugip
 */
public class ViewTong extends javax.swing.JFrame implements Runnable, ThreadFactory, Comparator<HoaDon> {

    public final BaoHanhRes baoHanhRes = new BaoHanhRes();
    public final KhuyenMaiServices khuyenMaiServices = new KhuyenMaiServices();
    public final resSanPham sanPham = new resSanPham();
    public final SerSanPham serSanPham = new SerSanPham();
    public final ResHoaDonCho resHoaDonCho = new ResHoaDonCho();
    public final ResHoaDon resHoaDon = new ResHoaDon();
    public final chitietembe chi = new chitietembe();
    DefaultTableModel dtmBaoHang = new DefaultTableModel();
    private final JframeCheck jcheckBaoHang = new JframeCheck();
    private final List<Object> jTextBaoHanh = new ArrayList<>();
    private DefaultComboBoxModel cbbKMBH = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbSanPhamBh = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbTT = new DefaultComboBoxModel();

    public final KhachHangServices khachHangServices = new KhachHangServices();
    public final KhachHangResponsitories hangResponsitories = new KhachHangResponsitories();
    DefaultTableModel dtmKhachhang = new DefaultTableModel();
    private final JframeCheck jcheckKhachHang = new JframeCheck();
    private final List<Object> jTextKhachhang = new ArrayList<>();

    DefaultTableModel dtmThongke = new DefaultTableModel();
    private final SerTK_DT scthd = new SerTK_DT();

    private DefaultTableModel dtmNhanVien = new DefaultTableModel();

    private final NhanVienService nhanVienService = new NhanVienService();
    private final ChucVuService chucVuService = new ChucVuService();

    private final JframeCheck jframeCheck = new JframeCheck();

    private final DefaultComboBoxModel dcb = new DefaultComboBoxModel();

    private String duongDanThuMucAnh;

    public final ChiTietKhuyenMaiResponsitories chiTietKhuyenMaiResponsitories = new ChiTietKhuyenMaiResponsitories();
    public final KhuyenMaiResponsitories khuyenMaiResponsitories = new KhuyenMaiResponsitories();
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm1 = new DefaultTableModel();
    private final JframeCheck jcheck = new JframeCheck();
    private final List<Object> jText = new ArrayList<>();
    private DefaultComboBoxModel cbbKM = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbSanPham = new DefaultComboBoxModel();
    private List<KhuyenMai> list;
    private List<ChiTietKhuyenMai> listct;

    private DefaultTableModel dtmSP = new DefaultTableModel();
    private DefaultTableModel dtmDM = new DefaultTableModel();

    private DefaultComboBoxModel dcmDongSP_SP = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcmChatLieu_SP = new DefaultComboBoxModel();

    private DefaultTableModel dtm_TT = new DefaultTableModel();
    private DefaultComboBoxModel dcmDongSP_Loc = new DefaultComboBoxModel();

    private final SerSanPham sanPhamSV = new SerSanPham();
    private final serDanhMuc danhMucSV = new serDanhMuc();

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
    private int count = 0;
    int a = 0;
    private int btnThemSanPham2;
    private String IDGH, IDHD;
    private HoaDon hdCho = null;
    private List<Object> ctghCho = null;

    private Set<String> tenSP = new HashSet<>();
    private ResGioHangCT resGH = new ResGioHangCT();
    private Map<String, StringBuilder> mapSP = new HashMap<>();

    private List<Object> jtext = new ArrayList<>();
    private List<Object> jtexgDathang = new ArrayList<>();
//    private DefaultTableModel dtm;

    public static BanHang banHang = new BanHang();
    private int checkButton;

    private List<HoaDon> listHD;
    private List<SanPham> sanphamList;

    List<SanPham> listSPUltra = this.resSP.getAll(dk(" trangThai ", "0"));

    private CardLayout carLayout;

    /**
     * Creates new form ViewTong
     */
    public ViewTong() {
        initComponents();
//        giamGia.setText("quyet");
        conBanHan();

        chay();
//        conSanPham();
//        conKhuyenMai();
//        conNhanVien();
//        conThongKe();
//        conKhachHang();
    }

//   CONSTRUCTOR CUA CAC VIEW
    private void conLichSu() {
        DefaultTableModel dtmLs = (DefaultTableModel) jTable1.getModel();
        dtmLs.setRowCount(0);
        for (HoaDon hoaDon : this.resHD.getAll("")) {
            String ttString = null;
            if (hoaDon.getTinhTrang() == 0) {
                ttString = "Hoan thanh";
            } else if (hoaDon.getTinhTrang() == 1) {
                ttString = "huy";
            } else if (hoaDon.getTinhTrang() == 4) {
                ttString = "Dang xu li";
            } else if (hoaDon.getTinhTrang() == 2) {
                ttString = "Cho";
            }
            String kh;
            if (hoaDon.getKh() == null) {
                kh = null;
            } else {
                kh = hoaDon.getKh().getTen();
            }
            dtmLs.addRow(new Object[]{
                hoaDon.getMa(),
                kh,
                hoaDon.getNgayT(),
                hoaDon.getNgayTT(),
                ttString,
                hoaDon.getHinhthucthanhtoan()
            });
        }
    }

    private void conBaoHanh() {
        jTextBaoHanh.add(txtAMoTa);
        jTextBaoHanh.add(lbSDT);
        jTextBaoHanh.add(lbTenKH);
        jTextBaoHanh.add(txtTim);
        jTextBaoHanh.add(lbMaBH);
        jTextBaoHanh.add(lbTenSP);
        jTextBaoHanh.add(cbbSuaChua);
        jTextBaoHanh.add(cbbTrangThai);
        dateNgayBH.setEnabled(false);
        loadTableCTBH();
    }

    private void conThongKe() {
        loadDTHT();

        new Bieudo1().setDataToChart1(jPanel42);
        //goi o day thanh oc cho
        new resbdsp().setDataToChart1(jPanel47);

    }

    private void conKhachHang() {
        jTextKhachhang.add(txtSDT);
        jTextKhachhang.add(txtTenKhachHang);
        jTextKhachhang.add(txtTim);
        jTextKhachhang.add(txtaDiaChi);
        jTextKhachhang.add(rdNam);
        jTextKhachhang.add(rdNu);
        loadTableKhachHang();
    }

    private void conNhanVien() {
        loadCombobox();
        loadTableNhanVien();
        sapXepTable();
    }

    private void conKhuyenMai() {

        jText.add(txtAMoTaKM);
        jText.add(txtGiaGiam);
        jText.add(txtTenKhuyenMai);
        jText.add(dateNgayBD);
        jText.add(dateNgayKT);
        jText.add(cbbTenKM);
        jText.add(rdConHan);
        jText.add(rdHetHan);

        dangNhapNV(DangNhap.nv);
        loadTableKM();
        loadComBoKM();
        loadTableSanPham();
        loadTableSanPham2();
        sapXepKhuyenMai(khuyenMaiResponsitories.getAllLoad());
    }

    private void conBanHan() {
        carLayout = (CardLayout) paneTong.getLayout();
//        loadTableSP();
//        initWebcam();
        sapXep(this.resHD.getAll(""));
        jtext.add(txttienkhachdua);
        jtext.add(txttienthua);

        jtexgDathang.add(txtTenShip);
        jtexgDathang.add(txtSDTShip);
        jtexgDathang.add(txtDShip);
//        btnThem.setVisible(false);
        if (!this.resSP.getAll("").isEmpty()) {
            loadTableSPVipPro(2, 0);
        } else {
            Tien.setVisible(false);
            Lui.setVisible(false);
        }
        result_field.setVisible(false);
        dangNhapNV(DangNhap.nv);

        getIconMenu(btnbanhang, "Basket.png");
        getIconMenu(btnnhanvien, "User.png");
        getIconMenu(btnsanpham, "Label.png");
        getIconMenu(btnkhachhang, "User group.png");
        getIconMenu(btnkhuyenmai, "Free.png");
        getIconMenu(btmthongke, "Diagram.png");
        getIconMenu(btnlichsu, "Clock.png");
        getIconMenu(btndx, "Open door.png");
        getIconMenu(btndx1, "Box.png");
        getIconMenu(jButton10, "Book.png");
    }

    public void getIconMenu(JButton bt, String dd) {
        InputStream stream = getClass().getResourceAsStream("/icon/Images/" + dd);
        System.out.println(stream);
        if (stream != null) {
            try {
                Image image = ImageIO.read(stream);
                if (image != null) {
                    Image scaledImage = image.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                    bt.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.out.println("Không đọc được ảnh");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Không tìm thấy ảnh");
        }
    }

    private void conSanPham() {
        tbHienThiSP.setModel(dtmSP);
        cbbDongSP_SP.setModel(dcmDongSP_SP);
        cbbDongSP_Loc1.setModel(dcmDongSP_Loc);
        String[] x = {"ID", "Mã", "Tên", "Màu sắc", "NSX", "Mô tả", "Gía nhập ", "Giá bán", "Trọng lượng", "Số lượng", "Kích thước", "Chất Liệu", "Danh mục", "Trạng thái", "QR"};
        dtmSP.setColumnIdentifiers(x);

        tbHienThi_DSP.setModel(dtmDM);
        String[] l = {"ID", "dòng sản phẩm"};
        dtmDM.setColumnIdentifiers(l);

        dcmDongSP_Loc.addElement(new DanhMucViewModel("Show"));

        showDataSP();
//        fillSP(0);
        showCbbDongSP();
        showCbbDongSP_Loc();
        showDataDM();
    }

//    DANG NHAP
    private void dangNhapNV(NhanVien nv) {
        Image image = new ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Icon\\" + nv.getAnh()).getImage().getScaledInstance(anhNV.getWidth(), anhNV.getHeight(), 0);
        anhNV.setIcon(new ImageIcon(image));
        tenNV.setText(nv.getTenNhanVien());
        CV.setText(nv.getChucVu().getTenChucVu());
    }

// BAN HANG  
    int countLoadTableVipPro = 0;

    private void setIcon(String dd, JLabel j) {
//        Image anh = new ImageIcon(dd).getImage().getScaledInstance(j.getWidth(), j.getHeight(), Image.SCALE_SMOOTH);
//        j.setIcon(new ImageIcon(anh));
        ImageIcon icon = new ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Image\\" + dd);
        Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

// đặt kích thước cho JLabel theo kích thước của ảnh mới
        j.setPreferredSize(new Dimension(150, 150));
        j.setIcon(scaledIcon);
    }

    private void loadTableSPVipPro(int dkCong, int stt) {
        sanphamList = new ArrayList<>();
        int sp1 = 0;
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
        if (listSP.size() > 1) {
            if (listSP != null) {
                if (dkCong > listSP.size()) {
                    dkCong = listSP.size();
                    countLoadTableVipPro++;
                }
                if (listSP.size() % 2 != 0 && countLoadTableVipPro > listSP.size()) {
                    stt = 0;
                    dkCong = 2;
                    countLoadTableVipPro = 0;
                    jpaneAnh2.setVisible(true);
                } else {
                    if (stt == listSP.size()) {
                        stt = 0;
                        dkCong = 2;
                        countLoadTableVipPro = 0;
                        btnThem1.setEnabled(true);
                    }
                }
                for (int i = stt; stt < dkCong; stt++) {

                    if (sp1 == 0) {
                        setIcon(listSP.get(stt).getMoTa(), txtAnhSP);
                        txtTenSp.setText(listSP.get(stt).getTenSanPham());
                        txtSLPRO.setText(listSP.get(stt).getSoLuong().toString());
                        txtGiaPRO.setText(listSP.get(stt).getGiaBan().toString());
                        txtMPRO4.setText(listSP.get(stt).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        if (listSP.get(stt).getTrangThai() == 0) {
                            txtTT.setText("Còn hàng");
                        } else {
                            txtTT.setText("Hết hàng");
                        }

                    } else {
                        setIcon(listSP.get(stt).getMoTa(), txtAnhSP1);
                        txtTenSp2.setText(listSP.get(stt).getTenSanPham());
                        txtSLPRO2.setText(listSP.get(stt).getSoLuong().toString());
                        txtGiaPRO2.setText(listSP.get(stt).getGiaBan().toString());
                        txtMPRO2.setText(listSP.get(stt).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        if (listSP.get(stt).getTrangThai() == 0) {
                            txtTT2.setText("Còn hàng");
                        } else {
                            txtTT2.setText("Hết hàng");
                        }
                    }
                    sanphamList.add(listSP.get(stt));
                    sp1 = 1;
                    countLoadTableVipPro++;
                }
                if (dkCong == listSP.size() && listSP.size() % 2 != 0) {
//                setIcon(null, null);
                    txtAnhSP1.setIcon(null);
                    txtTenSp2.setText("");
                    txtSLPRO2.setText("");
                    txtGiaPRO2.setText("");
                    txtMPRO2.setText("");
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                    txtTT2.setText("");
                    jpaneAnh2.setVisible(false);

                }
            }
        } else if (listSP.size() == 1) {
            setIcon(listSP.get(0).getMoTa(), txtAnhSP);
            txtTenSp.setText(listSP.get(0).getTenSanPham());
            txtSLPRO.setText(listSP.get(0).getSoLuong().toString());
            txtGiaPRO.setText(listSP.get(0).getGiaBan().toString());
            txtMPRO4.setText(listSP.get(0).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
            if (listSP.get(0).getTrangThai() == 0) {
                txtTT.setText("Còn hàng");
            } else {
                txtTT.setText("Hết hàng");
            }

            txtAnhSP1.setIcon(null);
            txtTenSp2.setText("");
            txtSLPRO2.setText("");
            txtGiaPRO2.setText("");
            txtMPRO2.setText("");
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
            txtTT2.setText("");
            jpaneAnh2.setVisible(false);
            sanphamList.add(listSP.get(0));
        }

    }

// XUAT FILE PDF 
    private void xuatFilePdf(List<SanPham> sp, HoaDon cthd, int[] soLuong, float tongTien, float[] giamGia, float giaGiam) {
        try {
            Document document = new Document();
            // Khởi tạo PdfWriter để ghi tài liệu vào OutputStream
            PdfWriter.getInstance(document, new FileOutputStream("D:\\pujic\\DU1\\src\\main\\resources\\word\\" + new Random().nextLong(100000) + ".pdf"));
            // Mở tài liệu
            document.open();

            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Images\\anh.png");
            image.scaleAbsolute(80, 80); // Thiết lập kích thước ảnh là 200x200 pixel
            float pageHeight = document.getPageSize().getHeight();
            image.setAbsolutePosition(0, pageHeight - 80);
            document.add(image);
            document.add(new Paragraph());
            // Thêm nội dung
            BaseFont bf = BaseFont.createFont("D:\\pujic\\DU1\\src\\main\\resources\\font\\Arial Unicode MS Font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 20);
            Paragraph p = new Paragraph(" Hóa đơn thanh toán ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            document.add(new Paragraph());

            Paragraph p2 = new Paragraph("--------------------------------------------------------------------------------------------------------------------");
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);

            document.add(new Paragraph());

            Font font3 = new Font(bf, 12);

            Paragraph p30 = new Paragraph("Mã hóa đơn: " + cthd.getMa(), font3);
//            p3.setAlignment(Element.ALIGN_CENTER);
            p30.setFirstLineIndent(35);
            p30.setSpacingAfter(10);
            document.add(p30);

            document.add(new Paragraph());

            Paragraph p3 = new Paragraph("Tên khách hàng: " + cthd.getKh().getTen(), font3);
//            p3.setAlignment(Element.ALIGN_CENTER);
            p3.setFirstLineIndent(35);
            p3.setSpacingAfter(10);
            document.add(p3);

            document.add(new Paragraph());

            Font font4 = new Font(bf, 12);
            Paragraph p4 = new Paragraph("Số điện thoại: " + cthd.getKh().getSdt(), font4);
//            p4.setAlignment(Element.ALIGN_CENTER);
            p4.setFirstLineIndent(35);
            p4.setSpacingAfter(10);
            document.add(p4);
            Paragraph p5 = new Paragraph("Ngày thành toán: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(new Date()), font4);
//            p4.setAlignment(Element.ALIGN_CENTER);
            p5.setFirstLineIndent(35);
            p5.setSpacingAfter(10);
            document.add(p5);

            document.add(new Paragraph());

            Paragraph p6 = new Paragraph("Nhân viên: " + cthd.getNv().getTenNhanVien(), font4);
//            p4.setAlignment(Element.ALIGN_CENTER);
            p6.setFirstLineIndent(35);
            p6.setSpacingAfter(10);
            document.add(p6);

            document.add(new Paragraph());

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(10);
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Font font20 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
            PdfPCell cell1 = new PdfPCell(new Phrase("Sản phẩm", font4));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase("SL", font4));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("Đơn giá", font4));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase("Tổng tiền", font4));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell4);

// thêm dữ liệu vào bảng
            Paragraph p17 = new Paragraph("-----------------------------------------------------------------------------------------------------------------");
            p17.setAlignment(Element.ALIGN_CENTER);
            document.add(p17);

            document.add(new Paragraph());
            for (int i = 0; i < sp.size(); i++) {
                PdfPCell cell5 = new PdfPCell(new Phrase(sp.get(i).getTenSanPham(), font4));
                cell5.setBorder(PdfPCell.NO_BORDER);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell5);

                PdfPCell cell6 = new PdfPCell(new Phrase(soLuong[i] + "", font20));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell6.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cell6);

                PdfPCell cell7 = new PdfPCell(new Phrase(sp.get(i).getGiaBan() + "", font4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell7.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cell7);

                PdfPCell cell8 = new PdfPCell(new Phrase(sp.get(i).getGiaBan() * soLuong[i] + "", font4));
                cell8.setBorder(PdfPCell.NO_BORDER);
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell8);

            }
            document.add(table);

            Paragraph p19 = new Paragraph("-----------------------------------------------------------------------------------------------------------------");
            p19.setAlignment(Element.ALIGN_CENTER);
            document.add(p19);

            Font fontTT = new Font(bf, 12, Font.BOLD);
            PdfPTable table2 = new PdfPTable(4);
            table2.setWidthPercentage(100);
            table2.getDefaultCell().setPadding(10);
            table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell cellt1 = new PdfPCell(new Phrase("Tổng tiền:", fontTT));
            cellt1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellt1.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellt1);

            PdfPCell cellN = new PdfPCell();
            cellN.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellN);

            PdfPCell cellN1 = new PdfPCell();
            cellN1.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellN1);

            PdfPCell cellt4 = new PdfPCell(new Phrase(tongTien + "", fontTT));
            cellt4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellt4.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellt4);

//  Tien giam
            PdfPCell cellt2 = new PdfPCell(new Phrase("Tiền giảm:", fontTT));
            cellt2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellt2.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellt2);

            PdfPCell cellN2 = new PdfPCell();
            cellN2.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellN2);

            PdfPCell cellN3 = new PdfPCell();
            cellN3.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellN3);

            PdfPCell cellt5 = new PdfPCell(new Phrase(giaGiam + "", fontTT));
            cellt5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellt5.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellt5);

//  Tổng tiền:
//  Tien giam
            PdfPCell cellt6 = new PdfPCell(new Phrase("Thành tiền:", fontTT));
            cellt6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellt6.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellt6);

            PdfPCell cellN4 = new PdfPCell();
            cellN4.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellN4);

            PdfPCell cellN5 = new PdfPCell();
            cellN5.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cellN5);

            PdfPCell cell5 = new PdfPCell(new Phrase(tongTien - giaGiam + "", fontTT));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cell5);

            if (cbbhinhthuctt.getSelectedIndex() == 1) {
                PdfPCell cellt7 = new PdfPCell(new Phrase("Tiền trả khách:", fontTT));
                cellt7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellt7.setBorder(PdfPCell.NO_BORDER);
                table2.addCell(cellt7);

                PdfPCell cellN10 = new PdfPCell();
                cellN10.setBorder(PdfPCell.NO_BORDER);
                table2.addCell(cellN10);

                PdfPCell cellN11 = new PdfPCell();
                cellN11.setBorder(PdfPCell.NO_BORDER);
                table2.addCell(cellN11);

                PdfPCell cellt8 = new PdfPCell(new Phrase(txttienthua.getText().trim() + "", fontTT));
                cellt8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellt8.setBorder(PdfPCell.NO_BORDER);
                table2.addCell(cellt8);
            }

            document.add(table2);
//            

            Paragraph p13 = new Paragraph("-----------------------------------------------------------------------------------------------------------------");
            p13.setAlignment(Element.ALIGN_CENTER);
            document.add(p13);
            document.add(new Paragraph());

            Paragraph p14 = new Paragraph("Chúc quý khách vui vẻ, hẹn gặp lại", font4);
            p14.setAlignment(Element.ALIGN_CENTER);
            document.add(p14);

            Paragraph p15 = new Paragraph("**********************************************************");
            p15.setAlignment(Element.ALIGN_CENTER);
            document.add(p15);

            document.add(new Paragraph());

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    //  LUI SAN PHAM
    private void loadTableGiamSPVipPro(int dkCong, int stt) {
        sanphamList = new ArrayList<>();
        dkCong--;
        int sp1 = 0;
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
        if (listSP != null) {
            if (listSP.size() > 1) {
//                JOptionPane.showMessageDialog(this, countLoadTableVipPro);
//                JOptionPane.showMessageDialog(this, dkCong);
//                JOptionPane.showMessageDialog(this, stt);

                if (dkCong <= 0) {

                    if (listSP.size() % 2 != 0) {
                        countLoadTableVipPro = listSP.size() + 1;
                        dkCong = listSP.size() - 1;
                        stt = listSP.size() - 1;
                        jpaneAnh2.setVisible(true);
                    } else {
                        countLoadTableVipPro = listSP.size();
                        dkCong = listSP.size() - 1;
                        stt = listSP.size() - 2;
                        jpaneAnh2.setVisible(true);
                    }

                }
                JOptionPane.showMessageDialog(this, countLoadTableVipPro);
                JOptionPane.showMessageDialog(this, dkCong);
                JOptionPane.showMessageDialog(this, stt);
//            JOptionPane.showMessageDialog(this, countLoadTableVipPro);
//            JOptionPane.showMessageDialog(this, stt);
//            JOptionPane.showMessageDialog(this, dkCong);
                for (int i = dkCong; dkCong >= stt; dkCong--) {
                    if (listSP.size() % 2 != 0 && stt == listSP.size() - 1) {
                        setIcon(listSP.get(dkCong).getMoTa(), txtAnhSP);
                        txtTenSp.setText(listSP.get(dkCong).getTenSanPham());
                        txtSLPRO.setText(listSP.get(dkCong).getSoLuong().toString());
                        txtGiaPRO.setText(listSP.get(dkCong).getGiaBan().toString());
                        txtMPRO4.setText(listSP.get(dkCong).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        if (listSP.get(dkCong).getTrangThai() == 0) {
                            txtTT.setText("Còn hàng");
                        } else {
                            txtTT.setText("Hết hàng");
                        }

//  SET NULL CHO PANLE 
                        txtAnhSP1.setIcon(null);
                        txtTenSp2.setText("");
                        txtSLPRO2.setText("");
                        txtGiaPRO2.setText("");
                        txtMPRO2.setText("");
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        txtTT2.setText("");
                        jpaneAnh2.setVisible(false);
                        sanphamList.add(listSP.get(dkCong));
                        break;
                    }

// TRUONG HOP SO LE 1 SAN PHAM
                    if (sp1 == 0) {
                        jpaneAnh2.setVisible(true);
                        setIcon(listSP.get(dkCong).getMoTa(), txtAnhSP1);
                        txtTenSp2.setText(listSP.get(dkCong).getTenSanPham());
                        txtSLPRO2.setText(listSP.get(dkCong).getSoLuong().toString());
                        txtGiaPRO2.setText(listSP.get(dkCong).getGiaBan().toString());
                        txtMPRO2.setText(listSP.get(dkCong).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        if (listSP.get(dkCong).getTrangThai() == 0) {
                            txtTT2.setText("Còn hàng");
                        } else {
                            txtTT2.setText("Hết hàng");
                        }
//                   
                    } else {
                        setIcon(listSP.get(dkCong).getMoTa(), txtAnhSP);
                        txtTenSp.setText(listSP.get(dkCong).getTenSanPham());
                        txtSLPRO.setText(listSP.get(dkCong).getSoLuong().toString());
                        txtGiaPRO.setText(listSP.get(dkCong).getGiaBan().toString());
                        txtMPRO4.setText(listSP.get(dkCong).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        if (listSP.get(dkCong).getTrangThai() == 0) {
                            txtTT.setText("Còn hàng");
                        } else {
                            txtTT.setText("Hết hàng");
                        }
                    }
                    sanphamList.add(listSP.get(dkCong));
                    sp1 = 1;
                    if (listSP.size() % 2 != 0 || listSP.size() > 4) {
                        countLoadTableVipPro--;
                    }

                }

                Collections.reverse(sanphamList);
            }
        } else if (listSP.size() == 1) {
            setIcon(listSP.get(0).getMoTa(), txtAnhSP);
            txtTenSp.setText(listSP.get(0).getTenSanPham());
            txtSLPRO.setText(listSP.get(0).getSoLuong().toString());
            txtGiaPRO.setText(listSP.get(0).getGiaBan().toString());
            txtMPRO4.setText(listSP.get(0).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
            if (listSP.get(0).getTrangThai() == 0) {
                txtTT.setText("Còn hàng");
            } else {
                txtTT.setText("Hết hàng");
            }

            txtAnhSP1.setIcon(null);
            txtTenSp2.setText("");
            txtSLPRO2.setText("");
            txtGiaPRO2.setText("");
            txtMPRO2.setText("");
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
            txtTT2.setText("");
            jpaneAnh2.setVisible(false);
            sanphamList.add(listSP.get(0));
        }
    }

    private void sapXep(List<HoaDon> l) {
//        LocalDateTime.parse(IDGH).
        listHD = l;
        Collections.sort(listHD, (HoaDon o1, HoaDon o2)
                -> o1.getNgayT().getTime() > o2.getNgayT().getTime() ? -1 : 1);

        loadTableHoaDon(listHD);
    }

    private KhachHang khachHangMiNi;

//    public void getDateDatHang(KhachHang data) {
//        txtTenShip.setText(data.getTen());
//        txtSDTShip.setText(data.getSdt());
//        txtDShip.setText(data.getDiaChi());
//        khachHangMiNi = data;
//    }
//    public void getData(KhachHang data) {
//
//        txttenkh.setText(data.getTen());
//        khachHangMiNi = data;
////        setLocationRelativeTo(null);
//    }
    private void loadTableSP() {
        if (this.resSP.getAll(dk(" trangThai ", "0")) != null) {
//            DefaultTableModel dtm = (DefaultTableModel) tbltimtheoma.getModel();
            dtm.setRowCount(0);
            for (SanPham sanPhamView : this.resSP.getAll(dk(" trangThai ", "0"))) {
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

//   CHECK TRUNG SDT
    private boolean checkTrungSdt(String sdt) {
        for (KhachHang khachHang : this.resKH.getAllCoDK("")) {
            if (khachHang.getSdt().equalsIgnoreCase(sdt) == true) {
                if (checkTimKiem == 0) {
                    if (JOptionPane.showConfirmDialog(this, "Số điện thoại đã tồn tại bạn có muốn chọn thông tin khách hàng này") == JOptionPane.YES_OPTION) {
                        khachHangMiNi = khachHang;
                        txtDShip.setText(khachHang.getDiaChi());
                        txtTenShip.setText(khachHang.getTen());
                        txtSDTShip.setText(khachHang.getSdt());
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

// CHECK TIEN KHACH DUA VA THONG TIN KHACH HANG
    private String dk(String ten, String giaTri) {
        return " where " + ten + " = '" + giaTri + "'";
    }

    private String getValueTable(int vt, JTable table, int i) {
        if (table.getValueAt(vt, i) != null) {
            return table.getValueAt(vt, i).toString();
        }
        return null;
    }

    private void loadTableHoaDon(List<HoaDon> l) {
        if (l != null) {
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
//        tbltimtheoma.clearSelection();
        count = 0;
        result_field.setText("");

        checkButton = 10;
        checkTimKiem = 0;

        IDHD = null;
        IDGH = null;

        timKiemSanPham = 0;
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

//  GIONG THANG DUOI NHUNG O BAN HANG
    private void clearVipProUltra() {
        ctghCho = null;
        hdCho = null;
        count = 0;
        txtDShip.setText("");
        txtSDTShip.setText("");
        txtTenShip.setText("");
        txtdg1.setText("");
        txtsl.setText("");
        checkButton = 10;
        txtsl1.setText("");
        txttongtien1.setText("");
        txtgiamgia1.setText("");
        timKiemSanPham = 0;
    }

//    DUNG TRONG TRUONG HOP CLICK HOA DON XU LI VA HD CHO
    private void clearVipPro() {
//        dtm = (DefaultTableModel) tblgiohang.getModel();
//        dtm.setRowCount(0);
        ctghCho = null;
        hdCho = null;
        count = 0;
//        IDHD = null;
//        IDGH = null;
        checkButton = 10;
        khachHangMiNi = null;
        txtsl.setText("");
        txtdg.setText("");
        txttenkh.setText("");
        txtgiamgia.setText("");
        txttienkhachdua.setText("");
        txttienthua.setText("");
        txttongtien.setText("");
        timKiemSanPham = 0;
    }

// CLEAR DU LIEU VE TU DAU "BAN HANG"
    private void clear() {
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        ctghCho = null;
        hdCho = null;
        result_field.setText("");
        a = 0;
        tblgiohang.clearSelection();
        tbnHD.clearSelection();
//        tbltimtheoma.clearSelection();
        count = 0;
        IDHD = null;
        IDGH = null;
        checkButton = 10;
        khachHangMiNi = null;
        txtsl.setText("");
        txtdg.setText("");
        txttenkh.setText("");
        txtgiamgia.setText("");
        txttienkhachdua.setText("");
        txttienthua.setText("");
        txttongtien.setText("");
        cbbhinhthuctt.setSelectedIndex(0);
        rdodatt.setSelected(true);
        timKiemSanPham = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void clickTableGioHang() {
        int soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Mời nhập số lượng"));
//        String tenSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 1).toString();
//
//        if (soLuong > Integer.parseInt(tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 5).toString())) {
//            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
//            return;
//        }

//        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
//            if (tblgiohang.getValueAt(i, 1).toString().equals(tenSP_SanPham)) {
//                tblgiohang.setValueAt(soLuong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
//                        i, 2);
//                return;
//            }
//        }
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(count);
        count++;
        dtm.addRow(new Object[]{ //            tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString(), tenSP_SanPham, soLuong
        });
    }

//  CAP NHAP SO LUONG SAN PHAM VIP PRO
    private String capNhapSoLuongSanPhamVipPro(String so, JLabel jtextfell, SanPham sp) {
        int soLuong = Integer.parseInt(so);
        String tenSP_SanPham = sp.getTenSanPham();
        String maSP_SanPham = sp.getMa();
        float ctkm2;

        if (this.ctkm.getALLJoin1(maSP_SanPham).isEmpty()) {
            ctkm2 = 0;
        } else {
            ChiTietKhuyenMai ctkm3 = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(maSP_SanPham).get(0));
            ctkm2 = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(maSP_SanPham).get(0)).getKm().getGiaGiam();
        }

        if (soLuong > Integer.parseInt(txtSLPRO.getText().trim()) && btnThemSanPham != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
            return null;
        } else if (soLuong < 0 && btnThemSanPham != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            return null;
        }

        if (sanphamList.size() > 1 && soLuong > Integer.parseInt(txtSLPRO2.getText().trim()) && btnThemSanPham2 != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
            return null;
        } else if (sanphamList.size() > 1 && soLuong < 0 && btnThemSanPham2 != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            return null;
        }

// CAP NHAP SO LUONG SAN PHAM TRONG TABLE   
        jtextfell.setText(Integer.parseInt(jtextfell.getText().trim()) - soLuong + "");
//        tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) - soLuong, tbltimtheoma.getSelectedRow(), 5);

        if (Integer.parseInt(txtSLPRO.getText().trim()) == 0) {
            txtTT.setText("Hết hàng");
        }
        if (btnThemSanPham2 != 0 && Integer.parseInt(txtSLPRO2.getText().trim()) == 0) {
            txtTT2.setText("Hết hàng");
        }

        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            if (tblgiohang.getValueAt(i, 0).toString().equals(maSP_SanPham)) {
                tblgiohang.setValueAt(soLuong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
                        i, 2);
                return "quyet";
            }
        }
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(count);
        count++;
        dtm.addRow(new Object[]{
            sp.getMa(), sp.getTenSanPham(), soLuong,
            ctkm2
        });
        return "quyet";
    }

// CHON SAN PHAM VIP PRO
    private String chonSanPhamVipPro() {

        //  KIEM TRA CO HOA DON HOAC HOA DON CHO CHUA
        if (IDHD == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm vào giỏ hàng vui lòng chọn hóa đơn");
//            tbltimtheoma.clearSelection();
            return null;
        }

        if (tabbHD.getSelectedIndex() == 1 && hdCho != null) {
            JOptionPane.showMessageDialog(this, "Không thể thay đỗi sản phẩm");
            return null;
        }

        if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equalsIgnoreCase("Đang giao hàng") == true
                || getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equalsIgnoreCase("Đang chờ thanh toán") == true) {
        } else {
            if (result_field.getText().trim().length() == 0) {
                if (a == 0) {
                    dtm = (DefaultTableModel) tblgiohang.getModel();
                    dtm.setRowCount(0);
                    a = 1;
                }
            }
        }

//        System.out.println(txtSLPRO.getText().trim());
        if (Integer.parseInt(txtSLPRO.getText().trim()) == 0 && btnThemSanPham != 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng vui lòng chọn sản phẩm khác");
            return null;
        } else if (sanphamList.size() > 1 && Integer.parseInt(txtSLPRO2.getText().trim()) == 0 && btnThemSanPham2 != 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng vui lòng chọn sản phẩm khác");
            return null;
        }

        String so = JOptionPane.showInputDialog(this, "Mời nhập số lượng");
        if (so == null) {
            return null;
        }
        if (Integer.parseInt(so) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải >= 0");
            return null;
        }
//  KIEM TRA SAN PHAM XEM CLICK VAO BUTTON NAO NET 1 THI BUTTON = 10, 2 THI BUTTON = 12      
        SanPham sp = null;
        List<SanPham> listSPHDCHO = new ArrayList<>();
        if (hdCho != null) {
            count = tblgiohang.getRowCount();
//            for (Object c : ctghCho) {
//                listSPHDCHO.add(((ChiTietGioHang) c).getSp());
//            }
//            if (btnThemSanPham == 10) {
//                sp = listSPHDCHO.get(0);
//                capNhapSoLuongSanPhamVipPro(so, txtSLPRO, sp);
////                soLuongButton = Integer.parseInt(txtSLPRO.getText().trim());
//            } else if (btnThemSanPham2 == 12) {
//                sp = listSPHDCHO.get(1);
////                soLuongButton = Integer.parseInt(txtSLPRO2.getText().trim());
//                capNhapSoLuongSanPhamVipPro(so, txtSLPRO2, sp);
//            }
        }
        int soLuongButton = 0;
        if (btnThemSanPham == 10) {
            sp = sanphamList.get(0);
            capNhapSoLuongSanPhamVipPro(so, txtSLPRO, sp);
            soLuongButton = Integer.parseInt(txtSLPRO.getText().trim());
        } else if (btnThemSanPham2 == 12) {
            sp = sanphamList.get(1);
            soLuongButton = Integer.parseInt(txtSLPRO2.getText().trim());
            capNhapSoLuongSanPhamVipPro(so, txtSLPRO2, sp);
        }

        return "la sao";
    }

    private String chonSanPham() {
        //  KIEM TRA CO HOA DON HOAC HOA DON CHO CHUA
        if (IDHD == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm vào giỏ hàng vui lòng chọn hóa đơn");
//            tbltimtheoma.clearSelection();
            return null;
        }

        if (tabbHD.getSelectedIndex() == 1 && hdCho != null) {
            JOptionPane.showMessageDialog(this, "Không thể thay đỗi sản phẩm");
            return null;
        }

//        if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equalsIgnoreCase("Đang xử lí") == true && a == 0) {
//                dtm = (DefaultTableModel) tblgiohang.getModel();
//                dtm.setRowCount(0);
//                a =1;
//        }
        if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equalsIgnoreCase("Đang giao hàng") == true
                || getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equalsIgnoreCase("Đang chờ thanh toán") == true) {
        } else {
            if (a == 0) {
                dtm = (DefaultTableModel) tblgiohang.getModel();
                dtm.setRowCount(0);
                a = 1;
            }
        }
//        else if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equalsIgnoreCase("Đang chờ thanh toán") == false) {
//            System.out.println();
//            if (a == 0) {
//                dtm = (DefaultTableModel) tblgiohang.getModel();
//                dtm.setRowCount(0);
//                a = 1;
//            }
//        }

//        if (Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) == 0) {
//            JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng vui lòng chọn sản phẩm khác");
//            return null;
//        }
        String so = JOptionPane.showInputDialog(this, "Mời nhập số lượng");
        if (so == null) {
            return null;
        }
        int soLuong = Integer.parseInt(so);
//        String tenSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 1).toString();
//        String maSP_SanPham = tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString();
        float ctkm2;
//        if (this.ctkm.getALLJoin1(maSP_SanPham).isEmpty()) {
//            ctkm2 = 0;
//        } else {
//            ctkm2 = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(maSP_SanPham).get(0)).getKm().getGiaGiam();
//        }
//
//        if (soLuong > Integer.parseInt(tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 5).toString())) {
//            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
//            return null;
//        } else if (soLuong < 0) {
//            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
//            return null;
//        }

// CAP NHAP SO LUONG SAN PHAM TRONG TABLE       
//        tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) - soLuong, tbltimtheoma.getSelectedRow(), 5);
//
//        if (Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) == 0) {
//            tbltimtheoma.setValueAt("Hết hàng", tbltimtheoma.getSelectedRow(), 4);
//        }
//        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
//            if (tblgiohang.getValueAt(i, 0).toString().equals(maSP_SanPham)) {
//                tblgiohang.setValueAt(soLuong += Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
//                        i, 2);
//                return "asdf";
//            }
//        }
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(count);
        count++;
//        dtm.addRow(new Object[]{
//            tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString(), tenSP_SanPham, soLuong,
//            ctkm2
//        });

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
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
            tongTien = donGia - giaGiam;
        }
        if (tongTien < 0) {
            tongTien = -tongTien;
        }

        txttongtien.setText(tongTien + "");
        txtdg.setText(donGia + "");
        txtsl.setText(soluong + "");
        txtgiamgia.setText(String.format("%.2f", (double) Float.parseFloat(giaGiam + "")));
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
        if (hdCho != null) {
            IDGH = hdCho.getId();
            IDHD = hdCho.getId();
        }
//        JOptionPane.showMessageDialog(this, "IDHD: " + IDHD);
//        JOptionPane.showMessageDialog(this, "IDGH: " + IDGH);
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

//    HIGHT LIGHT HOA DON DUỌC CHON
    private void hightLighHoaDonChon(String id) {
        for (int i = 0; i < listHD.size(); i++) {
            if (listHD.get(i).getId().equalsIgnoreCase(id)) {
                tbnHD.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    int timKiemSanPham = 0;
    List<SanPham> listSPSearList;

    // LOAD SO LUONG SAN PHAM TRONG PANEL
    private void loadSLSanPhamPanel() {
        List<SanPham> listSP;
        if (timKiemSanPham == 72) {
            listSP = listSPSearList;
        } else {
            listSP = this.resSP.getAll(dk(" trangThai ", "0"));
        }
        if (listSP.size() > 1) {
            txtSLPRO.setText(sanphamList.get(0).getSoLuong() + "");
            if (sanphamList.get(0).getTrangThai() == 0) {
                txtTT.setText("Còn hàng");
            }
            if (countLoadTableVipPro <= listSP.size()) {

                if (sanphamList.get(1).getTrangThai() == 0) {
                    txtTT2.setText("Còn hàng");
                }
                txtSLPRO2.setText(sanphamList.get(1).getSoLuong() + "");
            }

        } else if (listSP.size() == 1) {
            txtSLPRO.setText(sanphamList.get(0).getSoLuong() + "");
            if (sanphamList.get(0).getTrangThai() == 0) {
                txtTT.setText("Còn hàng");
            }
        }
    }

//  Tim kiem san pham dua vao ten san pham
    private void loadSLSanPhamPanelDanhChoTimKiem(List<SanPham> listSP, int dkCong, int stt) {
        sanphamList = new ArrayList<>();
        int sp1 = 0;
        if (listSP.size() > 1) {
            if (listSP != null) {
                if (dkCong > listSP.size()) {
                    dkCong = listSP.size();
                    countLoadTableVipPro++;
                }
                if (listSP.size() % 2 != 0 && countLoadTableVipPro > listSP.size()) {
                    stt = 0;
                    dkCong = 2;
                    countLoadTableVipPro = 0;
                    jpaneAnh2.setVisible(true);
                } else {
                    if (stt == listSP.size()) {
                        stt = 0;
                        dkCong = 2;
                        countLoadTableVipPro = 0;
                        btnThem1.setEnabled(true);
                    }
                }
                for (int i = stt; stt < dkCong; stt++) {

                    if (sp1 == 0) {
                        setIcon(listSP.get(stt).getMoTa(), txtAnhSP);
                        txtTenSp.setText(listSP.get(stt).getTenSanPham());
                        txtSLPRO.setText(listSP.get(stt).getSoLuong().toString());
                        txtGiaPRO.setText(listSP.get(stt).getGiaBan().toString());
                        txtMPRO4.setText(listSP.get(stt).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        if (listSP.get(stt).getTrangThai() == 0) {
                            txtTT.setText("Còn hàng");
                        } else {
                            txtTT.setText("Hết hàng");
                        }
                    } else {
                        setIcon(listSP.get(stt).getMoTa(), txtAnhSP1);
                        txtTenSp2.setText(listSP.get(stt).getTenSanPham());
                        txtSLPRO2.setText(listSP.get(stt).getSoLuong().toString());
                        txtGiaPRO2.setText(listSP.get(stt).getGiaBan().toString());
                        txtMPRO2.setText(listSP.get(stt).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                        if (listSP.get(stt).getTrangThai() == 0) {
                            txtTT2.setText("Còn hàng");
                        } else {
                            txtTT2.setText("Hết hàng");
                        }
                    }
                    sanphamList.add(listSP.get(stt));
                    sp1 = 1;
                    countLoadTableVipPro++;
                }
                if (dkCong == listSP.size() && listSP.size() % 2 != 0) {
//                setIcon(null, null);
                    txtAnhSP1.setIcon(null);
                    txtTenSp2.setText("");
                    txtSLPRO2.setText("");
                    txtGiaPRO2.setText("");
                    txtMPRO2.setText("");
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                    txtTT2.setText("");
                    jpaneAnh2.setVisible(false);

                }
            }
        } else if (listSP.size() == 1) {
            setIcon(listSP.get(0).getMoTa(), txtAnhSP);
            txtTenSp.setText(listSP.get(0).getTenSanPham());
            txtSLPRO.setText(listSP.get(0).getSoLuong().toString());
            txtGiaPRO.setText(listSP.get(0).getGiaBan().toString());
            txtMPRO4.setText(listSP.get(0).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
            if (listSP.get(0).getTrangThai() == 0) {
                txtTT.setText("Còn hàng");
            } else {
                txtTT.setText("Hết hàng");
            }

            txtAnhSP1.setIcon(null);
            txtTenSp2.setText("");
            txtSLPRO2.setText("");
            txtGiaPRO2.setText("");
            txtMPRO2.setText("");
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
            txtTT2.setText("");
            jpaneAnh2.setVisible(false);
            sanphamList.add(listSP.get(0));
        }

    }

    private void loadSLSanPhamPanelHDCho() {
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
        if (listSP.size() > 1) {
            for (int i = 0; i < tblgiohang.getRowCount(); i++) {

                if (getValueTable(i, tblgiohang, 0).
                        equals(sanphamList.get(0).getMa())) {
                    txtSLPRO.setText(sanphamList.get(0).getSoLuong()
                            - Integer.parseInt(getValueTable(i, tblgiohang, 2))
                            + "");
                    if (Integer.parseInt(txtSLPRO.getText().trim()) == 0) {
                        txtTT.setText("Hết hàng");
                    }
                    break;
                } else if (countLoadTableVipPro <= listSP.size() && getValueTable(i, tblgiohang, 0).toString().
                        equals(sanphamList.get(1).getMa())) {
                    txtSLPRO2.setText(sanphamList.get(1).getSoLuong()
                            - Integer.parseInt(getValueTable(i, tblgiohang, 2))
                            + "");
                    if (Integer.parseInt(txtSLPRO2.getText().trim()) == 0) {
                        txtTT2.setText("Hết hàng");
                    }
                    break;
                }
            }

        } else if (listSP.size() == 1) {
            for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                if (getValueTable(i, tblgiohang, 0).
                        equals(sanphamList.get(0).getMa())) {
                    txtSLPRO.setText(sanphamList.get(0).getSoLuong()
                            - Integer.parseInt(getValueTable(i, tblgiohang, 2))
                            + "");
                    if (Integer.parseInt(txtSLPRO.getText().trim()) == 0) {
                        txtTT.setText("Hết hàng");
                    }
                    break;
                }
            }
        }
    }

//    private void loadSLSanPhamPanelHuy() {
//        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
//        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
//
//            if (getValueTable(i, tblgiohang, 0).
//                    equals(sanphamList.get(0).getMa())) {
//                txtSLPRO.setText(sanphamList.get(0).getSoLuong()
//                        - Integer.parseInt(getValueTable(i, tblgiohang, 2))
//                        + "");
//                if (Integer.parseInt(txtSLPRO.getText().trim()) == 0) {
//                    txtTT.setText("Hết hàng");
//                }
//                break;
//            } else if (getValueTable(i, tblgiohang, 0).toString().
//                    equals(sanphamList.get(1).getMa())) {
//                txtSLPRO2.setText(sanphamList.get(1).getSoLuong()
//                        - Integer.parseInt(getValueTable(i, tblgiohang, 2))
//                        + "");
//                if (Integer.parseInt(txtSLPRO2.getText().trim()) == 0) {
//                    txtTT2.setText("Hết hàng");
//                }
//                break;
//            }
//        }
//    }
    private void chuyenHangChoKhach(int trangThai, Date ngayTT) {
        String id = null;
        HoaDon hd;

        if (hdCho != null) {
            IDHD = hdCho.getId();
        }

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
                hd.setNv(DangNhap.nv);
                hd.setTongTien(Double.parseDouble(txttongtien1.getText().trim()));
                if (sp.getSoLuong() == 0) {
                    sp.setTrangThai(1);
                }
                this.resSP.update(sp);

                this.resGH.addHDCT(ctHD);

//                baoHanhRes.add(new domaimodel.BaoHanh(jcheck.createID().toString(), jcheck.randomMA(),
//                        1, sp, khachHangMiNi));
            }
        }
        KhachHang a;
        if (khachHangMiNi != null) {
            a = khachHangMiNi;
        } else {
            a = new KhachHang(jcheck.createID().toString(), jcheck.randomMA(), txtTenShip.getText().trim(), txtSDTShip.getText().trim(),
                    txtDShip.getText().trim());
        }
        GioHang g = this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0);
        g.setTinhtrang(trangThai);
        hd.setTinhTrang(trangThai);
        hd.setKh(a);
        hd.setNv(DangNhap.nv);
        g.setNv(a);
        this.resHD.update(hd);
        this.resGH.updateGH(g);
    }

//  CHECK DU LIEU DAT HANG
    private boolean checkDLDatHang() {
        if (jcheck.checkData(jtexgDathang, this) == false) {
            return false;
        }
        if (!txtTenShip.getText().trim().matches("\\D*")) {
            JOptionPane.showMessageDialog(this, "Vui lòng xem lại tên (không chứa số)");
            return false;
        }

        if (!txtSDTShip.getText().trim().matches("0[0-9]{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng vui lòng nhập lại");
            return false;
        }

        if (checkTrungSdt(txtSDTShip.getText().trim()) == false) {
            return false;
        }
        return true;
    }

    int checkTimKiem = 0;
    private int btnThemSanPham;

    //    CAP NHAP SO LUONG KHI CHUYEN MAN SAN PHAM
    private void capNhapSLSPChuyenMan() {
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            if (sanphamList.get(0).getMa().equals(getValueTable(i, tblgiohang, 0)) == true) {
                txtSLPRO.setText(sanphamList.get(0).getSoLuong()
                        - Integer.parseInt(getValueTable(i, tblgiohang, 2)) + "");
                if (Integer.parseInt(txtSLPRO.getText().trim()) == 0) {
                    txtTT.setText("Hết hàng");
                }
            } else if (countLoadTableVipPro <= listSP.size()
                    && sanphamList.get(1).getMa().equals(getValueTable(i, tblgiohang, 0)) == true) {

//                JOptionPane.showMessageDialog(this, sanphamList.get(1).getMa());
                txtSLPRO2.setText(sanphamList.get(1).getSoLuong()
                        - Integer.parseInt(getValueTable(i, tblgiohang, 2)) + "");
                if (Integer.parseInt(txtSLPRO2.getText().trim()) == 0) {
                    txtTT2.setText("Hết hàng");
                }
            }
        }
    }
//        int timKiemSanPham = 0;
//        List<SanPham> listSPSearList;

// QUET 
    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();

        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);

        executor.execute(this);
        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, jPanel2.getWidth(), jPanel2.getHeight()));
        jPanel2.repaint();
        this.pack();
    }

    @Override
    public void run() {
        do {
//            dtm = (DefaultTableModel) tblgiohang.getModel();
//            dtm.setRowCount(0);
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
                        sanpham = ((SanPham) this.resSP.getAll2(dk(" s.ma ", result_field.getText().trim())).get(0));
                    } catch (Exception e) {
                    }
//                    JOptionPane.showMessageDialog(this, sanpham.getMa());
//                    JOptionPane.showMessageDialog(this, (this.resSP.getAll2(dk(" s.ma ", result_field.getText().trim())).size()));
//Them san pham vao gio hang bang quet QR                  
                    if (sanpham != null) {
//                        int kt = 0;
                        int kt2 = 0;
//Kiem TRA SO LUON SAN PHAM
                        String sl = JOptionPane.showInputDialog(this, "Mời nhập số lượng ");
                        if (sl == null) {
                            break;
                        }
//                        listSPUltra;
                        soluong = Integer.parseInt(sl);
                        int check = 0;
                        for (int j = 0; j < listSPUltra.size(); j++) {
                            if (sanpham.getMa().equalsIgnoreCase(listSPUltra.get(j).getMa()) == true) {
                                if (soluong > Integer.parseInt(listSPUltra.get(j).getSoLuong().toString())) {
                                    JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
                                    check = 1;
                                    break;
                                } else if (soluong < 0) {
                                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                                    check = 1;
                                    break;
                                }

// CAP NHAP SO LUONG SAN PHAM TRONG TABLE       
//                                tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(j, tbltimtheoma, 5)) - soluong, j, 5);
//
//                                if (Integer.parseInt(getValueTable(j, tbltimtheoma, 5)) == 0) {
//                                    tbltimtheoma.setValueAt("Hết hàng", j, 4);
//                                }
//  CAP NHAP SO LUONG SAN PHAM TRONG PANEL
                                for (int i = 0; i < sanphamList.size(); i++) {
                                    if (sanphamList.get(i).getMa().equals(sanpham.getMa())) {
                                        if (i == 0) {
                                            txtSLPRO.setText((Integer.parseInt(txtSLPRO.getText().trim())
                                                    - soluong) + "");
                                            if (Integer.parseInt(txtSLPRO.getText().trim()) == 0) {
                                                txtTT.setText("Hết hàng");
                                            }
                                        } else if (i == 1) {
                                            txtSLPRO2.setText((Integer.parseInt(txtSLPRO2.getText().trim())
                                                    - soluong) + "");
                                            if (Integer.parseInt(txtSLPRO2.getText().trim()) == 0) {
                                                txtTT2.setText("Hết hàng");
                                            }
                                        }
                                    }
                                }
                                //

                                break;
                            }
                        }
                        if (check != 1) {

//Kiem tra ten san pham trong gio            
                            if (tblgiohang.getRowCount() != 0) {
                                if (getValueTable(0, tblgiohang, 0) != null) {
                                    for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                                        if (getValueTable(i, tblgiohang, 1).equals(sanpham.getTenSanPham())) {
                                            soluong += Integer.parseInt(getValueTable(i, tblgiohang, 2));
                                            tblgiohang.setValueAt(soluong, i, 2);
                                            kt2 = 1;
                                            break;
                                        }
                                    }
                                }
                            }
//Kiem tra san pham co trong gio chua (tang so luong) 

// THEM SAN PHAM VAO GIO HANG VA HIEN THI O HOA DON
                            if (kt2 != 1) {
                                float ctkm2;
                                if (this.ctkm.getALLJoin1(sanpham.getMa()).isEmpty()) {
                                    ctkm2 = 0;
                                } else {
                                    ctkm2 = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(sanpham.getMa()).get(0)).getKm().getGiaGiam();
                                }
//                            dtm = (DefaultTableModel) tblgiohang.getModel();
//                            dtm.setRowCount(0);
                                dtm = (DefaultTableModel) tblgiohang.getModel();
                                dtm.setRowCount(count);
                                dtm.addRow(new Object[]{
                                    sanpham.getMa(),
                                    sanpham.getTenSanPham(),
                                    soluong, ctkm2});
                                tenSP.add(sanpham.getTenSanPham());
                                count++;
                            }
                            sanPhamHienThiOHoaDon();
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

    //  HAM XU LI HOA DON CHO VA GIAO HANG
    private void xuLiHoaDonChoGiaoHang(int tt) {
        float giaGiam = 0;
        hdCho = (HoaDon) this.resHDCho.getAll(" ct.id= " + "'" + getValueTable(tbnHD.getSelectedRow(),
                tbnHD, 0) + "'", tt).get(0);
        ctghCho = this.resHDCho.getAllHDCHo(hdCho.getKh().getId(), tt);
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(count);

//        if (hdCho.getNgayT().getTime() < ctkm.getAll(IDGH)) {
//            
//        }
        for (Object object : ctghCho) {
            if (!this.ctkm.getALLJoin1(((ChiTietGioHang) object)
                    .getSp().getMa()).isEmpty()) {
                ChiTietKhuyenMai ctkm3 = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(((ChiTietGioHang) object).getSp().getMa()).get(0));

                if (hdCho.getNgayT().getTime() < ctkm3.getKm().getNgayBD().getTime()) {
                    giaGiam = 0;
                } else {
                    giaGiam = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(((ChiTietGioHang) object)
                            .getSp().getMa()).get(0)).getKm().getGiaGiam();
                }
            } else {
                giaGiam = 0;
            }

            dtm.addRow(
                    new Object[]{
                        ((ChiTietGioHang) object).getSp().getMa(),
                        ((ChiTietGioHang) object).getSp().getTenSanPham(),
                        ((ChiTietGioHang) object).getSoLuong(),
                        giaGiam
                    }
            );
        }
        count++;
//        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
//            for (int j = 0; j < tbltimtheoma.getRowCount(); j++) {
//                if (tblgiohang.getValueAt(i, 0).toString().equals(getValueTable(j, tbltimtheoma, 0))) {
//                    tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(j, tbltimtheoma, 5)) - Integer.parseInt(tblgiohang.getValueAt(i, 2).toString()),
//                            j, 5);
//                    break;
//                }
//            }
//
//        }
        if (tabbHD.getSelectedIndex() == 0) {
            sanPhamHienThiOHoaDon();
            txttenkh.setText(hdCho.getKh().getTen());
        } else if (tabbHD.getSelectedIndex() == 1) {
            sanPhamHienThiODATHANG();
            txtTenShip.setText(hdCho.getKh().getTen());
            txtDShip.setText(hdCho.getKh().getDiaChi());
            txtSDTShip.setText(hdCho.getKh().getSdt());
        }

    }

//    CHECK XU LI HOA DON
//    private boolean checkXULIHOADOn() {
//        if (IDHD != null) {
//            if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 3) == null) {
//                if (JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy hóa đơn hiện tại không",
//                        "Warring", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                    HoaDon hd = this.resHD.getAll(dk(" d.id ", IDHD)).get(0);
//                    GioHang gh = this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0);
//                    hd.setTinhTrang(2);
//                    gh.setTinhtrang(2);
//                    this.resGH.updateGH(gh);
//                    this.resHD.update(hd);
//                    JOptionPane.showMessageDialog(this, "Hủy thành công");
//                    clear();
//                    IDGH = null;
//                    IDHD = null;
//                    sapXep(this.resHD.getAll(""));
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    //  CHECK DU LIEU BAN HANG
    private boolean checkDuLieuBanHang() {
        if (rdodatt.isSelected() == true) {

            if (tblgiohang.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào giỏ hàng");
                return false;
            }

            if (txttenkh.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin khách hàng");
                return false;
            }

            if (cbbhinhthuctt.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hình thức thanh toán");
                return false;
            }

            if (cbbhinhthuctt.getSelectedItem().toString().equals("Tiền mặt")) {
                if (this.jcheck.checkData(jtext, this) == false) {
                    return false;
                }

                if (!txttienkhachdua.getText().trim().matches("\\d*")) {
                    JOptionPane.showMessageDialog(this, "Tiền khách đưa chưa đúng định dạng");
                    return false;
                }
                if (Float.parseFloat(txttienkhachdua.getText().trim()) < Float.parseFloat(txttongtien.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ");
                    return false;
                }

                if (!txttienthua.getText().trim().matches("\\d*\\.\\d*")) {
                    JOptionPane.showMessageDialog(this, "Tiền thừa chưa đúng định dạng");
                    return false;
                }
            }
        }
        return true;
    }

    //************** END BAN HANG ********************
//    San Pham
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
        lbMa_SP.setText(sp.getMa());
        txtTen_SP.setText(sp.getTenSanPham());
        txtMauSac_SP.setText(sp.getMauSac());
        Image iMT = new ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Image\\" + tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 5).
                toString()).getImage().
                getScaledInstance(lb_MoTa.getWidth(), lb_MoTa.getHeight(), 0);
        lb_MoTa.setIcon(new ImageIcon(iMT));
        mt = tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 5).toString();
        txtNSX_SP.setText(sp.getNhaSanXuat());
        txtGiaNhap_SP.setText(Float.toString(sp.getGiaNhap()));
        txtGiaBan_SP.setText(Float.toString(sp.getGiaBan()));
        txtTrongLuong_SP.setText(Float.toString(sp.getTrongLuong()));
        txtSoLuong_SP.setText(Integer.toString(sp.getSoLuong()));
        txtKichThuoc_SP.setText(Float.toString(sp.getKichThuoc()));
        txtChatLieu_SP.setText(sp.getChatLieu());
        dcmDongSP_SP.setSelectedItem(sp.getDm());
        Integer tt = sp.getTrangThai();
        if (tt == 0) {
            rdConHang.setSelected(true);
        } else {
            rdHetHang.setSelected(true);
        }
        Image iQR = new ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\QRCODE\\" + tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 14)
                .toString()).getImage()
                .getScaledInstance(lbQR_SP.getWidth(), lbQR_SP.getHeight(), 0);
        lbQR_SP.setIcon(new ImageIcon(iQR));
        dd = tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 14).toString();
        tbHienThiSP.setRowSelectionInterval(row, row);
    }

    private void fill(int row) {
//        System.out.println("xxxxxxxxxxxxxxxxx    " + this.danhMucSV.getAllLoad().size()+ "++++"+ row);
        DanhMucViewModel dm = this.danhMucSV.getAllLoad().get(row);
        lbID_DSP.setText(dm.getId());
        txtDongSP_DSP.setText(dm.getDongSP());
        tbHienThi_DSP.setRowSelectionInterval(row, row);
    }

    String xx;
    String dd;

    private SanPhamViewModel getDataSP(String x) {
        xx = jcheck.randomMA();
        if (x.equals("update")) {
//            System.out.println(((DanhMucViewModel) dcmDongSP_SP.getSelectedItem()).getId());
            return new SanPhamViewModel(tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 0).toString(),
                    tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 1).toString(), txtTen_SP.getText().trim(), txtMauSac_SP.getText().trim(),
                    txtNSX_SP.getText().trim(), mt,
                    Float.valueOf(txtGiaNhap_SP.getText().trim()), Float.valueOf(txtGiaBan_SP.getText().trim()),
                    Float.valueOf(txtTrongLuong_SP.getText().trim()), Integer.valueOf(txtSoLuong_SP.getText().trim()),
                    Float.valueOf(txtKichThuoc_SP.getText()),
                    txtChatLieu_SP.getText(),
                    (DanhMucViewModel) dcmDongSP_SP.getSelectedItem(),
                    rdConHang.isSelected() == true ? 0 : 1,
                    tbHienThiSP.getValueAt(tbHienThiSP.getSelectedRow(), 1) + ".png");
        }
//        System.out.println(((DanhMucViewModel) cbbDongSP_SP.getSelectedItem()).getId());
//        System.out.println(((ChatLieuViewModel) cbbChatLieu_SP.getSelectedItem()).getId());
        return new SanPhamViewModel(jcheck.createID().toString(), xx, txtTen_SP.getText().trim(),
                txtMauSac_SP.getText().trim(), txtNSX_SP.getText().trim(), mt,
                Float.valueOf(txtGiaNhap_SP.getText().trim()), Float.valueOf(txtGiaBan_SP.getText().trim()),
                Float.valueOf(txtTrongLuong_SP.getText().trim()), Integer.valueOf(txtSoLuong_SP.getText().trim()),
                Float.valueOf(txtKichThuoc_SP.getText()),
                txtChatLieu_SP.getText(),
                (DanhMucViewModel) dcmDongSP_SP.getSelectedItem(),
                rdConHang.isSelected() == true ? 0 : 1,
                xx + ".png");

    }

    private DanhMucViewModel getDataDM(String x) {
        if (x.equals("update")) {

            return new DanhMucViewModel(tbHienThi_DSP.getValueAt(tbHienThi_DSP.getSelectedRow(), 0).toString(),
                    txtDongSP_DSP.getText().trim());
        }
        return new DanhMucViewModel(jcheck.createID().toString(), txtDongSP_DSP.getText().trim());
    }

    private void clearSP() {
        lbMa_SP.setText("");
        txtTen_SP.setText("");
        txtMauSac_SP.setText("");
        txtNSX_SP.setText("");
        lb_MoTa.setIcon(null);
        txtGiaNhap_SP.setText("");
        txtGiaBan_SP.setText("");
        txtTrongLuong_SP.setText("");
        txtSoLuong_SP.setText("");
        cbbDongSP_SP.setSelectedIndex(0);
        txtChatLieu_SP.setText("");
        txtKichThuoc_SP.setText("");
        buttonGroup1.clearSelection();
        lbQR_SP.setIcon(null);
    }

    private boolean checkSP() {

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
        if (Float.valueOf(txtGiaBan_SP.getText().trim()) <= Float.valueOf(txtGiaNhap_SP.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Giá nhập đang nhỏ hơn hoặc bằng giá bán!");
            return false;
        }
        if (txtSoLuong_SP.getText().trim().length() < 1 || !txtSoLuong_SP.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng sản phẩm và chỉ nhập số nguyên!");
            return false;
        }
        if (Float.valueOf(txtSoLuong_SP.getText()) > 0 && rdHetHang.isSelected()) {
            JOptionPane.showMessageDialog(this, "Số lượng: " + txtSoLuong_SP.getText() + " >0, vui lòng chọn còn hàng");
            return false;
        }
        if (Float.valueOf(txtSoLuong_SP.getText()) == 0 && rdConHang.isSelected()) {
            JOptionPane.showMessageDialog(this, "Số lượng: = " + txtSoLuong_SP.getText() + ", vui lòng chọn Hết hàng");
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

    private boolean checkTrungSP_Ten(String ten) {
        for (int i = 0; i < this.sanPhamSV.getALl("").size(); i++) {
            if (this.sanPhamSV.getALl("").get(i).getTenSanPham().trim().equalsIgnoreCase(ten)) {
//                JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!");
//                System.out.println("MAaaaaaaaaaaa; " + this.sanPhamSV.getALl("").get(i).getMa());
                if (tbHienThiSP.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checkTrungSP_tl(Float tl) {

        for (int i = 0; i < this.sanPhamSV.getALl("").size(); i++) {
            System.out.println("xxxxxxxxxxxxxxxxxxx: " + this.sanPhamSV.getALl("").get(i).getTrongLuong());
            if (this.sanPhamSV.getALl("").get(i).getTrongLuong().equals(tl)) {
                if (tbHienThiSP.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    String mt;

    private void selectByName(List<SanPhamViewModel> sp) {
        dtmSP.setRowCount(0);
        for (SanPhamViewModel i : this.sanPhamSV.selectByTen(txtSearch_Loc.getText())) {
            dtmSP.addRow(i.toDataRow());
        }
    }

    private Boolean checkDM_Delete() {
        for (int i = 0; i < this.sanPhamSV.getALl("").size(); i++) {
            if (tbHienThi_DSP.getValueAt(
                    tbHienThi_DSP.getSelectedRow(), 1).toString().
                    equalsIgnoreCase(this.sanPhamSV.getALl("").
                            get(i).getDm().getDongSP())) {
                JOptionPane.showMessageDialog(this, "Dòng sản phẩm "
                        + "'" + tbHienThi_DSP.getValueAt(tbHienThi_DSP.getSelectedRow(), 1).toString() + "'"
                        + " đang được sử dụng cho " + "Sản phẩm "
                        + "'" + this.sanPhamSV.getALl("").get(i).getTenSanPham() + "'" + "!\n");
                return false;
            }
        }
        return true;
    }
//************** END SAN PHAM ********************

// KHUYEN MAI
    public void loadTableKM() {
        dtm = (DefaultTableModel) tbHienThi.getModel();
        dtm.setRowCount(0);
        for (domaimodel.KhuyenMai object : khuyenMaiResponsitories.getAllLoad()) {
            if (object.getTT().equalsIgnoreCase("Hết Hạn")) {
                object.setTrangThai("Hết Hạn");
                khuyenMaiResponsitories.update(object);
//            return;
            } else if (object.getTT().equalsIgnoreCase("Chưa Hoạt Động")) {
                object.setTrangThai("Chưa Hoạt Động");
                khuyenMaiResponsitories.update(object);
            } else {
                object.setTrangThai("Còn Hạn");
                khuyenMaiResponsitories.update(object);
            }
            dtm.addRow(object.toRow());
        }

    }

    public void sapXepKhuyenMai(List<KhuyenMai> list1) {
        list = list1;
        Collections.sort(list, (KhuyenMai o1, KhuyenMai o2)
                -> o1.getNgayBD().getTime() > o2.getNgayBD().getTime() ? -1 : 1);

        loadTableKM1(list);

    }

    public void loadTableKM1(List<KhuyenMai> lit) {
        dtmBaoHang = (DefaultTableModel) tbHienThi.getModel();
        dtmBaoHang.setRowCount(0);
        for (domaimodel.KhuyenMai object : lit) {
            dtmBaoHang.addRow(object.toRow());
        }
    }
    private DefaultTableModel dtmKhuyenmai;

    public void finTT(String a) {
        dtmKhuyenmai = (DefaultTableModel) tbHienThi.getModel();
        dtmKhuyenmai.setRowCount(0);
        for (domaimodel.KhuyenMai object : khuyenMaiResponsitories.getAllLoad()) {
            dtmKhuyenmai.addRow(object.toRow());
        }

    }

    public void finTenKM(List<domaimodel.KhuyenMai> list) {
        dtmKhuyenmai = (DefaultTableModel) tbHienThi.getModel();
        dtmKhuyenmai.setRowCount(0);
        List<domaimodel.KhuyenMai> khvm = khuyenMaiResponsitories.SelectbyName(txtTimKM.getText());
        for (domaimodel.KhuyenMai khvms : khvm) {
            dtmKhuyenmai.addRow(khvms.toRow());
        }
    }

    public void finTenSP(List<ChiTietKhuyenMai> list) {
        dtmKhuyenmai = (DefaultTableModel) tbHienThiSP.getModel();
        dtm.setRowCount(0);
        List<ChiTietKhuyenMai> khvm = chiTietKhuyenMaiResponsitories.SelectbyNameSP(txtTimSP.getText());
        for (ChiTietKhuyenMai khvms : khvm) {
            dtmKhuyenmai.addRow(khvms.toRow1());
        }
    }

    public void finTrangThai(List<KhuyenMai> list) {

        dtmKhuyenmai = (DefaultTableModel) tbHienThi.getModel();
        dtmKhuyenmai.setRowCount(0);

        List<KhuyenMai> khvm = khuyenMaiResponsitories.SelectbyTrangThai((String) cbbLocTrangThaiKM.getSelectedItem());
        for (KhuyenMai khvms : khvm) {
            if (khvms.getTT().equalsIgnoreCase("Hết Hạn")) {
                khvms.setTrangThai("Hết Hạn");
                khuyenMaiResponsitories.update(khvms);
//            return;
            } else if (khvms.getTT().equalsIgnoreCase("Chưa Hoạt Động")) {
                khvms.setTrangThai("Chưa Hoạt Động");
                khuyenMaiResponsitories.update(khvms);
            } else {
                khvms.setTrangThai("Còn Hạn");
                khuyenMaiResponsitories.update(khvms);
            }
            dtmKhuyenmai.addRow(khvms.toRow());
        }

    }

    public void finGia(List<SanPham> list) {
        dtmKhuyenmai = (DefaultTableModel) tbHienThiTTSP.getModel();
        dtmKhuyenmai.setRowCount(0);
        List<SanPham> khvm = sanPham.SelectbyGia(Float.parseFloat(txtTu.getText()), Float.parseFloat(txtDen.getText()));
        for (SanPham khvms : khvm) {

            dtmKhuyenmai.addRow(khvms.toRowi());
        }
    }

    public void loadComBoKM() {
        List<domaimodel.KhuyenMai> list = khuyenMaiResponsitories.getAllLoad();
        for (domaimodel.KhuyenMai danhMucViewModel : list) {
            if (danhMucViewModel.getTT().equalsIgnoreCase("Chưa Hoạt Động")
                    || danhMucViewModel.getTT().equalsIgnoreCase("Còn Hạn")) {
                cbbKM.addElement(danhMucViewModel);
            }
        }
        cbbTenKM.setModel(cbbKM);
    }

    public void loadTableSanPham() {
        dtmKhuyenmai = (DefaultTableModel) tbHienThiSP1.getModel();
        dtmKhuyenmai.setRowCount(0);
        if (chiTietKhuyenMaiResponsitories.getAll(null) != null) {
            for (ChiTietKhuyenMai object : chiTietKhuyenMaiResponsitories.getAll(null)) {
                dtmKhuyenmai.addRow(object.toRow1());
            }
        }
    }

    public void loadTableSanPham2() {
        dtmKhuyenmai = (DefaultTableModel) tbHienThiTTSP.getModel();
        dtmKhuyenmai.setRowCount(0);
        for (SanPham object1 : sanPham.getAll123(null)) {
            dtmKhuyenmai.addRow(object1.toRowi());
        }
    }

    private void selectbyTen(List<SanPham> sp) {
        dtmKhuyenmai = (DefaultTableModel) tbHienThiTTSP.getModel();
        dtmKhuyenmai.setRowCount(0);
        List<SanPham> khvm = sanPham.SelectbyTen(txtTimKiemSP.getText());
        for (SanPham khvms : khvm) {
            dtmKhuyenmai.addRow(khvms.toRowi());
        }
    }

    public void fillFormKM(int row) throws ParseException {
        domaimodel.KhuyenMai ctkmvm = khuyenMaiResponsitories.getAllLoad().get(row);
        txtTenKhuyenMai.setText(ctkmvm.getTenKM());
        String gia = Float.toString(ctkmvm.getGiaGiam());
        txtGiaGiam.setText(gia);
        txtAMoTaKM.setText(ctkmvm.getMoTa());
        Date ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayBD()));
        Date ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(ctkmvm.getNgayKT()));
        dateNgayBD.setDate(ngayBD);
        dateNgayKT.setDate(ngayKT);
        String gt = ctkmvm.getTT();
        if (gt.equalsIgnoreCase("Hết Hạn")) {
            rdHetHan.setSelected(true);
        } else {
            rdConHan.setSelected(true);
        }

    }

    public void fillFormSP(int row) throws ParseException {
        ChiTietKhuyenMai ctkm = chiTietKhuyenMaiResponsitories.getAll(null).get(row);
        cbbKM.setSelectedItem(ctkm.getKm());

    }

    private KhuyenMai getData(String dk) {
        if (dk.equalsIgnoreCase("update")) {
            System.out.println("update");
            System.out.println("id" + tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString());
            return new domaimodel.KhuyenMai(tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0).toString(),
                    tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 1).toString(),
                    txtTenKhuyenMai.getText().trim(), Float.parseFloat(txtGiaGiam.getText().trim()),
                    dateNgayBD.getDate(), dateNgayKT.getDate(), rdHetHan.isSelected() == true ? "Hết Hạn" : "Còn Hạn", txtAMoTaKM.getText().trim());
        }

        return new KhuyenMai(jcheck.createID().toString(),
                jcheck.randomMA(),
                txtTenKhuyenMai.getText().trim(), Float.parseFloat(txtGiaGiam.getText().trim()),
                dateNgayBD.getDate(), dateNgayKT.getDate(), rdHetHan.isSelected() == true ? "Hết Hạn" : "Còn Hạn",
                txtAMoTaKM.getText().trim());
    }

    private boolean checkMaKM(String ma) {
        for (int i = 0; i < khuyenMaiResponsitories.getAllLoad().size(); i++) {
            System.out.println("makm " + khuyenMaiResponsitories.getAllLoad().get(i).getTenKM());
            if (khuyenMaiResponsitories.getAllLoad().get(i).getTenKM().equalsIgnoreCase(ma)) {
                if (tbHienThi.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean checKM(String tenKM, String tenSP) {
        for (int i = 0; i < chiTietKhuyenMaiResponsitories.getAll("").size(); i++) {
            if (chiTietKhuyenMaiResponsitories.getAll("").get(i).getKm().getTenKM().
                    equalsIgnoreCase(tenKM)
                    && chiTietKhuyenMaiResponsitories.getAll("").get(i).getSp().getTenSanPham()
                            .equalsIgnoreCase(tenSP)) {
                if (tbHienThiSP1.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
//  1 san pham co 1 km dang hoat dong, n km chua hoat dong

    private boolean checkKm3TruongHop(String idsp, String idKm, KhuyenMai km) {
        List<ChiTietKhuyenMai> listctKm = chiTietKhuyenMaiResponsitories.getAll3(" where c.sp.id = '" + idsp + "'");
        for (ChiTietKhuyenMai chiTietKhuyenMai : listctKm) {
            if (chiTietKhuyenMai.getKm().getTrangThai().equals("Còn Hạn") && km.getTrangThai().equals("Chưa Hoạt Động")) {
                if (chiTietKhuyenMai.getKm().getNgayKT().getTime() > km.getNgayBD().getTime()) {
                    JOptionPane.showMessageDialog(this, "Không the ap km tron khoang time");
                    return false;
                }
            } else if (chiTietKhuyenMai.getKm().getTrangThai().equals("Chưa Hoạt Động")
                    && km.getTrangThai().equals("Chưa Hoạt Động")) {
                if (chiTietKhuyenMai.getKm().getNgayKT().getTime() > km.getNgayKT().getTime()) {
                    JOptionPane.showMessageDialog(this, "San pham co km");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checThemKM(Date ngayBD, Date ngayKT, String ten) {
        for (int i = 0; i < chiTietKhuyenMaiResponsitories.getAll(null).size(); i++) {
            if (chiTietKhuyenMaiResponsitories.getAll(null).get(i).getKm().getNgayBD().compareTo(ngayKT) > 0
                    && chiTietKhuyenMaiResponsitories.getAll(null).get(i).getKm().getNgayKT().compareTo(ngayBD) < 0
                    && chiTietKhuyenMaiResponsitories.getAll(null).get(i).getSp().getTenSanPham().equalsIgnoreCase(ten)) {
                if (tbHienThiSP.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

//    public boolean addALL() {
//        ChiTietKhuyenMai chiTietKhuyenMai = null;
//        if (cball.isSelected()) {
//            List<SanPham> list1 = sanPham.getAll123("");
//            chiTietKhuyenMai = new ChiTietKhuyenMai(
//                    jcheck.createID().toString(),
//                    jcheck.randomMA(),
//                    null,
//                    (KhuyenMai) cbbKM.getSelectedItem()
//            );
//            for (SanPham sp : list1) {
//
//                chiTietKhuyenMai.setId(jcheck.createID().toString());
//                chiTietKhuyenMai.setMa(jcheck.randomMA());
//                chiTietKhuyenMai.setSp(sp);
//                if (checkKm3TruongHop(sp.getId(), ((KhuyenMai) cbbKM.getSelectedItem()).getId(), (KhuyenMai) cbbKM.getSelectedItem()) == false) {
//                    return false;
//                }
//                if(checKM(((KhuyenMai)cbbKM.getSelectedItem()).getTenKM(), sp.getTenSanPham())==false){
//                    JOptionPane.showMessageDialog(this, "Sản phẩm này đã được áp khuyến mãi " + ((KhuyenMai)cbbKM.getSelectedItem()).getTenKM());
//                    return false;
//                }
//
//                chiTietKhuyenMaiResponsitories.add(chiTietKhuyenMai);
//            }
//
//        }
//        return false;
//    }
    public ChiTietKhuyenMai getDataCTKM(String dk) {

        ChiTietKhuyenMai chiTietKhuyenMai = null;
//        SanPham pham = new SanPham(tbHienThiTTSP.getValueAt(tbHienThiTTSP.getSelectedRow(), 0).
//                toString(), tbHienThiTTSP.getValueAt(tbHienThiTTSP.getSelectedRow(), 2).toString());

        if (dk.equalsIgnoreCase("update")) {
            return chiTietKhuyenMai = new ChiTietKhuyenMai(
                    tbHienThiSP1.getValueAt(tbHienThiSP1.getSelectedRow(), 0).toString(),
                    tbHienThiSP1.getValueAt(tbHienThiSP1.getSelectedRow(), 1).toString(),
                    ((ChiTietKhuyenMai) chiTietKhuyenMaiResponsitories.getAll("").get(tbHienThiTTSP.getSelectedRow())).getSp(),
                    (KhuyenMai) cbbKM.getSelectedItem()
            );
        }
        Session s = DBConnection.getsetFactory().openSession();
        tbHienThiSP1.repaint();

        return chiTietKhuyenMai;

    }

    private boolean checkCalendar() {

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date a1, a2, a3;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            //ép về dd/mm/yyyy 00:00
            a1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").
                    parse(new SimpleDateFormat("dd/MM/yyyy").format(dateNgayKT.getDate()) + " 00:00:00 AM");
            dateNgayKT.setDate(a1);

            // lấy ngày tháng hiện tại
            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1; // vì tháng trong Calendar tính từ 0 đến 11 nên phải cộng thêm 1
            int year = cal.get(Calendar.YEAR);
            String dateString = formatter.format(cal.getTime());

            //ngày bắt đầu 
            Date dateBD = dateNgayBD.getDate();
            String bd = formatter.format(dateBD);

            //ngyaf kết thúc
            Date dateKT = dateNgayKT.getDate();
            String kt = formatter.format(dateKT);

//            //ngày bđ lớn hơn 30 ngày
            Date endDate = new Date(date.getTime() - (30 * 24 * 60 * 60 * 1000));

            //check
            if (dateNgayBD.getDate().before(endDate) == false) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được quá 30 ngày so với hiện tại");
                dateNgayBD.requestFocus();
                return false;
            }
            if (bd.equalsIgnoreCase(dateString)) {
                System.out.println("ok");
            } else {
                if (dateNgayBD.getDate().getTime() < new Date().getTime()) {
                    JOptionPane.showMessageDialog(this, "Ngày bắt đầu không thể là ngày quá khứ");
                    return false;
                }
            }
            if (bd.equalsIgnoreCase(kt)) {
                System.out.println("hi");
            } else if (a1.compareTo(dateNgayBD.getDate()) < 0) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc không được sau ngày bắt đầu");
                dateNgayKT.requestFocus();
                return false;
            }

        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiView.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;
    }

    private void clearKhuyenMai() {
        jcheck.clearView(jText, tbHienThi);
        dateNgayBD.setDate(null);
        dateNgayKT.setDate(null);

    }

    public void chay() {
        Thread th = new Thread() {
            @Override
            public void run() {
                String txt = lbl_Chay.getText() + " ";
                while (true) {
                    txt = txt.charAt(txt.length() - 1) + txt.substring(0, txt.length() - 1);
                    try {
                        sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbl_Chay.setText(txt);
                }
//                if (new Date()) {
//                    
//                }
            }

        };
        th.start();
    }
    private final int akhuyenmai = 12 * 60 * 60 * 1000;

    public void chay1() {

        Thread th = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(akhuyenmai);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    new view.KhuyenMaiView().setVisible(true);
                }
            }

        };
        th.start();
    }

    

    public boolean add() {

        ChiTietKhuyenMai chiTietKhuyenMai = null;
        chiTietKhuyenMai = new ChiTietKhuyenMai(
                jcheck.createID().toString(),
                jcheck.randomMA(),
                null,
                (KhuyenMai) cbbKM.getSelectedItem()
        );
        System.out.println("tenkmthu" + ((KhuyenMai) cbbKM.getSelectedItem()).getTenKM());

        for (int i = 0; i < tbHienThiTTSP.getRowCount(); i++) {
            if ((Boolean) tbHienThiTTSP.getValueAt(i, 4) == true) {
                System.out.println("ténp" + tbHienThiTTSP.getValueAt(i, 2).toString());
                if (checkKm3TruongHop(tbHienThiTTSP.getValueAt(i, 0).toString(), ((KhuyenMai) cbbKM.getSelectedItem()).getId(), (KhuyenMai) cbbKM.getSelectedItem()) == false) {
                    return false;
                }
                if (checKM(((KhuyenMai) cbbKM.getSelectedItem()).getTenKM(), tbHienThiTTSP.getValueAt(i, 2).toString()) == false) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm này đã được áp khuyến mãi " + ((KhuyenMai) cbbKM.getSelectedItem()).getTenKM());
                    return false;
                }

                List<SanPham> list1 = sanPham.getAll123("");
                chiTietKhuyenMai.setId(jcheck.createID().toString());
                chiTietKhuyenMai.setMa(jcheck.randomMA());
                chiTietKhuyenMai.setSp(sanPham.getAll123("").get(i));
                chiTietKhuyenMaiResponsitories.add(chiTietKhuyenMai);
            }
        }
        return false;
    }

//    ************** END KHUYEN MAI ********************
//    NHAN VIEN
    public void loadCombobox() {
        for (ChucVuViewModel chucVuViewModel : chucVuService.getAllLoad()) {
            dcb.addElement(chucVuViewModel);
        }
        comboboxChucVu.setModel(dcb);
    }

    public void loadTableNhanVien() {
        dtmNhanVien = (DefaultTableModel) tableHienThi.getModel();
        dtmNhanVien.setRowCount(0);
        if (nhanVienService.getListNhanVienFromDb(null) != null) {
            List<NhanVienViewModel> listNhanVien = nhanVienService.getListNhanVienFromDb("");
            for (NhanVienViewModel nhanVienViewModel : listNhanVien) {
                dtmNhanVien.addRow(
                        new Object[]{
                            nhanVienViewModel.getIdNhanVien(),
                            nhanVienViewModel.getMaNhanVien(),
                            nhanVienViewModel.getTenNhanVien(),
                            nhanVienViewModel.getCmnd(),
                            nhanVienViewModel.getGioiTinh(),
                            nhanVienViewModel.getNgaySinh(),
                            nhanVienViewModel.getDiaChi(),
                            nhanVienViewModel.getSdt(),
                            nhanVienViewModel.getEmail(),
                            nhanVienViewModel.getAnh(),
                            nhanVienViewModel.getTenTaiKhoan(),
                            nhanVienViewModel.getMatKhau(),
                            nhanVienViewModel.getChucVuViewModel(),
                            nhanVienViewModel.trangThai()
                        });
            }
        }
    }

    public void sapXepTable() {
        dtmNhanVien = (DefaultTableModel) tableHienThi.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(dtmNhanVien);
        tableHienThi.setRowSorter(sorter);
    }

    public void clearNhanVien() {
        txtTenNhanVien.setText("");
        txtMaNhanVien.setText("");
        txtTenTaiKhoan.setText("");
        txtSdt.setText("");
        txtDiaChi.setText("");
        txtCmnd.setText("");
        labelThemAnh.setIcon(null);
        txtEmail.setText("");
        dateNgaySinh.setDate(null);
        txtMatKhau.setText("");
        comboboxChucVu.setSelectedIndex(0);
        comboboxTrangThai.setSelectedIndex(0);

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
                    txtTenNhanVien.getText(),
                    txtCmnd.getText(),
                    gioiTinh,
                    dateNgaySinh.getDate(),
                    txtDiaChi.getText(),
                    txtSdt.getText(),
                    txtEmail.getText(),
                    duongDanThuMucAnh,
                    txtTenTaiKhoan.getText(),
                    txtMatKhau.getText(),
                    (ChucVuViewModel) dcb.getSelectedItem(),
                    trangThai
            );
        }
        return new NhanVienViewModel(
                jframeCheck.createID().toString(),
                txtMaNhanVien.getText().trim(),
                txtTenNhanVien.getText().trim(),
                txtCmnd.getText().trim(),
                gioiTinh,
                dateNgaySinh.getDate(),
                txtDiaChi.getText().trim(),
                txtSdt.getText().trim(),
                txtEmail.getText().trim(),
                duongDanThuMucAnh,
                txtTenTaiKhoan.getText().trim(),
                txtMatKhau.getText().trim(),
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
//    ************** END NHANVIEN ********************

//  KHACH HANG
    public void loadTableKhachHang() {
        dtmKhachhang = (DefaultTableModel) tbHienThi1.getModel();
        dtmKhachhang.setRowCount(0);
        List<KhachHangViewModel> khvms = khachHangServices.getAllKhachHang();
        for (KhachHangViewModel khvm : khvms) {
            dtmKhachhang.addRow(khvm.toRow());
        }
    }
//

    public void findMaKhachHang(List<KhachHang> list) {
        dtmKhachhang = (DefaultTableModel) tbHienThi1.getModel();
        dtmKhachhang.setRowCount(0);
        List<KhachHang> khvm = khachHangServices.SelectbyName(txtTim.getText());
        for (KhachHang khvms : khvm) {
            dtmKhachhang.addRow(khvms.toRow0());
        }
    }

    public void fillFormKhachhang(int row) throws ParseException {
        KhachHangViewModel nhanVien = khachHangServices.getAllKhachHang().get(row);
        txtTenKhachHang.setText(nhanVien.getTen());
        txtaDiaChi.setText(nhanVien.getDiaChi());
        txtSDT.setText(nhanVien.getSđt());
        Date ngay = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(nhanVien.getNgaySinh()));
        dateNgaySinh.setDate(ngay);
        String gt = nhanVien.getGioiTinh();
        if (gt.equalsIgnoreCase("nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }

//        Date ngay = new SimpleDateFormat("dd/MM/yyyy").
//                format(nhanVien.getNgaySinh());
    }

    private boolean checkSDTKH(String sdt) {
        for (int i = 0; i < hangResponsitories.getAllKH().size(); i++) {
            if (hangResponsitories.getAllKH().get(i).getSdt().equalsIgnoreCase(sdt)) {
                if (tbHienThi1.getSelectedRow() == i) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private KhachHangViewModel getDataKhachHang(String dk) {
        if (dk.equalsIgnoreCase("update")) {
            System.out.println("update");
            return new KhachHangViewModel(tbHienThi1.getValueAt(tbHienThi1.getSelectedRow(), 0).toString(),
                    tbHienThi1.getValueAt(tbHienThi1.getSelectedRow(), 1).toString(),
                    txtTenKhachHang.getText().trim(), rdNam.isSelected() == true ? "Nam" : "Nữ", txtSDT.getText(),
                    dateNgaySinh.getDate(), txtaDiaChi.getText());
        }
        return new KhachHangViewModel(jcheckKhachHang.createID().toString(), jcheckKhachHang.randomMA(),
                txtTenKhachHang.getText().trim(), rdNam.isSelected() == true ? "Nam" : "Nữ",
                txtSDT.getText(), dateNgaySinh.getDate(), txtaDiaChi.getText());
//        return null;
    }

    public void clearKhachHang() {
        txtTenKhachHang.setText("");
        txtSDT.setText("");
        txtTim.setText("");
        txtaDiaChi.setText("");
        dateNgaySinh.setDate(null);

    }
//    ************** END KHACH HANG ********************

//    THONG KE
    public void findNgay(List<ChiTietHoaDon> list) throws ParseException {
        dtmThongke = (DefaultTableModel) Tbltheongay.getModel();
        dtmThongke.setRowCount(0);
        Date d = dateTK_doanhthu.getDate();
        List<ChiTietHoaDon> kh = scthd.select_doanhthu_theongayTC(d);
        for (ChiTietHoaDon c : kh) {
            dtmThongke.addRow(new Object[]{
                c.getSOHD(), c.getTONGTIEN(), c.getSKH()
            });
        }
    }

    /*---doanh thu theo tháng*/
 /*------doanh thu theo thang---------*/
    public void findThang(List<ChiTietHoaDon> list) {
        dtmThongke = (DefaultTableModel) Tbltheongay.getModel();
        dtmThongke.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.select_doanhthu_theothangTC(Integer.parseInt((String) cbbthang.getSelectedItem()), Integer.parseInt(txtnam.getText()));
        for (ChiTietHoaDon s : kh) {
            dtmThongke.addRow(new Object[]{
                s.getSOHD(), s.getTONGTIEN(), s.getSKH()
            });
        }

    }

    /*-----SAN PHAM-------*/
 /*------Tong Hop-------*/
    public void findTOP(List<ChiTietHoaDon> list) {
        dtmThongke = (DefaultTableModel) tblTONGHOP.getModel();
        dtmThongke.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.SelectTOP(Integer.parseInt(txtn.getText()));
        for (ChiTietHoaDon t : kh) {
            dtmThongke.addRow(new Object[]{
                t.getMaSP(), t.getTenSP(), t.getMS(), t.getNSX(), t.getSOHD(), t.getSluong(), t.getTONGTIEN()
            });
        }
    }

    /*----SP theo thang-----*/
    public void findSPThang(List<ChiTietHoaDon> list) {
        dtmThongke = (DefaultTableModel) tblSP_H.getModel();
        dtmThongke.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.SelectbySPThang(Integer.parseInt((String) cbbSPthang.getSelectedItem()), Integer.parseInt(txtSPNam.getText()));
        for (ChiTietHoaDon t : kh) {
            dtmThongke.addRow(new Object[]{
                t.getMaSP(), t.getTenSP(), t.getMS(), t.getNSX(), t.getSOHD(), t.getSluong(), t.getTONGTIEN()
            });
        }
    }

    /*---SP theo ngay-----*/
    public void findSPNgay(List<ChiTietHoaDon> list) {
        dtmThongke = (DefaultTableModel) tblSP_H.getModel();
        dtmThongke.setRowCount(0);
        List<ChiTietHoaDon> kh = scthd.SelectbySPNgay(date_SPNgay.getDate());
        for (ChiTietHoaDon t : kh) {
            dtmThongke.addRow(new Object[]{
                t.getMaSP(), t.getTenSP(), t.getMS(), t.getNSX(), t.getSOHD(), t.getSluong(), t.getTONGTIEN()
            });
        }
    }

    private void loadDTThang() {
        try {
            lblbihuy.setText(((scthd.select_doanhthu_theothangBH(Integer.parseInt(cbbthang.getSelectedItem().toString()), Integer.parseInt(txtnam.getText())))) + "");
            lblthanhcong2.setText(((ChiTietHoaDon) (scthd.select_doanhthu_theothangTC(Integer.parseInt(cbbthang.getSelectedItem().toString()), Integer.parseInt(txtnam.getText())).get(0))).getSOHD() + "");
            lbldtthang.setText(((ChiTietHoaDon) (scthd.select_doanhthu_theothangTC(Integer.parseInt(cbbthang.getSelectedItem().toString()), Integer.parseInt(txtnam.getText())).get(0))).getTONGTIEN() + "");
            lblKH.setText(((ChiTietHoaDon) (scthd.select_doanhthu_theothangTC(Integer.parseInt(cbbthang.getSelectedItem().toString()), Integer.parseInt(txtnam.getText())).get(0))).getSKH() + "");
            lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
        } catch (NumberFormatException e) {
            // thông báo cho người dùng biết rằng giá trị không hợp lệ
            System.out.println("Giá trị không hợp lệ");
        }

    }

    private void loadDTNgay() {
        try {
            lblbihuy.setText(((scthd.select_doanhthu_theongayBH(dateTK_doanhthu.getDate()))) + "");
            lblthanhcong2.setText(((ChiTietHoaDon) (scthd.select_doanhthu_theongayTC(dateTK_doanhthu.getDate()).get(0))).getSOHD() + "");
            lbldtngay.setText(((ChiTietHoaDon) (scthd.select_doanhthu_theongayTC(dateTK_doanhthu.getDate()).get(0))).getTONGTIEN() + "");
            lblKH.setText(((ChiTietHoaDon) (scthd.select_doanhthu_theongayTC(dateTK_doanhthu.getDate()).get(0))).getSKH() + "");
            lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
        } catch (NumberFormatException e) {
            // thông báo cho người dùng biết rằng giá trị không hợp lệ
            System.out.println("Giá trị không hợp lệ");
        }

    }

    private void loadDTHT() {
        try {
            lblbihuy.setText(((scthd.select_hientaiBH())) + "");
            lblthanhcong2.setText(((ChiTietHoaDon) (scthd.select_hientaiTC().get(0))).getSOHD() + "");
            lbldtngay.setText(((ChiTietHoaDon) (scthd.select_hientaiTC().get(0))).getTONGTIEN() + "");
            lblKH.setText(((ChiTietHoaDon) (scthd.select_hientaiTC().get(0))).getSKH() + "");
            lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
        } catch (NumberFormatException e) {
            // thông báo cho người dùng biết rằng giá trị không hợp lệ
            System.out.println("Giá trị không hợp lệ");
        }

    }

//    ************** END THONG KE ********************
//  BAO HANH
    public void loadTableHD(List<HoaDon> list) {
        dtmBaoHang = (DefaultTableModel) tbHienThi2.getModel();
        dtmBaoHang.setRowCount(0);
        List<HoaDon> danhSachHoaDon = resHoaDon.SelectbyMaHD(txtTim2.getText());
        if (danhSachHoaDon != null && !danhSachHoaDon.isEmpty()) {
            for (HoaDon hoaDon : list) {
                // Xử lý thông tin hoá đơn ở đây
                dtmBaoHang.addRow(hoaDon.toRowhd());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã hoá đơn : " + txtTim2.getText());
        }
    }

    public void loadTableCTBH() {
        if (baoHanhRes.getAll("") != null) {
            dtmBaoHang = (DefaultTableModel) tbhHienThiCTBH.getModel();
            dtmBaoHang.setRowCount(0);
            for (domaimodel.BaoHanh object : baoHanhRes.getAll("")) {
                dtmBaoHang.addRow(object.toRowCTBH());
            }
        }
    }
    private boolean checkCalendarBH() {

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date a1, a2, a3;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            //ép về dd/mm/yyyy 00:00
            a1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").
                    parse(new SimpleDateFormat("dd/MM/yyyy").format(dateNgayTra.getDate()) + " 00:00:00 AM");
            dateNgayTra.setDate(a1);

            // lấy ngày tháng hiện tại
            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1; // vì tháng trong Calendar tính từ 0 đến 11 nên phải cộng thêm 1
            int year = cal.get(Calendar.YEAR);
            String dateString = formatter.format(cal.getTime());

            //ngày bắt đầu 
            Date dateBD = dateNgayBH.getDate();
            String bd = formatter.format(dateBD);

            //ngyaf kết thúc
            Date dateKT = dateNgayTra.getDate();
            String kt = formatter.format(dateKT);

            if (bd.equalsIgnoreCase(kt)) {
                System.out.println("ok");
            } else {
                if (dateNgayTra.getDate().getTime() < new Date().getTime()) {
                    JOptionPane.showMessageDialog(this, "Ngày trả sp cho khách không thể là ngày quá khứ");
                    return false;
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiView.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;
    }

//    public void sapXep(List<BaoHanh> list1) {
//        list = list1;
//        Collections.sort(list, (BaoHanh o1, BaoHanh o2)
//                -> o1.getNgayBH().getTime() > o2.getNgayBH().getTime() ? -1 : 1);
//
//        loadTableKM1(list);
//
//    }
    public boolean checkThoiHan() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        Date ngayThanhToan = (Date) tbHienThi2.getValueAt(tbHienThi2.getSelectedRow(), 4);

        // Tính toán ngày kết thúc bảo hành (là ngày thanh toán cộng với 6 tháng)
        Calendar calEndDate = Calendar.getInstance();
        calEndDate.setTime(ngayThanhToan);
        calEndDate.add(Calendar.MONTH, 6);
        Date endDate = calEndDate.getTime();

        // So sánh ngày hiện tại với ngày kết thúc bảo hành
        if (currentDate.compareTo(endDate) > 0) {
            JOptionPane.showMessageDialog(this, "Sản Phẩm của bạn đã hết thời gian bảo hành");
            return false;
        }
        return true;
    }

    public void loadTableKMBaoHanh(List<KhuyenMai> lit) {
        dtmBaoHang = (DefaultTableModel) tbHienThi.getModel();
        dtmBaoHang.setRowCount(0);
        for (domaimodel.KhuyenMai object : lit) {
            dtmBaoHang.addRow(object.toRow());
        }

    }

    public void fillFormBH(int row) throws ParseException {
        domaimodel.BaoHanh ctkmvm = baoHanhRes.getAll("").get(row);
        lbSDT.setText(ctkmvm.getCthd().getHd().getKh().getSdt());
        lbMaBH.setText(ctkmvm.getMa());
        lbTenSP.setText(ctkmvm.getCthd().getSp().getTenSanPham());
        lbTenKH.setText(ctkmvm.getCthd().getHd().getKh().getTen());
        int a = ctkmvm.getSoLuongBaoHanh();
        txtSL.setText(Integer.toString(a));
        Date ngayBH = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").
                format(ctkmvm.getNgayBH()));
        dateNgayBH.setDate(ngayBH);

        if (ctkmvm.getNgayTra() != null) {
            {
                Date ngayTra = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").
                        format(ctkmvm.getNgayTra()));
                dateNgayTra.setDate(ngayTra);
            }

            if (ctkmvm.getSuaChua().equalsIgnoreCase("Đội Sửa Chữa 1")) {
                cbbSuaChua.setSelectedIndex(0);
            } else {
                cbbSuaChua.setSelectedIndex(1);
            }
            int tt = ctkmvm.getTinhTrang();

            if (tt == 0) {
                cbbTrangThai.setSelectedIndex(0);
            } else {
                cbbTrangThai.setSelectedIndex(1);
            }

        }
    }

    public BaoHanh getDataCTBH(String dk) {

        if (dk.equalsIgnoreCase("update")) {
//            hd = (HoaDon) resHoaDonCho.getAllSPHDNoEm((String) tbHienThi.getValueAt(tbHienThi.getSelectedRow(), 0), 0).get(0);
            return new BaoHanh(tbhHienThiCTBH.getValueAt(tbhHienThiCTBH.getSelectedRow(), 0).toString(),
                    tbhHienThiCTBH.getValueAt(tbhHienThiCTBH.getSelectedRow(), 1).toString(),
                    (Date) tbhHienThiCTBH.getValueAt(tbhHienThiCTBH.getSelectedRow(), 7),
                    txtAMoTa.getText(),
                    cbbTrangThai.getSelectedIndex() == 0 ? 0 : 1,
                    cbbSuaChua.getSelectedIndex() == 0 ? "Đội Sửa Chữa 1" : "Đội Sửa Chữa 2",
                    (int) tbhHienThiCTBH.getValueAt(tbhHienThiCTBH.getSelectedRow(), 9),
                    dateNgayTra.getDate(),
                    baoHanhRes.getALlBH().get(tbhHienThiCTBH.getSelectedRow()).getCthd());

        }

        return null;

    }

    private void clearBH() {
        jcheckBaoHang.clearView(jTextBaoHanh, tbHienThi);
        dateNgayTra.setDate(null);

    }
//    ************** END BAO HANH ********************

//    THONG KE XUAT FILE EXCEL
//    try {
//            HSSFWorkbook hSSFWorkbook = new HSSFWorkbook();
//            HSSFSheet sheet = hSSFWorkbook.createSheet("quyet");
//            HSSFRow row = null;
//            Cell cell = null;
//            row = sheet.createRow(3);
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("ID");
//            
//            cell = row.createCell(1, CellType.STRING);
//            cell.setCellValue("Ma");
//            
////            cell = row.createCell(2, CellType.STRING);
////            cell.setCellValue("Ten");
//// Lay du lieu dua vao excel           
//            for (int i = 0; i < tblNV.getRowCount(); i++) {
//                row = sheet.createRow(3 + i+1);
//                for (int j = 0; j < tblNV.getColumnCount(); j++) {
//                    cell = row.createCell(j,CellType.STRING);
//                    cell.setCellValue(tblNV.getValueAt(i, j).toString());
//                }
//                
//            }
//            File f = new File("Excel\\test.xls");
//            
//            System.out.println(f.createNewFile());
//            try {
//                FileOutputStream fis = new FileOutputStream(f);
//                hSSFWorkbook.write(fis);
//                fis.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JOptionPane.showMessageDialog(this, "thanh cong");
//    ************** END FILE THONG KE ********************
// CLEAR HET MAU   
    private void clearMau() {
        List<JButton> listBT = new ArrayList<>();
        listBT.add(btnbanhang);
        listBT.add(btnnhanvien);
        listBT.add(btnsanpham);
        listBT.add(btnkhachhang);
        listBT.add(btnkhuyenmai);
        listBT.add(btmthongke);
        listBT.add(btnlichsu);
        listBT.add(btndx1);

        for (JButton jButton : listBT) {
            jButton.setBackground(null);
        }
    }

//    Day la code cua thanhoccho
//    END CODE THANH OC CHO
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tenNV = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnbanhang = new javax.swing.JButton();
        btnnhanvien = new javax.swing.JButton();
        btnsanpham = new javax.swing.JButton();
        btnkhachhang = new javax.swing.JButton();
        btnkhuyenmai = new javax.swing.JButton();
        btmthongke = new javax.swing.JButton();
        btnlichsu = new javax.swing.JButton();
        btndx = new javax.swing.JButton();
        anhNV = new javax.swing.JLabel();
        CV = new javax.swing.JLabel();
        btndx1 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        paneTong = new javax.swing.JPanel();
        TabeBanHang = new javax.swing.JTabbedPane();
        FrameBanHang = new javax.swing.JInternalFrame();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbnHD = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jpaneAnh2 = new javax.swing.JPanel();
        txtAnhSP1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtMPRO3 = new javax.swing.JLabel();
        txtTenSp3 = new javax.swing.JLabel();
        txtSLPRO3 = new javax.swing.JLabel();
        txtGiaPRO3 = new javax.swing.JLabel();
        txtTT3 = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();
        txtTT2 = new javax.swing.JLabel();
        txtGiaPRO2 = new javax.swing.JLabel();
        txtSLPRO2 = new javax.swing.JLabel();
        txtTenSp2 = new javax.swing.JLabel();
        txtMPRO2 = new javax.swing.JLabel();
        giaGiam2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        Lui = new javax.swing.JLabel();
        Tien = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        txtAnhSP = new javax.swing.JLabel();
        panelAnh1 = new javax.swing.JPanel();
        txtMPRO1 = new javax.swing.JLabel();
        txtTenSp1 = new javax.swing.JLabel();
        txtSLPRO1 = new javax.swing.JLabel();
        txtGiaPRO1 = new javax.swing.JLabel();
        txtTT1 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        txtTT = new javax.swing.JLabel();
        txtGiaPRO = new javax.swing.JLabel();
        txtSLPRO = new javax.swing.JLabel();
        txtTenSp = new javax.swing.JLabel();
        txtMPRO4 = new javax.swing.JLabel();
        cbbtrangthai = new javax.swing.JComboBox<>();
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
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtPVC = new javax.swing.JTextField();
        txtgiamgia1 = new javax.swing.JTextField();
        txttongtien1 = new javax.swing.JTextField();
        txtdg1 = new javax.swing.JTextField();
        txtsl1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnHoangThanh = new javax.swing.JButton();
        btnChuyenHang = new javax.swing.JButton();
        btnHuyDonHang = new javax.swing.JButton();
        tbnThemDatHang = new javax.swing.JButton();
        txtMPRO = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        tabSanPham = new javax.swing.JTabbedPane();
        frameSanPham = new javax.swing.JInternalFrame();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        txtTen_SP = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtMauSac_SP = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtNSX_SP = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtSoLuong_SP = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        rdConHang = new javax.swing.JRadioButton();
        txtTrongLuong_SP = new javax.swing.JTextField();
        rdHetHang = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        txtGiaBan_SP = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtGiaNhap_SP = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        cbbDongSP_SP = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btAddSP = new javax.swing.JButton();
        btUpdateSP = new javax.swing.JButton();
        btClearSP = new javax.swing.JButton();
        btnTao = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHienThiSP = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        txtSearch_Loc = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        cbbTrangThai_Loc1 = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        cbbDongSP_Loc1 = new javax.swing.JComboBox<>();
        btnTao1 = new javax.swing.JButton();
        lb_MoTa = new javax.swing.JLabel();
        lbQR_SP = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        lbMa_SP = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKichThuoc_SP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtChatLieu_SP = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        lable = new javax.swing.JLabel();
        lbID_DSP = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtDongSP_DSP = new javax.swing.JTextField();
        btAddDSP = new javax.swing.JButton();
        btUpdateDSP = new javax.swing.JButton();
        btDeleteDelete = new javax.swing.JButton();
        btClearDSP = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbHienThi_DSP = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        tabKhuyenmai = new javax.swing.JTabbedPane();
        jframeKhuyenMai = new javax.swing.JInternalFrame();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTimKM = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
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
        jLabel45 = new javax.swing.JLabel();
        rdHetHan = new javax.swing.JRadioButton();
        rdConHan = new javax.swing.JRadioButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbHienThiSP1 = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txtTimSP = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        btnTaoSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbHienThiTTSP = new javax.swing.JTable();
        cbbTenKM = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtTu = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtDen = new javax.swing.JTextField();
        tabNhanVien = new javax.swing.JTabbedPane();
        jframeNhanVien = new javax.swing.JInternalFrame();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        txtCmnd = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        radioNam = new javax.swing.JRadioButton();
        jLabel54 = new javax.swing.JLabel();
        radioNu = new javax.swing.JRadioButton();
        jLabel55 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtTenTaiKhoan = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        comboboxChucVu = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        comboboxTrangThai = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        panelThemAnh = new javax.swing.JPanel();
        labelThemAnh = new javax.swing.JLabel();
        buttonThem = new javax.swing.JButton();
        buttonCapNhat = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableHienThi = new javax.swing.JTable();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtMatKhau = new javax.swing.JTextField();
        tabKhachHang = new javax.swing.JTabbedPane();
        jframeKhachHang = new javax.swing.JInternalFrame();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel26 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        btnThem2 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        dateNgaySinh1 = new com.toedter.calendar.JDateChooser();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtaDiaChi = new javax.swing.JTextArea();
        jPanel28 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbHienThi1 = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        txtTim1 = new javax.swing.JTextField();
        btnTim1 = new javax.swing.JButton();
        tabThongKe = new javax.swing.JTabbedPane();
        jframeThongKe = new javax.swing.JInternalFrame();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        lblSoDH2 = new javax.swing.JLabel();
        lblthanhcong2 = new javax.swing.JLabel();
        lblbihuy = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        lbldtngay = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        lbldtthang = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        lblKH = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        cbbltg = new javax.swing.JComboBox<>();
        paneTT = new javax.swing.JPanel();
        panekhoangngay = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        datetngayBD = new com.toedter.calendar.JDateChooser();
        jLabel86 = new javax.swing.JLabel();
        datengayKT = new com.toedter.calendar.JDateChooser();
        btnkhoangngay = new javax.swing.JButton();
        paneHientai = new javax.swing.JPanel();
        btnhiendthientai = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel38 = new javax.swing.JPanel();
        jTabbedPane9 = new javax.swing.JTabbedPane();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        Tbltheongay = new javax.swing.JTable();
        cbbthoigian = new javax.swing.JComboBox<>();
        jPanel41 = new javax.swing.JPanel();
        panetngay = new javax.swing.JPanel();
        dateTK_doanhthu = new com.toedter.calendar.JDateChooser();
        jLabel87 = new javax.swing.JLabel();
        panemthang = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        cbbthang = new javax.swing.JComboBox<>();
        jLabel89 = new javax.swing.JLabel();
        txtnam = new javax.swing.JTextField();
        btntim = new javax.swing.JButton();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel44 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblSP_H = new javax.swing.JTable();
        jPanel45 = new javax.swing.JPanel();
        panelSPtheongay = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        date_SPNgay = new com.toedter.calendar.JDateChooser();
        panelSPThang = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        cbbSPthang = new javax.swing.JComboBox<>();
        jLabel92 = new javax.swing.JLabel();
        txtSPNam = new javax.swing.JTextField();
        btnthang = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblTONGHOP = new javax.swing.JTable();
        btnhien = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        txtn = new javax.swing.JTextField();
        cbbccngay = new javax.swing.JComboBox<>();
        jPanel47 = new javax.swing.JPanel();
        tabLichSU = new javax.swing.JTabbedPane();
        jfarmeLichSu = new javax.swing.JInternalFrame();
        jTabbedPane11 = new javax.swing.JTabbedPane();
        jPanel48 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tabBaoHanh = new javax.swing.JTabbedPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbHienThi2 = new javax.swing.JTable();
        jLabel106 = new javax.swing.JLabel();
        txtTim2 = new javax.swing.JTextField();
        btnTim2 = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbhHienThiCTBH = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        lbMaBH = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        lbTenSP = new javax.swing.JLabel();
        cbbSuaChua = new javax.swing.JComboBox<>();
        jLabel111 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtAMoTa = new javax.swing.JTextArea();
        jLabel113 = new javax.swing.JLabel();
        btnSua1 = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        dateNgayTra = new com.toedter.calendar.JDateChooser();
        dateNgayBH = new com.toedter.calendar.JDateChooser();
        jLabel116 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        lbTenKH = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        lbl_Chay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel10.setBackground(new java.awt.Color(255, 255, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên Nhân Viên :");

        tenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNV.setText("NVA");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Chức Vụ :");

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

        btndx.setBackground(new java.awt.Color(255, 255, 153));
        btndx.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndx.setText("Đăng Xuất");
        btndx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndxActionPerformed(evt);
            }
        });

        CV.setText("jLabel6");

        btndx1.setBackground(new java.awt.Color(255, 255, 153));
        btndx1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndx1.setText("Bảo hành");
        btndx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndx1ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 153));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setText("Đổi mật khẩu");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btndx, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btndx1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                        .addComponent(btnbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btmthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnlichsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel23))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(anhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btmthongke, btnbanhang, btndx, btndx1, btnkhachhang, btnkhuyenmai, btnlichsu, btnnhanvien, btnsanpham, jButton10});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tenNV))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(CV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btmthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnlichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndx1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btndx, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btmthongke, btnbanhang, btndx, btndx1, btnkhachhang, btnkhuyenmai, btnlichsu, btnnhanvien, btnsanpham, jButton10});

        paneTong.setLayout(new java.awt.CardLayout());

        FrameBanHang.setVisible(true);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 204));

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));
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

        btnxoatatca.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Images\\Delete.png")); // NOI18N
        btnxoatatca.setText("Xóa tất cả");
        btnxoatatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoatatcaActionPerformed(evt);
            }
        });

        btnxoasp.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Hopstarter-Button-Button-Delete.32.png")); // NOI18N
        btnxoasp.setText("Xóa sản phẩm");
        btnxoasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaspActionPerformed(evt);
            }
        });

        btntaohd.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-100-Flat-Cart-add.32.png")); // NOI18N
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
                .addContainerGap()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnxoasp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoatatca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jPanel6.setBackground(new java.awt.Color(153, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                false, false, false, false, false, false
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
        tbnHD.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbnHDPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tbnHD);

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jpaneAnh2.setBackground(new java.awt.Color(153, 255, 255));

        txtAnhSP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        txtMPRO3.setText("Mã:");

        txtTenSp3.setText("Tên:");

        txtSLPRO3.setText("Số lượng:");

        txtGiaPRO3.setText("Giá:");

        txtTT3.setText("Trạng thái:");

        btnThem1.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Fasticon-Shop-Cart-Shop-cart-add.24.png")); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThem1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThem1MouseExited(evt);
            }
        });
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        txtTT2.setText("TT");

        txtGiaPRO2.setText("GIA");

        txtSLPRO2.setText("SOLuong");

        txtTenSp2.setText("Ten");

        txtMPRO2.setText("MA");

        giaGiam2.setBackground(new java.awt.Color(255, 255, 153));
        giaGiam2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        giaGiam2.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTT3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaPRO3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSLPRO3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSLPRO2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaPRO2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                            .addComponent(txtMPRO3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMPRO2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                            .addComponent(txtTenSp3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTenSp2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(btnThem1)
                        .addGap(18, 18, 18)
                        .addComponent(giaGiam2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGap(16, 16, 16))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMPRO3)
                    .addComponent(txtMPRO2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSp3)
                    .addComponent(txtTenSp2))
                .addGap(12, 12, 12)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSLPRO3)
                    .addComponent(txtSLPRO2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaPRO3)
                    .addComponent(txtGiaPRO2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTT3)
                    .addComponent(txtTT2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(giaGiam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpaneAnh2Layout = new javax.swing.GroupLayout(jpaneAnh2);
        jpaneAnh2.setLayout(jpaneAnh2Layout);
        jpaneAnh2Layout.setHorizontalGroup(
            jpaneAnh2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneAnh2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtAnhSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jpaneAnh2Layout.setVerticalGroup(
            jpaneAnh2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAnhSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel6.setText("Tìm kiếm:");

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Aha-Soft-Free-3d-Glossy-Interface-Move.32.png")); // NOI18N
        jButton3.setText("All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Lui.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\icon\\Images\\Left.png")); // NOI18N
        Lui.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Lui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LuiMouseClicked(evt);
            }
        });

        Tien.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\icon\\Images\\Right.png")); // NOI18N
        Tien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TienMouseClicked(evt);
            }
        });

        jPanel54.setBackground(new java.awt.Color(153, 255, 255));

        txtAnhSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelAnh1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelAnh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAnh1MouseClicked(evt);
            }
        });

        txtMPRO1.setText("Mã:");

        txtTenSp1.setText("Tên:");

        txtSLPRO1.setText("Số lượng:");

        txtGiaPRO1.setText("Giá:");

        txtTT1.setText("Trạng thái:");

        btnThem.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Fasticon-Shop-Cart-Shop-cart-add.24.png")); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemMouseExited(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtTT.setText("TT");

        txtGiaPRO.setText("GIA");

        txtSLPRO.setText("SOLuong");

        txtTenSp.setText("Ten");

        txtMPRO4.setText("MA");

        javax.swing.GroupLayout panelAnh1Layout = new javax.swing.GroupLayout(panelAnh1);
        panelAnh1.setLayout(panelAnh1Layout);
        panelAnh1Layout.setHorizontalGroup(
            panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnh1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnh1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnThem))
                    .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelAnh1Layout.createSequentialGroup()
                            .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenSp1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                .addComponent(txtMPRO1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMPRO4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelAnh1Layout.createSequentialGroup()
                            .addComponent(txtSLPRO1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSLPRO, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelAnh1Layout.createSequentialGroup()
                            .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGiaPRO1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGiaPRO, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelAnh1Layout.setVerticalGroup(
            panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnh1Layout.createSequentialGroup()
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMPRO1)
                    .addComponent(txtMPRO4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSLPRO1)
                    .addComponent(txtSLPRO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaPRO1)
                    .addComponent(txtGiaPRO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTT1)
                    .addComponent(txtTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(txtAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAnhSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpaneAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Lui, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpaneAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jButton3))
                    .addComponent(Tien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lui, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        cbbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Đã thanh toán", "Hóa đơn chờ", "Đã hủy", "Đang giao hàng", "Đang xử lí", "Tất cả" }));
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
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbtrangthai, 0, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbHD.setBackground(new java.awt.Color(153, 255, 255));
        tabbHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbHDMouseClicked(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(153, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel9.setText("Tạo hóa đơn");

        jLabel11.setText("Tên khách hàng");

        txttenkh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txttenkh.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txttenkh.setEnabled(false);
        txttenkh.setName("Tên khách hàng"); // NOI18N

        jLabel14.setText("Số lượng ");

        jLabel15.setText("Đơn giá");

        txtsl.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtsl.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtsl.setEnabled(false);

        txtdg.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtdg.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtdg.setEnabled(false);

        jLabel16.setText("Giảm giá");

        jLabel18.setText("Trạng thái");

        jLabel19.setText("Hình thức thanh toán");

        jLabel20.setText("Tổng tiền");

        jLabel21.setText("Tiền khách đưa");

        jLabel22.setText("Tiền thừa");

        txtgiamgia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtgiamgia.setText("0");
        txtgiamgia.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txtgiamgia.setEnabled(false);

        buttonGroup1.add(rdodatt);
        rdodatt.setSelected(true);
        rdodatt.setText("Đã thanh toán");

        cbbhinhthuctt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hình thức thanh toán", "Tiền mặt", "Thẻ" }));
        cbbhinhthuctt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbhinhthucttItemStateChanged(evt);
            }
        });

        txttongtien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txttongtien.setText("0");
        txttongtien.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txttongtien.setEnabled(false);

        txttienkhachdua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txttienkhachdua.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txttienkhachdua.setName("Tiền khách đưa "); // NOI18N
        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyReleased(evt);
            }
        });

        txttienthua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txttienthua.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txttienthua.setEnabled(false);
        txttienthua.setName("Tiền thừa "); // NOI18N

        btntt.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-Seo-Services-Pay-per-click.32.png")); // NOI18N
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
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(cbbhinhthuctt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(216, 216, 216))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel22))
                                        .addGap(49, 49, 49)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdodatt)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdochuatt)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(47, 47, 47)
                                                .addComponent(txttenkh))
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
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(132, 132, 132)
                                        .addComponent(btntt)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addGap(17, 17, 17)
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
                .addGap(99, 99, 99))
        );

        tabbHD.addTab("Hóa đơn", jPanel8);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel10.setText("Tên khách hàng: ");

        txtTenShip.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenShip.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtTenShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenShipActionPerformed(evt);
            }
        });

        jLabel12.setText("Số điện thoại:");

        txtSDTShip.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSDTShip.setDisabledTextColor(new java.awt.Color(255, 0, 0));

        jLabel13.setText("Địa chỉ:");

        txtDShip.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDShip.setDisabledTextColor(new java.awt.Color(255, 51, 51));

        jLabel29.setText("VND");

        jPanel9.setBackground(new java.awt.Color(153, 255, 255));

        jLabel25.setText("Số lượng ");

        jLabel24.setText("Phí vận chuyển:");

        jLabel26.setText("Đơn giá");

        jLabel27.setText("Giảm giá");

        jLabel28.setText("Tổng tiền");

        txtPVC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPVC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPVC.setText("25");
        txtPVC.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtPVC.setEnabled(false);
        txtPVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPVCActionPerformed(evt);
            }
        });

        txtgiamgia1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtgiamgia1.setText("0");
        txtgiamgia1.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtgiamgia1.setEnabled(false);
        txtgiamgia1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtgiamgia1InputMethodTextChanged(evt);
            }
        });
        txtgiamgia1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtgiamgia1PropertyChange(evt);
            }
        });
        txtgiamgia1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtgiamgia1VetoableChange(evt);
            }
        });

        txttongtien1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txttongtien1.setText("0");
        txttongtien1.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txttongtien1.setEnabled(false);
        txttongtien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtien1ActionPerformed(evt);
            }
        });

        txtdg1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtdg1.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtdg1.setEnabled(false);
        txtdg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdg1ActionPerformed(evt);
            }
        });

        txtsl1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtsl1.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtsl1.setEnabled(false);
        txtsl1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsl1FocusLost(evt);
            }
        });
        txtsl1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtsl1PropertyChange(evt);
            }
        });

        jLabel4.setText("VND");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)))
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtPVC, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttongtien1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdg1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsl1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(136, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtgiamgia1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtsl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtdg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtgiamgia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txttongtien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));

        btnHoangThanh.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-Seo-Services-Pay-per-click.32.png")); // NOI18N
        btnHoangThanh.setText("Hoàn thành");
        btnHoangThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoangThanhActionPerformed(evt);
            }
        });

        btnChuyenHang.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-Colorful-Long-Shadow-Cart.32.png")); // NOI18N
        btnChuyenHang.setText("Giao đơn hàng");
        btnChuyenHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenHangActionPerformed(evt);
            }
        });

        btnHuyDonHang.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Hopstarter-Button-Button-Delete.32.png")); // NOI18N
        btnHuyDonHang.setText("Hủy đơn hàng");
        btnHuyDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDonHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnChuyenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHoangThanh))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btnHuyDonHang)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChuyenHang)
                    .addComponent(btnHoangThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuyDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnChuyenHang, btnHoangThanh, btnHuyDonHang});

        tbnThemDatHang.setText("Tìm kiếm");
        tbnThemDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnThemDatHangActionPerformed(evt);
            }
        });

        txtMPRO.setText("MA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMPRO, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTenShip, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtDShip, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tbnThemDatHang))
                                            .addComponent(txtSDTShip, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(170, 170, 170)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
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
                    .addComponent(txtDShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnThemDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(210, 210, 210))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMPRO)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(208, 208, 208))
        );

        tabbHD.addTab("Đặt hàng", jPanel1);

        jButton2.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Images\\Pictogrammers-Material-Qrcode-scan.32.png")); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(449, 449, 449))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(result_field, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))
                        .addComponent(tabbHD, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(result_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(tabbHD, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lí bán hàng", jPanel4);

        javax.swing.GroupLayout FrameBanHangLayout = new javax.swing.GroupLayout(FrameBanHang.getContentPane());
        FrameBanHang.getContentPane().setLayout(FrameBanHangLayout);
        FrameBanHangLayout.setHorizontalGroup(
            FrameBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1273, Short.MAX_VALUE)
            .addGroup(FrameBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FrameBanHangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        FrameBanHangLayout.setVerticalGroup(
            FrameBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
            .addGroup(FrameBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FrameBanHangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        TabeBanHang.addTab("Bán Hàng", FrameBanHang);

        paneTong.add(TabeBanHang, "bhang");

        frameSanPham.setVisible(true);

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(102, 255, 255));

        txtTen_SP.setName("Tên sản phẩm"); // NOI18N

        jLabel30.setText("Màu sắc: ");

        txtMauSac_SP.setName("Màu sắc"); // NOI18N

        jLabel31.setText("Nhà sản xuất:");

        txtNSX_SP.setName("Nhà sản xuất"); // NOI18N
        txtNSX_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNSX_SPActionPerformed(evt);
            }
        });

        jLabel32.setText("Số lượng");

        txtSoLuong_SP.setName("Số lượng"); // NOI18N
        txtSoLuong_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuong_SPActionPerformed(evt);
            }
        });

        jLabel33.setText("Trang thai: ");

        jLabel34.setText("Trọng lượng:");

        buttonGroup1.add(rdConHang);
        rdConHang.setText("Còn hàng");

        txtTrongLuong_SP.setName("Trọng lượng"); // NOI18N

        buttonGroup1.add(rdHetHang);
        rdHetHang.setText("Hết hàng");

        jLabel35.setText("Giá bán:");

        txtGiaBan_SP.setName("Giá bán"); // NOI18N

        jLabel36.setText("Giá nhập:");

        txtGiaNhap_SP.setName("Giá nhập"); // NOI18N
        txtGiaNhap_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhap_SPActionPerformed(evt);
            }
        });

        jLabel37.setText("Mô tả:");

        cbbDongSP_SP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDongSP_SP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDongSP_SPItemStateChanged(evt);
            }
        });

        jLabel38.setText("Dòng SP:");

        jLabel39.setText("Tên sản phẩm: ");

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btAddSP.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Images\\Add.png")); // NOI18N
        btAddSP.setText("Add");
        btAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddSPActionPerformed(evt);
            }
        });

        btUpdateSP.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Dakirby309-Windows-8-Metro-Other-Update-Metro.32.png")); // NOI18N
        btUpdateSP.setText("Update");
        btUpdateSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateSPActionPerformed(evt);
            }
        });

        btClearSP.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Seanau-Email-Clear.32.png")); // NOI18N
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
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(btAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btUpdateSP)
                .addGap(26, 26, 26))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btClearSP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddSP, btClearSP, btUpdateSP});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddSP)
                    .addComponent(btUpdateSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btClearSP)
                .addGap(15, 15, 15))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAddSP, btClearSP, btUpdateSP});

        btnTao.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Bootstrap-Bootstrap-Bootstrap-qr-code-scan.32.png")); // NOI18N
        btnTao.setText("Tạo mã");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        jLabel40.setText("QR:");

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
        jScrollPane4.setViewportView(tbHienThiSP);

        jLabel41.setText("Mã SP:");

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiện ích"));

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

        jLabel42.setText("Trạng thái:");

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
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTrangThai_Loc1, 0, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cbbTrangThai_Loc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc - Dòng Sản phẩm"));

        jLabel43.setText("Dòng SP:");

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
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbDongSP_Loc1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(cbbDongSP_Loc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        btnTao1.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Gakuseisean-Aire-Search-Images.32.png")); // NOI18N
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

        jButton4.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Steve-Zondicons-View-Show.32.png")); // NOI18N
        jButton4.setText("Show");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lbMa_SP.setText("   ");
        lbMa_SP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Kích thước:");

        jLabel7.setText("Chất liệu:");

        jButton5.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-Filetype-Excel-xls.32.png")); // NOI18N
        jButton5.setText("Nhập File");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnTao)
                                            .addComponent(jButton4)
                                            .addComponent(btnTao1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5))
                                    .addComponent(lb_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel37)))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                            .addComponent(jLabel40)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbQR_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel41)
                                                .addComponent(jLabel39)
                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                    .addComponent(jLabel30)
                                                    .addGap(36, 36, 36)
                                                    .addComponent(txtMauSac_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(6, 6, 6)))
                                    .addComponent(jLabel38))
                                .addGap(43, 43, 43))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cbbDongSP_SP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbMa_SP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTen_SP, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                            .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(rdConHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdHetHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtKichThuoc_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel35)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiaBan_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(txtTrongLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(172, 172, 172))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbDongSP_SP, lbMa_SP, txtGiaNhap_SP, txtMauSac_SP, txtNSX_SP, txtTen_SP});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel35)
                                .addComponent(txtGiaBan_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel34)
                                        .addComponent(txtTrongLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(20, 20, 20)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel32)
                                        .addComponent(txtSoLuong_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtKichThuoc_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtChatLieu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rdConHang)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rdHetHang)
                                            .addComponent(jLabel33)))
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(lb_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnTao1)
                                        .addComponent(jButton5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnTao)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton4))))
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMa_SP)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel41)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtMauSac_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtNSX_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtGiaNhap_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(cbbDongSP_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(lbQR_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbDongSP_SP, lbMa_SP, txtGiaNhap_SP, txtMauSac_SP, txtNSX_SP, txtTen_SP});

        jTabbedPane2.addTab("Sản phẩm", jPanel11);

        jPanel15.setBackground(new java.awt.Color(102, 255, 255));

        jPanel18.setBackground(new java.awt.Color(102, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Dòng sản  phẩm"));

        lable.setText("ID");

        lbID_DSP.setText(" ");

        jLabel44.setText("Dòng Sản phẩm:");

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
        jScrollPane5.setViewportView(tbHienThi_DSP);

        jButton6.setText("Đọc");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(jLabel44)
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
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel44)
                    .addComponent(txtDongSP_DSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddDSP)
                    .addComponent(btUpdateDSP)
                    .addComponent(btDeleteDelete)
                    .addComponent(btClearDSP))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thuộc tính chi tiết", jPanel15);

        javax.swing.GroupLayout frameSanPhamLayout = new javax.swing.GroupLayout(frameSanPham.getContentPane());
        frameSanPham.getContentPane().setLayout(frameSanPhamLayout);
        frameSanPhamLayout.setHorizontalGroup(
            frameSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        frameSanPhamLayout.setVerticalGroup(
            frameSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );

        tabSanPham.addTab("Quản lý sản phẩm", frameSanPham);

        paneTong.add(tabSanPham, "sp");

        jframeKhuyenMai.setVisible(true);

        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(153, 255, 255));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane6.setViewportView(tbHienThi);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tìm Kiếm :");

        txtTimKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKMKeyReleased(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Jommans-Mushroom-Search.32.png")); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Trạng Thái :");

        cbbLocTrangThaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn Hạn", "Hết Hạn", "Chưa Hoạt Động" }));
        cbbLocTrangThaiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTrangThaiKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(23, 23, 23)
                .addComponent(cbbLocTrangThaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbbLocTrangThaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel8)
                .addGap(52, 52, 52)
                .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(101, 101, 101)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiem)
                        .addComponent(jLabel8))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

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
        dateNgayBD.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayBDPropertyChange(evt);
            }
        });

        jLabel76.setText("Ngày Kết Thúc : ");

        dateNgayKT.setDateFormatString("dd/MM/yyyy");

        jLabel77.setText("Mô Tả :");

        txtAMoTaKM.setColumns(20);
        txtAMoTaKM.setRows(5);
        jScrollPane13.setViewportView(txtAMoTaKM);

        btnTaoKM.setBackground(new java.awt.Color(51, 204, 255));
        btnTaoKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoKM.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Images\\Add.png")); // NOI18N
        btnTaoKM.setText("Thêm");
        btnTaoKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoKMActionPerformed(evt);
            }
        });

        btnLamMoiKM.setBackground(new java.awt.Color(51, 204, 255));
        btnLamMoiKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoiKM.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Seanau-Email-Clear.32.png")); // NOI18N
        btnLamMoiKM.setText("Mới");
        btnLamMoiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKMActionPerformed(evt);
            }
        });

        btnSuaKM.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaKM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaKM.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Dakirby309-Windows-8-Metro-Other-Update-Metro.32.png")); // NOI18N
        btnSuaKM.setText("Sửa");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        jLabel45.setText("Trạng Thái :");

        rdHetHan.setText("Hết Hạn");

        rdConHan.setText("Còn Hạn");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addComponent(jLabel73)
                    .addComponent(jLabel45)
                    .addComponent(btnTaoKM))
                .addGap(24, 24, 24)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addComponent(rdHetHan)
                            .addGap(62, 62, 62)
                            .addComponent(rdConHan))
                        .addComponent(txtTenKhuyenMai)
                        .addComponent(txtGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(btnLamMoiKM)
                        .addGap(30, 30, 30)
                        .addComponent(btnSuaKM)))
                .addGap(55, 55, 55)
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
                .addGap(266, 266, 266))
        );

        jPanel36Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoiKM, btnSuaKM, btnTaoKM});

        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel74)))
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(rdHetHan)
                            .addComponent(rdConHan)))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaoKM)
                            .addComponent(btnLamMoiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaKM))))
                .addGap(412, 412, 412))
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel75)
                            .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel76)
                        .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel36Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoiKM, btnSuaKM, btnTaoKM});

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Khuyến Mãi", jPanel17);

        jPanel23.setBackground(new java.awt.Color(153, 255, 255));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        tbHienThiSP1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên KM", "Tên SP", "Ngày bắt đầu", "Ngày kết thúc", "Giá Giảm", "Trạng Thái"
            }
        ));
        tbHienThiSP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiSP1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbHienThiSP1);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setText("Tìm Kiếm :");

        txtTimSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSPKeyReleased(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(51, 204, 255));
        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Jommans-Mushroom-Search.32.png")); // NOI18N
        btnTim.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel46)
                .addGap(27, 27, 27)
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnTim)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));

        btnTaoSP.setBackground(new java.awt.Color(51, 204, 255));
        btnTaoSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoSP.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-Folded-Add-contact-folded.32.png")); // NOI18N
        btnTaoSP.setText("Thêm");
        btnTaoSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoSPActionPerformed(evt);
            }
        });

        btnSuaSP.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaSP.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Dakirby309-Windows-8-Metro-Other-Update-Metro.32.png")); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 204, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Hopstarter-Button-Button-Delete.32.png")); // NOI18N
        jButton7.setText("Làm Mới");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        tbHienThiTTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "TênSP", "Giá Bán", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbHienThiTTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiTTSPMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbHienThiTTSP);

        cbbTenKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel47.setText("Tên Khuyến Mãi :");

        jLabel48.setText("Tìm kiếm :");

        txtTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPKeyReleased(evt);
            }
        });

        jLabel49.setText("Giá Từ : ");

        txtTu.setText("0");
        txtTu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuKeyReleased(evt);
            }
        });

        jLabel50.setText("Đến :");

        txtDen.setText("0");
        txtDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDenKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbTenKM, 0, 251, Short.MAX_VALUE)
                            .addComponent(txtTimKiemSP)))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnTaoSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(18, 18, 18)
                        .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel39Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaSP, btnTaoSP, jButton7});

        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoSP)
                    .addComponent(btnSuaSP))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(32, 32, 32))
        );

        jPanel39Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaSP, btnTaoSP, jButton7});

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        jTabbedPane3.addTab("Chi Tiết Khuyến Mãi", jPanel23);

        javax.swing.GroupLayout jframeKhuyenMaiLayout = new javax.swing.GroupLayout(jframeKhuyenMai.getContentPane());
        jframeKhuyenMai.getContentPane().setLayout(jframeKhuyenMaiLayout);
        jframeKhuyenMaiLayout.setHorizontalGroup(
            jframeKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jframeKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jframeKhuyenMaiLayout.setVerticalGroup(
            jframeKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jframeKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        tabKhuyenmai.addTab("QL khuyến mãi", jframeKhuyenMai);

        paneTong.add(tabKhuyenmai, "km");

        jframeNhanVien.setVisible(true);

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel25.setBackground(new java.awt.Color(153, 255, 255));

        jLabel51.setText("Mã nhân viên:");

        jLabel52.setText("Tên nhân viên:");

        txtTenNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên nhân viên"));

        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã nhân viên"));

        txtCmnd.setBorder(javax.swing.BorderFactory.createTitledBorder("CMND"));

        jLabel53.setText("CMND:");

        radioNam.setText("Nam");

        jLabel54.setText("Giới tính:");

        radioNu.setText("Nữ");

        jLabel55.setText("Ngày sinh:");

        txtSdt.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        jLabel56.setText("Số điện thoại:");

        jLabel57.setText("Tên tài khoản:");

        txtTenTaiKhoan.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên tài khoản"));

        jLabel58.setText("Mật khẩu:");

        txtEmail.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jLabel59.setText("Email:");

        jLabel60.setText("Chức vụ:");

        jLabel61.setText("Trạng thái:");

        comboboxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn làm", "Đã nghỉ" }));

        jLabel62.setText("Địa chỉ:");

        txtDiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));

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
            .addComponent(labelThemAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
        );
        panelThemAnhLayout.setVerticalGroup(
            panelThemAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemAnhLayout.createSequentialGroup()
                .addComponent(labelThemAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonThem.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Images\\Add.png")); // NOI18N
        buttonThem.setText("Lưu");
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        buttonCapNhat.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Dakirby309-Windows-8-Metro-Other-Update-Metro.32.png")); // NOI18N
        buttonCapNhat.setText("Cập nhật");
        buttonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatActionPerformed(evt);
            }
        });

        buttonXoa.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Hopstarter-Button-Button-Delete.32.png")); // NOI18N
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
                "ID", "Mã", "Tên", "CMND", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "Ảnh", "Tài khoản", "Mật khẩu", "Chức vụ", "Trạng thái"
            }
        ));
        tableHienThi.setGridColor(new java.awt.Color(255, 255, 255));
        tableHienThi.setSelectionBackground(new java.awt.Color(51, 102, 255));
        tableHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHienThiMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tableHienThi);

        txtMatKhau.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu"));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addGap(21, 21, 21))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel51)
                                .addComponent(jLabel52)
                                .addComponent(jLabel54)
                                .addComponent(jLabel55)
                                .addComponent(jLabel53))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel62)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(radioNam)
                                .addGap(18, 18, 18)
                                .addComponent(radioNu))
                            .addComponent(txtCmnd, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtDiaChi))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel60)
                                        .addGap(220, 220, 220))
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel58)
                                            .addComponent(jLabel57)
                                            .addComponent(jLabel56))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addComponent(comboboxChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel61)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboboxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel59)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(96, 96, 96)
                        .addComponent(panelThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(buttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(buttonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jPanel25Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonCapNhat, buttonThem, buttonXoa});

        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel51))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel52)
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(txtCmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(radioNam)
                            .addComponent(radioNu))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(panelThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboboxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboboxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))))
                .addGap(36, 36, 36)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel25Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonCapNhat, buttonThem, buttonXoa});

        jTabbedPane4.addTab("Thông tin nhân viên", jPanel25);

        javax.swing.GroupLayout jframeNhanVienLayout = new javax.swing.GroupLayout(jframeNhanVien.getContentPane());
        jframeNhanVien.getContentPane().setLayout(jframeNhanVienLayout);
        jframeNhanVienLayout.setHorizontalGroup(
            jframeNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1273, Short.MAX_VALUE)
            .addGroup(jframeNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jframeNhanVienLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane4)
                    .addContainerGap()))
        );
        jframeNhanVienLayout.setVerticalGroup(
            jframeNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
            .addGroup(jframeNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jframeNhanVienLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tabNhanVien.addTab("Ql nhân viên", jframeNhanVien);

        paneTong.add(tabNhanVien, "nv");

        jframeKhachHang.setVisible(true);

        jPanel26.setBackground(new java.awt.Color(153, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 51, 51));
        jLabel63.setText("Thiết Lập Thông Tin Khách Hàng");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 51, 51));
        jLabel64.setText("Thông Tin Khách Hàng");

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setText("Tên KH :");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel66.setText("Giới Tính :");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel67.setText("SĐT :");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel68.setText("Ngày Sinh :");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setText("Địa Chỉ :");

        rdNam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdNam.setText("Nam");

        rdNu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdNu.setText("Nữ");

        btnThem2.setBackground(new java.awt.Color(51, 204, 255));
        btnThem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem2.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-Folded-Add-contact-folded.32.png")); // NOI18N
        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(51, 204, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Hopstarter-Button-Button-Delete.32.png")); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(51, 204, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Dakirby309-Windows-8-Metro-Other-Update-Metro.32.png")); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(51, 204, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Seanau-Email-Clear.32.png")); // NOI18N
        btnLamMoi.setText("Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtaDiaChi.setColumns(20);
        txtaDiaChi.setRows(5);
        jScrollPane10.setViewportView(txtaDiaChi);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(jLabel65))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(btnThem2)
                        .addGap(66, 66, 66)
                        .addComponent(btnXoa)
                        .addGap(75, 75, 75)
                        .addComponent(btnSua)
                        .addGap(48, 48, 48)
                        .addComponent(btnLamMoi)
                        .addGap(0, 500, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                .addComponent(txtTenKhachHang))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(rdNam)
                                .addGap(31, 31, 31)
                                .addComponent(rdNu)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateNgaySinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel66)
                                    .addComponent(rdNam)
                                    .addComponent(rdNu))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel67)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel69)))
                        .addGap(41, 41, 41))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(dateNgaySinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoa)
                        .addComponent(btnSua)
                        .addComponent(btnLamMoi))
                    .addComponent(btnThem2))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6)
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        tbHienThi1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã KH", "Tên KH", "Giới Tính", "SDT", "Ngày Sinh", "Địa Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHienThi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThi1MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbHienThi1);

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel70.setText("Tìm Kiếm :");

        txtTim1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTim1KeyReleased(evt);
            }
        });

        btnTim1.setBackground(new java.awt.Color(51, 204, 255));
        btnTim1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTim1.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Gakuseisean-Aire-Search-Images.32.png")); // NOI18N
        btnTim1.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel70)
                .addGap(27, 27, 27)
                .addComponent(txtTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnTim1)
                .addContainerGap(446, Short.MAX_VALUE))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(txtTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel63)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPane5.addTab("Danh Sách KH", jPanel26);

        javax.swing.GroupLayout jframeKhachHangLayout = new javax.swing.GroupLayout(jframeKhachHang.getContentPane());
        jframeKhachHang.getContentPane().setLayout(jframeKhachHangLayout);
        jframeKhachHangLayout.setHorizontalGroup(
            jframeKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jframeKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5)
                .addContainerGap())
        );
        jframeKhachHangLayout.setVerticalGroup(
            jframeKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jframeKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5)
                .addGap(68, 68, 68))
        );

        tabKhachHang.addTab("Khach hàng", jframeKhachHang);

        paneTong.add(tabKhachHang, "kh");

        jframeThongKe.setVisible(true);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 0, 0, new java.awt.Color(0, 0, 0)));

        jPanel31.setBackground(new java.awt.Color(255, 255, 204));
        jPanel31.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jPanel32.setBackground(new java.awt.Color(255, 255, 153));
        jPanel32.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 51, 102));
        jLabel71.setText("Số ĐH :");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 51, 102));
        jLabel72.setText("Thành Công :");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 51, 102));
        jLabel78.setText("Bị Hủy :");

        lblSoDH2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoDH2.setForeground(new java.awt.Color(255, 51, 102));
        lblSoDH2.setText("0");

        lblthanhcong2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblthanhcong2.setForeground(new java.awt.Color(255, 51, 102));
        lblthanhcong2.setText("0");

        lblbihuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblbihuy.setForeground(new java.awt.Color(255, 51, 102));
        lblbihuy.setText("0");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSoDH2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblthanhcong2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblbihuy, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(lblSoDH2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(lblthanhcong2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(lblbihuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel33.setBackground(new java.awt.Color(153, 255, 153));
        jPanel33.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 51, 102));
        jLabel79.setText("Doanh Thu Ngày :");

        lbldtngay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbldtngay.setForeground(new java.awt.Color(255, 51, 102));
        lbldtngay.setText("0");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 51, 102));
        jLabel80.setText("VND");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel79)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lbldtngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel80)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldtngay)
                    .addComponent(jLabel80))
                .addGap(23, 23, 23))
        );

        jPanel34.setBackground(new java.awt.Color(153, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 51, 102));
        jLabel81.setText("Doanh Thu Tháng :");

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 51, 102));
        jLabel82.setText("VND");

        lbldtthang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbldtthang.setForeground(new java.awt.Color(255, 51, 102));
        lbldtthang.setText("0");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addComponent(lbldtthang, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel82))
                    .addComponent(jLabel81))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel81)
                .addGap(30, 30, 30)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldtthang)
                    .addComponent(jLabel82))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.setBackground(new java.awt.Color(255, 204, 204));
        jPanel35.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 51, 102));
        jLabel83.setText("Tổng Khách Hàng");

        lblKH.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblKH.setForeground(new java.awt.Color(255, 51, 102));
        lblKH.setText("0");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblKH, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel35Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel83)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblKH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.setBackground(new java.awt.Color(255, 255, 204));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel84.setText("Loại Thời Gian :");

        cbbltg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo Ngày", "Hôm Nay", " ", " ", " ", " " }));
        cbbltg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbltgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbltg, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(cbbltg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        paneTT.setBackground(new java.awt.Color(255, 255, 204));
        paneTT.setBorder(new javax.swing.border.MatteBorder(null));
        paneTT.setLayout(new java.awt.CardLayout());

        panekhoangngay.setBackground(new java.awt.Color(255, 255, 204));

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel85.setText("Từ Ngày :");

        datetngayBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datetngayBDMouseClicked(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel86.setText("Đến Ngày :");

        datengayKT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datengayKTMouseClicked(evt);
            }
        });

        btnkhoangngay.setBackground(new java.awt.Color(102, 204, 255));
        btnkhoangngay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                .addComponent(jLabel85)
                .addGap(18, 18, 18)
                .addComponent(datetngayBD, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panekhoangngayLayout.createSequentialGroup()
                        .addGroup(panekhoangngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panekhoangngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnkhoangngay)
                                .addComponent(datetngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(datengayKT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );

        paneTT.add(panekhoangngay, "card3");

        paneHientai.setBackground(new java.awt.Color(255, 255, 204));

        btnhiendthientai.setBackground(new java.awt.Color(102, 204, 255));
        btnhiendthientai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        jButton11.setBackground(new java.awt.Color(102, 204, 255));
        jButton11.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Graphicloads-Filetype-Excel-xls.32.png")); // NOI18N
        jButton11.setText("xuất excel");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(paneTT, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paneTT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jTabbedPane8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jTabbedPane8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane8MouseClicked(evt);
            }
        });

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jTabbedPane9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane9MouseClicked(evt);
            }
        });

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane12.setViewportView(Tbltheongay);

        cbbthoigian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo Ngày", " Theo Tháng", " " }));
        cbbthoigian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbthoigianActionPerformed(evt);
            }
        });

        jPanel41.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel41.setLayout(new java.awt.CardLayout());

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

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel87.setText("chọn ngày :");

        javax.swing.GroupLayout panetngayLayout = new javax.swing.GroupLayout(panetngay);
        panetngay.setLayout(panetngayLayout);
        panetngayLayout.setHorizontalGroup(
            panetngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panetngayLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel87)
                .addGap(27, 27, 27)
                .addComponent(dateTK_doanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        panetngayLayout.setVerticalGroup(
            panetngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panetngayLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panetngayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateTK_doanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel41.add(panetngay, "card2");

        panemthang.setBackground(new java.awt.Color(255, 255, 255));
        panemthang.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel88.setText("Tháng :");

        cbbthang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel89.setText("Năm :");

        btntim.setBackground(new java.awt.Color(0, 204, 255));
        btntim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbthang, 0, 107, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel89)
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
                    .addComponent(jLabel88)
                    .addComponent(cbbthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89)
                    .addComponent(txtnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntim))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel41.add(panemthang, "card3");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(cbbthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(cbbthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane9.addTab("Chi Tiết ", jPanel40);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jTabbedPane9.addTab("Biểu Đồ", jPanel42);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane9)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jTabbedPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Doanh Thu", jPanel38);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        tblSP_H.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Màu Sắc", "NSX", "Số ĐH", "Số Lượng", "Tổng Tiền"
            }
        ));
        jScrollPane14.setViewportView(tblSP_H);

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel45.setForeground(new java.awt.Color(255, 255, 255));
        jPanel45.setLayout(new java.awt.CardLayout());

        panelSPtheongay.setBackground(new java.awt.Color(255, 255, 255));
        panelSPtheongay.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        panelSPtheongay.setPreferredSize(new java.awt.Dimension(480, 54));

        jLabel90.setBackground(new java.awt.Color(0, 0, 0));
        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel90.setText("Tìm Ngày :");

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
                .addComponent(jLabel90)
                .addContainerGap(369, Short.MAX_VALUE))
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
                .addComponent(jLabel90)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(panelSPtheongayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSPtheongayLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(date_SPNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );

        jPanel45.add(panelSPtheongay, "card2");

        panelSPThang.setBackground(new java.awt.Color(255, 255, 255));
        panelSPThang.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel91.setText("Tháng : ");

        cbbSPthang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel92.setText("Năm :");

        txtSPNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSPNamActionPerformed(evt);
            }
        });

        btnthang.setBackground(new java.awt.Color(51, 204, 255));
        btnthang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSPthang, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSPNam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthang)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        panelSPThangLayout.setVerticalGroup(
            panelSPThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPThangLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelSPThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(cbbSPthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92)
                    .addComponent(txtSPNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthang))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel45.add(panelSPThang, "card3");

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng Hợp Sản Phẩm đã Bán Trong Các Năm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 51, 102))); // NOI18N
        jPanel46.setForeground(new java.awt.Color(255, 0, 102));

        tblTONGHOP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên Sp", "Màu Sắc", "NSX", "Số ĐH", "Số Lượng", "Tổng Tiền"
            }
        ));
        jScrollPane15.setViewportView(tblTONGHOP);

        btnhien.setBackground(new java.awt.Color(51, 204, 255));
        btnhien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhien.setText("Hien");
        btnhien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhienActionPerformed(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel93.setText("Năm :");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(jLabel93)
                                .addGap(18, 18, 18)
                                .addComponent(txtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                        .addComponent(btnhien)
                        .addGap(252, 252, 252))))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(txtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbccngay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel44Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(cbbccngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jTabbedPane10.addTab("Chi Tiết", jPanel44);

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane10.addTab("Biểu đồ", jPanel47);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane10)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane10)
        );

        jTabbedPane8.addTab("Sản Phẩm", jPanel43);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 1270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane7.addTab("Doanh Thu", jPanel30);

        javax.swing.GroupLayout jframeThongKeLayout = new javax.swing.GroupLayout(jframeThongKe.getContentPane());
        jframeThongKe.getContentPane().setLayout(jframeThongKeLayout);
        jframeThongKeLayout.setHorizontalGroup(
            jframeThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1273, Short.MAX_VALUE)
        );
        jframeThongKeLayout.setVerticalGroup(
            jframeThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );

        tabThongKe.addTab("Thống kê", jframeThongKe);

        paneTong.add(tabThongKe, "tk");

        jfarmeLichSu.setVisible(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã khách hàng", "Ngày tạo", "Ngày thanh toán", "Tình trạng", "Hình thức thanh toán"
            }
        ));
        jScrollPane16.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane11.addTab("Lịch sử hóa đơn", jPanel48);

        javax.swing.GroupLayout jfarmeLichSuLayout = new javax.swing.GroupLayout(jfarmeLichSu.getContentPane());
        jfarmeLichSu.getContentPane().setLayout(jfarmeLichSuLayout);
        jfarmeLichSuLayout.setHorizontalGroup(
            jfarmeLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane11)
        );
        jfarmeLichSuLayout.setVerticalGroup(
            jfarmeLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jfarmeLichSuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane11)
                .addGap(42, 42, 42))
        );

        tabLichSU.addTab("Lịch sử", jfarmeLichSu);

        paneTong.add(tabLichSU, "ls");

        jInternalFrame1.setVisible(true);

        jPanel50.setBackground(new java.awt.Color(153, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbHienThi2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã HD", "Tên KH", "SDT", "Ngày Thanh Toán", "Trạng Thái"
            }
        ));
        tbHienThi2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThi2MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tbHienThi2);

        jLabel106.setText("Tìm Kiếm :");

        txtTim2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));

        btnTim2.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Jommans-Mushroom-Search.32.png")); // NOI18N
        btnTim2.setText("Tìm kiếm");
        btnTim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel106)
                .addGap(34, 34, 34)
                .addComponent(txtTim2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btnTim2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel106)
                        .addComponent(btnTim2))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(txtTim2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel51.setBackground(new java.awt.Color(153, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbhHienThiCTBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên KH", "Sdt", "Mã SP", "Tên SP", "Sửa Chữa", "Ngày Bảo Hành", "Ngày Trả", "Số lượng", "Trạng Thái", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbhHienThiCTBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhHienThiCTBHMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tbhHienThiCTBH);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jPanel52.setBackground(new java.awt.Color(153, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin bảo hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)))); // NOI18N

        jLabel107.setText("Tên KH :");

        jLabel108.setText("Mã bảo hành :");

        lbMaBH.setText("jLabel7");

        jLabel109.setText("Tên SP :");

        jLabel110.setText("Sửa chữa : ");

        lbTenSP.setText("jLabel10");

        cbbSuaChua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đội Sửa Chữa 1", "Đội Sửa Chữa 2" }));

        jLabel111.setText("Trạng thái :");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Xử Lí", "Đã Hoàn Thành" }));

        jLabel112.setText("Mô tả :");

        txtAMoTa.setColumns(20);
        txtAMoTa.setRows(5);
        jScrollPane20.setViewportView(txtAMoTa);

        jLabel113.setText("SDT :");

        btnSua1.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Dakirby309-Windows-8-Metro-Other-Update-Metro.32.png")); // NOI18N
        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnLamMoi1.setIcon(new javax.swing.ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\iconBT\\Seanau-Email-Clear.32.png")); // NOI18N
        btnLamMoi1.setText("Làm Mới");

        jLabel114.setText("Ngày BH :");

        jLabel115.setText("Ngày Trả :");

        dateNgayTra.setDateFormatString("dd/MM/yyyy");
        dateNgayTra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayTraPropertyChange(evt);
            }
        });

        dateNgayBH.setDateFormatString("dd/MM/yyyy");
        dateNgayBH.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayBHPropertyChange(evt);
            }
        });

        jLabel116.setText("Số Lượng :");

        lbTenKH.setText("jLabel10");

        lbSDT.setText("jLabel13");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel107)
                            .addComponent(jLabel108)
                            .addComponent(jLabel113)
                            .addComponent(jLabel109)
                            .addComponent(jLabel116))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMaBH)
                            .addComponent(lbTenSP)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTenKH)
                            .addComponent(lbSDT)))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btnLamMoi1))
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel111)
                                    .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel115)
                                        .addComponent(jLabel110)
                                        .addComponent(jLabel114)))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(dateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateNgayBH, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbbSuaChua, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel52Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi1, btnSua1});

        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(lbMaBH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(lbTenKH))
                .addGap(21, 21, 21)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113)
                    .addComponent(lbSDT))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(lbTenSP))
                .addGap(15, 15, 15)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel116)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel114)
                    .addComponent(dateNgayBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115))
                .addGap(15, 15, 15)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(cbbSuaChua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua1)
                    .addComponent(btnLamMoi1))
                .addGap(27, 27, 27))
        );

        jPanel52Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi1, btnSua1});

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabBaoHanh.addTab("Bảo hành", jInternalFrame1);

        paneTong.add(tabBaoHanh, "bh");

        lbl_Chay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_Chay.setText("Chào Mừng Tất Cả Mọi Người Đến Với Cửa Hàng Trang Sức Phụ Kiện Nữ N1.Chúc Mọi Người Có Một Ngày Vui Vẻ.                                                                                                                                                      ");

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(lbl_Chay)
                .addGap(0, 119, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_Chay))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(paneTong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneTong, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
        clearMau();
        conBanHan();
        btnbanhang.setBackground(Color.red);
        carLayout.show(paneTong, "bhang");
    }//GEN-LAST:event_btnbanhangActionPerformed

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
        if (DangNhap.nv.getChucVu().getTenChucVu().equals("Nhan Vien")) {
            JOptionPane.showMessageDialog(this, "Cố làm thêm vài năm để lên chức đi");
            return;
        }
        clearMau();
        conNhanVien();
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        btnnhanvien.setBackground(Color.red);
        carLayout.show(paneTong, "nv");
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamActionPerformed
        clearMau();
        conSanPham();
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        btnsanpham.setBackground(Color.red);
        carLayout.show(paneTong, "sp");

    }//GEN-LAST:event_btnsanphamActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
        clearMau();
        conKhachHang();
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        btnkhachhang.setBackground(Color.red);
        carLayout.show(paneTong, "kh");
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnkhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhuyenmaiActionPerformed
        clearMau();
        conKhuyenMai();
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        btnkhuyenmai.setBackground(Color.red);
        carLayout.show(paneTong, "km");
    }//GEN-LAST:event_btnkhuyenmaiActionPerformed

    private void btmthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmthongkeActionPerformed
        if (DangNhap.nv.getChucVu().getTenChucVu().equals("Nhan Vien")) {
            JOptionPane.showMessageDialog(this, "Cố làm thêm vài năm để lên chức đi");
            return;
        }
        clearMau();
        conThongKe();
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        btmthongke.setBackground(Color.red);
        carLayout.show(paneTong, "tk");
    }//GEN-LAST:event_btmthongkeActionPerformed

    private void btnlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlichsuActionPerformed
        clearMau();
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        conLichSu();
        btnlichsu.setBackground(Color.red);
        carLayout.show(paneTong, "ls");
    }//GEN-LAST:event_btnlichsuActionPerformed

    private void btndxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndxActionPerformed
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btndxActionPerformed

    private void btndx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndx1ActionPerformed
        clearMau();
        conBaoHanh();
        clear();
        clearDatHang();
        if (webcam != null) {
            webcam.close();
        }
        btndx1.setBackground(Color.red);
        carLayout.show(paneTong, "bh");
    }//GEN-LAST:event_btndx1ActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked

    }//GEN-LAST:event_tblgiohangMouseClicked

    private void tblgiohangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblgiohangMouseEntered

    private void btnxoatatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoatatcaActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa tất cả sản phẩm không ?")
                != JOptionPane.YES_OPTION) {
            return;
        }
        if (tblgiohang.getRowCount() == 0) {
            return;
        }
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
//        loadTableSP();
        count = 0;
//        if (tabbHD.getSelectedIndex() == 0) {
//            sanPhamHienThiOHoaDon();
//        } else
//            sanPhamHienThiODATHANG();
        clear();
        clearDatHang();
    }//GEN-LAST:event_btnxoatatcaActionPerformed

    private void btnxoaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaspActionPerformed
        if (tblgiohang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm cần xóa");
            return;
        }

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
            if (tabbHD.getSelectedIndex() == 0) {
                sanPhamHienThiOHoaDon();
            } else {
                sanPhamHienThiODATHANG();
            }
            return;
        }
//        for (int i = 0; i < tbltimtheoma.getRowCount(); i++) {
//            if (getValueTable(i, tbltimtheoma, 0).equals(getValueTable(tblgiohang.getSelectedRow(), tblgiohang, 0))) {
//                tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(i, tbltimtheoma, 5)) + soLuong, i, 5);
//                break;
//            }
//        }
        if (tabbHD.getSelectedIndex() == 0) {
            sanPhamHienThiOHoaDon();
        } else {
            sanPhamHienThiODATHANG();
        }

        tbnHD.clearSelection();
        //        this.resSP.update(s);
        //        loadTableSP();
    }//GEN-LAST:event_btnxoaspActionPerformed

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed

        if (tabbHD.getSelectedIndex() == 0) {
            clear();
        } else if (tabbHD.getSelectedIndex() == 1) {
            clearDatHang();
        }
        //  ID HOA DON , ID GIO HANG SE DUOC TAO TRUOC
        IDHD = jcheck.createID().toString();
        IDGH = IDHD;

        // TAO HOA DON KHI BAM NUT
        this.resGH.addHD(new HoaDon(IDHD, jcheck.randomMA(), new Date(new java.util.Date().getTime()), 4,
                null, DangNhap.nv
        ));

        //  TAO GIO HANG DONG THOI VOI HOA DON
        this.resGH.addGH(new GioHang(IDGH, jcheck.randomMA(),
                new Date(new java.util.Date().getTime()), 0));

        sapXep(this.resHD.getAll(""));
        hightLighHoaDonChon(IDHD);
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        dtm.addRow(new Object[]{});
    }//GEN-LAST:event_btntaohdActionPerformed

    private void tbnHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnHDMouseClicked
        count = 0;
        if (tabbHD.getSelectedIndex() == 0) {
            if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang chờ thanh toán")) {
                //  XU LI HOA DON CHO O BAN HANG
                if (hdCho != null) {
                    if (hdCho.getId().equalsIgnoreCase(getValueTable(tbnHD.getSelectedRow(), tbnHD, 0)) == true) {
                        return;
                    } else {
                        //                        loadTableSP();
                        //                        loadSLSanPhamPanel();
                        loadSLSanPhamPanelHDCho();
                    }
                    //                    System.out.println("hdcho: " + hdCho.getId());
                }

                xuLiHoaDonChoGiaoHang(1);
                IDHD = hdCho.getId();
                IDGH = hdCho.getId();
                loadSLSanPhamPanelHDCho();
            } else if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang xử lí")) {
                IDHD = ((HoaDon) this.resHD.getAll(dk(" d.id ", getValueTable(tbnHD.getSelectedRow(),
                        tbnHD, 0))).get(0)).getId();
                IDGH = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", getValueTable(tbnHD.getSelectedRow(),
                        tbnHD, 0))).get(0)).getId();
                if (hdCho != null) {
                    clearVipPro();
                }
                //                IDGH = null;
                //                IDHD = null;
                dtm = (DefaultTableModel) tblgiohang.getModel();
                dtm.setRowCount(0);
                dtm.addRow(new Object[]{});
                sapXep(listHD);
                //                loadTableSP();
                loadSLSanPhamPanel();
                hightLighHoaDonChon(IDHD);
                a = 0;
            } else {
                IDHD = null;
                IDGH = null;
                dtm = (DefaultTableModel) tblgiohang.getModel();
                dtm.setRowCount(0);
                clear();
                sapXep(listHD);
                //                loadTableSP();
                loadSLSanPhamPanel();
                a = 0;
            }

            //  DAT HANG
        } else if (tabbHD.getSelectedIndex() == 1) {
            if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).
                    equals("Đang giao hàng")) {
                //  XU LI HOA DON O DAT HANG
                if (hdCho != null) {
                    if (hdCho.getId().equalsIgnoreCase(getValueTable(tbnHD.getSelectedRow(), tbnHD, 0)) == true) {
                        return;
                    } else {
                        //                        loadTableSP();
                        //                        loadSLSanPhamPanel();
                        loadSLSanPhamPanelHDCho();
                    }
                }

                xuLiHoaDonChoGiaoHang(3);
                IDHD = hdCho.getId();
                IDGH = hdCho.getId();
                //                loadTableSP();
                loadSLSanPhamPanelHDCho();
            } else if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang xử lí")) {
                IDHD = ((HoaDon) this.resHD.getAll(dk(" d.id ", getValueTable(tbnHD.getSelectedRow(),
                        tbnHD, 0))).get(0)).getId();
                IDGH = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", getValueTable(tbnHD.getSelectedRow(),
                        tbnHD, 0))).get(0)).getId();
                if (hdCho != null) {
                    clearVipProUltra();
                }
                //                IDGH = null;
                //                IDHD = null;
                dtm = (DefaultTableModel) tblgiohang.getModel();
                dtm.setRowCount(0);
                dtm.addRow(new Object[]{});
                sapXep(listHD);
                //                loadTableSP();
                loadSLSanPhamPanel();
                hightLighHoaDonChon(IDHD);
                a = 0;
            } else {
                IDHD = null;
                IDGH = null;
                dtm = (DefaultTableModel) tblgiohang.getModel();
                dtm.setRowCount(0);
                clearDatHang();
                //                loadTableSP();
                loadSLSanPhamPanel();
                a = 0;
            }
        }
    }//GEN-LAST:event_tbnHDMouseClicked

    private void tbnHDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnHDMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnHDMouseEntered

    private void tbnHDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbnHDPropertyChange
        //        if (listHD.size() != 0) {
        //            sapXep(listHD);
        //        }
    }//GEN-LAST:event_tbnHDPropertyChange

    private void cbbtrangthaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbtrangthaiItemStateChanged
        //Đã thanh toán, Chưa thanh toán, Đã hũy, Đang giao hàng
        int tt = 0;
        if (cbbtrangthai.getSelectedIndex() == 0) {
            return;
        } else if (cbbtrangthai.getSelectedIndex() == 1) {
            tt = 0;
        } else if (cbbtrangthai.getSelectedIndex() == 2) {
            tt = 1;
        } else if (cbbtrangthai.getSelectedIndex() == 3) {
            tt = 2;
        } else if (cbbtrangthai.getSelectedIndex() == 4) {
            tt = 3;
        } else if (cbbtrangthai.getSelectedIndex() == 5) {
            tt = 4;
        } else if (cbbtrangthai.getSelectedIndex() == 6) {
            sapXep(this.resHD.getAll(""));
            return;
        }
        sapXep(this.resHDCho.getAll(" ct.tinhTrang = " + tt, tt));
    }//GEN-LAST:event_cbbtrangthaiItemStateChanged

    private void cbbtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaiActionPerformed

    private void cbbhinhthucttItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbhinhthucttItemStateChanged
        if (cbbhinhthuctt.getSelectedIndex() == 2) {
            txttienkhachdua.setEnabled(false);
        } else {
            txttienkhachdua.setEnabled(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbhinhthucttItemStateChanged

    private void txttienkhachduaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienkhachduaKeyReleased
        try {
            if (Float.parseFloat(txttienkhachdua.getText().trim()) < 0) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        float a = Float.parseFloat(txttienkhachdua.getText().trim()) - (Float.parseFloat(txtdg.getText().trim())
                - Float.parseFloat(txtgiamgia.getText().trim()));
        txttienthua.setText(String.format("%.2f", (float) Float.parseFloat(a + "")));
    }//GEN-LAST:event_txttienkhachduaKeyReleased

    private boolean checkKhachHangHDCho;
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
        if (IDHD == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể thanh toán hóa đơn");
            return;
        } else if (IDHD == null && hdCho == null && getValueTable(0, tblgiohang, 0) == null && rdoHuy.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào giỏ hàng");
            return;
        }

        //        if (IDHD != null || hdCho != null&& rdoHuy.isSelected() == false) {
        //            if (getValueTable(0, tblgiohang, 0) == null) {
        //                JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào giỏ hàng");
        //                return;
        //            }
        //
        //        }
        if (checkDuLieuBanHang() == false) {
            return;
        }

        if (tabbHD.getSelectedIndex() == 0 && rdoHuy.isSelected() == false) {
            //            if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).equals("Đang xử lí")) {
            for (HoaDon khachHang : this.resHD.getAll("")) {
                if (hdCho == null) {
                    if (khachHang.getKh() != null) {
                        if (khachHang.getKh().getId().equals(khachHangMiNi.getId()) && khachHang.getTinhTrang() == 1) {
                            JOptionPane.showMessageDialog(this, "Khách hàng đang có một hóa đơn chờ vui lòng thanh toán");
                            checkKhachHangHDCho = false;
                            return;
                            //                    }
                        }

                    }
                }
            }
        }

        if (tblgiohang.getRowCount() == 0 && rdoHuy.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phảm");
            return;
        }

        //        if (checkKhachHangHDCho == false) {
        //            return;
        //        }
        //  CHECK DU LIEU BAN HANG KHI CHON RADIO HOAN THANH
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
        List<SanPham> spList = new ArrayList<>();
        int soluong2[] = new int[this.resSP.getAll("").size()];
        float giamGia[] = new float[this.resSP.getAll("").size()];
        if (trangThai == 0) {
            float giamGiaHD = 0;
            for (int i = 0; i < tblgiohang.getRowCount(); i++) {
                id = getValueTable(i, tblgiohang, 0);
                SanPham sp = ((SanPham) this.resSP.getAll(dk(" s.ma ", id)).get(0));
                if (Float.parseFloat(tblgiohang.getValueAt(i, 3).toString()) > 0) {
                    giamGiaHD = (sp.getGiaBan() * Float.parseFloat(tblgiohang.getValueAt(i, 3).toString()) / 100)
                            * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
                }

                float tongTienHD = (Float.parseFloat(getValueTable(i, tblgiohang, 2)) * sp.getGiaBan())
                        - giamGiaHD;

                float giaGiamReal;
                if (this.ctkm.getALLJoin1(sp.getMa()).isEmpty()) {
                    giaGiamReal = 0;
                } else {
                    giaGiamReal = Float.parseFloat(txtgiamgia.getText().trim());
                }
                embeddableCTHD idDouble = new embeddableCTHD(IDHD, sp.getId());
                ChiTietHoaDon ctHD = new ChiTietHoaDon(idDouble, hd, sp,
                        Integer.parseInt(getValueTable(i, tblgiohang, 2)),
                        tongTienHD,
                        new Date(new java.util.Date().getTime()), trangThai,
                        giaGiamReal
                );
                sp.setSoLuong(sp.getSoLuong() - Integer.parseInt(getValueTable(i, tblgiohang, 2)));
                hd.setTinhTrang(0);
                hd.setNgayTT(new Date());
                hd.setHinhthucthanhtoan(cbbhinhthuctt.getSelectedItem().toString());
                hd.setKh(khachHangMiNi);
                hd.setNv(DangNhap.nv);
                hd.setTongTien(Double.parseDouble(txttongtien.getText().trim()));
                if (sp.getSoLuong() == 0) {
                    sp.setTrangThai(1);
                }
                this.resSP.update(sp);

                this.resGH.addHDCT(ctHD);

                this.resGH.updateHD(hd);

                if (hdCho == null) {
                    themSanPhamVaoGioHangChiTiet();
                }

                spList.add(sp);
                soluong2[i] = Integer.parseInt(getValueTable(i, tblgiohang, 2));
                //                baoHanhRes.add(new domaimodel.BaoHanh(jcheck.createID().toString(), jcheck.randomMA(),
                //                        1, sp, khachHangMiNi));

            }

            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hóa đơn không?") == JOptionPane.YES_OPTION) {
                xuatFilePdf(spList, hd, soluong2, Float.parseFloat(txttongtien.getText().trim()), giamGia, Float.parseFloat(txtgiamgia.getText().trim()));
                //                resDoiTra.add(new domaimodel.DoiTra(jcheck.createID().toString(), jcheck.randomMA(),
                //                        10, sp, khachHangMiNi));

            }

            JOptionPane.showMessageDialog(this, "Thanh toán thành công");

            //  TRUONG HOP HOA DON CHO, GIO HANG TRANG THAI 1, HOA DON TRANG THAI 1
        } else if (trangThai == 1) {

            //  TRUONG HOP KHI XU LI HOA DON CHO, NEU CHON RADIO 'CHO'
            if (hdCho != null) {
                JOptionPane.showMessageDialog(this, "Không thể tạo hóa chờ vui lòng thanh toán hóa đơn chờ hiện tại");
                return;
            } else {
                if (txttenkh.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin khách hàng");
                    return;
                }
                GioHang g = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0));
                g.setTinhtrang(1);
                hd.setTinhTrang(1);
                hd.setKh(khachHangMiNi);
                hd.setNv(DangNhap.nv);
                g.setNv(hd.getKh());
                this.resGH.updateGH(g);
                this.resGH.updateHD(hd);
            }
            if (hdCho == null) {
                themSanPhamVaoGioHangChiTiet();
            }
            //            clear();
            //  TRUONG HOP HOA DON HUY, GIO HANG TRANG THAI 2, HOA DON TRANG THAI 2
        } else if (trangThai == 2) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy hóa đơn ?", "Huy", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            //  TRUONG HOP XU LI HOA DON KHI CHON RADIO HUY
            if (hdCho != null) {
                String lyDo = null;
                do {
                    lyDo = JOptionPane.showInputDialog(this, "Vui lòng nhập lý do hủy");
                    if (lyDo.trim().length() == 0) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập lý do");
                    }
                } while (lyDo.trim().length() == 0);

                hdCho.setLyDo(lyDo);
                hdCho.setTinhTrang(2);
                hdCho.setNv(DangNhap.nv);
                GioHang gioHang = ((ChiTietGioHang) ctghCho.get(0)).getGh();
                gioHang.setTinhtrang(2);
                this.resGH.updateGH(gioHang);
                this.resGH.updateHD(hdCho);
            } else {
                String lyDo = null;
                do {
                    lyDo = JOptionPane.showInputDialog(this, "Vui lòng nhập lý do hủy");
                    if (lyDo.trim().length() == 0) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập lý do");
                    }
                } while (lyDo.trim().length() == 0);

                hd.setLyDo(lyDo);
                GioHang g = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0));
                g.setTinhtrang(2);
                hd.setTinhTrang(2);
                hd.setNv(DangNhap.nv);
                hd.setKh(khachHangMiNi);
                g.setNv(hd.getKh());
                this.resGH.updateGH(g);
                this.resGH.updateHD(hd);
            }
        }

        sapXep(this.resHD.getAll(""));
        loadTableSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        clear();
        //        loadTableSP();
        //        loadSLSanPhamPanelHDCho();
    }//GEN-LAST:event_btnttActionPerformed

    private void rdochuattActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdochuattActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdochuattActionPerformed

    public void getDateDatHang(KhachHang data) {
        txtTenShip.setText(data.getTen());
        txtSDTShip.setText(data.getSdt());
        txtDShip.setText(data.getDiaChi());
        khachHangMiNi = data;
    }
//    public void getData(KhachHang data) {
//
//        txttenkh.setText(data.getTen());
//        khachHangMiNi = data;
////        setLocationRelativeTo(null);
//    }

    public void khachHangMiNiBanHang(KhachHang k) {
        txttenkh.setText(k.getTen());
        khachHangMiNi = k;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new khachHangMini().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        System.out.println("pane 0");        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MouseClicked

    private void txtPVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPVCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPVCActionPerformed

    private void txtgiamgia1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtgiamgia1InputMethodTextChanged

    }//GEN-LAST:event_txtgiamgia1InputMethodTextChanged

    private void txtgiamgia1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtgiamgia1PropertyChange
        //        System.out.println("alsjflsa");
        //        btnChuyenHang.setVisible(true);
        //        btnHoangThanh.setVisible(true);
        //        btnHuyDonHang.setVisible(true);
    }//GEN-LAST:event_txtgiamgia1PropertyChange

    private void txtgiamgia1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtgiamgia1VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiamgia1VetoableChange

    private void txttongtien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtien1ActionPerformed

    private void txtdg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdg1ActionPerformed

    private void txtsl1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsl1FocusLost

    }//GEN-LAST:event_txtsl1FocusLost

    private void txtsl1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtsl1PropertyChange

    }//GEN-LAST:event_txtsl1PropertyChange

    private void btnHoangThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoangThanhActionPerformed
        checkTimKiem = 2;
        if (IDHD == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể thực hiện");
            return;
        }

        if (checkButton != 3 && getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).
                equals("Đang giao hàng") == false) {
            JOptionPane.showMessageDialog(this, "Không thể xử lí");
            return;
        }

        // KHACH HANG NHAN DUOC HANG CHUYEN SANG HOAN THANH
        themSanPhamVaoGioHangChiTiet();
        chuyenHangChoKhach(0, new Date());
        sapXep(this.resHD.getAll(""));
        clearDatHang();
        JOptionPane.showMessageDialog(this, "Đã hoàn thành đơn hàng");
        loadTableSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        checkButton = 0;
    }//GEN-LAST:event_btnHoangThanhActionPerformed

    private void btnChuyenHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenHangActionPerformed
        if (IDHD == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể thực hiện");
            return;
        } else if (IDHD == null && hdCho == null && getValueTable(0, tblgiohang, 0) == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào giỏ hàng");
            return;
        }

        if (IDHD != null || hdCho != null) {
            if (getValueTable(0, tblgiohang, 0) == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào giỏ hàng");
                return;
            }

        }

        if (checkDLDatHang() == false) {
            return;
        }
        //  CHUYEN HANG CHO KHACH HANG
        if (hdCho != null) {
            JOptionPane.showMessageDialog(this, "Không thể chuyên hàng vui lòng hoàn thành hoặc hủy đơn hàng chờ lúc này");
            return;
        }

        themSanPhamVaoGioHangChiTiet();
        chuyenHangChoKhach(3, null);
        sapXep(this.resHD.getAll(""));
        clearDatHang();
        JOptionPane.showMessageDialog(this, "Giao hàng thàng công");
        loadTableSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        checkButton = 3;
    }//GEN-LAST:event_btnChuyenHangActionPerformed

    private void btnHuyDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDonHangActionPerformed
        if (IDHD == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể thực hiện");
            return;
        }

        //        if (checkButton != 3 && getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).
        //                equals("Đang giao hàng") == false) {
        //            JOptionPane.showMessageDialog(this, "Không thể xử lí");
        //            return;
        //        }
        // HUY DON HANG VAN CHUYEN
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy hóa đơn ?", "Huy", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        String lyDo = null;
        do {
            lyDo = JOptionPane.showInputDialog(this, "Vui lòng nhập lý do hủy");
            if (lyDo.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập lý do");
            }
        } while (lyDo.trim().length() == 0);

        if (IDHD == null) {
            IDHD = hdCho.getId();
        } else if (hdCho != null) {
            GioHang g = ((ChiTietGioHang) ctghCho.get(0)).getGh();
            g.setTinhtrang(2);
            this.resGH.updateGH(g);
        }
        //        themSanPhamVaoGioHangChiTiet();
        HoaDon hd = this.resHD.getAll(dk(" d.id ", IDHD)).get(0);
        hd.setLyDo(lyDo);
        hd.setTinhTrang(2);
        this.resHD.update(hd);

        sapXep(this.resHD.getAll(""));
        clearDatHang();
        loadTableSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        checkButton = 1;
    }//GEN-LAST:event_btnHuyDonHangActionPerformed

    private void tbnThemDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnThemDatHangActionPerformed
        new khachHangMini(1).setVisible(true);
        checkTimKiem = 1;
    }//GEN-LAST:event_tbnThemDatHangActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        System.out.println("panel 1");        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void tabbHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbHDMouseClicked
        clear();
        clearDatHang();
        loadTableSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        if (tabbHD.getSelectedIndex() == 1) {
            if (webcam != null) {
                webcam.close();
            }
        }
    }//GEN-LAST:event_tabbHDMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        if (webcam != null) {
//            webcam.close();
//        } else
        initWebcam();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
        tbHienThiSP.clearSelection();
        if (checkSP() == false) {
            return;
        }
        if (checkTrungSP_Ten(txtTen_SP.getText()) == false
                && checkTrungSP_tl(Float.valueOf(txtTrongLuong_SP.getText())) == false) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!");
            return;
        }

        int x = JOptionPane.showConfirmDialog(this, "Xác nhận thêm sản phẩm?", "Thông báo:", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.sanPhamSV.add(getDataSP("")) == 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                String data = xx;
                int width = 200;
                int height = 100;
                try {
                    // Tạo mã vạch
                    testBar barcode = new testBar();
                    BufferedImage image = barcode.createBarcode(data, width, height);

                    // Lưu ảnh mã vạch
                    File file = new File("D:\\pujic\\DU1\\src\\main\\resources\\QRCODE\\" + xx + ".png");
                    ImageIO.write(image, "png", file);
                    System.out.println(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                showDataSP();
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
        showDataSP();

    }//GEN-LAST:event_btAddSPActionPerformed

    private void btUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateSPActionPerformed
        // TODO add your handling code here:
        if (checkSP() == false) {
            return;
        }
        if (checkTrungSP_Ten(txtTen_SP.getText()) == false
                && checkTrungSP_tl(Float.valueOf(txtTrongLuong_SP.getText())) == false) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!");
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

    private void txtSearch_LocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch_LocKeyReleased
        // TODO add your handling code here:
        String ten = txtSearch_Loc.getText();
        List<SanPhamViewModel> sp = this.sanPhamSV.selectByTen(ten);
        selectByName(sp);
    }//GEN-LAST:event_txtSearch_LocKeyReleased

    private void cbbTrangThai_Loc1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTrangThai_Loc1ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            int tt = 0;
            if (cbbTrangThai_Loc1.getSelectedItem().toString().equals("Còn hàng")) {
                tt = 0;
                showDataSP(new SerSanPham().selectByTT((tt)));
            } else if (cbbTrangThai_Loc1.getSelectedItem().toString().equals("Hết hàng")) {
                tt = 1;
                showDataSP(new SerSanPham().selectByTT((tt)));
            } else {
                showDataSP();
            }

        }
    }//GEN-LAST:event_cbbTrangThai_Loc1ItemStateChanged

    private void cbbTrangThai_Loc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThai_Loc1ActionPerformed
        // TODO add your handling code here:
        //        String tt = cbbTrangThai_Loc1.getSelectedItem().toString();
        //        List<SanPhamViewModel> sp = this.sanPhamSV.selectByTT(Integer.valueOf(tt));
        //        searchByTT(sp);
    }//GEN-LAST:event_cbbTrangThai_Loc1ActionPerformed

    private void cbbTrangThai_Loc1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbTrangThai_Loc1KeyReleased
        // TODO add your handling code here:
        //        String ten = cbbDongSP_Loc1.getSelectedItem().toString();
        //        List<SanPhamViewModel> sp = this.sanPhamSV.selectByDongSP(ten);
        //        selectByNameDSP(sp);
    }//GEN-LAST:event_cbbTrangThai_Loc1KeyReleased

    private void cbbDongSP_Loc1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDongSP_Loc1ItemStateChanged
        if (evt.getStateChange() != ItemEvent.SELECTED) {
            if (cbbDongSP_Loc1.getSelectedIndex() == 0) {
                showDataSP();
            } else {
                showDataSP(new SerSanPham().selectByDongSP(((DanhMucViewModel) cbbDongSP_Loc1.getSelectedItem()).getDongSP()));
            }
        }
    }//GEN-LAST:event_cbbDongSP_Loc1ItemStateChanged

    private void cbbDongSP_Loc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDongSP_Loc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDongSP_Loc1ActionPerformed

    private void btnTao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTao1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Image"));
        fileChooser.showOpenDialog(this);
        File f = fileChooser.getSelectedFile();
        mt = f.getName();
        Image anh = new ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Image\\" + mt).getImage().getScaledInstance(lb_MoTa.getWidth(), lb_MoTa.getHeight(), 0);
        lb_MoTa.setIcon(new ImageIcon(anh));
    }//GEN-LAST:event_btnTao1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        showDataSP();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        HSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "D:\\pujic\\DU1\\src\\main\\resources\\Excel\\excelSanPham";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new HSSFWorkbook(excelBIS);
                HSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

                for (int row = 1; row < excelSheet.getLastRowNum(); row++) {
                    HSSFRow excelRow = excelSheet.getRow(row);

                    HSSFCell excelTen = excelRow.getCell(2);
                    HSSFCell excelMauSac = excelRow.getCell(3);
                    HSSFCell excelNSX = excelRow.getCell(4);
                    HSSFCell excelMoTa = excelRow.getCell(5);
                    HSSFCell excelGiaNhap = excelRow.getCell(6);
                    HSSFCell excelGiaBan = excelRow.getCell(7);
                    HSSFCell excelTrongLuong = excelRow.getCell(8);
                    HSSFCell excelSoLuog = excelRow.getCell(9);
                    HSSFCell excelKichThuoc = excelRow.getCell(10);
                    HSSFCell excelChatLieu = excelRow.getCell(11);
                    HSSFCell excelDongSP = excelRow.getCell(12);
                    HSSFCell excelTrangThai = excelRow.getCell(13);

                    //                    XSSFCell excelProgrammingLanguage = excelRow.getCell(2);
                    //                    XSSFCell excelSubject = excelRow.getCell(3);
                    //                    XSSFCell excelImage = excelRow.getCell(4);
                    //                    JLabel excelJL = new JLabel(new ImageIcon(new ImageIcon(excelImage.getStringCellValue()).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                    //                    dtmDM.addRow(new Object[]{excelName, excelGender, excelProgrammingLanguage, excelSubject, excelJL});
                    //                    dtmDM.addRow(new Object[]{excelName, excelGender});
                    String x = jcheck.randomMA();
                    String data = x;
                    int width = 200;
                    int height = 100;
                    try {
                        // Tạo mã vạch
                        testBar barcode = new testBar();
                        BufferedImage image = barcode.createBarcode(data, width, height);

                        // Lưu ảnh mã vạch
                        File file = new File("D:\\pujic\\DU1\\src\\main\\resources\\QRCODE\\" + x + ".png");
                        ImageIO.write(image, "png", file);
                        System.out.println(image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //                    System.out.println(Float.valueOf(excelGiaNhap.toString()));
                    //                    System.out.println(Float.valueOf(excelGiaBan.toString()));
                    //                    System.out.println(Float.valueOf(excelTrongLuong.toString()));
                    //                    System.out.println(Float.valueOf(excelKichThuoc.toString()));
                    //                    System.out.println(Integer.valueOf(excelTrangThai.toString()));

                    //Integer a = Integer.valueOf((int)(excelSoLuog.getNumericCellValue()));
                    //                    System.out.println(a instanceof Integer);
                    //JOptionPane.showMessageDialog(this, a.equals(Integer.parseInt("9")));
                    //int b = (int)(excelSoLuog.getNumericCellValue());
                    //// System.out.println(b instanceof int);
                    //JOptionPane.showMessageDialog(this, b + 1);
                    //break;
                    //                    System.out.println("xxxxxxxxxx: "+ Integer.valueOf((int)(excelSoLuog.getNumericCellValue())).equals(Integer.valueOf(9)));
                    DanhMucViewModel dm;
                    DanhMuc s = new resSanPham().timDM(excelDongSP.toString());
                    SanPham sp = new resSanPham().timSP(excelTen.toString());
                    if (s == null) {
                        dm = new DanhMucViewModel(jcheck.createID().toString(), excelDongSP.toString());
                    } else {
                        dm = new DanhMucViewModel(s.getId(), s.getDongSP());
                    }

                    if (sp != null) {
//                        JOptionPane.showMessageDialog(this, "xxxxxxxx");
                        continue;
                    }

                    this.sanPhamSV.add(new SanPhamViewModel(
                            jcheck.createID().toString(), x,
                            excelTen.toString(), excelMauSac.toString(),
                            excelNSX.toString(), excelMoTa.toString(),
                            Float.valueOf(excelGiaNhap.toString()),
                            Float.valueOf(excelGiaBan.toString()),
                            Float.valueOf(excelTrongLuong.toString()),
                            Integer.valueOf((int) (excelSoLuog.getNumericCellValue())),
                            Float.valueOf(excelKichThuoc.toString()),
                            excelChatLieu.toString(),
                            dm,
                            Integer.valueOf((int) (excelTrangThai.getNumericCellValue())),
                            //                            Integer.valueOf(excelTrangThai.toString()),
                            x + ".png"
                    ));

                    //new resSanPham().add(new SanPham(jcheck.createID().toString(), jcheck.randomMA(), Integer.valueOf((int)(excelSoLuog.getNumericCellValue()))));
                }
                showDataSP();
                showDataDM();
                JOptionPane.showMessageDialog(this, "Thành công!");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(this, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
                if (checkDM_Delete() == false) {
                    return;
                }
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo:", JOptionPane.INFORMATION_MESSAGE);
                danhMucSV.delete(tbHienThi_DSP.getValueAt(tbHienThi_DSP.getSelectedRow(), 0).toString());
                showDataDM();
                dcmChatLieu_SP.removeAllElements();
                dcmDongSP_SP.removeAllElements();
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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:

        //        dcmChatLieu_SP.removeAllElements();
        //        dcmDongSP_SP.removeAllElements();
        //        showCbbChatLieu();
        //        showCbbDongSP();
        //        showDataSP();
        //        fillSP(0);
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        try {

            fillFormKM(tbHienThi.getSelectedRow());
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void txtTimKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKMKeyReleased
        String ten = txtTimKM.getText();
        System.out.println(ten);
        List<KhuyenMai> list = khuyenMaiResponsitories.SelectbyName(ten);
        finTenKM(list);
    }//GEN-LAST:event_txtTimKMKeyReleased

    private void cbbLocTrangThaiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrangThaiKMActionPerformed
        String a = (String) cbbLocTrangThaiKM.getSelectedItem();
        //        System.out.println("1 :" + a);
        List<KhuyenMai> list = khuyenMaiResponsitories.SelectbyTrangThai(a);
        finTrangThai(list);

    }//GEN-LAST:event_cbbLocTrangThaiKMActionPerformed

    private void txtTenKhuyenMaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhuyenMaiFocusLost

    }//GEN-LAST:event_txtTenKhuyenMaiFocusLost

    private void dateNgayBDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayBDPropertyChange
        //        System.out.println("aaaaaaaa" + dateNgayBD.getDate());
    }//GEN-LAST:event_dateNgayBDPropertyChange

    private void btnTaoKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoKMActionPerformed
        tbHienThi.clearSelection();
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
        if (rdHetHan.isSelected() == true) {
            JOptionPane.showMessageDialog(this, "Không tạo khuyến mãi với trạng thái là hết hạn");
            return;
        }
        if (checkCalendar() == false) {
            return;
        }

        if (khuyenMaiResponsitories.add(getData(" ")) == 1) {
            JOptionPane.showMessageDialog(this, "thành công");
        }
        sapXepKhuyenMai(khuyenMaiResponsitories.getAllLoad());
        //        loadTableKM();
        clearKhuyenMai();
    }//GEN-LAST:event_btnTaoKMActionPerformed

    private void btnLamMoiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKMActionPerformed
        jcheck.clearView(jText, tbHienThi);
        dateNgayBD.setDate(null);
        dateNgayKT.setDate(null);
    }//GEN-LAST:event_btnLamMoiKMActionPerformed

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
            if (rdHetHan.isSelected() == true) {
                JOptionPane.showConfirmDialog(this, "Không được chuyển trạng thái của khuyến mãi sang hết hạn");
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
        clearKhuyenMai();
    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void tbHienThiSP1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiSP1MouseClicked
        int row = tbHienThiSP.getSelectedRow();
        try {
            fillFormSP(row);
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHienThiSP1MouseClicked

    private void txtTimSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSPKeyReleased
        String ten = txtTimSP.getText();
        System.out.println(ten);
        List<ChiTietKhuyenMai> list = chiTietKhuyenMaiResponsitories.SelectbyNameSP(ten);
        finTenSP(list);
    }//GEN-LAST:event_txtTimSPKeyReleased

    private void btnTaoSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoSPActionPerformed
        tbHienThiSP.clearSelection();

        //        if (addALL() == true) {
        //
        //            return;
        //
        //        } else {
        if (add() == true) {
            return;
        }

        loadTableSanPham();
        clearKhuyenMai();
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
        clearKhuyenMai();
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        clearKhuyenMai();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tbHienThiTTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiTTSPMouseClicked
        System.out.println("trạng thái" + tbHienThiTTSP.getValueAt(tbHienThiTTSP.getSelectedRow(), 4));
    }//GEN-LAST:event_tbHienThiTTSPMouseClicked

    private void txtTimKiemSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPKeyReleased
        String ten = txtTimKiemSP.getText();
        System.out.println(ten);
        List<SanPham> list = sanPham.SelectbyTen(ten);
        selectbyTen(list);
    }//GEN-LAST:event_txtTimKiemSPKeyReleased

    private void txtTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuKeyReleased
        Float ten = Float.parseFloat(txtTu.getText());
        Float ten1 = Float.parseFloat(txtDen.getText());

        System.out.println(ten);
        System.out.println(ten1);
        List<SanPham> list = sanPham.SelectbyGia(ten, ten1);
        finGia(list);
    }//GEN-LAST:event_txtTuKeyReleased

    private void txtDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenKeyReleased
        Float ten = Float.parseFloat(txtTu.getText());
        Float ten1 = Float.parseFloat(txtDen.getText());

        System.out.println(ten);
        System.out.println(ten1);
        List<SanPham> list = sanPham.SelectbyGia(ten, ten1);
        finGia(list);
    }//GEN-LAST:event_txtDenKeyReleased

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        //
        cbbKM.removeAllElements();
        //        cbbSanPham.removeAllElements();

        //        showDataSP();
        loadComBoKM();
        //        loadComBoSP();
    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void labelThemAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelThemAnhMouseClicked
        JFileChooser chonAnh = new JFileChooser("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Icon");
        chonAnh.showOpenDialog(this);
        File fileAnh = chonAnh.getSelectedFile();
        Image anh = new ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Icon\\" + fileAnh.getName()).getImage().getScaledInstance(labelThemAnh.getWidth(), panelThemAnh.getHeight(), 0);
        labelThemAnh.setIcon(new ImageIcon(anh));
        duongDanThuMucAnh = fileAnh.getName();
    }//GEN-LAST:event_labelThemAnhMouseClicked

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        tableHienThi.clearSelection();

        if (!checkMaNhanVien(txtMaNhanVien.getText())) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!");
            return;
        } else if (!checkCMND(txtCmnd.getText())) {
            JOptionPane.showMessageDialog(this, "Mã số căn cước công dân đã tồn tại!");
            return;
        } else if (!checkEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại!");
            return;
        } else if (!checkSdt(txtSdt.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
            return;
        } else if (!checkTenTaiKhoan(txtTenTaiKhoan.getText())) {
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại!");
            return;
        } else if (validateData()) {
            if (nhanVienService.them(getDataNhanVien("")) == 1) {
                return;
            }
        }

        clearNhanVien();

        loadTableNhanVien();
    }//GEN-LAST:event_buttonThemActionPerformed

    private void buttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatActionPerformed
        if (validateData()) {
            if (nhanVienService.capNhat(getDataNhanVien("capNhat")) == 1) {
                return;
            }
        }
        loadTableNhanVien();
        clearNhanVien();
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
            clearNhanVien();
            loadTableNhanVien();
        }
    }//GEN-LAST:event_buttonXoaActionPerformed

    private void tableHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHienThiMouseClicked
        int index = tableHienThi.getSelectedRow();
        Image image = new ImageIcon("D:\\pujic\\DU1\\src\\main\\resources\\icon\\Icon\\" + tableHienThi.getValueAt(index, 9).toString()).getImage().getScaledInstance(labelThemAnh.getWidth(), labelThemAnh.getHeight(), 0);

        txtMaNhanVien.setText((String) tableHienThi.getValueAt(index, 1));
        txtTenNhanVien.setText((String) tableHienThi.getValueAt(index, 2));
        txtCmnd.setText((String) tableHienThi.getValueAt(index, 3));
        if (tableHienThi.getValueAt(index, 4).equals("Nam")) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        dateNgaySinh.setDate((Date) tableHienThi.getValueAt(index, 5));
        txtDiaChi.setText((String) tableHienThi.getValueAt(index, 6));
        txtSdt.setText((String) tableHienThi.getValueAt(index, 7));
        txtEmail.setText((String) tableHienThi.getValueAt(index, 8));
        labelThemAnh.setIcon(new ImageIcon(image));
        duongDanThuMucAnh = tableHienThi.getValueAt(index, 9).toString();
        txtTenTaiKhoan.setText((String) tableHienThi.getValueAt(index, 10));
        txtMatKhau.setText((String) tableHienThi.getValueAt(index, 11));
        comboboxChucVu.setSelectedItem(tableHienThi.getValueAt(index, 12));
        comboboxTrangThai.setSelectedItem((String) tableHienThi.getValueAt(index, 13));
    }//GEN-LAST:event_tableHienThiMouseClicked

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        tbHienThi.clearSelection();
        if (txtTenKhachHang.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Tên KH đang trống");
            return;
        }
        if (txtSDT.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "SDT đang trống");
            return;
        }
        if (!txtSDT.getText().matches("0[0-9]{9}")) {
            JOptionPane.showMessageDialog(this, "SDT sai định dạng");
            return;
        }
        if (checkSDTKH(txtSDT.getText()) == false) {
            JOptionPane.showMessageDialog(this, "SDT đã tồn tại");
            return;
        }
        if (khachHangServices.add(getDataKhachHang("")) == 1) {
            JOptionPane.showMessageDialog(this, "Tạo thành công");
        }
        loadTableKhachHang();
        this.jcheck.clearView(jTextKhachhang, tbHienThi);
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (jcheck.checkClcick(tbHienThi, this) == false) {
            return;
        } else {
            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                khachHangServices.delete(tbHienThi1.getValueAt(tbHienThi1.getSelectedRow(), 0).toString());
                JOptionPane.showMessageDialog(this, "Xoá thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xoá thất bại");
            }
            loadTableKhachHang();
            this.jcheck.clearView(jTextKhachhang, tbHienThi);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (jcheck.checkClcick(tbHienThi1, this) == false) {
            return;
        } else {
            if (txtTenKhachHang.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Tên KH đang trống");
                return;
            }
            if (txtSDT.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "SDT đang trống");
                return;
            }
            if (txtSDT.getText().matches("{0}[0-9]{9}")) {
                JOptionPane.showMessageDialog(this, "SDT sai định dạng");
                return;
            }
            if (checkSDTKH(txtSDT.getText()) == false) {
                JOptionPane.showMessageDialog(this, "SDT đã tồn tại");
                return;
            }
            int x = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                khachHangServices.update(getDataKhachHang("update"));
                JOptionPane.showMessageDialog(this, "thành công");
            } else {
                JOptionPane.showMessageDialog(this, "thất bại");
            }
        }
        loadTableKhachHang();
        this.jcheck.clearView(jTextKhachhang, tbHienThi);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearKhachHang();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tbHienThi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThi1MouseClicked
        int row = tbHienThi1.getSelectedRow();
        try {
            fillFormKhachhang(row);
        } catch (ParseException ex) {
        }
    }//GEN-LAST:event_tbHienThi1MouseClicked

    private void txtTim1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTim1KeyReleased
        String ten = txtTim.getText();
        System.out.println(ten);
        List<KhachHang> listKH = this.khachHangServices.SelectbyName(ten);
        findMaKhachHang(listKH);
    }//GEN-LAST:event_txtTim1KeyReleased

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
        loadDTHT();
    }//GEN-LAST:event_btnhiendthientaiActionPerformed

    private void TbltheongayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbltheongayMouseClicked
        //        int row = Tbltheongay.getSelectedRow();
        //        if (cbbthoigian.getSelectedIndex() == 0) {
        //            lblbihuy.setText(((scthd.select_doanhthu_theongayBH(dateTK_doanhthu.getDate())))+ "");
        //            lblthanhcong2.setText(Tbltheongay.getValueAt(row, 0).toString());
        //            lbldtngay.setText(Tbltheongay.getValueAt(row, 1).toString());
        //            lblKH.setText(Tbltheongay.getValueAt(row, 2).toString());
        //            lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
        //        }

        //else if (cbbthoigian.getSelectedIndex() == 1) {
        //            lblbihuy.setText(((scthd.select_doanhthu_theothangBH(Integer.parseInt((String) cbbthang.getSelectedItem()), Integer.parseInt(txtnam.getText())))) + "");
        //            lblthanhcong2.setText(Tbltheongay.getValueAt(row, 0).toString());
        //            lbldtthang.setText(Tbltheongay.getValueAt(row, 1).toString());
        //            lblKH.setText(Tbltheongay.getValueAt(row, 2).toString());
        //            lblSoDH2.setText(Integer.parseInt(lblthanhcong2.getText()) + Integer.parseInt(lblbihuy.getText()) + "");
        //        }
    }//GEN-LAST:event_TbltheongayMouseClicked

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
            try {
                findNgay(list);
                loadDTNgay();
            } catch (ParseException ex) {
                Logger.getLogger(ThongKeView.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(list.size());
        }
    }//GEN-LAST:event_dateTK_doanhthuPropertyChange

    private void btntimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntimMouseClicked
        if (cbbthang.getSelectedItem() != null && txtnam.getText() != null) {
            List<ChiTietHoaDon> list = this.scthd.select_doanhthu_theothangTC(Integer.parseInt((String) cbbthang.getSelectedItem()), Integer.parseInt(txtnam.getText()));
            findThang(list);
            System.out.println(list);
            System.out.println(list.size());
            loadDTThang();
        }
    }//GEN-LAST:event_btntimMouseClicked

    private void jTabbedPane9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane9MouseClicked

    private void date_SPNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_SPNgayMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_date_SPNgayMouseClicked

    private void date_SPNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_SPNgayPropertyChange
        if (date_SPNgay.getDate() != null) {
            List<ChiTietHoaDon> kh = scthd.SelectbySPNgay(date_SPNgay.getDate());
            findSPNgay(kh);
        }
    }//GEN-LAST:event_date_SPNgayPropertyChange

    private void txtSPNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSPNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSPNamActionPerformed

    private void btnthangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthangMouseClicked
        List<ChiTietHoaDon> kh = scthd.SelectbySPThang(Integer.parseInt((String) cbbSPthang.getSelectedItem()), Integer.parseInt(txtSPNam.getText()));
        findSPThang(kh);
        System.out.println(kh.size());
    }//GEN-LAST:event_btnthangMouseClicked

    private void btnthangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthangActionPerformed

    private void btnhienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhienActionPerformed
        List<ChiTietHoaDon> list = scthd.SelectTOP(Integer.parseInt(txtn.getText()));
        findTOP(list);
        System.out.println(list);
    }//GEN-LAST:event_btnhienActionPerformed

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

    private void tbHienThi2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThi2MouseClicked

        int row = tbHienThi2.getSelectedRow();

        //        ChiTietBaoHanh c = getDataCTBH("");
        if (row == 0) {
            if (checkThoiHan() == false) {
                return;
            }
            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn bảo hành không", "Thông Báo", JOptionPane.YES_NO_OPTION);

            if (a == JOptionPane.YES_OPTION) {

                List<Object> lisst = resHoaDonCho.getAllSPHDNoEm(tbHienThi2.getValueAt(tbHienThi2.getSelectedRow(), 0).toString(), 0);
                new ChiTietBH(lisst).setVisible(true);

            }

            ////                JOptionPane.showConfirmDialog(this, c.getSoLanBH());
            //
            //        dtm = (DefaultTableModel) tb.getModel();
            //        dtm.setRowCount(0);
            //        for (HoaDon object : resHoaDon.getAll("")) {
            //            dtm.addRow(object.toRowhd());
            //        }
            //
            //    }
            //    }
            //                chiTietBaoHanhRes.add(c);
            //                loadTableCTBH();
            //                System.out.println("ok la");
        }
    }//GEN-LAST:event_tbHienThi2MouseClicked

    private void btnTim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim2ActionPerformed
        String a = txtTim2.getText();
        List<domaimodel.HoaDon> list1 = resHoaDon.SelectbyMaHD(a);
        
            loadTableHD(list1);
        
    }//GEN-LAST:event_btnTim2ActionPerformed

    private void tbhHienThiCTBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhHienThiCTBHMouseClicked
        int a1 = tbhHienThiCTBH.getSelectedRow();
        try {
            fillFormBH(a1);
            txtSL.setEnabled(false);
        } catch (ParseException ex) {
            Logger.getLogger(BaoHanhView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbhHienThiCTBHMouseClicked

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        if (jcheck.checkClcick(tbhHienThiCTBH, this) == false) {
            return;
        } else {
            if (checkCalendarBH() == false) {
                return;
            }
            int sl = Integer.parseInt(ChiTietBH.tbHienSP.getValueAt(ChiTietBH.tbHienSP.getSelectedRow(), 2).toString());
            if (Integer.parseInt(txtSL.getText()) >= sl) {
                JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng đã mua");
                return;
            }
            int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?", "Update", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                baoHanhRes.update(getDataCTBH("update"));
                System.out.println("ok la");
                JOptionPane.showMessageDialog(this, "Sửa thành công");

            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");

            }
        }
        loadTableCTBH();
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void dateNgayTraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayTraPropertyChange
        //        System.out.println("aaaaaaaa" + dateNgayBD.getDate());
    }//GEN-LAST:event_dateNgayTraPropertyChange

    private void dateNgayBHPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayBHPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNgayBHPropertyChange

    private void txtNSX_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNSX_SPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNSX_SPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnThemSanPham2 = 0;
        btnThemSanPham = 10;

        //  MUA HANG
        if (tabbHD.getSelectedIndex() == 0) {
            //        CHON SAN PHAM VAO GIO HANG
            if (chonSanPhamVipPro() == null) {
                return;
            }

            //        THEM SAN PHAM VAO GIO HANG CHI TIET
            //            themSanPhamVaoGioHangChiTiet();
            //         HIEN THI SAN PHAM THANH TOAN
            sanPhamHienThiOHoaDon();

            //  DAT HANG
        } else {
            //        CHON SAN PHAM VAO GIO HANG
            if (chonSanPhamVipPro() == null) {
                return;
            }
            //        THEM SAN PHAM VAO GIO HANG CHI TIET
            //            themSanPhamVaoGioHangChiTiet();

            //         HIEN THI SAN PHAM THANH TOAN TRONG DAT HANG
            themSPVaoDatHang();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        //        btnThem1.setEnabled(false);
        //        btnThem1.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseExited

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        //        btnThem.setEnabled(true);
        //        btnThem.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        //        btnThem.setEnabled(true);
        //        btnThem.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseClicked

    private void TienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TienMouseClicked
        loadTableSPVipPro(countLoadTableVipPro + 2, countLoadTableVipPro);
        capNhapSLSPChuyenMan();
    }//GEN-LAST:event_TienMouseClicked

    private void LuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiMouseClicked
        int a;
        if (!sanphamList.isEmpty()) {
            if (sanphamList.get(0).getMa().equals(this.resSP.getAll(dk(" trangThai ", "0")).get(0).getMa())) {
                countLoadTableVipPro = 0;
            }
        }

        a = countLoadTableVipPro - 4;

        loadTableGiamSPVipPro(countLoadTableVipPro - 2, a);
        capNhapSLSPChuyenMan();
    }//GEN-LAST:event_LuiMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        timKiemSanPham = 0;
        countLoadTableVipPro = 0;
        sanphamList.removeAll(sanphamList);
        loadTableSPVipPro(2, 0);
        jpaneAnh2.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        listSPSearList = resSP.SelectbyTen(txtTim.getText().trim());
        loadSLSanPhamPanelDanhChoTimKiem(listSPSearList, 2, 0);
        countLoadTableVipPro = 0;
//        sanphamList = new ArrayList<>();
//        sanphamList.add(e)
        timKiemSanPham = 72;
    }//GEN-LAST:event_txtTimKeyReleased

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed

    }//GEN-LAST:event_txtTimActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        btnThemSanPham = 0;
        btnThemSanPham2 = 12;

        //  MUA HANG
        if (tabbHD.getSelectedIndex() == 0) {
            //        CHON SAN PHAM VAO GIO HANG
            if (chonSanPhamVipPro() == null) {
                return;
            }

            //        THEM SAN PHAM VAO GIO HANG CHI TIET
            //            themSanPhamVaoGioHangChiTiet();
            //         HIEN THI SAN PHAM THANH TOAN
            sanPhamHienThiOHoaDon();

            //  DAT HANG
        } else {
            //        CHON SAN PHAM VAO GIO HANG
            if (chonSanPhamVipPro() == null) {
                return;
            }
            //        THEM SAN PHAM VAO GIO HANG CHI TIET
            //            themSanPhamVaoGioHangChiTiet();

            //         HIEN THI SAN PHAM THANH TOAN TRONG DAT HANG
            themSPVaoDatHang();
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThem1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseExited

        //        btnThem.setVisible(btnThem.setEnabled(false);false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseExited

    private void btnThem1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseEntered
        //        btnThem1.setVisible(true);
        //        btnThem1.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseEntered

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
        if (webcam != null) {
            webcam.close();
        }
        new Doimk().setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (jTabbedPane8.getSelectedIndex() == 0) {
            Excel e = new Excel();
            e.setVisible(true);
        } else {
            Excelbdsp e2 = new Excelbdsp();
            e2.setVisible(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void panelAnh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAnh1MouseClicked
        if (evt.getClickCount() == 2) {
            ChiTietKhuyenMai kmLABEL;
            if (!this.ctkm.getALLJoin1(sanphamList.get(0).getMa()).isEmpty()) {
                kmLABEL = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(sanphamList.get(0).
                        getMa()).get(0));
                new ThongTinKhuyenMai(this, true, kmLABEL).setVisible(true);
            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnh1MouseClicked

    private void jTabbedPane8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane8MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        if (evt.getClickCount() == 2) {
            ChiTietKhuyenMai kmLABEL;
            if (!this.ctkm.getALLJoin1(sanphamList.get(1).getMa()).isEmpty()) {
                kmLABEL = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(sanphamList.get(0).
                        getMa()).get(0));
                new ThongTinKhuyenMai(this, true, kmLABEL).setVisible(true);
            }

        }// TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseClicked

    private void txtTenShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenShipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenShipActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (evt.getClickCount() == 2) {
            if (webcam != null) {
                webcam.close();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

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
            java.util.logging.Logger.getLogger(ViewTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CV;
    private javax.swing.JInternalFrame FrameBanHang;
    private javax.swing.JLabel Lui;
    private javax.swing.JTabbedPane TabeBanHang;
    private javax.swing.JTable Tbltheongay;
    private javax.swing.JLabel Tien;
    private javax.swing.JLabel anhNV;
    private javax.swing.JButton btAddDSP;
    private javax.swing.JButton btAddSP;
    private javax.swing.JButton btClearDSP;
    private javax.swing.JButton btClearSP;
    private javax.swing.JButton btDeleteDelete;
    private javax.swing.JButton btUpdateDSP;
    private javax.swing.JButton btUpdateSP;
    private javax.swing.JButton btmthongke;
    private javax.swing.JButton btnChuyenHang;
    private javax.swing.JButton btnHoangThanh;
    private javax.swing.JButton btnHuyDonHang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoiKM;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnTao1;
    private javax.swing.JButton btnTaoKM;
    private javax.swing.JButton btnTaoSP;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTim1;
    private javax.swing.JButton btnTim2;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btndx;
    private javax.swing.JButton btndx1;
    private javax.swing.JButton btnhien;
    private javax.swing.JButton btnhiendthientai;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnkhoangngay;
    private javax.swing.JButton btnkhuyenmai;
    private javax.swing.JButton btnlichsu;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnsanpham;
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btnthang;
    private javax.swing.JButton btntim;
    private javax.swing.JButton btntt;
    private javax.swing.JButton btnxoasp;
    private javax.swing.JButton btnxoatatca;
    private javax.swing.JButton buttonCapNhat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JComboBox<String> cbbDongSP_Loc1;
    private javax.swing.JComboBox<String> cbbDongSP_SP;
    private javax.swing.JComboBox<String> cbbLocTrangThaiKM;
    private javax.swing.JComboBox<String> cbbSPthang;
    private javax.swing.JComboBox<String> cbbSuaChua;
    private javax.swing.JComboBox<String> cbbTenKM;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JComboBox<String> cbbTrangThai_Loc1;
    private javax.swing.JComboBox<String> cbbccngay;
    private javax.swing.JComboBox<String> cbbhinhthuctt;
    private javax.swing.JComboBox<String> cbbltg;
    private javax.swing.JComboBox<String> cbbthang;
    private javax.swing.JComboBox<String> cbbthoigian;
    private javax.swing.JComboBox<String> cbbtrangthai;
    private javax.swing.JComboBox<String> comboboxChucVu;
    private javax.swing.JComboBox<String> comboboxTrangThai;
    private com.toedter.calendar.JDateChooser dateNgayBD;
    private com.toedter.calendar.JDateChooser dateNgayBH;
    private com.toedter.calendar.JDateChooser dateNgayKT;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private com.toedter.calendar.JDateChooser dateNgaySinh1;
    private com.toedter.calendar.JDateChooser dateNgayTra;
    private com.toedter.calendar.JDateChooser dateTK_doanhthu;
    private com.toedter.calendar.JDateChooser date_SPNgay;
    private com.toedter.calendar.JDateChooser datengayKT;
    private com.toedter.calendar.JDateChooser datetngayBD;
    private javax.swing.JInternalFrame frameSanPham;
    private javax.swing.JLabel giaGiam2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
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
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane11;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTabbedPane jTabbedPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JInternalFrame jfarmeLichSu;
    private javax.swing.JInternalFrame jframeKhachHang;
    private javax.swing.JInternalFrame jframeKhuyenMai;
    private javax.swing.JInternalFrame jframeNhanVien;
    private javax.swing.JInternalFrame jframeThongKe;
    private javax.swing.JPanel jpaneAnh2;
    private javax.swing.JLabel labelThemAnh;
    private javax.swing.JLabel lable;
    private javax.swing.JLabel lbID_DSP;
    private javax.swing.JLabel lbMaBH;
    private javax.swing.JLabel lbMa_SP;
    private javax.swing.JLabel lbQR_SP;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTenKH;
    private javax.swing.JLabel lbTenSP;
    private javax.swing.JLabel lb_MoTa;
    private javax.swing.JLabel lblKH;
    private javax.swing.JLabel lblSoDH2;
    private javax.swing.JLabel lbl_Chay;
    private javax.swing.JLabel lblbihuy;
    private javax.swing.JLabel lbldtngay;
    private javax.swing.JLabel lbldtthang;
    private javax.swing.JLabel lblthanhcong2;
    private javax.swing.JPanel paneHientai;
    private javax.swing.JPanel paneTT;
    private javax.swing.JPanel paneTong;
    private javax.swing.JPanel panekhoangngay;
    private javax.swing.JPanel panelAnh1;
    private javax.swing.JPanel panelSPThang;
    private javax.swing.JPanel panelSPtheongay;
    private javax.swing.JPanel panelThemAnh;
    private javax.swing.JPanel panemthang;
    private javax.swing.JPanel panetngay;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JRadioButton rdConHan;
    private javax.swing.JRadioButton rdConHang;
    private javax.swing.JRadioButton rdHetHan;
    private javax.swing.JRadioButton rdHetHang;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JRadioButton rdochuatt;
    private javax.swing.JRadioButton rdodatt;
    private javax.swing.JTextField result_field;
    private javax.swing.JTabbedPane tabBaoHanh;
    private javax.swing.JTabbedPane tabKhachHang;
    private javax.swing.JTabbedPane tabKhuyenmai;
    private javax.swing.JTabbedPane tabLichSU;
    private javax.swing.JTabbedPane tabNhanVien;
    private javax.swing.JTabbedPane tabSanPham;
    private javax.swing.JTabbedPane tabThongKe;
    private javax.swing.JTabbedPane tabbHD;
    private javax.swing.JTable tableHienThi;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbHienThi1;
    private javax.swing.JTable tbHienThi2;
    private javax.swing.JTable tbHienThiSP;
    private javax.swing.JTable tbHienThiSP1;
    private javax.swing.JTable tbHienThiTTSP;
    private javax.swing.JTable tbHienThi_DSP;
    public static javax.swing.JTable tbhHienThiCTBH;
    private javax.swing.JTable tblSP_H;
    private javax.swing.JTable tblTONGHOP;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tbnHD;
    private javax.swing.JButton tbnThemDatHang;
    private javax.swing.JLabel tenNV;
    private javax.swing.JTextArea txtAMoTa;
    private javax.swing.JTextArea txtAMoTaKM;
    private javax.swing.JLabel txtAnhSP;
    private javax.swing.JLabel txtAnhSP1;
    private javax.swing.JTextField txtChatLieu_SP;
    private javax.swing.JTextField txtCmnd;
    private javax.swing.JTextField txtDShip;
    private javax.swing.JTextField txtDen;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDongSP_DSP;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGiaBan_SP;
    private javax.swing.JTextField txtGiaGiam;
    private javax.swing.JTextField txtGiaNhap_SP;
    private javax.swing.JLabel txtGiaPRO;
    private javax.swing.JLabel txtGiaPRO1;
    private javax.swing.JLabel txtGiaPRO2;
    private javax.swing.JLabel txtGiaPRO3;
    private javax.swing.JTextField txtKichThuoc_SP;
    private javax.swing.JLabel txtMPRO;
    private javax.swing.JLabel txtMPRO1;
    private javax.swing.JLabel txtMPRO2;
    private javax.swing.JLabel txtMPRO3;
    private javax.swing.JLabel txtMPRO4;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtMauSac_SP;
    private javax.swing.JTextField txtNSX_SP;
    private javax.swing.JTextField txtPVC;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTShip;
    private javax.swing.JTextField txtSL;
    private javax.swing.JLabel txtSLPRO;
    private javax.swing.JLabel txtSLPRO1;
    private javax.swing.JLabel txtSLPRO2;
    private javax.swing.JLabel txtSLPRO3;
    private javax.swing.JTextField txtSPNam;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearch_Loc;
    private javax.swing.JTextField txtSoLuong_SP;
    private javax.swing.JLabel txtTT;
    private javax.swing.JLabel txtTT1;
    private javax.swing.JLabel txtTT2;
    private javax.swing.JLabel txtTT3;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenShip;
    private javax.swing.JLabel txtTenSp;
    private javax.swing.JLabel txtTenSp1;
    private javax.swing.JLabel txtTenSp2;
    private javax.swing.JLabel txtTenSp3;
    private javax.swing.JTextField txtTenTaiKhoan;
    private javax.swing.JTextField txtTen_SP;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTim1;
    private javax.swing.JTextField txtTim2;
    private javax.swing.JTextField txtTimKM;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JTextField txtTrongLuong_SP;
    private javax.swing.JTextField txtTu;
    private javax.swing.JTextArea txtaDiaChi;
    private javax.swing.JTextField txtdg;
    private javax.swing.JTextField txtdg1;
    private javax.swing.JTextField txtgiamgia;
    private javax.swing.JTextField txtgiamgia1;
    private javax.swing.JTextField txtn;
    private javax.swing.JTextField txtnam;
    private javax.swing.JTextField txtsl;
    private javax.swing.JTextField txtsl1;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JTextField txttienthua;
    private javax.swing.JTextField txttongtien;
    private javax.swing.JTextField txttongtien1;
    // End of variables declaration//GEN-END:variables

    @Override
    public int compare(HoaDon o1, HoaDon o2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
