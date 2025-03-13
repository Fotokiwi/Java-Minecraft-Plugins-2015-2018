package org.community.FoodControl.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.material.Cake;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.FoodControl.FoodControl;

public class playerListener implements Listener {
	
	private final FoodControl plugin;
	
	private Map<Player, String> lastFood = new HashMap<Player, String>();
	
	private Set<String> allKeys;
 	 
	public playerListener(FoodControl plugin)
	{		
		this.plugin = plugin;
		this.allKeys = plugin.allKeys;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityRegainHealth(EntityRegainHealthEvent event)
    {		
		if(!(event.getEntity() instanceof Player))
			return;
		if(event.getRegainReason() == RegainReason.SATIATED) {
			event.setCancelled(true);
			Player player = (Player) event.getEntity();
			player.setSaturation((float) (player.getSaturation() + event.getAmount() * 0.75));
			return;
		}
    }  
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerConsumeItem(PlayerItemConsumeEvent event)
    {		
		Player player = event.getPlayer();
		Material item = event.getItem().getType();
		
		if(item == Material.POTION) {
			this.lastFood.put(player, "POTION-" + event.getItem().getDurability());
			checkFoodOrder(player, "POTION-" + event.getItem().getDurability());
			return;
		}
		
		if(item == Material.MILK_BUCKET) {
			event.setCancelled(true);
			return;
		}

		checkFoodOrder(player, item.name());
		this.lastFood.put(player, item.name());
    }
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event)
    {		
		Player p = (Player) event.getPlayer();      	
		
		if(event.hasBlock() && event.getClickedBlock().getType() == Material.CAKE_BLOCK) {
			event.setCancelled(true);
			onPlayerRightClickCake(p, event);
			return;
		}
    } 
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
		if(!(event.getEntity() instanceof Player))
			return;
		
		Player player = (Player) event.getEntity();
		
		String item = this.lastFood.get(player);
		
		if(item == null)
			return;
		
		if(!allKeys.contains("" + item)) {
			return;
		}
		
