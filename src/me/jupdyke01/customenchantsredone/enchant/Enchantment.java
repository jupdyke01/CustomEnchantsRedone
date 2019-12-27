package me.jupdyke01.customenchantsredone.enchant;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;

public abstract class Enchantment {
	String name;
	String desc;
	int tierMax;
	ArrayList<Material> affectedItems;
	HashMap<Integer, ArrayList<String>> results;

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
	
	public int getTierMax() {
		return tierMax;
	}

	public void setTierMax(int tierMax) {
		this.tierMax = tierMax;
	}
	
	public ArrayList<Material> getAffectedItems() {
		return affectedItems;
	}

	public void setAffectedItems(ArrayList<Material> affectedItems) {
		this.affectedItems = affectedItems;
	}
	
	public HashMap<Integer, ArrayList<String>> getResults() {
		return results;
	}

	public void setResults(HashMap<Integer, ArrayList<String>> results) {
		this.results = results;
	}
}
