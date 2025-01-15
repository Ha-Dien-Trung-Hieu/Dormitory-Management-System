package Do_an_cuoi_ky;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import javax.swing.*;

import java.awt.Color;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.List;

public class ReportChart {

    public void showGenderPieChart() {
    	 DefaultPieDataset pieDataset = new DefaultPieDataset();

         try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
             String query = "SELECT Gender, COUNT(*) AS Count FROM Student GROUP BY Gender";
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();

             while (rs.next()) {
                 String gender = rs.getString("Gender");
                 int count = rs.getInt("Count");
                 pieDataset.setValue(gender, count);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }

         JFreeChart pieChart = ChartFactory.createPieChart("Phân bố giới tính sinh viên", pieDataset, true, true, false);
         PiePlot plot = (PiePlot) pieChart.getPlot();
         plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.00%")));

         ChartPanel chartPanel = new ChartPanel(pieChart);
         JFrame frame = new JFrame("Phân bố giới tính sinh viên");
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.add(chartPanel);
        frame.pack();frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showRoomTypePieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
            String query = "SELECT RoomType, COUNT(*) AS Count FROM Room GROUP BY RoomType";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dataset.setValue(rs.getString("RoomType"), rs.getInt("Count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart("Biểu đồ loại phòng", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} : {1}"));
        ChartPanel chartPanel = new ChartPanel(chart);

        JFrame frame = new JFrame("Biểu đồ loại phòng");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showPaymentPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
            String query = "SELECT PaymentStatus, COUNT(*) AS Count FROM Invoice GROUP BY PaymentStatus";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dataset.setValue(rs.getString("PaymentStatus"), rs.getInt("Count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart("Trạng thái thanh toán", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.00%")));
        ChartPanel chartPanel = new ChartPanel(chart);

        JFrame frame = new JFrame("Trạng thái thanh toán");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack(); frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showMonthlyRevenueBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
            String query = "SELECT MONTH(IssueDate) AS Month, SUM(Amount) AS Total FROM Invoice WHERE PaymentStatus = 'Paid' GROUP BY MONTH(IssueDate)";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String month = "Tháng " + rs.getInt("Month");
                dataset.addValue(rs.getDouble("Total"), "Doanh thu", month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart("Doanh thu hàng tháng", "Tháng", "Doanh thu (VND)", dataset);
        
        
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(16, 255, 255));
            
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Doanh thu hàng tháng");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showRoomOccupancyBarChart() {
    	 DefaultPieDataset pieDataset = new DefaultPieDataset();

         try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
             // Lấy số lượng sinh viên theo loại phòng
             String query = "SELECT RoomType, COUNT(StudentID) AS StudentCount FROM Room r LEFT JOIN Contract c ON r.RoomID = c.RoomID GROUP BY RoomType";
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();

             while (rs.next()) {
                 String roomType = rs.getString("RoomType");
                 int studentCount = rs.getInt("StudentCount");
                 pieDataset.setValue(roomType, studentCount);
             }

             // Thêm số lượng sinh viên không có phòng
             String noRoomQuery = "SELECT COUNT(*) AS StudentCount FROM Student s LEFT JOIN Contract c ON s.StudentID = c.StudentID WHERE c.RoomID IS NULL";
             stmt = conn.prepareStatement(noRoomQuery);
             rs = stmt.executeQuery();

             if (rs.next()) {
                 int noRoomCount = rs.getInt("StudentCount");
                 pieDataset.setValue("Không có phòng", noRoomCount);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }

         JFreeChart pieChart = ChartFactory.createPieChart("Phân bổ sinh viên theo loại phòng", pieDataset, true, true, false);
         PiePlot plot = (PiePlot) pieChart.getPlot();
         plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.00%")));

         ChartPanel chartPanel = new ChartPanel(pieChart);
         JFrame frame = new JFrame("Phân bổ sinh viên theo loại phòng");
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.add(chartPanel);
         frame.pack();frame.setLocationRelativeTo(null);
         frame.setVisible(true);
    }

    public void showRoomUsagePieChart() {
    	DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
            String query = "SELECT CASE WHEN CurrentOccupants > 0 THEN 'Đang sử dụng' ELSE 'Không sử dụng' END AS UsageStatus, COUNT(*) AS Count FROM Room GROUP BY UsageStatus";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String status = rs.getString("UsageStatus");
                int count = rs.getInt("Count");
                barDataset.addValue(count, "Tình trạng sử dụng", status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart barChart = ChartFactory.createBarChart("Tình trạng sử dụng phòng", "Tình trạng", "Số lượng phòng", barDataset);
        ChartPanel chartPanel = new ChartPanel(barChart);

        JFrame frame = new JFrame("Tình trạng sử dụng phòng");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
