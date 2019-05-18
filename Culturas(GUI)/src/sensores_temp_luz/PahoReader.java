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

/**
 * Leitor PAHO
 * 
 * @author Eduardo
 *
 */
public class PahoReader extends Thread {

	private static final String TOPIC = "/sid_lab_2019";
	private static final String BROKER = "tcp://iot.eclipse.org";// "tcp://broker.mqtt-dashboard.com:1883";
	private static final String CLIENTID = "/sid_lab_2019";
	private static final MemoryPersistence persistence = new MemoryPersistence();
	private MongoMigration mongoM;
	private MySemaphore semaphore;

	public PahoReader(MongoMigration mongoM,MySemaphore semaphore) {
		// TODO Auto-generated constructor stub
		this.mongoM=mongoM;
		this.semaphore=semaphore;
		
	}

	/**
	 * Run
	 */
	public void run() {
		readValues();
	}

	// {"tmp":"22.40","hum":"61.30","dat":"9/4/2019","tim":"14:59:32","cell":"3138""sens":"wifi"}
	/**
	 * Verificar número de parâmetros
	 * 
	 * @param message
	 * @return
	 */
	public boolean checkNumberOfParameters(MqttMessage message) {
		boolean fiveParameters = false;
		String messageString = String.valueOf(message);
		String[] messageSplitted = messageString.split(",");
//		System.out.println(messageSplitted.length + " parameters!");
		if (messageSplitted.length == 4) {
			fiveParameters = true;
		}
		return fiveParameters;
	}

	/**
	 * Verificar validade dos parâmetros
	 * 
	 * @param message
	 * @return
	 */
	public boolean checkIfEachParameterIsValid(MqttMessage message) {
		boolean parameterValid = true;
		String messageString = String.valueOf(message);
		String[] messageSplitted = messageString.split(",");

		String temperatura = messageSplitted[0];
		int indexTemp = temperatura.indexOf(":");
		String subStringTemp = "";
		if (indexTemp != -1) {
			subStringTemp = temperatura.substring(2, indexTemp);
		}
		subStringTemp = subStringTemp.replace(subStringTemp.substring(subStringTemp.length() - 1), "");

		String humidade = messageSplitted[1];
		int indexHum = humidade.indexOf(":");
		String subStringHum = "";
		if (indexHum != -1) {
			subStringHum = humidade.substring(1, indexHum);
		}
		subStringHum = subStringHum.replace(subStringHum.substring(subStringHum.length() - 1), "");
		
		
		String data = messageSplitted[2];
		int indexData = data.indexOf(":");
		String subStringData = "";
		if (indexData != -1) {
			subStringData = data.substring(1, indexData);
		}
		subStringData = subStringData.replace(subStringData.substring(subStringData.length() - 1), "");

		String hora = messageSplitted[3];
		int indexHora = hora.indexOf(":");
		String subStringHora = "";
		if (indexHora != -1) {
			subStringHora = hora.substring(1, indexHora);
		}
		subStringHora = subStringHora.replace(subStringHora.substring(subStringHora.length() - 1), "");

//		String luminosidade = messageSplitted[4];
//		int indexLum = luminosidade.indexOf(":");
//		String subStringLum = "";
//		if (indexLum != -1) {
//			subStringLum = luminosidade.substring(1, indexLum);
//		}
//		subStringLum = subStringLum.replace(subStringLum.substring(subStringLum.length() - 1), "");
//
//		if (!subStringTemp.equals("tmp") || !subStringHum.equals("hum") || !subStringData.equals("dat")
//				|| !subStringHora.equals("tim") /*|| !subStringLum.equals("cell")*/) {
//			parameterValid = false;
//		}

//		System.out.println(parameterValid);
		return parameterValid;
	}

