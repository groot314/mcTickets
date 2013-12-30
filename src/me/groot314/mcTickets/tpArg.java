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
		player.teleport(new Location(Bukkit.getWorld(SQL.getWorld()), SQL.getX(), SQL.getY(), SQL.getZ()));
	}
}
