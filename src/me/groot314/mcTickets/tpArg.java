package me.groot314.mcTickets;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class tpArg {
	mcTickets plugin;
	mySQL SQL;
	
	public tpArg(mcTickets Plugin){
		SQL = new mySQL(Plugin);
		this.plugin = Plugin;
	}
	
	public void tp(Player player, String[] args){
		if(args.length == 2){
			int tn = Integer.valueOf(args[1]); //ticketNumber
			player.sendMessage(SQL.getWorld(tn));
			player.sendMessage(String.valueOf(SQL.getX(tn)));
			player.sendMessage(String.valueOf(SQL.getY(tn)));
			player.sendMessage(String.valueOf(SQL.getZ(tn)));
			player.teleport(new Location(Bukkit.getWorld(SQL.getWorld(tn)), SQL.getX(tn), SQL.getY(tn), SQL.getZ(tn)));
			player.sendMessage("Teleported to ticket: "+args[1]);
		} else{
			player.sendMessage("/ticket tp <#>");
		}
	}
}
