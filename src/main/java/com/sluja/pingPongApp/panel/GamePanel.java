package com.sluja.pingPongApp.panel;

import java.awt.Color;
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
import com.sluja.pingPongApp.steering.Steering;

public class GamePanel extends JPanel {

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
	private boolean gameFinished;

	public GamePanel(GameFrame gameFrame, GameForm gameForm, GameLevel gameLevel) {
		this.gameFrame = gameFrame;
		this.gameForm = gameForm;
		this.gameLevel = gameLevel;
		this.sumPoint = 0;
		this.gameFinished = false;
		this.setBackground(backgroundColor);
		this.setFocusable(true);
		this.ball = new RoundedBall(gameLevel, this);
		this.initializePlayersArray();
		this.initializePaddle();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // To change body of generated methods, choose Tools | Templates.
		this.paddles.forEach((paddle) -> paddle.draw(g));
		ball.draw(g); // drawing ball
		// if (ball.getScoreEnd())
		// ball.paintResult(g); // If game ends, the score with information will be
		// displayed
		 if (this.isGameFinished())
		 drawInfo(g); // Displaying informations at the beginning and at the end
	}
	
	private void drawInfo(Graphics g) {
		String text = "PRESS ESCAPE TO EXIT";
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        int textWidth = g.getFontMetrics().stringWidth(text);
		int textPositionX = this.gameFrame.getScreenWidth()/2 - textWidth/2;
        g.drawString(text, textPositionX, this.gameFrame.getScreenHeigth()/2);
	}

	public boolean isGameFinished() {
		return this.gameFinished;
	}
	
	private void initializePlayersArray() {
		int firstScorePositionX = (int) (this.gameFrame.getScreenWidth() * (0.25));
		int secondScorePositionX = (int) (this.gameFrame.getScreenWidth() * (0.75));
		int scorePositionY = (int) (this.gameFrame.getScreenHeigth() * (0.25));
		int secondPositionX = (int) (this.gameFrame.getScreenWidth()
				- Integer.parseInt(PropertyReader.getInstance().getProperty("paddle.width")) - 15);
		this.players.add(new Player(1, 0, firstScorePositionX, scorePositionY, Color.GREEN));
		this.players.add(new Player(2, secondPositionX, secondScorePositionX, scorePositionY, Color.RED));
	}

	private void initializePaddle() {
		this.paddles.add(new Paddle(players.get(0), this.ball));
		if (this.gameForm == GameForm.MULTIPLAYER)
			this.paddles.add(new Paddle(players.get(1), this.ball));
		else
			this.paddles.add(new ComputerPaddle(players.get(1), GameLevel.BEGINNER, this.ball));
	}

	public GameForm getGameForm() {
		return this.gameForm;
	}

	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	public void setGame() {
		this.setSteering(paddles);
		paddleFirstThread = new Thread(paddles.get(0));
		paddleSecondThread = new Thread(paddles.get(1));
		setRun(true);
		this.ball.generateReflectionAmount();
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
	}

	public boolean isRun() {
		return this.run;
	}
	
	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	private int getPoints() {
		points = 0;
		for (int i = 0; i < players.size(); i++)
			points += players.get(i).getScore().getPoints();
		return points;
	}
	
	public ArrayList<Paddle> getPaddles(){
		return this.paddles;
	}

	public void earnPoint() {
		if(this.ball.getPositionX() < this.getGameFrame().getScreenHeigth()/2) pickedPlayer = 1;
		else pickedPlayer = 0;
		this.players.get(pickedPlayer).getScore().addPoint();
		sumPoint = Math.abs(this.players.get(1).getScore().getPoints() - this.players.get(0).getScore().getPoints());
		if (!this.players.get(pickedPlayer).getScore().checkScore() || (sumPoint < 2)) {
			this.ball.setStartingPosition(this.getPoints());
			this.setRun(true);
		} else
			setGameFinished(true);
	}

	private void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

	public void setHomePosition() {
		paddles.forEach((paddle) -> paddle.setHomePosition());
	}


}
