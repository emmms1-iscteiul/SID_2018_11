package testes;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Test;

import gui.FuncionalidadesAdmin;
/**
 * Teste Funcionalidades do Admin
 * @author Eduardo
 *
 */
public class FuncionalidadesAdminTest {

	FuncionalidadesAdmin test = new FuncionalidadesAdmin();
/**
 * Teste login	
 */
	@Test
	public void testLogin() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
	}
/**
 * Teste logout	
 */
	@Test
	public void testLogout() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		test.logout();
		Assert.assertNotNull(test.getConnection());
	}
/**
 * Test inserir admin	
 */
	@Test
	public void testInserirAdmin() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		JTextField usernameTextN = new JTextField("admin");
		JTextField adminEmailText = new JTextField("adminN@iscte.pt");
		JPasswordField passwordTextN = new JPasswordField("admin");
		test.inserirAdmin(usernameTextN, adminEmailText, passwordTextN);
		Assert.assertNotNull(usernameTextN.getText());
		Assert.assertNotNull(adminEmailText.getText());
		Assert.assertNotNull(passwordTextN.getPassword());
	}
/**
 * Teste inserir investigador
 */
	@Test
	public void testInserirInvestigador() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		JTextField utilNameText = new JTextField("user");
		JTextField utilEmailText = new JTextField("userN@iscte.pt");
		JPasswordField passwordTextI = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.inserirInvestigador(utilNameText, utilEmailText, passwordTextI, frame );
		Assert.assertNotNull(test.getConnection());
		Assert.assertNotNull(utilNameText.getText());
		Assert.assertNotNull(utilEmailText.getText());
		Assert.assertNotNull(passwordTextI.getPassword());
		Assert.assertNotNull(frame);
	}
/**
 * Teste inserir variavel	
 */
	@Test
	public void testInserirVariavel() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeVariavelText = new JTextField("variavel");
		test.inserirVariavel(nomeVariavelText);
		Assert.assertNotNull(nomeVariavelText.getText());
	}
/**
 * Teste alterar email utilizador	
 */
	@Test
	public void testAlterarUtilizadorEmail() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeUser = new JTextField("emailNovo@iscte.pt");
		int idU = 1;
		test.alterarUtilizadorEmail(nomeUser, idU);
		Assert.assertNotNull(nomeUser.getText());
		Assert.assertNotNull(idU);
		Assert.assertNotEquals(0, idU);
	}
/**
 * Teste alterar nome utilizador	
 */
	@Test
	public void testAlterarUtilizadorNome() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeUserA = new JTextField("nomeNovo");
		int idU = 1;
		test.alterarUtilizadorNome(nomeUserA, idU);;
		Assert.assertNotNull(nomeUserA.getText());
		Assert.assertNotNull(idU);
		Assert.assertNotEquals(0, idU);
	}
/**
 * Teste alterar pass utilizador 
 */
	@Test
	public void testAlterarUtilizadorPass() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		JTextField pass = new JTextField("passNova");
		int idU = 1;
		test.alterarUtilizadorPass(pass, idU);;
		Assert.assertNotNull(pass.getText());
		Assert.assertNotNull(idU);
		Assert.assertNotEquals(0, idU);
	}
/**
 * Teste apagar utilizador	
 */
	@Test
	public void testApagarUtilizador() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		int rowIndex = 1;
		test.apagarUtilizador(rowIndex);
		Assert.assertNotNull(rowIndex);
		Assert.assertNotEquals(0, rowIndex);
	}
/**
 * Teste apagar variavel	
 */
	@Test
	public void testApagarVariavel() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		int rowIndex = 1;
		test.apagarVariavel(rowIndex);
		Assert.assertNotNull(rowIndex);
		Assert.assertNotEquals(0, rowIndex);
	}
/**
 * Teste alterar nome variavel	
 */
	@Test
	public void testAlterarVariavelNome() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeVarA = new JTextField("nomeVarNovo");
		int idV = 1;
		test.alterarVariavelNome(nomeVarA, idV);
		Assert.assertNotNull(nomeVarA.getText());
		Assert.assertNotNull(idV);
		Assert.assertNotEquals(0, idV);
	}
/**
 * Teste consultar utilizadores	
 */
	@Test
	public void testConsultarUtilizadores() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.consultarUtilizadores();
		Assert.assertNotNull(output);
	}
/**
 * Teste consultar variaveis	
 */
	@Test
	public void testConsultarVariaveis() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.consultarVariaveis();
		Assert.assertNotNull(output);
	}
/**
 * Teste consultar variaveis medidas	
 */
	@Test
	public void testConsultarVariaveisMedidas() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.consultarVariaveisMedidas();
		Assert.assertNotNull(output);
	}
/**
 * Teste consultar medições	
 */
	@Test
	public void testConsultarMedicoes() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.consultarMedicoes();
		Assert.assertNotNull(output);
	}
/**
 * Teste consultar culturas	
 */
	@Test
	public void testConsultarCulturas() {
		JTextField usernameText = new JTextField("root");
		JPasswordField passwordText = new JPasswordField("root");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.consultarCulturas();
		Assert.assertNotNull(output);
	}
}
