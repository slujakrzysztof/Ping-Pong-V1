package com.sluja.pingPongApp.properties;

import java.awt.Dimension;
import java.awt.Toolkit;

public class SizeManager {

	private static final SizeManager sizeManager = new SizeManager();

	public final int SCREEN_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.width"));
	public final int SCREEN_HEIGHT = Integer.parseInt(PropertyReader.getInstance().getProperty("window.height"));
	public final int PADDLE_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.width"));
	public final int PADDLE_HEIGHT = Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.height"));
	public final int PADDLE_BORDER_STRAIGHT_STRIKE = Integer
			.parseInt(PropertyReader.getInstance().getProperty("paddle.border.straightStrike"));
	public final int PADDLE_BORDER_CHANGED_SPEED = Integer
			.parseInt(PropertyReader.getInstance().getProperty("paddle.border.changedSpeed"));
	public final int SCORE_POSITION_Y = Integer.parseInt(PropertyReader.getInstance().getProperty("score.positionY"));
	public final int SPEED_BEGINNER = Integer.parseInt(PropertyReader.getInstance().getProperty("speed.beginner"));
	public final int SPEED_EASY = Integer.parseInt(PropertyReader.getInstance().getProperty("speed.easy"));
	public final int SPEED_MEDIUM = Integer.parseInt(PropertyReader.getInstance().getProperty("speed.medium"));
	public final int SPEED_HARD = Integer.parseInt(PropertyReader.getInstance().getProperty("speed.hard"));
	public final int SPEED_MASTER = Integer.parseInt(PropertyReader.getInstance().getProperty("speed.master"));
	public final int BALL_INCREASED_SPEED_X = Integer
			.parseInt(PropertyReader.getInstance().getProperty("ball.increasedSpeedX"));
	public final int BALL_INCREASED_SPEED_Y = Integer
			.parseInt(PropertyReader.getInstance().getProperty("ball.increasedSpeedY"));
	public final int SCREEN_BORDER = Integer.parseInt(PropertyReader.getInstance().getProperty("window.border"));
	public final int SCREEN_LOWER_BORDER = this.SCREEN_HEIGHT - this.SCREEN_BORDER;
	public final int SCREEN_UPPER_BORDER = this.SCREEN_BORDER;
	public final int SCREEN_LEFT_BORDER = this.SCREEN_BORDER;
	public final int SCREEN_RIGHT_BORDER = this.SCREEN_WIDTH - this.SCREEN_BORDER;
	public final int BALL_START_POSITION_Y = Integer
			.parseInt(PropertyReader.getInstance().getProperty("ball.startPositionY"));
	public final int BALL_MAX_REFLECTION_AMOUNT = Integer
			.parseInt(PropertyReader.getInstance().getProperty("ball.maxReflectionAmount"));
	public final int BUTTON_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("button.width"));
	public final int BUTTON_HEIGHT = Integer.parseInt(PropertyReader.getInstance().getProperty("button.height"));
	public final int SCREEN_POSITION_X = (Toolkit.getDefaultToolkit().getScreenSize().width / 2)
			- (this.SCREEN_WIDTH / 2);
	public final int SCREEN_POSITION_Y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2)
			- (this.SCREEN_HEIGHT / 2);
	public final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public final int SCORE_FIRST_POSITION_X = (int) (SCREEN_WIDTH * (0.25));
	public final int SCORE_SECOND_POSITION_X = (int) (SCREEN_WIDTH * (0.75));
	public final int PADDLE_FIRST_POSITION_X = this.SCREEN_LEFT_BORDER;
	public final int PADDLE_FIRST_REAL_POSITION_X = this.PADDLE_FIRST_POSITION_X + PADDLE_WIDTH;
	public final int PADDLE_SECOND_POSITION_X = this.SCREEN_RIGHT_BORDER - this.PADDLE_WIDTH;
	public final int BALL_ROUNDED_SIZE = Integer.parseInt(PropertyReader.getInstance().getProperty("roundedBall.size"));
	public final int BALL_ROUNDED_SPEED_X = Integer
			.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedX"));
	public final int BALL_ROUNDED_SPEED_Y = Integer
			.parseInt(PropertyReader.getInstance().getProperty("roundedBall.speedY"));

	public static SizeManager getInstance() {
		return sizeManager;
	}

}
