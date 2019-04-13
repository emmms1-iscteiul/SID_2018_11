package sensores_temp_luz;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoWrite   {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int i;
//		ServerAddress mongotest0 = new ServerAddress("127.0.0.1", 27017);
//		ServerAddress mongotest1 = new ServerAddress("127.0.0.1", 25017);
//		ServerAddress mongotest2 = new ServerAddress("127.0.0.1", 23017);
		MongoClient mongoClient1 = new MongoClient( 
		   //new MongoClientURI("mongodb://TOSHIBA:27017,TOSHIBA:25017,TOSHIBA:23017/?replicaSet=replicaDemo&autoConnectRetry=true"));
				new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicademo"));
		   //Arrays.asList(mongotest0,mongotest1,mongotest2));
		DB db = mongoClient1.getDB("db_demo");
		i=1;
		DBCollection table = db.getCollection("collection_demo");
		while(i<6) {
			
			BasicDBObject document = new BasicDBObject();
			document.put("Nome",i);
			try { table.insert(document);} catch (Exception e) {}
			i++;
			try{Thread.sleep(5000);} catch (InterruptedException  e) {Thread.currentThread().interrupt();}
		}
		mongoClient1.close();		
	}
}	
