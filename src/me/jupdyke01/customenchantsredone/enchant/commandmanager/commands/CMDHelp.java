package me.jupdyke01.customenchantsredone.enchant.commandmanager.commands;

import org.bukkit.entity.Player;

import me.jupdyke01.customenchantsredone.Main;
import me.jupdyke01.customenchantsredone.enchant.commandmanager.SubCommand;
import net.md_5.bungee.api.ChatColor;

public class CMDHelp extends SubCommand {

	public CMDHelp(Main main) {
		super(main);
	}

	@Override
	public void onCommand(Player p, String[] args) {
		for (SubCommand command : main.getCommandManager().getCommands()) {
			p.sendMessage(ChatColor.RED + "/ce " +  command.name() + " " + ChatColor.DARK_AQUA + command.info());
		}
	}

	@Override
	public String name() {
		return "help";
	}

	@Override
	public String info() {
		return "";
	}

	@Override
	public String[] aliases() {
		return new String[0];
	}

	
	
}
