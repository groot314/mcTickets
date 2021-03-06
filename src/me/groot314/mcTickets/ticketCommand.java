package me.groot314.mcTickets;

import java.awt.Color;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ticketCommand implements CommandExecutor {

	mcTickets plugin;
	assignArg assign;
	closeArg close;
	helpArg help;
	openArg open;
	replyArg reply;
	takeArg take;
	tpArg tp;
	viewArg view;

	public ticketCommand(mcTickets Plugin) {
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
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		Player player = (Player) sender;

		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("assign")) {
				if (player.hasPermission("mctickets.admin.assign"))
					assign.assign(player, args); // ticket assign
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("close")) {
				if (player.hasPermission("mctickets.player.close")
						|| player.hasPermission("mctickets.admin.close"))
					close.close(player, args); // ticket close
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				if (player.hasPermission("mctickets.player.help"))
					help.help(player, args); // ticket help
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("open")) {
				if (player.hasPermission("mctickets.player.open"))
					open.open(player, args); // ticket open
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("reply")) {
				if (player.hasPermission("mctickets.player.reply")
						|| player.hasPermission("mctickets.admin.reply"))
					reply.reply(player, args); // ticket reply
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("take")) {
				if (player.hasPermission("mctickets.admin.take"))
					take.take(player, args); // ticket take
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("tp")) {
				if (player.hasPermission("mctickets.admin.tp"))
					tp.tp(player, args); // ticket tp
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("view")) {
				if (player.hasPermission("mctickets.player.view")
						|| player.hasPermission("mctickets.admin.view.all")
						|| player.hasPermission("mctickets.admin.view.open")
						|| player.hasPermission("mctickets.admin.view.closed")
						|| player.hasPermission("mctickets.admin.view.taken")
						|| player.hasPermission("mctickets.admin.view.mine"))
					view.view(player, args); // ticket view
				else {
					player.sendMessage(Color.RED
							+ "You dont have permission to that command");
				}
				return true;
			}
			return false;
		} else {
			sender.sendMessage("/ticket help");
			return true;
		}
	}

}
