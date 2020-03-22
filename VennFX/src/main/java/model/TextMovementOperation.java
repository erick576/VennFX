package model;

import controller.VennDiagramWindowController;
import javafx.scene.control.TextField;

public class TextMovementOperation implements Operation {

	public TextField entry;
	public double coordinateX, coordinateY;
	public VennDiagramWindowController controller;
	
	public TextMovementOperation(VennDiagramWindowController controller, TextField entry, double X, double Y) {
		this.controller = controller;
		this.entry = entry;
		this.coordinateX = X;
		this.coordinateY = Y;
	}

	@Override
	public void executeUndo() {
		controller.setMovement(entry, coordinateX, coordinateY);
	}

	@Override
	public void executeRedo() {
		controller.setMovement(entry, coordinateX, coordinateY);
	}


}