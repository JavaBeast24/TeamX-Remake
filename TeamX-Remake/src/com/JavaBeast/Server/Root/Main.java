package com.JavaBeast.Server.Root;

import java.io.IOException;
import com.JavaBeast.Server.Host;

public class Main {

	public static void main(String[] args) throws IOException {
		Host.Open(1997);
		Host.serverType = "Root";
		
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
	}
	
}
