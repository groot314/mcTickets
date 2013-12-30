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
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
	}
	
	public void connect(){
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
		                                   "user=monty&password=greatsqldb");
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
