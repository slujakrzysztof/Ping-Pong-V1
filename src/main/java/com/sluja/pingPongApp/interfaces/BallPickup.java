package com.sluja.pingPongApp.interfaces;

public interface BallPickup {

	// ----- GETTERS ----- //

	int getReflectionAmount();

	boolean isPickedUp();

	// ----- SETTERS ----- //

	void setPickedUp(boolean pickedUp);

	// Changing the direction of the ball movement
	int changeDirection(int speed);

	/*
	 * Generating number of reflection after which the speed of the ball will
	 * increase - using RandomGenerator
	 */
	void generateReflectionAmount();

	/*
	 * true - the ball is out of the playing field - player will earn the point
	 * false - the ball got picked up and the game is being continued
	 */
	boolean earnPoint(int firstPositionX, int secondPositionX);

}
