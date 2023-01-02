package com.sluja.pingPongApp.model.ball;

import java.awt.Color;
import java.awt.Graphics;

import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.generator.RandomGenerator;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.PropertyReader;

public class RoundedBall implements Ball, Runnable {

	private int size;
	private int positionX;
	private int positionY;
	private int speedX;
	private int speedY;
	private GameLevel gameLevel;
	private int increasedSpeedX;
	private int increasedSpeedY;
	private boolean movingUp;
	private boolean movingFaster;
	private boolean movingStraight;
	private boolean pickedUp;
	private boolean borderCrossed;
	private boolean run;
	private boolean firstDirection;
	private final int SCREEN_HEIGHT = Integer.parseInt(PropertyReader.getInstance().getProperty("window.heigth"));
	private final int SCREEN_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.width"));
	RandomGenerator randomGenerator;
	private GamePanel gamePanel;
	private int movingDirection;

	public RoundedBall(GameLevel gameLevel, GamePanel gamePanel) {
		this.size = Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.size"));
		this.positionX = SCREEN_WIDTH / 2;
		this.positionY = SCREEN_HEIGHT / 2;
		this.speedX = Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedX"));
		this.speedY = Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedY"));
		this.movingDirection = Integer.signum(this.speedY);
		this.gameLevel = gameLevel;
		this.gamePanel = gamePanel;
		this.randomGenerator = new RandomGenerator();
	}

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
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	@Override
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	@Override
	public void setSpeedX(int speedX) {
		System.out.println("BALL SETX");
		this.speedX = speedX;
	}

	@Override
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	@Override
	public boolean isMovingUp() {
		return this.movingUp;
	}

	@Override
	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
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
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	@Override
	public void move() {
		if (checkBorders())
			this.setSpeedY(this.changeDirection(this.getSpeedY()));

		if (isPickedUp()) {
			System.out.println("SPEED X : " + this.getSpeedX());
			this.setSpeedX(this.changeDirection(this.getSpeedX()));
		}
		// if (this.earnPoint())
		// System.out.println("PUNKT");

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
		increasedSpeedX = Integer.parseInt(PropertyReader.getInstance().getProperty("ball.increasedSpeedX"));
		increasedSpeedY = Integer.parseInt(PropertyReader.getInstance().getProperty("ball.increasedSpeedY"));
		this.setSpeedX(increasedSpeedX);
		this.setSpeedY(increasedSpeedY);
	}

	@Override
	public void restoreSpeed() {
		this.setSpeedX(Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedX")));
		this.setSpeedY(Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedY")));
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
		if (points % 2 == 0 && points != 0)
			movingDirection *= (-1);
		this.setSpeedX(Math.abs(this.getSpeedX()) * movingDirection);

	}

	@Override
	public void setFirstDirection(boolean firstDirection) {
		this.firstDirection = firstDirection;
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
	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public boolean isMovingStraight() {
		return this.movingStraight;
	}

	@Override
	public void setMovingStraight(boolean movingStraight) {
		this.movingStraight = movingStraight;
	}

	@Override
	public void restoreSpeedY() {
		this.setSpeedY(Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedY")));
	}

	@Override
	public boolean isMovingFaster() {
		return this.movingFaster;
	}

	@Override
	public void setMovingFaster(boolean movingFaster) {
		this.movingFaster = movingFaster;
	}

	@Override
	public boolean checkPosition() {
		if (this.getPositionX() < this.SCREEN_WIDTH / 2)
			return true;
		return false;
	}

	@Override
	public void setBorderCrossed(boolean borderCrossed) {
		this.borderCrossed = borderCrossed;
	}

	@Override
	public boolean isBorderCrossed() {
		return this.borderCrossed;
	}

	@Override
	public void restoreSpeedX() {
		this.setSpeedX(Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedX")));
	}

	@Override
	public void run() {
		while (isRun()) {
			try {
				this.move();
				if ((this.earnPoint(this.getGamePanel().getPaddles().get(0).getPositionX(),
						this.getGamePanel().getPaddles().get(1).getPositionX()))) {
					throw new InterruptedException();
				}
				Thread.sleep(Math.abs(this.getSpeedX()));
				this.getGamePanel().repaint();
			} catch (InterruptedException e) {
				this.getGamePanel().setRun(false);
				this.getGamePanel().earnPoint();
				this.getGamePanel().setHomePosition();
				this.getGamePanel().repaint();
			}
		}
	}

}
