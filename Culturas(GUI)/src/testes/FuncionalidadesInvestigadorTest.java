package testes;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Test;

import gui.FuncionalidadesInvestigador;
/**
 * Teste Funcionalidades Investigador
 * @author Eduardo
 *
 */
public class FuncionalidadesInvestigadorTest {

	FuncionalidadesInvestigador test = new FuncionalidadesInvestigador();
/**
 * Teste login	
 */
	@Test
	public void testLogin() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
	}
/**
 * Teste logout	
 */
	@Test
	public void testLogout() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		test.logout();
		Assert.assertNotNull(test.getConnection());
	}
/**
 * Teste inserir cultura
 */
	@Test
	public void testInserirCultura() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeCultura = new JTextField("nomeC");
		JTextField descricaoCultura = new JTextField("descricaoC");
		test.inserirCultura(nomeCultura, descricaoCultura);
		Assert.assertNotNull(nomeCultura.getText());
		Assert.assertNotNull(descricaoCultura.getText());
	}
/**
 * Teste inserir medição	
 */
	@Test
	public void testInserirMedicao() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL);
		Assert.assertNotNull(test.getConnection());
		JTextField valorMedicaoText = new JTextField("valorM");
		JTextField nomeCulturaText = new JTextField("nomeC");
		JTextField nomeVariavelText = new JTextField("nomeV");
		JFrame frame = new JFrame();
		test.inserirMedicao(valorMedicaoText, nomeCulturaText, nomeVariavelText, frame);
		Assert.assertNotNull(valorMedicaoText.getText());
		Assert.assertNotNull(nomeCulturaText.getText());
		Assert.assertNotNull(nomeVariavelText.getText());
		Assert.assertNotNull(frame);
	}
/**
 * Teste apagar cultura	
 */
	@Test
	public void testApagarCultura() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		int rowIndex = 1;
		test.apagarCultura(rowIndex);
		Assert.assertNotNull(rowIndex);
		Assert.assertNotEquals(0, rowIndex);
	}
/**
 * Teste alterar nome cultura	
 */
	@Test
	public void testAlterarCulturaNome() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeC = new JTextField("novoNomeC");
		int idC = 2;
		test.alterarCulturaNome(nomeC, idC);
		Assert.assertNotNull(nomeC.getText());
		Assert.assertNotNull(idC);
		Assert.assertNotEquals(0, idC);
	}
/**
 * Teste alterar descrição cultura	
 */
	@Test
	public void testAlterarCulturaDescricao() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField descrC = new JTextField("novaDescricaoC");
		int idC = 2;
		test.alterarCulturaDescricao(descrC, idC);
		Assert.assertNotNull(descrC.getText());
		Assert.assertNotNull(idC);
		Assert.assertNotEquals(0, idC);
	}
/**
 * Teste alterar utilizador cultura	
 */
	@Test
	public void testAlterarCulturaUtilizador() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField utilNome = new JTextField("novoUtilizadorC");
		int idC = 2;
		test.alterarCulturaUtilizador(utilNome, idC);
		Assert.assertNotNull(utilNome.getText());
		Assert.assertNotNull(idC);
		Assert.assertNotEquals(0, idC);
	}
/**
 * Teste inserir variavel medida	
 */
	@Test
	public void testInserirVariavelMedida() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frameL = new JFrame();
		test.login(usernameText, passwordText, frameL);
		Assert.assertNotNull(test.getConnection());
		JTextField limiteInferiorText = new JTextField("10");
		JTextField limiteSuperiorText = new JTextField("30");
		JTextField regularidadeLimiteInferiorText = new JTextField("0.2");
		JTextField regularidadeLimiteSuperiorText = new JTextField("0.8");
		JTextField nomeCulturaText = new JTextField("nomeCT");
		JTextField nomeVariavelText = new JTextField("nomeVT");
		JFrame frame = new JFrame();
		test.inserirVariavelMedida(limiteInferiorText, limiteSuperiorText, regularidadeLimiteInferiorText, regularidadeLimiteSuperiorText, nomeCulturaText, nomeVariavelText, frame);
		Assert.assertNotNull(limiteInferiorText.getText());
		Assert.assertNotNull(limiteSuperiorText.getText());
		Assert.assertNotNull(regularidadeLimiteInferiorText.getText());
		Assert.assertNotNull(regularidadeLimiteSuperiorText.getText());
		Assert.assertNotNull(nomeCulturaText.getText());
		Assert.assertNotNull(nomeVariavelText.getText());
		Assert.assertNotNull(frame);
	}
