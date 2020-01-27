package com.starfluxgames.basicflight;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmd_bfreload implements CommandExecutor{

	private Main plugin;
	
	public cmd_bfreload(Main plugin)
	{
		this.plugin = plugin;
		plugin.getCommand("bfreload").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("basicflight.reload") || sender.isOp())
		{
			plugin.cfgm.reloadConfig();
			plugin.loadConfig();
			sender.sendMessage(plugin.prefix + ChatColor.GREEN + "Config Reloaded");	
		}
		
		return false;
	}
	
}