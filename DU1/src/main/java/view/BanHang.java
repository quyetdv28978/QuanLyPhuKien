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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.bridj.cpp.com.OLEAutomationLibrary;
import respon.ChiTietKhuyenMaiResponsitories;
import respon.KhachHangResponsitories;
import utility.JframeCheck;

/**
 *
 * @author HP
 */
public class BanHang extends javax.swing.JFrame implements Runnable, ThreadFactory, Comparator<HoaDon> {

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
    private String IDGH, IDHD;
    private HoaDon hdCho = null;
    private List<Object> ctghCho = null;

    private Set<String> tenSP = new HashSet<>();
    private ResGioHangCT resGH = new ResGioHangCT();
    private Map<String, StringBuilder> mapSP = new HashMap<>();

    private JframeCheck jcheck = new JframeCheck();
    private List<Object> jtext = new ArrayList<>();
    private List<Object> jtexgDathang = new ArrayList<>();
    private DefaultTableModel dtm;

    public static BanHang banHang = new BanHang();
    private int checkButton;

    private List<HoaDon> listHD;
    private List<SanPham> sanphamList;

    List<SanPham> listSPUltra = this.resSP.getAll(dk(" trangThai ", "0"));

    public BanHang(String ma) {
        initComponents();
        loadTableSP();
//        initWebcam();
        loadTableHoaDon(this.resHD.getAll(""));
        txttenkh.setText(ma);
    }

    public BanHang() {
        initComponents();
        loadTableSP();
        initWebcam();
        sapXep(this.resHD.getAll(""));
        jtext.add(txttienkhachdua);
        jtext.add(txttienthua);

        jtexgDathang.add(txtTenShip);
        jtexgDathang.add(txtSDTShip);
        jtexgDathang.add(txtDShip);
        btnThem.setVisible(false);
        loadTableSPVipPro(2, 0);
//        addJPANEL();
        btnThem.setVisible(false);
        btnThem1.setVisible(false);
        tbltimtheoma.setVisible(false);
    }

