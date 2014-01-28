package me.groot314.mcTickets;

import org.bukkit.entity.Player;

public class helpArg {

	mcTickets plugin;

	public helpArg(mcTickets Plugin) {
		this.plugin = Plugin;
	}

	public void help(Player player, String[] args) {
		player.sendMessage("---[mcTickets]---");
		player.sendMessage("/tickets");
		player.sendMessage("/ticket open <message>");
		player.sendMessage("/ticket close <#>");
		player.sendMessage("/ticket reply <#> reply");
		player.sendMessage("/ticket view");
		player.sendMessage("/ticket help");
		player.sendMessage("Admin Commands");
		player.sendMessage("/ticket assign <admin>");
		player.sendMessage("/ticket take");
		player.sendMessage("/ticket tp <id>");
		player.sendMessage("/ticket view all/open/closed/taken/mine");

	}
}
