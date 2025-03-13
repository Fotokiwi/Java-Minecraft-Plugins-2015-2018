package org.community.pointsOfInterestCommands;

import java.lang.reflect.Method;
import java.util.List;

public class CACommand {
	private String commandName;
	private List<CACommandArgument> arguments;
	private boolean consoleOnly;
	private boolean playerOnly;
	private boolean adminOnly;
	private boolean visibleForAdminOnly;
	private String description; 
	private Method method;
	
	
	public CACommand(String commandName, List<CACommandArgument>arguments, boolean consoleOnly, boolean playerOnly, boolean adminOnly, boolean visibleForAdminOnly, String description, Method method)
	{
		this.commandName = commandName;
		this.setArguments(arguments);
		this.setConsoleOnly(consoleOnly);
		this.setPlayerOnly(playerOnly);
		this.setAdminOnly(adminOnly);
		this.setVisibleForAdminOnly(visibleForAdminOnly);
		this.description = description;
		this.method = method;
	}
	
	public int getArgumentsLength(){
		return getArguments().size();
	}
	
	public String getCommandName(){
		return commandName;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Method getMethod(){
		return method;
	}

	public boolean isVisibleForAdminOnly() {
		return visibleForAdminOnly;
	}

	public void setVisibleForAdminOnly(boolean visibleForAdminOnly) {
		this.visibleForAdminOnly = visibleForAdminOnly;
	}

	public boolean isPlayerOnly() {
		return playerOnly;
	}

	public void setPlayerOnly(boolean playerOnly) {
		this.playerOnly = playerOnly;
	}

	public boolean isConsoleOnly() {
		return consoleOnly;
	}

	public void setConsoleOnly(boolean consoleOnly) {
		this.consoleOnly = consoleOnly;
	}

	public List<CACommandArgument> getArguments() {
		return arguments;
	}

	public void setArguments(List<CACommandArgument> arguments) {
		this.arguments = arguments;
	}

	public boolean isAdminOnly() {
		return adminOnly;
	}

	public void setAdminOnly(boolean adminOnly) {
		this.adminOnly = adminOnly;
	}
	
	

}
