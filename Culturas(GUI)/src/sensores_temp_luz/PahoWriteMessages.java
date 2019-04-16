package sensores_temp_luz;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class PahoWriteMessages extends Thread {

	public void run() {


		//String content = "{\"tmp\":\"25.10\",\"hum\":\"61.30\",\"dat\":\"9/4/2019\",\"tim\":\"14:59:32\",\"cell\":\"3138\"\"sens\":\"wifi\"}";
		String broker = "tcp://iot.eclipse.org:1883";
		String clientId = "PSBOS";
		//MemoryPersistence persistence = new MemoryPersistence();

		try {

			IMqttClient publisher = new MqttClient(broker, clientId);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setAutomaticReconnect(true);
			connOpts.setConnectionTimeout(3);
			System.out.println("Connecting to broker: "+broker);
			publisher.connect(connOpts);
			System.out.println("Connected");
			
			Sensor sensorDummy = new Sensor(publisher);

			for (int i = 0; i<5; i++) {
				
				try {
					sensorDummy.call();
					sleep(3000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

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
