package com.sluja.pingPongApp.panel;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.ball.RoundedBall;
import com.sluja.pingPongApp.properties.PropertyReader;
import com.sluja.pingPongApp.properties.SizeManager;

public class MainPanel extends JPanel {

	// ----- CONSTANTS ----- //
	public final String MENU_PANEL = PropertyReader.getInstance().getProperty("card.name.menuPanel");
	public final String GAME_PANEL = PropertyReader.getInstance().getProperty("card.name.gamePanel");
	private final int SCREEN_WIDTH = SizeManager.getInstance().SCREEN_WIDTH;
	private final int SCREEN_HEIGHT = SizeManager.getInstance().SCREEN_HEIGHT;
	
	// ----- Variables ----- //
	private CardLayout layout;
	private GamePanel gamePanel;
	private MenuPanel menuPanel;
	private GameFrame gameFrame;
	private GameForm gameForm;
	
	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //

	public MainPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		layout = new CardLayout();
		gamePanel = new GamePanel(gameFrame);
		menuPanel = new MenuPanel(this, gameFrame);
		this.setLayout(layout);
		this.add(gamePanel, GAME_PANEL);
		this.add(menuPanel, MENU_PANEL);
		this.showPanel(MENU_PANEL);
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //
	
	public GameForm getGameForm() {
		return this.gameForm;
	}
	
	public String getLabelMenuPanel() {
		return MENU_PANEL;
	}

	public String getLabelGamePanel() {
		return GAME_PANEL;
	}

	public MenuPanel getMenuPanel() {
		return this.menuPanel;
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
	
	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //
	
	public void showPanel(String panel) {
		((CardLayout) this.getLayout()).show(this, panel);
		if (panel.equals(GAME_PANEL)) {
			gamePanel.setFocusable(true);
		}
	}

	public void startGame(GameForm gameForm, GameLevel gameLevel, Ball ball) {
		this.getGamePanel().setGameForm(gameForm);
		if (gameForm == GameForm.SINGLE_PLAYER)
			this.getGamePanel().setGameLevel(gameLevel);
		this.getGamePanel().setBall(ball);
		this.getGamePanel().setGame();
	}

}
