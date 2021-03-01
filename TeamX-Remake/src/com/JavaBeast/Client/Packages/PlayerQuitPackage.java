package com.JavaBeast.Client.Packages;

import org.bukkit.entity.Player;

public class PlayerQuitPackage extends com.JavaBeast.Package {

	public PlayerQuitPackage(Player player) {
		super("playerQuit "+player.getName());
	}
	
	@Override
	public void handle() {
	}

	@Override
	public void pack() {
	}

}
