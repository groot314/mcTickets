package me.groot314.mcTickets;

import org.bukkit.plugin.java.JavaPlugin;

public class mcTickets extends JavaPlugin{
	
	mySQL connect = new mySQL(this);
	@Override
	public void onEnable() {
		this.getCommand("ticket").setExecutor(new ticketCommand(this));
		this.getCommand("t").setExecutor(new ticketCommand(this));
	}
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
}
