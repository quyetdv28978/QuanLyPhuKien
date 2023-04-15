package respon;

import domaimodel.ChiTietHoaDon;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.NoResultException;
import javax.swing.JPanel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import utility.DBConnection;

public class ResTK_DT {

//----------------------TK Doanh Thu-------------------//
//------------theo ngay---------------------*//
    public List<ChiTietHoaDon> Selectby_doanhthu_theoNgayTC(Date ngay) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(DISTINCT c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(DISTINCT c.hd.kh.id)\n"
                    + "FROM domaimodel.ChiTietHoaDon c\n"
                    + "WHERE CONVERT(DATE,c.hd.ngayTT)=CONVERT(DATE,:key1) AND c.hd.tinhTrang = 0";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngay);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD((int) ((Number) ob[0]).longValue());
                c.setTONGTIEN(ob[1] != null ? ((Number) ob[1]).doubleValue() : 0.0);
                c.setSKH(ob[2] != null ? ((Number) ob[2]).doubleValue() : 0.0);
                pas.add(c);
            }
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public Long Selectbydoanhthu_ngayBH(Date ngay) {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(DISTINCT h.id) FROM domaimodel.HoaDon h WHERE CONVERT(DATE,h.ngayTao,101)=CONVERT(DATE,:key1) AND h.tinhTrang = 2", Long.class);
            query.setParameter("key1", ngay);
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

//------------------theo tháng------------------*//
    public List<ChiTietHoaDon> Selectby_doanhthu_ThangTC(Integer thang, Integer nam) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT COUNT(DISTINCT c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(DISTINCT c.hd.kh.id) FROM ChiTietHoaDon c WHERE MONTH(c.hd.ngayTT)=:key1 AND YEAR(c.hd.ngayTT)=:key2 AND c.hd.tinhTrang=0 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", thang);
            query.setParameter("key2", nam);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD(((Number) ob[0]).intValue());
                c.setTONGTIEN(ob[1] != null ? ((Number) ob[1]).doubleValue() : 0.0);
                c.setSKH(ob[2] != null ? ((Number) ob[2]).doubleValue() : 0);
                pas.add(c);
            }
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public Long Selectbydoanhthu_thangBH(int thang, int nam) {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(DISTINCT h.id) FROM HoaDon h WHERE MONTH(h.ngayTao)=:fromDate AND YEAR(h.ngayTao)=:toDate AND h.tinhTrang = 2", Long.class);
            query.setParameter("fromDate", thang);
            query.setParameter("toDate", nam);
            return query.getSingleResult(); // Trả về kiểu Long
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    //-----------khoangngay---------------*//
    public List<ChiTietHoaDon> SelectbyKhoangNgayTC(Date ngaybd, Date ngayKt) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT COUNT(DISTINCT c.hd.id), SUM(c.donGia * c.soluong - c.giagiam),COUNT(DISTINCT c.hd.kh.id) FROM ChiTietHoaDon c WHERE CAST(c.hd.ngayTT as date) BETWEEN :key1 AND :key2 AND c.hd.tinhTrang=0 ";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngaybd);
            query.setParameter("key2", ngayKt);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD(((Number) ob[0]).intValue());
                c.setTONGTIEN(ob[1] != null ? ((Number) ob[1]).doubleValue() : 0.0);
                c.setSKH(ob[2] != null ? ((Number) ob[2]).doubleValue() : 0);
                pas.add(c);
            }
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public Long SelectbyKhoangNgayBH(Date fromDate, Date toDate) {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(DISTINCT h.id) FROM HoaDon h WHERE h.ngayTao BETWEEN :fromDate AND :toDate AND h.tinhTrang = 2", Long.class);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            return query.getSingleResult(); // Trả về kiểu Long
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    //--------------hientai---------------*/    
    public List<ChiTietHoaDon> SelectbyhientaiTC() {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(DISTINCT c.hd.id), SUM(c.donGia * c.soluong - c.giagiam), COUNT(DISTINCT c.hd.kh.id) FROM domaimodel.ChiTietHoaDon c WHERE CONVERT(DATE,c.hd.ngayTT)=CONVERT(DATE,current_date()) AND c.hd.tinhTrang = 0";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            List<Object[]> resultSetArr = query.getResultList();
            for (Object[] obj : resultSetArr) {
                ChiTietHoaDon c = new ChiTietHoaDon();
                c.setSOHD(((Number) obj[0]).intValue()); // Số hóa đơn
                c.setTONGTIEN(obj[1] != null ? ((Number) obj[1]).doubleValue() : 0.0); // Tổng tiền
                c.setSKH(obj[2] != null ? ((Number) obj[2]).doubleValue() : 0); // Số khách hàng
                pas.add(c);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public Long SelectbyhientaiBH() {
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT COUNT(DISTINCT h.id) FROM domaimodel.HoaDon h WHERE CONVERT(DATE,h.ngayTao)=CONVERT(DATE,current_date()) AND h.tinhTrang = 2";
            Query<Long> query = session.createQuery(sql, Long.class);
            return query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*-------------------------------------Phần Sản Phẩm --------------------------------*/
 /*-------tổng hợp theo năm-------*/
    public List<ChiTietHoaDon> selectTonghop(int nam) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT  c.sp.ma, c.sp.tenSanPham,c.sp.mauSac,c.sp.nhaSanXuat,COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam)\n"
                    + "FROM domaimodel.ChiTietHoaDon c Where YEAR(c.hd.ngayTT)=:key1 and c.hd.tinhTrang=0\n"
                    + "GROUP BY c.sp.ma, c.sp.tenSanPham,c.sp.mauSac,c.sp.nhaSanXuat\n"
                    + "ORDER BY SUM(c.donGia * c.soluong - c.giagiam)";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", nam);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon r = new ChiTietHoaDon();
                r.setMaSP(((String) ob[0]).toString());
                r.setTenSP(((String) ob[1]).toString());
                r.setMS(((String) ob[2]).toString());
                r.setNSX(((String) ob[3]).toString());
                r.setSOHD(((Number) ob[4]).intValue());
                r.setSluong(((Number) ob[5]).intValue());
                r.setTONGTIEN(ob[6] != null ? ((Number) ob[6]).doubleValue() : 0.0);
                pas.add(r);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    /*tìm Theo Ngày------*/
    public List<ChiTietHoaDon> selectSPNgay(Date ngay) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT c.sp.ma, c.sp.tenSanPham,c.sp.mauSac,c.sp.nhaSanXuat, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam) FROM ChiTietHoaDon c WHERE CONVERT(DATE,c.hd.ngayTT)=CONVERT(DATE,:key1) AND c.hd.tinhTrang = 0 GROUP BY c.sp.ma,c.sp.tenSanPham,c.sp.mauSac,c.sp.nhaSanXuat ORDER BY COUNT(c.sp.id) DESC";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", ngay);
            List<Object[]> ressulistArr = query.getResultList();
            for (Object[] ob : ressulistArr) {
                ChiTietHoaDon r = new ChiTietHoaDon();
                r.setMaSP(((String) ob[0]).toString());
                r.setTenSP(((String) ob[1]).toString());
                r.setMS(((String) ob[2]).toString());
                r.setNSX(((String) ob[3]).toString());
                r.setSOHD(((Number) ob[4]).intValue());
                r.setSluong(((Number) ob[5]).intValue());
                r.setTONGTIEN(ob[6] != null ? ((Number) ob[6]).doubleValue() : 0.0);
                pas.add(r);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    /*Tìm Theo Tháng-----*/
    public List<ChiTietHoaDon> selectSPThang(int thang, int nam, JPanel jpnItem) {
        List<ChiTietHoaDon> pas = new ArrayList<>();
        try (Session session = DBConnection.getsetFactory().openSession()) {
            String sql = "SELECT DISTINCT c.sp.ma, c.sp.tenSanPham,c.sp.mauSac,c.sp.nhaSanXuat, COUNT(c.sp.id), SUM(c.soluong), SUM(c.donGia * c.soluong - c.giagiam)\n"
                    + "FROM domaimodel.ChiTietHoaDon c Where MONTH(c.hd.ngayTT)=:key1 AND YEAR(c.hd.ngayTT)=:key2 and c.hd.tinhTrang=0\n"
                    + "GROUP BY c.sp.ma, c.sp.tenSanPham,c.sp.mauSac,c.sp.nhaSanXuat\n"
                    + "ORDER BY COUNT(c.sp.id) DESC";
            Query<Object[]> query = session.createQuery(sql, Object[].class);
            query.setParameter("key1", thang);
            query.setParameter("key2", nam);
            List<Object[]> ressulistArr = query.getResultList();
            if (ressulistArr != null) {
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (Object[] ob : ressulistArr) {
                    ChiTietHoaDon r = new ChiTietHoaDon();
                    r.setMaSP(((String) ob[0]).toString());
                    r.setTenSP(((String) ob[1]).toString());
                    r.setMS(((String) ob[2]).toString());
                    r.setNSX(((String) ob[3]).toString());
                    r.setSOHD(((Number) ob[4]).intValue());
                    r.setSluong(((Number) ob[5]).intValue());
                    r.setTONGTIEN(ob[6] != null ? ((Number) ob[6]).doubleValue() : 0.0);
                    pas.add(r);
                    dataset.addValue(r.getSluong(), "So luong", r.getTenSP().toString());
                }
                JFreeChart barChart = ChartFactory.createBarChart(
                        "Biểu đồ thống kê số lượng sản phẩm đã bán trong tháng".toUpperCase() + " " + thang + "/" + nam,
                        "Tên sản phẩm", "Số lượng",
                        dataset, PlotOrientation.VERTICAL, false, true, false);

                CategoryPlot plot = barChart.getCategoryPlot();
                plot.setRangeGridlinePaint(Color.BLACK);
                plot.setDomainGridlinePaint(Color.BLACK);

                ChartPanel chartPanel = new ChartPanel(barChart);
                chartPanel.setPreferredSize(new Dimension(400, 400));

                jpnItem.removeAll();
                jpnItem.setLayout(new CardLayout());
                jpnItem.add(chartPanel);
                jpnItem.validate();
                jpnItem.repaint();
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return pas;
    }

    public List<Object[]> getA(int year) {
        return DBConnection.getsetFactory().openSession().createQuery(
                "SELECT COUNT(c.hd.id), SUM(c.donGia * c.soluong - c.giagiam), MONTH(c.hd.ngayTT), YEAR(c.hd.ngayTT)\n"
                + "FROM ChiTietHoaDon c\n"
                + "WHERE c.hd.tinhTrang = 0\n"
                + "AND YEAR(c.hd.ngayTT) = :year\n"
                + "GROUP BY YEAR(c.hd.ngayTT), MONTH(c.hd.ngayTT)")
                .setParameter("year", year)
                .getResultList();
    }

    public void setDataToChart1(JPanel jpnItem, int year) {
        List<Object[]> listItem = getA(year);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (Object[] x : listItem) {
                int month = (Integer) x[3];
                dataset.addValue((Number) x[0], "Số Đơn Hàng", month);
                dataset.addValue((Number) x[1], "Doanh Thu", month);
            }
        }
        JFreeChart chart = ChartFactory.createLineChart(
                "Biểu đồ đường Doanh thu/Số đơn hàng trong năm " + year + " cửa hàng",
                "Tháng",
                "Số Lượng",
                dataset
        );

        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setPaint(Color.BLACK);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        jpnItem.removeAll();
        jpnItem.setLayout(new BorderLayout());
        jpnItem.add(chartPanel, BorderLayout.CENTER);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