/**
 * Teste alterar limite inferior variavel medida	
 */
	@Test
	public void testAlterarVariavelMedidaLimiteInfeior() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField limiteIText = new JTextField("15");
		int idVM = 2;
		test.alterarVariavelMedidaLimiteInferior(limiteIText, idVM );
		Assert.assertNotNull(limiteIText.getText());
		Assert.assertNotNull(idVM);
		Assert.assertNotEquals(0, idVM);
	}
/**
 * Teste alterar limite superior variavel medida	
 */
	@Test
	public void testAlterarVariavelMedidaLimiteSuperior() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField limiteSText = new JTextField("35");
		int idVM = 2;
		test.alterarVariavelMedidaLimiteSuperior(limiteSText, idVM);
		Assert.assertNotNull(limiteSText.getText());
		Assert.assertNotNull(idVM);
		Assert.assertNotEquals(0, idVM);
	}
/**
 * Teste alterar limite superior alerta variavel medida	
 */
	@Test
	public void testAlterarVariavelMedidaAlertaLimiteSuperior() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField regularidadeLimiteSText = new JTextField("0.9");
		int idVM = 2;
		test.alterarVariavelMedidaAlertaLimiteSuperior(regularidadeLimiteSText, idVM);
		Assert.assertNotNull(regularidadeLimiteSText.getText());
		Assert.assertNotNull(idVM);
		Assert.assertNotEquals(0, idVM);
	}
/**
 * Teste alterar limite inferior alerta variavel medida	
 */
	@Test
	public void testAlterarVariavelMedidaAlertaLimiteInferior() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField regularidadeLimiteIText = new JTextField("0.1");
		int idVM = 2;
		test.alterarVariavelMedidaAlertaLimiteInferior(regularidadeLimiteIText, idVM);
		Assert.assertNotNull(regularidadeLimiteIText.getText());
		Assert.assertNotNull(idVM);
		Assert.assertNotEquals(0, idVM);
	}
/**
 * Teste alterar variavel medida cultura	
 */
	@Test
	public void testAlterarVariavelMedidaCultura() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeCA = new JTextField("novoNomeVMC");
		int idVM = 2;
		test.alterarVariavelMedidaCultura(nomeCA, idVM);
		Assert.assertNotNull(nomeCA.getText());
		Assert.assertNotNull(idVM);
		Assert.assertNotEquals(0, idVM);
	}
/**
 * Teste alterar variavel medida variavel	
 */
	@Test
	public void testAlterarVariavelMedidaVariavel() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		JTextField nomeVA = new JTextField("novoNomeVMV");
		int idVM = 2;
		test.alterarVariavelMedidaVariavel(nomeVA, idVM);
		Assert.assertNotNull(nomeVA.getText());
		Assert.assertNotNull(idVM);
		Assert.assertNotEquals(0, idVM);
	}
/**
 * Teste filtrar variaveis medidas todas	
 */
	@Test
	public void testFiltrarVariaveisMedidasTudo() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.filtrarVariaveisMedidasTudo();
		Assert.assertNotNull(output);
	}
/**
 * Teste consultar variaveis investigador	
 */
	@Test
	public void testConsultarVariaveisInvestigador() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.consultarVariaveisInvestigador();
		Assert.assertNotNull(output);
	}
/**
 * Teste filtrar culturas todas	
 */
	@Test
	public void testFiltrarCulturaTudo() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.filtrarCulturaTudo();
		Assert.assertNotNull(output);
	}
/**
 * Teste filtrar medições todas	
 */
	@Test
	public void testFiltrarMedicaoTudo() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.filtrarMedicaoTudo();
		Assert.assertNotNull(output);
	}
/**
 * Teste filtrar alertas cultura	
 */
	@Test
	public void testFiltrarAlertasCultura() {
		JTextField usernameText = new JTextField("user");
		JPasswordField passwordText = new JPasswordField("password");
		JFrame frame = new JFrame();
		test.login(usernameText, passwordText, frame );
		Assert.assertNotNull(test.getConnection());
		ResultSet output = test.filtrarAlertasCultura();
		Assert.assertNull(output);
	}
	
}
