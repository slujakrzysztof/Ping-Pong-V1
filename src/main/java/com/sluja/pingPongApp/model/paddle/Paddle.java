package com.sluja.pingPongApp.model.paddle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.channels.ShutdownChannelGroupException;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.PropertyReader;

public class Paddle implements Runnable {

	public final int WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.width"));
	public final int HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.height"));
	public final int SCREEN_HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.heigth"));
	public final int SCREEN_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.width"));
	public final int PADDLE_BORDER = Integer
			.parseInt(PropertyReader.getInstance().getProperty("paddle.increasedBorder"));
	protected int positionY;
	protected int positionX;
	protected int speed;
	protected boolean movingUp;
	protected Color playerColor;
	protected Player player;
	protected Ball ball;
	protected int realPositionY;
	protected int realPositionX;
	protected int homePosition;
	protected boolean pickedUp;
	protected boolean run;
	protected int sizeH;
	protected GamePanel gamePanel;
	boolean conditionX = false;
	boolean conditionY = false;
	boolean increasedSpeedConditionFirst = false;
	boolean increasedSpeedConditionSecond = false;
	boolean changedSpeedConditionFirst = false;
	boolean changedSpeedConditionSecond = false;

	public Paddle(Player player, Ball ball) {
		this.player = player;
		this.setHomePosition();
		this.homePosition = this.SCREEN_HEIGTH / 2 - this.HEIGTH / 2;
		this.speed = 20;
		this.playerColor = player.getPlayerColor();
		this.positionX = player.getPositionX();
		this.realPositionX = WIDTH;
		this.ball = ball;
		this.gamePanel = this.ball.getGamePanel();
	}

	public void draw(Graphics g) {
		g.setColor(playerColor);
		g.fillRect(positionX, positionY, WIDTH, HEIGTH);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		this.drawScore(g);
	}

	protected void drawScore(Graphics g) {
		String score = "" + this.player.getScore().getPoints();
		int scorePositionX = this.player.getScorePositionX();
		int scorePositionY = this.player.getScorePositionY();
		g.drawString(score, scorePositionX, scorePositionY);
	}

	public boolean isRun() {
		return this.run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public int getHomePosition() {
		return this.homePosition;
	}

	public Ball getBall() {
		return this.ball;
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

	public int getRealPositionX() {
		return this.realPositionX;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public boolean isPickedUp() {
		return this.pickedUp;
	}

	public boolean checkUpperPosition() {
		if (positionY <= 0) {
			positionY = 0;
			return true;
		}
		return false;
	}
	
	public boolean checkLowerPosition() {
		int lowerBorder = SCREEN_HEIGTH - HEIGTH - speed;
		if (positionY >= lowerBorder) {
			positionY = lowerBorder;
			return true;
		}
		return false;
	}


	public void checkPosition() {
		this.checkUpperPosition();
		this.checkLowerPosition();
	}

	public void moveUp() {
		this.setPositionY(this.getPositionY() - this.speed);
	}

	public void moveDown() {
		this.setPositionY(this.getPositionY() + this.speed);
	}

	public void setHomePosition() {
		this.setPositionY(this.getHomePosition());
	}

	public boolean pickup() {

		conditionY = ((this.getBall().getPositionY() + this.getBall().getSizeY()) > this.getPositionY())
				&& ((this.getBall().getPositionY() + this.getBall().getSizeY()) < (this.getPositionY() + this.HEIGTH));

		switch (this.getPlayer().getId()) {
		case 1: {
			conditionX = this.getRealPositionX() >= this.getBall().getPositionX();
			increasedSpeedConditionFirst = this.getBall().getPositionY() > this.getPositionY();
			increasedSpeedConditionSecond = this.getBall()
					.getPositionY() > (this.getPositionY() + this.HEIGTH - this.PADDLE_BORDER);
			changedSpeedConditionFirst = this.getBall().getPositionY() > this.getPositionY();
			changedSpeedConditionSecond = this.getBall().getPositionY() > (this.getPositionY() + this.HEIGTH * (0.75));
			break;
		}
		case 2: {
			conditionX = this.getPositionX() < (this.getBall().getPositionX() + this.getBall().getSizeX());
			increasedSpeedConditionFirst = this.getBall().getPositionY() < (this.getPositionY() + this.PADDLE_BORDER);
			increasedSpeedConditionSecond = this.getBall().getPositionY() < (this.getPositionY() + this.HEIGTH);
			changedSpeedConditionFirst = this.getBall().getPositionY() < (this.getPositionY() + this.HEIGTH / 4);
			changedSpeedConditionSecond = this.getBall().getPositionY() < (this.getPositionY() + this.HEIGTH);
		}
		}

		if (conditionX && conditionY) {
			/*
			 * if (increasedSpeedConditionFirst || increasedSpeedConditionSecond) {
			 * this.getBall().increaseSpeed(); System.out.println("1"); }
			 */
			if (changedSpeedConditionFirst || changedSpeedConditionSecond) {
				this.getBall().setSpeedY(this.getBall().changeDirection(this.getBall().getSpeedY()));
				this.setPickedUp(true);
				System.out.println("2");
			}
			return true;
		}
		return false;
	}

	@Override
	public void run() {
		while (isRun()) {
			if (this.pickup())
				this.getBall().setSpeedX(this.getBall().changeDirection(this.getBall().getSpeedX()));
			this.checkPosition();
			try {
				Thread.sleep(80);
				// this.gamePanel.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
