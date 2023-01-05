package com.sluja.pingPongApp.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.listener.PanelListener;
import javax.swing.ImageIcon;

public class MenuPanel extends JPanel {

	private GameFrame gameFrame;

	private JButton exitButton;
	private JButton startButton;

	private JComboBox gameFormCheckBox;
	private JComboBox<GameLevel> gameLevelCheckBox;

	private ArrayList<JButton> buttonArray = new ArrayList<JButton>();

	private PanelListener panelListener;
	private GameLevel gameLevel;

	public MenuPanel(GameFrame gameFrame) {
		setLayout(null);

		this.gameFrame = gameFrame;
		this.panelListener = new PanelListener(this);

		this.gameLevelCheckBox = new JComboBox<GameLevel>();
		gameLevelCheckBox.setModel(new DefaultComboBoxModel(GameLevel.values()));
		gameLevelCheckBox.setBounds(40, 297, 310, 35);
		add(gameLevelCheckBox);

		this.gameFormCheckBox = new JComboBox();
		gameFormCheckBox.setBounds(40, 392, 310, 35);
		add(gameFormCheckBox);

		JLabel gameLevelLabel = new JLabel("Game Level");
		gameLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLevelLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		gameLevelLabel.setBounds(40, 255, 310, 30);
		add(gameLevelLabel);

		JLabel gameFormLabel = new JLabel("New label");
		gameFormLabel.setBounds(40, 353, 310, 30);
		add(gameFormLabel);

		this.startButton = new JButton("START");
		startButton.setBounds(40, 472, 310, 50);
		add(startButton);

		this.exitButton = new JButton("EXIT");
		exitButton.setBounds(40, 533, 310, 50);
		add(exitButton);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(MenuPanel.class.getResource("/com/sluja/pingPongApp/image/gameIcon.png")));
		imageLabel.setBounds(500, 60, 480, 500);
		add(imageLabel);

		JLabel titleLabel = new JLabel("New label");
		titleLabel.setBounds(60, 30, 410, 60);
		add(titleLabel);

		this.setButtonArray();
		this.setButtonListener();

	}
	
	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	private void setButtonArray() {
		this.buttonArray.add(startButton);
		this.buttonArray.add(exitButton);
	}

	private void setButtonListener() {
		this.buttonArray.forEach((button) -> button.addActionListener(panelListener));
	}

	public void setGameLevel(GameLevel gameLevel) {
		this.gameLevel = gameLevel;
	}

	public GameLevel getGameLevel() {
		return (GameLevel) this.gameLevelCheckBox.getSelectedItem();
	}
}
