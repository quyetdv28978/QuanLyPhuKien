package domaimodel;


import domaimodel.HoaDon;
import java.awt.CardLayout;
import java.awt.Dimension;
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


public class Bieudo1 {

//    private Hoadonres thongKeService = null;
//
//    public Taolop() {
//        this.thongKeService = new Hoadonres();
//    }

    public void setDataToChart1(JPanel jpnItem) {
//        List<HoaDon> listItem = thongKeService.getlistbyhoadon();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        if (listItem != null) {
//            for (HoaDon item : listItem) {
//                dataset.addValue(10, "", "20/02/2022");
//            }
//        }
 dataset.addValue(10000000, "Tổng tiền", "02/2022");
  dataset.addValue(20000000, "Tổng tiền", "03/2022");
    dataset.addValue(30000000, "Tổng tiền", "04/2022");

     


        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu theo tháng".toUpperCase(),
                "Tháng", "Tổng tiền",
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

   

}