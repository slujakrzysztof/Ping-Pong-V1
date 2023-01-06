package com.sluja.pingPongApp.model.paddle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.SizeManager;

public class Paddle implements Runnable {

	// ----- CONSTANTS ----- //
	public final int PADDLE_WIDTH = SizeManager.getInstance().PADDLE_WIDTH;
	public final int PADDLE_HEIGHT = SizeManager.getInstance().PADDLE_HEIGHT;
	public final int SCREEN_HEIGHT = SizeManager.getInstance().SCREEN_HEIGHT;
	public final int SCREEN_WIDTH = SizeManager.getInstance().SCREEN_WIDTH;
	private final int PADDLE_FIRST_REAL_POSITION_X = SizeManager.getInstance().PADDLE_FIRST_REAL_POSITION_X;
	private final int PADDLE_BORDER_CHANGED_SPEED = SizeManager.getInstance().PADDLE_BORDER_CHANGED_SPEED;
	private final int PADDLE_BORDER_STRAIGHT_STRIKE = SizeManager.getInstance().PADDLE_BORDER_STRAIGHT_STRIKE;
	private final int PADDLE_SPEED = SizeManager.getInstance().PADDLE_SPEED;

	// ----- Primitive type variables ----- //
	protected int positionY;
	protected int positionX;
	protected int speed;
	protected boolean movingUp;
	protected int realPositionX;
	protected int homePosition;
	protected boolean run;
	protected boolean conditionX = false;
	protected boolean conditionY = false;
	protected boolean increasedSpeedConditionFirst = false;
	protected boolean increasedSpeedConditionSecond = false;
	protected boolean changedSpeedConditionFirst = false;
	protected boolean changedSpeedConditionSecond = false;
	protected boolean straightStrikeConditionFirst = false;
	protected boolean straightStrikeConditionSecond = false;
	protected int reflectionAmount;

	// ----- Variables ----- //
	protected Color playerColor;
	protected Player player;
	protected Ball ball;
	protected GamePanel gamePanel;

	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //

	public Paddle(Player player, GamePanel gamePanel) {
		this.player = player;
		this.homePosition = this.SCREEN_HEIGHT / 2 - this.PADDLE_HEIGHT / 2;
		this.setHomePosition();
		this.speed = PADDLE_SPEED;
		this.playerColor = player.getPlayerColor();
		this.positionX = player.getPositionX();
		this.realPositionX = this.PADDLE_FIRST_REAL_POSITION_X;
		this.gamePanel = gamePanel;
		this.ball = this.gamePanel.getBall();
		this.reflectionAmount = 0;
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //

	public boolean isRun() {
		return this.run;
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

	public int getPositionX() {
		return positionX;
	}

	public boolean isMovingUp() {
		return movingUp;
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

	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	public void setRun(boolean run) {
		this.run = run;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //

	// Drawing paddle model
	public void draw(Graphics g) {
		g.setColor(playerColor);
		g.fillRect(positionX, positionY, PADDLE_WIDTH, PADDLE_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		this.drawScore(g);
		this.drawPaddleBorders(g);
	}

	// Drawing score of the player
	protected void drawScore(Graphics g) {
		String score = "" + this.player.getScore().getPoints();
		int scorePositionX = this.player.getScorePositionX();
		int scorePositionY = this.player.getScorePositionY();
		g.drawString(score, scorePositionX, scorePositionY);
	}

	// Drawing the spheres of the specific ball behaviors on the paddle
	protected void drawPaddleBorders(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawLine(player.getPositionX(), (this.getPositionY() + this.PADDLE_BORDER_CHANGED_SPEED),
				(player.getPositionX() + this.PADDLE_WIDTH), (this.getPositionY() + this.PADDLE_BORDER_CHANGED_SPEED));

		g.drawLine(player.getPositionX(), (this.getPositionY() + this.PADDLE_HEIGHT - this.PADDLE_BORDER_CHANGED_SPEED),
				(player.getPositionX() + this.PADDLE_WIDTH),
				(this.getPositionY() + this.PADDLE_HEIGHT - this.PADDLE_BORDER_CHANGED_SPEED));

		g.drawLine(player.getPositionX(),
				((this.getPositionY() + this.PADDLE_HEIGHT / 2) - this.PADDLE_BORDER_STRAIGHT_STRIKE),
				(player.getPositionX() + this.PADDLE_WIDTH),
				(this.getPositionY() + this.PADDLE_HEIGHT / 2) - this.PADDLE_BORDER_STRAIGHT_STRIKE);

		g.drawLine(player.getPositionX(),
				((this.getPositionY() + this.PADDLE_HEIGHT / 2) + this.PADDLE_BORDER_STRAIGHT_STRIKE),
				(player.getPositionX() + this.PADDLE_WIDTH),
				((this.getPositionY() + this.PADDLE_HEIGHT / 2) + this.PADDLE_BORDER_STRAIGHT_STRIKE));
	}

	/*
	 * true - the paddle touches the upper border of the screen - limiting the
	 * movement (position Y is set to 0)
	 */
	public boolean checkUpperPosition() {
		if (this.getPositionY() <= 0) {
			positionY = 0;
			return true;
		}
		return false;
	}

	/*
	 * true - the paddle touches the lower border of the screen - limiting the
	 * movement (position Y is set to the height of the paddle)
	 */
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

	/*
	 * true - the ball touches the paddle
	 */
	public boolean pickup() {

		conditionY = ((this.getBall().getPositionY() + this.getBall().getSizeY()) > this.getPositionY())
				&& (this.getBall().getPositionY() < (this.getPositionY() + this.PADDLE_HEIGHT));
		straightStrikeConditionFirst = this.getBall().getPositionY() >= ((this.getPositionY() + this.PADDLE_HEIGHT / 2)
				- this.PADDLE_BORDER_STRAIGHT_STRIKE);
		straightStrikeConditionSecond = this.getBall().getPositionY() <= ((this.getPositionY() + this.PADDLE_HEIGHT / 2)
				+ this.PADDLE_BORDER_STRAIGHT_STRIKE);
		changedSpeedConditionFirst = (this.getBall().getPositionY() > this.getPositionY())
				&& (this.getBall().getPositionY() < (this.getPositionY() + this.PADDLE_BORDER_CHANGED_SPEED));
		changedSpeedConditionSecond = ((this.getBall().getPositionY()
				+ this.getBall().getSizeY()) > (this.getPositionY() + this.PADDLE_HEIGHT
						- this.PADDLE_BORDER_CHANGED_SPEED))
				&& ((this.getBall().getPositionY() + this.getBall().getSizeY()) < this.getPositionY()
						+ this.PADDLE_HEIGHT);

		switch (this.getPlayer().getId()) {
		case 1: {
			conditionX = this.getRealPositionX() >= this.getBall().getPositionX();
			break;
		}
		case 2: {
			conditionX = (this.getPositionX() - this.getBall().getSizeX()) < this.getBall().getPositionX();
		}
		}

		if (conditionX && conditionY) {

			// Set the ball speed to the default value on the first pickup after increasing
			if (this.getBall().isMovingFaster()) {
				this.resetReflectionAmount();
				this.getBall().setMovingFaster(false);
			}

			if (this.reflectionAmount == this.getBall().getReflectionAmount()) {
				this.getBall().increaseSpeed();
				this.getBall().setMovingFaster(true);
			}

			if (straightStrikeConditionFirst && straightStrikeConditionSecond) {
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

	// Change the direction of the ball movement after pickup
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
