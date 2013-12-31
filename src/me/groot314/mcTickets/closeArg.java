package me.groot314.mcTickets;

import org.bukkit.entity.Player;

public class closeArg {
	mcTickets plugin;
	mySQL SQL;
	
	public closeArg(mcTickets Plugin){
		SQL = new mySQL(Plugin);
		this.plugin = Plugin;
	}
	
	public void close(Player player, String[] args){
		if(args.length == 2){
			if(SQL.setTicketStatus(Integer.valueOf(args[1]), "Closed")){//if can close
				player.sendMessage("Closed ticket "+args[1]);
			} else{
				player.sendMessage("Can't close ticket "+args[1]);
			}
		} else{
			player.sendMessage("/ticket close <#>");
		}
	}
}
