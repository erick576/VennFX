package model;

import controller.VennDiagramWindowController;

public class TitleTextOperation  implements Operation {

	public String title;
	public int side;
	public String prev;
	public VennDiagramWindowController controller;
	
	public TitleTextOperation(VennDiagramWindowController controller, String title, String prev, int side) {
		this.title = title;
		this.controller = controller;
		this.side = side;
		this.prev = "";
	}

	@Override
	public void executeUndo() {
		controller.setTitle(this.title, prev, side);
	}

	@Override
	public void executeRedo() {
		controller.setTitle(this.title, prev, side);
		
	}

}