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
			int x = SQL.getCord(tn,"x");
			int y = SQL.getCord(tn,"x");
			int z = SQL.getCord(tn,"x");
			String worldName = SQL.getWorld(tn);
			player.teleport(new Location(Bukkit.getWorld(worldName), x, y, z));
			player.sendMessage("TP to ticket: "+args[1]+" Location: World["+worldName+"] X["+String.valueOf(x)+"] Y["+String.valueOf(x)+"] Z["+String.valueOf(x)+"]");
		} else{
			player.sendMessage("/ticket tp <#>");
		}
	}
}
