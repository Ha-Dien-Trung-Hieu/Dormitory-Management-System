package Do_an_cuoi_ky;

import javax.swing.*;         
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.net.URL;

 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;
import javax.swing.Timer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Date;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Manager {
    private JFrame frame;
    private String adminID;
    private JLabel ttt1, ttt2, ttt3, ttt4, ttt5, animation;
    private JPasswordField passwordtxt;
    private JButton dashboardButton, roomManagementButton, studentManagementButton, buildingManagementButton
    , invoiceManagementButton, reportButton, sortByIdButton, sortByRoomButton, filterNoRoomButton, deleteStudentButton
    , addRoomButton, roomDetailsButton, kickStudentButton, editRoomButton; 
    JPanel centerPanel;
    private List<ImageIcon> frames;
    private Future<ImageIcon> futures;
    private int currentFrame = 0;
    private Font tableFont, headerFont;
    private DefaultTableCellRenderer cell;
    public Manager() {
        frame = new JFrame("Quản lý ký túc xá");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1350, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Thong Tin
        JPanel right_big = new JPanel(new BorderLayout());
        JPanel right = new JPanel(new GridLayout(6, 2, 10, 10));
        Border TT = BorderFactory.createLineBorder(Color.BLUE, 3);
        TitledBorder TTT = BorderFactory.createTitledBorder(TT, "Thông Tin Quản Lý");
        TTT.setTitleFont(new Font("Arial", Font.BOLD, 20));
        right.setBorder(TTT);
        right.setBackground(Color.WHITE);
        adminID = UILOGIN.getAdminID();
        

        ttt1 = new JLabel("_Null_"); ttt2 = new JLabel("_Null_"); ttt3 = new JLabel("_Null_"); ttt4 = new JLabel("_Null_"); ttt5 = new JLabel("_Null_");
    

        JLabel tt1 = new JLabel("AdminID");
        JPanel rowPanel1 = new JPanel(new BorderLayout()); rowPanel1.add(tt1, BorderLayout.WEST); rowPanel1.add(new JLabel(":                "), BorderLayout.EAST); right.add(rowPanel1); right.add(ttt1); rowPanel1.setBackground(Color.WHITE);
        JLabel tt2 = new JLabel("Họ và Tên"); 
        JPanel rowPanel2 = new JPanel(new BorderLayout()); rowPanel2.add(tt2, BorderLayout.WEST); rowPanel2.add(new JLabel(":                "), BorderLayout.EAST); right.add(rowPanel2); right.add(ttt2); rowPanel2.setBackground(Color.WHITE);
        JLabel tt3 = new JLabel("Chức Vụ");
        JPanel rowPanel3 = new JPanel(new BorderLayout()); rowPanel3.add(tt3, BorderLayout.WEST); rowPanel3.add(new JLabel(":                "), BorderLayout.EAST); right.add(rowPanel3); right.add(ttt3); rowPanel3.setBackground(Color.WHITE);
        JLabel tt4 = new JLabel("Số điện thoại");
        JPanel rowPanel4 = new JPanel(new BorderLayout()); rowPanel4.add(tt4, BorderLayout.WEST); rowPanel4.add(new JLabel(":                "), BorderLayout.EAST); right.add(rowPanel4); right.add(ttt4); rowPanel4.setBackground(Color.WHITE);
        JLabel tt5 = new JLabel("Email");
        JPanel rowPanel5 = new JPanel(new BorderLayout()); rowPanel5.add(tt5, BorderLayout.WEST); rowPanel5.add(new JLabel(":                "), BorderLayout.EAST); right.add(rowPanel5); right.add(ttt5); rowPanel5.setBackground(Color.WHITE);

        JButton updatePasswordButton = new JButton("Cập Nhật Mật Khẩu"); updatePasswordButton.setBackground(new Color(25, 255, 175));
        updatePasswordButton.addActionListener(e -> updatePassword());
        right.add(updatePasswordButton); 

        JButton logoutButton = new JButton("Đăng Xuất"); logoutButton.setBackground(new Color(25, 200, 255));
        logoutButton.addActionListener(e -> logout());
        right.add(logoutButton);

        right_big.add(right, BorderLayout.CENTER);
        JPanel right_down = new JPanel();
        right_down.setPreferredSize(new Dimension(300,300));
        animation = new JLabel();
        animation.setHorizontalAlignment(JLabel.CENTER);
        right_down.add(animation);
        right_big.add(right_down, BorderLayout.SOUTH);
        frame.add(right_big, BorderLayout.EAST);
        loadFrames();
        startAnimation();
        // Chuc nang
        JPanel leftPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        Border functionBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
        TitledBorder functionTitle = BorderFactory.createTitledBorder(functionBorder, "Chức Năng");
        functionTitle.setTitleFont(new Font("Arial", Font.BOLD, 20));
        leftPanel.setBorder(functionTitle);

        dashboardButton = createButton("Dashboard",  new Color(255, 80, 80));
        dashboardButton.addActionListener(e -> showDashboard());
        leftPanel.add(dashboardButton);
        
        roomManagementButton = createButton("Quản Lý Phòng",  new Color(255, 150, 150));
        roomManagementButton.addActionListener(e -> showRoomManagement());
        leftPanel.add(roomManagementButton);

        buildingManagementButton = createButton("Quản Lý Tòa Nhà",  new Color(255, 150, 150));
        buildingManagementButton.addActionListener(e -> showBuildingManagement());
        leftPanel.add(buildingManagementButton);

        studentManagementButton = createButton("Quản Lý Sinh Viên",  new Color(255, 150, 150));
        studentManagementButton.addActionListener(e -> showStudentManagement());
        leftPanel.add(studentManagementButton);

        invoiceManagementButton = createButton("Quản Lý Hóa Đơn",  new Color(255, 150, 150));
        invoiceManagementButton.addActionListener(e -> showInvoiceManagement());
        leftPanel.add(invoiceManagementButton);

        reportButton = createButton("Báo Cáo và Thống Kê",  new Color(255, 150, 150));
        reportButton.addActionListener(e -> showReports());
        leftPanel.add(reportButton);

        frame.add(leftPanel, BorderLayout.WEST);

        // load tt
        loadManagerInfo();
        tableFont = new Font("Arial", Font.PLAIN, 18);
        cell = new DefaultTableCellRenderer();
        cell.setHorizontalAlignment(JLabel.CENTER);
        
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder(null, " ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16)));
        showDashboard();

        frame.add(centerPanel, BorderLayout.CENTER);
        
       // new javafx.embed.swing.JFXPanel();
        frame.setVisible(true);
    }
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
    private void resetButtonColors() {
        dashboardButton.		setBackground( new Color(255, 150, 150));
        roomManagementButton.	setBackground( new Color(255, 150, 150));
        studentManagementButton.setBackground( new Color(255, 150, 150));
        buildingManagementButton.setBackground( new Color(255, 150, 150));
        invoiceManagementButton.setBackground( new Color(255, 150, 150));
        reportButton.			setBackground( new Color(255, 150, 150));
    }
    private void loadManagerInfo() {
        String sql = "SELECT * FROM manager WHERE AdminID = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, adminID);
            ResultSet rs = stmt.executeQuery();	
            if (rs.next()) {
                ttt1.setText(rs.getString("AdminID"));
                ttt2.setText(rs.getString("FullName"));
                ttt3.setText(rs.getString("Position"));
                ttt4.setText(rs.getString("PhoneNumber"));
                ttt5.setText(rs.getString("Email"));
            } else {
                JOptionPane.showMessageDialog(frame, "Không tìm thấy thông tin quản lý.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi khi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

	    private void updatePassword() {
	    	JFrame framepassword = new JFrame("Cập nhật mật khẩu");
	    	framepassword.setSize(500, 330);
	    	framepassword.setLocationRelativeTo(null);
	    	framepassword.setLayout(new BorderLayout());
	    	framepassword.setResizable(false);
	    	JPanel right = new JPanel(); right.setBackground(Color.WHITE);
	    	right.setPreferredSize(new Dimension(100,50));
	    	JPanel left = new JPanel();  left.setBackground(Color.WHITE);
	    	left.setPreferredSize(new Dimension(100,50));
	    	JPanel up = new JPanel(); 	 up.setBackground(Color.WHITE);
	    	JLabel cnmk = new JLabel("<html><u>Cập Nhật Mật Khẩu</u></html>");
	    	cnmk.setFont(new Font("Arial", Font.ITALIC, 30));
	    	cnmk.setForeground(new Color(0, 0, 150));
	    	up.add(cnmk);
	    	JPanel down = new JPanel(new FlowLayout()); down.setBackground(Color.WHITE);
	    	
	    	JPanel center = new JPanel(new GridLayout(4, 2, 10, 10)); center.setBackground(Color.WHITE);
	    	center.add(new JLabel("AdminID :"));
	        JLabel adminIDField = new JLabel(ttt1.getText());
	        center.add(adminIDField);
	        
	        center.add(new JLabel("Mật khẩu cũ :"));
	        JPasswordField oldPasswordField = new JPasswordField("");
	        center.add(oldPasswordField);
	
	        center.add(new JLabel("Mật khẩu mới :"));
	        JPasswordField newPasswordField = new JPasswordField("");
	        center.add(newPasswordField);
	
	        center.add(new JLabel("Nhập lại mật khẩu mới :"));
	        JPasswordField confirmPasswordField = new JPasswordField("");
	        center.add(confirmPasswordField);
	
	    	
	        JButton saveButton = new JButton("Lưu");
	        saveButton.setBackground(new Color(255, 255, 52));
	        saveButton.setForeground(Color.BLACK);
	        saveButton.setFont(new Font("Arial", Font.BOLD, 12));
	        saveButton.setOpaque(true);
	        saveButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	        saveButton.setFocusPainted(false);
	        saveButton.setPreferredSize(new Dimension(150, 50));
	        saveButton.addActionListener(e -> {
	            String oldPassword = new String(oldPasswordField.getPassword()).trim();
	            String newPassword = new String(newPasswordField.getPassword()).trim();
	            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
	
	            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
	                JOptionPane.showMessageDialog(framepassword, "Không được để trống bất kỳ trường nào.", "Lỗi", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	
	            if (!newPassword.equals(confirmPassword)) {
	                JOptionPane.showMessageDialog(framepassword, "Mật khẩu mới và mật khẩu nhập lại không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	
	            updatePassword(oldPassword, newPassword);
	            framepassword.dispose();
	        });
	        down.add(saveButton);
	        framepassword.add(right, BorderLayout.EAST);
	        framepassword.add(left, BorderLayout.WEST);
	        framepassword.add(up, BorderLayout.NORTH);
	        framepassword.add(down, BorderLayout.SOUTH);
	
	        framepassword.add(center, BorderLayout.CENTER);
	        framepassword.setVisible(true);
	    }
		    private void updatePassword(String oldPassword, String newPassword) {
		        String sql = "SELECT Password FROM manager WHERE AdminID = ?";
		        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
		             PreparedStatement stmt = conn.prepareStatement(sql)) {
		
		            stmt.setString(1, adminID);
		            ResultSet rs = stmt.executeQuery();
		
		            if (rs.next()) {
		                if (!oldPassword.equals(rs.getString("Password"))) {
		                    JOptionPane.showMessageDialog(frame, "Mật khẩu cũ không đúng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
		
		                String updateSql = "UPDATE manager SET Password = ? WHERE AdminID = ?";
		                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
		                    updateStmt.setString(1, newPassword);
		                    updateStmt.setString(2, adminID);
		
		                    int rowsUpdated = updateStmt.executeUpdate();
		                    if (rowsUpdated > 0) {
		                        JOptionPane.showMessageDialog(frame, "Cập nhật mật khẩu thành công.");
		                    } else {
		                        JOptionPane.showMessageDialog(frame, "Không thể cập nhật mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            } else {
		                JOptionPane.showMessageDialog(frame, "Không tìm thấy tài khoản quản lý.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(frame, "Lỗi cập nhật mật khẩu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
    private void logout() {
        new UILOGIN();
        frame.dispose();
    }
    private void showDashboard() {
        resetButtonColors();
        dashboardButton.setBackground(new Color(255, 80, 80));

        centerPanel.removeAll();
        JLabel dashboardLabel = new JLabel("TỔNG QUAN HỆ THỐNG", JLabel.CENTER);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 28));
        centerPanel.add(dashboardLabel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        statsPanel.add(createStatPanel("Tổng số sinh viên", fetchData("SELECT COUNT(*) FROM Student")));
        statsPanel.add(createStatPanel("Số phòng trống", fetchData("SELECT COUNT(*) FROM Room WHERE CurrentOccupants < Capacity")));
        statsPanel.add(createStatPanel("Số tòa nhà", fetchData("SELECT COUNT(*) FROM building")));
        statsPanel.add(createStatPanel("Doanh thu", fetchData("SELECT FORMAT(SUM(Amount), 0) AS FormattedRevenue FROM Invoice WHERE PaymentStatus = 'Paid' AND (Year(IssueDate) = 2024 OR Year(IssueDate) = 2025)")));
        statsPanel.add(createStatPanel("Số sinh viên nam", fetchData("SELECT COUNT(*) FROM Student WHERE Gender = 'Male'")));
        statsPanel.add(createStatPanel("Số sinh viên nữ", fetchData("SELECT COUNT(*) FROM Student WHERE Gender = 'Female'")));

        centerPanel.add(statsPanel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
	    private JPanel createStatPanel(String title, String value) {
	        JPanel panel = new JPanel(new BorderLayout());
	        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
	        titleLabel.setPreferredSize(new Dimension(100, 37));
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
	        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
	        valueLabel.setFont(new Font("Arial", Font.BOLD, 25));
	        valueLabel.setForeground(Color.BLUE);
	        JPanel down = new JPanel(); down.setPreferredSize(new Dimension(30, 30));
	        panel.add(titleLabel, BorderLayout.NORTH);
	        panel.add(valueLabel, BorderLayout.CENTER);
	        panel.add(down, 	  BorderLayout.SOUTH);
	        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        return panel;
	    }
	    private String fetchData(String query) {
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            if (rs.next()) {
	                return rs.getString(1);
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	        return "0";
	    }

    private void showRoomManagement() {
    	resetButtonColors();
    	roomManagementButton.setBackground(new Color(240, 80, 80));
    	centerPanel.removeAll();
        JLabel titleLabel = new JLabel("<html><u>Quản Lý Phòng</u></html>", JLabel.CENTER);
   
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        headerFont = new Font("Arial", Font.BOLD, 20);
        header.setFont(headerFont);
        table.setFont(tableFont);
        table.setRowHeight(30);

        model.addColumn("Phòng");
        model.addColumn("Kiểu Phòng");
        model.addColumn("Occupancy");
        model.addColumn("Giá Tiền");
        model.addColumn("Tòa Nhà");
        
        table.getColumnModel().getColumn(0).setCellRenderer(cell); 
        table.getColumnModel().getColumn(1).setCellRenderer(cell); 
        table.getColumnModel().getColumn(2).setCellRenderer(cell); 
        table.getColumnModel().getColumn(3).setCellRenderer(cell);
        table.getColumnModel().getColumn(4).setCellRenderer(cell);
        
        String query = "SELECT r.RoomID, r.RoomType, CONCAT(r.CurrentOccupants, '/', r.Capacity) AS Occupancy, FORMAT(r.Price, 0) AS FormattedPrice, b.Name AS BuildingName FROM Room r JOIN Building b ON r.BuildingID = b.BuildingID;";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

               ResultSetMetaData metaData = rs.getMetaData();
               int columnCount = metaData.getColumnCount();

               while (rs.next()) {
                   Object[] row = new Object[columnCount];
                   for (int i = 1; i <= columnCount; i++) {
                       row[i - 1] = rs.getObject(i);
                   }
                   model.addRow(row);
               }
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
           }
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        int columnIndex = table.getColumnModel().getColumnIndex("Phòng");
        sorter.setComparator(columnIndex, (o1, o2) -> {
            try {
                Integer num1 = Integer.parseInt(o1.toString().trim());
                Integer num2 = Integer.parseInt(o2.toString().trim());
                return Integer.compare(num1, num2);
            } catch (NumberFormatException e) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        sorter.setSortKeys(List.of(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
        table.setRowSorter(sorter);
      
	       JPanel controlPanel = new JPanel(new GridLayout(1, 2, 10, 10));
	        
	       addRoomButton = new JButton("Thêm Phòng");
	       addRoomButton.addActionListener(e -> showAddRoomFrame());
	       addRoomButton.setBackground(new Color(30, 255, 196));
	       addRoomButton.setForeground(Color.BLACK);
	       addRoomButton.setFont(new Font("Arial", Font.BOLD, 14));
	       addRoomButton.setOpaque(true);
	       addRoomButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	       addRoomButton.setFocusPainted(false);
	       addRoomButton.setPreferredSize(new Dimension(150, 50));
	       controlPanel.add(addRoomButton);
	       
	       editRoomButton = new JButton("Sửa Phòng");
	       editRoomButton.addActionListener(e -> showEditRoomFrame(model, table));
	       editRoomButton.setBackground(new Color(88, 250, 255));
	       editRoomButton.setForeground(Color.BLACK);
	       editRoomButton.setFont(new Font("Arial", Font.BOLD, 14));
	       editRoomButton.setOpaque(true);
	       editRoomButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	       editRoomButton.setFocusPainted(false);
	       editRoomButton.setPreferredSize(new Dimension(150, 50));
	       controlPanel.add(editRoomButton);
	       
	       roomDetailsButton  = createButton_Student("Hiển Thị Thông Tin");
	       roomDetailsButton.addActionListener(e -> showRoomDetailsFrame(table.getSelectedRow(), model, table));
	       roomDetailsButton.setBackground(new Color(98, 230, 255));
	       roomDetailsButton.setForeground(Color.BLACK);
	       roomDetailsButton.setFont(new Font("Arial", Font.BOLD, 14));
	       roomDetailsButton.setOpaque(true);
	       roomDetailsButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	       roomDetailsButton.setFocusPainted(false);
	       roomDetailsButton.setPreferredSize(new Dimension(150, 50));
	       controlPanel.add(roomDetailsButton);
           JScrollPane scrollPane = new JScrollPane(table);
           centerPanel.add(controlPanel, BorderLayout.SOUTH);
           centerPanel.add(scrollPane, BorderLayout.CENTER);
           
           centerPanel.revalidate();
           centerPanel.repaint();
    }
	    private void showAddRoomFrame() {
	    	JFrame addRoomFrame = new JFrame("Thêm Phòng");
	    	addRoomFrame.setSize(500, 330);
	    	addRoomFrame.setLocationRelativeTo(null);
	    	addRoomFrame.setLayout(new BorderLayout());
	    	addRoomFrame.setResizable(false);
	    	
	    	JPanel right = new JPanel(); right.setBackground(Color.WHITE);
	    	right.setPreferredSize(new Dimension(100,50));
	    	JPanel left = new JPanel();  left.setBackground(Color.WHITE);
	    	left.setPreferredSize(new Dimension(100,50));
	    	JPanel up = new JPanel(); 	 up.setBackground(Color.WHITE);
	    	JLabel tp = new JLabel("<html><u>Thêm Phòng</u></html>");
	    	tp.setFont(new Font("Arial", Font.ITALIC, 30));
	    	tp.setForeground(new Color(0, 0, 150));
	    	up.add(tp);
	    	
	        JPanel center = new JPanel(new GridLayout(5, 2, 10, 10));
	        center.setBackground(Color.white);
	        JLabel roomIDLabel = new JLabel("Mã Phòng:"); 	
	        JLabel roomIDValue = new JLabel(); 				
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = 'room'");
	            if (rs.next()) {
	                roomIDValue.setText(rs.getString(1));
	            }
	        } catch (SQLException e) {
	            roomIDValue.setText("Không thể lấy mã phòng");
	        }
	
	        JLabel roomTypeLabel = new JLabel("Loại Phòng:"); 
	        JComboBox<String> roomTypeComboBox = new JComboBox<>(new String[]{"Đơn", "Đôi", "Tập thể"}); roomTypeComboBox.setBackground(Color.WHITE);
	
	        JLabel capacityLabel = new JLabel("Sức Chứa:"); 
	        JLabel capacityValue = new JLabel("1"); 		
	        
	        JLabel buildingLabel = new JLabel("Tòa Nhà:");  
	        JComboBox<String> buildingComboBox = new JComboBox<>();  buildingComboBox.setBackground(Color.WHITE);
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT BuildingID, Name FROM Building");
	            while (rs.next()) {
	                buildingComboBox.addItem(rs.getString("BuildingID") + " - " + rs.getString("Name"));
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(addRoomFrame, "Không thể tải danh sách tòa nhà: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	
	        JLabel priceLabel = new JLabel("Giá:");
	        JTextField priceField = new JTextField(); priceField.setBackground(Color.WHITE);
	
	        roomTypeComboBox.addActionListener(e -> {	
	            String selectedType = (String) roomTypeComboBox.getSelectedItem();
	            if ("Đơn".equals(selectedType)) {
	                capacityValue.setText("1");
	            } else if ("Đôi".equals(selectedType)) {
	                capacityValue.setText("2");
	            } else if ("Tập thể".equals(selectedType)) {
	                capacityValue.setText("4");
	            }
	        });
	
	        JButton addButton = new JButton("Thêm");
	        addButton.setBackground(new Color(102, 255, 178));
	        addButton.setForeground(Color.BLACK);
	        addButton.setFont(new Font("Arial", Font.BOLD, 12));
	        addButton.setOpaque(true);
	        addButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	        addButton.setFocusPainted(false);
	        addButton.setPreferredSize(new Dimension(150, 50));
	        addButton.addActionListener(e -> {
	            String roomType = (String) roomTypeComboBox.getSelectedItem();
	            int capacity = Integer.parseInt(capacityValue.getText());
	            String building = (String) buildingComboBox.getSelectedItem();
	            String buildingID = building.split(" - ")[0];
	            String price = priceField.getText();
	
	            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	                PreparedStatement stmt = conn.prepareStatement(
	                        "INSERT INTO Room (RoomType, Capacity, BuildingID, Price) VALUES (?, ?, ?, ?)");
	                stmt.setString(1, roomType);
	                stmt.setInt(2, capacity);
	                stmt.setString(3, buildingID);
	                stmt.setString(4, price);
	                stmt.executeUpdate();
	                
	                JOptionPane.showMessageDialog(addRoomFrame, "Thêm phòng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                addRoomFrame.dispose();
	                showRoomManagement();
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(addRoomFrame, "Lỗi khi thêm phòng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        });
	
	        center.add(roomIDLabel);
	        center.add(roomIDValue);
	        center.add(roomTypeLabel);
	        center.add(roomTypeComboBox);
	        center.add(capacityLabel);
	        center.add(capacityValue);
	        center.add(buildingLabel);
	        center.add(buildingComboBox);
	        center.add(priceLabel);
	        center.add(priceField);
	        
	        JPanel down = new JPanel(); down.setBackground(Color.white);
	        down.add(addButton);
	        
	        addRoomFrame.add(up, BorderLayout.NORTH);
	        addRoomFrame.add(left, BorderLayout.WEST);
	        addRoomFrame.add(right, BorderLayout.EAST);
	        addRoomFrame.add(center, BorderLayout.CENTER);
	        addRoomFrame.add(down, BorderLayout.SOUTH);
	
	        addRoomFrame.setVisible(true);
	        
	    }
	    private void showEditRoomFrame(DefaultTableModel model, JTable table) {
	    int selectedRow = table.getSelectedRow();
	    if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một phòng để sửa.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
	    	JFrame editRoomFrame = new JFrame("Sửa Phòng");
	        editRoomFrame.setSize(500, 330);
	        editRoomFrame.setLocationRelativeTo(null);
	        editRoomFrame.setLayout(new BorderLayout());
	        editRoomFrame.setResizable(false);

	        JPanel right = new JPanel();
	        right.setBackground(Color.WHITE);
	        right.setPreferredSize(new Dimension(100, 50));
	        JPanel left = new JPanel();
	        left.setBackground(Color.WHITE);
	        left.setPreferredSize(new Dimension(100, 50));
	        JPanel up = new JPanel();
	        up.setBackground(Color.WHITE);
	        JLabel tp = new JLabel("<html><u>Sửa Phòng</u></html>");
	        tp.setFont(new Font("Arial", Font.ITALIC, 30));
	        tp.setForeground(new Color(0, 0, 150));
	        up.add(tp);

	        JPanel center = new JPanel(new GridLayout(5, 2, 10, 10));
	        center.setBackground(Color.WHITE);

	        JLabel roomIDLabel = new JLabel("Mã Phòng:");
	        JLabel roomIDValue = new JLabel();
	        
	        int modelRow = table.convertRowIndexToModel(selectedRow);
            String roomID = model.getValueAt(modelRow, 0).toString(); 
            roomIDValue.setText(roomID);
	      

	        JLabel roomTypeLabel = new JLabel("Kiểu Phòng:");
	        JComboBox<String> roomTypeComboBox = new JComboBox<>(new String[]{"Đơn", "Đôi", "Tập thể"});
	        roomTypeComboBox.setBackground(Color.WHITE);
	        
	        JLabel capacityLabel = new JLabel("Sức Chứa:");
	        JLabel capacityValue = new JLabel("1"); 
	        
	        roomTypeComboBox.addActionListener(e -> {	
	            String selectedType = (String) roomTypeComboBox.getSelectedItem();
	            if ("Đơn".equals(selectedType)) {
	                capacityValue.setText("1");
	            } else if ("Đôi".equals(selectedType)) {
	                capacityValue.setText("2");
	            } else if ("Tập thể".equals(selectedType)) {
	                capacityValue.setText("4");
	            }
	        });
	        JLabel priceLabel = new JLabel("Giá Tiền:");
	        JTextField priceField = new JTextField(); priceField.setBackground(Color.WHITE);

	        JLabel buildingLabel = new JLabel("Tòa Nhà:");
	        JComboBox<String> buildingComboBox = new JComboBox<>(); buildingComboBox.setBackground(Color.WHITE);
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT BuildingID, Name FROM Building");
	            while (rs.next()) {
	                buildingComboBox.addItem(rs.getString("BuildingID") + " - " + rs.getString("Name"));
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(editRoomFrame, "Không thể tải danh sách tòa nhà: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
 
	        JButton editButton = new JButton("Sửa");
	        editButton.setBackground(new Color(88, 250, 255));
	        editButton.setForeground(Color.BLACK);
	        editButton.setFont(new Font("Arial", Font.BOLD, 12));
	        editButton.setOpaque(true);
	        editButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	        editButton.setFocusPainted(false);
	        editButton.setPreferredSize(new Dimension(150, 50));
	        editButton.addActionListener(e -> {
	            String roomIDString = roomIDValue.getText();
	            String roomType = (String) roomTypeComboBox.getSelectedItem();
	            int capacity = Integer.parseInt(capacityValue.getText());
	            String price = priceField.getText();
	            String building = (String) buildingComboBox.getSelectedItem();
	            String buildingID = building.split(" - ")[0];
	       try {
	    	   double priceDouble = Double.parseDouble(price);
	           if (priceDouble < 0) {
	               JOptionPane.showMessageDialog(editRoomFrame, "Giá tiền phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	               return;
	           }
	       
	            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	                conn.setAutoCommit(false);

	                  PreparedStatement checkStmt = conn.prepareStatement("SELECT CurrentOccupants FROM Room WHERE RoomID = ?");
	                checkStmt.setString(1, roomIDString);
	                ResultSet rs = checkStmt.executeQuery();
	                if (rs.next()) {
	                    int currentOccupants = rs.getInt("CurrentOccupants");
	                    if (currentOccupants > capacity) {
	                        JOptionPane.showMessageDialog(editRoomFrame, "Số người hiện tại vượt quá sức chứa mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                }

	                PreparedStatement updateStmt = conn.prepareStatement(
	                        "UPDATE Room SET RoomType = ?, Capacity = ?, Price = ?, BuildingID = ? WHERE RoomID = ?");
	                updateStmt.setString(1, roomType);
	                updateStmt.setInt(2, capacity);
	                updateStmt.setDouble(3, priceDouble);
	                updateStmt.setString(4, buildingID);
	                updateStmt.setString(5, roomIDString);
	                updateStmt.executeUpdate();

	                conn.commit();
	                JOptionPane.showMessageDialog(editRoomFrame, "Cập nhật phòng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                editRoomFrame.dispose();
	                showRoomManagement();
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(editRoomFrame, "Lỗi khi cập nhật phòng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	            } 
	       } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(editRoomFrame, "Giá tiền phải là một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        });

	        center.add(roomIDLabel);
	        center.add(roomIDValue);
	        center.add(roomTypeLabel);
	        center.add(roomTypeComboBox);
	        center.add(capacityLabel);
	        center.add(capacityValue);
	        center.add(priceLabel);
	        center.add(priceField);
	        center.add(buildingLabel);
	        center.add(buildingComboBox);
	        JPanel down = new JPanel(); down.setBackground(Color.WHITE);
	        down.add(editButton);
	        editRoomFrame.add(up, BorderLayout.NORTH);
	        editRoomFrame.add(left, BorderLayout.WEST);
	        editRoomFrame.add(right, BorderLayout.EAST);
	        editRoomFrame.add(center, BorderLayout.CENTER);
	        editRoomFrame.add(down, BorderLayout.SOUTH);

	        editRoomFrame.setVisible(true);
        }
	    }	
	    private void showRoomDetailsFrame(int selectedRow, DefaultTableModel model, JTable table) {
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một phòng.", "Lỗi", JOptionPane.WARNING_MESSAGE);
	            return;
	        } else {
	        	
	        int modelRow = table.convertRowIndexToModel(selectedRow);	
	        String roomID = model.getValueAt(modelRow, 0).toString();
	        
	        JFrame roomDetailsFrame = new JFrame("Thông Tin Phòng");
	        roomDetailsFrame.setSize(1080, 500);
	        roomDetailsFrame.setLocationRelativeTo(null);
	        roomDetailsFrame.setLayout(new BorderLayout());
	        
	        JLabel title = new JLabel("<html><u>Thông Tin Phòng</u></html>");
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setFont(new Font("Arial", Font.ITALIC, 30));
	        title.setForeground(new Color(80, 80, 255));
	        title.setPreferredSize(new Dimension(200, 50));
	        roomDetailsFrame.add(title, BorderLayout.NORTH);
	        
	        JPanel InfoPanel = new JPanel(new BorderLayout());
	        JPanel roomInfoPanel_left = new JPanel();
	        
	        JPanel roomInfoPanel = new JPanel(new GridLayout(5, 2, 10, 10)); roomInfoPanel_left.add(roomInfoPanel);
	        JLabel roomIDLabel = new JLabel("Mã Phòng");
	        JLabel roomTypeLabel = new JLabel("Loại Phòng");
	        JLabel buildingLabel = new JLabel("Tòa Nhà");
	        JLabel priceLabel = new JLabel("Giá");
	
	        JLabel roomIDValue = new JLabel();
	        JLabel roomTypeValue = new JLabel();
	        JLabel buildingValue = new JLabel();
	        JLabel priceValue = new JLabel();
	        
	        DefaultTableModel model1 = new DefaultTableModel();
	        JTable studentTable = new JTable(model1);
	        JTableHeader header = studentTable.getTableHeader();
	        headerFont = new Font("Arial", Font.BOLD, 18);
	        header.setFont(headerFont);
	        Font tableFont1 = new Font("Arial", Font.PLAIN, 16);
	        studentTable.setFont(tableFont1);
	        studentTable.setRowHeight(30);

	        model1.addColumn("ID Sinh Viên");
	        model1.addColumn("Họ và Tên");
	        model1.addColumn("Email");
	        model1.addColumn("Số Điện Thoại");
	        model1.addColumn("Ngày Sinh");
	        model1.addColumn("Giới Tính");
	        model1.addColumn("Lớp");
	        
	        studentTable.getColumnModel().getColumn(0).setPreferredWidth(100);
	        studentTable.getColumnModel().getColumn(1).setPreferredWidth(130);
	        studentTable.getColumnModel().getColumn(2).setPreferredWidth(200);
	        studentTable.getColumnModel().getColumn(3).setPreferredWidth(100);
	        studentTable.getColumnModel().getColumn(4).setPreferredWidth(80);
	        studentTable.getColumnModel().getColumn(5).setPreferredWidth(75);
	        studentTable.getColumnModel().getColumn(6).setPreferredWidth(70);

	        studentTable.getColumnModel().getColumn(0).setCellRenderer(cell); 
	        studentTable.getColumnModel().getColumn(3).setCellRenderer(cell); 
	        studentTable.getColumnModel().getColumn(4).setCellRenderer(cell); 
	        studentTable.getColumnModel().getColumn(5).setCellRenderer(cell); 
	        studentTable.getColumnModel().getColumn(6).setCellRenderer(cell); 

	        
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	            PreparedStatement stmt = conn.prepareStatement("SELECT r.RoomType, FORMAT(r.Price, 0) AS FormattedPrice, b.Name FROM Room r JOIN building b ON r.BuildingID = b.BuildingID WHERE RoomID = ?");
	            stmt.setString(1, roomID);
	            ResultSet rs = stmt.executeQuery();
	
	            if (rs.next()) {
	                roomIDValue.setText(":  " + roomID);
	                roomTypeValue.setText(":  " + rs.getString("RoomType"));
	                priceValue.setText(":  " + rs.getString("FormattedPrice"));
	                buildingValue.setText(":  " + rs.getString("Name"));
	            }
	            PreparedStatement studentStmt = conn.prepareStatement(
	                    "SELECT s.IDSinhVien, s.FullName, s.Email, s.PhoneNumber, s.DateOfBirth, s.Gender, s.Class "
	                    + "FROM student s JOIN contract t ON s.StudentID = t.StudentID "
	                    + "WHERE RoomID = ?");
	            studentStmt.setString(1, roomID);
	            ResultSet studentRs = studentStmt.executeQuery();
	
	            model1.setRowCount(0); // Clear existing rows
	            while (studentRs.next()) {
	                model1.addRow(new Object[]{
	                        studentRs.getString("IDSinhVien"),
	                        studentRs.getString("FullName"),
	                        studentRs.getString("Email"),
	                        studentRs.getString("PhoneNumber"),
	                        studentRs.getString("DateOfBirth"),
	                        studentRs.getString("Gender"),
	                        studentRs.getString("Class")
	                });
	            }
	
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(roomDetailsFrame, "Lỗi khi tải thông tin phòng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	        
	        roomInfoPanel.add(roomIDLabel);
	        roomInfoPanel.add(roomIDValue);
	        roomInfoPanel.add(roomTypeLabel);
	        roomInfoPanel.add(roomTypeValue);
	        roomInfoPanel.add(buildingLabel);
	        roomInfoPanel.add(buildingValue);
	        roomInfoPanel.add(priceLabel);
	        roomInfoPanel.add(priceValue);
	
	
	        JScrollPane scrollPane = new JScrollPane(studentTable);
	        InfoPanel.add(roomInfoPanel_left, BorderLayout.NORTH);
	        InfoPanel.add(scrollPane, BorderLayout.CENTER);
	        roomDetailsFrame.add(InfoPanel, BorderLayout.CENTER);
	      
	        roomDetailsFrame.setVisible(true);
	        }
	    }
    private JButton createButton_Student(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(250, 255, 87));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }
    private void showStudentManagement() {
    	resetButtonColors();
        studentManagementButton.setBackground(new Color(240, 80, 80));
         
    	centerPanel.removeAll();
    	JPanel center = new JPanel(new BorderLayout());
    	JPanel up = new JPanel(new BorderLayout());
    	JPanel searchPanel = new JPanel();
    	
    	searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
    	searchPanel.setPreferredSize(new Dimension(300, 30));
        JTextField searchField = new JTextField();
        searchField.setBorder(BorderFactory.createLineBorder(new Color(64, 120, 246), 2));
        ImageIcon searchIcon1 = new ImageIcon(getClass().getResource("/UIimage/search.png"));        
        Image searchIcon2 = searchIcon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon searchIcon3 = new ImageIcon(searchIcon2);
        JButton searchButton = new JButton(); 
        
        searchButton.setIcon(searchIcon3);
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        searchButton.setContentAreaFilled(false);

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        up.add(searchPanel, BorderLayout.WEST);
    	
        JLabel titleLabel = new JLabel("<html><u>Quản Lý Sinh Viên</u></html>", JLabel.CENTER);
   
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        headerFont = new Font("Arial", Font.BOLD, 13);
        header.setFont(headerFont);
        Font tableFont1 = new Font("Arial", Font.PLAIN, 12);
        table.setFont(tableFont1);
        table.setRowHeight(30);
        
        model.addColumn("ID Sinh Viên");
        model.addColumn("Họ và Tên");
        model.addColumn("Ngày Sinh");
        model.addColumn("Giới Tính");
        model.addColumn("Lớp");
        model.addColumn("Khoa");
        model.addColumn("Số Điện Thoại");
        model.addColumn("Phòng");
        
        table.getColumnModel().getColumn(0).setPreferredWidth(125); 
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(100); 
        table.getColumnModel().getColumn(3).setPreferredWidth(80); 
        table.getColumnModel().getColumn(4).setPreferredWidth(80); 
        table.getColumnModel().getColumn(5).setPreferredWidth(250); 
        table.getColumnModel().getColumn(6).setPreferredWidth(150); 
        table.getColumnModel().getColumn(7).setPreferredWidth(120); 
        
        table.getColumnModel().getColumn(0).setCellRenderer(cell);
        table.getColumnModel().getColumn(2).setCellRenderer(cell);
        table.getColumnModel().getColumn(3).setCellRenderer(cell);
        table.getColumnModel().getColumn(4).setCellRenderer(cell);
        table.getColumnModel().getColumn(6).setCellRenderer(cell);
        table.getColumnModel().getColumn(7).setCellRenderer(cell);
        
        String query = "SELECT s.IDSinhVien, s.FullName, s.DateOfBirth, s.Gender, s.Class, s.Department, s.PhoneNumber, " +
                       "IFNULL(r.RoomID, 'NotInDormitory') AS RoomStatus " +
                       "FROM Student s LEFT JOIN Contract c ON s.StudentID = c.StudentID " +
                       "LEFT JOIN Room r ON c.RoomID = r.RoomID;";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("IDSinhVien"),
                        rs.getString("FullName"),
                        rs.getString("DateOfBirth"),
                        rs.getString("Gender"),
                        rs.getString("Class"),
                        rs.getString("Department"),
                        rs.getString("PhoneNumber"),
                        rs.getString("RoomStatus")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText();
                filterTable(model, searchText);
            }
        });

        searchButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một sinh viên để xem chi tiết.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int modelRow = table.convertRowIndexToModel(selectedRow); 
            String studentID = model.getValueAt(modelRow, 0).toString(); 
            showStudentDetails(studentID);
        });

        
        JPanel controlPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        
        sortByIdButton = createButton_Student("Sắp Xếp Theo ID");
        sortByIdButton.addActionListener(e -> sortTable(table, "ID Sinh Viên", sortByIdButton));
        
        controlPanel.add(sortByIdButton);
      
        sortByRoomButton  = createButton_Student("Sắp Xếp Theo Phòng");
        sortByRoomButton.addActionListener(e -> sortTable(table, "Phòng", sortByRoomButton));
        controlPanel.add(sortByRoomButton);

        filterNoRoomButton = createButton_Student("Hiển Thị Không Phòng");
        filterNoRoomButton.addActionListener(e -> filterNoRoom(table, filterNoRoomButton));
        controlPanel.add(filterNoRoomButton);
        
        kickStudentButton = createButton_Student("Đuổi Sinh Viên");
        kickStudentButton.addActionListener(e -> kickStudent(model, table));
        controlPanel.add(kickStudentButton);
        
        deleteStudentButton = createButton_Student("Xóa Sinh Viên");
        deleteStudentButton.addActionListener(e -> deleteSelectedStudent(model, table));
        controlPanel.add(deleteStudentButton);

        
        center.add(up, BorderLayout.NORTH);
        center.add(new JScrollPane(table), BorderLayout.CENTER);
        centerPanel.add(center, BorderLayout.CENTER);
        centerPanel.add(controlPanel, BorderLayout.SOUTH);

        centerPanel.revalidate();
        centerPanel.repaint();
        sortByIdButton.doClick();
    }
    private void filterTable(DefaultTableModel model, String searchText) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT s.IDSinhVien, s.FullName, s.DateOfBirth, s.Gender, s.Class, s.Department, s.PhoneNumber,"
                + " IFNULL(r.RoomID, 'NotInDormitory') AS RoomStatus FROM Student s LEFT JOIN Contract c ON"
                + " s.StudentID = c.StudentID LEFT JOIN Room r ON c.RoomID = r.RoomID "
                + "WHERE IDSinhVien LIKE ? OR FullName LIKE ?");
            stmt.setString(1, "%" + searchText + "%");
            stmt.setString(2, "%" + searchText + "%");
            ResultSet rs = stmt.executeQuery();

            model.setRowCount(0); 

            while (rs.next()) {
                model.addRow(new Object[]{
                	rs.getString("IDSinhVien"),
                    rs.getString("FullName"),
                    rs.getString("DateOfBirth"),
                    rs.getString("Gender"),
                    rs.getString("Class"),
                    rs.getString("Department"),
                    rs.getString("PhoneNumber"),
                    rs.getString("RoomStatus")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showStudentDetails(String studentID) {
        JFrame detailsFrame = new JFrame("Chi Tiết Sinh Viên");
        detailsFrame.setSize(1000, 330);
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setLayout(new BorderLayout());
        //detailsFrame.setResizable(false);

        JPanel up = new JPanel();
        JLabel titleLabel = new JLabel("<html><u>Chi Tiết Sinh Viên</u></html>");
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        titleLabel.setForeground(new Color(0, 0, 150));
        up.add(titleLabel);

        DefaultTableModel detailsModel = new DefaultTableModel();
        JTable detailsTable = new JTable(detailsModel); 
        JTableHeader header = detailsTable.getTableHeader();
        headerFont = new Font("Arial", Font.BOLD, 14);
        header.setFont(headerFont);
        Font tableFont1 = new Font("Arial", Font.PLAIN, 13);
        detailsTable.setFont(tableFont1);
        detailsTable.setRowHeight(30);
        
        detailsModel.addColumn("ID Sinh Viên");
        detailsModel.addColumn("Họ và Tên");
        detailsModel.addColumn("Ngày Sinh");
        detailsModel.addColumn("Giới Tính");
        detailsModel.addColumn("Lớp");
        detailsModel.addColumn("Khoa");
        detailsModel.addColumn("Số Điện Thoại");
        detailsModel.addColumn("Email");

        detailsTable.getColumnModel().getColumn(0).setPreferredWidth(100); 
        detailsTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        detailsTable.getColumnModel().getColumn(2).setPreferredWidth(80); 
        detailsTable.getColumnModel().getColumn(3).setPreferredWidth(80); 
        detailsTable.getColumnModel().getColumn(4).setPreferredWidth(70); 
        detailsTable.getColumnModel().getColumn(5).setPreferredWidth(180); 
        detailsTable.getColumnModel().getColumn(6).setPreferredWidth(110); 
        detailsTable.getColumnModel().getColumn(7).setPreferredWidth(200);
        
        detailsTable.getColumnModel().getColumn(0).setCellRenderer(cell);
        detailsTable.getColumnModel().getColumn(1).setCellRenderer(cell);
        detailsTable.getColumnModel().getColumn(2).setCellRenderer(cell);
        detailsTable.getColumnModel().getColumn(3).setCellRenderer(cell);
        detailsTable.getColumnModel().getColumn(4).setCellRenderer(cell);
        detailsTable.getColumnModel().getColumn(5).setCellRenderer(cell);
        detailsTable.getColumnModel().getColumn(6).setCellRenderer(cell);
        detailsTable.getColumnModel().getColumn(7).setCellRenderer(cell);

        
        DefaultTableModel detailsModel1 = new DefaultTableModel();
        JTable detailsTable1 = new JTable(detailsModel1); 
        JTableHeader header1 = detailsTable1.getTableHeader();
        headerFont = new Font("Arial", Font.BOLD, 16);
        header1.setFont(headerFont);
        detailsTable1.setFont(tableFont1);
        detailsTable1.setRowHeight(30);
        
        detailsModel1.addColumn("Phòng");
        detailsModel1.addColumn("Kiểu Phòng");
        detailsModel1.addColumn("Occupancy");
        detailsModel1.addColumn("Price");
        detailsModel1.addColumn("Tòa Nhà");
        detailsModel1.addColumn("Thanh Toán");

        detailsTable1.getColumnModel().getColumn(0).setCellRenderer(cell); 
        detailsTable1.getColumnModel().getColumn(1).setCellRenderer(cell); 
        detailsTable1.getColumnModel().getColumn(2).setCellRenderer(cell); 
        detailsTable1.getColumnModel().getColumn(3).setCellRenderer(cell); 
        detailsTable1.getColumnModel().getColumn(4).setCellRenderer(cell);
        detailsTable1.getColumnModel().getColumn(5).setCellRenderer(cell);
        
        boolean hasRoom = false;
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT s.IDSinhVien, s.FullName, s.DateOfBirth, s.Gender, s.Class, s.Department, s.PhoneNumber, s.Email, r.RoomID, r.RoomType, " +
                "CONCAT(r.CurrentOccupants, '/', r.Capacity) AS Occupancy, r.Price, b.Name AS BuildingName, " +
                "IF(i.PaymentStatus = 'Paid', 'Paid', 'Unpaid') AS InvoiceStatus " +
                "FROM Student s " +
                "LEFT JOIN Contract c ON s.StudentID = c.StudentID " +
                "LEFT JOIN Room r ON c.RoomID = r.RoomID " +
                "LEFT JOIN Building b ON r.BuildingID = b.BuildingID " +
                "LEFT JOIN Invoice i ON c.ContractID = i.ContractID " +
                "WHERE s.IDSinhVien = ?");
            stmt.setString(1, studentID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                detailsModel.addRow(new Object[]{
                    rs.getString("IDSinhVien"),
                    rs.getString("FullName"),
                    rs.getString("DateOfBirth"),
                    rs.getString("Gender"),
                    rs.getString("Class"),
                    rs.getString("Department"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Email")
                });
                
                String roomID = rs.getString("RoomID");
                if (roomID != null) {
                	hasRoom = true;
                	detailsModel1.addRow(new Object[]{
	                    roomID,
	                    rs.getString("RoomType"),
	                    rs.getString("Occupancy"),
	                    rs.getString("Price"),
	                    rs.getString("BuildingName"),
	                    rs.getString("InvoiceStatus")
                });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tải chi tiết sinh viên: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        JPanel tablePanel = new JPanel(new GridLayout(hasRoom ? 2 : 1, 1));
        tablePanel.add(new JScrollPane(detailsTable));
        if (hasRoom) {
            tablePanel.add(new JScrollPane(detailsTable1));
        }
        detailsFrame.add(up, BorderLayout.NORTH);
        detailsFrame.add(tablePanel, BorderLayout.CENTER);
        detailsFrame.setVisible(true);
    }
	    private void sortTable(JTable table, String column, JButton button) {
	    	TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
	        int columnIndex = table.getColumnModel().getColumnIndex(column);
	
	        if (columnIndex != -1) {
	        	if ("Phòng".equals(column)) {
	                sorter.setComparator(columnIndex, (o1, o2) -> {
	                    try {
	                        Integer num1 = Integer.parseInt(o1.toString().trim());
	                        Integer num2 = Integer.parseInt(o2.toString().trim());
	                        return Integer.compare(num1, num2);
	                    } catch (NumberFormatException e) {
	                        return o1.toString().compareTo(o2.toString());
	                    }
	                });
	            }
	            sorter.setSortKeys(List.of(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
	            table.setRowSorter(sorter);
	        } else {
	            JOptionPane.showMessageDialog(frame, "Cột để sắp xếp không tồn tại.", "Lỗi", JOptionPane.WARNING_MESSAGE);
	        }
	        
	        sortByIdButton.setBackground(new Color(250, 255, 87));
	        sortByRoomButton.setBackground(new Color(250, 255, 87));
	        filterNoRoomButton.setBackground(new Color(250, 255, 87));
	        button.setBackground(new Color(255, 191, 46));
	        
	    }
	
	    private void filterNoRoom(JTable table, JButton button) {
	    	DefaultTableModel model = (DefaultTableModel) table.getModel();
	        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	        table.setRowSorter(sorter);
	
	        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
	            @Override
	            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
	                String roomStatus = (String) entry.getValue(model.findColumn("Phòng"));
	                return "NotInDormitory".equals(roomStatus);
	            }
	        });
	        sortByIdButton.setBackground(new Color(250, 255, 87));
	        sortByRoomButton.setBackground(new Color(250, 255, 87));
	        filterNoRoomButton.setBackground(new Color(250, 255, 87));
	        button.setBackground(new Color(255, 191, 46));
	    }
	    private void kickStudent(DefaultTableModel model, JTable table) {
	    	int selectedRow = table.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sinh viên.", "Lỗi", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        int modelRow = table.convertRowIndexToModel(selectedRow);
	        String studentID = model.getValueAt(modelRow, 0).toString();
	        String roomID = model.getValueAt(modelRow, 7).toString();
	        
	        if ("NotInDormitory".equals(roomID)) {
	            JOptionPane.showMessageDialog(null, "Sinh viên không có phòng. Không thể đuổi!");
	            return;
	        }
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	            conn.setAutoCommit(false);
	            
	            // status in student
	            PreparedStatement updateStudentStmt = conn.prepareStatement(
	                    "UPDATE Student SET Status = 'NotInDormitory' WHERE IDSinhVien = ?");
	            updateStudentStmt.setString(1, studentID);
	            updateStudentStmt.executeUpdate();
	
	            // CurrentOccupants in room
	            if (roomID != null && !roomID.isEmpty() && !roomID.equals("NotInDormitory")) {
	                PreparedStatement updateRoomStmt = conn.prepareStatement(
	                        "UPDATE Room SET CurrentOccupants = CurrentOccupants - 1 WHERE RoomID = ?");
	                updateRoomStmt.setString(1, roomID);
	                updateRoomStmt.executeUpdate();
	            }
	
	            // Delete invoice in student
	            PreparedStatement deleteInvoiceStmt = conn.prepareStatement(
	                    "DELETE FROM Invoice WHERE ContractID IN (SELECT ContractID FROM Contract WHERE StudentID IN (SELECT StudentID FROM student WHERE IDSinhVien = ?))");
	            deleteInvoiceStmt.setString(1, studentID);
	            deleteInvoiceStmt.executeUpdate();
	
	            // Delete contract in student
	            PreparedStatement deleteContractStmt = conn.prepareStatement(
	                    "DELETE FROM Contract WHERE StudentID IN (SELECT StudentID FROM student WHERE IDSinhVien = ?)");
	            deleteContractStmt.setString(1, studentID);
	            deleteContractStmt.executeUpdate();
	
	            conn.commit();
	
	            JOptionPane.showMessageDialog(null, "Đã đuổi sinh viên thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Lỗi khi đuổi sinh viên: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    private void deleteSelectedStudent(DefaultTableModel model, JTable table) {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(frame, "Vui lòng chọn sinh viên để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        int reply = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn không ?", "Xóa Tài Khoản", JOptionPane.YES_NO_OPTION);
	    	if (reply == JOptionPane.YES_OPTION) {
	        String studentID = model.getValueAt(selectedRow, 0).toString();
	        String roomID = model.getValueAt(selectedRow, 7).toString();
	
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
	            conn.setAutoCommit(false);
	
	            if (!roomID.equals("NotInDormitory")) {
	                PreparedStatement updateRoomStmt = conn.prepareStatement(
	                        "UPDATE Room SET CurrentOccupants = CurrentOccupants - 1 WHERE RoomID = ?");
	                updateRoomStmt.setString(1, roomID);
	                updateRoomStmt.executeUpdate();
	            }
	
	            PreparedStatement deleteContractStmt = conn.prepareStatement(
	                    "DELETE FROM Contract WHERE StudentID = ?");
	            deleteContractStmt.setString(1, studentID);
	            deleteContractStmt.executeUpdate();
	
	            PreparedStatement deleteInvoiceStmt = conn.prepareStatement(
	                    "DELETE FROM Invoice WHERE ContractID IN (SELECT ContractID FROM Contract WHERE StudentID = ?)");
	            deleteInvoiceStmt.setString(1, studentID);
	            deleteInvoiceStmt.executeUpdate();
	
	            PreparedStatement deleteStudentStmt = conn.prepareStatement(
	                    "DELETE FROM Student WHERE StudentID = ?");
	            deleteStudentStmt.setString(1, studentID);
	            deleteStudentStmt.executeUpdate();
	
	            conn.commit();
	
	            model.removeRow(selectedRow);
	            JOptionPane.showMessageDialog(frame, "Xóa sinh viên thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(frame, "Lỗi khi xóa sinh viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Xem xét lại!");
	    	}
	    }
    private void showBuildingManagement() {
    	resetButtonColors();
    	buildingManagementButton.setBackground(new Color(240, 80, 80));
        String query = "SELECT b.BuildingID AS 'ID Tòa Nhà', b.Name AS 'Tòa Nhà', COUNT(r.RoomID) AS 'Tổng Phòng', b.Location AS 'Vị Trí' FROM Building b LEFT JOIN Room r ON b.BuildingID = r.BuildingID GROUP BY b.BuildingID, b.Name";
        showDataInTable("Quản Lý Tòa Nhà", query);
    }

    private void showInvoiceManagement() {
        resetButtonColors();
        invoiceManagementButton.setBackground(new Color(240, 80, 80));

        centerPanel.removeAll();

        JLabel titleLabel = new JLabel("<html><u>Quản Lý Hóa Đơn</u></html>", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        headerFont = new Font("Arial", Font.BOLD, 16);
        header.setFont(headerFont);
        Font tableFont1 = new Font("Arial", Font.PLAIN, 16);
        table.setFont(tableFont1);
        table.setRowHeight(30);
        
        model.addColumn("Invoice");
        model.addColumn("Contract");
        model.addColumn("Họ và Tên");
        model.addColumn("Phòng");
        model.addColumn("Ngày PH");
        model.addColumn("Ngày TT");
        model.addColumn("Số Tiền");
        model.addColumn("TT Thanh Toán");

        table.getColumnModel().getColumn(0).setPreferredWidth(90); 
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(160); 
        table.getColumnModel().getColumn(3).setPreferredWidth(90); 
        table.getColumnModel().getColumn(4).setPreferredWidth(110); 
        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(160);


        table.getColumnModel().getColumn(0).setCellRenderer(cell); 
        table.getColumnModel().getColumn(1).setCellRenderer(cell); 
        table.getColumnModel().getColumn(3).setCellRenderer(cell); 
        table.getColumnModel().getColumn(4).setCellRenderer(cell); 
        table.getColumnModel().getColumn(5).setCellRenderer(cell); 
        table.getColumnModel().getColumn(6).setCellRenderer(cell); 


        String query = "SELECT i.InvoiceID, i.ContractID, s.FullName, c.RoomID, i.IssueDate, i.PaymentDate, FORMAT(i.Amount, 0) AS FormattedAmount, i.PaymentStatus FROM Invoice i JOIN contract c ON c.ContractID = i.ContractID JOIN student s ON s.StudentID = c.StudentID";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("InvoiceID"),
                        rs.getString("ContractID"),
                        rs.getString("FullName"),
                        rs.getString("RoomID"),
                        rs.getString("IssueDate"),
                        rs.getString("PaymentDate"),
                        rs.getString("FormattedAmount"),
                        rs.getString("PaymentStatus")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (table.getColumnName(column).equals("TT Thanh Toán")) {
                    String status = table.getValueAt(row, column).toString();
                    c.setBackground("Paid".equals(status) ? new Color(81, 255, 151) : new Color(255, 73, 115));
                    c.setForeground("Paid".equals(status) ? Color.black : Color.white);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JButton filterPaidButton = new JButton("Hiển Thị Đã Thanh Toán");
        filterPaidButton.addActionListener(e -> filterInvoices(table, "Paid"));
        filterPaidButton.setBackground(new Color(118, 255, 108));
        filterPaidButton.setFont(new Font("Arial", Font.BOLD, 14));
        filterPaidButton.setOpaque(true);
        filterPaidButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        filterPaidButton.setFocusPainted(false);
        filterPaidButton.setPreferredSize(new Dimension(150, 50));
        controlPanel.add(filterPaidButton);

        JButton filterUnpaidButton = new JButton("Hiển Thị Chưa Thanh Toán");
        filterUnpaidButton.addActionListener(e -> filterInvoices(table, "Unpaid"));
        filterUnpaidButton.setBackground(new Color(255, 81, 145));
        filterUnpaidButton.setFont(new Font("Arial", Font.BOLD, 14));
        filterUnpaidButton.setOpaque(true);
        filterUnpaidButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        filterUnpaidButton.setFocusPainted(false);
        controlPanel.add(filterUnpaidButton);
        
        JButton resetButton = new JButton("Reset Thanh Toán");
        resetButton.addActionListener(e -> resetFilter(table));
        resetButton.setBackground(new Color(132, 244, 255));
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setOpaque(true);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        resetButton.setFocusPainted(false);
        controlPanel.add(resetButton);     
        
        JButton editInvoiceButton = new JButton("Chỉnh Sửa");
        editInvoiceButton.setBackground(new Color(255, 204, 255));
        editInvoiceButton.setFont(new Font("Arial", Font.BOLD, 14));
        editInvoiceButton.setOpaque(true);
        editInvoiceButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        editInvoiceButton.setFocusPainted(false);
        editInvoiceButton.addActionListener(e -> showEditInvoiceFrame(model, table));
        controlPanel.add(editInvoiceButton);
        
        centerPanel.add(controlPanel, BorderLayout.SOUTH);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void filterInvoices(JTable table, String status) {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                String roomStatus = (String) entry.getValue(model.findColumn("TT Thanh Toán"));
                return status.equals(roomStatus);
            }
        });
    }
    private void resetFilter(JTable table) {
    	 TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
    	    if (sorter != null) {
    	        sorter.setRowFilter(null); 
    	    }
    }
    private void showEditInvoiceFrame(DefaultTableModel model, JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để sửa.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFrame editInvoiceFrame = new JFrame("Chỉnh Sửa Hóa Đơn");
        editInvoiceFrame.setSize(500, 330);
        editInvoiceFrame.setLocationRelativeTo(null);
        editInvoiceFrame.setLayout(new BorderLayout());
        editInvoiceFrame.setResizable(false);

        JPanel up = new JPanel();
        up.setBackground(Color.WHITE);
        JPanel left = new JPanel(); left.setBackground(Color.WHITE);
        left.setPreferredSize(new Dimension(50,50));
        JPanel right = new JPanel(); right.setBackground(Color.WHITE);
        right.setPreferredSize(new Dimension(50,50));

        JLabel titleLabel = new JLabel("<html><u>Chỉnh Sửa Hóa Đơn</u></html>");
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        titleLabel.setForeground(new Color(0, 0, 150));
        up.add(titleLabel);

        JPanel center = new JPanel(new GridLayout(7, 2, 10, 10));
        center.setBackground(Color.WHITE);

        int modelRow = table.convertRowIndexToModel(selectedRow);

        JLabel invoiceIDLabel = new JLabel("Mã Hóa Đơn:");
        JLabel invoiceIDValue = new JLabel(model.getValueAt(modelRow, 0).toString());

        JLabel contractIDLabel = new JLabel("Mã Hợp Đồng:");
        JLabel contractIDValue = new JLabel(model.getValueAt(modelRow, 1).toString());

        JLabel fullNameLabel = new JLabel("Họ và Tên:");
        JLabel fullNameValue = new JLabel(model.getValueAt(modelRow, 2).toString());

        JLabel roomIDLabel = new JLabel("Phòng:");
        JLabel roomIDValue = new JLabel(model.getValueAt(modelRow, 3).toString());
        
        JLabel issueDateLabel = new JLabel("Ngày Phát Hành:");
        JLabel issueDateValue = new JLabel(model.getValueAt(modelRow, 4).toString());

        JLabel amountLabel = new JLabel("Số Tiền:");
        JLabel amountValue = new JLabel(model.getValueAt(modelRow, 6).toString());

        JLabel paymentStatusLabel = new JLabel("Trạng Thái Thanh Toán:");
        JComboBox<String> paymentStatusComboBox = new JComboBox<>(new String[]{"Paid", "Unpaid"});
        paymentStatusComboBox.setSelectedItem(model.getValueAt(modelRow, 7).toString());
        paymentStatusComboBox.setBackground(Color.WHITE);

        JButton saveButton = new JButton("Lưu");
        saveButton.setPreferredSize(new Dimension(100,30));
        saveButton.setBackground(new Color(88, 250, 255));
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setOpaque(true);
        saveButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        saveButton.addActionListener(e -> {
            String invoiceID = invoiceIDValue.getText();
            String newStatus = (String) paymentStatusComboBox.getSelectedItem();

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Invoice SET PaymentStatus = ?, PaymentDate = CASE WHEN ? = 'paid' THEN CURRENT_DATE WHEN ? = 'unpaid' THEN NULL ELSE PaymentDate END WHERE InvoiceID = ?");
                stmt.setString(1, newStatus);
                stmt.setString(2, newStatus);
                stmt.setString(3, newStatus);
                stmt.setString(4, invoiceID);

                int updatedRows = stmt.executeUpdate();
                if (updatedRows > 0) {
                    JOptionPane.showMessageDialog(editInvoiceFrame, "Cập nhật thành công!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                    model.setValueAt(newStatus, modelRow, 7);
                    
                    if (newStatus.equals("Paid")) {
                    	Date currentDate = new java.sql.Date(System.currentTimeMillis());
                        model.setValueAt(currentDate, modelRow, 5);
                    } else {
                        model.setValueAt(null, modelRow, 5); 	
                    }

                    editInvoiceFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(editInvoiceFrame, "Không thể cập nhật hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(editInvoiceFrame, "Lỗi khi cập nhật: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton qrButton = new JButton("Gửi hóa đơn");
        qrButton.setPreferredSize(new Dimension(100,30));
        qrButton.setBackground(new Color(255, 255, 102));
        qrButton.setFont(new Font("Arial", Font.BOLD, 14));
        qrButton.setOpaque(true);
        qrButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        qrButton.addActionListener(e -> {
            String invoiceID = invoiceIDValue.getText();
            String contractID = contractIDValue.getText();
            String fullName = fullNameValue.getText();
            String roomID = roomIDValue.getText();
            String issueDate = issueDateValue.getText();
            String amount = amountValue.getText();
            
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT email FROM student JOIN contract ON student.StudentID = contract.StudentID WHERE ContractID = ?");
                stmt.setString(1, contractID);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    String to = resultSet.getString("email");
                    sendEmail(to, invoiceID, contractID, fullName, roomID, issueDate, amount);
                } else {
                	JOptionPane.showMessageDialog(null, "Không tìm thấy hợp đồng với contractID.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         
        });
        
        
        center.add(invoiceIDLabel);
        center.add(invoiceIDValue);
        center.add(contractIDLabel);
        center.add(contractIDValue);
        center.add(fullNameLabel);
        center.add(fullNameValue);
        center.add(roomIDLabel);
        center.add(roomIDValue);
        center.add(issueDateLabel);
        center.add(issueDateValue);
        center.add(amountLabel);
        center.add(amountValue);
        center.add(paymentStatusLabel);
        center.add(paymentStatusComboBox);

        JPanel down = new JPanel(new FlowLayout());
        down.setBackground(Color.WHITE);
        down.add(saveButton);
        down.add(qrButton);
        
        editInvoiceFrame.add(up, BorderLayout.NORTH);
        editInvoiceFrame.add(center, BorderLayout.CENTER);
        editInvoiceFrame.add(down, BorderLayout.SOUTH);
        editInvoiceFrame.add(left, BorderLayout.EAST);
        editInvoiceFrame.add(right, BorderLayout.WEST);
        editInvoiceFrame.setVisible(true);
    }
	public void sendEmail(String to, String invoiceID, String contractID, String fullName
			, String roomID, String issueDate , String amount) {
		String from = "trunghieu27032006@gmail.com";
        String appPassword = "olbxmlmhrqqjrvum";
        String host = "smtp.gmail.com";
        ClassLoader classLoader = Manager.class.getClassLoader();
        URL resource = classLoader.getResource("UIimage/qr_code.jpg");
        String qrCodePath = resource.getPath();
        	
        Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, appPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			try {
            	message.setFrom(new InternetAddress(from, "Quản lý Ký túc xá", StandardCharsets.UTF_8.name()));
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
            }
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Thông báo hóa đơn từ ký túc xá (VKU)");

			String emailContent = String.format(
					 "<h3>Kính gửi: %s</h3>"
				     + "<p>Thông tin hóa đơn ký túc xá của bạn như sau:</p>"
				     + "<ul>"
				     + "<li><strong>Mã hóa đơn:</strong> %s</li>"
				     + "<li><strong>Mã hợp đồng:</strong> %s</li>"
				     + "<li><strong>Họ và tên:</strong> %s</li>"
				     + "<li><strong>Phòng:</strong> %s</li>"
				     + "<li><strong>Ngày phát hành:</strong> %s</li>"
				     + "<li><strong>Số tiền cần nộp:</strong> %s</li>"
				     + "<li><strong>Lưu ý với nội dung : Mã Sinh Viên + Họ Và Tên + Mã Phòng (Ví dụ: 24IT000-NguyenVanA-0)</strong></li>"

				     + "</ul>"
				     + "<p><img src=\"cid:qrCodeImage\" style=\"width:300px;height:auto;\"/></p>"
				     + "<p>Trân trọng,</p>"
				     + "<p>Quản lý Ký túc xá</p>",
			       fullName, invoiceID, contractID, fullName, roomID, issueDate, amount);
			
			MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(emailContent, "text/html; charset=UTF-8");
            
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(new File(qrCodePath));
            imagePart.setContentID("<qrCodeImage>");
            imagePart.setDisposition(MimeBodyPart.INLINE); 
	
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart); 
            multipart.addBodyPart(imagePart); 
            
            message.setContent(multipart);
            
			Transport.send(message);
			JOptionPane.showMessageDialog(null, "Gửi hóa đơn thành công!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);		
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
    private void showReports() {
    	    resetButtonColors();
    	    reportButton.setBackground(new Color(240, 80, 80));

    	    centerPanel.removeAll();
    	    JLabel titleLabel = new JLabel("<html><u>Báo Cáo và Thống Kê</u></html>", JLabel.CENTER);
    	    titleLabel.setFont(new Font("Arial", Font.ITALIC, 24));
    	    centerPanel.add(titleLabel, BorderLayout.NORTH);

    	    JPanel reportPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    	    
    	    ReportChart c = new ReportChart();
    	    
    	    JButton genderChartButton = createButton_Report("Biểu đồ giới tính", new Color(100, 200, 255));
    	    genderChartButton.addActionListener(e -> c.showGenderPieChart());
    	    reportPanel.add(genderChartButton);

    	    JButton roomTypeChartButton = createButton_Report("Biểu đồ loại phòng", new Color(150, 200, 255));
    	    roomTypeChartButton.addActionListener(e -> c.showRoomTypePieChart());
    	    reportPanel.add(roomTypeChartButton);

    	    JButton paymentChartButton = createButton_Report("Trạng thái thanh toán", new Color(200, 150, 255));
    	    paymentChartButton.addActionListener(e -> c.showPaymentPieChart());
    	    reportPanel.add(paymentChartButton);

    	    JButton monthlyRevenueChartButton = createButton_Report("Doanh thu hàng tháng", new Color(255, 200, 100));
    	    monthlyRevenueChartButton.addActionListener(e -> c.showMonthlyRevenueBarChart());
    	    reportPanel.add(monthlyRevenueChartButton);

    	    JButton livingCostChartButton = createButton_Report("Biểu đồ sinh viên", new Color(255, 100, 150));
    	    livingCostChartButton.addActionListener(e -> c.showRoomOccupancyBarChart());
    	    reportPanel.add(livingCostChartButton);

    	    JButton roomUsageChartButton = createButton_Report("Tình trạng sử dụng phòng", new Color(255, 200, 150));
    	    roomUsageChartButton.addActionListener(e -> c.showRoomUsagePieChart());
    	    reportPanel.add(roomUsageChartButton);

    	    centerPanel.add(reportPanel, BorderLayout.CENTER);
    	    centerPanel.revalidate();
    	    centerPanel.repaint();
    	}
    private JButton createButton_Report(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 22));
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    private void showDataInTable(String title, String query) {
    	centerPanel.removeAll();
    	JLabel titleLabel = new JLabel("<html><u>"+title+"</u></html>", JLabel.CENTER);    
    	titleLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        headerFont = new Font("Arial", Font.BOLD, 20);
        header.setFont(headerFont);
        Font tableFont1 = new Font("Arial", Font.PLAIN, 18);
        table.setFont(tableFont1);
        table.setRowHeight(30);
        
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        table.getColumnModel().getColumn(0).setCellRenderer(cell); 
        table.getColumnModel().getColumn(1).setCellRenderer(cell); 
        table.getColumnModel().getColumn(2).setCellRenderer(cell); 
        table.getColumnModel().getColumn(3).setCellRenderer(cell); 
        
        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER); 
        
        centerPanel.revalidate();
        centerPanel.repaint();
    }
    private void loadFrames() {
        frames = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4); 
        List<Future<ImageIcon>> futures = new ArrayList<>();
        
        for (int i = 1; i <= 120; i++) {
            final int index = i; 
            futures.add(executorService.submit(() -> {
                String imagePath = "/Animation/animation (" + index + ").png";
                ImageIcon animationO = new ImageIcon(getClass().getResource(imagePath));
                Image scaledImage = animationO.getImage().getScaledInstance(300, 300, Image.SCALE_AREA_AVERAGING);
                return new ImageIcon(scaledImage);
            }));
        }

        for (Future<ImageIcon> future : futures) {
            try {
                frames.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
    private void startAnimation() {
        Timer timer = new Timer(125, e -> {
        	
            animation.setIcon(frames.get(currentFrame));

            currentFrame = (currentFrame + 1) % frames.size();
        });
        timer.start();
    }
}
