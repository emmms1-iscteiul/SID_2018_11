package sensores_temp_luz;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoR implements MqttCallback {

	public void main(String[] args) {
		// TODO Auto-generated method stub

		try {



			String topic = "iscte_sid_2019";
			String broker = "tcp://iot.eclipse.org:1883";
			String clientId = "MCSSS";
			MemoryPersistence persistence = new MemoryPersistence();

			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: "+broker);

			sampleClient.connect(connOpts);

			System.out.println("Connected");

				System.out.println("PahoRead a funcionar.");
				//System.out.println("Chegou aqui");
				sampleClient.setCallback(this);
				sampleClient.subscribe(topic, 0);
				//notify();

			

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
	System.out.println(message);
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
	// TODO Auto-generated method stub

}

}