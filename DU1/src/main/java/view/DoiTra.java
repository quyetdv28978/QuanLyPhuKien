/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.itextpdf.text.BaseColor;
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
import domaimodel.BaoHanh;
import domaimodel.ChiTietHoaDon;
import domaimodel.HoaDon;
import domaimodel.SanPham;
import domaimodel.chitiethoadonNoEmbe;
import domaimodel.embeddableCTHD;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import respon.BaoHanhRes;
import respon.ChiTietHoaDonres;
import respon.ResGioHangCT;
import respon.ResHoaDon;
import respon.ResHoaDonCho;
import respon.resSanPham;
import utility.JframeCheck;

/**
 *
 * @author Admin
 */
public class DoiTra extends javax.swing.JFrame {

    private ChiTietHoaDonres cthd = new ChiTietHoaDonres();
    private List<Object> listcthd;
    private DefaultTableModel dtmDoiTra;
    private ResHoaDon resHD = new ResHoaDon();
    private ResHoaDonCho resHDCho = new ResHoaDonCho();
    private resSanPham rePham = new resSanPham();
    private final JframeCheck jcheck = new JframeCheck();
    private Map<String, Integer> sanphamSL = new HashMap<>();
    List<SanPham> lisst = new ArrayList<>();
    private resSanPham resSPDOITRA = new resSanPham();
    private ResGioHangCT resGH = new ResGioHangCT();
    private BaoHanhRes resBH = new BaoHanhRes();

    /**
     * Creates new form DoiTra
     */
    public DoiTra() {
        initComponents();
        setLocationRelativeTo(null);
    }
    /// load table hoá đơn đã thanh toán

    public void loadTableHD(List<HoaDon> list) {
        dtmDoiTra = (DefaultTableModel) tbHienHoaDon.getModel();
        dtmDoiTra.setRowCount(0);
        List<HoaDon> danhSachHoaDon = resHD.SelectbyHD(txtTimHoaDonDoi.getText());
        if (danhSachHoaDon != null && !danhSachHoaDon.isEmpty()) {
            for (HoaDon hoaDon : danhSachHoaDon) {
                // Xử lý thông tin hoá đơn ở đây
                dtmDoiTra.addRow(hoaDon.toRowhd1());
            }
        }
//        } else {
//            JOptionPane.showMessageDialog(this, "Không tìm thấy mã hoá đơn : " + txtTimHoaDon.getText());
//        }

    }
    //load table sản phẩm của hoá đơn

    public void loadTableSP(List<Object> l) {
        dtmDoiTra = (DefaultTableModel) tbHienSPHoaDon.getModel();
        dtmDoiTra.setRowCount(0);
        for (Object sanPham : l) {
            dtmDoiTra.addRow(((chitiethoadonNoEmbe) sanPham).toRowhd2());
        }
    }

    List<SanPham> listSP = new ArrayList<>();

    // XUAT FILE PDF 
    private void xuatFilePdf(List<SanPham> sp, HoaDon cthd, int[] soLuong, List<SanPham> spdoi, HoaDon cthddoi, int[] soLuongdoi) {
        try {
            Document document = new Document();
            // Khởi tạo PdfWriter để ghi tài liệu vào OutputStream
            PdfWriter.getInstance(document, new FileOutputStream("word\\output" + new Random().nextLong(100000) + ".pdf"));
            // Mở tài liệu
            document.open();
            // Thêm nội dung
            BaseFont bf = BaseFont.createFont("font\\Arial Unicode MS Font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 20);
            Paragraph p = new Paragraph(" Hóa đơn đổi trả ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            document.add(new Paragraph());

            Paragraph p2 = new Paragraph("--------------------------------------------------------------------------------------------------------------------");
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);

            document.add(new Paragraph());

            Font font3 = new Font(bf, 12);
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
            //Chưa lấy nhân viên
              Paragraph p6 = new Paragraph("Tên Nhân Viên: " + cthd, font4);
//            p4.setAlignment(Element.ALIGN_CENTER);
            p6.setFirstLineIndent(35);
            p6.setSpacingAfter(10);
            document.add(p6);

            document.add(new Paragraph());

            PdfPTable table = new PdfPTable(4);
            

            
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(10);
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Font font20 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
            PdfPCell cell1 = new PdfPCell(new Phrase("Sản phẩm đổi", font4));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell1.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase("SL", font4));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell2.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("Đơn giá", font4));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell3.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Phrase("Tổng tiền", font4));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell4.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell4);
            
          
