package gui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FuncionalidadesAdmin {

	Connection myConn = null;

	public FuncionalidadesAdmin() {

	}


	@SuppressWarnings("deprecation")
	public void login(JTextField usernameText, JPasswordField passwordText) {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", usernameText.getText(), passwordText.getText());
			System.out.println("Connected successfully!");


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logout() {

		try {
			myConn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("deprecation")
	public void inserirAdmin(JTextField usernameText, JTextField adminEmailText, JPasswordField passwordText) {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", "root", "root");

			CallableStatement cs = myConn.prepareCall("{call inserirAdmin(?,?,?)}");			
			cs.setString(1, adminEmailText.getText());
			cs.setString(2, usernameText.getText());
			cs.setString(3, passwordText.getText());

			cs.execute();
			System.out.println("Insert success!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@SuppressWarnings("deprecation")
	public void inserirInvestigador(JTextField utilNameText, JTextField utilEmailText, JPasswordField passwordText) {

		try {

			CallableStatement cs = myConn.prepareCall("{call inserirInvestigador(?,?,?)}");
			cs.setString(1, utilEmailText.getText());
			cs.setString(2, utilNameText.getText());
			cs.setString(3, passwordText.getText());

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inserirVariavel(JTextField nomeVariavelText) {

		try {

			CallableStatement cs = myConn.prepareCall("{call inserirVariavel(?)}");			
			cs.setString(1, nomeVariavelText.getText());

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarUtilizadorEmail(JTextField nomeUser, int idU) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorEmail(?)}");
			cs.setString(1, nomeUser.getText());
			cs.setInt(2, idU);

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void alterarUtilizadorNome(JTextField nomeUserA, int idU) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorNome(?,?)}");
			cs.setString(1, nomeUserA.getText());
			cs.setInt(2, idU);

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void alterarUtilizadorPass(JTextField pass, int idU) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorPass(?,?)}");
			cs.setString(1, pass.getText());
			cs.setInt(2, idU);

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void apagarUtilizador(JTextField emailUtil, JTextField nomeUtil) {

		try {

			CallableStatement cs = myConn.prepareCall("{call apagarUtilizador(?,?)}");
			cs.setString(1, emailUtil.getText());
			cs.setString(2, nomeUtil.getText());

			cs.execute();
			System.out.println("Delete success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void apagarVariavel(int rowIndex) {

		try {

			CallableStatement cs = myConn.prepareCall("{call apagarVariavel(?)}");			
			cs.setInt(1, rowIndex);

			cs.execute();
			System.out.println("Delete success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarVariavelNome(JTextField nomeVarA, int idV) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarVariavel(?,?)}");
			cs.setString(1, nomeVarA.getText());
			cs.setInt(2, idV);

			cs.execute();
			System.out.println("Insert success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarUtilizadorEmail(JTextField nomeUser, JTextField eMailUser) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorEmail(?)}");
			cs.setString(1, eMailUser.getText());
			cs.setString(2, nomeUser.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void alterarUtilizadorNome(JTextField nomeUserA, JTextField nomeUser) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorNome(?,?)}");
			cs.setString(1, nomeUser.getText());
			cs.setString(2, nomeUserA.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@SuppressWarnings("deprecation")
	public void alterarUtilizadorPass(JTextField user, JPasswordField passwordText) {

		try {

			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorPass(?,?)}");
			cs.setString(1, user.getText());
			cs.setString(2, passwordText.getText());

			cs.execute();
			System.out.println("Change success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void apagarVariavel(JTextField nomeVar) {

		try {

			CallableStatement cs = myConn.prepareCall("{call apagarVariavel(?)}");			
			cs.setString(1, nomeVar.getText());

			cs.execute();
			System.out.println("Delete success!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet consultarUtilizadores() {

		ResultSet utilizadores = null;

		try {

			CallableStatement cs = myConn.prepareCall("{call consultarUtilizadores()}");
			cs.execute();

			utilizadores = cs.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utilizadores;
	}

	public ResultSet consultarVariaveis() {

		ResultSet variaveis = null;
		
		try {

			CallableStatement cs = myConn.prepareCall("{call consultarVariaveis()}");
			cs.execute();

			variaveis = cs.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return variaveis;
	}

	public ResultSet consultarVariaveisMedidas() {

		ResultSet variaveisMedidas = null;
		
		try {

			CallableStatement cs = myConn.prepareCall("{call consultarVariaveisMedidas()}");
			cs.execute();

			variaveisMedidas = cs.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return variaveisMedidas;
	}

	public ResultSet consultarMedicoes() {
		
		ResultSet medicoes = null;

		try {

			CallableStatement cs = myConn.prepareCall("{call consultarMedicoes()}");
			cs.execute();

			medicoes = cs.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medicoes;
	}

	public ResultSet consultarCulturas() {

		ResultSet culturas = null;
		
		try {

			CallableStatement cs = myConn.prepareCall("{call consultarCulturas()}");
			cs.execute();

			culturas = cs.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return culturas;
	}
}
