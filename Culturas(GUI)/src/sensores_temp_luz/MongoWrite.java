package sensores_temp_luz;

import java.sql.Date;
import java.sql.Timestamp;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoWrite extends Thread {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		

		String[] s = content.split(",");
		String[] temp = s[0].split(":");
		String tempV = temp[1].substring(1, 6);
		double temperatura = Double.parseDouble(tempV);
		System.out.println(temperatura);

		String[] date = s[2].split(":");
		String dateV = date[1].substring(1, 9);
		String[] dateF = dateV.split("/");
		String dateFF = dateF[2] + "-" + dateF[1] + "-" + dateF[0];

		String timeV = s[3].substring(7, 15);

		String cellV = s[4].substring(8, 12);
		double cell = Double.parseDouble(cellV);

		String data = dateFF + " " + timeV;
		
		int i;
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
		DB db = mongoClient.getDB("Sensores");
		i=1;
		DBCollection table = db.getCollection("Medicoes");
		while(true) {
			
			BasicDBObject document = new BasicDBObject();
			document.put("_id", i);
			document.append("Timestamp", data);
			document.append("Temperatura", temperatura);
			document.append("Luminosidade", cell);
			try { table.insert(document);} catch (Exception e) {}
			i++;
			try{Thread.sleep(4000);} catch (InterruptedException  e) {Thread.currentThread().interrupt();}
		}
		mongoClient.close();	
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
			
	}
}	
