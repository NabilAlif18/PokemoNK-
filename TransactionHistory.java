import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class TransactionHistory extends JFrame implements ActionListener, MouseListener{

 JPanel main,paneljudul, paneltabel, panelbutton;
 JLabel thjudul, thheader1, transactionid, tanggal, pokemonID, pokemonName, pokemonLevel, pokemonType, qty;
 JTable tabel, tabel2;
 JButton bck;
 DefaultTableModel dtm, dtm2;
 JScrollPane sp, sp1;
 Connect con;
 int useridnew;
 int transactionidnew;
 
 public TransactionHistory(int userid) {
	this.useridnew = userid;
  
transactionhistory();
  init();
  getData();
}

 void init() {
  this.setTitle("Transaction History");
  this.setSize(900,700);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  this.setVisible(true);
 }
 
 public void transactionhistory(){
  
	 //tabel kedua muncul
	 
   con = con.getConnection();
  
   GridBagConstraints gbc = new GridBagConstraints(); 
   JPanel paneljudul = new JPanel(new GridBagLayout());
   JPanel paneltabel = new JPanel(new GridLayout(2,1));
   JPanel panelbutton = new JPanel(new GridBagLayout());
   JPanel main = new JPanel(new GridLayout(3,1));
   
   gbc.insets = new Insets(5,5,5,5);
   gbc.anchor = GridBagConstraints.LINE_START;
  
   //judul
   JLabel thjudul = new JLabel("Transaction History");
   thjudul.setFont(new Font("Transaction History", Font.BOLD, 50));
   gbc.gridx = 0;
   gbc.gridy = 0;
   paneljudul.add(thjudul,gbc);
   
   
   //header
   JLabel thheader1 = new JLabel("Select the row for details");
   thheader1.setFont(new Font("Select the row for details", Font.BOLD, 20));
   gbc.gridx = 0;
   gbc.gridy = 1;
   paneljudul.add(thheader1,gbc);
   
   
   //tabel header
   transactionid = new JLabel("Transaction Id");
   tanggal = new JLabel("Date");
   
   String[] header = {"Transaction Id", "Date"};
   dtm = new DefaultTableModel(header,0);
    
    tabel = new JTable(dtm);
    
   sp = new JScrollPane(tabel);
    
   paneltabel.add(sp);
   tabel.setBackground(Color.cyan);
   tabel.addMouseListener(this);
   
   //tabel detail
   
   transactionid = new JLabel("Transaction Id");
   pokemonID = new JLabel("Pokemon Id");
   pokemonName = new JLabel("Pokemon Name");
   pokemonLevel = new JLabel("Pokemon Level");
   pokemonType = new JLabel("Pokemon Type");
   qty = new JLabel("Quantity");
   
   String[] header2 =  {"TransactionId","PokemonId", "PokemonName", "PokemonLevel", "PokemonType"};
   dtm2 = new DefaultTableModel(header2,0);
   
   tabel2 = new JTable(dtm2);
   sp1 = new JScrollPane(tabel2);
   paneltabel.add(sp1);
   
   sp1.setVisible(false);
   
   
   //button
   
   JButton bck =  new JButton("Back to Main Menu");
   gbc.gridx = 0;
   gbc.gridy = 0;
   panelbutton.add(bck, gbc);
   bck.addActionListener(this);
   
   //color
   
   paneljudul.setBackground(Color.CYAN);
   paneltabel.setBackground(Color.CYAN);
   panelbutton.setBackground(Color.CYAN);
   
   main.add(paneljudul, BorderLayout.NORTH);
   main.add(paneltabel, BorderLayout.CENTER);
   main.add(panelbutton, BorderLayout.SOUTH);
  
   this.add(main);
   
 }
 
 public void getData() {
		dtm.setRowCount(0);
		 String query = String.format("SELECT * FROM headertransaction ht WHERE ht.UserId = %d",  useridnew);
		    	 
		 ResultSet rs = con.executeQuery(query);
	
			 try {
			  while(rs.next()) {
				 int transid;
				 Timestamp timestamp;
				 transid = rs.getInt("TransactionId");
				timestamp = rs.getTimestamp("Time");
				 
				 
			   dtm.addRow(new String[] {transid +"", timestamp+""});
			  
			  }
			 } catch (Exception e) {

			 }
	}
 

 
 

@Override
public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand() == "Back to Main Menu") {
		this.dispose();
		MainUser mu = new MainUser(useridnew);
		mu.setVisible(true);
	
}
 
}

@Override
public void mouseClicked(MouseEvent e) {
	if (e.getSource() == tabel) {
	sp1.setVisible(true);
	 dtm2.setRowCount(0);
		
		int clickedRow = tabel.getSelectedRow();
		String detailtransid =  dtm.getValueAt(clickedRow, 0).toString();
		String query = String.format("SELECT * FROM detailtransaction dt JOIN pokemon p ON dt.PokemonId = p.PokemonId WHERE dt.TransactionId = %d",  Integer.parseInt(detailtransid));
		    	 
		 ResultSet rs1 = con.executeQuery(query);

			 try {
			  while(rs1.next()) {
				 int pokeid, pokelevel, transid;
				 String pokename, poketype;
				 transid = rs1.getInt("TransactionId");
				 pokeid = rs1.getInt("PokemonId");
				pokename = rs1.getString("PokemonName");
				pokelevel = rs1.getInt("PokemonLevel");
				poketype = rs1.getString("PokemonType");
				 
				 
			   dtm2.addRow(new String[] {transid+"",pokeid +"", pokename, pokelevel+"", poketype});
			  
			  }
			 } catch (Exception e1) {

			 }

	}
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
 
}