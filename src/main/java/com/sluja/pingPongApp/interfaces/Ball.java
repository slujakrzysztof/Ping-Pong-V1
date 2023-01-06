package com.sluja.pingPongApp.interfaces;

import java.awt.Graphics;

import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.SizeManager;

public interface Ball extends BallMovement, BallPickup{
	 
	int getPositionX();

	int getPositionY();

	int getSpeedX();

	int getSpeedY();

	int getSizeX();

	int getSizeY();

	void draw(Graphics g);

	void setStartingPosition(int points);

	GamePanel getGamePanel();
	
	void setThread();
	
	void setFirstDirection(boolean firstDirection);
	
	void setSpeedX(int speedX);
	
	void setSpeedY(int speedY);

	boolean getFirstDirection();

}
