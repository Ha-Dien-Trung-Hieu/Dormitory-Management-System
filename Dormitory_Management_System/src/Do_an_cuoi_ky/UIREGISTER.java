package Do_an_cuoi_ky;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UIREGISTER extends JFrame{
	private JFrame frame;
	private JButton eyeBut, registerBut, returnimage;
	private boolean isPassVisible = false;
	private JPasswordField passwordtxt;
	private ImageIcon eyeopen, eyeclose, register1, register2, return1;
	private JTextField usernametxt, sdttxt, IDtxt, emailtxt;
	private JCheckBox Noiquy;
	private static ImageIcon Noiquyicon;
	public UIREGISTER() {
		frame = new JFrame("REGISTER SCREEN");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// -----------------Background Image-----------------
		JLayeredPane layeredPane = new JLayeredPane();
		frame.add(layeredPane);
		ImageIcon background = new ImageIcon(getClass().getResource("/UIimage/background1.png"));
		
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
		// JtextField fake 
		JTextField hiden = new JTextField(); hiden.setBounds(-1, -1, 0, 0); layeredPane.add(hiden, new Integer(0));
		ImageIcon user = new ImageIcon(getClass().getResource("/UIimage/user.png"));
		Image image3 = user.getImage().getScaledInstance(60, 55, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon3 = new ImageIcon(image3);
		JLabel username = new JLabel(scaledImageIcon3);
		username.setBounds(820, 210, 60, 55); 

		usernametxt = new JTextField("Nhập Thẻ Sinh Viên");
		usernametxt.setBounds(950, 225, 220, 30);
		usernametxt.setForeground(Color.WHITE);
		usernametxt.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15)); 
		usernametxt.setOpaque(false);

		usernametxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
		        if (usernametxt.getText().equals("Nhập Thẻ Sinh Viên")) {
		            usernametxt.setText("");
		            usernametxt.setForeground(new Color(0, 0, 150));
		        }
		    } 
			@Override
			public void focusLost(FocusEvent e) {
		        if (usernametxt.getText().isEmpty()) {
		            usernametxt.setText("Nhập Thẻ Sinh Viên");
		            usernametxt.setForeground(Color.WHITE);
		        }
		    }
		});
		// sddt
		user = new ImageIcon(getClass().getResource("/UIimage/phone.png"));
		image3 = user.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		scaledImageIcon3 = new ImageIcon(image3);
		JLabel sdt = new JLabel(scaledImageIcon3);
		sdt.setBounds(826, 320, 50, 50); 

		sdttxt = new JTextField("Nhập SĐT");
		sdttxt.setBounds(950, 330, 220, 30);
		sdttxt.setForeground(Color.WHITE);
		sdttxt.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15)); 
		sdttxt.setOpaque(false);

		sdttxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
		        if (sdttxt.getText().equals("Nhập SĐT")) {
		    		sdttxt.setText("");
		    		sdttxt.setForeground(new Color(0, 0, 150));
		        }
		    }
			@Override
			public void focusLost(FocusEvent e) {
		        if (sdttxt.getText().isEmpty()) {
		        	sdttxt.setText("Nhập SĐT");
		        	sdttxt.setForeground(Color.WHITE);
		        }
		    }
		});
		// ID (sv) 
		ImageIcon id = new ImageIcon(getClass().getResource("/UIimage/id.png"));
		Image image99 = id.getImage().getScaledInstance(85, 40, Image.SCALE_SMOOTH);
		ImageIcon idimage = new ImageIcon(image99);
		JLabel ID = new JLabel(idimage);
		ID.setBounds(805, 270, 85, 40); 
 
		IDtxt = new JTextField("Nhập CCCD");
		IDtxt.setBounds(950, 275, 220, 30);
		IDtxt.setForeground(Color.WHITE);
		IDtxt.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15)); 
		IDtxt.setOpaque(false);

		IDtxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
		        if (IDtxt.getText().equals("Nhập CCCD")) {
		        	IDtxt.setText("");
		        	IDtxt.setForeground(new Color(0, 0, 150));
		        }
		    }
			@Override
			public void focusLost(FocusEvent e) {
		        if (IDtxt.getText().isEmpty()) {
		        	IDtxt.setText("Nhập CCCD");
		        	IDtxt.setForeground(Color.WHITE);
		        }
		    }
		});
		
		

		// email
		ImageIcon mail = new ImageIcon(getClass().getResource("/UIimage/email.png"));
		Image image9 = mail.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon9 = new ImageIcon(image9);
		JLabel email = new JLabel(scaledImageIcon9);
		email.setBounds(829, 382, 45, 45); 
		
		emailtxt = new JTextField("Nhập Email");
		emailtxt.setBounds(950, 388, 220, 30);
		emailtxt.setForeground(Color.WHITE);		
		emailtxt.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15)); 
		emailtxt.setOpaque(false);

		emailtxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
		        if (emailtxt.getText().equals("Nhập Email")) {
		        	emailtxt.setText("");
		        	emailtxt.setForeground(new Color(0, 0, 150));
		        }
		    }
			@Override
			public void focusLost(FocusEvent e) {
		        if (emailtxt.getText().isEmpty()) {
		        	emailtxt.setText("Nhập Email");
		        	emailtxt.setForeground(Color.WHITE);
		        }
		    }
		});
		
		layeredPane.add(username, new Integer(1));
		layeredPane.add(usernametxt, new Integer(1));
		
		layeredPane.add(ID, new Integer(1));
		layeredPane.add(IDtxt, new Integer(1));
		
		layeredPane.add(email, new Integer(1));
		layeredPane.add(emailtxt, new Integer(1));
		
		layeredPane.add(sdt, new Integer(1));
		layeredPane.add(sdttxt, new Integer(1));
		// -------------add label and text field (Password)-----------
		ImageIcon pass = new ImageIcon(getClass().getResource("/UIimage/pass.png"));
		Image image4 = pass.getImage().getScaledInstance(65, 55, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon4 = new ImageIcon(image4);
		JLabel password = new JLabel(scaledImageIcon4);
		password.setBounds(820, 440, 65, 55); 

		passwordtxt = new JPasswordField();		
		passwordtxt.setBounds(950, 455, 220, 30);
		passwordtxt.setForeground(Color.WHITE);
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
		eyeBut.setBounds(1120, 456, 45, 25);
		eyeBut.setFocusable(false);
		eyeBut.setContentAreaFilled(false);
		eyeBut.setBorderPainted(false); 
		eyeBut.addActionListener(i -> eyeOpenClose());
		
		layeredPane.add(password, new Integer(1));
		layeredPane.add(passwordtxt, new Integer(1));
		layeredPane.add(eyeBut, new Integer(2));
		
		// check box noi quy
		Noiquy = new JCheckBox("Nội Quy Kí Túc Xá");
		Noiquy.setForeground(Color.WHITE);
		Noiquy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Noiquy.isSelected()) {
					OpenNoiQuy();
				}
					
			}
		});
		Noiquy.setBounds(947, 500, 130, 20);
		Noiquy.setBorderPainted(false); 
		Noiquy.setFocusPainted(false); 
		Noiquy.setOpaque(false);      
		Noiquy.setContentAreaFilled(false); 
		layeredPane.add(Noiquy, new Integer(1));
		// REGISTER BUTTON
		// register
		ImageIcon login1 = new ImageIcon(getClass().getResource("/UIimage/register1.png"));
		Image image7 = login1.getImage().getScaledInstance(220, 55, Image.SCALE_SMOOTH);
		register1 = new ImageIcon(image7);
		
		registerBut = new JButton();
		registerBut.setIcon(register1);
		registerBut.setBounds(900, 520, 220, 55);
		registerBut.setFocusable(false);
		registerBut.setContentAreaFilled(false); 
		registerBut.setBorderPainted(false); 
		registerBut.addActionListener(i -> clickLogin(registerBut));
		
		layeredPane.add(registerBut, new Integer(2));
		
		
		// return 
		ImageIcon returnicon = new ImageIcon(getClass().getResource("/UIimage/return.png"));
		Image image65 = returnicon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		return1 = new ImageIcon(image65);
		returnimage = new JButton(return1);
		returnimage.setBounds(197, 445, 120, 120); 
		returnimage.addActionListener(e -> clickLogin(returnimage));
		returnimage.setFocusable(false);
		returnimage.setContentAreaFilled(false);
		returnimage.setBorderPainted(false); 
		returnimage.setBorder(null);
		layeredPane.add(returnimage, new Integer(1));
		
		
		frame.setVisible(true);

	}
	private void eyeOpenClose() {
		if (isPassVisible) {
			passwordtxt.setEchoChar('•');
			isPassVisible = false;
			eyeBut.setIcon(eyeclose);
			passwordtxt.setForeground(Color.WHITE);		

		} else {
            passwordtxt.setEchoChar((char) 0); 
            isPassVisible = true;
            eyeBut.setIcon(eyeopen);
            passwordtxt.setForeground(new Color(0, 0, 150));
		}
	}
	private void clickLogin(JButton k) {
		if (k == registerBut) {
			String cccd = IDtxt.getText();
			String id = usernametxt.getText();
			String password = new String(passwordtxt.getPassword());
			String phone = sdttxt.getText();
			String email = emailtxt.getText();
			
			if (cccd.equals("Nhập CCCD") || cccd.equals("Nhập Thẻ Sinh Viên") ||
		            phone.equals("Nhập SĐT") || password.isEmpty() || email.equals("Nhập Email")) {
		            JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		    } else if (!Noiquy.isSelected()) {
		    	JOptionPane.showMessageDialog(frame, "Vui lòng đọc kĩ Nội Quy Kí Túc Xá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
		    } else
				try {
					if (isStudentExists(id)) {
						JOptionPane.showMessageDialog(frame, "Thẻ Sinh Viên Đã Tồn Tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					    return;
					} else
					try {
						saveToDatabase(id, cccd, password, phone, email);
						JOptionPane.showMessageDialog(frame, "Đăng ký thành công!");
						new UILOGIN();
					    dispose();
					    frame.setVisible(false);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Đăng ký thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		else if (k == returnimage) {
	    	new UILOGIN();
	    	dispose();
            frame.setVisible(false);
	    	
	    	
	    }
	}
	private boolean isStudentExists(String id) throws Exception {
	    String url = "jdbc:mysql://localhost:3306/dormitorymanagement";
	    String user = "root";
	    String password = "";

	    String sql = "SELECT COUNT(*) FROM student WHERE IDSinhVien = ?";

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, id);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0; 
	            }
	        }
	    }
	    return false;
	} 
	private void saveToDatabase(String id, String cccd, String password, String phone, String email) throws Exception {
	    String url = "jdbc:mysql://localhost:3306/dormitorymanagement"; 
	    String user = "root"; 
	    
	    String dbPassword = "";
	    
	    String sql = "INSERT INTO student (IDSinhVien, CCCDID, Password, PhoneNumber, Email) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url, user, dbPassword);
	    	PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
		        stmt.setString(1, id);
		        stmt.setString(2, cccd);
		        stmt.setString(3, password);
		        stmt.setString(4, phone);
		        stmt.setString(5, email);
		        stmt.executeUpdate();
	    }
	    
	}
	// Frame Noi Quy
	private void OpenNoiQuy() {
		JFrame frame_NoiQuy = new JFrame("Nội Quy Kí Túc Xá");
		frame_NoiQuy.setSize(640, 900);
		frame_NoiQuy.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_NoiQuy.setLocationRelativeTo(null);
		frame_NoiQuy.setResizable(false);
		JLayeredPane layerpane = new JLayeredPane();
		Noiquyicon = new ImageIcon(getClass().getResource("/UIimage/NoiQuy.png"));
		Image image98 = Noiquyicon.getImage().getScaledInstance(640, 900, Image.SCALE_SMOOTH);
		ImageIcon noiquyimage = new ImageIcon(image98);
		JLabel NoiQuybg = new JLabel(noiquyimage);
		NoiQuybg.setBounds(0, 0, 640, 900);
		layerpane.add(NoiQuybg, new Integer(0));
		
		frame_NoiQuy.add(layerpane);
		frame_NoiQuy.setVisible(true);
	}
	public static void main (String[] args) {
		new UIREGISTER();
	}
}
