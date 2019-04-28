package gui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FuncionalidadesInvestigador {

	Connection myConn = null;

	public FuncionalidadesInvestigador() {

	}


	@SuppressWarnings("deprecation")
	public void login(JTextField usernameText, JPasswordField passwordText) {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String userU = usernameText.getText();
			String password = passwordText.getText();

			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas?noAccessToProcedureBodies=true", userU, password);

			System.out.println("Connected successfully!");


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inserirCultura(JTextField nomeCultura, JTextField descricaoCultura) {

		try {

			String nomeCult = nomeCultura.getText();
			String descricaoCult = descricaoCultura.getText();

			CallableStatement cs = myConn.prepareCall("{call inserirCultura(?,?)}");
			cs.setString(1, nomeCult);
			cs.setString(2, descricaoCult);

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inserirMedicao(JTextField valorMedicaoText, JTextField nomeCulturaText, JTextField nomeVariavelText) {

		try {

			CallableStatement cs = myConn.prepareCall("{call inserirMedicao(?,?,?)}");
			cs.setString(1, valorMedicaoText.getText());
			cs.setString(2, nomeCulturaText.getText());
			cs.setString(3, nomeVariavelText.getText());

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("cultura não existe");
			e.printStackTrace();
		}
	}


	public void apagarCultura(String nomeCult) {

		try {

			CallableStatement cs = myConn.prepareCall("{call apagarCultura(?)}");
			cs.setString(1, nomeCult);

			cs.execute();
			System.out.println("Delete success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarCulturaNome(JTextField nomeCA,  JTextField nomeC) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarCulturaNome(?)}");
			cs.setString(1, nomeC.getText());
			cs.setString(2, nomeCA.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void alterarCulturaDescricao(JTextField nomeC,  JTextField descrC) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarCulturaDescricao(?,?)}");
			cs.setString(1, nomeC.getText());
			cs.setString(2, descrC.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void alterarCulturaUtilizador(JTextField nomeC,  JTextField utilNome) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarCulturaUtilizador(?,?)}");
			cs.setString(1, utilNome.getText());
			cs.setString(2, nomeC.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inserirVariavelMedida(JTextField limiteInferiorText, JTextField limiteSuperiorText, JTextField nomeCulturaText, JTextField nomeVariavelText) {

		try {

			CallableStatement cs = myConn.prepareCall("{call inserirVariavelMedida(?,?,?,?)}");
			cs.setString(1, nomeVariavelText.getText());
			cs.setString(2, nomeCulturaText.getText());
			cs.setString(3, limiteInferiorText.getText());
			cs.setString(4, limiteSuperiorText.getText());

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void apagarVariavelMedida(JTextField nomeC, JTextField nomeV) {

		try {

			CallableStatement cs = myConn.prepareCall("{call apagarVariavelMedida(?,?)}");
			cs.setString(1, nomeV.getText());
			cs.setString(2, nomeC.getText());

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void apagarMedicao(JTextField nomeC, JTextField nomeV) {

		try {

			CallableStatement cs = myConn.prepareCall("{call apagarMedicao(?,?)}");
			cs.setString(1, nomeV.getText());
			cs.setString(2, nomeC.getText());

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarVariavelMedidaLimiteInferior(JTextField limiteIText, JTextField nomeC, JTextField nomeV) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaLimiteInferior(?,?,?)}");
			cs.setString(1, limiteIText.getText());
			cs.setString(2, nomeC.getText());
			cs.setString(3, nomeV.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarVariavelMedidaLimiteSuperior(JTextField limiteSText, JTextField nomeC, JTextField nomeV) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaLimiteSuperior(?,?,?)}");
			cs.setString(1, limiteSText.getText());
			cs.setString(2, nomeC.getText());
			cs.setString(3, nomeV.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarVariavelMedidaCultura(JTextField nomeCA, JTextField nomeC, JTextField nomeV) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaCultura(?,?,?)}");
			cs.setString(1, nomeC.getText());
			cs.setString(2, nomeCA.getText());
			cs.setString(3, nomeV.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void alterarVariavelMedidaVariavel(JTextField nomeVA, JTextField nomeC, JTextField nomeV) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaVariavel(?,?,?)}");
			cs.setString(1, nomeV.getText());
			cs.setString(2, nomeVA.getText());
			cs.setString(3, nomeC.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void filtrarVariaveisMedidasTudo() {

		try {

			CallableStatement cs = myConn.prepareCall("{call filtrarVariaveisMedidasTudo()}");
			cs.execute();

			ResultSet myRs = cs.getResultSet();

			while(myRs.next()) {
				System.out.println(myRs.getString("LimiteInferior"));
				System.out.println(myRs.getString("LimiteSuperior"));
				System.out.println(myRs.getString("NomeCultura"));
				System.out.println(myRs.getString("NomeVariavel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void filtrarVariaveisMedidas(JTextField nomeC, JTextField nomeVar) {

		try {

			CallableStatement cs = myConn.prepareCall("{call filtrarVariaveisMedidas(?,?)}");
			cs.setString(1, nomeC.getText());
			cs.setString(2, nomeVar.getText());
			cs.execute();

			ResultSet myRs = cs.getResultSet();

			while(myRs.next()) {
				System.out.println(myRs.getString("LimiteInferior"));
				System.out.println(myRs.getString("LimiteSuperior"));
				System.out.println(myRs.getString("NomeCultura"));
				System.out.println(myRs.getString("NomeVariavel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void consultarVariaveisInvestigador() {

		try {

			CallableStatement cs = myConn.prepareCall("{call consultarVariaveisInv()}");
			cs.execute();

			ResultSet myRs = cs.getResultSet();

			while(myRs.next()) {
				System.out.println(myRs.getString("NomeVariavel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void filtrarCulturaTudo() {

		try {

			CallableStatement cs = myConn.prepareCall("{call filtrarCulturaTudo()}");
			cs.execute();

			ResultSet myRs = cs.getResultSet();

			while(myRs.next()) {
				System.out.println(myRs.getString("NomeCultura"));
				System.out.println(myRs.getString("DescricaoCultura"));
				System.out.println(myRs.getString("NomeUtilizador"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void filtrarCultura(JTextField nomeC) {

		try {

			CallableStatement cs = myConn.prepareCall("{call filtrarCultura(?)}");
			cs.setString(1, nomeC.getText());
			cs.execute();

			ResultSet myRs = cs.getResultSet();

			while(myRs.next()) {
				System.out.println(myRs.getString("NomeCultura"));
				System.out.println(myRs.getString("DescricaoCultura"));
				System.out.println(myRs.getString("NomeUtilizador"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void filtrarMedicaoTudo() {

		try {

			CallableStatement cs = myConn.prepareCall("{call filtrarMedicaoTudo()}");
			cs.execute();

			ResultSet myRs = cs.getResultSet();

			while(myRs.next()) {
				System.out.println(myRs.getString("NomeCultura"));
				System.out.println(myRs.getString("NomeVariavel"));
				System.out.println(myRs.getString("DataHoraMedicao"));
				System.out.println(myRs.getString("ValorMedicao"));
				System.out.println(myRs.getString("LimiteInferior"));
				System.out.println(myRs.getString("LimiteSuperior"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void filtrarMedicao(JTextField nomeC, JTextField nomeVar) {

		try {

			CallableStatement cs = myConn.prepareCall("{call filtrarMedicao(?,?)}");
			cs.setString(1, nomeC.getText());
			cs.setString(2, nomeVar.getText());
			cs.execute();

			ResultSet myRs = cs.getResultSet();

			while(myRs.next()) {
				System.out.println(myRs.getString("NomeCultura"));
				System.out.println(myRs.getString("NomeVariavel"));
				System.out.println(myRs.getString("DataHoraMedicao"));
				System.out.println(myRs.getString("ValorMedicao"));
				System.out.println(myRs.getString("LimiteInferior"));
				System.out.println(myRs.getString("LimiteSuperior"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


}