    private void setIcon(String dd, JLabel j) {
//        Image anh = new ImageIcon(dd).getImage().getScaledInstance(j.getWidth(), j.getHeight(), Image.SCALE_SMOOTH);
//        j.setIcon(new ImageIcon(anh));
        ImageIcon icon = new ImageIcon(dd);
        Image scaledImage = icon.getImage().getScaledInstance(j.getWidth(), j.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

// đặt kích thước cho JLabel theo kích thước của ảnh mới
        j.setPreferredSize(new Dimension(scaledIcon.getIconWidth(), scaledIcon.getIconHeight()));
        j.setIcon(scaledIcon);
    }

    int countLoadTableVipPro = 0;

    private void loadTableSPVipPro(int dkCong, int stt) {
        sanphamList = new ArrayList<>();
        int sp1 = 0;
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
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
                    } else
                        txtTT.setText("Hết hàng");
                } else {
                    setIcon(listSP.get(stt).getMoTa(), txtAnhSP1);
                    txtTenSp2.setText(listSP.get(stt).getTenSanPham());
                    txtSLPRO2.setText(listSP.get(stt).getSoLuong().toString());
                    txtGiaPRO2.setText(listSP.get(stt).getGiaBan().toString());
                    txtMPRO2.setText(listSP.get(stt).getMa());
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                    if (listSP.get(stt).getTrangThai() == 0) {
                        txtTT2.setText("Còn hàng");
                    } else
                        txtTT2.setText("Hết hàng");
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
    }

// XUAT FILE WORD
    private void xuatFileWord(List<SanPham> sp, HoaDon cthd, int[] soLuong, float tongTien, float[] giamGia) throws FileNotFoundException, IOException, InvalidFormatException {
        try {
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph paragraph4 = document.createParagraph();

// Tieu de hoa don
            XWPFRun run4 = paragraph4.createRun();

            String imgFile = "icon\\Icon\\images.jpg";

            byte[] imgBytes = IOUtils.toByteArray(new FileInputStream(imgFile));

            int pictureType = document.PICTURE_TYPE_JPEG; // Loại ảnh
            String pictureData = document.addPictureData(imgBytes, pictureType); // Thêm dữ liệu ảnh
            run4.addPicture(new ByteArrayInputStream(imgBytes), pictureType, "image.jpg", Units.toEMU(30), Units.toEMU(30)); // Thêm ảnh vào đối tượng XWPFRun
//  Thogn tin cua hang
            run4.addBreak();
            run4.setText("Cửa hàng bán phụ kiện trang sức nữ - Chi nhánh Nhom_1");
            run4.setBold(true);
            run4.addBreak();
            run4.setText("Địa chỉ: Tòa nhà FPT Polytechnic, P. Trịnh Văn Bô, Xuân Phương, Nam Từ Liêm, Hà Nội");
            XWPFParagraph paragraph5 = document.createParagraph();
            XWPFRun run5 = paragraph5.createRun();
            run5.setText("------------------------------------------------------------------------------------------------------------------------------");
            paragraph5.setAlignment(ParagraphAlignment.CENTER);
// Tạo một đối tượng XWPFParagraph mới
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            XWPFParagraph paragraph6 = document.createParagraph();
            XWPFRun run6 = paragraph6.createRun();
            run.setText(
                    "------------------------HÓA ĐƠN BÁN HÀNG-----------------------");
            run.setFontSize(20);
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            LocalDate c = LocalDate.now();
            run6.setText(
                    "Ngày " + c.getDayOfMonth() + " tháng " + c.getMonthValue() + " năm " + c.getYear());
            paragraph6.setAlignment(ParagraphAlignment.CENTER);
            paragraph.setAlignment(ParagraphAlignment.CENTER);

//        Thong tin kh
            XWPFParagraph x2 = document.createParagraph();
            XWPFRun r2 = x2.createRun();

            r2.setText(
                    "Họ tên khách hàng: " + cthd.getKh().getTen());
            r2.addBreak();

            r2.addBreak();

            r2.setText(
                    "Địa chỉ: " + cthd.getKh().getDiaChi());
            r2.addBreak();

            r2.addBreak();

            r2.setText(
                    "Số điện thoại: " + cthd.getKh().getSdt());
            r2.addBreak();

            r2.addBreak();

            r2.setText(
                    "Hình thức thanh toán: " + cthd.getHinhthucthanhtoan());
// TAO BANG SAN PHAM
            XWPFTable table = document.createTable(sp.size() + 2, 7);
            table.getRow(
                    0).getCell(0).setText("STT");
            table.getRow(
                    0).getCell(1).setText("Tên sản phẩm");
            table.getRow(
                    0).getCell(2).setText("Số lượng (Trọng lượng)");
            table.getRow(
                    0).getCell(3).setText("Loại sản phẩm");
            table.getRow(
                    0).getCell(4).setText("Giảm giá");

            table.getRow(
                    0).getCell(5).setText("Đơn giá");
            JOptionPane.showMessageDialog(this, soLuong.length);
//        String column[] = new String[]{"Tên sản phẩm", "Số lượng (Trọng lượng)", "Loại sản phẩm", "Đơn giá"};
            for (int j = 1; j < sp.size() + 1; j++) {
                int a = j - 1;
                table.getRow(j).getCell(1).setText(sp.get(a).getTenSanPham());
                table.getRow(j).getCell(2).setText(soLuong[a]
                        + "( " + sp.get(a).getTrongLuong() + " )");
                table.getRow(j).getCell(3).setText(sp.get(a).getDm().getDongSP());
                table.getRow(j).getCell(4).setText(giamGia[a] + "");
                table.getRow(j).getCell(5).setText(sp.get(a).getGiaBan() + "");
            }

            table.getRow(
                    sp.size() + 1).getCell(4).setText("Thành tiền");
            table.getRow(
                    sp.size() + 1).getCell(5).setText(tongTien + "");
            String file = "word\\banhang" + new Random().nextLong(100000) + ".docx";
            FileOutputStream out = new FileOutputStream(file);
            document.write(out);

        } catch (ParseException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableGiamSPVipPro(int dkCong, int stt) {
        sanphamList = new ArrayList<>();
        dkCong--;
        int sp1 = 0;
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
        if (listSP != null) {

//            if (dkCong > listSP.size()) {
//                dkCong = listSP.size();
//            }
            if (stt < 0) {

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
                    } else
                        txtTT.setText("Hết hàng");

//  SET NULL CHO PANLE 
                    txtAnhSP1.setIcon(null);
                    txtTenSp2.setText("");
                    txtSLPRO2.setText("");
                    txtGiaPRO2.setText("");
                    txtMPRO2.setText("");
//                sanPhamView.getTrangThai() == 0 ? "Còn hàng" : "Hết hàng",
                    txtTT2.setText("");
                    jpaneAnh2.setVisible(false);
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
                    } else
                        txtTT2.setText("Hết hàng");
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
                    } else
                        txtTT.setText("Hết hàng");
                }
                sanphamList.add(listSP.get(dkCong));
                sp1 = 1;
                countLoadTableVipPro--;
            }
            Collections.reverse(sanphamList);
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

