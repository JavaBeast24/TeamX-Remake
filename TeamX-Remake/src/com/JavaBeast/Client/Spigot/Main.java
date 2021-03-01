package com.JavaBeast.Client.Spigot;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.JavaBeast.Package;
import com.JavaBeast.Client.Connection;

public class Main extends JavaPlugin {

	private final String ip = "localhost";
	private final int port = 1997;
	
	public void onEnable() {
		
		//TODO: read ip and port for TeamXServer from config.yml
		
		try {
			Connection.Connect(ip, port);
			
			
			Package typePack = new Package("clientType spigot") {

				@Override
				public void handle() {
				}

				@Override
				public void pack() {
				}
				
			};			
			Connection.sendPackage(typePack);
			
			
			} catch (IOException IOEx) {
			System.out.println("Error while Connecting : " + IOEx.getMessage());
		}
		
		//TODO:register packages
		
		//ping package
		Package myPack = new Package() {
			
			@Override
			public void handle() {
				Package pong = new Package("pong") {
					
					@Override
					public void pack() {
					}
					
					@Override
					public void handle() {
					}
				};
				
				
				try {
					Connection.sendPackage(pong);
				} catch (IOException e) {
				}
			}

			@Override
			public void pack() {
			}
		};
		Package.registerPackage("ping", myPack);
		
		Bukkit.getPluginManager().registerEvents(new Events(), this);
	}
	
}
