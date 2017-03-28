import processing.core.PApplet;

public class Brick extends Actor {
	
	boolean active;

	public Brick(PApplet parent, float posX, float posY, float sizeW, float sizeH, int colF, int colS) {
		super(parent, posX, posY, sizeW, sizeH, colF, colS);
		active = true;
		// TODO Auto-generated constructor stub
	}

	public Brick(PApplet parent) {
		super(parent);
	}

	@Override
	void display() {
		if (active) {
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
	}

	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
