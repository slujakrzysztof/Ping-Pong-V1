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

	boolean isMovingStraight();

	void setMovingStraight(boolean movingStraight);

	boolean isMovingFaster();

	void setMovingFaster(boolean movingFaster);
	
    boolean isMovingUp();

	boolean isMovingForward();
	
	boolean isRun();

	void setRun(boolean run);
}
