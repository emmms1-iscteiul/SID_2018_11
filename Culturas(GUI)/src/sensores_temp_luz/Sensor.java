package sensores_temp_luz;

import java.util.concurrent.Callable;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Sensor implements Callable<Void> {

	private IMqttClient client;
	static final String TOPIC = "iscte_sid_2019_Se";
	
	public Sensor(IMqttClient publisher) {
		// TODO Auto-generated constructor stub
		this.client = publisher;
	}

	@Override
	public Void call() throws Exception {
		// TODO Auto-generated method stub~
		if ( !client.isConnected()) {
            return null;
        }
		
        MqttMessage msg = readSensorTemp();
        msg.setQos(0);
        msg.setRetained(true);
        client.publish(TOPIC, msg);
		
		return null;
	}

	private MqttMessage readSensorTemp() {
		// TODO Auto-generated method stub
		double temp =  0 + (int)(Math.random() * ((50 - 0) + 1));        
        byte[] payload = String.format("{\"tmp\":", temp).getBytes();  
        
        return new MqttMessage(payload); 
	}

}
