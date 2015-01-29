package com.silentdarknessmc.firework;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		
		File file = new File(getDataFolder() + File.separator + "config.yml");
		
		if(!file.exists())
		{
			System.out.print("++++++++++++++++++++++++++++++++++++++++++");
			System.out.print("Enabling SDMC Firework Plugin");
			System.out.print("Loading Config.yml");
			System.out.print("Successfully Enabled SDMC Firework Plugin");
			System.out.print("++++++++++++++++++++++++++++++++++++++++++");
			
			this.getConfig().addDefault("shootmessage", "Fireworks Away!");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
	}
	
	public void onDisable() {
		System.out.print("++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("Disabling SDMC Firework Plugin");
		System.out.print("Thanks For Using SDMC\'s Firework Plugin!");
		System.out.print("Successfully Disabled SDMC Firework Plugin");
		System.out.print("++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			if(cmd.getLabel().equalsIgnoreCase("shoot"))
			{
				Player player = (Player) sender;
				
				if(player.hasPermission("firework.shoot"))
				{
					Firework firework = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
					
					FireworkMeta meta = firework.getFireworkMeta();
					
					FireworkEffect effect = FireworkEffect.builder()
							.flicker(true)
							.withColor(Color.RED)
							.withFade(Color.GREEN)
							.with(Type.CREEPER)
							.trail(true)
							.build();
					
					meta.addEffect(effect);
					meta.setPower(2);
					
					firework.setFireworkMeta(meta);
					
					sender.sendMessage(this.getConfig().getString("shootmessage"));
					sender.sendMessage("This Plugin Is By SDMC!");
					sender.sendMessage("Server IP: play.silentdarknessmc.com");
				}
				
				if(!player.hasPermission("firework.shoot"))
				{
					player.sendMessage(ChatColor.RED + "You Don\'t Have Permission To Use This Command!");
				}
			}
		}
		
		return false;
	}
}