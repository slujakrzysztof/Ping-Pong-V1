package com.sluja.pingPongApp.model.paddle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.properties.PropertyReader;

public class Paddle {

	public final int WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.width"));
	public final int HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.height"));
	public final int SCREEN_HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.heigth"));
	public final int SCREEN_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.width"));
	protected int positionY;
	protected int positionX;
	protected int speed = 20;
	protected boolean movingUp;
	protected Color playerColor;
	protected Player player;

	public Paddle(Player player) {
		this.player = player;
		this.positionY = 80;
		this.playerColor = player.getPlayerColor();
		this.positionX = player.getPositionX();
	}

	public void draw(Graphics g) {
		g.setColor(playerColor);
		g.fillRect(positionX, positionY, WIDTH, HEIGTH);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		this.drawScore(g);
	}

	protected void drawScore(Graphics g) {
		String score = "" + this.player.getScore();
		int scorePositionX = this.player.getScorePositionX();
		int scorePositionY = Integer.parseInt(PropertyReader.getInstance().getProperty("score.PositionY"));
		g.drawString(score, scorePositionX, scorePositionY);
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public void move() {
		this.checkPosition();

		if (isMovingUp())
			positionY -= speed;
		else
			positionY += speed;
	}

	protected void checkPosition() {
		if (positionY <= 0)
			positionY = 0;
		if ((positionY + HEIGTH) >= SCREEN_HEIGTH)
			positionY = SCREEN_HEIGTH - HEIGTH;
	}

}