    public void getDateDatHang(KhachHang data) {
        txtTenShip.setText(data.getTen());
        txtSDTShip.setText(data.getSdt());
        txtDShip.setText(data.getDiaChi());
        khachHangMiNi = data;
    }

    public void getData(KhachHang data) {
        txttenkh.setText(data.getTen());
        khachHangMiNi = data;
//        setLocationRelativeTo(null);
    }

    private void loadTableSP() {
        if (this.resSP.getAll(dk(" trangThai ", "0")) != null) {
            DefaultTableModel dtm = (DefaultTableModel) tbltimtheoma.getModel();
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
                    } else
                        return false;
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
        tbltimtheoma.clearSelection();
        count = 0;

        checkButton = 10;
        checkTimKiem = 0;

        IDHD = null;
        IDGH = null;

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
    }

// CLEAR DU LIEU VE TU DAU "BAN HANG"
    private void clear() {
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        ctghCho = null;
        hdCho = null;
        a = 0;
        tblgiohang.clearSelection();
        tbnHD.clearSelection();
        tbltimtheoma.clearSelection();
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
            tbltimtheoma.getValueAt(tbltimtheoma.getSelectedRow(), 0).toString(), tenSP_SanPham, soLuong
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

        if (soLuong > Integer.parseInt(txtSLPRO2.getText().trim()) && btnThemSanPham2 != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
            return null;
        } else if (soLuong < 0 && btnThemSanPham2 != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            return null;
        }

// CAP NHAP SO LUONG SAN PHAM TRONG TABLE   
        jtextfell.setText(Integer.parseInt(jtextfell.getText().trim()) - soLuong + "");
//        tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) - soLuong, tbltimtheoma.getSelectedRow(), 5);

        if (Integer.parseInt(txtSLPRO.getText().trim()) == 0) {
            txtTT.setText("Hết hàng");
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
            if (a == 0) {
                dtm = (DefaultTableModel) tblgiohang.getModel();
                dtm.setRowCount(0);
                a = 1;
            }
        }

//        System.out.println(txtSLPRO.getText().trim());
        if (Integer.parseInt(txtSLPRO.getText().trim()) == 0 && btnThemSanPham != 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng vui lòng chọn sản phẩm khác");
            return null;
        } else if (Integer.parseInt(txtSLPRO2.getText().trim()) == 0 && btnThemSanPham2 != 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng vui lòng chọn sản phẩm khác");
            return null;
        }

        String so = JOptionPane.showInputDialog(this, "Mời nhập số lượng");
        if (so == null) {
            return null;
        }
//  KIEM TRA SAN PHAM XEM CLICK VAO BUTTON NAO NET 1 THI BUTTON = 10, 2 THI BUTTON = 12      
        SanPham sp = null;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        jpanel45 = new javax.swing.JPanel();
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
        cbbtrangthai = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
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
        Tien = new javax.swing.JLabel();
        Lui = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtAnhSP = new javax.swing.JLabel();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltimtheoma = new javax.swing.JTable();
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
        jLabel7 = new javax.swing.JLabel();
        txtMPRO = new javax.swing.JLabel();
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
        getContentPane().add(jLabel3, java.awt.BorderLayout.CENTER);

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelAnh1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelAnh1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelAnh1MouseMoved(evt);
            }
        });
        panelAnh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAnh1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAnh1MouseExited(evt);
            }
        });
        panelAnh1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelAnh1ComponentResized(evt);
            }
        });

        txtMPRO1.setText("Mã:");

        txtTenSp1.setText("Tên:");

        txtSLPRO1.setText("Số lượng:");

        txtGiaPRO1.setText("Giá:");

        txtTT1.setText("Trạng thái:");

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
                        .addComponent(txtSLPRO1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(txtSLPRO, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAnh1Layout.createSequentialGroup()
                        .addComponent(txtTenSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAnh1Layout.createSequentialGroup()
                        .addComponent(txtMPRO1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMPRO4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAnh1Layout.createSequentialGroup()
                        .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaPRO1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaPRO, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAnh1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(42, 42, 42))
        );
        panelAnh1Layout.setVerticalGroup(
            panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnh1Layout.createSequentialGroup()
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMPRO1)
                    .addComponent(txtMPRO4))
                .addGap(9, 9, 9)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSLPRO1)
                    .addComponent(txtSLPRO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaPRO1)
                    .addComponent(txtGiaPRO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAnh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTT)
                    .addComponent(txtTT1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tien.setText("Tien");
        Tien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TienMouseClicked(evt);
            }
        });

        Lui.setText("Lui");
        Lui.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Lui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LuiMouseClicked(evt);
            }
        });

        txtAnhSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAnhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtAnhSPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtAnhSPMouseExited(evt);
            }
        });
        txtAnhSP.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                txtAnhSPComponentResized(evt);
            }
        });

        txtAnhSP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAnhSP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtAnhSP1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtAnhSP1MouseExited(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel13MouseMoved(evt);
            }
        });
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel13MouseExited(evt);
            }
        });

        txtMPRO3.setText("Mã:");

        txtTenSp3.setText("Tên:");

        txtSLPRO3.setText("Số lượng:");

        txtGiaPRO3.setText("Giá:");

        txtTT3.setText("Trạng thái:");

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

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiaPRO3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSLPRO2)
                                    .addComponent(txtGiaPRO2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(txtSLPRO3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(65, 65, 65)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addComponent(txtMPRO3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMPRO2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addComponent(txtTenSp3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenSp2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem1)
                .addGap(43, 43, 43))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMPRO3)
                    .addComponent(txtMPRO2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSp3)
                    .addComponent(txtTenSp2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpaneAnh2Layout = new javax.swing.GroupLayout(jpaneAnh2);
        jpaneAnh2.setLayout(jpaneAnh2Layout);
        jpaneAnh2Layout.setHorizontalGroup(
            jpaneAnh2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneAnh2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAnhSP1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpaneAnh2Layout.setVerticalGroup(
            jpaneAnh2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneAnh2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpaneAnh2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAnhSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpaneAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addComponent(jSeparator3))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(Lui, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(Tien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelAnh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAnhSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jpaneAnh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Tien, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(Lui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbHDMouseClicked(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel9.setText("Tạo hóa đơn");

        jLabel11.setText("Tên khách hàng");

        txttenkh.setEnabled(false);
        txttenkh.setName("Tên khách hàng"); // NOI18N

        jLabel14.setText("Số lượng ");

        jLabel15.setText("Đơn giá");

        txtsl.setEnabled(false);

        txtdg.setEnabled(false);

        jLabel16.setText("Giảm giá");

        jLabel18.setText("Trạng thái");

        jLabel19.setText("Hình thức thanh toán");

        jLabel20.setText("Tổng tiền");

        jLabel21.setText("Tiền khách đưa");

        jLabel22.setText("Tiền thừa");

        txtgiamgia.setText("0");
        txtgiamgia.setEnabled(false);

        buttonGroup1.add(rdodatt);
        rdodatt.setSelected(true);
        rdodatt.setText("Đã thanh toán");

        cbbhinhthuctt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hình thức thanh toán", "Tiền mặt", "Thẻ" }));

        txttongtien.setText("0");
        txttongtien.setEnabled(false);

        txttienkhachdua.setName("Tiền khách đưa "); // NOI18N
        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyReleased(evt);
            }
        });

        txttienthua.setEnabled(false);
        txttienthua.setName("Tiền thừa "); // NOI18N

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
        tbltimtheoma.setEnabled(false);
        tbltimtheoma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltimtheomaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltimtheoma);

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
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                        .addGap(132, 132, 132)
                                        .addComponent(btntt, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdodatt)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdochuatt)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(59, 59, 59)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbHD.addTab("Hóa đơn", jPanel8);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel10.setText("Tên khách hàng: ");

        jLabel12.setText("Số điện thoại:");

        jLabel13.setText("Địa chỉ:");

        jLabel29.setText("VND");

        jLabel25.setText("Số lượng ");

        jLabel24.setText("Phí vận chuyển:");

        jLabel26.setText("Đơn giá");

        jLabel27.setText("Giảm giá");

        jLabel28.setText("Tổng tiền");

        txtPVC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPVC.setText("25");
        txtPVC.setEnabled(false);
        txtPVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPVCActionPerformed(evt);
            }
        });

        txtgiamgia1.setText("0");
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

        txttongtien1.setText("0");
        txttongtien1.setEnabled(false);
        txttongtien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtien1ActionPerformed(evt);
            }
        });

        txtdg1.setEnabled(false);
        txtdg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdg1ActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel28)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtPVC, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtgiamgia1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttongtien1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdg1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsl1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(136, Short.MAX_VALUE))))
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
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtgiamgia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txttongtien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnHoangThanh.setText("Hoàn thành");
        btnHoangThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoangThanhActionPerformed(evt);
            }
        });

        btnChuyenHang.setText("Giao đơn hàng");
        btnChuyenHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenHangActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(btnChuyenHang)
                .addGap(18, 18, 18)
                .addComponent(btnHoangThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuyDonHang)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoangThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChuyenHang))
                .addGap(72, 72, 72))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnChuyenHang, btnHoangThanh, btnHuyDonHang});

        tbnThemDatHang.setText("Tìm kiếm");
        tbnThemDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnThemDatHangActionPerformed(evt);
            }
        });

        jLabel7.setText("Tìm kiếm theo mã");

        txtMPRO.setText("MA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMPRO, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSDTShip, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                            .addComponent(txtDShip)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTenShip, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tbnThemDatHang))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(170, 170, 170)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnThemDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(210, 210, 210))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMPRO))
                .addGap(130, 130, 130))
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
                            .addComponent(result_field, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbHD, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tabbHD, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 226, Short.MAX_VALUE))))
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
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpanel45Layout = new javax.swing.GroupLayout(jpanel45);
        jpanel45.setLayout(jpanel45Layout);
        jpanel45Layout.setHorizontalGroup(
            jpanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanel45Layout.setVerticalGroup(
            jpanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel45Layout.createSequentialGroup()
                .addGroup(jpanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanel45Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jpanel45, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
int a = 0;
//    CHON SAN PHAM VAO GIO HANG

    private String chonSanPham() {
        //  KIEM TRA CO HOA DON HOAC HOA DON CHO CHUA
        if (IDHD == null && hdCho == null) {
            JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm vào giỏ hàng vui lòng chọn hóa đơn");
            tbltimtheoma.clearSelection();
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

        if (Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) == 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng vui lòng chọn sản phẩm khác");
            return null;
        }

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

// CAP NHAP SO LUONG SAN PHAM TRONG TABLE       
        tbltimtheoma.setValueAt(Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) - soLuong, tbltimtheoma.getSelectedRow(), 5);

        if (Integer.parseInt(getValueTable(tbltimtheoma.getSelectedRow(), tbltimtheoma, 5)) == 0) {
            tbltimtheoma.setValueAt("Hết hàng", tbltimtheoma.getSelectedRow(), 4);
        }

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
                    * Integer.parseInt(tblgiohang.getValueAt(i, 2).toString());
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

//    HIGHT LIGHT HOA DON DUỌC CHON
    private void hightLighHoaDonChon(String id) {
        for (int i = 0; i < listHD.size(); i++) {
            if (listHD.get(i).getId().equalsIgnoreCase(id)) {
                tbnHD.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed
// NEU DANG CO HOA DON CHO THI CHAN
//        if (hdCho != null) {
//            JOptionPane.showMessageDialog(this, "Vui lòng xử lí hóa đơn hiện tại");
//            return;
//        }
//
        if (tabbHD.getSelectedIndex() == 0) {
            clear();
        } else if (tabbHD.getSelectedIndex() == 1) {
            clearDatHang();
        }
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

        sapXep(this.resHD.getAll(""));
        hightLighHoaDonChon(IDHD);
        dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);
        dtm.addRow(new Object[]{});
    }//GEN-LAST:event_btntaohdActionPerformed

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
        if (IDHD == null && hdCho == null && rdoHuy.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Không thể thanh toán hóa đơn");
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
                if (sp.getSoLuong() == 0) {
                    sp.setTrangThai(1);
                }
                this.resSP.update(sp);

                this.resGH.addHDCT(ctHD);

                this.resGH.updateHD(hd);
                spList.add(sp);
                soluong2[i] = Integer.parseInt(getValueTable(i, tblgiohang, 2));

            }

            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hóa đơn không?") == JOptionPane.YES_OPTION) {
                try {
                    xuatFileWord(spList, hd, soluong2, Float.parseFloat(txttongtien.getText().trim()), giamGia);
//                resDoiTra.add(new domaimodel.DoiTra(jcheck.createID().toString(), jcheck.randomMA(),
//                        10, sp, khachHangMiNi));
                } catch (IOException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidFormatException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy hóa đơn ?") != JOptionPane.YES_OPTION) {
                return;
            }
//  TRUONG HOP XU LI HOA DON KHI CHON RADIO HUY          
            if (hdCho != null) {
                hdCho.setTinhTrang(2);
                GioHang gioHang = ((ChiTietGioHang) ctghCho.get(0)).getGh();
                gioHang.setTinhtrang(2);
                this.resGH.updateGH(gioHang);
                this.resGH.updateHD(hdCho);
            } else {
                GioHang g = ((GioHang) this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0));
                g.setTinhtrang(2);
                hd.setTinhTrang(2);
                hd.setKh(khachHangMiNi);
                g.setNv(hd.getKh());
                this.resGH.updateGH(g);
                this.resGH.updateHD(hd);
            }
        }

        sapXep(this.resHD.getAll(""));
        loadTableGiamSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        clear();
//        loadTableSP();
//        loadSLSanPhamPanelHDCho();

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
                } else
                    giaGiam = ((ChiTietKhuyenMai) this.ctkm.getALLJoin1(((ChiTietGioHang) object)
                            .getSp().getMa()).get(0)).getKm().getGiaGiam();
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
// LOAD SO LUONG SAN PHAM TRONG PANEL
    private void loadSLSanPhamPanel() {
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
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
    }

    private void loadSLSanPhamPanelHDCho() {
        List<SanPham> listSP = this.resSP.getAll(dk(" trangThai ", "0"));
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

    private void btnxoatatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoatatcaActionPerformed
        if (tblgiohang.getRowCount() == 0) {
            return;
        }
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
        if (tblgiohang.getRowCount() == 0) {
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
            } else
                sanPhamHienThiODATHANG();
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
        webcam.close();
        new QuanLyNhanVien().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamActionPerformed
        webcam.close();
        this.dispose();
        new viewSanPham().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnsanphamActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
        webcam.close();
        this.dispose();
        new KhachHangView().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnkhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhuyenmaiActionPerformed
        webcam.close();
        this.dispose();
        new KhuyenMaiView().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnkhuyenmaiActionPerformed

    private void btmthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmthongkeActionPerformed
        webcam.close();
        this.dispose();
        new ThongKe().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btmthongkeActionPerformed

    private void btnlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlichsuActionPerformed
        webcam.close();
        this.dispose();
        new LichSu().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnlichsuActionPerformed
    private boolean checkKhachHangHDCho;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new khachHangMini().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txttongtien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtien1ActionPerformed

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
                if (sp.getSoLuong() == 0) {
                    sp.setTrangThai(1);
                }
                this.resSP.update(sp);

                this.resGH.addHDCT(ctHD);

//                resDoiTra.add(new domaimodel.DoiTra(jcheck.createID().toString(), jcheck.randomMA(),
//                        10, sp, khachHangMiNi));
            }
        }
        KhachHang a;
        if (khachHangMiNi != null) {
            a = khachHangMiNi;
        } else
            a = new KhachHang(jcheck.createID().toString(), jcheck.randomMA(), txtTenShip.getText().trim(), txtSDTShip.getText().trim(),
                    txtDShip.getText().trim());
        GioHang g = this.resGH.getALlGioHangs(dk(" g.id ", IDGH)).get(0);
        g.setTinhtrang(trangThai);
        hd.setTinhTrang(trangThai);
        hd.setKh(a);
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
        loadTableGiamSPVipPro(2, 0);
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
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy hóa đơn ?") != JOptionPane.YES_OPTION) {
            return;
        }

