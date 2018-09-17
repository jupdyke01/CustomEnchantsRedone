package me.jupdyke01.customenchantsredone.enchant.listeners;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.jupdyke01.customenchantsredone.enchant.EnchantManager;
import me.jupdyke01.customenchantsredone.enchant.Enchantment;
import me.jupdyke01.customenchantsredone.enchant.enums.EnchantTrigger;
import me.jupdyke01.customenchantsredone.enchant.utils.EnchantUtils;
import me.jupdyke01.customenchantsredone.enchant.utils.Utils;

public class EnchantListeners implements Listener {

	private EnchantManager em;

	public EnchantListeners(EnchantManager em) {
		this.em = em;
	}

	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent  e) {
		if (!e.isCancelled()) {
			if (!(e.getEntity() instanceof Player))
				return;
			if (!(e.getDamager() instanceof Player))
				return;
			Player attacker = (Player) e.getDamager();
			Player attacked = (Player) e.getEntity();

			ArrayList<ItemStack> checkItemsAttacker = new ArrayList<>();
			checkItemsAttacker.add(attacker.getInventory().getItemInMainHand());
			checkItemsAttacker.add(attacker.getInventory().getHelmet());
			checkItemsAttacker.add(attacker.getInventory().getChestplate());
			checkItemsAttacker.add(attacker.getInventory().getLeggings());
			checkItemsAttacker.add(attacker.getInventory().getBoots());

			ArrayList<ItemStack> checkItemsAttacked = new ArrayList<>();
			checkItemsAttacked.add(attacked.getInventory().getItemInMainHand());
			checkItemsAttacked.add(attacked.getInventory().getHelmet());
			checkItemsAttacked.add(attacked.getInventory().getChestplate());
			checkItemsAttacked.add(attacked.getInventory().getLeggings());
			checkItemsAttacked.add(attacked.getInventory().getBoots());

			int tier = 1;

			for (ItemStack item : checkItemsAttacker) {
				for (Enchantment enchant : em.getEnchantsOnItem(item)) {
					if (enchant.getAffectedItems().contains(item.getType())) {
						if (enchant.getTrigger().equals(EnchantTrigger.onPlayerHit)) {
							for (String str : item.getItemMeta().getLore()) {
								if (str.contains(enchant.getName())) {
									tier = EnchantUtils.getEnchantLevelFromString(str.split(" ")[str.split(" ").length - 1]);
								}
							}

							if (Utils.getRandom(1, (int) 100 / enchant.getChances().get(tier - 1)) == 1) {
								enchant.doResults(tier, attacker, attacked);
							}
						}
					}
				}
			}

			for (ItemStack item : checkItemsAttacked) {
				for (Enchantment enchant : em.getEnchantsOnItem(item)) {
					if (enchant.getAffectedItems().contains(item.getType())) {
						if (enchant.getTrigger().equals(EnchantTrigger.onPlayerHitYou)) {
							for (String str : item.getItemMeta().getLore()) {
								if (str.contains(enchant.getName())) {
									tier = EnchantUtils.getEnchantLevelFromString(str.split(" ")[str.split(" ").length - 1]);
								}
							}
							if (Utils.getRandom(1, (int) 100 / enchant.getChances().get(tier - 1)) == 1) {
								enchant.doResults(tier, attacker, attacked);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() != null) {
			Player killer = e.getEntity().getKiller();
			Player killed = e.getEntity();
			
			
		}
	}
}
