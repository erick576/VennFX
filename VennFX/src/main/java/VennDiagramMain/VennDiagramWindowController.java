package main.java.VennDiagramMain;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class VennDiagramWindowController implements Initializable {

	@FXML
	public Text filler, filler2;

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
	public Button entryButton, title1Button, title2Button, change1Button, change2Button, exportButton, importButton,
			clearButton;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	public static ArrayList<String> entriesAB = new ArrayList<>();
	public static ArrayList<String> entriesA = new ArrayList<>();
	public static ArrayList<String> entriesB = new ArrayList<>();
	public static ArrayList<TextField> entries = new ArrayList<>();
	public static String[] Titles = new String[] { "Left Side", "Right Side", "Middle" };
	public static String fileDictName = "";
	private Stack<Operation> undo = new Stack<>();
	private Stack<Operation> redo = new Stack<>();
	public static Stack<Operation> allEntries = new Stack<>();

	public VennDiagramWindowController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.undo = new Stack<>();
		this.redo = new Stack<>();
		circle1.setFill(Color.TRANSPARENT);
		circle2.setFill(Color.TRANSPARENT);
	}

	public void entryButton(ActionEvent event) {
		try {

			if (textField.getText().trim().contentEquals("")) {
				throw new Exception();
			}

			// Add Entry into the set
			// When hotZones are implemented and dragged into a certain zone add the text to
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

			Operation c = new TextCreationOperation(this, entry);
			undo.push(c);
			undo.push(new TextMovementOperation(this, entry, entry.getTranslateX(), entry.getTranslateY()));

			stackPane.getChildren().add(entry);
			textField.setText("");
			entry.setBackground(Background.EMPTY);
			ContextMenu context = new ContextMenu();
			MenuItem item1 = new MenuItem("Delete");
			MenuItem item2 = new MenuItem("Edit");
			MenuItem item3 = new MenuItem("Lock");
			context.getItems().addAll(item1, item2, item3);
			entry.setContextMenu(context);
			item1.setOnAction(e -> {
				entries.remove(entry);
				entry.setVisible(false);
				undo.push(new TextRemovedOperation(this, entry));
			});
			item2.setOnAction(e -> entry.setEditable(true));
			item3.setOnAction(e -> entry.setEditable(false));

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
				} else {

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("TextField Out of Bounds");
					alert.setContentText(
							"If you dont place the textField inside the bounds, I wont be able to add it to the CSV File.");
					alert.showAndWait();
				}

				undo.push(new TextMovementOperation(this, entry, entry.getTranslateX(), entry.getTranslateY()));

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
			Operation c = new TitleTextOperation(this, title1.getText(), "", 1);
			undo.push(c);

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
			Operation c = new TitleTextOperation(this, title2.getText(), "", 2);
			undo.push(c);
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

	// Change Color on Circle1
	public void color1(ActionEvent event) {
		try {
			Color selectedColor = color1.getValue();
			Operation c = new ColorOperation(this, selectedColor, (Color) circle1.getFill(), this.circle1);
			undo.push(c);
			circle1.setFill(selectedColor);
		} catch (Exception e) {

		}
	}

	// Change Color on Circle2
	public void color2(ActionEvent event) {
		try {
			Color selectedColor = color2.getValue();
			Operation c = new ColorOperation(this, selectedColor, (Color) circle2.getFill(), this.circle2);
			undo.push(c);
			circle2.setFill(selectedColor);
		} catch (Exception e) {

		}
	}

	// Export CSV Function Here
	public void exportButton(ActionEvent event) throws InvalidFileException {
		try {

			// Putting the entries in the App into their respective sides based on their
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

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save the file");
			FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setSelectedFile(new File(fileDictName));
			fileChooser.setVisible(true);
			JFileChooser jfc = new JFileChooser();
			final JFrame frame = new JFrame();
			int res = jfc.showSaveDialog(frame);
			File file = jfc.getSelectedFile();
			if (res != JFileChooser.APPROVE_OPTION) {
				return;
			}

			if (!file.getName().subSequence(file.getName().length() - 5, file.getName().length()).equals(".xlsx")) {
				throw new InvalidFileException("");
			}

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

			FileOutputStream doc = new FileOutputStream(file.getPath());
			workbook.write(doc);
			doc.close();
			workbook.close();
			JOptionPane.showMessageDialog(null, "Entries Saved!");

		} catch (InvalidFileException e) {
			JOptionPane.showMessageDialog(null, "Invalid File!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No Entries!");
		}

		entriesA.clear();
		entriesAB.clear();
		entriesB.clear();
	}

//	// Import Button Function
//	public void importButton(ActionEvent event) throws InvalidFileException {
//		try {
//			JFileChooser fileChooser = new JFileChooser();
//			fileChooser.setDialogTitle("Save the file");
//			FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx");
//			fileChooser.setAcceptAllFileFilterUsed(false);
//			fileChooser.addChoosableFileFilter(filter);
//			fileChooser.setSelectedFile(new File(fileDictName));
//			fileChooser.setVisible(true);
//			JFileChooser jfc = new JFileChooser();
//			final JFrame frame = new JFrame();
//			int res = jfc.showSaveDialog(frame);
//			File file = jfc.getSelectedFile();
//			if (res != JFileChooser.APPROVE_OPTION) {
//				return;
//			}
//			
//			if (!file.getName().subSequence(file.getName().length() - 5, file.getName().length()).equals(".xlsx")) {
//				throw new InvalidFileException("");
//			}
//			
//			
//			
//		} catch (InvalidFileException e) {
//			JOptionPane.showMessageDialog(null, "Invalid File!");
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "No Entries!");
//		}
//	}

	public void clearButton(ActionEvent event) {
		try {
			if (entries.size() == 0 && title1.getText().contentEquals("") && title2.getText().contentEquals("")
					&& circle1.getFill().equals(Color.TRANSPARENT) && circle2.getFill().equals(Color.TRANSPARENT)) {
				throw new Exception();
			}
			if(isClear()) {
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
		}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Is Already Empty");
		}
	}

	private boolean isClear() {
		boolean clear = false;
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Clear all");
		alert.setHeaderText("Are you sure you want to clear all entries?");
		ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getDialogPane().getButtonTypes().add(cancelButtonType);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			clear = true;
		}
		return clear;
	}

	// Undo Button Function
	public void undoButton(ActionEvent event) throws EmptyStackException {
		try {
			if (undo.size() > 0) {
				redo.push(undo.pop());
				Operation holder = redo.peek();
				holder.executeUndo();
			}
		} catch (EmptyStackException e) {
			circle1.setFill(Color.TRANSPARENT);
			circle2.setFill(Color.TRANSPARENT);
			title1.setText("");
			title2.setText("");
			title1.setEditable(true);
			title2.setEditable(true);
			textField.setText("");
		}
	}

	// Redo Button Function
	public void redoButton(ActionEvent event) throws EmptyStackException {
		try {
			if (redo.size() > 0) {
				undo.push(redo.pop());
				Operation holder = undo.peek();
				holder.executeRedo();
			}
		} catch (EmptyStackException e) {
			if (undo.size() == 0) {
				circle1.setFill(Color.TRANSPARENT);
				circle2.setFill(Color.TRANSPARENT);
				title1.setText("");
				title2.setText("");
				title1.setEditable(true);
				title2.setEditable(true);
				textField.setText("");
			}
		}
	}

	// Color Function for Redo Button
	public void setColorRedo(Circle circle, Color color, Color prev) {
		if (circle1.equals(circle) && circle1.getFill().equals(Color.TRANSPARENT)) {
			circle1.setFill(color);
		} else if (circle1.equals(circle) && !circle1.getFill().equals(Color.TRANSPARENT)) {
			circle1.setFill(prev);
		} else if (circle2.equals(circle) && circle2.getFill().equals(Color.TRANSPARENT)
				|| (!prev.equals(Color.TRANSPARENT) && !color.equals(Color.TRANSPARENT))) {
			circle2.setFill(color);
		} else if (circle2.equals(circle) && !circle2.getFill().equals(Color.TRANSPARENT)) {
			circle2.setFill(prev);
		}
	}

	// Color Function for Undo Button
	public void setColorUndo(Circle circle, Color color, Color prev) {
		if (circle1.equals(circle) && circle1.getFill().equals(Color.TRANSPARENT)) {
			circle1.setFill(color);
		} else if (circle1.equals(circle) && !circle1.getFill().equals(Color.TRANSPARENT)) {
			circle1.setFill(prev);
		} else if (circle2.equals(circle) && circle2.getFill().equals(Color.TRANSPARENT)) {
			circle2.setFill(color);
		} else if (circle2.equals(circle) && !circle2.getFill().equals(Color.TRANSPARENT)) {
			circle2.setFill(prev);
		}
	}

	// Title Function for Undo And Redo Button
	public void setTitle(String title, String prev, int side) {
		if (side == 1 && !title1.getText().equals("")) {
			title1.setEditable(true);
			title1.setText(prev);
		} else if (side == 1 && title1.getText().equals("")) {
			title1.setEditable(true);
			title1.setText(title);
		} else if (side == 2 && !title2.getText().equals("")) {
			title2.setEditable(true);
			title2.setText(prev);
		} else if (side == 2 && title2.getText().equals("")) {
			title2.setEditable(true);
			title2.setText(title);
		}
	}

	// Entry Function for Redo Button
	public void setEntryRedo(TextField entry) {
		entry.setVisible(true);
		entries.add(entry);
	}

	// Entry Function for Undo Button
	public void setEntryUndo(TextField entry) {
		entry.setVisible(false);
		entries.remove(entry);
	}

	// Drag Function for Undo and Redo Button
	public void setMovement(TextField entry, double coordinateX, double coordinateY) {
		entry.setTranslateX(coordinateX);
		entry.setTranslateY(coordinateY);
	}

	// Remove Entry Function for Undo Button
	public void removedEntryUndo(TextField entry) {
		entry.setVisible(true);
		entries.add(entry);
	}

	// Remove Entry Function for Redo But
	public void removedEntryRedo(TextField entry) {
		entry.setVisible(false);
		entries.remove(entry);
	}

}