// thêm dữ liệu vào bảng
            Paragraph p17 = new Paragraph("-----------------------------------------------------------------------------------------------------------------");
            p17.setAlignment(Element.ALIGN_CENTER);
            document.add(p17);

            document.add(new Paragraph());
            float tongTienCuaTongTien = 0;
            for (int i = 0; i < sp.size(); i++) {
                PdfPCell cell5 = new PdfPCell(new Phrase(sp.get(i).getTenSanPham(), font4));
//                cell5.setBorder(PdfPCell.NO_BORDER);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell5);

                PdfPCell cell6 = new PdfPCell(new Phrase(soLuong[i] + "", font20));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell6.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cell6);

                PdfPCell cell7 = new PdfPCell(new Phrase(sp.get(i).getGiaBan() + "", font4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell7.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cell7);

                PdfPCell cell8 = new PdfPCell(new Phrase(sp.get(i).getGiaBan() * soLuong[i] + "", font4));
//                cell8.setBorder(PdfPCell.NO_BORDER);
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell8);
                
                tongTienCuaTongTien += soLuong[i] * sp.get(i).getGiaBan();

            }
            document.add(table);
            
            
            Paragraph p20 = new Paragraph(new Phrase("Tổng Tiền : " + tongTienCuaTongTien,font3));
            p20.setAlignment(Element.ALIGN_RIGHT);
            document.add(p20);

            Paragraph p19 = new Paragraph("-----------------------------------------------------------------------------------------------------------------");
            p19.setAlignment(Element.ALIGN_CENTER);
            document.add(p19);

            PdfPTable tableDoiTra = new PdfPTable(4);
            tableDoiTra.setWidthPercentage(100);
            tableDoiTra.getDefaultCell().setPadding(10);
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Font font20DoiTra = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
            PdfPCell cell1DoiTra = new PdfPCell(new Phrase("Sản phẩm trả", font4));
            cell1DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell1.setBorder(PdfPCell.NO_BORDER);
            tableDoiTra.addCell(cell1DoiTra);

            PdfPCell cell2DoiTra = new PdfPCell(new Phrase("SL", font4));
            cell2DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell2.setBorder(PdfPCell.NO_BORDER);
            tableDoiTra.addCell(cell2DoiTra);

            PdfPCell cell3DoiTra = new PdfPCell(new Phrase("Đơn giá", font4));
            cell3DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell3.setBorder(PdfPCell.NO_BORDER);
            tableDoiTra.addCell(cell3DoiTra);

            PdfPCell cell4DoiTra = new PdfPCell(new Phrase("Tổng tiền", font4));
            cell4DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell4.setBorder(PdfPCell.NO_BORDER);
            tableDoiTra.addCell(cell4DoiTra);

