package com.sluja.pingPongApp.model;

public class Ball {

	protected int x, y, dx, dy;
	protected int speed;
	protected boolean movingForward, movingForwardVertical;

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
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
