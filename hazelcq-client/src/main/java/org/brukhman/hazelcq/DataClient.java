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
			int random = new Random().nextInt(500000);
			String data = map.get(random);
			System.out.println("Got " + data);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public final static void main(String... args) {
		DataClient dataClient = new DataClient(args[0]);
		dataClient.start();
	}

}
