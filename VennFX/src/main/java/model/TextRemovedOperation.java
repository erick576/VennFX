package model;

import controller.VennDiagramWindowController;
import javafx.scene.control.TextField;

public class TextRemovedOperation implements Operation  {

	public TextField entry;
	public VennDiagramWindowController controller;
	
	public TextRemovedOperation(VennDiagramWindowController controller, TextField entry) {
		this.controller = controller;
		this.entry = entry;
	}

	@Override
	public void executeUndo() {
		controller.removedEntryUndo(entry);
	}

	@Override
	public void executeRedo() {
		// TODO Auto-generated method stub
		controller.removedEntryRedo(entry);
	}

}