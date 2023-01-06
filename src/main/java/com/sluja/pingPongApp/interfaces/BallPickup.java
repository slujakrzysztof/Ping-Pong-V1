package com.sluja.pingPongApp.interfaces;

public interface BallPickup {

	int changeDirection(int speed);

	void generateReflectionAmount();

	int getReflectionAmount();

	boolean earnPoint(int firstPositionX, int secondPositionX);
}
