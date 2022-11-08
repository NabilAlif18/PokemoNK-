import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainUser extends JFrame implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int useridnew;

	public MainUser(int userid) {
		this.useridnew = userid;
		user();
		init();
	}
	
	void init() {
		this.setTitle("Welcome User");
		this.setSize(900,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void user() {
		//panel main
		JPanel panelMain = new JPanel(new GridBagLayout());
		JLabel judul = new JLabel("PokemoNK");
		
		judul.setFont(new Font("PokemoNK", Font.BOLD, 75));
		panelMain.setBackground(Color.cyan);
		
		//user
		JMenuBar menuBar = new JMenuBar();
		JMenu User = new JMenu("User");
		JMenuItem signoutUser = new JMenuItem("Sign Out");
		User.add(signoutUser);
		menuBar.add(User);
		
		signoutUser.addActionListener(this);
	
		//adventure
		JMenu Adventure = new JMenu("Adventure");
		JMenuItem pokemonMarket = new JMenuItem("Pokemon Market");
		JMenuItem bagUser = new JMenuItem("Bag");
		Adventure.add(pokemonMarket);
		Adventure.add(bagUser);
		menuBar.add(Adventure);
		
		pokemonMarket.addActionListener(this);
		bagUser.addActionListener(this);
		
		//transaction
		JMenu Transaction = new JMenu("Transaction");
		JMenuItem transactionHistory = new JMenuItem("View Transaction History");
		Transaction.add(transactionHistory);
		menuBar.add(Transaction);
		
		transactionHistory.addActionListener(this);
		
		//add menubar
		setJMenuBar(menuBar);
		
		
		panelMain.add(judul);
		this.add(panelMain, BorderLayout.CENTER);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Sign Out") {
			this.dispose();
			Login login = new Login();
			login.setVisible(true);
		} else if (e.getActionCommand() == "Pokemon Market") {
			this.dispose();
			BuyPokemon buypokemon = new BuyPokemon(useridnew);
			buypokemon.setVisible(true);
		} else if (e.getActionCommand() == "Bag") {
			this.dispose();
			BagForm bf = new BagForm(useridnew);
			bf.setVisible(true);
		} else if (e.getActionCommand() == "View Transaction History") {
			this.dispose();
			TransactionHistory th = new TransactionHistory(useridnew);
			th.setVisible(true);
		}
	}

}