// thêm dữ liệu vào bảng
            Paragraph p17DoiTra = new Paragraph("");
            p17DoiTra.setAlignment(Element.ALIGN_CENTER);
            document.add(p17DoiTra);

            document.add(new Paragraph());
            double tongTienTraCuaTongTien = 0;
            for (int i = 0; i < spdoi.size(); i++) {
                PdfPCell cell5DoiTra = new PdfPCell(new Phrase(spdoi.get(i).getTenSanPham(), font4));
//                cell5.setBorder(PdfPCell.NO_BORDER);
                cell5DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableDoiTra.addCell(cell5DoiTra);

                PdfPCell cell6DoiTra = new PdfPCell(new Phrase(soLuongdoi[i] + "", font20));
                cell6DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell6.setBorder(PdfPCell.NO_BORDER);
                tableDoiTra.addCell(cell6DoiTra);

                PdfPCell cell7DoiTra = new PdfPCell(new Phrase(spdoi.get(i).getGiaBan() + "", font4));
                cell7DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell7.setBorder(PdfPCell.NO_BORDER);
                tableDoiTra.addCell(cell7DoiTra);

                PdfPCell cell8DoiTra = new PdfPCell(new Phrase(spdoi.get(i).getGiaBan() * soLuongdoi[i] + "", font4));
//                cell8.setBorder(PdfPCell.NO_BORDER);
                cell8DoiTra.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableDoiTra.addCell(cell8DoiTra);
                
                tongTienTraCuaTongTien += soLuongdoi[i] * spdoi.get(i).getGiaBan();

            }
            document.add(tableDoiTra);
