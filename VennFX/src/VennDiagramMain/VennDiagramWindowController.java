package VennDiagramMain;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class VennDiagramWindowController implements Initializable {

	@FXML
	public TextField textField, title1, title2, side;

	@FXML
	public StackPane stackPane;

	@FXML
	public Circle circle1;

	@FXML
	public Circle circle2;

	@FXML
	public Button entryButton, title1Button, title2Button;

	@FXML
	public VBox Abox, Bbox, ABbox;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	public static HashSet<String> entriesAB = new HashSet<>();
	public static HashSet<String> entriesA = new HashSet<>();
	public static HashSet<String> entriesB = new HashSet<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void entryButton(ActionEvent event) {
		try {

			// Testing For Invalid Statements
			/*
			 * Situations that will throw an invalid statement 
			 * 1.Empty Text 
			 * 2.Text Length Over 100 
			 * 3.Word Length Over 20 
			 * 4.If a word is not All CAPS, NO CAPS, or ONLY
			 * THE FIRST LETTER CAPS
			 */
			String testSpaces = textField.getText();
			if (textField.getText().length() > 100
					|| (testSpaces.replaceAll("", " ").length() == textField.getText().length()
							&& textField.getText().length() > 20)
					|| textField.getText().equals("")) {
				throw new Exception();
			}

			int wordLength = 0;
			int capCount = 0;
			boolean isFirst = false;
			ArrayList<Integer> lengths = new ArrayList<Integer>();
			for (int i = 0; i < textField.getText().length(); i++) {
				if (textField.getText().charAt(i) != ' ') {
					wordLength++;
				}

				if (Character.isUpperCase(textField.getText().charAt(i)) && wordLength == 1) {
					capCount++;
					isFirst = true;
					continue;
				}

				if (Character.isUpperCase(textField.getText().charAt(i))) {
					capCount++;
				}

				if (textField.getText().charAt(i) == ' ') {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
				if (i == textField.getText().length() - 1) {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
			}
			for (int i : lengths) {
				if (i > 20) {
					throw new Exception();
				}
			}
			
			// If the side textbox is invalid
			if (!side.getText().contentEquals("") && !side.getText().contentEquals("a")
					&& !side.getText().contentEquals("A") && !side.getText().contentEquals("B")
					&& !side.getText().contentEquals("b") && !side.getText().contentEquals("ab")
					&& !side.getText().contentEquals("AB")) {
				throw new Exception();
			}
			
			
			// Add Entry into the set
			//When hotZones are inplemented and dragged into a cerain zone add the text to the set like below for example
			//entriesA.add(textField.getText());
			
			// Input Valid Text File Into Drag and Drop TextBox
			
			TextField entry = new TextField();
			entry.autosize();
			entry.setText(textField.getText());
			entry.setVisible(true);
			entry.setEditable(false);
			entry.resizeRelocate(circle1.getCenterX(), circle1.getCenterY(), 1, 1);
			entry.resize(50, 50);
			entry.setMinWidth(30);
			entry.setPrefWidth(30);
			entry.setMaxWidth(200);

			stackPane.getChildren().add(entry);
			textField.setText("");

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

			// Dragging into HotZone Functionallity
			
			
			
			
			
			
			
			
			
			
			
			

			
			// If Side is already specified then there is no need to drag, the text box will automatically fall in the hotZone
			if (!side.getText().equals("")) {
				if (side.getText().contentEquals("A") || side.getText().contentEquals("a")) {
					Abox.getChildren().add(entry);
				}
				else if (side.getText().contentEquals("B") || side.getText().contentEquals("b")) {
					Bbox.getChildren().add(entry);
				}
				else if (side.getText().contentEquals("AB") || side.getText().contentEquals("ab")) {
					entry.setMaxWidth(150);
					ABbox.getChildren().add(entry);
				}
				side.setText("");
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			textField.setText("");
			side.setText("");
		}
	}

	public void title1Button(ActionEvent event) {
		try {
			
			// Testing For Invalid Statements
			/*
			 * Situations that will throw an invalid statement 
			 * 1.Empty Text 
			 * 2.Text Length Over 100 
			 * 3.Word Length Over 20 
			 * 4.If a word is not All CAPS, NO CAPS, or ONLY
			 * THE FIRST LETTER CAPS
			 */
			
			String testSpaces = title1.getText();
			if (title1.getText().length() > 100 || (testSpaces.replaceAll("", " ").length() == title1.getText().length()
					&& title1.getText().length() > 20) || title1.getText().equals("")) {
				throw new Exception();
			}

			int wordLength = 0;
			int capCount = 0;
			boolean isFirst = false;
			ArrayList<Integer> lengths = new ArrayList<Integer>();
			for (int i = 0; i < title1.getText().length(); i++) {
				if (title1.getText().charAt(i) != ' ') {
					wordLength++;
				}

				if (Character.isUpperCase(title1.getText().charAt(i)) && wordLength == 1) {
					capCount++;
					isFirst = true;
					continue;
				}

				if (Character.isUpperCase(title1.getText().charAt(i))) {
					capCount++;
				}

				if (title1.getText().charAt(i) == ' ') {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
				if (i == title1.getText().length() - 1) {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
			}
			for (int i : lengths) {
				if (i > 20) {
					throw new Exception();
				}
			}
			
			
			//Set Text
			title1.setEditable(false);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			title1.setText("");
		}
	}

	public void title2Button(ActionEvent event) {
		try {
			
			// Testing For Invalid Statements
			/*
			 * Situations that will throw an invalid statement 
			 * 1.Empty Text 
			 * 2.Text Length Over 100 
			 * 3.Word Length Over 20 
			 * 4.If a word is not All CAPS, NO CAPS, or ONLY
			 * THE FIRST LETTER CAPS
			 */
			
			String testSpaces = title2.getText();
			if (title2.getText().length() > 100 || (testSpaces.replaceAll("", " ").length() == title2.getText().length()
					&& title2.getText().length() > 20) || title2.getText().equals("")) {
				throw new Exception();
			}

			int wordLength = 0;
			int capCount = 0;
			boolean isFirst = false;
			ArrayList<Integer> lengths = new ArrayList<Integer>();
			for (int i = 0; i < title2.getText().length(); i++) {
				if (title2.getText().charAt(i) != ' ') {
					wordLength++;
				}

				if (Character.isUpperCase(title2.getText().charAt(i)) && wordLength == 1) {
					capCount++;
					isFirst = true;
					continue;
				}

				if (Character.isUpperCase(title2.getText().charAt(i))) {
					capCount++;
				}

				if (title2.getText().charAt(i) == ' ') {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
				if (i == title2.getText().length() - 1) {
					lengths.add(wordLength);
					if (capCount != wordLength
							&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
						throw new Exception();
					}
					capCount = 0;
					wordLength = 0;
				}
			}
			for (int i : lengths) {
				if (i > 20) {
					throw new Exception();
				}
			}
			
			//Set Text
			title2.setEditable(false);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			title2.setText("");
		}
	}

}
