package com.sluja.pingPongApp.model;

import java.awt.Color;

public class Player {

	private Color playerColor;
	private int score;
	private int positionX;
	private int scorePositionX;

	public Player() {

	}

	public Player(int score, int positionX, int scorePositionX) {
		this.score = 0;
		this.positionX = positionX;
		this.scorePositionX = scorePositionX;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
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
}
