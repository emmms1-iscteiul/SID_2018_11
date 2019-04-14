package sensores_temp_luz;

import java.sql.*;

public class MongoMigration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Conectar à base de dados monotorizacao_de_culturas
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");
			System.out.println("Connected successfully!");

			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("select NomeVariavel from variaveis");
			
			while (myRs.next()) {
				//System.out.println(myRs.getString("NomeVariavel"));
				if (myRs.isLast()) {
					myStmt.executeUpdate("insert into variaveis(NomeVariavel, EmailUtilizador) values ('vitaminaC', 'root@iscte-iul.pt')");
					System.out.println("Insert success!");
					break;
				}
			}
			
			System.out.println("Out while successfully!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


