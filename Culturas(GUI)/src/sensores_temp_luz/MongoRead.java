package sensores_temp_luz;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoRead extends Thread {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

		DB db = mongoClient.getDB("Sensores");
		DBCollection table = db.getCollection("Medicoes");
		DBCursor cursor = table.find();
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		mongoClient.close();
	}

	@SuppressWarnings("deprecation")
	public void run() {

		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
