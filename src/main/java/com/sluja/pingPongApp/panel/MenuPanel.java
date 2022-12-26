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
	private Map<JButton, Integer> buttonArray = new LinkedHashMap<JButton, Integer>();

	public MenuPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.exitButton = new PanelButton("EXIT", this);
		this.singlePlayerButton = new PanelButton(GameForm.SINGLE_PLAYER.name(), this);
		this.multiPlayerButton = new PanelButton(GameForm.MULTIPLAYER.name(), this);
		this.buttonArray.put(exitButton,2);
		this.buttonArray.put(singlePlayerButton,1);
		this.buttonArray.put(multiPlayerButton,3);
		this.initializeListener();
		this.initializePanel();
	}

	private void initializeListener() {
		this.exitButton.addActionListener(this);
		this.singlePlayerButton.addActionListener(this);
		this.multiPlayerButton.addActionListener(this);
	}

	private void initializePanel() {
		for (int counter = 0; counter < buttonArray.size(); counter++)
			((JButton)this.buttonArray.keySet().toArray()[counter]).setSize(BUTTON_WIDTH, BUTTON_HEIGTH);
		int firstButtonPositionX = this.getGameFrame().getScreenWidth() / 4 - BUTTON_WIDTH / 2;
	}

	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == singlePlayerButton || e.getSource() == multiPlayerButton)
			System.out.println(((JButton) e.getSource()).getText());
		// this.getGameFrame().setGameForm(GameForm.SINGLE_PLAYER);
		// else if (e.getSource() == multiPlayerButton)
		// this.getGameFrame().setGameForm(GameForm.MULTIPLAYER);
		if (e.getSource() == exitButton)
			System.exit(0);

	}

}
