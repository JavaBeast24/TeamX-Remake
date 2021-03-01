package com.JavaBeast.Client.Packages;

import org.bukkit.entity.Player;

public class PlayerJoinPackage extends com.JavaBeast.Package {

	public PlayerJoinPackage(Player player) {
		super("playerJoin "+player.getName());
	}
	
	@Override
	public void handle() {
	}

	@Override
	public void pack() {
	}

}
