package com.sluja.pingPongApp.model.paddle;

import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.PropertyReader;

public class ComputerPaddle extends Paddle implements Runnable {

	// ----- Primitive type variables ----- //
	private int borderX;

	// ----- Variables ----- //
	GameLevel gameLevel;

	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //

	public ComputerPaddle(Player player, GameLevel gameLevel, GamePanel gamePanel) {
		super(player, gamePanel);
		this.gameLevel = gameLevel;
		this.borderX = (int) (this.SCREEN_WIDTH
				* Float.parseFloat(PropertyReader.getInstance().getProperty("paddle.computer.borderX")));
		this.setSpeed();
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //

	public GameLevel getGameLevel() {
		return this.gameLevel;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	private void setSpeed() {
		String actualSpeed = "speed." + this.gameLevel.name().toLowerCase();
		this.speed = Integer.parseInt(PropertyReader.getInstance().getProperty(actualSpeed));
	}

	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //

	/*
	 * Enabling movement of the paddle when the ball crosses the border If ball is
	 * moving up - the paddle is also moving up (the same situation when moving
	 * down)
	 */
	public void computerPaddleMove() {

		if ((this.getBall().getPositionX() + this.getBall().getSizeX()) > this.borderX) {

			if (this.getPositionY() > this.getBall().getPositionY())
				this.setMovingUp(true);
			else if (this.getPositionY() < this.getBall().getPositionY())
				this.setMovingUp(false);
			if (isMovingUp())
				this.setPositionY(this.getPositionY() - this.getSpeed());
			else
				this.setPositionY(this.getPositionY() + this.getSpeed());
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
