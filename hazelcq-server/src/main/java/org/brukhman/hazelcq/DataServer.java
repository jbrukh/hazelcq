package org.brukhman.hazelcq;

import java.util.UUID;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;

public final class DataServer {

	private IMap<Integer, String> map;
	private final static int MAX = 100000;
	
	public final void start() {
		map = Hazelcast.getMap("data");
		System.out.println("populating map...");
		
		long t1 = System.nanoTime();
		populate();
		long t2 = System.nanoTime();
		
		System.out.println("done." + (t2-t1)/1000000 + "ms");
	}
	
	private final void populate() {
		for (int i = 0; i < MAX; i++) {
			map.put(i, UUID.randomUUID().toString());
		}
	}
	
	public final static void main(String... args) {
		DataServer dataServer = new DataServer();
		dataServer.start();
	}
	
}
