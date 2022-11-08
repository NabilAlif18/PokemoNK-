import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class BagForm extends JFrame implements ActionListener{

 /**
  * 
  */
 private static final long serialVersionUID = 1L;
 JPanel panelMain, panelUp, panelMid,panelMid2, panelBot;
 JTable table;
 DefaultTableModel dtm;
 JLabel pokemonID, pokemonName, pokemonLevel, pokemonType, qty, id, title, error;
 JTextField pokeID;
 JButton checkout, btm, delete;
 int headerid, qty2, pokeid;
 Connect con;
 int useridnew;
 
 
 public BagForm(int userid) {
  this.useridnew = userid;
  bagform();
  init();
  getdata();
 }
 
 void init() {
  this.setTitle("Manage Bag");
  this.setSize(900,700);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  this.setVisible(true);
 }
 
 public void bagform() {
  
  con = Connect.getConnection();
  
  GridBagConstraints gbc = new GridBagConstraints();
  gbc.insets = new Insets(5,5,5,5);
  gbc.anchor = GridBagConstraints.LINE_START;
  
  //panel
  panelMain = new JPanel(new GridLayout(4,1));
  panelUp = new JPanel(new GridLayout());
  panelMid = new JPanel(new GridBagLayout());
  panelMid2 = new JPanel(new GridBagLayout());
  panelBot = new JPanel(new GridLayout(3,2));
  
  //table
  pokemonID = new JLabel("Pokemon ID");
  pokemonName = new JLabel("Pokemon Name");
  pokemonLevel = new JLabel("Pokemon Level");
  pokemonType = new JLabel("Pokemon Type");
  qty = new JLabel("Quantity");
  
  //input id
  title = new JLabel("Manage Cart");
  id = new JLabel("Pokemon Id :");
  pokeID = new JTextField();
  pokeID.setPreferredSize(new Dimension (200,30));
  
  
  title.setFont(new Font("Manage Cart", Font.BOLD, 50));
  
  //button
  JPanel panelbtm = new JPanel(new GridBagLayout());
  
  btm = new JButton("Back to Main");
  gbc.gridx = 1;
  gbc.gridy = 0;
  panelbtm.add(btm, gbc);
  btm.addActionListener(this);
  panelbtm.setBackground(Color.cyan);
  
  checkout = new JButton("Checkout");
  gbc.gridx = 2;
  gbc.gridy = 0;
  panelbtm.add(checkout, gbc);
  checkout.addActionListener(this);
  
  JPanel paneldelete = new JPanel(new GridBagLayout());
  delete = new JButton("Delete");
  gbc.gridx = 0;
  gbc.gridy = 0;
  paneldelete.add(delete, gbc);
  delete.addActionListener(this);
  paneldelete.setBackground(Color.cyan);
  
  JPanel showlabel = new JPanel(new GridBagLayout());
  error = new JLabel("");
  gbc.gridx = 0;
  gbc.gridy = 0;
  showlabel.add(error, gbc);
  showlabel.setBackground(Color.cyan);
  error.setForeground(Color.red);
  
  //header table
  String[] header = {"Pokemon ID", "Pokemon Name", "Pokemon Level", "Pokemon Type", "Quantity"};
  dtm = new DefaultTableModel(header,0);
   
   table = new JTable(dtm);

   

   
   
  JScrollPane sp = new JScrollPane(table);
  
  //add panel
  panelMain.add(panelUp);
  panelMain.add(panelMid);
  panelMain.add(panelMid2);
  panelMain.add(panelBot);
  
  panelUp.add(sp);
  table.setBackground(Color.cyan);
  
  panelMid.add(title);
  panelMid.setBackground(Color.cyan);
  panelMid2.add(id);
  panelMid2.add(pokeID);
  panelMid2.setBackground(Color.cyan);
  
  panelBot.add(paneldelete);
  panelBot.add(panelbtm);
  panelBot.add(showlabel);
  panelBot.setBackground(Color.cyan);
  
  
  this.add(panelMain);
  
  
 }
 
  
  

 
 public void getdata(){
  dtm.setRowCount(0);
   String query = String.format("SELECT * FROM pokemon p JOIN cart c ON p.PokemonId = c.PokemonId WHERE c.UserId = %d",  useridnew);
 
        
   ResultSet rs = con.executeQuery(query);
 
    try {
     while(rs.next()) {
     int id, lvl,qty;
     String name, type; 
     id = rs.getInt("PokemonId");
     lvl = rs.getInt("PokemonLevel");
     type = rs.getString("PokemonType");
     name = rs.getString("PokemonName");
     qty = rs.getInt("Quantity");
     
      dtm.addRow(new String[] {id +"", name, lvl +"", type, qty+""});
     
     }
    } catch (Exception e) {

    }
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {
  
  if (e.getActionCommand() == "Back to Main") {
   this.dispose();
   MainUser mu = new MainUser(useridnew);
   mu.setVisible(true);
  } else if(e.getActionCommand() == "Checkout") {
   
   String tempID;
   tempID = pokeID.getText();
   
   if(tempID.isEmpty()){
    error.setText("Pokemon is empty");
   }else{
    
    ResultSet rs = con.read("SELECT * FROM cart");
   try {
    while(rs.next()){
     if(Integer.parseInt(tempID) == (rs.getInt(1))){
      
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
     
      pokeid = rs.getInt("PokemonId");
      qty2 = rs.getInt("Quantity");
        
       PreparedStatement ps = con.preparedStatement("INSERT INTO headertransaction VALUES(null,?,?)");
       try {
      
    	   ps.setInt(1, useridnew);
      ps.setTimestamp(2, timestamp);;
      ps.execute();
     
     
       
       } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
       }
             
      String Delete = String.format("DELETE FROM cart WHERE PokemonId = %d",Integer.parseInt(tempID));
        con.executeUpdate(Delete);
        getdata();
        error.setText("Checkout Succesfully");
     
      return;
    
     }else{
      error.setText("Pokemon Doesnt Exist");
     
     
    }
    
    }
   } catch (SQLException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }
   }
   
   
  } else if(e.getActionCommand() == "Delete") {
   String tempID = pokeID.getText();
  
   
   
  if(tempID.isEmpty()){
   error.setText("Pokemon is empty");
  }else{
   ResultSet rs = con.read("SELECT * FROM cart");
   try {
    while(rs.next()){
     if(Integer.parseInt(tempID) == (rs.getInt(1))){
        String Delete = String.format("DELETE FROM cart WHERE PokemonId = %d", Integer.parseInt(tempID));
        con.executeUpdate(Delete);
        getdata();
        error.setText("Deleted Succesfully");
  
        return;
     }else{
      error.setText("Pokemon Doesnt Exist");
     
     
    }
    }
    
   } catch (SQLException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }
  }
  }
 }

}