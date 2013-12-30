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
			if(SQL.setTicketStatus(args[1], "closed")){//if can close
				player.sendMessage("Replyed to ticket "+args[1]);
			} else{
				player.sendMessage("Can't reply to ticket "+args[1]);
			}
		} else{
			player.sendMessage("/ticket close <#>");
		}
	}
}
