package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameMode extends Application {

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
		loader.setLocation(getClass().getResource("/View/GameMode.fxml"));
		this.mainLayout = (AnchorPane) loader.load();
		this.mainLayout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