//            
             Paragraph p21 = new Paragraph(new Phrase("Tổng Tiền : " + tongTienTraCuaTongTien, font3));
            p21.setAlignment(Element.ALIGN_RIGHT);
            document.add(p21);
            
            Paragraph p13 = new Paragraph("-----------------------------------------------------------------------------------------------------------------");
            p13.setAlignment(Element.ALIGN_CENTER);
            document.add(p13);
            document.add(new Paragraph());
            
            font.setColor(BaseColor.RED);
            Paragraph p22 = new Paragraph("TỔNG TIỀN : " + lbTongTien.getText(),font);
            p22.setAlignment(Element.ALIGN_LEFT);
            document.add(p22);
            
            
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_ChuChay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btntrangchu = new javax.swing.JButton();
        btnbanhang = new javax.swing.JButton();
        btnnhanvien = new javax.swing.JButton();
        btnsanpham = new javax.swing.JButton();
        btnkhachhang = new javax.swing.JButton();
        btnkhuyenmai = new javax.swing.JButton();
        btnthongke = new javax.swing.JButton();
        btnlichsu = new javax.swing.JButton();
        btndangxuat = new javax.swing.JButton();
        btnquenmatkhau = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHienHoaDon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTimHoaDonDoi = new javax.swing.JTextField();
        btnTimHoaDon = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbTenKH = new javax.swing.JLabel();
        btnDoi = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtALyDO = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbHienThiDoiTra = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbHienSPHoaDon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_ChuChay.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ChuChay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_ChuChay.setText("Xin Chào Mọi Người Đến Đến Với Cửa Hàng Phụ Kiện Trang Sức N1.Chúc mọi người có một ngày tốt lành.                                                                                                                                                                        ");

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

        btntrangchu.setBackground(new java.awt.Color(255, 255, 153));
        btntrangchu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrangchu.setText("Trang Chủ ");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(btntrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlichsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnquenmatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(btndangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btntrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnlichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnquenmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 204));

        jTabbedPane3.setBackground(new java.awt.Color(0, 255, 255));

        jPanel12.setBackground(new java.awt.Color(0, 255, 255));

        tbHienHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã Hoá Đơn", "Khách hàng", "Ngày thanh toán", "Đơn giá", "Trạng thái"
            }
        ));
        jScrollPane4.setViewportView(tbHienHoaDon);

        jLabel6.setText("Tìm kiếm :");

        txtTimHoaDonDoi.setText("11129");

        btnTimHoaDon.setText("Tìm");
        btnTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHoaDonActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đổi hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel8.setText("Tên khách hàng :");

        lbTenKH.setText("jLabel33");

        btnDoi.setText("Đổi hàng");
        btnDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiActionPerformed(evt);
            }
        });

        jLabel2.setText("Tổng tiền :");

        jLabel4.setText("Lý do :");

        txtALyDO.setColumns(20);
        txtALyDO.setRows(5);
        jScrollPane1.setViewportView(txtALyDO);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTenKH)
                            .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnDoi)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbTenKH))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnDoi)
                .addGap(60, 60, 60))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbHienThiDoiTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Sản phẩm", "Số lượng", "Ngày đổi"
            }
        ));
        tbHienThiDoiTra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbHienThiDoiTra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbHienThiDoiTraPropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(tbHienThiDoiTra);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbHienSPHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Tên SP", "Số lượng", "Giá tiền"
            }
        ));
        tbHienSPHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienSPHoaDonMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbHienSPHoaDon);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(txtTimHoaDonDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(48, 48, 48)
                            .addComponent(btnTimHoaDon))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTimHoaDonDoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimHoaDon))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("tab1", jPanel12);

        jTabbedPane1.addTab("Đổi hàng", jTabbedPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_ChuChay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ChuChay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntrangchuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntrangchuActionPerformed
        //        this.setVisible(false);
        //        new Trangch().setVisible(true);
    }//GEN-LAST:event_btntrangchuActionPerformed

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
        //        this.setVisible(false);
        //        new BanHang().setVisible(true);
    }//GEN-LAST:event_btnbanhangActionPerformed

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
        //        this.setVisible(false);
        //        new NhanVie().setVisible(true);
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsanphamActionPerformed
        //        this.setVisible(false);
        //        new SanPha().setVisible(true);
    }//GEN-LAST:event_btnsanphamActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
        //        this.setVisible(false);
        //        new KhachHan().setVisible(true);
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnkhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhuyenmaiActionPerformed
        //        this.setVisible(false);
        //        new KhuyenMa().setVisible(true);
    }//GEN-LAST:event_btnkhuyenmaiActionPerformed

    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        this.setVisible(false);
        new ThongKeView().setVisible(true);
    }//GEN-LAST:event_btnthongkeActionPerformed

    private void btnlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlichsuActionPerformed
        //        this.setVisible(false);
        //        new LichS().setVisible(true);
    }//GEN-LAST:event_btnlichsuActionPerformed

    private void btndangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndangxuatActionPerformed
        //        this.setVisible(false);
        //        new DangNhap().setVisible(true);
    }//GEN-LAST:event_btndangxuatActionPerformed

    private void btnquenmatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquenmatkhauActionPerformed
        //        this.setVisible(false);
        //        new QuenMatKhau().setVisible(true);
    }//GEN-LAST:event_btnquenmatkhauActionPerformed
    HoaDon hoaDonDoi;
    private List<Object> listDoiTra;
    private void btnTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHoaDonActionPerformed

        String a = txtTimHoaDonDoi.getText();
        List<domaimodel.HoaDon> list1 = resHD.SelectbyMaHD(a);

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date endDate = new Date(date.getTime() - (1 * 24 * 60 * 60 * 1000));
        if (list1.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hoá đơn : " + txtTimHoaDonDoi.getText());
            return;
        }
        hoaDonDoi = list1.get(0);
        if (resBH.getAllBHChange1(hoaDonDoi.getId()).size() != 0) {
            JOptionPane.showMessageDialog(this, "Hoá đơn đang được bảo hành");
            return;
        }
        if (hoaDonDoi.getNgayTT().after(endDate) == false) {
            JOptionPane.showMessageDialog(this, "Hoá đơn đã hết thời gian đổi trả");
            return;
        }

        loadTableHD(list1);
        listDoiTra = resHDCho.getAllSPHDNoEm(tbHienHoaDon.getValueAt(0, 0).toString(), 0);
        loadTableSP(listDoiTra);
        lbTenKH.setText(hoaDonDoi.getKh().getTen());

    }//GEN-LAST:event_btnTimHoaDonActionPerformed
    List<SanPham> listClick = new ArrayList<>();
    private void tbHienSPHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienSPHoaDonMouseClicked

        int row = tbHienSPHoaDon.getSelectedRow();