//        if (getValueTable(tbnHD.getSelectedRow(), tbnHD, 4).
//                equals("Đang xử lí") == true) {
//            
//        }
        if (IDHD == null) {
            IDHD = hdCho.getId();
        } else if (hdCho != null) {
            GioHang g = ((ChiTietGioHang) ctghCho.get(0)).getGh();
            g.setTinhtrang(2);
            this.resGH.updateGH(g);
        }
//        themSanPhamVaoGioHangChiTiet();
        HoaDon hd = this.resHD.getAll(dk(" d.id ", IDHD)).get(0);

        hd.setTinhTrang(2);
        this.resHD.update(hd);

        sapXep(this.resHD.getAll(""));
        clearDatHang();
        loadTableGiamSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        checkButton = 1;
    }//GEN-LAST:event_btnHuyDonHangActionPerformed
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
        loadTableGiamSPVipPro(2, 0);
        countLoadTableVipPro = 0;
        checkButton = 0;
    }//GEN-LAST:event_btnHoangThanhActionPerformed

    private void cbbtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaiActionPerformed

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

    private void tblgiohangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblgiohangMouseEntered

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbanhangActionPerformed

    private void txtsl1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtsl1PropertyChange
    }//GEN-LAST:event_txtsl1PropertyChange

    private void txtdg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdg1ActionPerformed

    private void txtPVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPVCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPVCActionPerformed

    private void txtgiamgia1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtgiamgia1PropertyChange
