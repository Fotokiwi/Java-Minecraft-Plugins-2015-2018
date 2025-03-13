package org.community.MessageController;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class MessageDeathListener implements Listener {

	@SuppressWarnings("unused")
	private MessageController plugin;

	public MessageDeathListener(MessageController plugin)
	{		
		this.plugin = plugin;
	}


	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		EntityDamageEvent damageEvent = player.getLastDamageCause();

		if(damageEvent instanceof EntityDamageByEntityEvent){
			Entity damager = ((EntityDamageByEntityEvent)damageEvent).getDamager();
			
			if(damager instanceof IronGolem)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(6);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" schmeckte die stählernde Faust eines "+ChatColor.RED+"Eisengolems"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" legte sich erfolglos mit einem "+ChatColor.RED+"Eisengolem "+ChatColor.WHITE+"an!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem "+ChatColor.RED+"Eisengolem "+ChatColor.WHITE+"zerquetscht!");
					break;				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" von einem Bodyguard "+ChatColor.RED+"eisenhart"+ChatColor.WHITE+" erwischt!");
					break;					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" kam unter einen "+ChatColor.RED+"Eisengolem"+ChatColor.WHITE+"!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist jetzt Flach wie eine "+ChatColor.RED+"Briefmarke"+ChatColor.WHITE+"!");
					break;
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat den alten "+ChatColor.RED+"Golem"+ChatColor.WHITE+" unterschätzt!");
					break;
					
				}
			}
			
			else if(damager instanceof Silverfish)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(6);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage("An " + ChatColor.GOLD+player.getName()+ChatColor.WHITE+" knabbern jetzt die "+ChatColor.RED+"Maden"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fand ein jehes Ende durch"+ChatColor.RED+" Silberfische"+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb an wildem "+ChatColor.RED+"Gewusel "+ChatColor.WHITE+"!");
					break;				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" sah es noch kurz "+ChatColor.RED+"silbern "+ChatColor.WHITE+"blitzen und verschied dann leider!");
					break;					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte die "+ChatColor.RED+"Silberfische"+ChatColor.WHITE+" nicht schnell genug zerquetschen!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" unterlag im Kampf gegen "+ChatColor.RED+"Silberfische"+ChatColor.WHITE+"!");
					break;
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hatte zu viele "+ChatColor.RED+"Silberfische"+ChatColor.WHITE+" in seiner Rüstung!");
					break;
					
				}
			}
			
			else if(damager instanceof MagmaCube)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(6);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde zu einem kleinen kokelndem "+ChatColor.RED+"Würfel"+ChatColor.WHITE+" gepresst!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" war das Frühstück eines"+ChatColor.RED+"Magma-Würfels "+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat leider kein "+ChatColor.AQUA+"Wasser "+ChatColor.WHITE+"gewürfelt!");
					break;				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" schmolz unter der Last eines "+ChatColor.RED+"Magma-Würfels"+ChatColor.WHITE+"!");
					break;					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte den "+ChatColor.RED+"Magma-Würfel"+ChatColor.WHITE+" nicht besiegen!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Geschichte endet mit einem "+ChatColor.RED+"Magma-Würfel"+ChatColor.WHITE+"!");
					break;
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte einen"+ChatColor.RED+" Magma-Würfel"+ChatColor.WHITE+" aufspühren!");
					break;
					
				}
			}
			
			else if(damager instanceof Slime)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(6);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde kräftig "+ChatColor.RED+"eingeschleimt"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" erstickte in klebrigen "+ChatColor.RED+"Schleim"+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" unterlag einem "+ChatColor.RED+"Hüpfwürfel "+ChatColor.WHITE+"!");
					break;				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem "+ChatColor.RED+"Slime"+ChatColor.WHITE+" gefressen!");
					break;					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb an einer "+ChatColor.RED+"Schleimvergiftung"+ChatColor.WHITE+"!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wusste nicht, wo der "+ChatColor.RED+"Slime"+ChatColor.WHITE+" seinen Kopf zum abschlagen hatte!");
					break;
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde eingekleistert vom Nektar eines"+ChatColor.RED+" Slimes"+ChatColor.WHITE+"!");
					break;
					
				}
			}
			
			else if(damager instanceof Enderman)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(6);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hatte doch Angst vorm schwarzen "+ChatColor.RED+"Enderman"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde hinterrücks vom "+ChatColor.RED+"Enderman "+ChatColor.WHITE+"erschlagen!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" schaute dem "+ChatColor.RED+"Enderman "+ChatColor.WHITE+"zu tief ins Gesicht!");
					break;				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" sah den "+ChatColor.RED+"Enderman"+ChatColor.WHITE+" nicht mehr kommen!");
					break;					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hielt niemand den Rücken frei von "+ChatColor.RED+"Endermännern"+ChatColor.WHITE+"!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verlor den "+ChatColor.RED+"Tanz des Enderman"+ChatColor.WHITE+"!");
					break;
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb vor Angst beim Anblick eines"+ChatColor.RED+" Endermans"+ChatColor.WHITE+"!");
					break;
					
				}
			}

			else if(damager instanceof Spider && !(damager instanceof CaveSpider))
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(10);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" unterlag einer "+ChatColor.RED+"Spinne"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einer "+ChatColor.RED+"Spinne "+ChatColor.WHITE+"gefressen!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von zu einem "+ChatColor.RED+"Spinnen Kokon "+ChatColor.WHITE+"verarbeitet!");
					break;				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" waren "+ChatColor.RED+"acht Beine "+ChatColor.WHITE+"einfach zu viel!");
					break;					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist jetzt "+ChatColor.RED+"zartes Spinnenfutter"+ChatColor.WHITE+"!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einer "+ChatColor.RED+"Arachnida "+ChatColor.WHITE+" exekutiert!");
					break;
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" tanzte für"+ChatColor.RED+" acht Beine"+ChatColor.WHITE+" nicht gut genug!");
					break;
				case 7 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Tod kam an einem "+ChatColor.RED+" dünnen Faden"+ChatColor.WHITE+" hinab!");
					break;					
				case 8 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" blickte zu letzt in "+ChatColor.RED+"acht"+ChatColor.WHITE+" riesige Augen!");
					break;
				case 9 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" irrte bei der "+ChatColor.RED+"Weberknechts"+ChatColor.WHITE+"-Vermutung!");
					break;
				case 10 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verstrickte sich im"+ChatColor.RED+" Spinnennetz"+ChatColor.WHITE+"!");
					break;
					
				}
			}
            
			else if(damager instanceof CaveSpider)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(5);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" spührt den Kuss der "+ChatColor.RED+"Schwarzen Witwe"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" erlag der lähmenden Wirkung des "+ChatColor.RED+"Spinnengiftes"+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat eine kleine "+ChatColor.RED+"Giftspinne "+ChatColor.WHITE+"einfach übersehen!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" war doch nicht immun gegen "+ChatColor.RED+"Spinnengift"+ChatColor.WHITE+"!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist jetzt Futter für"+ChatColor.RED+" Junggiftspinnen"+ChatColor.WHITE+"!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" marschierte zu tief ins"+ChatColor.RED+" Spinnennetz"+ChatColor.WHITE+"!");
					break;
					
				}
			}
			else if(damager instanceof Skeleton || (damager instanceof Arrow && ((Arrow) damager).getShooter() instanceof Skeleton))
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(8);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" bekam "+ChatColor.RED+"Pfeile "+ChatColor.WHITE+"nicht nur in sein Knie!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem "+ChatColor.RED+"Skelett "+ChatColor.WHITE+"gerichtet!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" sah zu letzt nur einen grinsenden "+ChatColor.RED+"Totenschädel"+ChatColor.WHITE+"!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem gut gezieltem "+ChatColor.RED+"Pfeil "+ChatColor.WHITE+"niedergestreckt!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem "+ChatColor.RED+"Skelett"+ChatColor.WHITE+" erschossen!");
					break;
				
				case 5:
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fand den "+ChatColor.RED+"gefederten Tod"+ChatColor.WHITE+"!");
					break;
					
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" unterschätzte den "+ChatColor.RED+"Knochenkalle"+ChatColor.WHITE+"!");
					break;
					
				case 7 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem "+ChatColor.RED+"Skelett"+ChatColor.WHITE+" mit einer Sense geholt!");
					break;
				
				case 8:
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" reiht sich ein in die Armeen der "+ChatColor.RED+"Skelette"+ChatColor.WHITE+"!");
					break;
					
				}
			}
			
			else if(damager instanceof Zombie && !(damager instanceof PigZombie))
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(11);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist jetzt "+ChatColor.RED+"Zombiefutter"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+"s " + ChatColor.WHITE+" Gebeine gehören nun den "+ChatColor.RED+"Zombies"+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat zu viele "+ChatColor.RED+"Zombies "+ChatColor.WHITE+"von sich kosten lassen!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte den modrigen Atem des "+ChatColor.RED+"Zombies "+ChatColor.WHITE+"nicht ertragen!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" erlag seinen"+ChatColor.RED+" Zombie-"+ChatColor.WHITE+"Bisswunden!");
					break;
				
				case 5:					
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" tanzte seinen letzten Tanz mit den"+ChatColor.RED+" Zombies"+ChatColor.WHITE+" und überlebte nicht!");
					break;
					
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" mag jetzt keine "+ChatColor.RED+"Zombies "+ChatColor.WHITE+"mehr!");
					break;
					
				case 7 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist nun selbst "+ChatColor.RED+" verrottetes "+ChatColor.WHITE+" Fleisch!");
					break;
				
				case 8:					
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s "+ChatColor.RED+" Gehirn"+ChatColor.WHITE+" war saftig und reichhaltig!");
					break;
					
				case 9 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem gammligen "+ChatColor.RED+"Zombie "+ChatColor.WHITE+" getötet!");
					break;
					
				case 10 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" schaut sich mit den "+ChatColor.RED+"Untoten"+ChatColor.WHITE+" fortan die Radieschen von unten an!");
					break;
				
				case 11:					
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" erlag einer"+ChatColor.RED+" Zombiebisswunde"+ChatColor.WHITE+" !");
					break;
				}		
			}
			
			else if(damager instanceof Wolf)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(4);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist jetzt "+ChatColor.RED+"Hundefutter"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" irrte sich bei diesem "+ChatColor.RED+"Welpen"+ChatColor.WHITE+"!");
					break;
					
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" spürte den Biss eines "+ChatColor.RED+"Wolfes"+ChatColor.WHITE+"!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Knochen sind jetzt ein "+ChatColor.RED+"Leckerli"+ChatColor.WHITE+" für die Wölfe!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem"+ChatColor.RED+" Hündchen"+ChatColor.WHITE+" getötet!");
					break;					
				}			
			
			
		}
			
			
			else if(damager instanceof Ghast)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(4);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat einen "+ChatColor.RED+"Geist"+ChatColor.WHITE+" gesehen!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde Opfer der "+ChatColor.RED+"Geisterwelt"+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hüpfte schlechter als "+ChatColor.RED+"Super "+ChatColor.WHITE+"Mario!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verlor das "+ChatColor.RED+"Geister"+ChatColor.WHITE+"-Schützenduell!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hatte zu wenig Furcht vor"+ChatColor.RED+" Gespenstern"+ChatColor.WHITE+"!");
					break;					
				}			
			
			
		}		
			
			else if(damager instanceof Witch || damageEvent.getCause() == DamageCause.MAGIC )
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(6);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ließ sich von einer "+ChatColor.RED+"Hexe"+ChatColor.WHITE+" fortlocken!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" kostete die Tränke einer "+ChatColor.RED+"Hexe"+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" war verzaubert von der Schönheit einer "+ChatColor.RED+"Hexe"+ChatColor.WHITE+"!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" erlag dem hypnotischen "+ChatColor.RED+"Hexenkichern"+ChatColor.WHITE+"!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" Zeigefinger war endlich "+ChatColor.RED+" dick"+ChatColor.WHITE+" genug!");
					break;
					
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" aß den falschen "+ChatColor.RED+" Apfel"+ChatColor.WHITE+"!");
					break;	
					
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde in einen "+ChatColor.RED+" Frosch"+ChatColor.WHITE+" verwandelt!");
					break;	
				}			
			
			
		}		
			else if(damager instanceof Blaze)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(4);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem "+ChatColor.RED+"Feuerdämon"+ChatColor.WHITE+" flambiert!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" "+ChatColor.RED+"brannte"+ChatColor.WHITE+" hell und kurz!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einer "+ChatColor.RED+"Lohe "+ChatColor.WHITE+"überrascht!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb im Duell mit einer "+ChatColor.RED+"Lohe"+ChatColor.WHITE+"!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" brannte durch "+ChatColor.RED+" Lohenfeuer"+ChatColor.WHITE+" bis zum bitteren Ende!");
					break;					
				}			
			
			
		}			
			
			
			else if(damager instanceof PigZombie)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(8);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von einem "+ChatColor.RED+"Ork"+ChatColor.WHITE+" gerissen!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde Opfer eines blutrünstigen "+ChatColor.RED+"Orks"+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verlor den Kampf gegen die "+ChatColor.RED+"Orks"+ChatColor.WHITE+"!");
					break;
				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist schon bald "+ChatColor.RED+"Orklagerdekoration"+ChatColor.WHITE+"!");
					break;
					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wird ein köstliches Mahl für die"+ChatColor.RED+" Orks"+ChatColor.WHITE+"!");
					break;
					
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" kostete zu oft von einer rostigen "+ChatColor.RED+"Orkklinge"+ChatColor.WHITE+"!");
					break;
					
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte sich nicht mehr gegen die"+ChatColor.RED+" Orks"+ChatColor.WHITE+" wehren!");
					break;
					
				case 7 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb heldenhaft im Kampf gegen die "+ChatColor.RED+"Orks"+ChatColor.WHITE+"!");
					break;
					
				case 8 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fand ein episches Ende durch die Hauer eines"+ChatColor.RED+" Orks"+ChatColor.WHITE+"!");
					break;
					
				}			
			}
			else if(damageEvent.getCause() == DamageCause.ENTITY_EXPLOSION || damager instanceof Creeper ) //DamageCause. liefert alle Schadensarten :-)
			{
				Random rnd = new Random();
				int chance = rnd.nextInt(7);
				
				switch (chance)
				{
				case 0 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" now rests in "+ChatColor.RED+"pieces"+ChatColor.WHITE+"!");
					break;
				case 1 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde zu einer "+ChatColor.RED +"rosa Wolke "+ChatColor.WHITE+"!");
					break;
				case 2 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verschwand mit einem lautem "+ChatColor.RED+"Knall"+ChatColor.WHITE+"!");
					break;				
				case 3 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verteilt sich "+ChatColor.RED+"überall "+ChatColor.WHITE+"in der Landschaft!");
					break;					
				case 4 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde Opfer einer "+ChatColor.RED+"Explosion"+ChatColor.WHITE+"!");
					break;
				case 5 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb in einer "+ChatColor.RED+"Explosion"+ChatColor.WHITE+"!");
					break;						
				case 6 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fand ein "+ChatColor.RED+"lautes"+ChatColor.WHITE+" Ende!");
					break;
				case 7 :
					event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde "+ChatColor.RED+"vaporisiert"+ChatColor.WHITE+"!");
					break;	
				}
				
			}
		}
		else if(damageEvent.getCause() == DamageCause.ENTITY_EXPLOSION || damageEvent.getCause() == DamageCause.BLOCK_EXPLOSION ) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(5);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" now rests in "+ChatColor.RED+"pieces"+ChatColor.WHITE+"!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde zu einer "+ChatColor.RED +"rosa Wolke "+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verschwand mit einem lautem "+ChatColor.RED+"Knall"+ChatColor.WHITE+"!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verteilt sich "+ChatColor.RED+"überall "+ChatColor.WHITE+"in der Landschaft!");
				break;					
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde Opfer einer "+ChatColor.RED+"Explosion"+ChatColor.WHITE+"!");
				break;
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb in einer "+ChatColor.RED+"Explosion"+ChatColor.WHITE+"!");
				break;			
			}
			
		}
		else if(damageEvent.getCause() == DamageCause.DROWNING) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(5);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" schwimmt jetzt mit den "+ChatColor.RED+"Fischen"+ChatColor.WHITE+"!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fand ein nasses "+ChatColor.RED +"Grab"+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verstarb an "+ChatColor.RED+"Atemnot"+ChatColor.WHITE+"!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte nicht so gut "+ChatColor.RED+"tauchen "+ChatColor.WHITE+", wie er dachte!");
				break;					
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" "+ChatColor.RED+"ertrank"+ChatColor.WHITE+" jämmerlich!");
				break;
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s letzte Worte waren: "+ChatColor.RED+"Blubb blubb blubb"+ChatColor.WHITE+" ...");
				break;			
			}

		}
			
			
		else if(damageEvent.getCause() == DamageCause.LAVA) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(8);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" war nicht "+ChatColor.RED+"lavafest"+ChatColor.WHITE+"!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb den "+ChatColor.RED +"Magmatot"+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verdampfte in "+ChatColor.RED+"Lava"+ChatColor.WHITE+"!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte nicht auf "+ChatColor.RED+"Lava"+ChatColor.WHITE+" wandeln!");
				break;
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist jetzt "+ChatColor.RED+"Steinkohle"+ChatColor.WHITE+"!");
				break;				
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" entdeckte "+ChatColor.RED+"Lava"+ChatColor.WHITE+" und war nicht vorsichtig genug!");
				break;
			case 6 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte nicht in "+ChatColor.RED+"Lava"+ChatColor.WHITE+" schwimmen!");
				break;				
			case 7 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist auf jeden Fall "+ChatColor.RED+"gar"+ChatColor.WHITE+"!");
				break;
			case 8 :
				event.setDeathMessage("Der Genuss von " + ChatColor.GOLD+player.getName()+ChatColor.WHITE+" erhöht nun das "+ChatColor.RED+"Krebsrisiko"+ChatColor.WHITE+" immens!");
				break;
					
			}

		}	
		
		else if(damageEvent.getCause() == DamageCause.STARVATION) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(7);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist "+ChatColor.RED+"verhungert"+ChatColor.WHITE+"!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hatte nichts mehr zu "+ChatColor.RED +"beißen"+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" vernachlässigte seine "+ChatColor.RED+"Ernährung"+ChatColor.WHITE+"!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" arbeitete nur, aber vergaß dabei das"+ChatColor.RED+" Essen"+ChatColor.WHITE+"!");
				break;
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist nun Veganer der "+ChatColor.RED+" 10."+ChatColor.WHITE+" Stufe!");
				break;
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb den Tod der "+ChatColor.RED+"Armen"+ChatColor.WHITE+"!");
				break;				
			case 6 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist jetzt ein heißes"+ChatColor.RED+" Supermodel"+ChatColor.WHITE+"!");
				break;
			case 7 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat den Löffel abgegeben! ");
				break;	
					
			}

		}
		
		else if(damageEvent.getCause() == DamageCause.PROJECTILE) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(6);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Stirn ziert jetzt ein "+ChatColor.RED+"Pfeilschaft"+ChatColor.WHITE+"!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte die Pfeile doch nicht"+ChatColor.RED +" fangen"+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" entschied sich fortan als "+ChatColor.RED+"Igel"+ChatColor.WHITE+" zu leben!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde "+ChatColor.RED+"erschossen"+ChatColor.WHITE+"!");
				break;
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb an einer "+ChatColor.RED+"Pfeilwunde"+ChatColor.WHITE+"!");
				break;
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" zog einen Pfeil aus der Wunde und "+ChatColor.RED+"verblutete"+ChatColor.WHITE+"!");
				break;
			case 6 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Rüstung hielt den "+ChatColor.RED+"Pfeilen"+ChatColor.WHITE+" nicht länger stand!");
				break;	
					
			}
			
			
		}	
		else if(damageEvent.getCause() == DamageCause.LIGHTNING) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(3);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde vom "+ChatColor.RED+"Blitz"+ChatColor.WHITE+" erschlagen!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde von den "+ChatColor.RED +"Göttern"+ChatColor.WHITE+" bestraft!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist doch nicht "+ChatColor.RED+"Benjamin Franklin"+ChatColor.WHITE+"!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" entdeckte die "+ChatColor.RED+"Elektrizität"+ChatColor.WHITE+"!");
				break;					
					
			}

		}
		else if(damageEvent.getCause() == DamageCause.FALL) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(10);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist leider in großer "+ChatColor.RED+"Höhe"+ChatColor.WHITE+" abgerutscht!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte doch nicht "+ChatColor.RED +"fliegen"+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" stürtzte in den "+ChatColor.RED+"Tod"+ChatColor.WHITE+"!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" segelte wie ein "+ChatColor.RED+"Stein"+ChatColor.WHITE+"!");
				break;
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" trat in die "+ChatColor.RED +"Luft"+ChatColor.WHITE+"!");
				break;
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" entdeckte die "+ChatColor.RED+"Gravitation"+ChatColor.WHITE+"!");
				break;				
			case 6 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fiel "+ChatColor.RED+"tiefer"+ChatColor.WHITE+" als er dachte!");
				break;
			case 7 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Hochmut kam vor dem "+ChatColor.RED+"Fall"+ChatColor.WHITE+"!");
				break;
			case 8 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" übersah das große "+ChatColor.RED +"Loch"+ChatColor.WHITE+" und stürzte zu tode!");
				break;
			case 9 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wedelte noch mit den Armen, "+ChatColor.RED+"starb"+ChatColor.WHITE+" aber dennoch!");
				break;				
			case 10 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" versank im "+ChatColor.RED+"Boden"+ChatColor.WHITE+"!");
				break;	
					
			}

		}
		else if(damageEvent.getCause() == DamageCause.WITHER) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(6);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist einem bösen "+ChatColor.RED+"Fluch"+ChatColor.WHITE+" zum Opfer gefallen!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte ohne seinen "+ChatColor.RED +"Lebensbalken"+ChatColor.WHITE+" nicht sein!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ging ins "+ChatColor.YELLOW+"Licht"+ChatColor.WHITE+" und kam nicht mehr zurück!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" unterlag "+ChatColor.BLACK+"dunklen Mächten"+ChatColor.WHITE+"!");
				break;
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Tot wurde durch den Zauber eines "+ChatColor.RED +"Withers"+ChatColor.WHITE+" begünstigt!");
				break;
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" verstarb im Kampf gegen "+ChatColor.RED+"dunkle"+ChatColor.WHITE+" Kräfte!");
				break;				
			case 6 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+"s Seele gehört jetzt der "+ChatColor.DARK_RED+"dunklen Legion"+ChatColor.WHITE+"!");
				break;
	
					
			}

		}
		else if(damageEvent.getCause() == DamageCause.FIRE || damageEvent.getCause()==DamageCause.FIRE_TICK) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(10);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" "+ChatColor.RED+"brannte"+ChatColor.WHITE+" lichter loh!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" starb an seinen "+ChatColor.RED +"Verbrennungen"+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte dem "+ChatColor.RED+"Feuer"+ChatColor.WHITE+" nicht mehr trotzen!");
				break;				
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde zu einem Häufchen "+ChatColor.RED+"Asche"+ChatColor.WHITE+"!");
				break;
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde "+ChatColor.RED +"Feuerbestattet"+ChatColor.WHITE+"!");
				break;
			case 5 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fand ein "+ChatColor.RED+"hitziges"+ChatColor.WHITE+" Ende!");
				break;				
			case 6 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" war "+ChatColor.RED+"Feuer und Flamme"+ChatColor.WHITE+" für seine Situation!");
				break;
			case 7 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat den "+ChatColor.RED+"Garpunkt"+ChatColor.WHITE+" erreicht!");
				break;
			case 8 :
				event.setDeathMessage("Zu viel Eiweis von " + ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist nun"+ChatColor.RED +" denaturiert"+ChatColor.WHITE+"!");
				break;
			case 9 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" stand zu lang in"+ChatColor.RED+" Flammen"+ChatColor.WHITE+"!");
				break;				
			case 10 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist dem heißen "+ChatColor.RED+"Element"+ChatColor.WHITE+" zum Opfer gefallen!");
				break;	
					
			}

		}
		else if(damageEvent.getCause() == DamageCause.FALLING_BLOCK) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(2);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" wurde "+ChatColor.RED+"zerquetscht"+ChatColor.WHITE+"!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" fiel etwas sehr "+ChatColor.RED +"schweres"+ChatColor.WHITE+" auf den Kopf!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" hat etwas auf den Kopf bekommen und "+ChatColor.RED+"verstarb"+ChatColor.WHITE+"!");
				break;				
			}

		}	
		else if(damageEvent.getCause() == DamageCause.SUICIDE) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(4);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte das "+ChatColor.RED+"Elend"+ChatColor.WHITE+" dieser Welt nicht mehr ertragen!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" nahm die "+ChatColor.RED +"Feiglingsausfahrt"+ChatColor.WHITE+"!");
				break;
			case 2 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" beging "+ChatColor.RED+"Selbstmord"+ChatColor.WHITE+"!");
				break;
			case 3 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" entschied sich für die "+ChatColor.BLACK +"dunkle"+ChatColor.WHITE+" Seite!");
				break;
			case 4 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" legte sich zum "+ChatColor.RED+"sterben"+ChatColor.WHITE+" nieder!");
				break;	
			}

		}		
		else if(damageEvent.getCause() == DamageCause.SUFFOCATION) //DamageCause. liefert alle Schadensarten :-)
		{
			Random rnd = new Random();
			int chance = rnd.nextInt(1);
			
			switch (chance)
			{
			case 0 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" konnte dem "+ChatColor.RED+"Druck"+ChatColor.WHITE+" nicht mehr standhalten!");
				break;
			case 1 :
				event.setDeathMessage(ChatColor.GOLD+player.getName()+ChatColor.WHITE+" ist die "+ChatColor.RED +"Decke"+ChatColor.WHITE+" auf den Kopf gefallen!");
				break;
			}

		}
	}
}