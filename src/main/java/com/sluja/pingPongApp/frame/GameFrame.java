package com.sluja.pingPongApp.frame;

import java.awt.CardLayout;
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
import com.sluja.pingPongApp.panel.MainPanel;
import com.sluja.pingPongApp.panel.MenuPanel;
import com.sluja.pingPongApp.properties.PropertyReader;

public class GameFrame extends JFrame {

	private final String TITLE = PropertyReader.getInstance().getProperty("title");
	private final int WINDOW_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.width"));
	private final int WINDOW_HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.heigth"));
	private final Dimension WINDOW_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private final int POSITION_X = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.WINDOW_WIDTH / 2);
	private final int POSITION_Y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.WINDOW_HEIGTH / 2);
	private MainPanel mainPanel;

	private CardLayout layout;

	private GameForm gameForm;

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		layout = new CardLayout();
		this.setLayout(layout);
		//this.setSize((int)WINDOW_SIZE.getWidth(), (int)WINDOW_SIZE.getHeight());
		//this.setExtendedState(JFrame.); 
		//this.setUndecorated(true);
		mainPanel = new MainPanel(this);
		//gamePanel.setGame(this.ball, players, paddles);
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(POSITION_X, POSITION_Y, WINDOW_WIDTH, WINDOW_HEIGTH);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	//	this.setVisible(true);
		//
	}
	
	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}
	
	public int getScreenWidth() {
		return this.WINDOW_WIDTH;
	}
	
	public int getScreenHeigth() {
		return this.WINDOW_HEIGTH;
	}
	
	public MainPanel getMainPanel() {
		return this.mainPanel;
	}
}