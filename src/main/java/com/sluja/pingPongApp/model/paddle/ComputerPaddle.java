package com.sluja.pingPongApp.model.paddle;

import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.properties.PropertyReader;

public class ComputerPaddle extends Paddle implements Runnable {

	GameLevel gameLevel;
	private int speed;
	private int backSpeed;
	private int backPosition;
	private int homePositionY;
	private int border;
	private int lowerBorder, upperBorder;
	private int borderX;
	private boolean conditionFirst;
	private boolean conditionSecond;

	public ComputerPaddle(Player player, GameLevel gameLevel, Ball ball) {
		super(player, ball);
		this.gameLevel = gameLevel;
		this.ball = ball;
		this.conditionFirst = false;
		this.conditionSecond = false;
		this.homePositionY = this.SCREEN_HEIGTH / 2 - this.HEIGTH / 2;
		this.backSpeed = setBackSpeed();
		this.border = setBorder(gameLevel);
		this.realPositionY = this.getPositionY() + this.HEIGTH;
		this.borderX = (int) (this.SCREEN_WIDTH
				* Float.parseFloat(PropertyReader.getInstance().getProperty("paddle.computer.borderX")));
		this.setSpeed();
	}

	private int setBackSpeed() {
		String backSpeedString = "backSpeed." + this.getGameLevel().name().toLowerCase();
		return Integer.parseInt(PropertyReader.getInstance().getProperty(backSpeedString));
	}

	public int getBackSpeed() {
		return this.backSpeed;
	}

	public GameLevel getGameLevel() {
		return this.gameLevel;
	}

	private int setBorder(GameLevel gameLevel) {
		String borderValue = "paddle.computer.border." + gameLevel.name().toLowerCase();
		System.out.println("BORDER: " + borderValue + " , "
				+ Integer.parseInt(PropertyReader.getInstance().getProperty(borderValue)));
		return Integer.parseInt(PropertyReader.getInstance().getProperty(borderValue));
	}

	private void setSpeed() {
		String actualSpeed = "speed." + this.gameLevel.name().toLowerCase();
		String actualBackSpeed = "backSpeed." + this.gameLevel.name().toLowerCase();
		this.speed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualSpeed));
		this.backSpeed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualBackSpeed));
	}

	private boolean checkBorder(int positionY, int ballPositionY) {
		conditionFirst = (positionY - this.border) < ballPositionY;
		conditionSecond = ((positionY + this.HEIGTH) + this.border) > ballPositionY;
		if (conditionFirst && conditionSecond)
			return true;
		return false;

	}

	private void moveBeforePickup() {
		if (checkBorder(this.getPositionY(), this.getBall().getPositionY())) {
			System.out.println("WARTOSC: " + setMovingDirection(this.getBall().getPositionY()));
			this.setMovingUp(setMovingDirection(this.getBall().getPositionY()));
		}
	}

	private void moveAfterPickup() {
		if (this.isPickedUp()) {
			if (this.getPositionY() > this.getHomePosition())
				backPosition = this.getPositionY() - this.getBackSpeed();
			else
				backPosition = this.getPositionY() + this.getBackSpeed();
			this.setPositionY(backPosition);
			this.setPickedUp(false);
		}
		/*
		 * if (this.isPickedUp()) {
		 * 
		 * if (!(this.getPositionY() > (this.getHomePosition() - 150) &&
		 * this.getPositionY() < (this.getHomePosition() + this.HEIGTH + 150))) { if
		 * (this.getPositionY() < (this.getHomePosition() - 20))
		 * this.setMovingUp(false); else if (this.getPositionY() >
		 * (this.getHomePosition() + this.HEIGTH + 20)) this.setMovingUp(true); } }
		 */
	}

	private boolean setMovingDirection(int ballPositionY) {
		if (this.getPositionY() > ballPositionY)
			return true;
		return false;
	}

	public void move() {
		/*
		 * if (isMovingUp()) this.positionY -= speed; else this.positionY += speed;
		 */

	}

	public void computerPaddleMove() {

		if (this.getBall().getPositionX() > this.borderX) {

			if (this.getPositionY() > this.getBall().getPositionY())
				this.setMovingUp(true);
			else if (this.getPositionY() < this.getBall().getPositionY()) {
				this.setMovingUp(false);
				System.out.println("COMPUTER PADDLE");
			}
			if (isMovingUp())
				this.setPositionY(this.getPositionY() - this.speed);
			else
				this.setPositionY(this.getPositionY() + this.speed);
		}
		/*
		 * } if (this.getBall().isMovingForward()) {
		 * System.out.println("PRZED ODBICIEM"); this.moveBeforePickup(); this.move(); }
		 * else this.moveAfterPickup();
		 * 
		 * } // if (this.getBall().getPositionX() < this.SCREEN_WIDTH / 4) //
		 * this.setPickedUp(false);
		 * 
		 * 
		 * if (this.ball.getPositionX() > this.borderX) { if (this.getPositionY() >
		 * this.ball.getPositionY()) this.setMovingUp(true); else if(this.getPositionY()
		 * < this.ball.getPositionY()) this.setMovingUp(false); this.move();
		 * System.out.println("Is moving UP" + isMovingUp()); }
		 */
	}

	private void moveBack() {
		if (this.isMovingUp())
			positionY -= backSpeed;
		else
			positionY += backSpeed;
	}

	private void checkHomePosition() {

		if ((this.ball.getPositionY() < this.homePositionY)
				&& !((realPositionY / 2) < this.lowerBorder && (realPositionY / 2) > this.upperBorder)) {
			if ((realPositionY / 2) > this.homePositionY)
				this.setMovingUp(true);
			else
				this.setMovingUp(false);
			this.moveBack();
		}
	}

	private boolean borderCrossed() {
		if ((this.getBall().getPositionX() + this.getBall().getSizeX()) > this.getPositionX())
			return true;
		return false;
	}

	@Override
	public void run() {
		//while (true) {
			while (isRun()) {
				try {
					if (this.pickup())
						this.getBall().setSpeedX(this.getBall().changeDirection(this.getBall().getSpeedX()));

					this.computerPaddleMove();
					this.checkPosition();
					/*if (borderCrossed()) {
						this.getBall().setBorderCrossed(true);
						throw new InterruptedException();
					}*/
					Thread.sleep(45);
					this.gamePanel.repaint();
				} catch (InterruptedException e) {
				//	while(!isRun());
				}
			}
			System.out.println("POZA");
		//}
	}
}
