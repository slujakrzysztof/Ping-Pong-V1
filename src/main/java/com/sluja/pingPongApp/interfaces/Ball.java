package com.sluja.pingPongApp.interfaces;

import java.awt.Graphics;

import com.sluja.pingPongApp.panel.GamePanel;

public interface Ball {
	
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
	
	public void setFirstDirection(boolean firstDirection);
	
	public boolean getFirstDirection();
	
	public void setMovingUp(boolean movingUp);
	
	public void move();
	
	public boolean checkBorders();
	
	public void increaseSpeed();
	
	public boolean earnPoint(int firstPositionX, int secondPositionX);
	
	public void restoreSpeed();
	
	public GamePanel getGamePanel();
	
	public boolean isMovingStraight();
	
	public void setMovingStraight(boolean movingStraight);
	
	public void restoreSpeedY();
	
	public void restoreSpeedX();
	
	public boolean isRun();
	
	public void setRun(boolean run);
	
	public int changeDirection(int speed);

	void generateReflectionAmount();

	int getReflectionAmount();
	
	boolean isMovingFaster();
	
	void setMovingFaster(boolean movingFaster);
	
	boolean checkPosition();
	
	void setBorderCrossed(boolean borderCrossed);
	
	boolean isBorderCrossed();
	
	void setThread();

}
