package com.sluja.pingPongApp.panel;

import java.awt.Color;
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
import com.sluja.pingPongApp.steering.Steering;

public class GamePanel extends JPanel implements Runnable {

	GameFrame gameFrame;
	GameForm gameForm;
	GameLevel gameLevel;
	Color backgroundColor = Color.BLACK;
	Steering steering;
	private Thread paddleFirstThread;
	private Thread paddleSecondThread;
	private Thread ballThread;
	private Thread mainThread;
	private ArrayList<Paddle> paddles = new ArrayList<Paddle>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Ball ball;
	private boolean run = true;
	private int pickedPlayer;
	private int points;

	public GamePanel(GameFrame gameFrame, GameForm gameForm, GameLevel gameLevel) {
		this.gameFrame = gameFrame;
		this.gameForm = gameForm;
		this.gameLevel = gameLevel;
		this.setBackground(backgroundColor);
		this.setFocusable(true);
		this.ball = new RoundedBall(gameLevel, this);
		this.initializePlayersArray();
		this.initializePaddle();
		mainThread = new Thread(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // To change body of generated methods, choose Tools | Templates.
		this.paddles.get(0).draw(g); // drawing first paddle
		this.paddles.get(1).draw(g); // drawing second paddle
		ball.draw(g); // drawing ball
		// if (ball.getScoreEnd())
		// ball.paintResult(g); // If game ends, the score with information will be
		// displayed
		// if (!gameStarted)
		// drawInfo(g); // Displaying informations at the beginning and at the end
	}
	
	private void initializePlayersArray() {
		int firstScorePositionX = (int) (this.gameFrame.getScreenWidth() * (0.25));
		int secondScorePositionX = (int) (this.gameFrame.getScreenWidth() * (0.75));
		int scorePositionY = (int) (this.gameFrame.getScreenHeigth() * (0.25));
		int secondPositionX = (int) (this.gameFrame.getScreenWidth()
				- Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.width"))  - 15);
		this.players.add(new Player(1, 0, firstScorePositionX, scorePositionY, Color.GREEN));
		this.players.add(new Player(2, secondPositionX, secondScorePositionX, scorePositionY, Color.RED));
	}
	
	private void initializePaddle() {
		this.paddles.add(new Paddle(players.get(0), this.ball));
		if(this.gameForm == GameForm.MULTIPLAYER) this.paddles.add(new Paddle(players.get(1), this.ball));
		else this.paddles.add(new ComputerPaddle(players.get(1), GameLevel.BEGINNER, this.ball));
	}

	public GameForm getGameForm() {
		return this.gameForm;
	}

	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	public void setGame() {
		//this.ball = ball;
		//this.addPaddles(paddles);
		//this.addPlayers(players);
		this.setSteering(paddles);
		this.paddles.get(0).setRun(true);
		this.paddles.get(1).setRun(true);
		this.ball.setRun(true);
		System.out.println("SIEMA");
		paddleFirstThread = new Thread(paddles.get(0));
		ballThread = new Thread((RoundedBall)this.ball);
		paddleSecondThread = new Thread(paddles.get(1));
		paddleFirstThread.start();
		paddleSecondThread.start();
		ballThread.start();
		mainThread.start();
	}

	private void addPaddles(ArrayList<Paddle> paddles) {
		this.paddles.addAll(paddles);
	}

	private void addPlayers(ArrayList<Player> players) {
		this.players.addAll(players);
	}

	public void setSteering(ArrayList<Paddle> paddles) {
		steering = new Steering(paddles, this.getGameForm(), this);
		// this.addKeyListener(steering);
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public boolean isRun() {
		return this.run;
	}

	private int getPoints() {
		points = 0;
		for (int i = 0; i < players.size(); i++)
			points += players.get(i).getScore().getPoints();
		return points;
	}

	private void earnPoint() {
		pickedPlayer = this.ball.isMovingForward() ? 0 : 1;
		System.out.println("PICKEDPL: " + pickedPlayer);
		this.players.get(pickedPlayer).getScore().addPoint();
		if (!this.players.get(pickedPlayer).getScore().checkScore()) {
			this.ball.setStartingPosition(this.getPoints());
			System.out.println("PUNKT: " + this.getPoints());
			this.setRun(true);
		}
	}

	private void setHomePosition(Paddle paddle) {
		paddle.setHomePosition();
	}

	private void setBallSpeed(Paddle paddle) {
		if (paddle.pickup())
			this.ball.setSpeedX(this.ball.changeDirection(this.ball.getSpeedX()));
	}

	// Thread running
	@Override
	public void run() {
		while (isRun()) {
			/*try {
				ball.move();

				if (ball.earnPoint()) {
					Thread.sleep(1000);
					throw new InterruptedException();
				}
				paddles.forEach((paddle) -> setBallSpeed(paddle));
				if (this.getGameForm() == GameForm.SINGLE_PLAYER)
					((ComputerPaddle) this.paddles.get(1)).computerPaddleMove();
				Thread.sleep(Math.abs(this.ball.getSpeedX()));*/
				repaint();

			/*} catch (InterruptedException ex) {
				// gameStarted = false;
				this.setRun(false);
				this.points = 0;
				this.earnPoint();
				this.paddles.forEach((paddle) -> setHomePosition(paddle));
				repaint();
			}*/
		}
	}
}
