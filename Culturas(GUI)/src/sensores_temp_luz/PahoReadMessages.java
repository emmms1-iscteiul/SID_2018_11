package sensores_temp_luz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoReadMessages extends Thread {

	String topic = "iscte_sid_2019_Se";
	
	public static void main(String[] args) {


	}

	public void run() {
		// TODO Auto-generated method stub

		
		String broker = "tcp://iot.eclipse.org:1883";
		String clientId = "PSBOS";
		
		try {

			//MemoryPersistence persistence = new MemoryPersistence();

			IMqttClient subscriber = new MqttClient(broker, clientId);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setAutomaticReconnect(true);
			connOpts.setConnectionTimeout(3);
			System.out.println("Connecting to broker: "+broker);
			subscriber.connect(connOpts);
			System.out.println("Connected");

			while (true) {

				CountDownLatch receivedSignal = new CountDownLatch(5);
				subscriber.subscribe(Sensor.TOPIC, (topic, msg) -> {
				    byte[] payload = msg.getPayload();
				    System.out.println(payload.toString());
				    // ... payload handling omitted
				    receivedSignal.countDown();
				});
				receivedSignal.await(1, TimeUnit.MINUTES);
			}

		} catch (MqttException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}