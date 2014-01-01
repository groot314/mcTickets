package me.groot314.mcTickets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
	
	public void connect(){
		try {
			String host = "localhost";
			String database = "mcTickets";
			String user = "root";
			String password = "pass";
			loadDriver();
		    conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database,user,password);
		    st = conn.createStatement();
		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public void checkTables(){
		try {
			st.executeUpdate("CREATE TABLE IF NOT EXISTS tickets ( "
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
			st.executeUpdate("CREATE TABLE IF NOT EXISTS replys ( "
                    +"ID int NOT NULL AUTO_INCREMENT,"
					+"PRIMARY KEY (ID),"
                    +"ticket INT(11),"
                    +"User VARCHAR(16),"
                    +"Message VARCHAR(128),"
                    +"x INT( 11 ),"
                    +"y INT( 11 ),"
                    +"z INT( 11 ))" )  ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int newTicket(String playerName, String worldName, int x, int y, int z,String reason){
		int ticketNumber = 0;
		connect();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				rs = st.executeQuery("SELECT MAX(ID) FROM tickets");
				if(rs.next()){
					ticketNumber = rs.getInt("MAX(ID)");
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ticketNumber;
	}
	boolean setTicketStatus(int ticketNumber, String status){
		connect();
		try {
			String query ="UPDATE tickets SET Status=? WHERE ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			pstmt.setInt(2, ticketNumber);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Integer> getTickets(int pageNumber, String column, String wantedValue){
		connect();
		ArrayList<Integer> ticketNumbers = new ArrayList<Integer>();
		int offSet = (pageNumber * 5) - 5;
		try {
			String query ="SELECT * FROM tickets WHERE "+column+"='"+wantedValue+"' ORDER BY ID DESC limit 5 OFFSET "+offSet;
			rs = st.executeQuery(query);
			while(rs.next()){
				ticketNumbers.add(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketNumbers;
	}
	public ArrayList<Integer> getAllTickets(int pageNumber){
		connect();
		ArrayList<Integer> ticketNumbers = new ArrayList<Integer>();
		int offSet = (pageNumber * 5) - 5;
		try {
			String query ="SELECT * FROM tickets ORDER BY ID DESC limit 5 OFFSET "+offSet;
			rs = st.executeQuery(query);
			while(rs.next()){
				ticketNumbers.add(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketNumbers;
	}
	
	public ArrayList<Integer> findReplys(int ticketnumber, int pageNumber){//make array
		connect();
		ArrayList<Integer> ticketNumbers = new ArrayList<Integer>();
		int offSet = (pageNumber * 5) - 5;
		try {
			String query ="SELECT * FROM replys WHERE ticket="+ticketnumber+" ORDER BY ID DESC LIMIT 5 OFFSET "+offSet;
			rs = st.executeQuery(query);
			while(rs.next()){
				ticketNumbers.add(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketNumbers;
	}
	public void addReply(int ticketNumber,String playerName,String message){
		connect();
		try {
			String query = "INSERT INTO replys(Ticket, User, Message)"
					+" VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ticketNumber);
			pstmt.setString(2, playerName);
			pstmt.setString(3, message);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public String getReplyInfo(int id, String column){
		connect();
		String info = null;
		try {
			rs = st.executeQuery("SELECT * FROM replys WHERE ID="+id);
			if(rs.next()){
				info = rs.getString(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return info;
	}
	
	public String getWorld(int ticketNumber){
		connect();
		String world = null;
		try {
			String query ="SELECT * FROM tickets WHERE ID="+ticketNumber;
			rs = st.executeQuery(query);
			if(rs.next()){
				world = rs.getString("World");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return world;
		
	}
	public int getCord(int ticketNumber,String cordName){
		connect();
		int cord = 0;
		try {
			String query ="SELECT * FROM tickets WHERE ID="+ticketNumber;
			rs = st.executeQuery(query);
			if(rs.next()){
				cord = rs.getInt(cordName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cord;	
	}
	
	public String getTicketInfo(int ticketNumber, String column){
		connect();
		String info = null;
		//String ticketNumberString = String.valueOf(ticketNumber);
		try {
			String query ="SELECT * FROM tickets WHERE ID="+ticketNumber;
			rs = st.executeQuery(query);
			if(rs.next()){
				info = rs.getString(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return info;
	}
	
	public void setAssigned(int ticketNumber, String player){
		connect();
		try {
			String query = "UPDATE tickets SET Assigned=? WHERE ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, player);
			pstmt.setInt(2, ticketNumber);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
