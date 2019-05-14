package sensores_temp_luz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
/**
 * Migração Mongo
 * @author Eduardo
 *
 */
public class MongoMigration extends Thread {
	
	private ArrayList<Double> valoresTemperatura=new ArrayList<Double>();
	private ArrayList<Double> valoresLuminosidade=new ArrayList<Double>();
//	private ArrayList<Double> medias=new ArrayList<Double>();
	
	Connection myConn;	
/**
 * Run	
 */
	@SuppressWarnings("deprecation")
	public void run() {
		
		valoresLuminosidade();
		valoresTemperatura();
		try {
			sleep(4000);
			
			Class.forName("com.mysql.jdbc.Driver");

			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			System.out.println("Connected successfully!");

			DB db = mongoClient.getDB("Sensores");
			DBCollection table = db.getCollection("Medicoes");
			DBCursor cursor = table.find();
			
			while(cursor.hasNext()) {
				
				double temperat = (double) cursor.curr().get("Temperatura");
				double lumin = (double) cursor.curr().get("Luminosidade");
				String dateS = (String) cursor.curr().get("DataHoraMedicao");
				boolean exported = (boolean) cursor.curr().get("Exportado");
				System.out.println("Temperatura: " + temperat + " Luminosidade: " + lumin + " DataHoraMedicao: " + dateS + " Exportado: " + exported);

				if (!exported) {
					String sqlQuery = "insert into medicoes_temperatura_luminosidade(DataHoraMedicao, ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values (?, "+temperat+", "+lumin+")";

					PreparedStatement stmt = myConn.prepareStatement(sqlQuery);
					java.sql.Timestamp dateSS = Timestamp.valueOf(dateS);
					stmt.setTimestamp(1, dateSS);
					stmt.executeUpdate();
					
					cursor.curr().put("Exportado", true);
				}
			}

			mongoClient.close();

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
private void valoresTemperatura() {
	MongoClient mongoClient = new MongoClient(
			new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
	DB db = mongoClient.getDB("Sensores");
	DBCollection table = db.getCollection("Medicoes");
	DBCursor cursor = table.find();
	valoresTemperatura2(cursor);
}
private void valoresTemperatura2(DBCursor cursor) {
	while (cursor.hasNext()) {
		valoresTemperatura3(cursor);
	}
}
private void valoresTemperatura3(DBCursor cursor) {
	double temperat = (double) cursor.curr().get("Temperatura");
	valoresTemperatura.add(temperat);
}
private void valoresLuminosidade() {
	MongoClient mongoClient = new MongoClient(
			new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
	DB db = mongoClient.getDB("Sensores");
	DBCollection table = db.getCollection("Medicoes");
	DBCursor cursor = table.find();
	valoresLuminosidade2(cursor);
}
private void valoresLuminosidade2(DBCursor cursor) {
	while (cursor.hasNext()) {
		valoresLuminosidade3(cursor);
	}
}
private void valoresLuminosidade3(DBCursor cursor) {
	double lumin = (double) cursor.curr().get("Luminosidade");
	valoresLuminosidade.add(lumin);
}
}


