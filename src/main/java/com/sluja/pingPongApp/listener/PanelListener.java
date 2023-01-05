package com.sluja.pingPongApp.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sluja.pingPongApp.button.PanelButton;
import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.panel.MainPanel;
import com.sluja.pingPongApp.panel.MenuPanel;

public class PanelListener implements ActionListener {

	private String sourceName;
	private MainPanel mainPanel;

	public PanelListener(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
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
		System.out.println("SOURCE: " + sourceName);

		if (sourceName.equals("SINGLE_PLAYER") || sourceName.equals("MULTIPLAYER")) {
			//this.getMainPanel().getGa
		}
		/*
		 * this.getGameFrame().setGameForm(GameForm.valueOf(((PanelButton)
		 * e.getSource()).getText()));
		 * this.getGameFrame().getMainPanel().getGamePanel().setGame();
		 * this.getGameFrame().getMainPanel().showPanel(this.getGameFrame().getMainPanel
		 * ().getLabelGamePanel());
		 */

		if (sourceName.equals("EXIT"))
			System.exit(0);

	}

}
