package com.starfluxgames.basicflight;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_flight implements CommandExecutor{

	private Main plugin;
	
	public cmd_flight(Main plugin)
	{
		this.plugin = plugin;
		plugin.getCommand("flight").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "Only players can run this command!");
			return true;
		}
		Player player = (Player) sender;
		
		if (player.hasPermission("basicflight.flight") || player.isOp())
		{
			Boolean requiresElytra = plugin.cfgm.maincfg.getBoolean("require-elytra");
			Boolean hasElytra = false;
			if (player.getInventory().getArmorContents()[2] != null)
				if (player.getInventory().getArmorContents()[2].getType().toString().toLowerCase().contains("elytra"))
					hasElytra = true;
			
			
			if (requiresElytra)
			{
				if (hasElytra)
				{
					toggleFlight(player);
					return true;
				}else{
					if (player.hasPermission("basicflight.flight.bypasselytra"))
					{
						toggleFlight(player);
						return true;
					}else
					{
						player.sendMessage(plugin.prefix + ChatColor.RED + "You require an Elytra to fly!");
						return false;
					}
				}
			}else
			{
				toggleFlight(player);
				return true;
			}
		}else
		{
			player.sendMessage(plugin.prefix + ChatColor.RED + "Invalid Permissions!");
			return false;
		}
	}
	
	private void toggleFlight(Player player)
	{
		if (player.getAllowFlight())
		{
			player.sendMessage(plugin.prefix + ChatColor.RED + "Flight Disabled");
			player.setAllowFlight(false);
		}else
		{
			player.sendMessage(plugin.prefix + ChatColor.GREEN+ "Flight Enabled");
			player.setAllowFlight(true);
		}
	}
	
}