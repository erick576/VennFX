package test.java.VennDiagramMain;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VennDiagramWindowMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent mainNode = FXMLLoader.load(getClass().getResource("/views/VennDiagramWindowView.fxml"));
		primaryStage.setScene(new Scene(mainNode));
		primaryStage.show();
		primaryStage.toFront();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
