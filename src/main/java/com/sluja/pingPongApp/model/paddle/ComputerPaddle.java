package com.sluja.pingPongApp.model.paddle;

import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.PropertyReader;

public class ComputerPaddle extends Paddle implements Runnable {

	GameLevel gameLevel;
	private int speed;
	private int backSpeed;

	private int borderX;

	public ComputerPaddle(Player player, GameLevel gameLevel, GamePanel gamePanel) {
		super(player, gamePanel);
		this.gameLevel = gameLevel;
		this.realPositionY = this.getPositionY() + this.PADDLE_HEIGHT;
		this.borderX = (int) (this.SCREEN_WIDTH
				* Float.parseFloat(PropertyReader.getInstance().getProperty("paddle.computer.borderX")));
		this.setSpeed();
	}

	public int getBackSpeed() {
		return this.backSpeed;
	}

	public GameLevel getGameLevel() {
		return this.gameLevel;
	}

	private void setSpeed() {
		String actualSpeed = "speed." + this.gameLevel.name().toLowerCase();
		this.speed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualSpeed));
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
	}

	@Override
	public void run() {
		while (isRun()) {
			try {

				this.computerPaddleMove();
				this.checkPosition();

				Thread.sleep(45);
			
			} catch (InterruptedException e) {

			}
		}
	}
}
