package com.sluja.pingPongApp.model;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.sluja.pingPongApp.enums.GameForm;

public class Player {

	private Color playerColor;
	private Score score;
	private int positionX;
	private int scorePositionX, scorePositionY;
	private int id;

	public Player(int id, int positionX, int scorePositionX, int scorePositionY, Color playerColor) {
		this.id = id;
		this.positionX = positionX;
		this.scorePositionX = scorePositionX;
		this.scorePositionY = scorePositionY;
		this.playerColor = playerColor;
		this.score = new Score();
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getScorePositionX() {
		return scorePositionX;
	}

	public void setScorePositionX(int scorePositionX) {
		this.scorePositionX = scorePositionX;
	}
	
	public int getScorePositionY() {
		return scorePositionY;
	}

	public void setScorePositionY(int scorePositionY) {
		this.scorePositionY = scorePositionY;
	}
	
	public int getId() {
		return this.id;
	}

}
