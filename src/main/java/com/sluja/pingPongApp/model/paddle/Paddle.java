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
	protected boolean conditionX = false;
	protected boolean conditionY = false;
	protected boolean increasedSpeedConditionFirst = false;
	protected boolean increasedSpeedConditionSecond = false;
	protected boolean changedSpeedConditionFirst = false;
	protected boolean changedSpeedConditionSecond = false;
	protected boolean straightStrikeConditionFirst = false;
	protected boolean straightStrikeConditionSecond = false;
	protected int reflectionAmount;
	protected boolean pointEarned;

	public Paddle(Player player, Ball ball) {
		this.player = player;
		this.homePosition = this.SCREEN_HEIGTH / 2 - this.HEIGTH / 2;
		this.setHomePosition();
		this.speed = 30;
		this.playerColor = player.getPlayerColor();
		this.positionX = player.getPositionX();
		this.realPositionX = WIDTH;
		this.ball = ball;
		this.pointEarned = false;
		this.reflectionAmount = 0;
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

	public GamePanel getGamePanel() {
		return this.gamePanel;
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
				&& (this.getBall().getPositionY() < (this.getPositionY() + this.HEIGTH));
		straightStrikeConditionFirst = this.getBall().getPositionY() >= ((this.getPositionY() + this.HEIGTH) / 2 - 20);
		straightStrikeConditionSecond = this.getBall().getPositionY() <= ((this.getPositionY() + this.HEIGTH) / 2 + 20);

		switch (this.getPlayer().getId()) {
		case 1: {
			conditionX = this.getRealPositionX() >= this.getBall().getPositionX();
			changedSpeedConditionFirst = (this.getBall().getPositionY() > this.getPositionY())
					&& (this.getBall().getPositionY() < (this.getPositionY() + 20));
			changedSpeedConditionSecond = ((this.getBall().getPositionY()
					+ this.getBall().getSizeY()) > (this.getPositionY() + this.HEIGTH - 20))
					&& ((this.getBall().getPositionY() + this.getBall().getSizeY()) < this.getPositionY()
							+ this.HEIGTH);
			break;
		}
		case 2: {
			conditionX = (this.getPositionX() - this.getBall().getSizeX()) < (this.getBall().getPositionX()
					+ this.getBall().getSizeX());
			changedSpeedConditionFirst = (this.getBall().getPositionY() < (this.getPositionY() + this.HEIGTH / 4));
			changedSpeedConditionSecond = this.getBall().getPositionY() < (this.getPositionY() + this.HEIGTH);
		}
		}

		if (conditionX && conditionY) {

			if (this.getBall().isMovingFaster()) {
				this.resetReflectionAmount();
				this.getBall().setMovingFaster(false);
			}

			if (this.reflectionAmount == this.getBall().getReflectionAmount()) {
				this.getBall().increaseSpeed();
				this.getBall().setMovingFaster(true);
			}
			if (straightStrikeConditionFirst && straightStrikeConditionSecond) {
				System.out.println("STRAIGHT");
				this.getBall().setSpeedY(0);
				this.getBall().setMovingStraight(true);
				straightStrikeConditionFirst = false;
				straightStrikeConditionSecond = false;
			} else if (this.getBall().isMovingStraight()) {
				if (this.getBall().getPositionY() > (this.getPositionY() + this.HEIGTH) / 2)
					this.getBall().restoreSpeedY();
				else {
					this.getBall().restoreSpeedY();
					this.getBall().changeDirection(this.getBall().getSpeedY());
				}
				this.getBall().setMovingStraight(false);
			}

			if (changedSpeedConditionFirst || changedSpeedConditionSecond)
				this.getBall().setSpeedY(this.getBall().changeDirection(this.getBall().getSpeedY()));

			this.reflectionAmount += 1;
			return true;
		}
		return false;
	}

	public void resetReflectionAmount() {
		this.reflectionAmount = 0;
		this.getBall().generateReflectionAmount();
	}

	@Override
	public void run() {
		while (isRun()) {
			try {
				this.getBall().move();
				if (this.pickup())
					this.getBall().setSpeedX(this.getBall().changeDirection(this.getBall().getSpeedX()));
				if ((this.getBall().earnPoint(this.getPlayer().getId(), this.getPositionX(),
						this.getGamePanel().getPaddles().get(1).getPositionX()))) {
					// || this.getBall().isBorderCrossed()) {
					// Thread.sleep(1000);
					throw new InterruptedException();
				}
				this.checkPosition();
				Thread.sleep(Math.abs(this.getBall().getSpeedX()));
				this.getGamePanel().repaint();
			} catch (InterruptedException e) {
				// this.getBall().setBorderCrossed(false);
				this.getGamePanel().setRun(false);
				this.getGamePanel().earnPoint();
				this.getGamePanel().setHomePosition();
				this.getGamePanel().repaint();

			}
		}
	}

}
