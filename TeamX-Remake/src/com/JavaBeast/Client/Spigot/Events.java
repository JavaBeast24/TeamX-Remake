package com.JavaBeast.Client.Spigot;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.JavaBeast.Client.Connection;
import com.JavaBeast.Client.Packages.PlayerJoinPackage;
import com.JavaBeast.Client.Packages.PlayerQuitPackage;

public class Events implements Listener{

	//TODO: Listen for events to send to the server
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		try { Connection.sendPackage(new PlayerJoinPackage(event.getPlayer())); } catch (IOException e) { }
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		try { Connection.sendPackage(new PlayerQuitPackage(event.getPlayer())); } catch (IOException e) { }
	}
	
}
