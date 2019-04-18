package sensores_temp_luz;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

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
					for(MqttMessage s:sms)	{
						String smsString = String.valueOf(s);
		//				sleep(3000);
						System.out.println("Mensagem: "+smsString);
					}

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
