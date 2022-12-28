package com.sluja.pingPongApp.interfaces;

import java.awt.Graphics;

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
	
	public boolean earnPoint();
	
	public void restoreSpeed();
	
	public int changeDirection(int speed);

}
