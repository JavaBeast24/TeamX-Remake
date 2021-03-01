package com.JavaBeast.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.JavaBeast.Server.Packages.PingPackage;
import com.JavaBeast.Server.Packages.ServerTypePackage;

public class Host {

	private static ServerSocket serverSocket;

	private static boolean waitForConnections = true;
	private static Thread connectionThread;
	private static Thread pingThread;
	
	public static String serverType;
	
	private static List<Client> clients = new ArrayList<>();

	private static int id = 0;
	
	public static void Open(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		waitForConnections();
		ping();
	}

	private static void waitForConnections() {
		connectionThread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (waitForConnections) {

					try {

						Socket socket = serverSocket.accept();

						Client client = new Client(socket, "noname"+id);
						client.sendPackage(new ServerTypePackage(serverType));
						System.out.println("client connected!");
						id++;
						clients.add(client);

						Thread.sleep(1000);
					} catch (InterruptedException | IOException e) {
					}
				}
			}
		});

		connectionThread.start();
	}

	private static void ping() {
		pingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (waitForConnections) {

					try {
						
						for(Client client:clients) {
							client.sendPackage(new PingPackage());
						}
						
						Thread.sleep(10000);
					} catch (InterruptedException | IOException e) {
					}
				}
			}
		});

		pingThread.start();
	}
	
	public static List<Client> getClients(){
		return clients;
	}
}
