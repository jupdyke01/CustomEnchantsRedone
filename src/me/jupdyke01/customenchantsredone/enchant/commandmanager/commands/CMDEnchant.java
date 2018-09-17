package me.jupdyke01.customenchantsredone.enchant.commandmanager.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.jupdyke01.customenchantsredone.Main;
import me.jupdyke01.customenchantsredone.enchant.commandmanager.SubCommand;
import me.jupdyke01.customenchantsredone.enchant.utils.EnchantUtils;

public class CMDEnchant extends SubCommand {

	public CMDEnchant(Main main) {
		super(main);
	}

	@Override
	public void onCommand(Player p, String[] args) {
		if (p.hasPermission("cer.enchant")) {
			if (args.length == 3) {
				if (main.getEnchantManager().getEnchant(args[1]) != null) {
					if (EnchantUtils.getEnchantLevelFromString(args[2]) != 0) {
						if (main.getEnchantManager().getEnchant(args[1]).getTierMax() >= EnchantUtils.getEnchantLevelFromString(args[2])) {
							if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
								if (main.getEnchantManager().getEnchant(args[1]).getAffectedItems().contains(p.getInventory().getItemInMainHand().getType())) {
									if (!main.getEnchantManager().getEnchantsOnItem(p.getInventory().getItemInMainHand()).contains(main.getEnchantManager().getEnchant(args[1]))) {
										main.getEnchantManager().enchantItem(p.getInventory().getItemInMainHand(), main.getEnchantManager().getEnchant(args[1]), EnchantUtils.getEnchantLevelFromString(args[2]));
										System.out.println("enchanted");
										return;
									} else {
										if (main.getEnchantManager().getEnchantLevelOnItem(p.getInventory().getItemInMainHand(), main.getEnchantManager().getEnchant(args[1])) != EnchantUtils.getEnchantLevelFromString(args[2])) {
											main.getEnchantManager().unEnchantItem(p.getInventory().getItemInMainHand(), main.getEnchantManager().getEnchant(args[1]));
											main.getEnchantManager().enchantItem(p.getInventory().getItemInMainHand(), main.getEnchantManager().getEnchant(args[1]), EnchantUtils.getEnchantLevelFromString(args[2]));
											return;
										}
										System.out.println("Enchant already there");
										// Already contains enchant
										return;
									}
								} else {
									System.out.println("can't go on item");
									// Enchant can not go on item
									return;
								}
							} else {
								System.out.println("can't enchant air");
								// Can not enchant air
								return;
							}
						} else {
							System.out.println("level too high");
							// Level Too High
							return;
						}
					} else {
						System.out.println("invalid level");
						// Invalid Level
						return;
					}
				} else {
					System.out.println("invalid enchant");
					// Invalid Enchant
					return;
				}
			} else {
				System.out.println("invalid syntax");
				//Invalid Syntax
				return;
			}
		}
	}

	@Override
	public String name() {
		return "enchant";
	}

	@Override
	public String info() {
		return "<enchant> <tier>";
	}

	@Override
	public String[] aliases() {
		return new String[0];
	}

}
