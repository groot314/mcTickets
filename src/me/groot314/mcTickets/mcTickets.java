package me.groot314.mcTickets;

import org.bukkit.plugin.java.JavaPlugin;

public class mcTickets extends JavaPlugin {

	mySQL connect = new mySQL(this);

	@Override
	public void onEnable() {
		this.saveDefaultConfig();

		connect.connect();
		connect.checkTables();
		this.getCommand("ticket").setExecutor(new ticketCommand(this));
		this.getCommand("t").setExecutor(new ticketCommand(this));
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}
