package com.sluja.pingPongApp.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.model.paddle.Paddle;
import com.sluja.pingPongApp.steering.Steering;

public class GamePanel extends JPanel implements Runnable {

	GameFrame gameFrame;
	GameForm gameForm;
	GameLevel gameLevel;
	Color backgroundColor = Color.BLACK;
	Steering steering;
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

	public GameForm getGameForm() {
		return this.gameForm;
	}

	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	public void setGame(Ball ball, ArrayList<Player> players, ArrayList<Paddle> paddles) {
		this.ball = ball;
		this.addPaddles(paddles);
		this.addPlayers(players);
		this.setSteering(paddles);
		System.out.println("SIEMA");
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

	// Thread running
	@Override
	public void run() {
		while (isRun()) {
			try {
				ball.move();
				if (ball.earnPoint()) {
					Thread.sleep(1000);
					throw new InterruptedException();
				}
				for (int playerCounter = 0; playerCounter < paddles.size(); playerCounter++) {
					if (this.paddles.get(playerCounter).pickup())
						this.ball.setSpeedX(this.ball.changeDirection(this.ball.getSpeedX()));
				}
				Thread.sleep(Math.abs(ball.getSpeedX()));
				repaint();

			} catch (InterruptedException ex) {
				// gameStarted = false;
				this.setRun(false);
				this.points = 0;
				this.earnPoint();
				repaint();
			}
		}
	}
}
