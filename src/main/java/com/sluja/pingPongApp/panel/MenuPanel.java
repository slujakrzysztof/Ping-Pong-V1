package com.sluja.pingPongApp.panel;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.interfaces.Ball;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import com.sluja.pingPongApp.enums.BallModel;

public class MenuPanel extends JPanel implements ActionListener {

	private GameFrame gameFrame;

	private JButton exitButton;
	private JButton startButton;

	private JComboBox<BallModel> gameFormCheckBox;
	private JComboBox<GameLevel> gameLevelCheckBox;

	private ArrayList<AbstractButton> buttonArray = new ArrayList<AbstractButton>();

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JRadioButton singlePlayerRadioButton;
	private JRadioButton multiPlayerRadioButton;

	private MainPanel mainPanel;

	private Class ballClass;
	private Constructor ballClassConstructor;
	private Object ball;
	private GamePanel gamePanel;

	private boolean checkBoxEnabled;

	public MenuPanel(MainPanel mainPanel, GameFrame gameFrame) {
		setLayout(null);

		this.mainPanel = mainPanel;
		this.gameFrame = gameFrame;
		this.gamePanel = this.mainPanel.getGamePanel();

		this.gameLevelCheckBox = new JComboBox<GameLevel>();
		gameLevelCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		gameLevelCheckBox.setModel(new DefaultComboBoxModel(GameLevel.values()));
		gameLevelCheckBox.setBounds(40, 297, 310, 35);
		add(gameLevelCheckBox);

		this.gameFormCheckBox = new JComboBox<BallModel>();
		gameFormCheckBox.setModel(new DefaultComboBoxModel(BallModel.values()));
		gameFormCheckBox.setBounds(40, 392, 310, 35);
		add(gameFormCheckBox);

		JLabel gameLevelLabel = new JLabel("Game Level");
		gameLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLevelLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		gameLevelLabel.setBounds(40, 255, 310, 30);
		add(gameLevelLabel);

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

		singlePlayerRadioButton = new JRadioButton("SINGLE_PLAYER");
		singlePlayerRadioButton.setSelected(true);
		singlePlayerRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(singlePlayerRadioButton);
		singlePlayerRadioButton.setBounds(40, 209, 138, 23);
		add(singlePlayerRadioButton);

		multiPlayerRadioButton = new JRadioButton("MULTIPLAYER");
		multiPlayerRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(multiPlayerRadioButton);
		multiPlayerRadioButton.setBounds(241, 209, 109, 23);
		add(multiPlayerRadioButton);

		JLabel GameFormLabel = new JLabel("Game Form");
		GameFormLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GameFormLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GameFormLabel.setBounds(40, 157, 310, 30);
		add(GameFormLabel);

		JLabel ballModelLabel = new JLabel("Ball Model");
		ballModelLabel.setLabelFor(this);
		ballModelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ballModelLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		ballModelLabel.setBounds(40, 351, 310, 30);
		add(ballModelLabel);

		this.setButtonArray();
		this.setButtonListener();

	}

	public MainPanel getMainPanel() {
		return this.mainPanel;
	}

	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	private void setButtonArray() {
		this.buttonArray.add(startButton);
		this.buttonArray.add(exitButton);
		this.buttonArray.add(singlePlayerRadioButton);
		this.buttonArray.add(multiPlayerRadioButton);
	}

	private void setButtonListener() {
		this.buttonArray.forEach((button) -> button.addActionListener(this));
	}

	public GameLevel getGameLevel() {
		return (GameLevel) this.gameLevelCheckBox.getSelectedItem();
	}

	private String getSelectedButtonText() {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected())
				return button.getText();
		}
		return null;
	}

	private Ball getBall() {
		try {
			ballClass = Class.forName(this.getBallModel());
			ballClassConstructor = ballClass.getConstructor(GamePanel.class);
			ball = ballClassConstructor.newInstance(gamePanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Ball) this.ball;
	}

	public void setCheckBoxEnabled(boolean checkBoxEnabled) {
		this.checkBoxEnabled = checkBoxEnabled;
	}
	
	public boolean isCheckBoxEnabled() {
		return this.checkBoxEnabled;
	}

	private void setGameLevelEnabled() {
		if (this.multiPlayerRadioButton.isSelected())
			this.setCheckBoxEnabled(false);
		else
			this.setCheckBoxEnabled(true);
		
		this.gameLevelCheckBox.setEnabled(isCheckBoxEnabled());
	}

	public GameForm getGameForm() {
		return (GameForm) GameForm.valueOf(getSelectedButtonText());
	}

	public String getBallModel() {
		return ((BallModel) this.gameFormCheckBox.getSelectedItem()).getClassModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startButton) {
			this.getMainPanel().setGameForm(this.getGameForm());
			this.getMainPanel().startGame(this.getMainPanel().getGameForm(), this.getGameLevel(), this.getBall());
			this.getMainPanel().showPanel(this.getMainPanel().getLabelGamePanel());
		}

		if (e.getSource() == exitButton)
			System.exit(0);

		this.setGameLevelEnabled();
	}
}
