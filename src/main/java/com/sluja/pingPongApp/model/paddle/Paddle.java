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
import com.sluja.pingPongApp.properties.SizeManager;

public class Paddle implements Runnable {

	public final int PADDLE_WIDTH = SizeManager.getInstance().PADDLE_WIDTH;
	public final int PADDLE_HEIGHT = SizeManager.getInstance().PADDLE_HEIGHT;
	public final int SCREEN_HEIGHT = SizeManager.getInstance().SCREEN_HEIGHT;
	public final int SCREEN_WIDTH = SizeManager.getInstance().SCREEN_WIDTH;
	private final int PADDLE_FIRST_REAL_POSITION_X = SizeManager.getInstance().PADDLE_FIRST_REAL_POSITION_X;
	private final int SCREEN_LOWER_BORDER = SizeManager.getInstance().SCREEN_LOWER_BORDER;
	private final int SCREEN_UPPER_BORDER = SizeManager.getInstance().SCREEN_UPPER_BORDER;
	private final int SCREEN_LEFT_BORDER = SizeManager.getInstance().SCREEN_LEFT_BORDER;
	private final int SCREEN_RIGHT_BORDER = SizeManager.getInstance().SCREEN_RIGHT_BORDER;

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

	public Paddle(Player player, GamePanel gamePanel) {
		this.player = player;
		this.homePosition = this.SCREEN_HEIGHT / 2 - this.PADDLE_HEIGHT / 2;
		this.setHomePosition();
		this.speed = 30;
		this.playerColor = player.getPlayerColor();
		this.positionX = player.getPositionX();
		this.realPositionX = this.PADDLE_FIRST_REAL_POSITION_X;
		this.gamePanel = gamePanel;
		this.ball = this.gamePanel.getBall();
		this.pointEarned = false;
		this.reflectionAmount = 0;
		// this.gamePanel = this.ball.getGamePanel();
	}

	public void draw(Graphics g) {
		g.setColor(playerColor);
		g.fillRect(positionX, positionY, PADDLE_WIDTH, PADDLE_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
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

	public int getSpeed() {
		return this.speed;
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
		if (this.getPositionY() <= 0) {
			positionY = 0;
			return true;
		}
		return false;
	}

	public boolean checkLowerPosition() {
		if (this.getPositionY() >= (this.SCREEN_HEIGHT - this.PADDLE_HEIGHT))
			return true;
		return false;
	}

	public void checkPosition() {

		if (this.getPositionY() <= 0)
			this.setPositionY(0);

		if (this.getPositionY() >= (this.SCREEN_HEIGHT - this.PADDLE_HEIGHT))
			this.setPositionY(this.SCREEN_HEIGHT - this.PADDLE_HEIGHT);

	}

	public void moveUp() {
		this.setPositionY(this.getPositionY() - this.getSpeed());
	}

	public void moveDown() {
		this.setPositionY(this.getPositionY() + this.getSpeed());
	}

	public void setHomePosition() {
		this.setPositionY(this.getHomePosition());
	}

	public boolean pickup() {

		conditionY = ((this.getBall().getPositionY() + this.getBall().getSizeY()) > this.getPositionY())
				&& (this.getBall().getPositionY() < (this.getPositionY() + this.PADDLE_HEIGHT));
		straightStrikeConditionFirst = this.getBall()
				.getPositionY() >= ((this.getPositionY() + this.PADDLE_HEIGHT) / 2 - 20);
		straightStrikeConditionSecond = this.getBall()
				.getPositionY() <= ((this.getPositionY() + this.PADDLE_HEIGHT) / 2 + 20);

		switch (this.getPlayer().getId()) {
		case 1: {
			conditionX = this.getRealPositionX() /* + this.getBall().getSizeX()) */ >= this.getBall().getPositionX();
			changedSpeedConditionFirst = (this.getBall().getPositionY() > this.getPositionY())
					&& (this.getBall().getPositionY() < (this.getPositionY() + 20));
			changedSpeedConditionSecond = ((this.getBall().getPositionY()
					+ this.getBall().getSizeY()) > (this.getPositionY() + this.PADDLE_HEIGHT - 20))
					&& ((this.getBall().getPositionY() + this.getBall().getSizeY()) < this.getPositionY()
							+ this.PADDLE_HEIGHT);
			break;
		}
		case 2: {
			conditionX = (this.getPositionX() - this.getBall().getSizeX()) < this.getBall().getPositionX();
			// + this.getBall().getSizeX());
			changedSpeedConditionFirst = (this.getBall()
					.getPositionY() < (this.getPositionY() + this.PADDLE_HEIGHT / 4));
			changedSpeedConditionSecond = this.getBall().getPositionY() < (this.getPositionY() + this.PADDLE_HEIGHT);
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
				if (this.getBall().getPositionY() > (this.getPositionY() + this.PADDLE_HEIGHT) / 2)
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

	public void checkPickup() {
		if (this.pickup())
			this.getBall().setSpeedX(this.getBall().changeDirection(this.getBall().getSpeedX()));
	}

	public void resetReflectionAmount() {
		this.reflectionAmount = 0;
		this.getBall().generateReflectionAmount();
	}

	@Override
	public void run() {
		while (isRun()) {
			try {
				this.checkPosition();
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}

}
