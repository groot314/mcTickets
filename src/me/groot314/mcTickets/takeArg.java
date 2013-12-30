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
		if(args.length == 1){
			SQL.setAssigned(player.getDisplayName());
		} else{
			player.sendMessage("/ticket take");
		}
	}
}
