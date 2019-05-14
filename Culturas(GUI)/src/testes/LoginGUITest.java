package testes;


import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Test;

import gui.LoginGui;
/**
 * Teste GUI Login
 * @author Eduardo
 *
 */
public class LoginGUITest {

	LoginGui test = new LoginGui("Login");
/**
 * Teste verificar user	
 */
	@Test
	public void testCheckUser() {
		JTextField usernameText = new JTextField("User");
		String output = test.checkUser(usernameText );
		Assert.assertNotNull(usernameText);
		Assert.assertNotNull(output);
	}

	@Test
	public void testCheckUserPass() {
		JTextField usernameText = new JTextField("Password");
		String output = test.checkUserPass(usernameText);
		Assert.assertNotNull(usernameText);
		Assert.assertNotNull(output);
	}
	
}
