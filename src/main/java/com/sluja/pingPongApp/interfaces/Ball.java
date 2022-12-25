package com.sluja.pingPongApp.interfaces;

public interface Ball {
	
	public int getPositionX();

	public int getPositionY();

	public int getSpeedX();

	public int getSpeedY();
	
	public int getSizeX();
	
	public int getSizeY();
	
	public void setPickedUp(boolean pickedUp);

	public boolean isPickedUp();
	
	public void setPositionX(int positionX);

	public void setPositionY(int positionY);

	public void setSpeedX(int speedX);

	public void setSpeedY(int speedY);
	
	public boolean isMovingForward();
	
	public void setMovingForward(boolean movingForward);
	
	public void move();
	
	public boolean checkBorders();
	
	public void increaseSpeed();
	
	public void restoreSpeed();
	
	public int changeDirection(int speed);

}
