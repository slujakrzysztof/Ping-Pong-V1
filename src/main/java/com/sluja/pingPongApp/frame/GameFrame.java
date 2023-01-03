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
import com.sluja.pingPongApp.properties.SizeManager;

public class GameFrame extends JFrame {

	private final String TITLE = PropertyReader.getInstance().getProperty("title");
	private final int SCREEN_POSITION_X = SizeManager.getInstance().SCREEN_POSITION_X;
	private final int SCREEN_POSITION_Y = SizeManager.getInstance().SCREEN_POSITION_Y;
	private final int SCREEN_WIDTH = SizeManager.getInstance().SCREEN_WIDTH;
	private final int SCREEN_HEIGHT = SizeManager.getInstance().SCREEN_HEIGHT;

	private MainPanel mainPanel;

	private CardLayout layout;

	private GameForm gameForm;

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		layout = new CardLayout();
		this.setLayout(layout);
		mainPanel = new MainPanel(this);
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(SCREEN_POSITION_X, SCREEN_POSITION_Y, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	public MainPanel getMainPanel() {
		return this.mainPanel;
	}
}