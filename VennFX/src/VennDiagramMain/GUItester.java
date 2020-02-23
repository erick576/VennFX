package VennDiagramMain;

import org.junit.Before;
import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUItester extends Application {

	public LoginWindow scene;

	@SuppressWarnings({ "static-access" })
	@Before
	public void setup() throws Exception {
		scene = new LoginWindow();
		scene.main(new String[2]);
	}

	@Test
	public void Startup() throws InterruptedException {
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
