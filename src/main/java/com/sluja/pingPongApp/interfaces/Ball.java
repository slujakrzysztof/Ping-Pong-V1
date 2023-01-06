package com.sluja.pingPongApp.interfaces;

import java.awt.Graphics;

import com.sluja.pingPongApp.panel.GamePanel;

public interface Ball extends BallMovement, BallPickup{

	public int getPositionX();

	public int getPositionY();

	public int getSpeedX();

	public int getSpeedY();

	public int getSizeX();

	public int getSizeY();

	public void setPickedUp(boolean pickedUp);

	public void draw(Graphics g);

	public boolean isPickedUp();

	public void setPositionX(int positionX);

	public void setPositionY(int positionY);

	public void setSpeedX(int speedX);

	public void setSpeedY(int speedY);

	public boolean isMovingUp();

	public boolean isMovingForward();

	public void setStartingPosition(int points);

	public void setMovingUp(boolean movingUp);

	public GamePanel getGamePanel();

	public boolean isMovingStraight();

	public void setMovingStraight(boolean movingStraight);

	public boolean isRun();

	public void setRun(boolean run);

	boolean isMovingFaster();

	void setMovingFaster(boolean movingFaster);

	void setBorderCrossed(boolean borderCrossed);

	boolean isBorderCrossed();

	void setThread();
	
	void setFirstDirection(boolean firstDirection);

	boolean getFirstDirection();

	void move();

	boolean checkBorders();

	void increaseSpeed();

	void restoreSpeed();

	void restoreSpeedY();

	void restoreSpeedX();

	boolean checkPosition();
	
	int changeDirection(int speed);

	void generateReflectionAmount();

	int getReflectionAmount();

	boolean earnPoint(int firstPositionX, int secondPositionX);

}
