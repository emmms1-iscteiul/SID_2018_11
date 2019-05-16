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
public class MongoMigration extends Thread {


	Connection myConn;
	private Semaphore sem;
	//private int contadorMongo = 0;

	public MongoMigration(Semaphore sem) {
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

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas",
					"root", "root");
			System.out.println("Connected successfully!");

			DB db = mongoClient.getDB("Sensores");
			DBCollection table = db.getCollection("Medicoes");
			
			try {
				//table.insert(queue.dequeue());
				System.out.println("Insert success e removeu da blocking queue.");
			} catch (Exception e) {
				System.out.println("fila vazia");
			}

			

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}