//        System.out.println("alsjflsa");
//        btnChuyenHang.setVisible(true);
//        btnHoangThanh.setVisible(true);
//        btnHuyDonHang.setVisible(true);
    }//GEN-LAST:event_txtgiamgia1PropertyChange

    private void txtgiamgia1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtgiamgia1VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiamgia1VetoableChange

    private void txtgiamgia1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtgiamgia1InputMethodTextChanged
    }//GEN-LAST:event_txtgiamgia1InputMethodTextChanged

    private void txtsl1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsl1FocusLost
    }//GEN-LAST:event_txtsl1FocusLost

    private void tbnHDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbnHDPropertyChange
//        if (listHD.size() != 0) {
//            sapXep(listHD);
//        }
    }//GEN-LAST:event_tbnHDPropertyChange
    int tab = 0;
    private void tabbHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbHDMouseClicked
        //        System.out.println(tab);
//        if (tab != 0) {
        clear();
        clearDatHang();
        loadTableGiamSPVipPro(2, 0);
        countLoadTableVipPro = 0;
//            tab = 0;
//            return;
//        }
//        tab = 1;
// TODO add your handling code here:
    }//GEN-LAST:event_tabbHDMouseClicked

    private void tbnThemDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnThemDatHangActionPerformed
        new khachHangMini(1).setVisible(true);
        checkTimKiem = 1;
    }//GEN-LAST:event_tbnThemDatHangActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        System.out.println("panel 1");        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        System.out.println("pane 0");        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MouseClicked

    private void panelAnh1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAnh1MouseMoved
        System.out.println("mouse moved");        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnh1MouseMoved

    private void txtAnhSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnhSPMouseEntered
        btnThem.setVisible(true);
    }//GEN-LAST:event_txtAnhSPMouseEntered

    private void txtAnhSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnhSPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnhSPMouseExited

    private void panelAnh1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAnh1MouseEntered
        btnThem.setEnabled(true);        // TODO add your handling code here:      
        btnThem.setVisible(true);
    }//GEN-LAST:event_panelAnh1MouseEntered

    private void panelAnh1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAnh1MouseExited
        btnThem.setVisible(false);
        btnThem1.setVisible(false);
        btnThem.setEnabled(false);
        btnThem1.setEnabled(false);
    }//GEN-LAST:event_panelAnh1MouseExited

    private int btnThemSanPham;
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
    private int btnThemSanPham2;
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

    private void txtAnhSP1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnhSP1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnhSP1MouseEntered

    private void txtAnhSP1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnhSP1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnhSP1MouseExited

    private void jPanel13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseMoved

    private void jPanel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseEntered
        btnThem1.setVisible(true);
        btnThem1.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseEntered

    private void jPanel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseExited
        btnThem.setVisible(false);
        btnThem1.setVisible(false);
        btnThem.setEnabled(false);
        btnThem1.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel13MouseExited

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
    private void TienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TienMouseClicked
        loadTableSPVipPro(countLoadTableVipPro + 2, countLoadTableVipPro);
        capNhapSLSPChuyenMan();
    }//GEN-LAST:event_TienMouseClicked

    private void txtAnhSPComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtAnhSPComponentResized
        txtAnhSP.setSize(150, 150);
        txtAnhSP1.setSize(150, 150);
    }//GEN-LAST:event_txtAnhSPComponentResized

    private void LuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuiMouseClicked

        loadTableGiamSPVipPro(countLoadTableVipPro - 2, countLoadTableVipPro - 4);
        capNhapSLSPChuyenMan();
    }//GEN-LAST:event_LuiMouseClicked

    private void panelAnh1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelAnh1ComponentResized
