package me.groot314.mcTickets;

import org.bukkit.entity.Player;

public class takeArg {
	mcTickets plugin;
	mySQL SQL;
	
	public takeArg(mcTickets Plugin){
		SQL = new mySQL(Plugin);
		this.plugin = Plugin;
	}
	
	public void take(Player player, String[] args){
		if(args.length == 2){
			SQL.setAssigned(Integer.valueOf(args[1]),player.getName());
		} else{
			player.sendMessage("/ticket take <#>");
		}
	}
}
