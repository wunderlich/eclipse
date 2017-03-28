import processing.core.PApplet;
import processing.core.PVector;

public abstract class DynamicActor extends Actor {

	//----------------------------------------
	// Attributes
	//----------------------------------------

	PVector speed;

	//----------------------------------------
	// Constructors
	//----------------------------------------

	DynamicActor(PApplet parent, float posX, float posY, float sizeW, float sizeH, int colF, int colS, PVector speed) {
		super(parent, posX, posY, sizeW, sizeH, colF, colS);
		this.speed = speed;
	}

	//----------------------------------------
	// Display-Method
	//----------------------------------------

	abstract void display(); 

	//----------------------------------------
	// Miscellaneous Methods
	//----------------------------------------

	void move() {
		this.posX += speed.x;
		this.posY += speed.y;
	}
	
	boolean hitsWall(Direction direction) {
		if (direction == Direction.LEFT && posX <= sizeW/2) {
			return true;
		}
		
		if (direction == Direction.RIGHT && posX >= parent.width - sizeW/2) {
			return true;
		}
				
		if (direction == Direction.TOP && posY <= sizeH/2) {
			return true;
		}
		
		if (direction == Direction.BOTTOM && posY >= parent.height - sizeH/2) {
			return true;
		}
		
		return false;
	}

	void bounceBorder() {
		if (hitsWall(Direction.LEFT) || hitsWall(Direction.RIGHT)) {
			speed.x *= (-1);
		}

		if (hitsWall(Direction.TOP) || hitsWall(Direction.BOTTOM)) {
			speed.y *= (-1);
		}
	}

	boolean bounceAt(Actor actor) {
		if (this.hitsTop(actor) && speed.y > 0) {
			speed.y *= -1;
			return true;
		} else if (this.hitsBottom(actor) && speed.y < 0) {
			speed.y *= -1;
			return true;
		} else if (this.hitsLeft(actor) && speed.x > 0) {
			speed.x *= -1;
			return true;
		} else if (this.hitsRight(actor) && speed.x < 0) {
			speed.x *= -1;
			return true;
		}
		return false;
	}

	//----------------------------------------
	// Setter
	//----------------------------------------

	void setSpeed(PVector speed) {
		this.speed = speed;
	}

	void setSpeedX(float speedX) {
		this.speed.x = speedX;
	}

	void setSpeedY(float speedY) {
		this.speed.y = speedY;
	}

	//----------------------------------------
	// Getter
	//----------------------------------------

	PVector getSpeed() {
		return speed;
	}

	float getSpeedX() {
		return speed.x;
	}

	float getSpeedY() {
		return speed.y;
	}
}
