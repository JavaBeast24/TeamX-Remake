package com.JavaBeast.Server.SpigotNetwork;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.JavaBeast.Server.Host;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		try {
			Host.Open(1997);
			Host.serverType = "SpigotNetwork";

			//register Packages
			com.JavaBeast.Package.registerPackage("clientType", new com.JavaBeast.Package() {

				@Override
				public void handle() {
					if(args.length == 1) {
						client.setType(args[0]);
					}
				}

				@Override
				public void pack() {
				}
				
			});
			
		}catch(IOException ex) {
			System.out.println("error while creating server : "+ex.getMessage());
		}
	}
	
}
