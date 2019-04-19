package sensores_temp_luz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoMigration {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Map<Integer, Double> medicoesTemp = new HashMap<Integer,Double>();
			Map<Integer, Double> medicoesLum = new HashMap<Integer,Double>();
			Map<Integer, String> medicoesTimeStamp = new HashMap<Integer,String>();

			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			System.out.println("Connected successfully!");


			DB db = mongoClient.getDB("Sensores");
			DBCollection table = db.getCollection("Medicoes");
			DBCursor cursor = table.find();
			
			while(cursor.hasNext()) {
				
				int id = (int) cursor.next().get("_id");
				double temperat = (double) cursor.curr().get("Temperatura");
				double lumin = (double) cursor.curr().get("Luminosidade");
				String dateS = (String) cursor.curr().get("DataHoraMedicao");
				System.out.println("ID: " + id + " Temperatura: " + temperat + " Luminosidade: " + lumin + " Timestamp:" + dateS);

				medicoesTemp.put(id, temperat);
				medicoesLum.put(id, lumin);
				medicoesTimeStamp.put(id, dateS);

				System.out.println("Inserção no hashmap com sucesso!");

			}

			Statement myStmt = myConn.createStatement();

			ResultSet myRs = myStmt.executeQuery("select IDMedicaoLuminosidadeTemperatura from medicoes_luminosidade_e_temperatura");
			
			while (myRs.next()) {
				
				if (!medicoesTemp.containsKey(myRs.getInt("IDMedicaoLuminosidadeTemperatura")) && !medicoesLum.containsKey(myRs.getInt("IDMedicaoLuminosidadeTemperatura"))) {
					String sqlQuery = "insert into medicoes_luminosidade_e_temperatura(DataHoraMedicaoLuminosidadeTemperatura, ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values (?, "+medicoesTemp.get(myRs.getInt("IDMedicaoLuminosidadeTemperatura"))+", "+medicoesLum.get(myRs.getInt("IDMedicaoLuminosidadeTemperatura"))+")";

					PreparedStatement stmt = myConn.prepareStatement(sqlQuery);
					java.sql.Timestamp dateSS = Timestamp.valueOf(medicoesTimeStamp.get(myRs.getInt("IDMedicaoLuminosidadeTemperatura")));
					stmt.setTimestamp(1, dateSS);
					stmt.executeUpdate();
					System.out.println("Insert success!");
				} 
			}

			mongoClient.close();

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}

}


