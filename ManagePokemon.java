import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

public class ManagePokemon extends JFrame implements ActionListener{

	 JPanel mainPanel;
	 JPanel editorPanel;
	 JPanel buttonPanel;
	 JPanel panel;

	 
	 JTable table;
	 DefaultTableModel dtm;
	 
	 JLabel PokemonName,PokeName,PokemonID, Pokemonlvl, type, PokeID,Pkname,Pklvl,Pktype,error;
	 JTextField Pname,PName,Plvl, Pokemonid,Ptype,idPoke,pkname,pklvl,pktype;
	 JButton delete,insert, back,update;
	 
	 Connect con;
	 int useridnew;
	 
	 void init(){
		 this.setTitle("Welcome Admin");
		 this.setSize(900,700);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setLocationRelativeTo(null);
		 setVisible(true);
		 }
		 
	public ManagePokemon(int userid) {
		 con = con.getConnection();
		 
		 this.useridnew=userid;
		
// panel		
		panel = new JPanel(new GridLayout(2,1));
		mainPanel = new JPanel(new GridLayout(0,1));
		editorPanel = new JPanel(new GridBagLayout());
		buttonPanel = new JPanel (new GridBagLayout());
		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.insets = new Insets(5,5,5,5);
		  gbc.anchor = GridBagConstraints.LINE_START;




		
// Button		
		delete = new JButton("Delete");
		delete.setPreferredSize(new Dimension(150,30));
		delete.addActionListener(this);
		
		insert = new JButton("Insert");
		insert.setPreferredSize(new Dimension(150,30));
		insert.addActionListener(this);
		
		back = new JButton("Back To Main");
		back.setPreferredSize(new Dimension(150,30));
		back.addActionListener(this);
		
		update = new JButton("Update");
		update.setPreferredSize(new Dimension(150,30));
		update.addActionListener(this);
		
		
		
//Table 
		PokemonID = new JLabel("Pokemon ID");
		PokemonName = new JLabel("Pokemon Name");
		Pokemonlvl = new JLabel("Pokemon Level");
		type = new JLabel("Pokemon Type");
		
		String[] header = {"Pokemon ID", "Pokemon Name", "Pokemon Level", "Pokemon Type"};
		dtm = new DefaultTableModel(header,0);
		table = new JTable(dtm);
		JScrollPane sp = new JScrollPane(table);
		mainPanel.add(sp);
		table.setBackground(Color.cyan);
		
		
			
	 
// input		
// baris 1		
		PokemonName = new JLabel("Pokemon Name : ");
		Pname = new JTextField();
		Pname.setPreferredSize(new Dimension(150,20));
		gbc.gridx=3;
		gbc.gridy=2;
		editorPanel.add(PokemonName, gbc);
		gbc.gridx=6;
		gbc.gridy=2;
		editorPanel.add(Pname, gbc);
		
		Pokemonlvl =new JLabel("Pokemon Level : ");
		Plvl= new JTextField();
		Plvl.setPreferredSize(new Dimension(150,20));
		gbc.gridx=3;
		gbc.gridy=5;
		editorPanel.add(Pokemonlvl, gbc);
		gbc.gridx=6;
		gbc.gridy=5;
		editorPanel.add(Plvl, gbc);
		
		type = new JLabel("Pokemon Type : ");
		Ptype = new JTextField();
		Ptype.setPreferredSize(new Dimension(150,20));
		gbc.gridx=3;
		gbc.gridy=6;
		editorPanel.add(type, gbc);
		gbc.gridx=6;
		gbc.gridy=6;
		editorPanel.add(Ptype, gbc);
		
// baris 2		
		PokemonID = new JLabel("Pokemon ID : ");
		Pokemonid= new JTextField();
		Pokemonid.setPreferredSize(new Dimension(150,20));
		gbc.gridx=7;
		gbc.gridy=2;
		editorPanel.add(PokemonID, gbc);
		gbc.gridx=12;
		gbc.gridy=2;
		editorPanel.add(Pokemonid, gbc);
		
		gbc.gridx=12;
		gbc.gridy=5;
		editorPanel.add(delete, gbc);
		
// baris 3		
		PokeID = new JLabel("Pokemon ID : ");
		idPoke= new JTextField();
		idPoke.setPreferredSize(new Dimension(150,20));
		gbc.gridx=19;
		gbc.gridy=2;
		editorPanel.add(PokeID, gbc);
		gbc.gridx=22;
		gbc.gridy=2;
		editorPanel.add(idPoke, gbc);
		
		Pkname=new JLabel("Pokemon Name : ");
		pkname = new JTextField();
		pkname.setPreferredSize(new Dimension(150,20));
		gbc.gridx=19;
		gbc.gridy=5;
		editorPanel.add(Pkname, gbc);
		gbc.gridx=22;
		gbc.gridy=5;
		editorPanel.add(pkname, gbc);
		
		Pklvl=new JLabel("Pokemon Level : ");
		pklvl = new JTextField();
		pklvl.setPreferredSize(new Dimension(150,20));
		gbc.gridx=19;
		gbc.gridy=6;
		editorPanel.add(Pklvl, gbc);
		gbc.gridx=22;
		gbc.gridy=6;
		editorPanel.add(pklvl, gbc);
		
		Pktype = new JLabel ("Pokemon Type : ");
		pktype = new JTextField();
		pktype.setPreferredSize(new Dimension(150,20));
		gbc.gridx=19;
		gbc.gridy=15;
		editorPanel.add(Pktype, gbc);
		gbc.gridx=22;
		gbc.gridy=15;
		editorPanel.add(pktype, gbc);
		
// button bawah		
		gbc.gridx= 6;
		gbc.gridy=25;
		editorPanel.add(insert, gbc);
		
		gbc.gridx=12;
		gbc.gridy=25;
		editorPanel.add(back, gbc);
		
		gbc.gridx=22;
		gbc.gridy=25;
		editorPanel.add(update, gbc);
		
		error = new JLabel("");
		gbc.gridx = 12;
		gbc.gridy = 35;
		editorPanel.add(error, gbc);
		error.setForeground(Color.red);
		mainPanel.isVisible();


		
		mainPanel.setBackground(Color.cyan);
		editorPanel.setBackground(Color.cyan);
		buttonPanel.setBackground(Color.cyan);
		
		

		panel.add(mainPanel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(300,100));
		panel.add(editorPanel, BorderLayout.CENTER);

		
		this.add(panel);
		
		
		
		init();
		isitable();
	}

void isitable() {	
	dtm.setRowCount(0);
	
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
		  int PokemonID,Pokemonlvl;
		  String PokemonName,type;
	  
		PokemonID = rs.getInt("PokemonId");
		PokemonName = rs.getString("PokemonName");
		Pokemonlvl = rs.getInt("PokemonLevel");
		type = rs.getString("PokemonType");
	   dtm.addRow(new String[] {PokemonID + "",PokemonName,Pokemonlvl +"",type});
	  
	  }
	 } catch (Exception e) {
		 
	 }

	 }
	
	void insert() {
	
			String insname,inslvl,instype ;
			
			insname = Pname.getText();
			inslvl = Plvl.getText();
			instype = Ptype.getText();
			
			 if (insname.isEmpty() ||inslvl.isEmpty()|| instype.isEmpty()) {
				error.setText("All Text Must Filled");
			 }else if(Integer.parseInt(inslvl)<0) {
				error.setText("Pokemon Level must be more than 0!");
				 }else {
			  dtm.addRow(new Object[]{null, insname, inslvl, instype});		
			 PreparedStatement ps = con.preparedStatement("INSERT INTO pokemon VALUES(null,?,?,?)");
			 try {
				ps.setString(1, insname);
				ps.setInt(2, Integer.parseInt(inslvl));
				ps.setString(3,instype);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 error.setText("Pokemon has been added!");
			clearfield(); 
			isitable();

			}		 
			
	}

	void delete() {
		String ID = "";
		ResultSet rs = con.read("SELECT * FROM pokemon");
		ID = Pokemonid.getText();

	if (ID.isEmpty()) {
		error.setText("All Text Must Filled");
	} else {
			try {
				while(rs.next()) {
				if(Integer.parseInt(ID)== rs.getInt(1)){
			        String Delete = String.format("DELETE FROM pokemon WHERE pokemonId = %d", Integer.parseInt(ID));
					con.executeUpdate(Delete);
					 error.setText("Data Has been Deleted");
					 
						clearfield(); 
						isitable();
						return;
					
					}else {
						error.setText("Pokemon Not Found");
			}
				}
				

			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	}

	}
	
	void update() {
		String ID,Name,lvl,Type;
		
		
		ID = idPoke.getText();
		Name = pkname.getText();
		lvl = pklvl.getText();
		Type = pktype.getText();
		
		 if (ID.isEmpty() ||Name.isEmpty()|| lvl.isEmpty()|| Type.isEmpty()) {
			error.setText("All Text Must Filled");
		
		 }else if (Integer.parseInt(lvl) < 1 ) {
			 error.setText("Minimum level is 1");
		 			 
		 }else {
			 
			 PreparedStatement ps = con.preparedStatement("UPDATE pokemon SET PokemonName = ?,PokemonLevel=?,PokemonType=? WHERE PokemonId =?");
			 try {

				ps.setString(1, Name);
				ps.setInt(2, Integer.parseInt(lvl));
				ps.setString(3,Type);
				ps.setInt(4,Integer.parseInt(ID));
				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			error.setText("Updated successfully");
			clearfield(); 
			isitable();
		 }
		
		
		
	}
	
	void clearfield() {
		Pname.setText("");
		Plvl.setText("");
		Ptype.setText("");
		Pokemonid.setText("");
		idPoke.setText("");
		pkname.setText("");
		pklvl.setText("");
		pktype.setText("");
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()== back) {
				this.dispose();
				MainUser mainuser = new MainUser(useridnew);
				mainuser.setVisible(true);	
				
			}else if (e.getSource()== insert) {
				insert();
			}else if (e.getSource() == delete) {
				delete();
			}else if (e.getSource() == update) {
				update();
			}
				
			
			
		
	}

	
}

