package main.java.VennDiagramMain;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VennDiagramWindow extends Application {

	private AnchorPane mainLayout;
	public static Stage primaryStage;
	private FXMLLoader loader;

	@Override
	public void start(Stage primaryStage) throws IOException {
		VennDiagramWindow.primaryStage = primaryStage;
		VennDiagramWindowView();
		Scene scene = new Scene(this.mainLayout);
		VennDiagramWindow.primaryStage.setScene(scene);
		VennDiagramWindow.primaryStage.show();
		VennDiagramWindow.primaryStage.show();
	}

	private void VennDiagramWindowView() throws IOException {

		this.loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("VennDiagramWindowView.fxml"));
		this.mainLayout = (AnchorPane) loader.load();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
