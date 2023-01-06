package com.sluja.pingPongApp.interfaces;

public interface BallMovement {

	void setFirstDirection(boolean firstDirection);

	boolean getFirstDirection();

	void move();

	boolean checkBorders();

	void increaseSpeed();

	void restoreSpeed();

	void restoreSpeedY();

	void restoreSpeedX();

	boolean checkPosition();
}
