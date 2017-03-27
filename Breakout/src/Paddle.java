import processing.core.PApplet;
import processing.core.PVector;

public class Paddle extends DynamicActor {

	Paddle(PApplet parent, float posX, float posY, float sizeW, float sizeH, int colF, int colS, PVector speed) {
		super(parent, posX, posY, sizeW, sizeH, colF, colS, speed);
	}

	void display() {
		parent.pushStyle();
		parent.rectMode(parent.CENTER);
		parent.fill(colF);
		parent.rect(posX, posY, sizeW, sizeH);
		parent.rectMode(parent.CORNERS);
		parent.noFill();
		parent.stroke(colS);
		parent.rect(getLeft(), getTop(), getRight(), getBottom());
		parent.popStyle();
	}

	void moveLeft() {
		if (!hitsWall(Direction.LEFT)) {
			setPosX(getPosX() - getSpeedX());
		}
	}

	void moveRight() {
		if (!hitsWall(Direction.RIGHT)) {
			setPosX(getPosX() + getSpeedX());
		}
	}
}