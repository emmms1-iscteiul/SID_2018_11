package sensores_temp_luz;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoWrite extends Thread {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int i;
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
		DB db = mongoClient.getDB("db_demo");
		i=1;
		DBCollection table = db.getCollection("collection_demo");
		while(i<6) {
			
			BasicDBObject document = new BasicDBObject();
			document.put("NomeVariavel", i);
			try { table.insert(document);} catch (Exception e) {}
			i++;
			try{Thread.sleep(2000);} catch (InterruptedException  e) {Thread.currentThread().interrupt();}
		}
		mongoClient.close();	
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
			
	}
}	
