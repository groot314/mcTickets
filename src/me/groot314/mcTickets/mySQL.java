package me.groot314.mcTickets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class mySQL {
	
	mcTickets plugin;
	
	public mySQL(mcTickets Plugin){
		this.plugin = Plugin;
	}
	
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	
	private void loadDriver(){
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println(ex);
        }
	}
	
	public void connect(String host,String database,String user,String password){
		try {
			loadDriver();
		    conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database,user,password);
		    st = (Statement) conn.createStatement();
		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public void checkTables(){
		try {
			st.executeUpdate("DROP TABLE IF EXISTS  account ");
			st.executeUpdate("CREATE TABLE  tickets ( "
                    +"ID int NOT NULL AUTO_INCREMENT,"
					+"PRIMARY KEY (ID),"
                    +"Status VARCHAR(16),"
                    +"User VARCHAR(16),"
                    +"Reason VARCHAR(128),"
                    +"Assigned VARCHAR(16),"
                    +"World VARCHAR(30),"
                    +"x INT( 11 ),"
                    +"y INT( 11 ),"
                    +"z INT( 11 ))" )  ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void newTicket(String playerName, String worldName, int x, int y, int z,String reason){
		//int ticketNumber = 0;
		try {
			plugin.mySQL_connect();
			String query = "INSERT INTO tickets(Status, User, Reason, World, x, y, z)"
					+" VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "Open");
			pstmt.setString(2, playerName);
			pstmt.setString(3, reason);
			pstmt.setString(4, worldName);
			pstmt.setInt(5, x);
			pstmt.setInt(6, y);
			pstmt.setInt(7, z);
			pstmt.executeUpdate();
			//rs = st.executeQuery("SELECT * FROM tickets WHERE max(id)");
			//ticketNumber = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//return ticketNumber;
	}
	boolean setTicketStatus(String ticketNumber, String status){
		try {
			rs = st.executeQuery("UPDATE tickets SET Status='"+status+"' WHERE id='"+ticketNumber+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void findReplys(int ticketnumber){//make array
		
	}
	public boolean addReply(String ticketNumber,String playerName,String message){
		//find if ticket is there
		//add reply
		return false;
		
	}
	public String getWorld(int ticketNumber){
		String world = null;
		try {
			rs = st.executeQuery("SELECT * FROM tickets WHERE id="+ticketNumber);
			world = rs.getString("world");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return world;
		
	}
	public int getX(int ticketNumber){
		int x = 0;
		try {
			rs = st.executeQuery("SELECT * FROM tickets WHERE id="+ticketNumber);
			x = rs.getInt("x");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;	
	}
	public int getY(int ticketNumber){
		int y = 0;
		try {
			rs = st.executeQuery("SELECT * FROM tickets WHERE id="+ticketNumber);
			y = rs.getInt("y");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return y;	
	}
	public int getZ(int ticketNumber){
		int z = 0;
		try {
			rs = st.executeQuery("SELECT * FROM tickets WHERE id="+ticketNumber);
			z = rs.getInt("x");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return z;	
	}
	
	public String getTicketStatus(int ticketNumber){
		String status = null;
		try {
			rs = st.executeQuery("SELECT * FROM tickets WHERE id="+ticketNumber);
			status = rs.getString("Status");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	public void setAssigned(int ticketNumber, String player){
		try {
			rs = st.executeQuery("UPDATE tickets SET Assigned='"+player+"' WHERE id='"+ticketNumber+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
