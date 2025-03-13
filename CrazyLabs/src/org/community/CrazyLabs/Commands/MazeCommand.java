package org.community.CrazyLabs.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.CrazyLabs.CrazyLabs;
import org.community.CrazyLabs.MazeSettings.*;
import org.community.CrazyLabs.MazeGeneration.*;

public class MazeCommand {

	private CrazyLabs plugin;

	public MazeCommand(CrazyLabs plugin)
	{
		this.plugin = plugin;
	}

	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if(cmd.getName().equalsIgnoreCase("maze")){

			Player player = (Player) sender;

			if(args.length == 0) {
				sender.sendMessage("Die korrekte Syntax lautet: /maze create [Theme] [Modulgröße]");
				return true;
			}

			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("generate")) {
					Maze maze = plugin.mazeEditor.get(player);
					if(maze != null) {
						maze.generateBlueprint();
						sender.sendMessage("Das Blueprint wurde erfolgreich generiert.");
						return true;
					} else {
						sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
						return true;
					}
				}
				sender.sendMessage("Die korrekte Syntax lautet: /maze create [Theme] [Modulgröße]");
				return true;
			}

			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("build")) {
					if(args[1].equalsIgnoreCase("blueprint")) {
						Maze maze = plugin.mazeEditor.get(player);
						if(maze != null) {
							maze.setMazeLocation(player.getLocation());
							maze.generateIngameMaze();
							sender.sendMessage("Das Labyrinth wird nun im Spiel generiert.");
							return true;
						} else {
							sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
							return true;
						}
					}
					if(args[1].equalsIgnoreCase("dungeon")) {
						Maze maze = plugin.mazeEditor.get(player);
						if(maze != null) {
							maze.setMazeLocation(player.getLocation());
							maze.generateIngameMaze();
							sender.sendMessage("Das Labyrinth wird nun im Spiel generiert.");
							return true;
						} else {
							sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
							return true;
						}
					}
				}
				sender.sendMessage("Die korrekte Syntax lautet: /maze create [Theme] [Modulgröße]");
				return true;
			}

			if(args.length == 3) {
				if(!args[0].equalsIgnoreCase("create") && !args[0].equalsIgnoreCase("set")) {
					sender.sendMessage("Die korrekte Syntax lautet: /maze create [Theme] [Modulgröße]");					
					return true;
				}
				if(args[0].equalsIgnoreCase("create")) {
					if(!Theme.themeExists(args[1])) {
						sender.sendMessage("Dieses Theme ist ungültig. Folgende Themes existieren:");	
						sender.sendMessage(Theme.getThemes());
						return true;
					}
					try {
						int moduleSize = Integer.parseInt(args[2].trim());
						if(!ModuleSize.isSizeAllowed(args[1], moduleSize)) {
							sender.sendMessage("Diese Modulgröße ist ungültig. Folgende Größen existieren:");	
							sender.sendMessage(ModuleSize.getModuleSizes(args[1]));
							return true;
						} else {
							Maze maze = new Maze(plugin, args[1], moduleSize);
							sender.sendMessage("" + player.getName());
							sender.sendMessage("" + maze.getMazeAlgorithm());
							plugin.mazeEditor.put(player, maze);
						}
					}
					catch (NumberFormatException nfe) {
						sender.sendMessage("Die Modulgröße muss ein ganzzahliger Wert sein (Integer).");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("set")) {
					if(args[1].equalsIgnoreCase("algorithm")) {
						if(!Algorithm.algorithmExists(args[2])) {
							sender.sendMessage("Dieser Algorithmus wird nicht unterstützt. Folgende Algorithmen existieren:");	
							sender.sendMessage(Algorithm.getAlgorithms());
							return true;
						} else {
							Maze maze = plugin.mazeEditor.get(player);
							if(maze != null) {
								maze.setMazeAlgorithm(args[2]);
								sender.sendMessage("Das Labyrinth-Algorithmus wurde auf '" + args[2] + "' geändert.");
								return true;
							} else {
								sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
								return true;
							}
						}
					}
					if(args[1].equalsIgnoreCase("length")) {
						try {
							int length = Integer.parseInt(args[2].trim());
							if(!Size.isInsideLengthLimits(length)) {
								sender.sendMessage("Die Länge ist außerhalb des zulässigen Bereichs. Gültigkeit:");	
								sender.sendMessage("Minimum: " + Size.getMinSize() + " Maximum: " + Size.getMaxSize());
								return true;
							} else {
								Maze maze = plugin.mazeEditor.get(player);
								if(maze != null) {
									maze.setMazeLength(length);
									sender.sendMessage("Das Länge wurde auf '" + args[2] + "' geändert.");
									return true;
								} else {
									sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
									return true;
								}
							}
						}
						catch (NumberFormatException nfe) {
							sender.sendMessage("Die Länge muss ein ganzzahliger Wert sein (Integer).");
							return true;
						}						
					}
					if(args[1].equalsIgnoreCase("width")) {
						try {
							int width = Integer.parseInt(args[2].trim());
							if(!Size.isInsideLengthLimits(width)) {
								sender.sendMessage("Die Breite ist außerhalb des zulässigen Bereichs. Gültigkeit:");	
								sender.sendMessage("Minimum: " + Size.getMinSize() + " Maximum: " + Size.getMaxSize());
								return true;
							} else {
								Maze maze = plugin.mazeEditor.get(player);
								if(maze != null) {
									maze.setMazeWidth(width);
									sender.sendMessage("Das Breite wurde auf '" + args[2] + "' geändert.");
									return true;
								} else {
									sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
									return true;
								}
							}
						}
						catch (NumberFormatException nfe) {
							sender.sendMessage("Die Breite muss ein ganzzahliger Wert sein (Integer).");
							return true;
						}						
					}
					if(args[1].equalsIgnoreCase("heigth")) {
						try {
							int heigth = Integer.parseInt(args[2].trim());
							if(!Size.isInsideHeigthLimits(heigth)) {
								sender.sendMessage("Die Höhe ist außerhalb des zulässigen Bereichs. Gültigkeit:");	
								sender.sendMessage("Minimum: " + Size.getMinHeigth() + " Maximum: " + Size.getMaxHeigth());
								return true;
							} else {
								Maze maze = plugin.mazeEditor.get(player);
								if(maze != null) {
									maze.setMazeHeigth(heigth);
									sender.sendMessage("Das Höhe wurde auf '" + args[2] + "' geändert.");
									return true;
								} else {
									sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
									return true;
								}
							}
						}
						catch (NumberFormatException nfe) {
							sender.sendMessage("Die Höhe muss ein ganzzahliger Wert sein (Integer).");
							return true;
						}						
					}
				}
				
				if(args.length == 4) {
					if(args[0].equalsIgnoreCase("set")){
						if(args[1].equalsIgnoreCase("entrance")){
							Maze m = plugin.mazeEditor.get(sender);
							if(m != null)
							{
								
							}
							else{
								sender.sendMessage("Dieser Befehl setzt voraus, dass bereits ein Labyrinth initialisiert wurde.");
								return true;
							}
						}
					}
					
				}
			}
		}

		return false;
	}

}