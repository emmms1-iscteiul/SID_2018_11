package sensores_temp_luz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.Semaphore;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Migração Mongo
 * 
 * @author Eduardo
 *
 */
public class MySQLMigration extends Thread {


	Connection myConn;
	private Semaphore sem;
	//private int contadorMongo = 0;

	public MySQLMigration(Semaphore sem) {
		this.sem=sem;
	}

	/**
	 * Run
	 */
	@SuppressWarnings("deprecation")
	public void run() {

//		valoresLuminosidade();
//		valoresTemperatura();
		try {
			System.out.println("thread Mongo");

			Class.forName("com.mysql.jdbc.Driver");

			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			System.out.println("Connected successfully!");

			DB db = mongoClient.getDB("Sensores");
			DBCollection table = db.getCollection("Medicoes");

			DBCursor cursor = table.find();

			while (cursor.hasNext()) {

				String dateS = (String) cursor.next().get("DataHoraMedicao");
				double temp = (double) cursor.curr().get("Temperatura");
				double lumin = (double) cursor.curr().get("Luminosidade");
				boolean exported = (boolean) cursor.curr().get("Exportado");
				System.out.println("Temperatura: " + temp + " Luminosidade: " + lumin + " DataHoraMedicao: " + dateS
						+ " Exportado: " + exported);

				if (!exported) {
					String sqlQuery = "insert into medicao_temperatura_luminosidade(DataHoraMedicao, ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values (?, "
							+ temp + ", " + lumin + ")";

					PreparedStatement stmt = myConn.prepareStatement(sqlQuery);
					java.sql.Timestamp dateSS = Timestamp.valueOf(dateS);
					stmt.setTimestamp(1, dateSS);
					stmt.executeUpdate();

					System.out.println("Insert success!");
					exported = true;

					cursor.curr().put("Exportado", exported);
					if (table.count() == 3) {
						table.drop();
					}
				}
			}

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}