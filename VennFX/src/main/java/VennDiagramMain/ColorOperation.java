package VennDiagramMain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ColorOperation implements Operation {

	public Color color;
	public Color prevColor;
	public Circle circle;
	public VennDiagramWindowController controller;

	public ColorOperation(VennDiagramWindowController controller, Color color, Color prev, Circle circle) {
		this.color = color;
		this.circle = circle;
		this.prevColor = prev;
		this.controller = controller;
	}

	@Override
	public void executeUndo() {
		controller.setColorUndo(this.circle, this.color, this.prevColor);
	}

	@Override
	public void executeRedo() {
		controller.setColorRedo(this.circle, this.color, this.prevColor);		
	}
	
}
