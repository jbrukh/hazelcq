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
		long t1 = System.nanoTime();
		IMap<Integer, String> map = hazelClient.getMap("data");
		long t2 = System.nanoTime();

		System.out.println("Done in " + (t2-t1)/1000000 + "ms");
		int count = 0;
		while (true) {
			int random = new Random().nextInt(500);
			t1 = System.nanoTime();
			String data = map.get(count%500);
			t2 = System.nanoTime();
			System.out.println(++count +", " + (t2-t1));	
			if ( count > 1000 ) {
				break;
			}
		}
	}
	
	public final static void main(String... args) {
		DataClient dataClient = new DataClient(args[0]);
		dataClient.start();
	}

}
