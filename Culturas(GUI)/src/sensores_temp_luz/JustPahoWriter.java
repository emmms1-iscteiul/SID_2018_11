package sensores_temp_luz;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class JustPahoWriter {

	
	
	private static final String TOPIC = "iscte_sid_2019_SID1";
	private static final String BROKER = "tcp://iot.eclipse.org:1883";
	private static final String CLIENTID = "SID1";
	private static final MemoryPersistence persistence = new MemoryPersistence();
	

	
	public void write()	{
		String content = "{\"tmp\":\"25.10\",\"hum\":\"61.30\",\"dat\":\"9/4/2019\",\"tim\":\"14:59:32\",\"cell\":\"3138\"\"sens\":\"wifi\"}";
		int qos	= 1;
		try {
				

				MqttClient sampleClient = new MqttClient(BROKER, CLIENTID,persistence);
				MqttConnectOptions connOpts = new MqttConnectOptions();
				connOpts.setCleanSession(true);
				System.out.println("Connecting to broker: "+BROKER);
				sampleClient.connect(connOpts);
				System.out.println("PahoWrite is Connected");
				
		
					System.out.println("Publishing message: "+content);
					MqttMessage message = new MqttMessage(content.getBytes());
					message.setQos(qos);
					message.setRetained(true);
					sampleClient.publish(TOPIC, message);
					sampleClient.disconnect();
					System.exit(0);
			

		} catch(MqttException me) {
			System.out.println("Error in Write");
		}

	}
	
	public static void main(String[] args) {
		JustPahoWriter writer=new JustPahoWriter();
		writer.write();
	}
}
