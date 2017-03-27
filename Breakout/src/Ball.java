import processing.core.PApplet;
import processing.core.PVector;

class Ball extends DynamicActor {

	//----------------------------------------
	// Attributes
	//----------------------------------------

	//----------------------------------------
	// Constructors
	//----------------------------------------

	Ball(PApplet parent, float posX, float posY, float size, int colF, int colS, PVector speed) {
		super(parent, posX, posY, size, size, colF, colS, speed);
	}

	//----------------------------------------
	// Display-Method
	//----------------------------------------

	void display() {
		parent.pushStyle();
		//parent.ellipseMode(parent.RADIUS);
		parent.fill(colF);
		parent.ellipse(posX, posY, sizeW, sizeH);
		parent.popStyle();
	}
}