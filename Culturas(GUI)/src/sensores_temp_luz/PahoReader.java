package sensores_temp_luz;

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
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;

public class PahoReader extends Thread {

	private static final String TOPIC = "iscte_sid_2019_SIDEx2";
	private static final String BROKER = "tcp://iot.eclipse.org:1883";
	private static final String CLIENTID = "SID2";
	private static final MemoryPersistence persistence = new MemoryPersistence();
	private boolean exported = false; //


	public void run() {
		read();
	}

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

				@SuppressWarnings("deprecation")
				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {

					sleep(3000);
					MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
					
					String smsString = String.valueOf(message);
					
					System.out.println("Mensagem: "+smsString);
					String[] stringSplitted = smsString.split(",");
					String[] temp = stringSplitted[0].split(":");

					String tempV = "";
					if (temp[1].length() == 7) {
						tempV = temp[1].substring(1, 6);
					}
					else if (temp[1].length() == 6) {
						tempV = temp[1].substring(1, 5);
					}
					else if(temp[1].length() == 8)	{
						tempV = temp[1].substring(1, 7);
					}

					double temperatura = Double.parseDouble(tempV);


					String cellV = "";
					if (stringSplitted[4].length() == 29) {
						cellV = stringSplitted[4].substring(8, 13);
					}
					else if (stringSplitted[4].length() == 28) {
						cellV = stringSplitted[4].substring(8, 12);
					}
					else if (stringSplitted[4].length() == 27) {
						cellV = stringSplitted[4].substring(8, 11);
					}
					else if(stringSplitted[4].length() == 26)	{
						cellV = stringSplitted[4].substring(8, 10);
					}
					else if(stringSplitted[4].length() == 25) {
						cellV = stringSplitted[4].substring(8, 9);
					}

					double cell = Double.parseDouble(cellV);

					String[] date = stringSplitted[2].split(":");
					String dateV = date[1].substring(1, 9);
					String[] dateF = dateV.split("/");
					String dateFF = dateF[2] + "-" + dateF[1] + "-" + dateF[0];

					String timeV = stringSplitted[3].substring(7, 15);

					String data = dateFF + " " + timeV;

					DB db = mongoClient.getDB("Sensores");
					DBCollection table = db.getCollection("Medicoes");

					BasicDBObject document = new BasicDBObject();
					document.append("DataHoraMedicao", data);
					document.append("Temperatura", temperatura);
					document.append("Luminosidade", cell);
					document.append("Exportado", exported);
					try { table.insert(document); System.out.println("Insert success.");} catch (Exception e) {}

					mongoClient.close();

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



	public static void main(String[] args) {
		PahoReader reader = new PahoReader();
		reader.read();


	}

}
