import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class BuyPokemon extends JFrame implements ActionListener{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JPanel mainPanel;
 JPanel editorPanel;
 JPanel buttonPanel;
 JPanel panel;
 JPanel err;
 
 JTable table;
 DefaultTableModel dtm;
 
 JLabel PokemonID, PokemonName, Pokemonlvl, type, in, Qty, error;
 JTextField insert, qty;
 JButton ins, back;
 Connect con;
 int useridnew;
 
 void init(){
 this.setTitle("1");
 this.setSize(900,700);
 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 this.setLocationRelativeTo(null);
 setVisible(true);
 }
 
 public BuyPokemon(int userid) {
	 
	
	 
	this.useridnew = userid;
 con = Connect.getConnection();
 


//panel
panel = new JPanel(new GridLayout(4,1));
mainPanel = new JPanel(new GridLayout(0,1));
editorPanel = new JPanel(new GridBagLayout());
buttonPanel = new JPanel (new GridBagLayout());
err = new JPanel(new GridBagLayout());

GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(5,5,5,5);
gbc.anchor = GridBagConstraints.LINE_START;

panel.setLayout(null);
mainPanel.setLocation(3,0);
mainPanel.setSize(880,400);

editorPanel.setLocation(3,400);
editorPanel.setSize(880, 150);

buttonPanel.setLocation(3,550);
buttonPanel.setSize(880,50);

err.setLocation(3,600);
err.setSize(880,50);
 
//table
PokemonID = new JLabel("Pokemon ID");
PokemonName = new JLabel("Pokemon Name");
Pokemonlvl = new JLabel("Pokemon Level");
type = new JLabel("Pokemon Type");


//input insert
in = new JLabel("Insert Pokemon ID : ");
insert = new JTextField();
insert.setPreferredSize(new Dimension(200,33));
JPanel space1 = new JPanel (new GridLayout(3,2));
JLabel space2 = new JLabel (" ");
JLabel space3 = new JLabel (" ");

//input quantity
Qty = new JLabel("Quantity : ");
qty = new JTextField();
qty.setPreferredSize(new Dimension(200,33));

//button
ins = new JButton("Insert");
gbc.gridx = 0;
gbc.gridy = 0;
buttonPanel.add(ins, gbc);
ins.addActionListener(this);

JLabel space4 = new JLabel("");
gbc.gridx=1;
gbc.gridy=0;
buttonPanel.add(space4,gbc);

back = new JButton("Back to Main");
gbc.gridx = 2;
gbc.gridy = 0;
buttonPanel.add(back, gbc);
back.addActionListener(this);

error = new JLabel("");
gbc.gridx = 0;
gbc.gridy = 0;
err.add(error, gbc);
error.setForeground(Color.red);


//isi table
String[] header = {"Pokemon ID", "Pokemon Name", "Pokemon Level", "Pokemon Type"};
dtm = new DefaultTableModel(header,0);
 
 table = new JTable(dtm);

 
 String query = "SELECT * FROM pokemon";
 ResultSet rs =con.executeQuery(query);
 ResultSetMetaData rsm=null;
 try {
  rsm = (ResultSetMetaData) rs.getMetaData();
 } catch (SQLException e1) {
  
  e1.printStackTrace();
 }
 
 try {
  while(rs.next()) {
  Vector<Object> data = new Vector<>();
  
   for(int i = 1; i <= rsm.getColumnCount(); i++) {
    data.add(rs.getObject(i));
   }
   
   dtm.addRow(data);
  
  }
 } catch (Exception e) {

 }
 
 
 
JScrollPane sp = new JScrollPane(table);
 
//add panel
mainPanel.add(sp);
table.setBackground(Color.cyan);

space1.add(in);
space1.add(insert);
space1.add(space2);
space1.add(space3);
space1.add(Qty);
space1.add(qty);
editorPanel.add(space1);
editorPanel.setBackground(Color.cyan);
space1.setBackground(Color.cyan);


buttonPanel.setBackground(Color.cyan);
err.setBackground(Color.cyan);

panel.add(mainPanel, BorderLayout.NORTH);
panel.add(editorPanel, BorderLayout.CENTER);
panel.add(buttonPanel, BorderLayout.SOUTH);
panel.add(err, BorderLayout.SOUTH);

panel.setBackground(Color.cyan);
this.add(panel);

 init();
 }

@Override
public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand() == "Insert") {
		String insertTemp, qtyTemp;
		insertTemp = insert.getText();
		qtyTemp = qty.getText();
		
		
		if (insertTemp.isEmpty() && qtyTemp.isEmpty()) {
			error.setText("Pokemon must exists!");
		} else {
			
			ResultSet rs = con.read("SELECT * FROM pokemon");
			try {
				while(rs.next()){
				if(Integer.parseInt(insertTemp) == (rs.getInt(1))){
					
				PreparedStatement ps = con.preparedStatement("INSERT INTO cart VALUES (?,?,?)");
				try {
					ps.setInt(1, Integer.parseInt(insertTemp));
					ps.setInt(2, useridnew);
					ps.setInt(3, Integer.parseInt(qtyTemp));
					ps.execute();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				error.setText("Insert success");
				return;
				
				}else if(Integer.parseInt(insertTemp) != (rs.getInt(1))){
				
					error.setText("Pokemon not available");		
	
				}	
			}
		} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
	}
			
}
		
		
		
	} else if (e.getActionCommand() == "Back to Main") {
		this.dispose();
		MainUser mainuser = new MainUser(useridnew);
		mainuser.setVisible(true);
	}
	
}

}