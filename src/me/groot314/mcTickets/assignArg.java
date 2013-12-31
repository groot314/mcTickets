package me.groot314.mcTickets;

import org.bukkit.entity.Player;

public class assignArg {
	mcTickets plugin;
	mySQL SQL;
	
	public assignArg(mcTickets Plugin){
		SQL = new mySQL(Plugin);
		this.plugin = Plugin;
	}
	
	public void assign(Player player, String[] args){
		if(args.length == 3){
			SQL.setAssigned(Integer.valueOf(args[1]),args[2]);
			player.sendMessage(args[2]+" assigned to ticket "+args[1]);
		}else{
			player.sendMessage("/ticket assign <#> <admin>");
		}
	}
}
