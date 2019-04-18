package sensores_temp_luz;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class PahoReader extends Thread {
	

	private static final String TOPIC = "iscte_sid_2019_SIDEx2";
	private static final String BROKER = "tcp://iot.eclipse.org:1883";
	private static final String CLIENTID = "SID2";
	private static final MemoryPersistence persistence = new MemoryPersistence();

	private ArrayList<MqttMessage>sms=new ArrayList<MqttMessage>();
	
	public void read() {
		try {

			MqttClient sampleClient = new MqttClient(BROKER, CLIENTID, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + BROKER);

			sampleClient.connect(connOpts);

			System.out.println("PahoRead is Connected");

			sampleClient.setCallback(new MqttCallback() {

				@Override
				public void connectionLost(Throwable cause) {
					// TODO Auto-generated method stub

				}

				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
					sms.add(message);
//					MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
					for(MqttMessage s:sms)	{
						String smsString = String.valueOf(s);
		//				sleep(3000);
						System.out.println("Mensagem: "+smsString);
						String[] stringSplitted = smsString.split(",");
						String[] temp = stringSplitted[0].split(":");
						String tempV = "";
						if (temp[1].length() == 7) {
							tempV = temp[1].substring(1, 6);
							System.out.println("Temperatura: " + tempV);
						}
						else if (temp[1].length() == 6) {
							tempV = temp[1].substring(1, 5);
							System.out.println("Temperatura: " + tempV);
						}
						else if(temp[1].length() == 8)	{
							tempV = temp[1].substring(1, 7);
							System.out.println("Temperatura: " + tempV);
						}
						double temperatura = Double.parseDouble(tempV);
						System.out.println(temperatura);
//
//						String[] date = stringSplitted[2].split(":");
//						String dateV = date[1].substring(1, 9);
//						String[] dateF = dateV.split("/");
//						String dateFF = dateF[2] + "-" + dateF[1] + "-" + dateF[0];
//
//						String timeV = stringSplitted[3].substring(7, 15);
//
//						String cellV = stringSplitted[4].substring(8, 12);
//						double cell = Double.parseDouble(cellV);
//
//						String data = dateFF + " " + timeV;
//						
//						int i;
//						
//						DB db = mongoClient.getDB("Sensores");
//						i=1;
//						DBCollection table = db.getCollection("Medicoes");
//							
//							BasicDBObject document = new BasicDBObject();
//							document.put("_id", i);
//							document.append("Timestamp", data);
//							document.append("Temperatura", temperatura);
//							document.append("Luminosidade", cell);
//							try { table.insert(document);} catch (Exception e) {}
//							i++;
//							try{Thread.sleep(4000);} catch (InterruptedException  e) {Thread.currentThread().interrupt();}
//					
					}
//					mongoClient.close();
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					// TODO Auto-generated method stub

				}

			});

			sampleClient.subscribe(TOPIC, 1);

		} catch (MqttException e) {
			System.out.println("Error in read");
		}

	}

	public ArrayList<MqttMessage> getSMS()	{
		return sms;
	}
	
	public static void main(String[] args) {
		PahoReader reader = new PahoReader();
		reader.read();

		
	}
}
