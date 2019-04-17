package sensores_temp_luz;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class JustPahoReader {

	
	private static final String TOPIC = "iscte_sid_2019_SID1";
	private static final String BROKER = "tcp://iot.eclipse.org:1883";
	private static final String CLIENTID = "SID1";
	private static final MemoryPersistence persistence = new MemoryPersistence();
	
	
	public void read()	{
		try {

			MqttClient sampleClient = new MqttClient(BROKER, CLIENTID,persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: "+BROKER);

			sampleClient.connect(connOpts);

			System.out.println("PahoRead is Connected");
			

				sampleClient.setCallback(new  MqttCallback() {
					
					@Override
					public void connectionLost(Throwable cause) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void messageArrived(String topic, MqttMessage message) throws Exception {
						System.out.println("Topic: "+topic+" Message: "+message.toString());
						
					}

					@Override
					public void deliveryComplete(IMqttDeliveryToken token) {
						// TODO Auto-generated method stub
						
					}
					
				});
				sampleClient.subscribe(TOPIC, 1);
		
				
				
				//notify();

			

		} catch (MqttException e) {
			System.out.println("Error in read");
		}
	}
	
	public static void main(String[] args) {
		JustPahoReader reader=new JustPahoReader();
		reader.read();
	}
}
