/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import utility.DBConnection;

/**
 *
 * @author HP
 */
public class resbdsp {
    public List<Object[]> getbdsp(){
        return DBConnection.getsetFactory().openSession().createQuery("select sum(h.soluong),h.sp.tenSanPham from ChiTietHoaDon h join h.hd join h.sp where h.trangThai=0 group by h.sp.tenSanPham").getResultList();
    }
     public void setDataToChart1(JPanel jpnItem) {
        List<Object[]> listItem = getbdsp();
//
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (Object[] x : listItem) {
                System.out.println(x[0]);
                System.out.println(x[1]);
//                System.out.println(x[2]);
//                System.out.println(x[3]);
                dataset.addValue((Long)x[0], "So luong",x[1].toString() );
            }
        }
     


        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê so luong san pham ban chay nhat".toUpperCase(),
                "Ten san pham", "So luong",
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
