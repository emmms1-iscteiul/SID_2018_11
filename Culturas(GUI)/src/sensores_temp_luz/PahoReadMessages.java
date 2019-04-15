package sensores_temp_luz;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoReadMessages extends Thread implements MqttCallback {

	PahoWriteMessages pahoW;
	
	public PahoReadMessages(PahoWriteMessages write) {
		// TODO Auto-generated constructor stub
		pahoW = write;
	}

	public static void main(String[] args) {


	}

	public void run() {
		// TODO Auto-generated method stub

		try {

			String topic = "iscte_sid_2019_S1";
			String broker = "tcp://iot.eclipse.org:1883";
			String clientId = "QaRDj";
			MemoryPersistence persistence = new MemoryPersistence();
			
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: "+broker);

			sampleClient.connect(connOpts);

			System.out.println("Connected");

			while (true) {

				System.out.println("PahoRead a funcionar.");
				try {
					while (!pahoW.isInterrupted()) {
						wait();
					}
				} catch (InterruptedException  e) {
					System.out.println("Foi interrompido");
					//Thread.currentThread().interrupt();
				}
				sampleClient.setCallback(this);
				sampleClient.subscribe(topic, 0);
				//notify();
				
			}

		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Message: " + message.toString());
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}
}