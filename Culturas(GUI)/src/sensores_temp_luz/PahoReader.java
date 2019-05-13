package sensores_temp_luz;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
/**
 * Leitor PAHO
 * @author Eduardo
 *
 */
public class PahoReader extends Thread {

	private static final String TOPIC = "/sid_lab_2019_2";
	private static final String BROKER = "tcp://iot.eclipse.org:1883";// "tcp://broker.mqtt-dashboard.com:1883";
	private static final String CLIENTID = "sid_lab_2019";
	private static final MemoryPersistence persistence = new MemoryPersistence();
	private boolean exported = false; //
/**
 * Run
 */
	public void run() {
		read();
	}

	// {"tmp":"22.40","hum":"61.30","dat":"9/4/2019","tim":"14:59:32","cell":"3138""sens":"wifi"}
/**
 * Verificar n�mero de par�metros
 * @param message
 * @return
 */
	public boolean checkNumberOfParameters(MqttMessage message) {
		boolean fiveParameters = true;
		String messageString = String.valueOf(message);
		String[] messageSplitted = messageString.split(",");
		System.out.println(messageSplitted.length + " parameters!");
		if (messageSplitted.length != 5) {
			fiveParameters = false;
		}
		return fiveParameters;
	}
/**
 * Verificar validade dos par�metros
 * @param message
 * @return
 */
	public boolean checkIfEachParameterIsValid(MqttMessage message) {
		boolean parameterValid = true;
		String subStringLum = subStringLum(message);
		String subStringHora = subStringHora(message);
		String subStringData = subStringData(message);
		String subStringHum = subStringHum(message);
		String subStringTemp = subStringTemp(message);
		if (!subStringTemp.equals("tmp") || !subStringHum.equals("hum") || !subStringData.equals("dat")
				|| !subStringHora.equals("tim") || !subStringLum.equals("cell")) {
			parameterValid = false;
		}

		System.out.println(parameterValid);
		return parameterValid;
	}

private String subStringTemp(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String temperatura = messageSplitted[0];
	int indexTemp = temperatura.indexOf(":");
	String subStringTemp = "";
	if (indexTemp != -1) {
		subStringTemp = temperatura.substring(2, indexTemp);
	}
	subStringTemp = subStringTemp.replace(subStringTemp.substring(subStringTemp.length() - 1), "");
	return subStringTemp;
}

private String subStringHum(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String humidade = messageSplitted[1];
	int indexHum = humidade.indexOf(":");
	String subStringHum = "";
	if (indexHum != -1) {
		subStringHum = humidade.substring(1, indexHum);
	}
	subStringHum = subStringHum.replace(subStringHum.substring(subStringHum.length() - 1), "");
	return subStringHum;
}

private String subStringData(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String data = messageSplitted[2];
	int indexData = data.indexOf(":");
	String subStringData = "";
	if (indexData != -1) {
		subStringData = data.substring(1, indexData);
	}
	subStringData = subStringData.replace(subStringData.substring(subStringData.length() - 1), "");
	return subStringData;
}

private String subStringHora(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String hora = messageSplitted[3];
	int indexHora = hora.indexOf(":");
	String subStringHora = "";
	if (indexHora != -1) {
		subStringHora = hora.substring(1, indexHora);
	}
	subStringHora = subStringHora.replace(subStringHora.substring(subStringHora.length() - 1), "");
	return subStringHora;
}

private String subStringLum(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String luminosidade = messageSplitted[4];
	int indexLum = luminosidade.indexOf(":");
	String subStringLum = "";
	if (indexLum != -1) {
		subStringLum = luminosidade.substring(1, indexLum);
	}
	subStringLum = subStringLum.replace(subStringLum.substring(subStringLum.length() - 1), "");
	return subStringLum;
}
/**
 * Verificar valor de par�metros
 * @param message
 * @return
 */
	public boolean checkValueOfEachParameter(MqttMessage message) {
		boolean valueIsValid = false;
		boolean dataIsOk=true;
		boolean timeIsOk=true;
		int dataIsValid = dataIsValid(message);
		String luminosidade = luminosidade(message);
		String tempo = tempo(message);
		String humidade = humidade(message);
		String temperatura = temperatura(message);
		

		// System.out.println(temperatura);

		

		// System.out.println(humidade);

		

		// System.out.println(data);

		

		// System.out.println(tempo);

		

		// System.out.println(luminosidade);

		String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

		

//		System.out.println(currentDayInt);
//		System.out.println(dataDayInt);

		if (dataIsValid != 0) {
			System.out.println("Data Error!");
			dataIsOk = false;
		}

		timeIsOk = timeIsOk(timeIsOk, tempo, currentTime);
		String[] currentTimeSplitted = currentTime.split(":");

		String currentHour = currentTimeSplitted[0];
		String currentMinutes = currentTimeSplitted[1];

		String[] sensorTimeSplitted = tempo.split(":");

		String sensorHour = sensorTimeSplitted[0];
		String sensorMinutes = sensorTimeSplitted[1];

		if (!currentHour.equals(sensorHour) && !currentMinutes.equals(sensorMinutes)) {
			System.out.println("Time Error!");
		}

		if(dataIsOk&&timeIsOk)	{
			valueIsValid=true;
		}
		
		System.out.println(valueIsValid);
		
		return valueIsValid;
	}

private String temperatura(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String temperatura = messageSplitted[0];
	String[] temperaturaSplitted = temperatura.split(":");
	temperatura = temperaturaSplitted[1];
	temperatura = temperatura.replace(temperatura.substring(temperatura.length() - 1), "");
	return temperatura;
}

private boolean timeIsOk(boolean timeIsOk, String tempo, String currentTime) {
	String[] currentTimeSplitted = currentTime.split(":");
	String currentHour = currentTimeSplitted[0];
	String currentMinutes = currentTimeSplitted[1];
	String[] sensorTimeSplitted = tempo.split(":");
	String sensorHour = sensorTimeSplitted[0];
	String sensorMinutes = sensorTimeSplitted[1];
	if (!currentHour.equals(sensorHour) && !currentMinutes.equals(sensorMinutes)) {
		timeIsOk = false;
	}
	return timeIsOk;
}

private String humidade(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String humidade = messageSplitted[1];
	String[] humidadeSplitted = humidade.split(":");
	humidade = humidadeSplitted[1];
	humidade = humidade.replace(humidade.substring(humidade.length() - 1), "");
	return humidade;
}

private String tempo(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String tempo = messageSplitted[3];
	int indexTemp = tempo.length();
	if (indexTemp != -1) {
		tempo = tempo.substring(6, indexTemp);
	}
	tempo = tempo.replace(tempo.substring(tempo.length() - 1), "");
	return tempo;
}

private String luminosidade(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String luminosidade = messageSplitted[4];
	String[] luminosidadeSplitted = luminosidade.split(":");
	luminosidade = luminosidadeSplitted[1];
	int indexLum = luminosidade.indexOf("s");
	if (indexLum != -1) {
		luminosidade = luminosidade.substring(1, indexLum);
	}
	luminosidade = luminosidade.replace(luminosidade.substring(luminosidade.length() - 1), "");
	return luminosidade;
}

private int dataIsValid(MqttMessage message) throws java.lang.NumberFormatException {
	String data = data(message);
	String currentData = new SimpleDateFormat("d/M/yyyy").format(Calendar.getInstance().getTime());
	String[] currentDataSplitted = currentData.split("/");
	String currentYear = currentDataSplitted[2];
	String currentMonth = currentDataSplitted[1];
	String currentDay = currentDataSplitted[0];
	String[] sensorDataSplitted = data.split("/");
	String sensorDataYear = sensorDataSplitted[2];
	String sensorDataMonth = sensorDataSplitted[1];
	String sensorDataDay = sensorDataSplitted[0];
	int currentDayInt = Integer.parseInt(currentDay);
	int dataDayInt = Integer.parseInt(sensorDataDay);
	int dataIsValid = -1;
	if (currentYear.equals(sensorDataYear) && currentMonth.equals(sensorDataMonth)) {
		dataIsValid = dataDayInt - currentDayInt;
	}
	return dataIsValid;
}

private String data(MqttMessage message) {
	String messageString = String.valueOf(message);
	String[] messageSplitted = messageString.split(",");
	String data = messageSplitted[2];
	String[] dataSplitted = data.split(":");
	data = dataSplitted[1];
	data = data.replace(data.substring(data.length() - 1), "");
	return data;
}
/**
 * Leitura 
 */
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

