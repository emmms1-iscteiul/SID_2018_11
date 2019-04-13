package sensores_temp_luz;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoRead extends Thread {

	public static void main(String[] args) {

	}

	@SuppressWarnings("deprecation")
	public void run() {

		try {
			MongoClient mongoClient = new MongoClient( new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicademo"));

			DB db = mongoClient.getDB("db_demo");
			DBCollection table = db.getCollection("collection_demo");
			DBCursor cursor = table.find();
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
