package me.jupdyke01.customenchantsredone.enchant.results;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

public class Results {

	public static void sendMessage(Player p, String str) {
		p.sendMessage(str.split("\\(")[1].split("\\)")[0]);
	}

	public static void addPotionEffect(Player p, PotionEffect pe) {
		p.addPotionEffect(pe);
	}
	
	public static void stealXP(Player stealer, Player victim, int xp) {
		if (victim.getTotalExperience() >= xp) {
			victim.setTotalExperience(victim.getTotalExperience() - xp);
			stealer.setTotalExperience(stealer.getTotalExperience() + xp);
		} else {
			stealer.setTotalExperience(stealer.getTotalExperience() + victim.getTotalExperience());
			victim.setTotalExperience(0);
		}
	}
	
	public static void stealHealth(Player stealer, Player victim, Double amount) {
		Double firstHealth = victim.getHealth();
		victim.damage(amount);
		Double secondHealth = victim.getHealth();
		stealer.setHealth(stealer.getHealth() + (firstHealth - secondHealth));
	}
	
	public static void damage(Player p, Double damage) {
		p.damage(damage);
	}
	
	public static void heal(Player p, Double health) {
		p.setHealth(p.getHealth() + health);
	}
	
	public static void dropHead(Player p, Location location) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwningPlayer(p);
		skull.setItemMeta(meta);
		location.getWorld().dropItem(location, skull);
	}
	
	
}
