package sensores_temp_luz;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoWrite extends Thread {
	
	
	private ArrayList<MqttMessage> sms;
	private PahoReader pahoReader;
	
	public MongoWrite() {
		pahoReader=new PahoReader();
		pahoReader.read();
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//sms=pahoReader.getSMS();
	}
	
	@SuppressWarnings("deprecation")
	public void write()	{
		
		//Falta ir buscar a mensagem do Paho

		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
		for(MqttMessage message:sms)	{
			
		String smsString = String.valueOf(message);
		System.out.println("Mensagem: "+smsString);
		String[] s = smsString.split(",");
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
		
		DB db = mongoClient.getDB("Sensores");
		i=1;
		DBCollection table = db.getCollection("Medicoes");
			
			BasicDBObject document = new BasicDBObject();
			document.put("_id", i);
			document.append("Timestamp", data);
			document.append("Temperatura", temperatura);
			document.append("Luminosidade", cell);
			try { table.insert(document);} catch (Exception e) {}
			i++;
			try{Thread.sleep(4000);} catch (InterruptedException  e) {Thread.currentThread().interrupt();
			
			}
			}
	

		//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
//		for(MqttMessage message:sms)	{
//			
//		String smsString = String.valueOf(message);
//		System.out.println("Mensagem: "+smsString);
//		String[] s = smsString.split(",");
//		String[] temp = s[0].split(":");
//		//System.out.println(temp[1].length());
//				
//		double temperatura = Double.valueOf(tempV);
//		System.out.println(temperatura);

//		String[] date = s[2].split(":");
//		String dateV = date[1].substring(1, 9);
//		String[] dateF = dateV.split("/");
//		String dateFF = dateF[2] + "-" + dateF[1] + "-" + dateF[0];
//
//		String timeV = s[3].substring(7, 15);
//
//		String cellV = s[4].substring(8, 12);
//		double cell = Double.parseDouble(cellV);
//
//		String data = dateFF + " " + timeV;
//		
//		int i;
		
//		DB db = mongoClient.getDB("Sensores");
//		i=1;
//		DBCollection table = db.getCollection("Medicoes");
//			
//			BasicDBObject document = new BasicDBObject();
//			document.put("_id", i);
//			document.append("Timestamp", data);
//			document.append("Temperatura", temperatura);
//			document.append("Luminosidade", cell);
//			try { table.insert(document);} catch (Exception e) {}
//			i++;
//			try{Thread.sleep(4000);} catch (InterruptedException  e) {Thread.currentThread().interrupt();}
//	

	//	mongoClient.close();	

		
	}

		//mongoClient.close();	

		


	
	@SuppressWarnings("deprecation")
	public void run() {
			
	}
	
	public static void main(String[] args) {
		MongoWrite mW=new MongoWrite();
		mW.write();
	}
}	
