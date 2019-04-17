package sensores_temp_luz;

import java.util.concurrent.Callable;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Sensor implements Callable<Void> {

	private IMqttClient client;
	static final String TOPIC = "iscte_sid_2019_Ex1";
	
	public Sensor(IMqttClient publisher) {
		this.client = publisher;
	}

	@Override
	public Void call() throws Exception {
		if ( !client.isConnected()) {
            return null;
        }
		
        MqttMessage msg = readSensorTemp();
        msg.setQos(1); //se this option when message loss is not acceptable and your subscribers can handle duplicates
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
