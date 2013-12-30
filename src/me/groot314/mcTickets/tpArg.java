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
		int tn = Integer.parseInt(args[1]); //ticketNumber
		if(args.length == 2){
			player.teleport(new Location(Bukkit.getWorld(SQL.getWorld(tn)), SQL.getX(tn), SQL.getY(tn), SQL.getZ(tn)));
		} else{
			player.sendMessage("/ticket tp <#>");
		}
	}
}
