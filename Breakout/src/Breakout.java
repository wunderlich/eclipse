import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;


public class Breakout extends PApplet {

	//Control Mode
	Control control = Control.KEYBOARD;

	int noBricks = 3;
	float speedUpFactor = 1.2f;
	
	PVector initSpeed = new PVector(5, -2);
	
	State state;

	Paddle pad;
	Ball ball;
	
	Brick[] bricks;
	int activeBricks;
	
	PFont fontRegular;


	public static void main(String[] args) {
		PApplet.main("Breakout");

	}

	public void settings(){
		size(800, 900);
	}

	public void setup(){		
		
		state = State.SETUP;
		
		setupGame();
		
		fontRegular = createFont("BPdotsSquare.otf", 40);
	}

	public void draw(){
		background(0);
		
		text(state.name(), 15, 15);
		text("speedX: " + ball.getSpeedX() + ", speedY: " + ball.getSpeedY(), 15, 30);

		if (state == State.PLAY) {
			ball.bounceBorder();
			ball.bounceAt(pad);
			
			ball.move();
			
			detectCollision();

			if (control == Control.MOUSE) {
				pad.setPosX(mouseX);
			}
		}
		
		if (activeBricks == 0) {
			state = State.WIN;
			pushStyle();
			fill(125, 125);
			rectMode(CENTER);
			rect(width/2, height/2, 650, 125);
			
			fill(255, 0, 255);
			textFont(fontRegular);
			textAlign(CENTER, CENTER);
			text("YOU WON!!\nPRESS SPACE TO PLAY AGAIN", width/2, height/2);
			popStyle();
		}
		
		
		if (ball.hitsWall(Direction.BOTTOM)) {
			state = State.GAMEOVER;
			pushStyle();
			fill(125, 125);
			rectMode(CENTER);
			rect(width/2, height/2, 650, 125);
			
			fill(255, 0, 255);
			textFont(fontRegular);
			textAlign(CENTER, CENTER);
			text("GAME OVER\nPRESS SPACE TO PLAY AGAIN", width/2, height/2);
			popStyle();
		}
		
		ball.display();

		pad.display();
		
		for (Brick b : bricks) {
			b.display();
		}
		
		if (state == State.START) {
			pushStyle();
			fill(125, 125);
			rectMode(CENTER);
			rect(width/2, height/2, 550, 125);
			
			fill(255, 0, 255);
			textFont(fontRegular);
			textAlign(CENTER, CENTER);
			text("PRESS SPACE TO START", width/2, height/2);
			popStyle();
		}
		
		if (state == State.PAUSE) {
			pushStyle();
			fill(125, 125);
			rectMode(CENTER);
			rect(width/2, height/2, 200, 125);
			
			fill(255, 0, 255);
			textFont(fontRegular);
			textAlign(CENTER, CENTER);
			text("PAUSE", width/2, height/2);
			popStyle();
		}

		
		if(keyPressed) {
			if (key == CODED) {
				if (control == Control.KEYBOARD) {
					if (keyCode == LEFT) {
						pad.moveLeft();
					}
					if (keyCode == RIGHT) {
						pad.moveRight();
					}
				}
			} else {

				if (key == ESC) {
					key = 0;
					if (state == State.PLAY) {
						state = State.PAUSE;
					} else if (state == State.PAUSE) {
						state = State.PLAY;
					}
				}

				if (key == ' ' && state == State.START) {
					state = State.PLAY;
				}

				if (key == ' ' && (state == State.GAMEOVER || state == State.WIN)) {
					setupGame();
				}
			}
		}
	} // draw()

	
	// Prevent ESC from exiting the program
	public void keyPressed() {
		if (key == ESC) {
			key = 0; 
		}
	}
	
	void setupGame() {
		state = State.SETUP;
		pad = new Paddle(this, width/2, height - 100, 100, 20, color(255), color(0, 255, 255), new PVector(10, 0));
		ball = new Ball(this, width/2, height - 150, 30, color(0, 255, 0), color(0, 255, 0), initSpeed.copy());
		
		//ball.setSpeed(initSpeed);
		
		activeBricks = noBricks;
		
		bricks = new Brick[noBricks];
		
		float brickW = width/noBricks - 10;
		
		for (int i = 0; i < bricks.length; i++) {
			bricks[i] = new Brick(this, i * width/bricks.length + brickW/2 + 5, 100, 50, 25, color(255, 255, 0), color(255, 255, 0));
		}
		delay(1000);
		state = State.START;
		
	}

	void detectCollision() {
		for (Brick b : bricks) {
			if (b.isActive()) {
				if (ball.bounceAt(b)) {
					b.setActive(false);
					activeBricks--;
					ball.setSpeed(ball.getSpeed().mult(speedUpFactor));
				}
			}
		}
	}
	
} // class Breakout
