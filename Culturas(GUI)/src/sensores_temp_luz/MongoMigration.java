package sensores_temp_luz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

		//Conectar à base de dados monotorizacao_de_culturas
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Map<Integer, Integer> medicoesTemp = new HashMap<Integer,Integer>();
			//Map<Integer, Integer> medicoesLum = new HashMap<Integer,Integer>();

			String content = "{\"tmp\":\"25.10\",\"hum\":\"61.30\",\"dat\":\"9/4/2019\",\"tim\":\"14:59:32\",\"cell\":\"3138\"\"sens\":\"wifi\"}";

			String[] s = content.split(",");
			String[] temp = s[0].split(":");
			String tempV = temp[1].substring(1, 6);
			double temperatura = Double.parseDouble(tempV);

			String[] date = s[2].split(":");
			String dateV = date[1].substring(1, 9);
			String[] dateF = dateV.split("/");
			String dateFF = dateF[2] + "-" + dateF[1] + "-" + dateF[0];
			System.out.println(dateFF);

			String timeV = s[3].substring(7, 15);

			String cellV = s[4].substring(8, 12);
			int cell = Integer.parseInt(cellV);

			String data = dateFF + " " + timeV;
			
			java.sql.Timestamp dateS = Timestamp.valueOf(data);
			System.out.println(dateS.toString());
			

			//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			System.out.println("Connected successfully!");
			
			
			
			String sqlQuery = "insert into medicoes_luminosidade_e_temperatura(DataHoraMedicaoLuminosidadeTemperatura, ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values (?, "+temperatura+", "+cell+")";
			
			PreparedStatement stmt = myConn.prepareStatement(sqlQuery);
			stmt.setTimestamp(1, dateS);
			stmt.executeUpdate();

			//ResultSet myRs = myStmt.executeQuery("select IDMedicaoLuminosidadeTemperatura from medicoes_luminosidade_e_temperatura");

			//myStmt.executeUpdate("insert into medicoes_luminosidade_e_temperatura(DataHoraMedicaoLuminosidadeTemperatura, ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values (?, "+temperatura+", "+cell+")");
			System.out.println("Inserção completa");

			//			DB db = mongoClient.getDB("db_demo");
			//			DBCollection table = db.getCollection("collection_demo");
			//			DBCursor cursor = table.find();
			//			while(cursor.hasNext()) {
			//				int id = (int) cursor.next().get("_id");
			//				
			//				int i = (int) cursor.curr().get("NomeVariavel");
			//				System.out.println("ID: " + id + "Valor: " + i);
			//				
			//				medicoesTemp.put(id, i);
			//				
			//				System.out.println("Inserção no hashmap com sucesso!");
			//	
			//			}

			//			while (myRs.next()) {
			//				System.out.println(myRs.getInt(1));
			//				if (!medicoesTemp.containsKey(myRs.getInt(1))) {
			//					myStmt.executeUpdate("insert into medicoes_luminosidade_e_temperatura(ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values ("+medicoesTemp.get(myRs.getInt(1))+", 0)");
			//					System.out.println("Insert success!");
			//				}
			//			}

			//			medicoes.clear();
			//mongoClient.close();

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}

}


