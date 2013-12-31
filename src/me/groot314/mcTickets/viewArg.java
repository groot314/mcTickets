package me.groot314.mcTickets;

import java.util.ArrayList;

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
		
		pageNumber = Integer.valueOf(args[3]);
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
		}else{//ticket view <#>
			player.sendMessage("/ticket view");//ticket view <> <page #>
		}
	}
	
	private void viewAll(){
		
	}
	private void viewOpen(){
		ArrayList<Integer> tN = SQL.getTickets(pageNumber, "Status", "Open"); 
		player.sendMessage("---[mcTickets]---");
		player.sendMessage("---[All Tickets]---");
		for (int i = 0; i < tN.size(); i++) {
			//get ticket info from each given ticket value
		}
	}
	private void viewClosed(){
		ArrayList<Integer> tN = SQL.getTickets(pageNumber, "Status", "Closed");
		player.sendMessage("---[mcTickets]---");
		player.sendMessage("---[All Tickets]---");
		for (int i = 0; i < tN.size(); i++) {
			//get ticket info from each given ticket value
		}
	}
	private void viewTaken(){
		ArrayList<Integer> tN = SQL.getTickets(pageNumber, "Assigned", player.getDisplayName());
		player.sendMessage("---[mcTickets]---");
		player.sendMessage("---[All Tickets]---");
		for (int i = 0; i < tN.size(); i++) {
			//get ticket info from each given ticket value
		}
	}
	private void viewMine(){
		ArrayList<Integer> tN = SQL.getTickets(pageNumber, "User", player.getDisplayName());
		player.sendMessage("---[mcTickets]---");
		player.sendMessage("---[All Tickets]---");
		for (int i = 0; i < tN.size(); i++) {
			//get ticket info from each given ticket value
		}
	}
}
