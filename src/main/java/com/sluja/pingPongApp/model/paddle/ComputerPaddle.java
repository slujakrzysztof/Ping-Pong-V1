package com.sluja.pingPongApp.model.paddle;

import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.properties.PropertyReader;

public class ComputerPaddle extends Paddle {

	GameLevel gameLevel;
	private int speed;
	private int backSpeed;
	private int homePosition;
	private int border;
	private int lowerBorder, upperBorder;


	public ComputerPaddle(Player player, GameLevel gameLevel, Ball ball) {
		super(player,ball);
		this.gameLevel = gameLevel;
		this.ball = ball;
		this.homePosition = this.SCREEN_HEIGTH / 2;
		this.border = Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.border"));
		this.realPositionY = this.getPositionY() + this.HEIGTH;
		this.lowerBorder = this.homePosition + this.border;
		this.upperBorder = this.homePosition - this.border;
		this.setSpeed();
	}

	private void setSpeed() {
		String actualSpeed = "speed." + this.gameLevel.name().toLowerCase();
		String actualBackSpeed = "backSpeed." + this.gameLevel.name().toLowerCase();
		this.speed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualSpeed));
		this.backSpeed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualBackSpeed));
	}

	private void moveBack() {
		if (this.isMovingUp())
			positionY -= backSpeed;
		else
			positionY += backSpeed;
	}

	private void checkHomePosition() {


		if ((this.ball.getPositionX() < this.homePosition)
				&& !((realPositionY / 2) < this.lowerBorder && (realPositionY / 2) > this.upperBorder)) {
			if ((realPositionY / 2) > this.homePosition)
				this.setMovingUp(true);
			else
				this.setMovingUp(false);
			this.moveBack();
		}
	}
}
