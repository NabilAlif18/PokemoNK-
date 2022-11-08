import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Connect {
 private final String USERNAME = "root"; // change with your MySQL username, the default username is 'root'
 private final String PASSWORD = ""; // change with your MySQL password, the default password is empty
 private final String DATABASE = "pokemonk"; // change with the database name that you use
 private final String HOST = "localhost:3306"; // change with your MySQL host, the default port is 3306
 private final String CONECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
 
 private Connection con;
 private Statement st;
 private static Connect connect;
 
 /**
 * Constructor for Connect class
 * <br>
 * This class is used singleton design pattern, so this class only have one instance
 */
    Connect() {
     try {  
    	 Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONECTION, USERNAME, PASSWORD);  
            st = con.createStatement(); 
        } catch(Exception e) {
         e.printStackTrace();
         System.out.println("Failed to connect the database, the system is terminated!");
         System.exit(0);
        }  
    }
    public static synchronized Connect getConnection() {
  return connect = (connect == null) ? new Connect() : connect;
    }
 public ResultSet executeQuery(String query) {
  // TODO Auto-generated method stub
   ResultSet rs = null;
  try {
            rs = st.executeQuery(query);
        } catch(Exception e) {
         e.printStackTrace();
        }
        return rs;
        
        
 }
 public void executeUpdate(String query) {
	  try {
	   st.executeUpdate(query);
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 }
	 
	 //prepared statement method
	 public PreparedStatement preparedStatement(String query) {
	  PreparedStatement ps = null;
	  try {
	   ps = con.prepareStatement(query);
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return ps;
	 }
	public ResultSet read(String query) {
		ResultSet rs = null;
		 try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	 

	}


