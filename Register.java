import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.PlainDocument;


public class Register extends JFrame implements ActionListener{
 
private static final int Center = 0;
JLabel labelRegisterSpace;
JTextField regusernameTextField;
JTextField regfirstnameTextField;
JTextField reglastnameTextField;
JTextField regemailTextField;
JPasswordField regpasswordTextField;
JPasswordField regconfirmpasswordTextField;
JButton registerBT, backloginBT;
JLabel errorlabel;
JRadioButton maleRB, femaleRB;
JSpinner ageSp;

Connect con = Connect.getConnection();

 public Register() {
  register();
  init();
 }
 
 void init() {
  this.setTitle("PokemoNK");
  this.setSize(900,700);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  setVisible(true);
 }

 
 public void register(){

	 
  JPanel regpanelMain = new JPanel(new GridLayout(0,1));
  GridBagConstraints gbc = new GridBagConstraints(); 
 //Panel Register
   JPanel panelRegister = new JPanel(new GridBagLayout());
   JPanel panelRegister2 = new JPanel(new GridBagLayout());
   JPanel panelRegister3 = new JPanel(new GridBagLayout());
  
  gbc.insets = new Insets(5,5,5,5);
  gbc.anchor = GridBagConstraints.LINE_START;
  
   //register title
   JPanel regpanelLabel = new JPanel();
   JLabel registerFormLabel = new JLabel("Register");
   regpanelLabel.add(registerFormLabel);
//   regpanelMain.add(regpanelLabel);
   regpanelLabel.setBackground(Color.cyan);
   panelRegister.add(registerFormLabel);
   
   JLabel regusernameLabel = new JLabel("Username");
   regusernameTextField = new JTextField();
   regusernameTextField.setPreferredSize(new Dimension(555,30));
   gbc.gridx = 0;
   gbc.gridy = 0;
   panelRegister2.add(regusernameLabel, gbc);
   gbc.gridx = 0;
   gbc.gridy = 1;
   panelRegister2.add(regusernameTextField, gbc);
   
   
   //firstname
   JLabel regfirstnameLabel = new JLabel("First Name");
   regfirstnameTextField = new JTextField();
   regfirstnameTextField.setPreferredSize(new Dimension(555,30));
   gbc.gridx = 0;
   gbc.gridy = 2;
   panelRegister2.add(regfirstnameLabel, gbc);
   gbc.gridx = 0;
   gbc.gridy = 3;
   panelRegister2.add(regfirstnameTextField,gbc);
   
   
   
   //lastname
   JLabel reglastnameLabel = new JLabel("Last Name");
   reglastnameTextField = new JTextField();
   reglastnameTextField.setPreferredSize(new Dimension(555,30));
   gbc.gridx = 0;
   gbc.gridy = 4;
   panelRegister2.add(reglastnameLabel, gbc);
   gbc.gridx = 0;
   gbc.gridy = 5;
   panelRegister2.add(reglastnameTextField, gbc);
   
   
   
   //Email
   JLabel regemailLabel = new JLabel("E-mail");
   regemailTextField = new JTextField();
   regemailTextField.setPreferredSize(new Dimension(555,30));
   gbc.gridx = 0;
   gbc.gridy = 6;
   panelRegister2.add(regemailLabel, gbc);
   gbc.gridx = 0;
   gbc.gridy = 7;
   panelRegister2.add(regemailTextField, gbc);
 
   //gender
   JPanel panelRB = new JPanel();
   maleRB = new JRadioButton("Male");
   femaleRB = new JRadioButton("Female");
   ButtonGroup genderBG = new ButtonGroup();
   genderBG.add(maleRB);
   genderBG.add(femaleRB);
   gbc.gridy = 8;
   panelRB.add(maleRB);
   panelRB.add(femaleRB);
   panelRegister2.add(panelRB, gbc);
   
   maleRB.setBackground(Color.CYAN);
   femaleRB.setBackground(Color.CYAN);
   
   
   maleRB.addActionListener(this);
   femaleRB.addActionListener(this);

   //age
     ageSp = new JSpinner(new SpinnerNumberModel(11, 11, 99, 1));
  JLabel ageLabel = new JLabel("Age");
    ageSp.setPreferredSize(new Dimension(555, 30));
     gbc.gridx = 0;
   gbc.gridy = 9;
   
   panelRegister2.add(ageLabel, gbc);
    gbc.gridx = 0;
    gbc.gridy = 10;
    panelRegister2.add(ageSp, gbc);
    
   //password
 
   JLabel regpasswordLabel = new JLabel("Password");
   regpasswordTextField = new JPasswordField();
   regpasswordTextField.setPreferredSize(new Dimension(555,30));
   gbc.gridx = 0;
   gbc.gridy = 11;
   panelRegister2.add(regpasswordLabel, gbc);
   gbc.gridx = 0;
   gbc.gridy = 12;
   panelRegister2.add(regpasswordTextField, gbc);
   
   //confirmPassword
   JLabel regconfirmpasswordLabel = new JLabel("Confirm Password");
   regconfirmpasswordTextField = new JPasswordField();
   regconfirmpasswordTextField.setPreferredSize(new Dimension(555,30));
   gbc.gridx = 0;
   gbc.gridy = 13;
   panelRegister2.add(regconfirmpasswordLabel, gbc);
   gbc.gridx = 0;
   gbc.gridy = 14;
   panelRegister2.add(regconfirmpasswordTextField, gbc);   
   panelRegister.setBackground(Color.cyan);
   panelRegister2.setBackground(Color.CYAN);
   panelRegister3.setBackground(Color.CYAN);
   
   //space
 
   JPanel panelSpace = new JPanel();
   panelSpace.setBackground(Color.cyan);
   
   //button
   registerBT = new JButton("Register");
   errorlabel = new JLabel("");
   backloginBT = new JButton("Back to Login");
   gbc.gridx = 0;
   gbc.gridy = 0;
   panelRegister3.add(registerBT, gbc);
   gbc.gridx = 2;
   gbc.gridy = 0;
   panelRegister3.add(backloginBT, gbc);
   gbc.gridy = 1;
   panelRegister3.add(errorlabel, gbc);
   errorlabel.setForeground(Color.red);
 
   
   regpanelMain.add(panelRegister);
   regpanelMain.add(panelRegister2);
   regpanelMain.add(panelRegister3);
   
   this.add(panelRegister, BorderLayout.NORTH);
   this.add(panelRegister2, BorderLayout.CENTER);
   this.add(panelRegister3, BorderLayout.SOUTH); 
   
   registerBT.addActionListener(this);
   backloginBT.addActionListener(this);
 }


@Override
public void actionPerformed(ActionEvent e) {
 
 if (e.getSource() == backloginBT) {
 this.dispose();
 Login login = new Login();
 login.setVisible(true);
	
 
 }else if(e.getActionCommand() == "Register"){
 String GTuname, GTfname, GTlname, GTemail, GTgender, GTpass, GTcpass;
 int GTage;
   GTuname = regusernameTextField.getText();
   GTfname = regfirstnameTextField.getText();
   GTlname = reglastnameTextField.getText();
   GTemail = regemailTextField.getText();
   GTage = (int) ageSp.getValue();
   GTpass = String.valueOf(regpasswordTextField.getPassword());
   GTcpass = String.valueOf(regconfirmpasswordTextField.getPassword());
   
   if (GTuname.isEmpty() || GTfname.isEmpty() || GTlname.isEmpty() || GTemail.isEmpty() || GTpass.isEmpty() || GTcpass.isEmpty()){
   errorlabel.setText("All text must be filled");

   }else if (GTuname.length() < 8 || GTuname.length() > 15){
    errorlabel.setText("Username length must be more than 8 and no more than 15");
    
   }else if (!(GTemail.contains("@") || GTemail.contains(".com"))){
    errorlabel.setText("E-mail format (XXXX..@XXXX.com");
    
   }else if(!(maleRB.isSelected() || femaleRB.isSelected())) {
	errorlabel.setText("Choose one of the gender"); 
   
   }else if (GTpass.length() > 20){
    errorlabel.setText("Password must less than 20");
    
   }else if(!(GTpass.matches("^[a-zA-Z0-9@\\#$%&*()_+\\]\\[';:?.,!^-]{8,}$")) && GTpass.matches("^[0-9@\\#$%&*()_+\\]\\[';:?.,!^-]{8,}$")){
    errorlabel.setText("Password must contains character and strings");
   
   }else if(!GTcpass.equals(GTpass)) {
   errorlabel.setText("Password must be the same");
   
   }else{
	   if (maleRB.isSelected()) {
		   GTgender = "male" ;
		   PreparedStatement ps = con.preparedStatement("INSERT INTO user VALUES(null,?,?,?,?,?,?)");
		   
		   try {
			ps.setString(1, GTuname);
			ps.setString(2, GTfname + GTlname);
			ps.setInt(3, (GTage));
			ps.setString(4, GTemail);
			ps.setString(5, GTgender);
			ps.setString(6, GTpass);
			ps.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	   
		 
 } else if (femaleRB.isSelected()) {
	 GTgender = "female";
	   PreparedStatement ps = con.preparedStatement("INSERT INTO user VALUES(null,?,?,?,?,?,?)");
	   
	   try {
		ps.setString(1, GTuname);
		ps.setString(2, GTfname + GTlname);
		ps.setInt(3, (GTage));
		ps.setString(4, GTemail);
		ps.setString(5, GTgender);
		ps.setString(6, GTpass);
		ps.execute();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   }
	   this.dispose();
	   Login login = new Login();
	   login.setVisible(true);
   }
 }
 }
 
 }
 