//        ChiTietBaoHanh c = getDataCTBH("");
        if (row >= 0) {
            if (resHDCho.checkSPApDungKm(tbHienSPHoaDon.getValueAt(tbHienSPHoaDon.getSelectedRow(), 0).toString(), txtTimHoaDonDoi.getText()) == false) {
                JOptionPane.showMessageDialog(this, "Sản phẩm không được đổi trả vì áp dụng khuyến mãi");
                return;
            }

            String soLuong = JOptionPane.showInputDialog(this, "Số lượng");
            int soLuongReal = Integer.parseInt(soLuong);
            sanphamSL.put(tbHienSPHoaDon.getValueAt(tbHienSPHoaDon.getSelectedRow(), 0).toString(), Integer.valueOf(soLuongReal));
            Float tien = (Float) tbHienSPHoaDon.getValueAt(tbHienSPHoaDon.getSelectedRow(), 3);
            int c = Integer.parseInt(tbHienSPHoaDon.getValueAt(tbHienSPHoaDon.getSelectedRow(), 2).toString());
            if (soLuongReal > c || soLuongReal == 0) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm nhập vào phải bé hơn hoặc bằng số lượng đã mua");
                return;
            }

            lisst = rePham.getAllSPHDCungGiaNoEm(tbHienSPHoaDon.getValueAt(tbHienSPHoaDon.getSelectedRow(), 3).toString(), 0);
            new ChiTietDoi(lisst, soLuongReal * tien, soLuongReal).setVisible(true);
            listClick.add(rePham.getAll2(dk(" s.ma ", tbHienSPHoaDon.getValueAt(tbHienSPHoaDon.getSelectedRow(), 0).toString())).get(0));

//            }
        }
    }//GEN-LAST:event_tbHienSPHoaDonMouseClicked
    private String dk(String ten, String giaTri) {
        return " where " + ten + " = '" + giaTri + "'";
    }

    private String getValueTable(int vt, JTable table, int i) {
        if (table.getValueAt(vt, i) != null) {
            return table.getValueAt(vt, i).toString();
        }
        return null;
    }

    private void btnDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiActionPerformed

        String hdid = jcheck.createID().toString();
        String idCT = jcheck.createID().toString();
        hoaDonDoi.setTinhTrang(2);
        hoaDonDoi.setLyDo(txtALyDO.getText());
        resHD.update(hoaDonDoi);
        resHD.add(new HoaDon(hdid, jcheck.randomMA(), new Date(), new Date(),
                0, Float.parseFloat(lbTongTien.getText().trim()), "Đã thanh toán", "", hoaDonDoi.getKh(), null));
        List<SanPham> listdoi = new ArrayList<>();
        int soluongdoi[] = new int[lisst.size()];

