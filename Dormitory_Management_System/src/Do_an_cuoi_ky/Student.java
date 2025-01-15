package Do_an_cuoi_ky;

import java.awt.BorderLayout;  

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;

import javax.swing.*;

public class Student {
	private JFrame frame, framecapnhat, registerFrame;
	private JLabel ttt1, ttt2, ttt3, ttt4, ttt5, ttt6, ttt7, ttt8, ttt9, ttt10; 
	private JLabel tt1, tt2, tt3, tt4, tt5, tt6, tt7, tt8, tt9, tt10, t1; 
	private String thesinhvien; 
	private JTextField t2, t7, t8, t9, hiden; 
	private JPanel t3, t4, t5, t6, rowPanel, right;
	private JComboBox<String> dayComboBox, monthComboBox, yearComboBox, ClassComboBox, DepartmentComboBox;
	private JScrollPane dayScrollPane, monthScrollPane, yearScrollPane, ClassScrollPane, DepartmentScrollPane;
	private JRadioButton male, female; private ButtonGroup group;
	private JCheckBox check;
	private int daySQL, monthSQL, yearSQL;
	private JLabel RoomIDLabel, B_NameLabel, SoluongLabel, T_RoomIDLabel, T_B_NameLabel, T_SoluongLabel, noRoomLabel;
	private DefaultTableModel tableModel;
	private JTable studentTable;
	private String room;
	public Student() {
		frame = new JFrame("Ký Túc Xá");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		frame.add(north, BorderLayout.NORTH);
		// thong tin
		JPanel left = new JPanel();
		left.setBackground(Color.WHITE);
		left.setLayout(new GridLayout(11, 2 ,0 ,5));
		left.setPreferredSize(new Dimension(375, 0));
		Border TT = BorderFactory.createLineBorder(Color.RED, 3);
		TitledBorder TTT = BorderFactory.createTitledBorder(TT, "Thông Tin Sinh Viên");
		TTT.setTitleFont(new Font("Arial", Font.ITALIC, 20));
		left.setBorder(TTT);
		ttt1 = new JLabel("_Null_"); ttt2 = new JLabel("_Null_"); ttt3 = new JLabel("_Null_"); ttt4 = new JLabel("_Null_"); ttt5 = new JLabel("_Null_");
		ttt6 = new JLabel("_Null_"); ttt7 = new JLabel("_Null_"); ttt8 = new JLabel("_Null_"); ttt9 = new JLabel("_Null_"); ttt10 = new JLabel("_Null_");
		
		tt1 = new JLabel("Thẻ Sinh Viên"); 	
		JPanel rowPanel1 = new JPanel(new BorderLayout()); rowPanel1.add(tt1, BorderLayout.WEST); rowPanel1.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel1); left.add(ttt1);  rowPanel1.setBackground(Color.WHITE);
		tt2 = new JLabel("Họ và Tên");		
		JPanel rowPanel2 = new JPanel(new BorderLayout()); rowPanel2.add(tt2, BorderLayout.WEST); rowPanel2.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel2); left.add(ttt2);  rowPanel2.setBackground(Color.WHITE);
		tt3 = new JLabel("Ngày sinh"); 			 
		JPanel rowPanel3 = new JPanel(new BorderLayout()); rowPanel3.add(tt3, BorderLayout.WEST); rowPanel3.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel3); left.add(ttt3);  rowPanel3.setBackground(Color.WHITE);
		tt4 = new JLabel("Giới tính"); 			
		JPanel rowPanel4 = new JPanel(new BorderLayout()); rowPanel4.add(tt4, BorderLayout.WEST); rowPanel4.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel4); left.add(ttt4);  rowPanel4.setBackground(Color.WHITE);
		tt5 = new JLabel("Lớp học"); 			
		JPanel rowPanel5 = new JPanel(new BorderLayout()); rowPanel5.add(tt5, BorderLayout.WEST); rowPanel5.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel5); left.add(ttt5);  rowPanel5.setBackground(Color.WHITE);
		tt6 = new JLabel("Ngành học"); 			
		JPanel rowPanel6 = new JPanel(new BorderLayout()); rowPanel6.add(tt6, BorderLayout.WEST); rowPanel6.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel6); left.add(ttt6);  rowPanel6.setBackground(Color.WHITE);
		tt7 = new JLabel("Số điện thoại"); 		 
		JPanel rowPanel7 = new JPanel(new BorderLayout()); rowPanel7.add(tt7, BorderLayout.WEST); rowPanel7.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel7); left.add(ttt7);  rowPanel7.setBackground(Color.WHITE);
		tt8 = new JLabel("Email"); 			
		JPanel rowPanel8 = new JPanel(new BorderLayout()); rowPanel8.add(tt8, BorderLayout.WEST); rowPanel8.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel8); left.add(ttt8);	 rowPanel8.setBackground(Color.WHITE);
		tt9 = new JLabel("CCCD"); 				
		JPanel rowPanel9 = new JPanel(new BorderLayout()); rowPanel9.add(tt9, BorderLayout.WEST); rowPanel9.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel9); left.add(ttt9);  rowPanel9.setBackground(Color.WHITE);
		tt10 = new JLabel("Phòng"); 			
		JPanel rowPanel10 = new JPanel(new BorderLayout()); rowPanel10.add(tt10, BorderLayout.WEST); rowPanel10.add(new JLabel(":      "), BorderLayout.EAST); left.add(rowPanel10); left.add(ttt10); rowPanel10.setBackground(Color.WHITE);

		thesinhvien = UILOGIN.getThesinhvien();
		//thesinhvien = "24IT003";
		ttt1.setText(thesinhvien);
		loadStudentInfo(thesinhvien);
		left.setPreferredSize(new Dimension(350, 550));
		
		// capnhat
		JPanel updet = new JPanel(new GridLayout(1, 1, 2, 2));
	
		JButton capnhatButton = new JButton("Cập Nhật Thông Tin");
		
		capnhatButton.addActionListener(e -> FrameCapNhat());
		// xoa tk
		//JButton xoatkButton = new JButton("Xóa Tài Khoản");
		//xoatkButton.addActionListener(e -> XoaTK());
		updet.add(capnhatButton); //updet.add(xoatkButton);
		// logout
		// image 
		ImageIcon logout1 = new ImageIcon(getClass().getResource("/UIimage/logout.png"));
		Image image1 = logout1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon logout2 = new ImageIcon(image1);
		JButton logoutButton = new JButton();
		logoutButton.setIcon(logout2);
		logoutButton.setFocusable(false);
		logoutButton.setContentAreaFilled(false);
		logoutButton.setBorderPainted(false); 
		logoutButton.setBackground(Color.white);
		capnhatButton.setBackground(new Color(25, 200, 255));
		//xoatkButton.setBackground(Color.white);
		logoutButton.addActionListener(e -> logout());
		
		left.add(updet);
		left.add(logoutButton);
		frame.add(left, BorderLayout.EAST);
		
		// Chức năng
		right = new JPanel(new BorderLayout());
		Border TTright = BorderFactory.createLineBorder(Color.RED, 3);
		TitledBorder TTTright = BorderFactory.createTitledBorder(TTright, "Thông tin phòng");
		TTTright.setTitleFont(new Font("Arial", Font.ITALIC, 20));
		right.setBorder(TTTright);
		right.setPreferredSize(new Dimension(400,500));
		right.setBackground(Color.white);
		noRoomLabel = new JLabel("<html><u>Bạn chưa có phòng</u></html>", JLabel.CENTER);
		noRoomLabel.setFont(new Font ("Arial", Font.ITALIC, 16));
		right.add(noRoomLabel);
		if (ttt10.getText().equalsIgnoreCase("NotInDormitory")) {
			noRoomLabel.setVisible(true);
		} else hienthitablephong();
	
		
        frame.add(right, BorderLayout.WEST);
        
        
        // Thong Bao 
        // tieu de
        JPanel center = new JPanel(new BorderLayout());
        JPanel tbPanel = new JPanel(new FlowLayout());
        JLabel tbLabel = new JLabel("<html><u>Thông Báo Ký Túc Xá</u></html>");
        tbLabel.setFont(new Font("Arial", Font.ITALIC, 30));	
        tbLabel.setForeground(new Color(0, 0, 150));
        tbPanel.add(tbLabel);
        center.add(tbPanel, BorderLayout.NORTH);
        // thong bao trong
        JPanel nofiPanel = new JPanel();
        nofiPanel.setLayout(new BoxLayout(nofiPanel, BoxLayout.Y_AXIS));
        JLabel nofiLabel = new JLabel();
        nofiLabel.setText("<html><h2 style='color:blue;'>Thông báo từ quản lý</h2><ul>" +
                "<li><b>Lịch kiểm tra:</b> thứ 2 và thứ 5 hàng tuần</li>" +
                "<li><b>Thông tin gia hạn hợp đồng:</b></li>" +
                "<ul><li>Gia hạn hợp đồng trước ngày 20 mỗi tháng để tránh phí phạt</li>" +
                "<li>Ưu tiên lựa chọn phòng mới cho sinh viên gia hạn sớm</li></ul>" +
                "<li><b>Các chính sách mới:</b></li>" +
                "<ul><li>Giảm 10% phí thuê phòng khi gia hạn trước 1 tháng</li>" +
                "<li>Hỗ trợ sửa chữa thiết bị phòng miễn phí trong giờ hành chính</li>" +
                "<li>Thêm các khóa học kỹ năng miễn phí cho sinh viên nội trú</li></ul>" +
                "</ul><p style='text-align:right;'><i>Trân trọng,<br>Ký túc xá VKU<br>Ngày 20 tháng 12 năm 2024</i></p>" +
                "<h3 style='color:green;'>Hướng dẫn sinh viên mới</h3><ul>" +
                "<li><b>Cập nhật thông tin:</b> Vui lòng sử dụng chức năng cập nhật ở bên phải.</li>" +
                "<li><b>Đăng ký phòng:</b> Sử dụng nút bên dưới để đăng ký phòng.</li>" +
                "</ul></html>");
        nofiPanel.add(nofiLabel);
        center.add(nofiPanel, BorderLayout.CENTER);
        // dang ki south
        
        JPanel dangkiPanel = new JPanel(new FlowLayout());
        dangkiPanel.setPreferredSize(new Dimension(200, 150));
        JButton dangkiButton = new JButton(" Đăng Ký! ");
        dangkiButton.setBackground(new Color(255, 255, 200));
        dangkiButton.setForeground(Color.RED);
        dangkiButton.setFont(new Font("Arial", Font.BOLD, 20));
        dangkiButton.setOpaque(true);
        dangkiButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        dangkiButton.setFocusPainted(false);
        dangkiButton.addActionListener(e -> dangkiPhong());
        dangkiPanel.add(dangkiButton);
        center.add(dangkiPanel, BorderLayout.SOUTH);
        frame.add(center,BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
	// tải thông tin xuống
	private void loadStudentInfo(String id) {
	    String sql = "SELECT * FROM student WHERE IDSinhvien = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	         
	        stmt.setString(1, id);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	            	
	                ttt1.setText(rs.getString("IDSinhVien"));
	                ttt2.setText(rs.getString("FullName"));
	                ttt3.setText(rs.getString("DateOfBirth"));
	                ttt4.setText(rs.getString("Gender"));
	                ttt5.setText(rs.getString("Class"));
	                ttt6.setText(rs.getString("Department"));
	                ttt7.setText(rs.getString("PhoneNumber"));
	                ttt8.setText(rs.getString("Email"));
	                ttt9.setText(rs.getString("CCCDID"));
	                ttt10.setText(rs.getString("Status"));
	            } else {
	                JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin sinh viên với ID: " + id);
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn dữ liệu: " + e.getMessage());
	    }
	}
	private void FrameCapNhat() {
		framecapnhat = new JFrame("Cập Nhật Thông Tin");
		framecapnhat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		framecapnhat.setSize(400, 600);
		framecapnhat.setLocationRelativeTo(null);
		framecapnhat.setResizable(false);
		framecapnhat.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		JLabel cntt = new JLabel("<html><u>Cập Nhật Thông Tin</u></html>");
		cntt.setFont(new Font("Arial", Font.ITALIC, 30));
		
		cntt.setForeground(new Color(0, 0, 150));
		north.add(cntt);
		JPanel center = new JPanel(new GridLayout(10, 2, 10, 10));
		// ID
		JPanel t1_panelBor = new JPanel(new BorderLayout());
		JPanel t1_panel = new JPanel(new FlowLayout());
		t1 = new JLabel(thesinhvien); t1_panel.add(t1);
		t1_panelBor.add(new JPanel(), BorderLayout.NORTH);
		t1_panelBor.add(t1_panel, BorderLayout.CENTER);
		center.add(tt1); center.add(t1_panelBor);
		// full name
		t2 = new JTextField(ttt2.getText());   center.add(tt2); center.add(t2);
		// date of birth
		center.add(tt3); 
		t3 = new JPanel(new FlowLayout());
		dayComboBox = new JComboBox<>();
		monthComboBox = new JComboBox<>();
		yearComboBox = new JComboBox<>();
		
        for (int i = 1; i <= 31; i++) 
            dayComboBox.addItem(String.valueOf(i));
        for (int i = 1; i <= 12; i++) 
            monthComboBox.addItem(String.valueOf(i));   
        for (int i = 1980; i <= 2024; i++) 
            yearComboBox.addItem(String.valueOf(i));
        getDayMothYear();
        dayScrollPane = new JScrollPane(dayComboBox);
        monthScrollPane = new JScrollPane(monthComboBox);
        yearScrollPane = new JScrollPane(yearComboBox);
        
        
       
        
        monthComboBox.addActionListener(e -> updateDays(dayComboBox, monthComboBox, yearComboBox));
        yearComboBox.addActionListener(e -> updateDays(dayComboBox, monthComboBox, yearComboBox));
        updateDays(dayComboBox, monthComboBox, yearComboBox);
        
        monthComboBox.setSelectedItem(String.valueOf(monthSQL));
        yearComboBox.setSelectedItem(String.valueOf(yearSQL));
        dayComboBox.setSelectedItem(String.valueOf(daySQL));
        
        t3.add(yearScrollPane); t3.add(monthScrollPane); t3.add(dayScrollPane); 
        center.add(t3);
		// gender
        center.add(tt4);
		t4 = new JPanel(new FlowLayout());
		male 	= new JRadioButton("Male");
		female  = new JRadioButton("Female");
		group 	= new ButtonGroup();
		group.add(male); group.add(female);
		t4.add(male); t4.add(female);
		center.add(t4);
		// Class
		center.add(tt5);
		t5 = new JPanel();
		String ClassArr[] = {
			"24GIT1", "24GIT2", "24IT1", "24IT2", "24AI", "24GIC", "24NS", "24ITe", "24GITe", "24GBA", "24EL", "24DA", "24CE" 		
		};
		ClassComboBox = new JComboBox<>(ClassArr);
		ClassScrollPane = new JScrollPane(ClassComboBox);
		t5.add(ClassScrollPane);
		center.add(t5);
		// ----------
		center.add(tt6); 
		t6 = new JPanel();
		String DepartmentArr[] = {
			"Information Technology (K)", "Information Technology (J)", "Information Technology (E)",
			"Information Technology (B)", "Artificial Intelligence", "Network Security",
			"Digital Art Design", "Digital Marketing", "E-Commerce Admin", "Semiconductor IC Design" , "Logictis"		
		};
		DepartmentComboBox = new JComboBox<>(DepartmentArr);
		DepartmentScrollPane = new JScrollPane(DepartmentComboBox);
		t6.add(DepartmentScrollPane);
		center.add(t6);
		t7 = new JTextField(ttt7.getText());  center.add(tt7); center.add(t7);
		t8 = new JTextField(ttt8.getText());  center.add(tt8); center.add(t8);
		t9 = new JTextField(ttt9.getText());  center.add(tt9); center.add(t9);
		center.add(new JLabel()); 
		// check
		check = new JCheckBox("Tick để xác nhận thay đổi!"); check.setForeground(Color.red);
		center.add(check);
		
		JPanel south = new JPanel(new BorderLayout());
		JPanel south_Flow = new JPanel(new FlowLayout());
		JButton Accept = new JButton("ACCEPT");
		JButton Reset  = new JButton("RESET");
		Accept.addActionListener(e -> updateToDatabase());
		Reset.addActionListener(e -> resetTT());
		south_Flow.add(Accept); south_Flow.add(Reset);
		south.add(south_Flow, BorderLayout.CENTER);
		south.add(south_Flow);
		framecapnhat.add(north, BorderLayout.NORTH);
		framecapnhat.add(center, BorderLayout.CENTER);
		framecapnhat.add(south, BorderLayout.SOUTH);
		framecapnhat.setVisible(true);
		
	}
			private void updateDays(JComboBox<String> dayComboBox, JComboBox<String> monthComboBox, JComboBox<String> yearComboBox) {
				int month = Integer.parseInt((String) monthComboBox.getSelectedItem());
				int year = Integer.parseInt((String) yearComboBox.getSelectedItem());
				
				int daysInMonth = getDaysInMonth(month, year);
				dayComboBox.removeAllItems();
				for (int i = 1; i <= daysInMonth; i++) {
		            dayComboBox.addItem(String.valueOf(i));
		        }
			}
			private int getDaysInMonth(int month, int year) {
				if (month == 2) {
					// chia cho 4 nhung khong chia cho 100 ( 1500 1400 ko phai ) / chia cho 400 ( 2000, 2400)
					if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
						return 29;
					} else return 28;
				}
				if (month == 4 || month == 6 || month == 9 || month == 11) {
		            return 30;
				} else return 31;
			}
			private void resetTT() {
				t2.setText("");
				dayComboBox.setSelectedIndex(0); monthComboBox.setSelectedIndex(0); yearComboBox.setSelectedIndex(0);
				group.clearSelection();
				ClassComboBox.setSelectedIndex(0);
				check.setSelected(false);
				t7.setText("");
				t8.setText("");
				t9.setText("");
			}
			private void getDayMothYear() {
				 String url = "jdbc:mysql://localhost:3306/dormitorymanagement";
			     String user = "root";
			     String password = "";
		         String sql = "SELECT YEAR(DateOfBirth) AS YEAR, MONTH(DateOfBirth) AS MONTH, DAY(DateOfBirth) AS DAY FROM student WHERE IDSinhVien=?";

			        try (Connection conn = DriverManager.getConnection(url, user, password);
			            PreparedStatement stmt = conn.prepareStatement(sql)) {
			        	
			        	stmt.setString(1, thesinhvien);
			        	try (ResultSet rs = stmt.executeQuery()) {
				            while (rs.next()) {
				            	yearSQL = rs.getInt("YEAR");  
				                monthSQL = rs.getInt("MONTH"); 
				                daySQL = rs.getInt("DAY");  				          
				               
				            }
				        }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			}
	//tải thông tin lên
	private void updateToDatabase() {
		if (t2.getText().matches(".*\\d.*")) { 
		    JOptionPane.showMessageDialog(null, "Không được nhập số!");
		    return;
		} else if (!male.isSelected() && !female.isSelected()) {
			JOptionPane.showMessageDialog(null, "Hãy chọn giới tính!");
		    return;
		} else if (!t7.getText().matches("[0-9]{9,11}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
		    return;
		} else if (!t8.getText().matches("^[a-zA-Z0-9._%+-]+@vku\\.udn\\.vn$")) {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ (@vku.udn.vn)!");
		    return;
		} else if (!t9.getText().matches("[0-9]{12}")) {
			JOptionPane.showMessageDialog(null, "Căn cước công dân không hợp lệ!");
		    return;
		} else if (!check.isSelected()) {
			JOptionPane.showMessageDialog(null, "Hãy xác nhận thay đổi!");
		    return;
		} else {
			updateStudentInfo(thesinhvien);
			framecapnhat.setVisible(false);
			loadStudentInfo(thesinhvien);
		}
		
	}
	private void updateStudentInfo(String id) {
        String sql = "UPDATE student SET FullName=?, DateOfBirth=?, Gender=?, Class=?, Department=?, PhoneNumber=?, Email=?, CCCDID=? WHERE IDSinhvien=?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
        	String day = dayComboBox.getSelectedItem().toString();
            String month = monthComboBox.getSelectedItem().toString();
            String year = yearComboBox.getSelectedItem().toString();

        	String dateString = String.format("%s-%02d-%02d", year, Integer.parseInt(month), Integer.parseInt(day));
        	String gender =  male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "");
        	
        	String class_String = ClassComboBox.getSelectedItem().toString();
        	String depart_String = DepartmentComboBox.getSelectedItem().toString();
        	
            stmt.setString(1, t2.getText());
            stmt.setDate(2, java.sql.Date.valueOf(dateString));
            stmt.setString(3, gender);
            stmt.setString(4, class_String);
            stmt.setString(5, depart_String);
            stmt.setString(6, t7.getText());
            stmt.setString(7, t8.getText());
            stmt.setString(8, t9.getText());
            stmt.setString(9, id);
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(frame, "Cập nhật thông tin thành công!");
            } else {
                JOptionPane.showMessageDialog(frame, "Không có sinh viên với ID này.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi cập nhật thông tin: " + e.getMessage());
        }
    }
	private void dangkiPhong() {
		if (ttt10.getText().equalsIgnoreCase("InDormitory")) {
			JOptionPane.showMessageDialog(frame, "Bạn đã có phòng!\nLiên hệ với Admin để đổi hoặc đăng kí lại!");
			return;
		} else {
			registerFrame = new JFrame("Đăng ký phòng");
	        registerFrame.setSize(600, 600);
	        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        registerFrame.setLayout(new BorderLayout());
	        registerFrame.setLocationRelativeTo(null);
	        // north
	        JPanel tddkPanel = new JPanel(new FlowLayout());
	        JLabel tddkLabel = new JLabel("Danh Sách Phòng Ký Túc");
	        tddkLabel.setFont(new Font("Arial", Font.ITALIC, 30));	
	        tddkLabel.setForeground(new Color(0, 0, 150));
	        tddkPanel.add(tddkLabel);
	        registerFrame.add(tddkPanel, BorderLayout.NORTH);
	        // table center
	        DefaultTableModel model = new DefaultTableModel();
	        JTable roomTable = new JTable(model);
	
	        model.addColumn("Phòng");
	        model.addColumn("Tòa Nhà");
	        model.addColumn("Kiểu Phòng");
	        model.addColumn("Số Lượng");
	        model.addColumn("Giá Cả");
	        roomTable.getColumnModel().getColumn(0).setPreferredWidth(75); 
	        roomTable.getColumnModel().getColumn(1).setPreferredWidth(75);
	        roomTable.getColumnModel().getColumn(2).setPreferredWidth(180); 
	        roomTable.getColumnModel().getColumn(3).setPreferredWidth(75); 
	        roomTable.getColumnModel().getColumn(4).setPreferredWidth(180); 
	        
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	        roomTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
	        roomTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
	        roomTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        roomTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 
	        roomTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); 
	        roomTable.setRowHeight(30); 
	        roomTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    int selectedRoom = roomTable.getSelectedRow();
	                    if (selectedRoom >= 0)  {               
	                        room = (String) roomTable.getValueAt(selectedRoom, 0); 
	                    }
	                }
	            }
	        });
	        
	        
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "");
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT r.RoomID, b.Name, r.RoomType, r.Capacity, r.CurrentOccupants, FORMAT(r.Price, 0) AS FormattedPrice " +
                         "FROM room r " +
                         "JOIN building b ON r.BuildingID = b.BuildingID")) {
	
	            while (rs.next()) {
	            	String roomID		 = rs.getString("RoomID");
	                String buildingName  = rs.getString("Name");
	                String roomType 	 = rs.getString("RoomType");
	                int soluong1 		 = rs.getInt("CurrentOccupants");
	                int soluong2 		 = rs.getInt("Capacity");
	                String soluong = soluong1 + "/" + soluong2;
	                if (soluong1 == soluong2) soluong += "  \u2716";
	                else soluong += "  \u2713";
	                String price 		 = rs.getString("FormattedPrice") + " VND";
	                
	                model.addRow(new Object[]{
	                    roomID,
	                    buildingName,
	                    roomType,
	                    soluong,
	                    price
	                });
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        JScrollPane tableScrollPane = new JScrollPane(roomTable);
	        registerFrame.add(tableScrollPane, BorderLayout.CENTER);
	        JPanel south_register = new JPanel(new FlowLayout());
	        JButton registerButton = new JButton("Đăng ký");
	        south_register.add(registerButton);
	        registerButton.addActionListener(e -> Dangkyphong_chucnang(room));
	        loadStudentInfo(thesinhvien);
	        
	        registerFrame.add(south_register, BorderLayout.SOUTH);
	        registerFrame.setVisible(true);
		}
	}
	private void Dangkyphong_chucnang(String room) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dormitorymanagement", "root", "")) {
            String sql = "SELECT RoomID, CurrentOccupants, Capacity FROM room WHERE RoomID = ? AND CurrentOccupants = Capacity";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, room);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	JOptionPane.showMessageDialog(null, "Phòng đã đầy!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }else {
            		conn.setAutoCommit(false);
            	 String sqlGetStudentID = "SELECT StudentID FROM student WHERE IDSinhVien = ?";
                 PreparedStatement stmtGetStudentID = conn.prepareStatement(sqlGetStudentID);
                 stmtGetStudentID.setString(1, thesinhvien);
                 ResultSet rsStudent = stmtGetStudentID.executeQuery();

                 if (rsStudent.next()) {
                	String studentID = rsStudent.getString("StudentID");
	            	String sql1 = "INSERT INTO contract (StudentID, RoomID, StartDate, EndDate) " +
	                        "SELECT ?, ?, '2025-01-01', '2025-12-31' FROM student WHERE IDSinhVien = ?";
					PreparedStatement stmtInsertContract = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
					stmtInsertContract.setString(1, studentID);
					stmtInsertContract.setString(2, room);
					stmtInsertContract.setString(3, thesinhvien);
					stmtInsertContract.executeUpdate();
					
					ResultSet rsContract = stmtInsertContract.getGeneratedKeys();
					int contractID = 0;
					if (rsContract.next()) {
					    contractID = rsContract.getInt(1);
					}
					
					String sqlGetPrice = "SELECT Price FROM Room WHERE RoomID = ?";
					PreparedStatement stmtGetPrice = conn.prepareStatement(sqlGetPrice);
					stmtGetPrice.setString(1, room);
					ResultSet rsPrice = stmtGetPrice.executeQuery();
					double price = 0.0;
					if (rsPrice.next()) {
					    price = rsPrice.getDouble("Price");
					}
					
					String sqlInsertInvoice = "INSERT INTO Invoice (ContractID, IssueDate, Amount, PaymentStatus) " +
	                          "VALUES (?, NOW(), ?, 'Unpaid')";
					PreparedStatement stmtInsertInvoice = conn.prepareStatement(sqlInsertInvoice);
					stmtInsertInvoice.setInt(1, contractID);
					stmtInsertInvoice.setDouble(2, price);
					stmtInsertInvoice.executeUpdate();
						
					String sql2 = "UPDATE Room SET CurrentOccupants = CurrentOccupants + 1 WHERE RoomID = ?";
					PreparedStatement stmtUpdateRoom = conn.prepareStatement(sql2);
					stmtUpdateRoom.setString(1, room);
					stmtUpdateRoom.executeUpdate();
					
					String sql3 = "UPDATE Student SET Status = 'InDormitory' WHERE IDSinhVien = ?";
					PreparedStatement stmtUpdateStudent = conn.prepareStatement(sql3);
					stmtUpdateStudent.setString(1, thesinhvien);
					stmtUpdateStudent.executeUpdate();
					conn.commit();
					JOptionPane.showMessageDialog(null, "Đăng ký phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			        hienthitablephong();
			        
                 	}else { 
	                    JOptionPane.showMessageDialog(null, "Không tìm thấy StudentID cho IDSinhVien này!", "Thông báo", JOptionPane.ERROR_MESSAGE);
	                }
                 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

	}
	private void openRoomFrame() {
		if (ttt10.getText().equalsIgnoreCase("NotInDormitory")) {
			JOptionPane.showMessageDialog(frame, "Bạn chưa có phòng, Hãy đăng ký ngay!");
			return;
		} else {
			JFrame frameopenroom = new JFrame("Thông tin phòng");
			frameopenroom.setSize(600, 400);
			frameopenroom.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameopenroom.setLocationRelativeTo(null);
	        
			frameopenroom.setLayout(new BorderLayout());
	        	      
			JPanel infoPanel = new JPanel(new BorderLayout());
	        JPanel infoPanel1 = new JPanel(new GridLayout(3, 2, 10, 10));
	        RoomIDLabel = new JLabel("Phòng	 ");
	        B_NameLabel = new JLabel("Tòa Nhà ");
	        SoluongLabel = new JLabel("Số Lượng  ");
	        T_RoomIDLabel = new JLabel();
	        T_B_NameLabel = new JLabel();
	        T_SoluongLabel = new JLabel();
	        

	        tableModel = new DefaultTableModel(new String[]{"Full Name", "Class", "Phone Number"}, 0);
	        studentTable = new JTable(tableModel);
	        loadData();
	        
	        JLabel rowPanel_1 = new JLabel(" :    " + T_RoomIDLabel.getText());
	        JLabel rowPanel_2 = new JLabel(" :  " + T_B_NameLabel.getText());
	        JLabel rowPanel_3 = new JLabel(" :  " + T_SoluongLabel.getText());

	        infoPanel1.add(RoomIDLabel);        infoPanel1.add(rowPanel_1); 
	        infoPanel1.add(B_NameLabel);        infoPanel1.add(rowPanel_2);
	        infoPanel1.add(SoluongLabel);       infoPanel1.add(rowPanel_3);
	
	        infoPanel.add(infoPanel1, BorderLayout.WEST);
	        
	        frameopenroom.add(infoPanel, BorderLayout.NORTH);
	        
	        
	        frameopenroom.add(new JScrollPane(studentTable), BorderLayout.CENTER);
	        
	        // Load data
	        
	        frameopenroom.setVisible(true);
		}
	}
	private void loadData() {
        String url = "jdbc:mysql://localhost:3306/dormitorymanagement";
        String user = "root"; 
        String password = "";
        String query = """
                SELECT 
				    r.RoomID,
				    b.Name AS BuildingName,
				    CONCAT(r.CurrentOccupants, '  /  ', r.Capacity) AS OccupantsInfo,
				    s2.FullName AS RoommateName,
				    s2.Class AS RoommateClass,
				    s2.PhoneNumber AS RoommatePhone
				FROM student s1
				JOIN contract c1 ON s1.studentID = c1.StudentID
				JOIN room r ON c1.RoomID = r.RoomID
				JOIN building b ON r.BuildingID = b.BuildingID
				JOIN contract c2 ON r.RoomID = c2.RoomID
				JOIN Student s2 ON c2.StudentID = s2.StudentID
				WHERE s1.IDSinhVien = ?;
                """;
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, thesinhvien);
            ResultSet rs = stmt.executeQuery();
            
            boolean isFirstRow = true;
            while (rs.next()) {
                if (isFirstRow) {
                	T_RoomIDLabel.setText(rs.getString("RoomID"));
                	T_B_NameLabel.setText(rs.getString("BuildingName"));
                	T_SoluongLabel.setText(rs.getString("OccupantsInfo"));
                    isFirstRow = false;
                }
                String fullName = rs.getString("RoommateName");
                String className = rs.getString("RoommateClass");
                String phoneNumber = rs.getString("RoommatePhone");
                
                if (fullName != null) {
                    tableModel.addRow(new Object[]{fullName, className, phoneNumber});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	private void hienthitablephong() {
		noRoomLabel.setVisible(false);
		
        // Panel
		JPanel infoPanel = new JPanel(new BorderLayout());
		JPanel infoPanel1_big = new JPanel(new BorderLayout()); infoPanel1_big.setBackground(Color.white);
        JPanel infoPanel1 = new JPanel(new GridLayout(3, 2, 10, 10)); infoPanel1_big.add(infoPanel1, BorderLayout.WEST);
        infoPanel.setBackground(Color.white);
        infoPanel1.setBackground(Color.white);
        RoomIDLabel = new JLabel(" Phòng	 ");
        B_NameLabel = new JLabel(" Tòa Nhà ");
        SoluongLabel = new JLabel(" Số Lượng  ");
        T_RoomIDLabel = new JLabel();
        T_B_NameLabel = new JLabel();
        T_SoluongLabel = new JLabel();
        
        // Table for student details
        tableModel = new DefaultTableModel(new String[]{"Họ và Tên", "Lớp", "Số Điện Thoại"}, 0);
        studentTable = new JTable(tableModel);
        DefaultTableCellRenderer cell= new DefaultTableCellRenderer();
        cell.setHorizontalAlignment(JLabel.CENTER);
        studentTable.getColumnModel().getColumn(0).setCellRenderer(cell); 
        studentTable.getColumnModel().getColumn(1).setCellRenderer(cell); 
        studentTable.getColumnModel().getColumn(2).setCellRenderer(cell); 
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(75);
        loadData();
        
        JLabel rowPanel_1 = new JLabel(" :    " + T_RoomIDLabel.getText());
        JLabel rowPanel_2 = new JLabel(" :  " + T_B_NameLabel.getText());
        JLabel rowPanel_3 = new JLabel(" :  " + T_SoluongLabel.getText());

        infoPanel1.add(RoomIDLabel);        infoPanel1.add(rowPanel_1); 
        infoPanel1.add(B_NameLabel);        infoPanel1.add(rowPanel_2);
        infoPanel1.add(SoluongLabel);       infoPanel1.add(rowPanel_3);
        

        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        studentScrollPane.getViewport().setBackground(Color.white);
        
        JPanel tableIn = new JPanel(new BorderLayout());
        JPanel tableIn1 = new JPanel();
        tableIn1.setPreferredSize(new Dimension(25,25)); tableIn1.setBackground(Color.WHITE);
        tableIn.add(tableIn1, BorderLayout.NORTH);  
        tableIn.add(studentScrollPane);
        infoPanel.add(infoPanel1_big, BorderLayout.NORTH);
        infoPanel.add(tableIn, BorderLayout.CENTER);
        
        right.add(infoPanel, BorderLayout.CENTER);
	}
	private void logout() {
		thesinhvien = null;
		new UILOGIN();
        frame.setVisible(false);
	}
}
