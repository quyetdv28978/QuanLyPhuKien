package domaimodel;

import domaimodel.HoaDon;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import utility.DBConnection;

public class Bieudo1 {

    public Bieudo1() {
    }

//
    //    dataset.addValue(30000000, "Tổng tiền", "04/2022");
//
//     nó không biết thằng nayf
    public List<Object[]> getA() {
        return DBConnection.getsetFactory().openSession().createQuery(
                "select month(c.hd.ngayTT),sum(c.soluong*c.donGia-c.giagiam) from ChiTietHoaDon c  where c.hd.tinhTrang=0 group by month(c.hd.ngayTT)").getResultList();
    }

//    public List<Object[]> get() {
////        dung thang nay
//        List<Object[]> listThongKe = new ArrayList<>();
//
////        o tren
//        List<Object[]> thognKe = getA();
////        System.out.println(thognKe.size());
//        int kt = 0, count = 0;
//        double tongTien = 0;
//        for (int i = 0; i < thognKe.size(); i++) {
//            kt = 1;
//            Object[] a = new Object[thognKe.size()];
//            for (int j = i + 1; j < thognKe.size(); j++) {
//                if (new SimpleDateFormat("MM").format(((Date) thognKe.get(i)[0])).
//                        equals(new SimpleDateFormat("MM").format(((Date) thognKe.get(j)[0])))) {
//                    System.out.println("id hoa don: " + thognKe.get(j)[2]);
//                    if (kt != 0) {
//                        tongTien += ((double) thognKe.get(i)[1]) + ((double) thognKe.get(j)[1]);
//                    } else
//                        tongTien += ((double) thognKe.get(j)[1]);
//                    System.out.println("tien a: " + (double) thognKe.get(i)[1] + "   " + "tien b: " + (double) thognKe.get(j)[1]);
////                          listThongKe.remove(a);
//                    System.out.println("tong tien: " + tongTien);
//                    System.out.println("siza: " + thognKe.size());
//
////                     listThongKe.add();
//                    thognKe.remove(j);
//                    j--;
////                     i--;
//                    kt = 0;
////                     count ++;
//                }
//            }
//            if (kt != 0) {
//                a[count] = (((double) thognKe.get(i)[1]));
//                a[count + 1] = new SimpleDateFormat("MM").format(((Date) thognKe.get(i)[0]));
//                listThongKe.add(a);
//
////                listThongKe.add(((double)thognKe.get(i)[1]));
////                listThongKe.add(new SimpleDateFormat("MM").format(((Date)thognKe.get(i)[0])));
//            } else {
////                thognKe.remove(i);
////               i--;
//                a[count] = tongTien;
//                a[count + 1] = new SimpleDateFormat("MM").format(((Date) thognKe.get(i)[0]));
//                listThongKe.add(a);
//            }
//        }
//        return listThongKe;
//    }

    public void setDataToChart1(JPanel jpnItem) {
        List<Object[]> listItem = getA();
//
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (Object[] x : listItem) {
                dataset.addValue((Integer) x[0], "Tổng tiền", (Double)x[1]);
            }
        }
// dataset.addValue(10000000, "Tổng tiền", "02/2022");
//  dataset.addValue(20000000, "Tổng tiền", "03/2022");
//    dataset.addValue(30000000, "Tổng tiền", "04/2022");
//
//     

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu theo tháng".toUpperCase(),
                "Tháng", "Tổng tiền",
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 400));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

}
