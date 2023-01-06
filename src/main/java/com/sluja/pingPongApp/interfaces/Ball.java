package com.sluja.pingPongApp.interfaces;

import java.awt.Graphics;

import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.SizeManager;

public interface Ball extends BallMovement, BallPickup{
	
	// ----- GETTERS ----- // 
	int getPositionX();

	int getPositionY();

	int getSpeedX();

	int getSpeedY();

	int getSizeX();

	int getSizeY();

	boolean getFirstDirection();
	
	GamePanel getGamePanel();
	
	// ----- SETTERS ----- // 
	
	void setFirstDirection(boolean firstDirection);
	
	void setSpeedX(int speedX);
	
	void setSpeedY(int speedY);
	
	void setStartingPosition(int points);

	void setThread();	 
	
	// ------------------- //

	void draw(Graphics g);	

}
