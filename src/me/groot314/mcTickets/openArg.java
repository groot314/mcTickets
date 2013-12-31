package me.groot314.mcTickets;

import org.bukkit.entity.Player;

public class openArg {

	mcTickets plugin;
	mySQL SQL;

	public openArg(mcTickets Plugin) {
		SQL = new mySQL(Plugin);
		this.plugin = Plugin;
	}

	public void open(Player player, String[] args) {

		if (args.length > 1) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				sb.append(args[i]).append(" ");
			}
			String message = sb.toString().trim();

			int ticketNumber = SQL.newTicket(player.getName(), player.getWorld().getName(),
					player.getLocation().getBlockX(), player.getLocation()
							.getBlockY(), player.getLocation().getBlockZ(),
					message);
			player.sendMessage("Ticket "+ticketNumber+" Opened");
			// player.sendMessage("Ticket "+String.valueOf(ticketNumber)+" Opened");

		} else {
			player.sendMessage("/ticket open <reason>");
		}
	}
}
