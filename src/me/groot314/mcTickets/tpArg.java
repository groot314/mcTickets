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
			int y = SQL.getCord(tn,"y");
			int z = SQL.getCord(tn,"z");
			String worldName = SQL.getWorld(tn);
			player.teleport(new Location(Bukkit.getWorld(worldName), x, y, z));
			player.sendMessage("TP to ticket: "+args[1]+" Location: World["+worldName+"] X["+String.valueOf(x)+"] Y["+String.valueOf(y)+"] Z["+String.valueOf(z)+"]");
		} else{
			player.sendMessage("/ticket tp <#>");
		}
	}
}
