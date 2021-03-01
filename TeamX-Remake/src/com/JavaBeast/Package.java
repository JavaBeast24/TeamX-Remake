package com.JavaBeast;

import java.util.Arrays;
import java.util.HashMap;

import com.JavaBeast.Server.Client;

public abstract class Package {

	private static HashMap<String, Package> packages  = new HashMap<>();
	
	private final String message;

	private String type;
	protected String[] args;
	
	public Client client;
	
	public Package(String message) {
		this.message = message;
		String[] data = message.split(" ");
		this.args = Arrays.copyOfRange(data,  1, data.length);
		this.type = data[0];
	}
	
	public Package() {
		this.message = "";
	}
	

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}
	
	public String[] getArgs() {
		return args;
	}
	
	public abstract void handle();
	
	public abstract void pack();
	
	public static Package fromString(String string, String[] args) {
		if(packages.containsKey(string.split(" ")[0])) {
			Package pack = packages.get(string.split(" ")[0]);
			pack.args = args;
			pack.type = string;
			pack.pack();
			return pack;
		}else return null;
	}
	
	public static void registerPackage(String upCode,Package pack) {
		packages.put(upCode, pack);
	}
	
}
