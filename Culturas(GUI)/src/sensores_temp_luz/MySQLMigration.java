package sensores_temp_luz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

/**
 * Migração Mongo
 * 
 * @author Eduardo
 *
 */
public class MySQLMigration extends Thread {

	private MongoMigration mongoM;
	Connection myConn;
	private MySemaphore semaphore;

	public MySQLMigration(MongoMigration mongoM,MySemaphore semaphore) {
		this.mongoM = mongoM;
		this.semaphore=semaphore;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root",
					"root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connected successfully!");
	}

	/**
	 * Run
	 */
	public void run() {
		semaphore.acquire();
		while(!interrupted())	{

			DBCursor cursor = mongoM.getValuesMongoMedicoes();
			DBCursor cursorSuccess = mongoM.getValuesMongoSuccess();

			while (cursor.hasNext()) {
				String date = (String) cursor.next().get("DataHoraMedicao");
				double temp = (double) cursor.curr().get("Temperatura");
				//double lumin = (double) cursor.curr().get("Luminosidade");
				System.out.println("Temperatura: " + temp + " DataHoraMedicao: " + date);
				
				if(!cursorSuccess.hasNext()) {
					String sqlQuery = "insert into medicao_temperatura_luminosidade(DataHoraMedicao, ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values (?, "
							+ temp + ", " + 1 + ")";

					BasicDBObject exportedDocument = new BasicDBObject();
					exportedDocument.append("DataHoraMedicao", date);
					exportedDocument.append("Temperatura", temp);
					//							exportedDocument.append(Luminosidade, lumin);
					mongoM.insertValuesMongoSucess(exportedDocument);
					
					PreparedStatement stmt;
					try {
						stmt = myConn.prepareStatement(sqlQuery);
						java.sql.Timestamp dateS = Timestamp.valueOf(date);
						stmt.setTimestamp(1, dateS);
						stmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("My sql Insert success!");
				}

				while (cursorSuccess.hasNext()) {
					if (cursorSuccess.itcount()==3) {
						System.out.println("Foi tudo exportado");
						break;
					}
					String dateS = (String) cursor.next().get("DataHoraMedicao");
					double tempS = (double) cursor.curr().get("Temperatura");
					//double luminS = (double) cursor.curr().get("Luminosidade");
					System.out.println("Temperatura: " + tempS + " DataHoraMedicao: " + dateS);
					
					if (!date.equals(dateS)&&temp!=tempS /*lumin!=luminS*/)	{
						String sqlQuery = "insert into medicao_temperatura_luminosidade(DataHoraMedicao, ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values (?, "
								+ tempS + ", " + 1 + ")";

						BasicDBObject exportedDocument = new BasicDBObject();
						exportedDocument.append("DataHoraMedicao", dateS);
						exportedDocument.append("Temperatura", tempS);
						//							exportedDocument.append(Luminosidade, luminS);
						mongoM.insertValuesMongoSucess(exportedDocument);
						
						PreparedStatement stmt;
						try {
							stmt = myConn.prepareStatement(sqlQuery);
							java.sql.Timestamp dateSS = Timestamp.valueOf(dateS);
							stmt.setTimestamp(1, dateSS);
							stmt.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
						}
					
						System.out.println("My sql Insert success!");

						//					
					}	

				}
				semaphore.release();
				try {
					sleep(7000);
				} catch (InterruptedException e) {
					System.out.println("Erro no sleep");
				}
			}
		}
	}


}
