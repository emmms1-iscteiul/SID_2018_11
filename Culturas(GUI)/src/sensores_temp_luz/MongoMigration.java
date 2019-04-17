package sensores_temp_luz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			Map<Integer, Integer> medicoesLum = new HashMap<Integer,Integer>();

			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			System.out.println("Connected successfully!");

			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("select IDMedicaoLuminosidadeTemperatura from medicoes_luminosidade_e_temperatura");

			DB db = mongoClient.getDB("db_demo");
			DBCollection table = db.getCollection("collection_demo");
			DBCursor cursor = table.find();
			while(cursor.hasNext()) {
				int id = (int) cursor.next().get("_id");
				
				int i = (int) cursor.curr().get("NomeVariavel");
				System.out.println("ID: " + id + "Valor: " + i);
				
				medicoesTemp.put(id, i);
				
				System.out.println("Inserção no hashmap com sucesso!");
	
			}
			
			while (myRs.next()) {
				System.out.println(myRs.getInt(1));
				if (!medicoesTemp.containsKey(myRs.getInt(1))) {
					myStmt.executeUpdate("insert into medicoes_luminosidade_e_temperatura(ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values ("+medicoesTemp.get(myRs.getInt(1))+", 0)");
					System.out.println("Insert success!");
				}
			}
	
//			medicoes.clear();
			mongoClient.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} 


	}
}


