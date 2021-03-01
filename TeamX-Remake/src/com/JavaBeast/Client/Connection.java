package com.JavaBeast.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import com.JavaBeast.Package;

public class Connection {

	private static Socket socket;

	private static Thread listenerThread;
	private static boolean listen = true;

	public static void Connect(String ip, int port) throws UnknownHostException, IOException {
		socket = new Socket(ip, port);
		listenForMessage();
	}

	private static void listenForMessage() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (listen) {

					try {

						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

						String message = dataInputStream.readUTF();

						System.out.println(message);
						
						String[] data = message.split(" ");

						com.JavaBeast.Package pack = Package.fromString(data[0],
								Arrays.copyOfRange(data, 1, data.length));
						if (pack != null) {
							pack.handle();
						}

						Thread.sleep(1000);

					} catch (Exception e) {

						System.out.println("Error while receiving message : " + e.getMessage());
						return;
					}

				}

			}
		});

		thread.start();
		listenerThread = thread;
	}

	public static void sendPackage(Package pack) throws IOException {
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(pack.getMessage());
		outputStream.flush();
	}

	public static void disconnect() throws IOException {
		listen = false;
		listenerThread.interrupt();

		socket.close();
	}

}
