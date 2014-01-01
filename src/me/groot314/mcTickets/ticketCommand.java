package me.groot314.mcTickets;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ticketCommand implements CommandExecutor{

	mcTickets plugin;
	assignArg assign;
	closeArg close;
	helpArg help;
	openArg open;
	replyArg reply;
	takeArg take;
	tpArg tp;
	viewArg view;
	
	public ticketCommand(mcTickets Plugin){
		assign = new assignArg(Plugin);
		close = new closeArg(Plugin);
		help = new helpArg(Plugin);
		open = new openArg(Plugin);
		reply = new replyArg(Plugin);
		take = new takeArg(Plugin);
		tp = new tpArg(Plugin);
		view = new viewArg(Plugin);
		this.plugin = Plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if(args.length >= 1){
			if(args[0].equalsIgnoreCase("assign")){
				assign.assign(player, args); //ticket assign
				return true;
			}
			else if(args[0].equalsIgnoreCase("close")){
				close.close(player, args); //ticket close
				return true;
			}
			else if(args[0].equalsIgnoreCase("help")){
				help.help(player, args); //ticket help
				return true;
			}
			else if(args[0].equalsIgnoreCase("open")){
				open.open(player, args); //ticket open
				return true;
			}
			else if(args[0].equalsIgnoreCase("reply")){
				reply.reply(player, args); //ticket reply
				return true;
			}
			else if(args[0].equalsIgnoreCase("take")){
				take.take(player, args); //ticket take
				return true;
			}
			else if(args[0].equalsIgnoreCase("tp")){
				tp.tp(player, args); //ticket tp
				return true;
			}
			else if(args[0].equalsIgnoreCase("view")){
				view.view(player, args); //ticket view
				return true;
			}
			return false;
		}
		else{
		sender.sendMessage("/ticket help");
		return true;
		}
	}

}