		event.setCancelled(true);
		this.lastFood.put(player, null);
		onPlayerRightClick(player, item);
		//checkFoodOrder(player, item);
    }

	private void onPlayerRightClickCake(Player p, PlayerInteractEvent event)
    {
        // If cake isn't defined, or if the player can't eat, return
        int hunger = plugin.config.getInt("Food.CAKE_BLOCK.Hunger", 0);
        int health = plugin.config.getInt("Food.CAKE_BLOCK.Health", 0);
        double saturation = plugin.config.getDouble("Food.CAKE_BLOCK.Saturation", 0.0);
        
        // Eat the cake.
        eatCake(event.getClickedBlock());
        
        setHealth(p, health);
        
        setHunger(p, hunger);
        
        if(p.getHealth() >= 20)
        	return;
        	
        setSaturation(p, saturation);
    }
    
    private void onPlayerRightClick(Player p, String item)
    {
        // If the item has no health value, return
        int hunger = plugin.config.getInt("Food." + item + ".Hunger", 0);
        int health = plugin.config.getInt("Food." + item + ".Health", 0);
        double saturation = plugin.config.getDouble("Food." + item + ".Saturation", 0.0);
        
       	setHealth(p, health);
        
        setHunger(p, hunger);
        
        if(item.equalsIgnoreCase("GOLDEN_APPLE") && p.getHealth() >= 20)
        	return;
        	
        setSaturation(p, saturation);
        
    } 
    
    private void checkFoodOrder(Player player, String item) {

    	if(plugin.foodOrder.get(player) == null) {
    		plugin.foodOrder.put(player, item);
    		//String itemCache = "" + plugin.foodOrder.get(player);
    		//player.sendMessage(itemCache);
    	} else {
    		String itemCache = "" + plugin.foodOrder.get(player);
	    	
	    	String[] items = itemCache.split(",");
	    	String[] finalItems = null;
	    	
	    	if(items.length == 1) {
	    		String[] newItems = new String[2];
	    		newItems[0] = items[0];
	    		newItems[1] = item;
	    		finalItems = newItems;
	    	}
	    	if(items.length == 2) {
	    		String[] newItems = new String[3];
	    		newItems[0] = items[0];
	    		newItems[1] = items[1];
	    		newItems[2] = item;
	    		finalItems = newItems;
	    	}
	    	if(items.length == 3) {
	    		String[] newItems = new String[4];
	    		newItems[0] = items[0];
	    		newItems[1] = items[1];
	    		newItems[2] = items[2];
	    		newItems[3] = item;
	    		finalItems = newItems;
	    	}
	    	if(items.length == 4) {
	    		String[] newItems = new String[5];
	    		newItems[0] = items[0];
	    		newItems[1] = items[1];
	    		newItems[2] = items[2];
	    		newItems[3] = items[3];
	    		newItems[4] = item;
	    		finalItems = newItems;
	    	}
	    	if(items.length == 5) {
	
	    		String[] newItems = new String[5];
	    		newItems[0] = items[1];
	    		newItems[1] = items[2];
	    		newItems[2] = items[3];
	    		newItems[3] = items[4];
	    		newItems[4] = item;
	    		finalItems = newItems;
	    	}
	    	
	    	itemCache = "";
	    	
	    	for(int i = 0; i < finalItems.length; i++) {
	    		itemCache += finalItems[i] + ",";
	    	}
	    	
	    	itemCache = itemCache.substring(0, itemCache.length() - 1);
	    	
	    	//player.sendMessage(itemCache);
	    	plugin.foodOrder.put(player, itemCache);
	    	
	    	if(itemCache.split(",").length == 5) {
	    		plugin.foodOrder.put(player, itemCache);
	    		checkMeals(player, itemCache);
	    	}
    	}    	    	
		
	}
    
    private void eatCake(Block block)
    {
        Cake cake = (Cake) block.getState().getData();
    	// Grab the amount of eaten slices.
        int eaten = cake.getSlicesEaten();
        
        // If this is the last piece, remove the cake.
        if (eaten == 5)
            block.setType(Material.AIR);
        else
            cake.setSlicesRemaining(eaten + 1);
    }
    
    private void checkMeals(Player player, String foodOrder) {
    	if(plugin.meals.getString("Meals." + foodOrder + ".Effect") == null){
    		//player.sendMessage("Kein Effekt für die Mahlzeit: " + foodOrder + " hinterlegt.");
    	} else {
    		String effect = plugin.meals.getString("Meals." + foodOrder + ".Effect");
    		castFoodEffect(player, effect, foodOrder);
    		plugin.foodOrder.remove(player);
    		//player.sendMessage("Es ist ein Effekt für die Mahlzeit: " + foodOrder + " vorhanden.");
    	}
    }

	private void castFoodEffect(Player player, String effect, String meal) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect), plugin.meals.getInt("Meals." + meal + ".Duration", 100), plugin.meals.getInt("Meals." + meal + ".Strength", 1)));	
	}
	
	/*@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event)
    {		
		Player p = (Player) event.getPlayer();      	
		
		if(!event.hasItem()) {
			foodcache.put(p.getName(), false);
			return;
		}
		
		Material item = event.getItem().getType();
		
		if(p.getFoodLevel() >= 20 && allKeys.contains(item.name())) {
			event.setCancelled(true);
			return;
		}
		
		if(event.hasBlock() && event.getClickedBlock().getType() == Material.CAKE_BLOCK) {
			event.setCancelled(true);
			onPlayerRightClickCake(p, event);
			return;
		}  
		
		if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;		
		
		if(allKeys.contains("" + item.name())) {
			foodcache.put(p.getName(), true);
			foodcache_timestamp.put(p.getName(), System.currentTimeMillis());
		}
    } */
	
	/*@EventHandler(priority = EventPriority.HIGH)
	public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
		if(!(event.getEntity() instanceof Player))
			return;
		
		Player p = (Player) event.getEntity();
		
		p.sendMessage("Huhn oder Ei?");
		
		Material item = event.getEntity().getItemInHand().getType();
		
		if(item == null)
			return;
		
		if(!allKeys.contains("" + item.name())) {
			return;
		}
		
		if(foodcache.containsKey(p.getName())) {
			if(foodcache.get(p.getName()) == true && (System.currentTimeMillis() <= foodcache_timestamp.get(p.getName()) + 3000)) {
				foodcache.put(p.getName(), false);
				event.setCancelled(true);
				onPlayerRightClick(p, item);
				checkFoodOrder(p, item);
			} else {
				event.setCancelled(true);
			}
		}
    } */   
    
    /*private void checkFoodOrder(Player player, Material item) {

    	if(plugin.foodOrder.get(player) == null) {
    		plugin.foodOrder.put(player, item.name() + ",");
    		String itemCache = "" + plugin.foodOrder.get(player);
    		player.sendMessage(itemCache);
    	} else {
    		String itemCache = "" + plugin.foodOrder.get(player);
	    	
	    	String[] items = itemCache.split(",");
	    	String[] finalItems = null;
	    	
	    	if(items.length == 1) {
	    		String[] newItems = new String[2];
	    		newItems[0] = items[0];
	    		newItems[1] = item.name();
	    		finalItems = newItems;
	    	}
	    	if(items.length == 2) {
	    		String[] newItems = new String[3];
	    		newItems[0] = items[0];
	    		newItems[1] = items[1];
	    		newItems[2] = item.name();
	    		finalItems = newItems;
	    	}
	    	if(items.length == 3) {
	    		String[] newItems = new String[4];
	    		newItems[0] = items[0];
	    		newItems[1] = items[1];
	    		newItems[2] = items[2];
	    		newItems[3] = item.name();
	    		finalItems = newItems;
	    	}
	    	if(items.length == 4) {
	    		String[] newItems = new String[5];
	    		newItems[0] = items[0];
	    		newItems[1] = items[1];
	    		newItems[2] = items[2];
	    		newItems[3] = items[3];
	    		newItems[4] = item.name();
	    		finalItems = newItems;
	    	}
	    	if(items.length == 5) {
	
	    		String[] newItems = new String[5];
	    		newItems[0] = items[1];
	    		newItems[1] = items[2];
	    		newItems[2] = items[3];
	    		newItems[3] = items[4];
	    		newItems[4] = item.name();
	    		finalItems = newItems;
	    	}
	    	
	    	itemCache = "";
	    	
	    	for(int i = 0; i < finalItems.length; i++) {
	    		itemCache += finalItems[i] + ",";
	    	}
	    	
	    	itemCache = itemCache.substring(0, itemCache.length() - 1);
	    	
	    	//player.sendMessage(itemCache);
	    	plugin.foodOrder.put(player, itemCache);
	    	
	    	if(items.length == 5) {
	    		plugin.foodOrder.put(player, itemCache);
	    		checkMeals(player, itemCache);
	    	}
    	}    	    	
		
	}*/

	/*private void onPlayerRightClickCake(Player p, PlayerInteractEvent event)
    {
        // If cake isn't defined, or if the player can't eat, return
        int hunger = plugin.config.getInt("Food.CAKE_BLOCK.Hunger", 0);
        int health = plugin.config.getInt("Food.CAKE_BLOCK.Health", 0);
        double saturation = plugin.config.getDouble("Food.CAKE_BLOCK.Saturation", 0.0);
        
        // Eat the cake.
        eatCake(event.getClickedBlock());
        
        setHealth(p, health);
        
        setHunger(p, hunger);
        
        if(p.getHealth() >= 20)
        	return;
        	
        setSaturation(p, saturation);
    }
    
    private void onPlayerRightClick(Player p, Material item)
    {
        // If the item has no health value, return
        int hunger = plugin.config.getInt("Food." + item.name() + ".Hunger", 0);
        int health = plugin.config.getInt("Food." + item.name() + ".Health", 0);
        double saturation = plugin.config.getDouble("Food." + item.name() + ".Saturation", 0.0);
        
       	setHealth(p, health);
        
        setHunger(p, hunger);
        
        if(item == Material.GOLDEN_APPLE && p.getHealth() >= 20)
        	return;
        	
        setSaturation(p, saturation);
        
    } */
    
    public void testfood(Player p){
    	p.setHealth(2);
    	p.setFoodLevel(2);
    	p.setSaturation(0);
    }
    
    private void setHealth(Player p, int health)
    {
        double newHealth = Math.min(20, p.getHealth() + health);
        newHealth = Math.max(0, newHealth);
        p.setHealth(newHealth);
    }  
    
    private void setHunger(Player p, int hunger)
    {
        int newHunger = Math.min(20, p.getFoodLevel() + hunger);
        newHunger = Math.max(0, newHunger);
        p.setFoodLevel(newHunger);       
    }
    
    private void setSaturation(Player p, double saturation) {
        float newSaturation = (float) Math.min(20, p.getSaturation() + saturation);
        newSaturation = (float) Math.max(0, p.getSaturation() + saturation);
        p.setSaturation(newSaturation);  
    	/*double newSaturation = p.getSaturation() + Float.parseFloat("" + saturation);
    	if(newSaturation <= 0)
    		newSaturation = 0;
    	newSaturation = Math.round(newSaturation*100)/100.0;
    	float finishSaturation = Float.parseFloat("" + newSaturation);
    	p.setSaturation(finishSaturation);*/
    }
    
   /* @SuppressWarnings("unused")
	private boolean manageTimestamp(Player player) {
    	if (timestamp.containsKey(player.getName())) {
    		if (System.currentTimeMillis() >= timestamp.get(player.getName()) + 5000) {
    			timestamp.put(player.getName(), System.currentTimeMillis());
    			return true;
    		}
    	} else {
    		timestamp.put(player.getName(), System.currentTimeMillis());
    		return true;
    	}
    	return false;
    }
    
    private void eatCake(Block block)
    {
        Cake cake = (Cake) block.getState().getData();
    	// Grab the amount of eaten slices.
        int eaten = cake.getSlicesEaten();
        
        // If this is the last piece, remove the cake.
        if (eaten == 5)
            block.setType(Material.AIR);
        else
            cake.setSlicesRemaining(eaten + 1);
    }

    @SuppressWarnings("unused")
	private boolean canEat(Player p, int health, int hunger)
    {
        if (health != 0) {
	    	if (health > 0 && p.getHealth() >= 20)
	            return false;
	        if (health < 0 && p.getHealth() + health <= 0)
	            return false;
        }
        if (hunger != 0) {
	    	if (hunger > 0 && p.getFoodLevel() >= 20)
	            return false;
	        if (hunger < 0 && p.getFoodLevel() + hunger <= 0)
	            return false;
        }
        return true;
    }*/
	
}