//  LAY HOA DON MOI DUOC TAO
        HoaDon hdMoi = resHD.getAll(dk(" d.id ", hdid)).get(0);

        chitiethoadonNoEmbe ctHDtren;
        for (int i = 0; i < tbHienSPHoaDon.getRowCount(); i++) {
            SanPham sp = resSPDOITRA.getAll(dk(" s.ma ", tbHienSPHoaDon.getValueAt(i, 0).toString())).get(0);
            if (sanphamSL.get(tbHienSPHoaDon.getValueAt(i, 0)) != null) {
                if (sanphamSL.get(tbHienSPHoaDon.getValueAt(i, 0)).equals(Integer.valueOf(tbHienSPHoaDon.getValueAt(i, 2).toString())) == false) {
                    ctHDtren = new chitiethoadonNoEmbe(idCT, hdMoi, sp,
                            Integer.valueOf(tbHienSPHoaDon.getValueAt(i, 2).toString()) - sanphamSL.get(tbHienSPHoaDon.getValueAt(i, 0)),
                            sp.getGiaBan() * sanphamSL.get(tbHienSPHoaDon.getValueAt(i, 0)),
                            ((chitiethoadonNoEmbe) listDoiTra.get(i)).getGiagiam(), new Date(), 0);
                    this.resGH.addHDCTNoEm(ctHDtren);
//   CHUYEN SAN PHAM BAO HANH SANG HOA DON MOI
                    if (resBH.getAllBHChange(hoaDonDoi.getId(), sp.getMa()) != null) {
                        for (BaoHanh baoHanh : resBH.getAllBHChange(hoaDonDoi.getId(), sp.getMa())) {
                            baoHanh.setCthd(ctHDtren);
                            resBH.update(baoHanh);
                        }
                    }

                }
            }
            chitiethoadonNoEmbe c = (chitiethoadonNoEmbe) listDoiTra.get(i);
            c.setId(jcheck.createID().toString());
            c.setHd(hdMoi);
//            this.resGH.addHDCTNoEm(c);
        }

        for (int i = 0; i < tbHienThiDoiTra.getRowCount(); i++) {
            soluongdoi[i] = Integer.parseInt(tbHienThiDoiTra.getValueAt(i, 2).toString());
            SanPham sp = ((SanPham) rePham.getAll(dk(" s.ma ", tbHienThiDoiTra.getValueAt(i, 0).toString())).get(0));
            embeddableCTHD idDouble = new embeddableCTHD(hdid, sp.getId());
            listdoi.add(sp);
            float giaGiamReal;
            ChiTietHoaDon ctHD = new ChiTietHoaDon(idDouble, resHD.getAll(dk(" d.id ", hdid)).get(0), sp,
                    Integer.parseInt(getValueTable(i, tbHienThiDoiTra, 2)),
                    Float.parseFloat(getValueTable(i, tbHienThiDoiTra, 2)) * sp.getGiaBan(), 0,
                    new Date(), 0
            );
            new ResGioHangCT().addHDCT(ctHD);

        }

        int soluongcu[] = new int[listDoiTra.size()];
        for (int i = 0; i < listDoiTra.size(); i++) {
            soluongcu[i] = Integer.parseInt(tbHienSPHoaDon.getValueAt(i, 2).toString());
        }
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hoá đơn");
        if (a == JOptionPane.YES_OPTION) {
            xuatFilePdf(listClick, hoaDonDoi, soluongcu, listdoi, hoaDonDoi, soluongdoi);
        }
         DefaultTableModel dtmHienHoaDon = (DefaultTableModel) tbHienHoaDon.getModel();
        dtmHienHoaDon.setRowCount(0);
         DefaultTableModel dtmXoaHienSpHoaDon = (DefaultTableModel) tbHienSPHoaDon.getModel();
        dtmXoaHienSpHoaDon.setRowCount(0);

    }//GEN-LAST:event_btnDoiActionPerformed

    private void tbHienThiDoiTraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbHienThiDoiTraPropertyChange
    }//GEN-LAST:event_tbHienThiDoiTraPropertyChange

    /**
     * @param args the command line arguments
     */
    static DoiTra d;

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
            java.util.logging.Logger.getLogger(DoiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                d = new DoiTra();
                d.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoi;
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btndangxuat;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnkhuyenmai;
    private javax.swing.JButton btnlichsu;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnquenmatkhau;
    private javax.swing.JButton btnsanpham;
    private javax.swing.JButton btnthongke;
    private javax.swing.JButton btntrangchu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbTenKH;
    public static javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbl_ChuChay;
    public static javax.swing.JTable tbHienHoaDon;
    public static javax.swing.JTable tbHienSPHoaDon;
    public static javax.swing.JTable tbHienThiDoiTra;
    private javax.swing.JTextArea txtALyDO;
    private javax.swing.JTextField txtTimHoaDonDoi;
    // End of variables declaration//GEN-END:variables
}
