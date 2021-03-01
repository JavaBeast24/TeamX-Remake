package com.JavaBeast.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import com.JavaBeast.Package;

public class Client {

	private final Socket socket;

	private boolean receive = true;
	private Thread receiverThread;
	private String name;
	private String type;
	
	private final Client instance = this;
	
	public Client(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
		receive();
	}

	public void sendPackage(com.JavaBeast.Package pack) throws IOException {
		DataOutputStream dataOutputSteam = new DataOutputStream(socket.getOutputStream());
		dataOutputSteam.writeUTF(pack.getMessage());
		dataOutputSteam.flush();
	}

	private void receive() {
		receiverThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (receive) {

					try {
			
						
						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
						String msg = dataInputStream.readUTF()+" "+getName();
						String[] data = msg.split(" ");
						
						
						System.out.println(msg);
						
						
						com.JavaBeast.Package pack = Package.fromString(data[0],
								Arrays.copyOfRange(data, 1, data.length));
						if (pack != null) {
							pack.client = instance;
							pack.handle();
						}
						
						Thread.sleep(1000);
					} catch (InterruptedException | IOException e) {
					}
				}
			}
		});

		receiverThread.start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
