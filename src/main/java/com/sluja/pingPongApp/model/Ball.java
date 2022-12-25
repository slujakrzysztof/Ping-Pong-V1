package com.sluja.pingPongApp.model;

public class Ball {

	protected int positionX, positionY, speedX, speedY;
	protected int speed;
	protected boolean movingForward, movingForwardVertical;

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isMovingForward() {
		return movingForward;
	}

	public void setMovingForward(boolean movingForward) {
		this.movingForward = movingForward;
	}

	public boolean isMovingForwardVertical() {
		return movingForwardVertical;
	}

	public void setMovingForwardVertical(boolean movingForwardVertical) {
		this.movingForwardVertical = movingForwardVertical;
	}
}
