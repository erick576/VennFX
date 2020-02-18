package VennDiagramMain;

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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
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
	public Button entryButton, title1Button, title2Button, change1Button, change2Button, exportButton;

	@FXML
	public VBox Abox, Bbox, ABbox;

	@FXML
	public ChoiceBox<String> sides;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	public static ArrayList<String> entriesAB = new ArrayList<>();
	public static ArrayList<String> entriesA = new ArrayList<>();
	public static ArrayList<String> entriesB = new ArrayList<>();
	public static String[] Titles = new String[] {"Left Side", "Right Side", "Middle"};
	ObservableList<String> sidesList = FXCollections.observableArrayList("Left Side", "Right Side", "Middle", "");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
	}

	public void entryButton(ActionEvent event) {
		try {

			if (textField.getText().contentEquals("")) {
				throw new Exception();
			}

			// Add Entry into the set
			// When hotZones are inplemented and dragged into a cerain zone add the text to
			// the set like below for example
			// entriesA.add(textField.getText());

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

			// If Side is already specified then there is no need to drag, the text box will
			// automatically fall in the hotZone
			if (sides.getValue().contentEquals("Left Side")) {
				Abox.getChildren().add(entry);
				entriesA.add(entry.getText());
			} else if (sides.getValue().contentEquals("Right Side")) {
				Bbox.getChildren().add(entry);
				entriesB.add(entry.getText());
			} else if (sides.getValue().contentEquals("Middle")) {
				entry.setMaxWidth(150);
				ABbox.getChildren().add(entry);
				entriesAB.add(entry.getText());
			}
			sides.setValue("");

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Please enter a valid entry");
			textField.setText("");
		}
	}

	// Set Title1
	public void title1Button(ActionEvent event) {
		try {

			if (title1.getText().contentEquals("")) {
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

			if (title2.getText().contentEquals("")) {
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

	private void loadData() {
		sides.setValue("");
		sides.setItems(sidesList);
	}

//Color Function Diabled For Now (May not be a good idea)

//	public void color1(ActionEvent event) {
//		try {
//			Color selectedColor = color1.getValue();
//			circle1.setFill(selectedColor);
//
//		} catch (Exception e) {
//
//		}
//	}
//
//	public void color2(ActionEvent event) {
//		try {
//			Color selectedColor = color2.getValue();
//			circle2.setFill(selectedColor);
//
//		} catch (Exception e) {
//
//		}
//	}

	// Export CSV Function Here
	public void exportButton(ActionEvent event) {
		try {

			if (entriesA.isEmpty() && entriesB.isEmpty() && entriesAB.isEmpty()) {
				throw new Exception();
			}
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

}
