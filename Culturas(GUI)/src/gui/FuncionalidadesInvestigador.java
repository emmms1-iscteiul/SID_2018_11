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

public class FuncionalidadesInvestigador {

	Connection myConn = null;

	public FuncionalidadesInvestigador() {

	}


	@SuppressWarnings("deprecation")
	public void login(JTextField usernameText, JPasswordField passwordText) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monotorizacao_de_culturas?noAccessToProcedureBodies=true", usernameText.getText(), passwordText.getText());
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

	public void inserirCultura(JTextField nomeCultura, JTextField descricaoCultura) {

		try {
			CallableStatement cs = myConn.prepareCall("{call inserirCultura(?,?)}");
			cs.setString(1, nomeCultura.getText());
			cs.setString(2, descricaoCultura.getText());
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}

	public void inserirMedicao(JTextField valorMedicaoText, JTextField nomeCulturaText, JTextField nomeVariavelText, JFrame frame) {

		try {
			CallableStatement cs = myConn.prepareCall("{call inserirMedicao(?,?,?)}");
			cs.setString(1, valorMedicaoText.getText());
			cs.setString(2, nomeCulturaText.getText());
			cs.setString(3, nomeVariavelText.getText());
			cs.execute();
			JOptionPane.showMessageDialog(frame, "Inserção com sucesso");
			frame.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Verificar as Variáveis e Culturas que existem antes de inserir uma medição", "ERRO - NECESSÁRIO PREENCHER UMA VARIAVEL MEDIDA PRIMEIRO", JOptionPane.ERROR_MESSAGE);
		}
	}


	public void apagarCultura(int rowIndex) {

		try {
			CallableStatement cs = myConn.prepareCall("{call apagarCultura(?)}");
			cs.setInt(1, rowIndex);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarCulturaNome(JTextField nomeC, int idC) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarCulturaNome(?,?)}");
			cs.setString(1, nomeC.getText());
			cs.setInt(2, idC);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void alterarCulturaDescricao(JTextField descrC, int idC) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarCulturaDescricao(?,?)}");
			cs.setString(1, descrC.getText());
			cs.setInt(2, idC);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void alterarCulturaUtilizador(JTextField utilNome, int idC) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarCulturaUtilizador(?,?)}");
			cs.setString(1, utilNome.getText());
			cs.setInt(2, idC);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inserirVariavelMedida(JTextField limiteInferiorText, JTextField limiteSuperiorText, JTextField regularidadeLimiteInferiorText, JTextField regularidadeLimiteSuperiorText, JTextField nomeCulturaText, JTextField nomeVariavelText, JFrame frame) {

		try {
			CallableStatement cs = myConn.prepareCall("{call inserirVariavelMedida(?,?,?,?,?,?)}");
			cs.setString(1, limiteInferiorText.getText());
			cs.setString(2, limiteSuperiorText.getText());
			cs.setString(3, nomeVariavelText.getText());
			cs.setString(4, nomeCulturaText.getText());
			cs.setString(5, regularidadeLimiteInferiorText.getText());
			cs.setString(6, regularidadeLimiteSuperiorText.getText());
			cs.execute();
			JOptionPane.showMessageDialog(frame, "Inserção com sucesso");
			frame.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Verifique as Culturas e Variáveis que existem nas suas tabelas antes de inserir", "ERRO - NECESSÁRIO EXISTENCIA DE VARIAVEL E CULTURA PRIMEIRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void alterarVariavelMedidaLimiteInferior(JTextField limiteIText, int idVM) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaLimiteInferior(?,?)}");
			cs.setString(1, limiteIText.getText());
			cs.setInt(2, idVM);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarVariavelMedidaLimiteSuperior(JTextField limiteSText, int idVM) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaLimiteSuperior(?,?)}");
			cs.setString(1, limiteSText.getText());
			cs.setInt(2, idVM);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarVariavelMedidaAlertaLimiteSuperior(JTextField regularidadeLimiteSText, int idVM) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaLimiteSuperior(?,?)}");
			cs.setString(1, regularidadeLimiteSText.getText());
			cs.setInt(2, idVM);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarVariavelMedidaAlertaLimiteInferior(JTextField regularidadeLimiteIText, int idVM) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaLimiteSuperior(?,?)}");
			cs.setString(1, regularidadeLimiteIText.getText());
			cs.setInt(2, idVM);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarVariavelMedidaCultura(JTextField nomeCA, int idVM) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaCultura(?,?)}");
			cs.setString(1, nomeCA.getText());
			cs.setInt(2, idVM);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void alterarVariavelMedidaVariavel(JTextField nomeVA, int idVM) {

		try {
			CallableStatement cs = myConn.prepareCall("{call alterarVariavelMedidaVariavel(?,?)}");
			cs.setString(1, nomeVA.getText());
			cs.setInt(2, idVM);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public ResultSet filtrarVariaveisMedidasTudo() {

		ResultSet variaveisMedidasT = null;
		
		try {
			CallableStatement cs = myConn.prepareCall("{call filtrarVariaveisMedidasTudo()}");
			cs.execute();
			variaveisMedidasT = cs.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return variaveisMedidasT;
	}
	
	public ResultSet consultarVariaveisInvestigador() {

		ResultSet variaveisInv = null;
		
		try {
			CallableStatement cs = myConn.prepareCall("{call consultarVariaveisInvestigador()}");
			cs.execute();
			variaveisInv = cs.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return variaveisInv;
	}
	
	public ResultSet filtrarCulturaTudo() {

		ResultSet culturaT = null;
		
		try {
			CallableStatement cs = myConn.prepareCall("{call filtrarCulturaTudo()}");
			cs.execute();
			culturaT = cs.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return culturaT;
	}
	
	public ResultSet filtrarMedicaoTudo() {

		ResultSet medicaoT = null;
		
		try {
			CallableStatement cs = myConn.prepareCall("{call filtrarMedicaoTudo()}");
			cs.execute();
			medicaoT = cs.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medicaoT;
	}
	
	public ResultSet filtrarAlertasCultura() {

		ResultSet alertasC = null;
		
		try {
			CallableStatement cs = myConn.prepareCall("{call filtrarAlertasCultura()}");
			cs.execute();
			alertasC = cs.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alertasC;
	}
	
	public Connection getConnection() {
		return myConn;
	}
	
}
