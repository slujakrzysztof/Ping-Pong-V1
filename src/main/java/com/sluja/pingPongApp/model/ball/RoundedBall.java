package com.sluja.pingPongApp.model.ball;

import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.properties.PropertyReader;

public class RoundedBall implements Ball {

	private int size;
	private int positionX;
	private int positionY;
	private int realPositionX;
	private int speedX;
	private int speedY;
	private GameLevel gameLevel;
	private int increasedSpeedX;
	private int increasedSpeedY;
	private boolean movingForward;
	private final int SCREEN_HEIGHT = Integer.parseInt(PropertyReader.getInstance().getProperty("window.heigth"));
	
	public RoundedBall(GameLevel gameLevel) {
		this.size = Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.size"));
		this.speedX = Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.dX"));
		this.speedY = Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.dY"));
		this.gameLevel = gameLevel;
	}
	
	@Override
	public int getPositionX() {
		return this.positionX;
	}

	@Override
	public int getPositionY() {
		return this.positionY;
	}

	@Override
	public int getSpeedX() {
		return this.speedX;
	}

	@Override
	public int getSpeedY() {
		return this.speedY;
	}
	
	@Override
	public int getSizeX() {
		return this.size;
	}

	@Override
	public int getSizeY() {
		return this.size;
	}
	
	@Override
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	@Override
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	@Override
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	@Override
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	@Override
	public boolean isMovingForward() {
		return this.movingForward;
	}

	@Override
	public void setMovingForward(boolean movingForward) {
		this.movingForward = movingForward;
	}
	
	@Override
	public void move() {
		if(checkBorders()) 	this.setSpeedY(this.changeDirection());
		
	}

	@Override
	public boolean checkBorders() {
		if(this.getPositionY() < 0) {
			this.setPositionY(0);
			return true;
		}
		else if(this.getPositionY() + this.size > this.SCREEN_HEIGHT){
			this.setPositionY(this.SCREEN_HEIGHT - this.size);
			return true;
		}
		return false;
	}

	@Override
	public void increaseSpeed() {
		increasedSpeedX = Integer.parseInt(PropertyReader.getInstance().getProperty("ball.increasedSpeedX"));
		increasedSpeedY = Integer.parseInt(PropertyReader.getInstance().getProperty("ball.increasedSpeedY"));
		this.setSpeedX(increasedSpeedX);
		this.setSpeedY(increasedSpeedY);
	}
	
	@Override
	public void restoreSpeed() {
		this.setSpeedX(Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.dX")));
		this.setSpeedY(Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.dY")));
	}

	@Override
	public int changeDirection() {
		return this.getSpeedY() * (-1);
	}


	
	



}
