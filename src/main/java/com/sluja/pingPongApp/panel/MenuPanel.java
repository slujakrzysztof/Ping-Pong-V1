package com.sluja.pingPongApp.panel;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sluja.pingPongApp.button.PanelButton;
import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.properties.PropertyReader;

public class MenuPanel extends JPanel implements ActionListener {

	private final int BUTTON_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("button.width"));
	private final int BUTTON_HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("button.heigth"));
	private GameFrame gameFrame;
	private PanelButton exitButton;
	private PanelButton singlePlayerButton;
	private PanelButton multiPlayerButton;
	private ArrayList<PanelButton> buttonArray = new ArrayList<PanelButton>();

	public MenuPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.exitButton = new PanelButton("EXIT", this, 3, 200);
		this.singlePlayerButton = new PanelButton(GameForm.SINGLE_PLAYER.name(), this, 1, 200);
		this.multiPlayerButton = new PanelButton(GameForm.MULTIPLAYER.name(), this, 2, 200);
		this.buttonArray.add(exitButton);
		this.buttonArray.add(singlePlayerButton);
		this.buttonArray.add(multiPlayerButton);
		this.initializeListener();
		this.initializePanel();
	}

	private void initializeListener() {
		this.exitButton.addActionListener(this);
		this.singlePlayerButton.addActionListener(this);
		this.multiPlayerButton.addActionListener(this);
	}

	private void initializePanel() {
		PanelButton actualButton;
		for (int counter = 0; counter < buttonArray.size(); counter++) {
			actualButton = buttonArray.get(counter);
			actualButton.setSize(BUTTON_WIDTH, BUTTON_HEIGTH);
			actualButton.calculatePosition(200, 100);
		}
	}

	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == singlePlayerButton || e.getSource() == multiPlayerButton) {
			this.getGameFrame().setGameForm(GameForm.valueOf(((PanelButton) e.getSource()).getText()));
		}
		if (e.getSource() == exitButton)
			System.exit(0);

	}

}
