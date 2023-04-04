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
import respon.bieudores;


public class Bieudo {

    private bieudores thongKeService ;

    public Bieudo() {
        this.thongKeService = new bieudores();
    }

    public void setDataToChart1(JPanel jpnItem) {
        List<HoaDon> listItem = thongKeService.getall();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (HoaDon x : listItem) {
                dataset.addValue(x.getTongTien(), "Tong tien", x.getNgayTT().toString());
                System.out.println(x.getNgayTT().toString());
            }
           
        }
// dataset.addValue(10000000, "Tổng tiền", "02/2022");
//  dataset.addValue(20000000, "Tổng tiền", "03/2022");
//    dataset.addValue(30000000, "Tổng tiền", "04/2022");
//
//      dataset.addValue(25000000, "Tổng tiền", "05/2022");
//
//        dataset.addValue(15000000, "Tổng tiền", "06/2022");
//
//          dataset.addValue(40000000, "Tổng tiền", "07/2022");


        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu theo tháng".toUpperCase(),
                "Ngay tt", "Tổng tiền",
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 400));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

   

}