package sensores_temp_luz;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SincronizacaoPaho  {

	private static final String TOPIC = "iscte_sid_2019_Ex2";
	private static final String BROKER = "tcp://iot.eclipse.org:1883";
	private static final String CLIENTID = "MCSSS";
	private static final MemoryPersistence persistence = new MemoryPersistence();
	
	private ArrayList<MqttMessage> msgs=new ArrayList<MqttMessage>();
	
	
	public void doConection()	{
		
	}
	
	
	public synchronized void write()	{
		String content = "{\"tmp\":\"25.10\",\"hum\":\"61.30\",\"dat\":\"9/4/2019\",\"tim\":\"14:59:32\",\"cell\":\"3138\"\"sens\":\"wifi\"}";
		int qos	= 1;

		

		try {
				

				MqttClient sampleClient = new MqttClient(BROKER, CLIENTID, persistence);
				MqttConnectOptions connOpts = new MqttConnectOptions();
				connOpts.setCleanSession(true);
				System.out.println("Connecting to broker: "+BROKER);
				sampleClient.connect(connOpts);
				System.out.println("PahoWrite is Connected");
				
		
					System.out.println("Publishing message: "+content);
					//sampleClient.subscribe(topic);
					MqttMessage message = new MqttMessage(content.getBytes());
					message.setQos(qos);
					msgs.add(message);
					//message.setRetained(true);
					sampleClient.publish(TOPIC, message);
				//	sampleClient.disconnect();
				//	System.out.println("PahoWrite Disconnected");
				
				


			//	System.exit(0);
			

		} catch(MqttException me) {
			System.out.println("Error in Write");
		}

	}
	
	

	public synchronized void read()	{
		try {



			String topic = "iscte_sid_2019_Ex2";
			String broker = "tcp://iot.eclipse.org:1883";
			String clientId = "MCSSS";
			MemoryPersistence persistence = new MemoryPersistence();

			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: "+broker);

			sampleClient.connect(connOpts);

			System.out.println("PahoRead is Connected");

				sampleClient.setCallback(new  MqttCallback() {
					
					@Override
					public void connectionLost(Throwable cause) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void messageArrived(String topic, MqttMessage message) throws Exception {
						System.out.println("Topic: "+topic+"Message: "+message.toString());
						
					}

					@Override
					public void deliveryComplete(IMqttDeliveryToken token) {
						// TODO Auto-generated method stub
						
					}
					
				});
				sampleClient.subscribe(topic, 1);
		
				
				for(MqttMessage m:msgs)	{
					try {
						System.out.println(m.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Erro na sms");
					}
				}
				
				//notify();

			

		} catch (MqttException e) {
			System.out.println("Error in read");
		}
	}



	
}
