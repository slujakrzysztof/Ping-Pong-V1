package com.sluja.pingPongApp.model.ball;

import java.awt.Color;
import java.awt.Graphics;

import com.sluja.pingPongApp.generator.RandomGenerator;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.SizeManager;

public class RoundedBall implements Ball, Runnable {

	// ----- CONSTANTS ----- //
	private final int SCREEN_HEIGHT = SizeManager.getInstance().SCREEN_HEIGHT;
	private final int SCREEN_WIDTH = SizeManager.getInstance().SCREEN_WIDTH;
	private final int BALL_ROUNDED_SPEED_X = SizeManager.getInstance().BALL_ROUNDED_SPEED_X;
	private final int BALL_ROUNDED_SPEED_Y = SizeManager.getInstance().BALL_ROUNDED_SPEED_Y;
	private final int BALL_INCREASED_SPEED_X = SizeManager.getInstance().BALL_INCREASED_SPEED_X;
	private final int BALL_INCREASED_SPEED_Y = SizeManager.getInstance().BALL_INCREASED_SPEED_Y;

	// ----- Primitive type variables ----- //
	private int size;
	private int positionX;
	private int positionY;
	private int speedX;
	private int speedY;
	private boolean movingUp;
	private boolean movingFaster;
	private boolean movingStraight;
	private boolean pickedUp;
	private boolean run;
	private boolean firstDirection;
	private int movingDirection;

	// ----- Variables ----- //
	RandomGenerator randomGenerator;
	private GamePanel gamePanel;
	private Thread ballThread;

	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //

	public RoundedBall(GamePanel gamePanel) {
		this.size = SizeManager.getInstance().BALL_ROUNDED_SIZE;
		this.positionX = SCREEN_WIDTH / 2;
		this.positionY = SCREEN_HEIGHT / 2;
		this.speedX = BALL_ROUNDED_SPEED_X;
		this.speedY = BALL_ROUNDED_SPEED_Y;
		this.movingDirection = Integer.signum(this.speedY);
		this.gamePanel = gamePanel;
		this.randomGenerator = new RandomGenerator();
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //

	@Override
	public int getReflectionAmount() {
		return this.randomGenerator.getReflectionAmount();
	}

	@Override
	public int getPositionX() {
		return this.positionX;
	}

	@Override
	public int getPositionY() {
		return this.positionY;
	}

	@Override
	public int getSpeedX() {
		return this.speedX;
	}

	@Override
	public int getSpeedY() {
		return this.speedY;
	}

	@Override
	public int getSizeX() {
		return this.size;
	}

	@Override
	public int getSizeY() {
		return this.size;
	}

	@Override
	public boolean isMovingUp() {
		return this.movingUp;
	}

	@Override
	public boolean isPickedUp() {
		return pickedUp;
	}

	@Override
	public boolean isMovingForward() {
		if (this.getSpeedX() > 0)
			return true;
		return false;
	}

	@Override
	public boolean getFirstDirection() {
		return this.firstDirection;
	}

	@Override
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	@Override
	public boolean isRun() {
		return this.run;
	}

	@Override
	public boolean isMovingStraight() {
		return this.movingStraight;
	}

	@Override
	public boolean isMovingFaster() {
		return this.movingFaster;
	}

	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	@Override
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	@Override
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	@Override
	public void setFirstDirection(boolean firstDirection) {
		this.firstDirection = firstDirection;
	}

	@Override
	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public void setMovingStraight(boolean movingStraight) {
		this.movingStraight = movingStraight;
	}

	@Override
	public void setMovingFaster(boolean movingFaster) {
		this.movingFaster = movingFaster;
	}

	@Override
	public void setThread() {
		this.ballThread = new Thread(this);
		this.ballThread.start();
	}
	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //

	// Drawing the ball
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(this.getPositionX(), this.getPositionY(), this.getSizeX(), this.getSizeY());
	}

	@Override
	public void generateReflectionAmount() {
		this.randomGenerator.generateReflectionAmount();
	}

	@Override
	public void move() {
		// Reflecting ball if it touches upper or lower border of the screen
		if (checkBorders())
			this.setSpeedY(this.changeDirection(this.getSpeedY()));

		// Reflecting ball if it touches the paddle
		if (isPickedUp())
			this.setSpeedX(this.changeDirection(this.getSpeedX()));
		
		// Moving the ball
		this.setPositionX(this.getPositionX() + this.getSpeedX());
		this.setPositionY(this.getPositionY() + this.getSpeedY());
	}

	@Override
	public boolean checkBorders() {
		if (this.getPositionY() < 0) {
			this.setPositionY(0);
			return true;
		} else if ((this.getPositionY() + this.size) > (this.SCREEN_HEIGHT - this.size)) {
			this.setPositionY(this.SCREEN_HEIGHT - (2 * this.size));
			return true;
		}
		return false;
	}
	
	@Override
	public void increaseSpeed() {
		this.setSpeedX(this.BALL_INCREASED_SPEED_X);
		this.setSpeedY(this.BALL_INCREASED_SPEED_Y);
	}

	@Override
	public void restoreSpeed() {
		this.setSpeedX(this.BALL_ROUNDED_SPEED_X);
		this.setSpeedY(this.BALL_ROUNDED_SPEED_Y);
	}

	@Override
	public boolean earnPoint(int firstPositionX, int secondPositionX) {
		if ((this.getPositionX() <= 0) || ((this.getPositionX() + this.getSizeX()) >= this.SCREEN_WIDTH)
				|| (this.getPositionX() < positionX)
				|| ((this.getPositionX() + this.getSizeX() / 4) > secondPositionX)) {
			return true;
		}
		return false;
	}

	@Override
	public int changeDirection(int speed) {
		return speed * (-1);
	}

	@Override
	public void setStartingPosition(int points) {
		this.setPositionX(this.SCREEN_WIDTH / 2 - this.size / 2);
		this.randomGenerator.generateStartBallPosition();
		this.setPositionY(randomGenerator.getStartBallPosition());
		this.restoreSpeed();
		// After every two points the ball changes its moving direction - service simulation 
		if (points % 2 == 0 && points != 0)
			movingDirection *= (-1);
		this.setSpeedX(Math.abs(this.getSpeedX()) * movingDirection);

	}

	@Override
	public void restoreSpeedY() {
		this.setSpeedY(this.BALL_ROUNDED_SPEED_Y);
	}

	@Override
	public boolean checkPosition() {
		if (this.getPositionX() < this.SCREEN_WIDTH / 2)
			return true;
		return false;
	}

	@Override
	public void restoreSpeedX() {
		this.setSpeedX(this.BALL_ROUNDED_SPEED_X);
	}

	@Override
	public void run() {
		while (isRun()) {
			try {
				this.move();
				// Check the reflection with the paddle
				this.getGamePanel().getPaddles().forEach((paddle) -> paddle.checkPickup());
				// Ball is out of the playing field - throwing InterruptedException
				if ((this.earnPoint(this.getGamePanel().getPaddles().get(0).getPositionX(),
						this.getGamePanel().getPaddles().get(1).getPositionX()))) {
					throw new InterruptedException();
				}
				Thread.sleep(Math.abs(this.getSpeedX()));
				this.getGamePanel().repaint();
			} catch (InterruptedException e) {
				// Stop the game and add point to the player
				this.getGamePanel().setRun(false);
				this.getGamePanel().earnPoint();
				this.getGamePanel().setHomePosition();
				this.getGamePanel().repaint();
			}
		}
	}

}
