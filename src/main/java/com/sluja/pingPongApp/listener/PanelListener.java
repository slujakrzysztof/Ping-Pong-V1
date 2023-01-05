package com.sluja.pingPongApp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sluja.pingPongApp.button.PanelButton;
import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.panel.MainPanel;
import com.sluja.pingPongApp.panel.MenuPanel;

public class PanelListener implements ActionListener {

	private String sourceName;
	private MainPanel mainPanel;
	private MenuPanel menuPanel;

	public PanelListener(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
		this.setMainPanel(this.menuPanel.getGameFrame().getMainPanel());
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.sourceName = e.getActionCommand().toString();
		if (sourceName.equals("SINGLE_PLAYER") || sourceName.equals("MULTIPLAYER")) {
			this.menuPanel.getGameFrame().getMainPanel().setGameForm(GameForm.valueOf(sourceName));
			this.menuPanel.getGameFrame().getMainPanel().startGame(this.menuPanel.getGameFrame().getMainPanel().getGameForm(), GameLevel.BEGINNER);;
			this.menuPanel.getGameFrame().getMainPanel()
					.showPanel(this.menuPanel.getGameFrame().getMainPanel().getLabelGamePanel());
		}

		if (sourceName.equals("EXIT"))
			System.exit(0);

	}

}
