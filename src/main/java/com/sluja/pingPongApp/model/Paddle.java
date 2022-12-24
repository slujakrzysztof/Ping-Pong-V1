package com.sluja.pingPongApp.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sluja.pingPongApp.properties.PropertyReader;

public class Paddle {

	private final int WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("paddleWidth"));
	private final int HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("paddleHeight"));

	private int dY = 20;
	private int positionY;
	private int positionX;
	private boolean movingDirection;
	private Color playerColor;
	
	private Player player;

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
	
	private void drawScore(Graphics g) {
		
	}

}
