package org.community.EpicFighters.Class.Mage;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.community.EpicFighters.EpicFighters;

public class epicFightersMagePlayerInteractEvent implements Listener {

	private EpicFighters plugin;

	public epicFightersMagePlayerInteractEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if(player.getItemInHand().getType() != Material.BLAZE_ROD)
			return;

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
			return;
		
		if(!player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Zauberstab"))
			return;

		String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill." + (plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active")));

		if(skill == null)
			return;
		String skillType = plugin.skill.getString("Skill." + skill + ".Type");
		if(!skillType.equalsIgnoreCase("Richtungszauber") && !skillType.equalsIgnoreCase("Direktzauber") && !skillType.equalsIgnoreCase("Bodenzauber"))
			return;
		
		if(skillType.equalsIgnoreCase("Richtungszauber")) {
			
			plugin.eFMageCastTranslation.translateSpell(skill, player);
			
		} else if(skillType.equalsIgnoreCase("Direktzauber")) {
			
			Player targetPlayer = null;
			Entity targetEntity = null;

			int skillRange = plugin.skill.getInt("Skill." + skill + ".Option.Range", 0);

			targetPlayer = getTargetPlayer(player);
			targetEntity = getTargetEntity(player);
			
			if(targetPlayer != null && targetPlayer.getLocation().distance(player.getLocation()) <= skillRange) {

				if(targetPlayer.isOnline() == false)
					return;
				
				plugin.eFMageCastTranslation.translateSpell(skill, player, targetPlayer);

			} else if(targetEntity != null && targetEntity.getLocation().distance(player.getLocation()) <= skillRange) {

				plugin.eFMageCastTranslation.translateSpell(skill, player, targetEntity);

			}
			
		} else if(skillType.equalsIgnoreCase("Bodenzauber")) {
			
			int skillRange = plugin.skill.getInt("Skill." + skill + ".Option.Range", 0);
			
			@SuppressWarnings("deprecation")
			Block targetBlock = player.getTargetBlock(null, skillRange);
			if(targetBlock == null || targetBlock.getType() == Material.AIR)
				return;
			
			Location groundLocation = targetBlock.getLocation();
			
			plugin.eFMageCastTranslation.translateSpell(skill, player, groundLocation);
			
		} else {
			return;
		}
	}

	private Player getTargetPlayer(Player player) {
		return getTarget(player, player.getWorld().getPlayers());
	}

	private Entity getTargetEntity(Player player) {

		Player observer = player;

		Location observerPos = observer.getEyeLocation();
		Vector3D observerDir = new Vector3D(observerPos.getDirection());

		Vector3D observerStart = new Vector3D(observerPos);
		Vector3D observerEnd = observerStart.add(observerDir.multiply(60));

		Entity hit = null;

		// Get nearby entities
		for (Entity target : player.getNearbyEntities(60, 60, 60)) {
			// Bounding box of the given player
			Vector3D targetPos = new Vector3D(target.getLocation());
			Vector3D minimum = targetPos.add(-0.5, 0, -0.5);
			Vector3D maximum = targetPos.add(0.5, 1.67, 0.5);

			if (target != observer && hasIntersection(observerStart, observerEnd, minimum, maximum)) {
				if (hit == null || hit.getLocation().distanceSquared(observerPos) > target.getLocation().distanceSquared(observerPos)) {
					hit = target;
				}
			}
		}
		return hit;
	}

	private boolean hasIntersection(Vector3D p1, Vector3D p2, Vector3D min, Vector3D max) {
		final double epsilon = 0.0001f;

		Vector3D d = p2.subtract(p1).multiply(0.5);
		Vector3D e = max.subtract(min).multiply(0.5);
		Vector3D c = p1.add(d).subtract(min.add(max).multiply(0.5));
		Vector3D ad = d.abs();

		if (Math.abs(c.x) > e.x + ad.x)
			return false;
		if (Math.abs(c.y) > e.y + ad.y)
			return false;
		if (Math.abs(c.z) > e.z + ad.z)
			return false;

		if (Math.abs(d.y * c.z - d.z * c.y) > e.y * ad.z + e.z * ad.y + epsilon)
			return false;
		if (Math.abs(d.z * c.x - d.x * c.z) > e.z * ad.x + e.x * ad.z + epsilon)
			return false;
		if (Math.abs(d.x * c.y - d.y * c.x) > e.x * ad.y + e.y * ad.x + epsilon)
			return false;

		return true;
	}

	private <T extends org.bukkit.entity.Entity> T getTarget(final org.bukkit.entity.Entity entity, final Iterable<T> entities) {
		if (entity == null)
			return null;
		T target = null;
		final double threshold = 0.7;
		for (final T other : entities) {
			final Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
			if (entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < threshold && n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0) {
				if (target == null || target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation()))
					target = other;
			}
		}
		return target;
	}

}