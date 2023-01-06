package com.sluja.pingPongApp.interfaces;

public interface BallMovement {

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //

	boolean getFirstDirection();

	boolean isMovingStraight();

	boolean isMovingFaster();

	boolean isMovingUp();

	boolean isMovingForward();

	boolean isRun();

	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	void setFirstDirection(boolean firstDirection);

	void setMovingFaster(boolean movingFaster);

	void setMovingStraight(boolean movingStraight);

	void setRun(boolean run);

	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //

	void move();

	//Enable ball reflection when it touches the borders of the screen 
	boolean checkBorders();

	void increaseSpeed();
	
	/*
	 * true - the ball is on the left side of the screen (player two earns the point)
	 * false - the ball is on the right side of the screen (player one earns the point) 
	 */
	boolean checkPosition();

	//Reset speed in both directions
	void restoreSpeed();

	void restoreSpeedY();

	void restoreSpeedX();

}
