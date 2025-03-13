package org.community.fourWays.Utility;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.community.fourWays.fourWays;

public class Rewards {

	@SuppressWarnings("unused")
	private fourWays plugin;

	public Rewards(fourWays plugin) {
		this.plugin = plugin;
	}

	public void dropRewardItem(Entity entity, Player player, Material itemMaterial, int i, int j, int k, int l)
	{   
		int level = 0;

		ItemStack waffe = player.getItemInHand();
		if(waffe.containsEnchantment(Enchantment.LOOT_BONUS_MOBS)){
			level = waffe.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);	
		}
		int rewardAmount;
		double random1 = (Math.random()*100);
		int random = (int) random1;
		if ( random >= l){
			rewardAmount = 0;
		} else {
			if (j == k) {
				rewardAmount = k+level;
			}
			else if (j > k) {
				rewardAmount = j+level;
			}
			else {
				//rewardAmount = (int) (j + Math.random() * (k - j));
				rewardAmount = k+level;
			}
		} 

		if(itemMaterial == Material.WOOL)
			rewardAmount = rewardAmount - level;

		if(rewardAmount!=0){
			if(i == 0) {
				player.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(itemMaterial, rewardAmount));
			} else {
				player.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(itemMaterial, rewardAmount, (short) i));
			}}
	}

	public void dropLowItem(Entity entity, Player player, int Menge, Material itemMaterial, int subID, int Chance){
		double random1 = (Math.random()*1000);
		int random = (int) random1;
		if(random<=Chance){
			if(subID==0){
				ItemStack item = new ItemStack(itemMaterial, Menge);
				player.getWorld().dropItemNaturally(entity.getLocation(), item);
			}else{
				ItemStack item = new ItemStack(itemMaterial, Menge, (short) subID);
				player.getWorld().dropItemNaturally(entity.getLocation(), item);
			}
		}
	}
}