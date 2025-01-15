package Do_an_cuoi_ky;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UILOGIN extends JFrame{
	private JFrame frame;
	private JButton eyeBut, loginQuanlyBut, loginSinhvienBut, registerBut;
	private boolean isPassVisible = false;
	private JPasswordField passwordtxt;
	private JTextField usernametxt;
	private ImageIcon eyeopen, eyeclose, loginquanly1, loginquanly2, loginsinhvien1, loginsinhvien2 ,kimage2, register1, register2;
	private static String thesinhvien;
	private static String adminID;
	public UILOGIN() {
		frame = new JFrame("LOGIN SCREEN");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// -----------------Background Image-----------------
		JLayeredPane layeredPane = new JLayeredPane();
		frame.add(layeredPane);
		ImageIcon background = new ImageIcon(getClass().getResource("/UIimage/background.png"));
		
		Image image = background.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(image);
		JLabel bg = new JLabel(scaledImageIcon);
		bg.setBounds(0, 0, 1280, 720);
		layeredPane.add(bg, new Integer(0));
		
		
		// ------------------------Add Login-------------
		// ---------add Logo-------- 
		ImageIcon LoginLogo = new ImageIcon(getClass().getResource("/UIimage/LoginLogo.png"));
		Image image2 = LoginLogo.getImage().getScaledInstance(265, 265, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon2 = new ImageIcon(image2);
		JLabel logo = new JLabel(scaledImageIcon2);
		logo.setBounds(508, 225, 265, 265); 
		layeredPane.add(logo, new Integer(1));
		
		// ------------add label and text field (username)------------
		JTextField hiden = new JTextField(); hiden.setBounds(-1, -1, 0, 0); layeredPane.add(hiden, new Integer(0));
		ImageIcon user = new ImageIcon(getClass().getResource("/UIimage/user.png"));
		Image image3 = user.getImage().getScaledInstance(60, 55, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon3 = new ImageIcon(image3);
		JLabel username = new JLabel(scaledImageIcon3);
		username.setBounds(70, 250, 60, 55); 
		
		usernametxt = new JTextField();
		usernametxt.setBounds(200, 260, 220, 30);
		usernametxt.setForeground(new Color(0, 0, 150));
		usernametxt.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15)); 
		usernametxt.setOpaque(false);
		
		layeredPane.add(username, new Integer(1));
		layeredPane.add(usernametxt, new Integer(1));
		
		// -------------add label and text field (Password)-----------
		ImageIcon pass = new ImageIcon(getClass().getResource("/UIimage/pass.png"));
		Image image4 = pass.getImage().getScaledInstance(65, 55, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon4 = new ImageIcon(image4);
		JLabel password = new JLabel(scaledImageIcon4);
		password.setBounds(68, 350, 65, 55); 

		passwordtxt = new JPasswordField();		
		passwordtxt.setBounds(200, 362, 220, 30);
		passwordtxt.setForeground(new Color(0, 0, 150));
		passwordtxt.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15)); 
		passwordtxt.setOpaque(false);
		
		// ----password eye icon (action and close)
		// ---eye open
		ImageIcon eye1 = new ImageIcon(getClass().getResource("/UIimage/eye.png"));
		Image image5 = eye1.getImage().getScaledInstance(45, 25, Image.SCALE_SMOOTH);
		eyeopen = new ImageIcon(image5);
		// ---eyeclose
		ImageIcon eye2 = new ImageIcon(getClass().getResource("/UIimage/eyeclose.png"));
		Image image6 = eye2.getImage().getScaledInstance(45, 25, Image.SCALE_SMOOTH);
		eyeclose = new ImageIcon(image6);
		// ---eye action
		eyeBut = new JButton();
		eyeBut.setIcon(eyeclose);
		eyeBut.setBounds(370, 365, 45, 25);
		eyeBut.setFocusable(false);
		eyeBut.setContentAreaFilled(false);
		eyeBut.setBorderPainted(false); 
		eyeBut.addActionListener(i -> eyeOpenClose());
		
		layeredPane.add(password, new Integer(1)); 
		layeredPane.add(passwordtxt, new Integer(1));
		layeredPane.add(eyeBut, new Integer(2));
		
		// --------------Login button------------
		//login before
		ImageIcon login1 = new ImageIcon(getClass().getResource("/UIimage/loginquanly1.png"));
		Image image7 = login1.getImage().getScaledInstance(200, 55, Image.SCALE_SMOOTH);
		loginquanly1 = new ImageIcon(image7);
		//login action
		loginQuanlyBut = new JButton();
		loginQuanlyBut.setIcon(loginquanly1);
		loginQuanlyBut.setBounds(100, 460, 200, 55);
		loginQuanlyBut.setFocusable(false);
		loginQuanlyBut.setContentAreaFilled(false);
		loginQuanlyBut.setBorderPainted(false); 
		loginQuanlyBut.addActionListener(i -> clickLogin(loginQuanlyBut));
		
		login1 = new ImageIcon(getClass().getResource("/UIimage/loginsinhvien1.png"));
		image7 = login1.getImage().getScaledInstance(200, 55, Image.SCALE_SMOOTH);
		loginsinhvien1 = new ImageIcon(image7);
		loginSinhvienBut = new JButton();
		loginSinhvienBut.setIcon(loginsinhvien1);
		loginSinhvienBut.setBounds(271, 460, 200, 55);
		loginSinhvienBut.setFocusable(false);
		loginSinhvienBut.setContentAreaFilled(false);
		loginSinhvienBut.setBorderPainted(false); 
		loginSinhvienBut.addActionListener(i -> clickLogin(loginSinhvienBut));
		
		// register
		login1 = new ImageIcon(getClass().getResource("/UIimage/register1.png"));
		image7 = login1.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
		register1 = new ImageIcon(image7);
		
		registerBut = new JButton();
		registerBut.setIcon(register1);
		registerBut.setBounds(932, 502, 220, 60);
		registerBut.setFocusable(false);
		registerBut.setContentAreaFilled(false);
		registerBut.setBorderPainted(false); 
		registerBut.addActionListener(i -> clickLogin(registerBut));
		
		
		
		layeredPane.add(loginQuanlyBut, new Integer(1));
		layeredPane.add(loginSinhvienBut, new Integer(2));
		layeredPane.add(registerBut, new Integer(2));
		// KETNOICSDL
		 
		frame.setVisible(true);

	}
	private void eyeOpenClose() {
		if (isPassVisible) {
			passwordtxt.setEchoChar('•');
			isPassVisible = false;
			eyeBut.setIcon(eyeclose);
		} else {
            passwordtxt.setEchoChar((char) 0); 
            isPassVisible = true;
            eyeBut.setIcon(eyeopen);
		}
	}
	private void clickLogin(JButton k) {
		ImageIcon kimage1 = null;
		kimage2 = null;
		if (k == loginQuanlyBut) {
			kimage1 = loginquanly2;
			kimage2 = loginquanly1;
			k.setIcon(kimage1);
		    javax.swing.Timer timer = new javax.swing.Timer(100, e -> k.setIcon(kimage2));
		    timer.setRepeats(false); 
		    timer.start();
		    if (kiemtraTTManager(usernametxt.getText(), new String (passwordtxt.getPassword()))) {
		    	javax.swing.JOptionPane.showMessageDialog(frame,"Đăng nhập thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		    	setAdminID(usernametxt);
		    	new Manager();
		    	dispose();
		        frame.setVisible(false);
		    } else {
		        javax.swing.JOptionPane.showMessageDialog(frame, "Lỗi khi truy vấn dữ liệu!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
		    }
		}
		else if (k == loginSinhvienBut) {
			kimage1 = loginsinhvien2;
			kimage2 = loginsinhvien1;
			k.setIcon(kimage1);
		    javax.swing.Timer timer = new javax.swing.Timer(100, e -> k.setIcon(kimage2));
		    timer.setRepeats(false); 
		    timer.start();
		    if (kiemtraTTStudent(usernametxt.getText(), new String (passwordtxt.getPassword()))) {
		    	javax.swing.JOptionPane.showMessageDialog(frame,"Đăng nhập thành công!", "Thành công", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		    	
		    	setThesinhvien(usernametxt);
		    	new Student();
		    	dispose();
		        frame.setVisible(false);
		    } else {
		        javax.swing.JOptionPane.showMessageDialog(frame, "Lỗi khi truy vấn dữ liệu!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
		    }
		}
		else if (k == registerBut) {
            new UIREGISTER();
            dispose();
            frame.setVisible(false);
		}
		else {System.out.println();}
	    
	} 
	// KT connect
	private Connection connectToDatabase() {
	    try {
	        String url = "jdbc:mysql://localhost:3306/dormitorymanagement"; 
	        String user = "root"; 
	        String password = "";
	        Connection conn = DriverManager.getConnection(url, user, password);
	        return conn;
	    } catch (Exception e) {
	        e.printStackTrace();
	        javax.swing.JOptionPane.showMessageDialog(frame, "Lỗi kết nối cơ sở dữ liệu!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
	}
	private boolean kiemtraTTManager(String username, String password) {
	    Connection conn = connectToDatabase();
	    if (conn != null) {
	        try {
	            String query = "SELECT * FROM manager WHERE AdminID = ? AND Password = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            javax.swing.JOptionPane.showMessageDialog(frame, "Lỗi khi truy vấn dữ liệu!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    return false;
	}
	private boolean kiemtraTTStudent(String username, String password) {
	    Connection conn = connectToDatabase();
	    if (conn != null) {
	        try {
	            String query = "SELECT * FROM student WHERE IDSinhVien = ? AND Password = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            javax.swing.JOptionPane.showMessageDialog(frame, "Lỗi khi truy vấn dữ liệu!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    return false;
	}
	public static void setThesinhvien(JTextField usernametxt) {
        thesinhvien = usernametxt.getText();
    }
	public static String getThesinhvien() {
	        return thesinhvien;
	    }
	public static void setAdminID(JTextField usernametxt) {
        adminID = usernametxt.getText();
    }
	public static String getAdminID() {
	        return adminID;
	    }
	public static void main(String[] args) {
		new UILOGIN();
	
	}
}
