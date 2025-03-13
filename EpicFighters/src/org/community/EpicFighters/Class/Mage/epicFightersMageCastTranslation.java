package org.community.EpicFighters.Class.Mage;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.community.EpicFighters.EpicFighters;

public class epicFightersMageCastTranslation {
	
	private EpicFighters plugin;

	public epicFightersMageCastTranslation(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	// Richtungszauber
	
	public void translateSpell(String shortcut, Player player) {

		// Elementarmagier
		if (shortcut.equals("Feuerball_Stufe_1")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicFireball(player, plugin.skill.getInt("Skill." + shortcut + ".Option.Speed", 1), shortcut);
				}				
				return;
			}
			return;
		}

		// Elementarmagier
		if (shortcut.equals("Eisball_Stufe_1")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicIceball(player, plugin.skill.getInt("Skill." + shortcut + ".Option.Speed", 1), shortcut);
				}				
				return;
			}
			return;
		}

		// Elementarmagier
		if (shortcut.equals("Kristallball_Stufe_1")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicArrow(player, plugin.skill.getInt("Skill." + shortcut + ".Option.Speed", 1), shortcut);
				}				
				return;
			}
			return;
		}

		// Elementarmagier
		if (shortcut.equals("Seelenschlag")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicExplosion(player, plugin.skill.getInt("Skill." + shortcut + ".Option.Radius", 5));
				}				
				return;
			}
			return;
		}
		
	}
	
	// Direktzauber
	public void translateSpell(String shortcut, Player player, Entity entity) {
		
		// Elementarmagier
		if (shortcut.equals("Eis_Gitter")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.icePrison(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Kleiner_Seelenbrand")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicFire(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Grosser_Seelenbrand")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicFire(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Kleine_Vergiftung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicPoison(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Grosse_Vergiftung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicPoison(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Kleine_Verlangsamung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicSlowness(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Grosse_Verlangsamung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicSlowness(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Kleine_Schwaechung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicWeakness(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Grosse_Schwaechung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicWeakness(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Kleine_Amnesie")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicCharm(entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Kleine_Telekinese")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicKnockback(player, entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Force", 15), plugin.skill.getInt("Skill." + shortcut + ".Option.yForce", 15), plugin.skill.getInt("Skill." + shortcut + ".Option.maxYForce", 20));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Aggro")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {			        
			        plugin.eFBerserkerDamageSpells.getAggro(player, entity);
				}				
				return;
			}
			return;
		}
	}

	
	// Bodenzauber
	public void translateSpell(String shortcut, Player player, Location location) {

		// Elementarmagier
		if (shortcut.equals("Kleiner_Blitzschlag")) {
			if(checkCooldown(shortcut, player)) {
				plugin.eFMageDamageSpells.magicLightning(player, location, plugin.skill.getInt("Skill." + shortcut + ".Option.Amount", 1));
				setMana(shortcut, player);
			}
			return;
		}

		// Elementarmagier
		if (shortcut.equals("Grosser_Blitzschlag")) {
			if(checkCooldown(shortcut, player)) {
				plugin.eFMageDamageSpells.magicLightning(player, location, plugin.skill.getInt("Skill." + shortcut + ".Option.Amount", 1));
				setMana(shortcut, player);
			}
			return;
		}

		// Elementarmagier
		if (shortcut.equals("Kleiner_Kettenblitz")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicLightningChain(player, location, plugin.skill.getInt("Skill." + shortcut + ".Option.Area", 3));
				}				
				return;
			}
			return;
		}

		// Arkanist
		if (shortcut.equals("Telekinese_AE")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicKnockback(player, location, plugin.skill.getInt("Skill." + shortcut + ".Option.Radius", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Force", 15), plugin.skill.getInt("Skill." + shortcut + ".Option.yForce", 15), plugin.skill.getInt("Skill." + shortcut + ".Option.maxYForce", 20));
				}				
				return;
			}
			return;
		}
		
		/*if (shortcut.equals("Grosse_Vergiftung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicPoison(block, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Area", 5));
				}				
				return;
			}
			return;
		}
		
		if (shortcut.equals("Grosse_Verlangsamung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicSlowness(block, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Area", 5));
				}				
				return;
			}
			return;
		}
		
		if (shortcut.equals("Grosse_Schwaechung")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.magicWeakness(block, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Strength", 5), plugin.skill.getInt("Skill." + shortcut + ".Option.Area", 5));
				}				
				return;
			}
			return;
		}
		
		if (shortcut.equals("Feuerkugel")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFMageDamageSpells.sendCannonToEntity(entity, player);
				}				
				return;
			}
			return;
		}*/
		
	}
	
	private boolean checkCooldown(String shortcut, Player player) {
		if(plugin.cooldown.getString(player.getName() + "." + shortcut) == null) {
			plugin.cooldown.set(player.getName() + "." + shortcut, System.currentTimeMillis());
			return true;
		} else {
			if(System.currentTimeMillis() >= (plugin.cooldown.getLong(player.getName() + "." + shortcut) + plugin.skill.getLong("Skill." + shortcut + ".CooldownInSeconds", 0) * 1000)) {
				plugin.cooldown.set(player.getName() + "." + shortcut, System.currentTimeMillis());
				return true;
			} else {
				return false;
			}
		}
	}
	
	private boolean setMana(String shortcut, Player player) {
		float mana = plugin.skill.getInt("Skill." + shortcut + ".Mana", 2);
		
		if(player.getSaturation() >= mana) {
			player.setSaturation(player.getSaturation() - mana);
			return true;
		} else if(player.getFoodLevel() + player.getSaturation() <= mana){
			return false;
		} else {
			mana = mana - player.getSaturation();
			player.setSaturation(0);
			player.setFoodLevel(player.getFoodLevel() - (int)(mana));
			return true;
		}
	}
	
}