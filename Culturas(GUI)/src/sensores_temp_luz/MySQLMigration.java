package sensores_temp_luz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


	private BasicDBObject medicaoAntiga = null;
	private double lRAST = 0;
	private double lRAIT = 0;
	private double lRASL = 0;
	private double lRAIL = 0;

	private int contadorDiferencasTemp = 0;
	private int contadorMedicoesTemp = 0;
	private int contadorDiferencasLumin = 0;
	private int contadorMedicoesLumin = 0;
	
	
	private double limiteSuperiorTemp = 0;
	private double limiteInferiorTemp = 0;
	private double limiteSuperiorLumin = 0;
	private double limiteInferiorLumin = 0;

	private double difTemp = 0;
	private double difLumin = 0;

	public MySQLMigration(MongoMigration mongoM,MySemaphore semaphore) {
		this.mongoM = mongoM;
		this.semaphore=semaphore;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas"
					+ "?noAccessToProcedureBodies=true", "sensor",
					"sensor");

			CallableStatement cs3 = myConn.prepareCall("{call obterLimitesSistema()}");
			cs3.execute();
			ResultSet limites = cs3.getResultSet();
			while (limites.next()) {
				limiteSuperiorTemp = Double.valueOf(limites.getObject("LimiteSuperiorTemperatura").toString());
				System.out.println(limiteSuperiorTemp);
				limiteInferiorTemp = Double.valueOf(limites.getObject("LimiteInferiorTemperatura").toString());
				System.out.println(limiteInferiorTemp);
				limiteSuperiorLumin = Double.valueOf(limites.getObject("LimiteSuperiorLuminosidade").toString());
				System.out.println(limiteSuperiorLumin);
				limiteInferiorLumin = Double.valueOf(limites.getObject("LimiteInferiorLuminosidade").toString());
				System.out.println(limiteInferiorLumin);
			}
			difTemp = (limiteSuperiorTemp - limiteInferiorTemp)/2;
			System.out.println(difTemp);
			difLumin = (limiteSuperiorLumin - limiteInferiorLumin)/2;
			System.out.println(difLumin);
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

		
		while(!interrupted())	{
			semaphore.acquire();

			DBCursor cursor = mongoM.getValuesMongoMedicoes();

			try {
				CallableStatement cs = myConn.prepareCall("{call obterMinimoLimiteSuperior()}");
				cs.execute();
				ResultSet limiteS = cs.getResultSet();

				while (limiteS.next()) {
					lRAST = Double.valueOf(limiteS.getObject("MIN(LimiteSuperior)").toString());
					System.out.println(lRAST);
				}

				CallableStatement cs2 = myConn.prepareCall("{call obterMaximoLimiteInferior()}");
				cs2.execute();
				ResultSet limiteI = cs2.getResultSet();

				while (limiteI.next()) {
					lRAIT = Double.valueOf(limiteI.getObject("MAX(LimiteInferior)").toString());
					System.out.println(lRAIT);
				}
				
				CallableStatement cs4 = myConn.prepareCall("{call obterMinimoLimiteSuperiorLumin()}");
				cs4.execute();
				ResultSet limiteSL = cs4.getResultSet();

				while (limiteSL.next()) {
					lRASL = Double.valueOf(limiteSL.getObject("MIN(LimiteSuperior)").toString());
					System.out.println(lRASL);
				}
			
			
			CallableStatement cs5 = myConn.prepareCall("{call obterMaximoLimiteInferiorLumin()}");
			cs5.execute();
			ResultSet limiteIL = cs5.getResultSet();

			while (limiteIL.next()) {
				lRAIL = Double.valueOf(limiteIL.getObject("MAX(LimiteInferior)").toString());
				System.out.println(lRAIL);
			}
		} catch (SQLException e) {

		}

			while (cursor.hasNext()) {


				String date = (String) cursor.next().get("DataHoraMedicao");
				double temp = (double) cursor.curr().get("Temperatura");
				double lumin = (double) cursor.curr().get("Luminosidade");
				
				if (medicaoAntiga == null) {
					String sqlQuery = "insert into medicao_temperatura_luminosidade(DataHoraMedicao, "
							+ "ValorMedicaoTemperatura, ValorMedicaoLuminosidade)"
							+ " values (?, "
							+ temp + ", " + lumin + ")";

					PreparedStatement stmt;
					try {
						stmt = myConn.prepareStatement(sqlQuery);
						java.sql.Timestamp dateS = Timestamp.valueOf(date);
						stmt.setTimestamp(1, dateS);
						stmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					}

					BasicDBObject exportedDocument = new BasicDBObject();
					exportedDocument.append("DataHoraMedicao", date);
					exportedDocument.append("Temperatura", temp);
					exportedDocument.append("Luminosidade", lumin);
					mongoM.insertValuesMongoSucess(exportedDocument);
					
					System.out.println("My sql Insert success!");
					
					if ((temp < lRAIT) && (temp > limiteInferiorTemp + (limiteInferiorTemp * 0.05))) {
						String sqlQuery8 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
								+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
								+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

						PreparedStatement stmt8;
						try {
							stmt8 = myConn.prepareStatement(sqlQuery8);
							java.sql.Timestamp dateSS = Timestamp.valueOf(date);
							stmt8.setTimestamp(1, dateSS);
							stmt8.setString(2, "Temperatura");
							stmt8.setString(3, "ATENCAO A CHEGAR AO LIMITE INFERIOR");
							stmt8.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if ((temp <= limiteInferiorTemp + (limiteInferiorTemp * 0.95))) {
						String sqlQuery9 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
								+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
								+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

						PreparedStatement stmt9;
						try {
							stmt9 = myConn.prepareStatement(sqlQuery9);
							java.sql.Timestamp dateSS = Timestamp.valueOf(date);
							stmt9.setTimestamp(1, dateSS);
							stmt9.setString(2, "Temperatura");
							stmt9.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE INFERIOR");
							stmt9.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if ((temp <= (limiteSuperiorTemp * 0.95)) && (temp > lRAST)) {
						String sqlQuery10 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
								+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
								+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

						PreparedStatement stmt10;
						try {
							stmt10 = myConn.prepareStatement(sqlQuery10);
							java.sql.Timestamp dateSS = Timestamp.valueOf(date);
							stmt10.setTimestamp(1, dateSS);
							stmt10.setString(2, "Temperatura");
							stmt10.setString(3, "ATENCAO A CHEGAR AO LIMITE SUPERIOR");
							stmt10.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if ((temp > limiteSuperiorTemp * 0.95)) {
						String sqlQuery11 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
								+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
								+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

						PreparedStatement stmt11;
						try {
							stmt11 = myConn.prepareStatement(sqlQuery11);
							java.sql.Timestamp dateSS = Timestamp.valueOf(date);
							stmt11.setTimestamp(1, dateSS);
							stmt11.setString(2, "Temperatura");
							stmt11.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE SUPERIOR");
							stmt11.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						if ((lumin < lRAIL) && (lumin > limiteInferiorLumin + (limiteInferiorLumin * 0.05))) {
							String sqlQuery12 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt12;
							try {
								stmt12 = myConn.prepareStatement(sqlQuery12);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt12.setTimestamp(1, dateSS);
								stmt12.setString(2, "Luminosidade");
								stmt12.setString(3, "ATENCAO A CHEGAR AO LIMITE INFERIOR");
								stmt12.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((lumin <= limiteInferiorLumin + (limiteInferiorLumin * 0.95))) {
							String sqlQuery13 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt13;
							try {
								stmt13 = myConn.prepareStatement(sqlQuery13);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt13.setTimestamp(1, dateSS);
								stmt13.setString(2, "Luminosidade");
								stmt13.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE INFERIOR");
								stmt13.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((lumin <= (limiteSuperiorLumin * 0.95)) && (lumin > lRASL)) {
							String sqlQuery14 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt14;
							try {
								stmt14 = myConn.prepareStatement(sqlQuery14);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt14.setTimestamp(1, dateSS);
								stmt14.setString(2, "Luminosidade");
								stmt14.setString(3, "ATENCAO A CHEGAR AO LIMITE SUPERIOR");
								stmt14.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((lumin > limiteSuperiorLumin * 0.95)) {
							String sqlQuery15 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt15;
							try {
								stmt15 = myConn.prepareStatement(sqlQuery15);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt15.setTimestamp(1, dateSS);
								stmt15.setString(2, "Luminosidade");
								stmt15.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE SUPERIOR");
								stmt15.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

				} else {
					double diferTemp = Math.abs(temp - medicaoAntiga.getDouble("Temperatura"));
					double diferLumin = Math.abs(lumin-medicaoAntiga.getDouble("Luminosidade"));
					if (contadorMedicoesTemp < 3) {
						if (contadorDiferencasTemp >= 1) {
							contadorMedicoesTemp++;
						}
						if (diferTemp >= difTemp) {
							if (contadorDiferencasTemp == 0) {
								contadorMedicoesTemp++;
							}
							contadorDiferencasTemp++;
							if (contadorDiferencasTemp == 2) {
								System.out.println("Anomalia - " + medicaoAntiga);

								String sqlQuery7 = "insert into anomalias(NomeVariavel, Valor, DataHora)"
										+ " values (?, "+ medicaoAntiga.getDouble("Temperatura") + ", ?)";

								PreparedStatement stmt7;
								try {
									stmt7 = myConn.prepareStatement(sqlQuery7);
									java.sql.Timestamp dateA = Timestamp.valueOf(medicaoAntiga.getString("DataHoraMedicao"));
									stmt7.setTimestamp(1, dateA);
									stmt7.setDouble(2, medicaoAntiga.getDouble("Temperatura"));
									stmt7.executeUpdate();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								contadorDiferencasTemp = 0;
								contadorMedicoesTemp = 0;
							}
						}
					} 
					if (contadorMedicoesLumin < 3) {
						if (contadorDiferencasLumin >= 1) {
							contadorMedicoesLumin++;
						}
						if (diferLumin >= difLumin) {
							if (contadorDiferencasLumin == 0) {
								contadorMedicoesLumin++;
							}
							contadorDiferencasLumin++;
							if (contadorDiferencasLumin == 2) {
								System.out.println("Anomalia - " + medicaoAntiga);

								String sqlQuery7 = "insert into anomalias(NomeVariavel, Valor, DataHora)"
										+ " values (?, "+ medicaoAntiga.getDouble("Luminosidade") + ", ?)";

								PreparedStatement stmt7;
								try {
									stmt7 = myConn.prepareStatement(sqlQuery7);
									java.sql.Timestamp dateA = Timestamp.valueOf(medicaoAntiga.getString("DataHoraMedicao"));
									stmt7.setTimestamp(1, dateA);
									stmt7.setDouble(2, medicaoAntiga.getDouble("Luminosidade"));
									stmt7.executeUpdate();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								contadorDiferencasLumin = 0;
								contadorMedicoesLumin = 0;
							}
						}
					} 
					if (contadorMedicoesTemp == 0 || contadorMedicoesTemp == 3) {
						
						contadorMedicoesTemp = 0;
						contadorDiferencasTemp = 0;
						if ((temp < lRAIT) && (temp > limiteInferiorTemp + (limiteInferiorTemp * 0.05))) {
							String sqlQuery8 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

							PreparedStatement stmt8;
							try {
								stmt8 = myConn.prepareStatement(sqlQuery8);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt8.setTimestamp(1, dateSS);
								stmt8.setString(2, "Temperatura");
								stmt8.setString(3, "ATENCAO A CHEGAR AO LIMITE INFERIOR");
								stmt8.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((temp <= limiteInferiorTemp + (limiteInferiorTemp * 0.95))) {
							String sqlQuery9 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

							PreparedStatement stmt9;
							try {
								stmt9 = myConn.prepareStatement(sqlQuery9);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt9.setTimestamp(1, dateSS);
								stmt9.setString(2, "Temperatura");
								stmt9.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE INFERIOR");
								stmt9.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((temp <= (limiteSuperiorTemp * 0.95)) && (temp > lRAST)) {
							String sqlQuery10 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

							PreparedStatement stmt10;
							try {
								stmt10 = myConn.prepareStatement(sqlQuery10);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt10.setTimestamp(1, dateSS);
								stmt10.setString(2, "Temperatura");
								stmt10.setString(3, "ATENCAO A CHEGAR AO LIMITE SUPERIOR");
								stmt10.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((temp > limiteSuperiorTemp * 0.95)) {
							String sqlQuery11 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorTemp + ",  " + limiteInferiorTemp + ", "+temp+", ?)";

							PreparedStatement stmt11;
							try {
								stmt11 = myConn.prepareStatement(sqlQuery11);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt11.setTimestamp(1, dateSS);
								stmt11.setString(2, "Temperatura");
								stmt11.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE SUPERIOR");
								stmt11.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						String sqlQuery = "insert into medicao_temperatura_luminosidade(DataHoraMedicao, "
								+ "ValorMedicaoTemperatura, ValorMedicaoLuminosidade)"
								+ " values (?, "
								+ temp + ", " + lumin + ")";

						PreparedStatement stmt;
						try {
							stmt = myConn.prepareStatement(sqlQuery);
							java.sql.Timestamp dateS = Timestamp.valueOf(date);
							stmt.setTimestamp(1, dateS);
							stmt.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
						}

						BasicDBObject exportedDocument = new BasicDBObject();
						exportedDocument.append("DataHoraMedicao", date);
						exportedDocument.append("Temperatura", temp);
						exportedDocument.append("Luminosidade", lumin);
						mongoM.insertValuesMongoSucess(exportedDocument);
						
						System.out.println("My sql Insert success!");

					}
					if (contadorMedicoesLumin == 0 || contadorMedicoesLumin == 3) {
						contadorMedicoesLumin = 0;
						contadorDiferencasLumin = 0;
						if ((lumin < lRAIL) && (lumin > limiteInferiorLumin + (limiteInferiorLumin * 0.05))) {
							String sqlQuery8 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt8;
							try {
								stmt8 = myConn.prepareStatement(sqlQuery8);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt8.setTimestamp(1, dateSS);
								stmt8.setString(2, "Luminosidade");
								stmt8.setString(3, "ATENCAO A CHEGAR AO LIMITE INFERIOR");
								stmt8.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((lumin <= limiteInferiorLumin + (limiteInferiorLumin * 0.95))) {
							String sqlQuery9 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt9;
							try {
								stmt9 = myConn.prepareStatement(sqlQuery9);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt9.setTimestamp(1, dateSS);
								stmt9.setString(2, "Luminosidade");
								stmt9.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE INFERIOR");
								stmt9.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((lumin <= (limiteSuperiorLumin * 0.95)) && (lumin > lRASL)) {
							String sqlQuery10 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt10;
							try {
								stmt10 = myConn.prepareStatement(sqlQuery10);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt10.setTimestamp(1, dateSS);
								stmt10.setString(2, "Luminosidade");
								stmt10.setString(3, "ATENCAO A CHEGAR AO LIMITE SUPERIOR");
								stmt10.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if ((lumin > limiteSuperiorLumin * 0.95)) {
							String sqlQuery11 = "insert into alertas_globais(DataHoraAlerta, NomeVariavel,"
									+ " LimiteSuperior, LimiteInferior, ValorMedicao, Descricao)"
									+ " values (?, ?, " + limiteSuperiorLumin + ",  " + limiteInferiorLumin + ", "+lumin+", ?)";

							PreparedStatement stmt11;
							try {
								stmt11 = myConn.prepareStatement(sqlQuery11);
								java.sql.Timestamp dateSS = Timestamp.valueOf(date);
								stmt11.setTimestamp(1, dateSS);
								stmt11.setString(2, "Luminosidade");
								stmt11.setString(3, "EM ESTADO CRITICO ATINGIU O LIMITE SUPERIOR");
								stmt11.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				}			


				medicaoAntiga = (BasicDBObject) cursor.curr();
				mongoM.removeValuesMongo((BasicDBObject) cursor.curr());
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
