import processing.core.PApplet;
import processing.core.PVector;


public class Breakout extends PApplet {

	//Control Mode
	Control control = Control.KEYBOARD;
	boolean gameActive;

	Paddle pad;
	Ball ball;


	public static void main(String[] args) {
		PApplet.main("Breakout");

	}

	public void settings(){
		size(800, 900);
	}

	public void setup(){
		gameActive = false;
		pad = new Paddle(this, width/2, height - 100, 100, 20, color(255), color(0, 255, 255), new PVector(6, 0));
		ball = new Ball(this, width/2, height - 150, 30, color(0, 255, 0), color(0, 255, 0), new PVector(-3, -5));
	}

	public void draw(){
		background(0);

		ball.display();
		ball.bounceBorder();
		ball.bounceAt(pad);

		if (gameActive) {
			ball.move();
		}

		if (control == Control.MOUSE) {
			pad.setPosX(mouseX);
		}

		pad.display();

		if(keyPressed) {

			if (key == ' ') {
				gameActive = true;
			}

			if (control == Control.KEYBOARD) {
				if (keyCode == LEFT) {
					pad.moveLeft();
				}
				if (keyCode == RIGHT) {
					pad.moveRight();
				}
			}
		}
	}
}
