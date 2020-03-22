package view;

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
	public static final String APP_TITLE = "VennFX";

	@Override
	public void start(Stage primaryStage) throws IOException {
		VennDiagramWindow.primaryStage = primaryStage;
		VennDiagramWindowView();
		Scene scene = new Scene(this.mainLayout);
		VennDiagramWindow.primaryStage.setScene(scene);

		VennDiagramWindow.primaryStage.sizeToScene();
		VennDiagramWindow.primaryStage.setTitle(APP_TITLE);

		VennDiagramWindow.primaryStage.setMinWidth(primaryStage.getWidth());
		VennDiagramWindow.primaryStage.setMinHeight(primaryStage.getHeight());

		VennDiagramWindow.primaryStage.show();
//
//		VennDiagramWindow.primaryStage.setMaximized(true);

	}

	private void VennDiagramWindowView() throws IOException {

		this.loader = new FXMLLoader();
//		System.out.println(getClass().getResource("../view"));
		loader.setLocation(getClass().getResource("/VennDiagramWindowView.fxml"));
		this.mainLayout = (AnchorPane) loader.load();
	}

	public static void main(String[] args) {
		launch(args);
	}

}