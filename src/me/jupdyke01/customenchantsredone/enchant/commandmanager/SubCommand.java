package me.jupdyke01.customenchantsredone.enchant.commandmanager;

import org.bukkit.entity.Player;

import me.jupdyke01.customenchantsredone.Main;

public abstract class SubCommand {

	public Main main;
	
    public SubCommand(Main main) {
    	this.main = main;
    }

    public abstract void onCommand(Player player, String[] args);

    public abstract String name();

    public abstract String info();

    public abstract String[] aliases();
}