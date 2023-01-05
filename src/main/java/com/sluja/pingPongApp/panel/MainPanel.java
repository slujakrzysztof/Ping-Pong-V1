package com.sluja.pingPongApp.panel;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.properties.PropertyReader;
import com.sluja.pingPongApp.properties.SizeManager;

public class MainPanel extends JPanel {

	private CardLayout layout;
	public final String MENU_PANEL = PropertyReader.getInstance().getProperty("card.name.menuPanel");
	public final String GAME_PANEL = PropertyReader.getInstance().getProperty("card.name.gamePanel");
	private final int SCREEN_WIDTH = SizeManager.getInstance().SCREEN_WIDTH;
	private final int SCREEN_HEIGHT = SizeManager.getInstance().SCREEN_HEIGHT;
	
	private GamePanel gamePanel;
	private MenuPanel menuPanel;
	private GameFrame gameFrame;

	private GameForm gameForm;
	
	public MainPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		layout = new CardLayout();
		gamePanel = new GamePanel(gameFrame, GameForm.SINGLE_PLAYER, GameLevel.BEGINNER);
		menuPanel = new MenuPanel(gameFrame);
		this.setLayout(layout);
		this.add(gamePanel, GAME_PANEL);
		this.add(menuPanel, MENU_PANEL);
		this.showPanel(MENU_PANEL);
	}
	
	public GameForm getGameForm() {
		return this.gameForm;
	}
	
	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}

	public String getLabelMenuPanel() {
		return MENU_PANEL;
	}

	public String getLabelGamePanel() {
		return GAME_PANEL;
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	public void showPanel(String panel) {
		((CardLayout) this.getLayout()).show(this, panel);
		if (panel.equals(GAME_PANEL)) {
			gamePanel.setFocusable(true);
		}
	}

}
