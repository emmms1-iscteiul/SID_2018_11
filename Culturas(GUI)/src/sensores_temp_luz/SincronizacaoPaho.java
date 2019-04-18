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

	private static final String TOPIC = "iscte_sid_2019_SIDEx1";
	private static final String BROKER = "tcp://iot.eclipse.org:1883";
	private static final String CLIENTID = "SID1";
	private static final MemoryPersistence persistence = new MemoryPersistence();
	
	private static final int NºSMSALER=5;
	
	private int smsEscritas;
	private int smsLidas;
	
	public SincronizacaoPaho()	{
		smsEscritas=0;
		smsLidas=0;
	}
	
	ArrayList<String>valores=new ArrayList<String>();
	
	
	public void inserirValores()	{
		String content1 = "{\"tmp\":\"25.10\",\"hum\":\"61.30\",\"dat\":\"9/4/2019\",\"tim\":\"14:59:32\",\"cell\":\"3138\"\"sens\":\"wifi\"}";
		String content2="{\"tmp\":\"20.10\",\"hum\":\"10.30\",\"dat\":\"10/4/2019\",\"tim\":\"18:59:32\",\"cell\":\"0538\"\"sens\":\"wifi\"}";
		String content3="{\"tmp\":\"19.10\",\"hum\":\"10.30\",\"dat\":\"10/4/2019\",\"tim\":\"18:59:32\",\"cell\":\"0538\"\"sens\":\"wifi\"}";
		String content4="{\"tmp\":\"18.10\",\"hum\":\"10.30\",\"dat\":\"10/4/2019\",\"tim\":\"18:59:32\",\"cell\":\"0538\"\"sens\":\"wifi\"}";
		String content5="{\"tmp\":\"17.10\",\"hum\":\"10.30\",\"dat\":\"10/4/2019\",\"tim\":\"18:59:32\",\"cell\":\"0538\"\"sens\":\"wifi\"}";
	
		valores.add(content1);
		valores.add(content2);
		valores.add(content3);
		valores.add(content4);
		valores.add(content5);
		
	
	}
	
	public synchronized void write(String content)	{
		int qos	= 1;
		try {
				

				MqttClient sampleClient = new MqttClient(BROKER, CLIENTID,persistence);
				MqttConnectOptions connOpts = new MqttConnectOptions();
				connOpts.setCleanSession(true);
				System.out.println("Connecting to broker: "+BROKER);
				sampleClient.connect(connOpts);
				System.out.println("PahoWrite is Connected");
				
				while(smsEscritas>smsLidas)	{
					try {
						wait();
					} catch (InterruptedException e) {
						System.out.println("Error on wait");
					}
				}
					System.out.println("Publishing message: "+content);
					MqttMessage message = new MqttMessage(content.getBytes());
					message.setQos(qos);
		//			message.setRetained(true);
					sampleClient.publish(TOPIC, message);
					
					smsEscritas++;
					
					notifyAll();
				
				sampleClient.disconnect();	
			//	System.exit(0);


		} catch(MqttException me) {
			System.out.println("Error in Write");
		}

	}
	
	
	public synchronized void read()	{
		try {

			MqttClient sampleClient = new MqttClient(BROKER, CLIENTID,persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: "+BROKER);

			sampleClient.connect(connOpts);

			System.out.println("PahoRead is Connected");
			
			while(true)	{
		
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
				smsLidas++;
				sampleClient.subscribe(TOPIC, 1);
				
			}
			//	sampleClient.disconnect();
		
				
			

			

		} catch (MqttException e) {
			System.out.println("Error in read");
		}
		

	}

	
	public int getSMSEnviadas()	{
		return smsEscritas;
	}
	
	public int getSMSLidas()	{
		return smsLidas;
	}
	
	public int getNsmsALer()	{
		return NºSMSALER;
	}
	
}
