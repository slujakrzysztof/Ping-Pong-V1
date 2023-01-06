package com.sluja.pingPongApp.model;

import java.awt.Color;

public class Player {

	// ----- Primitive type variables ----- //
	private int positionX;
	private int scorePositionX, scorePositionY;
	private int id;

	// ----- Variables ----- //
	private Color playerColor;
	private Score score;

	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //

	public Player(int id, int positionX, int scorePositionX, int scorePositionY, Color playerColor) {
		this.id = id;
		this.positionX = positionX;
		this.scorePositionX = scorePositionX;
		this.scorePositionY = scorePositionY;
		this.playerColor = playerColor;
		this.score = new Score();
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //

	public Color getPlayerColor() {
		return playerColor;
	}

	public Score getScore() {
		return score;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getScorePositionX() {
		return scorePositionX;
	}

	public int getScorePositionY() {
		return scorePositionY;
	}

	public int getId() {
		return this.id;
	}

	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setScorePositionX(int scorePositionX) {
		this.scorePositionX = scorePositionX;
	}

	public void setScorePositionY(int scorePositionY) {
		this.scorePositionY = scorePositionY;
	}

	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //

	public void resetScore() {
		this.score.resetScore();
	}

}
