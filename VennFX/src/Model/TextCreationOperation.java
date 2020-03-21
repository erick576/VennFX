package Model;

import Controller.VennDiagramWindowController;
import javafx.scene.control.TextField;

public class TextCreationOperation implements Operation  {

	public TextField entry;
	public VennDiagramWindowController controller;
	
	public TextCreationOperation(VennDiagramWindowController controller, TextField entry) {
		this.controller = controller;
		this.entry = entry;
	}

	@Override
	public void executeUndo() {
		controller.setEntryUndo(entry);
	}

	@Override
	public void executeRedo() {
		// TODO Auto-generated method stub
		controller.setEntryRedo(entry);
	}

}
