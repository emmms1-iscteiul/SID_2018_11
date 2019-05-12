package gui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * Funcionalidades Admin
 * @author Eduardo
 *
 */
public class FuncionalidadesAdmin {

	Connection myConn = null;

	public FuncionalidadesAdmin() {

	}

/**
 * Login Admin
 * @param usernameText
 * @param passwordText
 * @param frame
 */
	@SuppressWarnings("deprecation")
	public void login(JTextField usernameText, JPasswordField passwordText, JFrame frame) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas", usernameText.getText(), passwordText.getText());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Password errada", "ERRO - TENTE OUTRA PASSWORD", JOptionPane.ERROR_MESSAGE);
			
		}
	}
/**
 * Logout Admin
 */
	public void logout() {

		try {
			myConn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

/**
 * Inserir Admin
 * @param usernameText
 * @param adminEmailText
 * @param passwordText
 */
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Inserir Investigador
 * @param utilNameText
 * @param utilEmailText
 * @param passwordText
 * @param frame
 */
	@SuppressWarnings("deprecation")
	public void inserirInvestigador(JTextField utilNameText, JTextField utilEmailText, JPasswordField passwordText, JFrame frame) {

		try {
			CallableStatement cs = myConn.prepareCall("{call inserirInvestigador(?,?,?)}");
			cs.setString(1, utilEmailText.getText());
			cs.setString(2, utilNameText.getText());
			cs.setString(3, passwordText.getText());
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Email ou UserName já existem", "ERRO - TENTE OUTRO USER OU EMAIL", JOptionPane.ERROR_MESSAGE);
		}
	}
/**
 * Inserir Variavel
 * @param nomeVariavelText
 */
	public void inserirVariavel(JTextField nomeVariavelText) {

		try {
			CallableStatement cs = myConn.prepareCall("{call inserirVariavel(?)}");			
			cs.setString(1, nomeVariavelText.getText());
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * Alterar Email Utilizador
 * @param nomeUser
 * @param idU
 */
	public void alterarUtilizadorEmail(JTextField nomeUser, int idU) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorEmail(?,?)}");
			cs.setString(1, nomeUser.getText());
			cs.setInt(2, idU);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Alterar Nome Utilizador
 * @param nomeUserA
 * @param idU
 */
	public void alterarUtilizadorNome(JTextField nomeUserA, int idU) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorNome(?,?)}");
			cs.setString(1, nomeUserA.getText());
			cs.setInt(2, idU);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Alterar Pass Utilizador
 * @param pass
 * @param idU
 */
	public void alterarUtilizadorPass(JTextField pass, int idU) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarUtilizadorPass(?,?)}");
			cs.setString(1, pass.getText());
			cs.setInt(2, idU);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Apagar Utilizador
 * @param rowIndex
 */
	public void apagarUtilizador(int rowIndex) {

		try {
			CallableStatement cs = myConn.prepareCall("{call apagarUtilizador(?)}");
			cs.setInt(1, rowIndex);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Apagar variavel
 * @param rowIndex
 */
	public void apagarVariavel(int rowIndex) {

		try {
			CallableStatement cs = myConn.prepareCall("{call apagarVariavel(?)}");			
			cs.setInt(1, rowIndex);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * ALterar nome variavel
 * @param nomeVarA
 * @param idV
 */
	public void alterarVariavelNome(JTextField nomeVarA, int idV) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarVariavelNome(?,?)}");
			cs.setString(1, nomeVarA.getText());
			cs.setInt(2, idV);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Consultar utilizadores
 * @return
 */
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
/**
 * Consultar variaveis
 * @return
 */
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
/**
 * COnsultar variaveis medidas
 * @return
 */
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
/**
 * Consultar medições
 * @return
 */
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
/**
 * Consultar Culturas
 * @return
 */
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
/**
 * Conexão
 * @return
 */
	public Connection getConnection() {
		return myConn;
	}
}
