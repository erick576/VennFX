package VennDiagramMain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class LoginWindowTester extends ApplicationTest {

	@FXML
	public TextField username, password;
	
	@FXML
	public Button loginButton, newUserButton, deleteUserButton;

	
	@Before
	public void setUpClass() throws Exception {
		ApplicationTest.launch(LoginWindowMain.class);
	}

	/* This operation comes from ApplicationTest and loads the GUI to test. */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent loginWindow = FXMLLoader.load(getClass().getResource("LoginWindowView.fxml"));
		primaryStage.setScene(new Scene(loginWindow));
		primaryStage.show();
		primaryStage.toFront();
	}

	/* Just a shortcut to retrieve widgets in the GUI. */
	public <T extends Node> T find(final String query) {
		/** TestFX provides many operations to retrieve elements from the loaded GUI. */
		return lookup(query).query();
	}

	@Before
	public void setUp() throws Exception {
		// THESE ARE THE NAMES OF YOUR @FXML FIELDS

		username = find("#username");
		password = find("#password");
		loginButton = find("loginButton");
		newUserButton = find("newUserButton");
		deleteUserButton = find("deleteUserButton");

	}

	@After
	public void tearDown() throws Exception {
		/* Close the window. It will be re-opened at the next test. */
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}

	@Test
	public void NewUserButtonTest() throws InterruptedException {
		clickOn("#username");
		write("Ericn576");
		clickOn("#password");
		write("12345");
		clickOn("newUserButton");
		assertEquals(username.getText(), "Ericn576");
		assertEquals(password.getText(), "12345");
		type(KeyCode.ENTER);
		assertEquals(username.getText(), "...");
		assertEquals(password.getText(), "...");
		type(KeyCode.ENTER);
	}

	@Test
	public void DeleteUserButtonTest() throws InterruptedException {
		fail("");
	}

	@Test
	public void LoginButtonTest() throws InterruptedException {
		fail();
	}
}
