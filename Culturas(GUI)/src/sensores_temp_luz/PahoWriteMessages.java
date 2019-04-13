package sensores_temp_luz;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoWriteMessages {

	public static void main(String[] args) {

		String topic        = "iscte_sid_2019_S1";
		String content		= "{\"tmp\":\"25.10\",\"hum\":\"61.30\",\"dat\":\"9/4/2019\",\"tim\":\"14:59:32\",\"cell\":\"3138\"\"sens\":\"wifi\"}";
		int qos				= 0;
		String broker       = "tcp://iot.eclipse.org:1883";
		String clientId     = "QaRDj";
		MemoryPersistence persistence = new MemoryPersistence();

		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: "+broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");
			System.out.println("Publishing message: "+content);
			//sampleClient.subscribe(topic);
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			message.setRetained(true);
			sampleClient.publish(topic, message);
			sampleClient.disconnect();
			System.out.println("Disconnected");
			System.exit(0);
		} catch(MqttException me) {
			System.out.println("reason "+me.getReasonCode());
			System.out.println("msg "+me.getMessage());
			System.out.println("loc "+me.getLocalizedMessage());
			System.out.println("cause "+me.getCause());
			System.out.println("excep "+me);
			me.printStackTrace();
		}
	}
}
