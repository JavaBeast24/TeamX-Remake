package com.JavaBeast.Client.Normal;

import java.io.IOException;
import java.util.Scanner;

import com.JavaBeast.Package;
import com.JavaBeast.Client.Connection;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("ip:");
		String ip = scanner.nextLine();

		System.out.println("port:");
		int port = Integer.parseInt(scanner.nextLine());

		scanner.close();
		
		try {
			Connection.Connect(ip, port);
			
			
			Package typePack = new Package("clientType normal") {

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
	}

}