	/**
	 * Verificar valor de parâmetros
	 * 
	 * @param message
	 * @return
	 */
	public boolean checkValueOfEachParameter(MqttMessage message) {
		boolean valueIsValid = false;
		boolean valueNull=false;
		boolean dataIsOk = true;
		boolean timeIsOk = false;
		String messageString = String.valueOf(message);
		String[] messageSplitted = messageString.split(",");

		String temperatura = messageSplitted[0];
		String[] temperaturaSplitted = temperatura.split(":");
		temperatura = temperaturaSplitted[1];
		temperatura = temperatura.replace(temperatura.substring(temperatura.length() - 1), "");
		if(temperatura.equals("")||temperatura.equals(null))	{
			valueNull=true;
		}

		// System.out.println(temperatura);

		String humidade = messageSplitted[1];
		String[] humidadeSplitted = humidade.split(":");
		humidade = humidadeSplitted[1];
		humidade = humidade.replace(humidade.substring(humidade.length() - 1), "");

		if(humidade.equals("")||humidade.equals(null))	{
			valueNull=true;
		}


		// System.out.println(humidade);

		String data = messageSplitted[2];
		String[] dataSplitted = data.split(":");
		data = dataSplitted[1];
		data = data.replace(data.substring(data.length() - 1), "");
		if(data.equals("")||data.equals(null))	{
			valueNull=true;
		}
		// System.out.println(data);

		String tempo = messageSplitted[3];
		int indexTemp = tempo.length();
		if (indexTemp != -1) {
			tempo = tempo.substring(6, indexTemp);
		}
		tempo = tempo.replace(tempo.substring(tempo.length() - 1), "");
		if(tempo.equals("")||tempo.equals(null))	{
			valueNull=true;
		}
		// System.out.println(tempo);

//		String luminosidade = messageSplitted[4];
//		String[] luminosidadeSplitted = luminosidade.split(":");
//		luminosidade = luminosidadeSplitted[1];
//		int indexLum = luminosidade.indexOf("s");
//		if (indexLum != -1) {
//			luminosidade = luminosidade.substring(1, indexLum);
//		}
//		luminosidade = luminosidade.replace(luminosidade.substring(luminosidade.length() - 1), "");
//		if(luminosidade.equals("")||luminosidade.equals(null))	{
//			valueNull=true;
//		}
		// System.out.println(luminosidade);

		String currentData = new SimpleDateFormat("d/M/yyyy").format(Calendar.getInstance().getTime());
		String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

		String[] currentDataSplitted = currentData.split("/");

		String currentYear = currentDataSplitted[2];
		// System.out.println(currentYear);
		String currentMonth = currentDataSplitted[1];
		// System.out.println(currentMonth);
		String currentDay = currentDataSplitted[0];
		// System.out.println(currentDay);

		String[] sensorDataSplitted = data.split("/");

		String sensorDataYear = sensorDataSplitted[2];
		// System.out.println(sensorDataYear);
		String sensorDataMonth = sensorDataSplitted[1];
		// System.out.println(sensorDataMonth);
		String sensorDataDay = sensorDataSplitted[0];
//		int sensorDataDayint=Integer.parseInt(sensorDataDay);
//		sensorDataDayint++;
//		sensorDataDay=String.valueOf(sensorDataDayint);
		

		int currentDayInt = Integer.parseInt(currentDay);
		int dataDayInt = Integer.parseInt(sensorDataDay);

//			System.out.println(currentDayInt);
//			System.out.println(dataDayInt);

		int dataIsValid = -1;

		if (currentYear.equals(sensorDataYear) && currentMonth.equals(sensorDataMonth)) {
			dataIsValid = dataDayInt - currentDayInt;
		}

		if (dataIsValid != 0) {
			System.out.println("Data Error!");
			dataIsOk = false;
		}

		boolean hourOK=false;
		boolean minutesOk=false;
		
		String[] currentTimeSplitted = currentTime.split(":");

		String currentHour = currentTimeSplitted[0];
		System.out.println("Pc Hour: "+currentHour);
		String currentMinutes = currentTimeSplitted[1];
		//currentMinutes=currentMinutes.substring(1,currentMinutes.length());

		String[] sensorTimeSplitted = tempo.split(":");

		String sensorHour = sensorTimeSplitted[0];
		sensorHour=sensorHour.substring(1, sensorHour.length());
		int sensorIncrementedHour=Integer.parseInt(sensorHour);
		sensorIncrementedHour++;
		sensorHour=String.valueOf(sensorIncrementedHour);
		sensorHour="01";
	

		System.out.println("Sensor Hour: "+sensorHour);
		
		String sensorMinutes = sensorTimeSplitted[1];
		System.out.println(sensorMinutes);
		System.out.println(currentMinutes);
		

		if (currentHour.equals(sensorHour)) {
			hourOK=true;
		}
		
		if (currentMinutes.equals(sensorMinutes)) {
			minutesOk=true;
		}
		
		if(hourOK && minutesOk)	{
			timeIsOk=true;
			System.out.println("Time it´s fine");
		}else	{
			System.out.println("Time Error!");
		}
		
		if (dataIsOk && timeIsOk&&!valueNull) {
			valueIsValid = true;
		}
		
		

		System.out.println("DeH: "+valueIsValid);

		return valueIsValid;
	}

