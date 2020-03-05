import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VennDiagramWindowController implements Initializable {

	@FXML
	public ColorPicker color1, color2;

	@FXML
	public TextField textField, title1, title2;

	@FXML
	public StackPane stackPane;

	@FXML
	public Circle circle1;

	@FXML
	public Circle circle2;

	@FXML
	public Button entryButton, title1Button, title2Button, change1Button, change2Button, exportButton, clearButton;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	public static ArrayList<String> entriesAB = new ArrayList<>();
	public static ArrayList<String> entriesA = new ArrayList<>();
	public static ArrayList<String> entriesB = new ArrayList<>();
	public static ArrayList<TextField> entries = new ArrayList<>();
	public static String[] Titles = new String[] { "Left Side", "Right Side", "Middle" };

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void entryButton(ActionEvent event) {
		try {

			if (textField.getText().trim().contentEquals("")) {
				throw new Exception();
			}

			// Add Entry into the set
			// When hotZones are inplemented and dragged into a cerain zone add the text to
			// the set like below for example
			// entriesA.add(textField.getText());

			// Input Valid Text File Into Drag and Drop TextBox
			// Enter entry into general ArrayList called "entries" for when export function
			// is needed
			TextField entry = new TextField();
			entries.add(entry);
			entry.autosize();
			entry.setText(textField.getText());
			entry.setVisible(true);
			entry.setEditable(false);
			entry.resizeRelocate(circle1.getCenterX(), circle1.getCenterY(), 1, 1);
			entry.resize(50, 50);
			entry.setMinWidth(30);
			entry.setPrefWidth(30);
			entry.setMaxWidth(150);

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

			// Dragging into HotZone Functionallity (Everything is implemented But Without Hotzones)
			// Will warn user if entry is outside of venn diagram (Entry positions will be calculated at the end when the user wants to export the entries)

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
				} else {

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("TextField Out of Bounds");
					alert.setContentText(
							"If you dont place the textField inside the bounds, I wont be able to add it to the CSV File.");
					alert.showAndWait();
				}

			});

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			textField.setText("");
		}
	}

	// Set Title1
	public void title1Button(ActionEvent event) {
		try {

			if (title1.getText().trim().contentEquals("")) {
				throw new Exception();
			}

			// Set Text
			JOptionPane.showMessageDialog(null, "Title Is Now Locked");
			title1.setEditable(false);
			Titles[0] = title1.getText();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			title1.setText("");
		}
	}

	// Set Title2
	public void title2Button(ActionEvent event) {
		try {

			if (title2.getText().trim().contentEquals("")) {
				throw new Exception();
			}

			// Set Text
			JOptionPane.showMessageDialog(null, "Title Is Now Locked");
			title2.setEditable(false);
			Titles[1] = title2.getText();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			title2.setText("");
		}
	}

	// Change Title1
	public void change1Button(ActionEvent event) {
		try {
			if (title1.getText().contentEquals("")) {
				throw new Exception();
			}
			title1.setEditable(true);
			JOptionPane.showMessageDialog(null, "Title Is Now Unocked");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No Title Yet");
			title1.setText("");
		}
	}

	// Change Title2
	public void change2Button(ActionEvent event) {
		try {
			if (title2.getText().contentEquals("")) {
				throw new Exception();
			}

			JOptionPane.showMessageDialog(null, "Title Is Now Unocked");
			title2.setEditable(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No Title Yet");
			title2.setText("");
		}
	}


	public void color1(ActionEvent event) {
		try {
			Color selectedColor = color1.getValue();
			circle1.setFill(selectedColor);
		} catch (Exception e) {

		}
	}

	public void color2(ActionEvent event) {
		try {
			Color selectedColor = color2.getValue();
			circle2.setFill(selectedColor);

		} catch (Exception e) {

		}
	}

	// Export CSV Function Here
	public void exportButton(ActionEvent event) {
		try {

			// Putting the entires in the app into their respective sides based on their
			// position
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

			// Saving it onto the excel sheet
			int max = Math.max(Math.max(entriesA.size(), entriesB.size()), entriesAB.size());
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Results");

			Row headerRow = sheet.createRow(0);

			for (int i = 0; i < 3; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(Titles[i]);
			}

			for (int i = 1; i <= max; i++) {
				Row row = sheet.createRow(i);
				if (i - 1 <= entriesA.size() - 1) {
					row.createCell(0).setCellValue(entriesA.get(i - 1));
				}
				if (i - 1 <= entriesB.size() - 1) {
					row.createCell(1).setCellValue(entriesB.get(i - 1));
				}
				if (i - 1 <= entriesAB.size() - 1) {
					row.createCell(2).setCellValue(entriesAB.get(i - 1));
				}
			}

			FileOutputStream fileOut = new FileOutputStream("Results.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			JOptionPane.showMessageDialog(null, "Entries Saved!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No Entries Stored");

		}
	}

	// Clear Button Function
	public void clearButton(ActionEvent event) {
		try {
			if (entries.size() == 0 && title1.getText().contentEquals("") && title2.getText().contentEquals("")) {
				throw new Exception();
			}
			for (TextField entry : entries) {
				entry.setVisible(false);
			}
			entries.clear();
			title1.setText("");
			title1.setEditable(true);
			title2.setText("");
			title2.setEditable(true);
			circle1.setFill(Color.TRANSPARENT);
			circle2.setFill(Color.TRANSPARENT);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Is Already Empty");
		}
	}
}
