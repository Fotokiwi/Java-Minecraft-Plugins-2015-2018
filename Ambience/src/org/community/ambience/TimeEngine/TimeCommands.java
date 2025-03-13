package org.community.ambience.TimeEngine;

import java.util.Calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.community.ambience.Ambience;


public class TimeCommands {
	
	Ambience plugin;

	public TimeCommands(Ambience plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("unused")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH); // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		
		long time = plugin.getServer().getWorld("Startinsel").getTime();
		
		int hours = (int) ((Math.floor(time / 1000.0) + 8) % 24); // '8' is the offset
		int minutes = (int) Math.floor((time % 1000) / 1000.0 * 60);
		if(minutes < 10) {
			sender.sendMessage("Es ist " + hours + ":0" + minutes + "Uhr.");
		} else {
			sender.sendMessage("Es ist " + hours + ":" + minutes + "Uhr.");
		}		
		
		//Februar sender.sendMessage("Wir schreiben den " + hour + ". Tag des " + day + ". Zyklus im Jahr der salzigen Tränen.");
		//März sender.sendMessage("Wir schreiben den " + hour + ". Tag des " + day + ". Zyklus im Jahr der kleinen Steinchen.");
		sender.sendMessage("Wir schreiben den " + hour + ". Tag des " + day + ". Zyklus im Jahr der fleißigen Angler.");
		return true;
	
	}
}
