package me.jupdyke01.customenchantsredone.enchant;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jupdyke01.customenchantsredone.enchant.enums.EnchantTrigger;
import me.jupdyke01.customenchantsredone.enchant.enums.EnchantType;
import me.jupdyke01.customenchantsredone.enchant.results.Results;

public class Enchantment {

	private String name;
	private String desc;
	private EnchantType type;
	private int tierMax;
	private EnchantTrigger trigger;
	private ArrayList<Integer> chances;
	private ArrayList<Material> affectedItems;
	
	private HashMap<Integer, ArrayList<String>> results;

	public Enchantment(String name, String desc, EnchantType type, int tierMax, EnchantTrigger trigger, ArrayList<Integer> chances, HashMap<Integer, ArrayList<String>> results, ArrayList<Material> affectedItems) {
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.tierMax = tierMax;
		this.trigger = trigger;
		this.chances = chances;
		this.results = results;
		this.affectedItems = affectedItems;
	}

	public ArrayList<Material> getAffectedItems() {
		return affectedItems;
	}

	public void setAffectedItems(ArrayList<Material> affectedItems) {
		this.affectedItems = affectedItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public EnchantType getType() {
		return type;
	}

	public void setType(EnchantType type) {
		this.type = type;
	}

	public int getTierMax() {
		return tierMax;
	}

	public void setTierMax(int tierMax) {
		this.tierMax = tierMax;
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

	public HashMap<Integer, ArrayList<String>> getResults() {
		return results;
	}

	public void setResults(HashMap<Integer, ArrayList<String>> results) {
		this.results = results;
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
