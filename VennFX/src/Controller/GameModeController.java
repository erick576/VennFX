package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class GameModeController implements Initializable {

	@FXML
	public TextField title1, title2;

	@FXML
	public StackPane stackPane;

	@FXML
	public Circle circle1;

	@FXML
	public Circle circle2;

	@FXML
	public Button submitButton;

	private static int ylevel = -450;
	private static final int xlevel = 700;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	public static ArrayList<String> entriesAB = new ArrayList<>();
	public static ArrayList<String> entriesA = new ArrayList<>();
	public static ArrayList<String> entriesB = new ArrayList<>();
	public static ArrayList<TextField> entries = new ArrayList<>();
	public static String[] Titles = new String[] { "Left Side", "Right Side", "Middle" };

	public GameModeController() {
	}

	/*
	 * Fruits: Apple Pear Oranges Grapefruit Mandarin Limes Nectarines Apricots
	 * Peach Plums Banana Mango Strawberry Raspberry Blueberry Kiwi Passionfruit
	 * Watermelon Rockmelon Honeydew Melon Avocado
	 * 
	 * Both: Tomato
	 * 
	 * Vegetables: Lettuce Spinach Silverbeet Cabbage Cauliflower Brussels Sprout
	 * Broccoli Pumpkin Cucumber Zucchini Potato Sweet Potato Celery Asparagus Onion
	 * Garlic Shallot
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JOptionPane.showMessageDialog(null, "Welcome to Game Mode!");
		ylevel = -450;
		title1.setText("Fruits");
		title2.setText("Vegetables");
		title1.setEditable(false);
		title2.setEditable(false);

		entriesA.clear();
		entriesB.clear();
		entriesAB.clear();
		entries.clear();
		
		String[] entries = new String[] { "Apple", "Brussels Sprout", "Sweet Potato", "Grapefruit", "Pumpkin", "Lime",
				"Nectarine", "Apricot", "Lettuce", "Plum", "Celery", "Mango", "Cucumber", "Cabbage", "Blueberry",
				"Onion", "Cauliflower", "Watermelon", "Rockmelon", "Asparagus", "Melon", "Tomato", "Avocado", "Peach",
				"Spinach", "Silverbeet", "Raspberry", "Passionfruit", "Pear", "Broccoli", "Mandarin", "Strawberry",
				"Zucchini", "Potato", "Orange", "Banana", "Honeydew", "Kiwi", "Garlic", "Shallot" };

		for (int i = 0; i < 40; i++) {
			addEntry(entries[i]);
		}

	}

	public void submitButton(ActionEvent event) throws Exception {
		// Putting the entries in the Application into their respective sides based on
		// their position
		try {
			for (TextField entry : entries) {
				Point2D leftCenter = circle1.localToParent(circle1.getCenterX(), circle1.getCenterY());
				Point2D rightCenter = circle2.localToParent(circle2.getCenterX(), circle2.getCenterY());
				double leftRadius = circle1.getRadius();
				double rightRadius = circle2.getRadius();
				Point2D textFieldLocation = entry.localToParent(entry.getScene().getX(), entry.getScene().getY());
				double distanceToLeft = textFieldLocation.distance(leftCenter);
				double distanceToRight = textFieldLocation.distance(rightCenter);
				if (distanceToLeft <= leftRadius && distanceToRight <= rightRadius) {
					entriesAB.add(entry.getText());
				}

				else if (distanceToLeft <= leftRadius) {
					entriesA.add(entry.getText());

				} else if (distanceToRight <= rightRadius) {
					entriesB.add(entry.getText());

				}
			}
			if (entriesA.isEmpty() && entriesB.isEmpty() && entriesAB.isEmpty()) {
				throw new Exception();
			}

			int score = 0;
			int total = 40;

			if (entriesAB.contains("Tomato")) {
				score++;
			}
			if (entriesA.contains("Apple")) {
				score++;
			}
			if (entriesA.contains("Pear")) {
				score++;
			}
			if (entriesA.contains("Oranges")) {
				score++;
			}
			if (entriesA.contains("Grapefruit")) {
				score++;
			}
			if (entriesA.contains("Mandarin")) {
				score++;
			}
			if (entriesA.contains("Limes")) {
				score++;
			}
			if (entriesA.contains("Nectarines")) {
				score++;
			}
			if (entriesA.contains("Apricots")) {
				score++;
			}
			if (entriesA.contains("Peach")) {
				score++;
			}
			if (entriesA.contains("Plums")) {
				score++;
			}
			if (entriesA.contains("Banana")) {
				score++;
			}
			if (entriesA.contains("Mango")) {
				score++;
			}
			if (entriesA.contains("Strawberry")) {
				score++;
			}
			if (entriesA.contains("Raspberry")) {
				score++;
			}
			if (entriesA.contains("Blueberry")) {
				score++;
			}
			if (entriesA.contains("Kiwi")) {
				score++;
			}
			if (entriesA.contains("Passionfruit")) {
				score++;
			}
			if (entriesA.contains("Watermelon")) {
				score++;
			}
			if (entriesA.contains("Rockmelon")) {
				score++;
			}
			if (entriesA.contains("Honeydew")) {
				score++;
			}
			if (entriesA.contains("Melon")) {
				score++;
			}
			if (entriesA.contains("Avocado")) {
				score++;
			}
			if (entriesB.contains("Lettuce")) {
				score++;
			}
			if (entriesB.contains("Spinach")) {
				score++;
			}
			if (entriesB.contains("Silverbeet")) {
				score++;
			}
			if (entriesB.contains("Cabbage")) {
				score++;
			}
			if (entriesB.contains("Cauliflower")) {
				score++;
			}
			if (entriesB.contains("Brussels Sprout")) {
				score++;
			}
			if (entriesB.contains("Broccoli")) {
				score++;
			}
			if (entriesB.contains("Pumpkin")) {
				score++;
			}
			if (entriesB.contains("Cucumber")) {
				score++;
			}
			if (entriesB.contains("Zucchini")) {
				score++;
			}
			if (entriesB.contains("Potato")) {
				score++;
			}
			if (entriesB.contains("Sweet Potato")) {
				score++;
			}
			if (entriesB.contains("Celery")) {
				score++;
			}
			if (entriesB.contains("Asparagus")) {
				score++;
			}
			if (entriesB.contains("Onion")) {
				score++;
			}
			if (entriesB.contains("Garlic")) {
				score++;
			}
			if (entriesB.contains("Shallot")) {
				score++;
			}

			JOptionPane.showMessageDialog(null, "Total Score: " + score + " / " + total);
			((Node) event.getSource()).getScene().getWindow().hide();
		} catch (Exception e) {
		}
	}

	public void addEntry(String text) {
		TextField entry = new TextField();
		entries.add(entry);
		entry.autosize();
		entry.setText(text);
		entry.setVisible(true);
		entry.setEditable(false);
		entry.resizeRelocate(circle1.getCenterX(), circle1.getCenterY(), 1, 1);
		entry.setTranslateY(ylevel);
		entry.setTranslateX(xlevel);
		ylevel = ylevel + 15;
		if (ylevel > 225) {
			ylevel = -150;
		}
		entry.resize(50, 50);
		entry.setMinWidth(30);
		entry.setPrefWidth(30);
		entry.setMaxWidth(150);
		stackPane.getChildren().add(entry);
		entry.setBackground(Background.EMPTY);

		// Drag and Drop Functionality
		entry.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {

			orgSceneX = e.getSceneX();
			orgSceneY = e.getSceneY();
			orgTranslateX = entry.getTranslateX();
			orgTranslateY = entry.getTranslateY();

			entry.toFront();
		});

		entry.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {

			double offsetX = e.getSceneX() - orgSceneX;
			double offsetY = e.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;
			((TextField) (e.getSource())).setTranslateX(newTranslateX);
			((TextField) (e.getSource())).setTranslateY(newTranslateY);

		});

		// Dragging into HotZone Functionality (Everything is implemented But Without
		// HotZones)
		// Will warn user if entry is outside of VennDiagram (Entry positions will be
		// calculated at the end when the user wants to export the entries)

		entry.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {

			Point2D leftCenter = circle1.localToParent(circle1.getCenterX(), circle1.getCenterY());
			Point2D rightCenter = circle2.localToParent(circle2.getCenterX(), circle2.getCenterY());

			double leftRadius = circle1.getRadius();
			double rightRadius = circle2.getRadius();

			Point2D textFieldLocation = entry.localToParent(entry.getScene().getX(), entry.getScene().getY());

			double distanceToLeft = textFieldLocation.distance(leftCenter);
			double distanceToRight = textFieldLocation.distance(rightCenter);

			if ((distanceToLeft <= leftRadius && distanceToRight <= rightRadius) || (distanceToLeft <= leftRadius)
					|| (distanceToRight <= rightRadius)) {
			}
		});
	}

}
