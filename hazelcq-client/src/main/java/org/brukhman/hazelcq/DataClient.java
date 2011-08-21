package org.brukhman.hazelcq;

import java.util.Random;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public final class DataClient {
	
	private final String clusterHost;
	
	public DataClient(String clusterHost) {
		this.clusterHost = clusterHost;
	}
	
	public final void start() {
		HazelcastInstance hazelClient = HazelcastClient.newHazelcastClient("dev", "dev", 
				this.clusterHost);
		System.out.println("Getting map...");
		IMap<Integer, String> map = hazelClient.getMap("data");
		System.out.println("Done.");
		
		while (true) {
			int random = new Random().nextInt(100000);
			long t1 = System.nanoTime();
			String data = map.get(random);
			long t2 = System.nanoTime();
			System.out.println((t2-t1));			
		}
	}
	
	public final static void main(String... args) {
		DataClient dataClient = new DataClient(args[0]);
		dataClient.start();
	}

}
