package com.sluja.pingPongApp.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JWindow;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.Player;
import com.sluja.pingPongApp.model.ball.RoundedBall;
import com.sluja.pingPongApp.model.paddle.Paddle;
import com.sluja.pingPongApp.panel.GamePanel;
import com.sluja.pingPongApp.properties.PropertyReader;

public class GameFrame extends JFrame {

	private final String TITLE = PropertyReader.getInstance().getProperty("title");
	private final int WINDOW_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.width"));
	private final int WINDOW_HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.heigth"));
	private final Dimension WINDOW_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private final int POSITION_X = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.WINDOW_WIDTH / 2);
	private final int POSITION_Y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.WINDOW_HEIGTH / 2);
	private GamePanel gamePanel;
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Paddle> paddles = new ArrayList<Paddle>();
	Ball ball;

	private int gameForm = 0;

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setSize((int)WINDOW_SIZE.getWidth(), (int)WINDOW_SIZE.getHeight());
		//this.setExtendedState(JFrame.); 
		//this.setUndecorated(true);
		gamePanel = new GamePanel(this, GameForm.SINGLE_PLAYER, GameLevel.BEGINNER);
		this.ball = new RoundedBall(GameLevel.BEGINNER);
		players.add(new Player(1));
		paddles.add(new Paddle(players.get(0), this.ball));
		gamePanel.setGame(this.ball, players, paddles);
		this.getContentPane().add(gamePanel);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(POSITION_X, POSITION_Y, WINDOW_WIDTH, WINDOW_HEIGTH);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	//	this.setVisible(true);
		//
	}
}