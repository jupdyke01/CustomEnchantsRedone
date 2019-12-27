package me.jupdyke01.customenchantsredone.enchant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jupdyke01.customenchantsredone.Main;
import me.jupdyke01.customenchantsredone.enchant.enums.EnchantTrigger;
import me.jupdyke01.customenchantsredone.enchant.utils.EnchantUtils;
import me.jupdyke01.customenchantsredone.enchant.utils.Lang;
import net.md_5.bungee.api.ChatColor;

public class EnchantManager {

	private ArrayList<ActiveEnchantment> enchants = new ArrayList<>();

	private Main main;

	public EnchantManager(Main main) {
		this.main = main;
	}

	public int getEnchantLevelOnItem(ItemStack item, ActiveEnchantment enchant) {
		if (item.hasItemMeta())
			if (item.getItemMeta().hasLore())
				for (String loreLine : item.getItemMeta().getLore()) {
					if (getEnchant(loreLine) != null && getEnchant(loreLine).equals(enchant)) {
						return EnchantUtils.getEnchantLevelFromString(loreLine.split(" ")[loreLine.split(" ").length - 1]);
					}
				}

		return 0;
	}

	public void unEnchantItem(ItemStack item, ActiveEnchantment enchant) {
		if (item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			if (item.getItemMeta().hasLore()) {
				List<String> lore = new ArrayList<>();
				List<String> loreRemove = new ArrayList<>();
				lore.addAll(item.getItemMeta().getLore());
				for (String loreLine : lore) {
					if (getEnchant(loreLine) != null && getEnchant(loreLine).equals(enchant)) {
						loreRemove.add(loreLine);
					}
				}
				lore.removeAll(loreRemove);
				meta.setLore(lore);
				item.setItemMeta(meta);
			}
		}
	}

	public ActiveEnchantment getEnchant(String name) {
		for (ActiveEnchantment enchant : enchants) {
			if (name.contains(" ")) {
				if (ChatColor.stripColor(name.substring(0, name.length() - (name.split(" ")[name.split(" ").length - 1].length() + 1))).equals(enchant.getName()))
					return enchant;
			} else {
				if (ChatColor.stripColor(name).equals(enchant.getName()))
					return enchant;
			}
		}
		return null;
	}

	public void enchantItem(ItemStack item, ActiveEnchantment ench, int tier) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (meta.hasLore())
			lore.addAll(meta.getLore());
		lore.add(Lang.enchantColor() + ench.getName() + " " + EnchantUtils.getEnchantLevelFromInt(tier));
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	public ArrayList<ActiveEnchantment> getEnchantsOnItem(ItemStack item) {
		ArrayList<ActiveEnchantment> enchantsOnItem = new ArrayList<>();
		if (item != null) {
			if (item.hasItemMeta()) {
				if (item.getItemMeta().hasLore()) {
					for (String lore : item.getItemMeta().getLore()) {
						ActiveEnchantment enchant = getEnchant(lore.substring(0, lore.length() - (lore.split(" ")[lore.split(" ").length - 1].length() + 1)));
						if (enchant != null) {
							enchantsOnItem.add(enchant);
						}
					}
				}
			}
		}
		return enchantsOnItem;
	}

	public boolean enchantExists(String name) {
		if (new File(main.getDataFolder().getAbsolutePath() + File.separator + "Enchants").exists()) {
			for (File file : new File(main.getDataFolder().getAbsolutePath() + File.separator + "Enchants").listFiles()) { 
				if (file.getName().contains(name)) {
					System.out.println("true");
					return true;
				} else {
					System.out.println("false");
					return false;
				}
			}
			return false;
		}
		return false;
	}

	public void createActiveEnchant(String name, String desc, int tierMax, EnchantTrigger trigger, ArrayList<Integer> chances, HashMap<Integer, ArrayList<String>> results, ArrayList<Material> affectedItems) {
		File file = new File(main.getDataFolder() + File.separator + "Active Enchants", name + ".yml");
		FileConfiguration enchantFile = YamlConfiguration.loadConfiguration(file);
		enchantFile.set("Name", name);
		enchantFile.set("Description", desc);
		enchantFile.set("TierMax", tierMax);
		enchantFile.set("Trigger", trigger.name());
		enchantFile.set("Chances", chances);


		for (int index : results.keySet()) {
			ArrayList<String> resultSets = new ArrayList<>();
			for (String resultLine : results.get(index)) {
				resultSets.add(resultLine);
			}
			enchantFile.set("Results." + index, resultSets);
		}

		ArrayList<String> affectedItemNames = new ArrayList<>();
		for (Material mat : affectedItems) {
			affectedItemNames.add(mat.name());
		}
		enchantFile.set("Items", affectedItemNames);


		try {
			enchantFile.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPassiveEnchant(String name, String desc, int tierMax, HashMap<Integer, ArrayList<String>> results, ArrayList<Material> affectedItems) {
		File file = new File(main.getDataFolder() + File.separator + "Passive Enchants", name + ".yml");
		FileConfiguration enchantFile = YamlConfiguration.loadConfiguration(file);
		enchantFile.set("Name", name);
		enchantFile.set("Description", desc);
		enchantFile.set("TierMax", tierMax);

		for (int index : results.keySet()) {
			ArrayList<String> resultSets = new ArrayList<>();
			for (String resultLine : results.get(index)) {
				resultSets.add(resultLine);
			}
			enchantFile.set("Results." + index, resultSets);
		}

		ArrayList<String> affectedItemNames = new ArrayList<>();
		for (Material mat : affectedItems) {
			affectedItemNames.add(mat.name());
		}
		enchantFile.set("Items", affectedItemNames);


		try {
			enchantFile.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadEnchants() {
		if (main.getDataFolder().exists())
			for (File file : new File(main.getDataFolder().getAbsolutePath() + File.separator + "Enchants").listFiles()) {
				FileConfiguration enchantFile = YamlConfiguration.loadConfiguration(file);

				String name;
				String desc;
				int tierMax;
				EnchantTrigger trigger;
				ArrayList<Integer> chances = new ArrayList<>();
				HashMap<Integer, ArrayList<String>> results = new HashMap<>();
				ArrayList<Material> affectedItem = new ArrayList<>();

				// NAME
				name = enchantFile.getString("Name");
				// DESC
				desc = enchantFile.getString("Description");
				// TIERMAX
				tierMax = enchantFile.getInt("TierMax");
				// TRIGGER
				trigger = EnchantTrigger.valueOf(enchantFile.getString("Trigger"));
				// CHANCES
				for (int chance : enchantFile.getIntegerList("Chances")) {
					chances.add(chance);
				}
				// RESULTS
				HashMap<Integer, ArrayList<String>> tempResults = new HashMap<>();
				int x = 0;
				for (String resultSet : enchantFile.getConfigurationSection("Results").getKeys(false)) {
					ArrayList<String> resultSets = new ArrayList<>();
					for (String resultLine : enchantFile.getStringList("Results." + resultSet)) {
						resultSets.add(resultLine);
					}
					tempResults.put(x, resultSets);
					x++;
				}
				results.putAll(tempResults);
				// Affected Items
				ArrayList<Material> items = new ArrayList<>();
				for (String str : enchantFile.getStringList("Items")) {
					items.add(Material.valueOf(str));
				}
				affectedItem.addAll(items);
				// Adding to enchant list
				enchants.add(new ActiveEnchantment(name, desc, tierMax, trigger, chances, results, affectedItem));
			}
	}

	public Main getMain() {
		return main;
	}

}
