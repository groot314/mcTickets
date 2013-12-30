package me.groot314.mcTickets;

import org.bukkit.entity.Player;

public class openArg {
	
	mcTickets plugin;
	mySQL SQL;
	
	public openArg(mcTickets Plugin){
		SQL = new mySQL(Plugin);
		this.plugin = Plugin;
	}
	
	public void open(Player player, String[] args){
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < args.length; i++)
		{
		    sb.append(args[i]).append(" ");
		}
		String message  = sb.toString().trim();
		
		SQL.newTicket(player, player.getLocation().getBlockX(),
				player.getLocation().getBlockY(),
				player.getLocation().getBlockZ(), message);
	}
}
