package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.scene.image.Image;
import javafx.stage.Screen;
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
		VennDiagramWindow.primaryStage.getIcons().add(new Image("/VennFX_Logo.png"));
		VennDiagramWindow.primaryStage.setMinWidth(primaryStage.getWidth());
		VennDiagramWindow.primaryStage.setMinHeight(primaryStage.getHeight());
		
		VennDiagramWindow.primaryStage.show();
	
		VennDiagramWindow.primaryStage.setMaximized(true);

//        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        VennDiagramWindow.primaryStage.setX((primScreenBounds.getWidth() - VennDiagramWindow.primaryStage.getWidth()) / 2); 
//        VennDiagramWindow.primaryStage.setY((primScreenBounds.getHeight() - VennDiagramWindow.primaryStage.getHeight()) / 4);    
	}
	
	private void VennDiagramWindowView() throws IOException {

		this.loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/VennDiagramWindowView.fxml"));
		this.mainLayout = (AnchorPane) loader.load();
		this.mainLayout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));

//		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
//		double width = resolution.getWidth();
//		double height = resolution.getHeight(); 
//		double w = width/2000;  // your window width
//		double h = height/1200;  // your window height
//		Scale scale = new Scale(w, h, 0, 0);
//		this.mainLayout.getTransforms().add(scale);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
