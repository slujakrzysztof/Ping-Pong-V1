package com.sluja.pingPongApp.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.model.ball.RoundedBall;
import com.sluja.pingPongApp.model.paddle.ComputerPaddle;
import com.sluja.pingPongApp.model.paddle.Paddle;
import com.sluja.pingPongApp.properties.PropertyReader;
import com.sluja.pingPongApp.properties.SizeManager;
import com.sluja.pingPongApp.steering.Steering;

public class GamePanel extends JPanel {

	private final int SCREEN_WIDTH = SizeManager.getInstance().SCREEN_WIDTH;
	private final int SCREEN_HEIGHT = SizeManager.getInstance().SCREEN_HEIGHT;
	private final int SCORE_FIRST_POSITION_X = SizeManager.getInstance().SCORE_FIRST_POSITION_X;
	private final int SCORE_SECOND_POSITION_X = SizeManager.getInstance().SCORE_SECOND_POSITION_X;
	private final int SCORE_POSITION_Y = SizeManager.getInstance().SCORE_POSITION_Y;
	private final int PADDLE_FIRST_POSITION_X = SizeManager.getInstance().PADDLE_FIRST_POSITION_X;
	private final int PADDLE_SECOND_POSITION_X = SizeManager.getInstance().PADDLE_SECOND_POSITION_X;
	GameFrame gameFrame;
	GameForm gameForm;
	GameLevel gameLevel;
	Color backgroundColor = Color.BLACK;
	Steering steering;

	private Thread paddleFirstThread;
	private Thread paddleSecondThread;

	private ArrayList<Paddle> paddles = new ArrayList<Paddle>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Ball ball;
	private boolean run = true;

	private int pickedPlayer;
	private int points;
	private int sumPoint;

	public GamePanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.sumPoint = 0;
		this.setBackground(backgroundColor);
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	}

	public GamePanel(GameFrame gameFrame, GameForm gameForm, GameLevel gameLevel) {
		this(gameFrame);
		this.gameForm = gameForm;
		this.gameLevel = gameLevel;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // To change body of generated methods, choose Tools | Templates.
		this.paddles.forEach((paddle) -> paddle.draw(g));
		ball.draw(g); // drawing ball
	}

	public void setGameLevel(GameLevel gameLevel) {
		this.gameLevel = gameLevel;
	}

	public GameLevel getGameLevel() {
		return this.gameLevel;
	}

	private void initializePlayersArray() {
		this.players.add(new Player(1, PADDLE_FIRST_POSITION_X, SCORE_FIRST_POSITION_X, SCORE_POSITION_Y, Color.GREEN));
		this.players.add(new Player(2, PADDLE_SECOND_POSITION_X, SCORE_SECOND_POSITION_X, SCORE_POSITION_Y, Color.RED));
	}

	private void initializePaddle() {
		this.paddles.add(new Paddle(players.get(0), this));
		if (this.gameForm == GameForm.MULTIPLAYER)
			this.paddles.add(new Paddle(players.get(1), this));
		else
			this.paddles.add(new ComputerPaddle(players.get(1), this.getGameLevel(), this));
	}

	public GameForm getGameForm() {
		return this.gameForm;
	}

	public GamePanel getObject() {
		return this;
	}

	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	public Ball getBall() {
		return this.ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public void setGame() {
		this.setSteering(paddles);
		this.initializePlayersArray();
		this.initializePaddle();
		paddleFirstThread = new Thread(paddles.get(0));
		paddleSecondThread = new Thread(paddles.get(1));
		setRun(true);
		this.getBall().setThread();
		this.getBall().generateReflectionAmount();
		paddleFirstThread.start();
		paddleSecondThread.start();
	}

	public void setSteering(ArrayList<Paddle> paddles) {
		this.steering = new Steering(paddles, this.getGameForm(), this);
	}

	public void setRun(boolean run) {
		this.paddles.forEach((paddle) -> {
			paddle.setRun(run);
			if (!run)
				paddle.resetReflectionAmount();
			System.out.println("111: " + this.ball.getReflectionAmount());
		});
		this.ball.setRun(run);
	}

	public boolean isRun() {
		return this.run;
	}

	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	private int getPoints() {
		points = 0;
		players.forEach((player) -> points += player.getScore().getPoints());
		return points;
	}

	public ArrayList<Paddle> getPaddles() {
		return this.paddles;
	}

	private void resetScore() {
		this.players.forEach((player) -> player.resetScore());
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public void earnPoint() {
		if (this.ball.getPositionX() < SCREEN_WIDTH / 2)
			pickedPlayer = 1;
		else
			pickedPlayer = 0;
		this.players.get(pickedPlayer).getScore().addPoint();
		sumPoint = Math.abs(this.players.get(1).getScore().getPoints() - this.players.get(0).getScore().getPoints());
		if (!this.players.get(pickedPlayer).getScore().checkScore() || (sumPoint < 2)) {
			this.ball.setStartingPosition(this.getPoints());
			this.setRun(true);
		} else {
			this.resetGame();
			this.getGameFrame().getMainPanel().showPanel(this.getGameFrame().getMainPanel().MENU_PANEL);
		}
	}

	private void resetGame() {
		// this.resetScore();
		this.getPaddles().clear();
		this.getPlayers().clear();
		GamePanel gamePanel = this.getObject();
		gamePanel = null;
	}

	public void setHomePosition() {
		paddles.forEach((paddle) -> paddle.setHomePosition());
	}

}
