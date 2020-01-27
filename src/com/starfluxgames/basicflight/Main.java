package com.starfluxgames.basicflight;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{

	public ConfigManager cfgm;
	public static String prefix = "";

	@Override
	public void onEnable() {
		loadConfigManager();
		setupDefaultConfig();
		loadConfig();
		new cmd_flight(this);
		new cmd_bfreload(this);
	}

	public void setupDefaultConfig() {
		if (!cfgm.maincfg.contains("require-elytra")) {
			cfgm.maincfg.set("require-elytra", false);
		}
		if (!cfgm.maincfg.contains("disable-prefix")) {
			cfgm.maincfg.set("disable-prefix", false);
		}
		cfgm.saveConfig();
		cfgm.reloadConfig();
	}
	
	public void loadConfig()
	{
		if (!cfgm.maincfg.getBoolean("disable-prefix"))
			prefix = ChatColor.RED + "[" + ChatColor.GOLD + "Basic Flight" + ChatColor.RED + "]" + ChatColor.RESET + " ";
		else if (cfgm.maincfg.getBoolean("disable-prefix"))
			prefix = "";
	}
	
	public void loadConfigManager() {
		cfgm = new ConfigManager();
		cfgm.setup();
		cfgm.saveConfig();
		cfgm.reloadConfig();
	}
}