//        panelAnh1.setSize(183, 150);        // TODO add your handling code here:
    }//GEN-LAST:event_panelAnh1ComponentResized

    private void btnThem1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseEntered
        btnThem1.setVisible(true);
        btnThem1.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseEntered

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        btnThem.setEnabled(true);
        btnThem.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThem1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseExited
        btnThem.setEnabled(false);
        btnThem.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseExited

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        btnThem1.setEnabled(false);
        btnThem1.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseExited

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        btnThem.setEnabled(true);
        btnThem.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseEntered

    /**
     * @param args the command line arguments
     */
    private void initWebcam() {
        if (panel == null) {
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
                        sanpham = ((SanPham) this.resSP.getAll(dk(" s.ma ", result_field.getText().trim())).get(0));
                    } catch (Exception e) {
                    }
//                    JOptionPane.showMessageDialog(this, sanpham.getMa());

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
                        for (int j = 0; j < listSPUltra.size(); j++) {
                            if (sanpham.getMa().equalsIgnoreCase(listSPUltra.get(j).getMa()) == true) {
                                if (soluong > Integer.parseInt(listSPUltra.get(j).getSoLuong().toString())) {
                                    JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng trong kho");
                                    break;
                                } else if (soluong < 0) {
                                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
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
    private javax.swing.JLabel Lui;
    private javax.swing.JLabel Tien;
    private javax.swing.JButton btmthongke;
    private javax.swing.JButton btnChuyenHang;
    private javax.swing.JButton btnHoangThanh;
    private javax.swing.JButton btnHuyDonHang;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
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
    private javax.swing.JComboBox<String> cbbhinhthuctt;
    private javax.swing.JComboBox<String> cbbtrangthai;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpaneAnh2;
    private javax.swing.JPanel jpanel45;
    private javax.swing.JPanel panelAnh1;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JRadioButton rdochuatt;
    private javax.swing.JRadioButton rdodatt;
    private javax.swing.JTextField result_field;
    private javax.swing.JTabbedPane tabbHD;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tbltimtheoma;
    private javax.swing.JTable tbnHD;
    private javax.swing.JButton tbnThemDatHang;
    private javax.swing.JLabel txtAnhSP;
    private javax.swing.JLabel txtAnhSP1;
    private javax.swing.JTextField txtDShip;
    private javax.swing.JLabel txtGiaPRO;
    private javax.swing.JLabel txtGiaPRO1;
    private javax.swing.JLabel txtGiaPRO2;
    private javax.swing.JLabel txtGiaPRO3;
    private javax.swing.JLabel txtMPRO;
    private javax.swing.JLabel txtMPRO1;
    private javax.swing.JLabel txtMPRO2;
    private javax.swing.JLabel txtMPRO3;
    private javax.swing.JLabel txtMPRO4;
    private javax.swing.JTextField txtPVC;
    private javax.swing.JTextField txtSDTShip;
    private javax.swing.JLabel txtSLPRO;
    private javax.swing.JLabel txtSLPRO1;
    private javax.swing.JLabel txtSLPRO2;
    private javax.swing.JLabel txtSLPRO3;
    private javax.swing.JLabel txtTT;
    private javax.swing.JLabel txtTT1;
    private javax.swing.JLabel txtTT2;
    private javax.swing.JLabel txtTT3;
    private javax.swing.JTextField txtTenShip;
    private javax.swing.JLabel txtTenSp;
    private javax.swing.JLabel txtTenSp1;
    private javax.swing.JLabel txtTenSp2;
    private javax.swing.JLabel txtTenSp3;
    private javax.swing.JTextField txtdg;
    private javax.swing.JTextField txtdg1;
    private javax.swing.JTextField txtgiamgia;
    private javax.swing.JTextField txtgiamgia1;
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
        return o1.getNgayT().getTime() > o1.getNgayT().getTime() ? 0 : 1;
    }
}