				@SuppressWarnings("deprecation")
				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {

					sleep(3000);
					MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

					String smsString = String.valueOf(message);

					System.out.println("Mensagem: " + smsString);

					checkNumberOfParameters(message);
					checkIfEachParameterIsValid(message);
					checkValueOfEachParameter(message);

					String[] stringSplitted = smsString.split(",");
					String[] temp = stringSplitted[0].split(":");

					String tempV = "";
					if (temp[1].length() == 7) {
						tempV = temp[1].substring(1, 6);
					} else if (temp[1].length() == 6) {
						tempV = temp[1].substring(1, 5);
					} else if (temp[1].length() == 8) {
						tempV = temp[1].substring(1, 7);
					}

					double temperatura = Double.parseDouble(tempV);

					String cellV = "";
					if (stringSplitted[4].length() == 29) {
						cellV = stringSplitted[4].substring(8, 13);
					} else if (stringSplitted[4].length() == 28) {
						cellV = stringSplitted[4].substring(8, 12);
					} else if (stringSplitted[4].length() == 27) {
						cellV = stringSplitted[4].substring(8, 11);
					} else if (stringSplitted[4].length() == 26) {
						cellV = stringSplitted[4].substring(8, 10);
					} else if (stringSplitted[4].length() == 25) {
						cellV = stringSplitted[4].substring(8, 9);
					}

					double cell = Double.parseDouble(cellV);

					String[] date = stringSplitted[2].split(":");
					String dateV = date[1].substring(1, 9);
					String[] dateF = dateV.split("/");
					String dateFF = dateF[2] + "-" + dateF[1] + "-" + dateF[0];

					String timeV = stringSplitted[3].substring(7, 15);

					String data = dateFF + " " + timeV;

					DB db = mongoClient.getDB("Sensores");
					DBCollection table = db.getCollection("Medicoes");

					BasicDBObject document = new BasicDBObject();
					document.append("DataHoraMedicao", data);
					document.append("Temperatura", temperatura);
					document.append("Luminosidade", cell);
					document.append("Exportado", exported);
					try { table.insert(document); System.out.println("Insert success.");} catch (Exception e) {}

					mongoClient.close();
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
/**
 * Main
 * @param args
 */
	public static void main(String[] args) {
		PahoReader reader = new PahoReader();
		reader.read();

	}

}
