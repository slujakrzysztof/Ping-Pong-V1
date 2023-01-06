package com.sluja.pingPongApp.model;

public class Score {

	// ----- Primitive type variables ----- //
	private int points;
	private int winningPoints;
	private boolean playerWon;

	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //

	public Score() {
		this.points = 0;
		this.winningPoints = 11;
		this.playerWon = false;
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //

	public int getPoints() {
		return points;
	}

	public void getWinningPoints(int winningPoints) {
		this.winningPoints = winningPoints;
	}

	public int getWinningPoints() {
		return this.winningPoints;
	}

	public boolean isPlayerWon() {
		return playerWon;
	}

	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	public void setPoints(int points) {
		this.points = points;
	}

	public void setPlayerWon(boolean playerWon) {
		this.playerWon = playerWon;
	}

	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //

	public void addPoint() {
		this.setPoints(this.getPoints() + 1);
	}

	/*
	 * true - if player earns a certain amount of points (winningPoints), he wins
	 * the game
	 */
	public boolean checkScore() {
		if (this.getPoints() >= this.getWinningPoints())
			this.setPlayerWon(true);
		return this.isPlayerWon();
	}

	public void resetScore() {
		this.points = 0;
	}

}
