import processing.core.PApplet;

public abstract class Actor {
	//----------------------------------------
	// Attributes
	//----------------------------------------
	
	PApplet parent;
	
	float posX;
	float posY;

	float sizeW;
	float sizeH;

	int colF;
	int colS;

	//----------------------------------------
	// Constructors
	//----------------------------------------

	Actor(PApplet parent, float posX, float posY, float sizeW, float sizeH, int colF, int colS) {
		this.parent = parent;
		this.posX = posX;
		this.posY = posY;
		this.sizeW = sizeW;
		this.sizeH = sizeH;
		this.colF = colF;
		this.colS = colS;
	}

	Actor(PApplet parent) {
		this(parent, 0.0f, 0.0f, 0.0f, 0.0f, parent.color(0), parent.color(5));
	}

	//----------------------------------------
	// Display-Method
	//----------------------------------------

	abstract void display(); 

	//----------------------------------------
	// Miscellaneous Methods
	//----------------------------------------

	boolean hitsTop(Actor actor) {
		if (posX >= actor.getLeft() && posX <= actor.getRight() &&
				posY >= actor.getTop() - sizeH/2 && posY <= actor.getTop()
				) {
			return true;
		} else {
			return false;
		}
	}

	boolean hitsBottom(Actor actor) {
		if (posX >= actor.getLeft() && posX <= actor.getRight() &&
				posY >= actor.getBottom() && posY <= actor.getBottom() + sizeH/2
				) {
			return true;
		} else {
			return false;
		}
	}

	boolean hitsLeft(Actor actor) {
		if (posX >= actor.getLeft() - sizeW/2 && posX <= actor.getLeft() &&
				posY >= actor.getTop() && posY <= actor.getBottom()
				) {
			return true;
		} else {
			return false;
		}
	}

	boolean hitsRight(Actor actor) {
		if (posX >= actor.getRight() && posX <= actor.getRight() + sizeW/2 &&
				posY >= actor.getTop() && posY <= actor.getBottom()
				) {
			return true;
		} else {
			return false;
		}
	}

	boolean hits(Actor actor) {
		if (this.hitsTop(actor) || this.hitsBottom(actor) || this.hitsLeft(actor) || this.hitsRight(actor)) {
			return true;
		} else {
			return false;
		}
	}

	//----------------------------------------
	// Setter
	//----------------------------------------

	void setPosX(float posX) {
		this.posX = posX;
	}

	void setPosY(float posY) {
		this.posY = posY;
	}

	void setPos(float posX, float posY) {
		setPosX(posX);
		setPosY(posY);
	}

	void setSizeW(float sizeW) {
		this.sizeW = sizeW;
	}

	void setSizeH(float sizeH) {
		this.sizeH = sizeH;
	}

	void setsize(float sizeW, float sizeH) {
		setSizeW(sizeW);
		setSizeH(sizeH);
	}

	void setColF(int colF) {
		this.colF = colF;
	}

	void setColS(int colS) {
		this.colS = colS;
	}

	//----------------------------------------
	// Getter
	//----------------------------------------

	float getPosX() {
		return posX;
	}

	float getPosY() {
		return posY;
	}

	float getSizeW() {
		return sizeW;
	}

	float getSizeH() {
		return sizeH;
	}

	float getTop() {
		return posY - sizeH/2;
	}

	float getBottom() {
		return posY + sizeH/2;
	}

	float getLeft() {
		return posX - sizeW/2;
	}

	float getRight() {
		return posX + sizeW/2;
	}

	int getColF() {
		return colF;
	}

	int getColS() {
		return colS;
	}
}
