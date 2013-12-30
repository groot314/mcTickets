package me.groot314.mcTickets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class mySQL {
	
	mcTickets plugin;
	
	public mySQL(mcTickets Plugin){
		this.plugin = Plugin;
	}
	
	Connection conn = null;
	
	public void loadDriver(){
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
		    // Do something with the Connection
		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public int newTicket(Player player, int x, int y, int z,String reason){
		return 0;
	}
	
	public void findReplys(int ticketnumber){//make array
		
	}
	public int getX(){
		return 0;	
	}
	public int getY(){
		return 0;
	}
	public int getZ(){
		return 0;
	}
}
