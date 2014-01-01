package me.groot314.mcTickets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class viewArg {
	mcTickets plugin;
	mySQL SQL;
	
	public viewArg(mcTickets Plugin){
		SQL = new mySQL(Plugin);
		this.plugin = Plugin;
	}
	
	Player player;
	String[] args;
	int pageNumber;
	
	public void view(Player p, String[] arg){
		player = p;
		args = arg;
		if(args.length >= 3){
			pageNumber = pageNumber(args[2]);
		}else{
			pageNumber = 1;
		}
		
		if(args.length == 1){
			//admin view open
			//player view mine
		}else if(args[1].equalsIgnoreCase("all")){
			viewAll();
		}else if(args[1].equalsIgnoreCase("open")){
			viewOpen();
		}else if(args[1].equalsIgnoreCase("closed")){
			viewClosed();
		}else if(args[1].equalsIgnoreCase("taken")){
			viewTaken();
		}else if(args[1].equalsIgnoreCase("mine")){
			viewMine();
		}else if(isInt(args[1])){
			int ticketNumber = Integer.valueOf(args[1]);
			ArrayList<Integer> rNs = SQL.findReplys(ticketNumber, pageNumber); 
			player.sendMessage(ChatColor.DARK_AQUA+"["+ticketNumber+"]["+SQL.getTicketInfo(ticketNumber, "User")+"]");
			for (int i = 0; i < rNs.size(); i++) {
				int id = rNs.get(i);

				player.sendMessage(userColor(ticketNumber,SQL.getReplyInfo(id, "User"))+"["+SQL.getReplyInfo(id, "User")+"]"
						+": "+ChatColor.AQUA+SQL.getReplyInfo(id, "Message"));
			}
			if (rNs.size() == 5){
				player.sendMessage(ChatColor.BLUE+"[For 2nd page]:"+ChatColor.AQUA+" /ticket view <Ticket> <Page>");
			}
		}
		else{//ticket view <#>
			player.sendMessage("/ticket view");//ticket view <> <page #>
		}
	}
	
	private void viewAll(){
		ArrayList<Integer> tNs = SQL.getAllTickets(pageNumber); 
		player.sendMessage(ChatColor.DARK_AQUA+"---[All Tickets]---");
		for (int i = 0; i < tNs.size(); i++) {
			int tN = tNs.get(i);
			player.sendMessage(ChatColor.BLUE+"["+String.valueOf(tN)+"]"
					+statusColor(SQL.getTicketInfo(tN,"Status")) 
					+"["+SQL.getTicketInfo(tN,"Status")+"]"
					+ChatColor.BLUE
					+"[User: "+SQL.getTicketInfo(tN,"User")+"]"
					+": "+SQL.getTicketInfo(tN,"Reason"));
		}
	}
	private void viewOpen(){
		ArrayList<Integer> tNs = SQL.getTickets(pageNumber, "Status", "Open"); 
		player.sendMessage(ChatColor.DARK_AQUA+"---[Open Tickets]---");
		for (int i = 0; i < tNs.size(); i++) {
			int tN = tNs.get(i);
			player.sendMessage(ChatColor.BLUE+"["+String.valueOf(tN)+"]" 
					+statusColor(SQL.getTicketInfo(tN,"Status"))
					+"["+SQL.getTicketInfo(tN,"Status")+"]"
					+ChatColor.BLUE
					+"[User: "+SQL.getTicketInfo(tN,"User")+"]"
					+": "+SQL.getTicketInfo(tN,"Reason"));
		}
	}
	private void viewClosed(){
		ArrayList<Integer> tNs = SQL.getTickets(pageNumber, "Status", "Closed");
		player.sendMessage(ChatColor.DARK_AQUA+"---[Closed Tickets]---");
		for (int i = 0; i < tNs.size(); i++) {
			int tN = tNs.get(i);
			player.sendMessage(ChatColor.BLUE+"["+String.valueOf(tN)+"]"
					+statusColor(SQL.getTicketInfo(tN,"Status"))
					+"["+SQL.getTicketInfo(tN,"Status")+"]"
					+ChatColor.BLUE
					+"[User: "+SQL.getTicketInfo(tN,"User")+"]"
					+": "+SQL.getTicketInfo(tN,"Reason"));
		}
	}
	private void viewTaken(){
		ArrayList<Integer> tNs = SQL.getTickets(pageNumber, "Assigned", player.getName());
		player.sendMessage(ChatColor.DARK_AQUA+"---[Your taken Tickets]---");
		for (int i = 0; i < tNs.size(); i++) {
			int tN = tNs.get(i);
			player.sendMessage(ChatColor.BLUE+"["+String.valueOf(tN)+"]"
					+statusColor(SQL.getTicketInfo(tN,"Status"))
					+"["+SQL.getTicketInfo(tN,"Status")+"]"
					+ChatColor.BLUE
					+"[User: "+SQL.getTicketInfo(tN,"User")+"]"
					+": "+SQL.getTicketInfo(tN,"Reason"));
		}
	}
	private void viewMine(){
		ArrayList<Integer> tNs = SQL.getTickets(pageNumber, "User", player.getName());
		player.sendMessage(ChatColor.DARK_AQUA+"---[Your Tickets]---");
		for (int i = 0; i < tNs.size(); i++) {
			int tN = tNs.get(i);
			player.sendMessage(ChatColor.BLUE+"["+String.valueOf(tN)+"]"
					+statusColor(SQL.getTicketInfo(tN,"Status"))
					+"["+SQL.getTicketInfo(tN,"Status")+"]"
					+ChatColor.BLUE
					+"[User: "+SQL.getTicketInfo(tN,"User")+"]"
					+": "+SQL.getTicketInfo(tN,"Reason"));
		}
	}
	
	
	private int pageNumber(String arg){
		int PN = Integer.valueOf(arg);
		return PN;
	}
	private static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	private ChatColor userColor(int tN,String replier){
		if(replier.equals(SQL.getTicketInfo(tN, "User"))){
			return ChatColor.BLUE;
		}else {
			return ChatColor.RED;
		}
		
	}
	private ChatColor statusColor(String status){
		if(status.equals("Open")){
			return ChatColor.GREEN;
		}else{
			return ChatColor.GRAY;
		}
	}
}
