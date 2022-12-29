package com.sluja.pingPongApp.model.paddle;

import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.properties.PropertyReader;

public class ComputerPaddle extends Paddle {

	GameLevel gameLevel;
	private int speed;
	private int backSpeed;
	private int homePositionY;
	private int border;
	private int lowerBorder, upperBorder;
	private int borderX;

	public ComputerPaddle(Player player, GameLevel gameLevel, Ball ball) {
		super(player, ball);
		this.gameLevel = gameLevel;
		this.ball = ball;
		this.homePositionY = this.SCREEN_HEIGTH / 2 - this.HEIGTH / 2;
		this.border = setBorder(gameLevel);
		this.realPositionY = this.getPositionY() + this.HEIGTH;
		this.lowerBorder = this.homePositionY + this.border;
		this.upperBorder = this.homePositionY - this.border;
		this.borderX = (int) (this.SCREEN_WIDTH
				* Float.parseFloat(PropertyReader.getInstance().getProperty("paddle.computer.borderX")));
		this.setSpeed();
	}

	private int setBorder(GameLevel gameLevel) {
		String borderValue = "paddle.computer.border." + gameLevel.name().toLowerCase();
		return Integer.parseInt(PropertyReader.getInstance().getProperty(borderValue));
	}
	
	private void setSpeed() {
		String actualSpeed = "speed." + this.gameLevel.name().toLowerCase();
		String actualBackSpeed = "backSpeed." + this.gameLevel.name().toLowerCase();
		this.speed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualSpeed));
		this.backSpeed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualBackSpeed));
	}

	public void computerPaddleMove() {
		if (this.ball.getPositionX() > this.borderX) {
			if (this.getPositionY() > this.ball.getPositionY())
				this.setMovingUp(true);
			else if(this.getPositionY() < this.ball.getPositionY())
				this.setMovingUp(false);
			this.move();
			System.out.println("Is moving UP" + isMovingUp());
		}
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
}
