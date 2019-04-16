package sensores_temp_luz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoMigration {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Conectar à base de dados monotorizacao_de_culturas
		try {
			Class.forName("com.mysql.jdbc.Driver");

			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			System.out.println("Connected successfully!");

			Statement myStmt = myConn.createStatement();

			DB db = mongoClient.getDB("db_demo");
			DBCollection table = db.getCollection("collection_demo");
			//DBObject query = new BasicDBObject();
			DBCursor cursor = table.find();
			//int i = 0;
			while(cursor.hasNext()) {
				int i = (Integer) cursor.next().get("NomeVariavel");
				System.out.println(i);

				myStmt.executeUpdate("insert into medicoes_luminosidade_e_temperatura(ValorMedicaoTemperatura, ValorMedicaoLuminosidade) values ("+i+", 0)");
				System.out.println("Insert success!");
			}

			//	System.out.println(s);
			//}
			mongoClient.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println("Out while successfully!");


	}
}


