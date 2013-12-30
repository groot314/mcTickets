package me.groot314.mcTickets;

import org.bukkit.entity.Player;

public class replyArg {
	mcTickets plugin;
	mySQL SQL;
	
	public replyArg(mcTickets Plugin){
		this.plugin = Plugin;
		SQL = new mySQL(Plugin);
	}
	
	public void reply(Player player, String[] args){
		
		if(args.length == 2){
			player.sendMessage("/ticket reply <ticketNumber> <reply>");
		} else if(args.length > 2){
			StringBuilder sb = new StringBuilder();
			for(int i = 2; i < args.length; i++)
			{
			    sb.append(args[i]).append(" ");
			}
			String message  = sb.toString().trim();
			
			if(SQL.addReply(args[2], player.getDisplayName(), message)){//if can reply
				player.sendMessage("Cant reply to ticket"+args[2]);
			}
		}
	}
}
