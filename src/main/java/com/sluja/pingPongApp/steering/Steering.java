package com.sluja.pingPongApp.steering;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.model.paddle.Paddle;
import com.sluja.pingPongApp.panel.GamePanel;

public class Steering {

	// ----- Variables ----- //
	private ArrayList<Paddle> paddles;
	private GameForm gameForm;
	private InputMap inputMap;
	private ActionMap actionMap;
	private GamePanel gamePanel;

	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //
	
	public Steering(ArrayList<Paddle> paddles, GameForm gameForm, GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.paddles = paddles;
		this.gameForm = gameForm;
		inputMap = gamePanel.getInputMap(2);
		actionMap = gamePanel.getActionMap();
		this.setSteering();
		this.initializeActionMap();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //
	
	public GameForm getGameForm() {
		return this.gameForm;
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	public ArrayList<Paddle> getPaddles() {
		return this.paddles;
	}
	
	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	private void setSteering() {

		this.singlePlayerSteering();
		if (this.getGameForm() == GameForm.MULTIPLAYER)
			this.multiPlayerSteering();
	}
	
	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //
	
	private void moveDown(int index) {
		if (!getPaddles().get(index).checkLowerPosition())
			getPaddles().get(index).moveDown();
	}

	private void moveUp(int index) {
		if (!getPaddles().get(index).checkUpperPosition())
			getPaddles().get(index).moveUp();
	}

	private void initializeActionMap() {
		actionMap.put("movingUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveUp(0);
			}
		});

		actionMap.put("movingDown", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveDown(0);
			}
		});

		actionMap.put("movingUpSecondPlayer", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveUp(1);
			}
		});

		actionMap.put("movingDownSecondPlayer", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveDown(1);
			}
		});

	}
	
	private void singlePlayerSteering() {
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "movingUp");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "movingDown");
	}

	private void multiPlayerSteering() {
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "movingUpSecondPlayer");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "movingDownSecondPlayer");
	}

}