	/**
	 * Leitura
	 */
	public void readValues() {
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

					semaphore.acquire();
					String smsString = String.valueOf(message);

					System.out.println("Mensagem: " + smsString);

					if (checkNumberOfParameters(message)) {
						if (checkIfEachParameterIsValid(message)) {
							if (checkValueOfEachParameter(message)) {

								String[] stringSplitted = smsString.split(",");
								String[] temp = stringSplitted[0].split(":");

								String tempV = "";
								if (temp[1].length() == 7) {
									tempV = temp[1].substring(1, 6);
	//								System.out.println(tempV);
								}
								if (temp[1].length() == 6) {
									tempV = temp[1].substring(1, 5);
		//							System.out.println(tempV);
								}
								if (temp[1].length() == 8) {
									tempV = temp[1].substring(1, 7);
			//						System.out.println(tempV);
								}

								double temperatura = Double.parseDouble(tempV);

//								String cellV = "";
//								if (stringSplitted[4].length() == 29) {
//									cellV = stringSplitted[4].substring(8, 13);
//				//					System.out.println(cellV);
//								} else if (stringSplitted[4].length() == 28) {
//									cellV = stringSplitted[4].substring(8, 12);
//					//				System.out.println(cellV);
//								} else if (stringSplitted[4].length() == 27) {
//									cellV = stringSplitted[4].substring(8, 12);
//						//			System.out.println(cellV);
//								} else if (stringSplitted[4].length() == 26) {
//									cellV = stringSplitted[4].substring(8, 11);
//							//		System.out.println(cellV);
//								} else if (stringSplitted[4].length() == 25) {
//									cellV = stringSplitted[4].substring(8, 10);
//					//				System.out.println(cellV);
//								}
//
//								double cell = Double.parseDouble(cellV);

								String[] date = stringSplitted[2].split(":");
				//				System.out.println(date[1]);
								String dateV = "";
								if (date[1].length() == 11) {
									dateV = date[1].substring(1, 10);
					//				System.out.println(dateV);
								}
								if (date[1].length() == 10) {
									dateV = date[1].substring(1, 9);
								}
								String[] dateF = dateV.split("/");
								String dateFF = dateF[2] + "-" + dateF[1] + "-" + dateF[0];
				//				System.out.println(dateFF);
								
								String timeV = "";
								System.out.println(stringSplitted[3].length());
								if (stringSplitted[3].length() == 29) {
									timeV = stringSplitted[3].substring(7, 14);
				//					System.out.println(timeV);
								}
								if (stringSplitted[3].length() == 30) {
									timeV = stringSplitted[3].substring(7, 15);
				//					System.out.println(timeV);
								}
								if (stringSplitted[3].length() == 28) {
									timeV = stringSplitted[3].substring(7, 13);
				//					System.out.println(timeV);
								}
								
								String data = dateFF + " " + timeV;
					//			System.out.println(data);
			
								BasicDBObject document = new BasicDBObject();
								document.append("DataHoraMedicao", data);
								document.append("Temperatura", temperatura);
								//document.append("Luminosidade", cell);
								
								mongoM.insertValuesMongo(document);
								//sleep(1000);
								semaphore.release();
							}	
						}
					}
					
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					// TODO Auto-generated method stub

				}

			});

			sampleClient.subscribe(TOPIC, 0);

		} catch (MqttException e) {
			e.printStackTrace();
		}

	}


}