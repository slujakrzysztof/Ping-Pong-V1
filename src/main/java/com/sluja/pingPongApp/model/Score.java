package com.sluja.pingPongApp.model;

public class Score {

	private int points;
	private int winningPoints;
	private boolean playerWon;

	public Score() {
		this.points = 0;
		this.winningPoints = 11;
		this.playerWon = false;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getWinningPoints() {
		return this.winningPoints;
	}

	public boolean isPlayerWon() {
		return playerWon;
	}

	public void setPlayerWon(boolean playerWon) {
		this.playerWon = playerWon;
	}

	public void addPoint() {
		this.setPoints(this.getPoints() + 1);
	}

	public boolean checkScore() {
		if(this.getPoints() >= this.getWinningPoints()) this.setPlayerWon(true);
		return this.isPlayerWon();
	}

}
