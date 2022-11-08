import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.text.PlainDocument;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Login extends JFrame implements ActionListener{
	
	JPanel panelLabel, panelLogin, panelButton;
	JLabel labelRegisterSpace, usernameLabel, passwordLabel;
	JTextField usernameTextField;
	JPasswordField passwordTextField;
	
	Connect con = Connect.getConnection();

	
	public Login() {
		
		login();
		init();
	}
	
	void init() {
		this.setTitle("PokemoNK");
		this.setSize(900,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void login() {
		
		
		JPanel panelMain = new JPanel(new GridLayout(0,1));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		gbc.anchor = GridBagConstraints.LINE_START;
		
		//login title
		panelLabel = new JPanel(new GridBagLayout());
		JLabel loginFormLabel = new JLabel("Login");
		panelLabel.add(loginFormLabel);
		panelMain.add(panelLabel);
		
		loginFormLabel.setFont(new Font("Login", Font.BOLD, 50));
		panelLabel.setBackground(Color.cyan);
		
		//username
		panelLogin = new JPanel(new GridBagLayout());
		
		usernameLabel = new JLabel("Username");
		usernameTextField = new JTextField();
		usernameTextField.setPreferredSize(new Dimension(555,50));
		gbc.gridx=0;
		gbc.gridy=0;
		panelLogin.add(usernameLabel, gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		panelLogin.add(usernameTextField,gbc);
		
		//password
		passwordLabel = new JLabel("Password");
		passwordTextField = new JPasswordField();
		passwordTextField.setPreferredSize(new Dimension(555,50));
		gbc.gridx=0;
		gbc.gridy=2;
		panelLogin.add(passwordLabel, gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		panelLogin.add(passwordTextField,gbc);
		
		panelMain.add(panelLogin);
		panelLogin.setBackground(Color.cyan);
		
		
		//button
		JPanel panelButton = new JPanel(new GridBagLayout());
		JButton login = new JButton("Login");
		login.setPreferredSize(new Dimension(555,40));
		gbc.gridx=0;
		gbc.gridy=0;
		panelButton.add(login, gbc);
		JButton register = new JButton("Register");
		register.setPreferredSize(new Dimension(555,40));
		gbc.gridx=0;
		gbc.gridy=1;
		panelButton.add(register,gbc);
		labelRegisterSpace = new JLabel("");
		labelRegisterSpace.setPreferredSize(new Dimension(555,40));
		gbc.gridx=0;
		gbc.gridy=2;
		panelButton.add(labelRegisterSpace,gbc);
		
		
		labelRegisterSpace.setHorizontalAlignment(JLabel.CENTER);
		
		panelMain.add(panelButton);
		panelButton.setBackground(Color.cyan);
		labelRegisterSpace.setForeground(Color.red);
		
		login.addActionListener(this);
		register.addActionListener(this);
		
		this.add(panelMain);
		 }
		
	

	public static void main(String[] args) {
		new Login();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Login") {
			String userTemp, GTpass;
			userTemp = usernameTextField.getText();
			GTpass = String.valueOf(passwordTextField.getPassword());
			
			if(userTemp.isEmpty() || GTpass.isEmpty()) {
				labelRegisterSpace.setText("All field must be filled");
				
			} else {
				 ResultSet rs = con.read("SELECT * FROM user");
				 try {
					while(rs.next()){
						 if(userTemp.equals(rs.getString(2))){
							 if(userTemp.contains("admin")){
								 if(GTpass.equals(rs.getString(7))){
									 int userid = rs.getInt("UserId");
									 this.dispose();
										 ManagePokemon admin = new ManagePokemon(userid);
										 admin.setVisible(true);
										 return;
								 		} else{
											 labelRegisterSpace.setText("Username or password false");
							 		}
							 } 
								 else if(GTpass.equals(rs.getString(7))){
								  int userid = rs.getInt("UserId");
								  this.dispose();
									 MainUser user = new MainUser(userid);
									 user.setVisible(true);
								 }
							 }else{
								 labelRegisterSpace.setText("Username or password false");
							 
						 }
						 
						 
					 }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		} else if (e.getActionCommand() == "Register") {
			this.dispose();
			Register reg = new Register();
			reg.setVisible(true);
		}
		}
			
		
	}



