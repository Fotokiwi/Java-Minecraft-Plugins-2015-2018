package org.community.pointsOfInterestCommands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;




public class CACommandHandler {
	
	private List<CACommand> commands;
	private final float numberOfCommandsPerPage = 6f;
	public final static Logger log = Logger.getLogger("Minecraft");

	public CACommandHandler(List<CACommand> commands) {
		this.commands = commands;

	}

	public boolean getCommand(CommandSender sender, Command cmd, String[] args, boolean senderIsAdmin) {
		if ((args.length == 1 || args.length == 2) && (args[0].equals("help") || args[0].equals("hilfe")))
			return listAllCommands(sender, cmd, args, senderIsAdmin);
		List<CACommand> possibleCommands = new ArrayList<CACommand>();
		int l = args.length;
		for (CACommand c : commands) {
			if (c.getArgumentsLength() == l) {
				possibleCommands.add(c);
			}
		}
		if (possibleCommands.size() == 0) {
			sender.sendMessage("Die Anzahl der Argumente von deinem Befehl stimmt mit keinem der existierenden Befehle des Plugins überein. \nBitte verwende "
					+ ChatColor.GOLD
					+ "/"
					+ cmd.getName()
					+ " help "
					+ ChatColor.WHITE
					+ "für eine Übersicht über alle Befehle.");
		}
		for (CACommand c : possibleCommands) {
			if (doArgumentsMatch(c.getArguments(), args)) {
				if (!c.isAdminOnly() || senderIsAdmin) {
					if (c.isConsoleOnly() && !(sender instanceof Player)) {
						invokeCommand(c, sender, c.getMethod(), args);
						return true;
					} else if (c.isPlayerOnly() && (sender instanceof Player)) {
						invokeCommand(c, sender, c.getMethod(), args);
						return true;
					} else {
						invokeCommand(c, sender, c.getMethod(), args);
						return true;
					}
				}
				else{
					sender.sendMessage("Du besitzt nicht die nötigen Rechte, um diesen Befehl auszuführen.");
				}
			}
		}
		sender.sendMessage("Die Argumente deines Befehls stimmen mit keinem existierenden Befehl dieses Plugins überein.\nBitte verwende "
				+ ChatColor.GOLD
				+ "/"
				+ cmd.getName()
				+ " help "
				+ ChatColor.WHITE
				+ "für eine Übersicht über alle Befehle.");
		return true;

	}

	public boolean listAllCommands(CommandSender sender, Command cmd, String[] args, boolean senderIsAdmin) {
		List<CACommand> allowedCommand = new ArrayList<CACommand>();
		for (CACommand c : commands) {
			if (!c.isVisibleForAdminOnly() || senderIsAdmin)
				allowedCommand.add(c);
		}
		int NumberOfCommands = allowedCommand.size();
		int pageToShow = 0;
		if (args.length > 1) {
			try {
				pageToShow = Integer.parseInt(args[1]) - 1;
			} catch (Exception e) {
				sender.sendMessage("Bitte verwende eine natürliche Zahl zur Angabe der Hilfsseite, du du aufrufen möchtest.");
				return true;
			}
		}
		int maxPage = (int) Math.ceil(NumberOfCommands / numberOfCommandsPerPage);
		if (pageToShow > maxPage) {
			sender.sendMessage("Du hast versucht eine Hilfsseite auszuwählen, die nicht existiert");
		}
		sender.sendMessage("Es existieren folgende Befehle (Seite " + (pageToShow + 1) + " von " + maxPage + "):");
		for (int i = 0; i < numberOfCommandsPerPage; i++) {
			if((int) numberOfCommandsPerPage * pageToShow + i >= commands.size())
				return true;
			CACommand ca = commands.get((int) numberOfCommandsPerPage * pageToShow + i);
			sender.sendMessage(ChatColor.GOLD + "/" + ca.getCommandName() + parseArguments(ca.getArguments())
					+ ChatColor.GRAY + " - " + ChatColor.WHITE + ca.getDescription());
		}
		return true;
	}

	public String parseArguments(List<CACommandArgument> l) {
		String ret = "";
		for (CACommandArgument ca : l) {
			if (ca.isSpecificToValue()) {
				ret += " " + ca.helpValue();
			} else
				ret += " [" + ca.getDescriptionForNonSpecific() + "]";
		}
		return ret;
	}

	public boolean doArgumentsMatch(List<CACommandArgument> arguments, String[] args) {
		if (arguments.size() != args.length)
			return false;
		for (int i = 0; i < arguments.size(); i++) {
			CACommandArgument ca = arguments.get(i);
			if (!ca.compareToArgument(args[i]))
				return false;
		}
		return true;
	}

	public void invokeCommand(CACommand cmd, CommandSender cs, Method m, String[] args)
	{
		int intIndex = 0;
		int stringIndex = 0;
		Object[] parameter = new Object[m.getParameterCount()];
		for(int i = 0; i < parameter.length; i++){
			Class<?> c = (m.getParameterTypes())[i];
			log.log(Level.SEVERE, "parameter " + i + " : " + c.getSimpleName());
			if(c.getSimpleName().equals("CommandSender"))
				parameter[i] = cs;
			if(c.getSimpleName().equals("Player"))
				parameter[i] = (Player) cs;
			if(c.getSimpleName().equals("int")){
				try{
					parameter[i] = Integer.parseInt(args[findSuitableInt(cmd, intIndex)]);
				}
				catch(Exception e){
					log.log(Level.SEVERE, "CACommandHandler couldn't parse argument in invokeCommand. ");
					parameter[i] = 0;
				}
			}
			if(c.getSimpleName().equals("String")){
				parameter[i] = args[findSuitableString(cmd, stringIndex)];
			}				
				
		}
		
		for(Object o : parameter){
			log.log(Level.SEVERE, "Class: " + o.getClass().getSimpleName() + " " + o.toString());
		}
		
		try {
			m.invoke(parameter);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, "CACommandHandler: Method was not public. ");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, "CACommandHandler: At least one argument was illegal ");
		} catch (InvocationTargetException e) {
			log.log(Level.SEVERE, "CACommandHandler: InvocationTargetException " + e.getMessage());
		}
	}
	
	public int findSuitableInt(CACommand cmd, int startIndex){
		for(int i = startIndex; i < cmd.getArgumentsLength(); i++){
			if((cmd.getArguments().get(i) instanceof CACommandArgumentInt) && !cmd.getArguments().get(i).isSpecificToValue())
				return i;
		}
		log.log(Level.SEVERE, "CACommandHandler: couldn't find a suitable integer in the arguments list");
		return 0;
	}
	
	public int findSuitableString(CACommand cmd, int startIndex){
		for(int i = startIndex; i < cmd.getArgumentsLength(); i++){
			if((cmd.getArguments().get(i) instanceof CACommandArgumentString) &&!cmd.getArguments().get(i).isSpecificToValue())
				return i;
		}
		log.log(Level.SEVERE, "CACommandHandler: couldn't find a suitable string in the arguments list");
		return 0;
	}
}
