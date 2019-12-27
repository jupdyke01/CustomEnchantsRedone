package me.jupdyke01.customenchantsredone.enchant;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jupdyke01.customenchantsredone.enchant.enums.EnchantTrigger;
import me.jupdyke01.customenchantsredone.enchant.results.Results;

public class ActiveEnchantment extends Enchantment {

	private EnchantTrigger trigger;
	private ArrayList<Integer> chances;

	public ActiveEnchantment(String name, String desc, int tierMax, EnchantTrigger trigger, ArrayList<Integer> chances, HashMap<Integer, ArrayList<String>> results, ArrayList<Material> affectedItems) {
		this.name = name;
		this.desc = desc;
		this.tierMax = tierMax;
		this.trigger = trigger;
		this.chances = chances;
		this.results = results;
		this.affectedItems = affectedItems;
	}

	public EnchantTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(EnchantTrigger trigger) {
		this.trigger = trigger;
	}

	public ArrayList<Integer> getChances() {
		return chances;
	}

	public void setChances(ArrayList<Integer> chances) {
		this.chances = chances;
	}

	public void doResults(int tier, Player attacker, Player attacked) {
		
		ArrayList<String> resultList = results.get(0);
		for (String str : resultList) {
			
			if (str.contains("sendMessage")) {
				if (str.split("@")[1].equals("attacker")) {
					Results.sendMessage(attacker, str);
				} else if (str.split("@")[1].equals("attacked")) {
					Results.sendMessage(attacked, str);
				}
			} else if (str.contains("applyPotion")) {
				PotionEffectType type = PotionEffectType.getByName(str.split("\\(")[1].split(",")[0]);
				int level = Integer.valueOf(str.split(" ")[1].split(",")[0]);
				int time = Integer.valueOf(str.split(" ")[2].split("\\)")[0]);
				if (str.split("@")[1].equals("attacker")) {
					Results.addPotionEffect(attacker, new PotionEffect(type, time * 20, level));
				} else if (str.split("@")[1].equals("attacked")) {
					Results.addPotionEffect(attacked, new PotionEffect(type, time * 20, level));
				}
			} else if (str.contains("stealXP")) {
				int xp = Integer.valueOf(str.split("\\(")[1].split("\\)")[0]);
				if (str.split("@")[1].equals("attacker")) {
					Results.stealXP(attacked, attacker, xp);
				} else if (str.split("@")[1].equals("attacked")) {
					Results.stealXP(attacker, attacked, xp);
				}
			} else if (str.contains("damage")) {
				Double damage = Double.valueOf(str.split("\\(")[1].split("\\)")[0]) * 1.0;
				if (str.split("@")[1].equals("attacker")) {
					Results.damage(attacker, damage);
				} else if (str.split("@")[1].equals("attacked")) {
					Results.damage(attacked, damage);
				}
			} else if (str.contains("heal")) {
				Double damage = Double.valueOf(str.split("\\(")[1].split("\\)")[0]) * 1.0;
				if (str.split("@")[1].equals("attacker")) {
					Results.heal(attacker, damage);
				} else if (str.split("@")[1].equals("attacked")) {
					Results.heal(attacked, damage);
				}
			} else if (str.contains("stealHealth")) {
				Double amount = Double.valueOf(str.split("\\(")[1].split("\\)")[0]) * 1.0;
				if (str.split("@")[1].equals("attacker")) {
					Results.stealHealth(attacker, attacked, amount);
				} else if (str.split("@")[1].equals("attacked")) {
					Results.stealHealth(attacked, attacker, amount);
				}
			} else if (str.contains("dropHead")) {
				
			}
			
		}
	}

}
